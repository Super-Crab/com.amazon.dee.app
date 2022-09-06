package com.google.android.datatransport.runtime.backends;

import dagger.Binds;
import dagger.Module;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
@Module
/* loaded from: classes2.dex */
public abstract class BackendRegistryModule {
    @Binds
    abstract BackendRegistry backendRegistry(MetadataBackendRegistry metadataBackendRegistry);
}
