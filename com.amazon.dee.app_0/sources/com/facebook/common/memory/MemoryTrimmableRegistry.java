package com.facebook.common.memory;
/* loaded from: classes2.dex */
public interface MemoryTrimmableRegistry {
    void registerMemoryTrimmable(MemoryTrimmable trimmable);

    void unregisterMemoryTrimmable(MemoryTrimmable trimmable);
}
