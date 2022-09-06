package com.amazon.device.nos;

import android.content.Context;
import android.util.Log;
/* loaded from: classes12.dex */
public class NoWakeUpNetworkOptimizationManagerImpl extends GenericNetworkOptimizationManagerImpl {
    private static final String TAG = "NoWakeUpNetworkOptimizationManagerImpl";
    private static NetworkOptimizationManager sNetworkOptimizationManager;

    protected NoWakeUpNetworkOptimizationManagerImpl(Context context) {
        super(context);
    }

    public static synchronized NetworkOptimizationManager getInstance(Context context) {
        NetworkOptimizationManager networkOptimizationManager;
        synchronized (NoWakeUpNetworkOptimizationManagerImpl.class) {
            if (context != null) {
                if (sNetworkOptimizationManager == null) {
                    sNetworkOptimizationManager = new NoWakeUpNetworkOptimizationManagerImpl(context);
                }
                networkOptimizationManager = sNetworkOptimizationManager;
            } else {
                throw new IllegalArgumentException("Context must not be null.");
            }
        }
        return networkOptimizationManager;
    }

    @Override // com.amazon.device.nos.GenericNetworkOptimizationManagerImpl, com.amazon.device.nos.NetworkOptimizationManager
    public synchronized int register(TransferCriteria transferCriteria) {
        Log.i(TAG, "register non-wake up alarm");
        return super.register(transferCriteria, 3);
    }
}
