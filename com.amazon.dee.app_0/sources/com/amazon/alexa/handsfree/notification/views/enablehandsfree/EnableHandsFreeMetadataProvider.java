package com.amazon.alexa.handsfree.notification.views.enablehandsfree;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.notification.notificationmetadata.NotificationMetadata;
import com.amazon.alexa.handsfree.notification.views.base.NotificationMetadataProvider;
/* loaded from: classes8.dex */
public class EnableHandsFreeMetadataProvider implements NotificationMetadataProvider {
    @Override // com.amazon.alexa.handsfree.notification.views.base.NotificationMetadataProvider
    public NotificationMetadata getNotificationMetadata(@NonNull Context context) {
        return NotificationMetadata.ENABLE_HANDS_FREE;
    }
}
