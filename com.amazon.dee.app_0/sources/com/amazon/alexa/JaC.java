package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.auto.storage.StorageModule;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.smarthomecameras.routing.CamerasRouteParameter;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
/* compiled from: DeviceModule.java */
@Module
/* loaded from: classes.dex */
public class JaC {
    @Provides
    @Singleton
    @Named(CamerasRouteParameter.CAPABILITIES)
    public PersistentStorage BIo(Context context) {
        return new EKZ(context.getSharedPreferences(CamerasRouteParameter.CAPABILITIES, 0));
    }

    @Provides
    @Singleton
    @Named("TWA_SHARED_PREFS")
    public PersistentStorage JTe(Context context) {
        return new EKZ(context.getSharedPreferences("TWA_SHARED_PREFS", 0));
    }

    @Provides
    @Singleton
    public AMPDInformationProvider LPk(Context context) {
        return AMPDInformationProvider.getInstance(context);
    }

    @Provides
    @Singleton
    @Named("AlexaServiceSettings")
    public PersistentStorage Qle(Context context) {
        return new EKZ(context.getSharedPreferences("AlexaServiceSettings", 0));
    }

    @Provides
    @Singleton
    @Named("AlexaNotificationPreferences")
    public PersistentStorage jiA(Context context) {
        return new EKZ(context.getSharedPreferences("AlexaNotificationPreferences", 0));
    }

    @Provides
    @Singleton
    @Named("AlexaDevicePreferences")
    public PersistentStorage zQM(Context context) {
        return new EKZ(context.getSharedPreferences("AlexaDevicePreferences", 0));
    }

    @Provides
    @Singleton
    @Named("capability_agents")
    public PersistentStorage zZm(Context context) {
        return new EKZ(context.getSharedPreferences("capability_agents", 0));
    }

    @Provides
    @Singleton
    @Named(StorageModule.NAVIGATION_STORE)
    public PersistentStorage zyO(Context context) {
        return new EKZ(context.getSharedPreferences(StorageModule.NAVIGATION_STORE, 0));
    }
}
