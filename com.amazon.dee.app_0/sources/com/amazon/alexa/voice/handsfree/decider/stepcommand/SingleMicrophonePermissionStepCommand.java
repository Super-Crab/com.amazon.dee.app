package com.amazon.alexa.voice.handsfree.decider.stepcommand;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.notificationmetadata.NotificationMetadata;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.voice.handsfree.decider.StepType;
import com.amazon.alexa.voice.handsfree.decider.setup.SinglePermissionsActivity;
import com.amazon.alexa.voice.handsfree.metrics.MetricType;
/* loaded from: classes11.dex */
public class SingleMicrophonePermissionStepCommand extends StepCommand {
    public SingleMicrophonePermissionStepCommand(@NonNull Context context) {
        this(context, StepType.ALEXA_APP_AUDIO_PERMISSION, MetricsBuilderProvider.getInstance(context), NotificationMetadata.RECORD_AUDIO_PERMISSIONS, MetricType.ALEXA_APP_AUDIO_PERMISSION_METADATA_CALLED);
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.stepcommand.StepCommand
    public Intent getStepIntent() {
        return new Intent(getContext(), SinglePermissionsActivity.class);
    }

    @VisibleForTesting
    SingleMicrophonePermissionStepCommand(@NonNull Context context, @NonNull StepType stepType, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull NotificationMetadata notificationMetadata, @NonNull MetricType metricType) {
        super(context, stepType, metricsBuilderProvider, notificationMetadata, metricType);
    }
}
