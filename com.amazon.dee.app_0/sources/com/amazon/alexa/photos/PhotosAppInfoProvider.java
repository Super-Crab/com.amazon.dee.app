package com.amazon.alexa.photos;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
/* loaded from: classes9.dex */
public class PhotosAppInfoProvider {
    @VisibleForTesting
    static final String APPLICATION_NAME = "AlexaMobileAndroid";
    @NonNull
    private final Context context;
    @NonNull
    private final String userAgent;

    public PhotosAppInfoProvider(@NonNull Context context, @NonNull String str) {
        this.context = context;
        this.userAgent = str;
    }

    @NonNull
    public String getApplicationId() {
        return this.context.getString(R.string.NEW_UPLOADER_APP_ID);
    }

    @NonNull
    public String getApplicationName() {
        return "AlexaMobileAndroid";
    }

    @NonNull
    public String getUserAgent() {
        return this.userAgent;
    }
}
