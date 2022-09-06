package com.amazon.device.nos;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes12.dex */
public class GenericNetworkOptimizationManagerImpl implements NetworkOptimizationManager {
    private static final String TAG = "GenericNetworkOptimizationManagerImpl";
    private static NetworkOptimizationManager sNetworkOptimizationManager;
    protected final AlarmManager mAlarmManager;
    private final Context mContext;
    protected final Map<ComponentName, Set<Integer>> mRegistrations = new HashMap();

    /* JADX INFO: Access modifiers changed from: protected */
    public GenericNetworkOptimizationManagerImpl(Context context) {
        this.mContext = context;
        this.mAlarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
    }

    public static synchronized NetworkOptimizationManager getInstance(Context context) {
        NetworkOptimizationManager networkOptimizationManager;
        synchronized (GenericNetworkOptimizationManagerImpl.class) {
            if (context != null) {
                if (sNetworkOptimizationManager == null) {
                    sNetworkOptimizationManager = new GenericNetworkOptimizationManagerImpl(context);
                }
                networkOptimizationManager = sNetworkOptimizationManager;
            } else {
                throw new IllegalArgumentException("Context must not be null.");
            }
        }
        return networkOptimizationManager;
    }

    protected Intent createNosIntent(ComponentName componentName, int i) {
        Intent intent = new Intent(NetworkOptimizationManager.ACTION_NOS_DATA_TRANSFER);
        intent.setComponent(componentName);
        intent.setType(String.valueOf(i));
        intent.putExtra(NetworkOptimizationManager.EXTRA_REGISTRATION_ID, i);
        intent.putExtra(NetworkOptimizationManager.EXTRA_RESULT_CODE, 1);
        intent.putExtra(NetworkOptimizationManager.EXTRA_DETAIL_CODE, 1);
        return intent;
    }

    @Override // com.amazon.device.nos.NetworkOptimizationManager
    public synchronized void deregister(ComponentName componentName, int i) {
        if (componentName != null) {
            this.mAlarmManager.cancel(PendingIntent.getBroadcast(this.mContext, 0, createNosIntent(componentName, i), 0));
            Set<Integer> set = this.mRegistrations.get(componentName);
            if (set != null) {
                set.remove(Integer.valueOf(i));
            }
            String str = "Deregistered: " + componentName.toString() + ":" + i;
        } else {
            throw new IllegalArgumentException("ComponentName must not be null.");
        }
    }

    @Override // com.amazon.device.nos.NetworkOptimizationManager
    public synchronized void deregisterAll(ComponentName componentName) {
        if (componentName != null) {
            Set<Integer> set = this.mRegistrations.get(componentName);
            if (set != null) {
                for (Integer num : set) {
                    this.mAlarmManager.cancel(PendingIntent.getBroadcast(this.mContext, 0, createNosIntent(componentName, num.intValue()), 0));
                }
                this.mRegistrations.remove(componentName);
            }
            String str = "Deregistered all: " + componentName.toString();
        } else {
            throw new IllegalArgumentException("ComponentName must not be null.");
        }
    }

    @Override // com.amazon.device.nos.NetworkOptimizationManager
    public synchronized int register(TransferCriteria transferCriteria) {
        return register(transferCriteria, 2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized int register(TransferCriteria transferCriteria, int i) {
        if (transferCriteria != null) {
            if (this.mRegistrations.get(transferCriteria.getComponentName()) == null) {
                HashSet hashSet = new HashSet();
                hashSet.add(Integer.valueOf(transferCriteria.getRegistrationID()));
                this.mRegistrations.put(transferCriteria.getComponentName(), hashSet);
            } else {
                this.mRegistrations.get(transferCriteria.getComponentName()).add(Integer.valueOf(transferCriteria.getRegistrationID()));
            }
            PendingIntent broadcast = PendingIntent.getBroadcast(this.mContext, 0, createNosIntent(transferCriteria.getComponentName(), transferCriteria.getRegistrationID()), 0);
            if (!transferCriteria.isRepeatRegistration()) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (AlarmManagerMethods.isSetWindowAvailable()) {
                    long minTransferDelayMillis = transferCriteria.getMinTransferDelayMillis();
                    long maxTransferDelayMillis = transferCriteria.getMaxTransferDelayMillis() - minTransferDelayMillis;
                    Log.i(TAG, "Setting inexact alarm: delay=" + minTransferDelayMillis + ", length=" + maxTransferDelayMillis);
                    AlarmManagerMethods.setWindow(this.mAlarmManager, i, elapsedRealtime + minTransferDelayMillis, maxTransferDelayMillis, broadcast);
                } else {
                    long maxTransferDelayMillis2 = transferCriteria.getMaxTransferDelayMillis();
                    Log.i(TAG, String.format("Setting exact alarm: delay=" + maxTransferDelayMillis2, new Object[0]));
                    this.mAlarmManager.set(i, elapsedRealtime + maxTransferDelayMillis2, broadcast);
                }
            } else {
                this.mAlarmManager.setRepeating(i, SystemClock.elapsedRealtime() + transferCriteria.getMaxTransferDelayMillis(), transferCriteria.getRepeatIntervalMillis(), broadcast);
            }
            String str = "Registered: " + transferCriteria.toString();
        } else {
            throw new IllegalArgumentException("TransferCriteria must not be null.");
        }
        return transferCriteria.getRegistrationID();
    }
}
