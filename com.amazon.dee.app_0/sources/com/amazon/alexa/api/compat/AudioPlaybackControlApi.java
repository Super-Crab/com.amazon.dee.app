package com.amazon.alexa.api.compat;

import android.app.PendingIntent;
import com.amazon.alexa.voice.nowplaying.bridge.VoiceBridgePayloadUtil;
import com.google.android.gms.common.internal.BaseGmsClient;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: AudioPlaybackControlApi.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\t\u0010\t\u001a\u00020\u0003H¦\u0002J\b\u0010\n\u001a\u00020\u0003H&J\b\u0010\u000b\u001a\u00020\u0003H&J\b\u0010\f\u001a\u00020\u0003H&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011H&J\b\u0010\u0012\u001a\u00020\u0003H&¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/api/compat/AudioPlaybackControlApi;", "", "deregisterAlexaAudioPlaybackStatusListener", "", "alexaAudioPlaybackStatusListener", "Lcom/amazon/alexa/api/compat/AlexaAudioPlaybackStatusListener;", "deregisterMediaPlaybackListener", "mediaPlaybackListener", "Lcom/amazon/alexa/api/compat/AlexaMediaPlaybackListener;", "next", "pause", VoiceBridgePayloadUtil.PayloadCommand.PLAY, "previous", "registerAlexaAudioPlaybackStatusListener", "registerMediaPlaybackListener", "setMediaNotificationContentIntent", BaseGmsClient.KEY_PENDING_INTENT, "Landroid/app/PendingIntent;", "stop", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface AudioPlaybackControlApi {
    void deregisterAlexaAudioPlaybackStatusListener(@NotNull AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener);

    void deregisterMediaPlaybackListener(@NotNull AlexaMediaPlaybackListener alexaMediaPlaybackListener);

    void next();

    void pause();

    void play();

    void previous();

    void registerAlexaAudioPlaybackStatusListener(@NotNull AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener);

    void registerMediaPlaybackListener(@NotNull AlexaMediaPlaybackListener alexaMediaPlaybackListener);

    void setMediaNotificationContentIntent(@NotNull PendingIntent pendingIntent);

    void stop();
}
