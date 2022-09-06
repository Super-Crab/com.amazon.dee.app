package org.bouncycastle.crypto.engines;
/* loaded from: classes4.dex */
public class VMPCKSA3Engine extends VMPCEngine {
    @Override // org.bouncycastle.crypto.engines.VMPCEngine, org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "VMPC-KSA3";
    }

    @Override // org.bouncycastle.crypto.engines.VMPCEngine
    protected void initKey(byte[] bArr, byte[] bArr2) {
        this.s = (byte) 0;
        this.P = new byte[256];
        for (int i = 0; i < 256; i++) {
            this.P[i] = (byte) i;
        }
        for (int i2 = 0; i2 < 768; i2++) {
            byte[] bArr3 = this.P;
            int i3 = i2 & 255;
            this.s = bArr3[(this.s + bArr3[i3] + bArr[i2 % bArr.length]) & 255];
            byte b = bArr3[i3];
            byte b2 = this.s;
            bArr3[i3] = bArr3[b2 & 255];
            bArr3[b2 & 255] = b;
        }
        for (int i4 = 0; i4 < 768; i4++) {
            byte[] bArr4 = this.P;
            int i5 = i4 & 255;
            this.s = bArr4[(this.s + bArr4[i5] + bArr2[i4 % bArr2.length]) & 255];
            byte b3 = bArr4[i5];
            byte b4 = this.s;
            bArr4[i5] = bArr4[b4 & 255];
            bArr4[b4 & 255] = b3;
        }
        for (int i6 = 0; i6 < 768; i6++) {
            byte[] bArr5 = this.P;
            int i7 = i6 & 255;
            this.s = bArr5[(this.s + bArr5[i7] + bArr[i6 % bArr.length]) & 255];
            byte b5 = bArr5[i7];
            byte b6 = this.s;
            bArr5[i7] = bArr5[b6 & 255];
            bArr5[b6 & 255] = b5;
        }
        this.n = (byte) 0;
    }
}
