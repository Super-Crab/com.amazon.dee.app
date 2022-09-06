package com.amazon.alexa.sharing.media.model;

import androidx.annotation.NonNull;
/* loaded from: classes10.dex */
public interface CloudDriveFileDownloadListener {
    void onError(Exception exc);

    void onSuccess(@NonNull String str, @NonNull String str2, @NonNull String str3, int i, int i2);
}
