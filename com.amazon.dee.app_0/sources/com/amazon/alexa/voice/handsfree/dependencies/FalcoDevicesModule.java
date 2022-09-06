package com.amazon.alexa.voice.handsfree.dependencies;

import android.content.Context;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.devices.DeviceTypeInformationProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes11.dex */
public class FalcoDevicesModule {
    @AhfScope
    @Provides
    public AMPDInformationProvider ampdInformationProvider(Context context) {
        return AMPDInformationProvider.getInstance(context);
    }

    @AhfScope
    @Provides
    public DeviceTypeInformationProvider provideDeviceTypeInformationProvider(Context context) {
        return DeviceTypeInformationProvider.getInstance(context);
    }
}
