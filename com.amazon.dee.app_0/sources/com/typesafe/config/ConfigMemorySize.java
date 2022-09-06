package com.typesafe.config;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes3.dex */
public final class ConfigMemorySize {
    private final long bytes;

    private ConfigMemorySize(long j) {
        if (j >= 0) {
            this.bytes = j;
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline56("Attempt to construct ConfigMemorySize with negative number: ", j));
    }

    public static ConfigMemorySize ofBytes(long j) {
        return new ConfigMemorySize(j);
    }

    public boolean equals(Object obj) {
        return (obj instanceof ConfigMemorySize) && ((ConfigMemorySize) obj).bytes == this.bytes;
    }

    public int hashCode() {
        return Long.valueOf(this.bytes).hashCode();
    }

    public long toBytes() {
        return this.bytes;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline87(GeneratedOutlineSupport1.outline107("ConfigMemorySize("), this.bytes, ")");
    }
}
