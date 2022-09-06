package com.amazon.alexa.voice.alerts;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes11.dex */
public final class AlertsModule {
    private AlertsModule() {
    }

    @Provides
    @Singleton
    public static AlertsFeatureEnabler provideAlertsFeatureEnabler(Context context) {
        return new AlertsFeatureEnabler(context);
    }
}
