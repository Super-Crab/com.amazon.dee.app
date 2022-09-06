package com.here.sdk.core.engine;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.here.NativeBase;
import java.util.Locale;
/* loaded from: classes3.dex */
class LocaleFactoryImpl extends NativeBase implements LocaleFactory {
    protected LocaleFactoryImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.core.engine.LocaleFactoryImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                LocaleFactoryImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.core.engine.LocaleFactory
    @Nullable
    public native Locale getLocaleByBcp47(@NonNull String str);
}
