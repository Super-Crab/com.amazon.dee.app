package com.facebook.common.disk;

import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class NoOpDiskTrimmableRegistry implements DiskTrimmableRegistry {
    @Nullable
    private static NoOpDiskTrimmableRegistry sInstance;

    private NoOpDiskTrimmableRegistry() {
    }

    public static synchronized NoOpDiskTrimmableRegistry getInstance() {
        NoOpDiskTrimmableRegistry noOpDiskTrimmableRegistry;
        synchronized (NoOpDiskTrimmableRegistry.class) {
            if (sInstance == null) {
                sInstance = new NoOpDiskTrimmableRegistry();
            }
            noOpDiskTrimmableRegistry = sInstance;
        }
        return noOpDiskTrimmableRegistry;
    }

    @Override // com.facebook.common.disk.DiskTrimmableRegistry
    public void registerDiskTrimmable(DiskTrimmable trimmable) {
    }

    @Override // com.facebook.common.disk.DiskTrimmableRegistry
    public void unregisterDiskTrimmable(DiskTrimmable trimmable) {
    }
}
