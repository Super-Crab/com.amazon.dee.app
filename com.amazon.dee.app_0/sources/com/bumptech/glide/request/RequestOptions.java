package com.bumptech.glide.request;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.stream.HttpGlideUrlLoader;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.load.resource.bitmap.DrawableTransformation;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.VideoDecoder;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawableTransformation;
import com.bumptech.glide.load.resource.gif.GifOptions;
import com.bumptech.glide.signature.EmptySignature;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes9.dex */
public class RequestOptions implements Cloneable {
    private static final int DISK_CACHE_STRATEGY = 4;
    private static final int ERROR_ID = 32;
    private static final int ERROR_PLACEHOLDER = 16;
    private static final int FALLBACK = 8192;
    private static final int FALLBACK_ID = 16384;
    private static final int IS_CACHEABLE = 256;
    private static final int ONLY_RETRIEVE_FROM_CACHE = 524288;
    private static final int OVERRIDE = 512;
    private static final int PLACEHOLDER = 64;
    private static final int PLACEHOLDER_ID = 128;
    private static final int PRIORITY = 8;
    private static final int RESOURCE_CLASS = 4096;
    private static final int SIGNATURE = 1024;
    private static final int SIZE_MULTIPLIER = 2;
    private static final int THEME = 32768;
    private static final int TRANSFORMATION = 2048;
    private static final int TRANSFORMATION_ALLOWED = 65536;
    private static final int TRANSFORMATION_REQUIRED = 131072;
    private static final int UNSET = -1;
    private static final int USE_ANIMATION_POOL = 1048576;
    private static final int USE_UNLIMITED_SOURCE_GENERATORS_POOL = 262144;
    @Nullable
    private static RequestOptions centerCropOptions;
    @Nullable
    private static RequestOptions centerInsideOptions;
    @Nullable
    private static RequestOptions circleCropOptions;
    @Nullable
    private static RequestOptions fitCenterOptions;
    @Nullable
    private static RequestOptions noAnimationOptions;
    @Nullable
    private static RequestOptions noTransformOptions;
    @Nullable
    private static RequestOptions skipMemoryCacheFalseOptions;
    @Nullable
    private static RequestOptions skipMemoryCacheTrueOptions;
    private int errorId;
    @Nullable
    private Drawable errorPlaceholder;
    @Nullable
    private Drawable fallbackDrawable;
    private int fallbackId;
    private int fields;
    private boolean isAutoCloneEnabled;
    private boolean isLocked;
    private boolean isTransformationRequired;
    private boolean onlyRetrieveFromCache;
    @Nullable
    private Drawable placeholderDrawable;
    private int placeholderId;
    @Nullable
    private Resources.Theme theme;
    private boolean useAnimationPool;
    private boolean useUnlimitedSourceGeneratorsPool;
    private float sizeMultiplier = 1.0f;
    @NonNull
    private DiskCacheStrategy diskCacheStrategy = DiskCacheStrategy.AUTOMATIC;
    @NonNull
    private Priority priority = Priority.NORMAL;
    private boolean isCacheable = true;
    private int overrideHeight = -1;
    private int overrideWidth = -1;
    @NonNull
    private Key signature = EmptySignature.obtain();
    private boolean isTransformationAllowed = true;
    @NonNull
    private Options options = new Options();
    @NonNull
    private Map<Class<?>, Transformation<?>> transformations = new HashMap();
    @NonNull
    private Class<?> resourceClass = Object.class;
    private boolean isScaleOnlyOrNoTransform = true;

    @NonNull
    @CheckResult
    public static RequestOptions bitmapTransform(@NonNull Transformation<Bitmap> transformation) {
        return new RequestOptions().mo1610transform(transformation);
    }

    @NonNull
    @CheckResult
    public static RequestOptions centerCropTransform() {
        if (centerCropOptions == null) {
            centerCropOptions = new RequestOptions().mo1572centerCrop().mo1571autoClone();
        }
        return centerCropOptions;
    }

    @NonNull
    @CheckResult
    public static RequestOptions centerInsideTransform() {
        if (centerInsideOptions == null) {
            centerInsideOptions = new RequestOptions().mo1573centerInside().mo1571autoClone();
        }
        return centerInsideOptions;
    }

