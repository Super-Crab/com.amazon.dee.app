package com.amazon.alexa.api.compat;

import android.app.PendingIntent;
import com.amazon.alexa.voice.nowplaying.bridge.VoiceBridgePayloadUtil;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AudioPlaybackControlSubcomponent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00172\u00020\u00012\u00020\u0002:\u0001\u0017B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016J\t\u0010\r\u001a\u00020\u0007H\u0096\u0002J\b\u0010\u000e\u001a\u00020\u0007H\u0016J\b\u0010\u000f\u001a\u00020\u0007H\u0016J\b\u0010\u0010\u001a\u00020\u0007H\u0016J\u0010\u0010\u0011\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0007H\u0016¨\u0006\u0018"}, d2 = {"Lcom/amazon/alexa/api/compat/AudioPlaybackControlSubcomponent;", "Lcom/amazon/alexa/api/compat/Subcomponent;", "Lcom/amazon/alexa/api/compat/AudioPlaybackControlApi;", "frameworkApis", "Lcom/amazon/alexa/api/AlexaMobileFrameworkApis;", "(Lcom/amazon/alexa/api/AlexaMobileFrameworkApis;)V", "deregisterAlexaAudioPlaybackStatusListener", "", "alexaAudioPlaybackStatusListener", "Lcom/amazon/alexa/api/compat/AlexaAudioPlaybackStatusListener;", "deregisterMediaPlaybackListener", "mediaPlaybackListener", "Lcom/amazon/alexa/api/compat/AlexaMediaPlaybackListener;", "next", "pause", VoiceBridgePayloadUtil.PayloadCommand.PLAY, "previous", "registerAlexaAudioPlaybackStatusListener", "registerMediaPlaybackListener", "setMediaNotificationContentIntent", BaseGmsClient.KEY_PENDING_INTENT, "Landroid/app/PendingIntent;", "stop", "Companion", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AudioPlaybackControlSubcomponent extends Subcomponent implements AudioPlaybackControlApi {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final Map<AlexaAudioPlaybackStatusListener, AlexaAudioPlaybackStatusListenerAdapter> playbackStatusListeners = new LinkedHashMap();
    @NotNull
    private static final Map<AlexaMediaPlaybackListener, AlexaMediaPlaybackListenerAdapter> mediaPlaybackListeners = new LinkedHashMap();

    /* compiled from: AudioPlaybackControlSubcomponent.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\b¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/api/compat/AudioPlaybackControlSubcomponent$Companion;", "", "()V", "mediaPlaybackListeners", "", "Lcom/amazon/alexa/api/compat/AlexaMediaPlaybackListener;", "Lcom/amazon/alexa/api/compat/AlexaMediaPlaybackListenerAdapter;", "getMediaPlaybackListeners", "()Ljava/util/Map;", "playbackStatusListeners", "Lcom/amazon/alexa/api/compat/AlexaAudioPlaybackStatusListener;", "Lcom/amazon/alexa/api/compat/AlexaAudioPlaybackStatusListenerAdapter;", "getPlaybackStatusListeners", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final Map<AlexaMediaPlaybackListener, AlexaMediaPlaybackListenerAdapter> getMediaPlaybackListeners() {
            return AudioPlaybackControlSubcomponent.mediaPlaybackListeners;
        }

        @NotNull
        public final Map<AlexaAudioPlaybackStatusListener, AlexaAudioPlaybackStatusListenerAdapter> getPlaybackStatusListeners() {
            return AudioPlaybackControlSubcomponent.playbackStatusListeners;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioPlaybackControlSubcomponent(@NotNull com.amazon.alexa.api.AlexaMobileFrameworkApis frameworkApis) {
        super(frameworkApis);
        Intrinsics.checkParameterIsNotNull(frameworkApis, "frameworkApis");
    }

    @Override // com.amazon.alexa.api.compat.AudioPlaybackControlApi
    public void deregisterAlexaAudioPlaybackStatusListener(@NotNull AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener) {
        Intrinsics.checkParameterIsNotNull(alexaAudioPlaybackStatusListener, "alexaAudioPlaybackStatusListener");
        AlexaAudioPlaybackStatusListenerAdapter remove = playbackStatusListeners.remove(alexaAudioPlaybackStatusListener);
        if (remove != null) {
            getFrameworkApis().getMediaPlaybackController().deregisterAlexaAudioPlaybackStatusListener(remove);
        }
    }

    @Override // com.amazon.alexa.api.compat.AudioPlaybackControlApi
    public void deregisterMediaPlaybackListener(@NotNull AlexaMediaPlaybackListener mediaPlaybackListener) {
        Intrinsics.checkParameterIsNotNull(mediaPlaybackListener, "mediaPlaybackListener");
        AlexaMediaPlaybackListenerAdapter remove = mediaPlaybackListeners.remove(mediaPlaybackListener);
        if (remove != null) {
            getFrameworkApis().getMediaPlaybackController().deregisterMediaPlaybackListener(remove);
        }
    }

    @Override // com.amazon.alexa.api.compat.AudioPlaybackControlApi
    public void next() {
        getFrameworkApis().getMediaPlaybackController().next();
    }

    @Override // com.amazon.alexa.api.compat.AudioPlaybackControlApi
    public void pause() {
        getFrameworkApis().getMediaPlaybackController().pause();
    }

    @Override // com.amazon.alexa.api.compat.AudioPlaybackControlApi
    public void play() {
        getFrameworkApis().getMediaPlaybackController().play();
    }

    @Override // com.amazon.alexa.api.compat.AudioPlaybackControlApi
    public void previous() {
        getFrameworkApis().getMediaPlaybackController().previous();
    }

    @Override // com.amazon.alexa.api.compat.AudioPlaybackControlApi
    public void registerAlexaAudioPlaybackStatusListener(@NotNull AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener) {
        Intrinsics.checkParameterIsNotNull(alexaAudioPlaybackStatusListener, "alexaAudioPlaybackStatusListener");
        AlexaAudioPlaybackStatusListenerAdapter alexaAudioPlaybackStatusListenerAdapter = new AlexaAudioPlaybackStatusListenerAdapter(alexaAudioPlaybackStatusListener);
        playbackStatusListeners.put(alexaAudioPlaybackStatusListener, alexaAudioPlaybackStatusListenerAdapter);
        getFrameworkApis().getMediaPlaybackController().registerAlexaAudioPlaybackStatusListener(alexaAudioPlaybackStatusListenerAdapter);
    }

    @Override // com.amazon.alexa.api.compat.AudioPlaybackControlApi
    public void registerMediaPlaybackListener(@NotNull AlexaMediaPlaybackListener mediaPlaybackListener) {
        Intrinsics.checkParameterIsNotNull(mediaPlaybackListener, "mediaPlaybackListener");
        AlexaMediaPlaybackListenerAdapter alexaMediaPlaybackListenerAdapter = new AlexaMediaPlaybackListenerAdapter(mediaPlaybackListener);
        mediaPlaybackListeners.put(mediaPlaybackListener, alexaMediaPlaybackListenerAdapter);
        getFrameworkApis().getMediaPlaybackController().registerMediaPlaybackListener(alexaMediaPlaybackListenerAdapter);
    }

    @Override // com.amazon.alexa.api.compat.AudioPlaybackControlApi
    public void setMediaNotificationContentIntent(@NotNull PendingIntent pendingIntent) {
        Intrinsics.checkParameterIsNotNull(pendingIntent, "pendingIntent");
        getFrameworkApis().getMediaPlaybackController().setMediaNotificationContentIntent(pendingIntent);
    }

    @Override // com.amazon.alexa.api.compat.AudioPlaybackControlApi
    public void stop() {
        getFrameworkApis().getMediaPlaybackController().stop();
    }
}
