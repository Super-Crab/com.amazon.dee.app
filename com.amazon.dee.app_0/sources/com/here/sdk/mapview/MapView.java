package com.here.sdk.mapview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.here.sdk.core.Anchor2D;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.LanguageCode;
import com.here.sdk.core.Point2D;
import com.here.sdk.core.engine.SDKNativeEngine;
import com.here.sdk.gestures.GestureDetector;
import com.here.sdk.gestures.Gestures;
import com.here.sdk.mapview.MapView;
import com.here.sdk.mapview.MapViewBase;
import com.here.sdk.mapview.MapViewInternal;
import com.here.sdk.mapview.NetworkChangesObserver;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class MapView extends FrameLayout implements SurfaceHolder.Callback, MapViewBase {
    private static final String BUNDLE_KEY_MAP_VIEW_ID = "com.here.sdk.bundle_key_map_view_id";
    private static final String LOG_TAG = "MapView";
    private static final long NO_MAP_VIEW_ID = -1;
    private static boolean sNetworkObservationStarted = false;
    private FirstFrameRenderedListener mFirstFrameRenderedListener;
    private GestureDetector mGestureDetector;
    private MapSceneReadyListener mMapSceneReadyListener;
    private MapViewInternal mMapViewInternal;
    private long mNativeSurface;
    private SurfaceView mSurfaceView;
    private long mTimeOfFirstFrameRendered;
    private ViewPinsManager mViewPinsManager;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class FirstFrameRenderedListener implements com.here.sdk.mapview.FirstFrameRenderedListener {
        private final WeakReference<MapView> mWeakMapView;

        FirstFrameRenderedListener(WeakReference<MapView> weakReference) {
            this.mWeakMapView = weakReference;
        }

        @Override // com.here.sdk.mapview.FirstFrameRenderedListener
        public void onFirstFrameRendered() {
            MapView mapView = this.mWeakMapView.get();
            if (mapView != null) {
                mapView.mTimeOfFirstFrameRendered = SystemClock.elapsedRealtime();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class MapSceneReadyListener implements com.here.sdk.mapview.MapSceneReadyListener {
        private OnReadyListener mReadyListener;
        private final WeakReference<MapView> mWeakMapView;

        MapSceneReadyListener(WeakReference<MapView> weakReference) {
            this.mWeakMapView = weakReference;
        }

        @Override // com.here.sdk.mapview.MapSceneReadyListener
        public void onMapSceneReady() {
            MapView mapView = this.mWeakMapView.get();
            if (mapView != null) {
                mapView.mSurfaceView.setBackground(null);
                mapView.mViewPinsManager.updatePositions();
            }
            OnReadyListener onReadyListener = this.mReadyListener;
            if (onReadyListener != null) {
                onReadyListener.onMapViewReady();
            }
        }

        void setOnReadyListener(OnReadyListener onReadyListener) {
            this.mReadyListener = onReadyListener;
        }
    }

    @FunctionalInterface
    /* loaded from: classes3.dex */
    public interface OnReadyListener {
        void onMapViewReady();
    }

    @FunctionalInterface
    /* loaded from: classes3.dex */
    public interface TakeScreenshotCallback {
        void onScreenshotTaken(@Nullable Bitmap bitmap);
    }

    /* loaded from: classes3.dex */
    public interface ViewPin {
        Anchor2D getAnchorPoint();

        GeoCoordinates getGeoCoordinates();

        void setAnchorPoint(@NonNull Anchor2D anchor2D);

        void setGeoCoordinates(@NonNull GeoCoordinates geoCoordinates);

        void unpin();
    }

    public MapView(Context context) {
        this(context, null);
    }

    public MapView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MapView(Context context, AttributeSet attributeSet, int i) {
        this(null, context, attributeSet, i);
    }

    public MapView(SDKNativeEngine sDKNativeEngine, Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mNativeSurface = 0L;
        this.mTimeOfFirstFrameRendered = 0L;
        activateNetworkObservation(context);
        createSurface();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(TakeScreenshotCallback takeScreenshotCallback, byte[] bArr) {
        takeScreenshotCallback.onScreenshotTaken(bArr != null ? BitmapFactory.decodeByteArray(bArr, 0, bArr.length) : null);
    }

    private static void activateNetworkObservation(final Context context) {
        if (!sNetworkObservationStarted) {
            NetworkChangesObserver.observe(context, new NetworkChangesObserver.Listener() { // from class: com.here.sdk.mapview.MapView.1
                @Override // com.here.sdk.mapview.NetworkChangesObserver.Listener
                public void onConnected() {
                    new Handler(context.getMainLooper()).post($$Lambda$YOVmegADoZxWDVUXWQAPiJPiNI.INSTANCE);
                }

                @Override // com.here.sdk.mapview.NetworkChangesObserver.Listener
                public void onDisconnected() {
                }
            });
            sNetworkObservationStarted = true;
        }
    }

    private void checkMapViewInternalInitialized() {
        if (this.mMapViewInternal != null) {
            return;
        }
        throw new IllegalStateException("Please call MapView.onCreate() before calling any other MapView methods.");
    }

    private void destroyNativeSurface() {
        if (this.mNativeSurface != 0) {
            this.mViewPinsManager.setup(null, 0, 0);
            this.mMapViewInternal.detachHarpFromRenderTarget();
            releaseNativeSurface(this.mNativeSurface);
            this.mNativeSurface = 0L;
        }
    }

    private static native long getNativeSurface(Surface surface);

    @Nullable
    public static LanguageCode getPrimaryLanguage() {
        return MapContextProvider.getInstance().getPrimaryLanguage();
    }

    private boolean isContinuousRendering() {
        return this.mMapViewInternal.isContinuousRendering();
    }

    private static native void releaseNativeSurface(long j);

    private void setContinuousRendering(boolean z) {
        this.mMapViewInternal.setContinuousRendering(z);
    }

    public static void setPrimaryLanguage(@Nullable LanguageCode languageCode) {
        MapContextProvider.getInstance().setPrimaryLanguage(languageCode);
    }

    @Override // com.here.sdk.mapview.MapViewBase
    public void addMapRepresentable(@NonNull MapRepresentable mapRepresentable) {
        checkMapViewInternalInitialized();
        this.mMapViewInternal.addMapRepresentable(mapRepresentable);
    }

    void createSurface() {
        this.mViewPinsManager = new ViewPinsManager(getContext());
        this.mSurfaceView = new SurfaceView(getContext());
        this.mSurfaceView.setBackground(new ColorDrawable(Color.rgb(211, 211, 211)));
        this.mSurfaceView.getHolder().addCallback(this);
        addView(this.mSurfaceView);
        addView(this.mViewPinsManager);
    }

    void destroySurface() {
        removeAllViews();
        destroyNativeSurface();
    }

    @Override // com.here.sdk.mapview.MapViewBase
    @Nullable
    public Point2D geoToViewCoordinates(@NonNull GeoCoordinates geoCoordinates) {
        checkMapViewInternalInitialized();
        return this.mMapViewInternal.geoToViewCoordinates(geoCoordinates);
    }

    @Override // com.here.sdk.mapview.MapViewBase
    @NonNull
    public MapCamera getCamera() {
        checkMapViewInternalInitialized();
        return this.mMapViewInternal.getCamera();
    }

    @Override // com.here.sdk.mapview.MapViewBase
    @NonNull
    public Gestures getGestures() {
        checkMapViewInternalInitialized();
        return this.mMapViewInternal.getGestures();
    }

    @Override // com.here.sdk.mapview.MapViewBase
    @NonNull
    public MapScene getMapScene() {
        checkMapViewInternalInitialized();
        return this.mMapViewInternal.getMapScene();
    }

    @Override // com.here.sdk.mapview.MapViewBase
    public double getPixelScale() {
        checkMapViewInternalInitialized();
        return this.mMapViewInternal.getPixelScale();
    }

    public List<ViewPin> getViewPins() {
        return new ArrayList(this.mViewPinsManager.getViewPins());
    }

    public void onCreate(Bundle bundle) {
        onCreate(bundle, "");
    }

    public void onCreate(Bundle bundle, String str) {
        long j = -1;
        if (bundle != null) {
            j = bundle.getLong(BUNDLE_KEY_MAP_VIEW_ID + str, -1L);
        }
        this.mMapViewInternal = MapViewInternal.hasInstance(j) ? MapViewInternal.restoreInstance(j) : new MapViewInternal();
        this.mMapViewInternal.getCamera().addObserver(this.mViewPinsManager);
        this.mMapSceneReadyListener = new MapSceneReadyListener(new WeakReference(this));
        this.mMapViewInternal.getMapScene().setMapSceneReadyListener(this.mMapSceneReadyListener);
        FirstFrameRenderedListener firstFrameRenderedListener = new FirstFrameRenderedListener(new WeakReference(this));
        this.mFirstFrameRenderedListener = firstFrameRenderedListener;
        this.mMapViewInternal.setFirstFrameRenderedListener(firstFrameRenderedListener);
        this.mGestureDetector = new GestureDetector(this.mMapViewInternal.getInternalGestureDetector());
    }

    public void onDestroy() {
        this.mMapViewInternal.getCamera().removeObserver(this.mViewPinsManager);
        removeView(this.mViewPinsManager);
        this.mSurfaceView.getHolder().removeCallback(this);
        removeView(this.mSurfaceView);
        this.mMapViewInternal.getMapScene().setMapSceneReadyListener(null);
        destroySurface();
        if (!MapViewInternal.hasInstance(this.mMapViewInternal)) {
            this.mMapViewInternal.destroy();
        }
    }

    public void onPause() {
        MapContextProvider.getInstance().pause();
    }

    public void onResume() {
        MapContextProvider.getInstance().resume();
    }

    public void onSaveInstanceState(Bundle bundle) {
        onSaveInstanceState(bundle, "");
    }

    public void onSaveInstanceState(Bundle bundle, String str) {
        MapViewInternal mapViewInternal = this.mMapViewInternal;
        if (mapViewInternal != null) {
            long storeInstance = MapViewInternal.storeInstance(mapViewInternal);
            bundle.putLong(BUNDLE_KEY_MAP_VIEW_ID + str, storeInstance);
        }
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.mGestureDetector.processMotionEvent(motionEvent);
        return true;
    }

    @Override // com.here.sdk.mapview.MapViewBase
    public void pickMapItems(@NonNull Point2D point2D, double d, @NonNull MapViewBase.PickMapItemsCallback pickMapItemsCallback) {
        this.mMapViewInternal.pickMapItems(point2D, d, pickMapItemsCallback);
    }

    @Nullable
    public ViewPin pinView(@NonNull View view, GeoCoordinates geoCoordinates) {
        if (view.getParent() != null) {
            return null;
        }
        return this.mViewPinsManager.pinView(view, geoCoordinates);
    }

    @Override // com.here.sdk.mapview.MapViewBase
    public void removeMapRepresentable(@NonNull MapRepresentable mapRepresentable) {
        checkMapViewInternalInitialized();
        this.mMapViewInternal.removeMapRepresentable(mapRepresentable);
    }

    public void setOnReadyListener(OnReadyListener onReadyListener) {
        checkMapViewInternalInitialized();
        this.mMapSceneReadyListener.setOnReadyListener(onReadyListener);
    }

    @Override // com.here.sdk.mapview.MapViewBase
    public void setWatermarkPosition(WatermarkPlacement watermarkPlacement, long j) {
        checkMapViewInternalInitialized();
        this.mMapViewInternal.setWatermarkPosition(watermarkPlacement, j);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (this.mNativeSurface == 0) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            long nativeSurface = getNativeSurface(surfaceHolder.getSurface());
            this.mNativeSurface = nativeSurface;
            this.mMapViewInternal.attachHarpToRenderTarget(nativeSurface);
            this.mMapViewInternal.setDisplayMetrics(displayMetrics.density, displayMetrics.widthPixels, displayMetrics.heightPixels);
            this.mMapViewInternal.setViewSize(i2, i3);
            this.mViewPinsManager.setup(new GeoConverter(this), i2, i3);
            this.mMapViewInternal.addWatermark(new Point2D(i2, i3));
        }
        this.mSurfaceView.setWillNotDraw(false);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        destroyNativeSurface();
    }

    public void takeScreenshot(final TakeScreenshotCallback takeScreenshotCallback) {
        checkMapViewInternalInitialized();
        this.mMapViewInternal.takeScreenshot(new MapViewInternal.TakeScreenshotCallback() { // from class: com.here.sdk.mapview.-$$Lambda$MapView$4N84sVwfztL1fRPJux-WuuOR3fs
            @Override // com.here.sdk.mapview.MapViewInternal.TakeScreenshotCallback
            public final void onScreenshotTaken(byte[] bArr) {
                MapView.a(MapView.TakeScreenshotCallback.this, bArr);
            }
        });
    }

    public void unpinView(@NonNull View view) {
        this.mViewPinsManager.unpinView(view);
    }

    @Override // com.here.sdk.mapview.MapViewBase
    @Nullable
    public GeoCoordinates viewToGeoCoordinates(@NonNull Point2D point2D) {
        checkMapViewInternalInitialized();
        return this.mMapViewInternal.viewToGeoCoordinates(point2D);
    }
}
