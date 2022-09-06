package com.amazon.deecomms.common.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.util.CallUtils;
/* loaded from: classes12.dex */
public class UserSwitchReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        boolean equals = intent.getAction().equals("android.intent.action.USER_BACKGROUND");
        boolean equals2 = intent.getAction().equals("android.intent.action.USER_FOREGROUND");
        int i = intent.getExtras().getInt("android.intent.extra.user_handle");
        if (equals && i == 0) {
            CallUtils.disableIncomingCalls.set(true);
        } else if (!equals2 || i != 0) {
        } else {
            CallUtils.disableIncomingCalls.set(false);
        }
    }
}
