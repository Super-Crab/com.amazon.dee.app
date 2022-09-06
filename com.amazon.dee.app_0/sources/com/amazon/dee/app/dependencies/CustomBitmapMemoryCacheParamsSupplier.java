package com.amazon.dee.app.dependencies;

import android.app.ActivityManager;
import android.content.Context;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.facebook.common.internal.Supplier;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
/* loaded from: classes12.dex */
public class CustomBitmapMemoryCacheParamsSupplier implements Supplier<MemoryCacheParams> {
    private static final int CACHE_DIVISION = 8;
    private static final int MAX_CACHE_ENTRIES = 256;
    private static final int MAX_CACHE_ENTRY_SIZE = Integer.MAX_VALUE;
    private static final int MAX_EVICTION_QUEUE_ENTRIES = Integer.MAX_VALUE;
    private static final int MAX_EVICTION_QUEUE_SIZE = Integer.MAX_VALUE;
    private final ActivityManager mActivityManager;

    public CustomBitmapMemoryCacheParamsSupplier(Context context) {
        this.mActivityManager = (ActivityManager) context.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
    }

    private int getMaxCacheSize() {
        int min = Math.min(this.mActivityManager.getMemoryClass() * 1048576, Integer.MAX_VALUE);
        if (min < 33554432) {
            return 4194304;
        }
        if (min >= 67108864) {
            return min / 8;
        }
        return 6291456;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.facebook.common.internal.Supplier
    /* renamed from: get */
    public MemoryCacheParams mo6895get() {
        return new MemoryCacheParams(getMaxCacheSize(), 256, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
}
