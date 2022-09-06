package com.amazon.alexa.wakeword;

import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaAudioSink;
import com.amazon.alexa.api.AlexaWakeWord;
import com.amazon.alexa.audiocapturer.AudioCapturer;
import com.amazon.alexa.audiocapturer.MetricsListener;
import com.amazon.alexa.audiocapturer.RecordingRunnable;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import com.amazon.alexa.voice.pryon.asr.PryonWakeWordDetectorCompat;
import com.amazon.alexa.wakeword.ShortRingBuffer;
import com.amazon.alexa.wakeword.WakeWordDetector;
import com.amazon.alexa.wakeword.pryon.PryonCallbacksWrapper;
import com.amazon.alexa.wakeword.pryon.WakeWordDetectionCallbacks;
import com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener;
import com.amazon.alexa.wakeword.pryon.WakeWordDetectorProvider;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
/* loaded from: classes11.dex */
public class WakeWordDetectingAudioCapturer implements AudioCapturer, WakeWordDetectionCallbacks {
    @VisibleForTesting
    static final int PREROLL_SAMPLE_SIZE = 8000;
    private static final int SAMPLES_IN_ONE_MS = 16;
    private static final String TAG = "WakeWordDetectingAudioCapturer";
    private AlexaAudioSink audioSink;
    private final BufferingWakeWordDetector bufferingWakeWordDetector;
    private final WakeWordDetector.WakeWordDetectionListener callbacks;
    private final PryonWakeWordDetectorCompat detectorCompat;
    private volatile boolean isTransferringData;
    private final PryonCallbacksWrapper pryonCallbacksWrapper;
    private final ExecutorService transferringExecutor;
    private final WakeWordDetectionMetricsListener wakeWordDetectionMetricsListener;

    public WakeWordDetectingAudioCapturer(WakeWordDetectorProvider wakeWordDetectorProvider, WakeWordDetector.WakeWordDetectionListener wakeWordDetectionListener, @Nullable WakeWordDetector.AudioCaptureListener audioCaptureListener, @Nullable MetricsListener metricsListener, WakeWordDetectionMetricsListener wakeWordDetectionMetricsListener) {
        this(wakeWordDetectorProvider.mo2864get(), wakeWordDetectionListener, audioCaptureListener, metricsListener, wakeWordDetectionMetricsListener);
    }

    private AlexaWakeWord adjustWakeWordIndices(AlexaWakeWord alexaWakeWord, ShortRingBuffer.RingBufferReader ringBufferReader) {
        try {
            int available = ringBufferReader.available();
            long samplesPushedToPryon = this.bufferingWakeWordDetector.getSamplesPushedToPryon();
            long startIndexInSamples = alexaWakeWord.getStartIndexInSamples();
            String str = "Pryon detected the wake word at " + alexaWakeWord.getStartIndexInSamples() + " - " + alexaWakeWord.getEndIndexInSamples();
            long j = samplesPushedToPryon - startIndexInSamples;
            String str2 = "Samples between user utterance and end of buffer: " + j;
            long j2 = available;
            if (j <= j2) {
                long min = Math.min(8000L, j2 - j);
                String str3 = "Preroll size in samples: " + min;
                ringBufferReader.skip((int) Math.max(0L, j2 - (j + min)));
                long endIndexInSamples = (alexaWakeWord.getEndIndexInSamples() - startIndexInSamples) + min;
                String str4 = "Adjusted the indices to " + min + " - " + endIndexInSamples;
                return new AlexaWakeWord(alexaWakeWord.getWakeWordName(), min, endIndexInSamples);
            }
            throw new IOException("Not enough bytes in the buffer. This should never happen");
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            this.audioSink.abandon();
            return null;
        }
    }

    private long convertSamplesToMilliseconds(long j) {
        return j / 16;
    }

