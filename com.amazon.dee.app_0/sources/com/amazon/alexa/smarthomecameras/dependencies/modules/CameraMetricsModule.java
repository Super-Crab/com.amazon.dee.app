package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes10.dex */
public class CameraMetricsModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public static Mobilytics provideMobilytics() {
        return (Mobilytics) GeneratedOutlineSupport1.outline21(Mobilytics.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static CamerasMobilyticsService provideMobilyticsService(Mobilytics mobilytics) {
        Preconditions.checkNotNull(mobilytics, "Mobilytics cannot be null");
        return new CamerasMobilyticsService(mobilytics);
    }
}
