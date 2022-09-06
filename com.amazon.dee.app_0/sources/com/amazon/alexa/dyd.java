package com.amazon.alexa;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.PowerManager;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
/* compiled from: ContextModule.java */
@Module
/* loaded from: classes.dex */
public class dyd {
    public final Context zZm;

    public dyd(Context context) {
        this.zZm = context;
    }

    @Provides
    @Singleton
    public KeyguardManager BIo(Context context) {
        return (KeyguardManager) context.getSystemService("keyguard");
    }

    @Provides
    @Singleton
    public NotificationManager JTe(Context context) {
        return (NotificationManager) context.getSystemService("notification");
    }

    @Provides
    @Singleton
    public AudioManager LPk(Context context) {
        return (AudioManager) context.getSystemService("audio");
    }

    @Provides
    @Singleton
    public LocationManager Mlj(Context context) {
        return (LocationManager) context.getSystemService("location");
    }

    @Provides
    @Singleton
    public WindowManager Qle(Context context) {
        return (WindowManager) context.getSystemService("window");
    }

    @Provides
    @Singleton
    public WifiManager jiA(Context context) {
        return (WifiManager) context.getSystemService("wifi");
    }

    @Provides
    @Singleton
    public ConnectivityManager yPL(Context context) {
        return (ConnectivityManager) context.getSystemService("connectivity");
    }

    @Provides
    @Singleton
    public PowerManager zQM(Context context) {
        return (PowerManager) context.getSystemService("power");
    }

    @Provides
    @Singleton
    public ActivityManager zZm(Context context) {
        return (ActivityManager) context.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
    }

    @Provides
    @Singleton
    public TelephonyManager zyO(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }

    @Provides
    @Singleton
    public PackageManager zzR(Context context) {
        return context.getPackageManager();
    }
}
