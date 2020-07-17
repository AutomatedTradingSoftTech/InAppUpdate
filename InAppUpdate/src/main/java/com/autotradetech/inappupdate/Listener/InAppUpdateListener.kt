package com.autotradetech.inappupdate.Listener

interface InAppUpdateListener {
    fun onSuccess()
    fun onFailure(errorMessage : String)
    fun onCheckUpdateAgain()
    fun onNoUpdateAvailable()
}