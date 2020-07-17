## InAppUpdate
Library for Playstore updates forcefully.

## Download
Retrofit requires at minimum Java 8+ or Android API 16+.

### 1) Add below code in (Project) build.gradle file
~~~
allprojects {
    repositories {
          maven {
              url 'https://jitpack.io' 
          }
     }
}
~~~

### 2) Add below dependency in (Module) build.gradle
~~~
implementation 'com.github.AutomatedTradingSoftTech:InAppUpdate:0.0.9'
~~~

## Configuration

### 1) Add below code in AndroidManifest.xml
~~~
<activity android:name="com.autotradetech.inappupdate.CheckPlaystoreUpdateActivity"
            android:screenOrientation="portrait"
            android:theme="@style/Theme.Transparent"/>
~~~

### 2) Add below code in Style.xml
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


### 3) Add below code in your Activity to check update is available or not.

~~~
startActivityForResult(Intent(this, CheckPlaystoreUpdateActivity::class.java), 100)
~~~

### 4) Put below code in onActivityResult()
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

## Licence

~~~
Copyright 2020 Automated Trading Soft Tech Pvt. Ltd.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
~~~
