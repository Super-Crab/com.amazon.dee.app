package com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire;
/* loaded from: classes13.dex */
public class MultiByteInteger {
    private int length;
    private long value;

    public MultiByteInteger(long j) {
        this(j, -1);
    }

    public int getEncodedLength() {
        return this.length;
    }

    public long getValue() {
        return this.value;
    }

    public MultiByteInteger(long j, int i) {
        this.value = j;
        this.length = i;
    }
}
