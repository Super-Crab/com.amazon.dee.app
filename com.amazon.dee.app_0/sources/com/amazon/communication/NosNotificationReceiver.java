package com.amazon.communication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import com.amazon.device.nos.NetworkOptimizationManager;
import com.amazon.dp.logger.DPLogger;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* loaded from: classes12.dex */
public class NosNotificationReceiver extends BroadcastReceiver {
    private static final DPLogger log = new DPLogger("TComm.NosNotificationReceiver");
    private static final ConcurrentMap<Integer, Runnable> sExpectedNotifications = new ConcurrentHashMap();

    public static void associate(int i, Runnable runnable) {
        sExpectedNotifications.put(Integer.valueOf(i), runnable);
    }

    public static Runnable disassociate(int i) {
        return sExpectedNotifications.remove(Integer.valueOf(i));
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        PowerManager.WakeLock newWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(1, "TComm.NosNotificationReceiver");
        newWakeLock.acquire(PowerManagerWrapperImpl.WAKE_LOCK_TIMEOUT_MS);
        try {
            log.info("onReceive", "received intent", intent);
            String action = intent == null ? null : intent.getAction();
            if (!NetworkOptimizationManager.ACTION_NOS_DATA_TRANSFER.equals(action)) {
                log.warn("onReceive", "received unexpected intent", MAPAccountManager.KEY_INTENT, intent, "action", action);
                log.verbose("onReceive", "releasing wakeLock", "wakeLock", newWakeLock);
                newWakeLock.release();
                return;
            }
            int intExtra = intent.getIntExtra(NetworkOptimizationManager.EXTRA_REGISTRATION_ID, -1);
            Runnable disassociate = disassociate(intExtra);
            if (disassociate != null) {
                try {
                    log.debug("onReceive", "executing the associated task for registrationId", "registrationId", Integer.valueOf(intExtra));
                    disassociate.run();
                } catch (Exception e) {
                    log.error("onReceive", "unhandled exception while attempting to run associated task", "registrationId", Integer.valueOf(intExtra), e);
                }
            } else {
                log.warn("onReceive", "didn't find any associated task for registrationId", "registrationId", Integer.valueOf(intExtra));
            }
            log.verbose("onReceive", "releasing wakeLock", "wakeLock", newWakeLock);
            newWakeLock.release();
        } catch (Throwable th) {
            log.verbose("onReceive", "releasing wakeLock", "wakeLock", newWakeLock);
            newWakeLock.release();
            throw th;
        }
    }
}
