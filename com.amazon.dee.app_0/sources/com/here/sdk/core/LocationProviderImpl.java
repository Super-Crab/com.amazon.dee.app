package com.here.sdk.core;

import androidx.annotation.Nullable;
import com.here.NativeBase;
/* loaded from: classes3.dex */
class LocationProviderImpl extends NativeBase implements LocationProvider {
    protected LocationProviderImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.core.LocationProviderImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                LocationProviderImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.core.LocationProvider
    @Nullable
    public native LocationListener getListener();

    @Override // com.here.sdk.core.LocationProvider
    public native void setListener(@Nullable LocationListener locationListener);

    @Override // com.here.sdk.core.LocationProvider
    public native void start();

    @Override // com.here.sdk.core.LocationProvider
    public native void stop();
}
