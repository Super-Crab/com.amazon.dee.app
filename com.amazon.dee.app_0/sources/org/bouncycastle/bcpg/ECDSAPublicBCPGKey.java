package org.bouncycastle.bcpg;

import java.io.IOException;
import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.math.ec.ECPoint;
/* loaded from: classes4.dex */
public class ECDSAPublicBCPGKey extends ECPublicBCPGKey {
    public ECDSAPublicBCPGKey(ASN1ObjectIdentifier aSN1ObjectIdentifier, BigInteger bigInteger) throws IOException {
        super(aSN1ObjectIdentifier, bigInteger);
    }

    public ECDSAPublicBCPGKey(ASN1ObjectIdentifier aSN1ObjectIdentifier, ECPoint eCPoint) {
        super(aSN1ObjectIdentifier, eCPoint);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ECDSAPublicBCPGKey(BCPGInputStream bCPGInputStream) throws IOException {
        super(bCPGInputStream);
    }
}
