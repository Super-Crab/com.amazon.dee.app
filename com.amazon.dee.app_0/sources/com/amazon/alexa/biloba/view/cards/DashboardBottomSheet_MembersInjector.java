package com.amazon.alexa.biloba.view.cards;

import com.amazon.alexa.biloba.storage.CardsStore;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class DashboardBottomSheet_MembersInjector implements MembersInjector<DashboardBottomSheet> {
    private final Provider<CardsStore> cardsStoreProvider;

    public DashboardBottomSheet_MembersInjector(Provider<CardsStore> provider) {
        this.cardsStoreProvider = provider;
    }

    public static MembersInjector<DashboardBottomSheet> create(Provider<CardsStore> provider) {
        return new DashboardBottomSheet_MembersInjector(provider);
    }

    public static void injectCardsStore(DashboardBottomSheet dashboardBottomSheet, Lazy<CardsStore> lazy) {
        dashboardBottomSheet.cardsStore = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DashboardBottomSheet dashboardBottomSheet) {
        injectCardsStore(dashboardBottomSheet, DoubleCheck.lazy(this.cardsStoreProvider));
    }
}
