package com.amazon.alexa.mobilytics;

import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.dependencies.MobilyticsModule;
import com.amazon.alexa.mobilytics.util.Log;
import com.google.common.base.Preconditions;
/* loaded from: classes9.dex */
public final class MobilyticsFactory {
    private MobilyticsFactory() {
    }

    public static Mobilytics getMobilytics(@Nullable MobilyticsConfiguration mobilyticsConfiguration) {
        try {
            Preconditions.checkNotNull(mobilyticsConfiguration);
            if (!mobilyticsConfiguration.isDebug()) {
                Log.enforceReleaseLogging();
            }
            return DaggerMobilyticsComponent.builder().mobilyticsModule(new MobilyticsModule(mobilyticsConfiguration)).build().mobilytics();
        } catch (Exception e) {
            throw new MobilyticsException("error initializing Mobilytics", e);
        }
    }
}
