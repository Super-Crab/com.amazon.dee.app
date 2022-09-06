package com.bugsnag.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;
@Deprecated
/* loaded from: classes.dex */
public class NetworkException extends IOException {
    private static final long serialVersionUID = -4370366096145029322L;

    public NetworkException(@NonNull String str, @Nullable Throwable th) {
        super(str, th);
    }
}
