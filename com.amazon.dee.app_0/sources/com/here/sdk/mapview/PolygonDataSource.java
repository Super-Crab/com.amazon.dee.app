package com.here.sdk.mapview;

import com.here.NativeBase;
/* loaded from: classes3.dex */
class PolygonDataSource extends NativeBase {
    protected PolygonDataSource(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.PolygonDataSource.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                PolygonDataSource.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);
}
