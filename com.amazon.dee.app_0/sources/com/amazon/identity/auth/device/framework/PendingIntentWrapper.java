package com.amazon.identity.auth.device.framework;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class PendingIntentWrapper {
    public final Intent intent;
    public final PendingIntent kA;
    public final PendingIntentType kB;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum PendingIntentType {
        Broadcast,
        Service,
        Activity
    }

    private PendingIntentWrapper(PendingIntent pendingIntent, PendingIntentType pendingIntentType, Intent intent) {
        this.kA = pendingIntent;
        this.kB = pendingIntentType;
        this.intent = intent;
    }

    public static PendingIntentWrapper b(Context context, Intent intent) {
        PendingIntent service = PendingIntent.getService(context, 0, intent, 1073741824);
        PendingIntentType pendingIntentType = PendingIntentType.Service;
        if (service == null) {
            return null;
        }
        return new PendingIntentWrapper(service, pendingIntentType, intent);
    }
}
