package org.bouncycastle.bcpg;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes4.dex */
public class UserAttributeSubpacketInputStream extends InputStream implements UserAttributeSubpacketTags {
    InputStream in;

    public UserAttributeSubpacketInputStream(InputStream inputStream) {
        this.in = inputStream;
    }

    private void readFully(byte[] bArr, int i, int i2) throws IOException {
        if (i2 > 0) {
            int read = read();
            if (read < 0) {
                throw new EOFException();
            }
            bArr[i] = (byte) read;
            i++;
            i2--;
        }
        while (i2 > 0) {
            int read2 = this.in.read(bArr, i, i2);
            if (read2 < 0) {
                throw new EOFException();
            }
            i += read2;
            i2 -= read2;
        }
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.in.available();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        return this.in.read();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.bouncycastle.bcpg.UserAttributeSubpacket readPacket() throws java.io.IOException {
        /*
            r6 = this;
            int r0 = r6.read()
            if (r0 >= 0) goto L8
            r0 = 0
            return r0
        L8:
            r1 = 0
            r2 = 1
            r3 = 192(0xc0, float:2.69E-43)
            if (r0 >= r3) goto L10
        Le:
            r3 = r1
            goto L48
        L10:
            r4 = 223(0xdf, float:3.12E-43)
            if (r0 > r4) goto L22
            int r0 = r0 + (-192)
            int r0 = r0 << 8
            java.io.InputStream r4 = r6.in
            int r4 = r4.read()
            int r4 = r4 + r0
            int r0 = r4 + 192
            goto Le
        L22:
            r3 = 255(0xff, float:3.57E-43)
            if (r0 != r3) goto L6d
            java.io.InputStream r0 = r6.in
            int r0 = r0.read()
            int r0 = r0 << 24
            java.io.InputStream r3 = r6.in
            int r3 = r3.read()
            int r3 = r3 << 16
            r0 = r0 | r3
            java.io.InputStream r3 = r6.in
            int r3 = r3.read()
            int r3 = r3 << 8
            r0 = r0 | r3
            java.io.InputStream r3 = r6.in
            int r3 = r3.read()
            r0 = r0 | r3
            r3 = r2
        L48:
            java.io.InputStream r4 = r6.in
            int r4 = r4.read()
            if (r4 < 0) goto L65
            int r0 = r0 - r2
            byte[] r0 = new byte[r0]
            int r5 = r0.length
            r6.readFully(r0, r1, r5)
            if (r4 == r2) goto L5f
            org.bouncycastle.bcpg.UserAttributeSubpacket r1 = new org.bouncycastle.bcpg.UserAttributeSubpacket
            r1.<init>(r4, r3, r0)
            return r1
        L5f:
            org.bouncycastle.bcpg.attr.ImageAttribute r1 = new org.bouncycastle.bcpg.attr.ImageAttribute
            r1.<init>(r3, r0)
            return r1
        L65:
            java.io.EOFException r0 = new java.io.EOFException
            java.lang.String r1 = "unexpected EOF reading user attribute sub packet"
            r0.<init>(r1)
            throw r0
        L6d:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "unrecognised length reading user attribute sub packet"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.bcpg.UserAttributeSubpacketInputStream.readPacket():org.bouncycastle.bcpg.UserAttributeSubpacket");
    }
}
