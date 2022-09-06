package com.amazon.alexa.voice.handsfree.decider.stepcommand;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.notificationmetadata.NotificationMetadata;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.voice.handsfree.decider.StepType;
import com.amazon.alexa.voice.handsfree.decider.setup.HandsFreeIntroActivity;
import com.amazon.alexa.voice.handsfree.metrics.MetricType;
/* loaded from: classes11.dex */
public class AISProfileHandsFreeIntroStepCommand extends StepCommand {
    public AISProfileHandsFreeIntroStepCommand(@NonNull Context context) {
        this(context, StepType.AIS_PROFILE_HANDS_FREE_INTRO, MetricsBuilderProvider.getInstance(context), NotificationMetadata.HANDS_FREE_INTRO, MetricType.HANDS_FREE_INTRO_METADATA_CALLED);
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.stepcommand.StepCommand
    @Nullable
    public Intent getStepIntent() {
        Intent intent = new Intent(getContext(), HandsFreeIntroActivity.class);
        intent.putExtra(HandsFreeIntroActivity.EXTRA_IS_AIS_FLOW, true);
        return intent;
    }

    @VisibleForTesting
    AISProfileHandsFreeIntroStepCommand(@NonNull Context context, @NonNull StepType stepType, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull NotificationMetadata notificationMetadata, @NonNull MetricType metricType) {
        super(context, stepType, metricsBuilderProvider, notificationMetadata, metricType);
    }
}
