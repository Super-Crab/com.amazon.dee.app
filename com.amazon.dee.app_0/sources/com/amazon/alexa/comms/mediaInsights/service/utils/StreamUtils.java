package com.amazon.alexa.comms.mediaInsights.service.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
/* loaded from: classes6.dex */
public class StreamUtils {
    private static final Charset UTF8_CHARSET = Charset.forName("UTF-8");

    public static void closeStreams(Closeable... closeableArr) {
        for (Closeable closeable : closeableArr) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Throwable unused) {
                }
            }
        }
    }

    public static byte[] gzipCompress(String str) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        long currentTimeMillis = System.currentTimeMillis();
        GZIPOutputStream gZIPOutputStream = null;
        try {
            byte[] bytes = str.getBytes(UTF8_CHARSET);
            byteArrayOutputStream = new ByteArrayOutputStream(bytes.length);
            try {
                GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    gZIPOutputStream2.write(bytes);
                    gZIPOutputStream2.finish();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    closeStreams(gZIPOutputStream2);
                    closeStreams(byteArrayOutputStream);
                    String.format("gzip compression time : %s ms", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                    return byteArray;
                } catch (Throwable th) {
                    th = th;
                    gZIPOutputStream = gZIPOutputStream2;
                    closeStreams(gZIPOutputStream);
                    closeStreams(byteArrayOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.io.Closeable[]] */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.io.Closeable[]] */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6, types: [java.io.BufferedReader] */
    public static String gzipDecompress(byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream;
        InputStreamReader inputStreamReader;
        ?? r8;
        Throwable th;
        GZIPInputStream gZIPInputStream;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
                try {
                    inputStreamReader = new InputStreamReader(gZIPInputStream, UTF8_CHARSET);
                    try {
                        r8 = new BufferedReader(inputStreamReader);
                        try {
                            StringBuilder sb = new StringBuilder();
                            while (true) {
                                String readLine = r8.readLine();
                                if (readLine != null) {
                                    sb.append(readLine);
                                } else {
                                    String sb2 = sb.toString();
                                    closeStreams(new Closeable[]{r8, inputStreamReader, gZIPInputStream, byteArrayInputStream});
                                    return sb2;
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            closeStreams(new Closeable[]{r8, inputStreamReader, gZIPInputStream, byteArrayInputStream});
                            throw th;
                        }
                    } catch (Throwable th3) {
                        r8 = 0;
                        th = th3;
                    }
                } catch (Throwable th4) {
                    r8 = 0;
                    th = th4;
                    inputStreamReader = null;
                }
            } catch (Throwable th5) {
                th = th5;
                inputStreamReader = null;
                r8 = inputStreamReader;
                th = th;
                gZIPInputStream = r8;
                closeStreams(new Closeable[]{r8, inputStreamReader, gZIPInputStream, byteArrayInputStream});
                throw th;
            }
        } catch (Throwable th6) {
            th = th6;
            byteArrayInputStream = null;
            inputStreamReader = null;
        }
    }
}
