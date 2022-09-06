package com.amazon.alexa.biloba.storage;

import android.content.SharedPreferences;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.alexa.biloba.dependency.BilobaScope;
import com.amazon.alexa.biloba.generated.models.Card;
import com.amazon.alexa.biloba.generated.models.CardsResponse;
import com.amazon.alexa.biloba.generated.network.api.CardApi;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.view.cards.CardData;
import com.amazon.alexa.biloba.view.cards.DashboardCard;
import com.amazon.alexa.biloba.view.cards.NotificationCard;
import com.amazon.alexa.biloba.view.cards.TipsCard;
import com.amazon.alexa.biloba.view.common.recycler.BaseRecyclerItem;
import com.amazon.alexa.biloba.view.dashboard.CardTransformer;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.google.common.collect.ImmutableList;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.functions.Action1;
@Singleton
@BilobaScope
/* loaded from: classes6.dex */
public class CardsStore extends DataStore {
    protected static final String DISMISSED_CARDS_KEY = "biloba.dismissed.cards";
    private static final String TAG = "CardsStore";
    @Inject
    Lazy<BilobaMetricsService> bilobaMetricsService;
    @Inject
    Lazy<CardApi> cardApi;
    @Inject
    Lazy<CardTransformer> cardTransformer;
    @Inject
    Lazy<CrashMetadata> crashMetadata;
    @Inject
    Lazy<CrashReporter> crashReporter;
    private final MutableLiveData<List<BaseRecyclerItem>> liveDashboardCards;
    private final MutableLiveData<List<BaseRecyclerItem>> liveTipsCards;

    @Inject
    public CardsStore() {
        this.liveDashboardCards = new MutableLiveData<>();
        this.liveTipsCards = new MutableLiveData<>();
    }

