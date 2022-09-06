package com.amazon.alexa.sharing.media.model;

import android.net.Uri;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import rx.Completable;
import rx.Observable;
/* loaded from: classes10.dex */
public interface CloudDriveService {

    /* loaded from: classes10.dex */
    public static class DownloadResponse {
        private String mFileExtension;
        private int mHeight;
        private String mLocalMediaPath;
        private String mMediaType;
        private int mWidth;

        public String getFileExtension() {
            return this.mFileExtension;
        }

        public int getHeight() {
            return this.mHeight;
        }

        public String getLocalMediaPath() {
            return this.mLocalMediaPath;
        }

        public String getMediaType() {
            return this.mMediaType;
        }

        public int getWidth() {
            return this.mWidth;
        }

        public void setFileExtension(String str) {
            this.mFileExtension = str;
        }

        public void setHeight(int i) {
            this.mHeight = i;
        }

        public void setLocalMediaPath(String str) {
            this.mLocalMediaPath = str;
        }

        public void setMediaType(String str) {
            this.mMediaType = str;
        }

        public void setWidth(int i) {
            this.mWidth = i;
        }
    }

    /* loaded from: classes10.dex */
    public static class UploadResponse {
        private String mImageID;
        private String mOwnerID;

        public String getId() {
            return this.mImageID;
        }

        public String getOwnerID() {
            return this.mOwnerID;
        }

        public void setId(@NonNull String str) {
            this.mImageID = str;
        }

        public void setOwnerID(@NonNull String str) {
            this.mOwnerID = str;
        }
    }

    Observable<DownloadResponse> downloadImage(@NonNull String str, @NonNull String str2, @NonNull String str3);

    AsyncTask<Void, Void, Void> downloadSingleFile(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull CloudDriveFileDownloadListener cloudDriveFileDownloadListener);

    AsyncTask<Void, Void, Void> downloadSingleThumbnail(@NonNull String str, @NonNull int i, @NonNull String str2, @NonNull CloudDriveFileDownloadListener cloudDriveFileDownloadListener);

    Observable<DownloadResponse> downloadThumbnail(@NonNull String str, @NonNull int i, @NonNull String str2);

    String getAlexaFolderId();

    String getRootNodeId();

    void init(@NonNull SetupCompletedListener setupCompletedListener);

    Completable initialize();

    Observable<UploadResponse> uploadImage(@NonNull Uri uri);

    AsyncTask<Void, Void, Void> uploadSingleFile(@NonNull Uri uri, @NonNull CloudDriveFileUploadListener cloudDriveFileUploadListener);
}
