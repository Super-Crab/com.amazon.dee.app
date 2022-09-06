package com.facebook.fresco.ui.common;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes2.dex */
public class DimensionsInfo {
    private final int mDecodedImageHeight;
    private final int mDecodedImageWidth;
    private final int mEncodedImageHeight;
    private final int mEncodedImageWidth;
    private final String mScaleType;
    private final int mViewportHeight;
    private final int mViewportWidth;

    public DimensionsInfo(int viewportWidth, int viewportHeight, int encodedImageWidth, int encodedImageHeight, int decodedImageWidth, int decodedImageHeight, String scaleType) {
        this.mViewportWidth = viewportWidth;
        this.mViewportHeight = viewportHeight;
        this.mEncodedImageWidth = encodedImageWidth;
        this.mEncodedImageHeight = encodedImageHeight;
        this.mDecodedImageWidth = decodedImageWidth;
        this.mDecodedImageHeight = decodedImageHeight;
        this.mScaleType = scaleType;
    }

    public int getDecodedImageHeight() {
        return this.mDecodedImageHeight;
    }

    public int getDecodedImageWidth() {
        return this.mDecodedImageWidth;
    }

    public int getEncodedImageHeight() {
        return this.mEncodedImageHeight;
    }

    public int getEncodedImageWidth() {
        return this.mEncodedImageWidth;
    }

    public String getScaleType() {
        return this.mScaleType;
    }

    public int getViewportHeight() {
        return this.mViewportHeight;
    }

    public int getViewportWidth() {
        return this.mViewportWidth;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DimensionsInfo{mViewportWidth=");
        outline107.append(this.mViewportWidth);
        outline107.append(", mViewportHeight=");
        outline107.append(this.mViewportHeight);
        outline107.append(", mEncodedImageWidth=");
        outline107.append(this.mEncodedImageWidth);
        outline107.append(", mEncodedImageHeight=");
        outline107.append(this.mEncodedImageHeight);
        outline107.append(", mDecodedImageWidth=");
        outline107.append(this.mDecodedImageWidth);
        outline107.append(", mDecodedImageHeight=");
        outline107.append(this.mDecodedImageHeight);
        outline107.append(", mScaleType='");
        return GeneratedOutlineSupport1.outline90(outline107, this.mScaleType, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }
}
