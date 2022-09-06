package com.amazon.deecomms.media.photos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public interface CloudDriveFileUploadListener {
    void onError(Exception exc);

    void onSuccess(@NonNull String str, @NonNull String str2, @Nullable String str3);
}
