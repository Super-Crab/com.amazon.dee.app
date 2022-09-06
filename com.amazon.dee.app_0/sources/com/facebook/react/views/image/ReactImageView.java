package com.facebook.react.views.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.controller.ForwardingControllerListener;
import com.facebook.drawee.drawable.AutoRotateDrawable;
import com.facebook.drawee.drawable.RoundedColorDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.GenericDraweeView;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.modules.fresco.ReactNetworkImageRequest;
import com.facebook.react.uimanager.FloatUtil;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.imagehelper.ImageSource;
import com.facebook.react.views.imagehelper.MultiSourceHelper;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import com.facebook.yoga.YogaConstants;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
/* loaded from: classes2.dex */
public class ReactImageView extends GenericDraweeView {
    public static final int REMOTE_IMAGE_FADE_DURATION_MS = 300;
    public static final String REMOTE_TRANSPARENT_BITMAP_URI = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNkYAAAAAYAAjCB0C8AAAAASUVORK5CYII=";
    private int mBackgroundColor;
    @Nullable
    private RoundedColorDrawable mBackgroundImageDrawable;
    private int mBorderColor;
    @Nullable
    private float[] mBorderCornerRadii;
    private float mBorderRadius;
    private float mBorderWidth;
    @Nullable
    private ImageSource mCachedImageSource;
    @Nullable
    private Object mCallerContext;
    @Nullable
    private ControllerListener mControllerForTesting;
    @Nullable
    private Drawable mDefaultImageDrawable;
    @Nullable
    private ReactImageDownloadListener mDownloadListener;
    private final AbstractDraweeControllerBuilder mDraweeControllerBuilder;
    private int mFadeDurationMs;
    @Nullable
    private GlobalImageLoadListener mGlobalImageLoadListener;
    private ReadableMap mHeaders;
    @Nullable
    private ImageSource mImageSource;
    private boolean mIsDirty;
    @Nullable
    private IterativeBoxBlurPostProcessor mIterativeBoxBlurPostProcessor;
    @Nullable
    private Drawable mLoadingImageDrawable;
    private int mOverlayColor;
    private boolean mProgressiveRenderingEnabled;
    private ImageResizeMethod mResizeMethod;
    private final RoundedCornerPostprocessor mRoundedCornerPostprocessor;
    private ScalingUtils.ScaleType mScaleType;
    private final List<ImageSource> mSources;
    private Shader.TileMode mTileMode;
    private final TilePostprocessor mTilePostprocessor;
    private static float[] sComputedCornerRadii = new float[4];
    private static final Matrix sMatrix = new Matrix();
    private static final Matrix sInverse = new Matrix();
    private static final Matrix sTileMatrix = new Matrix();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class RoundedCornerPostprocessor extends BasePostprocessor {
        private RoundedCornerPostprocessor() {
        }

        void getRadii(Bitmap bitmap, float[] fArr, float[] fArr2) {
            ReactImageView.this.mScaleType.getTransform(ReactImageView.sMatrix, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), bitmap.getWidth(), bitmap.getHeight(), 0.0f, 0.0f);
            ReactImageView.sMatrix.invert(ReactImageView.sInverse);
            fArr2[0] = ReactImageView.sInverse.mapRadius(fArr[0]);
            fArr2[1] = fArr2[0];
            fArr2[2] = ReactImageView.sInverse.mapRadius(fArr[1]);
            fArr2[3] = fArr2[2];
            fArr2[4] = ReactImageView.sInverse.mapRadius(fArr[2]);
            fArr2[5] = fArr2[4];
            fArr2[6] = ReactImageView.sInverse.mapRadius(fArr[3]);
            fArr2[7] = fArr2[6];
        }

