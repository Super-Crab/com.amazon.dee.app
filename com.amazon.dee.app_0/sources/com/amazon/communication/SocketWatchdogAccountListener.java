package com.amazon.communication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.communication.authentication.MapAccountManagerWrapper;
import com.amazon.communication.websocket.CloseDetail;
import com.amazon.communication.websocket.CloseStatusCodes;
import com.amazon.dp.logger.DPLogger;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public class SocketWatchdogAccountListener extends BroadcastReceiver {
    private final MapAccountManagerWrapper mAccountManager;
    private final AtomicReference<Set<String>> mAmazonAccounts;
    protected final int mInstance = sInstanceCounter.getAndIncrement();
    private final AlwaysOnSocketWatchdog mSocketWatchdog;
    private static final DPLogger log = new DPLogger("TComm.SocketWatchdogAccountListener");
    private static final AtomicInteger sInstanceCounter = new AtomicInteger();
    protected static final CloseDetail ACCOUNT_CHANGE_DETAIL = new CloseDetail(CloseStatusCodes.ACCOUNT_CHANGE, "Amazon Account status change");

    public SocketWatchdogAccountListener(MapAccountManagerWrapper mapAccountManagerWrapper, AlwaysOnSocketWatchdog alwaysOnSocketWatchdog) {
        if (mapAccountManagerWrapper != null) {
            if (alwaysOnSocketWatchdog != null) {
                this.mAccountManager = mapAccountManagerWrapper;
                this.mSocketWatchdog = alwaysOnSocketWatchdog;
                this.mAmazonAccounts = new AtomicReference<>(mapAccountManagerWrapper.getAccounts());
                return;
            }
            throw new IllegalArgumentException("socketWatchdog must not be null");
        }
        throw new IllegalArgumentException("account manager must not be null");
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        boolean z;
        log.verbose("onReceive", "accounts updated", "instance", Integer.valueOf(this.mInstance), MAPAccountManager.KEY_INTENT, intent);
        if (intent == null) {
            return;
        }
        String[] strArr = TCommService.ACCOUNT_CHANGE_INTENTS;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            } else if (intent.getAction().equals(strArr[i])) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (z) {
            Set<String> accounts = this.mAccountManager.getAccounts();
            log.verbose("onReceive", "got accounts", "instance", Integer.valueOf(this.mInstance), "accounts", accounts);
            if (!this.mAmazonAccounts.getAndSet(accounts).equals(accounts)) {
                log.info("onReceive", "Change in amazon accounts set detected, tearing down connection", "instance", Integer.valueOf(this.mInstance));
                return;
            } else {
                log.verbose("onReceive", "No changes to amazon accounts detedcted, not tearing down connection", "instance", Integer.valueOf(this.mInstance));
                return;
            }
        }
        log.warn("onReceive", "received intent we didn't register for. possible security risk", "instance", Integer.valueOf(this.mInstance), MAPAccountManager.KEY_INTENT, intent);
    }
}
