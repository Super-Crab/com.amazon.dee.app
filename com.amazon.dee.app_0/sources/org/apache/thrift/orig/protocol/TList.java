package org.apache.thrift.orig.protocol;
/* loaded from: classes4.dex */
public final class TList {
    public final byte elemType;
    public final int size;

    public TList() {
        this((byte) 0, 0);
    }

    public TList(byte b, int i) {
        this.elemType = b;
        this.size = i;
    }
}
