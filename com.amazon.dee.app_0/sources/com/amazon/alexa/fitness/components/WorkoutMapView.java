package com.amazon.alexa.fitness.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.alexa.fitness.api.afx.FeatureService;
import com.amazon.alexa.lifecycle.api.LifecycleEvent;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.here.sdk.core.GeoBox;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.engine.SDKNativeEngine;
import com.here.sdk.core.engine.SDKOptions;
import com.here.sdk.engine.InitProvider;
import com.here.sdk.engine.OptionsReader;
import com.here.sdk.mapview.MapCamera;
import com.here.sdk.mapview.MapError;
import com.here.sdk.mapview.MapScene;
import com.here.sdk.mapview.MapScheme;
import com.here.sdk.mapview.MapView;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Iterator;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: WorkoutMapView.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\nH\u0016J\u0010\u0010 \u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u0013H\u0016J\u0006\u0010\"\u001a\u00020\u001eJ\b\u0010#\u001a\u0004\u0018\u00010\u0015J\b\u0010$\u001a\u00020\u001eH\u0016J\b\u0010%\u001a\u00020\u001eH\u0016J\b\u0010&\u001a\u00020\u001eH\u0016J\b\u0010'\u001a\u00020\u001eH\u0016J\b\u0010(\u001a\u00020\u001eH\u0016J\b\u0010)\u001a\u00020\u001eH\u0016J\b\u0010*\u001a\u00020\u001eH\u0014J\b\u0010+\u001a\u00020\u001eH\u0014J\u0018\u0010,\u001a\u00020\u001e2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0014J\b\u00101\u001a\u00020\u001eH\u0016R \u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001a\u001a\n \u001c*\u0004\u0018\u00010\u001b0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lcom/amazon/alexa/fitness/components/WorkoutMapView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "Lcom/amazon/alexa/protocols/lifecycle/MainActivityLifecycleObserver;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "adapters", "Ljava/util/WeakHashMap;", "Lcom/amazon/alexa/fitness/components/MapViewAdapter;", "Ljava/lang/ref/WeakReference;", "featureService", "Lcom/amazon/alexa/fitness/api/afx/FeatureService;", LifecycleEvent.IS_LOADED, "", "lastKnownBoundingBox", "Lcom/here/sdk/core/GeoBox;", "listeners", "Lcom/amazon/alexa/fitness/components/MapViewListener;", "mapView", "Lcom/here/sdk/mapview/MapView;", "getMapView", "()Lcom/here/sdk/mapview/MapView;", "setMapView", "(Lcom/here/sdk/mapview/MapView;)V", "notificationCenter", "Lcom/amazon/alexa/protocols/lifecycle/MainActivityLifecycleObserverRegistrar;", "kotlin.jvm.PlatformType", "addAdapter", "", "adapter", "addListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "center", "getMap", "onActivityCreated", "onActivityDestroy", "onActivityPause", "onActivityResume", "onActivityStart", "onActivityStop", "onAttachedToWindow", "onDetachedFromWindow", "onVisibilityChanged", "changedView", "Landroid/view/View;", "visibility", "", "update", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public class WorkoutMapView extends ConstraintLayout implements MainActivityLifecycleObserver {
    private final WeakHashMap<MapViewAdapter, WeakReference<MapViewAdapter>> adapters;
    private final FeatureService featureService;
    private boolean isLoaded;
    private GeoBox lastKnownBoundingBox;
    private final WeakHashMap<MapViewListener, WeakReference<MapViewListener>> listeners;
    @Nullable
    private MapView mapView;
    private final MainActivityLifecycleObserverRegistrar notificationCenter;

    /* compiled from: WorkoutMapView.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "error", "Lcom/here/sdk/mapview/MapError;", "onLoadScene"}, k = 3, mv = {1, 1, 16})
    /* renamed from: com.amazon.alexa.fitness.components.WorkoutMapView$3  reason: invalid class name */
    /* loaded from: classes8.dex */
    static final class AnonymousClass3 implements MapScene.LoadSceneCallback {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        AnonymousClass3() {
        }

        @Override // com.here.sdk.mapview.MapScene.LoadSceneCallback
        public final void onLoadScene(@Nullable MapError mapError) {
            if (mapError != null) {
                Log.e("AFX-WorkoutMapView", "loadScene failed: " + mapError);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @SuppressLint({"ClickableViewAccessibility"})
    public WorkoutMapView(@NotNull Context context, @NotNull AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
        this.featureService = (FeatureService) GeneratedOutlineSupport1.outline22(FeatureService.class, "ComponentRegistry.getIns…ervice::class.java).get()");
        this.notificationCenter = (MainActivityLifecycleObserverRegistrar) GeneratedOutlineSupport1.outline20(MainActivityLifecycleObserverRegistrar.class);
        this.adapters = new WeakHashMap<>();
        this.listeners = new WeakHashMap<>();
        if (!this.featureService.isMapViewEnabled()) {
            return;
        }
        InitProvider.initialize(context);
        if (SDKNativeEngine.getSharedInstance() == null) {
            try {
                Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
                String string = bundle.getString(OptionsReader.accessKeyIdName);
                String string2 = bundle.getString(OptionsReader.accessKeySecretName);
                if (string != null && string2 != null) {
                    SDKNativeEngine.setSharedInstance(new SDKNativeEngine(new SDKOptions(string, string2, "")));
                }
                Log.e("AFX-WorkoutMapView", "unable to retrieve keys to initialize Here Maps SDK");
                return;
            } catch (Exception e) {
                Log.e("AFX-WorkoutMapView", "error initializing HereMaps SDK", e);
            }
        }
        final MapView mapView = new MapView(context);
        mapView.onCreate(null);
        mapView.setOnReadyListener(new MapView.OnReadyListener() { // from class: com.amazon.alexa.fitness.components.WorkoutMapView.1
            @Override // com.here.sdk.mapview.MapView.OnReadyListener
            public final void onMapViewReady() {
                WorkoutMapView.this.isLoaded = true;
            }
        });
        mapView.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.alexa.fitness.components.WorkoutMapView.2
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent event) {
                GeoBox geoBox;
                Intrinsics.checkExpressionValueIsNotNull(event, "event");
                int action = event.getAction();
                if (action == 0) {
                    WorkoutMapView workoutMapView = WorkoutMapView.this;
                    MapCamera camera = mapView.getCamera();
                    Intrinsics.checkExpressionValueIsNotNull(camera, "mapView.camera");
                    workoutMapView.lastKnownBoundingBox = camera.getBoundingBox();
                } else if (action == 2 && (geoBox = WorkoutMapView.this.lastKnownBoundingBox) != null) {
                    MapCamera camera2 = mapView.getCamera();
                    Intrinsics.checkExpressionValueIsNotNull(camera2, "mapView.camera");
                    if (!Intrinsics.areEqual(geoBox, camera2.getBoundingBox())) {
                        Collection<WeakReference> values = WorkoutMapView.this.listeners.values();
                        Intrinsics.checkExpressionValueIsNotNull(values, "listeners.values");
                        for (WeakReference weakReference : values) {
                            MapViewListener mapViewListener = (MapViewListener) weakReference.get();
                            if (mapViewListener != null) {
                                mapViewListener.onMapOffCentered(mapView);
                            }
                        }
                        WorkoutMapView.this.lastKnownBoundingBox = null;
                    }
                }
                if (view != null) {
                    return view.onTouchEvent(event);
                }
                return true;
            }
        });
        center();
        mapView.getMapScene().loadScene(MapScheme.NORMAL_DAY, AnonymousClass3.INSTANCE);
        if (getParent() != null) {
            ViewParent parent = getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mapView);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
            }
        }
        addView(mapView, -1, -1);
        this.mapView = mapView;
    }

    public void addAdapter(@NotNull MapViewAdapter adapter) {
        Intrinsics.checkParameterIsNotNull(adapter, "adapter");
        String str = "adding adapter: " + adapter;
        WeakReference weakReference = new WeakReference(adapter);
        if (this.mapView != null) {
            adapter.setMapView(this);
        } else {
            Log.e("AFX-WorkoutMapView", "mapView does not exist");
        }
        this.adapters.put(weakReference.get(), weakReference);
    }

    public void addListener(@NotNull MapViewListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        WeakReference weakReference = new WeakReference(listener);
        this.listeners.put(weakReference.get(), weakReference);
    }

    public final void center() {
        GeoCoordinates geoCoordinates = new GeoCoordinates(47.6161434d, -122.3419602d);
        MapView map = getMap();
        if (map != null) {
            map.getCamera().lookAt(geoCoordinates, new MapCamera.OrientationUpdate(Double.valueOf((double) FrostVideoEffectController.VIDEO_STRENGTH_CLEAR), Double.valueOf((double) FrostVideoEffectController.VIDEO_STRENGTH_CLEAR)), 120.0d);
        }
    }

    @Nullable
    public final MapView getMap() {
        return this.mapView;
    }

    @Nullable
    public final MapView getMapView() {
        return this.mapView;
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityCreated() {
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityDestroy() {
        MapView mapView = this.mapView;
        if (mapView != null) {
            mapView.onDestroy();
        }
        Collection<WeakReference<MapViewListener>> values = this.listeners.values();
        Intrinsics.checkExpressionValueIsNotNull(values, "listeners.values");
        Iterator<T> it2 = values.iterator();
        while (it2.hasNext()) {
            MapViewListener mapViewListener = (MapViewListener) ((WeakReference) it2.next()).get();
            if (mapViewListener != null) {
                mapViewListener.onDestroy();
            }
        }
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityPause() {
        MapView mapView = this.mapView;
        if (mapView != null) {
            mapView.onPause();
        }
        Collection<WeakReference<MapViewListener>> values = this.listeners.values();
        Intrinsics.checkExpressionValueIsNotNull(values, "listeners.values");
        Iterator<T> it2 = values.iterator();
        while (it2.hasNext()) {
            MapViewListener mapViewListener = (MapViewListener) ((WeakReference) it2.next()).get();
            if (mapViewListener != null) {
                mapViewListener.onPause();
            }
        }
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityResume() {
        MapView mapView = this.mapView;
        if (mapView != null) {
            mapView.onResume();
        }
        Collection<WeakReference<MapViewListener>> values = this.listeners.values();
        Intrinsics.checkExpressionValueIsNotNull(values, "listeners.values");
        Iterator<T> it2 = values.iterator();
        while (it2.hasNext()) {
            MapViewListener mapViewListener = (MapViewListener) ((WeakReference) it2.next()).get();
            if (mapViewListener != null) {
                mapViewListener.onResume();
            }
        }
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityStart() {
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityStop() {
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MapView mapView = this.mapView;
        if (mapView != null) {
            mapView.onResume();
        }
        Collection<WeakReference<MapViewListener>> values = this.listeners.values();
        Intrinsics.checkExpressionValueIsNotNull(values, "listeners.values");
        Iterator<T> it2 = values.iterator();
        while (it2.hasNext()) {
            MapViewListener mapViewListener = (MapViewListener) ((WeakReference) it2.next()).get();
            if (mapViewListener != null) {
                mapViewListener.onResume();
            }
        }
        this.notificationCenter.addObserver(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        MapView mapView = this.mapView;
        if (mapView != null) {
            mapView.onDestroy();
        }
        Collection<WeakReference<MapViewListener>> values = this.listeners.values();
        Intrinsics.checkExpressionValueIsNotNull(values, "listeners.values");
        Iterator<T> it2 = values.iterator();
        while (it2.hasNext()) {
            MapViewListener mapViewListener = (MapViewListener) ((WeakReference) it2.next()).get();
            if (mapViewListener != null) {
                mapViewListener.onDestroy();
            }
        }
        this.notificationCenter.removeObserver(this);
    }

    @Override // android.view.View
    protected void onVisibilityChanged(@NotNull View changedView, int i) {
        Intrinsics.checkParameterIsNotNull(changedView, "changedView");
        if (i != 0) {
            Collection<WeakReference<MapViewListener>> values = this.listeners.values();
            Intrinsics.checkExpressionValueIsNotNull(values, "listeners.values");
            Iterator<T> it2 = values.iterator();
            while (it2.hasNext()) {
                MapViewListener mapViewListener = (MapViewListener) ((WeakReference) it2.next()).get();
                if (mapViewListener != null) {
                    mapViewListener.onPause();
                }
            }
            return;
        }
        Collection<WeakReference<MapViewListener>> values2 = this.listeners.values();
        Intrinsics.checkExpressionValueIsNotNull(values2, "listeners.values");
        Iterator<T> it3 = values2.iterator();
        while (it3.hasNext()) {
            MapViewListener mapViewListener2 = (MapViewListener) ((WeakReference) it3.next()).get();
            if (mapViewListener2 != null) {
                mapViewListener2.onResume();
            }
        }
    }

    public final void setMapView(@Nullable MapView mapView) {
        this.mapView = mapView;
    }

    public void update() {
        Collection<WeakReference<MapViewAdapter>> values = this.adapters.values();
        Intrinsics.checkExpressionValueIsNotNull(values, "adapters.values");
        Iterator<T> it2 = values.iterator();
        while (it2.hasNext()) {
            MapViewAdapter mapViewAdapter = (MapViewAdapter) ((WeakReference) it2.next()).get();
            if (mapViewAdapter != null) {
                mapViewAdapter.adapt();
            }
        }
    }
}
