package com.amazon.alexa.voice.handsfree.decider.stepcommand;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.notification.notificationmetadata.NotificationMetadata;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.LockScreenSettingActivity;
import com.amazon.alexa.voice.handsfree.decider.StepType;
import com.amazon.alexa.voice.handsfree.metrics.MetricType;
/* loaded from: classes11.dex */
public class LockScreenSettingsStepCommand extends StepCommand {
    public LockScreenSettingsStepCommand(@NonNull Context context) {
        this(context, StepType.LOCK_SCREEN_SETTING, MetricsBuilderProvider.getInstance(context), NotificationMetadata.LOCK_SCREEN_SETTING, MetricType.LOCK_SCREEN_SETTING_NOTIFICATION_METADATA_CALLED);
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.stepcommand.StepCommand
    @Nullable
    public Intent getStepIntent() {
        Intent intent = new Intent(getContext(), LockScreenSettingActivity.class);
        intent.setAction("android.intent.action.VIEW");
        return intent;
    }

    LockScreenSettingsStepCommand(@NonNull Context context, @NonNull StepType stepType, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull NotificationMetadata notificationMetadata, @NonNull MetricType metricType) {
        super(context, stepType, metricsBuilderProvider, notificationMetadata, metricType);
    }
}
