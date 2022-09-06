package com.here.sdk.mapview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.here.NativeBase;
/* loaded from: classes3.dex */
public final class MapScene extends NativeBase {

    /* loaded from: classes3.dex */
    public enum LayerState {
        VISIBLE(0),
        HIDDEN(1);
        
        public final int value;

        LayerState(int i) {
            this.value = i;
        }
    }

    /* loaded from: classes3.dex */
    public static final class Layers {
        public static final String BUILDING_FOOTPRINTS = "heresdk_layer_building_footprints";
        public static final String EXTRUDED_BUILDINGS = "heresdk_layer_extruded_buildings";
        public static final String TRAFFIC_FLOW = "heresdk_layer_traffic_flow";
        public static final String TRAFFIC_INCIDENTS = "heresdk_layer_traffic_incidents";
    }

    @FunctionalInterface
    /* loaded from: classes3.dex */
    public interface LoadSceneCallback {
        void onLoadScene(@Nullable MapError mapError);
    }

    /* loaded from: classes3.dex */
    static class LoadSceneCallbackImpl extends NativeBase implements LoadSceneCallback {
        protected LoadSceneCallbackImpl(long j, Object obj) {
            super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.MapScene.LoadSceneCallbackImpl.1
                @Override // com.here.NativeBase.Disposer
                public void disposeNative(long j2) {
                    LoadSceneCallbackImpl.disposeNativeHandle(j2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static native void disposeNativeHandle(long j);

        @Override // com.here.sdk.mapview.MapScene.LoadSceneCallback
        public native void onLoadScene(@Nullable MapError mapError);
    }

    protected MapScene(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.MapScene.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                MapScene.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    native void addMapArrow(@NonNull MapArrow mapArrow);

    public native void addMapMarker(@NonNull MapMarker mapMarker);

    public native void addMapMarker3d(@NonNull MapMarker3D mapMarker3D);

    public native void addMapPolygon(@NonNull MapPolygon mapPolygon);

    public native void addMapPolyline(@NonNull MapPolyline mapPolyline);

    native void destroy();

    native boolean isSceneLoaded();

    public native void loadScene(@NonNull MapScheme mapScheme, @Nullable LoadSceneCallback loadSceneCallback);

    public native void loadScene(@NonNull String str, @Nullable LoadSceneCallback loadSceneCallback);

    native void removeMapArrow(@NonNull MapArrow mapArrow);

    public native void removeMapMarker(@NonNull MapMarker mapMarker);

    public native void removeMapMarker3d(@NonNull MapMarker3D mapMarker3D);

    public native void removeMapPolygon(@NonNull MapPolygon mapPolygon);

    public native void removeMapPolyline(@NonNull MapPolyline mapPolyline);

    native void setHarpMapView(@NonNull HarpMapView harpMapView);

    public native void setLayerState(@NonNull String str, @NonNull LayerState layerState);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native void setMapSceneReadyListener(@Nullable MapSceneReadyListener mapSceneReadyListener);

    native void setMapSchemeChangeListener(@Nullable MapSchemeChangeListener mapSchemeChangeListener);
}
