package com.amazon.alexa.voice.tta.search;

import android.app.Activity;
import com.amazon.alexa.voice.tta.permissions.PermissionsUtil;
import com.amazon.alexa.voice.tta.statemachine.SearchWorkflow;
import com.amazon.alexa.voice.tta.typing.PersistentStorage;
import com.amazon.alexa.voice.ui.tta.TtaNavigator;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class SearchInteractor_Factory implements Factory<SearchInteractor> {
    private final Provider<Activity> activityProvider;
    private final Provider<PermissionsUtil> permissionsUtilProvider;
    private final Provider<PersistentStorage> persistentStorageProvider;
    private final Provider<SearchModel> searchModelProvider;
    private final Provider<SearchWorkflow> searchWorkflowProvider;
    private final Provider<TtaNavigator> ttaNavigatorProvider;
    private final Provider<DefaultTtaResultHandler> ttaResultHandlerProvider;

    public SearchInteractor_Factory(Provider<Activity> provider, Provider<SearchModel> provider2, Provider<SearchWorkflow> provider3, Provider<DefaultTtaResultHandler> provider4, Provider<TtaNavigator> provider5, Provider<PersistentStorage> provider6, Provider<PermissionsUtil> provider7) {
        this.activityProvider = provider;
        this.searchModelProvider = provider2;
        this.searchWorkflowProvider = provider3;
        this.ttaResultHandlerProvider = provider4;
        this.ttaNavigatorProvider = provider5;
        this.persistentStorageProvider = provider6;
        this.permissionsUtilProvider = provider7;
    }

    public static SearchInteractor_Factory create(Provider<Activity> provider, Provider<SearchModel> provider2, Provider<SearchWorkflow> provider3, Provider<DefaultTtaResultHandler> provider4, Provider<TtaNavigator> provider5, Provider<PersistentStorage> provider6, Provider<PermissionsUtil> provider7) {
        return new SearchInteractor_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static SearchInteractor newSearchInteractor(Activity activity, SearchModel searchModel, SearchWorkflow searchWorkflow, DefaultTtaResultHandler defaultTtaResultHandler, TtaNavigator ttaNavigator, PersistentStorage persistentStorage, PermissionsUtil permissionsUtil) {
        return new SearchInteractor(activity, searchModel, searchWorkflow, defaultTtaResultHandler, ttaNavigator, persistentStorage, permissionsUtil);
    }

    public static SearchInteractor provideInstance(Provider<Activity> provider, Provider<SearchModel> provider2, Provider<SearchWorkflow> provider3, Provider<DefaultTtaResultHandler> provider4, Provider<TtaNavigator> provider5, Provider<PersistentStorage> provider6, Provider<PermissionsUtil> provider7) {
        return new SearchInteractor(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SearchInteractor mo10268get() {
        return provideInstance(this.activityProvider, this.searchModelProvider, this.searchWorkflowProvider, this.ttaResultHandlerProvider, this.ttaNavigatorProvider, this.persistentStorageProvider, this.permissionsUtilProvider);
    }
}
