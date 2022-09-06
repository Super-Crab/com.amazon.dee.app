package com.amazon.alexa.handsfree.uservoicerecognition.edgesv.enrollment;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.enrollment.unified.UnifiedEnroller;
import com.amazon.alexa.enrollment.unified.api.EnrollmentProvider;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVREnroller;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.callback.EnrollmentCallback;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UserInfo;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UtteranceInfo;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UtteranceTrainingMetadata;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.feedback.UtteranceFeedbackFactory;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.uservoicerecognition.edgesv.cache.EnrollmentStateCache;
import com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener;
import com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener;
import com.amazon.alexa.wakeword.speakerverification.model.SpeakerVerificationModelDownloadScheduler;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public class EdgeSVUVREnroller implements UVREnroller {
    private static final double DEFAULT_QUALITY_SCORE = 0.0d;
    private static final int DEFAULT_UTTERANCE_METADATA_VALUE = 1;
    private static final UtteranceTrainingMetadata DEFAULT_UTTERANCE_TRAINING_METADATA = new UtteranceTrainingMetadata(1, 1);
    private static final String TAG = "EdgeSVUVREnroller";
    private final Context mContext;
    private final EnrollmentStateCache mEnrollmentStateCache;
    private final SpeakerVerificationModelDownloadScheduler mModelDownloadScheduler;
    private final UnifiedEnroller mUnifiedEnroller;
    private final UtteranceFeedbackFactory mUtteranceFeedbackFactory;

    public EdgeSVUVREnroller(@NonNull Context context, @NonNull SpeakerVerificationMetricsListener speakerVerificationMetricsListener, @NonNull WakeWordDetectionMetricsListener wakeWordDetectionMetricsListener, @NonNull EnrollmentStateCache enrollmentStateCache) {
        this(new UnifiedEnroller(context, speakerVerificationMetricsListener, wakeWordDetectionMetricsListener), enrollmentStateCache, new SpeakerVerificationModelDownloadScheduler(), context, new UtteranceFeedbackFactory());
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVREnroller
    public void cancelUserVoiceEnrollment(@NonNull final ResponseCallback responseCallback) {
        this.mUnifiedEnroller.cancelUserVoiceEnrollment(new ResponseCallback() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.edgesv.enrollment.EdgeSVUVREnroller.5
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                Log.d(EdgeSVUVREnroller.TAG, "cancelUserVoiceEnrollment onError");
                responseCallback.onError(callbackErrorMetadata);
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onSuccess() {
                Log.d(EdgeSVUVREnroller.TAG, "cancelUserVoiceEnrollment success");
                responseCallback.onSuccess();
            }
        });
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVREnroller
    public void cancelUtteranceTraining(@NonNull final ResponseCallback responseCallback) {
        this.mUnifiedEnroller.cancelUtteranceTraining(new ResponseCallback() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.edgesv.enrollment.EdgeSVUVREnroller.4
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                Log.d(EdgeSVUVREnroller.TAG, "cancelUtteranceTraining onError");
                responseCallback.onError(callbackErrorMetadata);
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onSuccess() {
                Log.d(EdgeSVUVREnroller.TAG, "cancelUtteranceTraining success");
                responseCallback.onSuccess();
            }
        });
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVREnroller
    public void finishUserVoiceEnrollment(@NonNull final ResultCallback<Double> resultCallback) {
        this.mUnifiedEnroller.finishUserVoiceEnrollment(new ResponseCallback() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.edgesv.enrollment.EdgeSVUVREnroller.3
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                Log.d(EdgeSVUVREnroller.TAG, "finishUserVoiceEnrollment onError");
                EdgeSVUVREnroller.this.mEnrollmentStateCache.setEnrollmentState(false);
                resultCallback.onError(callbackErrorMetadata);
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onSuccess() {
                Log.d(EdgeSVUVREnroller.TAG, "finishUserVoiceEnrollment success");
                EdgeSVUVREnroller.this.mModelDownloadScheduler.scheduleModelDownload(EdgeSVUVREnroller.this.mContext);
                EdgeSVUVREnroller.this.mEnrollmentStateCache.setEnrollmentState(true);
                resultCallback.onResult(Double.valueOf(0.0d));
            }
        });
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVREnroller
    @NonNull
    public List<UtteranceInfo> getUtterances() {
        List<String> utterances = this.mUnifiedEnroller.getUtterances();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < utterances.size(); i++) {
            arrayList.add(new UtteranceInfo(String.valueOf(i), utterances.get(i)));
        }
        return arrayList;
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVREnroller
    public void startUserVoiceEnrollment(@NonNull UserInfo userInfo, @NonNull final ResponseCallback responseCallback) {
        this.mUnifiedEnroller.startUserVoiceEnrollment(new ResponseCallback() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.edgesv.enrollment.EdgeSVUVREnroller.1
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                responseCallback.onError(callbackErrorMetadata);
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onSuccess() {
                responseCallback.onSuccess();
            }
        });
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVREnroller
    public void startUtteranceTraining(@NonNull UtteranceInfo utteranceInfo, @NonNull final EnrollmentCallback enrollmentCallback) {
        enrollmentCallback.onStartRecording();
        this.mUnifiedEnroller.startUtteranceTraining(new EnrollmentProvider.UtteranceTrainingCallback() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.edgesv.enrollment.EdgeSVUVREnroller.2
            @Override // com.amazon.alexa.enrollment.unified.api.EnrollmentProvider.UtteranceTrainingCallback
            public void onAudioCaptured(@NonNull short[] sArr) {
                enrollmentCallback.onFeedback(EdgeSVUVREnroller.this.mUtteranceFeedbackFactory.getPcmDataFeedback(sArr));
            }

            @Override // com.amazon.alexa.enrollment.unified.api.EnrollmentProvider.UtteranceTrainingCallback
            public void onError(@NonNull EnrollmentErrorContract enrollmentErrorContract) {
                Log.d(EdgeSVUVREnroller.TAG, "startUtteranceTraining error");
                enrollmentCallback.onError(enrollmentErrorContract, EdgeSVUVREnroller.DEFAULT_UTTERANCE_TRAINING_METADATA);
                enrollmentCallback.onStopRecording();
            }

            @Override // com.amazon.alexa.enrollment.unified.api.EnrollmentProvider.UtteranceTrainingCallback
            public void onSuccess() {
                Log.d(EdgeSVUVREnroller.TAG, "startUtteranceTraining success");
                enrollmentCallback.onSuccess(EdgeSVUVREnroller.DEFAULT_UTTERANCE_TRAINING_METADATA);
                enrollmentCallback.onStopRecording();
            }
        });
    }

    @VisibleForTesting
    EdgeSVUVREnroller(@NonNull UnifiedEnroller unifiedEnroller, @NonNull EnrollmentStateCache enrollmentStateCache, @NonNull SpeakerVerificationModelDownloadScheduler speakerVerificationModelDownloadScheduler, @NonNull Context context, @NonNull UtteranceFeedbackFactory utteranceFeedbackFactory) {
        this.mUnifiedEnroller = unifiedEnroller;
        this.mEnrollmentStateCache = enrollmentStateCache;
        this.mModelDownloadScheduler = speakerVerificationModelDownloadScheduler;
        this.mContext = context;
        this.mUtteranceFeedbackFactory = utteranceFeedbackFactory;
    }
}
