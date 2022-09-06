package com.amazon.alexa.voice.locale;

import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes11.dex */
public final class LocaleModule {
    private LocaleModule() {
    }

    @Provides
    @Singleton
    public static DlsFeatureEnabler provideDlsFeatureEnabler() {
        return new DlsFeatureEnabler();
    }

    @Provides
    public static LocaleAPI provideLocaleAPI() {
        return new LocaleAPI();
    }

    @Provides
    @Singleton
    public static LocaleInteractor provideLocaleInteractor(AlexaServicesConnection alexaServicesConnection, LocaleAPI localeAPI, LocalePreference localePreference, IdentityService identityService, VoxMetricEventProcessingService voxMetricEventProcessingService, DlsFeatureEnabler dlsFeatureEnabler) {
        return new AlexaLocaleInteractor(alexaServicesConnection, localeAPI, localePreference, identityService, voxMetricEventProcessingService, dlsFeatureEnabler);
    }

    @Provides
    public static LocalePreference provideLocaleSetting(Context context) {
        return new UserLocalePreference(context);
    }
}
