package com.ntduc.androidimagecropper

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ntduc.androidimagecropper.CropImage.activity
import com.ntduc.androidimagecropper.CropImage.getActivityResult

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /** Start pick image activity with chooser.  */
    fun onSelectImageClick(view: View?) {
        activity(null).setGuidelines(CropImageView.Guidelines.ON).start(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // handle result of CropImageActivity
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = getActivityResult(data)
            if (resultCode == RESULT_OK) {
                (findViewById<View>(R.id.quick_start_cropped_image) as ImageView).setImageURI(result!!.uri)
                Toast.makeText(
                    this, "Cropping successful, Sample: " + result.sampleSize, Toast.LENGTH_LONG
                )
                    .show()
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " + result!!.error, Toast.LENGTH_LONG).show()
            }
        }
    }
}