package com.amazon.deecomms.remoteConfig;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.reactnativecommunity.netinfo.BroadcastReceiverConnectivityReceiver;
/* loaded from: classes12.dex */
public class ConfigurationSyncReceiver extends BroadcastReceiver {
    public static final String ACTION_RETRY_SYNC = "com.amazon.deecomms.receiver.ACTION_RETRY_SYNC";
    public static final String CONFIG_SYNC_ALARM_ACTION = "com.amazon.deecomms.receiver.CONFIG_SYNC_ACTION";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ConfigurationSyncReceiver.class);

    private void requestSync(Context context, int i) {
        CommsLogger commsLogger = LOG;
        commsLogger.i("Requesting sync. Attempt number " + i);
        Intent intent = new Intent(context, ConfigurationSyncService.class);
        intent.setAction(ConfigurationSyncService.ACTION_SYNC_CONFIG);
        intent.putExtra(ConfigurationSyncService.EXTRA_RETRY_ATTEMPT, i);
        ConfigurationSyncService.enqueueWork(context, intent);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("OnReceive - Alarm received in ConfigurationSyncReceiver- ");
        outline1.append(System.currentTimeMillis());
        outline1.append("Received event ");
        outline1.append(intent.getAction());
        commsLogger.i(outline1.toString());
        if (!CONFIG_SYNC_ALARM_ACTION.equals(intent.getAction()) && !"android.intent.action.BOOT_COMPLETED".equals(intent.getAction()) && !BroadcastReceiverConnectivityReceiver.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            if (ACTION_RETRY_SYNC.equals(intent.getAction())) {
                CommsLogger commsLogger2 = LOG;
                StringBuilder outline12 = GeneratedOutlineSupport.outline1("Requesting sync retry. Retry attempt number: ");
                outline12.append(intent.getIntExtra(ConfigurationSyncService.EXTRA_RETRY_ATTEMPT, 0));
                commsLogger2.i(outline12.toString());
                requestSync(context, intent.getIntExtra(ConfigurationSyncService.EXTRA_RETRY_ATTEMPT, 0));
                return;
            }
            CommsLogger commsLogger3 = LOG;
            StringBuilder outline13 = GeneratedOutlineSupport.outline1("Ignoring event ");
            outline13.append(intent.getAction());
            commsLogger3.i(outline13.toString());
            return;
        }
        requestSync(context, 0);
    }
}
