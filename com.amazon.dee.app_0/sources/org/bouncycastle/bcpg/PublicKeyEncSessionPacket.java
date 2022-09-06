package org.bouncycastle.bcpg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes4.dex */
public class PublicKeyEncSessionPacket extends ContainedPacket implements PublicKeyAlgorithmTags {
    private int algorithm;
    private byte[][] data;
    private long keyID;
    private int version;

    public PublicKeyEncSessionPacket(long j, int i, byte[][] bArr) {
        this.version = 3;
        this.keyID = j;
        this.algorithm = i;
        this.data = new byte[bArr.length];
        for (int i2 = 0; i2 != bArr.length; i2++) {
            this.data[i2] = Arrays.clone(bArr[i2]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PublicKeyEncSessionPacket(BCPGInputStream bCPGInputStream) throws IOException {
        this.version = bCPGInputStream.read();
        this.keyID |= bCPGInputStream.read() << 56;
        this.keyID |= bCPGInputStream.read() << 48;
        this.keyID |= bCPGInputStream.read() << 40;
        this.keyID |= bCPGInputStream.read() << 32;
        this.keyID |= bCPGInputStream.read() << 24;
        this.keyID |= bCPGInputStream.read() << 16;
        this.keyID |= bCPGInputStream.read() << 8;
        this.keyID |= bCPGInputStream.read();
        this.algorithm = bCPGInputStream.read();
        int i = this.algorithm;
        if (i == 1 || i == 2) {
            this.data = new byte[1];
            this.data[0] = new MPInteger(bCPGInputStream).getEncoded();
            return;
        }
        if (i != 16) {
            if (i == 18) {
                this.data = new byte[1];
                this.data[0] = Streams.readAll(bCPGInputStream);
                return;
            } else if (i != 20) {
                throw new IOException("unknown PGP public key algorithm encountered");
            }
        }
        this.data = new byte[2];
        this.data[0] = new MPInteger(bCPGInputStream).getEncoded();
        this.data[1] = new MPInteger(bCPGInputStream).getEncoded();
    }

    @Override // org.bouncycastle.bcpg.ContainedPacket
    public void encode(BCPGOutputStream bCPGOutputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BCPGOutputStream bCPGOutputStream2 = new BCPGOutputStream(byteArrayOutputStream);
        bCPGOutputStream2.write(this.version);
        bCPGOutputStream2.write((byte) (this.keyID >> 56));
        bCPGOutputStream2.write((byte) (this.keyID >> 48));
        bCPGOutputStream2.write((byte) (this.keyID >> 40));
        bCPGOutputStream2.write((byte) (this.keyID >> 32));
        bCPGOutputStream2.write((byte) (this.keyID >> 24));
        bCPGOutputStream2.write((byte) (this.keyID >> 16));
        bCPGOutputStream2.write((byte) (this.keyID >> 8));
        bCPGOutputStream2.write((byte) this.keyID);
        bCPGOutputStream2.write(this.algorithm);
        int i = 0;
        while (true) {
            byte[][] bArr = this.data;
            if (i == bArr.length) {
                bCPGOutputStream2.close();
                bCPGOutputStream.writePacket(1, byteArrayOutputStream.toByteArray(), true);
                return;
            }
            bCPGOutputStream2.write(bArr[i]);
            i++;
        }
    }

    public int getAlgorithm() {
        return this.algorithm;
    }

    public byte[][] getEncSessionKey() {
        return this.data;
    }

    public long getKeyID() {
        return this.keyID;
    }

    public int getVersion() {
        return this.version;
    }
}
