package com.amazon.alexa.accessory.davs;

import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.io.InputStreamSource;
import com.amazon.alexa.accessory.io.Source;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/* loaded from: classes.dex */
public final class MD5ChecksumCalculator {
    private static final String HASHING_ALGORITHM = "MD5";
    private byte[] buffer;
    private final MessageDigest digest;

    public MD5ChecksumCalculator() {
        try {
            this.digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("UNSUPPORTED algorithm:MD5", e);
        }
    }

    private synchronized String calculateSignature(Source source) throws IOException {
        if (this.buffer == null) {
            this.buffer = new byte[4096];
        }
        this.digest.reset();
        while (true) {
            int read = source.read(this.buffer, 0, this.buffer.length);
            if (read == -1) {
            } else {
                this.digest.update(this.buffer, 0, read);
            }
        }
        return IOUtils.byteArrayToBase64(this.digest.digest());
    }

    public String getMD5Signature(File file) throws IOException {
        InputStreamSource inputStreamSource = null;
        try {
            InputStreamSource inputStreamSource2 = new InputStreamSource(new FileInputStream(file));
            try {
                String calculateSignature = calculateSignature(inputStreamSource2);
                IOUtils.closeQuietly(inputStreamSource2);
                return calculateSignature;
            } catch (Throwable th) {
                th = th;
                inputStreamSource = inputStreamSource2;
                IOUtils.closeQuietly(inputStreamSource);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
