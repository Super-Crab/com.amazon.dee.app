package com.amazon.alexa.voice.handsfree.mike.vesper.notifications.notifiers;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.voice.handsfree.mike.vesper.notifications.NotificationOccurrenceCounter;
import com.amazon.alexa.voice.handsfree.mike.vesper.notifications.channels.NotificationChannelProperties;
import com.amazon.alexa.voice.handsfree.mike.vesper.notifications.metrics.NotificationEventMetadata;
import com.amazon.alexa.voice.handsfree.mike.vesper.notifications.views.AppDownloadNotification;
import java.util.Objects;
/* loaded from: classes11.dex */
public class AppDownloadNotifier {
    private static final String EMPTY_NOTIFICATION_TITLE = "";
    static final int NOTIFICATION_ID = 1001;
    private static final String TAG = "AppDownloadNotifier";
    private final AMPDInformationProvider mAMPDInformationProvider;
    private final AppDownloadNotification mAppDownloadNotification;
    private final Context mContext;
    private final MetricsBuilderProvider mMetricsBuilderProvider;
    private final NotificationManager mNotificationManager;
    private final NotificationOccurrenceCounter mNotificationOccurrenceCounter;
    private final PackageManager mPackageManager;

    public AppDownloadNotifier(@NonNull Context context) {
        this(context, (NotificationManager) context.getSystemService(NotificationManager.class), new AppDownloadNotification(NotificationChannelProperties.HANDS_FREE_SETUP.getChannelId()), NotificationOccurrenceCounter.getInstance(), MetricsBuilderProvider.getInstance(context), AMPDInformationProvider.getInstance(context), context.getPackageManager());
    }

    public boolean isNotificationRequired() {
        String voiceAppPackageName = this.mAMPDInformationProvider.getVoiceAppPackageName();
        if (voiceAppPackageName == null) {
            return false;
        }
        try {
            this.mPackageManager.getPackageInfo(voiceAppPackageName, 0);
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return true;
        }
    }

    public void reportNotificationMetric() {
        Notification notification = this.mAppDownloadNotification.getNotification(this.mContext);
        String string = notification.extras.getString(NotificationCompat.EXTRA_TEXT);
        this.mMetricsBuilderProvider.newBuilder().withNotificationEventMetric(TAG, NotificationEventMetadata.Component.HANDSFREE_SETUP, NotificationEventMetadata.NotificationType.PARTNER_VOICE_APP_DOWNLOAD, Objects.toString(notification.extras.getString(NotificationCompat.EXTRA_TITLE), ""), Objects.toString(string, "")).emit(this.mContext);
    }

    public void showNotification() {
        this.mNotificationManager.notify(1001, this.mAppDownloadNotification.getNotification(this.mContext));
        reportNotificationMetric();
    }

    public void updateNotificationCount() {
        this.mNotificationOccurrenceCounter.incrementNotificationCount(this.mContext);
    }

    @VisibleForTesting
    AppDownloadNotifier(@NonNull Context context, @NonNull NotificationManager notificationManager, @NonNull AppDownloadNotification appDownloadNotification, @NonNull NotificationOccurrenceCounter notificationOccurrenceCounter, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull AMPDInformationProvider aMPDInformationProvider, @NonNull PackageManager packageManager) {
        this.mContext = context;
        this.mNotificationManager = notificationManager;
        this.mAppDownloadNotification = appDownloadNotification;
        this.mNotificationOccurrenceCounter = notificationOccurrenceCounter;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mAMPDInformationProvider = aMPDInformationProvider;
        this.mPackageManager = packageManager;
    }
}
