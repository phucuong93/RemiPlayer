package com.remi.player.framework.permission

fun interface PermissionManager {
    fun checkAndRequestStoragePermissions(onAllPermissionGranted: () -> Unit)
}
