package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParserUtils;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
/* loaded from: classes6.dex */
public final class Downsampler {
    private static final int MARK_POSITION = 10485760;
    static final String TAG = "Downsampler";
    private final BitmapPool bitmapPool;
    private final ArrayPool byteArrayPool;
    private final DisplayMetrics displayMetrics;
    private final HardwareConfigState hardwareConfigState = HardwareConfigState.getInstance();
    private final List<ImageHeaderParser> parsers;
    public static final Option<DecodeFormat> DECODE_FORMAT = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeFormat", DecodeFormat.DEFAULT);
    public static final Option<DownsampleStrategy> DOWNSAMPLE_STRATEGY = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.DownsampleStrategy", DownsampleStrategy.DEFAULT);
    public static final Option<Boolean> FIX_BITMAP_SIZE_TO_REQUESTED_DIMENSIONS = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.FixBitmapSize", false);
    public static final Option<Boolean> ALLOW_HARDWARE_CONFIG = Option.memory("com.bumtpech.glide.load.resource.bitmap.Downsampler.AllowHardwareDecode");
    private static final String WBMP_MIME_TYPE = "image/vnd.wap.wbmp";
    private static final String ICO_MIME_TYPE = "image/x-ico";
    private static final Set<String> NO_DOWNSAMPLE_PRE_N_MIME_TYPES = Collections.unmodifiableSet(new HashSet(Arrays.asList(WBMP_MIME_TYPE, ICO_MIME_TYPE)));
    private static final DecodeCallbacks EMPTY_CALLBACKS = new DecodeCallbacks() { // from class: com.bumptech.glide.load.resource.bitmap.Downsampler.1
        @Override // com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeCallbacks
        public void onDecodeComplete(BitmapPool bitmapPool, Bitmap bitmap) {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeCallbacks
        public void onObtainBounds() {
        }
    };
    private static final Set<ImageHeaderParser.ImageType> TYPES_THAT_USE_POOL_PRE_KITKAT = Collections.unmodifiableSet(EnumSet.of(ImageHeaderParser.ImageType.JPEG, ImageHeaderParser.ImageType.PNG_A, ImageHeaderParser.ImageType.PNG));
    private static final Queue<BitmapFactory.Options> OPTIONS_QUEUE = Util.createQueue(0);

    /* loaded from: classes6.dex */
    public interface DecodeCallbacks {
        void onDecodeComplete(BitmapPool bitmapPool, Bitmap bitmap) throws IOException;

        void onObtainBounds();
    }

    public Downsampler(List<ImageHeaderParser> list, DisplayMetrics displayMetrics, BitmapPool bitmapPool, ArrayPool arrayPool) {
        this.parsers = list;
        this.displayMetrics = (DisplayMetrics) Preconditions.checkNotNull(displayMetrics);
        this.bitmapPool = (BitmapPool) Preconditions.checkNotNull(bitmapPool);
        this.byteArrayPool = (ArrayPool) Preconditions.checkNotNull(arrayPool);
    }

    private static int adjustTargetDensityForError(double d) {
        int densityMultiplier = getDensityMultiplier(d);
        int round = round(densityMultiplier * d);
        return round((d / (round / densityMultiplier)) * round);
    }

    private void calculateConfig(InputStream inputStream, DecodeFormat decodeFormat, boolean z, boolean z2, BitmapFactory.Options options, int i, int i2) {
        if (this.hardwareConfigState.setHardwareConfigIfAllowed(i, i2, options, decodeFormat, z, z2)) {
            return;
        }
        if (decodeFormat != DecodeFormat.PREFER_ARGB_8888 && decodeFormat != DecodeFormat.PREFER_ARGB_8888_DISALLOW_HARDWARE) {
            int i3 = Build.VERSION.SDK_INT;
            boolean z3 = false;
            try {
                z3 = ImageHeaderParserUtils.getType(this.parsers, inputStream, this.byteArrayPool).hasAlpha();
            } catch (IOException unused) {
                if (Log.isLoggable(TAG, 3)) {
                    String str = "Cannot determine whether the image has alpha or not from header, format " + decodeFormat;
                }
            }
            options.inPreferredConfig = z3 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
            if (options.inPreferredConfig != Bitmap.Config.RGB_565) {
                return;
            }
            options.inDither = true;
            return;
        }
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
    }

    private static void calculateScaling(ImageHeaderParser.ImageType imageType, InputStream inputStream, DecodeCallbacks decodeCallbacks, BitmapPool bitmapPool, DownsampleStrategy downsampleStrategy, int i, int i2, int i3, int i4, int i5, BitmapFactory.Options options) throws IOException {
        float scaleFactor;
        int min;
        int floor;
        int floor2;
        if (i2 <= 0 || i3 <= 0) {
            if (!Log.isLoggable(TAG, 3)) {
                return;
            }
            String str = "Unable to determine dimensions for: " + imageType + " with target [" + i4 + ReactProperties.HereMapMarker.X + i5 + "]";
            return;
        }
        if (i != 90 && i != 270) {
            scaleFactor = downsampleStrategy.getScaleFactor(i2, i3, i4, i5);
        } else {
            scaleFactor = downsampleStrategy.getScaleFactor(i3, i2, i4, i5);
        }
        if (scaleFactor > 0.0f) {
            DownsampleStrategy.SampleSizeRounding sampleSizeRounding = downsampleStrategy.getSampleSizeRounding(i2, i3, i4, i5);
            if (sampleSizeRounding != null) {
                float f = i2;
                float f2 = i3;
                int round = i2 / round(scaleFactor * f);
                int round2 = i3 / round(scaleFactor * f2);
                if (sampleSizeRounding == DownsampleStrategy.SampleSizeRounding.MEMORY) {
                    min = Math.max(round, round2);
                } else {
                    min = Math.min(round, round2);
                }
                int i6 = Build.VERSION.SDK_INT;
                int max = Math.max(1, Integer.highestOneBit(min));
                if (sampleSizeRounding == DownsampleStrategy.SampleSizeRounding.MEMORY && max < 1.0f / scaleFactor) {
                    max <<= 1;
                }
                options.inSampleSize = max;
                if (imageType == ImageHeaderParser.ImageType.JPEG) {
                    float min2 = Math.min(max, 8);
                    floor = (int) Math.ceil(f / min2);
                    floor2 = (int) Math.ceil(f2 / min2);
                    int i7 = max / 8;
                    if (i7 > 0) {
                        floor /= i7;
                        floor2 /= i7;
                    }
                } else if (imageType != ImageHeaderParser.ImageType.PNG && imageType != ImageHeaderParser.ImageType.PNG_A) {
                    if (imageType != ImageHeaderParser.ImageType.WEBP && imageType != ImageHeaderParser.ImageType.WEBP_A) {
                        if (i2 % max == 0 && i3 % max == 0) {
                            floor = i2 / max;
                            floor2 = i3 / max;
                        } else {
                            int[] dimensions = getDimensions(inputStream, options, decodeCallbacks, bitmapPool);
                            int i8 = dimensions[0];
                            floor2 = dimensions[1];
                            floor = i8;
                        }
                    } else {
                        int i9 = Build.VERSION.SDK_INT;
                        float f3 = max;
                        floor = Math.round(f / f3);
                        floor2 = Math.round(f2 / f3);
                    }
                } else {
                    float f4 = max;
                    floor = (int) Math.floor(f / f4);
                    floor2 = (int) Math.floor(f2 / f4);
                }
                double scaleFactor2 = downsampleStrategy.getScaleFactor(floor, floor2, i4, i5);
                int i10 = Build.VERSION.SDK_INT;
                options.inTargetDensity = adjustTargetDensityForError(scaleFactor2);
                options.inDensity = getDensityMultiplier(scaleFactor2);
                if (isScaling(options)) {
                    options.inScaled = true;
                } else {
                    options.inTargetDensity = 0;
                    options.inDensity = 0;
                }
                if (!Log.isLoggable(TAG, 2)) {
                    return;
                }
                StringBuilder outline110 = GeneratedOutlineSupport1.outline110("Calculate scaling, source: [", i2, ReactProperties.HereMapMarker.X, i3, "], target: [");
                GeneratedOutlineSupport1.outline175(outline110, i4, ReactProperties.HereMapMarker.X, i5, "], power of two scaled: [");
                GeneratedOutlineSupport1.outline175(outline110, floor, ReactProperties.HereMapMarker.X, floor2, "], exact scale factor: ");
                outline110.append(scaleFactor);
                outline110.append(", power of 2 sample size: ");
                outline110.append(max);
                outline110.append(", adjusted scale factor: ");
                outline110.append(scaleFactor2);
                outline110.append(", target density: ");
                outline110.append(options.inTargetDensity);
                outline110.append(", density: ");
                outline110.append(options.inDensity);
                outline110.toString();
                return;
            }
            throw new IllegalArgumentException("Cannot round with null rounding");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Cannot scale with factor: ");
        sb.append(scaleFactor);
        sb.append(" from: ");
        sb.append(downsampleStrategy);
        sb.append(", source: [");
        GeneratedOutlineSupport1.outline175(sb, i2, ReactProperties.HereMapMarker.X, i3, "], target: [");
        sb.append(i4);
        sb.append(ReactProperties.HereMapMarker.X);
        sb.append(i5);
        sb.append("]");
        throw new IllegalArgumentException(sb.toString());
    }

    private Bitmap decodeFromWrappedStreams(InputStream inputStream, BitmapFactory.Options options, DownsampleStrategy downsampleStrategy, DecodeFormat decodeFormat, boolean z, int i, int i2, boolean z2, DecodeCallbacks decodeCallbacks) throws IOException {
        Downsampler downsampler;
        int round;
        int round2;
        int i3;
        long logTime = LogTime.getLogTime();
        int[] dimensions = getDimensions(inputStream, options, decodeCallbacks, this.bitmapPool);
        int i4 = dimensions[0];
        int i5 = dimensions[1];
        String str = options.outMimeType;
        boolean z3 = (i4 == -1 || i5 == -1) ? false : z;
        int orientation = ImageHeaderParserUtils.getOrientation(this.parsers, inputStream, this.byteArrayPool);
        int exifOrientationDegrees = TransformationUtils.getExifOrientationDegrees(orientation);
        boolean isExifOrientationRequired = TransformationUtils.isExifOrientationRequired(orientation);
        int i6 = i == Integer.MIN_VALUE ? i4 : i;
        int i7 = i2 == Integer.MIN_VALUE ? i5 : i2;
        ImageHeaderParser.ImageType type = ImageHeaderParserUtils.getType(this.parsers, inputStream, this.byteArrayPool);
        calculateScaling(type, inputStream, decodeCallbacks, this.bitmapPool, downsampleStrategy, exifOrientationDegrees, i4, i5, i6, i7, options);
        calculateConfig(inputStream, decodeFormat, z3, isExifOrientationRequired, options, i6, i7);
        int i8 = Build.VERSION.SDK_INT;
        if (options.inSampleSize == 1 || 1 != 0) {
            downsampler = this;
            if (downsampler.shouldUsePool(type)) {
                if (i4 < 0 || i5 < 0 || !z2 || 1 == 0) {
                    float f = isScaling(options) ? options.inTargetDensity / options.inDensity : 1.0f;
                    int i9 = options.inSampleSize;
                    float f2 = i9;
                    round = Math.round(((int) Math.ceil(i4 / f2)) * f);
                    round2 = Math.round(((int) Math.ceil(i5 / f2)) * f);
                    if (Log.isLoggable(TAG, 2)) {
                        StringBuilder outline110 = GeneratedOutlineSupport1.outline110("Calculated target [", round, ReactProperties.HereMapMarker.X, round2, "] for source [");
                        GeneratedOutlineSupport1.outline175(outline110, i4, ReactProperties.HereMapMarker.X, i5, "], sampleSize: ");
                        outline110.append(i9);
                        outline110.append(", targetDensity: ");
                        outline110.append(options.inTargetDensity);
                        outline110.append(", density: ");
                        outline110.append(options.inDensity);
                        outline110.append(", density multiplier: ");
                        outline110.append(f);
                        outline110.toString();
                    }
                } else {
                    round = i6;
                    round2 = i7;
                }
                if (round > 0 && round2 > 0) {
                    setInBitmap(options, downsampler.bitmapPool, round, round2);
                }
            }
        } else {
            downsampler = this;
        }
        Bitmap decodeStream = decodeStream(inputStream, options, decodeCallbacks, downsampler.bitmapPool);
        decodeCallbacks.onDecodeComplete(downsampler.bitmapPool, decodeStream);
        if (Log.isLoggable(TAG, 2)) {
            i3 = orientation;
            logDecode(i4, i5, str, options, decodeStream, i, i2, logTime);
        } else {
            i3 = orientation;
        }
        Bitmap bitmap = null;
        if (decodeStream != null) {
            decodeStream.setDensity(downsampler.displayMetrics.densityDpi);
            bitmap = TransformationUtils.rotateImageExif(downsampler.bitmapPool, decodeStream, i3);
            if (!decodeStream.equals(bitmap)) {
                downsampler.bitmapPool.put(decodeStream);
            }
        }
        return bitmap;
    }

    private static Bitmap decodeStream(InputStream inputStream, BitmapFactory.Options options, DecodeCallbacks decodeCallbacks, BitmapPool bitmapPool) throws IOException {
        if (options.inJustDecodeBounds) {
            inputStream.mark(10485760);
        } else {
            decodeCallbacks.onObtainBounds();
        }
        int i = options.outWidth;
        int i2 = options.outHeight;
        String str = options.outMimeType;
        TransformationUtils.getBitmapDrawableLock().lock();
        try {
            try {
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, null, options);
                TransformationUtils.getBitmapDrawableLock().unlock();
                if (options.inJustDecodeBounds) {
                    inputStream.reset();
                }
                return decodeStream;
            } catch (IllegalArgumentException e) {
                IOException newIoExceptionForInBitmapAssertion = newIoExceptionForInBitmapAssertion(e, i, i2, str, options);
                Log.isLoggable(TAG, 3);
                if (options.inBitmap != null) {
                    try {
                        inputStream.reset();
                        bitmapPool.put(options.inBitmap);
                        options.inBitmap = null;
                        Bitmap decodeStream2 = decodeStream(inputStream, options, decodeCallbacks, bitmapPool);
                        TransformationUtils.getBitmapDrawableLock().unlock();
                        return decodeStream2;
                    } catch (IOException unused) {
                        throw newIoExceptionForInBitmapAssertion;
                    }
                }
                throw newIoExceptionForInBitmapAssertion;
            }
        } catch (Throwable th) {
            TransformationUtils.getBitmapDrawableLock().unlock();
            throw th;
        }
    }

    @Nullable
    @TargetApi(19)
    private static String getBitmapString(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        int i = Build.VERSION.SDK_INT;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(" (");
        outline107.append(bitmap.getAllocationByteCount());
        outline107.append(")");
        String sb = outline107.toString();
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("[");
        outline1072.append(bitmap.getWidth());
        outline1072.append(ReactProperties.HereMapMarker.X);
        outline1072.append(bitmap.getHeight());
        outline1072.append("] ");
        outline1072.append(bitmap.getConfig());
        outline1072.append(sb);
        return outline1072.toString();
    }

    private static synchronized BitmapFactory.Options getDefaultOptions() {
        BitmapFactory.Options poll;
        synchronized (Downsampler.class) {
            synchronized (OPTIONS_QUEUE) {
                poll = OPTIONS_QUEUE.poll();
            }
            if (poll == null) {
                poll = new BitmapFactory.Options();
                resetOptions(poll);
            }
        }
        return poll;
    }

    private static int getDensityMultiplier(double d) {
        if (d > 1.0d) {
            d = 1.0d / d;
        }
        return (int) Math.round(d * 2.147483647E9d);
    }

    private static int[] getDimensions(InputStream inputStream, BitmapFactory.Options options, DecodeCallbacks decodeCallbacks, BitmapPool bitmapPool) throws IOException {
        options.inJustDecodeBounds = true;
        decodeStream(inputStream, options, decodeCallbacks, bitmapPool);
        options.inJustDecodeBounds = false;
        return new int[]{options.outWidth, options.outHeight};
    }

    private static String getInBitmapString(BitmapFactory.Options options) {
        return getBitmapString(options.inBitmap);
    }

    private static boolean isScaling(BitmapFactory.Options options) {
        int i;
        int i2 = options.inTargetDensity;
        return i2 > 0 && (i = options.inDensity) > 0 && i2 != i;
    }

    private static void logDecode(int i, int i2, String str, BitmapFactory.Options options, Bitmap bitmap, int i3, int i4, long j) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Decoded ");
        outline107.append(getBitmapString(bitmap));
        outline107.append(" from [");
        outline107.append(i);
        outline107.append(ReactProperties.HereMapMarker.X);
        outline107.append(i2);
        outline107.append("] ");
        outline107.append(str);
        outline107.append(" with inBitmap ");
        outline107.append(getInBitmapString(options));
        outline107.append(" for [");
        outline107.append(i3);
        outline107.append(ReactProperties.HereMapMarker.X);
        outline107.append(i4);
        outline107.append("], sample size: ");
        outline107.append(options.inSampleSize);
        outline107.append(", density: ");
        outline107.append(options.inDensity);
        outline107.append(", target density: ");
        outline107.append(options.inTargetDensity);
        outline107.append(", thread: ");
        outline107.append(Thread.currentThread().getName());
        outline107.append(", duration: ");
        outline107.append(LogTime.getElapsedMillis(j));
        outline107.toString();
    }

