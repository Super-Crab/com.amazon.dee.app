package com.amazon.alexa.wakeword.speakerverification.pryon;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.wakeword.speakerverification.enrollment.EnrollmentUtterance;
import com.amazon.alexa.wakeword.speakerverification.metrics.PryonMetricsListener;
import com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener;
import com.amazon.pryon.android.asr.PryonLite5000;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class PryonEnroller {
    @VisibleForTesting
    static final int PRYON_INITIALIZED = 1;
    @VisibleForTesting
    static final int PRYON_NOT_INITIALIZED = 0;
    @VisibleForTesting
    static final int PRYON_SUCCESS = 0;
    private static final String TAG = "PryonEnroller";
    private final PryonLite5000 mEngine;
    private final PryonMetricsListener mMetricsListener;
    private byte[] mProfileId;

    public PryonEnroller(@NonNull PryonLite5000.Callbacks callbacks, @NonNull SpeakerVerificationMetricsListener speakerVerificationMetricsListener) {
        this(new PryonLite5000(callbacks), speakerVerificationMetricsListener);
    }

    private boolean checkEngineResult(@NonNull PryonOperation pryonOperation, int i) {
        if (i == 0) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Pryon operation ");
            outline107.append(pryonOperation.name());
            outline107.append(" succeeded");
            Log.e(str, outline107.toString());
            this.mMetricsListener.onPryonOperationSuccess(pryonOperation);
            return true;
        }
        String str2 = TAG;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Pryon operation ");
        outline1072.append(pryonOperation.name());
        outline1072.append(" failed with code ");
        outline1072.append(i);
        Log.e(str2, outline1072.toString());
        this.mMetricsListener.onPryonOperationFailure(pryonOperation, i);
        return false;
    }

    private void destroySpeakerVerificationEngine() {
        checkEngineResult(PryonOperation.ENROLLMENT_DISABLE, this.mEngine.speakerVerificationEnrollmentDisable(this.mProfileId));
        checkEngineResult(PryonOperation.DESTROY_ENROLLMENT_SESSION, this.mEngine.speakerVerificationEnrollmentDestroySession(this.mProfileId));
        checkEngineResult(PryonOperation.DESTROY, this.mEngine.destroy());
    }

    public boolean initialize(@NonNull byte[] bArr, @NonNull PryonLite5000.Config config) {
        boolean z = false;
        if (!isPryonAvailable()) {
            return false;
        }
        if (this.mEngine.isInitialized() == 1) {
            return true;
        }
        this.mProfileId = bArr;
        if (checkEngineResult(PryonOperation.INITIALIZE, this.mEngine.initialize(config)) && checkEngineResult(PryonOperation.CREATE_ENROLLMENT_SESSION, this.mEngine.speakerVerificationEnrollmentCreateSession(this.mProfileId)) && checkEngineResult(PryonOperation.ENROLLMENT_ENABLE, this.mEngine.speakerVerificationEnrollmentEnable(this.mProfileId))) {
            z = true;
        }
        if (!z) {
            destroySpeakerVerificationEngine();
        }
        return z;
    }

    @VisibleForTesting
    boolean isPryonAvailable() {
        return PryonLite5000.isAvailable();
    }

    public boolean pushExample(@NonNull EnrollmentUtterance enrollmentUtterance) {
        return checkEngineResult(PryonOperation.PUSH_EXAMPLE, this.mEngine.speakerVerificationEnrollmentPushExample(enrollmentUtterance.getWakeWord(), enrollmentUtterance.getStartIndex(), enrollmentUtterance.getEndIndex(), enrollmentUtterance.getAudio(), enrollmentUtterance.getMetadata()));
    }

    public void release() {
        if (this.mEngine.isInitialized() == 1) {
            destroySpeakerVerificationEngine();
        }
        this.mProfileId = null;
    }

    @VisibleForTesting
    PryonEnroller(@NonNull PryonLite5000 pryonLite5000, @NonNull SpeakerVerificationMetricsListener speakerVerificationMetricsListener) {
        this.mEngine = pryonLite5000;
        this.mMetricsListener = speakerVerificationMetricsListener;
    }
}
