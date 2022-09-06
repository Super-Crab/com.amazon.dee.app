package com.amazon.deecomms.nativemodules;

import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.FileUtils;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
/* loaded from: classes12.dex */
public class CommsMetricsLogStorage extends ReactContextBaseJavaModule {
    private static final String E_FILE_DELETE_ERROR = "E_FILE_DELETE_ERROR";
    private static final String E_FILE_DOES_NOT_EXIST_ERROR = "E_FILE_DOES_NOT_EXIST_ERROR";
    private static final String E_FILE_WRITE_ERROR = "E_FILE_WRITE_ERROR";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsMetricsLogStorage.class);
    private static final String LOG_DIR = "logs";
    private final File metricsLogDir;

    public CommsMetricsLogStorage(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.metricsLogDir = new File(reactApplicationContext.getCacheDir(), LOG_DIR);
    }

    private void ensureSafeFile(File file) throws IOException {
        String canonicalPath = this.metricsLogDir.getCanonicalPath();
        String canonicalPath2 = file.getCanonicalPath();
        if (canonicalPath2.startsWith(canonicalPath)) {
            return;
        }
        throw new IOException(GeneratedOutlineSupport1.outline77("File ", canonicalPath2, " does not exist in the log directory: ", canonicalPath, "."));
    }

    @ReactMethod
    public void deleteLogFile(String str, Promise promise) {
        File file = new File(this.metricsLogDir, str);
        if (!file.exists()) {
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("ENOENT: no such file or directory at path '");
            outline1.append(file.getAbsolutePath());
            outline1.append("'.");
            promise.reject(E_FILE_DOES_NOT_EXIST_ERROR, outline1.toString());
            return;
        }
        try {
            ensureSafeFile(file);
            if (file.delete()) {
                promise.resolve(null);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("File '");
                sb.append(file.getAbsolutePath());
                sb.append("' could not be deleted.");
                String sb2 = sb.toString();
                LOG.e(sb2);
                promise.reject(E_FILE_DELETE_ERROR, sb2);
            }
        } catch (IOException e) {
            LOG.e(e.getMessage(), e);
            promise.reject(E_FILE_DELETE_ERROR, e);
        }
    }

    @ReactMethod
    public void getLogFilenames(final String str, Promise promise) {
        WritableArray createArray = Arguments.createArray();
        if (!this.metricsLogDir.exists()) {
            promise.resolve(createArray);
            return;
        }
        File[] listFiles = this.metricsLogDir.listFiles(new FilenameFilter() { // from class: com.amazon.deecomms.nativemodules.-$$Lambda$CommsMetricsLogStorage$WHV7vacst51sEzLZwSKliKCdoMI
            @Override // java.io.FilenameFilter
            public final boolean accept(File file, String str2) {
                boolean startsWith;
                startsWith = str2.startsWith(str);
                return startsWith;
            }
        });
        if (listFiles == null) {
            promise.resolve(createArray);
            return;
        }
        for (File file : listFiles) {
            createArray.pushString(file.getName());
        }
        promise.resolve(createArray);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "CommsMetricsLogStorage";
    }

    @ReactMethod
    public void writeEntry(String str, String str2, Promise promise) {
        FileUtils.writeToFile(str2, str, this.metricsLogDir);
        promise.resolve(null);
    }
}
