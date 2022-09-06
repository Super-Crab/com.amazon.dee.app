package com.here.sdk.core.utilities;

import androidx.annotation.NonNull;
/* loaded from: classes3.dex */
public final class Preconditions {
    private Preconditions() {
    }

    @NonNull
    public static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw null;
    }
}
