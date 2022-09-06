package com.amazon.appmanager.lib;

import android.content.Context;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.maft.metrics.MetricsFactory;
/* loaded from: classes11.dex */
public abstract class PreloadManager {
    private static PreloadManager defaultPreloadManager;

    public static synchronized PreloadManager getDefault() {
        PreloadManager preloadManager;
        synchronized (PreloadManager.class) {
            if (defaultPreloadManager == null) {
                defaultPreloadManager = new DefaultPreloadManager();
            }
            preloadManager = defaultPreloadManager;
        }
        return preloadManager;
    }

    @Nullable
    public abstract String getPreloadAssociateTag(@NonNull Context context, @NonNull MetricsFactory metricsFactory, @NonNull String str) throws RemoteException, UnrecognizedMarketplaceException, IllegalArgumentException, SecurityException;

    @Nullable
    public abstract String getPreloadAssociateTagWithoutRegionId(@NonNull Context context, @NonNull MetricsFactory metricsFactory) throws RemoteException, IllegalArgumentException, SecurityException;
}
