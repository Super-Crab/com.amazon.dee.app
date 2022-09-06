package com.amazon.alexa.handsfree.notification.notifiers;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.service.notification.StatusBarNotification;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.NotificationModule;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.NotificationUtils;
import com.amazon.alexa.handsfree.notification.R;
import com.amazon.alexa.handsfree.notification.api.ConfigurationProvider;
import com.amazon.alexa.handsfree.notification.api.HandsFreeSetupStateProvider;
import com.amazon.alexa.handsfree.notification.views.base.NotificationView;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.utils.AlexaAppSignInContract;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import dagger.Lazy;
/* loaded from: classes8.dex */
public abstract class Notifier {
    @VisibleForTesting
    static final String NUMBER_OF_SHOWN_NOTIFICATIONS_KEY = "com.amazon.alexa.handsfree.notification.notifiers.ShownNotifications";
    public static final String SHARED_PREFERENCES_FILE = "com.amazon.alexa.handsfree.notification.notifiers.Notifier";
    private static final String TAG = "Notifier";
    private final Lazy<AlexaAppSignInContract> mAlexaAppSignInContractLazy;
    private final ConfigurationProvider mConfigurationProvider;
    private final Context mContext;
    private final HandsFreeSetupStateProvider mHandsFreeSetupStateProvider;
    private final NotificationManager mNotificationManager;
    private final NotificationType mNotificationType;
    private final NotificationUtils mNotificationUtils;
    private final NotificationView mNotificationView;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Notifier(@NonNull Context context, @NonNull NotificationType notificationType, @NonNull NotificationView notificationView) {
        this(context, notificationType, new NotificationUtils(context, notificationType), notificationView, NotificationModule.getInstance().getContract().getHandsFreeSetupStateProvider(), NotificationModule.getInstance().getContract().getConfigurationProvider(), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).alexaAppSignInContractLazy());
    }

    public boolean canEnableWakeWord() {
        return this.mHandsFreeSetupStateProvider.canEnableWakeWord(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Context getContext() {
        return this.mContext;
    }

    public int getNotificationOccurrenceCount() {
        return this.mNotificationUtils.getNotificationOccurrenceCount();
    }

    public NotificationType getNotificationType() {
        return this.mNotificationType;
    }

    @VisibleForTesting
    int getNumberOfShownNotifications() {
        return getSharedPreferences(this.mContext).getInt(NUMBER_OF_SHOWN_NOTIFICATIONS_KEY, 0);
    }

    @VisibleForTesting
    SharedPreferences getSharedPreferences(@NonNull Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_FILE, 0);
    }

    @VisibleForTesting
    void increaseNumberOfShownNotifications() {
        getSharedPreferences(this.mContext).edit().putInt(NUMBER_OF_SHOWN_NOTIFICATIONS_KEY, getSharedPreferences(this.mContext).getInt(NUMBER_OF_SHOWN_NOTIFICATIONS_KEY, 0) + 1).apply();
    }

    public boolean isHandsFreeSetupComplete() {
        return this.mHandsFreeSetupStateProvider.isHandsFreeSetupComplete(this.mContext);
    }

    public boolean isNotificationAllowed() {
        int integer;
        if (this.mAlexaAppSignInContractLazy.mo358get().getSignInState(this.mContext, false)) {
            return true;
        }
        if (this.mConfigurationProvider.getMaxAllowedNotificationsBeforeTerms(this.mContext) != null) {
            integer = this.mConfigurationProvider.getMaxAllowedNotificationsBeforeTerms(this.mContext).intValue();
        } else {
            integer = this.mContext.getResources().getInteger(R.integer.maximum_allowed_notifications_before_terms);
        }
        return getNumberOfShownNotifications() < integer;
    }

    protected boolean isNotificationCurrentlyActive(int i) {
        try {
            for (StatusBarNotification statusBarNotification : this.mNotificationManager.getActiveNotifications()) {
                if (statusBarNotification.getId() == i) {
                    return true;
                }
            }
            return false;
        } catch (RuntimeException unused) {
            Log.e(TAG, "NotificationManager couldn't retrieve active notifications");
            return false;
        }
    }

    public abstract boolean isNotificationRequired();

    @VisibleForTesting
    void reportNotificationMetric() {
        this.mNotificationUtils.reportNotificationMetric(this.mNotificationView, this.mNotificationType.getComponent(), this.mNotificationType.getNotificationType(), this);
    }

    public void showNotification() {
        this.mNotificationManager.notify(this.mNotificationView.getNotificationId(), this.mNotificationView.getNotification(this.mContext));
        reportNotificationMetric();
        increaseNumberOfShownNotifications();
    }

    public void updateNotificationCount() {
        this.mNotificationUtils.updateNotificationCount();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public Notifier(@NonNull Context context, @NonNull NotificationType notificationType, @NonNull NotificationUtils notificationUtils, @NonNull NotificationView notificationView, @NonNull HandsFreeSetupStateProvider handsFreeSetupStateProvider, @NonNull ConfigurationProvider configurationProvider, @NonNull Lazy<AlexaAppSignInContract> lazy) {
        this.mContext = context;
        this.mNotificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
        this.mNotificationType = notificationType;
        this.mNotificationUtils = notificationUtils;
        this.mNotificationView = notificationView;
        this.mHandsFreeSetupStateProvider = handsFreeSetupStateProvider;
        this.mConfigurationProvider = configurationProvider;
        this.mAlexaAppSignInContractLazy = lazy;
    }
}
