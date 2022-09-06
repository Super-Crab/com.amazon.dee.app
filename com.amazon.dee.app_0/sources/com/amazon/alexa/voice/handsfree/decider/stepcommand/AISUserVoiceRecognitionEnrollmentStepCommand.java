package com.amazon.alexa.voice.handsfree.decider.stepcommand;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.notificationmetadata.NotificationMetadata;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.EnrollmentActivity;
import com.amazon.alexa.voice.handsfree.decider.StepType;
import com.amazon.alexa.voice.handsfree.metrics.MetricType;
/* loaded from: classes11.dex */
public class AISUserVoiceRecognitionEnrollmentStepCommand extends StepCommand {
    public AISUserVoiceRecognitionEnrollmentStepCommand(@NonNull Context context) {
        this(context, StepType.AIS_USER_VOICE_RECOGNITION_ENROLLMENT, MetricsBuilderProvider.getInstance(context), NotificationMetadata.USER_VERIFICATION_ENROLLMENT, MetricType.USER_VOICE_RECOGNITION_ENROLLMENT_NOTIFICATION_METADATA_CALLED);
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.stepcommand.StepCommand
    public Intent getStepIntent() {
        Intent intent = new Intent(getContext(), EnrollmentActivity.class);
        intent.putExtra(EnrollmentActivity.EXTRA_SUPPRESS_UVR_ACTIVATION, true);
        return intent;
    }

    @VisibleForTesting
    AISUserVoiceRecognitionEnrollmentStepCommand(@NonNull Context context, @NonNull StepType stepType, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull NotificationMetadata notificationMetadata, @NonNull MetricType metricType) {
        super(context, stepType, metricsBuilderProvider, notificationMetadata, metricType);
    }
}