        @Override // com.facebook.imagepipeline.request.BasePostprocessor
        public void process(Bitmap bitmap, Bitmap bitmap2) {
            ReactImageView.this.cornerRadii(ReactImageView.sComputedCornerRadii);
            bitmap.setHasAlpha(true);
            if (FloatUtil.floatsEqual(ReactImageView.sComputedCornerRadii[0], 0.0f) && FloatUtil.floatsEqual(ReactImageView.sComputedCornerRadii[1], 0.0f) && FloatUtil.floatsEqual(ReactImageView.sComputedCornerRadii[2], 0.0f) && FloatUtil.floatsEqual(ReactImageView.sComputedCornerRadii[3], 0.0f)) {
                super.process(bitmap, bitmap2);
                return;
            }
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            paint.setShader(new BitmapShader(bitmap2, tileMode, tileMode));
            Canvas canvas = new Canvas(bitmap);
            float[] fArr = new float[8];
            getRadii(bitmap2, ReactImageView.sComputedCornerRadii, fArr);
            Path path = new Path();
            path.addRoundRect(new RectF(0.0f, 0.0f, bitmap2.getWidth(), bitmap2.getHeight()), fArr, Path.Direction.CW);
            canvas.drawPath(path, paint);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class TilePostprocessor extends BasePostprocessor {
        private TilePostprocessor() {
        }

        @Override // com.facebook.imagepipeline.request.BasePostprocessor, com.facebook.imagepipeline.request.Postprocessor
        public CloseableReference<Bitmap> process(Bitmap bitmap, PlatformBitmapFactory platformBitmapFactory) {
            Rect rect = new Rect(0, 0, ReactImageView.this.getWidth(), ReactImageView.this.getHeight());
            ReactImageView.this.mScaleType.getTransform(ReactImageView.sTileMatrix, rect, bitmap.getWidth(), bitmap.getHeight(), 0.0f, 0.0f);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            BitmapShader bitmapShader = new BitmapShader(bitmap, ReactImageView.this.mTileMode, ReactImageView.this.mTileMode);
            bitmapShader.setLocalMatrix(ReactImageView.sTileMatrix);
            paint.setShader(bitmapShader);
            CloseableReference<Bitmap> createBitmap = platformBitmapFactory.createBitmap(ReactImageView.this.getWidth(), ReactImageView.this.getHeight());
            try {
                new Canvas(createBitmap.get()).drawRect(rect, paint);
                return createBitmap.m6865clone();
            } finally {
                CloseableReference.closeSafely(createBitmap);
            }
        }
    }

    public ReactImageView(Context context, AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, @Nullable GlobalImageLoadListener globalImageLoadListener, @Nullable Object obj) {
        super(context, buildHierarchy(context));
        this.mResizeMethod = ImageResizeMethod.AUTO;
        this.mBackgroundColor = 0;
        this.mBorderRadius = Float.NaN;
        this.mTileMode = ImageResizeMode.defaultTileMode();
        this.mFadeDurationMs = -1;
        this.mScaleType = ImageResizeMode.defaultValue();
        this.mDraweeControllerBuilder = abstractDraweeControllerBuilder;
        this.mRoundedCornerPostprocessor = new RoundedCornerPostprocessor();
        this.mTilePostprocessor = new TilePostprocessor();
        this.mGlobalImageLoadListener = globalImageLoadListener;
        this.mCallerContext = obj;
        this.mSources = new LinkedList();
    }

    private static GenericDraweeHierarchy buildHierarchy(Context context) {
        return new GenericDraweeHierarchyBuilder(context.getResources()).setRoundingParams(RoundingParams.fromCornersRadius(0.0f)).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cornerRadii(float[] fArr) {
        float f = !YogaConstants.isUndefined(this.mBorderRadius) ? this.mBorderRadius : 0.0f;
        float[] fArr2 = this.mBorderCornerRadii;
        fArr[0] = (fArr2 == null || YogaConstants.isUndefined(fArr2[0])) ? f : this.mBorderCornerRadii[0];
        float[] fArr3 = this.mBorderCornerRadii;
        fArr[1] = (fArr3 == null || YogaConstants.isUndefined(fArr3[1])) ? f : this.mBorderCornerRadii[1];
        float[] fArr4 = this.mBorderCornerRadii;
        fArr[2] = (fArr4 == null || YogaConstants.isUndefined(fArr4[2])) ? f : this.mBorderCornerRadii[2];
        float[] fArr5 = this.mBorderCornerRadii;
        if (fArr5 != null && !YogaConstants.isUndefined(fArr5[3])) {
            f = this.mBorderCornerRadii[3];
        }
        fArr[3] = f;
    }

    private boolean hasMultipleSources() {
        return this.mSources.size() > 1;
    }

    private boolean isTiled() {
        return this.mTileMode != Shader.TileMode.CLAMP;
    }

    private void setSourceImage() {
        this.mImageSource = null;
        if (this.mSources.isEmpty()) {
            this.mSources.add(new ImageSource(getContext(), REMOTE_TRANSPARENT_BITMAP_URI));
        } else if (hasMultipleSources()) {
            MultiSourceHelper.MultiSourceResult bestSourceForSize = MultiSourceHelper.getBestSourceForSize(getWidth(), getHeight(), this.mSources);
            this.mImageSource = bestSourceForSize.getBestResult();
            this.mCachedImageSource = bestSourceForSize.getBestResultInCache();
            return;
        }
        this.mImageSource = this.mSources.get(0);
    }

    private boolean shouldResize(ImageSource imageSource) {
        ImageResizeMethod imageResizeMethod = this.mResizeMethod;
        return imageResizeMethod == ImageResizeMethod.AUTO ? UriUtil.isLocalContentUri(imageSource.getUri()) || UriUtil.isLocalFileUri(imageSource.getUri()) : imageResizeMethod == ImageResizeMethod.RESIZE;
    }

    private void warnImageSource(String str) {
    }

    @Override // android.widget.ImageView, android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public void maybeUpdateView() {
        if (!this.mIsDirty) {
            return;
        }
        if (hasMultipleSources() && (getWidth() <= 0 || getHeight() <= 0)) {
            return;
        }
        setSourceImage();
        ImageSource imageSource = this.mImageSource;
        if (imageSource == null) {
            return;
        }
        boolean shouldResize = shouldResize(imageSource);
        if (shouldResize && (getWidth() <= 0 || getHeight() <= 0)) {
            return;
        }
        if (isTiled() && (getWidth() <= 0 || getHeight() <= 0)) {
            return;
        }
        GenericDraweeHierarchy hierarchy = getHierarchy();
        hierarchy.setActualImageScaleType(this.mScaleType);
        Drawable drawable = this.mDefaultImageDrawable;
        if (drawable != null) {
            hierarchy.setPlaceholderImage(drawable, this.mScaleType);
        }
        Drawable drawable2 = this.mLoadingImageDrawable;
        if (drawable2 != null) {
            hierarchy.setPlaceholderImage(drawable2, ScalingUtils.ScaleType.CENTER);
        }
        ScalingUtils.ScaleType scaleType = this.mScaleType;
        boolean z = (scaleType == ScalingUtils.ScaleType.CENTER_CROP || scaleType == ScalingUtils.ScaleType.FOCUS_CROP) ? false : true;
        RoundingParams roundingParams = hierarchy.getRoundingParams();
        cornerRadii(sComputedCornerRadii);
        float[] fArr = sComputedCornerRadii;
        roundingParams.setCornersRadii(fArr[0], fArr[1], fArr[2], fArr[3]);
        RoundedColorDrawable roundedColorDrawable = this.mBackgroundImageDrawable;
        if (roundedColorDrawable != null) {
            roundedColorDrawable.setBorder(this.mBorderColor, this.mBorderWidth);
            this.mBackgroundImageDrawable.setRadii(roundingParams.getCornersRadii());
            hierarchy.setBackgroundImage(this.mBackgroundImageDrawable);
        }
        if (z) {
            roundingParams.setCornersRadius(0.0f);
        }
        roundingParams.setBorder(this.mBorderColor, this.mBorderWidth);
        int i = this.mOverlayColor;
        if (i != 0) {
            roundingParams.setOverlayColor(i);
        } else {
            roundingParams.setRoundingMethod(RoundingParams.RoundingMethod.BITMAP_ONLY);
        }
        hierarchy.setRoundingParams(roundingParams);
        int i2 = this.mFadeDurationMs;
        if (i2 < 0) {
            i2 = this.mImageSource.isResource() ? 0 : 300;
        }
        hierarchy.setFadeDuration(i2);
        LinkedList linkedList = new LinkedList();
        if (z) {
            linkedList.add(this.mRoundedCornerPostprocessor);
        }
        IterativeBoxBlurPostProcessor iterativeBoxBlurPostProcessor = this.mIterativeBoxBlurPostProcessor;
        if (iterativeBoxBlurPostProcessor != null) {
            linkedList.add(iterativeBoxBlurPostProcessor);
        }
        if (isTiled()) {
            linkedList.add(this.mTilePostprocessor);
        }
        Postprocessor from = MultiPostprocessor.from(linkedList);
        ResizeOptions resizeOptions = shouldResize ? new ResizeOptions(getWidth(), getHeight()) : null;
        ReactNetworkImageRequest fromBuilderWithHeaders = ReactNetworkImageRequest.fromBuilderWithHeaders(ImageRequestBuilder.newBuilderWithSource(this.mImageSource.getUri()).setPostprocessor(from).setResizeOptions(resizeOptions).setAutoRotateEnabled(true).setProgressiveRenderingEnabled(this.mProgressiveRenderingEnabled), this.mHeaders);
        GlobalImageLoadListener globalImageLoadListener = this.mGlobalImageLoadListener;
        if (globalImageLoadListener != null) {
            globalImageLoadListener.onLoadAttempt(this.mImageSource.getUri());
        }
        this.mDraweeControllerBuilder.reset();
        this.mDraweeControllerBuilder.setAutoPlayAnimations(true).mo6880setCallerContext(this.mCallerContext).mo6881setOldController(getController()).setImageRequest(fromBuilderWithHeaders);
        ImageSource imageSource2 = this.mCachedImageSource;
        if (imageSource2 != null) {
            this.mDraweeControllerBuilder.setLowResImageRequest(ImageRequestBuilder.newBuilderWithSource(imageSource2.getUri()).setPostprocessor(from).setResizeOptions(resizeOptions).setAutoRotateEnabled(true).setProgressiveRenderingEnabled(this.mProgressiveRenderingEnabled).build());
        }
        if (this.mDownloadListener != null && this.mControllerForTesting != null) {
            ForwardingControllerListener forwardingControllerListener = new ForwardingControllerListener();
            forwardingControllerListener.addListener(this.mDownloadListener);
            forwardingControllerListener.addListener(this.mControllerForTesting);
            this.mDraweeControllerBuilder.setControllerListener(forwardingControllerListener);
        } else {
            ControllerListener controllerListener = this.mControllerForTesting;
            if (controllerListener != null) {
                this.mDraweeControllerBuilder.setControllerListener(controllerListener);
            } else {
                ReactImageDownloadListener reactImageDownloadListener = this.mDownloadListener;
                if (reactImageDownloadListener != null) {
                    this.mDraweeControllerBuilder.setControllerListener(reactImageDownloadListener);
                }
            }
        }
        ReactImageDownloadListener reactImageDownloadListener2 = this.mDownloadListener;
        if (reactImageDownloadListener2 != null) {
            hierarchy.setProgressBarImage(reactImageDownloadListener2);
        }
        setController(this.mDraweeControllerBuilder.mo6879build());
        this.mIsDirty = false;
        this.mDraweeControllerBuilder.reset();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i <= 0 || i2 <= 0) {
            return;
        }
        this.mIsDirty = this.mIsDirty || hasMultipleSources() || isTiled();
        maybeUpdateView();
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        if (this.mBackgroundColor != i) {
            this.mBackgroundColor = i;
            this.mBackgroundImageDrawable = new RoundedColorDrawable(i);
            this.mIsDirty = true;
        }
    }

