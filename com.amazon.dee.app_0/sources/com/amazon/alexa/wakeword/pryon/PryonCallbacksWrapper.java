package com.amazon.alexa.wakeword.pryon;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaWakeWord;
import com.amazon.alexa.utils.validation.Preconditions;
import com.amazon.pryon.android.asr.PryonLite5000;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* loaded from: classes11.dex */
public class PryonCallbacksWrapper implements PryonLite5000.Callbacks {
    private static final String TAG = "PryonCallbacksWrapper";
    private final ExecutorService callbackExecutorService;
    private final WakeWordDetectionCallbacks callbacks;
    private final WakeWordDetectionMetricsListener metricsListener;

    public PryonCallbacksWrapper(WakeWordDetectionCallbacks wakeWordDetectionCallbacks, WakeWordDetectionMetricsListener wakeWordDetectionMetricsListener) {
        this(wakeWordDetectionCallbacks, Executors.newSingleThreadExecutor(), wakeWordDetectionMetricsListener);
    }

    @Override // com.amazon.pryon.android.asr.PryonLite5000.Callbacks
    public void errorEvent(int i) {
        String str = TAG;
        Log.e(str, "Error emitted by wakeword engine, error = " + i);
        this.metricsListener.onPryonErrorEvent(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ExecutorService getCallbackExecutor() {
        return this.callbackExecutorService;
    }

    public void release() {
        this.callbackExecutorService.shutdown();
    }

    @Override // com.amazon.pryon.android.asr.PryonLite5000.Callbacks
    public void speakerVerificationClassificationEvent(final byte[] bArr, final int i, final byte[] bArr2, float f, float f2, float f3, int i2) {
        Log.i(TAG, "speakerVerificationClassificationEvent");
        this.callbackExecutorService.execute(new Runnable() { // from class: com.amazon.alexa.wakeword.pryon.PryonCallbacksWrapper.2
            @Override // java.lang.Runnable
            public void run() {
                PryonCallbacksWrapper.this.callbacks.onClassificationEvent(bArr, i, bArr2);
            }
        });
    }

    @Override // com.amazon.pryon.android.asr.PryonLite5000.Callbacks
    public void speakerVerificationEnrollmentEvent(byte[] bArr, int i, byte[] bArr2, byte[] bArr3) {
    }

    @Override // com.amazon.pryon.android.asr.PryonLite5000.Callbacks
    public void speakerVerificationWakewordExampleEvent(final String str, final int i, final int i2, final short[] sArr, final byte[] bArr) {
        Log.i(TAG, "speakerVerificationWakewordExampleEvent");
        this.callbackExecutorService.execute(new Runnable() { // from class: com.amazon.alexa.wakeword.pryon.PryonCallbacksWrapper.3
            @Override // java.lang.Runnable
            public void run() {
                PryonCallbacksWrapper.this.callbacks.onEnrollmentExampleEvent(new AlexaWakeWord(str, i, i2), sArr, bArr);
            }
        });
    }

    @Override // com.amazon.pryon.android.asr.PryonLite5000.Callbacks
    public void vadStateChanged(int i) {
    }

    @Override // com.amazon.pryon.android.asr.PryonLite5000.Callbacks
    public void wakeWordDetected(final String str, final long j, final long j2, final byte[] bArr) {
        Log.i(TAG, "wakeWordDetected");
        this.callbackExecutorService.execute(new Runnable() { // from class: com.amazon.alexa.wakeword.pryon.PryonCallbacksWrapper.1
            @Override // java.lang.Runnable
            public void run() {
                PryonCallbacksWrapper.this.callbacks.onWakeWordDetected(new AlexaWakeWord(str, j, j2), bArr);
            }
        });
    }

    @VisibleForTesting
    protected PryonCallbacksWrapper(WakeWordDetectionCallbacks wakeWordDetectionCallbacks, ExecutorService executorService, WakeWordDetectionMetricsListener wakeWordDetectionMetricsListener) {
        Preconditions.notNull(wakeWordDetectionCallbacks, "Callbacks is null");
        Preconditions.notNull(executorService, "Executor is null");
        this.callbacks = wakeWordDetectionCallbacks;
        this.callbackExecutorService = executorService;
        this.metricsListener = wakeWordDetectionMetricsListener;
    }
}
