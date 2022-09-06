package com.facebook.imagepipeline.common;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.internal.Objects;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.transformation.BitmapTransformation;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes2.dex */
public class ImageDecodeOptions {
    private static final ImageDecodeOptions DEFAULTS = newBuilder().build();
    public final Bitmap.Config animatedBitmapConfig;
    public final Bitmap.Config bitmapConfig;
    @Nullable
    public final BitmapTransformation bitmapTransformation;
    @Nullable
    public final ColorSpace colorSpace;
    @Nullable
    public final ImageDecoder customImageDecoder;
    public final boolean decodeAllFrames;
    public final boolean decodePreviewFrame;
    private final boolean excludeBitmapConfigFromComparison;
    public final boolean forceStaticImage;
    public final int maxDimensionPx;
    public final int minDecodeIntervalMs;
    public final boolean useLastFrameForPreview;

    public ImageDecodeOptions(ImageDecodeOptionsBuilder b) {
        this.minDecodeIntervalMs = b.getMinDecodeIntervalMs();
        this.maxDimensionPx = b.getMaxDimensionPx();
        this.decodePreviewFrame = b.getDecodePreviewFrame();
        this.useLastFrameForPreview = b.getUseLastFrameForPreview();
        this.decodeAllFrames = b.getDecodeAllFrames();
        this.forceStaticImage = b.getForceStaticImage();
        this.bitmapConfig = b.getBitmapConfig();
        this.animatedBitmapConfig = b.getAnimatedBitmapConfig();
        this.customImageDecoder = b.getCustomImageDecoder();
        this.bitmapTransformation = b.getBitmapTransformation();
        this.colorSpace = b.getColorSpace();
        this.excludeBitmapConfigFromComparison = b.getExcludeBitmapConfigFromComparison();
    }

    public static ImageDecodeOptions defaults() {
        return DEFAULTS;
    }

    public static ImageDecodeOptionsBuilder newBuilder() {
        return new ImageDecodeOptionsBuilder();
    }

    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || ImageDecodeOptions.class != o.getClass()) {
            return false;
        }
        ImageDecodeOptions imageDecodeOptions = (ImageDecodeOptions) o;
        if (this.minDecodeIntervalMs != imageDecodeOptions.minDecodeIntervalMs || this.maxDimensionPx != imageDecodeOptions.maxDimensionPx || this.decodePreviewFrame != imageDecodeOptions.decodePreviewFrame || this.useLastFrameForPreview != imageDecodeOptions.useLastFrameForPreview || this.decodeAllFrames != imageDecodeOptions.decodeAllFrames || this.forceStaticImage != imageDecodeOptions.forceStaticImage) {
            return false;
        }
        if (!this.excludeBitmapConfigFromComparison && this.bitmapConfig != imageDecodeOptions.bitmapConfig) {
            return false;
        }
        return (this.excludeBitmapConfigFromComparison || this.animatedBitmapConfig == imageDecodeOptions.animatedBitmapConfig) && this.customImageDecoder == imageDecodeOptions.customImageDecoder && this.bitmapTransformation == imageDecodeOptions.bitmapTransformation && this.colorSpace == imageDecodeOptions.colorSpace;
    }

    public int hashCode() {
        int i = (((((((((this.minDecodeIntervalMs * 31) + this.maxDimensionPx) * 31) + (this.decodePreviewFrame ? 1 : 0)) * 31) + (this.useLastFrameForPreview ? 1 : 0)) * 31) + (this.decodeAllFrames ? 1 : 0)) * 31) + (this.forceStaticImage ? 1 : 0);
        if (!this.excludeBitmapConfigFromComparison) {
            i = (i * 31) + this.bitmapConfig.ordinal();
        }
        int i2 = 0;
        if (!this.excludeBitmapConfigFromComparison) {
            int i3 = i * 31;
            Bitmap.Config config = this.animatedBitmapConfig;
            i = i3 + (config != null ? config.ordinal() : 0);
        }
        int i4 = i * 31;
        ImageDecoder imageDecoder = this.customImageDecoder;
        int hashCode = (i4 + (imageDecoder != null ? imageDecoder.hashCode() : 0)) * 31;
        BitmapTransformation bitmapTransformation = this.bitmapTransformation;
        int hashCode2 = (hashCode + (bitmapTransformation != null ? bitmapTransformation.hashCode() : 0)) * 31;
        ColorSpace colorSpace = this.colorSpace;
        if (colorSpace != null) {
            i2 = colorSpace.hashCode();
        }
        return hashCode2 + i2;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ImageDecodeOptions{");
        outline107.append(toStringHelper().toString());
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    protected Objects.ToStringHelper toStringHelper() {
        return Objects.toStringHelper(this).add("minDecodeIntervalMs", this.minDecodeIntervalMs).add("maxDimensionPx", this.maxDimensionPx).add("decodePreviewFrame", this.decodePreviewFrame).add("useLastFrameForPreview", this.useLastFrameForPreview).add("decodeAllFrames", this.decodeAllFrames).add("forceStaticImage", this.forceStaticImage).add("bitmapConfigName", this.bitmapConfig.name()).add("animatedBitmapConfigName", this.animatedBitmapConfig.name()).add("customImageDecoder", this.customImageDecoder).add("bitmapTransformation", this.bitmapTransformation).add("colorSpace", this.colorSpace);
    }
}
