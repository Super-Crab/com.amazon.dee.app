package com.amazon.dee.app.elements.bridges;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationManagerCompat;
import com.amazon.dee.app.elements.CollectionsFactory;
import com.amazon.dee.app.services.logging.Log;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
@ReactModule(name = "NotificationSettings")
/* loaded from: classes12.dex */
public class NotificationSettingsModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    public static final String ARE_NOTIFICATIONS_ENABLED = "areNotificationsEnabled";
    static final String ERROR_UNKNOWN = "Unknown";
    private static final String MODULE_NAME = "NotificationSettingsModule";
    public static final String TAG = "NotificationSettingsModule";
    private final CollectionsFactory collectionsFactory;
    private Promise lastNavigateToGlobalNotificationSettingsPromise;
    private final NotificationManagerCompat notificationManagerCompat;
    private final ReactApplicationContext reactApplicationContext;

    public NotificationSettingsModule(ReactApplicationContext reactApplicationContext, CollectionsFactory collectionsFactory) {
        super(reactApplicationContext);
        this.reactApplicationContext = reactApplicationContext;
        this.collectionsFactory = collectionsFactory;
        this.notificationManagerCompat = NotificationManagerCompat.from(reactApplicationContext);
        reactApplicationContext.addActivityEventListener(this);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "NotificationSettingsModule";
    }

    @ReactMethod
    public void getNotificationSettings(Promise promise) {
        WritableMap createMap = this.collectionsFactory.createMap();
        createMap.putBoolean(ARE_NOTIFICATIONS_ENABLED, this.notificationManagerCompat.areNotificationsEnabled());
        promise.resolve(createMap);
    }

    @ReactMethod
    @SuppressFBWarnings({"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"})
    public void navigateToGlobalNotificationSettings(Promise promise) {
        Intent intent = new Intent();
        int i = Build.VERSION.SDK_INT;
        intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
        intent.putExtra("android.provider.extra.APP_PACKAGE", this.reactApplicationContext.getPackageName());
        if (getCurrentActivity() != null) {
            Activity currentActivity = getCurrentActivity();
            this.lastNavigateToGlobalNotificationSettingsPromise = promise;
            currentActivity.startActivityForResult(intent, 10);
            return;
        }
        promise.reject("Error - Cannot find current activity", "Unknown");
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        try {
            if (this.lastNavigateToGlobalNotificationSettingsPromise == null) {
                Log.e("NotificationSettingsModule", "No matching promise for activity");
            } else if (i != 10) {
                this.lastNavigateToGlobalNotificationSettingsPromise.reject("Invalid request code", "Unknown");
            } else {
                this.lastNavigateToGlobalNotificationSettingsPromise.resolve(null);
            }
        } finally {
            this.lastNavigateToGlobalNotificationSettingsPromise = null;
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }
}
