package com.amazon.communication.support;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.amazon.communication.AndroidTCommService;
import com.amazon.communication.TCommRestartSwitch;
import com.amazon.communication.TCommService;
import com.amazon.dp.logger.DPLogger;
import com.amazon.identity.auth.device.api.MAPAccountManager;
/* loaded from: classes12.dex */
public class CsmPackageChangedBroadcastReceiver extends BroadcastReceiver {
    public static final String CSM_PACKAGE_NAME = "amazon.speech.sim";
    public static final String VIZZINI_PACKAGE_NAME = "com.amazon.vizzini";
    private static final DPLogger log = new DPLogger("TComm.CsmPackageChangedBroadcastReceiver");

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        log.info("onReceive", MAPAccountManager.KEY_INTENT, intent);
        Uri data = intent.getData();
        if (data == null) {
            return;
        }
        String schemeSpecificPart = data.getSchemeSpecificPart();
        if (!CSM_PACKAGE_NAME.equals(schemeSpecificPart) && !"com.amazon.vizzini".equals(schemeSpecificPart)) {
            log.info("onReceive", "Received an intent for an unexpected package", schemeSpecificPart);
        } else if ("android.intent.action.PACKAGE_ADDED".equals(intent.getAction())) {
            if (!(AndroidTCommService.mTCommService instanceof TCommService)) {
                return;
            }
            log.info("onReceive", "CSM was added to the device and the WS connection is active, restart", new Object[0]);
            TCommRestartSwitch.getInstance().restartTComm();
        } else {
            log.warn("onReceive", "Received an unexpected intent", intent);
        }
    }
}
