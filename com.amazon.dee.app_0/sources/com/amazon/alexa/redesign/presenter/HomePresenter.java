package com.amazon.alexa.redesign.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.actions.Action;
import com.amazon.alexa.redesign.actions.ActionFactory;
import com.amazon.alexa.redesign.entity.AlertBannerModel;
import com.amazon.alexa.redesign.entity.CardModel;
import com.amazon.alexa.redesign.entity.DismissIdentifier;
import com.amazon.alexa.redesign.entity.templates.MiniCardTemplateModel;
import com.amazon.alexa.redesign.entity.templates.VoxIngressTemplateModel;
import com.amazon.alexa.redesign.utils.Constants;
import com.amazon.alexa.redesign.utils.LatencyInteractor;
import com.amazon.alexa.redesign.utils.ViewRecorder;
import com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem;
import com.amazon.alexa.redesign.view.templates.voxIngressTemplate.VoxIngressTemplateViewItem;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class HomePresenter implements HomeContract.Presenter {
    protected static final String DEFAULT_BACKGROUND_COLOR = "#163954";
    private static final String STARTUP_STATE = "startupState";
    private static final String TAG = "HomePresenter";
    private static boolean firstEntryAsUser = true;
    @VisibleForTesting
    final ClickToDismissHandler clickToDismissHandler;
    private final FeatureServiceV2 featureServiceV2;
    private final HomeContract.HomeMetricsRecorder homeMetricsRecorder;
    private final HomeContract.Interactor interactor;
    private final HomeContract.LatencyInteractor latencyInteractor;
    private final HomeContract.OEInteractor oeInteractor;
    private RecyclerViewItem previousRecyclerViewItem;
    private final RoutingService routingService;
    private int totalItem;
    private final HomeContract.View view;
    @VisibleForTesting
    final ViewRecorder viewRecorder;
    private VoxIngressTemplateModel voxIngressTemplateModel;
    private int previousItemIndex = 0;
    private Map<Object, Long> cardsSeenBeforeScroll = new HashMap();
    private boolean isInForeground = true;
    private boolean isInBackground = false;
    private boolean notifiedViewOnCacheLoad = false;
    private boolean notifiedViewOnServerLoad = false;
    private boolean notifiedViewOnServerLoadNoCache = false;
    private boolean notifiedViewOnSkeleton = false;
    private boolean boundData = false;
    private String lastRouteName = "";
    @VisibleForTesting
    boolean mostRecentIsOffline = false;
    private AlertBannerModel mostRecentAlertBannerModel = null;
    private final PersistentStorage.Factory persistentStorageFactory = (PersistentStorage.Factory) GeneratedOutlineSupport1.outline20(PersistentStorage.Factory.class);
    private final PersistentStorage persistentStorage = this.persistentStorageFactory.create(STARTUP_STATE);

    public HomePresenter(HomeContract.View view, HomeContract.Interactor interactor, HomeContract.HomeMetricsRecorder homeMetricsRecorder, HomeContract.LatencyInteractor latencyInteractor, HomeContract.OEInteractor oEInteractor, RoutingService routingService, ClickToDismissHandler clickToDismissHandler, ViewRecorder viewRecorder, FeatureServiceV2 featureServiceV2) {
        this.view = view;
        this.interactor = interactor;
        this.homeMetricsRecorder = homeMetricsRecorder;
        this.latencyInteractor = latencyInteractor;
        this.oeInteractor = oEInteractor;
        this.routingService = routingService;
        this.clickToDismissHandler = clickToDismissHandler;
        this.viewRecorder = viewRecorder;
        this.featureServiceV2 = featureServiceV2;
    }

    private void displayVoxIngress() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.voxIngressTemplateModel);
        ArrayList arrayList2 = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("contentType", Constants.SKELETON);
            jSONObject.put("contentProvider", Constants.SKELETON);
            jSONObject.put("contentId", Constants.SKELETON);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MiniCardTemplateModel miniCardTemplateModel = new MiniCardTemplateModel(jSONObject, arrayList2, true, true);
        MiniCardTemplateModel miniCardTemplateModel2 = new MiniCardTemplateModel(jSONObject, arrayList2, true, false);
        MiniCardTemplateModel miniCardTemplateModel3 = new MiniCardTemplateModel(jSONObject, arrayList2, true, false);
        arrayList.add(miniCardTemplateModel);
        arrayList.add(miniCardTemplateModel2);
        arrayList.add(miniCardTemplateModel3);
        setColdStartAndUpdateType(firstEntryAsUser, Constants.VIEW_UPDATE_TYPE_SKELETON, arrayList);
        this.view.updateCardModels(arrayList);
        this.notifiedViewOnSkeleton = true;
        this.latencyInteractor.recordProfileEvent(LatencyInteractor.PROFILE_EVENT_TOLD_VIEW_RENDER_VOX);
    }

    private void filterOutDismissedCards(List<CardModel> list) {
        List<DismissIdentifier> dismissedCardIds = this.interactor.getDismissedCardIds(new String[]{Constants.KEY_DISMISS_CARDS, Constants.KEY_CLICK_TO_DISMISS_CARDS});
        HashSet hashSet = new HashSet();
        for (DismissIdentifier dismissIdentifier : dismissedCardIds) {
            hashSet.add(dismissIdentifier.getDismissId());
        }
        ListIterator<CardModel> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if (hashSet.contains(listIterator.next().getDismissCardId())) {
                listIterator.remove();
            }
        }
    }

    @VisibleForTesting
    private long getCurrentTimeInMillis() {
        return Calendar.getInstance().getTimeInMillis();
    }

    private void handleInAppLatencyInit(boolean z) {
        if (z) {
            this.latencyInteractor.initInAppLatencyColdStart();
            this.latencyInteractor.recordProfileEvent(LatencyInteractor.PROFILE_EVENT_STARTED_PRESENTING_COLD);
            return;
        }
        this.latencyInteractor.initInAppLatencyWarmStart();
        this.latencyInteractor.recordProfileEvent(LatencyInteractor.PROFILE_EVENT_STARTED_PRESENTING_WARM);
    }

    private void handleOfflineMode() {
        this.mostRecentIsOffline = !this.interactor.validateNetworkConnection();
        this.view.setOfflineBannerVisibility(this.mostRecentIsOffline);
        this.voxIngressTemplateModel = this.interactor.getVoxIngressModel();
        this.voxIngressTemplateModel.setShouldAnimate(this.view.getShouldVoxAnimateOnCacheLoad());
        this.voxIngressTemplateModel.setOfflineMode(this.mostRecentIsOffline);
        this.voxIngressTemplateModel.setSkeleton(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$displayActiveCardsFromCache$13(List list, List list2) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list2);
        arrayList.addAll(list);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$displayCachedCardsAndFetchServerCards$3(List list, List list2) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list2);
        arrayList.addAll(list);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$displayMockedCards$0(List list, List list2) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list2);
        arrayList.addAll(list);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$fetchHomeCardsFromServer$7(List list, List list2) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list2);
        arrayList.addAll(list);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$fetchHomeCardsFromServerWhenCacheError$10(List list, List list2) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list2);
        arrayList.addAll(list);
        return arrayList;
    }

    static /* synthetic */ void lambda$onLogOut$16() throws Throwable {
    }

    private void publishHomeEnterEvent() {
        this.interactor.publishEventBusMessage(new Message.Builder().setEventType(com.amazon.alexa.mode.Constants.DEVICE_HOME_EVENT).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordEnterHomeOE() {
        this.oeInteractor.recordEnterHome();
    }

    private void resendDismissedCardMetricsOnError(Map<String, Object> map, long j) {
        map.put(JsonFields.ACTION_TYPE, ActionFactory.DISMISS_ACTION);
        map.put("refMarker", "dismissRetry-" + j);
        this.homeMetricsRecorder.recordClickEvent(map);
    }

    private void setColdStartAndUpdateType(boolean z, String str, List<CardModel> list) {
        for (CardModel cardModel : list) {
            cardModel.setIsColdStart(z);
            cardModel.setViewUpdateType(str);
            cardModel.setLastRouteName(this.lastRouteName);
        }
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void activateDebugMenu(Context context) {
        if ((context.getApplicationInfo().flags & 2) != 0) {
            this.interactor.launchDebugMenu();
        }
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    @SuppressLint({"CheckResult"})
    public void displayActiveCardsFromCache(final String str) {
        Single.zip(this.interactor.getRawHomeCardsFromCache(), this.interactor.getLocalCards(), $$Lambda$HomePresenter$MmS_9UPc5J8UB4Wqsc_CwBRk.INSTANCE).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.redesign.presenter.-$$Lambda$HomePresenter$cH86Ao6mdjYlwI6bp691accNzyo
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                HomePresenter.this.lambda$displayActiveCardsFromCache$14$HomePresenter(str, (List) obj);
            }
        }, $$Lambda$HomePresenter$0q92cUxVXlA1_ifLa3RNzC03wBU.INSTANCE);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void displayBottomSheet(BottomSheetDialogFragment bottomSheetDialogFragment) {
        this.view.displayBottomSheet(bottomSheetDialogFragment);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    @SuppressLint({"CheckResult"})
    public void displayCachedCardsAndFetchServerCards() {
        Single.zip(this.interactor.getRawHomeCardsFromCache(), this.interactor.getLocalCards(), $$Lambda$HomePresenter$QMODDmevBpvAK8wbOWdiy4FGgU.INSTANCE).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.redesign.presenter.-$$Lambda$HomePresenter$-fmTs2a5GrzvrJqZLOT_ywUnlao
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                HomePresenter.this.lambda$displayCachedCardsAndFetchServerCards$4$HomePresenter((List) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.redesign.presenter.-$$Lambda$HomePresenter$7uLpZU4Wf6zDuDfI-XWTDcStad8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                HomePresenter.this.lambda$displayCachedCardsAndFetchServerCards$5$HomePresenter((Throwable) obj);
            }
        });
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    @SuppressLint({"CheckResult"})
    public void displayMockedCards() {
        Single.zip(this.interactor.getCardsFromMockedData(), this.interactor.getLocalCards(), $$Lambda$HomePresenter$wZkXI9YN0vJtG2Esrn3uFQsJY6E.INSTANCE).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.redesign.presenter.-$$Lambda$HomePresenter$-iPCafKIICrU_KEyhT9TfLONem8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                HomePresenter.this.lambda$displayMockedCards$1$HomePresenter((List) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.redesign.presenter.-$$Lambda$HomePresenter$Hisaoqx5dI_Y1TlbSrmqLzIKy-A
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                HomePresenter.this.lambda$displayMockedCards$2$HomePresenter((Throwable) obj);
            }
        });
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void end() {
        this.latencyInteractor.dropInAppLatency();
        this.viewRecorder.updateAndRecordCardsOnScreen(this.cardsSeenBeforeScroll, true);
        this.homeMetricsRecorder.recordExitHome(false);
        this.interactor.clearEventCacheClickEvents();
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    @SuppressLint({"CheckResult"})
    @VisibleForTesting
    public void fetchHomeCardsFromServer(final boolean z, final String str) {
        Single.zip(this.interactor.getRawHomeCardsFromServer(), this.interactor.getLocalCards(), $$Lambda$HomePresenter$YkkYi0qKS6HUHN4gCLl1R1yF25M.INSTANCE).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.redesign.presenter.-$$Lambda$HomePresenter$mioHPFyxFUcr4DvQoeYNo6un730
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                HomePresenter.this.lambda$fetchHomeCardsFromServer$8$HomePresenter(z, str, (List) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.redesign.presenter.-$$Lambda$HomePresenter$dj5F7K8D6SbP9XeLcjBkmTQYE4Y
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                HomePresenter.this.lambda$fetchHomeCardsFromServer$9$HomePresenter((Throwable) obj);
            }
        });
    }

    @SuppressLint({"CheckResult"})
    @VisibleForTesting
    void fetchHomeCardsFromServerWhenCacheError() {
        Single.zip(this.interactor.getRawHomeCardsFromServer(), this.interactor.getLocalCards(), $$Lambda$HomePresenter$A_XZx6WenKZZEuXPQiMvDuj7mr4.INSTANCE).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.redesign.presenter.-$$Lambda$HomePresenter$idan6DwfUZN_lNJMkqf9HNWG_c4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                HomePresenter.this.lambda$fetchHomeCardsFromServerWhenCacheError$11$HomePresenter((List) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.redesign.presenter.-$$Lambda$HomePresenter$zQ05KyTtcmhLB5vNc11jpuUf0P8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                HomePresenter.this.lambda$fetchHomeCardsFromServerWhenCacheError$12$HomePresenter((Throwable) obj);
            }
        });
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public int getViewBackgroundColor(int i) {
        RecyclerViewItem mo2380getViewItemFromPosition = this.view.mo2380getViewItemFromPosition(i);
        return mo2380getViewItemFromPosition != null ? mo2380getViewItemFromPosition.getViewBackgroundColor() : Color.parseColor(DEFAULT_BACKGROUND_COLOR);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void handleClickToDismiss(int i, CardModel cardModel) {
        this.clickToDismissHandler.handleClickToDismiss(i, cardModel);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void initRNPlaceholder() {
        this.view.initRNPlaceholder(this.interactor.getRNPlaceholderBundle());
    }

    public /* synthetic */ void lambda$displayActiveCardsFromCache$14$HomePresenter(String str, List list) throws Throwable {
        int i;
        this.voxIngressTemplateModel.setShouldAnimate(false);
        this.voxIngressTemplateModel.setSkeleton(false);
        list.add(0, this.voxIngressTemplateModel);
        filterOutDismissedCards(list);
        setColdStartAndUpdateType(firstEntryAsUser, str, list);
        this.view.updateCardModels(list);
        this.previousItemIndex = this.view.getFirstVisibleItemPosition();
        if (this.previousItemIndex == this.view.getTotalViewItems() && (i = this.previousItemIndex) > 0) {
            this.previousItemIndex = i - 1;
        }
        this.view.returnToLastVisitedPosition();
        this.previousRecyclerViewItem = this.view.mo2380getViewItemFromPosition(this.previousItemIndex);
    }

    public /* synthetic */ void lambda$displayCachedCardsAndFetchServerCards$4$HomePresenter(List list) throws Throwable {
        if (firstEntryAsUser) {
            this.latencyInteractor.recordProfileEvent(LatencyInteractor.PROFILE_EVENT_GOT_CACHED_CARDS_COLD);
            this.voxIngressTemplateModel.setShouldAnimate(false);
        } else {
            this.latencyInteractor.recordProfileEvent(LatencyInteractor.PROFILE_EVENT_GOT_CACHED_CARDS_WARM);
            this.voxIngressTemplateModel.setShouldAnimate(this.view.getShouldVoxAnimateOnCacheLoad());
        }
        this.voxIngressTemplateModel.setSkeleton(false);
        list.add(0, this.voxIngressTemplateModel);
        filterOutDismissedCards(list);
        setColdStartAndUpdateType(firstEntryAsUser, Constants.VIEW_UPDATE_TYPE_CACHE, list);
        this.view.updateCardModels(list);
        this.notifiedViewOnCacheLoad = true;
        if (!firstEntryAsUser && !this.interactor.needRefresh()) {
            this.view.returnToLastVisitedPosition();
            recordCardsSeenOnColdStart(this.view.getFirstReturnToPosition(), this.view.getLastReturnToPosition());
            return;
        }
        fetchHomeCardsFromServer(false, Constants.VIEW_UPDATE_TYPE_SERVER);
    }

    public /* synthetic */ void lambda$displayCachedCardsAndFetchServerCards$5$HomePresenter(Throwable th) throws Throwable {
        Log.e(TAG, "Cache Error!");
        if (Boolean.valueOf(this.interactor.validateNetworkConnection()).booleanValue()) {
            fetchHomeCardsFromServerWhenCacheError();
            return;
        }
        this.view.displayErrorPage();
        this.oeInteractor.recordErrorPageView();
    }

    public /* synthetic */ void lambda$displayMockedCards$1$HomePresenter(List list) throws Throwable {
        if (firstEntryAsUser) {
            this.voxIngressTemplateModel.setShouldAnimate(false);
        } else {
            this.voxIngressTemplateModel.setShouldAnimate(this.view.getShouldVoxAnimateOnCacheLoad());
        }
        this.voxIngressTemplateModel.setSkeleton(false);
        list.add(0, this.voxIngressTemplateModel);
        filterOutDismissedCards(list);
        this.view.updateCardModels(list);
        if (!firstEntryAsUser && !this.interactor.needRefresh()) {
            this.view.returnToLastVisitedPosition();
            recordCardsSeenOnColdStart(this.view.getFirstReturnToPosition(), this.view.getLastReturnToPosition());
        } else {
            firstEntryAsUser = false;
        }
        this.view.finishRefresh();
    }

    public /* synthetic */ void lambda$displayMockedCards$2$HomePresenter(Throwable th) throws Throwable {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Mock Cards Error! ");
        outline107.append(th.toString());
        Log.e(str, outline107.toString());
        this.view.finishRefresh();
    }

    public /* synthetic */ void lambda$fetchHomeCardsFromServer$8$HomePresenter(boolean z, String str, List list) throws Throwable {
        if (firstEntryAsUser) {
            this.latencyInteractor.recordProfileEvent(LatencyInteractor.PROFILE_EVENT_GOT_SERVER_CARDS_COLD);
        } else {
            this.latencyInteractor.recordProfileEvent(LatencyInteractor.PROFILE_EVENT_GOT_SERVER_CARDS_WARM);
        }
        validateDismissedCardsAndUpdateDataStore(list);
        this.voxIngressTemplateModel.setShouldAnimate(z);
        this.voxIngressTemplateModel.setSkeleton(false);
        this.voxIngressTemplateModel.setShowingRefreshPill(false);
        list.add(0, this.voxIngressTemplateModel);
        filterOutDismissedCards(list);
        setColdStartAndUpdateType(firstEntryAsUser, str, list);
        this.view.updateCardModels(list);
        this.notifiedViewOnServerLoad = true;
        if (this.interactor.needRefresh()) {
            this.interactor.resetNeedRefreshValue();
        }
        this.view.finishRefresh();
        this.view.setRefreshButtonVisibility(false);
        if (str.equals(Constants.VIEW_UPDATE_TYPE_PULLREFRESH)) {
            this.viewRecorder.recordViewEventForCardsOutOfView(this.cardsSeenBeforeScroll, false);
        } else if (!str.equals(Constants.VIEW_UPDATE_TYPE_ERROR_PAGE_RETRY)) {
        } else {
            this.view.hideErrorPage();
        }
    }

    public /* synthetic */ void lambda$fetchHomeCardsFromServer$9$HomePresenter(Throwable th) throws Throwable {
        Log.e(TAG, th.getMessage());
        this.view.finishRefresh();
        this.view.setRefreshButtonVisibility(false);
        firstEntryAsUser = false;
    }

    public /* synthetic */ void lambda$fetchHomeCardsFromServerWhenCacheError$11$HomePresenter(List list) throws Throwable {
        if (firstEntryAsUser) {
            this.latencyInteractor.recordProfileEvent(LatencyInteractor.PROFILE_EVENT_GOT_SERVER_CARDS_COLD_NO_CACHE);
        } else {
            this.latencyInteractor.recordProfileEvent(LatencyInteractor.PROFILE_EVENT_GOT_SERVER_CARDS_WARM_NO_CACHE);
        }
        validateDismissedCardsAndUpdateDataStore(list);
        this.view.hideErrorPage();
        this.voxIngressTemplateModel.setShouldAnimate(false);
        this.voxIngressTemplateModel.setSkeleton(false);
        list.add(0, this.voxIngressTemplateModel);
        filterOutDismissedCards(list);
        setColdStartAndUpdateType(firstEntryAsUser, Constants.VIEW_UPDATE_TYPE_SERVER_NO_CACHE, list);
        this.view.updateCardModels(list);
        this.notifiedViewOnServerLoadNoCache = true;
    }

    public /* synthetic */ void lambda$fetchHomeCardsFromServerWhenCacheError$12$HomePresenter(Throwable th) throws Throwable {
        Log.e(TAG, "Error while getting cards from server!!");
        this.view.displayErrorPage();
        this.latencyInteractor.dropInAppLatency();
        this.oeInteractor.recordErrorPageView();
        Log.e(TAG, th.getMessage());
        firstEntryAsUser = false;
    }

    public /* synthetic */ void lambda$onLiveUpdateEvent$6$HomePresenter() {
        this.view.setRefreshButtonVisibility(true);
        HomeContract.View view = this.view;
        AlertBannerModel alertBannerModel = this.mostRecentAlertBannerModel;
        view.updateAccessibilityOrder(alertBannerModel != null ? alertBannerModel.getAlertMessage() : null);
        this.voxIngressTemplateModel.setShowingRefreshPill(true);
        this.voxIngressTemplateModel.setShouldAnimate(false);
        this.view.updateCardModel(0, new VoxIngressTemplateViewItem(this.voxIngressTemplateModel, this.mostRecentAlertBannerModel));
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void onActivityDestroyed() {
        this.interactor.clearLocalCards();
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void onBoundCardView(String str) {
        this.boundData = true;
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void onCancelTimerAction(int i) {
        CardModel mo2390getModel;
        this.view.onCancelTimerAction(i);
        RecyclerViewItem mo2380getViewItemFromPosition = this.view.mo2380getViewItemFromPosition(i);
        if (mo2380getViewItemFromPosition == null || (mo2390getModel = mo2380getViewItemFromPosition.mo2390getModel()) == null) {
            return;
        }
        this.interactor.saveDismissedCardId(new DismissIdentifier(System.currentTimeMillis(), mo2390getModel.getDismissCardId()), Constants.KEY_DISMISS_CARDS);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void onConfigurationChange() {
        if (this.view.isViewAttached()) {
            this.view.handleRotation();
        }
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void onCreatedCardView() {
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void onHeaderVoxClick(Map<String, Object> map) {
        this.voxIngressTemplateModel.getOnVoxClickAction().execute(map);
        Map<String, Object> topLevelMetricsObject = this.voxIngressTemplateModel.getTopLevelMetricsObject();
        topLevelMetricsObject.put("metaActionType", "header");
        this.homeMetricsRecorder.recordClickEvent(topLevelMetricsObject);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void onHomeCardsViewFinishedLoading(boolean z) {
        if (!z) {
            this.latencyInteractor.recordWarmStartInAppLatency();
        }
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void onLayoutCompleted() {
        if (this.boundData) {
            if (this.notifiedViewOnServerLoad) {
                if (firstEntryAsUser) {
                    this.latencyInteractor.recordProfileEvent(LatencyInteractor.PROFILE_EVENT_LAID_SERVER_CARDS_COLD);
                    firstEntryAsUser = false;
                } else {
                    this.latencyInteractor.recordProfileEvent(LatencyInteractor.PROFILE_EVENT_LAID_SERVER_CARDS_WARM);
                }
                this.notifiedViewOnServerLoad = false;
            }
            if (this.notifiedViewOnSkeleton) {
                this.latencyInteractor.recordProfileEvent(LatencyInteractor.PROFILE_EVENT_LAID_SKELETON);
                this.notifiedViewOnSkeleton = false;
            }
            if (this.notifiedViewOnCacheLoad) {
                if (firstEntryAsUser) {
                    this.latencyInteractor.recordProfileEvent(LatencyInteractor.PROFILE_EVENT_LAID_CACHED_CARDS_COLD);
                } else {
                    this.latencyInteractor.recordProfileEvent(LatencyInteractor.PROFILE_EVENT_LAID_CACHED_CARDS_WARM);
                }
                this.notifiedViewOnCacheLoad = false;
            }
            if (this.notifiedViewOnServerLoadNoCache) {
                if (firstEntryAsUser) {
                    this.latencyInteractor.recordProfileEvent(LatencyInteractor.PROFILE_EVENT_LAID_SERVER_CARDS_COLD_NO_CACHE);
                    firstEntryAsUser = false;
                } else {
                    this.latencyInteractor.recordProfileEvent(LatencyInteractor.PROFILE_EVENT_LAID_SERVER_CARDS_WARM_NO_CACHE);
                }
                this.notifiedViewOnServerLoadNoCache = false;
            }
        }
        this.boundData = false;
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void onLiveUpdateEvent() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.alexa.redesign.presenter.-$$Lambda$HomePresenter$xwB3FGgGCqe9lJnSRYz_AmfxHbg
            @Override // java.lang.Runnable
            public final void run() {
                HomePresenter.this.lambda$onLiveUpdateEvent$6$HomePresenter();
            }
        });
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void onLocalCardEventReceived() {
        if (this.view.isViewAttached()) {
            displayActiveCardsFromCache(Constants.VIEW_UPDATE_TYPE_EVENT_BUS);
        }
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    @SuppressLint({"CheckResult"})
    public void onLogOut() {
        firstEntryAsUser = true;
        this.view.setLogoutFlag(true);
        this.view.resetReturnPosition();
        this.view.setFirstRenderCompleteOnLogOut();
        this.interactor.clearLocalCards();
        this.interactor.resetNeedRefreshValue();
        this.interactor.clearHomeCardsFromCache().subscribeOn(Schedulers.io()).subscribe($$Lambda$HomePresenter$OK0NklQmN0Y69qKsfJDItTLESL8.INSTANCE, $$Lambda$HomePresenter$BNnCkdiCcrOS7gyxZmUuNGS1eE.INSTANCE);
        this.interactor.clearDismissedCardIds();
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void onModalToggle(boolean z) {
        RecyclerViewItem recyclerViewItem = this.previousRecyclerViewItem;
        if (recyclerViewItem != null) {
            this.homeMetricsRecorder.recordModalToggle(z, recyclerViewItem, this.previousItemIndex, this.totalItem);
        }
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    /* renamed from: onNetworkChanged */
    public void lambda$onResumeHome$18$HomePresenter() {
        if (!this.view.isViewAttached() || !this.isInForeground) {
            return;
        }
        boolean z = !this.interactor.validateNetworkConnection();
        if (z != this.mostRecentIsOffline) {
            start(false);
        }
        this.mostRecentIsOffline = z;
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void onPauseHome(RecyclerViewItem recyclerViewItem, int i, int i2) {
        this.isInForeground = false;
        this.isInBackground = true;
        this.viewRecorder.updateAndRecordCardsOnScreen(this.cardsSeenBeforeScroll, false);
        this.homeMetricsRecorder.recordExitHome(true);
        this.interactor.clearEventCacheClickEvents();
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void onReactCardRenderRequest(String str) {
        this.oeInteractor.recordReactCardRenderRequest(str);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void onRefreshButtonClicked() {
        this.view.goToPosition(0);
        this.view.setRefreshButtonVisibility(false);
        HomeContract.View view = this.view;
        AlertBannerModel alertBannerModel = this.mostRecentAlertBannerModel;
        view.updateAccessibilityOrder(alertBannerModel != null ? alertBannerModel.getAlertMessage() : null);
        this.voxIngressTemplateModel.setShowingRefreshPill(false);
        this.voxIngressTemplateModel.setShouldAnimate(true);
        this.view.resetReturnPosition();
        fetchHomeCardsFromServer(!this.mostRecentIsOffline, Constants.VIEW_UPDATE_TYPE_REFRESH_BUTTON);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void onRemoveCardClicked(int i, Map<String, Object> map, String str) {
        map.put(JsonFields.ACTION_TYPE, ActionFactory.DISMISS_ACTION);
        this.interactor.saveDismissedCardId(new DismissIdentifier(this.homeMetricsRecorder.recordClickEvent(map), str), Constants.KEY_DISMISS_CARDS);
        this.view.removeCardAt(i);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void onRequestHomeRefreshEvent(boolean z) {
        if (z && this.view.isViewAttached()) {
            fetchHomeCardsFromServer(false, Constants.VIEW_UPDATE_TYPE_SERVER);
        } else {
            this.interactor.requestRefresh();
        }
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void onResumeHome(boolean z) {
        publishHomeEnterEvent();
        this.homeMetricsRecorder.recordEnterHome(z);
        if (z) {
            recordEnterHomeOE();
        }
        this.isInForeground = true;
        this.isInBackground = false;
        if (z) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.alexa.redesign.presenter.-$$Lambda$HomePresenter$S-a47bLp55yRFpzShgawiDcA0bg
                @Override // java.lang.Runnable
                public final void run() {
                    HomePresenter.this.lambda$onResumeHome$18$HomePresenter();
                }
            });
        }
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void onScrolledToViewItem(RecyclerViewItem recyclerViewItem, int i, int i2, int i3) {
        if (recyclerViewItem == null) {
            return;
        }
        if (this.previousItemIndex == 0 && this.previousRecyclerViewItem == null) {
            this.previousRecyclerViewItem = this.view.mo2380getViewItemFromPosition(0);
        }
        Map<Object, Long> recordCardsInRange = this.viewRecorder.recordCardsInRange(i, i2);
        this.viewRecorder.recordViewEventForCardsOutOfView(this.viewRecorder.getCardsOutOfView(this.cardsSeenBeforeScroll, recordCardsInRange), false);
        this.viewRecorder.updateCardsSeenBeforeNextScroll(this.cardsSeenBeforeScroll, recordCardsInRange);
        this.previousItemIndex = i;
        this.previousRecyclerViewItem = recyclerViewItem;
        this.totalItem = i3;
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void onSkeletonSlideUpFinished() {
        this.latencyInteractor.recordProfileEvent(LatencyInteractor.PROFILE_EVENT_FINISHED_RENDERING_SKELETON);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void onTWAClick(Map<String, Object> map, Action action) {
        action.execute(map);
        Map<String, Object> topLevelMetricsObject = this.voxIngressTemplateModel.getTopLevelMetricsObject();
        topLevelMetricsObject.put("metaActionType", "header");
        this.homeMetricsRecorder.recordClickEvent(topLevelMetricsObject);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void onThreeDotsClicked(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("cardId", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.interactor.publishEventBusMessage(new Message.Builder().setEventType("open:bottom:sheet").setPayload(jSONObject.toString()).build());
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void onVoxIngressFinishedLoading(boolean z) {
        if (!z || !this.view.getCslOverride()) {
            return;
        }
        this.latencyInteractor.recordColdStartLatency();
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void onVoxIngressFinishedLoadingWithLogout(boolean z) {
        if (!z || !this.view.getCslOverride()) {
            return;
        }
        this.latencyInteractor.recordColdStartInAppLatency();
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void publishHomeInitializedEvent() {
        this.interactor.publishEventBusMessage(new Message.Builder().setEventType(com.amazon.alexa.mode.Constants.HOME_CHANNEL_INITIALIZED_EVENT).build());
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void publishTWAAvailablityRequest() {
        this.interactor.publishEventBusMessage(new Message.Builder().setEventType("voice::typeToAlexaAvailabilityRequest").build());
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void recordCardsSeenOnColdStart(int i, int i2) {
        this.cardsSeenBeforeScroll = this.viewRecorder.recordCardsInRange(i, i2);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void setLastRouteName(String str) {
        this.lastRouteName = str;
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void setOutageAlert(@Nullable AlertBannerModel alertBannerModel) {
        if (alertBannerModel != null && alertBannerModel.getAlertMessage() != null) {
            this.view.setOutageAlert(alertBannerModel.getAlertMessage());
        } else {
            this.view.setOutageAlert(null);
        }
        this.mostRecentAlertBannerModel = alertBannerModel;
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void start(boolean z) {
        handleOfflineMode();
        if (!z) {
            this.homeMetricsRecorder.recordEnterHome(false);
        }
        handleInAppLatencyInit(z);
        if (z) {
            displayVoxIngress();
        } else {
            displayCachedCardsAndFetchServerCards();
        }
        initRNPlaceholder();
        this.view.revokeSwipeExperience();
        publishHomeEnterEvent();
        if (this.featureServiceV2.hasAccess("CH_HOME_PAGE_LOAD_OPTIMIZATION_ANDROID", false)) {
            Executors.newSingleThreadExecutor().execute(new Runnable() { // from class: com.amazon.alexa.redesign.presenter.-$$Lambda$HomePresenter$Hq9QZhwGS9S4F1i2pvqcQ-U6svQ
                @Override // java.lang.Runnable
                public final void run() {
                    HomePresenter.this.recordEnterHomeOE();
                }
            });
        } else {
            recordEnterHomeOE();
        }
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void subscribeToEventBus() {
        this.interactor.subscribeToEventBus();
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Presenter
    public void unsubscribeFromEventBus() {
        this.interactor.unsubscribeFromEventBus();
    }

    @VisibleForTesting
    void validateDismissedCardsAndUpdateDataStore(@NonNull List<CardModel> list) {
        ArrayList<DismissIdentifier> arrayList = new ArrayList(this.interactor.getDismissedCardIds(new String[]{Constants.KEY_DISMISS_CARDS}));
        HashMap hashMap = new HashMap();
        for (CardModel cardModel : list) {
            hashMap.put(cardModel.getDismissCardId(), cardModel);
        }
        for (DismissIdentifier dismissIdentifier : arrayList) {
            String dismissId = dismissIdentifier.getDismissId();
            if (hashMap.keySet().contains(dismissId) && hashMap.get(dismissId) != null) {
                resendDismissedCardMetricsOnError(((CardModel) hashMap.get(dismissId)).getTopLevelMetricsObject(), dismissIdentifier.getTimestamp());
            } else {
                this.interactor.deleteDismissedCardId(dismissIdentifier, Constants.KEY_DISMISS_CARDS);
            }
        }
    }
}
