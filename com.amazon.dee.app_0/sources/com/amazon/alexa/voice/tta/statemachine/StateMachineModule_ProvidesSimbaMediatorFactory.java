package com.amazon.alexa.voice.tta.statemachine;

import android.content.Context;
import com.amazon.alexa.voice.tta.search.FrictionHelper;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEventSender;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class StateMachineModule_ProvidesSimbaMediatorFactory implements Factory<SimbaMediator> {
    private final Provider<AlexaMediator> alexaMediatorProvider;
    private final Provider<Context> contextProvider;
    private final Provider<FrictionHelper> frictionHelperProvider;
    private final StateMachineModule module;
    private final Provider<TtaEventSender> ttaEventSenderProvider;

    public StateMachineModule_ProvidesSimbaMediatorFactory(StateMachineModule stateMachineModule, Provider<Context> provider, Provider<AlexaMediator> provider2, Provider<FrictionHelper> provider3, Provider<TtaEventSender> provider4) {
        this.module = stateMachineModule;
        this.contextProvider = provider;
        this.alexaMediatorProvider = provider2;
        this.frictionHelperProvider = provider3;
        this.ttaEventSenderProvider = provider4;
    }

    public static StateMachineModule_ProvidesSimbaMediatorFactory create(StateMachineModule stateMachineModule, Provider<Context> provider, Provider<AlexaMediator> provider2, Provider<FrictionHelper> provider3, Provider<TtaEventSender> provider4) {
        return new StateMachineModule_ProvidesSimbaMediatorFactory(stateMachineModule, provider, provider2, provider3, provider4);
    }

    public static SimbaMediator provideInstance(StateMachineModule stateMachineModule, Provider<Context> provider, Provider<AlexaMediator> provider2, Provider<FrictionHelper> provider3, Provider<TtaEventSender> provider4) {
        return proxyProvidesSimbaMediator(stateMachineModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static SimbaMediator proxyProvidesSimbaMediator(StateMachineModule stateMachineModule, Context context, AlexaMediator alexaMediator, FrictionHelper frictionHelper, TtaEventSender ttaEventSender) {
        return (SimbaMediator) Preconditions.checkNotNull(stateMachineModule.providesSimbaMediator(context, alexaMediator, frictionHelper, ttaEventSender), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SimbaMediator mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.alexaMediatorProvider, this.frictionHelperProvider, this.ttaEventSenderProvider);
    }
}
