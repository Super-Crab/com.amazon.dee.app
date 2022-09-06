package com.amazon.ptz.dagger;

import android.view.View;
import com.amazon.ptz.digital.DigitalZoomState;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class GestureHandlerModule_ProvideDigitalZoomStateFactory implements Factory<DigitalZoomState> {
    private final GestureHandlerModule module;
    private final Provider<View> viewProvider;

    public GestureHandlerModule_ProvideDigitalZoomStateFactory(GestureHandlerModule gestureHandlerModule, Provider<View> provider) {
        this.module = gestureHandlerModule;
        this.viewProvider = provider;
    }

    public static GestureHandlerModule_ProvideDigitalZoomStateFactory create(GestureHandlerModule gestureHandlerModule, Provider<View> provider) {
        return new GestureHandlerModule_ProvideDigitalZoomStateFactory(gestureHandlerModule, provider);
    }

    public static DigitalZoomState provideInstance(GestureHandlerModule gestureHandlerModule, Provider<View> provider) {
        return proxyProvideDigitalZoomState(gestureHandlerModule, provider.mo10268get());
    }

    public static DigitalZoomState proxyProvideDigitalZoomState(GestureHandlerModule gestureHandlerModule, View view) {
        return (DigitalZoomState) Preconditions.checkNotNull(gestureHandlerModule.provideDigitalZoomState(view), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DigitalZoomState mo10268get() {
        return provideInstance(this.module, this.viewProvider);
    }
}
