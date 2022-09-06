package com.amazon.alexa.accessory.notificationpublisher.utils;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.util.Map;
/* loaded from: classes.dex */
public final class NotificationFileHelper extends BaseFileHelper {
    private static final String ANNOUNCEMENT_FILE_SUFFIX = "announcement";
    private static final String CONTENT_FILE_SUFFIX = "content";
    private static final String CONTENT_MERGED_SUFFIX = "content_merged";
    private static final int MAX_FILE_NAME_LEN = ((255 - Math.max(12, 14)) - 4) - 1;
    private static final String NOTIFICATION_FILE_EXTENSION = ".mp3";
    private static final String NOTIFICATION_FOLDER = "notifications";
    private static final String READ_BACK_FILE_NAME = "read_back";
    private static final String TAG = "NotificationFileHelper";

    public NotificationFileHelper(@NonNull Context context, @NonNull String str) throws NullPointerException {
        super(context, str, NOTIFICATION_FOLDER, NOTIFICATION_FILE_EXTENSION, TAG);
    }

    public static boolean deleteAllFiles() {
        try {
            Log.i(TAG, "deleteAllFiles");
            return deleteFiles(null);
        } catch (Exception e) {
            Log.e(TAG, "deleteAllFiles failed", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_delete_all_audio_files_error_notification");
            return false;
        }
    }

    public static boolean deleteExpiredFiles(long j) {
        try {
            Log.i(TAG, "deleteExpiredFiles");
            return deleteFiles(Long.valueOf(j));
        } catch (Exception e) {
            Log.e(TAG, "deleteExpiredFiles failed", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_delete_expired_audio_files_error_notification", MetricsRecorder.customAttributesForException(e, 512));
            return false;
        }
    }

    public static boolean deleteFiles(@Nullable Long l) {
        String notificationsFolderPath = getNotificationsFolderPath(DependencyProvider.getContext());
        String str = TAG;
        Log.i(str, "deleteFiles - folderPath: " + notificationsFolderPath);
        return BaseFileHelper.deleteFilesInDirectory(notificationsFolderPath, l);
    }

    @NonNull
    public static String getNotificationsFolderPath(@NonNull Context context) {
        return context.getFilesDir().getAbsoluteFile() + "/" + NOTIFICATION_FOLDER;
    }

    @Nullable
    public File getAnnouncementFile() {
        return BaseFileHelper.getFile(getAnnouncementFilePath());
    }

    public String getAnnouncementFilePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFolderPath());
        sb.append("/");
        return GeneratedOutlineSupport1.outline93(sb, this.fileName, "_", "announcement", NOTIFICATION_FILE_EXTENSION);
    }

    @Nullable
    public File getContentFile() {
        return BaseFileHelper.getFile(getContentFilePath());
    }

    public String getContentFilePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFolderPath());
        sb.append("/");
        return GeneratedOutlineSupport1.outline93(sb, this.fileName, "_", "content", NOTIFICATION_FILE_EXTENSION);
    }

    @Nullable
    public File getMergedContentFile() {
        return BaseFileHelper.getFile(getMergedContentFilePath());
    }

    public String getMergedContentFilePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFolderPath());
        sb.append("/");
        return GeneratedOutlineSupport1.outline93(sb, this.fileName, "_", CONTENT_MERGED_SUFFIX, NOTIFICATION_FILE_EXTENSION);
    }

    @Nullable
    @VisibleForTesting
    File getReadBackFile() {
        return BaseFileHelper.getFile(getReadBackFilePath());
    }

    public String getReadBackFilePath() {
        return getFolderPath() + "/" + READ_BACK_FILE_NAME + NOTIFICATION_FILE_EXTENSION;
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.utils.BaseFileHelper
    @NonNull
    @VisibleForTesting
    public String getValidFileName(@NonNull String str) {
        String replaceAll = str.replaceAll("\\s+", "");
        try {
            if (replaceAll.length() >= MAX_FILE_NAME_LEN) {
                int lastIndexOf = replaceAll.lastIndexOf(AccessoryMetricsConstants.DELIMITER);
                String substring = replaceAll.substring(lastIndexOf + 1);
                String replaceAll2 = replaceAll.substring(0, lastIndexOf).replaceAll("\\W", "_");
                String substring2 = replaceAll2.substring(0, Math.min(replaceAll2.length(), (MAX_FILE_NAME_LEN - substring.length()) - 1));
                return substring2 + "_" + substring;
            }
            return replaceAll.replaceAll("\\W", "_");
        } catch (Exception e) {
            String str2 = this.logTag;
            Log.e(str2, "Failed to get valid file name for uuid: " + str, e);
            Map<String, Object> customAttributesForException = MetricsRecorder.customAttributesForException(e, 512);
            MetricsRecorder metricsRecorder = MetricsRecorder.getInstance();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FocusFilter_getValidAudioFileName_exception_");
            outline107.append(e.getClass().getSimpleName());
            metricsRecorder.recordCounter(outline107.toString(), customAttributesForException);
            return replaceAll;
        }
    }

    public boolean removeReadBackFile() {
        Log.d(TAG, "removeReadBackFile");
        try {
            return getReadBackFile().delete();
        } catch (Exception e) {
            String str = TAG;
            Log.d(str, "removeReadBackFile failed: " + e);
            return false;
        }
    }

    public NotificationFileHelper(@NonNull Context context) {
        super(context, READ_BACK_FILE_NAME, NOTIFICATION_FOLDER, NOTIFICATION_FILE_EXTENSION, TAG);
    }

    @NonNull
    public String getNotificationsFolderPath() {
        return getFolderPath();
    }
}
