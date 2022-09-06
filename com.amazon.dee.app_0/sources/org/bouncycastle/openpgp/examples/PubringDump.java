package org.bouncycastle.openpgp.examples;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.security.Security;
import java.util.Iterator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.PGPPublicKeyRingCollection;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes5.dex */
public class PubringDump {
    public static String getAlgorithm(int i) {
        if (i != 1) {
            if (i == 2) {
                return "RSA_ENCRYPT";
            }
            if (i == 3) {
                return "RSA_SIGN";
            }
            switch (i) {
                case 16:
                    return "ELGAMAL_ENCRYPT";
                case 17:
                    return "DSA";
                case 18:
                    return "ECDH";
                case 19:
                    return "ECDSA";
                case 20:
                    return "ELGAMAL_GENERAL";
                case 21:
                    return "DIFFIE_HELLMAN";
                default:
                    return "unknown";
            }
        }
        return "RSA_GENERAL";
    }

    public static void main(String[] strArr) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        Iterator<PGPPublicKeyRing> keyRings = new PGPPublicKeyRingCollection(PGPUtil.getDecoderStream(new FileInputStream(strArr[0])), new JcaKeyFingerprintCalculator()).getKeyRings();
        while (keyRings.hasNext()) {
            PGPPublicKeyRing next = keyRings.next();
            try {
                next.getPublicKey();
                Iterator<PGPPublicKey> publicKeys = next.getPublicKeys();
                boolean z = true;
                while (publicKeys.hasNext()) {
                    PGPPublicKey next2 = publicKeys.next();
                    if (z) {
                        PrintStream printStream = System.out;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Key ID: ");
                        outline107.append(Long.toHexString(next2.getKeyID()));
                        printStream.println(outline107.toString());
                        z = false;
                    } else {
                        PrintStream printStream2 = System.out;
                        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Key ID: ");
                        outline1072.append(Long.toHexString(next2.getKeyID()));
                        outline1072.append(" (subkey)");
                        printStream2.println(outline1072.toString());
                    }
                    PrintStream printStream3 = System.out;
                    StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("            Algorithm: ");
                    outline1073.append(getAlgorithm(next2.getAlgorithm()));
                    printStream3.println(outline1073.toString());
                    GeneratedOutlineSupport1.outline178(GeneratedOutlineSupport1.outline107("            Fingerprint: "), new String(Hex.encode(next2.getFingerprint())), System.out);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
