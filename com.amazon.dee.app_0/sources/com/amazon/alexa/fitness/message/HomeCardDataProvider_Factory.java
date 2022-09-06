package com.amazon.alexa.fitness.message;

import android.content.Context;
import com.amazon.alexa.fitness.accessory.FitnessAccessorySensorProvider;
import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator;
import com.amazon.alexa.fitness.api.afx.HomeCardViewModel;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.sdk.SessionManager;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class HomeCardDataProvider_Factory implements Factory<HomeCardDataProvider> {
    private final Provider<FitnessAccessorySensorProvider> accessorySensorProvider;
    private final Provider<ComponentRegistry> componentRegistryProvider;
    private final Provider<Context> contextProvider;
    private final Provider<FitnessHomeCardPublisher> fitnessHomeCardPublisherProvider;
    private final Provider<FitnessSessionOrchestrator> fitnessSessionOrchestratorProvider;
    private final Provider<HomeCardViewModel> homeCardViewModelProvider;
    private final Provider<ILog> logProvider;
    private final Provider<SessionManager> sessionManagerProvider;

    public HomeCardDataProvider_Factory(Provider<FitnessAccessorySensorProvider> provider, Provider<Context> provider2, Provider<FitnessHomeCardPublisher> provider3, Provider<HomeCardViewModel> provider4, Provider<SessionManager> provider5, Provider<FitnessSessionOrchestrator> provider6, Provider<ComponentRegistry> provider7, Provider<ILog> provider8) {
        this.accessorySensorProvider = provider;
        this.contextProvider = provider2;
        this.fitnessHomeCardPublisherProvider = provider3;
        this.homeCardViewModelProvider = provider4;
        this.sessionManagerProvider = provider5;
        this.fitnessSessionOrchestratorProvider = provider6;
        this.componentRegistryProvider = provider7;
        this.logProvider = provider8;
    }

    public static HomeCardDataProvider_Factory create(Provider<FitnessAccessorySensorProvider> provider, Provider<Context> provider2, Provider<FitnessHomeCardPublisher> provider3, Provider<HomeCardViewModel> provider4, Provider<SessionManager> provider5, Provider<FitnessSessionOrchestrator> provider6, Provider<ComponentRegistry> provider7, Provider<ILog> provider8) {
        return new HomeCardDataProvider_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static HomeCardDataProvider newHomeCardDataProvider(FitnessAccessorySensorProvider fitnessAccessorySensorProvider, Context context, FitnessHomeCardPublisher fitnessHomeCardPublisher, HomeCardViewModel homeCardViewModel, SessionManager sessionManager, FitnessSessionOrchestrator fitnessSessionOrchestrator, ComponentRegistry componentRegistry, ILog iLog) {
        return new HomeCardDataProvider(fitnessAccessorySensorProvider, context, fitnessHomeCardPublisher, homeCardViewModel, sessionManager, fitnessSessionOrchestrator, componentRegistry, iLog);
    }

    public static HomeCardDataProvider provideInstance(Provider<FitnessAccessorySensorProvider> provider, Provider<Context> provider2, Provider<FitnessHomeCardPublisher> provider3, Provider<HomeCardViewModel> provider4, Provider<SessionManager> provider5, Provider<FitnessSessionOrchestrator> provider6, Provider<ComponentRegistry> provider7, Provider<ILog> provider8) {
        return new HomeCardDataProvider(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HomeCardDataProvider mo10268get() {
        return provideInstance(this.accessorySensorProvider, this.contextProvider, this.fitnessHomeCardPublisherProvider, this.homeCardViewModelProvider, this.sessionManagerProvider, this.fitnessSessionOrchestratorProvider, this.componentRegistryProvider, this.logProvider);
    }
}
