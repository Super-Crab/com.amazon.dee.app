package com.facebook.imagepipeline.platform;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.graphics.Rect;
import android.os.Build;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Pools;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.streams.LimitedInputStream;
import com.facebook.common.streams.TailAppendingInputStream;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.BitmapPool;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
@ThreadSafe
@TargetApi(11)
/* loaded from: classes2.dex */
public abstract class DefaultDecoder implements PlatformDecoder {
    private static final int DECODE_BUFFER_SIZE = 16384;
    private final BitmapPool mBitmapPool;
    @VisibleForTesting
    final Pools.SynchronizedPool<ByteBuffer> mDecodeBuffers;
    @Nullable
    private final PreverificationHelper mPreverificationHelper;
    private static final Class<?> TAG = DefaultDecoder.class;
    private static final byte[] EOI_TAIL = {-1, -39};

    public DefaultDecoder(BitmapPool bitmapPool, int maxNumThreads, Pools.SynchronizedPool decodeBuffers) {
        int i = Build.VERSION.SDK_INT;
        this.mPreverificationHelper = new PreverificationHelper();
        this.mBitmapPool = bitmapPool;
        this.mDecodeBuffers = decodeBuffers;
        for (int i2 = 0; i2 < maxNumThreads; i2++) {
            this.mDecodeBuffers.release(ByteBuffer.allocate(16384));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x009c A[Catch: all -> 0x00c6, RuntimeException -> 0x00c8, IllegalArgumentException -> 0x00d1, TryCatch #7 {IllegalArgumentException -> 0x00d1, RuntimeException -> 0x00c8, blocks: (B:26:0x0067, B:31:0x0080, B:45:0x00a3, B:38:0x0095, B:41:0x009c, B:42:0x009f), top: B:77:0x0067, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00a3 A[Catch: all -> 0x00c6, RuntimeException -> 0x00c8, IllegalArgumentException -> 0x00d1, TRY_LEAVE, TryCatch #7 {IllegalArgumentException -> 0x00d1, RuntimeException -> 0x00c8, blocks: (B:26:0x0067, B:31:0x0080, B:45:0x00a3, B:38:0x0095, B:41:0x009c, B:42:0x009f), top: B:77:0x0067, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00ae A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.facebook.common.references.CloseableReference<android.graphics.Bitmap> decodeFromStream(java.io.InputStream r8, android.graphics.BitmapFactory.Options r9, @javax.annotation.Nullable android.graphics.Rect r10, @javax.annotation.Nullable final android.graphics.ColorSpace r11) {
        /*
            Method dump skipped, instructions count: 256
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.platform.DefaultDecoder.decodeFromStream(java.io.InputStream, android.graphics.BitmapFactory$Options, android.graphics.Rect, android.graphics.ColorSpace):com.facebook.common.references.CloseableReference");
    }

    private static BitmapFactory.Options getDecodeOptionsForStream(EncodedImage encodedImage, Bitmap.Config bitmapConfig) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = encodedImage.getSampleSize();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(encodedImage.getInputStream(), null, options);
        if (options.outWidth != -1 && options.outHeight != -1) {
            options.inJustDecodeBounds = false;
            options.inDither = true;
            options.inPreferredConfig = bitmapConfig;
            options.inMutable = true;
            return options;
        }
        throw new IllegalArgumentException();
    }

    @Override // com.facebook.imagepipeline.platform.PlatformDecoder
    public CloseableReference<Bitmap> decodeFromEncodedImage(EncodedImage encodedImage, Bitmap.Config bitmapConfig, @Nullable Rect regionToDecode) {
        return decodeFromEncodedImageWithColorSpace(encodedImage, bitmapConfig, regionToDecode, null);
    }

    @Override // com.facebook.imagepipeline.platform.PlatformDecoder
    public CloseableReference<Bitmap> decodeFromEncodedImageWithColorSpace(EncodedImage encodedImage, Bitmap.Config bitmapConfig, @Nullable Rect regionToDecode, @Nullable final ColorSpace colorSpace) {
        BitmapFactory.Options decodeOptionsForStream = getDecodeOptionsForStream(encodedImage, bitmapConfig);
        boolean z = decodeOptionsForStream.inPreferredConfig != Bitmap.Config.ARGB_8888;
        try {
            return decodeFromStream((InputStream) Preconditions.checkNotNull(encodedImage.getInputStream()), decodeOptionsForStream, regionToDecode, colorSpace);
        } catch (RuntimeException e) {
            if (z) {
                return decodeFromEncodedImageWithColorSpace(encodedImage, Bitmap.Config.ARGB_8888, regionToDecode, colorSpace);
            }
            throw e;
        }
    }

    @Override // com.facebook.imagepipeline.platform.PlatformDecoder
    public CloseableReference<Bitmap> decodeJPEGFromEncodedImage(EncodedImage encodedImage, Bitmap.Config bitmapConfig, @Nullable Rect regionToDecode, int length) {
        return decodeJPEGFromEncodedImageWithColorSpace(encodedImage, bitmapConfig, regionToDecode, length, null);
    }

    @Override // com.facebook.imagepipeline.platform.PlatformDecoder
    public CloseableReference<Bitmap> decodeJPEGFromEncodedImageWithColorSpace(EncodedImage encodedImage, Bitmap.Config bitmapConfig, @Nullable Rect regionToDecode, int length, @Nullable final ColorSpace colorSpace) {
        boolean isCompleteAt = encodedImage.isCompleteAt(length);
        BitmapFactory.Options decodeOptionsForStream = getDecodeOptionsForStream(encodedImage, bitmapConfig);
        InputStream inputStream = encodedImage.getInputStream();
        Preconditions.checkNotNull(inputStream);
        if (encodedImage.getSize() > length) {
            inputStream = new LimitedInputStream(inputStream, length);
        }
        InputStream tailAppendingInputStream = !isCompleteAt ? new TailAppendingInputStream(inputStream, EOI_TAIL) : inputStream;
        boolean z = decodeOptionsForStream.inPreferredConfig != Bitmap.Config.ARGB_8888;
        try {
            try {
                CloseableReference<Bitmap> decodeFromStream = decodeFromStream(tailAppendingInputStream, decodeOptionsForStream, regionToDecode, colorSpace);
                try {
                    tailAppendingInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return decodeFromStream;
            } catch (RuntimeException e2) {
                if (z) {
                    CloseableReference<Bitmap> decodeJPEGFromEncodedImageWithColorSpace = decodeJPEGFromEncodedImageWithColorSpace(encodedImage, Bitmap.Config.ARGB_8888, regionToDecode, length, colorSpace);
                    try {
                        tailAppendingInputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    return decodeJPEGFromEncodedImageWithColorSpace;
                }
                throw e2;
            }
        } catch (Throwable th) {
            try {
                tailAppendingInputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            throw th;
        }
    }

    protected CloseableReference<Bitmap> decodeStaticImageFromStream(InputStream inputStream, BitmapFactory.Options options, @Nullable Rect regionToDecode) {
        return decodeFromStream(inputStream, options, regionToDecode, null);
    }

    public abstract int getBitmapSize(final int width, final int height, final BitmapFactory.Options options);
}
