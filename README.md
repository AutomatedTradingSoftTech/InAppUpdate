## InAppUpdate
Library for Playstore updates forcefully

## Download

### Add below code in (Project) build.gradle file
~~~
allprojects {
    repositories {
          maven {
              url 'https://jitpack.io' 
          }
     }
}
~~~

### Add below dependency in (Module) build.gradle
~~~
implementation 'com.github.AutomatedTradingSoftTech:InAppUpdate:0.0.9'
~~~

## Configuration

### Add below code in AndroidManifest.xml
~~~
<activity android:name="com.autotradetech.inappupdate.CheckPlaystoreUpdateActivity"
            android:screenOrientation="portrait"
            android:theme="@style/Theme.Transparent"/>
~~~

### Add below code in Style.xml
~~~
<style name="Theme.Transparent"  parent="Theme.AppCompat.Light.NoActionBar">
        <item name="android:windowIsTranslucent">true</item>
        <item name="android:windowBackground">@android:color/transparent</item>
        <item name="android:windowContentOverlay">@null</item>
        <item name="android:windowNoTitle">true</item>
        <item name="android:windowIsFloating">true</item>
        <item name="android:backgroundDimEnabled">false</item>
</style>
~~~


### Add below code in your Activity to check update is available or not.

~~~
startActivityForResult(Intent(this, CheckPlaystoreUpdateActivity::class.java), 100)
~~~

### Put below code in onActivityResult()
~~~
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 100) {
                Log.e("Result: ", data!!.getStringExtra("Result"))
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
                        // if you want to request the update again just start CheckPlaystoreUpdateActivity again
                        startActivityForResult(Intent(this, CheckPlaystoreUpdateActivity::class.java), 100)
                    }
                    "" -> {
                    }
                }
            }
        }
}
~~~
