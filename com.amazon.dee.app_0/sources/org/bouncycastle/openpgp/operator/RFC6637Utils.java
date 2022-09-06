package org.bouncycastle.openpgp.operator;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.bcpg.ECDHPublicBCPGKey;
import org.bouncycastle.bcpg.PublicKeyPacket;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes5.dex */
public class RFC6637Utils {
    private static final byte[] ANONYMOUS_SENDER = Hex.decode("416E6F6E796D6F75732053656E64657220202020");

    private RFC6637Utils() {
    }

    public static byte[] createUserKeyingMaterial(PublicKeyPacket publicKeyPacket, KeyFingerPrintCalculator keyFingerPrintCalculator) throws IOException, PGPException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ECDHPublicBCPGKey eCDHPublicBCPGKey = (ECDHPublicBCPGKey) publicKeyPacket.getKey();
        byte[] encoded = eCDHPublicBCPGKey.getCurveOID().getEncoded();
        byteArrayOutputStream.write(encoded, 1, encoded.length - 1);
        byteArrayOutputStream.write(publicKeyPacket.getAlgorithm());
        byteArrayOutputStream.write(3);
        byteArrayOutputStream.write(1);
        byteArrayOutputStream.write(eCDHPublicBCPGKey.getHashAlgorithm());
        byteArrayOutputStream.write(eCDHPublicBCPGKey.getSymmetricKeyAlgorithm());
        byteArrayOutputStream.write(ANONYMOUS_SENDER);
        byteArrayOutputStream.write(keyFingerPrintCalculator.calculateFingerprint(publicKeyPacket));
        return byteArrayOutputStream.toByteArray();
    }

    public static String getAgreementAlgorithm(PublicKeyPacket publicKeyPacket) {
        ECDHPublicBCPGKey eCDHPublicBCPGKey = (ECDHPublicBCPGKey) publicKeyPacket.getKey();
        switch (eCDHPublicBCPGKey.getHashAlgorithm()) {
            case 8:
                return "ECCDHwithSHA256CKDF";
            case 9:
                return "ECCDHwithSHA384CKDF";
            case 10:
                return "ECCDHwithSHA512CKDF";
            default:
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown hash algorithm specified: ");
                outline107.append((int) eCDHPublicBCPGKey.getHashAlgorithm());
                throw new IllegalArgumentException(outline107.toString());
        }
    }

    public static ASN1ObjectIdentifier getKeyEncryptionOID(int i) throws PGPException {
        if (i != 7) {
            if (i == 8) {
                return NISTObjectIdentifiers.id_aes192_wrap;
            }
            if (i != 9) {
                throw new PGPException(GeneratedOutlineSupport1.outline49("unknown symmetric algorithm ID: ", i));
            }
            return NISTObjectIdentifiers.id_aes256_wrap;
        }
        return NISTObjectIdentifiers.id_aes128_wrap;
    }

    public static String getXDHAlgorithm(PublicKeyPacket publicKeyPacket) {
        ECDHPublicBCPGKey eCDHPublicBCPGKey = (ECDHPublicBCPGKey) publicKeyPacket.getKey();
        switch (eCDHPublicBCPGKey.getHashAlgorithm()) {
            case 8:
                return "X25519withSHA256CKDF";
            case 9:
                return "X25519withSHA384CKDF";
            case 10:
                return "X25519withSHA512CKDF";
            default:
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown hash algorithm specified: ");
                outline107.append((int) eCDHPublicBCPGKey.getHashAlgorithm());
                throw new IllegalArgumentException(outline107.toString());
        }
    }
}
