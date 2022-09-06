package com.amazon.alexa.handsfree.notification.configurations.timebased;

import android.content.Context;
import android.content.res.Resources;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.NotificationModule;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.api.ConfigurationProvider;
import com.amazon.alexa.handsfree.notification.configurations.NotificationQuotaManager;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
/* loaded from: classes8.dex */
public class TimeIntervalConfigHandler implements NotificationQuotaManager.QuotaConstraintsHandler {
    private static final String TAG = "TimeIntervalConfigHandler";
    private final ConfigurationProvider mConfigurationProvider;
    private final Context mContext;

    public TimeIntervalConfigHandler(@NonNull Context context) {
        this(context, NotificationModule.getInstance().getContract().getConfigurationProvider());
    }

    @NonNull
    private List<Long> getDefaultConfigTimeIntervals(@NonNull NotificationType notificationType) {
        int[] intArray;
        ArrayList arrayList = new ArrayList();
        try {
            int length = this.mContext.getResources().getIntArray(notificationType.getDefaultTimeIntervalsResId()).length;
            for (int i = 0; i < length; i++) {
                arrayList.add(Long.valueOf(intArray[i]));
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "getDefaultConfigTimeIntervals exception", e, new Object[0]);
        }
        return arrayList;
    }

    @Nullable
    private List<Long> getRemoteConfigTimeIntervals(@NonNull NotificationType notificationType) {
        return this.mConfigurationProvider.getNotificationTimeIntervals(this.mContext, notificationType.getRemoteConfigKey());
    }

    @Override // com.amazon.alexa.handsfree.notification.configurations.NotificationQuotaManager.QuotaConstraintsHandler
    @NonNull
    public List<Long> getConfigTimeIntervals(@NonNull NotificationType notificationType) {
        List<Long> remoteConfigTimeIntervals = getRemoteConfigTimeIntervals(notificationType);
        if (remoteConfigTimeIntervals != null && !remoteConfigTimeIntervals.isEmpty()) {
            String str = TAG;
            Log.d(str, "Remotely configured notification time intervals: " + remoteConfigTimeIntervals);
            return remoteConfigTimeIntervals;
        }
        Log.d(TAG, "Remotely configured time intervals can not be loaded, using default");
        return getDefaultConfigTimeIntervals(notificationType);
    }

    @Override // com.amazon.alexa.handsfree.notification.configurations.NotificationQuotaManager.QuotaConstraintsHandler
    public int getTotalNotifications(@NonNull NotificationType notificationType) {
        return getConfigTimeIntervals(notificationType).size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    @VisibleForTesting
    public TimeIntervalConfigHandler(@NonNull Context context, @NonNull ConfigurationProvider configurationProvider) {
        this.mContext = context;
        this.mConfigurationProvider = configurationProvider;
    }
}
