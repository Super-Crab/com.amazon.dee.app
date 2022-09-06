package com.here.sdk.engine;

import android.content.Context;
import androidx.annotation.NonNull;
import com.here.sdk.core.utilities.Preconditions;
/* loaded from: classes3.dex */
final class NetworkingInitializer {
    private NetworkingInitializer() {
    }

    private static native void initNative(@NonNull Context context);

    public static void initialize(@NonNull Context context) {
        initNative((Context) Preconditions.checkNotNull(context));
    }
}