    @NonNull
    @CheckResult
    public static RequestOptions circleCropTransform() {
        if (circleCropOptions == null) {
            circleCropOptions = new RequestOptions().mo1574circleCrop().mo1571autoClone();
        }
        return circleCropOptions;
    }

    @NonNull
    @CheckResult
    public static RequestOptions decodeTypeOf(@NonNull Class<?> cls) {
        return new RequestOptions().mo1576decode(cls);
    }

    @NonNull
    @CheckResult
    public static RequestOptions diskCacheStrategyOf(@NonNull DiskCacheStrategy diskCacheStrategy) {
        return new RequestOptions().mo1578diskCacheStrategy(diskCacheStrategy);
    }

    @NonNull
    @CheckResult
    public static RequestOptions downsampleOf(@NonNull DownsampleStrategy downsampleStrategy) {
        return new RequestOptions().mo1581downsample(downsampleStrategy);
    }

    @NonNull
    @CheckResult
    public static RequestOptions encodeFormatOf(@NonNull Bitmap.CompressFormat compressFormat) {
        return new RequestOptions().mo1582encodeFormat(compressFormat);
    }

    @NonNull
    @CheckResult
    public static RequestOptions encodeQualityOf(@IntRange(from = 0, to = 100) int i) {
        return new RequestOptions().mo1583encodeQuality(i);
    }

    @NonNull
    @CheckResult
    public static RequestOptions errorOf(@Nullable Drawable drawable) {
        return new RequestOptions().mo1585error(drawable);
    }

    @NonNull
    @CheckResult
    public static RequestOptions fitCenterTransform() {
        if (fitCenterOptions == null) {
            fitCenterOptions = new RequestOptions().mo1588fitCenter().mo1571autoClone();
        }
        return fitCenterOptions;
    }

    @NonNull
    @CheckResult
    public static RequestOptions formatOf(@NonNull DecodeFormat decodeFormat) {
        return new RequestOptions().mo1589format(decodeFormat);
    }

    @NonNull
    @CheckResult
    public static RequestOptions frameOf(@IntRange(from = 0) long j) {
        return new RequestOptions().mo1590frame(j);
    }

    private boolean isSet(int i) {
        return isSet(this.fields, i);
    }

    private static boolean isSet(int i, int i2) {
        return (i & i2) != 0;
    }

    @NonNull
    @CheckResult
    public static RequestOptions noAnimation() {
        if (noAnimationOptions == null) {
            noAnimationOptions = new RequestOptions().mo1579dontAnimate().mo1571autoClone();
        }
        return noAnimationOptions;
    }

    @NonNull
    @CheckResult
    public static RequestOptions noTransformation() {
        if (noTransformOptions == null) {
            noTransformOptions = new RequestOptions().mo1580dontTransform().mo1571autoClone();
        }
        return noTransformOptions;
    }

    @NonNull
    @CheckResult
    public static <T> RequestOptions option(@NonNull Option<T> option, @NonNull T t) {
        return new RequestOptions().mo1604set(option, t);
    }

    @NonNull
    private RequestOptions optionalScaleOnlyTransform(@NonNull DownsampleStrategy downsampleStrategy, @NonNull Transformation<Bitmap> transformation) {
        return scaleOnlyTransform(downsampleStrategy, transformation, false);
    }

    @NonNull
    @CheckResult
    public static RequestOptions overrideOf(@IntRange(from = 0) int i, @IntRange(from = 0) int i2) {
        return new RequestOptions().mo1600override(i, i2);
    }

    @NonNull
    @CheckResult
    public static RequestOptions placeholderOf(@Nullable Drawable drawable) {
        return new RequestOptions().mo1602placeholder(drawable);
    }

    @NonNull
    @CheckResult
    public static RequestOptions priorityOf(@NonNull Priority priority) {
        return new RequestOptions().mo1603priority(priority);
    }

    @NonNull
    private RequestOptions scaleOnlyTransform(@NonNull DownsampleStrategy downsampleStrategy, @NonNull Transformation<Bitmap> transformation) {
        return scaleOnlyTransform(downsampleStrategy, transformation, true);
    }

