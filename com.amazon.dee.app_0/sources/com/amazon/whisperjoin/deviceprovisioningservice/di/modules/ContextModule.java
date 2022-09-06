package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.app.job.JobScheduler;
import android.content.Context;
import android.net.wifi.WifiManager;
import com.amazon.whisperjoin.deviceprovisioningservice.util.SharedPreferencesProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.BluetoothSupportProvider;
import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes13.dex */
public class ContextModule {
    private final Context mContext;

    public ContextModule(Context context) {
        this.mContext = context.getApplicationContext();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public BluetoothSupportProvider providesBluetoothSupportProvider(Context context) {
        return new BluetoothSupportProvider.DefaultBluetoothSupportProvider(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public Context providesContext() {
        return this.mContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public Gson providesGson() {
        return new Gson();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public JobScheduler providesJobScheduler(Context context) {
        return (JobScheduler) context.getSystemService("jobscheduler");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public SharedPreferencesProvider providesSharedPreferencesProvider(Context context) {
        return new SharedPreferencesProvider(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public WifiManager providesWifiManager(Context context) {
        return (WifiManager) context.getApplicationContext().getSystemService("wifi");
    }
}
