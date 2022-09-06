package org.bouncycastle.bcpg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
/* loaded from: classes4.dex */
public class TrustPacket extends ContainedPacket {
    byte[] levelAndTrustAmount;

    public TrustPacket(int i) {
        this.levelAndTrustAmount = new byte[1];
        this.levelAndTrustAmount[0] = (byte) i;
    }

    public TrustPacket(BCPGInputStream bCPGInputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = bCPGInputStream.read();
            if (read < 0) {
                this.levelAndTrustAmount = byteArrayOutputStream.toByteArray();
                return;
            }
            byteArrayOutputStream.write(read);
        }
    }

    @Override // org.bouncycastle.bcpg.ContainedPacket
    public void encode(BCPGOutputStream bCPGOutputStream) throws IOException {
        bCPGOutputStream.writePacket(12, this.levelAndTrustAmount, true);
    }

    public byte[] getLevelAndTrustAmount() {
        return this.levelAndTrustAmount;
    }
}
