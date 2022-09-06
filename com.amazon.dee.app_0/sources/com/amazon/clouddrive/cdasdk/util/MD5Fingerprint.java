package com.amazon.clouddrive.cdasdk.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
/* loaded from: classes11.dex */
public class MD5Fingerprint {
    private static final int HEX_DIGIT_COUNT = 32;
    private static final int HEX_RADIX = 16;
    private static final int STREAM_BUFFER_SIZE = 8096;

    private static void checkIfInterrupted() throws InterruptedException {
        if (!Thread.interrupted()) {
            return;
        }
        throw new InterruptedException();
    }

    public String calculate(InputStream inputStream) throws IOException, InterruptedException, NoSuchAlgorithmException {
        int i;
        MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
        byte[] bArr = new byte[STREAM_BUFFER_SIZE];
        messageDigest.reset();
        while (true) {
            int read = inputStream.read(bArr);
            if (read <= 0) {
                break;
            }
            checkIfInterrupted();
            messageDigest.update(bArr, 0, read);
        }
        checkIfInterrupted();
        String bigInteger = new BigInteger(1, messageDigest.digest()).toString(16);
        if (bigInteger.length() == 32) {
            return bigInteger;
        }
        int length = 32 - bigInteger.length();
        StringBuilder sb = new StringBuilder();
        for (i = 0; i < length; i++) {
            sb.append('0');
        }
        sb.append(bigInteger);
        return sb.toString();
    }

    public String calculate(File file) throws IOException, InterruptedException, NoSuchAlgorithmException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            String calculate = calculate(fileInputStream);
            fileInputStream.close();
            return calculate;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    fileInputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }
}
