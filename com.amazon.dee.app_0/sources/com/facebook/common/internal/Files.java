package com.facebook.common.internal;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes2.dex */
public class Files {
    private Files() {
    }

    static byte[] readFile(InputStream in, long expectedSize) throws IOException {
        if (expectedSize <= 2147483647L) {
            if (expectedSize == 0) {
                return ByteStreams.toByteArray(in);
            }
            return ByteStreams.toByteArray(in, (int) expectedSize);
        }
        throw new OutOfMemoryError(GeneratedOutlineSupport1.outline57("file is too large to fit in a byte array: ", expectedSize, " bytes"));
    }

    public static byte[] toByteArray(File file) throws IOException {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byte[] readFile = readFile(fileInputStream, fileInputStream.getChannel().size());
                fileInputStream.close();
                return readFile;
            } catch (Throwable th) {
                th = th;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
    }
}
