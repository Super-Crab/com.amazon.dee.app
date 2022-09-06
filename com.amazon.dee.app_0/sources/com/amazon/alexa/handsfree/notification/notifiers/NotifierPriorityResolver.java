package com.amazon.alexa.handsfree.notification.notifiers;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.NotificationModule;
import com.amazon.alexa.handsfree.notification.metrics.MetricType;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import java.util.Arrays;
import javax.inject.Inject;
/* loaded from: classes8.dex */
public class NotifierPriorityResolver {
    private static final String TAG = "NotifierPriorityResolver";
    private final Context mContext;
    private final Intent mIntent;
    private final MetricsBuilderProvider mMetricsBuilderProvider;
    private final NotificationModule mNotificationModule;
    private final NotifierFactory mNotifierFactory;

    public NotifierPriorityResolver(@NonNull Context context, @NonNull Intent intent) {
        this(context, intent, new NotifierFactory(context), NotificationModule.getInstance(), MetricsBuilderProvider.getInstance(context));
    }

    private boolean isNotificationContractNull(@NonNull MetricType metricType) {
        if (this.mNotificationModule.getContract() == null) {
            this.mMetricsBuilderProvider.newBuilder().withPercentileMetricFailure(TAG, metricType.getValue()).emit(this.mContext);
            return true;
        }
        this.mMetricsBuilderProvider.newBuilder().withPercentileMetricSuccess(TAG, metricType.getValue()).emit(this.mContext);
        return false;
    }

    public Notifier getEnableHandsFreeNotifier() {
        return this.mNotifierFactory.getEnableHandsFreeNotifier();
    }

    @NonNull
    public Notifier getKillSwitchNotifier() {
        return this.mNotifierFactory.getKillSwitchNotifier();
    }

    @Nullable
    public Notifier getLanguageSwitchingNotifier() {
        Notifier languageSwitchingNotifier = this.mNotifierFactory.getLanguageSwitchingNotifier();
        if (languageSwitchingNotifier.isNotificationRequired()) {
            return languageSwitchingNotifier;
        }
        return null;
    }

    @Nullable
    public Notifier getPermissionNotifier() {
        Notifier permissionNotifier = this.mNotifierFactory.getPermissionNotifier();
        if (permissionNotifier.isNotificationRequired()) {
            return permissionNotifier;
        }
        return null;
    }

    @Nullable
    public Notifier getQuickSettingsNotifier() {
        Notifier quickSettingsNotifier = this.mNotifierFactory.getQuickSettingsNotifier();
        if (quickSettingsNotifier.isNotificationRequired()) {
            return quickSettingsNotifier;
        }
        return null;
    }

    @Nullable
    public Notifier getTimeBasedNotifier() {
        if (isNotificationContractNull(MetricType.NOTIFICATION_CONTRACT_NULL_TIME_BASED)) {
            return null;
        }
        Notifier handsFreeTimeBasedNotifier = this.mNotifierFactory.getHandsFreeTimeBasedNotifier();
        if (!handsFreeTimeBasedNotifier.isNotificationRequired()) {
            return null;
        }
        return handsFreeTimeBasedNotifier;
    }

    @Nullable
    public Notifier getUtteranceBasedNotifier() {
        if (isNotificationContractNull(MetricType.NOTIFICATION_CONTRACT_NULL_UTTERANCE)) {
            return null;
        }
        for (Notifier notifier : Arrays.asList(this.mNotifierFactory.getHandsFreeUtteranceNotifier(this.mIntent), this.mNotifierFactory.getVoiceProfileSetupNotifier(this.mIntent), this.mNotifierFactory.getShowOnLockScreenNotifier(this.mIntent))) {
            if (notifier.isNotificationRequired()) {
                Log.d(TAG, String.format("Notifier required: %s", notifier));
                return notifier;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    @VisibleForTesting
    public NotifierPriorityResolver(@NonNull Context context, @NonNull Intent intent, @NonNull NotifierFactory notifierFactory, @NonNull NotificationModule notificationModule, @NonNull MetricsBuilderProvider metricsBuilderProvider) {
        this.mContext = context;
        this.mIntent = intent;
        this.mNotifierFactory = notifierFactory;
        this.mNotificationModule = notificationModule;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
    }
}
