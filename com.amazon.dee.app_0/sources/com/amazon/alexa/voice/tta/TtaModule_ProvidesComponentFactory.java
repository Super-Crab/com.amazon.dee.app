package com.amazon.alexa.voice.tta;

import com.amazon.alexa.voice.ui.suggestions.SuggestionsHandler;
import com.amazon.alexa.voice.ui.tta.TtaMessageHandler;
import com.amazon.alexa.voice.ui.tta.TtaNavigator;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEventSender;
import com.amazon.alexa.voice.ui.tta.search.TtaResultHandler;
import com.amazon.regulator.Component;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class TtaModule_ProvidesComponentFactory implements Factory<Component> {
    private final TtaModule module;
    private final Provider<SuggestionsHandler> suggestionsHandlerProvider;
    private final Provider<TtaEventSender> ttaEventSenderProvider;
    private final Provider<TtaMessageHandler> ttaMessageHandlerProvider;
    private final Provider<TtaNavigator> ttaNavigatorProvider;
    private final Provider<TtaResultHandler> ttaResultHandlerProvider;

    public TtaModule_ProvidesComponentFactory(TtaModule ttaModule, Provider<TtaMessageHandler> provider, Provider<TtaResultHandler> provider2, Provider<TtaNavigator> provider3, Provider<TtaEventSender> provider4, Provider<SuggestionsHandler> provider5) {
        this.module = ttaModule;
        this.ttaMessageHandlerProvider = provider;
        this.ttaResultHandlerProvider = provider2;
        this.ttaNavigatorProvider = provider3;
        this.ttaEventSenderProvider = provider4;
        this.suggestionsHandlerProvider = provider5;
    }

    public static TtaModule_ProvidesComponentFactory create(TtaModule ttaModule, Provider<TtaMessageHandler> provider, Provider<TtaResultHandler> provider2, Provider<TtaNavigator> provider3, Provider<TtaEventSender> provider4, Provider<SuggestionsHandler> provider5) {
        return new TtaModule_ProvidesComponentFactory(ttaModule, provider, provider2, provider3, provider4, provider5);
    }

    public static Component provideInstance(TtaModule ttaModule, Provider<TtaMessageHandler> provider, Provider<TtaResultHandler> provider2, Provider<TtaNavigator> provider3, Provider<TtaEventSender> provider4, Provider<SuggestionsHandler> provider5) {
        return proxyProvidesComponent(ttaModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    public static Component proxyProvidesComponent(TtaModule ttaModule, TtaMessageHandler ttaMessageHandler, TtaResultHandler ttaResultHandler, TtaNavigator ttaNavigator, TtaEventSender ttaEventSender, SuggestionsHandler suggestionsHandler) {
        return (Component) Preconditions.checkNotNull(ttaModule.providesComponent(ttaMessageHandler, ttaResultHandler, ttaNavigator, ttaEventSender, suggestionsHandler), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Component mo10268get() {
        return provideInstance(this.module, this.ttaMessageHandlerProvider, this.ttaResultHandlerProvider, this.ttaNavigatorProvider, this.ttaEventSenderProvider, this.suggestionsHandlerProvider);
    }
}
