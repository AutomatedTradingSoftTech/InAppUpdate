package com.autotradetech.inappupdate

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.ActivityResult
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability

class CheckPlaystoreUpdateActivity : AppCompatActivity() {
    val MY_REQUEST_CODE = 1
    internal lateinit var appUpdateManager: AppUpdateManager
    val TAG = CheckPlaystoreUpdateActivity::class.java.simpleName
    //  lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CheckPlaystoreUpdate()
    }

    fun CheckPlaystoreUpdate() {

        appUpdateManager = AppUpdateManagerFactory.create(this)

        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        appUpdateManager.registerListener(listener)
        appUpdateInfoTask?.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                Log.d(TAG, "Update available")
                Toast.makeText(this, "Updates available", Toast.LENGTH_SHORT).show()

                appUpdateManager.startUpdateFlowForResult(
                    // Pass the intent that is returned by 'getAppUpdateInfo()'.
                    appUpdateInfo,
                    // Or 'AppUpdateType.FLEXIBLE' for flexible updates.
                    AppUpdateType.IMMEDIATE,
                    // The current activity making the update request.
                    this,
                    // Include a request code to later monitor this update request.
                    MY_REQUEST_CODE
                )
            } else {
                Log.d(TAG, "No Update available")
                // here redirect to login or dashboard if no update
                //  fetchDataFromServer()
                val intentData = Intent()
                intentData.putExtra("Result", "NoUpdate")
                setResult(Activity.RESULT_OK, intentData)
                finish()
                Toast.makeText(this, "No updates available", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private val listener: InstallStateUpdatedListener? =
        InstallStateUpdatedListener { installState ->
            if (installState.installStatus() == com.google.android.play.core.install.model.InstallStatus.DOWNLOADED) {
                // After the update is downloaded, show a notification
                // and request user confirmation to restart the app.
                Log.d(TAG, "An update has been downloaded")
                appUpdateManager.completeUpdate()
                Toast.makeText(
                    this,
                    "Update has been downloaded restart your app",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MY_REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {

                    Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "" + "Result Ok")
                    //  handle user's approval }

                    val intentData = Intent()
                    intentData.putExtra("Result", "ResultOk")
                    setResult(Activity.RESULT_OK, intentData)
                    finish()
                }
                Activity.RESULT_CANCELED -> {
                    Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "" + "Result Cancelled")
                    //  handle user's rejection  }

                    val intentData = Intent()
                    intentData.putExtra("Result", "Canceled")
                    setResult(Activity.RESULT_OK, intentData)
                    finish()
                }
                ActivityResult.RESULT_IN_APP_UPDATE_FAILED -> {
                    Toast.makeText(
                        this,
                        "Update fail, please wait",
                        Toast.LENGTH_SHORT
                    ).show()


                    // here check update again if update is fail
                    //if you want to request the update again just call checkUpdate()
                    //  handle update failure

                    val intentData = Intent()
                    intentData.putExtra("Result", "CheckUpdateAgain")
                    setResult(Activity.RESULT_OK, intentData)
                    finish()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            appUpdateManager.unregisterListener(listener)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}