package com.google.android.datatransport.runtime.backends;

import androidx.annotation.Nullable;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* loaded from: classes2.dex */
public interface BackendRegistry {
    @Nullable
    TransportBackend get(String str);
}
