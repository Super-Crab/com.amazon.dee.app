package org.bouncycastle.bcpg.sig;

import org.bouncycastle.bcpg.SignatureSubpacket;
/* loaded from: classes4.dex */
public class Features extends SignatureSubpacket {
    public static final byte FEATURE_MODIFICATION_DETECTION = 1;

    public Features(boolean z, byte b) {
        super(30, z, false, featureToByteArray(b));
    }

    public Features(boolean z, boolean z2, byte[] bArr) {
        super(30, z, z2, bArr);
    }

    private static final byte[] featureToByteArray(byte b) {
        return new byte[]{b};
    }

    private void setSupportsFeature(byte b, boolean z) {
        if (b != 0) {
            if (supportsFeature(b) == z) {
                return;
            }
            if (z) {
                byte[] bArr = this.data;
                byte[] bArr2 = new byte[bArr.length + 1];
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                bArr2[this.data.length] = b;
                this.data = bArr2;
                return;
            }
            int i = 0;
            while (true) {
                byte[] bArr3 = this.data;
                if (i >= bArr3.length) {
                    return;
                }
                if (bArr3[i] == b) {
                    byte[] bArr4 = new byte[bArr3.length - 1];
                    System.arraycopy(bArr3, 0, bArr4, 0, i);
                    System.arraycopy(this.data, i + 1, bArr4, i, bArr4.length - i);
                    this.data = bArr4;
                    return;
                }
                i++;
            }
        } else {
            throw new IllegalArgumentException("feature == 0");
        }
    }

    public boolean supportsFeature(byte b) {
        int i = 0;
        while (true) {
            byte[] bArr = this.data;
            if (i < bArr.length) {
                if (bArr[i] == b) {
                    return true;
                }
                i++;
            } else {
                return false;
            }
        }
    }

    public boolean supportsModificationDetection() {
        return supportsFeature((byte) 1);
    }
}
