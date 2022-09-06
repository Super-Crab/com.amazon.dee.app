package com.amazon.alexa.wakeword.speakerverification.pryon;

import androidx.annotation.Nullable;
import com.amazon.pryon.android.asr.PryonLite5000;
/* loaded from: classes11.dex */
public final class SpeakerVerificationConfigProvider {
    private static final int DEFAULT_DETECTION_SENSITIVITY = 500;
    private static final int MAX_PROFILE_ID_SIZE = 64;
    private static final int PRYON_ENROLLMENT_DEFAULT_SNR = 0;

    private SpeakerVerificationConfigProvider() {
    }

    public static PryonLite5000.Config createPryonConfig(@Nullable byte[] bArr, @Nullable byte[] bArr2, int i) {
        return createPryonConfig(bArr, bArr2, i, 0);
    }

    public static PryonLite5000.Config createPryonConfig(@Nullable byte[] bArr, @Nullable byte[] bArr2, int i, int i2) {
        PryonLite5000.Config config = new PryonLite5000.Config();
        config.useVad = false;
        config.wakewordModel = bArr;
        config.detectThreshold = 500;
        config.lowLatency = false;
        config.fingerprintList = null;
        config.speakerVerificationModel = bArr2;
        config.numEnrollmentExamples = i;
        config.minEnrollmentSnr = i2;
        config.maxLoadableProfiles = 1;
        config.maxProfileIdSize = 64;
        return config;
    }
}
