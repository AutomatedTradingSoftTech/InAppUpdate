package com.autotradetech.inappupdatedemo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.autotradetech.inappupdate.CheckPlaystoreUpdateActivity

class MainActivity : AppCompatActivity() {

    var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivityForResult(Intent(this, CheckPlaystoreUpdateActivity::class.java), 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 100) {
                Log.e(TAG, data!!.getStringExtra("Result"))
                when (data!!.getStringExtra("Result")) {
                    "NoUpdate" -> {
                        // if no update
                        // perform your regular flow from here
                    }
                    "ResultOk" -> {
                        //  handle user's approval
                    }
                    "Canceled" -> {
                        //  handle user's rejection
                    }
                    "CheckUpdateAgain" -> {
                        // here check update again if update is fail
                        //if you want to request the update again just start CheckPlaystoreUpdateActivity again
                    }
                    "" -> {

                    }
                }
            }
        }
    }
}