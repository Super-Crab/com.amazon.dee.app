package com.amazon.alexa.voiceui.cards;

import com.amazon.alexa.voiceui.BackButtonPressHandler;
import com.amazon.alexa.voiceui.SaveInstanceStateHandler;
import com.amazon.alexa.voiceui.events.UiEventReporter;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class CardInteractor_Factory implements Factory<CardInteractor> {
    private final Provider<BackButtonPressHandler> backButtonPressHandlerProvider;
    private final Provider<CardModel> cardModelProvider;
    private final Provider<SaveInstanceStateHandler> saveInstanceStateHandlerProvider;
    private final Provider<UiEventReporter> uiEventReporterProvider;

    public CardInteractor_Factory(Provider<CardModel> provider, Provider<UiEventReporter> provider2, Provider<BackButtonPressHandler> provider3, Provider<SaveInstanceStateHandler> provider4) {
        this.cardModelProvider = provider;
        this.uiEventReporterProvider = provider2;
        this.backButtonPressHandlerProvider = provider3;
        this.saveInstanceStateHandlerProvider = provider4;
    }

    public static CardInteractor_Factory create(Provider<CardModel> provider, Provider<UiEventReporter> provider2, Provider<BackButtonPressHandler> provider3, Provider<SaveInstanceStateHandler> provider4) {
        return new CardInteractor_Factory(provider, provider2, provider3, provider4);
    }

    public static CardInteractor newCardInteractor(Object obj, UiEventReporter uiEventReporter, BackButtonPressHandler backButtonPressHandler, SaveInstanceStateHandler saveInstanceStateHandler) {
        return new CardInteractor((CardModel) obj, uiEventReporter, backButtonPressHandler, saveInstanceStateHandler);
    }

    public static CardInteractor provideInstance(Provider<CardModel> provider, Provider<UiEventReporter> provider2, Provider<BackButtonPressHandler> provider3, Provider<SaveInstanceStateHandler> provider4) {
        return new CardInteractor(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CardInteractor mo10268get() {
        return provideInstance(this.cardModelProvider, this.uiEventReporterProvider, this.backButtonPressHandlerProvider, this.saveInstanceStateHandlerProvider);
    }
}
