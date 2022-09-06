package com.amazon.alexa.voice.tta.statemachine;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class StateMachineModule_ProvidesSearchWorkflowFactory implements Factory<SearchWorkflow> {
    private final Provider<AlexaMediator> alexaMediatorProvider;
    private final StateMachineModule module;
    private final Provider<SimbaMediator> simbaMediatorProvider;

    public StateMachineModule_ProvidesSearchWorkflowFactory(StateMachineModule stateMachineModule, Provider<AlexaMediator> provider, Provider<SimbaMediator> provider2) {
        this.module = stateMachineModule;
        this.alexaMediatorProvider = provider;
        this.simbaMediatorProvider = provider2;
    }

    public static StateMachineModule_ProvidesSearchWorkflowFactory create(StateMachineModule stateMachineModule, Provider<AlexaMediator> provider, Provider<SimbaMediator> provider2) {
        return new StateMachineModule_ProvidesSearchWorkflowFactory(stateMachineModule, provider, provider2);
    }

    public static SearchWorkflow provideInstance(StateMachineModule stateMachineModule, Provider<AlexaMediator> provider, Provider<SimbaMediator> provider2) {
        return proxyProvidesSearchWorkflow(stateMachineModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static SearchWorkflow proxyProvidesSearchWorkflow(StateMachineModule stateMachineModule, AlexaMediator alexaMediator, SimbaMediator simbaMediator) {
        return (SearchWorkflow) Preconditions.checkNotNull(stateMachineModule.providesSearchWorkflow(alexaMediator, simbaMediator), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SearchWorkflow mo10268get() {
        return provideInstance(this.module, this.alexaMediatorProvider, this.simbaMediatorProvider);
    }
}
