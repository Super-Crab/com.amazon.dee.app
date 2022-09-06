package org.bouncycastle.pqc.crypto.gmss;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes5.dex */
public class GMSSLeaf {
    private byte[] concHashs;
    private GMSSRandom gmssRandom;
    private int i;
    private int j;
    private int keysize;
    private byte[] leaf;
    private int mdsize;
    private Digest messDigestOTS;
    byte[] privateKeyOTS;
    private byte[] seed;
    private int steps;
    private int two_power_w;
    private int w;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GMSSLeaf(Digest digest, int i, int i2) {
        int ceil;
        int i3;
        this.w = i;
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(this.messDigestOTS);
        this.mdsize = this.messDigestOTS.getDigestSize();
        double d = i;
        this.keysize = ((int) Math.ceil((this.mdsize << 3) / d)) + ((int) Math.ceil(getLog((ceil << i) + 1) / d));
        this.two_power_w = 1 << i;
        int i4 = this.keysize;
        this.steps = (int) Math.ceil(GeneratedOutlineSupport1.outline3(i3 - 1, i4, 1, i4) / i2);
        int i5 = this.mdsize;
        this.seed = new byte[i5];
        this.leaf = new byte[i5];
        this.privateKeyOTS = new byte[i5];
        this.concHashs = new byte[i5 * this.keysize];
    }

    public GMSSLeaf(Digest digest, int i, int i2, byte[] bArr) {
        int ceil;
        int i3;
        this.w = i;
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(this.messDigestOTS);
        this.mdsize = this.messDigestOTS.getDigestSize();
        double d = i;
        this.keysize = ((int) Math.ceil((this.mdsize << 3) / d)) + ((int) Math.ceil(getLog((ceil << i) + 1) / d));
        this.two_power_w = 1 << i;
        int i4 = this.keysize;
        this.steps = (int) Math.ceil(GeneratedOutlineSupport1.outline3(i3 - 1, i4, 1, i4) / i2);
        int i5 = this.mdsize;
        this.seed = new byte[i5];
        this.leaf = new byte[i5];
        this.privateKeyOTS = new byte[i5];
        this.concHashs = new byte[i5 * this.keysize];
        initLeafCalc(bArr);
    }

    public GMSSLeaf(Digest digest, byte[][] bArr, int[] iArr) {
        this.i = iArr[0];
        this.j = iArr[1];
        this.steps = iArr[2];
        this.w = iArr[3];
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(this.messDigestOTS);
        this.mdsize = this.messDigestOTS.getDigestSize();
        int ceil = (int) Math.ceil((this.mdsize << 3) / this.w);
        this.keysize = ceil + ((int) Math.ceil(getLog((ceil << this.w) + 1) / this.w));
        this.two_power_w = 1 << this.w;
        this.privateKeyOTS = bArr[0];
        this.seed = bArr[1];
        this.concHashs = bArr[2];
        this.leaf = bArr[3];
    }

    private GMSSLeaf(GMSSLeaf gMSSLeaf) {
        this.messDigestOTS = gMSSLeaf.messDigestOTS;
        this.mdsize = gMSSLeaf.mdsize;
        this.keysize = gMSSLeaf.keysize;
        this.gmssRandom = gMSSLeaf.gmssRandom;
        this.leaf = Arrays.clone(gMSSLeaf.leaf);
        this.concHashs = Arrays.clone(gMSSLeaf.concHashs);
        this.i = gMSSLeaf.i;
        this.j = gMSSLeaf.j;
        this.two_power_w = gMSSLeaf.two_power_w;
        this.w = gMSSLeaf.w;
        this.steps = gMSSLeaf.steps;
        this.seed = Arrays.clone(gMSSLeaf.seed);
        this.privateKeyOTS = Arrays.clone(gMSSLeaf.privateKeyOTS);
    }

    private int getLog(int i) {
        int i2 = 1;
        int i3 = 2;
        while (i3 < i) {
            i3 <<= 1;
            i2++;
        }
        return i2;
    }

    private void updateLeafCalc() {
        byte[] bArr = new byte[this.messDigestOTS.getDigestSize()];
        for (int i = 0; i < this.steps + 10000; i++) {
            if (this.i == this.keysize && this.j == this.two_power_w - 1) {
                Digest digest = this.messDigestOTS;
                byte[] bArr2 = this.concHashs;
                digest.update(bArr2, 0, bArr2.length);
                this.leaf = new byte[this.messDigestOTS.getDigestSize()];
                this.messDigestOTS.doFinal(this.leaf, 0);
                return;
            }
            if (this.i == 0 || this.j == this.two_power_w - 1) {
                this.i++;
                this.j = 0;
                this.privateKeyOTS = this.gmssRandom.nextSeed(this.seed);
            } else {
                Digest digest2 = this.messDigestOTS;
                byte[] bArr3 = this.privateKeyOTS;
                digest2.update(bArr3, 0, bArr3.length);
                this.privateKeyOTS = bArr;
                this.messDigestOTS.doFinal(this.privateKeyOTS, 0);
                this.j++;
                if (this.j == this.two_power_w - 1) {
                    byte[] bArr4 = this.privateKeyOTS;
                    byte[] bArr5 = this.concHashs;
                    int i2 = this.mdsize;
                    System.arraycopy(bArr4, 0, bArr5, (this.i - 1) * i2, i2);
                }
            }
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unable to updateLeaf in steps: ");
        outline107.append(this.steps);
        outline107.append(" ");
        outline107.append(this.i);
        outline107.append(" ");
        outline107.append(this.j);
        throw new IllegalStateException(outline107.toString());
    }

    public byte[] getLeaf() {
        return Arrays.clone(this.leaf);
    }

    public byte[][] getStatByte() {
        return new byte[][]{this.privateKeyOTS, this.seed, this.concHashs, this.leaf};
    }

    public int[] getStatInt() {
        return new int[]{this.i, this.j, this.steps, this.w};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initLeafCalc(byte[] bArr) {
        this.i = 0;
        this.j = 0;
        byte[] bArr2 = new byte[this.mdsize];
        System.arraycopy(bArr, 0, bArr2, 0, this.seed.length);
        this.seed = this.gmssRandom.nextSeed(bArr2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GMSSLeaf nextLeaf() {
        GMSSLeaf gMSSLeaf = new GMSSLeaf(this);
        gMSSLeaf.updateLeafCalc();
        return gMSSLeaf;
    }

    public String toString() {
        StringBuilder outline113;
        String str = "";
        for (int i = 0; i < 4; i++) {
            str = GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107(str), getStatInt()[i], " ");
        }
        StringBuilder outline1132 = GeneratedOutlineSupport1.outline113(str, " ");
        outline1132.append(this.mdsize);
        outline1132.append(" ");
        outline1132.append(this.keysize);
        outline1132.append(" ");
        String outline86 = GeneratedOutlineSupport1.outline86(outline1132, this.two_power_w, " ");
        byte[][] statByte = getStatByte();
        for (int i2 = 0; i2 < 4; i2++) {
            if (statByte[i2] != null) {
                outline113 = GeneratedOutlineSupport1.outline107(outline86);
                outline113.append(new String(Hex.encode(statByte[i2])));
                outline113.append(" ");
            } else {
                outline113 = GeneratedOutlineSupport1.outline113(outline86, "null ");
            }
            outline86 = outline113.toString();
        }
        return outline86;
    }
}
