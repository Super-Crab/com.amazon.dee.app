package org.bouncycastle.openpgp.examples;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.SignatureException;
import java.util.Iterator;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.BCPGOutputStream;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSignature;
import org.bouncycastle.openpgp.PGPSignatureGenerator;
import org.bouncycastle.openpgp.PGPSignatureSubpacketGenerator;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyDecryptorBuilder;
import org.bouncycastle.util.Strings;
/* loaded from: classes5.dex */
public class ClearSignedFileProcessor {
    private static int getLengthWithoutSeparatorOrTrailingWhitespace(byte[] bArr) {
        int length = bArr.length - 1;
        while (length >= 0 && isWhiteSpace(bArr[length])) {
            length--;
        }
        return length + 1;
    }

    private static int getLengthWithoutWhiteSpace(byte[] bArr) {
        int length = bArr.length - 1;
        while (length >= 0 && isWhiteSpace(bArr[length])) {
            length--;
        }
        return length + 1;
    }

    private static byte[] getLineSeparator() {
        String lineSeparator = Strings.lineSeparator();
        byte[] bArr = new byte[lineSeparator.length()];
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = (byte) lineSeparator.charAt(i);
        }
        return bArr;
    }

    private static boolean isLineEnding(byte b) {
        return b == 13 || b == 10;
    }

    private static boolean isWhiteSpace(byte b) {
        return isLineEnding(b) || b == 9 || b == 32;
    }

    public static void main(String[] strArr) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        if (strArr[0].equals("-s")) {
            InputStream decoderStream = PGPUtil.getDecoderStream(new FileInputStream(strArr[2]));
            FileOutputStream fileOutputStream = new FileOutputStream(GeneratedOutlineSupport1.outline91(new StringBuilder(), strArr[1], ".asc"));
            if (strArr.length == 4) {
                signFile(strArr[1], decoderStream, fileOutputStream, strArr[3].toCharArray(), "SHA1");
            } else {
                signFile(strArr[1], decoderStream, fileOutputStream, strArr[3].toCharArray(), strArr[4]);
            }
        } else if (!strArr[0].equals("-v")) {
            System.err.println("usage: ClearSignedFileProcessor [-s file keyfile passPhrase]|[-v sigFile keyFile]");
        } else {
            if (strArr[1].indexOf(".asc") < 0) {
                System.err.println("file needs to end in \".asc\"");
                System.exit(1);
            }
            verifyFile(new FileInputStream(strArr[1]), PGPUtil.getDecoderStream(new FileInputStream(strArr[2])), strArr[1].substring(0, strArr[1].length() - 4));
        }
    }

    private static void processLine(OutputStream outputStream, PGPSignatureGenerator pGPSignatureGenerator, byte[] bArr) throws SignatureException, IOException {
        int lengthWithoutWhiteSpace = getLengthWithoutWhiteSpace(bArr);
        if (lengthWithoutWhiteSpace > 0) {
            pGPSignatureGenerator.update(bArr, 0, lengthWithoutWhiteSpace);
        }
        outputStream.write(bArr, 0, bArr.length);
    }

    private static void processLine(PGPSignature pGPSignature, byte[] bArr) throws SignatureException, IOException {
        int lengthWithoutWhiteSpace = getLengthWithoutWhiteSpace(bArr);
        if (lengthWithoutWhiteSpace > 0) {
            pGPSignature.update(bArr, 0, lengthWithoutWhiteSpace);
        }
    }

    private static int readInputLine(ByteArrayOutputStream byteArrayOutputStream, int i, InputStream inputStream) throws IOException {
        byteArrayOutputStream.reset();
        int i2 = i;
        do {
            byteArrayOutputStream.write(i2);
            if (i2 == 13 || i2 == 10) {
                i = readPassedEOL(byteArrayOutputStream, i2, inputStream);
                break;
            }
            i2 = inputStream.read();
        } while (i2 >= 0);
        if (i2 < 0) {
            return -1;
        }
        return i;
    }

    private static int readInputLine(ByteArrayOutputStream byteArrayOutputStream, InputStream inputStream) throws IOException {
        int read;
        byteArrayOutputStream.reset();
        do {
            read = inputStream.read();
            if (read < 0) {
                return -1;
            }
            byteArrayOutputStream.write(read);
            if (read == 13) {
                break;
            }
        } while (read != 10);
        return readPassedEOL(byteArrayOutputStream, read, inputStream);
    }

    private static int readPassedEOL(ByteArrayOutputStream byteArrayOutputStream, int i, InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (i == 13 && read == 10) {
            byteArrayOutputStream.write(read);
            return inputStream.read();
        }
        return read;
    }

    private static void signFile(String str, InputStream inputStream, OutputStream outputStream, char[] cArr, String str2) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, PGPException, SignatureException {
        int i = str2.equals("SHA256") ? 8 : str2.equals("SHA384") ? 9 : str2.equals("SHA512") ? 10 : str2.equals(MessageDigestAlgorithms.MD5) ? 1 : str2.equals("RIPEMD160") ? 3 : 2;
        PGPSecretKey readSecretKey = PGPExampleUtil.readSecretKey(inputStream);
        PGPPrivateKey extractPrivateKey = readSecretKey.extractPrivateKey(new JcePBESecretKeyDecryptorBuilder().setProvider(BouncyCastleProvider.PROVIDER_NAME).build(cArr));
        PGPSignatureGenerator pGPSignatureGenerator = new PGPSignatureGenerator(new JcaPGPContentSignerBuilder(readSecretKey.getPublicKey().getAlgorithm(), i).setProvider(BouncyCastleProvider.PROVIDER_NAME));
        PGPSignatureSubpacketGenerator pGPSignatureSubpacketGenerator = new PGPSignatureSubpacketGenerator();
        pGPSignatureGenerator.init(1, extractPrivateKey);
        Iterator<String> userIDs = readSecretKey.getPublicKey().getUserIDs();
        if (userIDs.hasNext()) {
            pGPSignatureSubpacketGenerator.setSignerUserID(false, userIDs.next());
            pGPSignatureGenerator.setHashedSubpackets(pGPSignatureSubpacketGenerator.generate());
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(str));
        ArmoredOutputStream armoredOutputStream = new ArmoredOutputStream(outputStream);
        armoredOutputStream.beginClearText(i);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int readInputLine = readInputLine(byteArrayOutputStream, bufferedInputStream);
        processLine(armoredOutputStream, pGPSignatureGenerator, byteArrayOutputStream.toByteArray());
        if (readInputLine == -1) {
            bufferedInputStream.close();
            armoredOutputStream.endClearText();
            pGPSignatureGenerator.generate().encode(new BCPGOutputStream(armoredOutputStream));
            armoredOutputStream.close();
        }
        do {
            readInputLine = readInputLine(byteArrayOutputStream, readInputLine, bufferedInputStream);
            pGPSignatureGenerator.update((byte) 13);
            pGPSignatureGenerator.update((byte) 10);
            processLine(armoredOutputStream, pGPSignatureGenerator, byteArrayOutputStream.toByteArray());
        } while (readInputLine != -1);
        bufferedInputStream.close();
        armoredOutputStream.endClearText();
        pGPSignatureGenerator.generate().encode(new BCPGOutputStream(armoredOutputStream));
        armoredOutputStream.close();
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void verifyFile(java.io.InputStream r8, java.io.InputStream r9, java.lang.String r10) throws java.lang.Exception {
        /*
            org.bouncycastle.bcpg.ArmoredInputStream r0 = new org.bouncycastle.bcpg.ArmoredInputStream
            r0.<init>(r8)
            java.io.BufferedOutputStream r8 = new java.io.BufferedOutputStream
            java.io.FileOutputStream r1 = new java.io.FileOutputStream
            r1.<init>(r10)
            r8.<init>(r1)
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
            r1.<init>()
            int r2 = readInputLine(r1, r0)
            byte[] r3 = getLineSeparator()
            r4 = 0
            r5 = -1
            if (r2 == r5) goto L4c
            boolean r6 = r0.isClearText()
            if (r6 == 0) goto L4c
            byte[] r6 = r1.toByteArray()
            int r7 = getLengthWithoutSeparatorOrTrailingWhitespace(r6)
            r8.write(r6, r4, r7)
        L31:
            r8.write(r3)
            if (r2 == r5) goto L5c
            boolean r6 = r0.isClearText()
            if (r6 == 0) goto L5c
            int r2 = readInputLine(r1, r2, r0)
            byte[] r6 = r1.toByteArray()
            int r7 = getLengthWithoutSeparatorOrTrailingWhitespace(r6)
            r8.write(r6, r4, r7)
            goto L31
        L4c:
            if (r2 == r5) goto L5c
            byte[] r2 = r1.toByteArray()
            int r6 = getLengthWithoutSeparatorOrTrailingWhitespace(r2)
            r8.write(r2, r4, r6)
            r8.write(r3)
        L5c:
            r8.close()
            org.bouncycastle.openpgp.PGPPublicKeyRingCollection r8 = new org.bouncycastle.openpgp.PGPPublicKeyRingCollection
            org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator r2 = new org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator
            r2.<init>()
            r8.<init>(r9, r2)
            org.bouncycastle.openpgp.jcajce.JcaPGPObjectFactory r9 = new org.bouncycastle.openpgp.jcajce.JcaPGPObjectFactory
            r9.<init>(r0)
            java.lang.Object r9 = r9.nextObject()
            org.bouncycastle.openpgp.PGPSignatureList r9 = (org.bouncycastle.openpgp.PGPSignatureList) r9
            org.bouncycastle.openpgp.PGPSignature r9 = r9.get(r4)
            long r2 = r9.getKeyID()
            org.bouncycastle.openpgp.PGPPublicKey r8 = r8.getPublicKey(r2)
            org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentVerifierBuilderProvider r0 = new org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentVerifierBuilderProvider
            r0.<init>()
            java.lang.String r2 = "BC"
            org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentVerifierBuilderProvider r0 = r0.setProvider(r2)
            r9.init(r0, r8)
            java.io.BufferedInputStream r8 = new java.io.BufferedInputStream
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r10)
            r8.<init>(r0)
            int r10 = readInputLine(r1, r8)
            byte[] r0 = r1.toByteArray()
            processLine(r9, r0)
            if (r10 == r5) goto Lbc
        La5:
            int r10 = readInputLine(r1, r10, r8)
            r0 = 13
            r9.update(r0)
            r0 = 10
            r9.update(r0)
            byte[] r0 = r1.toByteArray()
            processLine(r9, r0)
            if (r10 != r5) goto La5
        Lbc:
            r8.close()
            boolean r8 = r9.verify()
            if (r8 == 0) goto Lca
            java.io.PrintStream r8 = java.lang.System.out
            java.lang.String r9 = "signature verified."
            goto Lce
        Lca:
            java.io.PrintStream r8 = java.lang.System.out
            java.lang.String r9 = "signature verification failed."
        Lce:
            r8.println(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.openpgp.examples.ClearSignedFileProcessor.verifyFile(java.io.InputStream, java.io.InputStream, java.lang.String):void");
    }
}
