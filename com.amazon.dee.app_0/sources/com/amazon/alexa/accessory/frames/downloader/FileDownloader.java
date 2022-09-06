package com.amazon.alexa.accessory.frames.downloader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.frames.utils.Log;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
/* loaded from: classes.dex */
public class FileDownloader {
    private static final String TAG = "FileDownloader";
    private static FileDownloader fileDownloader;

    private long getFileLastModifiedDate(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file.lastModified();
        }
        String str2 = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getFileLastModifiedDate - Cannot get last modified date, folder does not exist - ");
        outline107.append(file.getAbsolutePath());
        Log.d(str2, outline107.toString());
        return -1L;
    }

    public static synchronized FileDownloader getInstance() {
        FileDownloader fileDownloader2;
        synchronized (FileDownloader.class) {
            if (fileDownloader == null) {
                fileDownloader = new FileDownloader();
            }
            fileDownloader2 = fileDownloader;
        }
        return fileDownloader2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean saveFile(String str, String str2, String str3, @NonNull byte[] bArr, @Nullable String str4) {
        boolean z;
        String str5 = TAG;
        Log.d(str5, "saveFile - requestId: " + str + " locale: " + str4);
        try {
            File file = new File(str2);
            if (!file.exists()) {
                String str6 = TAG;
                Log.i(str6, "saveFile - Creating folder - " + file.getAbsolutePath());
                z = file.mkdirs();
            } else {
                z = true;
            }
            if (!z) {
                String str7 = TAG;
                Log.w(str7, "saveFile - Cannot save file, folder does not exist - " + file.getAbsolutePath());
                return false;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file + "/" + str3, false);
            fileOutputStream.write(bArr);
            fileOutputStream.flush();
            fileOutputStream.close();
            return new File(file + "/" + str3).exists();
        } catch (Exception e) {
            Log.e(TAG, "saveFile - Exception.", e);
            return false;
        }
    }

    public synchronized void deleteExpiredAudio(String str, String str2) {
        String str3;
        long fileLastModifiedDate;
        try {
            Log.i(TAG, "deleteExpiredAudio - Check for expired audio");
            str3 = str + str2;
            fileLastModifiedDate = getInstance().getFileLastModifiedDate(str3);
        } catch (Exception e) {
            Log.e(TAG, "deleteExpiredAudio: failed with exception " + e);
        }
        if (fileLastModifiedDate == -1) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        Log.i(TAG, "deleteExpiredAudio - currentTimeMillis: " + currentTimeMillis + " lastModifiedTime: " + fileLastModifiedDate);
        if (currentTimeMillis - fileLastModifiedDate > TimeUnit.HOURS.toMillis(24L)) {
            Log.i(TAG, "deleteExpiredAudio - Found expired audio.");
            new File(str3).delete();
            Log.i(TAG, "deleteExpiredAudio - Deleted expired audio.");
        }
    }

    public void downloadUrl(String str, final String str2, @Nullable final String str3, final FileDownloadResponseHandler fileDownloadResponseHandler, @Nullable final String str4, @Nullable final String str5, @Nullable final String str6) {
        DependencyProvider.getHttpClient().newCall(new Request.Builder().url(str).build()).enqueue(new Callback() { // from class: com.amazon.alexa.accessory.frames.downloader.FileDownloader.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                String str7 = FileDownloader.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("requestDownload - onFailure. Exception for requestId ");
                outline107.append(str2);
                Log.e(str7, outline107.toString(), iOException);
                fileDownloadResponseHandler.handleDownloadResponse(false, str2, str3);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                String str7 = FileDownloader.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("requestDownload - onResponse success: ");
                outline107.append(response.isSuccessful());
                outline107.append(" for requestId: ");
                outline107.append(str2);
                Log.d(str7, outline107.toString());
                if (!response.isSuccessful()) {
                    fileDownloadResponseHandler.handleDownloadResponse(false, str2, str3);
                } else {
                    fileDownloadResponseHandler.handleDownloadResponse(FileDownloader.this.saveFile(str2, str4, str6, response.body().bytes(), str5), str2, str3);
                }
            }
        });
    }
}
