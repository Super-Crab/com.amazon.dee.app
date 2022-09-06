package com.amazon.alexa.handsfree.uservoicerecognition.metro.enrollment;

import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.callback.EnrollmentCallback;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UtteranceTrainingMetadata;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.feedback.UtteranceFeedbackFactory;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.magiear.handsfree.util.IEnrollmentCallback;
import com.magiear.handsfree.util.ParamDefinition;
/* loaded from: classes8.dex */
public class EnrollCallback extends IEnrollmentCallback.Stub {
    private static final int MAX_VOLUME = 100;
    private static final int MIN_VOLUME = 1;
    private static final String TAG = EnrollCallback.class.getSimpleName();
    private EnrollmentCallback mEnrollmentCallback;
    private final UtteranceFeedbackFactory mUtteranceFeedbackFactory;

    /* JADX INFO: Access modifiers changed from: package-private */
    public EnrollCallback(@NonNull EnrollmentCallback enrollmentCallback) {
        this(enrollmentCallback, new UtteranceFeedbackFactory());
    }

    @NonNull
    private UtteranceTrainingMetadata getUtteranceTrainingMetadata(@NonNull Bundle bundle) {
        return new UtteranceTrainingMetadata(bundle.getInt(ParamDefinition.KEY_ENROLL_RESULT_SNR), bundle.getInt(ParamDefinition.KEY_ENROLL_RESULT_CONFIDENCE));
    }

    @Override // com.magiear.handsfree.util.IEnrollmentCallback
    public void onFeedback(@NonNull Bundle bundle) throws RemoteException {
        this.mEnrollmentCallback.onFeedback(this.mUtteranceFeedbackFactory.getVolumeFeedback(bundle.getInt(ParamDefinition.KEY_ENROLL_FEEDBACK_INTENSITY), 1, 100));
    }

    @Override // com.magiear.handsfree.util.IEnrollmentCallback
    public void onResult(@NonNull Bundle bundle) throws RemoteException {
        String string = bundle.getString(ParamDefinition.KEY_ENROLL_RESULT);
        if ("success".equals(string)) {
            this.mEnrollmentCallback.onSuccess(getUtteranceTrainingMetadata(bundle));
        } else if ("fail".equals(string)) {
            this.mEnrollmentCallback.onError(EnrollError.from(bundle.getInt(ParamDefinition.KEY_ENROLL_ERROR_CODE)), getUtteranceTrainingMetadata(bundle));
        } else {
            Log.e(TAG, "onResult: error enrollment result was return.");
        }
    }

    @Override // com.magiear.handsfree.util.IEnrollmentCallback
    public void onStartProcessing() throws RemoteException {
        this.mEnrollmentCallback.onStartProcessing();
    }

    @Override // com.magiear.handsfree.util.IEnrollmentCallback
    public void onStartRecording() throws RemoteException {
        this.mEnrollmentCallback.onStartRecording();
    }

    @Override // com.magiear.handsfree.util.IEnrollmentCallback
    public void onStopProcessing() throws RemoteException {
        this.mEnrollmentCallback.onStopProcessing();
    }

    @Override // com.magiear.handsfree.util.IEnrollmentCallback
    public void onStopRecording() throws RemoteException {
        this.mEnrollmentCallback.onStopRecording();
    }

    @VisibleForTesting
    EnrollCallback(@NonNull EnrollmentCallback enrollmentCallback, @NonNull UtteranceFeedbackFactory utteranceFeedbackFactory) {
        this.mEnrollmentCallback = enrollmentCallback;
        this.mUtteranceFeedbackFactory = utteranceFeedbackFactory;
    }
}
