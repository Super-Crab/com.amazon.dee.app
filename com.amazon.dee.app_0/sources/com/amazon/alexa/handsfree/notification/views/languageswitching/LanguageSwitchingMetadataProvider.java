package com.amazon.alexa.handsfree.notification.views.languageswitching;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.notificationmetadata.NotificationMetadata;
import com.amazon.alexa.handsfree.notification.views.NotificationStateProvider;
import com.amazon.alexa.handsfree.notification.views.base.NotificationMetadataProvider;
import javax.inject.Inject;
/* loaded from: classes8.dex */
public class LanguageSwitchingMetadataProvider implements NotificationMetadataProvider {
    private final NotificationStateProvider mNotificationStateProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LanguageSwitchingMetadataProvider() {
        this(new NotificationStateProvider());
    }

    @Override // com.amazon.alexa.handsfree.notification.views.base.NotificationMetadataProvider
    @Nullable
    public NotificationMetadata getNotificationMetadata(@NonNull Context context) {
        if (this.mNotificationStateProvider.isNotificationDisabled(context)) {
            return null;
        }
        return NotificationMetadata.DYNAMIC_LANGUAGE_SWITCHING;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    @VisibleForTesting
    public LanguageSwitchingMetadataProvider(@NonNull NotificationStateProvider notificationStateProvider) {
        this.mNotificationStateProvider = notificationStateProvider;
    }
}
