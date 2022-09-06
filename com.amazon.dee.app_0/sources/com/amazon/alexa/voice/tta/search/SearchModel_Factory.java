package com.amazon.alexa.voice.tta.search;

import android.content.Context;
import com.amazon.alexa.voice.tta.permissions.PermissionsUtil;
import com.amazon.alexa.voice.tta.statemachine.SimbaMediator;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class SearchModel_Factory implements Factory<SearchModel> {
    private final Provider<Context> contextProvider;
    private final Provider<PermissionsUtil> permissionsUtilProvider;
    private final Provider<SimbaMediator> simbaMediatorProvider;

    public SearchModel_Factory(Provider<Context> provider, Provider<SimbaMediator> provider2, Provider<PermissionsUtil> provider3) {
        this.contextProvider = provider;
        this.simbaMediatorProvider = provider2;
        this.permissionsUtilProvider = provider3;
    }

    public static SearchModel_Factory create(Provider<Context> provider, Provider<SimbaMediator> provider2, Provider<PermissionsUtil> provider3) {
        return new SearchModel_Factory(provider, provider2, provider3);
    }

    public static SearchModel newSearchModel(Context context, SimbaMediator simbaMediator, PermissionsUtil permissionsUtil) {
        return new SearchModel(context, simbaMediator, permissionsUtil);
    }

    public static SearchModel provideInstance(Provider<Context> provider, Provider<SimbaMediator> provider2, Provider<PermissionsUtil> provider3) {
        return new SearchModel(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SearchModel mo10268get() {
        return provideInstance(this.contextProvider, this.simbaMediatorProvider, this.permissionsUtilProvider);
    }
}
