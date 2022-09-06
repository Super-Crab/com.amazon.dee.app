package com.amazon.alexa.biloba.view.tips;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.alexa.biloba.dependency.BilobaDependencies;
import com.amazon.alexa.biloba.storage.CardsStore;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.view.BilobaViewModel;
import com.amazon.alexa.biloba.view.cards.TipsCard;
import com.amazon.alexa.biloba.view.common.recycler.BaseRecyclerItem;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class TipsViewModel implements BilobaViewModel {
    private static final String TAG = "com.amazon.alexa.biloba.view.tips.TipsViewModel";
    @Inject
    Lazy<CardsStore> cardsStore;
    @Inject
    Lazy<CareActorsStore> careActorsStore;
    private final MutableLiveData<List<TipsCard>> liveDataTips;

    public TipsViewModel() {
        BilobaDependencies.inject(this);
        this.liveDataTips = new MutableLiveData<>(new ArrayList());
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void create(@Nullable Bundle bundle) {
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void destroy() {
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public LiveData<Throwable> getError() {
        return this.cardsStore.mo358get().getError();
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public String getErrorViewMetricName() {
        return "TipsView.Error";
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public LiveData<Boolean> getIsLoading() {
        return this.cardsStore.mo358get().getIsLoading();
    }

    public LiveData<List<BaseRecyclerItem>> getLiveDataCards() {
        return this.cardsStore.mo358get().getLiveTipsCards();
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void sendRequest() {
        this.cardsStore.mo358get().fetchTipCards(this.careActorsStore.mo358get().getCurrentGroupId());
    }

    @VisibleForTesting
    TipsViewModel(Lazy<CardsStore> lazy, Lazy<CareActorsStore> lazy2) {
        this.cardsStore = lazy;
        this.careActorsStore = lazy2;
        this.liveDataTips = new MutableLiveData<>(new ArrayList());
    }
}
