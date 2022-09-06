package com.facebook.react.modules.image;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.fbreact.specs.NativeImageLoaderAndroidSpec;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.fresco.ReactNetworkImageRequest;
import com.facebook.react.views.image.ReactCallerContextFactory;
import com.facebook.react.views.imagehelper.ImageSource;
@ReactModule(name = ImageLoaderModule.NAME)
/* loaded from: classes2.dex */
public class ImageLoaderModule extends NativeImageLoaderAndroidSpec implements LifecycleEventListener {
    private static final String ERROR_GET_SIZE_FAILURE = "E_GET_SIZE_FAILURE";
    private static final String ERROR_INVALID_URI = "E_INVALID_URI";
    private static final String ERROR_PREFETCH_FAILURE = "E_PREFETCH_FAILURE";
    public static final String NAME = "ImageLoader";
    @Nullable
    private final Object mCallerContext;
    @Nullable
    private ReactCallerContextFactory mCallerContextFactory;
    private final Object mEnqueuedRequestMonitor;
    private final SparseArray<DataSource<Void>> mEnqueuedRequests;
    @Nullable
    private ImagePipeline mImagePipeline;

    public ImageLoaderModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mEnqueuedRequestMonitor = new Object();
        this.mEnqueuedRequests = new SparseArray<>();
        this.mImagePipeline = null;
        this.mCallerContext = this;
    }

    @Nullable
    private Object getCallerContext() {
        ReactCallerContextFactory reactCallerContextFactory = this.mCallerContextFactory;
        return reactCallerContextFactory != null ? reactCallerContextFactory.getOrCreateCallerContext("", "") : this.mCallerContext;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ImagePipeline getImagePipeline() {
        ImagePipeline imagePipeline = this.mImagePipeline;
        return imagePipeline != null ? imagePipeline : Fresco.getImagePipeline();
    }

    private void registerRequest(int i, DataSource<Void> dataSource) {
        synchronized (this.mEnqueuedRequestMonitor) {
            this.mEnqueuedRequests.put(i, dataSource);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public DataSource<Void> removeRequest(int i) {
        DataSource<Void> dataSource;
        synchronized (this.mEnqueuedRequestMonitor) {
            dataSource = this.mEnqueuedRequests.get(i);
            this.mEnqueuedRequests.remove(i);
        }
        return dataSource;
    }

    @Override // com.facebook.fbreact.specs.NativeImageLoaderAndroidSpec
    public void abortRequest(double d) {
        DataSource<Void> removeRequest = removeRequest((int) d);
        if (removeRequest != null) {
            removeRequest.close();
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    @NonNull
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.fbreact.specs.NativeImageLoaderAndroidSpec
    @ReactMethod
    public void getSize(String str, final Promise promise) {
        if (str != null && !str.isEmpty()) {
            getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(new ImageSource(getReactApplicationContext(), str).getUri()).build(), getCallerContext()).subscribe(new BaseDataSubscriber<CloseableReference<CloseableImage>>() { // from class: com.facebook.react.modules.image.ImageLoaderModule.1
                @Override // com.facebook.datasource.BaseDataSubscriber
                protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                    promise.reject(ImageLoaderModule.ERROR_GET_SIZE_FAILURE, dataSource.getFailureCause());
                }

                @Override // com.facebook.datasource.BaseDataSubscriber
                protected void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                    if (!dataSource.isFinished()) {
                        return;
                    }
                    CloseableReference<CloseableImage> mo6898getResult = dataSource.mo6898getResult();
                    try {
                        if (mo6898getResult != null) {
                            try {
                                CloseableImage closeableImage = mo6898getResult.get();
                                WritableMap createMap = Arguments.createMap();
                                createMap.putInt("width", closeableImage.getWidth());
                                createMap.putInt("height", closeableImage.getHeight());
                                promise.resolve(createMap);
                            } catch (Exception e) {
                                promise.reject(ImageLoaderModule.ERROR_GET_SIZE_FAILURE, e);
                            }
                            return;
                        }
                        promise.reject(ImageLoaderModule.ERROR_GET_SIZE_FAILURE);
                    } finally {
                        CloseableReference.closeSafely(mo6898getResult);
                    }
                }
            }, CallerThreadExecutor.getInstance());
            return;
        }
        promise.reject(ERROR_INVALID_URI, "Cannot get the size of an image for an empty URI");
    }

    @Override // com.facebook.fbreact.specs.NativeImageLoaderAndroidSpec
    @ReactMethod
    public void getSizeWithHeaders(String str, ReadableMap readableMap, final Promise promise) {
        if (str != null && !str.isEmpty()) {
            getImagePipeline().fetchDecodedImage(ReactNetworkImageRequest.fromBuilderWithHeaders(ImageRequestBuilder.newBuilderWithSource(new ImageSource(getReactApplicationContext(), str).getUri()), readableMap), getCallerContext()).subscribe(new BaseDataSubscriber<CloseableReference<CloseableImage>>() { // from class: com.facebook.react.modules.image.ImageLoaderModule.2
                @Override // com.facebook.datasource.BaseDataSubscriber
                protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                    promise.reject(ImageLoaderModule.ERROR_GET_SIZE_FAILURE, dataSource.getFailureCause());
                }

                @Override // com.facebook.datasource.BaseDataSubscriber
                protected void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                    if (!dataSource.isFinished()) {
                        return;
                    }
                    CloseableReference<CloseableImage> mo6898getResult = dataSource.mo6898getResult();
                    try {
                        if (mo6898getResult != null) {
                            try {
                                CloseableImage closeableImage = mo6898getResult.get();
                                WritableMap createMap = Arguments.createMap();
                                createMap.putInt("width", closeableImage.getWidth());
                                createMap.putInt("height", closeableImage.getHeight());
                                promise.resolve(createMap);
                            } catch (Exception e) {
                                promise.reject(ImageLoaderModule.ERROR_GET_SIZE_FAILURE, e);
                            }
                            return;
                        }
                        promise.reject(ImageLoaderModule.ERROR_GET_SIZE_FAILURE);
                    } finally {
                        CloseableReference.closeSafely(mo6898getResult);
                    }
                }
            }, CallerThreadExecutor.getInstance());
            return;
        }
        promise.reject(ERROR_INVALID_URI, "Cannot get the size of an image for an empty URI");
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        synchronized (this.mEnqueuedRequestMonitor) {
            int size = this.mEnqueuedRequests.size();
            for (int i = 0; i < size; i++) {
                DataSource<Void> valueAt = this.mEnqueuedRequests.valueAt(i);
                if (valueAt != null) {
                    valueAt.close();
                }
            }
            this.mEnqueuedRequests.clear();
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
    }

    @Override // com.facebook.fbreact.specs.NativeImageLoaderAndroidSpec
    public void prefetchImage(String str, double d, final Promise promise) {
        final int i = (int) d;
        if (str != null && !str.isEmpty()) {
            DataSource<Void> prefetchToDiskCache = getImagePipeline().prefetchToDiskCache(ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).build(), getCallerContext());
            BaseDataSubscriber<Void> baseDataSubscriber = new BaseDataSubscriber<Void>() { // from class: com.facebook.react.modules.image.ImageLoaderModule.3
                @Override // com.facebook.datasource.BaseDataSubscriber
                protected void onFailureImpl(DataSource<Void> dataSource) {
                    try {
                        ImageLoaderModule.this.removeRequest(i);
                        promise.reject(ImageLoaderModule.ERROR_PREFETCH_FAILURE, dataSource.getFailureCause());
                    } finally {
                        dataSource.close();
                    }
                }

                @Override // com.facebook.datasource.BaseDataSubscriber
                protected void onNewResultImpl(DataSource<Void> dataSource) {
                    if (!dataSource.isFinished()) {
                        return;
                    }
                    try {
                        try {
                            ImageLoaderModule.this.removeRequest(i);
                            promise.resolve(true);
                        } catch (Exception e) {
                            promise.reject(ImageLoaderModule.ERROR_PREFETCH_FAILURE, e);
                        }
                    } finally {
                        dataSource.close();
                    }
                }
            };
            registerRequest(i, prefetchToDiskCache);
            prefetchToDiskCache.subscribe(baseDataSubscriber, CallerThreadExecutor.getInstance());
            return;
        }
        promise.reject(ERROR_INVALID_URI, "Cannot prefetch an image for an empty URI");
    }

    @Override // com.facebook.fbreact.specs.NativeImageLoaderAndroidSpec
    @ReactMethod
    public void queryCache(final ReadableArray readableArray, final Promise promise) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.facebook.react.modules.image.ImageLoaderModule.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.react.bridge.GuardedAsyncTask
            public void doInBackgroundGuarded(Void... voidArr) {
                WritableMap createMap = Arguments.createMap();
                ImagePipeline imagePipeline = ImageLoaderModule.this.getImagePipeline();
                for (int i = 0; i < readableArray.size(); i++) {
                    String string = readableArray.getString(i);
                    Uri parse = Uri.parse(string);
                    if (imagePipeline.isInBitmapMemoryCache(parse)) {
                        createMap.putString(string, "memory");
                    } else if (imagePipeline.isInDiskCacheSync(parse)) {
                        createMap.putString(string, "disk");
                    }
                }
                promise.resolve(createMap);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public ImageLoaderModule(ReactApplicationContext reactApplicationContext, Object obj) {
        super(reactApplicationContext);
        this.mEnqueuedRequestMonitor = new Object();
        this.mEnqueuedRequests = new SparseArray<>();
        this.mImagePipeline = null;
        this.mCallerContext = obj;
    }

    public ImageLoaderModule(ReactApplicationContext reactApplicationContext, ImagePipeline imagePipeline, ReactCallerContextFactory reactCallerContextFactory) {
        super(reactApplicationContext);
        this.mEnqueuedRequestMonitor = new Object();
        this.mEnqueuedRequests = new SparseArray<>();
        this.mImagePipeline = null;
        this.mCallerContextFactory = reactCallerContextFactory;
        this.mImagePipeline = imagePipeline;
        this.mCallerContext = null;
    }
}
