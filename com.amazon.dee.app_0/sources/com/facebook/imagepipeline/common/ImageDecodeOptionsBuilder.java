package com.facebook.imagepipeline.common;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import com.facebook.imagepipeline.common.ImageDecodeOptionsBuilder;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.transformation.BitmapTransformation;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class ImageDecodeOptionsBuilder<T extends ImageDecodeOptionsBuilder> {
    private Bitmap.Config mAnimatedBitmapConfig;
    private Bitmap.Config mBitmapConfig;
    @Nullable
    private BitmapTransformation mBitmapTransformation;
    @Nullable
    private ColorSpace mColorSpace;
    @Nullable
    private ImageDecoder mCustomImageDecoder;
    private boolean mDecodeAllFrames;
    private boolean mDecodePreviewFrame;
    private boolean mExcludeBitmapConfigFromComparison;
    private boolean mForceStaticImage;
    private boolean mUseLastFrameForPreview;
    private int mMinDecodeIntervalMs = 100;
    private int mMaxDimensionPx = Integer.MAX_VALUE;

    public ImageDecodeOptionsBuilder() {
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        this.mBitmapConfig = config;
        this.mAnimatedBitmapConfig = config;
    }

    public ImageDecodeOptions build() {
        return new ImageDecodeOptions(this);
    }

    public Bitmap.Config getAnimatedBitmapConfig() {
        return this.mAnimatedBitmapConfig;
    }

    public Bitmap.Config getBitmapConfig() {
        return this.mBitmapConfig;
    }

    @Nullable
    public BitmapTransformation getBitmapTransformation() {
        return this.mBitmapTransformation;
    }

    @Nullable
    public ColorSpace getColorSpace() {
        return this.mColorSpace;
    }

    @Nullable
    public ImageDecoder getCustomImageDecoder() {
        return this.mCustomImageDecoder;
    }

    public boolean getDecodeAllFrames() {
        return this.mDecodeAllFrames;
    }

    public boolean getDecodePreviewFrame() {
        return this.mDecodePreviewFrame;
    }

    public boolean getExcludeBitmapConfigFromComparison() {
        return this.mExcludeBitmapConfigFromComparison;
    }

    public boolean getForceStaticImage() {
        return this.mForceStaticImage;
    }

    public int getMaxDimensionPx() {
        return this.mMaxDimensionPx;
    }

    public int getMinDecodeIntervalMs() {
        return this.mMinDecodeIntervalMs;
    }

    protected T getThis() {
        return this;
    }

    public boolean getUseLastFrameForPreview() {
        return this.mUseLastFrameForPreview;
    }

    public T setAnimatedBitmapConfig(Bitmap.Config animatedBitmapConfig) {
        this.mAnimatedBitmapConfig = animatedBitmapConfig;
        return getThis();
    }

    public T setBitmapConfig(Bitmap.Config bitmapConfig) {
        this.mBitmapConfig = bitmapConfig;
        return getThis();
    }

    public T setBitmapTransformation(@Nullable BitmapTransformation bitmapTransformation) {
        this.mBitmapTransformation = bitmapTransformation;
        return getThis();
    }

    public T setColorSpace(ColorSpace colorSpace) {
        this.mColorSpace = colorSpace;
        return getThis();
    }

    public T setCustomImageDecoder(@Nullable ImageDecoder customImageDecoder) {
        this.mCustomImageDecoder = customImageDecoder;
        return getThis();
    }

    public T setDecodeAllFrames(boolean decodeAllFrames) {
        this.mDecodeAllFrames = decodeAllFrames;
        return getThis();
    }

    public T setDecodePreviewFrame(boolean decodePreviewFrame) {
        this.mDecodePreviewFrame = decodePreviewFrame;
        return getThis();
    }

    public T setExcludeBitmapConfigFromComparison(boolean excludeBitmapConfigFromComparison) {
        this.mExcludeBitmapConfigFromComparison = excludeBitmapConfigFromComparison;
        return getThis();
    }

    public T setForceStaticImage(boolean forceStaticImage) {
        this.mForceStaticImage = forceStaticImage;
        return getThis();
    }

    public ImageDecodeOptionsBuilder setFrom(ImageDecodeOptions options) {
        this.mMinDecodeIntervalMs = options.minDecodeIntervalMs;
        this.mMaxDimensionPx = options.maxDimensionPx;
        this.mDecodePreviewFrame = options.decodePreviewFrame;
        this.mUseLastFrameForPreview = options.useLastFrameForPreview;
        this.mDecodeAllFrames = options.decodeAllFrames;
        this.mForceStaticImage = options.forceStaticImage;
        this.mBitmapConfig = options.bitmapConfig;
        this.mAnimatedBitmapConfig = options.animatedBitmapConfig;
        this.mCustomImageDecoder = options.customImageDecoder;
        this.mBitmapTransformation = options.bitmapTransformation;
        this.mColorSpace = options.colorSpace;
        return getThis();
    }

    public T setMaxDimensionPx(int maxDimensionPx) {
        this.mMaxDimensionPx = maxDimensionPx;
        return getThis();
    }

    public T setMinDecodeIntervalMs(int intervalMs) {
        this.mMinDecodeIntervalMs = intervalMs;
        return getThis();
    }

    public T setUseLastFrameForPreview(boolean useLastFrameForPreview) {
        this.mUseLastFrameForPreview = useLastFrameForPreview;
        return getThis();
    }
}
