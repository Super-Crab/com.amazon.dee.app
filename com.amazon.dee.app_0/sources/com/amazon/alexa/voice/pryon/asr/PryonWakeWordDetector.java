package com.amazon.alexa.voice.pryon.asr;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.amazon.pryon.android.asr.PryonLite5000;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
/* loaded from: classes11.dex */
public class PryonWakeWordDetector implements WakeWordDetector, PryonLite5000.Callbacks {
    private static final String TAG = "PryonWakeWordDetector";
    private PryonLite5000.Callbacks eventHandlers;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private PryonTransferThread transferThread;
    private final boolean useAcousticEchoCanceler;
    private final boolean useNoiseSuppressor;
    private PryonLite5000 wakewordEngine;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes11.dex */
    public class InitializeException extends IllegalStateException {
        private int errorCode;

        public InitializeException(String str, int i) {
            super(str);
            this.errorCode = i;
        }

        public int getErrorCode() {
            return this.errorCode;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes11.dex */
    public static class PryonTransferThread extends Thread {
        private volatile boolean running = true;
        private final boolean useAcousticEchoCanceler;
        private final boolean useNoiseSuppressor;
        private final PryonLite5000 wakewordEngine;

        PryonTransferThread(PryonLite5000 pryonLite5000, boolean z, boolean z2) {
            this.wakewordEngine = pryonLite5000;
            this.useAcousticEchoCanceler = z;
            this.useNoiseSuppressor = z2;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            String str = PryonWakeWordDetector.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Beginning transfer of audio buffer to Pryon native component.  useAEC: ");
            outline107.append(this.useAcousticEchoCanceler);
            outline107.append(" useNS: ");
            outline107.append(this.useNoiseSuppressor);
            Log.i(str, outline107.toString());
            AudioCapturer audioCapturer = new AudioCapturer(this.wakewordEngine.getSamplesPerFrame(), this.useAcousticEchoCanceler, this.useNoiseSuppressor);
            while (this.running) {
                try {
                    try {
                        int pushAudio = this.wakewordEngine.pushAudio(audioCapturer.read());
                        if (pushAudio != 0) {
                            throw new IOException("Failed to push audio samples to Pryon. Error code = " + pushAudio);
                        }
                    } catch (IOException e) {
                        Log.e(PryonWakeWordDetector.TAG, "Failed to transfer audio to Wake Word engine.", e);
                    }
                } catch (Throwable th) {
                    audioCapturer.release();
                    throw th;
                }
            }
            audioCapturer.release();
            Log.i(PryonWakeWordDetector.TAG, "Transfer of audio buffer to Pryon is ending.");
        }

        public void terminate() {
            this.running = false;
        }
    }

    public PryonWakeWordDetector(PryonLite5000.Config config, boolean z, boolean z2) throws UnsupportedOperationException {
        this.useAcousticEchoCanceler = z;
        this.useNoiseSuppressor = z2;
        init(config);
    }

    private void init(PryonLite5000.Config config) throws UnsupportedOperationException {
        Log.i(TAG, "init");
        if (PryonLite5000.isAvailable()) {
            this.wakewordEngine = new PryonLite5000(this);
            Log.i(TAG, "Calling PryonLite initialize() from PryonWakeWordDetector.init()...");
            int initialize = this.wakewordEngine.initialize(config);
            if (initialize == 0) {
                return;
            }
            String str = TAG;
            Log.e(str, "PryonLite initialize() failed, error = " + initialize);
            release();
            throw new InitializeException(GeneratedOutlineSupport1.outline49("Failed to init PryonWakeWordDetector. ErrorCode ", initialize), initialize);
        }
        this.wakewordEngine = null;
        throw new UnsupportedOperationException("PryonLite wakeword engine is not available on this platform.");
    }

    private boolean isPryonAvailable() {
        return PryonLite5000.isAvailable() && this.wakewordEngine != null;
    }

    @Override // com.amazon.pryon.android.asr.PryonLite5000.Callbacks
    public void errorEvent(int i) {
        String str = TAG;
        Log.e(str, "Error emitted by wakeword engine, error = " + i);
    }

    public PryonLite5000 getPryonLite() {
        return this.wakewordEngine;
    }

    @Override // com.amazon.alexa.voice.pryon.asr.WakeWordDetector
    public boolean isActive() {
        return this.transferThread != null;
    }

    public /* synthetic */ void lambda$speakerVerificationClassificationEvent$2$PryonWakeWordDetector(byte[] bArr, int i, byte[] bArr2, float f, float f2, float f3, int i2) {
        PryonLite5000.Callbacks callbacks = this.eventHandlers;
        if (callbacks == null) {
            return;
        }
        callbacks.speakerVerificationClassificationEvent(bArr, i, bArr2, f, f2, f3, i2);
    }

    public /* synthetic */ void lambda$speakerVerificationWakewordExampleEvent$3$PryonWakeWordDetector(String str, int i, int i2, short[] sArr, byte[] bArr) {
        PryonLite5000.Callbacks callbacks = this.eventHandlers;
        if (callbacks == null) {
            return;
        }
        callbacks.speakerVerificationWakewordExampleEvent(str, i, i2, sArr, bArr);
    }

    public /* synthetic */ void lambda$vadStateChanged$1$PryonWakeWordDetector(int i) {
        PryonLite5000.Callbacks callbacks = this.eventHandlers;
        if (callbacks == null) {
            return;
        }
        callbacks.vadStateChanged(i);
    }

    public /* synthetic */ void lambda$wakeWordDetected$0$PryonWakeWordDetector(String str, long j, long j2, byte[] bArr) {
        PryonLite5000.Callbacks callbacks = this.eventHandlers;
        if (callbacks == null) {
            return;
        }
        callbacks.wakeWordDetected(str, j, j2, bArr);
    }

    @Override // com.amazon.alexa.voice.pryon.asr.WakeWordDetector
    public void release() {
        stop();
        if (this.wakewordEngine == null) {
            return;
        }
        Log.i(TAG, "Calling PryonLite destroy() from PryonWakeWordDetector.release()...");
        int destroy = this.wakewordEngine.destroy();
        this.wakewordEngine = null;
        if (destroy == 0) {
            return;
        }
        String str = TAG;
        Log.e(str, "PryonLite destroy() failed, error = " + destroy);
    }

    @Override // com.amazon.alexa.voice.pryon.asr.WakeWordDetector
    public void removePryonLiteCallbacks() {
        this.eventHandlers = null;
    }

    @Override // com.amazon.alexa.voice.pryon.asr.WakeWordDetector
    public void setPryonLiteCallbacks(PryonLite5000.Callbacks callbacks) {
        this.eventHandlers = callbacks;
    }

    @Override // com.amazon.alexa.voice.pryon.asr.WakeWordDetector
    public int setSensitivityThreshold(int i) {
        PryonLite5000 pryonLite5000;
        if (!PryonLite5000.isAvailable() || (pryonLite5000 = this.wakewordEngine) == null) {
            return -1;
        }
        return pryonLite5000.wakewordSetDetectionThreshold(i);
    }

    @Override // com.amazon.pryon.android.asr.PryonLite5000.Callbacks
    public void speakerVerificationClassificationEvent(final byte[] bArr, final int i, final byte[] bArr2, final float f, final float f2, final float f3, final int i2) {
        this.handler.post(new Runnable() { // from class: com.amazon.alexa.voice.pryon.asr.-$$Lambda$PryonWakeWordDetector$dWCXbf72zQ0QCF4PRO57ahrmXTA
            @Override // java.lang.Runnable
            public final void run() {
                PryonWakeWordDetector.this.lambda$speakerVerificationClassificationEvent$2$PryonWakeWordDetector(bArr, i, bArr2, f, f2, f3, i2);
            }
        });
    }

    @Override // com.amazon.pryon.android.asr.PryonLite5000.Callbacks
    public void speakerVerificationEnrollmentEvent(byte[] bArr, int i, byte[] bArr2, byte[] bArr3) {
    }

    @Override // com.amazon.pryon.android.asr.PryonLite5000.Callbacks
    public void speakerVerificationWakewordExampleEvent(final String str, final int i, final int i2, final short[] sArr, final byte[] bArr) {
        this.handler.post(new Runnable() { // from class: com.amazon.alexa.voice.pryon.asr.-$$Lambda$PryonWakeWordDetector$vO7F1hMfleHKgMTVNI6hY77jBDo
            @Override // java.lang.Runnable
            public final void run() {
                PryonWakeWordDetector.this.lambda$speakerVerificationWakewordExampleEvent$3$PryonWakeWordDetector(str, i, i2, sArr, bArr);
            }
        });
    }

    @Override // com.amazon.alexa.voice.pryon.asr.WakeWordDetector
    public void start() {
        if (!isPryonAvailable() || isActive()) {
            return;
        }
        this.transferThread = new PryonTransferThread(this.wakewordEngine, this.useAcousticEchoCanceler, this.useNoiseSuppressor);
        this.transferThread.start();
    }

    @Override // com.amazon.alexa.voice.pryon.asr.WakeWordDetector
    public void stop() {
        if (!isPryonAvailable() || !isActive()) {
            return;
        }
        this.transferThread.terminate();
        try {
            try {
                this.transferThread.join();
            } catch (InterruptedException unused) {
                this.transferThread.interrupt();
            }
        } finally {
            this.transferThread = null;
        }
    }

    @Override // com.amazon.pryon.android.asr.PryonLite5000.Callbacks
    public void vadStateChanged(final int i) {
        this.handler.post(new Runnable() { // from class: com.amazon.alexa.voice.pryon.asr.-$$Lambda$PryonWakeWordDetector$3Ga4jslM5wL8BopwPRuaHJi4zsM
            @Override // java.lang.Runnable
            public final void run() {
                PryonWakeWordDetector.this.lambda$vadStateChanged$1$PryonWakeWordDetector(i);
            }
        });
    }

    @Override // com.amazon.pryon.android.asr.PryonLite5000.Callbacks
    public void wakeWordDetected(final String str, final long j, final long j2, final byte[] bArr) {
        this.handler.post(new Runnable() { // from class: com.amazon.alexa.voice.pryon.asr.-$$Lambda$PryonWakeWordDetector$3-4yTBJW14jxcMVsj6nDMk7Gvj4
            @Override // java.lang.Runnable
            public final void run() {
                PryonWakeWordDetector.this.lambda$wakeWordDetected$0$PryonWakeWordDetector(str, j, j2, bArr);
            }
        });
    }
}
