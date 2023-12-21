package com.remi.player.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.remi.player.presentation.utils.component.UiText
import com.remi.player.framework.permission.PermissionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var permissionManager: PermissionManager

    private val _snackBarUIState: MutableSharedFlow<UiText> = MutableSharedFlow()
    val snackBarUIState: SharedFlow<UiText>
        get() = _snackBarUIState.asSharedFlow()

    fun showSnackBar(value: UiText) {
        viewModelScope.launch {
            _snackBarUIState.emit(value)
        }
    }

    fun onRequestAllPermission(onAllPermissionGranted: () -> Unit) {
        permissionManager.checkAndRequestStoragePermissions {
            onAllPermissionGranted()
        }
    }
}
