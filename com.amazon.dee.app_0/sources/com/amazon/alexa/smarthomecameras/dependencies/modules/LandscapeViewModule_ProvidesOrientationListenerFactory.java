package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.smarthomecameras.view.ActivityOrientationListener;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class LandscapeViewModule_ProvidesOrientationListenerFactory implements Factory<ActivityOrientationListener> {
    private final LandscapeViewModule module;

    public LandscapeViewModule_ProvidesOrientationListenerFactory(LandscapeViewModule landscapeViewModule) {
        this.module = landscapeViewModule;
    }

    public static LandscapeViewModule_ProvidesOrientationListenerFactory create(LandscapeViewModule landscapeViewModule) {
        return new LandscapeViewModule_ProvidesOrientationListenerFactory(landscapeViewModule);
    }

    public static ActivityOrientationListener provideInstance(LandscapeViewModule landscapeViewModule) {
        return proxyProvidesOrientationListener(landscapeViewModule);
    }

    public static ActivityOrientationListener proxyProvidesOrientationListener(LandscapeViewModule landscapeViewModule) {
        return (ActivityOrientationListener) Preconditions.checkNotNull(landscapeViewModule.providesOrientationListener(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ActivityOrientationListener mo10268get() {
        return provideInstance(this.module);
    }
}
