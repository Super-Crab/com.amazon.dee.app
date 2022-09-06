package com.amazon.communication.remotesetting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class StageSwitchReceiver extends BroadcastReceiver {
    public static final String ACTION_EXTRA_STAGE = "com.amazon.device.environment.extra.STAGE";
    public static final String ACTION_SWITCH_STAGE = "com.amazon.device.environment.action.SWITCH_STAGE";
    public static final String EXTRA_DESTINATION = "com.amazon.device.environment.extra.DESTINATION";
    private static final DPLogger log = new DPLogger("TComm.StageSwitchReceiver");

    private void requestStageSwitch(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("com.amazon.device.environment.extra.STAGE");
        if (context != null && stringExtra != null) {
            Intent intent2 = new Intent(context.getApplicationContext(), StageSwitchService.class);
            if (StageSwitchService.isValidStage(stringExtra)) {
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    for (String str : extras.keySet()) {
                        intent2.putExtra(str, intent.getStringExtra(str));
                    }
                }
                log.info("requestStageSwitch", "Requesting stage switch to", "stage", stringExtra);
                context.startService(intent2);
                return;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid stage: ", stringExtra));
        }
        throw new IllegalArgumentException("The arguments must not be null");
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        log.info("onReceive", "Received broadcast event", "action", intent.getAction());
        if ("com.amazon.device.environment.action.SWITCH_STAGE".equals(intent.getAction())) {
            try {
                requestStageSwitch(context, intent);
                return;
            } catch (IllegalArgumentException e) {
                log.warn("onReceive", "Unknown stage, ignoring event", "stage", intent.getStringExtra("com.amazon.device.environment.extra.STAGE"), "message", e.getMessage());
                return;
            }
        }
        log.warn("onReceive", "Unknown event, ignoring event", "action", intent.getAction());
    }
}
