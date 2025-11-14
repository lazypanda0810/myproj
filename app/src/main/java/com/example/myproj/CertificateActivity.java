package com.example.myproj;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CertificateActivity extends AppCompatActivity {

    public static final String EXTRA_STUDENT_NAME = "extra_student_name";
    public static final String EXTRA_COURSE_NAME = "extra_course_name";
    public static final String EXTRA_COMPLETION_PERCENTAGE = "extra_completion_percentage";
    public static final String EXTRA_TEST_SCORE = "extra_test_score";

    private LinearLayout certificateLayout;
    private String studentName;
    private String courseName;

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
            studentName = "Demo User"; // Default value
        }

        tvStudentName.setText(studentName);
        tvCourseName.setText(courseName);
        tvCompletionPercentage.setText("Course Completion: " + completionPercentage + "%");
        tvTestScore.setText("Test Score: " + testScore + "%");

        String currentDate = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());
        tvCompletionDate.setText("Completion Date: " + currentDate);

        btnDownload.setOnClickListener(v -> generateCertificate(false));
        btnShare.setOnClickListener(v -> generateCertificate(true));
    }

    private void generateCertificate(boolean isShare) {
        Bitmap bitmap = getBitmapFromView(certificateLayout);

        if (bitmap != null) {
            PdfDocument document = new PdfDocument();
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(), 1).create();
            PdfDocument.Page page = document.startPage(pageInfo);

            Canvas canvas = page.getCanvas();
            canvas.drawBitmap(bitmap, 0, 0, null);
            document.finishPage(page);

            String fileName = "Certificate_" + studentName.replace(" ", "_") + "_" + courseName.replace(" ", "_") + ".pdf";

            try {
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);
                FileOutputStream fos = new FileOutputStream(file);
                document.writeTo(fos);
                document.close();
                fos.close();

                if (isShare) {
                    shareCertificate(file);
                } else {
                    Toast.makeText(this, "🎉 Your certificate has been downloaded!", Toast.LENGTH_LONG).show();
                }

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error generating certificate: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void shareCertificate(File file) {
        Uri contentUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", file);

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("application/pdf");
        shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        startActivity(Intent.createChooser(shareIntent, "Share Certificate"));
    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }
}
