package com.amazon.tarazed.ui.tv;

import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class MoveTVUIHandler_Factory implements Factory<MoveTVUIHandler> {
    private final Provider<TVUIManager> tvUIManagerProvider;

    public MoveTVUIHandler_Factory(Provider<TVUIManager> provider) {
        this.tvUIManagerProvider = provider;
    }

    public static MoveTVUIHandler_Factory create(Provider<TVUIManager> provider) {
        return new MoveTVUIHandler_Factory(provider);
    }

    public static MoveTVUIHandler newMoveTVUIHandler(TVUIManager tVUIManager) {
        return new MoveTVUIHandler(tVUIManager);
    }

    public static MoveTVUIHandler provideInstance(Provider<TVUIManager> provider) {
        return new MoveTVUIHandler(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MoveTVUIHandler mo10268get() {
        return provideInstance(this.tvUIManagerProvider);
    }
}
