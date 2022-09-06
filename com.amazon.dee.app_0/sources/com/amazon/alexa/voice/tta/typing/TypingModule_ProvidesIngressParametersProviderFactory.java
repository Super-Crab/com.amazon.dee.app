package com.amazon.alexa.voice.tta.typing;

import com.amazon.alexa.voice.tta.metrics.EventClock;
import com.amazon.alexa.voice.tta.typing.IngressParameters;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class TypingModule_ProvidesIngressParametersProviderFactory implements Factory<IngressParameters.Provider> {
    private final Provider<EventClock> eventClockProvider;
    private final TypingModule module;

    public TypingModule_ProvidesIngressParametersProviderFactory(TypingModule typingModule, Provider<EventClock> provider) {
        this.module = typingModule;
        this.eventClockProvider = provider;
    }

    public static TypingModule_ProvidesIngressParametersProviderFactory create(TypingModule typingModule, Provider<EventClock> provider) {
        return new TypingModule_ProvidesIngressParametersProviderFactory(typingModule, provider);
    }

    public static IngressParameters.Provider provideInstance(TypingModule typingModule, Provider<EventClock> provider) {
        return proxyProvidesIngressParametersProvider(typingModule, provider.mo10268get());
    }

    public static IngressParameters.Provider proxyProvidesIngressParametersProvider(TypingModule typingModule, EventClock eventClock) {
        return (IngressParameters.Provider) Preconditions.checkNotNull(typingModule.providesIngressParametersProvider(eventClock), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public IngressParameters.Provider mo10268get() {
        return provideInstance(this.module, this.eventClockProvider);
    }
}
