package com.amazon.deecomms.core;

import com.amazon.deecomms.calling.util.VoxUtils;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class AlexaModule_ProvidesVoxUtilsFactory implements Factory<VoxUtils> {
    private final AlexaModule module;

    public AlexaModule_ProvidesVoxUtilsFactory(AlexaModule alexaModule) {
        this.module = alexaModule;
    }

    public static AlexaModule_ProvidesVoxUtilsFactory create(AlexaModule alexaModule) {
        return new AlexaModule_ProvidesVoxUtilsFactory(alexaModule);
    }

    public static VoxUtils provideInstance(AlexaModule alexaModule) {
        return (VoxUtils) Preconditions.checkNotNull(alexaModule.providesVoxUtils(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static VoxUtils proxyProvidesVoxUtils(AlexaModule alexaModule) {
        return (VoxUtils) Preconditions.checkNotNull(alexaModule.providesVoxUtils(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoxUtils mo10268get() {
        return provideInstance(this.module);
    }
}