    private List<BaseRecyclerItem> convertToDashboardCards(List<Card> list, SharedPreferences sharedPreferences, String str) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (Card card : list) {
                DashboardCard map = this.cardTransformer.mo358get().map(card, sharedPreferences, str);
                if (map == null) {
                    LogUtils.e(TAG, String.format("Mapped card was null for %s", card));
                } else if (card.getCategory().equals(Card.CategoryEnum.FEATURE_DISCOVERY_TIP)) {
                    arrayList.add((TipsCard) map);
                } else {
                    arrayList.add((NotificationCard) map);
                }
            }
        }
        return arrayList;
    }

    private List<BaseRecyclerItem> filterDismissedCards(List<BaseRecyclerItem> list, SharedPreferences sharedPreferences) {
        Set<String> stringSet = sharedPreferences.getStringSet(DISMISSED_CARDS_KEY, new HashSet());
        ArrayList arrayList = new ArrayList();
        for (BaseRecyclerItem baseRecyclerItem : list) {
            if (!stringSet.contains(getIdFromBaseCard(baseRecyclerItem))) {
                arrayList.add(baseRecyclerItem);
            }
        }
        return arrayList;
    }

    private String getIdFromBaseCard(BaseRecyclerItem baseRecyclerItem) {
        CardData cardData;
        return (baseRecyclerItem == null || (cardData = (CardData) baseRecyclerItem.getData()) == null || cardData.getCard() == null) ? "" : cardData.getCard().getId();
    }

    public void dismissCard(String str, SharedPreferences sharedPreferences) {
        Card card;
        HashSet hashSet = new HashSet(sharedPreferences.getStringSet(DISMISSED_CARDS_KEY, new HashSet()));
        hashSet.add(str);
        sharedPreferences.edit().putStringSet(DISMISSED_CARDS_KEY, hashSet).apply();
        ArrayList arrayList = new ArrayList(this.liveDashboardCards.getValue());
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) != null && ((BaseRecyclerItem) arrayList.get(i)).getData() != null && (card = ((CardData) ((BaseRecyclerItem) arrayList.get(i)).getData()).getCard()) != null && !card.getId().equals(str)) {
                arrayList2.add(arrayList.get(i));
            }
        }
        this.liveDashboardCards.setValue(arrayList2);
    }

    public void fetchDashboardCards(String str, final SharedPreferences sharedPreferences) {
        final MobilyticsMetricsTimer startTimer = this.bilobaMetricsService.mo358get().startTimer("getDashboardCardsApi.Time");
        setIsLoading(true);
        this.cardApi.mo358get().getCardsObservable(str, null).subscribe(new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$CardsStore$lCQ_3k_kcTHxWtmRJS_3xyviGX8
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CardsStore.this.lambda$fetchDashboardCards$2$CardsStore(sharedPreferences, startTimer, (CardsResponse) obj);
            }
        }, new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$CardsStore$lBcbnldlWQNnPJ6ccpzyIMdV2i0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CardsStore.this.lambda$fetchDashboardCards$3$CardsStore((Throwable) obj);
            }
        });
    }

    public void fetchTipCards(String str) {
        final MobilyticsMetricsTimer startTimer = this.bilobaMetricsService.mo358get().startTimer("getTipCardsApi.Time");
        setIsLoading(true);
        this.cardApi.mo358get().getCardsObservable(str, ImmutableList.of(Card.CategoryEnum.FEATURE_DISCOVERY_TIP.getValue())).subscribe(new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$CardsStore$XdexM3GNbFeDdg3VpMcL5Jb-MRg
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CardsStore.this.lambda$fetchTipCards$0$CardsStore(startTimer, (CardsResponse) obj);
            }
        }, new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$CardsStore$MzKmBtJ_1cM2uNBzmKQLgrIeXqE
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CardsStore.this.lambda$fetchTipCards$1$CardsStore((Throwable) obj);
            }
        });
    }

    public LiveData<List<BaseRecyclerItem>> getLiveDashboardCards() {
        return this.liveDashboardCards;
    }

    public LiveData<List<BaseRecyclerItem>> getLiveTipsCards() {
        return this.liveTipsCards;
    }

    public /* synthetic */ void lambda$fetchDashboardCards$2$CardsStore(SharedPreferences sharedPreferences, MobilyticsMetricsTimer mobilyticsMetricsTimer, CardsResponse cardsResponse) {
        this.crashMetadata.mo358get().put("get_dashboard_cards_success", true);
        this.liveDashboardCards.setValue(filterDismissedCards(convertToDashboardCards(cardsResponse.getCards(), sharedPreferences, MetricsConstants.UserInteractionMetrics.DASHBOARD_VIEW), sharedPreferences));
        setIsLoading(false);
        postError(null);
        this.bilobaMetricsService.mo358get().finishTimer(mobilyticsMetricsTimer);
        this.bilobaMetricsService.mo358get().recordOccurrence("getDashboardCardsApi.Success", true);
    }

    public /* synthetic */ void lambda$fetchDashboardCards$3$CardsStore(Throwable th) {
        LogUtils.e(TAG, "Error when getting cards", th);
        this.crashMetadata.mo358get().put("get_dashboard_cards_success", false);
        this.crashReporter.mo358get().reportNonFatal(th);
        setIsLoading(false);
        postError(th);
        this.bilobaMetricsService.mo358get().recordOccurrence("getDashboardCardsApi.Success", false);
    }

    public /* synthetic */ void lambda$fetchTipCards$0$CardsStore(MobilyticsMetricsTimer mobilyticsMetricsTimer, CardsResponse cardsResponse) {
        this.crashMetadata.mo358get().put("get_tip_cards_success", true);
        List<BaseRecyclerItem> convertToDashboardCards = convertToDashboardCards(cardsResponse.getCards(), null, MetricsConstants.UserInteractionMetrics.TIPS_VIEW);
        ArrayList arrayList = new ArrayList();
        for (BaseRecyclerItem baseRecyclerItem : convertToDashboardCards) {
            arrayList.add(baseRecyclerItem);
        }
        this.liveTipsCards.setValue(arrayList);
        setIsLoading(false);
        postError(null);
        this.bilobaMetricsService.mo358get().finishTimer(mobilyticsMetricsTimer);
        this.bilobaMetricsService.mo358get().recordOccurrence("getTipCardsApi.Success", true);
    }

    public /* synthetic */ void lambda$fetchTipCards$1$CardsStore(Throwable th) {
        LogUtils.e(TAG, "Error when getting cards", th);
        this.crashMetadata.mo358get().put("get_tip_cards_success", false);
        this.crashReporter.mo358get().reportNonFatal(th);
        setIsLoading(false);
        postError(th);
        this.bilobaMetricsService.mo358get().recordOccurrence("getTipCardsApi.Success", false);
    }

    public void setLiveDashboardCards(List<BaseRecyclerItem> list) {
        this.liveDashboardCards.setValue(list);
    }

    @VisibleForTesting
    public CardsStore(Lazy<CardApi> lazy, Lazy<CrashReporter> lazy2, Lazy<CrashMetadata> lazy3, Lazy<BilobaMetricsService> lazy4, Lazy<CardTransformer> lazy5) {
        this();
        this.cardApi = lazy;
        this.crashReporter = lazy2;
        this.crashMetadata = lazy3;
        this.bilobaMetricsService = lazy4;
        this.cardTransformer = lazy5;
    }
}
