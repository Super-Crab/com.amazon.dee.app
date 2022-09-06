package com.amazon.alexa.voiceui.cards;

import com.amazon.alexa.voice.ui.provider.CardFactoryWithMode;
import com.amazon.alexa.voice.ui.window.WindowInteractor;
import com.amazon.alexa.voiceui.RouterDelegate;
import com.amazon.alexa.voiceui.driveMode.DriveModeModel;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class CardView_Factory implements Factory<CardView> {
    private final Provider<CardFactoryWithMode> cardFactoryProvider;
    private final Provider<RouterDelegate> cardRouterProvider;
    private final Provider<DriveModeModel> driveModeModelProvider;
    private final Provider<WindowInteractor> windowInteractorProvider;

    public CardView_Factory(Provider<RouterDelegate> provider, Provider<CardFactoryWithMode> provider2, Provider<DriveModeModel> provider3, Provider<WindowInteractor> provider4) {
        this.cardRouterProvider = provider;
        this.cardFactoryProvider = provider2;
        this.driveModeModelProvider = provider3;
        this.windowInteractorProvider = provider4;
    }

    public static CardView_Factory create(Provider<RouterDelegate> provider, Provider<CardFactoryWithMode> provider2, Provider<DriveModeModel> provider3, Provider<WindowInteractor> provider4) {
        return new CardView_Factory(provider, provider2, provider3, provider4);
    }

    public static CardView newCardView(RouterDelegate routerDelegate, CardFactoryWithMode cardFactoryWithMode, DriveModeModel driveModeModel, WindowInteractor windowInteractor) {
        return new CardView(routerDelegate, cardFactoryWithMode, driveModeModel, windowInteractor);
    }

    public static CardView provideInstance(Provider<RouterDelegate> provider, Provider<CardFactoryWithMode> provider2, Provider<DriveModeModel> provider3, Provider<WindowInteractor> provider4) {
        return new CardView(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CardView mo10268get() {
        return provideInstance(this.cardRouterProvider, this.cardFactoryProvider, this.driveModeModelProvider, this.windowInteractorProvider);
    }
}
