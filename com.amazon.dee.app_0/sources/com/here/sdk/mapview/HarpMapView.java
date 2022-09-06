package com.here.sdk.mapview;

import com.here.NativeBase;
/* loaded from: classes3.dex */
class HarpMapView extends NativeBase {
    protected HarpMapView(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.HarpMapView.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                HarpMapView.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);
}
