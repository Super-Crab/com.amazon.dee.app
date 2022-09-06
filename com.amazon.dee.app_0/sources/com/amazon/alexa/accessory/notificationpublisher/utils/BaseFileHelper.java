package com.amazon.alexa.accessory.notificationpublisher.utils;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.SequenceInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class BaseFileHelper {
    private static final String TAG = "BaseFileHelper";
    final Context context;
    final String fileExtension;
    final String fileName;
    final String folderName;
    final String logTag;
    int maxFileNameLen = 255;

    public BaseFileHelper(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) throws NullPointerException {
        Preconditions.notNull(context, "context");
        Preconditions.notNull(str, "fileName");
        Preconditions.notNull(str2, "folderName");
        Preconditions.notNull(str3, "fileExtension");
        Preconditions.notNull(str4, "logTag");
        this.context = context;
        this.folderName = str2;
        this.fileExtension = str3;
        this.maxFileNameLen -= str3.length();
        this.fileName = getValidFileName(str);
        this.logTag = str4;
    }

    public static synchronized boolean clearDirectoryIfExists(@NonNull String str) {
        boolean deleteFilesInDirectory;
        synchronized (BaseFileHelper.class) {
            deleteFilesInDirectory = deleteFilesInDirectory(str, null);
        }
        return deleteFilesInDirectory;
    }

    public static synchronized void concatTwoAudioFilesToNewPath(File file, File file2, String str) {
        synchronized (BaseFileHelper.class) {
            HashMap hashMap = new HashMap();
            if (file != null && file.exists()) {
                if (file2 != null && file2.exists()) {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(file);
                        FileInputStream fileInputStream2 = new FileInputStream(file2);
                        SequenceInputStream sequenceInputStream = new SequenceInputStream(fileInputStream, fileInputStream2);
                        FileOutputStream fileOutputStream = new FileOutputStream(new File(str), true);
                        while (true) {
                            int read = sequenceInputStream.read();
                            if (read == -1) {
                                break;
                            }
                            fileOutputStream.write((byte) read);
                        }
                        fileOutputStream.close();
                        sequenceInputStream.close();
                        fileInputStream.close();
                        fileInputStream2.close();
                        MetricsRecorder.getInstance().recordCounter(MetricsConstants.MERGE_AUDIO_FILE_SUCCEEDED);
                    } catch (Exception e) {
                        MetricsRecorder.getInstance().recordCounter(MetricsConstants.MERGE_AUDIO_FILE_FAILED, MetricsRecorder.customAttributesForException(e));
                        String str2 = TAG;
                        Log.e(str2, "concatTwoAudioFilesToNewPath - concat files failed: " + e.getMessage());
                    }
                    return;
                }
                String str3 = TAG;
                Log.i(str3, "concatTwoAudioFilesToNewPath - second audio file does not exist: " + file2.getName());
                hashMap.put(MetricsConstants.CUSTOM_VALUES_KEY, file2.getName());
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.MERGE_AUDIO_FILE_FAILED_FILE_EMPTY, hashMap);
                return;
            }
            String str4 = TAG;
            Log.i(str4, "concatTwoAudioFilesToNewPath - first audio file does not exist: " + file.getName());
            hashMap.put(MetricsConstants.CUSTOM_VALUES_KEY, file.getName());
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.MERGE_AUDIO_FILE_FAILED_FILE_EMPTY, hashMap);
        }
    }

    public static synchronized boolean deleteFilesInDirectory(@NonNull String str, @Nullable Long l) {
        boolean z;
        synchronized (BaseFileHelper.class) {
            z = false;
            File file = new File(str);
            if (file.exists() && file.isDirectory()) {
                deleteFilesInDirectoryRecursively(file, l);
                z = true;
            }
        }
        return z;
    }

    private static synchronized void deleteFilesInDirectoryRecursively(@NonNull File file, @Nullable Long l) {
        synchronized (BaseFileHelper.class) {
            if (file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    deleteFilesInDirectoryRecursively(file2, l);
                }
            }
            if (l == null || System.currentTimeMillis() - file.lastModified() >= l.longValue()) {
                file.delete();
            }
        }
    }

    public static String sha256Hex(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            int length = digest.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02x", Integer.valueOf(digest[i] & 255)));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException unused) {
            return "";
        }
    }

    public boolean doesFileExists() {
        return new File(getFilePath()).exists();
    }

    @Nullable
    public File getFile() {
        return getFile(getFilePath());
    }

    @NonNull
    public String getFilePath() {
        return getFolderPath() + "/" + this.fileName + this.fileExtension;
    }

    @NonNull
    public String getFolderPath() {
        return this.context.getFilesDir().getAbsoluteFile() + "/" + this.folderName;
    }

    @NonNull
    @VisibleForTesting
    public String getValidFileName(@NonNull String str) {
        String replaceAll = str.replaceAll("\\s+", "");
        String str2 = this.logTag;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getValidFileName -  maxFileNameLen: ");
        outline107.append(this.maxFileNameLen);
        Log.d(str2, outline107.toString());
        try {
            if (replaceAll.length() >= this.maxFileNameLen) {
                String replaceAll2 = replaceAll.replaceAll("\\W", "_");
                return replaceAll2.substring(0, Math.min(replaceAll2.length(), this.maxFileNameLen));
            }
            return replaceAll.replaceAll("\\W", "_");
        } catch (Exception e) {
            String str3 = this.logTag;
            Log.e(str3, "Failed to get valid file name for uuid: " + str, e);
            Map<String, Object> customAttributesForException = MetricsRecorder.customAttributesForException(e, 512);
            MetricsRecorder metricsRecorder = MetricsRecorder.getInstance();
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("FocusFilter_getValidAudioFileName_exception_");
            outline1072.append(e.getClass().getSimpleName());
            metricsRecorder.recordCounter(outline1072.toString(), customAttributesForException);
            return replaceAll;
        }
    }

    @Nullable
    public static File getFile(@NonNull String str) {
        File file = new File(str);
        if (file.exists()) {
            return file;
        }
        return null;
    }

    public boolean doesFileExists(String str) {
        if (Strings.isNullOrEmpty(str)) {
            return false;
        }
        return new File(str).exists();
    }
}
