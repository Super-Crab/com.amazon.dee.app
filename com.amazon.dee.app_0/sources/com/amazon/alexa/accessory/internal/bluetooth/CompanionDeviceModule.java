package com.amazon.alexa.accessory.internal.bluetooth;

import android.app.PendingIntent;
import android.content.pm.PackageManager;
import androidx.annotation.RequiresApi;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes.dex */
public interface CompanionDeviceModule {
    @RequiresApi(api = 26)
    static boolean companionDeviceSetupAvailable(PackageManager packageManager) {
        return packageManager.hasSystemFeature("android.software.companion_device_setup");
    }

    @RequiresApi(api = 26)
    PendingIntent getRequestCompanionDevicePendingIntent(String str) throws IllegalAccessException;

    @RequiresApi(api = 26)
    boolean isCompanionDevice(String str) throws IllegalAccessException;

    @RequiresApi(api = 26)
    Observable<String> queryNewCompanionDevices();

    @RequiresApi(api = 26)
    Observable<String> queryRemovedCompanionDevices();

    @RequiresApi(api = 26)
    void removeCompanionDevice(String str) throws IllegalAccessException;

    @RequiresApi(api = 26)
    Single<Boolean> requestCompanionDevice(String str);
}
