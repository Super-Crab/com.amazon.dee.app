package org.bouncycastle.openpgp.operator.jcajce;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.math.BigInteger;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.util.BigIntegers;
/* loaded from: classes5.dex */
class JcaJcePGPUtil {
    JcaJcePGPUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ECPoint decodePoint(BigInteger bigInteger, ECCurve eCCurve) throws IOException {
        return eCCurve.decodePoint(BigIntegers.asUnsignedByteArray(bigInteger));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static X9ECParameters getX9Parameters(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        X9ECParameters byOID = CustomNamedCurves.getByOID(aSN1ObjectIdentifier);
        return byOID == null ? ECNamedCurveTable.getByOID(aSN1ObjectIdentifier) : byOID;
    }

    public static SecretKey makeSymmetricKey(int i, byte[] bArr) throws PGPException {
        String symmetricCipherName = PGPUtil.getSymmetricCipherName(i);
        if (symmetricCipherName != null) {
            return new SecretKeySpec(bArr, symmetricCipherName);
        }
        throw new PGPException(GeneratedOutlineSupport1.outline49("unknown symmetric algorithm: ", i));
    }
}
