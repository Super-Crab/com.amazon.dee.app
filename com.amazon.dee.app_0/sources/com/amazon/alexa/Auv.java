package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.utils.device.AlexaHandsFreeDeviceInformation;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
/* compiled from: AlexaHandsFreeDeviceInformationModule.java */
@Module
/* loaded from: classes.dex */
public class Auv {
    @Provides
    @Singleton
    public AlexaHandsFreeDeviceInformation zZm(Context context) {
        return new AlexaHandsFreeDeviceInformation(context);
    }
}
