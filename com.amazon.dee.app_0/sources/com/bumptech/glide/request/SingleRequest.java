package com.bumptech.glide.request;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pools;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.drawable.DrawableDecoderCompat;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.TransitionFactory;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Util;
import com.bumptech.glide.util.pool.FactoryPools;
import com.bumptech.glide.util.pool.StateVerifier;
/* loaded from: classes9.dex */
public final class SingleRequest<R> implements Request, SizeReadyCallback, ResourceCallback, FactoryPools.Poolable {
    private static final String GLIDE_TAG = "Glide";
    private TransitionFactory<? super R> animationFactory;
    private Context context;
    private Engine engine;
    private Drawable errorDrawable;
    private Drawable fallbackDrawable;
    private GlideContext glideContext;
    private int height;
    private boolean isCallingCallbacks;
    private Engine.LoadStatus loadStatus;
    @Nullable
    private Object model;
    private int overrideHeight;
    private int overrideWidth;
    private Drawable placeholderDrawable;
    private Priority priority;
    private RequestCoordinator requestCoordinator;
    private RequestListener<R> requestListener;
    private RequestOptions requestOptions;
    private Resource<R> resource;
    private long startTime;
    private final StateVerifier stateVerifier;
    private Status status;
    @Nullable
    private final String tag;
    private Target<R> target;
    @Nullable
    private RequestListener<R> targetListener;
    private Class<R> transcodeClass;
    private int width;
    private static final Pools.Pool<SingleRequest<?>> POOL = FactoryPools.simple(150, new FactoryPools.Factory<SingleRequest<?>>() { // from class: com.bumptech.glide.request.SingleRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.bumptech.glide.util.pool.FactoryPools.Factory
        /* renamed from: create */
        public SingleRequest<?> mo6791create() {
            return new SingleRequest<>();
        }
    });
    private static final String TAG = "Request";
    private static final boolean IS_VERBOSE_LOGGABLE = Log.isLoggable(TAG, 2);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public enum Status {
        PENDING,
        RUNNING,
        WAITING_FOR_SIZE,
        COMPLETE,
        FAILED,
        CANCELLED,
        CLEARED,
        PAUSED
    }

    SingleRequest() {
        this.tag = IS_VERBOSE_LOGGABLE ? String.valueOf(super.hashCode()) : null;
        this.stateVerifier = StateVerifier.newInstance();
    }

    private void assertNotCallingCallbacks() {
        if (!this.isCallingCallbacks) {
            return;
        }
        throw new IllegalStateException("You can't start or clear loads in RequestListener or Target callbacks. If you're trying to start a fallback request when a load fails, use RequestBuilder#error(RequestBuilder). Otherwise consider posting your into() or clear() calls to the main thread using a Handler instead.");
    }

    private boolean canNotifyCleared() {
        RequestCoordinator requestCoordinator = this.requestCoordinator;
        return requestCoordinator == null || requestCoordinator.canNotifyCleared(this);
    }

    private boolean canNotifyStatusChanged() {
        RequestCoordinator requestCoordinator = this.requestCoordinator;
        return requestCoordinator == null || requestCoordinator.canNotifyStatusChanged(this);
    }

    private boolean canSetResource() {
        RequestCoordinator requestCoordinator = this.requestCoordinator;
        return requestCoordinator == null || requestCoordinator.canSetImage(this);
    }

    private Drawable getErrorDrawable() {
        if (this.errorDrawable == null) {
            this.errorDrawable = this.requestOptions.getErrorPlaceholder();
            if (this.errorDrawable == null && this.requestOptions.getErrorId() > 0) {
                this.errorDrawable = loadDrawable(this.requestOptions.getErrorId());
            }
        }
        return this.errorDrawable;
    }

    private Drawable getFallbackDrawable() {
        if (this.fallbackDrawable == null) {
            this.fallbackDrawable = this.requestOptions.getFallbackDrawable();
            if (this.fallbackDrawable == null && this.requestOptions.getFallbackId() > 0) {
                this.fallbackDrawable = loadDrawable(this.requestOptions.getFallbackId());
            }
        }
        return this.fallbackDrawable;
    }

    private Drawable getPlaceholderDrawable() {
        if (this.placeholderDrawable == null) {
            this.placeholderDrawable = this.requestOptions.getPlaceholderDrawable();
            if (this.placeholderDrawable == null && this.requestOptions.getPlaceholderId() > 0) {
                this.placeholderDrawable = loadDrawable(this.requestOptions.getPlaceholderId());
            }
        }
        return this.placeholderDrawable;
    }

