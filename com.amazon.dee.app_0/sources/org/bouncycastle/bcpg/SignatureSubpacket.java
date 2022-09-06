package org.bouncycastle.bcpg;
/* loaded from: classes4.dex */
public class SignatureSubpacket {
    boolean critical;
    protected byte[] data;
    boolean isLongLength;
    int type;

    /* JADX INFO: Access modifiers changed from: protected */
    public SignatureSubpacket(int i, boolean z, boolean z2, byte[] bArr) {
        this.type = i;
        this.critical = z;
        this.isLongLength = z2;
        this.data = bArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void encode(java.io.OutputStream r5) throws java.io.IOException {
        /*
            r4 = this;
            byte[] r0 = r4.data
            int r0 = r0.length
            int r0 = r0 + 1
            boolean r1 = r4.isLongLength
            r2 = 255(0xff, float:3.57E-43)
            if (r1 == 0) goto L25
        Lb:
            r5.write(r2)
            int r1 = r0 >> 24
            byte r1 = (byte) r1
            r5.write(r1)
            int r1 = r0 >> 16
            byte r1 = (byte) r1
            r5.write(r1)
            int r1 = r0 >> 8
            byte r1 = (byte) r1
        L1d:
            r5.write(r1)
        L20:
            byte r0 = (byte) r0
            r5.write(r0)
            goto L36
        L25:
            r1 = 192(0xc0, float:2.69E-43)
            if (r0 >= r1) goto L2a
            goto L20
        L2a:
            r3 = 8383(0x20bf, float:1.1747E-41)
            if (r0 > r3) goto Lb
            int r0 = r0 + (-192)
            int r3 = r0 >> 8
            r2 = r2 & r3
            int r2 = r2 + r1
            byte r1 = (byte) r2
            goto L1d
        L36:
            boolean r0 = r4.critical
            if (r0 == 0) goto L3f
            int r0 = r4.type
            r0 = r0 | 128(0x80, float:1.794E-43)
            goto L41
        L3f:
            int r0 = r4.type
        L41:
            r5.write(r0)
            byte[] r0 = r4.data
            r5.write(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.bcpg.SignatureSubpacket.encode(java.io.OutputStream):void");
    }

    public byte[] getData() {
        return this.data;
    }

    public int getType() {
        return this.type;
    }

    public boolean isCritical() {
        return this.critical;
    }

    public boolean isLongLength() {
        return this.isLongLength;
    }
}
