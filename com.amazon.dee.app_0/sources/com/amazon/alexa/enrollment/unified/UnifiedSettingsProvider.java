package com.amazon.alexa.enrollment.unified;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.enrollment.unified.api.SettingsProvider;
import com.amazon.alexa.enrollment.unified.edgesv.EdgeSVSettingsProvider;
import com.amazon.alexa.enrollment.unified.speakerid.SpeakerIDSettingsProvider;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener;
/* loaded from: classes7.dex */
public class UnifiedSettingsProvider implements SettingsProvider {
    private static final String TAG = "UnifiedSettingsProvider";
    private final EdgeSVSettingsProvider mEdgeSVSettingsProvider;
    private final EnrollmentTypeResolver mEnrollmentTypeResolver;
    private final SpeakerIDSettingsProvider mSpeakerIDSettingsProvider;

    public UnifiedSettingsProvider(@NonNull Context context, @NonNull SpeakerVerificationMetricsListener speakerVerificationMetricsListener) {
        this(new SpeakerIDSettingsProvider(context), new EdgeSVSettingsProvider(context, speakerVerificationMetricsListener), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).enrollmentTypeResolverLazy().mo358get());
    }

    public void isSpeakerIDEnrolled(@NonNull ResultCallback<Boolean> resultCallback) {
        this.mSpeakerIDSettingsProvider.isUVREnrolled(resultCallback);
    }

    @Override // com.amazon.alexa.enrollment.unified.api.SettingsProvider
    public boolean isUVREnrolled() {
        if (this.mEnrollmentTypeResolver.getEnrollmentType() == EnrollmentType._1PSV_DECOUPLED) {
            return this.mEdgeSVSettingsProvider.isUVREnrolled();
        }
        if (this.mEdgeSVSettingsProvider.isUVREnrolled()) {
            return this.mSpeakerIDSettingsProvider.isUVREnrolled();
        }
        return false;
    }

    @Override // com.amazon.alexa.enrollment.unified.api.SettingsProvider
    public void removeUVRModel(@NonNull ResponseCallback responseCallback) {
        this.mEdgeSVSettingsProvider.removeUVRModel(responseCallback);
    }

    @Override // com.amazon.alexa.enrollment.unified.api.SettingsProvider
    public void isUVREnrolled(@NonNull final ResultCallback<Boolean> resultCallback) {
        this.mEdgeSVSettingsProvider.isUVREnrolled(new ResultCallback<Boolean>() { // from class: com.amazon.alexa.enrollment.unified.UnifiedSettingsProvider.1
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                resultCallback.onError(callbackErrorMetadata);
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
            public void onResult(@NonNull Boolean bool) {
                if (UnifiedSettingsProvider.this.mEnrollmentTypeResolver.getEnrollmentType() != EnrollmentType._1PSV_DECOUPLED && bool.booleanValue()) {
                    UnifiedSettingsProvider.this.mSpeakerIDSettingsProvider.isUVREnrolled(resultCallback);
                } else {
                    resultCallback.onResult(bool);
                }
            }
        });
    }

    @VisibleForTesting
    UnifiedSettingsProvider(@NonNull SpeakerIDSettingsProvider speakerIDSettingsProvider, @NonNull EdgeSVSettingsProvider edgeSVSettingsProvider, @NonNull EnrollmentTypeResolver enrollmentTypeResolver) {
        this.mSpeakerIDSettingsProvider = speakerIDSettingsProvider;
        this.mEdgeSVSettingsProvider = edgeSVSettingsProvider;
        this.mEnrollmentTypeResolver = enrollmentTypeResolver;
    }
}
