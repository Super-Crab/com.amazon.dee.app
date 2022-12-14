package com.google.android.datatransport.cct;

import androidx.annotation.Keep;
import com.google.android.datatransport.runtime.backends.BackendFactory;
import com.google.android.datatransport.runtime.backends.CreationContext;
import com.google.android.datatransport.runtime.backends.TransportBackend;
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
@Keep
/* loaded from: classes2.dex */
public class CctBackendFactory implements BackendFactory {
    @Override // com.google.android.datatransport.runtime.backends.BackendFactory
    public TransportBackend create(CreationContext creationContext) {
        return new zzc(creationContext.getApplicationContext(), creationContext.getWallClock(), creationContext.getMonotonicClock());
    }
}
