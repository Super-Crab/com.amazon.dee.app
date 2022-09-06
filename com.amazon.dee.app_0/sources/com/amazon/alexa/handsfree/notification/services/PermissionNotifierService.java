package com.amazon.alexa.handsfree.notification.services;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.JobIntentService;
import com.amazon.alexa.handsfree.notification.notifiers.Notifier;
import com.amazon.alexa.handsfree.notification.notifiers.NotifierPriorityResolver;
/* loaded from: classes8.dex */
public class PermissionNotifierService extends NotifierService {
    private static final int JOB_ID = 30007;

    public static void enqueueWork(@NonNull Context context, @NonNull Intent intent) {
        JobIntentService.enqueueWork(context, PermissionNotifierService.class, (int) JOB_ID, intent);
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(@NonNull Intent intent) {
        onHandleWork(new NotifierPriorityResolver(this, intent));
    }

    @VisibleForTesting
    void onHandleWork(@NonNull NotifierPriorityResolver notifierPriorityResolver) {
        Notifier permissionNotifier = notifierPriorityResolver.getPermissionNotifier();
        if (permissionNotifier != null) {
            permissionNotifier.updateNotificationCount();
            permissionNotifier.showNotification();
        }
    }
}
