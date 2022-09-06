package com.amazon.alexa.handsfree.notification.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.notification.services.EnableHandsFreeNotifierService;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public class EnableHandsFreeReceiver extends BroadcastReceiver {
    private static final String TAG = EnableHandsFreeReceiver.class.getSimpleName();

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        Log.d(TAG, "onReceive");
        EnableHandsFreeNotifierService.enqueueWork(context, new Intent());
    }
}
