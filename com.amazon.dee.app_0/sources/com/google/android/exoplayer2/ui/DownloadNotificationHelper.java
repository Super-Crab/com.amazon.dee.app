package com.google.android.exoplayer2.ui;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.app.NotificationCompat;
/* loaded from: classes2.dex */
public final class DownloadNotificationHelper {
    @StringRes
    private static final int NULL_STRING_ID = 0;
    private final NotificationCompat.Builder notificationBuilder;

    public DownloadNotificationHelper(Context context, String str) {
        this.notificationBuilder = new NotificationCompat.Builder(context.getApplicationContext(), str);
    }

    private Notification buildEndStateNotification(Context context, @DrawableRes int i, @Nullable PendingIntent pendingIntent, @Nullable String str, @StringRes int i2) {
        return buildNotification(context, i, pendingIntent, str, i2, 0, 0, false, false, true);
    }

    private Notification buildNotification(Context context, @DrawableRes int i, @Nullable PendingIntent pendingIntent, @Nullable String str, @StringRes int i2, int i3, int i4, boolean z, boolean z2, boolean z3) {
        this.notificationBuilder.setSmallIcon(i);
        NotificationCompat.BigTextStyle bigTextStyle = null;
        this.notificationBuilder.setContentTitle(i2 == 0 ? null : context.getResources().getString(i2));
        this.notificationBuilder.setContentIntent(pendingIntent);
        NotificationCompat.Builder builder = this.notificationBuilder;
        if (str != null) {
            bigTextStyle = new NotificationCompat.BigTextStyle().bigText(str);
        }
        builder.setStyle(bigTextStyle);
        this.notificationBuilder.setProgress(i3, i4, z);
        this.notificationBuilder.setOngoing(z2);
        this.notificationBuilder.setShowWhen(z3);
        return this.notificationBuilder.build();
    }

    public Notification buildDownloadCompletedNotification(Context context, @DrawableRes int i, @Nullable PendingIntent pendingIntent, @Nullable String str) {
        return buildEndStateNotification(context, i, pendingIntent, str, R.string.exo_download_completed);
    }

    public Notification buildDownloadFailedNotification(Context context, @DrawableRes int i, @Nullable PendingIntent pendingIntent, @Nullable String str) {
        return buildEndStateNotification(context, i, pendingIntent, str, R.string.exo_download_failed);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.app.Notification buildProgressNotification(android.content.Context r21, @androidx.annotation.DrawableRes int r22, @androidx.annotation.Nullable android.app.PendingIntent r23, @androidx.annotation.Nullable java.lang.String r24, java.util.List<com.google.android.exoplayer2.offline.Download> r25) {
        /*
            r20 = this;
            r0 = 1
            r1 = 0
            r2 = 0
            r7 = r0
            r3 = r1
            r4 = r3
            r6 = r4
            r8 = r6
            r5 = r2
            r2 = r8
        La:
            int r9 = r25.size()
            if (r2 >= r9) goto L47
            r9 = r25
            java.lang.Object r10 = r9.get(r2)
            com.google.android.exoplayer2.offline.Download r10 = (com.google.android.exoplayer2.offline.Download) r10
            int r11 = r10.state
            r12 = 5
            if (r11 != r12) goto L1f
            r4 = r0
            goto L44
        L1f:
            r12 = 7
            if (r11 == r12) goto L26
            r12 = 2
            if (r11 == r12) goto L26
            goto L44
        L26:
            float r3 = r10.getPercentDownloaded()
            r11 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r11 = (r3 > r11 ? 1 : (r3 == r11 ? 0 : -1))
            if (r11 == 0) goto L32
            float r5 = r5 + r3
            r7 = r1
        L32:
            long r10 = r10.getBytesDownloaded()
            r12 = 0
            int r3 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r3 <= 0) goto L3e
            r3 = r0
            goto L3f
        L3e:
            r3 = r1
        L3f:
            r3 = r3 | r8
            int r6 = r6 + 1
            r8 = r3
            r3 = r0
        L44:
            int r2 = r2 + 1
            goto La
        L47:
            if (r3 == 0) goto L4d
            int r2 = com.google.android.exoplayer2.ui.R.string.exo_download_downloading
        L4b:
            r14 = r2
            goto L53
        L4d:
            if (r4 == 0) goto L52
            int r2 = com.google.android.exoplayer2.ui.R.string.exo_download_removing
            goto L4b
        L52:
            r14 = r1
        L53:
            if (r3 == 0) goto L63
            float r2 = (float) r6
            float r5 = r5 / r2
            int r2 = (int) r5
            if (r7 == 0) goto L5d
            if (r8 == 0) goto L5d
            goto L5e
        L5d:
            r0 = r1
        L5e:
            r17 = r0
            r16 = r2
            goto L67
        L63:
            r17 = r0
            r16 = r1
        L67:
            r15 = 100
            r18 = 1
            r19 = 0
            r9 = r20
            r10 = r21
            r11 = r22
            r12 = r23
            r13 = r24
            android.app.Notification r0 = r9.buildNotification(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.DownloadNotificationHelper.buildProgressNotification(android.content.Context, int, android.app.PendingIntent, java.lang.String, java.util.List):android.app.Notification");
    }
}