    @NonNull
    private RequestOptions selfOrThrowIfLocked() {
        if (!this.isLocked) {
            return this;
        }
        throw new IllegalStateException("You cannot modify locked RequestOptions, consider clone()");
    }

    @NonNull
    @CheckResult
    public static RequestOptions signatureOf(@NonNull Key key) {
        return new RequestOptions().mo1605signature(key);
    }

    @NonNull
    @CheckResult
    public static RequestOptions sizeMultiplierOf(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        return new RequestOptions().mo1606sizeMultiplier(f);
    }

    @NonNull
    @CheckResult
    public static RequestOptions skipMemoryCacheOf(boolean z) {
        if (z) {
            if (skipMemoryCacheTrueOptions == null) {
                skipMemoryCacheTrueOptions = new RequestOptions().mo1607skipMemoryCache(true).mo1571autoClone();
            }
            return skipMemoryCacheTrueOptions;
        }
        if (skipMemoryCacheFalseOptions == null) {
            skipMemoryCacheFalseOptions = new RequestOptions().mo1607skipMemoryCache(false).mo1571autoClone();
        }
        return skipMemoryCacheFalseOptions;
    }

    @NonNull
    @CheckResult
    public static RequestOptions timeoutOf(@IntRange(from = 0) int i) {
        return new RequestOptions().mo1609timeout(i);
    }

    @NonNull
    @CheckResult
    /* renamed from: apply */
    public RequestOptions mo1570apply(@NonNull RequestOptions requestOptions) {
        if (this.isAutoCloneEnabled) {
            return clone().mo1570apply(requestOptions);
        }
        if (isSet(requestOptions.fields, 2)) {
            this.sizeMultiplier = requestOptions.sizeMultiplier;
        }
        if (isSet(requestOptions.fields, 262144)) {
            this.useUnlimitedSourceGeneratorsPool = requestOptions.useUnlimitedSourceGeneratorsPool;
        }
        if (isSet(requestOptions.fields, 1048576)) {
            this.useAnimationPool = requestOptions.useAnimationPool;
        }
        if (isSet(requestOptions.fields, 4)) {
            this.diskCacheStrategy = requestOptions.diskCacheStrategy;
        }
        if (isSet(requestOptions.fields, 8)) {
            this.priority = requestOptions.priority;
        }
        if (isSet(requestOptions.fields, 16)) {
            this.errorPlaceholder = requestOptions.errorPlaceholder;
        }
        if (isSet(requestOptions.fields, 32)) {
            this.errorId = requestOptions.errorId;
        }
        if (isSet(requestOptions.fields, 64)) {
            this.placeholderDrawable = requestOptions.placeholderDrawable;
        }
        if (isSet(requestOptions.fields, 128)) {
            this.placeholderId = requestOptions.placeholderId;
        }
        if (isSet(requestOptions.fields, 256)) {
            this.isCacheable = requestOptions.isCacheable;
        }
        if (isSet(requestOptions.fields, 512)) {
            this.overrideWidth = requestOptions.overrideWidth;
            this.overrideHeight = requestOptions.overrideHeight;
        }
        if (isSet(requestOptions.fields, 1024)) {
            this.signature = requestOptions.signature;
        }
        if (isSet(requestOptions.fields, 4096)) {
            this.resourceClass = requestOptions.resourceClass;
        }
        if (isSet(requestOptions.fields, 8192)) {
            this.fallbackDrawable = requestOptions.fallbackDrawable;
        }
        if (isSet(requestOptions.fields, 16384)) {
            this.fallbackId = requestOptions.fallbackId;
        }
        if (isSet(requestOptions.fields, 32768)) {
            this.theme = requestOptions.theme;
        }
        if (isSet(requestOptions.fields, 65536)) {
            this.isTransformationAllowed = requestOptions.isTransformationAllowed;
        }
        if (isSet(requestOptions.fields, 131072)) {
            this.isTransformationRequired = requestOptions.isTransformationRequired;
        }
        if (isSet(requestOptions.fields, 2048)) {
            this.transformations.putAll(requestOptions.transformations);
            this.isScaleOnlyOrNoTransform = requestOptions.isScaleOnlyOrNoTransform;
        }
        if (isSet(requestOptions.fields, 524288)) {
            this.onlyRetrieveFromCache = requestOptions.onlyRetrieveFromCache;
        }
        if (!this.isTransformationAllowed) {
            this.transformations.clear();
            this.fields &= -2049;
            this.isTransformationRequired = false;
            this.fields &= -131073;
            this.isScaleOnlyOrNoTransform = true;
        }
        this.fields |= requestOptions.fields;
        this.options.putAll(requestOptions.options);
        return selfOrThrowIfLocked();
    }

