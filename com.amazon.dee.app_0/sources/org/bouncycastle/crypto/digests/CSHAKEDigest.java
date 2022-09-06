package org.bouncycastle.crypto.digests;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class CSHAKEDigest extends SHAKEDigest {
    private static final byte[] padding = new byte[100];
    private final byte[] diff;

    public CSHAKEDigest(int i, byte[] bArr, byte[] bArr2) {
        super(i);
        if ((bArr == null || bArr.length == 0) && (bArr2 == null || bArr2.length == 0)) {
            this.diff = null;
            return;
        }
        this.diff = Arrays.concatenate(XofUtils.leftEncode(this.rate / 8), encodeString(bArr), encodeString(bArr2));
        diffPadAndAbsorb();
    }

    private void diffPadAndAbsorb() {
        int i = this.rate / 8;
        byte[] bArr = this.diff;
        absorb(bArr, 0, bArr.length);
        int length = this.diff.length % i;
        if (length != 0) {
            while (true) {
                i -= length;
                byte[] bArr2 = padding;
                if (i <= bArr2.length) {
                    absorb(bArr2, 0, i);
                    return;
                } else {
                    absorb(bArr2, 0, bArr2.length);
                    length = padding.length;
                }
            }
        }
    }

    private byte[] encodeString(byte[] bArr) {
        return (bArr == null || bArr.length == 0) ? XofUtils.leftEncode(0L) : Arrays.concatenate(XofUtils.leftEncode(bArr.length * 8), bArr);
    }

    @Override // org.bouncycastle.crypto.digests.SHAKEDigest, org.bouncycastle.crypto.Xof
    public int doOutput(byte[] bArr, int i, int i2) {
        if (this.diff != null) {
            if (!this.squeezing) {
                absorbBits(0, 2);
            }
            squeeze(bArr, i, i2 * 8);
            return i2;
        }
        return super.doOutput(bArr, i, i2);
    }

    @Override // org.bouncycastle.crypto.digests.SHAKEDigest, org.bouncycastle.crypto.digests.KeccakDigest, org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CSHAKE");
        outline107.append(this.fixedOutputLength);
        return outline107.toString();
    }

    @Override // org.bouncycastle.crypto.digests.KeccakDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        if (this.diff != null) {
            diffPadAndAbsorb();
        }
    }
}
