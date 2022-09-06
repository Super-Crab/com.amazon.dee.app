package com.amazon.alexa.voice.handsfree.settings;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.metrics.NotificationMetricReporter;
import com.amazon.alexa.voice.handsfree.metrics.MetricsConstants;
/* loaded from: classes11.dex */
public class QSEducationActivityPresenter {
    private static final String TAG = "QSEducationActivityPresenter";
    private NotificationMetricReporter mNotificationMetricReporter;
    private QSEducationMetricRecorder mQSEducationMetricRecorder;

    /* JADX INFO: Access modifiers changed from: package-private */
    public QSEducationActivityPresenter(@NonNull Context context) {
        this(new QSEducationMetricRecorder(context), new NotificationMetricReporter(context));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onDoneButtonClick() {
        this.mQSEducationMetricRecorder.recordClick(MetricsConstants.SubPageType.DONE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onNotificationClick(@NonNull Intent intent) {
        this.mNotificationMetricReporter.reportNotificationClickMetric(intent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onPageView(@NonNull boolean z) {
        this.mQSEducationMetricRecorder.recordPageView(z ? MetricsConstants.SubPageType.MAIN_MENU_EDUCATION : MetricsConstants.SubPageType.EDIT_MENU_EDUCATION);
    }

    @VisibleForTesting
    QSEducationActivityPresenter(@NonNull QSEducationMetricRecorder qSEducationMetricRecorder, @NonNull NotificationMetricReporter notificationMetricReporter) {
        this.mQSEducationMetricRecorder = qSEducationMetricRecorder;
        this.mNotificationMetricReporter = notificationMetricReporter;
    }
}
