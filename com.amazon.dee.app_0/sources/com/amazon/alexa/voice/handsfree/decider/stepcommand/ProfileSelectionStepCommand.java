package com.amazon.alexa.voice.handsfree.decider.stepcommand;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.notificationmetadata.NotificationMetadata;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.voice.handsfree.decider.StepType;
import com.amazon.alexa.voice.handsfree.metrics.MetricType;
/* loaded from: classes11.dex */
public class ProfileSelectionStepCommand extends StepCommand {
    @VisibleForTesting
    static final String ALEXA_APP_NAME = "com.amazon.dee.app";

    public ProfileSelectionStepCommand(@NonNull Context context) {
        this(context, StepType.PROFILE_SELECTION, MetricsBuilderProvider.getInstance(context), NotificationMetadata.PROFILE_SELECTION, MetricType.PROFILE_SELECTION_NOTIFICATION_METADATA_CALLED);
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.stepcommand.StepCommand
    @Nullable
    public Intent getStepIntent() {
        Intent launchIntentForPackage = getContext().getPackageManager().getLaunchIntentForPackage("com.amazon.dee.app");
        launchIntentForPackage.setAction("android.intent.action.VIEW");
        launchIntentForPackage.setData(Uri.parse("v2/profile-oobe/profile-oobe-start"));
        return launchIntentForPackage;
    }

    @VisibleForTesting
    ProfileSelectionStepCommand(@NonNull Context context, @NonNull StepType stepType, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull NotificationMetadata notificationMetadata, @NonNull MetricType metricType) {
        super(context, stepType, metricsBuilderProvider, notificationMetadata, metricType);
    }
}
