package com.amazon.alexa.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/* loaded from: classes9.dex */
public class RebootBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = RebootBroadcastReceiver.class.getSimpleName();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        GeofenceRestoreJobIntentService.schedule(context);
    }
}
