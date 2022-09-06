package com.facebook.imagepipeline.nativecode;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.graphics.Rect;
import android.os.Build;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.common.TooManyBitmapsException;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.BitmapCounter;
import com.facebook.imagepipeline.memory.BitmapCounterProvider;
import com.facebook.imagepipeline.platform.PlatformDecoder;
import com.facebook.imageutils.BitmapUtil;
import java.util.Locale;
import javax.annotation.Nullable;
@DoNotStrip
/* loaded from: classes2.dex */
public abstract class DalvikPurgeableDecoder implements PlatformDecoder {
    protected static final byte[] EOI;
    private final BitmapCounter mUnpooledBitmapsCounter = BitmapCounterProvider.get();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class OreoUtils {
        private OreoUtils() {
        }

        @TargetApi(26)
        static void setColorSpace(BitmapFactory.Options options, @Nullable ColorSpace colorSpace) {
            if (colorSpace == null) {
                colorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
            }
            options.inPreferredColorSpace = colorSpace;
        }
    }

    static {
        ImagePipelineNativeLoader.load();
        EOI = new byte[]{-1, -39};
    }

    @VisibleForTesting
    public static boolean endsWithEOI(CloseableReference<PooledByteBuffer> bytesRef, int length) {
        PooledByteBuffer pooledByteBuffer = bytesRef.get();
        return length >= 2 && pooledByteBuffer.read(length + (-2)) == -1 && pooledByteBuffer.read(length - 1) == -39;
    }

    @VisibleForTesting
    public static BitmapFactory.Options getBitmapFactoryOptions(int sampleSize, Bitmap.Config bitmapConfig) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = true;
        options.inPreferredConfig = bitmapConfig;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = sampleSize;
        int i = Build.VERSION.SDK_INT;
        options.inMutable = true;
        return options;
    }

    @DoNotStrip
    private static native void nativePinBitmap(Bitmap bitmap);

    protected abstract Bitmap decodeByteArrayAsPurgeable(CloseableReference<PooledByteBuffer> bytesRef, BitmapFactory.Options options);

    @Override // com.facebook.imagepipeline.platform.PlatformDecoder
    public CloseableReference<Bitmap> decodeFromEncodedImage(EncodedImage encodedImage, Bitmap.Config bitmapConfig, @Nullable Rect regionToDecode) {
        return decodeFromEncodedImageWithColorSpace(encodedImage, bitmapConfig, regionToDecode, null);
    }

    @Override // com.facebook.imagepipeline.platform.PlatformDecoder
    public CloseableReference<Bitmap> decodeFromEncodedImageWithColorSpace(final EncodedImage encodedImage, Bitmap.Config bitmapConfig, @Nullable Rect regionToDecode, @Nullable final ColorSpace colorSpace) {
        BitmapFactory.Options bitmapFactoryOptions = getBitmapFactoryOptions(encodedImage.getSampleSize(), bitmapConfig);
        int i = Build.VERSION.SDK_INT;
        OreoUtils.setColorSpace(bitmapFactoryOptions, colorSpace);
        CloseableReference<PooledByteBuffer> byteBufferRef = encodedImage.getByteBufferRef();
        Preconditions.checkNotNull(byteBufferRef);
        try {
            return pinBitmap(decodeByteArrayAsPurgeable(byteBufferRef, bitmapFactoryOptions));
        } finally {
            CloseableReference.closeSafely(byteBufferRef);
        }
    }

    protected abstract Bitmap decodeJPEGByteArrayAsPurgeable(CloseableReference<PooledByteBuffer> bytesRef, int length, BitmapFactory.Options options);

    @Override // com.facebook.imagepipeline.platform.PlatformDecoder
    public CloseableReference<Bitmap> decodeJPEGFromEncodedImage(EncodedImage encodedImage, Bitmap.Config bitmapConfig, @Nullable Rect regionToDecode, int length) {
        return decodeJPEGFromEncodedImageWithColorSpace(encodedImage, bitmapConfig, regionToDecode, length, null);
    }

    @Override // com.facebook.imagepipeline.platform.PlatformDecoder
    public CloseableReference<Bitmap> decodeJPEGFromEncodedImageWithColorSpace(final EncodedImage encodedImage, Bitmap.Config bitmapConfig, @Nullable Rect regionToDecode, int length, @Nullable final ColorSpace colorSpace) {
        BitmapFactory.Options bitmapFactoryOptions = getBitmapFactoryOptions(encodedImage.getSampleSize(), bitmapConfig);
        int i = Build.VERSION.SDK_INT;
        OreoUtils.setColorSpace(bitmapFactoryOptions, colorSpace);
        CloseableReference<PooledByteBuffer> byteBufferRef = encodedImage.getByteBufferRef();
        Preconditions.checkNotNull(byteBufferRef);
        try {
            return pinBitmap(decodeJPEGByteArrayAsPurgeable(byteBufferRef, length, bitmapFactoryOptions));
        } finally {
            CloseableReference.closeSafely(byteBufferRef);
        }
    }

    public CloseableReference<Bitmap> pinBitmap(Bitmap bitmap) {
        Preconditions.checkNotNull(bitmap);
        try {
            nativePinBitmap(bitmap);
            if (this.mUnpooledBitmapsCounter.increase(bitmap)) {
                return CloseableReference.of(bitmap, this.mUnpooledBitmapsCounter.getReleaser());
            }
            int sizeInBytes = BitmapUtil.getSizeInBytes(bitmap);
            bitmap.recycle();
            throw new TooManyBitmapsException(String.format(Locale.US, "Attempted to pin a bitmap of size %d bytes. The current pool count is %d, the current pool size is %d bytes. The current pool max count is %d, the current pool max size is %d bytes.", Integer.valueOf(sizeInBytes), Integer.valueOf(this.mUnpooledBitmapsCounter.getCount()), Long.valueOf(this.mUnpooledBitmapsCounter.getSize()), Integer.valueOf(this.mUnpooledBitmapsCounter.getMaxCount()), Integer.valueOf(this.mUnpooledBitmapsCounter.getMaxSize())));
        } catch (Exception e) {
            bitmap.recycle();
            throw Throwables.propagate(e);
        }
    }
}
