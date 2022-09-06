package com.amazon.alexa.photos.api;

import android.net.Uri;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Single;
import java.util.List;
/* loaded from: classes9.dex */
public interface PhotosUploader {
    @AnyThread
    void cancelUploads();

    @NonNull
    @AnyThread
    Single<String> createAlexaAlbum();

    @AnyThread
    boolean isUploadInProgress();

    void setAutoSaveEnabled(boolean z);

    void setCellularDataEnabled(boolean z);

    @AnyThread
    void upload(@NonNull List<Uri> list, @NonNull String str);
}
