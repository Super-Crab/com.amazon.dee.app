package com.here.sdk.core.engine;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.here.NativeBase;
import com.here.sdk.core.errors.InstantiationErrorException;
/* loaded from: classes3.dex */
public final class SDKNativeEngine extends NativeBase {
    protected SDKNativeEngine(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.core.engine.SDKNativeEngine.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                SDKNativeEngine.disposeNativeHandle(j2);
            }
        });
    }

    public SDKNativeEngine(@NonNull SDKOptions sDKOptions) throws InstantiationErrorException {
        this(make(sDKOptions), null);
        cacheThisInstance();
    }

    private native void cacheThisInstance();

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Nullable
    public static native SDKNativeEngine getSharedInstance();

    private static native long make(@NonNull SDKOptions sDKOptions) throws InstantiationErrorException;

    public static native void setSharedInstance(@Nullable SDKNativeEngine sDKNativeEngine);

    public native void dispose();

    @NonNull
    public native SDKOptions getOptions();

    @Deprecated
    public native void setAccessKey(@NonNull String str, @NonNull String str2);

    public native void setAccessKeySecret(@NonNull String str);

    public native void setLogAppender(@NonNull LogAppender logAppender);
}
