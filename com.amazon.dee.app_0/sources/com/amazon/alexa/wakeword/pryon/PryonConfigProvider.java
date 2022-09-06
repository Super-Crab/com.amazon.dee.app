package com.amazon.alexa.wakeword.pryon;

import androidx.annotation.VisibleForTesting;
import com.amazon.pryon.android.asr.PryonLite5000;
/* loaded from: classes11.dex */
public final class PryonConfigProvider {
    @VisibleForTesting
    static final int DEFAULT_DETECTION_SENSITIVITY = 500;

    private PryonConfigProvider() {
        throw new UnsupportedOperationException("don't instantiate me!");
    }

    public static PryonLite5000.Config createPryonConfig() {
        PryonLite5000.Config config = new PryonLite5000.Config();
        config.useVad = false;
        config.detectThreshold = 500;
        config.lowLatency = false;
        config.fingerprintList = null;
        config.speakerVerificationModel = null;
        config.numEnrollmentExamples = 0;
        config.minEnrollmentSnr = 0;
        config.maxLoadableProfiles = 0;
        config.maxProfileIdSize = 0;
        return config;
    }
}
