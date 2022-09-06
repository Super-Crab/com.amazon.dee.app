package com.amazon.alexa.handsfree.uservoicerecognition.quebec.enrollment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.callback.EnrollmentCallback;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UtteranceTrainingMetadata;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.feedback.UtteranceFeedbackFactory;
import com.amazon.alexa.handsfree.quebec.QuebecAPIConstants;
import com.quicinc.voice.activation.IUVRUtteranceTrainingCallback;
/* loaded from: classes8.dex */
public class QuebecUtteranceTrainingCallback extends IUVRUtteranceTrainingCallback.Stub {
    private static final int MAX_VOLUME = 100;
    private static final int MIN_VOLUME = 0;
    private static final String TAG = QuebecUtteranceTrainingCallback.class.getSimpleName();
    private EnrollmentCallback mEnrollmentCallback;
    private final UtteranceFeedbackFactory mUtteranceFeedbackFactory;

    /* JADX INFO: Access modifiers changed from: package-private */
    public QuebecUtteranceTrainingCallback(@NonNull EnrollmentCallback enrollmentCallback) {
        this(enrollmentCallback, new UtteranceFeedbackFactory());
    }

    @NonNull
    private UtteranceTrainingMetadata getUtteranceTrainingMetadata(@NonNull Bundle bundle) {
        return new UtteranceTrainingMetadata(bundle.getInt(QuebecAPIConstants.UVR_UTTERANCE_TRAINING_DATA_SNR), bundle.getInt(QuebecAPIConstants.UVR_UTTERANCE_TRAINING_DATA_CONFIDENCE));
    }

    @Override // com.quicinc.voice.activation.IUVRUtteranceTrainingCallback
    public void onError(@NonNull Bundle bundle) {
        this.mEnrollmentCallback.onError(EnrollError.from(bundle.getInt(QuebecAPIConstants.UVR_UTTERANCE_TRAINING_DATA_ERROR_CODE)), getUtteranceTrainingMetadata(bundle));
    }

    @Override // com.quicinc.voice.activation.IUVRUtteranceTrainingCallback
    public void onFeedback(@NonNull Bundle bundle) {
        this.mEnrollmentCallback.onFeedback(this.mUtteranceFeedbackFactory.getVolumeFeedback(bundle.getInt(QuebecAPIConstants.UVR_UTTERANCE_TRAINING_DATA_ENERGY), 0, 100));
    }

    @Override // com.quicinc.voice.activation.IUVRUtteranceTrainingCallback
    public void onStartProcessing(@NonNull Bundle bundle) {
        this.mEnrollmentCallback.onStartProcessing();
    }

    @Override // com.quicinc.voice.activation.IUVRUtteranceTrainingCallback
    public void onStartRecording(@NonNull Bundle bundle) {
        this.mEnrollmentCallback.onStartRecording();
    }

    @Override // com.quicinc.voice.activation.IUVRUtteranceTrainingCallback
    public void onStopProcessing(@NonNull Bundle bundle) {
        this.mEnrollmentCallback.onStopProcessing();
    }

    @Override // com.quicinc.voice.activation.IUVRUtteranceTrainingCallback
    public void onStopRecording(@NonNull Bundle bundle) {
        this.mEnrollmentCallback.onStopRecording();
    }

    @Override // com.quicinc.voice.activation.IUVRUtteranceTrainingCallback
    public void onSuccess(@NonNull Bundle bundle) {
        this.mEnrollmentCallback.onSuccess(getUtteranceTrainingMetadata(bundle));
    }

    @VisibleForTesting
    QuebecUtteranceTrainingCallback(@NonNull EnrollmentCallback enrollmentCallback, @NonNull UtteranceFeedbackFactory utteranceFeedbackFactory) {
        this.mEnrollmentCallback = enrollmentCallback;
        this.mUtteranceFeedbackFactory = utteranceFeedbackFactory;
    }
}
