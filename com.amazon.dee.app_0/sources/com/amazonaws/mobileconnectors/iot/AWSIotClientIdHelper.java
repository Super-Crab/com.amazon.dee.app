package com.amazonaws.mobileconnectors.iot;

import com.amazonaws.util.StringUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;
@Deprecated
/* loaded from: classes13.dex */
final class AWSIotClientIdHelper {
    private static final String PERSISTENCE_FILE = "CLIENT_ID";
    private static String clientId;

    private AWSIotClientIdHelper() {
    }

    static String generateClientId() {
        return UUID.randomUUID().toString();
    }

    static synchronized String getPersistedClientId(String str) throws IOException {
        String str2;
        synchronized (AWSIotClientIdHelper.class) {
            File file = new File(str, PERSISTENCE_FILE);
            if (clientId == null) {
                if (!file.exists()) {
                    persistId(file, generateClientId());
                }
                clientId = readPersistedId(file);
            }
            str2 = clientId;
        }
        return str2;
    }

    private static void persistId(File file, String str) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(str.getBytes(StringUtils.UTF8));
        fileOutputStream.close();
    }

    private static String readPersistedId(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        byte[] bArr = new byte[(int) randomAccessFile.length()];
        randomAccessFile.readFully(bArr);
        randomAccessFile.close();
        return new String(bArr, StringUtils.UTF8);
    }

    static void reset() {
        clientId = null;
    }
}
