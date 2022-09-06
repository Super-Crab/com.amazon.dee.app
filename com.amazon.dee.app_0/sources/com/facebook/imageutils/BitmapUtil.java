package com.facebook.imageutils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.util.Pair;
import androidx.core.util.Pools;
import com.facebook.common.internal.Preconditions;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public final class BitmapUtil {
    public static final int ALPHA_8_BYTES_PER_PIXEL = 1;
    public static final int ARGB_4444_BYTES_PER_PIXEL = 2;
    public static final int ARGB_8888_BYTES_PER_PIXEL = 4;
    private static final Pools.SynchronizedPool<ByteBuffer> DECODE_BUFFERS = new Pools.SynchronizedPool<>(12);
    private static final int DECODE_BUFFER_SIZE = 16384;
    public static final float MAX_BITMAP_SIZE = 2048.0f;
    private static final int POOL_SIZE = 12;
    public static final int RGBA_F16_BYTES_PER_PIXEL = 8;
    public static final int RGB_565_BYTES_PER_PIXEL = 2;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.facebook.imageutils.BitmapUtil$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Bitmap$Config = new int[Bitmap.Config.values().length];

        static {
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ARGB_8888.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ALPHA_8.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ARGB_4444.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.RGB_565.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.RGBA_F16.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.HARDWARE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    @Nullable
    public static Pair<Integer, Integer> decodeDimensions(byte[] bytes) {
        return decodeDimensions(new ByteArrayInputStream(bytes));
    }

    public static ImageMetaData decodeDimensionsAndColorSpace(InputStream is) {
        Preconditions.checkNotNull(is);
        ByteBuffer acquire = DECODE_BUFFERS.acquire();
        if (acquire == null) {
            acquire = ByteBuffer.allocate(16384);
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            options.inTempStorage = acquire.array();
            BitmapFactory.decodeStream(is, null, options);
            int i = Build.VERSION.SDK_INT;
            return new ImageMetaData(options.outWidth, options.outHeight, options.outColorSpace);
        } finally {
            DECODE_BUFFERS.release(acquire);
        }
    }

    public static int getPixelSizeForBitmapConfig(Bitmap.Config bitmapConfig) {
        switch (AnonymousClass1.$SwitchMap$android$graphics$Bitmap$Config[bitmapConfig.ordinal()]) {
            case 1:
                return 4;
            case 2:
                return 1;
            case 3:
            case 4:
                return 2;
            case 5:
                return 8;
            case 6:
                return 4;
            default:
                throw new UnsupportedOperationException("The provided Bitmap.Config is not supported");
        }
    }

    public static int getSizeInByteForBitmap(int width, int height, Bitmap.Config bitmapConfig) {
        return getPixelSizeForBitmapConfig(bitmapConfig) * width * height;
    }

    @SuppressLint({"NewApi"})
    public static int getSizeInBytes(@Nullable Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        int i = Build.VERSION.SDK_INT;
        try {
            return bitmap.getAllocationByteCount();
        } catch (NullPointerException unused) {
            int i2 = Build.VERSION.SDK_INT;
            return bitmap.getByteCount();
        }
    }

    @Nullable
    public static Pair<Integer, Integer> decodeDimensions(Uri uri) {
        Preconditions.checkNotNull(uri);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(uri.getPath(), options);
        int i = options.outWidth;
        if (i == -1 || options.outHeight == -1) {
            return null;
        }
        return new Pair<>(Integer.valueOf(i), Integer.valueOf(options.outHeight));
    }

    @Nullable
    public static Pair<Integer, Integer> decodeDimensions(InputStream is) {
        Preconditions.checkNotNull(is);
        ByteBuffer acquire = DECODE_BUFFERS.acquire();
        if (acquire == null) {
            acquire = ByteBuffer.allocate(16384);
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            options.inTempStorage = acquire.array();
            Pair<Integer, Integer> pair = null;
            BitmapFactory.decodeStream(is, null, options);
            if (options.outWidth != -1 && options.outHeight != -1) {
                pair = new Pair<>(Integer.valueOf(options.outWidth), Integer.valueOf(options.outHeight));
            }
            return pair;
        } finally {
            DECODE_BUFFERS.release(acquire);
        }
    }
}
