package com.amazon.alexa.voice.tta.statemachine;

import com.amazon.alexa.voice.tta.sdk.AlexaClientSdkApis;
import com.amazon.alexa.voice.tta.sdk.AlexaPlayerInfoCardTracker;
import com.amazon.alexa.voice.tta.sdk.AlexaRenderedCardReceiver;
import com.amazon.alexa.voice.tta.sdk.AlexaStateTracker;
import com.amazon.alexa.voice.tta.sdk.AlexaTextResponseTracker;
import com.amazon.alexa.voice.tta.typing.TtaMessageSanitizer;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEventSender;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class StateMachineModule_ProvidesAlexaMediatorFactory implements Factory<AlexaMediator> {
    private final Provider<AlexaClientSdkApis> alexaClientSdkApisProvider;
    private final Provider<AlexaPlayerInfoCardTracker> alexaPlayerInfoCardTrackerProvider;
    private final Provider<AlexaRenderedCardReceiver> alexaRenderedCardReceiverProvider;
    private final Provider<AlexaStateTracker> alexaStateTrackerProvider;
    private final Provider<AlexaTextResponseTracker> alexaTextResponseTrackerProvider;
    private final Provider<TtaMessageSanitizer> messageSanitizerProvider;
    private final StateMachineModule module;
    private final Provider<TtaEventSender> ttaEventSenderProvider;

    public StateMachineModule_ProvidesAlexaMediatorFactory(StateMachineModule stateMachineModule, Provider<AlexaClientSdkApis> provider, Provider<AlexaStateTracker> provider2, Provider<AlexaTextResponseTracker> provider3, Provider<AlexaRenderedCardReceiver> provider4, Provider<AlexaPlayerInfoCardTracker> provider5, Provider<TtaMessageSanitizer> provider6, Provider<TtaEventSender> provider7) {
        this.module = stateMachineModule;
        this.alexaClientSdkApisProvider = provider;
        this.alexaStateTrackerProvider = provider2;
        this.alexaTextResponseTrackerProvider = provider3;
        this.alexaRenderedCardReceiverProvider = provider4;
        this.alexaPlayerInfoCardTrackerProvider = provider5;
        this.messageSanitizerProvider = provider6;
        this.ttaEventSenderProvider = provider7;
    }

    public static StateMachineModule_ProvidesAlexaMediatorFactory create(StateMachineModule stateMachineModule, Provider<AlexaClientSdkApis> provider, Provider<AlexaStateTracker> provider2, Provider<AlexaTextResponseTracker> provider3, Provider<AlexaRenderedCardReceiver> provider4, Provider<AlexaPlayerInfoCardTracker> provider5, Provider<TtaMessageSanitizer> provider6, Provider<TtaEventSender> provider7) {
        return new StateMachineModule_ProvidesAlexaMediatorFactory(stateMachineModule, provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static AlexaMediator provideInstance(StateMachineModule stateMachineModule, Provider<AlexaClientSdkApis> provider, Provider<AlexaStateTracker> provider2, Provider<AlexaTextResponseTracker> provider3, Provider<AlexaRenderedCardReceiver> provider4, Provider<AlexaPlayerInfoCardTracker> provider5, Provider<TtaMessageSanitizer> provider6, Provider<TtaEventSender> provider7) {
        return proxyProvidesAlexaMediator(stateMachineModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get());
    }

    public static AlexaMediator proxyProvidesAlexaMediator(StateMachineModule stateMachineModule, AlexaClientSdkApis alexaClientSdkApis, AlexaStateTracker alexaStateTracker, AlexaTextResponseTracker alexaTextResponseTracker, AlexaRenderedCardReceiver alexaRenderedCardReceiver, AlexaPlayerInfoCardTracker alexaPlayerInfoCardTracker, TtaMessageSanitizer ttaMessageSanitizer, TtaEventSender ttaEventSender) {
        return (AlexaMediator) Preconditions.checkNotNull(stateMachineModule.providesAlexaMediator(alexaClientSdkApis, alexaStateTracker, alexaTextResponseTracker, alexaRenderedCardReceiver, alexaPlayerInfoCardTracker, ttaMessageSanitizer, ttaEventSender), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaMediator mo10268get() {
        return provideInstance(this.module, this.alexaClientSdkApisProvider, this.alexaStateTrackerProvider, this.alexaTextResponseTrackerProvider, this.alexaRenderedCardReceiverProvider, this.alexaPlayerInfoCardTrackerProvider, this.messageSanitizerProvider, this.ttaEventSenderProvider);
    }
}
