package com.amazon.communication.heartbeat;

import android.content.ComponentName;
import android.content.Context;
import com.amazon.device.nos.NetworkOptimizationManager;
import com.amazon.device.nos.TransferCriteria;
import com.amazon.device.nos.TransferCriteriaBuilder;
import com.amazon.dp.logger.DPLogger;
/* loaded from: classes12.dex */
public class NosHeartbeatNotificationScheduler implements HeartbeatNotificationScheduler {
    private static final DPLogger log = new DPLogger("TComm.NosHeartbeatNotificationScheduler");
    private final ComponentName mComponentName;
    private final Context mContext;
    private final HeartbeatIntervalDeterminer mIntervalDeterminer;
    private NetworkOptimizationManager mNosManager;

    public NosHeartbeatNotificationScheduler(Context context, NetworkOptimizationManager networkOptimizationManager, HeartbeatIntervalDeterminer heartbeatIntervalDeterminer) {
        this(context, new ComponentName(context, HeartbeatBroadcastReceiver.class), networkOptimizationManager, heartbeatIntervalDeterminer);
    }

    private TransferCriteria generateTransferCriteria(int i, long j, long j2) {
        return new TransferCriteriaBuilder().setComponentName(this.mComponentName).setDataSizeKB(1L).setRegistrationId(i).setMinTransferDelayMillis(j).setMaxTransferDelayMillis(j2).setAllowWifi().setAllowMobile().build();
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatNotificationScheduler
    public void cancelScheduledNotification() {
        log.verbose("cancelScheduledNotification", "attempting to deregister ComponentName", "componentName", this.mComponentName);
        this.mNosManager.deregisterAll(this.mComponentName);
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatNotificationScheduler
    public boolean scheduleHeartbeatNotification() {
        return scheduleHeartbeatNotification(1, this.mIntervalDeterminer.getMinimumHeartbeatIntervalMillis(), this.mIntervalDeterminer.getMaximumHeartbeatIntervalMillis());
    }

    public void setNosManager(NetworkOptimizationManager networkOptimizationManager) {
        this.mNosManager = networkOptimizationManager;
    }

    protected NosHeartbeatNotificationScheduler(Context context, ComponentName componentName, NetworkOptimizationManager networkOptimizationManager, HeartbeatIntervalDeterminer heartbeatIntervalDeterminer) {
        log.verbose("protected constructor", "entering", new Object[0]);
        if (context != null) {
            if (networkOptimizationManager == null) {
                throw new IllegalArgumentException("NetworkOptimizationManager must not be null");
            }
            if (heartbeatIntervalDeterminer != null) {
                this.mContext = context;
                this.mComponentName = componentName;
                this.mNosManager = networkOptimizationManager;
                this.mIntervalDeterminer = heartbeatIntervalDeterminer;
                return;
            }
            throw new IllegalArgumentException("HeartbeatIntervalDeterminer must not be null");
        }
        throw new IllegalArgumentException("context must not be null");
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatNotificationScheduler
    public void cancelScheduledNotification(int i) {
        log.verbose("cancelScheduledNotification", "attempting to deregister ComponentName", "componentName", this.mComponentName, "registrationId", Integer.valueOf(i));
        this.mNosManager.deregister(this.mComponentName, i);
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatNotificationScheduler
    public boolean scheduleHeartbeatNotification(int i, long j, long j2) {
        log.verbose("scheduleHeartbeatNotification", "attempting to register with NetworkOptimizationManager", "nosManager", this.mNosManager, "registrationId", Integer.valueOf(i));
        this.mNosManager.register(generateTransferCriteria(i, j, j2));
        log.verbose("scheduleHeartbeatNotification", "registration successful", new Object[0]);
        return true;
    }
}
