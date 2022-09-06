package com.facebook.common.disk;
/* loaded from: classes2.dex */
public interface DiskTrimmableRegistry {
    void registerDiskTrimmable(DiskTrimmable trimmable);

    void unregisterDiskTrimmable(DiskTrimmable trimmable);
}
