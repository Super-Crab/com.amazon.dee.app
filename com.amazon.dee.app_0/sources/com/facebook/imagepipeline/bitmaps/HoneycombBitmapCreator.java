package com.facebook.imagepipeline.bitmaps;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.webp.BitmapCreator;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.FlexByteArrayPool;
import com.facebook.imagepipeline.memory.PoolFactory;
/* loaded from: classes2.dex */
public class HoneycombBitmapCreator implements BitmapCreator {
    private final FlexByteArrayPool mFlexByteArrayPool;
    private final EmptyJpegGenerator mJpegGenerator;

    public HoneycombBitmapCreator(PoolFactory poolFactory) {
        this.mFlexByteArrayPool = poolFactory.getFlexByteArrayPool();
        this.mJpegGenerator = new EmptyJpegGenerator(poolFactory.getPooledByteBufferFactory());
    }

    private static BitmapFactory.Options getBitmapFactoryOptions(int sampleSize, Bitmap.Config bitmapConfig) {
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

    @Override // com.facebook.common.webp.BitmapCreator
    @TargetApi(12)
    public Bitmap createNakedBitmap(int width, int height, Bitmap.Config bitmapConfig) {
        EncodedImage encodedImage;
        CloseableReference<PooledByteBuffer> generate = this.mJpegGenerator.generate((short) width, (short) height);
        CloseableReference<byte[]> closeableReference = null;
        try {
            encodedImage = new EncodedImage(generate);
            try {
                encodedImage.setImageFormat(DefaultImageFormats.JPEG);
                BitmapFactory.Options bitmapFactoryOptions = getBitmapFactoryOptions(encodedImage.getSampleSize(), bitmapConfig);
                int size = generate.get().size();
                closeableReference = this.mFlexByteArrayPool.get(size + 2);
                byte[] bArr = closeableReference.get();
                generate.get().read(0, bArr, 0, size);
                Bitmap bitmap = (Bitmap) Preconditions.checkNotNull(BitmapFactory.decodeByteArray(bArr, 0, size, bitmapFactoryOptions));
                bitmap.setHasAlpha(true);
                bitmap.eraseColor(0);
                CloseableReference.closeSafely((CloseableReference<?>) closeableReference);
                EncodedImage.closeSafely(encodedImage);
                CloseableReference.closeSafely(generate);
                return bitmap;
            } catch (Throwable th) {
                th = th;
                CloseableReference.closeSafely((CloseableReference<?>) closeableReference);
                EncodedImage.closeSafely(encodedImage);
                CloseableReference.closeSafely(generate);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            encodedImage = null;
        }
    }
}
