package com.facebook.imagepipeline.transcoder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Build;
import com.facebook.common.logging.FLog;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import java.io.OutputStream;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class SimpleImageTranscoder implements ImageTranscoder {
    private static final String TAG = "SimpleImageTranscoder";
    private final int mMaxBitmapSize;
    private final boolean mResizingEnabled;

    public SimpleImageTranscoder(final boolean resizingEnabled, final int maxBitmapSize) {
        this.mResizingEnabled = resizingEnabled;
        this.mMaxBitmapSize = maxBitmapSize;
    }

    private static Bitmap.CompressFormat getOutputFormat(@Nullable final ImageFormat format) {
        if (format == null) {
            return Bitmap.CompressFormat.JPEG;
        }
        if (format == DefaultImageFormats.JPEG) {
            return Bitmap.CompressFormat.JPEG;
        }
        if (format == DefaultImageFormats.PNG) {
            return Bitmap.CompressFormat.PNG;
        }
        int i = Build.VERSION.SDK_INT;
        if (DefaultImageFormats.isStaticWebpFormat(format)) {
            return Bitmap.CompressFormat.WEBP;
        }
        return Bitmap.CompressFormat.JPEG;
    }

    private int getSampleSize(final EncodedImage encodedImage, final RotationOptions rotationOptions, @Nullable final ResizeOptions resizeOptions) {
        if (!this.mResizingEnabled) {
            return 1;
        }
        return DownsampleUtil.determineSampleSize(rotationOptions, resizeOptions, encodedImage, this.mMaxBitmapSize);
    }

    @Override // com.facebook.imagepipeline.transcoder.ImageTranscoder
    public boolean canResize(EncodedImage encodedImage, @Nullable RotationOptions rotationOptions, @Nullable ResizeOptions resizeOptions) {
        if (rotationOptions == null) {
            rotationOptions = RotationOptions.autoRotate();
        }
        return this.mResizingEnabled && DownsampleUtil.determineSampleSize(rotationOptions, resizeOptions, encodedImage, this.mMaxBitmapSize) > 1;
    }

    @Override // com.facebook.imagepipeline.transcoder.ImageTranscoder
    public boolean canTranscode(ImageFormat imageFormat) {
        return imageFormat == DefaultImageFormats.HEIF || imageFormat == DefaultImageFormats.JPEG;
    }

    @Override // com.facebook.imagepipeline.transcoder.ImageTranscoder
    public String getIdentifier() {
        return TAG;
    }

    @Override // com.facebook.imagepipeline.transcoder.ImageTranscoder
    public ImageTranscodeResult transcode(EncodedImage encodedImage, OutputStream outputStream, @Nullable RotationOptions rotationOptions, @Nullable ResizeOptions resizeOptions, @Nullable ImageFormat outputFormat, @Nullable Integer quality) {
        SimpleImageTranscoder simpleImageTranscoder;
        RotationOptions rotationOptions2;
        ResizeOptions resizeOptions2;
        Bitmap bitmap;
        Throwable th;
        OutOfMemoryError e;
        Integer num = quality == null ? 85 : quality;
        if (rotationOptions == null) {
            resizeOptions2 = resizeOptions;
            rotationOptions2 = RotationOptions.autoRotate();
            simpleImageTranscoder = this;
        } else {
            simpleImageTranscoder = this;
            rotationOptions2 = rotationOptions;
            resizeOptions2 = resizeOptions;
        }
        int sampleSize = simpleImageTranscoder.getSampleSize(encodedImage, rotationOptions2, resizeOptions2);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = sampleSize;
        try {
            Bitmap decodeStream = BitmapFactory.decodeStream(encodedImage.getInputStream(), null, options);
            if (decodeStream == null) {
                FLog.e(TAG, "Couldn't decode the EncodedImage InputStream ! ");
                return new ImageTranscodeResult(2);
            }
            Matrix transformationMatrix = JpegTranscoderUtils.getTransformationMatrix(encodedImage, rotationOptions2);
            if (transformationMatrix != null) {
                try {
                    bitmap = Bitmap.createBitmap(decodeStream, 0, 0, decodeStream.getWidth(), decodeStream.getHeight(), transformationMatrix, false);
                } catch (OutOfMemoryError e2) {
                    e = e2;
                    bitmap = decodeStream;
                    FLog.e(TAG, "Out-Of-Memory during transcode", e);
                    ImageTranscodeResult imageTranscodeResult = new ImageTranscodeResult(2);
                    bitmap.recycle();
                    decodeStream.recycle();
                    return imageTranscodeResult;
                } catch (Throwable th2) {
                    th = th2;
                    bitmap = decodeStream;
                    bitmap.recycle();
                    decodeStream.recycle();
                    throw th;
                }
            } else {
                bitmap = decodeStream;
            }
            try {
                try {
                    bitmap.compress(getOutputFormat(outputFormat), num.intValue(), outputStream);
                    int i = 1;
                    if (sampleSize > 1) {
                        i = 0;
                    }
                    ImageTranscodeResult imageTranscodeResult2 = new ImageTranscodeResult(i);
                    bitmap.recycle();
                    decodeStream.recycle();
                    return imageTranscodeResult2;
                } catch (OutOfMemoryError e3) {
                    e = e3;
                    FLog.e(TAG, "Out-Of-Memory during transcode", e);
                    ImageTranscodeResult imageTranscodeResult3 = new ImageTranscodeResult(2);
                    bitmap.recycle();
                    decodeStream.recycle();
                    return imageTranscodeResult3;
                }
            } catch (Throwable th3) {
                th = th3;
                bitmap.recycle();
                decodeStream.recycle();
                throw th;
            }
        } catch (OutOfMemoryError e4) {
            FLog.e(TAG, "Out-Of-Memory during transcode", e4);
            return new ImageTranscodeResult(2);
        }
    }
}
