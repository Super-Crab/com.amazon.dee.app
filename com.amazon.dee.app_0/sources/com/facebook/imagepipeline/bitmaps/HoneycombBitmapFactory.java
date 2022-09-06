package com.facebook.imagepipeline.bitmaps;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imagepipeline.core.CloseableReferenceFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.platform.PlatformDecoder;
import javax.annotation.concurrent.ThreadSafe;
@ThreadSafe
@TargetApi(11)
/* loaded from: classes2.dex */
public class HoneycombBitmapFactory extends PlatformBitmapFactory {
    private static final String TAG = "HoneycombBitmapFactory";
    private final CloseableReferenceFactory mCloseableReferenceFactory;
    private boolean mImmutableBitmapFallback;
    private final EmptyJpegGenerator mJpegGenerator;
    private final PlatformDecoder mPurgeableDecoder;

    public HoneycombBitmapFactory(EmptyJpegGenerator jpegGenerator, PlatformDecoder purgeableDecoder, CloseableReferenceFactory closeableReferenceFactory) {
        this.mJpegGenerator = jpegGenerator;
        this.mPurgeableDecoder = purgeableDecoder;
        this.mCloseableReferenceFactory = closeableReferenceFactory;
    }

    private CloseableReference<Bitmap> createFallbackBitmap(int width, int height, Bitmap.Config bitmapConfig) {
        return this.mCloseableReferenceFactory.create(Bitmap.createBitmap(width, height, bitmapConfig), SimpleBitmapReleaser.getInstance());
    }

    @Override // com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory
    @TargetApi(12)
    public CloseableReference<Bitmap> createBitmapInternal(int width, int height, Bitmap.Config bitmapConfig) {
        if (this.mImmutableBitmapFallback) {
            return createFallbackBitmap(width, height, bitmapConfig);
        }
        CloseableReference<PooledByteBuffer> generate = this.mJpegGenerator.generate((short) width, (short) height);
        try {
            EncodedImage encodedImage = new EncodedImage(generate);
            encodedImage.setImageFormat(DefaultImageFormats.JPEG);
            CloseableReference<Bitmap> decodeJPEGFromEncodedImage = this.mPurgeableDecoder.decodeJPEGFromEncodedImage(encodedImage, bitmapConfig, null, generate.get().size());
            if (!decodeJPEGFromEncodedImage.get().isMutable()) {
                CloseableReference.closeSafely(decodeJPEGFromEncodedImage);
                this.mImmutableBitmapFallback = true;
                FLog.wtf(TAG, "Immutable bitmap returned by decoder");
                CloseableReference<Bitmap> createFallbackBitmap = createFallbackBitmap(width, height, bitmapConfig);
                EncodedImage.closeSafely(encodedImage);
                return createFallbackBitmap;
            }
            decodeJPEGFromEncodedImage.get().setHasAlpha(true);
            decodeJPEGFromEncodedImage.get().eraseColor(0);
            EncodedImage.closeSafely(encodedImage);
            return decodeJPEGFromEncodedImage;
        } finally {
            generate.close();
        }
    }
}
