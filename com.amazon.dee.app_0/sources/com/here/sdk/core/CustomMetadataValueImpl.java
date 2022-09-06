package com.here.sdk.core;

import androidx.annotation.NonNull;
import com.here.NativeBase;
/* loaded from: classes3.dex */
class CustomMetadataValueImpl extends NativeBase implements CustomMetadataValue {
    protected CustomMetadataValueImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.core.CustomMetadataValueImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                CustomMetadataValueImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.core.CustomMetadataValue
    @NonNull
    public native String getTag();
}
