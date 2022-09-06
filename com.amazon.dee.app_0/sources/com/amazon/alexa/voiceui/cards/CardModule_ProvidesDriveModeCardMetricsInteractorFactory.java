package com.amazon.alexa.voiceui.cards;

import com.amazon.alexa.voice.ui.driveMode.metrics.DriveModeCardMetricsInteractor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class CardModule_ProvidesDriveModeCardMetricsInteractorFactory implements Factory<DriveModeCardMetricsInteractor> {
    private final Provider<DriveModeCardMetricsInteractorImpl> driveModeCardMetricsInteractorImplProvider;
    private final CardModule module;

    public CardModule_ProvidesDriveModeCardMetricsInteractorFactory(CardModule cardModule, Provider<DriveModeCardMetricsInteractorImpl> provider) {
        this.module = cardModule;
        this.driveModeCardMetricsInteractorImplProvider = provider;
    }

    public static CardModule_ProvidesDriveModeCardMetricsInteractorFactory create(CardModule cardModule, Provider<DriveModeCardMetricsInteractorImpl> provider) {
        return new CardModule_ProvidesDriveModeCardMetricsInteractorFactory(cardModule, provider);
    }

    public static DriveModeCardMetricsInteractor provideInstance(CardModule cardModule, Provider<DriveModeCardMetricsInteractorImpl> provider) {
        return proxyProvidesDriveModeCardMetricsInteractor(cardModule, provider.mo10268get());
    }

    public static DriveModeCardMetricsInteractor proxyProvidesDriveModeCardMetricsInteractor(CardModule cardModule, Object obj) {
        return (DriveModeCardMetricsInteractor) Preconditions.checkNotNull(cardModule.providesDriveModeCardMetricsInteractor((DriveModeCardMetricsInteractorImpl) obj), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DriveModeCardMetricsInteractor mo10268get() {
        return provideInstance(this.module, this.driveModeCardMetricsInteractorImplProvider);
    }
}
