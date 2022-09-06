package com.amazon.alexa.presence.service.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.amazon.alexa.presence.service.PresenceForegroundService;
import com.amazon.alexa.presence.service.PresenceForegroundServiceStateAdviser;
import com.amazon.alexa.presence.service.PresenceJobService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes9.dex */
public class PresenceForegroundServiceReceiver extends BroadcastReceiver {
    private static final String ANDROID_BLUETOOTH_ADAPTER_ACTION_STATE_CHANGED = "android.bluetooth.adapter.action.STATE_CHANGED";
    private static final String ANDROID_INTENT_ACTION_ACTION_BOOT_COMPLETED = "android.intent.action.ACTION_BOOT_COMPLETED";
    private static final String ANDROID_INTENT_ACTION_ACTION_MY_PACKAGE_REPLACED = "android.intent.action.ACTION_MY_PACKAGE_REPLACED";
    private static final String TAG = PresenceForegroundServiceReceiver.class.getName();
    private static volatile AtomicBoolean isRegistered = new AtomicBoolean();

    public static void registerForContextChanges(PresenceForegroundServiceReceiver presenceForegroundServiceReceiver, Context context) {
        if (isRegistered.compareAndSet(false, true)) {
            Context applicationContext = context.getApplicationContext();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(ANDROID_INTENT_ACTION_ACTION_MY_PACKAGE_REPLACED);
            intentFilter.addAction(ANDROID_INTENT_ACTION_ACTION_BOOT_COMPLETED);
            intentFilter.addAction(ANDROID_BLUETOOTH_ADAPTER_ACTION_STATE_CHANGED);
            applicationContext.registerReceiver(presenceForegroundServiceReceiver, intentFilter);
        }
    }

    public static void registerReceivers(Context context) {
        registerForContextChanges(new PresenceForegroundServiceReceiver(), context);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Received intent.  Action: ");
        outline107.append(intent.getAction());
        Log.i(str, outline107.toString());
        try {
            if (!new PresenceForegroundServiceStateAdviser(context).isBleConnV2Enabled()) {
                return;
            }
            registerForContextChanges(this, context);
            PresenceForegroundServiceStateAdviser presenceForegroundServiceStateAdviser = new PresenceForegroundServiceStateAdviser(context);
            PresenceJobService.Helper helper = new PresenceJobService.Helper(context);
            if (presenceForegroundServiceStateAdviser.serviceShouldBeRunning(intent)) {
                if (intent.getAction().equalsIgnoreCase(ANDROID_INTENT_ACTION_ACTION_BOOT_COMPLETED)) {
                    new PresenceForegroundService.Controls(context).notifyPresenceServiceToRun();
                } else {
                    helper.scheduleForegroundServiceStart();
                }
            } else {
                helper.scheduleForegroundServiceStop();
            }
        } catch (Throwable th) {
            Log.w(TAG, "Unrecoverable situation encountered while handling intent", th);
            String str2 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Intent action causing exception: ");
            outline1072.append(intent.getAction());
            Log.w(str2, outline1072.toString());
        }
    }
}