    private static IOException newIoExceptionForInBitmapAssertion(IllegalArgumentException illegalArgumentException, int i, int i2, String str, BitmapFactory.Options options) {
        StringBuilder outline110 = GeneratedOutlineSupport1.outline110("Exception decoding bitmap, outWidth: ", i, ", outHeight: ", i2, ", outMimeType: ");
        outline110.append(str);
        outline110.append(", inBitmap: ");
        outline110.append(getInBitmapString(options));
        return new IOException(outline110.toString(), illegalArgumentException);
    }

    private static void releaseOptions(BitmapFactory.Options options) {
        resetOptions(options);
        synchronized (OPTIONS_QUEUE) {
            OPTIONS_QUEUE.offer(options);
        }
    }

    private static void resetOptions(BitmapFactory.Options options) {
        options.inTempStorage = null;
        options.inDither = false;
        options.inScaled = false;
        options.inSampleSize = 1;
        options.inPreferredConfig = null;
        options.inJustDecodeBounds = false;
        options.inDensity = 0;
        options.inTargetDensity = 0;
        options.outWidth = 0;
        options.outHeight = 0;
        options.outMimeType = null;
        options.inBitmap = null;
        options.inMutable = true;
    }

    private static int round(double d) {
        return (int) (d + 0.5d);
    }

