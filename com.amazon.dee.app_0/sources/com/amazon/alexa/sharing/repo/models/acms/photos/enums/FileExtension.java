package com.amazon.alexa.sharing.repo.models.acms.photos.enums;

import androidx.annotation.NonNull;
import com.amazon.alexa.sharing.Constants;
/* loaded from: classes10.dex */
public enum FileExtension {
    PNG(".png"),
    JPG(Constants.DEFAULT_IMAGE_EXTENSION),
    JPEG(".jpeg"),
    BMP(".bmp"),
    GIF(".gif"),
    WEBP(".webp"),
    PSD(".psd"),
    TIFF(".tiff"),
    HEIC(".HEIC"),
    MP4(".mp4"),
    MOV(".mov"),
    M4V(".m4v"),
    UNKNOWN_EXTENSION("unknown");
    
    @NonNull
    private final String fileExtension;

    FileExtension(@NonNull String str) {
        this.fileExtension = str;
    }
}
