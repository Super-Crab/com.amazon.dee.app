package com.amazon.alexa.sharing.comms.dependencies;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class AlexaSharingModule_ProvidesContextFactory implements Factory<Context> {
    private final AlexaSharingModule module;

    public AlexaSharingModule_ProvidesContextFactory(AlexaSharingModule alexaSharingModule) {
        this.module = alexaSharingModule;
    }

    public static AlexaSharingModule_ProvidesContextFactory create(AlexaSharingModule alexaSharingModule) {
        return new AlexaSharingModule_ProvidesContextFactory(alexaSharingModule);
    }

    public static Context provideInstance(AlexaSharingModule alexaSharingModule) {
        return proxyProvidesContext(alexaSharingModule);
    }

    public static Context proxyProvidesContext(AlexaSharingModule alexaSharingModule) {
        return (Context) Preconditions.checkNotNull(alexaSharingModule.providesContext(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Context mo10268get() {
        return provideInstance(this.module);
    }
}
