package org.bouncycastle.bcpg;

import java.io.IOException;
import java.math.BigInteger;
/* loaded from: classes4.dex */
public class DSAPublicBCPGKey extends BCPGObject implements BCPGKey {
    MPInteger g;
    MPInteger p;
    MPInteger q;
    MPInteger y;

    public DSAPublicBCPGKey(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        this.p = new MPInteger(bigInteger);
        this.q = new MPInteger(bigInteger2);
        this.g = new MPInteger(bigInteger3);
        this.y = new MPInteger(bigInteger4);
    }

    public DSAPublicBCPGKey(BCPGInputStream bCPGInputStream) throws IOException {
        this.p = new MPInteger(bCPGInputStream);
        this.q = new MPInteger(bCPGInputStream);
        this.g = new MPInteger(bCPGInputStream);
        this.y = new MPInteger(bCPGInputStream);
    }

    @Override // org.bouncycastle.bcpg.BCPGObject
    public void encode(BCPGOutputStream bCPGOutputStream) throws IOException {
        bCPGOutputStream.writeObject(this.p);
        bCPGOutputStream.writeObject(this.q);
        bCPGOutputStream.writeObject(this.g);
        bCPGOutputStream.writeObject(this.y);
    }

    @Override // org.bouncycastle.bcpg.BCPGObject, org.bouncycastle.util.Encodable
    public byte[] getEncoded() {
        try {
            return super.getEncoded();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // org.bouncycastle.bcpg.BCPGKey
    public String getFormat() {
        return "PGP";
    }

    public BigInteger getG() {
        return this.g.getValue();
    }

    public BigInteger getP() {
        return this.p.getValue();
    }

    public BigInteger getQ() {
        return this.q.getValue();
    }

    public BigInteger getY() {
        return this.y.getValue();
    }
}
