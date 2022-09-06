package com.bumptech.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.ErrorRequestCoordinator;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestCoordinator;
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.SingleRequest;
import com.bumptech.glide.request.ThumbnailRequestCoordinator;
import com.bumptech.glide.request.target.PreloadTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.signature.ApplicationVersionSignature;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.net.URL;
/* loaded from: classes.dex */
public class RequestBuilder<TranscodeType> implements Cloneable, ModelTypes<RequestBuilder<TranscodeType>> {
    protected static final RequestOptions DOWNLOAD_ONLY_OPTIONS = new RequestOptions().mo1578diskCacheStrategy(DiskCacheStrategy.DATA).mo1603priority(Priority.LOW).mo1607skipMemoryCache(true);
    private final Context context;
    private final RequestOptions defaultRequestOptions;
    @Nullable
    private RequestBuilder<TranscodeType> errorBuilder;
    private final Glide glide;
    private final GlideContext glideContext;
    private boolean isDefaultTransitionOptionsSet;
    private boolean isModelSet;
    private boolean isThumbnailBuilt;
    @Nullable
    private Object model;
    @Nullable
    private RequestListener<TranscodeType> requestListener;
    private final RequestManager requestManager;
    @NonNull
    protected RequestOptions requestOptions;
    @Nullable
    private Float thumbSizeMultiplier;
    @Nullable
    private RequestBuilder<TranscodeType> thumbnailBuilder;
    private final Class<TranscodeType> transcodeClass;
    @NonNull
    private TransitionOptions<?, ? super TranscodeType> transitionOptions;

    /* renamed from: com.bumptech.glide.RequestBuilder$2  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType;
        static final /* synthetic */ int[] $SwitchMap$com$bumptech$glide$Priority = new int[Priority.values().length];

