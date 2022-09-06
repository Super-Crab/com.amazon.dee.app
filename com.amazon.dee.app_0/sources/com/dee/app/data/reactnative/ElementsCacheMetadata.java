package com.dee.app.data.reactnative;
/* loaded from: classes2.dex */
public class ElementsCacheMetadata {
    public long timestamp;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && ElementsCacheMetadata.class == obj.getClass() && this.timestamp == ((ElementsCacheMetadata) obj).timestamp;
    }

    public int hashCode() {
        return Long.valueOf(this.timestamp).hashCode();
    }
}
