package com.amazon.alexa.imageloader;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import java.io.File;
import java.net.URL;
/* loaded from: classes9.dex */
public class GlideRequest<TranscodeType> extends RequestBuilder<TranscodeType> implements Cloneable {
    GlideRequest(@NonNull Class<TranscodeType> cls, @NonNull RequestBuilder<?> requestBuilder) {
        super(cls, requestBuilder);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> centerCrop() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1572centerCrop();
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1572centerCrop();
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> centerInside() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1573centerInside();
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1573centerInside();
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> circleCrop() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1574circleCrop();
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1574circleCrop();
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> decode(@NonNull Class<?> cls) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1576decode(cls);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1576decode(cls);
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> disallowHardwareConfig() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1577disallowHardwareConfig();
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1577disallowHardwareConfig();
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> diskCacheStrategy(@NonNull DiskCacheStrategy diskCacheStrategy) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1578diskCacheStrategy(diskCacheStrategy);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1578diskCacheStrategy(diskCacheStrategy);
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> dontAnimate() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1579dontAnimate();
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1579dontAnimate();
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> dontTransform() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1580dontTransform();
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1580dontTransform();
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> downsample(@NonNull DownsampleStrategy downsampleStrategy) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1581downsample(downsampleStrategy);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1581downsample(downsampleStrategy);
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> encodeFormat(@NonNull Bitmap.CompressFormat compressFormat) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1582encodeFormat(compressFormat);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1582encodeFormat(compressFormat);
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> encodeQuality(@IntRange(from = 0, to = 100) int i) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1583encodeQuality(i);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1583encodeQuality(i);
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> fallback(@Nullable Drawable drawable) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1587fallback(drawable);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1587fallback(drawable);
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> fitCenter() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1588fitCenter();
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1588fitCenter();
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> format(@NonNull DecodeFormat decodeFormat) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1589format(decodeFormat);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1589format(decodeFormat);
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> frame(@IntRange(from = 0) long j) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1590frame(j);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1590frame(j);
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> onlyRetrieveFromCache(boolean z) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1592onlyRetrieveFromCache(z);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1592onlyRetrieveFromCache(z);
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> optionalCenterCrop() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1593optionalCenterCrop();
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1593optionalCenterCrop();
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> optionalCenterInside() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1594optionalCenterInside();
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1594optionalCenterInside();
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> optionalCircleCrop() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1595optionalCircleCrop();
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1595optionalCircleCrop();
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> optionalFitCenter() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1596optionalFitCenter();
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1596optionalFitCenter();
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> optionalTransform(@NonNull Transformation<Bitmap> transformation) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1597optionalTransform(transformation);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1597optionalTransform(transformation);
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> override(int i, int i2) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1600override(i, i2);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1600override(i, i2);
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> placeholder(@Nullable Drawable drawable) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1602placeholder(drawable);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1602placeholder(drawable);
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> priority(@NonNull Priority priority) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1603priority(priority);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1603priority(priority);
        }
        return this;
    }

    @NonNull
    @CheckResult
    public <T> GlideRequest<TranscodeType> set(@NonNull Option<T> option, @NonNull T t) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1604set((Option<Option<T>>) option, (Option<T>) t);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1604set((Option<Option<T>>) option, (Option<T>) t);
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> signature(@NonNull Key key) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1605signature(key);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1605signature(key);
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> sizeMultiplier(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1606sizeMultiplier(f);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1606sizeMultiplier(f);
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> skipMemoryCache(boolean z) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1607skipMemoryCache(z);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1607skipMemoryCache(z);
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> theme(@Nullable Resources.Theme theme) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1608theme(theme);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1608theme(theme);
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> timeout(@IntRange(from = 0) int i) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1609timeout(i);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1609timeout(i);
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> transform(@NonNull Transformation<Bitmap> transformation) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1610transform(transformation);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1610transform(transformation);
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> transforms(@NonNull Transformation<Bitmap>... transformationArr) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1612transforms(transformationArr);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1612transforms(transformationArr);
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> useAnimationPool(boolean z) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1613useAnimationPool(z);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1613useAnimationPool(z);
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> useUnlimitedSourceGeneratorsPool(boolean z) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1614useUnlimitedSourceGeneratorsPool(z);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1614useUnlimitedSourceGeneratorsPool(z);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GlideRequest(@NonNull Glide glide, @NonNull RequestManager requestManager, @NonNull Class<TranscodeType> cls, @NonNull Context context) {
        super(glide, requestManager, cls, context);
    }

    @Override // com.bumptech.glide.RequestBuilder
    @NonNull
    @CheckResult
    /* renamed from: apply */
    public GlideRequest<TranscodeType> mo1615apply(@NonNull RequestOptions requestOptions) {
        return (GlideRequest) super.mo1615apply(requestOptions);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> error(@Nullable Drawable drawable) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1585error(drawable);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1585error(drawable);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bumptech.glide.RequestBuilder
    @NonNull
    @CheckResult
    /* renamed from: getDownloadOnlyRequest */
    public GlideRequest<File> mo1618getDownloadOnlyRequest() {
        return new GlideRequest(File.class, this).mo1615apply(RequestBuilder.DOWNLOAD_ONLY_OPTIONS);
    }

    @Override // com.bumptech.glide.RequestBuilder
    @NonNull
    @CheckResult
    /* renamed from: listener */
    public GlideRequest<TranscodeType> mo1619listener(@Nullable RequestListener<TranscodeType> requestListener) {
        return (GlideRequest) super.mo1619listener((RequestListener) requestListener);
    }

    @Override // com.bumptech.glide.RequestBuilder
    @NonNull
    @CheckResult
    /* renamed from: transition */
    public GlideRequest<TranscodeType> mo1632transition(@NonNull TransitionOptions<?, ? super TranscodeType> transitionOptions) {
        return (GlideRequest) super.mo1632transition((TransitionOptions) transitionOptions);
    }

    @Override // com.bumptech.glide.RequestBuilder
    @CheckResult
    /* renamed from: clone */
    public GlideRequest<TranscodeType> mo1616clone() {
        return (GlideRequest) super.clone();
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> fallback(@DrawableRes int i) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1586fallback(i);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1586fallback(i);
        }
        return this;
    }

    @NonNull
    @CheckResult
    public <T> GlideRequest<TranscodeType> optionalTransform(@NonNull Class<T> cls, @NonNull Transformation<T> transformation) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1598optionalTransform((Class) cls, (Transformation) transformation);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1598optionalTransform((Class) cls, (Transformation) transformation);
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> override(int i) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1599override(i);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1599override(i);
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> placeholder(@DrawableRes int i) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1601placeholder(i);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1601placeholder(i);
        }
        return this;
    }

    @Override // com.bumptech.glide.RequestBuilder
    @NonNull
    @CheckResult
    /* renamed from: thumbnail */
    public GlideRequest<TranscodeType> mo1630thumbnail(@Nullable RequestBuilder<TranscodeType> requestBuilder) {
        return (GlideRequest) super.mo1630thumbnail((RequestBuilder) requestBuilder);
    }

    @NonNull
    @CheckResult
    public <T> GlideRequest<TranscodeType> transform(@NonNull Class<T> cls, @NonNull Transformation<T> transformation) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1611transform((Class) cls, (Transformation) transformation);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1611transform((Class) cls, (Transformation) transformation);
        }
        return this;
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> error(@DrawableRes int i) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).mo1584error(i);
        } else {
            this.requestOptions = new GlideOptions().mo1570apply(this.requestOptions).mo1584error(i);
        }
        return this;
    }

    @Override // com.bumptech.glide.RequestBuilder
    @NonNull
    @SafeVarargs
    @CheckResult
    /* renamed from: thumbnail */
    public final GlideRequest<TranscodeType> mo1631thumbnail(@Nullable RequestBuilder<TranscodeType>... requestBuilderArr) {
        return (GlideRequest) super.mo1631thumbnail((RequestBuilder[]) requestBuilderArr);
    }

    @Override // com.bumptech.glide.RequestBuilder
    @NonNull
    @CheckResult
    /* renamed from: thumbnail */
    public GlideRequest<TranscodeType> mo1629thumbnail(float f) {
        return (GlideRequest) super.mo1629thumbnail(f);
    }

    @Override // com.bumptech.glide.RequestBuilder
    @NonNull
    /* renamed from: error */
    public GlideRequest<TranscodeType> mo1617error(@Nullable RequestBuilder<TranscodeType> requestBuilder) {
        return (GlideRequest) super.mo1617error((RequestBuilder) requestBuilder);
    }

    @Override // com.bumptech.glide.RequestBuilder, com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    /* renamed from: load */
    public GlideRequest<TranscodeType> mo6761load(@Nullable Object obj) {
        return (GlideRequest) super.mo6761load(obj);
    }

    @Override // com.bumptech.glide.RequestBuilder, com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    /* renamed from: load */
    public GlideRequest<TranscodeType> mo6756load(@Nullable Bitmap bitmap) {
        return (GlideRequest) super.mo6756load(bitmap);
    }

    @Override // com.bumptech.glide.RequestBuilder, com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    /* renamed from: load */
    public GlideRequest<TranscodeType> mo6757load(@Nullable Drawable drawable) {
        return (GlideRequest) super.mo6757load(drawable);
    }

    @Override // com.bumptech.glide.RequestBuilder, com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    /* renamed from: load */
    public GlideRequest<TranscodeType> mo6762load(@Nullable String str) {
        return (GlideRequest) super.mo6762load(str);
    }

    @Override // com.bumptech.glide.RequestBuilder, com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    /* renamed from: load */
    public GlideRequest<TranscodeType> mo6758load(@Nullable Uri uri) {
        return (GlideRequest) super.mo6758load(uri);
    }

    @Override // com.bumptech.glide.RequestBuilder, com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    /* renamed from: load */
    public GlideRequest<TranscodeType> mo6759load(@Nullable File file) {
        return (GlideRequest) super.mo6759load(file);
    }

    @Override // com.bumptech.glide.RequestBuilder, com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    /* renamed from: load */
    public GlideRequest<TranscodeType> mo6760load(@Nullable @DrawableRes @RawRes Integer num) {
        return (GlideRequest) super.mo6760load(num);
    }

    @Override // com.bumptech.glide.RequestBuilder, com.bumptech.glide.ModelTypes
    @CheckResult
    @Deprecated
    /* renamed from: load */
    public GlideRequest<TranscodeType> mo6763load(@Nullable URL url) {
        return (GlideRequest) super.mo6763load(url);
    }

    @Override // com.bumptech.glide.RequestBuilder, com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    /* renamed from: load */
    public GlideRequest<TranscodeType> mo6764load(@Nullable byte[] bArr) {
        return (GlideRequest) super.mo6764load(bArr);
    }
}
