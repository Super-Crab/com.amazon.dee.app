package org.bouncycastle.openpgp.operator.bc;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.bouncycastle.bcpg.BCPGKey;
import org.bouncycastle.bcpg.MPInteger;
import org.bouncycastle.bcpg.PublicKeyPacket;
import org.bouncycastle.bcpg.RSAPublicBCPGKey;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.operator.KeyFingerPrintCalculator;
/* loaded from: classes5.dex */
public class BcKeyFingerprintCalculator implements KeyFingerPrintCalculator {
    @Override // org.bouncycastle.openpgp.operator.KeyFingerPrintCalculator
    public byte[] calculateFingerprint(PublicKeyPacket publicKeyPacket) throws PGPException {
        Digest digest;
        BCPGKey key = publicKeyPacket.getKey();
        if (publicKeyPacket.getVersion() <= 3) {
            RSAPublicBCPGKey rSAPublicBCPGKey = (RSAPublicBCPGKey) key;
            try {
                digest = new MD5Digest();
                byte[] encoded = new MPInteger(rSAPublicBCPGKey.getModulus()).getEncoded();
                digest.update(encoded, 2, encoded.length - 2);
                byte[] encoded2 = new MPInteger(rSAPublicBCPGKey.getPublicExponent()).getEncoded();
                digest.update(encoded2, 2, encoded2.length - 2);
            } catch (IOException e) {
                throw new PGPException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("can't encode key components: ")), e);
            }
        } else {
            try {
                byte[] encodedContents = publicKeyPacket.getEncodedContents();
                SHA1Digest sHA1Digest = new SHA1Digest();
                sHA1Digest.update((byte) -103);
                sHA1Digest.update((byte) (encodedContents.length >> 8));
                sHA1Digest.update((byte) encodedContents.length);
                sHA1Digest.update(encodedContents, 0, encodedContents.length);
                digest = sHA1Digest;
            } catch (IOException e2) {
                throw new PGPException(GeneratedOutlineSupport1.outline37(e2, GeneratedOutlineSupport1.outline107("can't encode key components: ")), e2);
            }
        }
        byte[] bArr = new byte[digest.getDigestSize()];
        digest.doFinal(bArr, 0);
        return bArr;
    }
}
