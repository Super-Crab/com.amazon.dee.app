package org.bouncycastle.bcpg.sig;

import org.bouncycastle.bcpg.SignatureSubpacket;
/* loaded from: classes4.dex */
public class SignatureExpirationTime extends SignatureSubpacket {
    public SignatureExpirationTime(boolean z, long j) {
        super(3, z, false, timeToBytes(j));
    }

    public SignatureExpirationTime(boolean z, boolean z2, byte[] bArr) {
        super(3, z, z2, bArr);
    }

    protected static byte[] timeToBytes(long j) {
        return new byte[]{(byte) (j >> 24), (byte) (j >> 16), (byte) (j >> 8), (byte) j};
    }

    public long getTime() {
        byte[] bArr = this.data;
        return ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (bArr[3] & 255);
    }
}
