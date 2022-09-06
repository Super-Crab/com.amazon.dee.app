package com.amazon.device.crashmanager;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.device.crashmanager.source.ArtifactSource;
import com.amazon.device.crashmanager.utils.CrashDescriptorDedupeUtil;
import com.amazon.device.crashmanager.utils.CrashDescriptorUtil;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes12.dex */
class AppFileArtifactSource implements ArtifactSource {
    private static final char CRASH_FILE_NAME_DELIMITER = '#';
    private static final String CRASH_LOG_FILENAME_PREFIX = "crashlog.v2.amzcl";
    private static final int CRASH_TAG_GROUP = 1;
    private static final boolean LOG_CONTENTS = false;
    private static final int MAX_KEY_LENGTH = 50;
    private static final int MAX_VALUE_LENGTH = 100;
    private static final String TAG = "com.amazon.device.crashmanager.AppFileArtifactSource";
    private final Context mContext;
    private final CrashDetailsCollectable mCrashDetailsCollectable;
    private final CrashDescriptorDedupeUtil mdedupUtil;
    private final Pattern pattern;

    public AppFileArtifactSource(Context context, CrashDetailsCollectable crashDetailsCollectable, CrashDescriptorDedupeUtil crashDescriptorDedupeUtil) {
        if (context != null) {
            if (crashDetailsCollectable != null) {
                this.mContext = context;
                this.mCrashDetailsCollectable = crashDetailsCollectable;
                this.pattern = Pattern.compile("#(.*?)#");
                this.mdedupUtil = crashDescriptorDedupeUtil;
                return;
            }
            throw new IllegalArgumentException("Crash details collector must not be null.");
        }
        throw new IllegalArgumentException("Context must not be null.");
    }

    private void addDetailToCrashReport(StringBuilder sb, String str, String str2) {
        if (!isNullOrEmpty(str)) {
            if (isNullOrEmpty(str2)) {
                str2 = "null";
            }
            GeneratedOutlineSupport1.outline181(sb, str, RealTimeTextConstants.COLON_SPACE, str2, "\n");
        }
    }

    private boolean deleteCrashReportFile(File file) {
        return this.mContext.deleteFile(file.getName());
    }

    private String generateCrashReport(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        Map<String, String> metadataAndDebugDetails = getMetadataAndDebugDetails(map);
        sb.append(metadataAndDebugDetails.get("metadataDetail"));
        sb.append("\n");
        GeneratedOutlineSupport1.outline180(sb, map.get("stackTrace"), "\n", "=== Complete details ===\n\n");
        sb.append(metadataAndDebugDetails.get("debugDetail"));
        return sb.toString();
    }

    private File getCrashReportFile() {
        File[] listFiles = this.mContext.getFileStreamPath("").listFiles(new FileFilter() { // from class: com.amazon.device.crashmanager.AppFileArtifactSource.1
            @Override // java.io.FileFilter
            public boolean accept(File file) {
                return file.getName().startsWith(AppFileArtifactSource.CRASH_LOG_FILENAME_PREFIX);
            }
        });
        if (listFiles == null || listFiles.length == 0) {
            return null;
        }
        return listFiles[0];
    }

    private String getCrashTagFromFileName(String str) {
        Matcher matcher = this.pattern.matcher(str);
        return matcher.find() ? matcher.group(1) : "data_app_crash";
    }

    private String getFileName(String str, String str2) {
        return "crashlog.v2.amzcl#" + str2 + CRASH_FILE_NAME_DELIMITER + str;
    }

    private Map<String, String> getMetadataAndDebugDetails(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        HashMap hashMap = new HashMap();
        ArrayList<String> arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        for (String str : arrayList) {
            String str2 = map.get(str);
            if (isHeaderMetadata(str, str2)) {
                addDetailToCrashReport(sb, str, str2);
            } else {
                addDetailToCrashReport(sb2, str, str2);
            }
        }
        hashMap.put("metadataDetail", sb.toString());
        hashMap.put("debugDetail", sb2.toString());
        return hashMap;
    }

