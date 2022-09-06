package com.amazon.alexa.voice.tta.search;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class SearchModule_ProvidesFrictionHelperFactory implements Factory<FrictionHelper> {
    private final SearchModule module;

    public SearchModule_ProvidesFrictionHelperFactory(SearchModule searchModule) {
        this.module = searchModule;
    }

    public static SearchModule_ProvidesFrictionHelperFactory create(SearchModule searchModule) {
        return new SearchModule_ProvidesFrictionHelperFactory(searchModule);
    }

    public static FrictionHelper provideInstance(SearchModule searchModule) {
        return proxyProvidesFrictionHelper(searchModule);
    }

    public static FrictionHelper proxyProvidesFrictionHelper(SearchModule searchModule) {
        return (FrictionHelper) Preconditions.checkNotNull(searchModule.providesFrictionHelper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FrictionHelper mo10268get() {
        return provideInstance(this.module);
    }
}
