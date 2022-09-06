package com.amazon.alexa.handsfree.notification.views.decider;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.NotificationModule;
import com.amazon.alexa.handsfree.notification.api.HandsFreeSetupStateProvider;
import com.amazon.alexa.handsfree.notification.notificationmetadata.NotificationMetadata;
import com.amazon.alexa.handsfree.notification.views.NotificationStateProvider;
import com.amazon.alexa.handsfree.notification.views.base.NotificationMetadataProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import javax.inject.Inject;
/* loaded from: classes8.dex */
public class DeciderMetadataProvider implements NotificationMetadataProvider {
    private static final String TAG = "DeciderMetadataProvider";
    private final HandsFreeSetupStateProvider mHandsFreeSetupStateProvider;
    private final NotificationStateProvider mNotificationStateProvider;

    public DeciderMetadataProvider() {
        this(new NotificationStateProvider(), NotificationModule.getInstance().getContract().getHandsFreeSetupStateProvider());
    }

    @Override // com.amazon.alexa.handsfree.notification.views.base.NotificationMetadataProvider
    @Nullable
    public NotificationMetadata getNotificationMetadata(@NonNull Context context) {
        if (this.mNotificationStateProvider.isNotificationDisabled(context)) {
            return null;
        }
        Log.d(TAG, "Decider to fetch metadata");
        return this.mHandsFreeSetupStateProvider.getNotificationMetadata(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    @VisibleForTesting
    public DeciderMetadataProvider(@NonNull NotificationStateProvider notificationStateProvider, @NonNull HandsFreeSetupStateProvider handsFreeSetupStateProvider) {
        this.mNotificationStateProvider = notificationStateProvider;
        this.mHandsFreeSetupStateProvider = handsFreeSetupStateProvider;
    }
}
