package com.amazon.communication.heartbeat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.communication.heartbeat.HeartbeatNotificationHandler;
import com.amazon.device.nos.NetworkOptimizationManager;
import com.amazon.dp.logger.DPLogger;
import com.amazon.identity.auth.device.api.MAPAccountManager;
/* loaded from: classes12.dex */
public class HeartbeatBroadcastReceiver extends BroadcastReceiver {
    private static final DPLogger log = new DPLogger("TComm.HeartbeatBroadcastReceiver");

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        log.info("onReceive", "received intent", intent);
        String action = intent == null ? null : intent.getAction();
        if (!NetworkOptimizationManager.ACTION_NOS_DATA_TRANSFER.equals(action)) {
            log.warn("onReceive", "Received unexpected intent", MAPAccountManager.KEY_INTENT, intent, "action", action);
            return;
        }
        int intExtra = intent.getIntExtra(NetworkOptimizationManager.EXTRA_RESULT_CODE, -1);
        int intExtra2 = intent.getIntExtra(NetworkOptimizationManager.EXTRA_DETAIL_CODE, -1);
        int intExtra3 = intent.getIntExtra(NetworkOptimizationManager.EXTRA_REGISTRATION_ID, -1);
        log.debug("onReceive", "Received NOS_DATA_TRANSFER", MAPAccountManager.KEY_INTENT, intent);
        HeartbeatNotificationHandler.HeartbeatNotificationAttribute heartbeatNotificationAttribute = HeartbeatNotificationHandler.HeartbeatNotificationAttribute.DEFAULT;
        if (intExtra == 2) {
            if ((intExtra2 & 32) == 32) {
                heartbeatNotificationAttribute = HeartbeatNotificationHandler.HeartbeatNotificationAttribute.ROAMING_ACTIVE;
            } else {
                heartbeatNotificationAttribute = HeartbeatNotificationHandler.HeartbeatNotificationAttribute.NO_CONNECTION_POSSIBLE;
            }
        }
        try {
            HeartbeatNotificationHandler heartbeatNotificationHandler = HeartbeatNotificationHandlerContainer.getInstance().getHeartbeatNotificationHandler();
            if (heartbeatNotificationHandler != null) {
                heartbeatNotificationHandler.onHeartbeatNotification(heartbeatNotificationAttribute, intExtra3);
            } else {
                log.warn("onReceive", "Cannot obtain reference to HeartbeatNotificationHandler. It may not have been initialized yet, ignoring intent.", new Object[0]);
            }
        } catch (Exception e) {
            log.error("onReceive", "Unhandled exception while attempting to notify handler.", e);
        }
    }
}