        static {
            try {
                $SwitchMap$com$bumptech$glide$Priority[Priority.LOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$bumptech$glide$Priority[Priority.NORMAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$bumptech$glide$Priority[Priority.HIGH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$bumptech$glide$Priority[Priority.IMMEDIATE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $SwitchMap$android$widget$ImageView$ScaleType = new int[ImageView.ScaleType.values().length];
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_CROP.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_START.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_XY.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.MATRIX.ordinal()] = 8;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public RequestBuilder(Glide glide, RequestManager requestManager, Class<TranscodeType> cls, Context context) {
        this.isDefaultTransitionOptionsSet = true;
        this.glide = glide;
        this.requestManager = requestManager;
        this.transcodeClass = cls;
        this.defaultRequestOptions = requestManager.getDefaultRequestOptions();
        this.context = context;
        this.transitionOptions = requestManager.getDefaultTransitionOptions(cls);
        this.requestOptions = this.defaultRequestOptions;
        this.glideContext = glide.getGlideContext();
    }

    private Request buildRequest(Target<TranscodeType> target, @Nullable RequestListener<TranscodeType> requestListener, RequestOptions requestOptions) {
        return buildRequestRecursive(target, requestListener, null, this.transitionOptions, requestOptions.getPriority(), requestOptions.getOverrideWidth(), requestOptions.getOverrideHeight(), requestOptions);
    }

    private Request buildRequestRecursive(Target<TranscodeType> target, @Nullable RequestListener<TranscodeType> requestListener, @Nullable RequestCoordinator requestCoordinator, TransitionOptions<?, ? super TranscodeType> transitionOptions, Priority priority, int i, int i2, RequestOptions requestOptions) {
        ErrorRequestCoordinator errorRequestCoordinator;
        ErrorRequestCoordinator errorRequestCoordinator2;
        if (this.errorBuilder != null) {
            errorRequestCoordinator2 = new ErrorRequestCoordinator(requestCoordinator);
            errorRequestCoordinator = errorRequestCoordinator2;
        } else {
            errorRequestCoordinator = null;
            errorRequestCoordinator2 = requestCoordinator;
        }
        Request buildThumbnailRequestRecursive = buildThumbnailRequestRecursive(target, requestListener, errorRequestCoordinator2, transitionOptions, priority, i, i2, requestOptions);
        if (errorRequestCoordinator == null) {
            return buildThumbnailRequestRecursive;
        }
        int overrideWidth = this.errorBuilder.requestOptions.getOverrideWidth();
        int overrideHeight = this.errorBuilder.requestOptions.getOverrideHeight();
        if (Util.isValidDimensions(i, i2) && !this.errorBuilder.requestOptions.isValidOverride()) {
            overrideWidth = requestOptions.getOverrideWidth();
            overrideHeight = requestOptions.getOverrideHeight();
        }
        RequestBuilder<TranscodeType> requestBuilder = this.errorBuilder;
        ErrorRequestCoordinator errorRequestCoordinator3 = errorRequestCoordinator;
        errorRequestCoordinator3.setRequests(buildThumbnailRequestRecursive, requestBuilder.buildRequestRecursive(target, requestListener, errorRequestCoordinator, requestBuilder.transitionOptions, requestBuilder.requestOptions.getPriority(), overrideWidth, overrideHeight, this.errorBuilder.requestOptions));
        return errorRequestCoordinator3;
    }

    private Request buildThumbnailRequestRecursive(Target<TranscodeType> target, RequestListener<TranscodeType> requestListener, @Nullable RequestCoordinator requestCoordinator, TransitionOptions<?, ? super TranscodeType> transitionOptions, Priority priority, int i, int i2, RequestOptions requestOptions) {
        RequestBuilder<TranscodeType> requestBuilder = this.thumbnailBuilder;
        if (requestBuilder != null) {
            if (!this.isThumbnailBuilt) {
                TransitionOptions<?, ? super TranscodeType> transitionOptions2 = requestBuilder.isDefaultTransitionOptionsSet ? transitionOptions : requestBuilder.transitionOptions;
                Priority priority2 = this.thumbnailBuilder.requestOptions.isPrioritySet() ? this.thumbnailBuilder.requestOptions.getPriority() : getThumbnailPriority(priority);
                int overrideWidth = this.thumbnailBuilder.requestOptions.getOverrideWidth();
                int overrideHeight = this.thumbnailBuilder.requestOptions.getOverrideHeight();
                if (Util.isValidDimensions(i, i2) && !this.thumbnailBuilder.requestOptions.isValidOverride()) {
                    overrideWidth = requestOptions.getOverrideWidth();
                    overrideHeight = requestOptions.getOverrideHeight();
                }
                ThumbnailRequestCoordinator thumbnailRequestCoordinator = new ThumbnailRequestCoordinator(requestCoordinator);
                Request obtainRequest = obtainRequest(target, requestListener, requestOptions, thumbnailRequestCoordinator, transitionOptions, priority, i, i2);
                this.isThumbnailBuilt = true;
                RequestBuilder<TranscodeType> requestBuilder2 = this.thumbnailBuilder;
                Request buildRequestRecursive = requestBuilder2.buildRequestRecursive(target, requestListener, thumbnailRequestCoordinator, transitionOptions2, priority2, overrideWidth, overrideHeight, requestBuilder2.requestOptions);
                this.isThumbnailBuilt = false;
                thumbnailRequestCoordinator.setRequests(obtainRequest, buildRequestRecursive);
                return thumbnailRequestCoordinator;
            }
            throw new IllegalStateException("You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()");
        } else if (this.thumbSizeMultiplier != null) {
            ThumbnailRequestCoordinator thumbnailRequestCoordinator2 = new ThumbnailRequestCoordinator(requestCoordinator);
            thumbnailRequestCoordinator2.setRequests(obtainRequest(target, requestListener, requestOptions, thumbnailRequestCoordinator2, transitionOptions, priority, i, i2), obtainRequest(target, requestListener, requestOptions.clone().mo1606sizeMultiplier(this.thumbSizeMultiplier.floatValue()), thumbnailRequestCoordinator2, transitionOptions, getThumbnailPriority(priority), i, i2));
            return thumbnailRequestCoordinator2;
        } else {
            return obtainRequest(target, requestListener, requestOptions, requestCoordinator, transitionOptions, priority, i, i2);
        }
    }

    @NonNull
    private Priority getThumbnailPriority(@NonNull Priority priority) {
        int ordinal = priority.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            return Priority.IMMEDIATE;
        }
        if (ordinal == 2) {
            return Priority.HIGH;
        }
        if (ordinal == 3) {
            return Priority.NORMAL;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unknown priority: ");
        outline107.append(this.requestOptions.getPriority());
        throw new IllegalArgumentException(outline107.toString());
    }

    private boolean isSkipMemoryCacheWithCompletePreviousRequest(RequestOptions requestOptions, Request request) {
        return !requestOptions.isMemoryCacheable() && request.isComplete();
    }

    @NonNull
    private RequestBuilder<TranscodeType> loadGeneric(@Nullable Object obj) {
        this.model = obj;
        this.isModelSet = true;
        return this;
    }

    private Request obtainRequest(Target<TranscodeType> target, RequestListener<TranscodeType> requestListener, RequestOptions requestOptions, RequestCoordinator requestCoordinator, TransitionOptions<?, ? super TranscodeType> transitionOptions, Priority priority, int i, int i2) {
        Context context = this.context;
        GlideContext glideContext = this.glideContext;
        return SingleRequest.obtain(context, glideContext, this.model, this.transcodeClass, requestOptions, i, i2, priority, target, requestListener, this.requestListener, requestCoordinator, glideContext.getEngine(), transitionOptions.getTransitionFactory());
    }

    @NonNull
    @CheckResult
    /* renamed from: apply */
    public RequestBuilder<TranscodeType> mo1615apply(@NonNull RequestOptions requestOptions) {
        Preconditions.checkNotNull(requestOptions);
        this.requestOptions = getMutableOptions().mo1570apply(requestOptions);
        return this;
    }

    @CheckResult
    @Deprecated
    public <Y extends Target<File>> Y downloadOnly(@NonNull Y y) {
        return (Y) mo1618getDownloadOnlyRequest().into((RequestBuilder<File>) y);
    }

    @NonNull
    /* renamed from: error */
    public RequestBuilder<TranscodeType> mo1617error(@Nullable RequestBuilder<TranscodeType> requestBuilder) {
        this.errorBuilder = requestBuilder;
        return this;
    }

    @NonNull
    @CheckResult
    /* renamed from: getDownloadOnlyRequest */
    protected RequestBuilder<File> mo1618getDownloadOnlyRequest() {
        return new RequestBuilder(File.class, this).mo1615apply(DOWNLOAD_ONLY_OPTIONS);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NonNull
    public RequestOptions getMutableOptions() {
        RequestOptions requestOptions = this.defaultRequestOptions;
        RequestOptions requestOptions2 = this.requestOptions;
        return requestOptions == requestOptions2 ? requestOptions2.clone() : requestOptions2;
    }

    @NonNull
    public <Y extends Target<TranscodeType>> Y into(@NonNull Y y) {
        return (Y) into((RequestBuilder<TranscodeType>) y, (RequestListener) null);
    }

    @NonNull
    @CheckResult
    /* renamed from: listener */
    public RequestBuilder<TranscodeType> mo1619listener(@Nullable RequestListener<TranscodeType> requestListener) {
        this.requestListener = requestListener;
        return this;
    }

    @NonNull
    public Target<TranscodeType> preload(int i, int i2) {
        return into((RequestBuilder<TranscodeType>) PreloadTarget.obtain(this.requestManager, i, i2));
    }

    @NonNull
    public FutureTarget<TranscodeType> submit() {
        return submit(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @NonNull
    @CheckResult
    /* renamed from: thumbnail */
    public RequestBuilder<TranscodeType> mo1630thumbnail(@Nullable RequestBuilder<TranscodeType> requestBuilder) {
        this.thumbnailBuilder = requestBuilder;
        return this;
    }

    @NonNull
    @CheckResult
    /* renamed from: transition */
    public RequestBuilder<TranscodeType> mo1632transition(@NonNull TransitionOptions<?, ? super TranscodeType> transitionOptions) {
        this.transitionOptions = (TransitionOptions) Preconditions.checkNotNull(transitionOptions);
        this.isDefaultTransitionOptionsSet = false;
        return this;
    }

    @CheckResult
    public RequestBuilder<TranscodeType> clone() {
        try {
            RequestBuilder<TranscodeType> requestBuilder = (RequestBuilder) super.clone();
            requestBuilder.requestOptions = requestBuilder.requestOptions.clone();
            requestBuilder.transitionOptions = (TransitionOptions<?, ? super TranscodeType>) requestBuilder.transitionOptions.m6765clone();
            return requestBuilder;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @CheckResult
    @Deprecated
    public FutureTarget<File> downloadOnly(int i, int i2) {
        return mo1618getDownloadOnlyRequest().submit(i, i2);
    }

    @NonNull
    <Y extends Target<TranscodeType>> Y into(@NonNull Y y, @Nullable RequestListener<TranscodeType> requestListener) {
        return (Y) into(y, requestListener, getMutableOptions());
    }

    @NonNull
    public FutureTarget<TranscodeType> submit(int i, int i2) {
        final RequestFutureTarget requestFutureTarget = new RequestFutureTarget(this.glideContext.getMainHandler(), i, i2);
        if (Util.isOnBackgroundThread()) {
            this.glideContext.getMainHandler().post(new Runnable() { // from class: com.bumptech.glide.RequestBuilder.1
                @Override // java.lang.Runnable
                public void run() {
                    if (!requestFutureTarget.isCancelled()) {
                        RequestBuilder requestBuilder = RequestBuilder.this;
                        RequestFutureTarget requestFutureTarget2 = requestFutureTarget;
                        requestBuilder.into((RequestBuilder) requestFutureTarget2, (RequestListener) requestFutureTarget2);
                    }
                }
            });
        } else {
            into((RequestBuilder<TranscodeType>) requestFutureTarget, requestFutureTarget);
        }
        return requestFutureTarget;
    }

    @NonNull
    @CheckResult
    /* renamed from: thumbnail */
    public RequestBuilder<TranscodeType> mo1631thumbnail(@Nullable RequestBuilder<TranscodeType>... requestBuilderArr) {
        RequestBuilder<TranscodeType> requestBuilder = null;
        if (requestBuilderArr != null && requestBuilderArr.length != 0) {
            for (int length = requestBuilderArr.length - 1; length >= 0; length--) {
                RequestBuilder<TranscodeType> requestBuilder2 = requestBuilderArr[length];
                if (requestBuilder2 != null) {
                    requestBuilder = requestBuilder == null ? requestBuilder2 : requestBuilder2.mo1630thumbnail(requestBuilder);
                }
            }
            return mo1630thumbnail(requestBuilder);
        }
        return mo1630thumbnail((RequestBuilder) null);
    }

    private <Y extends Target<TranscodeType>> Y into(@NonNull Y y, @Nullable RequestListener<TranscodeType> requestListener, @NonNull RequestOptions requestOptions) {
        Util.assertMainThread();
        Preconditions.checkNotNull(y);
        if (this.isModelSet) {
            RequestOptions mo1571autoClone = requestOptions.mo1571autoClone();
            Request buildRequest = buildRequest(y, requestListener, mo1571autoClone);
            Request request = y.getRequest();
            if (buildRequest.isEquivalentTo(request) && !isSkipMemoryCacheWithCompletePreviousRequest(mo1571autoClone, request)) {
                buildRequest.recycle();
                if (!((Request) Preconditions.checkNotNull(request)).isRunning()) {
                    request.begin();
                }
                return y;
            }
            this.requestManager.clear((Target<?>) y);
            y.setRequest(buildRequest);
            this.requestManager.track(y, buildRequest);
            return y;
        }
        throw new IllegalArgumentException("You must call #load() before calling #into()");
    }

    @NonNull
    public Target<TranscodeType> preload() {
        return preload(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @NonNull
    @CheckResult
    /* renamed from: thumbnail */
    public RequestBuilder<TranscodeType> mo1629thumbnail(float f) {
        if (f >= 0.0f && f <= 1.0f) {
            this.thumbSizeMultiplier = Float.valueOf(f);
            return this;
        }
        throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
    }

    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<TranscodeType> mo6761load(@Nullable Object obj) {
        return loadGeneric(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public RequestBuilder(Class<TranscodeType> cls, RequestBuilder<?> requestBuilder) {
        this(requestBuilder.glide, requestBuilder.requestManager, cls, requestBuilder.context);
        this.model = requestBuilder.model;
        this.isModelSet = requestBuilder.isModelSet;
        this.requestOptions = requestBuilder.requestOptions;
    }

    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<TranscodeType> mo6756load(@Nullable Bitmap bitmap) {
        return loadGeneric(bitmap).mo1615apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE));
    }

    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<TranscodeType> mo6757load(@Nullable Drawable drawable) {
        return loadGeneric(drawable).mo1615apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE));
    }

    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<TranscodeType> mo6762load(@Nullable String str) {
        return loadGeneric(str);
    }

    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<TranscodeType> mo6758load(@Nullable Uri uri) {
        return loadGeneric(uri);
    }

    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<TranscodeType> mo6759load(@Nullable File file) {
        return loadGeneric(file);
    }

    @NonNull
    public ViewTarget<ImageView, TranscodeType> into(@NonNull ImageView imageView) {
        Util.assertMainThread();
        Preconditions.checkNotNull(imageView);
        RequestOptions requestOptions = this.requestOptions;
        if (!requestOptions.isTransformationSet() && requestOptions.isTransformationAllowed() && imageView.getScaleType() != null) {
            switch (AnonymousClass2.$SwitchMap$android$widget$ImageView$ScaleType[imageView.getScaleType().ordinal()]) {
                case 1:
                    requestOptions = requestOptions.clone().mo1593optionalCenterCrop();
                    break;
                case 2:
                    requestOptions = requestOptions.clone().mo1594optionalCenterInside();
                    break;
                case 3:
                case 4:
                case 5:
                    requestOptions = requestOptions.clone().mo1596optionalFitCenter();
                    break;
                case 6:
                    requestOptions = requestOptions.clone().mo1594optionalCenterInside();
                    break;
            }
        }
        return (ViewTarget) into(this.glideContext.buildImageViewTarget(imageView, this.transcodeClass), null, requestOptions);
    }

    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<TranscodeType> mo6760load(@Nullable @DrawableRes @RawRes Integer num) {
        return loadGeneric(num).mo1615apply(RequestOptions.signatureOf(ApplicationVersionSignature.obtain(this.context)));
    }

    @CheckResult
    @Deprecated
    /* renamed from: load */
    public RequestBuilder<TranscodeType> mo6763load(@Nullable URL url) {
        return loadGeneric(url);
    }

    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<TranscodeType> mo6764load(@Nullable byte[] bArr) {
        RequestBuilder<TranscodeType> loadGeneric = loadGeneric(bArr);
        if (!loadGeneric.requestOptions.isDiskCacheStrategySet()) {
            loadGeneric = loadGeneric.mo1615apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE));
        }
        return !loadGeneric.requestOptions.isSkipMemoryCacheSet() ? loadGeneric.mo1615apply(RequestOptions.skipMemoryCacheOf(true)) : loadGeneric;
    }

    @Deprecated
    public FutureTarget<TranscodeType> into(int i, int i2) {
        return submit(i, i2);
    }
}
