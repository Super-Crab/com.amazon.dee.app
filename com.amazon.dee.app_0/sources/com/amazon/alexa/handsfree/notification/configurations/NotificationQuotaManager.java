package com.amazon.alexa.handsfree.notification.configurations;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.NotificationOccurrenceCounter;
import com.amazon.alexa.handsfree.notification.NotificationType;
import java.util.List;
import javax.inject.Inject;
/* loaded from: classes8.dex */
public class NotificationQuotaManager {
    private final Context mContext;
    private final NotificationOccurrenceCounter mNotificationOccurrenceCounter;
    private final QuotaConstraintsHandler mQuotaConstraintsHandler;

    /* loaded from: classes8.dex */
    public interface QuotaConstraintsHandler {
        List<Long> getConfigTimeIntervals(@NonNull NotificationType notificationType);

        int getTotalNotifications(@NonNull NotificationType notificationType);
    }

    public NotificationQuotaManager(@NonNull Context context, @NonNull QuotaConstraintsHandler quotaConstraintsHandler) {
        this(context, NotificationOccurrenceCounter.getInstance(), quotaConstraintsHandler);
    }

    public boolean hasQuotaAvailable(@NonNull NotificationType notificationType) {
        return this.mNotificationOccurrenceCounter.getNotificationCount(this.mContext, notificationType) < this.mQuotaConstraintsHandler.getTotalNotifications(notificationType);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    @VisibleForTesting
    public NotificationQuotaManager(@NonNull Context context, @NonNull NotificationOccurrenceCounter notificationOccurrenceCounter, @NonNull QuotaConstraintsHandler quotaConstraintsHandler) {
        this.mContext = context;
        this.mNotificationOccurrenceCounter = notificationOccurrenceCounter;
        this.mQuotaConstraintsHandler = quotaConstraintsHandler;
    }
}