    @TargetApi(26)
    private static void setInBitmap(BitmapFactory.Options options, BitmapPool bitmapPool, int i, int i2) {
        int i3 = Build.VERSION.SDK_INT;
        Bitmap.Config config = options.inPreferredConfig;
        if (config == Bitmap.Config.HARDWARE) {
            return;
        }
        Bitmap.Config config2 = options.outConfig;
        if (config2 != null) {
            config = config2;
        }
        options.inBitmap = bitmapPool.getDirty(i, i2, config);
    }

    private boolean shouldUsePool(ImageHeaderParser.ImageType imageType) {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    public Resource<Bitmap> decode(InputStream inputStream, int i, int i2, Options options) throws IOException {
        return decode(inputStream, i, i2, options, EMPTY_CALLBACKS);
    }

    public boolean handles(InputStream inputStream) {
        return true;
    }

    public boolean handles(ByteBuffer byteBuffer) {
        return true;
    }

    public Resource<Bitmap> decode(InputStream inputStream, int i, int i2, Options options, DecodeCallbacks decodeCallbacks) throws IOException {
        Preconditions.checkArgument(inputStream.markSupported(), "You must provide an InputStream that supports mark()");
        byte[] bArr = (byte[]) this.byteArrayPool.get(65536, byte[].class);
        BitmapFactory.Options defaultOptions = getDefaultOptions();
        defaultOptions.inTempStorage = bArr;
        DecodeFormat decodeFormat = (DecodeFormat) options.get(DECODE_FORMAT);
        try {
            return BitmapResource.obtain(decodeFromWrappedStreams(inputStream, defaultOptions, (DownsampleStrategy) options.get(DOWNSAMPLE_STRATEGY), decodeFormat, decodeFormat == DecodeFormat.PREFER_ARGB_8888_DISALLOW_HARDWARE ? false : options.get(ALLOW_HARDWARE_CONFIG) != null && ((Boolean) options.get(ALLOW_HARDWARE_CONFIG)).booleanValue(), i, i2, ((Boolean) options.get(FIX_BITMAP_SIZE_TO_REQUESTED_DIMENSIONS)).booleanValue(), decodeCallbacks), this.bitmapPool);
        } finally {
            releaseOptions(defaultOptions);
            this.byteArrayPool.put(bArr);
        }
    }
}
