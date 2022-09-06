package com.amazon.alexa.wakeword.speakerverification;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.wakeword.speakerverification.enrollment.EnrollmentUtterance;
import com.amazon.alexa.wakeword.speakerverification.metrics.PryonMetricsListener;
import com.amazon.pryon.android.asr.PryonLite5000;
import java.util.Arrays;
/* loaded from: classes11.dex */
public class SpeakerVerificationCallbacksWrapper implements PryonLite5000.Callbacks {
    private static final String TAG = "SpeakerVerificationCallbacksWrapper";
    private final PryonMetricsListener mMetricsListener;
    private final SpeakerVerificationEngineCallbacks mSpeakerVerificationEngineCallbacks;

    public SpeakerVerificationCallbacksWrapper(@NonNull SpeakerVerificationEngineCallbacks speakerVerificationEngineCallbacks, @NonNull PryonMetricsListener pryonMetricsListener) {
        this.mSpeakerVerificationEngineCallbacks = speakerVerificationEngineCallbacks;
        this.mMetricsListener = pryonMetricsListener;
    }

    @Override // com.amazon.pryon.android.asr.PryonLite5000.Callbacks
    public void errorEvent(int i) {
        String str = TAG;
        Log.e(str, "Error emitted by speaker verification engine, error = " + i);
    }

    @Override // com.amazon.pryon.android.asr.PryonLite5000.Callbacks
    public void speakerVerificationClassificationEvent(@NonNull byte[] bArr, int i, @NonNull byte[] bArr2, float f, float f2, float f3, int i2) {
    }

    @Override // com.amazon.pryon.android.asr.PryonLite5000.Callbacks
    public void speakerVerificationEnrollmentEvent(@NonNull byte[] bArr, int i, @NonNull byte[] bArr2, @NonNull byte[] bArr3) {
        if (i == 1) {
            Log.d(TAG, "speakerVerificationEnrollmentEvent: PRYON_LITE_SPEAKER_VERIFICATION_EXAMPLE_REJECTED");
            this.mSpeakerVerificationEngineCallbacks.onExampleRejected();
            this.mMetricsListener.onExampleRejected();
        } else if (i == 2) {
            Log.d(TAG, "speakerVerificationEnrollmentEvent: PRYON_LITE_SPEAKER_VERIFICATION_PROFILE_GENERATED");
            this.mSpeakerVerificationEngineCallbacks.onProfileGenerated(Arrays.copyOf(bArr2, bArr2.length));
        } else {
            String str = TAG;
            Log.d(str, "speakerVerificationEnrollmentEvent got notification: " + i);
        }
    }

    @Override // com.amazon.pryon.android.asr.PryonLite5000.Callbacks
    public void speakerVerificationWakewordExampleEvent(@NonNull String str, int i, int i2, @NonNull short[] sArr, @NonNull byte[] bArr) {
        Log.d(TAG, "speakerVerificationWakewordExampleEvent");
        this.mSpeakerVerificationEngineCallbacks.onExampleAccepted(new EnrollmentUtterance(str, i, i2, Arrays.copyOf(sArr, sArr.length), Arrays.copyOf(bArr, bArr.length)));
        this.mMetricsListener.onExampleAccepted();
    }

    @Override // com.amazon.pryon.android.asr.PryonLite5000.Callbacks
    public void vadStateChanged(int i) {
    }

    @Override // com.amazon.pryon.android.asr.PryonLite5000.Callbacks
    public void wakeWordDetected(@NonNull String str, long j, long j2, @NonNull byte[] bArr) {
    }
}