    private void startTransferring(final ShortRingBuffer.RingBufferReader ringBufferReader) {
        Log.i(TAG, "startTransferring");
        this.isTransferringData = true;
        this.transferringExecutor.execute(new Runnable() { // from class: com.amazon.alexa.wakeword.WakeWordDetectingAudioCapturer.1
            @Override // java.lang.Runnable
            public void run() {
                OutputStream openForWriting;
                int readAsBytes;
                if (WakeWordDetectingAudioCapturer.this.audioSink == null) {
                    return;
                }
                if (!WakeWordDetectingAudioCapturer.this.isCapturing()) {
                    WakeWordDetectingAudioCapturer.this.audioSink.abandon();
                    return;
                }
                try {
                    try {
                        openForWriting = WakeWordDetectingAudioCapturer.this.audioSink.openForWriting();
                    } catch (IOException e) {
                        Log.e(WakeWordDetectingAudioCapturer.TAG, e.getMessage(), e);
                        WakeWordDetectingAudioCapturer.this.audioSink.abandon();
                    }
                    try {
                        ByteBuffer allocateByteBuffer = WakeWordDetectingAudioCapturer.this.allocateByteBuffer();
                        while (WakeWordDetectingAudioCapturer.this.isCapturing() && (readAsBytes = ringBufferReader.readAsBytes(WakeWordDetectingAudioCapturer.this.getArray(allocateByteBuffer), RecordingRunnable.ACTUAL_BUFFER_SIZE)) >= 0) {
                            openForWriting.write(WakeWordDetectingAudioCapturer.this.getArray(allocateByteBuffer), 0, readAsBytes);
                        }
                        if (openForWriting != null) {
                            openForWriting.close();
                        }
                    } finally {
                    }
                } finally {
                    WakeWordDetectingAudioCapturer.this.audioSink.close();
                    WakeWordDetectingAudioCapturer.this.audioSink = null;
                    WakeWordDetectingAudioCapturer.this.isTransferringData = false;
                    String unused = WakeWordDetectingAudioCapturer.TAG;
                }
            }
        });
    }

    @VisibleForTesting
    ByteBuffer allocateByteBuffer() {
        return ByteBuffer.allocateDirect(RecordingRunnable.ACTUAL_BUFFER_SIZE);
    }

    @VisibleForTesting
    PryonCallbacksWrapper createPryonCallbacksWrapper() {
        return new PryonCallbacksWrapper(this, this.wakeWordDetectionMetricsListener);
    }

    @VisibleForTesting
    byte[] getArray(ByteBuffer byteBuffer) {
        return byteBuffer.array();
    }

    @Override // com.amazon.alexa.audiocapturer.AudioCapturer
    public synchronized boolean isCapturing() {
        return this.bufferingWakeWordDetector.isCapturing();
    }

