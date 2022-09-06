package com.amazon.alexa.handsfree.notification.notifiers;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.NotificationUtils;
import com.amazon.alexa.handsfree.notification.api.ConfigurationProvider;
import com.amazon.alexa.handsfree.notification.api.HandsFreeSetupStateProvider;
import com.amazon.alexa.handsfree.notification.configurations.NotificationQuotaManager;
import com.amazon.alexa.handsfree.notification.configurations.timebased.TimeIntervalConfigHandler;
import com.amazon.alexa.handsfree.notification.views.NotificationViewFactory;
import com.amazon.alexa.handsfree.notification.views.base.NotificationView;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManager;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManagerProvider;
import com.amazon.alexa.handsfree.protocols.utils.AlexaAppSignInContract;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
/* loaded from: classes8.dex */
public class EnableHandsFreeNotifier extends Notifier {
    public static final long HANDS_FREE_API_TIMEOUT_MILLIS = 10000;
    private static final String TAG = "EnableHandsFreeNotifier";
    private final NotificationQuotaManager mNotificationQuotaManager;
    private final PreferenceManager mPreferenceManager;
    private final WakeWordSettingsManager mWakeWordSettingsManager;

    /* loaded from: classes8.dex */
    public static class PreferenceManager {
        @VisibleForTesting
        static final String SHOW_ENABLE_HANDS_FREE_NOTIFICATION_KEY = "showEnableHandsFreeNotification";

        private SharedPreferences getSharedPreferences(@NonNull Context context) {
            return context.getSharedPreferences(Notifier.SHARED_PREFERENCES_FILE, 0);
        }

        public void disableEnableHandsFreeNotification(@NonNull Context context) {
            GeneratedOutlineSupport1.outline143(getSharedPreferences(context), SHOW_ENABLE_HANDS_FREE_NOTIFICATION_KEY, false);
        }

        public boolean shouldShowEnableHandsFreeNotification(@NonNull Context context) {
            return getSharedPreferences(context).getBoolean(SHOW_ENABLE_HANDS_FREE_NOTIFICATION_KEY, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EnableHandsFreeNotifier(@NonNull Context context) {
        super(context, NotificationType.ENABLE_HANDS_FREE, new NotificationViewFactory().getEnableHandsFreeNotification());
        this.mPreferenceManager = new PreferenceManager();
        this.mNotificationQuotaManager = new NotificationQuotaManager(context, new TimeIntervalConfigHandler(context));
        this.mWakeWordSettingsManager = WakeWordSettingsManagerProvider.getInstance().get();
    }

    private boolean isHandsFreeEnabled() {
        final CompletableFuture completableFuture = new CompletableFuture();
        this.mWakeWordSettingsManager.checkHandsFreeState(new ResultCallback<Boolean>() { // from class: com.amazon.alexa.handsfree.notification.notifiers.EnableHandsFreeNotifier.1
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                completableFuture.complete(true);
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
            public void onResult(@NonNull Boolean bool) {
                if (bool.booleanValue()) {
                    EnableHandsFreeNotifier.this.mPreferenceManager.disableEnableHandsFreeNotification(EnableHandsFreeNotifier.this.getContext());
                }
                completableFuture.complete(bool);
            }
        });
        try {
            return ((Boolean) completableFuture.get(10000L, TimeUnit.MILLISECONDS)).booleanValue();
        } catch (Exception unused) {
            return true;
        }
    }

    @Override // com.amazon.alexa.handsfree.notification.notifiers.Notifier
    public boolean isNotificationRequired() {
        boolean z = canEnableWakeWord() && this.mNotificationQuotaManager.hasQuotaAvailable(NotificationType.ENABLE_HANDS_FREE) && this.mPreferenceManager.shouldShowEnableHandsFreeNotification(getContext()) && !isHandsFreeEnabled();
        GeneratedOutlineSupport1.outline172("isNotificationRequired: ", z);
        return z;
    }

    @VisibleForTesting
    EnableHandsFreeNotifier(@NonNull Context context, @NonNull NotificationUtils notificationUtils, @NonNull NotificationView notificationView, @NonNull HandsFreeSetupStateProvider handsFreeSetupStateProvider, @NonNull ConfigurationProvider configurationProvider, @NonNull Lazy<AlexaAppSignInContract> lazy, @NonNull NotificationQuotaManager notificationQuotaManager, @NonNull WakeWordSettingsManager wakeWordSettingsManager) {
        super(context, NotificationType.ENABLE_HANDS_FREE, notificationUtils, notificationView, handsFreeSetupStateProvider, configurationProvider, lazy);
        this.mPreferenceManager = new PreferenceManager();
        this.mNotificationQuotaManager = notificationQuotaManager;
        this.mWakeWordSettingsManager = wakeWordSettingsManager;
    }
}
