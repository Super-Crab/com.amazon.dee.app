package com.amazon.alexa.handsfree.notification.services;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.configurations.scheduler.NotificationScheduler;
import com.amazon.alexa.handsfree.notification.configurations.timebased.TimeIntervalConfigHandler;
import com.amazon.alexa.handsfree.notification.notifiers.NotifierPriorityResolver;
/* loaded from: classes8.dex */
public class EnableHandsFreeNotifierService extends NotifierService {
    private static final int JOB_ID = 30008;
    private static final String TAG = EnableHandsFreeService.class.getSimpleName();

    public static void enqueueWork(@NonNull Context context, @NonNull Intent intent) {
        JobIntentService.enqueueWork(context, EnableHandsFreeNotifierService.class, (int) JOB_ID, intent);
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(@NonNull Intent intent) {
        notify(new NotifierPriorityResolver(this, new Intent()).getEnableHandsFreeNotifier());
        schedule(new NotificationScheduler(NotificationType.ENABLE_HANDS_FREE, this, new TimeIntervalConfigHandler(this)));
    }
}
