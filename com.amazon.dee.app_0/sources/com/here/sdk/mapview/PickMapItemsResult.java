package com.here.sdk.mapview;

import androidx.annotation.NonNull;
import com.here.NativeBase;
import java.util.List;
/* loaded from: classes3.dex */
public final class PickMapItemsResult extends NativeBase {
    protected PickMapItemsResult(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.PickMapItemsResult.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                PickMapItemsResult.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @NonNull
    public native List<MapMarker> getMarkers();

    @NonNull
    public native List<MapPolygon> getPolygons();

    @NonNull
    public native List<MapPolyline> getPolylines();
}
