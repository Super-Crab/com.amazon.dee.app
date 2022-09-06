package com.amazon.alexa.smarthomecameras.dependencies.modules;

import android.view.View;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class PtzModule_ProvidesPtzViewFactory implements Factory<View> {
    private final PtzModule module;

    public PtzModule_ProvidesPtzViewFactory(PtzModule ptzModule) {
        this.module = ptzModule;
    }

    public static PtzModule_ProvidesPtzViewFactory create(PtzModule ptzModule) {
        return new PtzModule_ProvidesPtzViewFactory(ptzModule);
    }

    public static View provideInstance(PtzModule ptzModule) {
        return proxyProvidesPtzView(ptzModule);
    }

    public static View proxyProvidesPtzView(PtzModule ptzModule) {
        return (View) Preconditions.checkNotNull(ptzModule.providesPtzView(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public View mo10268get() {
        return provideInstance(this.module);
    }
}
