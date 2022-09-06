package com.amazon.alexa.accessory.internal.util;

import android.util.Base64;
import com.amazon.alexa.accessory.io.InputStreamSource;
import com.amazon.alexa.accessory.io.Sink;
import com.amazon.alexa.accessory.io.Source;
import com.google.protobuf.ByteString;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.nio.ByteBuffer;
import java.util.UUID;
/* loaded from: classes.dex */
public final class IOUtils {
    private static final char[] HEX_CHARACTERS = "0123456789ABCDEF".toCharArray();
    private static final byte KEY = -13;
    private static final int UUID_BYTES = 16;

    private IOUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static byte[] base64ToByteArray(String str) {
        return Base64.decode(str, 2);
    }

    public static String byteArrayToBase64(byte[] bArr) {
        return byteArrayToBase64(bArr, 0, bArr.length);
    }

    public static String byteArrayToHex(byte[] bArr) {
        return byteArrayToHex(bArr, 0, bArr.length);
    }

    public static UUID byteArrayToUuid(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        return new UUID(wrap.getLong(), wrap.getLong());
    }

    public static UUID byteStringToUuid(ByteString byteString) {
        return byteArrayToUuid(byteString.toByteArray());
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static short combineTwoBytesIntoShort(byte b, byte b2) {
        return ByteBuffer.wrap(new byte[]{b, b2}).getShort();
    }

    public static byte[] fileToByteArray(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            byte[] streamToByteArray = streamToByteArray(fileInputStream);
            fileInputStream.close();
            return streamToByteArray;
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

    public static String fileToString(File file) throws IOException {
        return new String(fileToByteArray(file));
    }

    public static boolean isKthBitSet(int i, byte b) {
        return ((b >> i) & 1) != 0;
    }

    public static byte[] obfuscatedFileToByteArray(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            byte[] obfuscatedStreamToByteArray = obfuscatedStreamToByteArray(fileInputStream);
            fileInputStream.close();
            return obfuscatedStreamToByteArray;
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

    public static String obfuscatedFileToString(File file) throws IOException {
        return new String(obfuscatedFileToByteArray(file));
    }

    public static byte[] obfuscatedSourceToByteArray(Source source) throws IOException {
        byte[] bArr = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            try {
                int read = source.read(bArr, 0, bArr.length);
                if (read < 0) {
                    byteArrayOutputStream.flush();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    return byteArray;
                }
                for (int i = 0; i < read; i++) {
                    bArr[i] = (byte) (bArr[i] ^ KEY);
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
    }

    public static byte[] obfuscatedStreamToByteArray(InputStream inputStream) throws IOException {
        return obfuscatedSourceToByteArray(new InputStreamSource(inputStream));
    }

    public static byte setKthBit(int i, byte b) {
        return (byte) ((1 << i) | b);
    }

    public static byte[] sourceToByteArray(Source source) throws IOException {
        byte[] bArr = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            try {
                int read = source.read(bArr, 0, bArr.length);
                if (read < 0) {
                    byteArrayOutputStream.flush();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    return byteArray;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
    }

    public static byte[] streamToByteArray(InputStream inputStream) throws IOException {
        return sourceToByteArray(new InputStreamSource(inputStream));
    }

    public static void transfer(Source source, Sink sink) throws IOException {
        transfer(source, sink, new byte[2048]);
    }

    public static void transferObfuscated(Source source, Sink sink) throws IOException {
        transferObfuscated(source, sink, new byte[2048]);
    }

    public static byte[] uuidToByteArray(UUID uuid) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[16]);
        wrap.putLong(uuid.getMostSignificantBits());
        wrap.putLong(uuid.getLeastSignificantBits());
        return wrap.array();
    }

    public static ByteString uuidToByteString(UUID uuid) {
        return ByteString.copyFrom(uuidToByteArray(uuid));
    }

    public static void waitUntilNotified(Object obj, long j) throws InterruptedIOException {
        Preconditions.notNull(obj, "monitor == null");
        try {
            long nanoTime = System.nanoTime();
            obj.wait(j);
            if (j > 0 && System.nanoTime() - nanoTime >= j * 1000000) {
                throw new InterruptedIOException("timeout");
            }
        } catch (InterruptedException unused) {
            throw new InterruptedIOException("interrupted");
        }
    }

    public static String byteArrayToBase64(byte[] bArr, int i, int i2) {
        return Base64.encodeToString(bArr, i, i2, 2);
    }

    public static String byteArrayToHex(byte[] bArr, int i, int i2) {
        Preconditions.notNull(bArr, "bytes");
        char[] cArr = new char[i2 * 2];
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = bArr[i + i3] & 255;
            int i5 = i3 * 2;
            char[] cArr2 = HEX_CHARACTERS;
            cArr[i5] = cArr2[i4 >>> 4];
            cArr[i5 + 1] = cArr2[i4 & 15];
        }
        return new String(cArr);
    }

    public static void transfer(Source source, Sink sink, byte[] bArr) throws IOException {
        transfer(source, sink, bArr, -1);
    }

    public static void transferObfuscated(Source source, Sink sink, byte[] bArr) throws IOException {
        transferObfuscated(source, sink, bArr, -1);
    }

    public static void transfer(Source source, Sink sink, byte[] bArr, int i) throws IOException {
        int i2 = 0;
        while (true) {
            if (i2 < i || i == -1) {
                int length = bArr.length;
                if (i > 0) {
                    length = Math.min(length, i - i2);
                }
                int read = source.read(bArr, 0, length);
                if (read < 0) {
                    return;
                }
                i2 += read;
                sink.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static void transferObfuscated(Source source, Sink sink, byte[] bArr, int i) throws IOException {
        int i2 = 0;
        while (true) {
            if (i2 < i || i == -1) {
                int length = bArr.length;
                if (i > 0) {
                    length = Math.min(length, i - i2);
                }
                int read = source.read(bArr, 0, length);
                if (read < 0) {
                    return;
                }
                i2 += read;
                for (int i3 = 0; i3 < read; i3++) {
                    bArr[i3] = (byte) (bArr[i3] ^ KEY);
                }
                sink.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }
}
