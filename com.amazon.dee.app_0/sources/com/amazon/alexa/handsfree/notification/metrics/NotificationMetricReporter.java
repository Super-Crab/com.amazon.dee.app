package com.amazon.alexa.handsfree.notification.metrics;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.DismissIntentProvider;
import com.amazon.alexa.handsfree.notification.NotificationModule;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.api.NotificationContract;
import com.amazon.alexa.handsfree.notification.metrics.NotificationClickMetricMetadata;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import javax.inject.Inject;
/* loaded from: classes8.dex */
public class NotificationMetricReporter {
    private static final String EMPTY_NOTIFICATION_TITLE = "";
    private final Context mContext;
    private final MetricsBuilderProvider mMetricsBuilderProvider;
    private final NotificationContract mNotificationContract;

    /* renamed from: com.amazon.alexa.handsfree.notification.metrics.NotificationMetricReporter$1  reason: invalid class name */
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
                $SwitchMap$com$amazon$alexa$handsfree$notification$NotificationType[NotificationType.VOICE_PROFILE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$handsfree$notification$NotificationType[NotificationType.VOICE_PROFILE_ON_LOCK_SCREEN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$handsfree$notification$NotificationType[NotificationType.SHOW_ON_LOCK_SCREEN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$handsfree$notification$NotificationType[NotificationType.QUICK_SETTINGS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$alexa$handsfree$notification$NotificationType[NotificationType.LANGUAGE_SWITCHING.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public NotificationMetricReporter(@NonNull Context context) {
        this(context, MetricsBuilderProvider.getInstance(context), NotificationModule.getInstance().getContract());
    }

    public void reportNotificationClickMetric(@NonNull Intent intent) {
        NotificationClickMetricMetadata.PageType pageType;
        if (!intent.hasExtra(DismissIntentProvider.EXTRA_NOTIFICATION_OPERATION)) {
            return;
        }
        NotificationType valueOf = NotificationType.valueOf(intent.getStringExtra(DismissIntentProvider.EXTRA_NOTIFICATION_OPERATION));
        String stringExtra = intent.getStringExtra("extra_notification_text");
        NotificationClickMetricMetadata.SubPageType subPageType = NotificationClickMetricMetadata.SubPageType.NONE;
        int ordinal = valueOf.ordinal();
        if (ordinal != 9) {
            switch (ordinal) {
                case 0:
                    pageType = NotificationClickMetricMetadata.PageType.TIME_BASED_NOTIFICATION;
                    break;
                case 1:
                    pageType = NotificationClickMetricMetadata.PageType.UTTERANCE_BASED_NOTIFICATION;
                    break;
                case 2:
                    pageType = NotificationClickMetricMetadata.PageType.DISABLED_STATE_NOTIFICATION;
                    break;
                case 3:
                    pageType = NotificationClickMetricMetadata.PageType.VOICE_PROFILE_NOTIFICATION;
                    break;
                case 4:
                    pageType = NotificationClickMetricMetadata.PageType.VOICE_PROFILE_ON_LOCK_SCREEN_NOTIFICATION;
                    break;
                case 5:
                    pageType = NotificationClickMetricMetadata.PageType.SHOW_ON_LOCK_SCREEN_NOTIFICATION;
                    break;
                case 6:
                    pageType = NotificationClickMetricMetadata.PageType.QUICK_SETTINGS_NOTIFICATION;
                    NotificationContract notificationContract = this.mNotificationContract;
                    if (notificationContract != null) {
                        if (!notificationContract.getSettingsProvider().isQsTileInMainMenu()) {
                            subPageType = NotificationClickMetricMetadata.SubPageType.QS_TILE_IN_EDIT_MENU;
                            break;
                        } else {
                            subPageType = NotificationClickMetricMetadata.SubPageType.QS_TILE_IN_MAIN_MENU;
                            break;
                        }
                    }
                    break;
                default:
                    return;
            }
        } else {
            pageType = NotificationClickMetricMetadata.PageType.LANGUAGE_SWITCHING_NOTIFICATION;
        }
        reportNotificationClickMetric(pageType, subPageType, stringExtra);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    @VisibleForTesting
    public NotificationMetricReporter(@NonNull Context context, @NonNull MetricsBuilderProvider metricsBuilderProvider, @Nullable NotificationContract notificationContract) {
        this.mContext = context;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mNotificationContract = notificationContract;
    }

    public void reportNotificationClickMetric(@NonNull NotificationClickMetricMetadata.PageType pageType, @NonNull String str) {
        reportNotificationClickMetric(pageType, NotificationClickMetricMetadata.SubPageType.NONE, str);
    }

    public void reportNotificationClickMetric(@NonNull NotificationClickMetricMetadata.PageType pageType, @NonNull NotificationClickMetricMetadata.SubPageType subPageType, @NonNull String str) {
        this.mMetricsBuilderProvider.newBuilder().withNotificationClickMetric(NotificationMetricReporter.class.getName(), NotificationClickMetricMetadata.Component.NOTIFICATION, pageType, subPageType, "", str).emit(this.mContext);
    }
}
