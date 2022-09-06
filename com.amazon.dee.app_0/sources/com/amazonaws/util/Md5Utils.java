package com.amazonaws.util;

import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
/* loaded from: classes.dex */
public class Md5Utils {
    private static final int FOURTEEN = 14;
    private static final int SIXTEEN_K = 16384;

    public static byte[] computeMD5Hash(InputStream inputStream) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        try {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                byte[] bArr = new byte[16384];
                while (true) {
                    int read = bufferedInputStream.read(bArr, 0, bArr.length);
                    if (read == -1) {
                        break;
                    }
                    messageDigest.update(bArr, 0, read);
                }
                byte[] digest = messageDigest.digest();
                try {
                    bufferedInputStream.close();
                } catch (Exception e) {
                    Log log = LogFactory.getLog(Md5Utils.class);
                    log.debug("Unable to close input stream of hash candidate: " + e);
                }
                return digest;
            } catch (Throwable th) {
                try {
                    bufferedInputStream.close();
                } catch (Exception e2) {
                    Log log2 = LogFactory.getLog(Md5Utils.class);
                    log2.debug("Unable to close input stream of hash candidate: " + e2);
                }
                throw th;
            }
        } catch (NoSuchAlgorithmException e3) {
            throw new IllegalStateException(e3);
        }
    }

    public static String md5AsBase64(InputStream inputStream) throws IOException {
        return Base64.encodeAsString(computeMD5Hash(inputStream));
    }

    public static String md5AsBase64(byte[] bArr) {
        return Base64.encodeAsString(computeMD5Hash(bArr));
    }

    public static String md5AsBase64(File file) throws IOException {
        return Base64.encodeAsString(computeMD5Hash(file));
    }

    public static byte[] computeMD5Hash(byte[] bArr) {
        try {
            return MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(bArr);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    public static byte[] computeMD5Hash(File file) throws IOException {
        return computeMD5Hash(new FileInputStream(file));
    }
}
