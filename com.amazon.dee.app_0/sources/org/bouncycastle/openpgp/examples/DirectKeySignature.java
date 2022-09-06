package org.bouncycastle.openpgp.examples;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.security.Security;
import java.util.Iterator;
import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.sig.NotationData;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.bouncycastle.openpgp.PGPSignature;
import org.bouncycastle.openpgp.PGPSignatureGenerator;
import org.bouncycastle.openpgp.PGPSignatureSubpacketGenerator;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyDecryptorBuilder;
/* loaded from: classes5.dex */
public class DirectKeySignature {
    public static void main(String[] strArr) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        if (strArr.length != 1) {
            if (strArr.length != 5) {
                System.err.println("usage: DirectKeySignature secretKeyFile secretKeyPass publicKeyFile(key to be signed) NotationName NotationValue");
                System.err.println("or: DirectKeySignature signedPublicKeyFile");
                return;
            }
            PGPSecretKeyRing pGPSecretKeyRing = new PGPSecretKeyRing(PGPUtil.getDecoderStream(new FileInputStream(strArr[0])), new JcaKeyFingerprintCalculator());
            PGPPublicKeyRing pGPPublicKeyRing = new PGPPublicKeyRing(new ByteArrayInputStream(signPublicKey(pGPSecretKeyRing.getSecretKey(), strArr[1], new PGPPublicKeyRing(PGPUtil.getDecoderStream(new FileInputStream(strArr[2])), new JcaKeyFingerprintCalculator()).getPublicKey(), strArr[3], strArr[4])), new JcaKeyFingerprintCalculator());
            ArmoredOutputStream armoredOutputStream = new ArmoredOutputStream(new FileOutputStream("SignedKey.asc"));
            pGPPublicKeyRing.encode(armoredOutputStream);
            armoredOutputStream.flush();
            armoredOutputStream.close();
            return;
        }
        Iterator signaturesOfType = new PGPPublicKeyRing(PGPUtil.getDecoderStream(new FileInputStream(strArr[0])), new JcaKeyFingerprintCalculator()).getPublicKey().getSignaturesOfType(31);
        while (signaturesOfType.hasNext()) {
            PGPSignature pGPSignature = (PGPSignature) signaturesOfType.next();
            PrintStream printStream = System.out;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Signature date is: ");
            outline107.append(pGPSignature.getHashedSubPackets().getSignatureCreationTime());
            printStream.println(outline107.toString());
            NotationData[] notationDataOccurrences = pGPSignature.getHashedSubPackets().getNotationDataOccurrences();
            for (int i = 0; i < notationDataOccurrences.length; i++) {
                PrintStream printStream2 = System.out;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Found Notation named '");
                outline1072.append(notationDataOccurrences[i].getNotationName());
                outline1072.append("' with content '");
                outline1072.append(notationDataOccurrences[i].getNotationValue());
                outline1072.append("'.");
                printStream2.println(outline1072.toString());
            }
        }
    }

    private static byte[] signPublicKey(PGPSecretKey pGPSecretKey, String str, PGPPublicKey pGPPublicKey, String str2, String str3) throws Exception {
        PGPPrivateKey extractPrivateKey = pGPSecretKey.extractPrivateKey(new JcePBESecretKeyDecryptorBuilder().setProvider(BouncyCastleProvider.PROVIDER_NAME).build(str.toCharArray()));
        PGPSignatureGenerator pGPSignatureGenerator = new PGPSignatureGenerator(new JcaPGPContentSignerBuilder(pGPSecretKey.getPublicKey().getAlgorithm(), 2).setProvider(BouncyCastleProvider.PROVIDER_NAME));
        pGPSignatureGenerator.init(31, extractPrivateKey);
        PGPSignatureSubpacketGenerator pGPSignatureSubpacketGenerator = new PGPSignatureSubpacketGenerator();
        pGPSignatureSubpacketGenerator.setNotationData(true, true, str2, str3);
        pGPSignatureGenerator.setHashedSubpackets(pGPSignatureSubpacketGenerator.generate());
        return PGPPublicKey.addCertification(pGPPublicKey, pGPSignatureGenerator.generate()).getEncoded();
    }
}
