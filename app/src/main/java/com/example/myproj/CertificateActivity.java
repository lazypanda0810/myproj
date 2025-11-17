package com.example.myproj;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CertificateActivity extends AppCompatActivity {

    public static final String EXTRA_STUDENT_NAME = "extra_student_name";
    public static final String EXTRA_COURSE_NAME = "extra_course_name";
    public static final String EXTRA_COMPLETION_PERCENTAGE = "extra_completion_percentage";
    public static final String EXTRA_TEST_SCORE = "extra_test_score";

    private static final String TAG = "CertificateActivity"; // For logging

    private LinearLayout certificateLayout;
    private String studentName;
    private String courseName;

    // Executor for background tasks
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificate);

        certificateLayout = findViewById(R.id.certificate_layout);
        TextView tvStudentName = findViewById(R.id.tv_student_name);
        TextView tvCourseName = findViewById(R.id.tv_course_name);
        TextView tvCompletionDate = findViewById(R.id.tv_completion_date);
        TextView tvCompletionPercentage = findViewById(R.id.tv_completion_percentage);
        TextView tvTestScore = findViewById(R.id.tv_test_score);
        Button btnDownload = findViewById(R.id.btn_download_certificate);
        Button btnShare = findViewById(R.id.btn_share_certificate);

        studentName = getIntent().getStringExtra(EXTRA_STUDENT_NAME);
        courseName = getIntent().getStringExtra(EXTRA_COURSE_NAME);
        int completionPercentage = getIntent().getIntExtra(EXTRA_COMPLETION_PERCENTAGE, 0);
        int testScore = getIntent().getIntExtra(EXTRA_TEST_SCORE, 0);


        if (studentName == null || studentName.isEmpty()) {
            studentName = getString(R.string.demo_user); // Use string resource
        }

        tvStudentName.setText(studentName);
        tvCourseName.setText(courseName);

        tvCompletionPercentage.setText(getString(R.string.course_completion_percentage, completionPercentage));
        tvTestScore.setText(getString(R.string.test_score, testScore));

        String currentDate = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());
        tvCompletionDate.setText(getString(R.string.completion_date, currentDate));

        btnDownload.setOnClickListener(v -> generateCertificate(false));
        btnShare.setOnClickListener(v -> generateCertificate(true));
    }

    private void generateCertificate(boolean isShare) {
        // Capture the view on the UI thread first
        final Bitmap bitmap = getBitmapFromView(certificateLayout);

        executorService.execute(() -> {
            // All file operations are now on a background thread
            String fileName = "Certificate_" + studentName.replace(" ", "_") + "_" + courseName.replace(" ", "_") + ".pdf";
            Uri uri = null;

            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    ContentResolver resolver = getContentResolver();
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);
                    values.put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf");
                    values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS);

                    uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values);
                    if (uri == null) {
                        throw new IOException("Failed to create new MediaStore record.");
                    }
                    try (OutputStream outputStream = resolver.openOutputStream(uri)) {
                        writePdf(outputStream, bitmap);
                    }
                } else {
                    File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                    File file = new File(downloadsDir, fileName);
                    try (OutputStream outputStream = new FileOutputStream(file)) {
                        writePdf(outputStream, bitmap);
                    }
                    uri = Uri.fromFile(file);
                }

                final Uri finalUri = uri;
                runOnUiThread(() -> {
                    if (isShare) {
                        shareCertificate(finalUri);
                    } else {
                        Toast.makeText(this, R.string.certificate_downloaded, Toast.LENGTH_LONG).show();
                    }
                });

            } catch (IOException e) {
                // If there's an error, attempt to delete the incomplete file
                if (uri != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    getContentResolver().delete(uri, null, null);
                }
                Log.e(TAG, "Error generating certificate PDF", e);
                runOnUiThread(() -> Toast.makeText(this, getString(R.string.error_generating_certificate, e.getMessage()), Toast.LENGTH_LONG).show());
            }
        });
    }

    private void writePdf(OutputStream outputStream, Bitmap bitmap) throws IOException {
        if (outputStream == null) {
            throw new IOException("Failed to get output stream.");
        }
        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(), 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        canvas.drawBitmap(bitmap, 0, 0, null);
        document.finishPage(page);
        document.writeTo(outputStream);
        document.close();
    }

    private void shareCertificate(Uri uri) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("application/pdf");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_certificate)));
    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }
}
