IndependentTime
===============			

## Use
- Import ibrary module in your project
- add permission 
```xml
<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED"/>
```

- add receiver in to your AndroidManifest.xml       
```xml
 <receiver android:name="com.useit.independenttime.TimeReceiver" >
            <intent-filter>
                <action android:name="android.intent.action.BOOT_COMPLETED"/>
                <action android:name="android.intent.action.ACTION_SHUTDOWN"/>
                <action android:name="com.useit.independenttime.ACTION_TO_UPDATE_TIME"/>
            </intent-filter>
  </receiver>
```

        
- Set or update server time  

```java
final long someServerTime = System.currentTimeMillis();//or get time from your server
IndependentTimeHelper.setServerTime(this, someServerTime);
```

- Get updated  local time   

```java
final long localTime = IndependentTimeHelper.getTime(this);
```
