package com.amazon.alexa;

import android.content.Context;
import androidx.annotation.Nullable;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.devices.DeviceInformation;
import com.amazon.alexa.handsfree.devices.DeviceTypeInformationProvider;
import com.amazon.alexa.utils.TimeProvider;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
/* compiled from: IOComponentModule.java */
@Module
/* loaded from: classes.dex */
public class FLJ {
    @Nullable
    @Provides
    @Singleton
    public PRf zZm(Context context, Efr efr) {
        return efr.zZm(DeviceTypeInformationProvider.getInstance(context).getSupportedDeviceInformation(context));
    }

    @Nullable
    @Provides
    @Singleton
    public DeviceInformation zZm(Context context) {
        return DeviceTypeInformationProvider.getInstance(context).getSupportedDeviceInformation(context);
    }

    @Provides
    @Singleton
    public VIE zZm(Context context, peZ pez, Box box, Msx msx, TimeProvider timeProvider, AlexaClientEventBus alexaClientEventBus, gSO gso, AMPDInformationProvider aMPDInformationProvider) {
        return new adp(context, pez, box, DeviceTypeInformationProvider.getInstance(context).getSupportedDeviceInformation(context), msx, timeProvider, alexaClientEventBus, gso, aMPDInformationProvider);
    }
}
