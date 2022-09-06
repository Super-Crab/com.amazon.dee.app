package com.amazon.alexa.voice.handsfree.decider.stepcommand;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.notificationmetadata.NotificationMetadata;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.voice.handsfree.decider.StepType;
import com.amazon.alexa.voice.handsfree.decider.setup.LanguageSelectorActivity;
import com.amazon.alexa.voice.handsfree.metrics.MetricType;
/* loaded from: classes11.dex */
public class LanguageSelectorStepCommand extends StepCommand {
    public LanguageSelectorStepCommand(@NonNull Context context) {
        this(context, StepType.LANGUAGE_SELECTOR, MetricsBuilderProvider.getInstance(context), NotificationMetadata.LANGUAGE_SELECTOR, MetricType.LANGUAGE_SELECTOR_NOTIFICATION_METADATA_CALLED);
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.stepcommand.StepCommand
    public Intent getStepIntent() {
        return new Intent(getContext(), LanguageSelectorActivity.class);
    }

    @VisibleForTesting
    LanguageSelectorStepCommand(@NonNull Context context, @NonNull StepType stepType, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull NotificationMetadata notificationMetadata, @NonNull MetricType metricType) {
        super(context, stepType, metricsBuilderProvider, notificationMetadata, metricType);
    }
}
