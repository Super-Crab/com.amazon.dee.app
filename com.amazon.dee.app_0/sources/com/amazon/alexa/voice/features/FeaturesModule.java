package com.amazon.alexa.voice.features;

import android.os.Handler;
import android.os.Looper;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.voice.alerts.AlertsFeatureEnabler;
import com.amazon.alexa.voice.feature.FeatureAvailability;
import com.amazon.alexa.voice.locale.DlsFeatureEnabler;
import com.amazon.alexa.voice.tta.TypeToAlexaFeatureEnabler;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes11.dex */
public final class FeaturesModule {
    private FeaturesModule() {
    }

    @Provides
    @Singleton
    public static FeatureAvailability provideFeatureAvailability(FeatureServiceV2 featureServiceV2) {
        return new FeatureAvailabilityForUi(featureServiceV2);
    }

    @Provides
    @Singleton
    public static FeatureAvailabilityObserver provideFeatureAvailabilityChecker(VoiceFeatureChecker voiceFeatureChecker, AlertsFeatureEnabler alertsFeatureEnabler, TypeToAlexaFeatureEnabler typeToAlexaFeatureEnabler, DlsFeatureEnabler dlsFeatureEnabler) {
        return new FeatureAvailabilityObserver(voiceFeatureChecker, alertsFeatureEnabler, typeToAlexaFeatureEnabler, dlsFeatureEnabler);
    }

    @Provides
    @Singleton
    public static FeatureChecker provideFeatureChecker(FeatureServiceV2 featureServiceV2) {
        return provideVoiceFeatureChecker(featureServiceV2);
    }

    @Provides
    @Singleton
    public static VoiceFeatureChecker provideVoiceFeatureChecker(FeatureServiceV2 featureServiceV2) {
        return new VoiceFeatureChecker(featureServiceV2, new Handler(Looper.getMainLooper()));
    }
}