    public synchronized boolean isDetectingWakeWord() {
        boolean z;
        if (isCapturing()) {
            if (this.bufferingWakeWordDetector.isDetectingWakeWord()) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    @Override // com.amazon.alexa.wakeword.pryon.WakeWordDetectionCallbacks
    public void onClassificationEvent(byte[] bArr, int i, byte[] bArr2) {
        this.callbacks.onClassificationEvent(new ClassificationData(bArr, i, bArr2));
    }

    @Override // com.amazon.alexa.wakeword.pryon.WakeWordDetectionCallbacks
    public void onEnrollmentExampleEvent(AlexaWakeWord alexaWakeWord, short[] sArr, byte[] bArr) {
        this.callbacks.onEnrollmentExampleEvent(new EnrollmentData(alexaWakeWord, sArr, bArr));
    }

    @Override // com.amazon.alexa.wakeword.pryon.WakeWordDetectionCallbacks
    public void onWakeWordDetected(AlexaWakeWord alexaWakeWord, @Nullable byte[] bArr) {
        stopDetectingWakeWord();
        ShortRingBuffer.RingBufferReader reader = this.bufferingWakeWordDetector.getRingBuffer().getReader();
        AlexaWakeWord adjustWakeWordIndices = adjustWakeWordIndices(alexaWakeWord, reader);
        if (adjustWakeWordIndices == null || this.audioSink == null) {
            return;
        }
        startTransferring(reader);
        long convertSamplesToMilliseconds = convertSamplesToMilliseconds(adjustWakeWordIndices.getStartIndexInSamples());
        String str = TAG;
        Log.i(str, "Wakeword detected. Preroll size: " + convertSamplesToMilliseconds + "ms");
        this.callbacks.onWakewordDetected(new WakeWordData(this.audioSink, adjustWakeWordIndices, bArr));
    }

    public synchronized void pauseDetectingWakeWord() {
        if (!isDetectingWakeWord()) {
            return;
        }
        this.bufferingWakeWordDetector.pauseDetectingWakeWord();
    }

    public synchronized void resumeDetectingWakeWord() {
        if (!isDetectingWakeWord()) {
            return;
        }
        this.bufferingWakeWordDetector.resumeDetectingWakeWord();
    }

    @Override // com.amazon.alexa.audiocapturer.AudioCapturer
    public synchronized boolean startCapturing(AlexaAudioSink alexaAudioSink) {
        if (isCapturing()) {
            Log.e(TAG, "can't start capturing");
            return false;
        }
        this.audioSink = alexaAudioSink;
        return this.bufferingWakeWordDetector.startDetectingWakeWord();
    }

    @Override // com.amazon.alexa.audiocapturer.AudioCapturer
    public synchronized void stopCapturing() {
        Log.i(TAG, "stopCapturing");
        if (!isCapturing()) {
            return;
        }
        this.bufferingWakeWordDetector.stopCapturing();
        this.detectorCompat.removePryonLiteCallbacks();
        if (this.audioSink != null && !this.isTransferringData) {
            this.audioSink.close();
            this.audioSink = null;
        }
        this.transferringExecutor.shutdown();
        this.pryonCallbacksWrapper.release();
    }

    public synchronized void stopDetectingWakeWord() {
        if (!isDetectingWakeWord()) {
            return;
        }
        this.bufferingWakeWordDetector.stopDetectingWakeWord();
    }

    @VisibleForTesting
    WakeWordDetectingAudioCapturer(PryonWakeWordDetectorCompat pryonWakeWordDetectorCompat, WakeWordDetector.WakeWordDetectionListener wakeWordDetectionListener, @Nullable WakeWordDetector.AudioCaptureListener audioCaptureListener, @Nullable MetricsListener metricsListener, WakeWordDetectionMetricsListener wakeWordDetectionMetricsListener) {
        this(pryonWakeWordDetectorCompat, new BufferingWakeWordDetector(pryonWakeWordDetectorCompat.getPryonLite(), audioCaptureListener, metricsListener), wakeWordDetectionListener, wakeWordDetectionMetricsListener);
    }

    @VisibleForTesting
    WakeWordDetectingAudioCapturer(PryonWakeWordDetectorCompat pryonWakeWordDetectorCompat, BufferingWakeWordDetector bufferingWakeWordDetector, WakeWordDetector.WakeWordDetectionListener wakeWordDetectionListener, WakeWordDetectionMetricsListener wakeWordDetectionMetricsListener) {
        this.transferringExecutor = ExecutorFactory.newSingleThreadExecutor(TAG);
        this.detectorCompat = pryonWakeWordDetectorCompat;
        this.bufferingWakeWordDetector = bufferingWakeWordDetector;
        this.callbacks = wakeWordDetectionListener;
        this.pryonCallbacksWrapper = createPryonCallbacksWrapper();
        this.detectorCompat.setPryonLiteCallbacks(this.pryonCallbacksWrapper);
        this.wakeWordDetectionMetricsListener = wakeWordDetectionMetricsListener;
    }
}
