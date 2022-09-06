package com.amazon.alexa.imageloader;

import android.content.res.Resources;
import android.graphics.Bitmap;
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
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.RequestOptions;
/* loaded from: classes9.dex */
public final class GlideOptions extends RequestOptions implements Cloneable {
    private static GlideOptions centerCropTransform2;
    private static GlideOptions centerInsideTransform1;
    private static GlideOptions circleCropTransform3;
    private static GlideOptions fitCenterTransform0;
    private static GlideOptions noAnimation5;
    private static GlideOptions noTransformation4;

    @NonNull
    @CheckResult
    public static GlideOptions bitmapTransform(@NonNull Transformation<Bitmap> transformation) {
        return new GlideOptions().mo1610transform(transformation);
    }

    @NonNull
    @CheckResult
    public static GlideOptions centerCropTransform() {
        if (centerCropTransform2 == null) {
            centerCropTransform2 = new GlideOptions().mo1572centerCrop().mo1571autoClone();
        }
        return centerCropTransform2;
    }

    @NonNull
    @CheckResult
    public static GlideOptions centerInsideTransform() {
        if (centerInsideTransform1 == null) {
            centerInsideTransform1 = new GlideOptions().mo1573centerInside().mo1571autoClone();
        }
        return centerInsideTransform1;
    }

    @NonNull
    @CheckResult
    public static GlideOptions circleCropTransform() {
        if (circleCropTransform3 == null) {
            circleCropTransform3 = new GlideOptions().mo1574circleCrop().mo1571autoClone();
        }
        return circleCropTransform3;
    }

    @NonNull
    @CheckResult
    public static GlideOptions decodeTypeOf(@NonNull Class<?> cls) {
        return new GlideOptions().mo1576decode(cls);
    }

    @NonNull
    @CheckResult
    public static GlideOptions diskCacheStrategyOf(@NonNull DiskCacheStrategy diskCacheStrategy) {
        return new GlideOptions().mo1578diskCacheStrategy(diskCacheStrategy);
    }

    @NonNull
    @CheckResult
    public static GlideOptions downsampleOf(@NonNull DownsampleStrategy downsampleStrategy) {
        return new GlideOptions().mo1581downsample(downsampleStrategy);
    }

    @NonNull
    @CheckResult
    public static GlideOptions encodeFormatOf(@NonNull Bitmap.CompressFormat compressFormat) {
        return new GlideOptions().mo1582encodeFormat(compressFormat);
    }

    @NonNull
    @CheckResult
    public static GlideOptions encodeQualityOf(@IntRange(from = 0, to = 100) int i) {
        return new GlideOptions().mo1583encodeQuality(i);
    }

    @NonNull
    @CheckResult
    public static GlideOptions errorOf(@Nullable Drawable drawable) {
        return new GlideOptions().mo1585error(drawable);
    }

    @NonNull
    @CheckResult
    public static GlideOptions fitCenterTransform() {
        if (fitCenterTransform0 == null) {
            fitCenterTransform0 = new GlideOptions().mo1588fitCenter().mo1571autoClone();
        }
        return fitCenterTransform0;
    }

    @NonNull
    @CheckResult
    public static GlideOptions formatOf(@NonNull DecodeFormat decodeFormat) {
        return new GlideOptions().mo1589format(decodeFormat);
    }

    @NonNull
    @CheckResult
    public static GlideOptions frameOf(@IntRange(from = 0) long j) {
        return new GlideOptions().mo1590frame(j);
    }

    @NonNull
    @CheckResult
    public static GlideOptions noAnimation() {
        if (noAnimation5 == null) {
            noAnimation5 = new GlideOptions().mo1579dontAnimate().mo1571autoClone();
        }
        return noAnimation5;
    }

    @NonNull
    @CheckResult
    public static GlideOptions noTransformation() {
        if (noTransformation4 == null) {
            noTransformation4 = new GlideOptions().mo1580dontTransform().mo1571autoClone();
        }
        return noTransformation4;
    }

    @NonNull
    @CheckResult
    public static <T> GlideOptions option(@NonNull Option<T> option, @NonNull T t) {
        return new GlideOptions().mo1604set((Option<Option<T>>) option, (Option<T>) t);
    }

    @NonNull
    @CheckResult
    public static GlideOptions overrideOf(@IntRange(from = 0) int i, @IntRange(from = 0) int i2) {
        return new GlideOptions().mo1600override(i, i2);
    }

    @NonNull
    @CheckResult
    public static GlideOptions placeholderOf(@Nullable Drawable drawable) {
        return new GlideOptions().mo1602placeholder(drawable);
    }

    @NonNull
    @CheckResult
    public static GlideOptions priorityOf(@NonNull Priority priority) {
        return new GlideOptions().mo1603priority(priority);
    }

