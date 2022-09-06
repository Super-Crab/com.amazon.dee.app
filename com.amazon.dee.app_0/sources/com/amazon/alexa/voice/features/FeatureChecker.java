package com.amazon.alexa.voice.features;

import io.reactivex.rxjava3.core.Observable;
/* loaded from: classes11.dex */
public interface FeatureChecker {
    boolean hasFeature(VoiceFeature voiceFeature);

    Observable<Boolean> onFeatureAvailability(VoiceFeature voiceFeature);
}
