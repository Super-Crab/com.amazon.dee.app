package com.amazon.alexa.smarthomecameras.dependencies.modules;

import android.view.View;
import com.amazon.ptz.digital.DigitalZoomState;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class PtzModule_ProvidesDigitalZoomStateFactory implements Factory<DigitalZoomState> {
    private final PtzModule module;
    private final Provider<View> viewProvider;

    public PtzModule_ProvidesDigitalZoomStateFactory(PtzModule ptzModule, Provider<View> provider) {
        this.module = ptzModule;
        this.viewProvider = provider;
    }

    public static PtzModule_ProvidesDigitalZoomStateFactory create(PtzModule ptzModule, Provider<View> provider) {
        return new PtzModule_ProvidesDigitalZoomStateFactory(ptzModule, provider);
    }

    public static DigitalZoomState provideInstance(PtzModule ptzModule, Provider<View> provider) {
        return proxyProvidesDigitalZoomState(ptzModule, provider.mo10268get());
    }

    public static DigitalZoomState proxyProvidesDigitalZoomState(PtzModule ptzModule, View view) {
        return (DigitalZoomState) Preconditions.checkNotNull(ptzModule.providesDigitalZoomState(view), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DigitalZoomState mo10268get() {
        return provideInstance(this.module, this.viewProvider);
    }
}
