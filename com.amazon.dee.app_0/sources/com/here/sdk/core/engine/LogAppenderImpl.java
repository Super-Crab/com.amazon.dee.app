package com.here.sdk.core.engine;

import androidx.annotation.NonNull;
import com.here.NativeBase;
/* loaded from: classes3.dex */
class LogAppenderImpl extends NativeBase implements LogAppender {
    protected LogAppenderImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.core.engine.LogAppenderImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                LogAppenderImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.core.engine.LogAppender
    public native void log(@NonNull LogLevel logLevel, @NonNull String str);
}
