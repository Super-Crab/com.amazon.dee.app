package com.amazon.alexa.voice.handsfree.notifications.providers;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.api.HandsFreeSetupStateProvider;
import com.amazon.alexa.handsfree.notification.notificationmetadata.NotificationMetadata;
import com.amazon.alexa.voice.handsfree.decider.HandsFreeSetupDeciderProvider;
import com.amazon.alexa.voice.handsfree.decider.SetupFlowExecutionService;
import com.amazon.alexa.voice.handsfree.decider.stepcommand.StepCommand;
/* loaded from: classes11.dex */
public class NotificationHandsFreeSetupStateProvider implements HandsFreeSetupStateProvider {
    private final HandsFreeSetupDeciderProvider mHandsFreeSetupDeciderProvider;
    private final SetupFlowExecutionService.ServiceHelper mServiceHelper;

    public NotificationHandsFreeSetupStateProvider() {
        this(HandsFreeSetupDeciderProvider.getInstance(), SetupFlowExecutionService.ServiceHelper.getInstance());
    }

    @Override // com.amazon.alexa.handsfree.notification.api.HandsFreeSetupStateProvider
    public boolean canEnableWakeWord(@NonNull Context context) {
        return this.mHandsFreeSetupDeciderProvider.getHandsFreeSetupDecider(context).canEnableWakeWord();
    }

    @Override // com.amazon.alexa.handsfree.notification.api.HandsFreeSetupStateProvider
    @Nullable
    public NotificationMetadata getNotificationMetadata(@NonNull Context context) {
        StepCommand nextSetupStepCommand = this.mHandsFreeSetupDeciderProvider.getHandsFreeSetupDecider(context).getNextSetupStepCommand();
        if (nextSetupStepCommand != null) {
            return nextSetupStepCommand.getNotificationMetadata();
        }
        return null;
    }

    @Override // com.amazon.alexa.handsfree.notification.api.HandsFreeSetupStateProvider
    @NonNull
    public Intent getSetupFlowIntent(@NonNull Context context) {
        return this.mServiceHelper.getSetupFlowExecutionServiceIntent(context);
    }

    @Override // com.amazon.alexa.handsfree.notification.api.HandsFreeSetupStateProvider
    public boolean isHandsFreeSetupComplete(@NonNull Context context) {
        return !this.mHandsFreeSetupDeciderProvider.getHandsFreeSetupDecider(context).isTherePendingStepCommand();
    }

    @VisibleForTesting
    NotificationHandsFreeSetupStateProvider(@NonNull HandsFreeSetupDeciderProvider handsFreeSetupDeciderProvider, @NonNull SetupFlowExecutionService.ServiceHelper serviceHelper) {
        this.mHandsFreeSetupDeciderProvider = handsFreeSetupDeciderProvider;
        this.mServiceHelper = serviceHelper;
    }
}
