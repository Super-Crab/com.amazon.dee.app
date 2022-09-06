package com.amazon.alexa.wakeword.speakerverification.mlis;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
/* loaded from: classes11.dex */
public class UtterancesUploadToMlisReceiver extends BroadcastReceiver {
    @VisibleForTesting
    void enqueuePermissionCheck(@NonNull Context context, @NonNull Intent intent) {
        UtterancesUploadToMlisJobIntentService.enqueueWork(context, intent);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        enqueuePermissionCheck(context, intent);
    }
}
