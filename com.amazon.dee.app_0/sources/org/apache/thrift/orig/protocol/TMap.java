package org.apache.thrift.orig.protocol;
/* loaded from: classes4.dex */
public final class TMap {
    public final byte keyType;
    public final int size;
    public final byte valueType;

    public TMap() {
        this((byte) 0, (byte) 0, 0);
    }

    public TMap(byte b, byte b2, int i) {
        this.keyType = b;
        this.valueType = b2;
        this.size = i;
    }
}
