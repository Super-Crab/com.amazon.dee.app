package org.bouncycastle.openpgp.examples;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Date;
import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPCompressedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedDataList;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPLiteralDataGenerator;
import org.bouncycastle.openpgp.PGPPBEEncryptedData;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.jcajce.JcaPGPObjectFactory;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPDigestCalculatorProviderBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePBEDataDecryptorFactoryBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePBEKeyEncryptionMethodGenerator;
import org.bouncycastle.openpgp.operator.jcajce.JcePGPDataEncryptorBuilder;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes5.dex */
public class ByteArrayHandler {
    private static byte[] compress(byte[] bArr, String str, int i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PGPCompressedDataGenerator pGPCompressedDataGenerator = new PGPCompressedDataGenerator(i);
        OutputStream open = new PGPLiteralDataGenerator().open(pGPCompressedDataGenerator.open(byteArrayOutputStream), 'b', str, bArr.length, new Date());
        open.write(bArr);
        open.close();
        pGPCompressedDataGenerator.close();
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] decrypt(byte[] bArr, char[] cArr) throws IOException, PGPException, NoSuchProviderException {
        JcaPGPObjectFactory jcaPGPObjectFactory = new JcaPGPObjectFactory(PGPUtil.getDecoderStream(new ByteArrayInputStream(bArr)));
        Object nextObject = jcaPGPObjectFactory.nextObject();
        if (!(nextObject instanceof PGPEncryptedDataList)) {
            nextObject = jcaPGPObjectFactory.nextObject();
        }
        return Streams.readAll(((PGPLiteralData) new JcaPGPObjectFactory(((PGPCompressedData) new JcaPGPObjectFactory(((PGPPBEEncryptedData) ((PGPEncryptedDataList) nextObject).get(0)).getDataStream(new JcePBEDataDecryptorFactoryBuilder(new JcaPGPDigestCalculatorProviderBuilder().setProvider(BouncyCastleProvider.PROVIDER_NAME).build()).setProvider(BouncyCastleProvider.PROVIDER_NAME).build(cArr))).nextObject()).getDataStream()).nextObject()).getInputStream());
    }

    public static byte[] encrypt(byte[] bArr, char[] cArr, String str, int i, boolean z) throws IOException, PGPException, NoSuchProviderException {
        if (str == null) {
            str = "_CONSOLE";
        }
        byte[] compress = compress(bArr, str, 1);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStream armoredOutputStream = z ? new ArmoredOutputStream(byteArrayOutputStream) : byteArrayOutputStream;
        PGPEncryptedDataGenerator pGPEncryptedDataGenerator = new PGPEncryptedDataGenerator(new JcePGPDataEncryptorBuilder(i).setSecureRandom(new SecureRandom()).setProvider(BouncyCastleProvider.PROVIDER_NAME));
        pGPEncryptedDataGenerator.addMethod(new JcePBEKeyEncryptionMethodGenerator(cArr).setProvider(BouncyCastleProvider.PROVIDER_NAME));
        OutputStream open = pGPEncryptedDataGenerator.open(armoredOutputStream, compress.length);
        open.write(compress);
        open.close();
        if (z) {
            armoredOutputStream.close();
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static void main(String[] strArr) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        char[] charArray = "Dick Beck".toCharArray();
        byte[] bytes = "Hello world".getBytes();
        System.out.println("Starting PGP test");
        byte[] encrypt = encrypt(bytes, charArray, "iway", 3, true);
        PrintStream printStream = System.out;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("\nencrypted data = '");
        outline107.append(new String(encrypt));
        outline107.append("'");
        printStream.println(outline107.toString());
        byte[] decrypt = decrypt(encrypt, charArray);
        PrintStream printStream2 = System.out;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("\ndecrypted data = '");
        outline1072.append(new String(decrypt));
        outline1072.append("'");
        printStream2.println(outline1072.toString());
        byte[] encrypt2 = encrypt(bytes, charArray, "iway", 9, false);
        PrintStream printStream3 = System.out;
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("\nencrypted data = '");
        outline1073.append(new String(Hex.encode(encrypt2)));
        outline1073.append("'");
        printStream3.println(outline1073.toString());
        byte[] decrypt2 = decrypt(encrypt2, charArray);
        PrintStream printStream4 = System.out;
        StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("\ndecrypted data = '");
        outline1074.append(new String(decrypt2));
        outline1074.append("'");
        printStream4.println(outline1074.toString());
    }
}
