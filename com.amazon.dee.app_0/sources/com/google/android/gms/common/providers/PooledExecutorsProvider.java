package com.google.android.gms.common.providers;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.concurrent.ScheduledExecutorService;
@KeepForSdk
/* loaded from: classes2.dex */
public class PooledExecutorsProvider {
    private static PooledExecutorFactory zzey;

    /* loaded from: classes2.dex */
    public interface PooledExecutorFactory {
        @KeepForSdk
        ScheduledExecutorService newSingleThreadScheduledExecutor();
    }

    private PooledExecutorsProvider() {
    }

    @KeepForSdk
    public static synchronized PooledExecutorFactory getInstance() {
        PooledExecutorFactory pooledExecutorFactory;
        synchronized (PooledExecutorsProvider.class) {
            if (zzey == null) {
                zzey = new zza();
            }
            pooledExecutorFactory = zzey;
        }
        return pooledExecutorFactory;
    }
}
