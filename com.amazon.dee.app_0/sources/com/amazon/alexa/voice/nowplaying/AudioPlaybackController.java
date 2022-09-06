package com.amazon.alexa.voice.nowplaying;

import androidx.annotation.NonNull;
import com.amazon.alexa.api.AlexaAudioPlaybackListener;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaPlaybackState;
import com.amazon.alexa.api.AlexaPlayerInfoCardListener;
import com.amazon.alexa.api.AlexaPlayerInfoState;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.compat.AlexaServices;
import com.amazon.alexa.voice.ui.onedesign.util.Logger;
import com.amazon.alexa.voice.ui.player.PlaybackController;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes11.dex */
public final class AudioPlaybackController implements PlaybackController, AlexaServicesConnection.ConnectionListener, AlexaAudioPlaybackListener, AlexaPlayerInfoCardListener {
    private final AlexaServicesConnection alexaServicesConnection;
    private AtomicReference<AlexaPlaybackState> alexaPlaybackState = new AtomicReference<>(AlexaPlaybackState.NONE);
    private final List<PlaybackController.Callback> callbackList = new CopyOnWriteArrayList();

    /* renamed from: com.amazon.alexa.voice.nowplaying.AudioPlaybackController$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaPlayerInfoState = new int[AlexaPlayerInfoState.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaPlayerInfoState[AlexaPlayerInfoState.BUFFERING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaPlayerInfoState[AlexaPlayerInfoState.PLAYING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaPlayerInfoState[AlexaPlayerInfoState.PAUSED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaPlayerInfoState[AlexaPlayerInfoState.CANCELLED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaPlayerInfoState[AlexaPlayerInfoState.ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaPlayerInfoState[AlexaPlayerInfoState.DONE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public AudioPlaybackController(AlexaServicesConnection alexaServicesConnection) {
        this.alexaServicesConnection = alexaServicesConnection;
    }

    private void publishIsPlaying() {
        Logger.debug("Playback Started");
        for (PlaybackController.Callback callback : this.callbackList) {
            callback.onPlaybackStarted();
        }
    }

    private void publishPlaybackPaused() {
        Logger.debug("Playback Paused");
        for (PlaybackController.Callback callback : this.callbackList) {
            callback.onPlaybackPaused();
        }
    }

    private void publishPlaybackStopped() {
        Logger.debug("Playback Stopped");
        for (PlaybackController.Callback callback : this.callbackList) {
            callback.onPlaybackStopped();
        }
    }

    @Override // com.amazon.alexa.voice.ui.player.PlaybackController
    public void addCallback(PlaybackController.Callback callback) {
        this.callbackList.add(callback);
    }

    @Override // com.amazon.alexa.voice.ui.player.PlaybackController
    public long getCurrentPosition() {
        return 0L;
    }

    @Override // com.amazon.alexa.voice.ui.player.PlaybackController
    public long getDuration() {
        return 0L;
    }

    public void initialize() {
        this.alexaServicesConnection.registerListener(this);
    }

    @Override // com.amazon.alexa.voice.ui.player.PlaybackController
    public boolean isPlaying() {
        return AlexaPlaybackState.STOPPABLE_AND_NAVIGABLE == this.alexaPlaybackState.get();
    }

    @Override // com.amazon.alexa.voice.ui.player.PlaybackController
    public void next() {
        AlexaServices.AudioPlaybackControl.next(this.alexaServicesConnection);
    }

    @Override // com.amazon.alexa.api.AlexaPlayerInfoCardListener
    public void onAudioItemStateChanged(@NonNull AlexaPlayerInfoState alexaPlayerInfoState, @NonNull String str, long j) {
        int ordinal = alexaPlayerInfoState.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                publishPlaybackPaused();
                return;
            } else if (ordinal != 3) {
                if (ordinal == 5) {
                    return;
                }
                publishPlaybackStopped();
                return;
            }
        }
        publishIsPlaying();
    }

    @Override // com.amazon.alexa.api.AlexaAudioPlaybackListener
    public void onAudioPlaybackChanged(@NonNull AlexaPlaybackState alexaPlaybackState) {
        this.alexaPlaybackState.set(alexaPlaybackState);
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnected() {
        AlexaServices.Cards.registerPlayerInfoCardListener(this.alexaServicesConnection, this);
        AlexaServices.AudioPlaybackControl.registerListener(this.alexaServicesConnection, this);
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onDisconnected() {
        AlexaServices.Cards.deregisterPlayerInfoCardListener(this.alexaServicesConnection, this);
        AlexaServices.AudioPlaybackControl.deregisterListener(this.alexaServicesConnection, this);
    }

    @Override // com.amazon.alexa.api.AlexaPlayerInfoCardListener
    public void onReceivedPlayerInfoCard(@NonNull String str, boolean z) {
    }

    @Override // com.amazon.alexa.voice.ui.player.PlaybackController
    public void pause() {
        AlexaServices.AudioPlaybackControl.pause(this.alexaServicesConnection);
    }

    @Override // com.amazon.alexa.voice.ui.player.PlaybackController
    public void play() {
        AlexaServices.AudioPlaybackControl.play(this.alexaServicesConnection);
    }

    @Override // com.amazon.alexa.voice.ui.player.PlaybackController
    public void previous() {
        AlexaServices.AudioPlaybackControl.previous(this.alexaServicesConnection);
    }

    public void release() {
        this.alexaServicesConnection.deregisterListener(this);
    }

    @Override // com.amazon.alexa.voice.ui.player.PlaybackController
    public void removeCallback(PlaybackController.Callback callback) {
        this.callbackList.remove(callback);
    }

    @Override // com.amazon.alexa.voice.ui.player.PlaybackController
    public void seek(long j) {
        throw new UnsupportedOperationException("Seeking is not supported");
    }

    @Override // com.amazon.alexa.voice.ui.player.PlaybackController
    public void stop() {
        AlexaServices.AudioPlaybackControl.stop(this.alexaServicesConnection);
    }
}
