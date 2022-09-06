package com.google.android.exoplayer2.ui;

import amazon.speech.simclient.capability.CapabilityQueryConstants;
import android.annotation.SuppressLint;
import android.os.Looper;
import android.widget.TextView;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.amazon.tarazed.core.session.sessionEvents.PlaybackStates;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.util.Assertions;
import java.util.Locale;
/* loaded from: classes2.dex */
public class DebugTextViewHelper implements Player.EventListener, Runnable {
    private static final int REFRESH_INTERVAL_MS = 1000;
    private final SimpleExoPlayer player;
    private boolean started;
    private final TextView textView;

    public DebugTextViewHelper(SimpleExoPlayer simpleExoPlayer, TextView textView) {
        Assertions.checkArgument(simpleExoPlayer.getApplicationLooper() == Looper.getMainLooper());
        this.player = simpleExoPlayer;
        this.textView = textView;
    }

    private static String getDecoderCountersBufferCountString(DecoderCounters decoderCounters) {
        if (decoderCounters == null) {
            return "";
        }
        decoderCounters.ensureUpdated();
        return " sib:" + decoderCounters.skippedInputBufferCount + " sb:" + decoderCounters.skippedOutputBufferCount + " rb:" + decoderCounters.renderedOutputBufferCount + " db:" + decoderCounters.droppedBufferCount + " mcdb:" + decoderCounters.maxConsecutiveDroppedBufferCount + " dk:" + decoderCounters.droppedToKeyframeCount;
    }

    private static String getPixelAspectRatioString(float f) {
        if (f == -1.0f || f == 1.0f) {
            return "";
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(" par:");
        outline107.append(String.format(Locale.US, "%.02f", Float.valueOf(f)));
        return outline107.toString();
    }

    private static String getVideoFrameProcessingOffsetAverageString(long j, int i) {
        return i == 0 ? CapabilityQueryConstants.TARGET_NOT_AVAILABLE : String.valueOf((long) (j / i));
    }

    protected String getAudioString() {
        Format audioFormat = this.player.getAudioFormat();
        DecoderCounters audioDecoderCounters = this.player.getAudioDecoderCounters();
        if (audioFormat == null || audioDecoderCounters == null) {
            return "";
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("\n");
        outline107.append(audioFormat.sampleMimeType);
        outline107.append("(id:");
        outline107.append(audioFormat.id);
        outline107.append(" hz:");
        outline107.append(audioFormat.sampleRate);
        outline107.append(" ch:");
        outline107.append(audioFormat.channelCount);
        return GeneratedOutlineSupport1.outline91(outline107, getDecoderCountersBufferCountString(audioDecoderCounters), ")");
    }

    protected String getDebugString() {
        return getPlayerStateString() + getVideoString() + getAudioString();
    }

    protected String getPlayerStateString() {
        int playbackState = this.player.getPlaybackState();
        return String.format("playWhenReady:%s playbackState:%s window:%s", Boolean.valueOf(this.player.getPlayWhenReady()), playbackState != 1 ? playbackState != 2 ? playbackState != 3 ? playbackState != 4 ? "unknown" : PlaybackStates.ENDED : "ready" : "buffering" : "idle", Integer.valueOf(this.player.getCurrentWindowIndex()));
    }

    protected String getVideoString() {
        Format videoFormat = this.player.getVideoFormat();
        DecoderCounters videoDecoderCounters = this.player.getVideoDecoderCounters();
        if (videoFormat == null || videoDecoderCounters == null) {
            return "";
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("\n");
        outline107.append(videoFormat.sampleMimeType);
        outline107.append("(id:");
        outline107.append(videoFormat.id);
        outline107.append(" r:");
        outline107.append(videoFormat.width);
        outline107.append(ReactProperties.HereMapMarker.X);
        outline107.append(videoFormat.height);
        outline107.append(getPixelAspectRatioString(videoFormat.pixelWidthHeightRatio));
        outline107.append(getDecoderCountersBufferCountString(videoDecoderCounters));
        outline107.append(" vfpo: ");
        return GeneratedOutlineSupport1.outline91(outline107, getVideoFrameProcessingOffsetAverageString(videoDecoderCounters.totalVideoFrameProcessingOffsetUs, videoDecoderCounters.videoFrameProcessingOffsetCount), ")");
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public final void onPlayWhenReadyChanged(boolean z, int i) {
        updateAndPost();
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public final void onPlaybackStateChanged(int i) {
        updateAndPost();
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public final void onPositionDiscontinuity(int i) {
        updateAndPost();
    }

    @Override // java.lang.Runnable
    public final void run() {
        updateAndPost();
    }

    public final void start() {
        if (this.started) {
            return;
        }
        this.started = true;
        this.player.addListener(this);
        updateAndPost();
    }

    public final void stop() {
        if (!this.started) {
            return;
        }
        this.started = false;
        this.player.removeListener(this);
        this.textView.removeCallbacks(this);
    }

    @SuppressLint({"SetTextI18n"})
    protected final void updateAndPost() {
        this.textView.setText(getDebugString());
        this.textView.removeCallbacks(this);
        this.textView.postDelayed(this, 1000L);
    }
}
