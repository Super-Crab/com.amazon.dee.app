package com.amazon.alexa.logupload;

import android.content.Context;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
/* loaded from: classes9.dex */
public class LogRetriever {
    private static final int BUFFER_SIZE = 1024;
    private static final String LOG_FILE_PREFIX = "alexa_android_logs";
    private static final String TAG = "LogRetriever";
    private static final int TIMEOUT = 1500;
    private static final String TXT_FILE_SUFFIX = ".txt";
    private static final String ZIP_FILE_SUFFIX = ".zip";
    private Context context;
    private final LogStorageDirectory logStorageDirectory;

    /* loaded from: classes9.dex */
    public enum LogStorageDirectory {
        EXTERNAL,
        INTERNAL
    }

    public LogRetriever(Context context) {
        this.context = context;
        this.logStorageDirectory = LogStorageDirectory.INTERNAL;
    }

    private void cleanUpIfExists(File file) {
        if (file == null || file.delete()) {
            return;
        }
        Log.e(TAG, String.format("Failed to delete log file %s", file.getName()));
    }

    private String encodeToBase64(File file) throws IOException {
        try {
            InputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        writeStream(byteArrayOutputStream, fileInputStream);
                        String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
                        byteArrayOutputStream.close();
                        fileInputStream.close();
                        return encodeToString;
                    }
                }
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
        } catch (IOException e) {
            Log.e(TAG, "Unable to convert file to base64 encoded string", e);
            throw e;
        }
    }

    private File getStorageDirectory() {
        if (this.logStorageDirectory == LogStorageDirectory.INTERNAL) {
            return this.context.getFilesDir();
        }
        return Environment.getExternalStorageDirectory();
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.io.File writeLogFile() throws java.io.IOException, java.lang.InterruptedException, java.lang.IllegalThreadStateException {
        /*
            r5 = this;
            r0 = 0
            java.lang.String r1 = "alexa_android_logs"
            java.lang.String r2 = ".txt"
            java.io.File r3 = r5.getStorageDirectory()     // Catch: java.lang.IllegalThreadStateException -> L40 java.lang.InterruptedException -> L42 java.io.IOException -> L44
            java.io.File r1 = java.io.File.createTempFile(r1, r2, r3)     // Catch: java.lang.IllegalThreadStateException -> L40 java.lang.InterruptedException -> L42 java.io.IOException -> L44
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch: java.lang.IllegalThreadStateException -> L3a java.lang.InterruptedException -> L3c java.io.IOException -> L3e
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.IllegalThreadStateException -> L3a java.lang.InterruptedException -> L3c java.io.IOException -> L3e
            r3.<init>()     // Catch: java.lang.IllegalThreadStateException -> L3a java.lang.InterruptedException -> L3c java.io.IOException -> L3e
            java.lang.String r4 = "logcat -d -f "
            r3.append(r4)     // Catch: java.lang.IllegalThreadStateException -> L3a java.lang.InterruptedException -> L3c java.io.IOException -> L3e
            r3.append(r1)     // Catch: java.lang.IllegalThreadStateException -> L3a java.lang.InterruptedException -> L3c java.io.IOException -> L3e
            java.lang.String r3 = r3.toString()     // Catch: java.lang.IllegalThreadStateException -> L3a java.lang.InterruptedException -> L3c java.io.IOException -> L3e
            java.lang.Process r0 = r2.exec(r3)     // Catch: java.lang.IllegalThreadStateException -> L3a java.lang.InterruptedException -> L3c java.io.IOException -> L3e
            r2 = 1500(0x5dc, double:7.41E-321)
            java.lang.Thread.sleep(r2)     // Catch: java.lang.IllegalThreadStateException -> L3a java.lang.InterruptedException -> L3c java.io.IOException -> L3e
            int r2 = r0.exitValue()     // Catch: java.lang.IllegalThreadStateException -> L3a java.lang.InterruptedException -> L3c java.io.IOException -> L3e
            if (r2 != 0) goto L32
            return r1
        L32:
            java.io.IOException r2 = new java.io.IOException     // Catch: java.lang.IllegalThreadStateException -> L3a java.lang.InterruptedException -> L3c java.io.IOException -> L3e
            java.lang.String r3 = "Logcat did not terminate succesfully"
            r2.<init>(r3)     // Catch: java.lang.IllegalThreadStateException -> L3a java.lang.InterruptedException -> L3c java.io.IOException -> L3e
            throw r2     // Catch: java.lang.IllegalThreadStateException -> L3a java.lang.InterruptedException -> L3c java.io.IOException -> L3e
        L3a:
            r2 = move-exception
            goto L46
        L3c:
            r2 = move-exception
            goto L46
        L3e:
            r2 = move-exception
            goto L46
        L40:
            r2 = move-exception
            goto L45
        L42:
            r2 = move-exception
            goto L45
        L44:
            r2 = move-exception
        L45:
            r1 = r0
        L46:
            java.lang.String r3 = com.amazon.alexa.logupload.LogRetriever.TAG
            java.lang.String r4 = "Unable write to log file using logcat"
            android.util.Log.e(r3, r4, r2)
            if (r0 == 0) goto L52
            r0.destroy()
        L52:
            r5.cleanUpIfExists(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.logupload.LogRetriever.writeLogFile():java.io.File");
    }

    private void writeStream(OutputStream outputStream, InputStream inputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    private File zipFile(File file) throws IOException {
        File createTempFile = File.createTempFile(LOG_FILE_PREFIX, ZIP_FILE_SUFFIX, getStorageDirectory());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(createTempFile.getAbsolutePath());
            ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(fileOutputStream));
            try {
                FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
                zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                writeStream(zipOutputStream, fileInputStream);
                zipOutputStream.closeEntry();
                fileInputStream.close();
                zipOutputStream.close();
                fileOutputStream.close();
                return createTempFile;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        zipOutputStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "Unable to zip log file", e);
            throw e;
        }
    }

    public LogReport getDETReport(String str) throws LogUploadException {
        try {
            return new LogReportDET(str, getLogsAsBase64String());
        } catch (Exception e) {
            throw new LogUploadException("Unable to getDETReport", e);
        }
    }

    public String getLogsAsBase64String() throws IOException, InterruptedException, IllegalThreadStateException {
        File file;
        File file2 = null;
        try {
            file = writeLogFile();
            try {
                file2 = zipFile(file);
                String encodeToBase64 = encodeToBase64(file2);
                cleanUpIfExists(file);
                cleanUpIfExists(file2);
                return encodeToBase64;
            } catch (Throwable th) {
                th = th;
                cleanUpIfExists(file);
                cleanUpIfExists(file2);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            file = null;
        }
    }

    public LogRetriever() {
        this.logStorageDirectory = LogStorageDirectory.EXTERNAL;
    }
}
