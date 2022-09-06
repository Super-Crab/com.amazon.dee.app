package com.amazon.alexa.fitness.message.notification;

import android.content.Context;
import android.content.Intent;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.view.notification.FitnessNotificationService;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessNotificationPublisher.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/fitness/message/notification/FitnessNotificationPublisherImpl;", "Lcom/amazon/alexa/fitness/message/notification/FitnessNotificationPublisher;", "context", "Landroid/content/Context;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Landroid/content/Context;Lcom/amazon/alexa/fitness/logs/ILog;)V", "hideNotification", "", "showNotification", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FitnessNotificationPublisherImpl implements FitnessNotificationPublisher {
    private final Context context;
    private final ILog log;

    @Inject
    public FitnessNotificationPublisherImpl(@NotNull Context context, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.context = context;
        this.log = log;
    }

    @Override // com.amazon.alexa.fitness.message.notification.FitnessNotificationPublisher
    public void hideNotification() {
        ILog.DefaultImpls.info$default(this.log, "NotificationPublisher", "stopping FitnessNotificationService", null, 4, null);
        this.context.stopService(new Intent(this.context, FitnessNotificationService.class));
    }

    @Override // com.amazon.alexa.fitness.message.notification.FitnessNotificationPublisher
    public void showNotification() {
        ILog.DefaultImpls.info$default(this.log, "NotificationPublisher", "starting FitnessNotificationService", null, 4, null);
        ContextCompat.startForegroundService(this.context, new Intent(this.context, FitnessNotificationService.class));
    }
}
