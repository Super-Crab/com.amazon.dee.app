package com.amazon.alexa.voice.nowplaying;

import androidx.annotation.NonNull;
import com.amazon.alexa.api.AlexaPlayerInfoCardListener;
import com.amazon.alexa.api.AlexaPlayerInfoState;
import com.amazon.alexa.voice.nowplaying.bridge.VoiceBridgePayloadUtil;
import com.amazon.alexa.voice.nowplaying.bridge.VoiceNowPlayingEventBusModule;
/* loaded from: classes11.dex */
public final class AudioPlayerListenerAdapter implements AlexaPlayerInfoCardListener {
    private final AudioPlayerListener audioPlayerListener;
    private final VoiceNowPlayingEventBusModule voiceNowPlayingEventBusModule;

    /* renamed from: com.amazon.alexa.voice.nowplaying.AudioPlayerListenerAdapter$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaPlayerInfoState = new int[AlexaPlayerInfoState.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaPlayerInfoState[AlexaPlayerInfoState.BUFFERING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaPlayerInfoState[AlexaPlayerInfoState.PAUSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaPlayerInfoState[AlexaPlayerInfoState.PLAYING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaPlayerInfoState[AlexaPlayerInfoState.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaPlayerInfoState[AlexaPlayerInfoState.DONE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaPlayerInfoState[AlexaPlayerInfoState.CANCELLED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public AudioPlayerListenerAdapter(@NonNull AudioPlayerListener audioPlayerListener, @NonNull VoiceNowPlayingEventBusModule voiceNowPlayingEventBusModule) {
        this.audioPlayerListener = audioPlayerListener;
        this.voiceNowPlayingEventBusModule = voiceNowPlayingEventBusModule;
    }

    @Override // com.amazon.alexa.api.AlexaPlayerInfoCardListener
    public void onAudioItemStateChanged(@NonNull AlexaPlayerInfoState alexaPlayerInfoState, @NonNull String str, long j) {
        int ordinal = alexaPlayerInfoState.ordinal();
        if (ordinal == 0) {
            this.audioPlayerListener.onPlaying();
        } else if (ordinal != 1) {
            if (ordinal == 2) {
                this.audioPlayerListener.onPlaybackEnded();
            } else if (ordinal == 3) {
                this.audioPlayerListener.onBuffering();
            } else if (ordinal == 4) {
                this.audioPlayerListener.onError();
            }
            this.audioPlayerListener.onIdle();
        } else {
            this.audioPlayerListener.onPaused();
        }
        this.voiceNowPlayingEventBusModule.broadcastVoxPlaybackInfo(VoiceBridgePayloadUtil.createAudioItemStatePayload(alexaPlayerInfoState, str));
    }

    @Override // com.amazon.alexa.api.AlexaPlayerInfoCardListener
    public void onReceivedPlayerInfoCard(@NonNull String str, boolean z) {
    }
}
