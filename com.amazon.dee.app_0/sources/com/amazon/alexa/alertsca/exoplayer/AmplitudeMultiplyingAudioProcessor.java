package com.amazon.alexa.alertsca.exoplayer;

import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.audio.AudioProcessor;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes6.dex */
public class AmplitudeMultiplyingAudioProcessor implements AudioProcessor {
    private static final String TAG = "AmplitudeMultiplyingAudioProcessor";
    private ByteBuffer buffer;
    private int channelCount;
    private boolean inputEnded;
    private boolean isAmplificationEnabled;
    private float multiplier = 1.0f;
    private ByteBuffer outputBuffer;
    private int sampleRateHz;

    @Inject
    public AmplitudeMultiplyingAudioProcessor() {
        init();
    }

    private void init() {
        this.buffer = ByteBuffer.allocateDirect(AudioProcessor.EMPTY_BUFFER.capacity()).order(ByteOrder.nativeOrder());
        this.outputBuffer = AudioProcessor.EMPTY_BUFFER;
        this.channelCount = -1;
        this.sampleRateHz = -1;
        this.isAmplificationEnabled = false;
        this.multiplier = 1.0f;
    }

    private void prepareForOutput(int i) {
        if (this.buffer.capacity() < i) {
            this.buffer = ByteBuffer.allocateDirect(i).order(ByteOrder.nativeOrder());
        } else {
            this.buffer.clear();
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public AudioProcessor.AudioFormat configure(AudioProcessor.AudioFormat audioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        if (audioFormat.encoding == 2) {
            return audioFormat;
        }
        throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
    }

    public void enableAmplification(boolean z) {
        GeneratedOutlineSupport1.outline173("amplification enabled: ", z, TAG);
        this.isAmplificationEnabled = z;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void flush() {
        this.buffer = ByteBuffer.allocateDirect(AudioProcessor.EMPTY_BUFFER.capacity()).order(ByteOrder.nativeOrder());
        this.outputBuffer = AudioProcessor.EMPTY_BUFFER;
        this.inputEnded = false;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public ByteBuffer getOutput() {
        ByteBuffer byteBuffer = this.outputBuffer;
        this.outputBuffer = AudioProcessor.EMPTY_BUFFER;
        return byteBuffer;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean isActive() {
        return isAmplificationEnabled();
    }

    public boolean isAmplificationEnabled() {
        return this.isAmplificationEnabled;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean isEnded() {
        return this.inputEnded && this.outputBuffer == AudioProcessor.EMPTY_BUFFER;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void queueEndOfStream() {
        this.inputEnded = true;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void queueInput(ByteBuffer byteBuffer) {
        prepareForOutput(byteBuffer.remaining());
        while (byteBuffer.hasRemaining()) {
            this.buffer.putShort((short) (byteBuffer.getShort() * this.multiplier));
        }
        this.buffer.flip();
        this.outputBuffer = this.buffer;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void reset() {
        flush();
        init();
    }

    public void setMultiplier(float f) {
        String str = TAG;
        Log.i(str, "setting multiplier to: " + f);
        this.multiplier = f;
    }
}
