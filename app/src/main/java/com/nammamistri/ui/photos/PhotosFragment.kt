package com.nammamistri.ui.photos

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.fragment.app.Fragment
import com.nammamistri.R
import com.nammamistri.databinding.FragmentPhotosBinding

class PhotosFragment : Fragment(R.layout.fragment_photos) {

    private var _binding: FragmentPhotosBinding? = null
    private val binding get() = _binding!!

    private val CAMERA_REQUEST = 100

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentPhotosBinding.bind(view)

        binding.btnCapture.setOnClickListener {

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            startActivityForResult(intent, CAMERA_REQUEST)
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CAMERA_REQUEST &&
            resultCode == Activity.RESULT_OK
        ) {

            val photo = data?.extras?.get("data") as Bitmap

            binding.imagePreview.setImageBitmap(photo)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}