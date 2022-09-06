package com.amazon.alexa.drive.navigation;

import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.cards.Card;
import com.amazon.alexa.drive.cards.CardAdapter;
import com.amazon.alexa.drive.cards.OnCardClickListenerInterface;
import com.amazon.alexa.drive.metrics.DriveModeMetricsHelper;
import com.amazon.alexa.drive.metrics.NavigationMetrics;
import com.amazon.alexa.drive.navigation.SavedLocations;
import com.amazon.alexa.drive.network.NetworkConnectivityManager;
import com.amazon.alexa.drive.userstudy.DriverDistractionLog;
import com.amazon.alexa.drive.userstudy.LogConstants;
import com.amazon.alexa.drive.weblab.WeblabManager;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
/* loaded from: classes7.dex */
public final class NavigationCardManager implements OnCardClickListenerInterface {
    private static final String TAG = "NavigationCardManager";
    private NavigationCardProviderHelper cardProvider;
    private final CompositeDisposable disposable = new CompositeDisposable();
    private final String labelHome;
    private final String labelWork;
    private final CardAdapter mCardAdapter;
    private final Context mContext;
    private DriveModeThemeManager mDriveModeThemeManager;
    private final NavigationDataProvider mNavigationDataProvider;
    private final NetworkConnectivityManager mNetworkConnectivityManager;
    private Subscription mNetworkStatusSubscription;
    private final PreferredNavigationAppContentResolver mPreferredNavigationAppContentResolver;
    private final NavigationMetrics navigationMetrics;

    public NavigationCardManager(Context context, NavigationDataProvider navigationDataProvider, PreferredNavigationAppContentResolver preferredNavigationAppContentResolver, NavigationMetrics navigationMetrics, NetworkConnectivityManager networkConnectivityManager, DriveModeThemeManager driveModeThemeManager, WeblabManager weblabManager) {
        this.mContext = context;
        this.mNavigationDataProvider = navigationDataProvider;
        this.mPreferredNavigationAppContentResolver = preferredNavigationAppContentResolver;
        this.mNetworkConnectivityManager = networkConnectivityManager;
        this.navigationMetrics = navigationMetrics;
        this.mDriveModeThemeManager = driveModeThemeManager;
        this.mCardAdapter = new CardAdapter(context, this, this.mDriveModeThemeManager, weblabManager);
        this.cardProvider = new NavigationCardProviderHelper(context, navigationDataProvider, navigationMetrics, this, driveModeThemeManager, weblabManager);
        this.labelHome = this.mContext.getResources().getString(R.string.dm_card_nav_home);
        this.labelWork = this.mContext.getResources().getString(R.string.dm_card_nav_work);
    }

    private Subscription getNetworkStatusSubscription() {
        return this.mNetworkStatusSubscription;
    }

    private void logCardClickAction(Card card) {
        String autoModeType = DriveModeMetricsHelper.getAutoModeType();
        String title = card.getTitle();
        if (this.labelHome.equals(card.getTitle())) {
            this.navigationMetrics.logHomeLandingCardSelected(autoModeType);
        } else if (this.labelWork.equals(title)) {
            this.navigationMetrics.logWorkLandingCardSelected(autoModeType);
        } else {
            this.navigationMetrics.logStoredLocationSelected(autoModeType);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCardsReady(Boolean bool) {
        String str = "onCardsReady success ? " + bool;
        if (!bool.booleanValue()) {
            return;
        }
        updateSavedLocationsFeed();
    }

    public CardAdapter getAdapter() {
        return this.mCardAdapter;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleNetworkConnectionChange(boolean z) {
        this.cardProvider.setNetworkConnectionStatus(z);
        if (!z || this.mNavigationDataProvider.isSavedLocationFetched()) {
            return;
        }
        this.mNavigationDataProvider.requestNavigationSavedLocations();
    }

    void initNetworkConnectionManager() {
        this.mNetworkStatusSubscription = this.mNetworkConnectivityManager.getNetworkConnectivityStream().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.alexa.drive.navigation.-$$Lambda$TPOBSqSUMd5sKqCeE3Fbf5lqByM
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                NavigationCardManager.this.handleNetworkConnectionChange(((Boolean) obj).booleanValue());
            }
        });
        handleNetworkConnectionChange(this.mNetworkConnectivityManager.isNetworkConnected());
    }

    @Override // com.amazon.alexa.drive.cards.OnCardClickListenerInterface
    public void onCardClick(Card card) {
        Intent intent = MappingApplication.from(this.mPreferredNavigationAppContentResolver.getPreferredNavigationApp()).getIntent(((NavigationCard) card).getAddress(), MappingApplication.DRIVING);
        logCardClickAction(card);
        DriverDistractionLog.logTouch(LogConstants.NAVIGATION, card.getTitle());
        this.mContext.startActivity(intent);
    }

    void setCardProvider(NavigationCardProviderHelper navigationCardProviderHelper) {
        this.cardProvider = navigationCardProviderHelper;
    }

    public void start() {
        this.cardProvider.start();
        this.disposable.add(this.mNavigationDataProvider.queryLocations().subscribe(new Consumer() { // from class: com.amazon.alexa.drive.navigation.-$$Lambda$NavigationCardManager$_GJTwUUzqw0Vl8yVw0Ulray7nB4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                NavigationCardManager.this.onCardsReady((Boolean) obj);
            }
        }));
        initNetworkConnectionManager();
    }

    public void stop() {
        this.cardProvider.stop();
        this.disposable.clear();
        if (!getNetworkStatusSubscription().isUnsubscribed()) {
            getNetworkStatusSubscription().unsubscribe();
        }
    }

    public void updateSavedLocationsFeed() {
        List<SavedLocations.Item> savedLocations = this.mNavigationDataProvider.getSavedLocations();
        ArrayList arrayList = new ArrayList();
        for (SavedLocations.Item item : savedLocations) {
            NavigationCard navigationCard = new NavigationCard();
            navigationCard.setTitle(item.getAddressLabel());
            navigationCard.setDescription(item.getAddress().getAddressLine1());
            navigationCard.setItem(item);
            navigationCard.setIcon(R.drawable.ic_things_to_try);
            arrayList.add(navigationCard);
        }
        this.mCardAdapter.setCards(arrayList);
        this.cardProvider.onCardsReady();
    }
}
