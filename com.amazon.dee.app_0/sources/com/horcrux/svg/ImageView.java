package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.imagehelper.ImageSource;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@SuppressLint({"ViewConstructor"})
/* loaded from: classes3.dex */
public class ImageView extends RenderableView {
    private String mAlign;
    private SVGLength mH;
    private int mImageHeight;
    private int mImageWidth;
    private final AtomicBoolean mLoading;
    private int mMeetOrSlice;
    private SVGLength mW;
    private SVGLength mX;
    private SVGLength mY;
    private String uriString;

    public ImageView(ReactContext reactContext) {
        super(reactContext);
        this.mLoading = new AtomicBoolean(false);
    }

    private void doRender(Canvas canvas, Paint paint, Bitmap bitmap, float f) {
        if (this.mImageWidth == 0 || this.mImageHeight == 0) {
            this.mImageWidth = bitmap.getWidth();
            this.mImageHeight = bitmap.getHeight();
        }
        RectF rect = getRect();
        RectF rectF = new RectF(0.0f, 0.0f, this.mImageWidth, this.mImageHeight);
        ViewBox.getTransform(rectF, rect, this.mAlign, this.mMeetOrSlice).mapRect(rectF);
        canvas.clipPath(getPath(canvas, paint));
        Path clipPath = getClipPath(canvas, paint);
        if (clipPath != null) {
            canvas.clipPath(clipPath);
        }
        Paint paint2 = new Paint();
        paint2.setAlpha((int) (f * 255.0f));
        canvas.drawBitmap(bitmap, (Rect) null, rectF, paint2);
        this.mCTM.mapRect(rectF);
        setClientRect(rectF);
    }

    @Nonnull
    private RectF getRect() {
        double relativeOnWidth = relativeOnWidth(this.mX);
        double relativeOnHeight = relativeOnHeight(this.mY);
        double relativeOnWidth2 = relativeOnWidth(this.mW);
        double relativeOnHeight2 = relativeOnHeight(this.mH);
        if (relativeOnWidth2 == FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            relativeOnWidth2 = this.mImageWidth * this.mScale;
        }
        if (relativeOnHeight2 == FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            relativeOnHeight2 = this.mImageHeight * this.mScale;
        }
        return new RectF((float) relativeOnWidth, (float) relativeOnHeight, (float) (relativeOnWidth + relativeOnWidth2), (float) (relativeOnHeight + relativeOnHeight2));
    }

    private void loadBitmap(ImagePipeline imagePipeline, ImageRequest imageRequest) {
        this.mLoading.set(true);
        imagePipeline.fetchDecodedImage(imageRequest, this.mContext).subscribe(new BaseBitmapDataSubscriber() { // from class: com.horcrux.svg.ImageView.1
            @Override // com.facebook.datasource.BaseDataSubscriber
            public void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                ImageView.this.mLoading.set(false);
                FLog.w(ReactConstants.TAG, dataSource.getFailureCause(), "RNSVG: fetchDecodedImage failed!", new Object[0]);
            }

            @Override // com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber
            public void onNewResultImpl(Bitmap bitmap) {
                ImageView.this.mLoading.set(false);
                SvgView svgView = ImageView.this.getSvgView();
                if (svgView != null) {
                    svgView.invalidate();
                }
            }
        }, UiThreadImmediateExecutorService.getInstance());
    }

    private void tryRenderFromBitmapCache(ImagePipeline imagePipeline, ImageRequest imageRequest, Canvas canvas, Paint paint, float f) {
        DataSource<CloseableReference<CloseableImage>> fetchImageFromBitmapCache = imagePipeline.fetchImageFromBitmapCache(imageRequest, this.mContext);
        try {
            try {
                CloseableReference<CloseableImage> mo6898getResult = fetchImageFromBitmapCache.mo6898getResult();
                if (mo6898getResult == null) {
                    return;
                }
                try {
                    try {
                        CloseableImage closeableImage = mo6898getResult.get();
                        if (!(closeableImage instanceof CloseableBitmap)) {
                            return;
                        }
                        Bitmap underlyingBitmap = ((CloseableBitmap) closeableImage).getUnderlyingBitmap();
                        if (underlyingBitmap == null) {
                            return;
                        }
                        doRender(canvas, paint, underlyingBitmap, f);
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                } finally {
                    CloseableReference.closeSafely(mo6898getResult);
                }
            } catch (Exception e2) {
                throw new IllegalStateException(e2);
            }
        } finally {
            fetchImageFromBitmapCache.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public void draw(Canvas canvas, Paint paint, float f) {
        if (!this.mLoading.get()) {
            ImagePipeline imagePipeline = Fresco.getImagePipeline();
            ImageRequest fromUri = ImageRequest.fromUri(new ImageSource(this.mContext, this.uriString).getUri());
            if (imagePipeline.isInBitmapMemoryCache(fromUri)) {
                tryRenderFromBitmapCache(imagePipeline, fromUri, canvas, paint, f * this.mOpacity);
            } else {
                loadBitmap(imagePipeline, fromUri);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public Path getPath(Canvas canvas, Paint paint) {
        ((VirtualView) this).mPath = new Path();
        ((VirtualView) this).mPath.addRect(getRect(), Path.Direction.CW);
        return ((VirtualView) this).mPath;
    }

    @ReactProp(name = "align")
    public void setAlign(String str) {
        this.mAlign = str;
        invalidate();
    }

    @ReactProp(name = "height")
    public void setHeight(Dynamic dynamic) {
        this.mH = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "meetOrSlice")
    public void setMeetOrSlice(int i) {
        this.mMeetOrSlice = i;
        invalidate();
    }

    @ReactProp(name = "src")
    public void setSrc(@Nullable ReadableMap readableMap) {
        if (readableMap != null) {
            this.uriString = readableMap.getString("uri");
            String str = this.uriString;
            if (str == null || str.isEmpty()) {
                return;
            }
            if (readableMap.hasKey("width") && readableMap.hasKey("height")) {
                this.mImageWidth = readableMap.getInt("width");
                this.mImageHeight = readableMap.getInt("height");
            } else {
                this.mImageWidth = 0;
                this.mImageHeight = 0;
            }
            if (Uri.parse(this.uriString).getScheme() != null) {
                return;
            }
            ResourceDrawableIdHelper.getInstance().getResourceDrawableUri(this.mContext, this.uriString);
        }
    }

    @ReactProp(name = "width")
    public void setWidth(Dynamic dynamic) {
        this.mW = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = ReactProperties.HereMapMarker.X)
    public void setX(Dynamic dynamic) {
        this.mX = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = ReactProperties.HereMapMarker.Y)
    public void setY(Dynamic dynamic) {
        this.mY = SVGLength.from(dynamic);
        invalidate();
    }
}