    public void setBlurRadius(float f) {
        int pixelFromDIP = ((int) PixelUtil.toPixelFromDIP(f)) / 2;
        if (pixelFromDIP == 0) {
            this.mIterativeBoxBlurPostProcessor = null;
        } else {
            this.mIterativeBoxBlurPostProcessor = new IterativeBoxBlurPostProcessor(2, pixelFromDIP);
        }
        this.mIsDirty = true;
    }

    public void setBorderColor(int i) {
        this.mBorderColor = i;
        this.mIsDirty = true;
    }

    public void setBorderRadius(float f) {
        if (!FloatUtil.floatsEqual(this.mBorderRadius, f)) {
            this.mBorderRadius = f;
            this.mIsDirty = true;
        }
    }

    public void setBorderWidth(float f) {
        this.mBorderWidth = PixelUtil.toPixelFromDIP(f);
        this.mIsDirty = true;
    }

    public void setControllerListener(ControllerListener controllerListener) {
        this.mControllerForTesting = controllerListener;
        this.mIsDirty = true;
        maybeUpdateView();
    }

    public void setDefaultSource(@Nullable String str) {
        this.mDefaultImageDrawable = ResourceDrawableIdHelper.getInstance().getResourceDrawable(getContext(), str);
        this.mIsDirty = true;
    }

