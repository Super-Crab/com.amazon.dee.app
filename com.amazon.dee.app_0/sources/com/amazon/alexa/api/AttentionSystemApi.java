package com.amazon.alexa.api;

import com.amazon.alexa.client.annotations.NonNull;
/* loaded from: classes6.dex */
public interface AttentionSystemApi {
    void deregisterAttentionSystemListener(@NonNull AlexaAttentionSystemListener alexaAttentionSystemListener);

    void deregisterListener(@NonNull AlexaAttentionSystemSettingsListener alexaAttentionSystemSettingsListener);

    void deregisterListener(@NonNull AlexaStateListener alexaStateListener);

    void deregisterListener(@NonNull AlexaUserSpeechListener alexaUserSpeechListener);

    void registerAttentionSystemListener(@NonNull AlexaAttentionSystemListener alexaAttentionSystemListener);

    void registerListener(@NonNull AlexaAttentionSystemSettingsListener alexaAttentionSystemSettingsListener);

    void registerListener(@NonNull AlexaStateListener alexaStateListener);

    void registerListener(@NonNull AlexaUserSpeechListener alexaUserSpeechListener);

    void setEndpointSoundEnabled(boolean z);

    void setWakeSoundEnabled(boolean z);

    void stopForegroundAudioTask();
}
