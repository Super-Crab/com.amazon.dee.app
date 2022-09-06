package com.amazon.alexa.handsfree.notification.views.permission;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.notification.notificationmetadata.NotificationMetadata;
import com.amazon.alexa.handsfree.notification.views.base.NotificationMetadataProvider;
/* loaded from: classes8.dex */
public class PermissionMetadataProvider implements NotificationMetadataProvider {
    @Override // com.amazon.alexa.handsfree.notification.views.base.NotificationMetadataProvider
    @Nullable
    public NotificationMetadata getNotificationMetadata(@NonNull Context context) {
        return NotificationMetadata.PERMISSION_REQUEST;
    }
}
