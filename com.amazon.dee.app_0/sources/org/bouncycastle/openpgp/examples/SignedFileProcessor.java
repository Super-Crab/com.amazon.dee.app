package org.bouncycastle.openpgp.examples;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.SignatureException;
import java.util.Iterator;
import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.BCPGOutputStream;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPCompressedDataGenerator;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPLiteralDataGenerator;
import org.bouncycastle.openpgp.PGPOnePassSignature;
import org.bouncycastle.openpgp.PGPOnePassSignatureList;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyRingCollection;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSignatureGenerator;
import org.bouncycastle.openpgp.PGPSignatureList;
import org.bouncycastle.openpgp.PGPSignatureSubpacketGenerator;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.jcajce.JcaPGPObjectFactory;
import org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentVerifierBuilderProvider;
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyDecryptorBuilder;
/* loaded from: classes5.dex */
public class SignedFileProcessor {
    public static void main(String[] strArr) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        if (!strArr[0].equals("-s")) {
            if (strArr[0].equals("-v")) {
                verifyFile(new FileInputStream(strArr[1]), new FileInputStream(strArr[2]));
            } else {
                System.err.println("usage: SignedFileProcessor -v|-s [-a] file keyfile [passPhrase]");
            }
        } else if (strArr[1].equals("-a")) {
            signFile(strArr[2], new FileInputStream(strArr[3]), new FileOutputStream(GeneratedOutlineSupport1.outline91(new StringBuilder(), strArr[2], ".asc")), strArr[4].toCharArray(), true);
        } else {
            signFile(strArr[1], new FileInputStream(strArr[2]), new FileOutputStream(GeneratedOutlineSupport1.outline91(new StringBuilder(), strArr[1], ".bpg")), strArr[3].toCharArray(), false);
        }
    }

    private static void signFile(String str, InputStream inputStream, OutputStream outputStream, char[] cArr, boolean z) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, PGPException, SignatureException {
        if (z) {
            outputStream = new ArmoredOutputStream(outputStream);
        }
        PGPSecretKey readSecretKey = PGPExampleUtil.readSecretKey(inputStream);
        PGPPrivateKey extractPrivateKey = readSecretKey.extractPrivateKey(new JcePBESecretKeyDecryptorBuilder().setProvider(BouncyCastleProvider.PROVIDER_NAME).build(cArr));
        PGPSignatureGenerator pGPSignatureGenerator = new PGPSignatureGenerator(new JcaPGPContentSignerBuilder(readSecretKey.getPublicKey().getAlgorithm(), 2).setProvider(BouncyCastleProvider.PROVIDER_NAME));
        pGPSignatureGenerator.init(0, extractPrivateKey);
        Iterator<String> userIDs = readSecretKey.getPublicKey().getUserIDs();
        if (userIDs.hasNext()) {
            PGPSignatureSubpacketGenerator pGPSignatureSubpacketGenerator = new PGPSignatureSubpacketGenerator();
            pGPSignatureSubpacketGenerator.setSignerUserID(false, userIDs.next());
            pGPSignatureGenerator.setHashedSubpackets(pGPSignatureSubpacketGenerator.generate());
        }
        PGPCompressedDataGenerator pGPCompressedDataGenerator = new PGPCompressedDataGenerator(2);
        BCPGOutputStream bCPGOutputStream = new BCPGOutputStream(pGPCompressedDataGenerator.open(outputStream));
        pGPSignatureGenerator.generateOnePassVersion(false).encode(bCPGOutputStream);
        File file = new File(str);
        PGPLiteralDataGenerator pGPLiteralDataGenerator = new PGPLiteralDataGenerator();
        OutputStream open = pGPLiteralDataGenerator.open(bCPGOutputStream, 'b', file);
        FileInputStream fileInputStream = new FileInputStream(file);
        while (true) {
            int read = fileInputStream.read();
            if (read < 0) {
                break;
            }
            open.write(read);
            pGPSignatureGenerator.update((byte) read);
        }
        pGPLiteralDataGenerator.close();
        pGPSignatureGenerator.generate().encode(bCPGOutputStream);
        pGPCompressedDataGenerator.close();
        if (z) {
            outputStream.close();
        }
    }

    private static void verifyFile(InputStream inputStream, InputStream inputStream2) throws Exception {
        PrintStream printStream;
        String str;
        JcaPGPObjectFactory jcaPGPObjectFactory = new JcaPGPObjectFactory(((PGPCompressedData) new JcaPGPObjectFactory(PGPUtil.getDecoderStream(inputStream)).nextObject()).getDataStream());
        PGPOnePassSignature pGPOnePassSignature = ((PGPOnePassSignatureList) jcaPGPObjectFactory.nextObject()).get(0);
        PGPLiteralData pGPLiteralData = (PGPLiteralData) jcaPGPObjectFactory.nextObject();
        InputStream inputStream3 = pGPLiteralData.getInputStream();
        PGPPublicKey publicKey = new PGPPublicKeyRingCollection(PGPUtil.getDecoderStream(inputStream2), new JcaKeyFingerprintCalculator()).getPublicKey(pGPOnePassSignature.getKeyID());
        FileOutputStream fileOutputStream = new FileOutputStream(pGPLiteralData.getFileName());
        pGPOnePassSignature.init(new JcaPGPContentVerifierBuilderProvider().setProvider(BouncyCastleProvider.PROVIDER_NAME), publicKey);
        while (true) {
            int read = inputStream3.read();
            if (read < 0) {
                break;
            }
            pGPOnePassSignature.update((byte) read);
            fileOutputStream.write(read);
        }
        fileOutputStream.close();
        if (pGPOnePassSignature.verify(((PGPSignatureList) jcaPGPObjectFactory.nextObject()).get(0))) {
            printStream = System.out;
            str = "signature verified.";
        } else {
            printStream = System.out;
            str = "signature verification failed.";
        }
        printStream.println(str);
    }
}
