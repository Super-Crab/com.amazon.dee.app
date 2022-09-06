package com.here.sdk.routing;

import androidx.annotation.Nullable;
import com.here.NativeBase;
import java.util.List;
/* loaded from: classes3.dex */
class CalculateRouteCallbackImpl extends NativeBase implements CalculateRouteCallback {
    protected CalculateRouteCallbackImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.routing.CalculateRouteCallbackImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                CalculateRouteCallbackImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.routing.CalculateRouteCallback
    public native void onRouteCalculated(@Nullable RoutingError routingError, @Nullable List<Route> list);
}
