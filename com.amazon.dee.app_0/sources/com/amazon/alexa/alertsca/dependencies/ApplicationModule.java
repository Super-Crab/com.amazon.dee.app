package com.amazon.alexa.alertsca.dependencies;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.telephony.TelephonyManager;
import androidx.core.app.NotificationCompat;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.bluetooth.sco.BluetoothScoController;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
@Module
/* loaded from: classes6.dex */
public class ApplicationModule {
    public static final String ALERTS_DATA_STORE = "ALERTS_DATA_STORE";
    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public AlarmManager providesAlarmManager() {
        return (AlarmManager) this.context.getSystemService(NotificationCompat.CATEGORY_ALARM);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public AlexaServicesConnection providesAlexaServicesConnection() {
        return new AlexaServicesConnection(this.context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public NotificationManager providesAndroidNotificationManager() {
        return (NotificationManager) this.context.getSystemService("notification");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public Context providesApplicationContext() {
        return this.context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public AudioManager providesAudioManager() {
        return (AudioManager) this.context.getSystemService("audio");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public ConnectivityManager providesConnectivityManager() {
        return (ConnectivityManager) this.context.getSystemService("connectivity");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public Handler providesMainThreadHandler() {
        return new Handler(Looper.getMainLooper());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public PackageManager providesPackageManager() {
        return this.context.getPackageManager();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public PowerManager providesPowerManager() {
        return (PowerManager) this.context.getSystemService("power");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public BluetoothScoController providesScoController(Context context, AudioManager audioManager, TelephonyManager telephonyManager) {
        return new BluetoothScoController(audioManager, telephonyManager, context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    @Named(ALERTS_DATA_STORE)
    public SharedPreferences providesSharedPreferences() {
        return this.context.getSharedPreferences(ALERTS_DATA_STORE, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public TelephonyManager providesTelephonyManager() {
        return (TelephonyManager) this.context.getSystemService("phone");
    }
}
