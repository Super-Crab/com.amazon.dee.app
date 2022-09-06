package com.here.sdk.mapview;

import androidx.annotation.NonNull;
import com.here.NativeBase;
/* loaded from: classes3.dex */
class MapRepresentableImpl extends NativeBase implements MapRepresentable {
    protected MapRepresentableImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.MapRepresentableImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                MapRepresentableImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.mapview.MapRepresentable
    public native void attach(@NonNull MapViewBase mapViewBase);

    @Override // com.here.sdk.mapview.MapRepresentable
    public native void detach(@NonNull MapViewBase mapViewBase);
}
