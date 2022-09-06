package com.amazon.alexa.handsfree.notification.configurations.utterancebased;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.NotificationModule;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.R;
import com.amazon.alexa.handsfree.notification.api.ConfigurationProvider;
import com.amazon.alexa.handsfree.notification.configurations.NotificationQuotaManager;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import java.util.List;
import javax.inject.Inject;
/* loaded from: classes8.dex */
public class UtteranceConfigHandler implements NotificationQuotaManager.QuotaConstraintsHandler {
    private static final String TAG = "UtteranceConfigHandler";
    private final ConfigurationProvider mConfigurationProvider;
    private final Context mContext;

    public UtteranceConfigHandler(@NonNull Context context) {
        this(context, NotificationModule.getInstance().getContract().getConfigurationProvider());
    }

    private int getDefaultLimit(int i) {
        return this.mContext.getResources().getInteger(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getConfigDaysAfterFirstUtterance(@NonNull NotificationType notificationType) {
        Integer maxDaysAfterFirstUtterance = this.mConfigurationProvider.getMaxDaysAfterFirstUtterance(this.mContext, notificationType.getRemoteConfigKey());
        if (maxDaysAfterFirstUtterance == null) {
            int defaultLimit = getDefaultLimit(R.integer.maximum_days_after_first_utterance);
            String str = TAG;
            Log.d(str, "Remotely configured maximum days after first utterance notification can not be loaded, using default: " + defaultLimit);
            return defaultLimit;
        }
        String str2 = TAG;
        Log.d(str2, "Remotely configured maximum days after first utterance notification: " + maxDaysAfterFirstUtterance);
        return maxDaysAfterFirstUtterance.intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getConfigHoursBeforeNextUtterance(@NonNull NotificationType notificationType) {
        Integer minHoursBeforeNextUtterance = this.mConfigurationProvider.getMinHoursBeforeNextUtterance(this.mContext, notificationType.getRemoteConfigKey());
        if (minHoursBeforeNextUtterance == null) {
            int defaultLimit = getDefaultLimit(R.integer.minimum_hours_before_next_utterance);
            String str = TAG;
            Log.d(str, "Remotely configured minimum hours for next utterance notification can not be loaded, using default: " + defaultLimit);
            return defaultLimit;
        }
        String str2 = TAG;
        Log.d(str2, "Remotely configured minimum hours for next utterance notification: " + minHoursBeforeNextUtterance);
        return minHoursBeforeNextUtterance.intValue();
    }

    @Override // com.amazon.alexa.handsfree.notification.configurations.NotificationQuotaManager.QuotaConstraintsHandler
    public List<Long> getConfigTimeIntervals(@NonNull NotificationType notificationType) {
        return null;
    }

    @Override // com.amazon.alexa.handsfree.notification.configurations.NotificationQuotaManager.QuotaConstraintsHandler
    public int getTotalNotifications(@NonNull NotificationType notificationType) {
        Integer utteranceNotificationMaxCount = this.mConfigurationProvider.getUtteranceNotificationMaxCount(this.mContext, notificationType.getRemoteConfigKey());
        if (utteranceNotificationMaxCount == null) {
            int defaultLimit = getDefaultLimit(R.integer.maximum_count);
            String str = TAG;
            Log.d(str, "Remotely configured maximum count for " + notificationType + " notifications can not be loaded, using default: " + defaultLimit);
            return defaultLimit;
        }
        String str2 = TAG;
        Log.d(str2, "Remotely configured maximum count for " + notificationType + " notifications: " + utteranceNotificationMaxCount);
        return utteranceNotificationMaxCount.intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    @VisibleForTesting
    public UtteranceConfigHandler(@NonNull Context context, @NonNull ConfigurationProvider configurationProvider) {
        this.mContext = context;
        this.mConfigurationProvider = configurationProvider;
    }
}
