package com.here.sdk.mapview;

import androidx.annotation.NonNull;
import com.here.NativeBase;
import com.here.sdk.mapview.MapCamera;
/* loaded from: classes3.dex */
class MapCameraObserverImpl extends NativeBase implements MapCameraObserver {
    protected MapCameraObserverImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.MapCameraObserverImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                MapCameraObserverImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.mapview.MapCameraObserver
    public native void onCameraUpdated(@NonNull MapCamera.State state);
}
