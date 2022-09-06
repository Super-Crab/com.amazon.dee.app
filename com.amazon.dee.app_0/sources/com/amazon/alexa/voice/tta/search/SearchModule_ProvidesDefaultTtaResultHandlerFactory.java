package com.amazon.alexa.voice.tta.search;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class SearchModule_ProvidesDefaultTtaResultHandlerFactory implements Factory<DefaultTtaResultHandler> {
    private final SearchModule module;

    public SearchModule_ProvidesDefaultTtaResultHandlerFactory(SearchModule searchModule) {
        this.module = searchModule;
    }

    public static SearchModule_ProvidesDefaultTtaResultHandlerFactory create(SearchModule searchModule) {
        return new SearchModule_ProvidesDefaultTtaResultHandlerFactory(searchModule);
    }

    public static DefaultTtaResultHandler provideInstance(SearchModule searchModule) {
        return proxyProvidesDefaultTtaResultHandler(searchModule);
    }

    public static DefaultTtaResultHandler proxyProvidesDefaultTtaResultHandler(SearchModule searchModule) {
        return (DefaultTtaResultHandler) Preconditions.checkNotNull(searchModule.providesDefaultTtaResultHandler(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultTtaResultHandler mo10268get() {
        return provideInstance(this.module);
    }
}
