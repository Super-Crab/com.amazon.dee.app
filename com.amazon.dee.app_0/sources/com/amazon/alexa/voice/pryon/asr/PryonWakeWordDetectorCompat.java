package com.amazon.alexa.voice.pryon.asr;

import android.util.Log;
import com.amazon.alexa.voice.pryon.asr.PryonWakeWordDetector;
import com.amazon.pryon.android.asr.PryonLite5000;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class PryonWakeWordDetectorCompat implements WakeWordDetector {
    public static final int PRYON_UNAVAILABLE_ERROR_CODE = 251;
    private static final String TAG = "PryonWakeWordDetectorCompat";
    private int errorCode;
    private final PryonWakeWordDetector pryonDetector;

    public PryonWakeWordDetectorCompat() {
        this(null, false, false);
    }

    public int getPryonErrorCode() {
        return this.errorCode;
    }

    public PryonLite5000 getPryonLite() {
        PryonWakeWordDetector pryonWakeWordDetector = this.pryonDetector;
        if (pryonWakeWordDetector != null) {
            return pryonWakeWordDetector.getPryonLite();
        }
        Log.i(TAG, "Ignoring call to get PryonLite.");
        return null;
    }

    @Override // com.amazon.alexa.voice.pryon.asr.WakeWordDetector
    public boolean isActive() {
        PryonWakeWordDetector pryonWakeWordDetector = this.pryonDetector;
        if (pryonWakeWordDetector != null) {
            return pryonWakeWordDetector.isActive();
        }
        Log.i(TAG, "Ignoring call to isActive on wake word detector.");
        return false;
    }

    @Override // com.amazon.alexa.voice.pryon.asr.WakeWordDetector
    public void release() {
        PryonWakeWordDetector pryonWakeWordDetector = this.pryonDetector;
        if (pryonWakeWordDetector != null) {
            pryonWakeWordDetector.release();
        } else {
            Log.i(TAG, "Ignoring call to release wake word detector.");
        }
    }

    @Override // com.amazon.alexa.voice.pryon.asr.WakeWordDetector
    public void removePryonLiteCallbacks() {
        PryonWakeWordDetector pryonWakeWordDetector = this.pryonDetector;
        if (pryonWakeWordDetector == null) {
            Log.i(TAG, "Ignoring call to removePryonLiteCallbacks in wake word detector.");
        } else {
            pryonWakeWordDetector.removePryonLiteCallbacks();
        }
    }

    @Override // com.amazon.alexa.voice.pryon.asr.WakeWordDetector
    public void setPryonLiteCallbacks(PryonLite5000.Callbacks callbacks) {
        PryonWakeWordDetector pryonWakeWordDetector = this.pryonDetector;
        if (pryonWakeWordDetector == null) {
            Log.i(TAG, "Ignoring call to setPryonLiteCallbacks in wake word detector.");
        } else {
            pryonWakeWordDetector.setPryonLiteCallbacks(callbacks);
        }
    }

    @Override // com.amazon.alexa.voice.pryon.asr.WakeWordDetector
    public int setSensitivityThreshold(int i) {
        PryonWakeWordDetector pryonWakeWordDetector = this.pryonDetector;
        if (pryonWakeWordDetector == null) {
            Log.i(TAG, "Ignoring call to setSensitivityThreshold in wake word detector.");
            return -1;
        }
        return pryonWakeWordDetector.setSensitivityThreshold(i);
    }

    @Override // com.amazon.alexa.voice.pryon.asr.WakeWordDetector
    public void start() {
        PryonWakeWordDetector pryonWakeWordDetector = this.pryonDetector;
        if (pryonWakeWordDetector != null) {
            pryonWakeWordDetector.start();
        } else {
            Log.i(TAG, "Ignoring call to start wake word detector.");
        }
    }

    @Override // com.amazon.alexa.voice.pryon.asr.WakeWordDetector
    public void stop() {
        PryonWakeWordDetector pryonWakeWordDetector = this.pryonDetector;
        if (pryonWakeWordDetector != null) {
            pryonWakeWordDetector.stop();
        } else {
            Log.i(TAG, "Ignoring call to stop wake word detector.");
        }
    }

    public PryonWakeWordDetectorCompat(PryonLite5000.Config config, boolean z, boolean z2) {
        PryonWakeWordDetector pryonWakeWordDetector;
        try {
            this.errorCode = 0;
            pryonWakeWordDetector = new PryonWakeWordDetector(config, z, z2);
        } catch (PryonWakeWordDetector.InitializeException e) {
            Log.e(TAG, "Pryon initialization failed", e);
            this.errorCode = e.getErrorCode();
            pryonWakeWordDetector = null;
            this.pryonDetector = pryonWakeWordDetector;
        } catch (UnsupportedOperationException unused) {
            GeneratedOutlineSupport1.outline162("Pryon unavailable on platform ", System.getProperty("ro.product.cpu.abi", "unknown"), TAG);
            this.errorCode = 251;
            pryonWakeWordDetector = null;
            this.pryonDetector = pryonWakeWordDetector;
        }
        this.pryonDetector = pryonWakeWordDetector;
    }
}
