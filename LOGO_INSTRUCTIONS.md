# SkillUp Logo Installation Instructions

## Adding the SkillUp Logo as PNG

Since the image file cannot be automatically saved, please follow these steps to add the actual SkillUp logo PNG image to your project:

### Step 1: Save the Logo Image

1. Save the SkillUp logo image from the conversation as `skillup_logo.png`
2. Make sure it's a high-resolution PNG (at least 512x512px)

### Step 2: Add Logo to Drawable Folder

Copy the PNG file to:
```
app/src/main/res/drawable/skillup_logo.png
```

### Step 3: Generate App Launcher Icons (Recommended Method)

#### Using Android Studio Image Asset Studio:

1. Right-click on `res` folder
2. Select **New → Image Asset**
3. Choose **Launcher Icons (Adaptive and Legacy)**
4. Click **Path** and browse to your saved `skillup_logo.png`
5. Adjust the trim and padding as needed
6. Click **Next** → **Finish**

This will automatically generate all required icon sizes:
- `mipmap-mdpi/ic_launcher.png` (48x48)
- `mipmap-hdpi/ic_launcher.png` (72x72)
- `mipmap-xhdpi/ic_launcher.png` (96x96)
- `mipmap-xxhdpi/ic_launcher.png` (144x144)
- `mipmap-xxxhdpi/ic_launcher.png` (192x192)

### Step 4: Manual Icon Creation (Alternative)

If you prefer manual creation, resize the logo to these dimensions and place in respective folders:

```
app/src/main/res/mipmap-mdpi/ic_launcher.png (48x48)
app/src/main/res/mipmap-hdpi/ic_launcher.png (72x72)
app/src/main/res/mipmap-xhdpi/ic_launcher.png (96x96)
app/src/main/res/mipmap-xxhdpi/ic_launcher.png (144x144)
app/src/main/res/mipmap-xxxhdpi/ic_launcher.png (192x192)
```

### Step 5: Replace XML Vector Drawable (Optional)

The project currently uses an XML vector drawable (`skillup_logo.xml`). To use the PNG instead:

1. Delete or rename `app/src/main/res/drawable/skillup_logo.xml`
2. Add your PNG as `app/src/main/res/drawable/skillup_logo.png`

The app will automatically use the PNG version.

## Verification

After adding the logo:

1. Clean and rebuild the project:
   ```batch
   gradlew.bat clean
   gradlew.bat build
   ```

2. Check that:
   - App icon appears correctly on the home screen
   - Certificate displays the SkillUp logo
   - Logo renders clearly on different screen densities

## Logo Usage in Code

The logo is referenced in:

- **App Icon**: `AndroidManifest.xml` → `android:icon="@mipmap/ic_launcher"`
- **Certificate**: `activity_certificate.xml` → `android:src="@drawable/skillup_logo"`
- **Other Activities**: Can use `@drawable/skillup_logo` anywhere

## Color Scheme

SkillUp Brand Colors:
- **Primary Blue**: `#003A70` (Dark blue)
- **Secondary Blue**: `#0D47A1` (Medium blue)
- **White**: `#FFFFFF`
- **Text Gray**: `#666666`

These colors are used throughout the certificate and app UI.

## Troubleshooting

**Issue**: Icons not appearing after adding PNG
- **Solution**: Clean and rebuild project, invalidate caches (File → Invalidate Caches → Restart)

**Issue**: Logo appears pixelated
- **Solution**: Ensure PNG is high resolution (512x512 or higher), use Image Asset Studio for proper scaling

**Issue**: Certificate logo too large/small
- **Solution**: Adjust `android:layout_width` and `android:layout_height` in `activity_certificate.xml`

