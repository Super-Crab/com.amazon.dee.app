package com.amazon.alexa.presence.service.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.amazon.alexa.presence.service.PresenceSuggestGuestConnect;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes9.dex */
public class PresenceSuggestOnCancelReceiver extends BroadcastReceiver {
    private static final String TAG = PresenceSuggestOnCancelReceiver.class.getName();
    private static volatile AtomicBoolean isRegistered = new AtomicBoolean();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Received intent.  Action: ");
        outline107.append(intent.getAction());
        Log.i(str, outline107.toString());
        Log.i(TAG, "User dismissed Guest Connect notification");
        PresenceSuggestGuestConnect presenceSuggestGuestConnect = new PresenceSuggestGuestConnect(context);
        try {
            registerForContextChanges(this, context);
            long time = new Date().getTime();
            presenceSuggestGuestConnect.updateStorage(true, intent.getLongExtra(PresenceSuggestGuestConnect.DATE_SENT, time), time);
        } catch (Throwable th) {
            Log.w(TAG, "Unrecoverable situation encountered while handling intent", th);
            String str2 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Intent action causing exception: ");
            outline1072.append(intent.getAction());
            Log.w(str2, outline1072.toString());
        }
    }

    public void registerForContextChanges(PresenceSuggestOnCancelReceiver presenceSuggestOnCancelReceiver, Context context) {
        if (isRegistered.compareAndSet(false, true)) {
            Context applicationContext = context.getApplicationContext();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(PresenceSuggestGuestConnect.DISMISS_PRESENCE_BASED_SUGGESTION);
            applicationContext.registerReceiver(presenceSuggestOnCancelReceiver, intentFilter);
        }
    }
}
