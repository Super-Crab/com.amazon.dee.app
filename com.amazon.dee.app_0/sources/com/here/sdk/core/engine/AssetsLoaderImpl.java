package com.here.sdk.core.engine;

import androidx.annotation.NonNull;
import com.here.NativeBase;
import java.util.List;
/* loaded from: classes3.dex */
class AssetsLoaderImpl extends NativeBase implements AssetsLoader {
    protected AssetsLoaderImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.core.engine.AssetsLoaderImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                AssetsLoaderImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.core.engine.AssetsLoader
    @NonNull
    public native List<String> getSubfolderNames(@NonNull String str);

    @Override // com.here.sdk.core.engine.AssetsLoader
    @NonNull
    public native String loadAsset(@NonNull String str);

    @Override // com.here.sdk.core.engine.AssetsLoader
    @NonNull
    public native byte[] loadAssetBlob(@NonNull String str);
}
