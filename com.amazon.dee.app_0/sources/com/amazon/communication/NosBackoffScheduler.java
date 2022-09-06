package com.amazon.communication;

import android.content.ComponentName;
import android.content.Context;
import com.amazon.device.nos.NetworkOptimizationManager;
import com.amazon.device.nos.TransferCriteria;
import com.amazon.device.nos.TransferCriteriaBuilder;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public class NosBackoffScheduler implements BackoffScheduler {
    public static final int SMALL_DATA_SIZE_KB = 5;
    private static final DPLogger log = new DPLogger("TComm.NosBackoffScheduler");
    private final ComponentName mComponentName;
    private NetworkOptimizationManager mNosManager;

    public NosBackoffScheduler(Context context, NetworkOptimizationManager networkOptimizationManager) {
        this.mComponentName = new ComponentName(context, NosNotificationReceiver.class);
        this.mNosManager = networkOptimizationManager;
    }

    private TransferCriteria generateTransferCriteria(int i, long j, int i2) {
        return new TransferCriteriaBuilder().setComponentName(this.mComponentName).setDataSizeKB(i2).setRegistrationId(i).setMinTransferDelayMillis(j).setMaxTransferDelayMillis(j).setAllowWifi().setAllowMobile().setAllowRoaming().build();
    }

    @Override // com.amazon.communication.BackoffScheduler
    public void cancel(int i) {
        log.debug("schedule", "Canceling alarm via NosManager", "RegistrationId", Integer.valueOf(i));
        this.mNosManager.deregister(this.mComponentName, i);
        NosNotificationReceiver.disassociate(i);
    }

    @Override // com.amazon.communication.BackoffScheduler
    public long getMinimumDelayMillis() {
        return 5000L;
    }

    @Override // com.amazon.communication.BackoffScheduler
    public void schedule(int i, Runnable runnable, long j, TimeUnit timeUnit) {
        if (runnable != null) {
            if (timeUnit != null) {
                if (timeUnit.toMillis(j) >= getMinimumDelayMillis()) {
                    log.debug("schedule", "associating the passed in task with registrationId", "registrationId", Integer.valueOf(i));
                    NosNotificationReceiver.associate(i, runnable);
                    this.mNosManager.register(generateTransferCriteria(i, timeUnit.toMillis(j), 5));
                    return;
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("delay (in ms): (");
                outline107.append(timeUnit.toMillis(j));
                outline107.append(") must be at least this long: ");
                outline107.append(getMinimumDelayMillis());
                throw new IllegalArgumentException(outline107.toString());
            }
            throw new IllegalArgumentException("unit must not be null");
        }
        throw new IllegalArgumentException("task must not be null");
    }

    public void setNosManager(NetworkOptimizationManager networkOptimizationManager) {
        this.mNosManager = networkOptimizationManager;
    }
}
