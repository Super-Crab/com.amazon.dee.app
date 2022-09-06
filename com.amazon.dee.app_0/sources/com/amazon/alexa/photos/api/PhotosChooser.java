package com.amazon.alexa.photos.api;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.AnyThread;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import java.util.List;
/* loaded from: classes9.dex */
public interface PhotosChooser {
    @MainThread
    void choosePhotos(@NonNull Activity activity, int i);

    @NonNull
    @AnyThread
    List<Uri> handleResponse(@NonNull Intent intent);
}
