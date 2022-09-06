package com.drew.tools;

import com.drew.lang.annotations.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/* loaded from: classes2.dex */
public class FileUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    @NotNull
    public static byte[] readBytes(@NotNull File file) throws IOException {
        int length = (int) file.length();
        byte[] bArr = new byte[length];
        int i = 0;
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            while (i != length) {
                try {
                    int read = fileInputStream2.read(bArr, i, length - i);
                    if (read == -1) {
                        break;
                    }
                    i += read;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            }
            fileInputStream2.close();
            return bArr;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @NotNull
    public static byte[] readBytes(@NotNull String str) throws IOException {
        return readBytes(new File(str));
    }

    public static void saveBytes(@NotNull File file, @NotNull byte[] bArr) throws IOException {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (Throwable th) {
            th = th;
        }
        try {
            fileOutputStream.write(bArr);
            fileOutputStream.close();
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            throw th;
        }
    }
}
