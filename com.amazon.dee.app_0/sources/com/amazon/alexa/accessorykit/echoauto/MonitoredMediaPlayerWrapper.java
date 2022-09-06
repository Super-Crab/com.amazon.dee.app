package com.amazon.alexa.accessorykit.echoauto;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
/* loaded from: classes6.dex */
public class MonitoredMediaPlayerWrapper {
    private static final int MAX_LOOPS = 20;
    private final MediaPlayer mediaPlayer;
    private final MediaSessionCompat mediaSession;
    private Thread monitorThread;
    private final MediaPlayerMonitorRunnable monitorThreadRunnable;
    private PlaybackProgressCallbackInterface playbackProgressCallback;
    private final PlaybackStateCompat.Builder playbackStateBuilder;
    private int playbackLoopCount = 0;
    public boolean shouldLoopPlayback = false;
    private volatile boolean isMediaPlayerStopRequested = false;
    private volatile boolean isPrepared = false;
    private MediaPlayer.OnCompletionListener handleMediaPlayerCompletion = new MediaPlayer.OnCompletionListener() { // from class: com.amazon.alexa.accessorykit.echoauto.-$$Lambda$MonitoredMediaPlayerWrapper$ulbYqXiJ3WmQVp4KtcBWSQnDo38
        @Override // android.media.MediaPlayer.OnCompletionListener
        public final void onCompletion(MediaPlayer mediaPlayer) {
            MonitoredMediaPlayerWrapper.this.lambda$new$0$MonitoredMediaPlayerWrapper(mediaPlayer);
        }
    };
    private MediaPlayer.OnErrorListener handleMediaPlayerError = new MediaPlayer.OnErrorListener() { // from class: com.amazon.alexa.accessorykit.echoauto.-$$Lambda$MonitoredMediaPlayerWrapper$GGesB8QcrTgfPENqaevgYLcKj90
        @Override // android.media.MediaPlayer.OnErrorListener
        public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            return MonitoredMediaPlayerWrapper.this.lambda$new$1$MonitoredMediaPlayerWrapper(mediaPlayer, i, i2);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class MediaPlayerMonitorRunnable implements Runnable {
        private static final int MONITOR_INTERVAL_MILLIS = 50;
        private volatile boolean isStopRequested;

        private MediaPlayerMonitorRunnable() {
            this.isStopRequested = false;
        }

        @Override // java.lang.Runnable
        public void run() {
            MonitoredMediaPlayerWrapper.this.playbackLoopCount = 0;
            this.isStopRequested = false;
            double d = 0.0d;
            while (MonitoredMediaPlayerWrapper.this.playbackProgressCallback != null && MonitoredMediaPlayerWrapper.this.mediaPlayer != null && !this.isStopRequested) {
                try {
                    if (MonitoredMediaPlayerWrapper.this.mediaPlayer.isPlaying()) {
                        double duration = MonitoredMediaPlayerWrapper.this.mediaPlayer.getDuration();
                        double currentPosition = duration == FrostVideoEffectController.VIDEO_STRENGTH_CLEAR ? 0.0d : MonitoredMediaPlayerWrapper.this.mediaPlayer.getCurrentPosition() / duration;
                        if (MonitoredMediaPlayerWrapper.this.playbackProgressCallback != null && currentPosition != d) {
                            MonitoredMediaPlayerWrapper.this.playbackProgressCallback.onPlaybackPercentageChange(Double.valueOf(currentPosition));
                        }
                        d = currentPosition;
                    }
                    Thread.sleep(50L);
                } catch (InterruptedException unused) {
                    this.isStopRequested = true;
                }
            }
        }

        public void stop() {
            this.isStopRequested = true;
        }
    }

    /* loaded from: classes6.dex */
    public interface PlaybackProgressCallbackInterface {
        void onPlaybackPercentageChange(Double d);

        void onPlaybackStopped();
    }

    public MonitoredMediaPlayerWrapper(Context context, int i, PlaybackProgressCallbackInterface playbackProgressCallbackInterface) {
        this.mediaPlayer = MediaPlayer.create(context, i);
        this.mediaPlayer.setOnCompletionListener(this.handleMediaPlayerCompletion);
        this.mediaPlayer.setOnErrorListener(this.handleMediaPlayerError);
        this.mediaSession = new MediaSessionCompat(context, MonitoredMediaPlayerWrapper.class.getName());
        this.playbackStateBuilder = new PlaybackStateCompat.Builder();
        this.playbackProgressCallback = playbackProgressCallbackInterface;
        this.monitorThreadRunnable = new MediaPlayerMonitorRunnable();
        onPrepared();
    }

    private void onPlaybackStopped() {
        this.mediaSession.setActive(false);
        this.mediaSession.release();
    }

    private void onPlaybackWillStart() {
        this.playbackStateBuilder.setState(3, this.mediaPlayer.getCurrentPosition(), 0.0f);
        this.playbackStateBuilder.setActions(4L);
        if (!this.mediaSession.isActive()) {
            this.mediaSession.setActive(true);
        }
        this.mediaSession.setPlaybackState(this.playbackStateBuilder.build());
    }

    private void onPrepared() {
        this.isPrepared = true;
        play();
    }

    private void stopMonitoring() {
        if (this.monitorThread != null) {
            this.monitorThreadRunnable.stop();
            this.monitorThread = null;
        }
    }

    private void triggerPlaybackStoppedCallback() {
        PlaybackProgressCallbackInterface playbackProgressCallbackInterface = this.playbackProgressCallback;
        if (playbackProgressCallbackInterface != null) {
            playbackProgressCallbackInterface.onPlaybackStopped();
        }
    }

    public double getDuration() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        return mediaPlayer != null ? mediaPlayer.getDuration() : FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    }

    public /* synthetic */ void lambda$new$0$MonitoredMediaPlayerWrapper(MediaPlayer mediaPlayer) {
        this.playbackLoopCount++;
        if (this.playbackLoopCount >= 20) {
            stopMonitoring();
            onPlaybackStopped();
            triggerPlaybackStoppedCallback();
        } else if (!this.shouldLoopPlayback || this.isMediaPlayerStopRequested) {
        } else {
            mediaPlayer.seekTo(0);
            mediaPlayer.start();
        }
    }

    public /* synthetic */ boolean lambda$new$1$MonitoredMediaPlayerWrapper(MediaPlayer mediaPlayer, int i, int i2) {
        stop();
        triggerPlaybackStoppedCallback();
        return false;
    }

    public void pause() {
        if (this.mediaPlayer == null || !this.isPrepared) {
            return;
        }
        this.mediaPlayer.pause();
        this.isMediaPlayerStopRequested = true;
    }

    public void play() {
        this.isMediaPlayerStopRequested = false;
        onPlaybackWillStart();
        if (this.mediaPlayer != null && this.isPrepared) {
            this.mediaPlayer.start();
        }
        if (this.monitorThread == null) {
            this.monitorThread = new Thread(this.monitorThreadRunnable);
            this.monitorThread.setName("MediaPlayerMonitorRunnable");
            this.monitorThread.start();
        }
    }

    public void release() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }

    public void seekTo(int i) {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(i);
        }
    }

    public void stop() {
        this.isMediaPlayerStopRequested = true;
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            this.mediaPlayer.stop();
        }
        stopMonitoring();
        onPlaybackStopped();
    }
}
