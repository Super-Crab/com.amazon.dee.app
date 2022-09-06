package org.bouncycastle.bcpg;

import java.io.IOException;
/* loaded from: classes4.dex */
public class ModDetectionCodePacket extends ContainedPacket {
    private byte[] digest;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ModDetectionCodePacket(BCPGInputStream bCPGInputStream) throws IOException {
        this.digest = new byte[20];
        bCPGInputStream.readFully(this.digest);
    }

    public ModDetectionCodePacket(byte[] bArr) throws IOException {
        this.digest = new byte[bArr.length];
        byte[] bArr2 = this.digest;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
    }

    @Override // org.bouncycastle.bcpg.ContainedPacket
    public void encode(BCPGOutputStream bCPGOutputStream) throws IOException {
        bCPGOutputStream.writePacket(19, this.digest, false);
    }

    public byte[] getDigest() {
        byte[] bArr = this.digest;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        return bArr2;
    }
}
