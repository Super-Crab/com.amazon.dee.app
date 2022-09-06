package org.apache.thrift.orig.protocol;
/* loaded from: classes4.dex */
public final class TSet {
    public final byte elemType;
    public final int size;

    public TSet() {
        this((byte) 0, 0);
    }

    public TSet(byte b, int i) {
        this.elemType = b;
        this.size = i;
    }

    public TSet(TList tList) {
        this(tList.elemType, tList.size);
    }
}
