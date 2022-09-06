package com.amazon.alexa.voice.handsfree.decider.stepcommand;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.notificationmetadata.NotificationMetadata;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.voice.handsfree.decider.StepType;
import com.amazon.alexa.voice.handsfree.decider.setup.HandsFreeIntroActivity;
import com.amazon.alexa.voice.handsfree.metrics.MetricType;
/* loaded from: classes11.dex */
public class HandsFreeIntroStepCommand extends StepCommand {
    public HandsFreeIntroStepCommand(@NonNull Context context) {
        this(context, StepType.HANDS_FREE_INTRO, MetricsBuilderProvider.getInstance(context), NotificationMetadata.HANDS_FREE_INTRO, MetricType.HANDS_FREE_INTRO_METADATA_CALLED);
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.stepcommand.StepCommand
    public Intent getStepIntent() {
        return new Intent(getContext(), HandsFreeIntroActivity.class);
    }

    @VisibleForTesting
    HandsFreeIntroStepCommand(@NonNull Context context, @NonNull StepType stepType, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull NotificationMetadata notificationMetadata, @NonNull MetricType metricType) {
        super(context, stepType, metricsBuilderProvider, notificationMetadata, metricType);
    }
}
