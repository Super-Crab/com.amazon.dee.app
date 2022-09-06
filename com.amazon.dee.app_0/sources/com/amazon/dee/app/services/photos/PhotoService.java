package com.amazon.dee.app.services.photos;

import android.net.Uri;
import androidx.annotation.NonNull;
import rx.Completable;
import rx.Observable;
/* loaded from: classes12.dex */
public interface PhotoService {

    /* loaded from: classes12.dex */
    public static class DeleteResponse {
        private String id;

        public String getId() {
            return this.id;
        }

        public void setId(@NonNull String str) {
            this.id = str;
        }
    }

    /* loaded from: classes12.dex */
    public static class UploadResponse {
        private String id;
        private String url;

        public String getId() {
            return this.id;
        }

        public String getUrl() {
            return this.url;
        }

        public void setId(@NonNull String str) {
            this.id = str;
        }

        public void setUrl(@NonNull String str) {
            this.url = str;
        }
    }

    Observable<DeleteResponse> deleteImage(String str);

    Completable initialize();

    Observable<UploadResponse> uploadImage(Uri uri);
}
