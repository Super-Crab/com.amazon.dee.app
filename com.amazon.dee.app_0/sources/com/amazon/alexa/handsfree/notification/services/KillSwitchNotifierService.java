package com.amazon.alexa.handsfree.notification.services;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.configurations.scheduler.NotificationScheduler;
import com.amazon.alexa.handsfree.notification.configurations.timebased.TimeIntervalConfigHandler;
import com.amazon.alexa.handsfree.notification.notifiers.Notifier;
import com.amazon.alexa.handsfree.notification.notifiers.NotifierPriorityResolver;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.utils.AlexaAppSignInContract;
import dagger.Lazy;
/* loaded from: classes8.dex */
public class KillSwitchNotifierService extends NotifierService {
    private static final int JOB_ID = 30003;

    public static void enqueueWork(@NonNull Context context, @NonNull Intent intent) {
        JobIntentService.enqueueWork(context, KillSwitchNotifierService.class, (int) JOB_ID, intent);
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(@NonNull Intent intent) {
        onHandleWork(((FalcoProtocolComponent) AhfComponentsProvider.getComponent(this, FalcoProtocolComponent.class)).alexaAppSignInContractLazy(), new NotifierPriorityResolver(this, intent).getKillSwitchNotifier(), new NotificationScheduler(NotificationType.KILL_SWITCH, this, new TimeIntervalConfigHandler(this)));
    }

    void onHandleWork(@NonNull Lazy<AlexaAppSignInContract> lazy, @NonNull Notifier notifier, @NonNull NotificationScheduler notificationScheduler) {
        lazy.mo358get().setup(this, true);
        notify(notifier);
        schedule(notificationScheduler);
        lazy.mo358get().teardown(this);
    }
}
