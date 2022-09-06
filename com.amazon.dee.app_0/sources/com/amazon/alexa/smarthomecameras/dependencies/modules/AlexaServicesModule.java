package com.amazon.alexa.smarthomecameras.dependencies.modules;

import android.content.Context;
import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes10.dex */
public abstract class AlexaServicesModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static AlexaMobileFrameworkApis providesAmfApis(Context context) {
        return new AlexaMobileFrameworkApis(context);
    }
}
