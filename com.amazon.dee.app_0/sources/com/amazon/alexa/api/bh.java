package com.amazon.alexa.api;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
/* loaded from: classes6.dex */
class bh {

    /* loaded from: classes6.dex */
    private enum a {
        ALEXA_LEADER_IS_MISSED,
        AUTHORIZATION_IS_MISSED,
        UNKNOWN
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Intent a(Context context) {
        return a(context, a.ALEXA_LEADER_IS_MISSED);
    }

    private static Intent a(Context context, a aVar) {
        return new Intent().setComponent(new ComponentName(context.getApplicationContext(), "com.amazon.alexa.handsfree.notification.receivers.UtteranceReceiver")).setAction("com.amazon.alexa.handsfree.notification.REPORT_ALEXA_CONNECTION_FAILURE").putExtra("CONNECTION_FAILURE_REASON", aVar.name());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Intent b(Context context) {
        return a(context, a.AUTHORIZATION_IS_MISSED);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Intent c(Context context) {
        return a(context, a.UNKNOWN);
    }
}
