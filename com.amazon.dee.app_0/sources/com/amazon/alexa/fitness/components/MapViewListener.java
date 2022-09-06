package com.amazon.alexa.fitness.components;

import com.here.sdk.mapview.MapView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: MapViewListener.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016J\b\u0010\b\u001a\u00020\u0003H\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/fitness/components/MapViewListener;", "", "onDestroy", "", "onMapOffCentered", "mapView", "Lcom/here/sdk/mapview/MapView;", "onPause", "onResume", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface MapViewListener {

    /* compiled from: MapViewListener.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class DefaultImpls {
        public static void onDestroy(MapViewListener mapViewListener) {
        }

        public static void onMapOffCentered(MapViewListener mapViewListener, @NotNull MapView mapView) {
            Intrinsics.checkParameterIsNotNull(mapView, "mapView");
        }

        public static void onPause(MapViewListener mapViewListener) {
        }

        public static void onResume(MapViewListener mapViewListener) {
        }
    }

    void onDestroy();

    void onMapOffCentered(@NotNull MapView mapView);

    void onPause();

    void onResume();
}
