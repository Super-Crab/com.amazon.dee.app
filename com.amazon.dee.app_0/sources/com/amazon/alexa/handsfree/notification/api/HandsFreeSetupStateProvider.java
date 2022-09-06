package com.amazon.alexa.handsfree.notification.api;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.notification.notificationmetadata.NotificationMetadata;
/* loaded from: classes8.dex */
public interface HandsFreeSetupStateProvider {
    boolean canEnableWakeWord(@NonNull Context context);

    NotificationMetadata getNotificationMetadata(@NonNull Context context);

    Intent getSetupFlowIntent(@NonNull Context context);

    boolean isHandsFreeSetupComplete(@NonNull Context context);
}
