package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.smarthomecameras.directives.DirectiveSender;
import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class PtzModule_ProvidesDirectiveSenderFactory implements Factory<DirectiveSender> {
    private final Provider<Gson> gsonProvider;
    private final PtzModule module;

    public PtzModule_ProvidesDirectiveSenderFactory(PtzModule ptzModule, Provider<Gson> provider) {
        this.module = ptzModule;
        this.gsonProvider = provider;
    }

    public static PtzModule_ProvidesDirectiveSenderFactory create(PtzModule ptzModule, Provider<Gson> provider) {
        return new PtzModule_ProvidesDirectiveSenderFactory(ptzModule, provider);
    }

    public static DirectiveSender provideInstance(PtzModule ptzModule, Provider<Gson> provider) {
        return proxyProvidesDirectiveSender(ptzModule, provider.mo10268get());
    }

    public static DirectiveSender proxyProvidesDirectiveSender(PtzModule ptzModule, Gson gson) {
        return (DirectiveSender) Preconditions.checkNotNull(ptzModule.providesDirectiveSender(gson), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DirectiveSender mo10268get() {
        return provideInstance(this.module, this.gsonProvider);
    }
}
