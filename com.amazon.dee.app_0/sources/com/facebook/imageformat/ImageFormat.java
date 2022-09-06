package com.facebook.imageformat;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class ImageFormat {
    public static final ImageFormat UNKNOWN = new ImageFormat("UNKNOWN", null);
    @Nullable
    private final String mFileExtension;
    private final String mName;

    /* loaded from: classes2.dex */
    public interface FormatChecker {
        @Nullable
        ImageFormat determineFormat(@Nonnull byte[] headerBytes, int headerSize);

        int getHeaderSize();
    }

    public ImageFormat(String name, @Nullable String fileExtension) {
        this.mName = name;
        this.mFileExtension = fileExtension;
    }

    @Nullable
    public String getFileExtension() {
        return this.mFileExtension;
    }

    public String getName() {
        return this.mName;
    }

    public String toString() {
        return getName();
    }
}
