package com.amazon.alexa.voice.handsfree.metrics.permission;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes11.dex */
public class PartnerPermissionCheckReceiver extends BroadcastReceiver {
    private static final String DSP_BROADCAST_EXTRA_KEY = "EXTRA_STATE";
    private static final String DSP_BROADCAST_EXTRA_VALUE = "MICROPHONE_PERMISSION_NEEDED";
    private static final String TAG = PartnerPermissionCheckReceiver.class.getSimpleName();

    @VisibleForTesting
    void enqueuePermissionCheck(@NonNull Context context, @NonNull Intent intent) {
        PermissionCheckJobIntentService.enqueueWork(context, intent);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        if (!intent.hasExtra("EXTRA_STATE") || !intent.getStringExtra("EXTRA_STATE").equals(DSP_BROADCAST_EXTRA_VALUE)) {
            return;
        }
        Log.i(TAG, String.format("received %s broadcast extra value", DSP_BROADCAST_EXTRA_VALUE));
        enqueuePermissionCheck(context, intent);
    }
}
