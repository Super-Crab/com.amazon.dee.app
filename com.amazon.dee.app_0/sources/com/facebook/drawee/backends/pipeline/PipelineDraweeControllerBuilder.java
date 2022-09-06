package com.facebook.drawee.backends.pipeline;

import android.content.Context;
import android.net.Uri;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableList;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Suppliers;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.info.ImageOriginListener;
import com.facebook.drawee.backends.pipeline.info.ImagePerfDataListener;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.fresco.ui.common.ControllerListener2;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.Set;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class PipelineDraweeControllerBuilder extends AbstractDraweeControllerBuilder<PipelineDraweeControllerBuilder, ImageRequest, CloseableReference<CloseableImage>, ImageInfo> {
    @Nullable
    private ImmutableList<DrawableFactory> mCustomDrawableFactories;
    @Nullable
    private ImageOriginListener mImageOriginListener;
    @Nullable
    private ImagePerfDataListener mImagePerfDataListener;
    private final ImagePipeline mImagePipeline;
    private final PipelineDraweeControllerFactory mPipelineDraweeControllerFactory;

    /* renamed from: com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$drawee$controller$AbstractDraweeControllerBuilder$CacheLevel = new int[AbstractDraweeControllerBuilder.CacheLevel.values().length];

        static {
            try {
                $SwitchMap$com$facebook$drawee$controller$AbstractDraweeControllerBuilder$CacheLevel[AbstractDraweeControllerBuilder.CacheLevel.FULL_FETCH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$drawee$controller$AbstractDraweeControllerBuilder$CacheLevel[AbstractDraweeControllerBuilder.CacheLevel.DISK_CACHE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$drawee$controller$AbstractDraweeControllerBuilder$CacheLevel[AbstractDraweeControllerBuilder.CacheLevel.BITMAP_MEMORY_CACHE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public PipelineDraweeControllerBuilder(Context context, PipelineDraweeControllerFactory pipelineDraweeControllerFactory, ImagePipeline imagePipeline, Set<ControllerListener> boundControllerListeners, Set<ControllerListener2> boundControllerListeners2) {
        super(context, boundControllerListeners, boundControllerListeners2);
        this.mImagePipeline = imagePipeline;
        this.mPipelineDraweeControllerFactory = pipelineDraweeControllerFactory;
    }

    public static ImageRequest.RequestLevel convertCacheLevelToRequestLevel(AbstractDraweeControllerBuilder.CacheLevel cacheLevel) {
        int ordinal = cacheLevel.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return ImageRequest.RequestLevel.DISK_CACHE;
            }
            if (ordinal == 2) {
                return ImageRequest.RequestLevel.BITMAP_MEMORY_CACHE;
            }
            throw new RuntimeException("Cache level" + cacheLevel + "is not supported. ");
        }
        return ImageRequest.RequestLevel.FULL_FETCH;
    }

    @Nullable
    private CacheKey getCacheKey() {
        ImageRequest imageRequest = getImageRequest();
        CacheKeyFactory cacheKeyFactory = this.mImagePipeline.getCacheKeyFactory();
        if (cacheKeyFactory == null || imageRequest == null) {
            return null;
        }
        if (imageRequest.getPostprocessor() != null) {
            return cacheKeyFactory.getPostprocessedBitmapCacheKey(imageRequest, getCallerContext());
        }
        return cacheKeyFactory.getBitmapCacheKey(imageRequest, getCallerContext());
    }

    @Nullable
    protected RequestListener getRequestListener(final DraweeController controller) {
        if (controller instanceof PipelineDraweeController) {
            return ((PipelineDraweeController) controller).getRequestListener();
        }
        return null;
    }

    public PipelineDraweeControllerBuilder setCustomDrawableFactories(@Nullable ImmutableList<DrawableFactory> customDrawableFactories) {
        this.mCustomDrawableFactories = customDrawableFactories;
        return getThis();
    }

    public PipelineDraweeControllerBuilder setCustomDrawableFactory(DrawableFactory drawableFactory) {
        Preconditions.checkNotNull(drawableFactory);
        return setCustomDrawableFactories(ImmutableList.of((Object[]) new DrawableFactory[]{drawableFactory}));
    }

    public PipelineDraweeControllerBuilder setImageOriginListener(@Nullable ImageOriginListener imageOriginListener) {
        this.mImageOriginListener = imageOriginListener;
        return getThis();
    }

    public PipelineDraweeControllerBuilder setPerfDataListener(@Nullable ImagePerfDataListener imagePerfDataListener) {
        this.mImagePerfDataListener = imagePerfDataListener;
        return getThis();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.drawee.controller.AbstractDraweeControllerBuilder
    public DataSource<CloseableReference<CloseableImage>> getDataSourceForRequest(DraweeController controller, String controllerId, ImageRequest imageRequest, Object callerContext, AbstractDraweeControllerBuilder.CacheLevel cacheLevel) {
        return this.mImagePipeline.fetchDecodedImage(imageRequest, callerContext, convertCacheLevelToRequestLevel(cacheLevel), getRequestListener(controller), controllerId);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.drawee.controller.AbstractDraweeControllerBuilder
    /* renamed from: obtainController */
    public PipelineDraweeController mo6875obtainController() {
        PipelineDraweeController newController;
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("PipelineDraweeControllerBuilder#obtainController");
        }
        try {
            DraweeController oldController = getOldController();
            String generateUniqueControllerId = AbstractDraweeControllerBuilder.generateUniqueControllerId();
            if (oldController instanceof PipelineDraweeController) {
                newController = (PipelineDraweeController) oldController;
            } else {
                newController = this.mPipelineDraweeControllerFactory.newController();
            }
            newController.initialize(obtainDataSourceSupplier(newController, generateUniqueControllerId), generateUniqueControllerId, getCacheKey(), getCallerContext(), this.mCustomDrawableFactories, this.mImageOriginListener);
            newController.initializePerformanceMonitoring(this.mImagePerfDataListener, this, Suppliers.BOOLEAN_FALSE);
            return newController;
        } finally {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
        }
    }

    public PipelineDraweeControllerBuilder setCustomDrawableFactories(DrawableFactory... drawableFactories) {
        Preconditions.checkNotNull(drawableFactories);
        return setCustomDrawableFactories(ImmutableList.of((Object[]) drawableFactories));
    }

    @Override // com.facebook.drawee.interfaces.SimpleDraweeControllerBuilder
    /* renamed from: setUri */
    public PipelineDraweeControllerBuilder mo6876setUri(@Nullable Uri uri) {
        if (uri == null) {
            return (PipelineDraweeControllerBuilder) super.setImageRequest(null);
        }
        return (PipelineDraweeControllerBuilder) super.setImageRequest(ImageRequestBuilder.newBuilderWithSource(uri).setRotationOptions(RotationOptions.autoRotateAtRenderTime()).build());
    }

    @Override // com.facebook.drawee.interfaces.SimpleDraweeControllerBuilder
    /* renamed from: setUri */
    public PipelineDraweeControllerBuilder mo6877setUri(@Nullable String uriString) {
        if (uriString != null && !uriString.isEmpty()) {
            return mo6876setUri(Uri.parse(uriString));
        }
        return (PipelineDraweeControllerBuilder) super.setImageRequest(ImageRequest.fromUri(uriString));
    }
}
