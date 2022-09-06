package com.amazon.alexa.accessory.notificationpublisher.utils;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.io.File;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class DownloadableAudioFileHelper extends BaseFileHelper {
    private static final String FILE_EXTENSION = ".mp3";
    private static final String FOLDER = "TTSRequest";
    private static final String TAG = "DownloadableAudioFileHelper";
    public static final long DEFAULT_RETENTION_PERIOD = TimeUnit.DAYS.toMillis(1);
    public static final long DEFAULT_BEFORE_RETENTION_PERIOD_WINDOW = TimeUnit.MINUTES.toMillis(30);
    public static final long DEFAULT_CLEANUP_TASK_DELAY = TimeUnit.HOURS.toMillis(6);

    public DownloadableAudioFileHelper(@NonNull Context context, @NonNull String str, @NonNull String str2, @Nullable String str3) throws NullPointerException {
        super(context, BaseFileHelper.sha256Hex(str), getFolderPathWithParameters(context, str2, str3, false), FILE_EXTENSION, TAG);
    }

    public static boolean clearAudioFolder(@Nullable String str, @Nullable String str2) {
        try {
            Log.i(TAG, "clearAudioFolder");
            String folderPathWithParameters = getFolderPathWithParameters(DependencyProvider.getContext(), str, str2, true);
            String str3 = TAG;
            Log.i(str3, "clearAudioFolder - folderPath: " + folderPathWithParameters);
            return BaseFileHelper.clearDirectoryIfExists(folderPathWithParameters);
        } catch (Exception e) {
            Log.i(TAG, "clearAudioFolder failed", e);
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.CLEAR_TTS_AUDIO_FILE_FOLDER_ERROR, MetricsRecorder.customAttributesForException(e, 512));
            return false;
        }
    }

    public static boolean deleteExpiredFiles(@Nullable String str, @Nullable String str2, long j) {
        try {
            Log.i(TAG, "deleteExpiredFiles");
            String folderPathWithParameters = getFolderPathWithParameters(DependencyProvider.getContext(), str, str2, true);
            String str3 = TAG;
            Log.i(str3, "deleteExpiredFiles - folderPath: " + folderPathWithParameters);
            return BaseFileHelper.deleteFilesInDirectory(folderPathWithParameters, Long.valueOf(j));
        } catch (Exception e) {
            Log.e(TAG, "deleteExpiredFiles failed", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_delete_expired_audio_files_error_tts", MetricsRecorder.customAttributesForException(e, 512));
            return false;
        }
    }

    static String getFolderPathWithParameters(@NonNull Context context, @Nullable String str, @Nullable String str2, boolean z) {
        if (str == null && str2 != null) {
            str = NotificationConstants.DEFAULT_LOCALE.toString();
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(FOLDER);
        String str3 = "";
        outline107.append(Strings.isNullOrEmpty(str) ? str3 : GeneratedOutlineSupport1.outline72("/", str));
        if (!Strings.isNullOrEmpty(str2)) {
            str3 = GeneratedOutlineSupport1.outline72("/", str2);
        }
        outline107.append(str3);
        String sb = outline107.toString();
        if (z) {
            return context.getFilesDir().getAbsoluteFile() + "/" + sb;
        }
        return sb;
    }

    @VisibleForTesting
    boolean aboutToPastRetentionPeriod(long j, long j2) {
        File file = getFile();
        return file != null && System.currentTimeMillis() - file.lastModified() > j2 - j;
    }

    public boolean aboutToPastRetentionPeriod() {
        return aboutToPastRetentionPeriod(DEFAULT_BEFORE_RETENTION_PERIOD_WINDOW, DEFAULT_RETENTION_PERIOD);
    }
}
