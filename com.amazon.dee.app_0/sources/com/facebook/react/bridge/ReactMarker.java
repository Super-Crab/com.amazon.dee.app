package com.facebook.react.bridge;

import androidx.annotation.Nullable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes2.dex */
public class ReactMarker {
    private static final List<MarkerListener> sListeners = new CopyOnWriteArrayList();
    private static final List<FabricMarkerListener> sFabricMarkerListeners = new CopyOnWriteArrayList();

    /* loaded from: classes2.dex */
    public interface FabricMarkerListener {
        void logFabricMarker(ReactMarkerConstants reactMarkerConstants, @Nullable String str, int i, long j);
    }

    /* loaded from: classes2.dex */
    public interface MarkerListener {
        void logMarker(ReactMarkerConstants reactMarkerConstants, @Nullable String str, int i);
    }

    public static void addFabricListener(FabricMarkerListener fabricMarkerListener) {
        if (!sFabricMarkerListeners.contains(fabricMarkerListener)) {
            sFabricMarkerListeners.add(fabricMarkerListener);
        }
    }

    public static void addListener(MarkerListener markerListener) {
        if (!sListeners.contains(markerListener)) {
            sListeners.add(markerListener);
        }
    }

    public static void clearFabricMarkerListeners() {
        sFabricMarkerListeners.clear();
    }

    public static void clearMarkerListeners() {
        sListeners.clear();
    }

    public static void logFabricMarker(ReactMarkerConstants reactMarkerConstants, @Nullable String str, int i, long j) {
        for (FabricMarkerListener fabricMarkerListener : sFabricMarkerListeners) {
            fabricMarkerListener.logFabricMarker(reactMarkerConstants, str, i, j);
        }
    }

    public static void logMarker(String str) {
        logMarker(str, (String) null);
    }

    public static void removeFabricListener(FabricMarkerListener fabricMarkerListener) {
        sFabricMarkerListeners.remove(fabricMarkerListener);
    }

    public static void removeListener(MarkerListener markerListener) {
        sListeners.remove(markerListener);
    }

    public static void logMarker(String str, int i) {
        logMarker(str, (String) null, i);
    }

    public static void logFabricMarker(ReactMarkerConstants reactMarkerConstants, @Nullable String str, int i) {
        logFabricMarker(reactMarkerConstants, str, i, -1L);
    }

    public static void logMarker(String str, @Nullable String str2) {
        logMarker(str, str2, 0);
    }

    public static void logMarker(String str, @Nullable String str2, int i) {
        logMarker(ReactMarkerConstants.valueOf(str), str2, i);
    }

    public static void logMarker(ReactMarkerConstants reactMarkerConstants) {
        logMarker(reactMarkerConstants, (String) null, 0);
    }

    public static void logMarker(ReactMarkerConstants reactMarkerConstants, int i) {
        logMarker(reactMarkerConstants, (String) null, i);
    }

    public static void logMarker(ReactMarkerConstants reactMarkerConstants, @Nullable String str) {
        logMarker(reactMarkerConstants, str, 0);
    }

    public static void logMarker(ReactMarkerConstants reactMarkerConstants, @Nullable String str, int i) {
        logFabricMarker(reactMarkerConstants, str, i);
        for (MarkerListener markerListener : sListeners) {
            markerListener.logMarker(reactMarkerConstants, str, i);
        }
    }
}
