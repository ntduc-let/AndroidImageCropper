Android Image Cropper
=======

**Powerful** (Zoom, Rotation, Multi-Source), **customizable** (Shape, Limits, Style), **optimized** (Async, Sampling, Matrix) and **simple** image cropping library for Android.

![Crop](https://github.com/ArthurHub/Android-Image-Cropper/blob/master/art/demo.gif?raw=true)

## Usage
*For a working implementation, please have a look at the Sample Project*

[See GitHub Wiki for more info.](https://github.com/ArthurHub/Android-Image-Cropper/wiki)

1. Include the library
Include this in your root `build.gradle`

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
Include this in your app `build.gradle`

```
dependencies {
    // Other dependencies
    implementation 'com.github.ntduc-let:AndroidImageCropper:1.0.0'
}
```

Add permissions to manifest

 ```
 <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
 <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
 ```
Add this line to your Proguard config file

```
-keep class androidx.appcompat.widget.** { *; }
```
### Using Activity

2. Add `CropImageActivity` into your AndroidManifest.xml
 ```xml
 <activity android:name="com.ntduc.androidimagecropper.CropImageActivity"
   android:theme="@style/Base.Theme.AppCompat"/> <!-- optional (needed if default theme has no action bar) -->
 ```

3. Start `CropImageActivity` using builder pattern from your activity
 ```java
 // start picker to get image for cropping and then use the image in cropping activity
 CropImage.activity()
   .setGuidelines(CropImageView.Guidelines.ON)
   .start(this);

 // start cropping activity for pre-acquired image saved on the device
 CropImage.activity(imageUri)
  .start(this);

 // for fragment (DO NOT use `getActivity()`)
 CropImage.activity()
   .start(getContext(), this);
 ```

4. Override `onActivityResult` method in your activity to get crop result
 ```java
 @Override
 public void onActivityResult(int requestCode, int resultCode, Intent data) {
   if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
     CropImage.ActivityResult result = CropImage.getActivityResult(data);
     if (resultCode == RESULT_OK) {
       Uri resultUri = result.getUri();
     } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
       Exception error = result.getError();
     }
   }
 }
 ```

### Using View
2. Add `CropImageView` into your activity
 ```xml
 <!-- Image Cropper fill the remaining available height -->
 <com.ntduc.androidimagecropper.CropImageView
   xmlns:custom="http://schemas.android.com/apk/res-auto"
   android:id="@+id/cropImageView"
   android:layout_width="match_parent"
   android:layout_height="0dp"
   android:layout_weight="1"/>
 ```

3. Set image to crop
 ```java
 cropImageView.setImageUriAsync(uri);
 // or (prefer using uri for performance and better user experience)
 cropImageView.setImageBitmap(bitmap);
 ```

4. Get cropped image
 ```java
 // subscribe to async event using cropImageView.setOnCropImageCompleteListener(listener)
 cropImageView.getCroppedImageAsync();
 // or
 Bitmap cropped = cropImageView.getCroppedImage();
 ```
