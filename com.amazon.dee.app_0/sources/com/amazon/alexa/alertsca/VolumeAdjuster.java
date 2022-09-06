package com.amazon.alexa.alertsca;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.alertsca.exoplayer.AmplitudeCalculatingAudioSink;
import com.amazon.alexa.alertsca.exoplayer.AmplitudeCalculatingEventListener;
import com.amazon.alexa.alertsca.exoplayer.AmplitudeCalculatingExoPlayerRenderersFactory;
import com.amazon.alexa.alertsca.exoplayer.AmplitudeMultiplyingAudioProcessor;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.util.Util;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes6.dex */
public class VolumeAdjuster {
    private static final String TAG = "VolumeAdjuster";
    static final float VOLUME_REQUIRED = 0.7f;
    private final AmplitudeCalculatingAudioSink amplitudeCalculatingAudioSink;
    private SimpleExoPlayer amplitudeCalculatingExoPlayer;
    private final AmplitudeMultiplyingAudioProcessor amplitudeMultiplyingAudioProcessor;
    private final AudioManager audioManager;
    private final Context context;
    private final Map<Integer, Integer> originalVolumeByStream;

    @Inject
    public VolumeAdjuster(Context context, AudioManager audioManager, AmplitudeMultiplyingAudioProcessor amplitudeMultiplyingAudioProcessor, AmplitudeCalculatingAudioSink amplitudeCalculatingAudioSink) {
        this.originalVolumeByStream = new HashMap();
        this.context = context;
        this.audioManager = (AudioManager) Objects.requireNonNull(audioManager, "audioManager cannot be null");
        this.amplitudeMultiplyingAudioProcessor = amplitudeMultiplyingAudioProcessor;
        this.amplitudeCalculatingAudioSink = amplitudeCalculatingAudioSink;
    }

    private SimpleExoPlayer createAmplitudeCalculatingExoPlayer() {
        Context context = this.context;
        return new SimpleExoPlayer.Builder(context, new AmplitudeCalculatingExoPlayerRenderersFactory(context, this.amplitudeCalculatingAudioSink)).setTrackSelector(new DefaultTrackSelector()).build();
    }

    private int getCurrentStream(AudioAttributes audioAttributes) {
        return Util.getStreamTypeForAudioUsage(audioAttributes.usage);
    }

    private void resetToOriginalVolumeLevel(AudioAttributes audioAttributes) {
        int currentStream = getCurrentStream(audioAttributes);
        Integer num = this.originalVolumeByStream.get(Integer.valueOf(currentStream));
        if (num == null) {
            Log.i(TAG, "No entry found related to the corresponding stream.");
            return;
        }
        setStreamVolume(currentStream, num.intValue());
        this.originalVolumeByStream.remove(Integer.valueOf(currentStream));
    }

    private void setStreamVolume(int i, int i2) {
        try {
            this.audioManager.setStreamVolume(i, i2, 0);
        } catch (Exception e) {
            Log.w(TAG, "Couldn't adjust volume.", e);
        }
    }

    private SimpleExoPlayer setUpExoPlayerIfNull(SimpleExoPlayer simpleExoPlayer, AmplitudeMultiplyingAudioProcessor amplitudeMultiplyingAudioProcessor, AmplitudeCalculatingAudioSink amplitudeCalculatingAudioSink) {
        if (simpleExoPlayer == null) {
            simpleExoPlayer = createAmplitudeCalculatingExoPlayer();
        }
        simpleExoPlayer.addListener(new AmplitudeCalculatingEventListener(amplitudeMultiplyingAudioProcessor, amplitudeCalculatingAudioSink));
        return simpleExoPlayer;
    }

    private boolean shouldIncreaseStreamVolume(AudioAttributes audioAttributes) {
        return getCurrentStream(audioAttributes) != 3 || !this.audioManager.isMusicActive();
    }

    public void onPause(AudioAttributes audioAttributes) {
        resetToOriginalVolumeLevel(audioAttributes);
    }

    public void onPlay(AudioAttributes audioAttributes) {
        if (shouldIncreaseStreamVolume(audioAttributes)) {
            Log.i(TAG, "onPlay - increasing stream volume");
            int currentStream = getCurrentStream(audioAttributes);
            this.originalVolumeByStream.put(Integer.valueOf(currentStream), Integer.valueOf(this.audioManager.getStreamVolume(currentStream)));
            setStreamVolume(currentStream, (int) (this.audioManager.getStreamMaxVolume(currentStream) * 0.7f));
        }
    }

    public void onPrepare(AudioAttributes audioAttributes, MediaSource mediaSource) {
        if (shouldIncreaseStreamVolume(audioAttributes)) {
            this.amplitudeMultiplyingAudioProcessor.enableAmplification(false);
            return;
        }
        Log.i(TAG, "onPrepare - enabling amplification");
        this.amplitudeMultiplyingAudioProcessor.enableAmplification(true);
        SimpleExoPlayer upExoPlayerIfNull = setUpExoPlayerIfNull(this.amplitudeCalculatingExoPlayer, this.amplitudeMultiplyingAudioProcessor, this.amplitudeCalculatingAudioSink);
        upExoPlayerIfNull.prepare(mediaSource);
        upExoPlayerIfNull.setPlayWhenReady(true);
    }

    public void onStop(AudioAttributes audioAttributes) {
        resetToOriginalVolumeLevel(audioAttributes);
    }

    public void tearDown() {
        this.originalVolumeByStream.clear();
        SimpleExoPlayer simpleExoPlayer = this.amplitudeCalculatingExoPlayer;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.release();
        }
    }

    @VisibleForTesting
    protected VolumeAdjuster(Context context, AudioManager audioManager, AmplitudeMultiplyingAudioProcessor amplitudeMultiplyingAudioProcessor, AmplitudeCalculatingAudioSink amplitudeCalculatingAudioSink, SimpleExoPlayer simpleExoPlayer) {
        this(context, audioManager, amplitudeMultiplyingAudioProcessor, amplitudeCalculatingAudioSink);
        this.amplitudeCalculatingExoPlayer = setUpExoPlayerIfNull(simpleExoPlayer, amplitudeMultiplyingAudioProcessor, amplitudeCalculatingAudioSink);
    }
}
