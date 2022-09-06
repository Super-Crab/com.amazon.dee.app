package com.amazon.alexa.accessorykit.echoauto;

import android.media.AudioRecord;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes6.dex */
public class PlaybackLatencyMeasurementThread extends Thread {
    private static Lock lock = new ReentrantLock();
    private final AudioPlayer audioPlayer;
    private final AudioRecord audioRecord;
    private final Callback callback;
    private final float[] frequencies;
    private final double listenFor;
    private final float[] recordingFloats;
    private final double[] thresholds;
    private final int recordingSampleRate = 44100;
    private final int samplesPerMillisecond = Double.valueOf(Math.ceil(44.1d)).intValue();
    private final int recordingArraySize = this.samplesPerMillisecond * 25;
    private final int recordingBufferBytes = this.recordingArraySize * 4;
    private final double delayBuffer = 2500.0d;
    private volatile boolean isStopRequested = false;

    /* loaded from: classes6.dex */
    public interface Callback {
        void onLatencyMeasured(long j);

        void timedOut();
    }

    public PlaybackLatencyMeasurementThread(AudioPlayer audioPlayer, float[] fArr, double[] dArr, Callback callback) {
        Preconditions.notNull(callback, "callback");
        Preconditions.notNull(audioPlayer, "audioPlayer");
        this.frequencies = fArr;
        this.listenFor = audioPlayer.duration() + 2500.0d;
        this.thresholds = dArr;
        this.callback = callback;
        this.audioPlayer = audioPlayer;
        this.audioRecord = new AudioRecord(1, 44100, 16, 4, this.recordingBufferBytes);
        this.recordingFloats = new float[this.recordingArraySize];
    }

    private void cleanupThread() {
        AudioRecord audioRecord = this.audioRecord;
        if (audioRecord != null) {
            audioRecord.stop();
            this.audioRecord.release();
        }
        AudioPlayer audioPlayer = this.audioPlayer;
        if (audioPlayer != null) {
            audioPlayer.stop();
            this.audioPlayer.release();
        }
    }

    private boolean hadReadError(int i) {
        if (i == -6) {
            Logger.e("AudioRecord object no longer valid. Needs to be recreated", new IllegalStateException());
        } else if (i == -3) {
            Logger.e("AudioRecord object not properly initialized", new IllegalStateException());
        } else if (i == -2) {
            Logger.e("Invalid arguments to AudioRecord", new IllegalStateException());
        } else if (i != -1) {
            return i < 0;
        } else {
            Logger.e("Unknown error while getting AudioRecord data", new IllegalStateException());
        }
        return true;
    }

    private void recordUntilDetectedOrTimedOut(GoertzelManager goertzelManager) {
        this.audioRecord.startRecording();
        this.audioPlayer.start();
        long currentTimeMillis = System.currentTimeMillis();
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(this.recordingBufferBytes);
        allocateDirect.order(ByteOrder.LITTLE_ENDIAN);
        while (System.currentTimeMillis() - currentTimeMillis < this.listenFor && !this.isStopRequested) {
            allocateDirect.clear();
            if (hadReadError(this.audioRecord.read(allocateDirect, this.recordingBufferBytes))) {
                Logger.e("Read error, skipping one block.");
            } else if (!goertzelManager.isDetected()) {
                allocateDirect.asFloatBuffer().get(this.recordingFloats);
                goertzelManager.processSamples(this.recordingFloats);
            }
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        if (lock.tryLock()) {
            Logger.d("PlaybackLatencyMeasurementThread run");
            try {
                GoertzelManager goertzelManager = new GoertzelManager(44100.0f, this.recordingArraySize, this.frequencies, this.thresholds);
                recordUntilDetectedOrTimedOut(goertzelManager);
                if (goertzelManager.isDetected()) {
                    long samplesProcessedBeforeDetection = (goertzelManager.samplesProcessedBeforeDetection(0) * 1000) / 44100;
                    Logger.d("Goertzel filter detected the tone, estimated playback latency is %d ms.", Long.valueOf(samplesProcessedBeforeDetection));
                    this.callback.onLatencyMeasured(samplesProcessedBeforeDetection);
                } else {
                    this.callback.timedOut();
                }
            } finally {
                cleanupThread();
                lock.unlock();
                Logger.d("PlaybackLatencyMeasurementThread finishing.");
            }
        }
    }

    public void stopThread() {
        this.isStopRequested = true;
    }
}
