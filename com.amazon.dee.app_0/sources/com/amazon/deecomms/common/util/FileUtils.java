package com.amazon.deecomms.common.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public final class FileUtils {
    private static final String E_FILE_WRITE_ERROR = "E_FILE_WRITE_ERROR";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, FileUtils.class);
    private static final String METRICS_LOG_FILE_PREFIX = "metrics-";
    private static final String METRICS_LOG_FILE_SUFFIX = ".csv";

    private FileUtils() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void ensureSafeFile(File file, File file2) throws IOException {
        String canonicalPath = file2.getCanonicalPath();
        String canonicalPath2 = file.getCanonicalPath();
        if (canonicalPath2.startsWith(canonicalPath)) {
            return;
        }
        throw new IOException(GeneratedOutlineSupport1.outline77("File ", canonicalPath2, " does not exist in the log directory: ", canonicalPath, "."));
    }

    public static String getMetricsLogFileName() {
        String uTCDateOnly = DateUtil.getUTCDateOnly(new Date());
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(METRICS_LOG_FILE_PREFIX);
        stringBuffer.append(uTCDateOnly);
        stringBuffer.append(METRICS_LOG_FILE_SUFFIX);
        return stringBuffer.toString();
    }

    @Deprecated
    public static JSONObject readJsonFile(AssetManager assetManager, String str) throws IOException, JSONException {
        return new JSONObject(readTextFile(assetManager, str));
    }

    @Deprecated
    public static String readTextFile(AssetManager assetManager, String str) throws IOException {
        Throwable th;
        Scanner scanner;
        try {
            scanner = new Scanner(assetManager.open(str)).useDelimiter("\\A");
            try {
                String next = scanner.hasNext() ? scanner.next() : "";
                scanner.close();
                return next;
            } catch (Throwable th2) {
                th = th2;
                if (scanner != null) {
                    scanner.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            scanner = null;
        }
    }

    public static void writeToFile(@NonNull final String str, final String str2, @NonNull final File file) {
        new AsyncTask<Void, Void, Void>() { // from class: com.amazon.deecomms.common.util.FileUtils.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                File file2 = new File(file, str);
                if (file.exists() || file.mkdir()) {
                    try {
                        FileUtils.ensureSafeFile(file2, file);
                        if (!file2.exists()) {
                            file2.createNewFile();
                        }
                        try {
                            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file2, true));
                            bufferedWriter.append((CharSequence) str2);
                            bufferedWriter.newLine();
                            bufferedWriter.close();
                        } catch (IOException e) {
                            CommsLogger commsLogger = FileUtils.LOG;
                            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Error occurred writing data to file '");
                            outline1.append(file2.getAbsolutePath());
                            outline1.append("'");
                            commsLogger.e(outline1.toString(), e);
                        }
                        return null;
                    } catch (IOException e2) {
                        FileUtils.LOG.e("Error occurred creating file: ", e2);
                        return null;
                    }
                }
                CommsLogger commsLogger2 = FileUtils.LOG;
                StringBuilder outline12 = GeneratedOutlineSupport.outline1("Error creating directory: '");
                outline12.append(file2.getAbsolutePath());
                commsLogger2.e(outline12.toString());
                return null;
            }
        }.execute(new Void[0]);
    }

    public static JSONObject readJsonFile(@NonNull Context context, int i) {
        try {
            InputStream openRawResource = context.getResources().openRawResource(i);
            if (openRawResource == null) {
                if (openRawResource != null) {
                    openRawResource.close();
                }
                return null;
            }
            byte[] bArr = new byte[openRawResource.available()];
            openRawResource.read(bArr);
            String str = new String(bArr, "UTF-8");
            openRawResource.close();
            try {
                return new JSONObject(str);
            } catch (JSONException unused) {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
