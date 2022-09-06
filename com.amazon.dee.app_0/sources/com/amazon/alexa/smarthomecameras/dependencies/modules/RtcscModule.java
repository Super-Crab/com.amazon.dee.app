package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.rtcsc.interfaces.RtcscAppInfo;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes10.dex */
public abstract class RtcscModule {
    private static final String RTCSC_APP_CLIENT_NAME = "SmartHome-LiveView";

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static RtcscAppInfo providesRtcscAppInfo() {
        return new RtcscAppInfo(RTCSC_APP_CLIENT_NAME);
    }
}
