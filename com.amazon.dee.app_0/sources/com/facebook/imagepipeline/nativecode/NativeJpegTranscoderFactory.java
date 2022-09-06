package com.facebook.imagepipeline.nativecode;

import com.facebook.common.internal.DoNotStrip;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.transcoder.ImageTranscoder;
import com.facebook.imagepipeline.transcoder.ImageTranscoderFactory;
import javax.annotation.Nullable;
@DoNotStrip
/* loaded from: classes2.dex */
public class NativeJpegTranscoderFactory implements ImageTranscoderFactory {
    private final boolean mEnsureTranscoderLibraryLoaded;
    private final int mMaxBitmapSize;
    private final boolean mUseDownSamplingRatio;

    @DoNotStrip
    public NativeJpegTranscoderFactory(final int maxBitmapSize, final boolean useDownSamplingRatio, final boolean ensureTranscoderLibraryLoaded) {
        this.mMaxBitmapSize = maxBitmapSize;
        this.mUseDownSamplingRatio = useDownSamplingRatio;
        this.mEnsureTranscoderLibraryLoaded = ensureTranscoderLibraryLoaded;
    }

    @Override // com.facebook.imagepipeline.transcoder.ImageTranscoderFactory
    @DoNotStrip
    @Nullable
    public ImageTranscoder createImageTranscoder(ImageFormat imageFormat, boolean isResizingEnabled) {
        if (imageFormat != DefaultImageFormats.JPEG) {
            return null;
        }
        return new NativeJpegTranscoder(isResizingEnabled, this.mMaxBitmapSize, this.mUseDownSamplingRatio, this.mEnsureTranscoderLibraryLoaded);
    }
}
