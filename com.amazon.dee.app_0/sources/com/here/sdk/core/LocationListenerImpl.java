package com.here.sdk.core;

import androidx.annotation.NonNull;
import com.here.NativeBase;
/* loaded from: classes3.dex */
class LocationListenerImpl extends NativeBase implements LocationListener {
    protected LocationListenerImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.core.LocationListenerImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                LocationListenerImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.core.LocationListener
    @Deprecated
    public native void onLocationTimeout();

    @Override // com.here.sdk.core.LocationListener
    public native void onLocationUpdated(@NonNull Location location);
}
