package com.amazon.alexa.voice.sdk;

import com.amazon.alexa.api.AlexaAttentionSystemSettingsListener;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.compat.AlexaServicesApis;
import com.amazon.alexa.voice.features.FeatureChecker;
/* loaded from: classes11.dex */
public class AttentionSystemManager {
    private final AlexaServicesConnection alexaServicesConnection;
    private final FeatureChecker featureChecker;

    public AttentionSystemManager(AlexaServicesConnection alexaServicesConnection, FeatureChecker featureChecker) {
        this.alexaServicesConnection = alexaServicesConnection;
        this.featureChecker = featureChecker;
    }

    public void deregisterAlexaServicesConnectionListener(AlexaServicesConnection.ConnectionListener connectionListener) {
        this.alexaServicesConnection.deregisterListener(connectionListener);
    }

    public void deregisterAttentionSystemSettingsListener(AlexaAttentionSystemSettingsListener alexaAttentionSystemSettingsListener) {
        AlexaServicesApis.AttentionSystem.deregisterAttentionSystemSettingsListener(this.alexaServicesConnection, alexaAttentionSystemSettingsListener);
    }

    public boolean isEndpointSoundEnabled() {
        return AlexaServicesApis.AttentionSystem.isEndpointSoundEnabled(this.alexaServicesConnection);
    }

    public boolean isWakeSoundEnabled() {
        return AlexaServicesApis.AttentionSystem.isWakeSoundEnabled(this.alexaServicesConnection);
    }

    public void registerAlexaServicesConnectionListener(AlexaServicesConnection.ConnectionListener connectionListener) {
        this.alexaServicesConnection.registerListener(connectionListener);
    }

    public void registerAttentionSystemSettingsListener(AlexaAttentionSystemSettingsListener alexaAttentionSystemSettingsListener) {
        AlexaServicesApis.AttentionSystem.registerAttentionSystemSettingsListener(this.alexaServicesConnection, alexaAttentionSystemSettingsListener);
    }

    public void setEndpointSoundEnabled(boolean z) {
        AlexaServicesApis.AttentionSystem.setEndpointSoundEnabled(this.alexaServicesConnection, z);
    }

    public void setWakeSoundEnabled(boolean z) {
        AlexaServicesApis.AttentionSystem.setWakeSoundEnabled(this.alexaServicesConnection, z);
    }
}
