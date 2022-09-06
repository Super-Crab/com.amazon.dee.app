package com.amazon.alexa.voice.features;

import com.amazon.alexa.featureservice.data.registry.NativeFeatureRegistry;
/* loaded from: classes11.dex */
public enum VoiceFeature {
    FAKE_FEATURE_FOR_TEST("FAKE_FEATURE_FOR_TEST"),
    ALEXA_TIMER_AND_ALARM_ON_VOX_GUI_ANDROID(NativeFeatureRegistry.ALEXA_TIMER_AND_ALARM_ON_VOX_GUI_ANDROID),
    TTA_I18N_DEV(NativeFeatureRegistry.ALEXA_VOX_ANDROID_TTA_I18N_DEV),
    DLS(NativeFeatureRegistry.ALEXA_HANDS_FREE_DYNAMIC_LANGUAGE_SWITCHING);
    
    private final String featureName;

    VoiceFeature(String str) {
        this.featureName = str;
    }

    public String getName() {
        return this.featureName;
    }
}