    @NonNull
    /* renamed from: autoClone */
    public RequestOptions mo1571autoClone() {
        if (this.isLocked && !this.isAutoCloneEnabled) {
            throw new IllegalStateException("You cannot auto lock an already locked options object, try clone() first");
        }
        this.isAutoCloneEnabled = true;
        return mo1591lock();
    }

    @NonNull
    @CheckResult
    /* renamed from: centerCrop */
    public RequestOptions mo1572centerCrop() {
        return transform(DownsampleStrategy.CENTER_OUTSIDE, new CenterCrop());
    }

    @NonNull
    @CheckResult
    /* renamed from: centerInside */
    public RequestOptions mo1573centerInside() {
        return scaleOnlyTransform(DownsampleStrategy.CENTER_INSIDE, new CenterInside());
    }

    @NonNull
    @CheckResult
    /* renamed from: circleCrop */
    public RequestOptions mo1574circleCrop() {
        return transform(DownsampleStrategy.CENTER_INSIDE, new CircleCrop());
    }

    @NonNull
    @CheckResult
    /* renamed from: decode */
    public RequestOptions mo1576decode(@NonNull Class<?> cls) {
        if (this.isAutoCloneEnabled) {
            return clone().mo1576decode(cls);
        }
        this.resourceClass = (Class) Preconditions.checkNotNull(cls);
        this.fields |= 4096;
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    /* renamed from: disallowHardwareConfig */
    public RequestOptions mo1577disallowHardwareConfig() {
        return mo1604set(Downsampler.ALLOW_HARDWARE_CONFIG, false);
    }

    @NonNull
    @CheckResult
    /* renamed from: diskCacheStrategy */
    public RequestOptions mo1578diskCacheStrategy(@NonNull DiskCacheStrategy diskCacheStrategy) {
        if (this.isAutoCloneEnabled) {
            return clone().mo1578diskCacheStrategy(diskCacheStrategy);
        }
        this.diskCacheStrategy = (DiskCacheStrategy) Preconditions.checkNotNull(diskCacheStrategy);
        this.fields |= 4;
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    /* renamed from: dontAnimate */
    public RequestOptions mo1579dontAnimate() {
        return mo1604set(GifOptions.DISABLE_ANIMATION, true);
    }

    @NonNull
    @CheckResult
    /* renamed from: dontTransform */
    public RequestOptions mo1580dontTransform() {
        if (this.isAutoCloneEnabled) {
            return clone().mo1580dontTransform();
        }
        this.transformations.clear();
        this.fields &= -2049;
        this.isTransformationRequired = false;
        this.fields &= -131073;
        this.isTransformationAllowed = false;
        this.fields |= 65536;
        this.isScaleOnlyOrNoTransform = true;
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    /* renamed from: downsample */
    public RequestOptions mo1581downsample(@NonNull DownsampleStrategy downsampleStrategy) {
        return mo1604set(Downsampler.DOWNSAMPLE_STRATEGY, Preconditions.checkNotNull(downsampleStrategy));
    }

    @NonNull
    @CheckResult
    /* renamed from: encodeFormat */
    public RequestOptions mo1582encodeFormat(@NonNull Bitmap.CompressFormat compressFormat) {
        return mo1604set(BitmapEncoder.COMPRESSION_FORMAT, Preconditions.checkNotNull(compressFormat));
    }

    @NonNull
    @CheckResult
    /* renamed from: encodeQuality */
    public RequestOptions mo1583encodeQuality(@IntRange(from = 0, to = 100) int i) {
        return mo1604set(BitmapEncoder.COMPRESSION_QUALITY, Integer.valueOf(i));
    }

    public boolean equals(Object obj) {
        if (obj instanceof RequestOptions) {
            RequestOptions requestOptions = (RequestOptions) obj;
            return Float.compare(requestOptions.sizeMultiplier, this.sizeMultiplier) == 0 && this.errorId == requestOptions.errorId && Util.bothNullOrEqual(this.errorPlaceholder, requestOptions.errorPlaceholder) && this.placeholderId == requestOptions.placeholderId && Util.bothNullOrEqual(this.placeholderDrawable, requestOptions.placeholderDrawable) && this.fallbackId == requestOptions.fallbackId && Util.bothNullOrEqual(this.fallbackDrawable, requestOptions.fallbackDrawable) && this.isCacheable == requestOptions.isCacheable && this.overrideHeight == requestOptions.overrideHeight && this.overrideWidth == requestOptions.overrideWidth && this.isTransformationRequired == requestOptions.isTransformationRequired && this.isTransformationAllowed == requestOptions.isTransformationAllowed && this.useUnlimitedSourceGeneratorsPool == requestOptions.useUnlimitedSourceGeneratorsPool && this.onlyRetrieveFromCache == requestOptions.onlyRetrieveFromCache && this.diskCacheStrategy.equals(requestOptions.diskCacheStrategy) && this.priority == requestOptions.priority && this.options.equals(requestOptions.options) && this.transformations.equals(requestOptions.transformations) && this.resourceClass.equals(requestOptions.resourceClass) && Util.bothNullOrEqual(this.signature, requestOptions.signature) && Util.bothNullOrEqual(this.theme, requestOptions.theme);
        }
        return false;
    }

    @NonNull
    @CheckResult
    /* renamed from: error */
    public RequestOptions mo1585error(@Nullable Drawable drawable) {
        if (this.isAutoCloneEnabled) {
            return clone().mo1585error(drawable);
        }
        this.errorPlaceholder = drawable;
        this.fields |= 16;
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    /* renamed from: fallback */
    public RequestOptions mo1587fallback(@Nullable Drawable drawable) {
        if (this.isAutoCloneEnabled) {
            return clone().mo1587fallback(drawable);
        }
        this.fallbackDrawable = drawable;
        this.fields |= 8192;
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    /* renamed from: fitCenter */
    public RequestOptions mo1588fitCenter() {
        return scaleOnlyTransform(DownsampleStrategy.FIT_CENTER, new FitCenter());
    }

    @NonNull
    @CheckResult
    /* renamed from: format */
    public RequestOptions mo1589format(@NonNull DecodeFormat decodeFormat) {
        Preconditions.checkNotNull(decodeFormat);
        return mo1604set(Downsampler.DECODE_FORMAT, decodeFormat).mo1604set(GifOptions.DECODE_FORMAT, decodeFormat);
    }

    @NonNull
    @CheckResult
    /* renamed from: frame */
    public RequestOptions mo1590frame(@IntRange(from = 0) long j) {
        return mo1604set(VideoDecoder.TARGET_FRAME, Long.valueOf(j));
    }

    @NonNull
    public final DiskCacheStrategy getDiskCacheStrategy() {
        return this.diskCacheStrategy;
    }

    public final int getErrorId() {
        return this.errorId;
    }

    @Nullable
    public final Drawable getErrorPlaceholder() {
        return this.errorPlaceholder;
    }

    @Nullable
    public final Drawable getFallbackDrawable() {
        return this.fallbackDrawable;
    }

    public final int getFallbackId() {
        return this.fallbackId;
    }

    public final boolean getOnlyRetrieveFromCache() {
        return this.onlyRetrieveFromCache;
    }

    @NonNull
    public final Options getOptions() {
        return this.options;
    }

    public final int getOverrideHeight() {
        return this.overrideHeight;
    }

    public final int getOverrideWidth() {
        return this.overrideWidth;
    }

    @Nullable
    public final Drawable getPlaceholderDrawable() {
        return this.placeholderDrawable;
    }

    public final int getPlaceholderId() {
        return this.placeholderId;
    }

    @NonNull
    public final Priority getPriority() {
        return this.priority;
    }

    @NonNull
    public final Class<?> getResourceClass() {
        return this.resourceClass;
    }

    @NonNull
    public final Key getSignature() {
        return this.signature;
    }

    public final float getSizeMultiplier() {
        return this.sizeMultiplier;
    }

    @Nullable
    public final Resources.Theme getTheme() {
        return this.theme;
    }

    @NonNull
    public final Map<Class<?>, Transformation<?>> getTransformations() {
        return this.transformations;
    }

    public final boolean getUseAnimationPool() {
        return this.useAnimationPool;
    }

    public final boolean getUseUnlimitedSourceGeneratorsPool() {
        return this.useUnlimitedSourceGeneratorsPool;
    }

    public int hashCode() {
        return Util.hashCode(this.theme, Util.hashCode(this.signature, Util.hashCode(this.resourceClass, Util.hashCode(this.transformations, Util.hashCode(this.options, Util.hashCode(this.priority, Util.hashCode(this.diskCacheStrategy, Util.hashCode(this.onlyRetrieveFromCache, Util.hashCode(this.useUnlimitedSourceGeneratorsPool, Util.hashCode(this.isTransformationAllowed, Util.hashCode(this.isTransformationRequired, Util.hashCode(this.overrideWidth, Util.hashCode(this.overrideHeight, Util.hashCode(this.isCacheable, Util.hashCode(this.fallbackDrawable, Util.hashCode(this.fallbackId, Util.hashCode(this.placeholderDrawable, Util.hashCode(this.placeholderId, Util.hashCode(this.errorPlaceholder, Util.hashCode(this.errorId, Util.hashCode(this.sizeMultiplier)))))))))))))))))))));
    }

    protected boolean isAutoCloneEnabled() {
        return this.isAutoCloneEnabled;
    }

    public final boolean isDiskCacheStrategySet() {
        return isSet(4);
    }

    public final boolean isLocked() {
        return this.isLocked;
    }

    public final boolean isMemoryCacheable() {
        return this.isCacheable;
    }

    public final boolean isPrioritySet() {
        return isSet(8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isScaleOnlyOrNoTransform() {
        return this.isScaleOnlyOrNoTransform;
    }

    public final boolean isSkipMemoryCacheSet() {
        return isSet(256);
    }

    public final boolean isTransformationAllowed() {
        return this.isTransformationAllowed;
    }

    public final boolean isTransformationRequired() {
        return this.isTransformationRequired;
    }

    public final boolean isTransformationSet() {
        return isSet(2048);
    }

    public final boolean isValidOverride() {
        return Util.isValidDimensions(this.overrideWidth, this.overrideHeight);
    }

    @NonNull
    /* renamed from: lock */
    public RequestOptions mo1591lock() {
        this.isLocked = true;
        return this;
    }

    @NonNull
    @CheckResult
    /* renamed from: onlyRetrieveFromCache */
    public RequestOptions mo1592onlyRetrieveFromCache(boolean z) {
        if (this.isAutoCloneEnabled) {
            return clone().mo1592onlyRetrieveFromCache(z);
        }
        this.onlyRetrieveFromCache = z;
        this.fields |= 524288;
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    /* renamed from: optionalCenterCrop */
    public RequestOptions mo1593optionalCenterCrop() {
        return optionalTransform(DownsampleStrategy.CENTER_OUTSIDE, new CenterCrop());
    }

    @NonNull
    @CheckResult
    /* renamed from: optionalCenterInside */
    public RequestOptions mo1594optionalCenterInside() {
        return optionalScaleOnlyTransform(DownsampleStrategy.CENTER_INSIDE, new CenterInside());
    }

    @NonNull
    @CheckResult
    /* renamed from: optionalCircleCrop */
    public RequestOptions mo1595optionalCircleCrop() {
        return optionalTransform(DownsampleStrategy.CENTER_OUTSIDE, new CircleCrop());
    }

    @NonNull
    @CheckResult
    /* renamed from: optionalFitCenter */
    public RequestOptions mo1596optionalFitCenter() {
        return optionalScaleOnlyTransform(DownsampleStrategy.FIT_CENTER, new FitCenter());
    }

    @NonNull
    final RequestOptions optionalTransform(@NonNull DownsampleStrategy downsampleStrategy, @NonNull Transformation<Bitmap> transformation) {
        if (this.isAutoCloneEnabled) {
            return clone().optionalTransform(downsampleStrategy, transformation);
        }
        mo1581downsample(downsampleStrategy);
        return transform(transformation, false);
    }

    @NonNull
    @CheckResult
    /* renamed from: override */
    public RequestOptions mo1600override(int i, int i2) {
        if (this.isAutoCloneEnabled) {
            return clone().mo1600override(i, i2);
        }
        this.overrideWidth = i;
        this.overrideHeight = i2;
        this.fields |= 512;
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    /* renamed from: placeholder */
    public RequestOptions mo1602placeholder(@Nullable Drawable drawable) {
        if (this.isAutoCloneEnabled) {
            return clone().mo1602placeholder(drawable);
        }
        this.placeholderDrawable = drawable;
        this.fields |= 64;
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    /* renamed from: priority */
    public RequestOptions mo1603priority(@NonNull Priority priority) {
        if (this.isAutoCloneEnabled) {
            return clone().mo1603priority(priority);
        }
        this.priority = (Priority) Preconditions.checkNotNull(priority);
        this.fields |= 8;
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    /* renamed from: set */
    public <T> RequestOptions mo1604set(@NonNull Option<T> option, @NonNull T t) {
        if (this.isAutoCloneEnabled) {
            return clone().mo1604set(option, t);
        }
        Preconditions.checkNotNull(option);
        Preconditions.checkNotNull(t);
        this.options.set(option, t);
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    /* renamed from: signature */
    public RequestOptions mo1605signature(@NonNull Key key) {
        if (this.isAutoCloneEnabled) {
            return clone().mo1605signature(key);
        }
        this.signature = (Key) Preconditions.checkNotNull(key);
        this.fields |= 1024;
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    /* renamed from: sizeMultiplier */
    public RequestOptions mo1606sizeMultiplier(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        if (this.isAutoCloneEnabled) {
            return clone().mo1606sizeMultiplier(f);
        }
        if (f >= 0.0f && f <= 1.0f) {
            this.sizeMultiplier = f;
            this.fields |= 2;
            return selfOrThrowIfLocked();
        }
        throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
    }

    @NonNull
    @CheckResult
    /* renamed from: skipMemoryCache */
    public RequestOptions mo1607skipMemoryCache(boolean z) {
        if (this.isAutoCloneEnabled) {
            return clone().mo1607skipMemoryCache(true);
        }
        this.isCacheable = !z;
        this.fields |= 256;
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    /* renamed from: theme */
    public RequestOptions mo1608theme(@Nullable Resources.Theme theme) {
        if (this.isAutoCloneEnabled) {
            return clone().mo1608theme(theme);
        }
        this.theme = theme;
        this.fields |= 32768;
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    /* renamed from: timeout */
    public RequestOptions mo1609timeout(@IntRange(from = 0) int i) {
        return mo1604set(HttpGlideUrlLoader.TIMEOUT, Integer.valueOf(i));
    }

    @NonNull
    @CheckResult
    final RequestOptions transform(@NonNull DownsampleStrategy downsampleStrategy, @NonNull Transformation<Bitmap> transformation) {
        if (this.isAutoCloneEnabled) {
            return clone().transform(downsampleStrategy, transformation);
        }
        mo1581downsample(downsampleStrategy);
        return mo1610transform(transformation);
    }

    @NonNull
    @CheckResult
    /* renamed from: transforms */
    public RequestOptions mo1612transforms(@NonNull Transformation<Bitmap>... transformationArr) {
        return transform((Transformation<Bitmap>) new MultiTransformation(transformationArr), true);
    }

    @NonNull
    @CheckResult
    /* renamed from: useAnimationPool */
    public RequestOptions mo1613useAnimationPool(boolean z) {
        if (this.isAutoCloneEnabled) {
            return clone().mo1613useAnimationPool(z);
        }
        this.useAnimationPool = z;
        this.fields |= 1048576;
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    /* renamed from: useUnlimitedSourceGeneratorsPool */
    public RequestOptions mo1614useUnlimitedSourceGeneratorsPool(boolean z) {
        if (this.isAutoCloneEnabled) {
            return clone().mo1614useUnlimitedSourceGeneratorsPool(z);
        }
        this.useUnlimitedSourceGeneratorsPool = z;
        this.fields |= 262144;
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    public static RequestOptions errorOf(@DrawableRes int i) {
        return new RequestOptions().mo1584error(i);
    }

    @NonNull
    @CheckResult
    public static RequestOptions overrideOf(@IntRange(from = 0) int i) {
        return overrideOf(i, i);
    }

    @NonNull
    @CheckResult
    public static RequestOptions placeholderOf(@DrawableRes int i) {
        return new RequestOptions().mo1601placeholder(i);
    }

    @NonNull
    private RequestOptions scaleOnlyTransform(@NonNull DownsampleStrategy downsampleStrategy, @NonNull Transformation<Bitmap> transformation, boolean z) {
        RequestOptions transform = z ? transform(downsampleStrategy, transformation) : optionalTransform(downsampleStrategy, transformation);
        transform.isScaleOnlyOrNoTransform = true;
        return transform;
    }

    @CheckResult
    public RequestOptions clone() {
        try {
            RequestOptions requestOptions = (RequestOptions) super.clone();
            requestOptions.options = new Options();
            requestOptions.options.putAll(this.options);
            requestOptions.transformations = new HashMap();
            requestOptions.transformations.putAll(this.transformations);
            requestOptions.isLocked = false;
            requestOptions.isAutoCloneEnabled = false;
            return requestOptions;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    @CheckResult
    /* renamed from: optionalTransform */
    public RequestOptions mo1597optionalTransform(@NonNull Transformation<Bitmap> transformation) {
        return transform(transformation, false);
    }

    @NonNull
    @CheckResult
    /* renamed from: transform */
    public RequestOptions mo1610transform(@NonNull Transformation<Bitmap> transformation) {
        return transform(transformation, true);
    }

    @NonNull
    private RequestOptions transform(@NonNull Transformation<Bitmap> transformation, boolean z) {
        if (this.isAutoCloneEnabled) {
            return clone().transform(transformation, z);
        }
        DrawableTransformation drawableTransformation = new DrawableTransformation(transformation, z);
        transform(Bitmap.class, transformation, z);
        transform(Drawable.class, drawableTransformation, z);
        transform(BitmapDrawable.class, drawableTransformation.asBitmapDrawable(), z);
        transform(GifDrawable.class, new GifDrawableTransformation(transformation), z);
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    /* renamed from: error */
    public RequestOptions mo1584error(@DrawableRes int i) {
        if (this.isAutoCloneEnabled) {
            return clone().mo1584error(i);
        }
        this.errorId = i;
        this.fields |= 32;
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    /* renamed from: fallback */
    public RequestOptions mo1586fallback(@DrawableRes int i) {
        if (this.isAutoCloneEnabled) {
            return clone().mo1586fallback(i);
        }
        this.fallbackId = i;
        this.fields |= 16384;
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    /* renamed from: optionalTransform */
    public <T> RequestOptions mo1598optionalTransform(@NonNull Class<T> cls, @NonNull Transformation<T> transformation) {
        return transform(cls, transformation, false);
    }

    @NonNull
    @CheckResult
    /* renamed from: placeholder */
    public RequestOptions mo1601placeholder(@DrawableRes int i) {
        if (this.isAutoCloneEnabled) {
            return clone().mo1601placeholder(i);
        }
        this.placeholderId = i;
        this.fields |= 128;
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    /* renamed from: override */
    public RequestOptions mo1599override(int i) {
        return mo1600override(i, i);
    }

    @NonNull
    private <T> RequestOptions transform(@NonNull Class<T> cls, @NonNull Transformation<T> transformation, boolean z) {
        if (this.isAutoCloneEnabled) {
            return clone().transform(cls, transformation, z);
        }
        Preconditions.checkNotNull(cls);
        Preconditions.checkNotNull(transformation);
        this.transformations.put(cls, transformation);
        this.fields |= 2048;
        this.isTransformationAllowed = true;
        this.fields |= 65536;
        this.isScaleOnlyOrNoTransform = false;
        if (z) {
            this.fields |= 131072;
            this.isTransformationRequired = true;
        }
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    /* renamed from: transform */
    public <T> RequestOptions mo1611transform(@NonNull Class<T> cls, @NonNull Transformation<T> transformation) {
        return transform(cls, transformation, true);
    }
}
