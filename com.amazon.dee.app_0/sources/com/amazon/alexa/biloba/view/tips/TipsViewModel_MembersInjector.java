package com.amazon.alexa.biloba.view.tips;

import com.amazon.alexa.biloba.storage.CardsStore;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class TipsViewModel_MembersInjector implements MembersInjector<TipsViewModel> {
    private final Provider<CardsStore> cardsStoreProvider;
    private final Provider<CareActorsStore> careActorsStoreProvider;

    public TipsViewModel_MembersInjector(Provider<CardsStore> provider, Provider<CareActorsStore> provider2) {
        this.cardsStoreProvider = provider;
        this.careActorsStoreProvider = provider2;
    }

    public static MembersInjector<TipsViewModel> create(Provider<CardsStore> provider, Provider<CareActorsStore> provider2) {
        return new TipsViewModel_MembersInjector(provider, provider2);
    }

    public static void injectCardsStore(TipsViewModel tipsViewModel, Lazy<CardsStore> lazy) {
        tipsViewModel.cardsStore = lazy;
    }

    public static void injectCareActorsStore(TipsViewModel tipsViewModel, Lazy<CareActorsStore> lazy) {
        tipsViewModel.careActorsStore = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(TipsViewModel tipsViewModel) {
        injectCardsStore(tipsViewModel, DoubleCheck.lazy(this.cardsStoreProvider));
        injectCareActorsStore(tipsViewModel, DoubleCheck.lazy(this.careActorsStoreProvider));
    }
}
