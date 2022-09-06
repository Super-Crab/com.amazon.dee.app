package com.amazon.blueshift.bluefront.android.vad;

import android.os.SystemClock;
import com.amazon.blueshift.bluefront.android.vad.VoiceActivityDetector;
import com.amazon.blueshift.bluefront.android.vad.config.VADConfig;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
/* loaded from: classes11.dex */
public abstract class AbstractVAD<T extends VADConfig> implements VoiceActivityDetector {
    private static final int FRAME_DURATION = 10;
    private static final int VAD_FRAMES_PER_SEC = 100;
    private final int mEndpointingThreshold;
    private final int mSampleRate;
    private final ShortBuffer mSamplesBuffer;
    private long mStartpointedAt;
    private final int mStartpointingThreshold;
    private ByteBuffer mVAD;
    private final int mWakewordStartOfUtteranceEndpointingThreshold;
    private int mConsecutiveSpeechFrames = 0;
    private int mConsecutiveNonSpeechFrames = 0;
    private VoiceActivityDetector.VADState mVADState = VoiceActivityDetector.VADState.NOT_STARTPOINTED;

    static {
        System.loadLibrary("blueshift-audioprocessing");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractVAD(int i, T t) {
        this.mSampleRate = i;
        this.mStartpointingThreshold = t.getStartpointingThreshold();
        this.mEndpointingThreshold = t.getEndpointingThreshold();
        this.mWakewordStartOfUtteranceEndpointingThreshold = t.getWakewordStartOfUtteranceEndpointingThreshold();
        this.mVAD = setupVAD(t);
        this.mSamplesBuffer = ShortBuffer.wrap(new short[this.mSampleRate / 100]);
    }

    private boolean atStartOfWakewordUtterance() {
        return (SystemClock.elapsedRealtime() - this.mStartpointedAt) / 10 < ((long) this.mWakewordStartOfUtteranceEndpointingThreshold);
    }

    @Override // com.amazon.blueshift.bluefront.android.vad.VoiceActivityDetector, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (this.mVAD != null) {
            destroyVAD(this.mVAD);
            this.mVAD = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public native ByteBuffer createVAD(boolean z);

    protected native int destroyVAD(ByteBuffer byteBuffer);

    protected native int isSpeech(ByteBuffer byteBuffer, short[] sArr, int i);

    @Override // com.amazon.blueshift.bluefront.android.vad.VoiceActivityDetector
    public final synchronized VoiceActivityDetector.VADState processSamples(short[] sArr, int i) throws VADException {
        if (this.mVAD == null) {
            throw new VADException("VAD is not initialized");
        }
        int i2 = 0;
        while (true) {
            int i3 = i - i2;
            if (this.mSamplesBuffer.remaining() <= i3) {
                int remaining = this.mSamplesBuffer.remaining();
                this.mSamplesBuffer.put(sArr, i2, remaining);
                i2 += remaining;
                int isSpeech = isSpeech(this.mVAD, this.mSamplesBuffer.array(), this.mSampleRate);
                this.mSamplesBuffer.clear();
                if (isSpeech == 1) {
                    this.mConsecutiveSpeechFrames++;
                    this.mConsecutiveNonSpeechFrames = 0;
                } else if (isSpeech == 0) {
                    this.mConsecutiveNonSpeechFrames++;
                    this.mConsecutiveSpeechFrames = 0;
                } else {
                    throw new VADException("Error processing speech frames");
                }
                updateVADState();
            } else {
                this.mSamplesBuffer.put(sArr, i2, i3);
            }
        }
        return this.mVADState;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public native int setDNNThreshold(ByteBuffer byteBuffer, float f);

    /* JADX INFO: Access modifiers changed from: protected */
    public native int setWebRtcCustomizedMode(ByteBuffer byteBuffer, int i, int i2, int i3, int i4);

    /* JADX INFO: Access modifiers changed from: protected */
    public native int setWebRtcMode(ByteBuffer byteBuffer, int i);

    protected abstract ByteBuffer setupVAD(T t);

    void updateVADState() {
        if (this.mVADState == VoiceActivityDetector.VADState.NOT_STARTPOINTED && this.mConsecutiveSpeechFrames >= this.mStartpointingThreshold) {
            this.mVADState = VoiceActivityDetector.VADState.STARTPOINTED;
            this.mStartpointedAt = SystemClock.elapsedRealtime();
        } else if (this.mVADState != VoiceActivityDetector.VADState.STARTPOINTED || this.mConsecutiveNonSpeechFrames < this.mEndpointingThreshold) {
        } else {
            if (this.mWakewordStartOfUtteranceEndpointingThreshold != 0 && atStartOfWakewordUtterance()) {
                return;
            }
            this.mVADState = VoiceActivityDetector.VADState.ENDPOINTED;
        }
    }
}
