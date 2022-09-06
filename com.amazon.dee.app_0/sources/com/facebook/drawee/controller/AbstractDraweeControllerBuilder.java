package com.facebook.drawee.controller;

import android.content.Context;
import android.graphics.drawable.Animatable;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSources;
import com.facebook.datasource.FirstAvailableDataSourceSupplier;
import com.facebook.datasource.IncreasingQualityDataSourceSupplier;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.gestures.GestureDetector;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.SimpleDraweeControllerBuilder;
import com.facebook.fresco.ui.common.ControllerListener2;
import com.facebook.fresco.ui.common.LoggingListener;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import com.facebook.infer.annotation.ReturnsOwnership;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public abstract class AbstractDraweeControllerBuilder<BUILDER extends AbstractDraweeControllerBuilder<BUILDER, REQUEST, IMAGE, INFO>, REQUEST, IMAGE, INFO> implements SimpleDraweeControllerBuilder {
    private boolean mAutoPlayAnimations;
    private final Set<ControllerListener> mBoundControllerListeners;
    private final Set<ControllerListener2> mBoundControllerListeners2;
    @Nullable
    private Object mCallerContext;
    @Nullable
    private String mContentDescription;
    private final Context mContext;
    @Nullable
    private ControllerListener<? super INFO> mControllerListener;
    @Nullable
    private ControllerViewportVisibilityListener mControllerViewportVisibilityListener;
    @Nullable
    private Supplier<DataSource<IMAGE>> mDataSourceSupplier;
    @Nullable
    private REQUEST mImageRequest;
    @Nullable
    private LoggingListener mLoggingListener;
    @Nullable
    private REQUEST mLowResImageRequest;
    @Nullable
    private REQUEST[] mMultiImageRequests;
    @Nullable
    private DraweeController mOldController;
    private boolean mRetainImageOnFailure;
    private boolean mTapToRetryEnabled;
    private boolean mTryCacheOnlyFirst;
    private static final ControllerListener<Object> sAutoPlayAnimationsListener = new BaseControllerListener<Object>() { // from class: com.facebook.drawee.controller.AbstractDraweeControllerBuilder.1
        @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
        public void onFinalImageSet(String id, @Nullable Object info, @Nullable Animatable anim) {
            if (anim != null) {
                anim.start();
            }
        }
    };
    private static final NullPointerException NO_REQUEST_EXCEPTION = new NullPointerException("No image request was specified!");
    private static final AtomicLong sIdCounter = new AtomicLong();

    /* loaded from: classes2.dex */
    public enum CacheLevel {
        FULL_FETCH,
        DISK_CACHE,
        BITMAP_MEMORY_CACHE
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractDraweeControllerBuilder(Context context, Set<ControllerListener> boundControllerListeners, Set<ControllerListener2> boundControllerListeners2) {
        this.mContext = context;
        this.mBoundControllerListeners = boundControllerListeners;
        this.mBoundControllerListeners2 = boundControllerListeners2;
        init();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String generateUniqueControllerId() {
        return String.valueOf(sIdCounter.getAndIncrement());
    }

    private void init() {
        this.mCallerContext = null;
        this.mImageRequest = null;
        this.mLowResImageRequest = null;
        this.mMultiImageRequests = null;
        this.mTryCacheOnlyFirst = true;
        this.mControllerListener = null;
        this.mLoggingListener = null;
        this.mControllerViewportVisibilityListener = null;
        this.mTapToRetryEnabled = false;
        this.mAutoPlayAnimations = false;
        this.mOldController = null;
        this.mContentDescription = null;
    }

    protected AbstractDraweeController buildController() {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("AbstractDraweeControllerBuilder#buildController");
        }
        AbstractDraweeController mo6875obtainController = mo6875obtainController();
        mo6875obtainController.setRetainImageOnFailure(getRetainImageOnFailure());
        mo6875obtainController.setContentDescription(getContentDescription());
        mo6875obtainController.setControllerViewportVisibilityListener(getControllerViewportVisibilityListener());
        maybeBuildAndSetRetryManager(mo6875obtainController);
        maybeAttachListeners(mo6875obtainController);
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
        return mo6875obtainController;
    }

    public boolean getAutoPlayAnimations() {
        return this.mAutoPlayAnimations;
    }

    @Nullable
    public Object getCallerContext() {
        return this.mCallerContext;
    }

    @Nullable
    public String getContentDescription() {
        return this.mContentDescription;
    }

    protected Context getContext() {
        return this.mContext;
    }

    @Nullable
    public ControllerListener<? super INFO> getControllerListener() {
        return this.mControllerListener;
    }

    @Nullable
    public ControllerViewportVisibilityListener getControllerViewportVisibilityListener() {
        return this.mControllerViewportVisibilityListener;
    }

    protected abstract DataSource<IMAGE> getDataSourceForRequest(final DraweeController controller, final String controllerId, final REQUEST imageRequest, final Object callerContext, final CacheLevel cacheLevel);

    @Nullable
    public Supplier<DataSource<IMAGE>> getDataSourceSupplier() {
        return this.mDataSourceSupplier;
    }

    protected Supplier<DataSource<IMAGE>> getDataSourceSupplierForRequest(final DraweeController controller, String controllerId, REQUEST imageRequest) {
        return getDataSourceSupplierForRequest(controller, controllerId, imageRequest, CacheLevel.FULL_FETCH);
    }

    protected Supplier<DataSource<IMAGE>> getFirstAvailableDataSourceSupplier(final DraweeController controller, String controllerId, REQUEST[] imageRequests, boolean tryBitmapCacheOnlyFirst) {
        ArrayList arrayList = new ArrayList(imageRequests.length * 2);
        if (tryBitmapCacheOnlyFirst) {
            for (REQUEST request : imageRequests) {
                arrayList.add(getDataSourceSupplierForRequest(controller, controllerId, request, CacheLevel.BITMAP_MEMORY_CACHE));
            }
        }
        for (REQUEST request2 : imageRequests) {
            arrayList.add(getDataSourceSupplierForRequest(controller, controllerId, request2));
        }
        return FirstAvailableDataSourceSupplier.create(arrayList);
    }

    @Nullable
    public REQUEST[] getFirstAvailableImageRequests() {
        return this.mMultiImageRequests;
    }

    @Nullable
    public REQUEST getImageRequest() {
        return this.mImageRequest;
    }

    @Nullable
    public LoggingListener getLoggingListener() {
        return this.mLoggingListener;
    }

    @Nullable
    public REQUEST getLowResImageRequest() {
        return this.mLowResImageRequest;
    }

    @Nullable
    public DraweeController getOldController() {
        return this.mOldController;
    }

    public boolean getRetainImageOnFailure() {
        return this.mRetainImageOnFailure;
    }

    public boolean getTapToRetryEnabled() {
        return this.mTapToRetryEnabled;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final BUILDER getThis() {
        return this;
    }

    protected void maybeAttachListeners(AbstractDraweeController controller) {
        Set<ControllerListener> set = this.mBoundControllerListeners;
        if (set != null) {
            for (ControllerListener controllerListener : set) {
                controller.addControllerListener(controllerListener);
            }
        }
        Set<ControllerListener2> set2 = this.mBoundControllerListeners2;
        if (set2 != null) {
            for (ControllerListener2 controllerListener2 : set2) {
                controller.addControllerListener2(controllerListener2);
            }
        }
        ControllerListener<? super INFO> controllerListener3 = this.mControllerListener;
        if (controllerListener3 != null) {
            controller.addControllerListener(controllerListener3);
        }
        if (this.mAutoPlayAnimations) {
            controller.addControllerListener(sAutoPlayAnimationsListener);
        }
    }

    protected void maybeBuildAndSetGestureDetector(AbstractDraweeController controller) {
        if (controller.getGestureDetector() == null) {
            controller.setGestureDetector(GestureDetector.newInstance(this.mContext));
        }
    }

    protected void maybeBuildAndSetRetryManager(AbstractDraweeController controller) {
        if (!this.mTapToRetryEnabled) {
            return;
        }
        controller.getRetryManager().setTapToRetryEnabled(this.mTapToRetryEnabled);
        maybeBuildAndSetGestureDetector(controller);
    }

    @ReturnsOwnership
    /* renamed from: obtainController */
    protected abstract AbstractDraweeController mo6875obtainController();

    /* JADX INFO: Access modifiers changed from: protected */
    public Supplier<DataSource<IMAGE>> obtainDataSourceSupplier(final DraweeController controller, final String controllerId) {
        Supplier<DataSource<IMAGE>> supplier = this.mDataSourceSupplier;
        if (supplier != null) {
            return supplier;
        }
        Supplier<DataSource<IMAGE>> supplier2 = null;
        REQUEST request = this.mImageRequest;
        if (request != null) {
            supplier2 = getDataSourceSupplierForRequest(controller, controllerId, request);
        } else {
            REQUEST[] requestArr = this.mMultiImageRequests;
            if (requestArr != null) {
                supplier2 = getFirstAvailableDataSourceSupplier(controller, controllerId, requestArr, this.mTryCacheOnlyFirst);
            }
        }
        if (supplier2 != null && this.mLowResImageRequest != null) {
            ArrayList arrayList = new ArrayList(2);
            arrayList.add(supplier2);
            arrayList.add(getDataSourceSupplierForRequest(controller, controllerId, this.mLowResImageRequest));
            supplier2 = IncreasingQualityDataSourceSupplier.create(arrayList, false);
        }
        return supplier2 == null ? DataSources.getFailedDataSourceSupplier(NO_REQUEST_EXCEPTION) : supplier2;
    }

    public BUILDER reset() {
        init();
        return getThis();
    }

    public BUILDER setAutoPlayAnimations(boolean enabled) {
        this.mAutoPlayAnimations = enabled;
        return getThis();
    }

    public BUILDER setContentDescription(String contentDescription) {
        this.mContentDescription = contentDescription;
        return getThis();
    }

    public BUILDER setControllerListener(@Nullable ControllerListener<? super INFO> controllerListener) {
        this.mControllerListener = controllerListener;
        return getThis();
    }

    public BUILDER setControllerViewportVisibilityListener(@Nullable ControllerViewportVisibilityListener controllerViewportVisibilityListener) {
        this.mControllerViewportVisibilityListener = controllerViewportVisibilityListener;
        return getThis();
    }

    public BUILDER setDataSourceSupplier(@Nullable Supplier<DataSource<IMAGE>> dataSourceSupplier) {
        this.mDataSourceSupplier = dataSourceSupplier;
        return getThis();
    }

    public BUILDER setFirstAvailableImageRequests(REQUEST[] firstAvailableImageRequests) {
        return setFirstAvailableImageRequests(firstAvailableImageRequests, true);
    }

    public BUILDER setImageRequest(@Nullable REQUEST imageRequest) {
        this.mImageRequest = imageRequest;
        return getThis();
    }

    public BUILDER setLoggingListener(@Nullable LoggingListener loggingListener) {
        this.mLoggingListener = loggingListener;
        return getThis();
    }

    public BUILDER setLowResImageRequest(REQUEST lowResImageRequest) {
        this.mLowResImageRequest = lowResImageRequest;
        return getThis();
    }

    public BUILDER setRetainImageOnFailure(boolean enabled) {
        this.mRetainImageOnFailure = enabled;
        return getThis();
    }

    public BUILDER setTapToRetryEnabled(boolean enabled) {
        this.mTapToRetryEnabled = enabled;
        return getThis();
    }

    protected void validate() {
        boolean z = false;
        Preconditions.checkState(this.mMultiImageRequests == null || this.mImageRequest == null, "Cannot specify both ImageRequest and FirstAvailableImageRequests!");
        if (this.mDataSourceSupplier == null || (this.mMultiImageRequests == null && this.mImageRequest == null && this.mLowResImageRequest == null)) {
            z = true;
        }
        Preconditions.checkState(z, "Cannot specify DataSourceSupplier with other ImageRequests! Use one or the other.");
    }

    @Override // com.facebook.drawee.interfaces.SimpleDraweeControllerBuilder
    /* renamed from: build */
    public AbstractDraweeController mo6879build() {
        REQUEST request;
        validate();
        if (this.mImageRequest == null && this.mMultiImageRequests == null && (request = this.mLowResImageRequest) != null) {
            this.mImageRequest = request;
            this.mLowResImageRequest = null;
        }
        return buildController();
    }

    protected Supplier<DataSource<IMAGE>> getDataSourceSupplierForRequest(final DraweeController controller, final String controllerId, final REQUEST imageRequest, final CacheLevel cacheLevel) {
        final Object callerContext = getCallerContext();
        return new Supplier<DataSource<IMAGE>>() { // from class: com.facebook.drawee.controller.AbstractDraweeControllerBuilder.2
            public String toString() {
                return Objects.toStringHelper(this).add("request", imageRequest.toString()).toString();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.facebook.common.internal.Supplier
            /* renamed from: get */
            public DataSource<IMAGE> mo6895get() {
                return AbstractDraweeControllerBuilder.this.getDataSourceForRequest(controller, controllerId, imageRequest, callerContext, cacheLevel);
            }
        };
    }

    @Override // com.facebook.drawee.interfaces.SimpleDraweeControllerBuilder
    /* renamed from: setCallerContext */
    public BUILDER mo6880setCallerContext(Object callerContext) {
        this.mCallerContext = callerContext;
        return getThis();
    }

    public BUILDER setFirstAvailableImageRequests(REQUEST[] firstAvailableImageRequests, boolean tryCacheOnlyFirst) {
        Preconditions.checkArgument(firstAvailableImageRequests == null || firstAvailableImageRequests.length > 0, "No requests specified!");
        this.mMultiImageRequests = firstAvailableImageRequests;
        this.mTryCacheOnlyFirst = tryCacheOnlyFirst;
        return getThis();
    }

    @Override // com.facebook.drawee.interfaces.SimpleDraweeControllerBuilder
    /* renamed from: setOldController */
    public BUILDER mo6881setOldController(@Nullable DraweeController oldController) {
        this.mOldController = oldController;
        return getThis();
    }
}