    private void init(Context context, GlideContext glideContext, Object obj, Class<R> cls, RequestOptions requestOptions, int i, int i2, Priority priority, Target<R> target, RequestListener<R> requestListener, RequestListener<R> requestListener2, RequestCoordinator requestCoordinator, Engine engine, TransitionFactory<? super R> transitionFactory) {
        this.context = context;
        this.glideContext = glideContext;
        this.model = obj;
        this.transcodeClass = cls;
        this.requestOptions = requestOptions;
        this.overrideWidth = i;
        this.overrideHeight = i2;
        this.priority = priority;
        this.target = target;
        this.targetListener = requestListener;
        this.requestListener = requestListener2;
        this.requestCoordinator = requestCoordinator;
        this.engine = engine;
        this.animationFactory = transitionFactory;
        this.status = Status.PENDING;
    }

    private boolean isFirstReadyResource() {
        RequestCoordinator requestCoordinator = this.requestCoordinator;
        return requestCoordinator == null || !requestCoordinator.isAnyResourceSet();
    }

    private Drawable loadDrawable(@DrawableRes int i) {
        return DrawableDecoderCompat.getDrawable(this.glideContext, i, this.requestOptions.getTheme() != null ? this.requestOptions.getTheme() : this.context.getTheme());
    }

    private void logV(String str) {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, " this: ");
        outline113.append(this.tag);
        outline113.toString();
    }

    private static int maybeApplySizeMultiplier(int i, float f) {
        return i == Integer.MIN_VALUE ? i : Math.round(f * i);
    }

    private void notifyLoadFailed() {
        RequestCoordinator requestCoordinator = this.requestCoordinator;
        if (requestCoordinator != null) {
            requestCoordinator.onRequestFailed(this);
        }
    }

    private void notifyLoadSuccess() {
        RequestCoordinator requestCoordinator = this.requestCoordinator;
        if (requestCoordinator != null) {
            requestCoordinator.onRequestSuccess(this);
        }
    }

    public static <R> SingleRequest<R> obtain(Context context, GlideContext glideContext, Object obj, Class<R> cls, RequestOptions requestOptions, int i, int i2, Priority priority, Target<R> target, RequestListener<R> requestListener, RequestListener<R> requestListener2, RequestCoordinator requestCoordinator, Engine engine, TransitionFactory<? super R> transitionFactory) {
        SingleRequest<?> acquire = POOL.acquire();
        if (acquire == null) {
            acquire = new SingleRequest();
        }
        acquire.init(context, glideContext, obj, cls, requestOptions, i, i2, priority, target, requestListener, requestListener2, requestCoordinator, engine, transitionFactory);
        return acquire;
    }

    private void releaseResource(Resource<?> resource) {
        this.engine.release(resource);
        this.resource = null;
    }

    private void setErrorPlaceholder() {
        if (!canNotifyStatusChanged()) {
            return;
        }
        Drawable drawable = null;
        if (this.model == null) {
            drawable = getFallbackDrawable();
        }
        if (drawable == null) {
            drawable = getErrorDrawable();
        }
        if (drawable == null) {
            drawable = getPlaceholderDrawable();
        }
        this.target.onLoadFailed(drawable);
    }

    @Override // com.bumptech.glide.request.Request
    public void begin() {
        assertNotCallingCallbacks();
        this.stateVerifier.throwIfRecycled();
        this.startTime = LogTime.getLogTime();
        if (this.model == null) {
            if (Util.isValidDimensions(this.overrideWidth, this.overrideHeight)) {
                this.width = this.overrideWidth;
                this.height = this.overrideHeight;
            }
            onLoadFailed(new GlideException("Received null model"), getFallbackDrawable() == null ? 5 : 3);
            return;
        }
        Status status = this.status;
        if (status != Status.RUNNING) {
            if (status == Status.COMPLETE) {
                onResourceReady(this.resource, DataSource.MEMORY_CACHE);
                return;
            }
            this.status = Status.WAITING_FOR_SIZE;
            if (Util.isValidDimensions(this.overrideWidth, this.overrideHeight)) {
                onSizeReady(this.overrideWidth, this.overrideHeight);
            } else {
                this.target.getSize(this);
            }
            Status status2 = this.status;
            if ((status2 == Status.RUNNING || status2 == Status.WAITING_FOR_SIZE) && canNotifyStatusChanged()) {
                this.target.onLoadStarted(getPlaceholderDrawable());
            }
            if (!IS_VERBOSE_LOGGABLE) {
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("finished run method in ");
            outline107.append(LogTime.getElapsedMillis(this.startTime));
            logV(outline107.toString());
            return;
        }
        throw new IllegalArgumentException("Cannot restart a running request");
    }

    void cancel() {
        assertNotCallingCallbacks();
        this.stateVerifier.throwIfRecycled();
        this.target.removeCallback(this);
        this.status = Status.CANCELLED;
        Engine.LoadStatus loadStatus = this.loadStatus;
        if (loadStatus != null) {
            loadStatus.cancel();
            this.loadStatus = null;
        }
    }

    @Override // com.bumptech.glide.request.Request
    public void clear() {
        Util.assertMainThread();
        assertNotCallingCallbacks();
        this.stateVerifier.throwIfRecycled();
        if (this.status == Status.CLEARED) {
            return;
        }
        cancel();
        Resource<R> resource = this.resource;
        if (resource != null) {
            releaseResource(resource);
        }
        if (canNotifyCleared()) {
            this.target.onLoadCleared(getPlaceholderDrawable());
        }
        this.status = Status.CLEARED;
    }

    @Override // com.bumptech.glide.util.pool.FactoryPools.Poolable
    @NonNull
    public StateVerifier getVerifier() {
        return this.stateVerifier;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isCancelled() {
        Status status = this.status;
        return status == Status.CANCELLED || status == Status.CLEARED;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isComplete() {
        return this.status == Status.COMPLETE;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isEquivalentTo(Request request) {
        if (request instanceof SingleRequest) {
            SingleRequest singleRequest = (SingleRequest) request;
            if (this.overrideWidth != singleRequest.overrideWidth || this.overrideHeight != singleRequest.overrideHeight || !Util.bothModelsNullEquivalentOrEquals(this.model, singleRequest.model) || !this.transcodeClass.equals(singleRequest.transcodeClass) || !this.requestOptions.equals(singleRequest.requestOptions) || this.priority != singleRequest.priority) {
                return false;
            }
            RequestListener<R> requestListener = this.requestListener;
            RequestListener<R> requestListener2 = singleRequest.requestListener;
            if (requestListener != null) {
                if (requestListener2 == null) {
                    return false;
                }
            } else if (requestListener2 != null) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isFailed() {
        return this.status == Status.FAILED;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isPaused() {
        return this.status == Status.PAUSED;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isResourceSet() {
        return isComplete();
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isRunning() {
        Status status = this.status;
        return status == Status.RUNNING || status == Status.WAITING_FOR_SIZE;
    }

    @Override // com.bumptech.glide.request.ResourceCallback
    public void onLoadFailed(GlideException glideException) {
        onLoadFailed(glideException, 5);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bumptech.glide.request.ResourceCallback
    public void onResourceReady(Resource<?> resource, DataSource dataSource) {
        this.stateVerifier.throwIfRecycled();
        this.loadStatus = null;
        if (resource == null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expected to receive a Resource<R> with an object of ");
            outline107.append(this.transcodeClass);
            outline107.append(" inside, but instead got null.");
            onLoadFailed(new GlideException(outline107.toString()));
            return;
        }
        Object mo6789get = resource.mo6789get();
        if (mo6789get != null && this.transcodeClass.isAssignableFrom(mo6789get.getClass())) {
            if (!canSetResource()) {
                releaseResource(resource);
                this.status = Status.COMPLETE;
                return;
            }
            onResourceReady(resource, mo6789get, dataSource);
            return;
        }
        releaseResource(resource);
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Expected to receive an object of ");
        outline1072.append(this.transcodeClass);
        outline1072.append(" but instead got ");
        String str = "";
        outline1072.append(mo6789get != null ? mo6789get.getClass() : str);
        outline1072.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        outline1072.append(mo6789get);
        outline1072.append("} inside Resource{");
        outline1072.append(resource);
        outline1072.append("}.");
        String str2 = str;
        if (mo6789get == null) {
            str2 = " To indicate failure return a null Resource object, rather than a Resource object containing null data.";
        }
        outline1072.append(str2);
        onLoadFailed(new GlideException(outline1072.toString()));
    }

    @Override // com.bumptech.glide.request.target.SizeReadyCallback
    public void onSizeReady(int i, int i2) {
        this.stateVerifier.throwIfRecycled();
        if (IS_VERBOSE_LOGGABLE) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Got onSizeReady in ");
            outline107.append(LogTime.getElapsedMillis(this.startTime));
            logV(outline107.toString());
        }
        if (this.status != Status.WAITING_FOR_SIZE) {
            return;
        }
        this.status = Status.RUNNING;
        float sizeMultiplier = this.requestOptions.getSizeMultiplier();
        this.width = maybeApplySizeMultiplier(i, sizeMultiplier);
        this.height = maybeApplySizeMultiplier(i2, sizeMultiplier);
        if (IS_VERBOSE_LOGGABLE) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("finished setup for calling load in ");
            outline1072.append(LogTime.getElapsedMillis(this.startTime));
            logV(outline1072.toString());
        }
        this.loadStatus = this.engine.load(this.glideContext, this.model, this.requestOptions.getSignature(), this.width, this.height, this.requestOptions.getResourceClass(), this.transcodeClass, this.priority, this.requestOptions.getDiskCacheStrategy(), this.requestOptions.getTransformations(), this.requestOptions.isTransformationRequired(), this.requestOptions.isScaleOnlyOrNoTransform(), this.requestOptions.getOptions(), this.requestOptions.isMemoryCacheable(), this.requestOptions.getUseUnlimitedSourceGeneratorsPool(), this.requestOptions.getUseAnimationPool(), this.requestOptions.getOnlyRetrieveFromCache(), this);
        if (this.status != Status.RUNNING) {
            this.loadStatus = null;
        }
        if (!IS_VERBOSE_LOGGABLE) {
            return;
        }
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("finished onSizeReady in ");
        outline1073.append(LogTime.getElapsedMillis(this.startTime));
        logV(outline1073.toString());
    }

    @Override // com.bumptech.glide.request.Request
    public void pause() {
        clear();
        this.status = Status.PAUSED;
    }

    @Override // com.bumptech.glide.request.Request
    public void recycle() {
        assertNotCallingCallbacks();
        this.context = null;
        this.glideContext = null;
        this.model = null;
        this.transcodeClass = null;
        this.requestOptions = null;
        this.overrideWidth = -1;
        this.overrideHeight = -1;
        this.target = null;
        this.requestListener = null;
        this.targetListener = null;
        this.requestCoordinator = null;
        this.animationFactory = null;
        this.loadStatus = null;
        this.errorDrawable = null;
        this.placeholderDrawable = null;
        this.fallbackDrawable = null;
        this.width = -1;
        this.height = -1;
        POOL.release(this);
    }

    private void onLoadFailed(GlideException glideException, int i) {
        this.stateVerifier.throwIfRecycled();
        int logLevel = this.glideContext.getLogLevel();
        if (logLevel <= i) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Load failed for ");
            outline107.append(this.model);
            outline107.append(" with size [");
            outline107.append(this.width);
            outline107.append(ReactProperties.HereMapMarker.X);
            outline107.append(this.height);
            outline107.append("]");
            Log.w(GLIDE_TAG, outline107.toString(), glideException);
            if (logLevel <= 4) {
                glideException.logRootCauses(GLIDE_TAG);
            }
        }
        this.loadStatus = null;
        this.status = Status.FAILED;
        this.isCallingCallbacks = true;
        try {
            if ((this.requestListener == null || !this.requestListener.onLoadFailed(glideException, this.model, this.target, isFirstReadyResource())) && (this.targetListener == null || !this.targetListener.onLoadFailed(glideException, this.model, this.target, isFirstReadyResource()))) {
                setErrorPlaceholder();
            }
            this.isCallingCallbacks = false;
            notifyLoadFailed();
        } catch (Throwable th) {
            this.isCallingCallbacks = false;
            throw th;
        }
    }

    private void onResourceReady(Resource<R> resource, R r, DataSource dataSource) {
        boolean isFirstReadyResource = isFirstReadyResource();
        this.status = Status.COMPLETE;
        this.resource = resource;
        if (this.glideContext.getLogLevel() <= 3) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Finished loading ");
            outline107.append(r.getClass().getSimpleName());
            outline107.append(" from ");
            outline107.append(dataSource);
            outline107.append(" for ");
            outline107.append(this.model);
            outline107.append(" with size [");
            outline107.append(this.width);
            outline107.append(ReactProperties.HereMapMarker.X);
            outline107.append(this.height);
            outline107.append("] in ");
            outline107.append(LogTime.getElapsedMillis(this.startTime));
            outline107.append(" ms");
            outline107.toString();
        }
        this.isCallingCallbacks = true;
        try {
            if ((this.requestListener == null || !this.requestListener.onResourceReady(r, this.model, this.target, dataSource, isFirstReadyResource)) && (this.targetListener == null || !this.targetListener.onResourceReady(r, this.model, this.target, dataSource, isFirstReadyResource))) {
                this.target.onResourceReady(r, this.animationFactory.build(dataSource, isFirstReadyResource));
            }
            this.isCallingCallbacks = false;
            notifyLoadSuccess();
        } catch (Throwable th) {
            this.isCallingCallbacks = false;
            throw th;
        }
    }
}
