package com.example.jetpacknoteapp.utils
interface PermissionListener {

    fun   isPermissionGranted(isGranted : Boolean)

    fun   shouldShowRationaleInfo()
}