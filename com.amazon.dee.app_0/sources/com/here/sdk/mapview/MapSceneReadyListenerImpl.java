package com.here.sdk.mapview;

import com.here.NativeBase;
/* loaded from: classes3.dex */
class MapSceneReadyListenerImpl extends NativeBase implements MapSceneReadyListener {
    protected MapSceneReadyListenerImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.MapSceneReadyListenerImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                MapSceneReadyListenerImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.mapview.MapSceneReadyListener
    public native void onMapSceneReady();
}
