package com.amazon.identity.auth.device;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class jd {
    public static final String TAG = "com.amazon.identity.auth.device.jd";

    private jd() {
    }

    public static void a(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException unused) {
            io.e(TAG, "IOException thrown closing input stream");
        }
    }

    public static byte[] a(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read >= 0) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public static void a(OutputStream outputStream, byte[] bArr) throws IOException {
        try {
            outputStream.write(bArr);
        } finally {
            a(outputStream);
        }
    }
}
