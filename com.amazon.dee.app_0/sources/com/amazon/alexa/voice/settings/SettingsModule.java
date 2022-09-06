package com.amazon.alexa.voice.settings;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.voice.ftue.FtuePreference;
import com.amazon.alexa.voice.locale.LocaleInteractor;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import com.amazon.alexa.voice.permissions.VoicePermissionsAuthority;
import com.amazon.alexa.voice.sdk.AlexaStateTracker;
import com.amazon.alexa.voice.sdk.AttentionSystemManager;
import com.amazon.alexa.voice.tta.TypeToAlexaFeatureEnabler;
import com.amazon.alexa.voice.wakeword.AbiCompatibilityInterface;
import com.amazon.alexa.voice.wakeword.WakewordPreference;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes11.dex */
public final class SettingsModule {
    private SettingsModule() {
    }

    @Provides
    public static EarconSettingsHandler provideEarconSettingsHandler(EventBus eventBus, AttentionSystemManager attentionSystemManager, VoxMetricEventProcessingService voxMetricEventProcessingService, Context context) {
        return new EarconSettingsHandler(eventBus, attentionSystemManager, voxMetricEventProcessingService, context);
    }

    @Provides
    public static HandsfreeSettingsHandler provideHandsfreeSettingsHandler(EventBus eventBus, Context context, WakewordPreference wakewordPreference, FtuePreference ftuePreference, VoicePermissionsAuthority voicePermissionsAuthority, VoxMetricEventProcessingService voxMetricEventProcessingService, AbiCompatibilityInterface abiCompatibilityInterface) {
        return new HandsfreeSettingsHandler(eventBus, context, wakewordPreference, ftuePreference, voicePermissionsAuthority, voxMetricEventProcessingService, abiCompatibilityInterface);
    }

    @Provides
    public static HighPriorityActivityHandler provideHighPriorityActivityHandler(EventBus eventBus, AlexaStateTracker alexaStateTracker) {
        return new HighPriorityActivityHandler(eventBus, alexaStateTracker);
    }

    @Provides
    public static LocaleCombinationSettingHandler provideLocaleCombinationSettingsHandler(LocaleInteractor localeInteractor, EventBus eventBus) {
        return new LocaleCombinationSettingHandler(localeInteractor, eventBus);
    }

    @Provides
    public static LocaleSettingHandler provideLocaleSettingsHandler(LocaleInteractor localeInteractor, EventBus eventBus) {
        return new LocaleSettingHandler(localeInteractor, eventBus);
    }

    @Provides
    public static TtaAvailabilityHandler provideTtaAvailabilityHandler(EventBus eventBus, TypeToAlexaFeatureEnabler typeToAlexaFeatureEnabler) {
        return new TtaAvailabilityHandler(eventBus, typeToAlexaFeatureEnabler);
    }

    @Provides
    @Singleton
    public static VoiceSettings provideVoiceSettings(EventBus eventBus, HandsfreeSettingsHandler handsfreeSettingsHandler, EarconSettingsHandler earconSettingsHandler, LocaleSettingHandler localeSettingHandler, LocaleCombinationSettingHandler localeCombinationSettingHandler, HighPriorityActivityHandler highPriorityActivityHandler, TtaAvailabilityHandler ttaAvailabilityHandler) {
        return new VoiceSettings(eventBus, handsfreeSettingsHandler, earconSettingsHandler, localeSettingHandler, localeCombinationSettingHandler, highPriorityActivityHandler, ttaAvailabilityHandler);
    }
}
