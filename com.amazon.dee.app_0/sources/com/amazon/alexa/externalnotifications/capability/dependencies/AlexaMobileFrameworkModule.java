package com.amazon.alexa.externalnotifications.capability.dependencies;

import android.content.Context;
import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes7.dex */
public class AlexaMobileFrameworkModule {
    @Provides
    @Singleton
    public AlexaMobileFrameworkApis providesAmfApis(Context context) {
        return new AlexaMobileFrameworkApis(context);
    }
}
