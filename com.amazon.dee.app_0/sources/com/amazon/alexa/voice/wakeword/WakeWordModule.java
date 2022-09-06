package com.amazon.alexa.voice.wakeword;

import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import com.amazon.alexa.voice.permissions.VoicePermissionsAuthority;
import com.amazon.alexa.voice.platform.ABIRetriever;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes11.dex */
public final class WakeWordModule {
    private WakeWordModule() {
    }

    @Provides
    @Singleton
    public static AbiCompatibilityInterface provideCompatibilityInterface() {
        return new WakewordAbiCompatibility(new ABIRetriever());
    }

    @Provides
    @Singleton
    public static VoiceOverUtility provideVoiceOverUtility(Context context, VoxMetricEventProcessingService voxMetricEventProcessingService) {
        return new VoiceOverUtility(context, voxMetricEventProcessingService);
    }

    @Provides
    @Singleton
    public static WakeWordAuthority provideWakeWordAuthority(AlexaServicesConnection alexaServicesConnection, WakewordPreference wakewordPreference, WakeWordFeatureGate wakeWordFeatureGate, ApplicationLifecycleService applicationLifecycleService, VoicePermissionsAuthority voicePermissionsAuthority, WakeWordEventHandler wakeWordEventHandler, VoiceOverUtility voiceOverUtility) {
        return new WakeWordAuthority(alexaServicesConnection, wakewordPreference, wakeWordFeatureGate, applicationLifecycleService, voicePermissionsAuthority, wakeWordEventHandler, voiceOverUtility);
    }

    @Provides
    @Singleton
    public static WakeWordFeatureGate provideWakeWordFeatureGate(AlexaServicesConnection alexaServicesConnection, AbiCompatibilityInterface abiCompatibilityInterface) {
        return new WakeWordFeatureGate(alexaServicesConnection, abiCompatibilityInterface);
    }

    @Provides
    @Singleton
    public static WakewordPreference provideWakewordPreference(Context context) {
        return new UserWakewordPreference(context);
    }

    @Provides
    @Singleton
    public static WakeWordEventHandler providesWakeWordEventHandler(EventBus eventBus, ApplicationLifecycleService applicationLifecycleService) {
        return new WakeWordEventHandler(eventBus, applicationLifecycleService);
    }
}
