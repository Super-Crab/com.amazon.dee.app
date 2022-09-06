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
public class DoubleMicrophonePermissionsStepCommand extends StepCommand {
    public DoubleMicrophonePermissionsStepCommand(@NonNull Context context) {
        this(context, StepType.HANDS_FREE_PERMISSIONS, MetricsBuilderProvider.getInstance(context), NotificationMetadata.RECORD_AUDIO_PERMISSIONS, MetricType.HANDS_FREE_PERMISSIONS_NOTIFICATION_METADATA_CALLED);
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.stepcommand.StepCommand
    public Intent getStepIntent() {
        return new Intent(getContext(), SinglePermissionsActivity.class);
    }

    @VisibleForTesting
    DoubleMicrophonePermissionsStepCommand(@NonNull Context context, @NonNull StepType stepType, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull NotificationMetadata notificationMetadata, @NonNull MetricType metricType) {
        super(context, stepType, metricsBuilderProvider, notificationMetadata, metricType);
    }
}
