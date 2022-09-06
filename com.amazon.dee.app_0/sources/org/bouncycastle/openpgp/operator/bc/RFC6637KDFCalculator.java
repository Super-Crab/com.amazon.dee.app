package org.bouncycastle.openpgp.operator.bc;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.operator.PGPDigestCalculator;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes5.dex */
class RFC6637KDFCalculator {
    private static final byte[] ANONYMOUS_SENDER = Hex.decode("416E6F6E796D6F75732053656E64657220202020");
    private final PGPDigestCalculator digCalc;
    private final int keyAlgorithm;

    public RFC6637KDFCalculator(PGPDigestCalculator pGPDigestCalculator, int i) {
        this.digCalc = pGPDigestCalculator;
        this.keyAlgorithm = i;
    }

    private static byte[] KDF(PGPDigestCalculator pGPDigestCalculator, byte[] bArr, int i, byte[] bArr2) throws IOException {
        OutputStream outputStream = pGPDigestCalculator.getOutputStream();
        outputStream.write(0);
        outputStream.write(0);
        outputStream.write(0);
        outputStream.write(1);
        outputStream.write(bArr);
        outputStream.write(bArr2);
        byte[] bArr3 = new byte[i];
        System.arraycopy(pGPDigestCalculator.getDigest(), 0, bArr3, 0, bArr3.length);
        return bArr3;
    }

    private static int getKeyLen(int i) throws PGPException {
        if (i != 7) {
            if (i == 8) {
                return 24;
            }
            if (i != 9) {
                throw new PGPException(GeneratedOutlineSupport1.outline49("unknown symmetric algorithm ID: ", i));
            }
            return 32;
        }
        return 16;
    }

    public byte[] createKey(ECPoint eCPoint, byte[] bArr) throws PGPException {
        return createKey(eCPoint.getAffineXCoord().getEncoded(), bArr);
    }

    public byte[] createKey(byte[] bArr, byte[] bArr2) throws PGPException {
        try {
            return KDF(this.digCalc, bArr, getKeyLen(this.keyAlgorithm), bArr2);
        } catch (IOException e) {
            throw new PGPException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("Exception performing KDF: ")), e);
        }
    }
}
