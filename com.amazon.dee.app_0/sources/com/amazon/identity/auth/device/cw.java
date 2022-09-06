package com.amazon.identity.auth.device;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.amazon.identity.auth.device.framework.PendingIntentWrapper;
/* compiled from: DCP */
@SuppressLint({"MissingPermission"})
/* loaded from: classes12.dex */
public class cw {
    private static final String TAG = "com.amazon.identity.auth.device.cw";
    private final AlarmManager iD;
    private final Context mContext;

    public cw() {
        this.iD = null;
        this.mContext = null;
    }

    public void a(long j, PendingIntentWrapper pendingIntentWrapper) {
        try {
            this.iD.set(1, j, pendingIntentWrapper != null ? pendingIntentWrapper.kA : null);
        } catch (SecurityException e) {
            io.e(TAG, "AlarmManagerWrapper set failed!", e);
        }
    }

    public void a(PendingIntentWrapper pendingIntentWrapper) {
        try {
            this.iD.cancel(pendingIntentWrapper != null ? pendingIntentWrapper.kA : null);
        } catch (SecurityException e) {
            io.e(TAG, "AlarmManagerWrapper cancel failed!", e);
        }
    }

    public cw(Context context) {
        this.iD = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        this.mContext = context;
    }
}
