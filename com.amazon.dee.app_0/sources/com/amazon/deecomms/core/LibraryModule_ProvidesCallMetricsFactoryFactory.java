package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.calling.controller.CallMetricsFactory;
import com.amazon.deecomms.calling.util.CallHistoryHelper;
import com.amazon.deecomms.common.ApplicationManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesCallMetricsFactoryFactory implements Factory<CallMetricsFactory> {
    private final Provider<ApplicationManager> applicationManagerProvider;
    private final Provider<CallHistoryHelper> callHistoryHelperProvider;
    private final Provider<Context> contextProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesCallMetricsFactoryFactory(LibraryModule libraryModule, Provider<CallHistoryHelper> provider, Provider<ApplicationManager> provider2, Provider<Context> provider3) {
        this.module = libraryModule;
        this.callHistoryHelperProvider = provider;
        this.applicationManagerProvider = provider2;
        this.contextProvider = provider3;
    }

    public static LibraryModule_ProvidesCallMetricsFactoryFactory create(LibraryModule libraryModule, Provider<CallHistoryHelper> provider, Provider<ApplicationManager> provider2, Provider<Context> provider3) {
        return new LibraryModule_ProvidesCallMetricsFactoryFactory(libraryModule, provider, provider2, provider3);
    }

    public static CallMetricsFactory provideInstance(LibraryModule libraryModule, Provider<CallHistoryHelper> provider, Provider<ApplicationManager> provider2, Provider<Context> provider3) {
        return (CallMetricsFactory) Preconditions.checkNotNull(libraryModule.providesCallMetricsFactory(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CallMetricsFactory proxyProvidesCallMetricsFactory(LibraryModule libraryModule, CallHistoryHelper callHistoryHelper, ApplicationManager applicationManager, Context context) {
        return (CallMetricsFactory) Preconditions.checkNotNull(libraryModule.providesCallMetricsFactory(callHistoryHelper, applicationManager, context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CallMetricsFactory mo10268get() {
        return provideInstance(this.module, this.callHistoryHelperProvider, this.applicationManagerProvider, this.contextProvider);
    }
}
