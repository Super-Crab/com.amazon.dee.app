package com.amazon.communication.remotesetting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.communication.support.JobIntentService;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.reactnativecommunity.netinfo.BroadcastReceiverConnectivityReceiver;
/* loaded from: classes12.dex */
public class ConfigurationSyncReceiver extends BroadcastReceiver {
    public static final String ACTION_PERIODIC_SYNC = "com.amazon.communication.remotesetting.action.ACTION_PERIODIC_SYNC";
    public static final String ACTION_RETRY_SYNC = "com.amazon.communication.remotesetting.action.ACTION_RETRY_SYNC";
    public static final String EXTRA_RETRY_ATTEMPT = "com.amazon.communication.remotesetting.extra.SYNC_RETRY_ATTEMPT";
    public static final int JOB_ID = 68439;
    private static final DPLogger log = new DPLogger("TComm.ConfigurationSyncReceiver");

    private void requestSync(Context context, int i) {
        log.info("requestSync", "Requesting sync.", new Object[0]);
        Intent intent = new Intent(ConfigurationSyncService.ACTION_SYNC_CONFIG);
        intent.setClass(context, ConfigurationSyncService.class);
        intent.putExtra("com.amazon.communication.remotesetting.extra.SYNC_RETRY_ATTEMPT", i);
        JobIntentService.enqueueWork(context, intent.getComponent(), (int) JOB_ID, intent);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        DPLogger dPLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Received event ");
        outline107.append(intent.getAction());
        dPLogger.debug("onReceive", outline107.toString(), new Object[0]);
        if (!ACTION_PERIODIC_SYNC.equals(intent.getAction()) && !"android.intent.action.BOOT_COMPLETED".equals(intent.getAction()) && !BroadcastReceiverConnectivityReceiver.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            if (ACTION_RETRY_SYNC.equals(intent.getAction())) {
                log.info("onReceive", "Requesting sync retry.", new Object[0]);
                requestSync(context, intent.getIntExtra("com.amazon.communication.remotesetting.extra.SYNC_RETRY_ATTEMPT", 0));
                return;
            }
            DPLogger dPLogger2 = log;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Ignoring event ");
            outline1072.append(intent.getAction());
            dPLogger2.warn("onReceive", outline1072.toString(), new Object[0]);
            return;
        }
        requestSync(context, 0);
    }
}
