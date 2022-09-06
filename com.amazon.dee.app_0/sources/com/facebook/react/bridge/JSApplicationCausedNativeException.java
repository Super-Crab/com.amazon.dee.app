package com.facebook.react.bridge;

import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public class JSApplicationCausedNativeException extends RuntimeException {
    public JSApplicationCausedNativeException(String str) {
        super(str);
    }

    public JSApplicationCausedNativeException(@Nullable String str, @Nullable Throwable th) {
        super(str, th);
    }
}
