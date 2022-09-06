package com.amazon.alexa.handsfree.notification.notificationmetadata;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.notification.R;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public enum NotificationMetadata {
    TERMS_AND_CONDITIONS(R.string.empty_notification_title, R.string.handsfree_setup_notification_text),
    RECORD_AUDIO_PERMISSIONS(R.string.empty_notification_title, R.string.handsfree_setup_notification_text),
    RECORD_LOCATION_PERMISSION(R.string.empty_notification_title, R.string.handsfree_setup_notification_text),
    HANDS_FREE_INTRO(R.string.empty_notification_title, R.string.handsfree_setup_notification_text),
    LANGUAGE_SELECTOR(R.string.empty_notification_title, R.string.handsfree_setup_notification_text),
    USER_VERIFICATION_ENROLLMENT(R.string.empty_notification_title, R.string.handsfree_setup_notification_text),
    ALEXA_APP_INSTALL(R.string.empty_notification_title, R.string.alexa_app_download_notification_text),
    ALEXA_APP_ENABLE(R.string.empty_notification_title, R.string.alexa_app_enable_notification_text),
    ALEXA_APP_UPDATE(R.string.empty_notification_title, R.string.alexa_app_update_notification_text),
    ALEXA_APP_SIGN_IN(R.string.empty_notification_title, R.string.handsfree_setup_notification_text),
    KILL_SWITCH_OFF(R.string.empty_notification_title, R.string.kill_switch_off_notification_text),
    KILL_SWITCH_ON(R.string.empty_notification_title, R.string.kill_switch_on_notification_text),
    VENDOR_SPECIFIC(R.string.empty_notification_title, R.string.handsfree_setup_notification_text),
    SHOW_ON_LOCKSCREEN(R.string.empty_notification_title, R.string.show_on_lockscreen_notification_text),
    QUICK_SETTINGS(R.string.empty_notification_title, R.string.quick_settings_notification_text),
    PERMISSION_REQUEST(R.string.permission_request_notification_title, R.string.permission_request_notification_text),
    PROFILE_SELECTION(R.string.empty_notification_title, R.string.handsfree_setup_notification_text),
    LOCK_SCREEN_SETTING(R.string.empty_notification_title, R.string.handsfree_setup_notification_text),
    ENABLE_HANDS_FREE(R.string.empty_notification_title, R.string.enable_hands_free_notification_text),
    DYNAMIC_LANGUAGE_SWITCHING(R.string.dynamic_language_switching_title, R.string.dynamic_language_switching_text);
    
    @DrawableRes
    public static final int INVALID_ICON_ASSET = -1;
    public static final String NOTIFICATION_ICON_METADATA_NAME = "setup_notification_icon";
    private static final String TAG = NotificationMetadata.class.getSimpleName();
    private final int mTextResource;
    private final int mTitleResource;

    NotificationMetadata(int i, int i2) {
        this.mTitleResource = i;
        this.mTextResource = i2;
    }

    @DrawableRes
    public int getNotificationIconAsset(@NonNull Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                return applicationInfo.metaData.getInt("setup_notification_icon", -1);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Icon asset cannot be found in the package", e, new Object[0]);
        }
        return -1;
    }

    public String getNotificationText(@NonNull Context context) {
        return context.getString(this.mTextResource);
    }

    public String getNotificationTitle(@NonNull Context context) {
        return context.getString(this.mTitleResource);
    }
}
