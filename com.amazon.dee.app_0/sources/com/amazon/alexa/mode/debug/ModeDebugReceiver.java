package com.amazon.alexa.mode.debug;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
/* loaded from: classes9.dex */
public class ModeDebugReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (!EmulateConnection.ACTION_DEBUG.equals(intent.getAction()) || !intent.hasExtra(EmulateConnection.EXTRA_CONNECT)) {
            return;
        }
        Intent intent2 = new Intent(EmulateConnection.ACTION_DEBUG);
        intent2.putExtra(EmulateConnection.EXTRA_CONNECT, intent.getBooleanExtra(EmulateConnection.EXTRA_CONNECT, false));
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent2);
    }
}