    public void setFadeDuration(int i) {
        this.mFadeDurationMs = i;
    }

    public void setHeaders(ReadableMap readableMap) {
        this.mHeaders = readableMap;
    }

    public void setLoadingIndicatorSource(@Nullable String str) {
        Drawable resourceDrawable = ResourceDrawableIdHelper.getInstance().getResourceDrawable(getContext(), str);
        this.mLoadingImageDrawable = resourceDrawable != null ? new AutoRotateDrawable(resourceDrawable, 1000) : null;
        this.mIsDirty = true;
    }

    public void setOverlayColor(int i) {
        this.mOverlayColor = i;
        this.mIsDirty = true;
    }

    public void setProgressiveRenderingEnabled(boolean z) {
        this.mProgressiveRenderingEnabled = z;
    }

    public void setResizeMethod(ImageResizeMethod imageResizeMethod) {
        this.mResizeMethod = imageResizeMethod;
        this.mIsDirty = true;
    }

    public void setScaleType(ScalingUtils.ScaleType scaleType) {
        this.mScaleType = scaleType;
        this.mIsDirty = true;
    }

    public void setShouldNotifyLoadEvents(boolean z) {
        if (!z) {
            this.mDownloadListener = null;
        } else {
            final EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag((ReactContext) getContext(), getId());
            this.mDownloadListener = new ReactImageDownloadListener<ImageInfo>() { // from class: com.facebook.react.views.image.ReactImageView.1
                @Override // com.facebook.react.views.image.ReactImageDownloadListener, com.facebook.drawee.controller.ControllerListener
                public void onFailure(String str, Throwable th) {
                    eventDispatcherForReactTag.dispatchEvent(ImageLoadEvent.createErrorEvent(ReactImageView.this.getId(), th));
                }

                @Override // com.facebook.react.views.image.ReactImageDownloadListener
                public void onProgressChange(int i, int i2) {
                    eventDispatcherForReactTag.dispatchEvent(ImageLoadEvent.createProgressEvent(ReactImageView.this.getId(), ReactImageView.this.mImageSource.getSource(), i, i2));
                }

                @Override // com.facebook.react.views.image.ReactImageDownloadListener, com.facebook.drawee.controller.ControllerListener
                public void onSubmit(String str, Object obj) {
                    eventDispatcherForReactTag.dispatchEvent(ImageLoadEvent.createLoadStartEvent(ReactImageView.this.getId()));
                }

                @Override // com.facebook.react.views.image.ReactImageDownloadListener, com.facebook.drawee.controller.ControllerListener
                public void onFinalImageSet(String str, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
                    if (imageInfo != null) {
                        eventDispatcherForReactTag.dispatchEvent(ImageLoadEvent.createLoadEvent(ReactImageView.this.getId(), ReactImageView.this.mImageSource.getSource(), imageInfo.getWidth(), imageInfo.getHeight()));
                        eventDispatcherForReactTag.dispatchEvent(ImageLoadEvent.createLoadEndEvent(ReactImageView.this.getId()));
                    }
                }
            };
        }
        this.mIsDirty = true;
    }

