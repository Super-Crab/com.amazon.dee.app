package com.amazon.alexa.voice.locale;

import com.amazon.alexa.voice.features.FeatureEnabler;
import com.amazon.alexa.voice.features.VoiceFeature;
/* loaded from: classes11.dex */
public class DlsFeatureEnabler implements FeatureEnabler {
    private static final String TAG = "DlsFeatureEnabler";
    private boolean isWeblabEnabled = false;

    @Override // com.amazon.alexa.voice.features.FeatureEnabler
    public void enableFeature(boolean z) {
        this.isWeblabEnabled = z;
    }

    @Override // com.amazon.alexa.voice.features.FeatureEnabler
    public VoiceFeature getFeature() {
        return VoiceFeature.DLS;
    }

    public boolean isDLSEnabled() {
        return this.isWeblabEnabled;
    }
}
