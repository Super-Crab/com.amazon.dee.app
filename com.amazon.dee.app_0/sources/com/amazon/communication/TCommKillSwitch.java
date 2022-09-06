package com.amazon.communication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Process;
import com.amazon.dp.logger.DPLogger;
import com.amazon.identity.auth.device.api.MAPAccountManager;
/* loaded from: classes12.dex */
public class TCommKillSwitch {
    public static final String KILL_ACTION = "com.amazon.tcomm.SUICIDE";
    public static final String KILL_PERMISSION = "amazon.permission.KILL_TCOMM";
    private static final DPLogger log = new DPLogger("TCommKillSwitch");
    private final Context mContext;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.amazon.communication.TCommKillSwitch.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            TCommKillSwitch.log.warn("onReceive", "committing suicide", MAPAccountManager.KEY_INTENT, intent);
            Process.killProcess(Process.myPid());
        }
    };

    public TCommKillSwitch(Context context) {
        this.mContext = context;
        context.registerReceiver(this.mReceiver, new IntentFilter(KILL_ACTION), KILL_PERMISSION, null);
    }

    public void shutdown() {
        this.mContext.unregisterReceiver(this.mReceiver);
    }
}
