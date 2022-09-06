package com.amazon.alexa.accessory.notificationpublisher.urldownloader;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.utils.DownloadableAudioFileHelper;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationFileHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
/* loaded from: classes.dex */
public final class AudioFileDownloader {
    private static final String TAG = "AudioFileDownloader";

    private AudioFileDownloader() {
    }

    public static void downloadUrl(String str, String str2, int i, DownloadResponseHandler downloadResponseHandler, boolean z) throws RuntimeException {
        downloadUrl(str, str2, i, downloadResponseHandler, z, null, null, GeneratedOutlineSupport1.outline133(MetricsConstants.CUSTOM_VALUES_KEY, "None"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized boolean saveFile(String str, int i, @NonNull byte[] bArr, @Nullable String str2, @Nullable String str3) {
        Context context;
        String contentFilePath;
        String str4;
        File file;
        synchronized (AudioFileDownloader.class) {
            String str5 = TAG;
            Log.i(str5, "saveFile - requestId: " + str + " locale: " + str2);
            boolean z = false;
            try {
                context = DependencyProvider.getContext();
            } catch (Exception e) {
                Log.e(TAG, "saveFile - Exception.", e);
                Map<String, Object> customAttributesForException = MetricsRecorder.customAttributesForException(e, 512);
                MetricsRecorder metricsRecorder = MetricsRecorder.getInstance();
                metricsRecorder.recordCounter("FocusFilter_saveAudioFile_error_exception_" + e.getClass().getSimpleName(), customAttributesForException);
            }
            if (context == null) {
                Log.w(TAG, "saveFile - Unable to save file because of null Context");
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.SAVE_AUDIOFILE_ERROR_NO_CONTEXT);
                return false;
            }
            boolean z2 = true;
            if (i == 3) {
                if (!Strings.isNullOrEmpty(str3) && !Strings.isNullOrEmpty(str2)) {
                    String str6 = TAG;
                    Log.d(str6, "saveFile - CONTENT_PREFIX_TYPE - " + str3 + " " + str2);
                    DownloadableAudioFileHelper downloadableAudioFileHelper = new DownloadableAudioFileHelper(context, str3, str2, DownloadResponseHandler.TYPE_TO_NAME_MAP.mo7740get(Integer.valueOf(i)));
                    str4 = downloadableAudioFileHelper.getFilePath();
                    file = new File(downloadableAudioFileHelper.getFolderPath());
                }
                Log.w(TAG, "saveFile - fileName or locale for DownloadableAudioFileHelper is null or empty");
                return false;
            } else if (i == 2 || i == 1) {
                NotificationFileHelper notificationFileHelper = new NotificationFileHelper(context, str);
                if (i == 1) {
                    contentFilePath = notificationFileHelper.getAnnouncementFilePath();
                } else {
                    contentFilePath = notificationFileHelper.getContentFilePath();
                }
                str4 = contentFilePath;
                file = new File(notificationFileHelper.getNotificationsFolderPath());
            } else if (i == 4) {
                NotificationFileHelper notificationFileHelper2 = new NotificationFileHelper(context, str3);
                str4 = notificationFileHelper2.getMergedContentFilePath();
                file = new File(notificationFileHelper2.getNotificationsFolderPath());
            } else if (i == 5) {
                NotificationFileHelper notificationFileHelper3 = new NotificationFileHelper(context);
                str4 = notificationFileHelper3.getReadBackFilePath();
                file = new File(notificationFileHelper3.getNotificationsFolderPath());
            } else {
                String str7 = TAG;
                Log.w(str7, "saveFile - Not supported file type - " + i);
                return false;
            }
            String str8 = TAG;
            Log.d(str8, "saveFile - filePath: " + str4);
            if (!file.exists()) {
                String str9 = TAG;
                Log.i(str9, "saveFile - Creating folder - " + file.getAbsolutePath());
                z2 = file.mkdirs();
            }
            if (!z2) {
                String str10 = TAG;
                Log.w(str10, "saveFile - Cannot save file, folder does not exist - " + file.getAbsolutePath());
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.SAVE_AUDIOFILE_ERROR_NO_FOLDER);
                return false;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(str4, false);
            fileOutputStream.write(bArr);
            fileOutputStream.flush();
            fileOutputStream.close();
            z = new File(str4).exists();
            MetricsRecorder.getInstance().recordCounter(z ? MetricsConstants.SAVE_AUDIOFILE_SUCCESS : MetricsConstants.SAVE_AUDIOFILE_ERROR);
            return z;
        }
    }

    public static void downloadUrl(String str, final String str2, final int i, final DownloadResponseHandler downloadResponseHandler, final boolean z, @Nullable final String str3, @Nullable final String str4, @NonNull final Map<String, Object> map) throws RuntimeException {
        Preconditions.notEmpty(str, "url");
        Preconditions.notEmpty(str2, "requestId");
        Preconditions.notNull(downloadResponseHandler, "responseHandler");
        Preconditions.precondition(i == 1 || i == 2 || i == 3 || i == 4 || i == 5, "type is not ANNOUNCEMENT_TYPE or CONTENT_TYPE or CONTENT_PREFIX_TYPE or READ_BACK_TYPE");
        String str5 = TAG;
        Log.i(str5, "downloadUrl - requestId: " + str2 + " locale: " + str3);
        final String format = String.format(Locale.US, MetricsConstants.FETCH_LATENCY_TEMPLATE, DownloadResponseHandler.TYPE_TO_NAME_MAP.mo7740get(Integer.valueOf(i)));
        MetricsRecorder.getInstance().startTimer(format, str2);
        DependencyProvider.getHttpClient().newCall(new Request.Builder().url(str).build()).enqueue(new Callback() { // from class: com.amazon.alexa.accessory.notificationpublisher.urldownloader.AudioFileDownloader.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                String str6 = AudioFileDownloader.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("requestDownload - onFailure. Exception for requestId ");
                outline107.append(str2);
                Log.e(str6, outline107.toString(), iOException);
                MetricsRecorder.getInstance().recordCounter(String.format(Locale.US, MetricsConstants.FETCH_ERROR_TEMPLATE, DownloadResponseHandler.TYPE_TO_NAME_MAP.mo7740get(Integer.valueOf(i))));
                MetricsRecorder.getInstance().cancelTimer(format, str2);
                downloadResponseHandler.handleDownloadResponse(false, str2, i, z, map);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                String str6 = AudioFileDownloader.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("requestDownload - onResponse success: ");
                outline107.append(response.isSuccessful());
                outline107.append(" for requestId: ");
                outline107.append(str2);
                Log.i(str6, outline107.toString());
                if (!response.isSuccessful()) {
                    MetricsRecorder.getInstance().recordCounter(String.format(Locale.US, MetricsConstants.FETCH_ERROR_TEMPLATE, DownloadResponseHandler.TYPE_TO_NAME_MAP.mo7740get(Integer.valueOf(i))));
                    MetricsRecorder.getInstance().cancelTimer(format, str2);
                    downloadResponseHandler.handleDownloadResponse(false, str2, i, z, map);
                    return;
                }
                MetricsRecorder.getInstance().pauseAndRecordTimer(format, str2);
                MetricsRecorder.getInstance().recordCounter(String.format(Locale.US, MetricsConstants.FETCH_SUCCESS_TEMPLATE, DownloadResponseHandler.TYPE_TO_NAME_MAP.mo7740get(Integer.valueOf(i))));
                downloadResponseHandler.handleDownloadResponse(AudioFileDownloader.saveFile(str2, i, response.body().bytes(), str3, str4), str2, i, z, map);
            }
        });
    }
}
