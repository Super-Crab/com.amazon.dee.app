package org.apache.thrift.orig.transport;
/* loaded from: classes4.dex */
public class AutoExpandingBuffer {
    private byte[] array;
    private final double growthCoefficient;

    public AutoExpandingBuffer(int i, double d) {
        if (d >= 1.0d) {
            this.array = new byte[i];
            this.growthCoefficient = d;
            return;
        }
        throw new IllegalArgumentException("Growth coefficient must be >= 1.0");
    }

    public byte[] array() {
        return this.array;
    }

    public void resizeIfNecessary(int i) {
        byte[] bArr = this.array;
        if (bArr.length < i) {
            byte[] bArr2 = new byte[(int) (i * this.growthCoefficient)];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            this.array = bArr2;
        }
    }
}
