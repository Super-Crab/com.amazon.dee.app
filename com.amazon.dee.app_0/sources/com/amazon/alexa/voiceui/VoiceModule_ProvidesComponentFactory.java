package com.amazon.alexa.voiceui;

import com.amazon.alexa.voice.ui.driveMode.metrics.DriveModeCardMetricsInteractor;
import com.amazon.alexa.voice.ui.locale.GetLocaleAuthority;
import com.amazon.alexa.voice.ui.marketplace.MarketplaceAuthority;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.permissions.AndroidPermissionsChecker;
import com.amazon.alexa.voice.ui.permissions.AndroidPermissionsRequester;
import com.amazon.alexa.voice.ui.routing.Navigator;
import com.amazon.alexa.voice.ui.speech.AttentionSystemContract;
import com.amazon.alexa.voice.ui.speech.SpeechRecognizer;
import com.amazon.alexa.voice.ui.tta.TypingPrimerDisplayer;
import com.amazon.alexa.voice.ui.window.WindowInteractor;
import com.amazon.regulator.Component;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceModule_ProvidesComponentFactory implements Factory<Component> {
    private final Provider<AttentionSystemContract> attentionSystemContractProvider;
    private final Provider<CardMetricsInteractor> cardMetricsInteractorProvider;
    private final Provider<DriveModeCardMetricsInteractor> driveModeCardMetricsInteractorProvider;
    private final Provider<GetLocaleAuthority> getLocaleAuthorityProvider;
    private final Provider<MarketplaceAuthority> marketplaceAuthorityProvider;
    private final VoiceModule module;
    private final Provider<Navigator> navigatorProvider;
    private final Provider<AndroidPermissionsChecker> permissionsCheckerProvider;
    private final Provider<AndroidPermissionsRequester> permissionsRequesterProvider;
    private final Provider<SpeechRecognizer> speechRecognizerProvider;
    private final Provider<TypingPrimerDisplayer> typingPrimerDisplayerProvider;
    private final Provider<WindowInteractor> windowInteractorProvider;

    public VoiceModule_ProvidesComponentFactory(VoiceModule voiceModule, Provider<SpeechRecognizer> provider, Provider<GetLocaleAuthority> provider2, Provider<MarketplaceAuthority> provider3, Provider<CardMetricsInteractor> provider4, Provider<DriveModeCardMetricsInteractor> provider5, Provider<WindowInteractor> provider6, Provider<AttentionSystemContract> provider7, Provider<AndroidPermissionsRequester> provider8, Provider<AndroidPermissionsChecker> provider9, Provider<Navigator> provider10, Provider<TypingPrimerDisplayer> provider11) {
        this.module = voiceModule;
        this.speechRecognizerProvider = provider;
        this.getLocaleAuthorityProvider = provider2;
        this.marketplaceAuthorityProvider = provider3;
        this.cardMetricsInteractorProvider = provider4;
        this.driveModeCardMetricsInteractorProvider = provider5;
        this.windowInteractorProvider = provider6;
        this.attentionSystemContractProvider = provider7;
        this.permissionsRequesterProvider = provider8;
        this.permissionsCheckerProvider = provider9;
        this.navigatorProvider = provider10;
        this.typingPrimerDisplayerProvider = provider11;
    }

    public static VoiceModule_ProvidesComponentFactory create(VoiceModule voiceModule, Provider<SpeechRecognizer> provider, Provider<GetLocaleAuthority> provider2, Provider<MarketplaceAuthority> provider3, Provider<CardMetricsInteractor> provider4, Provider<DriveModeCardMetricsInteractor> provider5, Provider<WindowInteractor> provider6, Provider<AttentionSystemContract> provider7, Provider<AndroidPermissionsRequester> provider8, Provider<AndroidPermissionsChecker> provider9, Provider<Navigator> provider10, Provider<TypingPrimerDisplayer> provider11) {
        return new VoiceModule_ProvidesComponentFactory(voiceModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
    }

    public static Component provideInstance(VoiceModule voiceModule, Provider<SpeechRecognizer> provider, Provider<GetLocaleAuthority> provider2, Provider<MarketplaceAuthority> provider3, Provider<CardMetricsInteractor> provider4, Provider<DriveModeCardMetricsInteractor> provider5, Provider<WindowInteractor> provider6, Provider<AttentionSystemContract> provider7, Provider<AndroidPermissionsRequester> provider8, Provider<AndroidPermissionsChecker> provider9, Provider<Navigator> provider10, Provider<TypingPrimerDisplayer> provider11) {
        return proxyProvidesComponent(voiceModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get(), provider9.mo10268get(), provider10.mo10268get(), provider11.mo10268get());
    }

    public static Component proxyProvidesComponent(VoiceModule voiceModule, SpeechRecognizer speechRecognizer, GetLocaleAuthority getLocaleAuthority, MarketplaceAuthority marketplaceAuthority, CardMetricsInteractor cardMetricsInteractor, DriveModeCardMetricsInteractor driveModeCardMetricsInteractor, WindowInteractor windowInteractor, AttentionSystemContract attentionSystemContract, AndroidPermissionsRequester androidPermissionsRequester, AndroidPermissionsChecker androidPermissionsChecker, Navigator navigator, TypingPrimerDisplayer typingPrimerDisplayer) {
        return (Component) Preconditions.checkNotNull(voiceModule.providesComponent(speechRecognizer, getLocaleAuthority, marketplaceAuthority, cardMetricsInteractor, driveModeCardMetricsInteractor, windowInteractor, attentionSystemContract, androidPermissionsRequester, androidPermissionsChecker, navigator, typingPrimerDisplayer), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Component mo10268get() {
        return provideInstance(this.module, this.speechRecognizerProvider, this.getLocaleAuthorityProvider, this.marketplaceAuthorityProvider, this.cardMetricsInteractorProvider, this.driveModeCardMetricsInteractorProvider, this.windowInteractorProvider, this.attentionSystemContractProvider, this.permissionsRequesterProvider, this.permissionsCheckerProvider, this.navigatorProvider, this.typingPrimerDisplayerProvider);
    }
}