    @NonNull
    @CheckResult
    public static GlideOptions signatureOf(@NonNull Key key) {
        return new GlideOptions().mo1605signature(key);
    }

    @NonNull
    @CheckResult
    public static GlideOptions sizeMultiplierOf(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        return new GlideOptions().mo1606sizeMultiplier(f);
    }

    @NonNull
    @CheckResult
    public static GlideOptions skipMemoryCacheOf(boolean z) {
        return new GlideOptions().mo1607skipMemoryCache(z);
    }

    @NonNull
    @CheckResult
    public static GlideOptions timeoutOf(@IntRange(from = 0) int i) {
        return new GlideOptions().mo1609timeout(i);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: decode  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ RequestOptions mo1576decode(@NonNull Class cls) {
        return mo1576decode((Class<?>) cls);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: optionalTransform  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ RequestOptions mo1597optionalTransform(@NonNull Transformation transformation) {
        return mo1597optionalTransform((Transformation<Bitmap>) transformation);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: set  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ RequestOptions mo1604set(@NonNull Option option, @NonNull Object obj) {
        return mo1604set((Option<Option>) option, (Option) obj);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: transform  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ RequestOptions mo1610transform(@NonNull Transformation transformation) {
        return mo1610transform((Transformation<Bitmap>) transformation);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @SafeVarargs
    @CheckResult
    /* renamed from: transforms  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ RequestOptions mo1612transforms(@NonNull Transformation[] transformationArr) {
        return mo1612transforms((Transformation<Bitmap>[]) transformationArr);
    }

    @NonNull
    @CheckResult
    public static GlideOptions errorOf(@DrawableRes int i) {
        return new GlideOptions().mo1584error(i);
    }

    @NonNull
    @CheckResult
    public static GlideOptions overrideOf(@IntRange(from = 0) int i) {
        return new GlideOptions().mo1599override(i);
    }

    @NonNull
    @CheckResult
    public static GlideOptions placeholderOf(@DrawableRes int i) {
        return new GlideOptions().mo1601placeholder(i);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: apply */
    public final GlideOptions mo1570apply(@NonNull RequestOptions requestOptions) {
        return (GlideOptions) super.mo1570apply(requestOptions);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    /* renamed from: autoClone */
    public final GlideOptions mo1571autoClone() {
        return (GlideOptions) super.mo1571autoClone();
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: centerCrop */
    public final GlideOptions mo1572centerCrop() {
        return (GlideOptions) super.mo1572centerCrop();
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: centerInside */
    public final GlideOptions mo1573centerInside() {
        return (GlideOptions) super.mo1573centerInside();
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: circleCrop */
    public final GlideOptions mo1574circleCrop() {
        return (GlideOptions) super.mo1574circleCrop();
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: decode */
    public final GlideOptions mo1576decode(@NonNull Class<?> cls) {
        return (GlideOptions) super.mo1576decode(cls);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: disallowHardwareConfig */
    public final GlideOptions mo1577disallowHardwareConfig() {
        return (GlideOptions) super.mo1577disallowHardwareConfig();
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: diskCacheStrategy */
    public final GlideOptions mo1578diskCacheStrategy(@NonNull DiskCacheStrategy diskCacheStrategy) {
        return (GlideOptions) super.mo1578diskCacheStrategy(diskCacheStrategy);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: dontAnimate */
    public final GlideOptions mo1579dontAnimate() {
        return (GlideOptions) super.mo1579dontAnimate();
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: dontTransform */
    public final GlideOptions mo1580dontTransform() {
        return (GlideOptions) super.mo1580dontTransform();
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: downsample */
    public final GlideOptions mo1581downsample(@NonNull DownsampleStrategy downsampleStrategy) {
        return (GlideOptions) super.mo1581downsample(downsampleStrategy);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: encodeFormat */
    public final GlideOptions mo1582encodeFormat(@NonNull Bitmap.CompressFormat compressFormat) {
        return (GlideOptions) super.mo1582encodeFormat(compressFormat);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: encodeQuality */
    public final GlideOptions mo1583encodeQuality(@IntRange(from = 0, to = 100) int i) {
        return (GlideOptions) super.mo1583encodeQuality(i);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: fitCenter */
    public final GlideOptions mo1588fitCenter() {
        return (GlideOptions) super.mo1588fitCenter();
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: format */
    public final GlideOptions mo1589format(@NonNull DecodeFormat decodeFormat) {
        return (GlideOptions) super.mo1589format(decodeFormat);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: frame */
    public final GlideOptions mo1590frame(@IntRange(from = 0) long j) {
        return (GlideOptions) super.mo1590frame(j);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    /* renamed from: lock */
    public final GlideOptions mo1591lock() {
        return (GlideOptions) super.mo1591lock();
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: onlyRetrieveFromCache */
    public final GlideOptions mo1592onlyRetrieveFromCache(boolean z) {
        return (GlideOptions) super.mo1592onlyRetrieveFromCache(z);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: optionalCenterCrop */
    public final GlideOptions mo1593optionalCenterCrop() {
        return (GlideOptions) super.mo1593optionalCenterCrop();
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: optionalCenterInside */
    public final GlideOptions mo1594optionalCenterInside() {
        return (GlideOptions) super.mo1594optionalCenterInside();
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: optionalCircleCrop */
    public final GlideOptions mo1595optionalCircleCrop() {
        return (GlideOptions) super.mo1595optionalCircleCrop();
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: optionalFitCenter */
    public final GlideOptions mo1596optionalFitCenter() {
        return (GlideOptions) super.mo1596optionalFitCenter();
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: priority */
    public final GlideOptions mo1603priority(@NonNull Priority priority) {
        return (GlideOptions) super.mo1603priority(priority);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: set */
    public final <T> GlideOptions mo1604set(@NonNull Option<T> option, @NonNull T t) {
        return (GlideOptions) super.mo1604set((Option<Option<T>>) option, (Option<T>) t);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: signature */
    public final GlideOptions mo1605signature(@NonNull Key key) {
        return (GlideOptions) super.mo1605signature(key);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: sizeMultiplier */
    public final GlideOptions mo1606sizeMultiplier(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        return (GlideOptions) super.mo1606sizeMultiplier(f);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: skipMemoryCache */
    public final GlideOptions mo1607skipMemoryCache(boolean z) {
        return (GlideOptions) super.mo1607skipMemoryCache(z);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: theme */
    public final GlideOptions mo1608theme(@Nullable Resources.Theme theme) {
        return (GlideOptions) super.mo1608theme(theme);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: timeout */
    public final GlideOptions mo1609timeout(@IntRange(from = 0) int i) {
        return (GlideOptions) super.mo1609timeout(i);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @SafeVarargs
    @CheckResult
    /* renamed from: transforms */
    public final GlideOptions mo1612transforms(@NonNull Transformation<Bitmap>... transformationArr) {
        return (GlideOptions) super.mo1612transforms(transformationArr);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: useAnimationPool */
    public final GlideOptions mo1613useAnimationPool(boolean z) {
        return (GlideOptions) super.mo1613useAnimationPool(z);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: useUnlimitedSourceGeneratorsPool */
    public final GlideOptions mo1614useUnlimitedSourceGeneratorsPool(boolean z) {
        return (GlideOptions) super.mo1614useUnlimitedSourceGeneratorsPool(z);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @CheckResult
    /* renamed from: clone */
    public final GlideOptions mo1575clone() {
        return (GlideOptions) super.clone();
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: error */
    public final GlideOptions mo1585error(@Nullable Drawable drawable) {
        return (GlideOptions) super.mo1585error(drawable);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: fallback */
    public final GlideOptions mo1587fallback(@Nullable Drawable drawable) {
        return (GlideOptions) super.mo1587fallback(drawable);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: optionalTransform */
    public final GlideOptions mo1597optionalTransform(@NonNull Transformation<Bitmap> transformation) {
        return (GlideOptions) super.mo1597optionalTransform(transformation);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: override */
    public final GlideOptions mo1600override(int i, int i2) {
        return (GlideOptions) super.mo1600override(i, i2);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: placeholder */
    public final GlideOptions mo1602placeholder(@Nullable Drawable drawable) {
        return (GlideOptions) super.mo1602placeholder(drawable);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: transform */
    public final GlideOptions mo1610transform(@NonNull Transformation<Bitmap> transformation) {
        return (GlideOptions) super.mo1610transform(transformation);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: error */
    public final GlideOptions mo1584error(@DrawableRes int i) {
        return (GlideOptions) super.mo1584error(i);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: fallback */
    public final GlideOptions mo1586fallback(@DrawableRes int i) {
        return (GlideOptions) super.mo1586fallback(i);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: optionalTransform */
    public final <T> GlideOptions mo1598optionalTransform(@NonNull Class<T> cls, @NonNull Transformation<T> transformation) {
        return (GlideOptions) super.mo1598optionalTransform((Class) cls, (Transformation) transformation);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: override */
    public final GlideOptions mo1599override(int i) {
        return (GlideOptions) super.mo1599override(i);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: placeholder */
    public final GlideOptions mo1601placeholder(@DrawableRes int i) {
        return (GlideOptions) super.mo1601placeholder(i);
    }

    @Override // com.bumptech.glide.request.RequestOptions
    @NonNull
    @CheckResult
    /* renamed from: transform */
    public final <T> GlideOptions mo1611transform(@NonNull Class<T> cls, @NonNull Transformation<T> transformation) {
        return (GlideOptions) super.mo1611transform((Class) cls, (Transformation) transformation);
    }
}
