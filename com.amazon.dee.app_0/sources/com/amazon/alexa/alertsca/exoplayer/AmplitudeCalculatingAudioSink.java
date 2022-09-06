package com.amazon.alexa.alertsca.exoplayer;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.audio.AudioSink;
import com.google.android.exoplayer2.audio.AuxEffectInfo;
import java.nio.ByteBuffer;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes6.dex */
public class AmplitudeCalculatingAudioSink implements AudioSink {
    private long currentTimeUs;
    private boolean handledEndOfStream;
    private float maxAmplitudeMultiplier;
    private boolean maxAmplitudeMultiplierChanged;
    private PlaybackParameters playbackParameters;

    @Inject
    public AmplitudeCalculatingAudioSink() {
        init();
    }

    private void init() {
        this.currentTimeUs = Long.MIN_VALUE;
        this.handledEndOfStream = false;
        this.playbackParameters = null;
        this.maxAmplitudeMultiplierChanged = false;
        this.maxAmplitudeMultiplier = Float.MAX_VALUE;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void configure(Format format, int i, @Nullable int[] iArr) throws AudioSink.ConfigurationException {
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void disableTunneling() {
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void enableTunnelingV21() {
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void experimentalFlushWithoutAudioTrackRelease() {
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void flush() {
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public long getCurrentPositionUs(boolean z) {
        return this.currentTimeUs;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public int getFormatSupport(Format format) {
        return 0;
    }

    public float getMaxAmplitudeMultiplier() {
        if (!this.maxAmplitudeMultiplierChanged) {
            return 1.0f;
        }
        return this.maxAmplitudeMultiplier;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public PlaybackParameters getPlaybackParameters() {
        return this.playbackParameters;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public boolean getSkipSilenceEnabled() {
        return false;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public boolean handleBuffer(ByteBuffer byteBuffer, long j, int i) throws AudioSink.InitializationException, AudioSink.WriteException {
        this.currentTimeUs = j;
        while (byteBuffer.hasRemaining()) {
            short s = byteBuffer.getShort();
            if (s != 0) {
                this.maxAmplitudeMultiplier = Math.min(this.maxAmplitudeMultiplier, 32767.0f / Math.abs((int) s));
                this.maxAmplitudeMultiplierChanged = true;
            }
        }
        return true;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void handleDiscontinuity() {
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public boolean hasPendingData() {
        return false;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public boolean isEnded() {
        return this.handledEndOfStream;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void pause() {
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void play() {
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void playToEndOfStream() throws AudioSink.WriteException {
        this.handledEndOfStream = true;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void reset() {
        init();
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void setAudioAttributes(AudioAttributes audioAttributes) {
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void setAudioSessionId(int i) {
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void setAuxEffectInfo(AuxEffectInfo auxEffectInfo) {
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void setListener(AudioSink.Listener listener) {
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        this.playbackParameters = playbackParameters;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void setSkipSilenceEnabled(boolean z) {
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void setVolume(float f) {
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public boolean supportsFormat(Format format) {
        return false;
    }
}
