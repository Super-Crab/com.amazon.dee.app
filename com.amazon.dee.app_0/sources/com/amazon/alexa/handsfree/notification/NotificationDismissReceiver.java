package com.amazon.alexa.handsfree.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.metrics.NotificationDismissMetadata;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import javax.inject.Inject;
/* loaded from: classes8.dex */
public class NotificationDismissReceiver extends BroadcastReceiver {
    private static final String EMPTY_NOTIFICATION_TITLE = "";
    private static final String TAG = NotificationDismissReceiver.class.getSimpleName();
    private final Initializer mInitializer;

    /* renamed from: com.amazon.alexa.handsfree.notification.NotificationDismissReceiver$1  reason: invalid class name */
    /* loaded from: classes8.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$handsfree$notification$NotificationType = new int[NotificationType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$handsfree$notification$NotificationType[NotificationType.TIME_BASED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$handsfree$notification$NotificationType[NotificationType.UTTERANCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$handsfree$notification$NotificationType[NotificationType.KILL_SWITCH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$handsfree$notification$NotificationType[NotificationType.ENABLE_HANDS_FREE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$handsfree$notification$NotificationType[NotificationType.LANGUAGE_SWITCHING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public NotificationDismissReceiver() {
        this(InitializerProvider.getInitializer());
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        NotificationDismissMetadata.NotificationTrigger notificationTrigger;
        if (!intent.hasExtra(DismissIntentProvider.EXTRA_NOTIFICATION_OPERATION)) {
            Log.d(TAG, "onReceive: no notification operation found");
            return;
        }
        this.mInitializer.initialize(context);
        NotificationType valueOf = NotificationType.valueOf(intent.getStringExtra(DismissIntentProvider.EXTRA_NOTIFICATION_OPERATION));
        String stringExtra = intent.getStringExtra("extra_notification_text");
        int ordinal = valueOf.ordinal();
        if (ordinal == 0) {
            Log.d(TAG, "onReceive: user dismissed a time based notification");
            notificationTrigger = NotificationDismissMetadata.NotificationTrigger.TIME_BASED_NOTIFICATION;
        } else if (ordinal == 1) {
            Log.d(TAG, "onReceive: user dismissed a utterance based notification");
            notificationTrigger = NotificationDismissMetadata.NotificationTrigger.UTTERANCE_BASED_NOTIFICATION;
        } else if (ordinal == 2) {
            Log.d(TAG, "onReceive: user dismissed a kill switch notification");
            notificationTrigger = NotificationDismissMetadata.NotificationTrigger.DISABLED_STATE_NOTIFICATION;
        } else if (ordinal == 8) {
            Log.d(TAG, "onReceive: user dismissed the enable Hands-Free notification");
            notificationTrigger = NotificationDismissMetadata.NotificationTrigger.ENABLE_HANDS_FREE_NOTIFICATION;
        } else if (ordinal != 9) {
            return;
        } else {
            Log.d(TAG, "onReceive: user dismissed a language switching notification");
            notificationTrigger = NotificationDismissMetadata.NotificationTrigger.LANGUAGE_SWITCHING_NOTIFICATION;
        }
        MetricsBuilderProvider.getInstance(context).newBuilder().withNotificationDismissMetric(TAG, notificationTrigger, "", stringExtra).emit(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    @VisibleForTesting
    public NotificationDismissReceiver(@NonNull Initializer initializer) {
        this.mInitializer = initializer;
    }
}