    private boolean isHeaderMetadata(String str, String str2) {
        return str != null && str2 != null && str.length() < 50 && str2.length() < 100 && str.indexOf(10) < 0 && str2.indexOf(10) < 0;
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private void saveCrashReportToFile(String str, String str2, String str3) {
        if (str == null || str.length() == 0) {
            return;
        }
        try {
            String fileName = getFileName(str2, str3);
            Log.i(TAG, String.format("Saving crash report to file: %s", this.mContext.getFileStreamPath(fileName).getAbsolutePath()));
            synchronized (AppFileArtifactSource.class) {
                if (isDuplicateCrash(this.mContext.getFileStreamPath(fileName))) {
                    this.mdedupUtil.increaseCounter(str3, str2);
                    String str4 = TAG;
                    Log.i(str4, "Duplicate crash occurred with descriptor : " + str2);
                } else {
                    FileOutputStream openFileOutput = this.mContext.openFileOutput(fileName, 0);
                    try {
                        openFileOutput.write(str.getBytes(Charset.forName("UTF-8")));
                        this.mdedupUtil.increaseCounter(str3, str2);
                        String str5 = TAG;
                        Log.i(str5, "Saved crash into : " + fileName);
                        try {
                            openFileOutput.close();
                        } catch (Exception unused) {
                        }
                    } catch (Throwable th) {
                        if (openFileOutput != null) {
                            try {
                                openFileOutput.close();
                            } catch (Exception unused2) {
                            }
                        }
                        throw th;
                    }
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "Could not save crash report to file", e);
        }
    }

    String calculateCrashDescriptor(Throwable th, String str) throws Exception {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        StringBuilder sb = new StringBuilder();
        sb.append(stringWriter);
        if (str != null) {
            sb.append(str);
        }
        return CrashDescriptorUtil.calculateCrashDescriptor(sb);
    }

    public Artifact getNextArtifact(MetricEvent metricEvent) {
        return getNextArtifact(metricEvent, CrashManagerActions.ARTIFACT_UPLOAD);
    }

    protected boolean isDuplicateCrash(File file) {
        try {
            return file.exists();
        } catch (Exception unused) {
            Log.e(TAG, "Exception in checking the presence of duplicate file");
            return false;
        }
    }

    public Boolean isInForeground() {
        try {
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            return Boolean.valueOf(runningAppProcessInfo.importance <= 100);
        } catch (Exception unused) {
            return null;
        }
    }

    public void saveCrash(Throwable th) {
        saveCrash(th, "data_app_crash", null);
    }

    @Override // com.amazon.device.crashmanager.source.ArtifactSource
    public void saveCurrentIndex() {
        File crashReportFile = getCrashReportFile();
        if (crashReportFile == null || !crashReportFile.exists() || deleteCrashReportFile(crashReportFile)) {
            return;
        }
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to delete crash report file ");
        outline107.append(crashReportFile.getName());
        Log.e(str, outline107.toString());
    }

    @Override // com.amazon.device.crashmanager.source.ArtifactSource
    public Artifact getNextArtifact(MetricEvent metricEvent, String str) {
        File crashReportFile = getCrashReportFile();
        if (crashReportFile != null && crashReportFile.exists()) {
            try {
                return new Artifact(getCrashTagFromFileName(crashReportFile.getName()), new FileInputStream(crashReportFile), crashReportFile.lastModified());
            } catch (Exception e) {
                Log.w(TAG, "Could not find crash file.", e);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void saveCrash(Throwable th, String str, CrashDetailsCollectable crashDetailsCollectable) {
        Map<String, String> collect;
        Log.e(TAG, "=========================");
        Log.e(TAG, "CRASH HAS OCCURRED", th);
        Log.e(TAG, "Collecting details and saving to disk");
        Log.e(TAG, "=========================");
        try {
            Map<String, String> collect2 = this.mCrashDetailsCollectable.collect(th);
            if (crashDetailsCollectable != null && (collect = crashDetailsCollectable.collect(th)) != null) {
                collect2.putAll(collect);
            }
            Boolean isInForeground = isInForeground();
            if (isInForeground != null) {
                collect2.put("hasForegroundActivities", isInForeground.toString());
            }
            collect2.put("CrashType", str);
            collect2.put("ContentType", "ThirdPartyJavaCrash");
            collect2.put("process", collect2.get(CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME));
            collect2.put("version", collect2.get("packageVersionName"));
            collect2.put("osBuildNumber", collect2.get("androidBuildVersion"));
            String calculateCrashDescriptor = calculateCrashDescriptor(th, collect2.get(CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME));
            collect2.put("TraceHashCode", calculateCrashDescriptor);
            saveCrashReportToFile(generateCrashReport(collect2), calculateCrashDescriptor, str);
        } catch (Exception e) {
            Log.e(TAG, "Could not handle uncaught exception", e);
        }
    }
}
