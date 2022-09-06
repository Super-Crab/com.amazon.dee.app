package com.amazon.alexa.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
/* loaded from: classes10.dex */
public class ZipExtractor {
    private static final int BUFFER_SIZE = 4096;
    private static final String TAG = "ZipExtractor";

    private static void extractFile(ZipInputStream zipInputStream, String str) throws IOException {
        BufferedOutputStream bufferedOutputStream;
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str));
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = zipInputStream.read(bArr);
                if (read == -1) {
                    bufferedOutputStream.close();
                    return;
                }
                bufferedOutputStream.write(bArr, 0, read);
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedOutputStream2 = bufferedOutputStream;
            if (bufferedOutputStream2 != null) {
                bufferedOutputStream2.close();
            }
            throw th;
        }
    }

    private static String getFileNameOnly(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        String[] split = str.split("/");
        return split.length > 0 ? split[split.length - 1] : split[0];
    }

    public static void unzip(String str, String str2, String str3) throws IOException {
        ZipInputStream zipInputStream;
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            zipInputStream = new ZipInputStream(new FileInputStream(str));
            while (true) {
                try {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry == null) {
                        zipInputStream.close();
                        return;
                    }
                    String format = String.format("%s%s%s", str2, File.separator, nextEntry.getName());
                    if (!nextEntry.isDirectory() && nextEntry.getName().contains(str3)) {
                        extractFile(zipInputStream, str2 + File.separator + getFileNameOnly(format));
                    }
                    zipInputStream.closeEntry();
                } catch (Throwable th) {
                    th = th;
                    if (zipInputStream != null) {
                        zipInputStream.close();
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            zipInputStream = null;
        }
    }
}
