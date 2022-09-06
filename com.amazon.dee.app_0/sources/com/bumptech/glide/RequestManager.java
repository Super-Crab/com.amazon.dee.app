package com.bumptech.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.manager.ConnectivityMonitor;
import com.bumptech.glide.manager.ConnectivityMonitorFactory;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.LifecycleListener;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import com.bumptech.glide.manager.RequestTracker;
import com.bumptech.glide.manager.TargetTracker;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.net.URL;
/* loaded from: classes.dex */
public class RequestManager implements LifecycleListener, ModelTypes<RequestBuilder<Drawable>> {
    private static final RequestOptions DECODE_TYPE_BITMAP = RequestOptions.decodeTypeOf(Bitmap.class).mo1591lock();
    private static final RequestOptions DECODE_TYPE_GIF = RequestOptions.decodeTypeOf(GifDrawable.class).mo1591lock();
    private static final RequestOptions DOWNLOAD_ONLY_OPTIONS = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA).mo1603priority(Priority.LOW).mo1607skipMemoryCache(true);
    private final Runnable addSelfToLifecycle;
    private final ConnectivityMonitor connectivityMonitor;
    protected final Context context;
    protected final Glide glide;
    final Lifecycle lifecycle;
    private final Handler mainHandler;
    private RequestOptions requestOptions;
    private final RequestTracker requestTracker;
    private final TargetTracker targetTracker;
    private final RequestManagerTreeNode treeNode;

    /* loaded from: classes.dex */
    private static class ClearTarget extends ViewTarget<View, Object> {
        ClearTarget(@NonNull View view) {
            super(view);
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onResourceReady(@NonNull Object obj, @Nullable Transition<? super Object> transition) {
        }
    }

    /* loaded from: classes.dex */
    private static class RequestManagerConnectivityListener implements ConnectivityMonitor.ConnectivityListener {
        private final RequestTracker requestTracker;

        RequestManagerConnectivityListener(@NonNull RequestTracker requestTracker) {
            this.requestTracker = requestTracker;
        }

        @Override // com.bumptech.glide.manager.ConnectivityMonitor.ConnectivityListener
        public void onConnectivityChanged(boolean z) {
            if (z) {
                this.requestTracker.restartRequests();
            }
        }
    }

    public RequestManager(@NonNull Glide glide, @NonNull Lifecycle lifecycle, @NonNull RequestManagerTreeNode requestManagerTreeNode, @NonNull Context context) {
        this(glide, lifecycle, requestManagerTreeNode, new RequestTracker(), glide.getConnectivityMonitorFactory(), context);
    }

    private void untrackOrDelegate(@NonNull Target<?> target) {
        if (untrack(target) || this.glide.removeFromManagers(target) || target.getRequest() == null) {
            return;
        }
        Request request = target.getRequest();
        target.setRequest(null);
        request.clear();
    }

    private void updateRequestOptions(@NonNull RequestOptions requestOptions) {
        this.requestOptions = this.requestOptions.mo1570apply(requestOptions);
    }

    @NonNull
    /* renamed from: applyDefaultRequestOptions */
    public RequestManager mo1633applyDefaultRequestOptions(@NonNull RequestOptions requestOptions) {
        updateRequestOptions(requestOptions);
        return this;
    }

    @NonNull
    @CheckResult
    /* renamed from: as */
    public <ResourceType> RequestBuilder<ResourceType> mo1634as(@NonNull Class<ResourceType> cls) {
        return new RequestBuilder<>(this.glide, this, cls, this.context);
    }

    @NonNull
    @CheckResult
    /* renamed from: asBitmap */
    public RequestBuilder<Bitmap> mo1635asBitmap() {
        return mo1634as(Bitmap.class).mo1615apply(DECODE_TYPE_BITMAP);
    }

    @NonNull
    @CheckResult
    /* renamed from: asDrawable */
    public RequestBuilder<Drawable> mo1636asDrawable() {
        return mo1634as(Drawable.class);
    }

    @NonNull
    @CheckResult
    /* renamed from: asFile */
    public RequestBuilder<File> mo1637asFile() {
        return mo1634as(File.class).mo1615apply(RequestOptions.skipMemoryCacheOf(true));
    }

    @NonNull
    @CheckResult
    /* renamed from: asGif */
    public RequestBuilder<GifDrawable> mo1638asGif() {
        return mo1634as(GifDrawable.class).mo1615apply(DECODE_TYPE_GIF);
    }

    public void clear(@NonNull View view) {
        clear(new ClearTarget(view));
    }

    @NonNull
    @CheckResult
    /* renamed from: download */
    public RequestBuilder<File> mo1639download(@Nullable Object obj) {
        return mo1640downloadOnly().mo6761load(obj);
    }

    @NonNull
    @CheckResult
    /* renamed from: downloadOnly */
    public RequestBuilder<File> mo1640downloadOnly() {
        return mo1634as(File.class).mo1615apply(DOWNLOAD_ONLY_OPTIONS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RequestOptions getDefaultRequestOptions() {
        return this.requestOptions;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public <T> TransitionOptions<?, T> getDefaultTransitionOptions(Class<T> cls) {
        return this.glide.getGlideContext().getDefaultTransitionOptions(cls);
    }

    public boolean isPaused() {
        Util.assertMainThread();
        return this.requestTracker.isPaused();
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onDestroy() {
        this.targetTracker.onDestroy();
        for (Target<?> target : this.targetTracker.getAll()) {
            clear(target);
        }
        this.targetTracker.clear();
        this.requestTracker.clearRequests();
        this.lifecycle.removeListener(this);
        this.lifecycle.removeListener(this.connectivityMonitor);
        this.mainHandler.removeCallbacks(this.addSelfToLifecycle);
        this.glide.unregisterRequestManager(this);
    }

    @Deprecated
    public void onLowMemory() {
        this.glide.onLowMemory();
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onStart() {
        resumeRequests();
        this.targetTracker.onStart();
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onStop() {
        pauseRequests();
        this.targetTracker.onStop();
    }

    @Deprecated
    public void onTrimMemory(int i) {
        this.glide.onTrimMemory(i);
    }

    public void pauseAllRequests() {
        Util.assertMainThread();
        this.requestTracker.pauseAllRequests();
    }

    public void pauseRequests() {
        Util.assertMainThread();
        this.requestTracker.pauseRequests();
    }

    public void pauseRequestsRecursive() {
        Util.assertMainThread();
        pauseRequests();
        for (RequestManager requestManager : this.treeNode.getDescendants()) {
            requestManager.pauseRequests();
        }
    }

    public void resumeRequests() {
        Util.assertMainThread();
        this.requestTracker.resumeRequests();
    }

    public void resumeRequestsRecursive() {
        Util.assertMainThread();
        resumeRequests();
        for (RequestManager requestManager : this.treeNode.getDescendants()) {
            requestManager.resumeRequests();
        }
    }

    @NonNull
    /* renamed from: setDefaultRequestOptions */
    public RequestManager mo1650setDefaultRequestOptions(@NonNull RequestOptions requestOptions) {
        setRequestOptions(requestOptions);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setRequestOptions(@NonNull RequestOptions requestOptions) {
        this.requestOptions = requestOptions.clone().mo1571autoClone();
    }

    public String toString() {
        return super.toString() + "{tracker=" + this.requestTracker + ", treeNode=" + this.treeNode + EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void track(@NonNull Target<?> target, @NonNull Request request) {
        this.targetTracker.track(target);
        this.requestTracker.runRequest(request);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean untrack(@NonNull Target<?> target) {
        Request request = target.getRequest();
        if (request == null) {
            return true;
        }
        if (!this.requestTracker.clearRemoveAndRecycle(request)) {
            return false;
        }
        this.targetTracker.untrack(target);
        target.setRequest(null);
        return true;
    }

    public void clear(@Nullable final Target<?> target) {
        if (target == null) {
            return;
        }
        if (Util.isOnMainThread()) {
            untrackOrDelegate(target);
        } else {
            this.mainHandler.post(new Runnable() { // from class: com.bumptech.glide.RequestManager.2
                @Override // java.lang.Runnable
                public void run() {
                    RequestManager.this.clear(target);
                }
            });
        }
    }

    RequestManager(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode requestManagerTreeNode, RequestTracker requestTracker, ConnectivityMonitorFactory connectivityMonitorFactory, Context context) {
        this.targetTracker = new TargetTracker();
        this.addSelfToLifecycle = new Runnable() { // from class: com.bumptech.glide.RequestManager.1
            @Override // java.lang.Runnable
            public void run() {
                RequestManager requestManager = RequestManager.this;
                requestManager.lifecycle.addListener(requestManager);
            }
        };
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.glide = glide;
        this.lifecycle = lifecycle;
        this.treeNode = requestManagerTreeNode;
        this.requestTracker = requestTracker;
        this.context = context;
        this.connectivityMonitor = connectivityMonitorFactory.build(context.getApplicationContext(), new RequestManagerConnectivityListener(requestTracker));
        if (Util.isOnBackgroundThread()) {
            this.mainHandler.post(this.addSelfToLifecycle);
        } else {
            lifecycle.addListener(this);
        }
        lifecycle.addListener(this.connectivityMonitor);
        setRequestOptions(glide.getGlideContext().getDefaultRequestOptions());
        glide.registerRequestManager(this);
    }

    @Override // com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<Drawable> mo6756load(@Nullable Bitmap bitmap) {
        return mo1636asDrawable().mo6756load(bitmap);
    }

    @Override // com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<Drawable> mo6757load(@Nullable Drawable drawable) {
        return mo1636asDrawable().mo6757load(drawable);
    }

    @Override // com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<Drawable> mo6762load(@Nullable String str) {
        return mo1636asDrawable().mo6762load(str);
    }

    @Override // com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<Drawable> mo6758load(@Nullable Uri uri) {
        return mo1636asDrawable().mo6758load(uri);
    }

    @Override // com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<Drawable> mo6759load(@Nullable File file) {
        return mo1636asDrawable().mo6759load(file);
    }

    @Override // com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<Drawable> mo6760load(@Nullable Integer num) {
        return mo1636asDrawable().mo6760load(num);
    }

    @Override // com.bumptech.glide.ModelTypes
    @CheckResult
    @Deprecated
    /* renamed from: load */
    public RequestBuilder<Drawable> mo6763load(@Nullable URL url) {
        return mo1636asDrawable().mo6763load(url);
    }

    @Override // com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<Drawable> mo6764load(@Nullable byte[] bArr) {
        return mo1636asDrawable().mo6764load(bArr);
    }

    @Override // com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<Drawable> mo6761load(@Nullable Object obj) {
        return mo1636asDrawable().mo6761load(obj);
    }
}
