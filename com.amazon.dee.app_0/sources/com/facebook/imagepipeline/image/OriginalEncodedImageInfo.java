package com.facebook.imagepipeline.image;

import android.net.Uri;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class OriginalEncodedImageInfo {
    public static final OriginalEncodedImageInfo EMPTY = new OriginalEncodedImageInfo();
    @Nullable
    private final Object mCallerContext;
    private final int mHeight;
    @Nullable
    private final EncodedImageOrigin mOrigin;
    private final int mSize;
    @Nullable
    private final Uri mUri;
    private final int mWidth;

    private OriginalEncodedImageInfo() {
        this.mUri = null;
        this.mOrigin = EncodedImageOrigin.NOT_SET;
        this.mCallerContext = null;
        this.mWidth = -1;
        this.mHeight = -1;
        this.mSize = -1;
    }

    @Nullable
    public Object getCallerContext() {
        return this.mCallerContext;
    }

    public int getHeight() {
        return this.mHeight;
    }

    @Nullable
    public EncodedImageOrigin getOrigin() {
        return this.mOrigin;
    }

    public int getSize() {
        return this.mSize;
    }

    @Nullable
    public Uri getUri() {
        return this.mUri;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public OriginalEncodedImageInfo(Uri sourceUri, EncodedImageOrigin origin, @Nullable Object callerContext, int width, int height, int size) {
        this.mUri = sourceUri;
        this.mOrigin = origin;
        this.mCallerContext = callerContext;
        this.mWidth = width;
        this.mHeight = height;
        this.mSize = size;
    }
}
