package com.amazon.alexa.api.compat;

import com.amazon.alexa.api.AlexaAttentionSystemSettingsListener;
import com.amazon.alexa.api.AlexaUserSpeechListener;
import com.dee.app.metrics.MetricsConstants;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: AttentionSystemApi.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0012H&J\b\u0010\u0015\u001a\u00020\u0003H&¨\u0006\u0016"}, d2 = {"Lcom/amazon/alexa/api/compat/AttentionSystemApi;", "", MetricsConstants.Method.CACHE_CLEAR, "", "deregisterAttentionSystemSettingsListener", "attentionSystemSettingsListener", "Lcom/amazon/alexa/api/AlexaAttentionSystemSettingsListener;", "deregisterStateListener", "alexaStateListener", "Lcom/amazon/alexa/api/compat/AlexaStateListener;", "deregisterUserSpeechListener", "alexaUserSpeechListener", "Lcom/amazon/alexa/api/AlexaUserSpeechListener;", "registerAttentionSystemSettingsListener", "registerStateListener", "registerUserSpeechListener", "setEndpointSoundEnabled", "isEndpointSoundEnabled", "", "setWakeSoundEnabled", "isWakeSoundEnabled", "stopForegroundAudioTask", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface AttentionSystemApi {
    void clear();

    void deregisterAttentionSystemSettingsListener(@NotNull AlexaAttentionSystemSettingsListener alexaAttentionSystemSettingsListener);

    void deregisterStateListener(@NotNull AlexaStateListener alexaStateListener);

    void deregisterUserSpeechListener(@NotNull AlexaUserSpeechListener alexaUserSpeechListener);

    void registerAttentionSystemSettingsListener(@NotNull AlexaAttentionSystemSettingsListener alexaAttentionSystemSettingsListener);

    void registerStateListener(@NotNull AlexaStateListener alexaStateListener);

    void registerUserSpeechListener(@NotNull AlexaUserSpeechListener alexaUserSpeechListener);

    void setEndpointSoundEnabled(boolean z);

    void setWakeSoundEnabled(boolean z);

    void stopForegroundAudioTask();
}