    public void setSource(@Nullable ReadableArray readableArray) {
        this.mSources.clear();
        if (readableArray != null && readableArray.size() != 0) {
            if (readableArray.size() == 1) {
                String string = readableArray.mo6944getMap(0).getString("uri");
                ImageSource imageSource = new ImageSource(getContext(), string);
                this.mSources.add(imageSource);
                if (Uri.EMPTY.equals(imageSource.getUri())) {
                    warnImageSource(string);
                }
            } else {
                for (int i = 0; i < readableArray.size(); i++) {
                    ReadableMap mo6944getMap = readableArray.mo6944getMap(i);
                    String string2 = mo6944getMap.getString("uri");
                    ImageSource imageSource2 = new ImageSource(getContext(), string2, mo6944getMap.getDouble("width"), mo6944getMap.getDouble("height"));
                    this.mSources.add(imageSource2);
                    if (Uri.EMPTY.equals(imageSource2.getUri())) {
                        warnImageSource(string2);
                    }
                }
            }
        } else {
            this.mSources.add(new ImageSource(getContext(), REMOTE_TRANSPARENT_BITMAP_URI));
        }
        this.mIsDirty = true;
    }

    public void setTileMode(Shader.TileMode tileMode) {
        this.mTileMode = tileMode;
        this.mIsDirty = true;
    }

    public void updateCallerContext(@Nullable Object obj) {
        this.mCallerContext = obj;
        this.mIsDirty = true;
    }

    public void setBorderRadius(float f, int i) {
        if (this.mBorderCornerRadii == null) {
            this.mBorderCornerRadii = new float[4];
            Arrays.fill(this.mBorderCornerRadii, Float.NaN);
        }
        if (!FloatUtil.floatsEqual(this.mBorderCornerRadii[i], f)) {
            this.mBorderCornerRadii[i] = f;
            this.mIsDirty = true;
        }
    }
}
