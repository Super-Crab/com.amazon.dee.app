package com.amazon.alexa.alertsca;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.alertsca.exoplayer.AmplitudeMultiplyingAudioProcessor;
import com.amazon.alexa.alertsca.metrics.MetricsConstants;
import com.amazon.alexa.alertsca.metrics.service.MetricsService;
import com.amazon.alexa.alertsca.utils.HandlerThreadWrapper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes6.dex */
public class AlertsExoPlayer {
    private static final String ALERTS_EXO_PLAYER = "alerts-exo-player";
    private static final String HANDLER_NAME = "exo-player-thread";
    private static final String TAG = "AlertsExoPlayer";
    private final AmplitudeMultiplyingAudioProcessor amplitudeMultiplyingAudioProcessor;
    private final Context context;
    private DataSource.Factory dataSourceFactory;
    private PlayerEventListener eventListener;
    private SimpleExoPlayer exoPlayer;
    private final HandlerThreadWrapper handlerWrapper;
    private volatile boolean isPaused;
    private volatile boolean isPrepared;
    private final MetricsService metricsService;
    private final VolumeAdjuster volumeAdjuster;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class ExoplayerEventListener extends Player.DefaultEventListener {
        private ExoplayerEventListener() {
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onPlayerError(final ExoPlaybackException exoPlaybackException) {
            String unused = AlertsExoPlayer.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onPlayerError: ");
            outline107.append(exoPlaybackException.getMessage());
            outline107.toString();
            AlertsExoPlayer.this.handlerWrapper.post(new Runnable() { // from class: com.amazon.alexa.alertsca.AlertsExoPlayer.ExoplayerEventListener.2
                @Override // java.lang.Runnable
                public void run() {
                    if (AlertsExoPlayer.this.eventListener != null) {
                        AlertsExoPlayer.this.eventListener.onPlaybackFailed(exoPlaybackException);
                    }
                    AlertsExoPlayer.this.metricsService.recordEvent(MetricsConstants.ALERTS.EXO_PLAYER.PLAYER_ERROR);
                }
            });
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onPlayerStateChanged(boolean z, int i) {
            if (i == 1) {
                String unused = AlertsExoPlayer.TAG;
                GeneratedOutlineSupport1.outline172("Player state changed: IDLE. Play when ready? ", z);
            } else if (i == 2) {
            } else {
                if (i == 3) {
                    String unused2 = AlertsExoPlayer.TAG;
                    GeneratedOutlineSupport1.outline172("Player state changed: READY. Play when ready? ", z);
                } else if (i != 4) {
                    String unused3 = AlertsExoPlayer.TAG;
                    GeneratedOutlineSupport1.outline149("Player State changed: ", i);
                } else {
                    String unused4 = AlertsExoPlayer.TAG;
                    GeneratedOutlineSupport1.outline172("Player state changed: ENDED. Play when ready? ", z);
                    AlertsExoPlayer.this.handlerWrapper.post(new Runnable() { // from class: com.amazon.alexa.alertsca.AlertsExoPlayer.ExoplayerEventListener.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (AlertsExoPlayer.this.eventListener != null) {
                                AlertsExoPlayer.this.eventListener.onPlaybackFinished();
                            }
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class ExoplayerRenderersFactory extends DefaultRenderersFactory {
        private final AmplitudeMultiplyingAudioProcessor amplitudeMultiplyingAudioProcessor;

        public ExoplayerRenderersFactory(Context context, AmplitudeMultiplyingAudioProcessor amplitudeMultiplyingAudioProcessor) {
            super(context);
            this.amplitudeMultiplyingAudioProcessor = amplitudeMultiplyingAudioProcessor;
        }
    }

    @Inject
    public AlertsExoPlayer(Context context, MetricsService metricsService, VolumeAdjuster volumeAdjuster, AmplitudeMultiplyingAudioProcessor amplitudeMultiplyingAudioProcessor) {
        this(context, new HandlerThreadWrapper(HANDLER_NAME), metricsService, volumeAdjuster, amplitudeMultiplyingAudioProcessor);
    }

    private void createInternalPlayer() {
        this.handlerWrapper.post(new Runnable() { // from class: com.amazon.alexa.alertsca.AlertsExoPlayer.7
            @Override // java.lang.Runnable
            public void run() {
                AlertsExoPlayer.this.createPlayer();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MediaSource createMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(this.dataSourceFactory).mo7417createMediaSource(uri);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createPlayer() {
        this.exoPlayer = createExoPlayer();
        SimpleExoPlayer simpleExoPlayer = this.exoPlayer;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.addListener(new ExoplayerEventListener());
        } else {
            this.metricsService.recordEvent(MetricsConstants.ALERTS.EXO_PLAYER.CREATE_PLAYER.failure());
            throw new IllegalStateException("Cannot create ExoPlayer");
        }
    }

    @VisibleForTesting
    SimpleExoPlayer createExoPlayer() {
        Context context = this.context;
        return new SimpleExoPlayer.Builder(context, new ExoplayerRenderersFactory(context, this.amplitudeMultiplyingAudioProcessor)).setTrackSelector(new DefaultTrackSelector()).build();
    }

    public void pause() {
        this.handlerWrapper.post(new Runnable() { // from class: com.amazon.alexa.alertsca.AlertsExoPlayer.3
            @Override // java.lang.Runnable
            public void run() {
                AlertsExoPlayer.this.volumeAdjuster.onPause(AlertsExoPlayer.this.exoPlayer.getAudioAttributes());
                if (AlertsExoPlayer.this.isPrepared) {
                    AlertsExoPlayer.this.isPaused = true;
                    AlertsExoPlayer.this.exoPlayer.setPlayWhenReady(false);
                } else {
                    Log.w(AlertsExoPlayer.TAG, "Called pause without preparing");
                }
                AlertsExoPlayer.this.metricsService.recordEvent(MetricsConstants.ALERTS.EXO_PLAYER.PAUSE.result(AlertsExoPlayer.this.isPrepared));
            }
        });
    }

    public void play() {
        this.handlerWrapper.post(new Runnable() { // from class: com.amazon.alexa.alertsca.AlertsExoPlayer.2
            @Override // java.lang.Runnable
            public void run() {
                AlertsExoPlayer.this.volumeAdjuster.onPlay(AlertsExoPlayer.this.exoPlayer.getAudioAttributes());
                if (AlertsExoPlayer.this.isPrepared) {
                    AlertsExoPlayer.this.isPaused = false;
                    AlertsExoPlayer.this.exoPlayer.setPlayWhenReady(true);
                } else {
                    Log.w(AlertsExoPlayer.TAG, "Calling play without a media source");
                }
                AlertsExoPlayer.this.metricsService.recordEvent(MetricsConstants.ALERTS.EXO_PLAYER.PLAY.result(AlertsExoPlayer.this.isPrepared));
            }
        });
    }

    public void prepare(@Nullable final Uri uri, @Nullable final PlayerEventListener playerEventListener, final boolean z) {
        String str = "prepare: uri = " + uri;
        this.handlerWrapper.post(new Runnable() { // from class: com.amazon.alexa.alertsca.AlertsExoPlayer.1
            @Override // java.lang.Runnable
            public void run() {
                PlayerEventListener playerEventListener2;
                if (AlertsExoPlayer.this.exoPlayer == null) {
                    AlertsExoPlayer.this.createPlayer();
                }
                if (uri == null || (playerEventListener2 = playerEventListener) == null) {
                    String unused = AlertsExoPlayer.TAG;
                    AlertsExoPlayer.this.metricsService.recordEvent(MetricsConstants.ALERTS.EXO_PLAYER.CANNOT_PREPARE);
                    return;
                }
                AlertsExoPlayer.this.eventListener = playerEventListener2;
                AlertsExoPlayer.this.volumeAdjuster.onPrepare(AlertsExoPlayer.this.exoPlayer.getAudioAttributes(), AlertsExoPlayer.this.createMediaSource(uri));
                MediaSource createMediaSource = AlertsExoPlayer.this.createMediaSource(uri);
                if (z) {
                    createMediaSource = new LoopingMediaSource(createMediaSource);
                }
                AlertsExoPlayer.this.exoPlayer.prepare(createMediaSource);
                AlertsExoPlayer.this.isPrepared = true;
            }
        });
    }

    public void release() {
        this.handlerWrapper.post(new Runnable() { // from class: com.amazon.alexa.alertsca.AlertsExoPlayer.5
            @Override // java.lang.Runnable
            public void run() {
                if (AlertsExoPlayer.this.exoPlayer != null) {
                    AlertsExoPlayer.this.exoPlayer.release();
                }
                AlertsExoPlayer.this.isPrepared = false;
            }
        });
        this.handlerWrapper.quitSafely();
        this.volumeAdjuster.tearDown();
    }

    public void setAudioAttributes(final AudioAttributes audioAttributes) {
        this.handlerWrapper.post(new Runnable() { // from class: com.amazon.alexa.alertsca.AlertsExoPlayer.6
            @Override // java.lang.Runnable
            public void run() {
                AlertsExoPlayer.this.exoPlayer.setAudioAttributes(audioAttributes, false);
            }
        });
    }

    public void stop() {
        this.handlerWrapper.post(new Runnable() { // from class: com.amazon.alexa.alertsca.AlertsExoPlayer.4
            @Override // java.lang.Runnable
            public void run() {
                AlertsExoPlayer.this.volumeAdjuster.onStop(AlertsExoPlayer.this.exoPlayer.getAudioAttributes());
                if (AlertsExoPlayer.this.isPrepared) {
                    if (!AlertsExoPlayer.this.isPaused) {
                        AlertsExoPlayer.this.exoPlayer.stop();
                    }
                    AlertsExoPlayer.this.exoPlayer.setPlayWhenReady(false);
                } else {
                    Log.w(AlertsExoPlayer.TAG, "Called stop without preparing");
                }
                AlertsExoPlayer.this.metricsService.recordEvent(MetricsConstants.ALERTS.EXO_PLAYER.STOP.result(AlertsExoPlayer.this.isPrepared));
            }
        });
    }

    @VisibleForTesting
    public AlertsExoPlayer(Context context, HandlerThreadWrapper handlerThreadWrapper, MetricsService metricsService, VolumeAdjuster volumeAdjuster, AmplitudeMultiplyingAudioProcessor amplitudeMultiplyingAudioProcessor) {
        this.context = context;
        this.handlerWrapper = handlerThreadWrapper;
        this.metricsService = metricsService;
        this.volumeAdjuster = volumeAdjuster;
        this.amplitudeMultiplyingAudioProcessor = amplitudeMultiplyingAudioProcessor;
        this.isPaused = true;
        this.dataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, ALERTS_EXO_PLAYER));
        createInternalPlayer();
    }
}
