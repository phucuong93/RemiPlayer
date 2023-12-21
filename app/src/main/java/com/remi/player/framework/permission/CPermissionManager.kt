package com.remi.player.framework.permission

import android.Manifest
import android.content.Context
import android.util.Log
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CPermissionManager @Inject constructor(@ApplicationContext val context: Context) :
    PermissionManager {

    private val storagePermission = mutableListOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    override fun checkAndRequestStoragePermissions(onAllPermissionGranted: () -> Unit) {
        Dexter.withContext(context)
            .withPermissions(
                storagePermission
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    Log.e("test", "requested all permission onPermissionsChecked")
                    onAllPermissionGranted()
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest?>?,
                    token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }
            }).check()
    }
}
