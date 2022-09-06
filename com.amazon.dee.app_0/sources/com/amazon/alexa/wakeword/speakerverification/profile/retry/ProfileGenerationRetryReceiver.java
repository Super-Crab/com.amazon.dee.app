package com.amazon.alexa.wakeword.speakerverification.profile.retry;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
/* loaded from: classes11.dex */
public class ProfileGenerationRetryReceiver extends BroadcastReceiver {
    @VisibleForTesting
    void enqueuePermissionCheck(@NonNull Context context, @NonNull Intent intent) {
        ProfileGenerationRetryJobIntentService.enqueueWork(context, intent);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        enqueuePermissionCheck(context, intent);
    }
}
