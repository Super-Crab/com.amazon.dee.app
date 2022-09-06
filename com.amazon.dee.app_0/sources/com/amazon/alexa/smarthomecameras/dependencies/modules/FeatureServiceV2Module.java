package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes10.dex */
public abstract class FeatureServiceV2Module {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static FeatureServiceV2 provideFeatureServiceV2() {
        return (FeatureServiceV2) GeneratedOutlineSupport1.outline20(FeatureServiceV2.class);
    }
}
