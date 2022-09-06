package com.amazon.alexa.voice.tta.search;

import com.amazon.alexa.voice.ui.tta.search.TtaResultHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class SearchModule_ProvidesTtaResultHandlerFactory implements Factory<TtaResultHandler> {
    private final SearchModule module;
    private final Provider<DefaultTtaResultHandler> ttaResultHandlerProvider;

    public SearchModule_ProvidesTtaResultHandlerFactory(SearchModule searchModule, Provider<DefaultTtaResultHandler> provider) {
        this.module = searchModule;
        this.ttaResultHandlerProvider = provider;
    }

    public static SearchModule_ProvidesTtaResultHandlerFactory create(SearchModule searchModule, Provider<DefaultTtaResultHandler> provider) {
        return new SearchModule_ProvidesTtaResultHandlerFactory(searchModule, provider);
    }

    public static TtaResultHandler provideInstance(SearchModule searchModule, Provider<DefaultTtaResultHandler> provider) {
        return proxyProvidesTtaResultHandler(searchModule, provider.mo10268get());
    }

    public static TtaResultHandler proxyProvidesTtaResultHandler(SearchModule searchModule, DefaultTtaResultHandler defaultTtaResultHandler) {
        return (TtaResultHandler) Preconditions.checkNotNull(searchModule.providesTtaResultHandler(defaultTtaResultHandler), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TtaResultHandler mo10268get() {
        return provideInstance(this.module, this.ttaResultHandlerProvider);
    }
}
