package com.google.android.exoplayer2.ui;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.offline.Download;
import java.util.List;
@Deprecated
/* loaded from: classes2.dex */
public final class DownloadNotificationUtil {
    private DownloadNotificationUtil() {
    }

    public static Notification buildDownloadCompletedNotification(Context context, @DrawableRes int i, String str, @Nullable PendingIntent pendingIntent, @Nullable String str2) {
        return new DownloadNotificationHelper(context, str).buildDownloadCompletedNotification(context, i, pendingIntent, str2);
    }

    public static Notification buildDownloadFailedNotification(Context context, @DrawableRes int i, String str, @Nullable PendingIntent pendingIntent, @Nullable String str2) {
        return new DownloadNotificationHelper(context, str).buildDownloadFailedNotification(context, i, pendingIntent, str2);
    }

    public static Notification buildProgressNotification(Context context, @DrawableRes int i, String str, @Nullable PendingIntent pendingIntent, @Nullable String str2, List<Download> list) {
        return new DownloadNotificationHelper(context, str).buildProgressNotification(context, i, pendingIntent, str2, list);
    }
}
