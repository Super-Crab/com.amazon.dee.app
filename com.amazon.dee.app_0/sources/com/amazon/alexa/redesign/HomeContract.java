package com.amazon.alexa.redesign;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.redesign.actions.Action;
import com.amazon.alexa.redesign.entity.AlertBannerModel;
import com.amazon.alexa.redesign.entity.CardModel;
import com.amazon.alexa.redesign.entity.DismissIdentifier;
import com.amazon.alexa.redesign.entity.templates.DomainCardTemplateModel;
import com.amazon.alexa.redesign.entity.templates.VoxIngressTemplateModel;
import com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public interface HomeContract {

    /* loaded from: classes10.dex */
    public interface CardDismissedListener {
        void displayActiveCardsFromCache();
    }

    /* loaded from: classes10.dex */
    public interface HomeMetricsRecorder {
        long recordClickEvent(Map<String, Object> map);

        void recordClickEventFromCards(HashMap<String, Object> hashMap);

        void recordDeepLink(Map<String, Object> map);

        void recordEnterHome(boolean z);

        void recordExitHome(boolean z);

        void recordModalToggle(boolean z, RecyclerViewItem recyclerViewItem, int i, int i2);

        void recordViewEvent(Map<String, Object> map, boolean z);
    }

    /* loaded from: classes10.dex */
    public interface Interactor {
        void clearDismissedCardIds();

        void clearEventCacheClickEvents();

        Completable clearHomeCardsFromCache();

        void clearLocalCards();

        void deleteDismissedCardId(DismissIdentifier dismissIdentifier, String str);

        Single<List<CardModel>> getCardsFromMockedData();

        List<DismissIdentifier> getDismissedCardIds(String[] strArr);

        Single<List<DomainCardTemplateModel>> getLocalCards();

        Bundle getRNPlaceholderBundle();

        Single<List<CardModel>> getRawHomeCardsFromCache();

        Single<List<CardModel>> getRawHomeCardsFromServer();

        VoxIngressTemplateModel getVoxIngressModel();

        void launchDebugMenu();

        boolean needRefresh();

        void processEarlyEventBusMessages(List<Message> list);

        void publishEventBusMessage(Message message);

        void requestRefresh();

        void resetNeedRefreshValue();

        void saveDismissedCardId(DismissIdentifier dismissIdentifier, String str);

        void subscribeToEventBus();

        void unsubscribeFromEventBus();

        boolean validateNetworkConnection();
    }

    /* loaded from: classes10.dex */
    public interface LatencyInteractor {
        void dropInAppLatency();

        void initInAppLatencyColdStart();

        void initInAppLatencyWarmStart();

        void recordColdStartInAppLatency();

        void recordColdStartLatency();

        void recordProfileEvent(String str);

        void recordWarmStartInAppLatency();
    }

    /* loaded from: classes10.dex */
    public interface LiveUpdatesListener {
        void onLiveUpdateEvent();
    }

    /* loaded from: classes10.dex */
    public interface LocalCardListener {
        void onLocalCardEventReceived();
    }

    /* loaded from: classes10.dex */
    public interface LogOutListener {
        void setFlagOnLogOut();
    }

    /* loaded from: classes10.dex */
    public interface ModalToggleListener {
        void onModalToggle(boolean z);
    }

    /* loaded from: classes10.dex */
    public interface OEInteractor {
        void recordBadRouteOccurrence(String str, String str2);

        void recordEnterHome();

        void recordErrorPageView();

        void recordMalformedDataOccurrence(String str);

        void recordMalformedViewOccurrence(String str, String str2);

        void recordPullToRefresh();

        void recordReactCardRenderRequest(String str);
    }

    /* loaded from: classes10.dex */
    public interface Presenter {
        void activateDebugMenu(Context context);

        void displayActiveCardsFromCache(String str);

        void displayBottomSheet(BottomSheetDialogFragment bottomSheetDialogFragment);

        void displayCachedCardsAndFetchServerCards();

        void displayMockedCards();

        void end();

        void fetchHomeCardsFromServer(boolean z, String str);

        int getViewBackgroundColor(int i);

        void handleClickToDismiss(int i, CardModel cardModel);

        void initRNPlaceholder();

        void onActivityDestroyed();

        void onBoundCardView(String str);

        void onCancelTimerAction(int i);

        void onConfigurationChange();

        void onCreatedCardView();

        void onHeaderVoxClick(Map<String, Object> map);

        void onHomeCardsViewFinishedLoading(boolean z);

        void onLayoutCompleted();

        void onLiveUpdateEvent();

        void onLocalCardEventReceived();

        void onLogOut();

        void onModalToggle(boolean z);

        void onNetworkChanged();

        void onPauseHome(RecyclerViewItem recyclerViewItem, int i, int i2);

        void onReactCardRenderRequest(String str);

        void onRefreshButtonClicked();

        void onRemoveCardClicked(int i, Map<String, Object> map, String str);

        void onRequestHomeRefreshEvent(boolean z);

        void onResumeHome(boolean z);

        void onScrolledToViewItem(RecyclerViewItem recyclerViewItem, int i, int i2, int i3);

        void onSkeletonSlideUpFinished();

        void onTWAClick(Map<String, Object> map, Action action);

        void onThreeDotsClicked(String str);

        void onVoxIngressFinishedLoading(boolean z);

        void onVoxIngressFinishedLoadingWithLogout(boolean z);

        void publishHomeInitializedEvent();

        void publishTWAAvailablityRequest();

        void recordCardsSeenOnColdStart(int i, int i2);

        void setLastRouteName(String str);

        void setOutageAlert(AlertBannerModel alertBannerModel);

        void start(boolean z);

        void subscribeToEventBus();

        void unsubscribeFromEventBus();
    }

    /* loaded from: classes10.dex */
    public interface RequestHomeRefreshListener {
        void onRequestHomeRefreshEvent(boolean z);
    }

    /* loaded from: classes10.dex */
    public interface View {
        void displayBottomSheet(BottomSheetDialogFragment bottomSheetDialogFragment);

        void displayErrorPage();

        Pair<Integer, CardModel> findDataByDismissId(String str);

        void finishRefresh();

        void forceUpdateViewItem(int i);

        boolean getCslOverride();

        int getFirstReturnToPosition();

        int getFirstVisibleItemPosition();

        int getLastReturnToPosition();

        int getLastVisibleItemPosition();

        boolean getLogoutFlag();

        double getPercentCardOnScreen(boolean z, int i);

        boolean getShouldVoxAnimateOnCacheLoad();

        int getTotalViewItems();

        /* renamed from: getViewItemFromPosition */
        RecyclerViewItem mo2380getViewItemFromPosition(int i);

        void goToPosition(int i);

        void handleRotation();

        void hideErrorPage();

        void initRNPlaceholder(Bundle bundle);

        boolean isViewAttached();

        void onCancelTimerAction(int i);

        void onErrorPageRetryClicked();

        void recordCardsSeenOnColdStart(int i, int i2);

        void registerReceivers();

        void removeCardAt(int i);

        void resetReturnPosition();

        void returnToLastVisitedCard();

        void returnToLastVisitedPosition();

        void revokeSwipeExperience();

        void setFirstRenderCompleteOnLogOut();

        void setLogoutFlag(boolean z);

        void setOfflineBannerVisibility(boolean z);

        void setOutageAlert(String str);

        void setRefreshButtonVisibility(boolean z);

        void setViewBackgroundColor(android.view.View view);

        boolean shouldRecordViewForCard(double d, int i);

        void unregisterReceivers();

        void updateAccessibilityOrder(@Nullable String str);

        void updateCardModel(int i, RecyclerViewItem recyclerViewItem);

        void updateCardModels(List<CardModel> list);
    }
}
