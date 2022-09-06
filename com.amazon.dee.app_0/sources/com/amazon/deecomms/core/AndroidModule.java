package com.amazon.deecomms.core;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.os.BatteryManager;
import android.os.PowerManager;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes12.dex */
public class AndroidModule {
    @Provides
    public ActivityManager providesActivityManager(@NonNull Context context) {
        return (ActivityManager) context.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
    }

    @Provides
    public AlarmManager providesAlarmManager(Context context) {
        return (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
    }

    @Provides
    public AudioManager providesAudioManager(Context context) {
        return (AudioManager) context.getSystemService("audio");
    }

    @Provides
    public BatteryManager providesBatteryManager(Context context) {
        return (BatteryManager) context.getSystemService("batterymanager");
    }

    @Provides
    public ConnectivityManager providesConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService("connectivity");
    }

    @Provides
    public NotificationManager providesNotificationManager(Context context) {
        return (NotificationManager) context.getSystemService("notification");
    }

    @Provides
    public PackageManager providesPackageManager(Context context) {
        return context.getPackageManager();
    }

    @Provides
    public PowerManager providesPowerManager(Context context) {
        return (PowerManager) context.getSystemService("power");
    }

    @Provides
    public SharedPreferences providesSharedPreferences(@NonNull Context context) {
        return context.getSharedPreferences("SHARED_PREFS", 0);
    }

    @Provides
    public TelecomManager providesTelecomManager(Context context) {
        return (TelecomManager) context.getSystemService("telecom");
    }

    @Provides
    public TelephonyManager providesTelephonyManager(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }
}
