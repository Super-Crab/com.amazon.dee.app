package com.amazon.clouddrive.utils;

import com.amazon.clouddrive.internal.utils.Closer;
import com.amazon.clouddrive.internal.utils.ThreadUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
/* loaded from: classes11.dex */
public class MD5Fingerprint {
    private static final int HEX_DIGIT_COUNT = 32;
    private static final int HEX_RADIX = 16;
    private final MessageDigest mDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
    private final byte[] mBuffer = new byte[8096];

    public String calculate(InputStream inputStream) throws IOException, InterruptedException {
        int i;
        this.mDigest.reset();
        while (true) {
            int read = inputStream.read(this.mBuffer);
            if (read <= 0) {
                break;
            }
            ThreadUtil.checkIfInterrupted();
            this.mDigest.update(this.mBuffer, 0, read);
        }
        ThreadUtil.checkIfInterrupted();
        String bigInteger = new BigInteger(1, this.mDigest.digest()).toString(16);
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

    public String calculate(File file) throws IOException, InterruptedException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            return calculate(fileInputStream);
        } finally {
            Closer.closeQuietly(fileInputStream);
        }
    }
}
