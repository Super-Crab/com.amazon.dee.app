package com.amazon.alexa.redesign.interactor;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.EventBusException;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.redesign.DismissedCardDataStore;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.api.EventBusListener;
import com.amazon.alexa.redesign.debug.ActivityLauncher;
import com.amazon.alexa.redesign.debug.DebugMenuActivity;
import com.amazon.alexa.redesign.entity.CardModel;
import com.amazon.alexa.redesign.entity.DismissIdentifier;
import com.amazon.alexa.redesign.entity.templates.DomainCardTemplateModel;
import com.amazon.alexa.redesign.entity.templates.VoxIngressTemplateModel;
import com.amazon.alexa.redesign.repository.HomeCardsRepository;
import com.amazon.alexa.redesign.repository.VoxIngressRepository;
import com.amazon.alexa.redesign.subscriber.HomeSubscriber;
import com.amazon.alexa.redesign.view.templates.domainCardTemplate.EventCache;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes10.dex */
public class HomeInteractor implements HomeContract.Interactor {
    private static final String TAG = "HomeInteractor";
    private final ActivityLauncher activityLauncher;
    private DismissedCardDataStore dismissedCardDataStore;
    private final EventBus eventBus;
    private final EventCache eventCache;
    private HomeCardsRepository homeCardsRepository;
    @VisibleForTesting
    HomeSubscriber homeSubscriber;
    @VisibleForTesting
    final AtomicBoolean needRefresh = new AtomicBoolean(false);
    private NetworkService networkService;
    private VoxIngressRepository voxIngressRepository;

    public HomeInteractor(HomeCardsRepository homeCardsRepository, VoxIngressRepository voxIngressRepository, NetworkService networkService, DismissedCardDataStore dismissedCardDataStore, EventCache eventCache, EventBus eventBus, ActivityLauncher activityLauncher, HomeContract.RequestHomeRefreshListener requestHomeRefreshListener, HomeContract.LogOutListener logOutListener, HomeContract.ModalToggleListener modalToggleListener, HomeContract.LocalCardListener localCardListener, HomeContract.CardDismissedListener cardDismissedListener, HomeContract.LiveUpdatesListener liveUpdatesListener, EventBusListener eventBusListener, EventBusListener eventBusListener2) {
        this.homeCardsRepository = homeCardsRepository;
        this.voxIngressRepository = voxIngressRepository;
        this.networkService = networkService;
        this.dismissedCardDataStore = dismissedCardDataStore;
        this.eventCache = eventCache;
        this.eventBus = eventBus;
        this.activityLauncher = activityLauncher;
        this.homeSubscriber = new HomeSubscriber(homeCardsRepository, requestHomeRefreshListener, logOutListener, modalToggleListener, localCardListener, cardDismissedListener, dismissedCardDataStore, liveUpdatesListener, eventBusListener, eventBusListener2);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Interactor
    public void clearDismissedCardIds() {
        this.dismissedCardDataStore.clearDismissedCardDataStore();
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Interactor
    public void clearEventCacheClickEvents() {
        this.eventCache.clearClickEvents();
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Interactor
    public Completable clearHomeCardsFromCache() {
        return this.homeCardsRepository.clearHomeCardsFromCache();
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Interactor
    public void clearLocalCards() {
        this.homeCardsRepository.clearLocalCards();
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Interactor
    public void deleteDismissedCardId(DismissIdentifier dismissIdentifier, String str) {
        this.dismissedCardDataStore.deleteDismissedCardIds(dismissIdentifier, str);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Interactor
    public Single<List<CardModel>> getCardsFromMockedData() {
        return this.homeCardsRepository.getCardsFromMockedData();
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Interactor
    public List<DismissIdentifier> getDismissedCardIds(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            arrayList.addAll(this.dismissedCardDataStore.getDismissedCardIds(str));
        }
        return arrayList;
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Interactor
    public Single<List<DomainCardTemplateModel>> getLocalCards() {
        return this.homeCardsRepository.getLocalCards();
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Interactor
    public Bundle getRNPlaceholderBundle() {
        return GeneratedOutlineSupport1.outline11("modalType", "ContinueListeningDevicePicker");
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Interactor
    public Single<List<CardModel>> getRawHomeCardsFromCache() {
        return this.homeCardsRepository.getRawHomeCardsFromCache();
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Interactor
    public Single<List<CardModel>> getRawHomeCardsFromServer() {
        return this.homeCardsRepository.getRawHomeCardsFromServer();
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Interactor
    public VoxIngressTemplateModel getVoxIngressModel() {
        return this.voxIngressRepository.getModel();
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Interactor
    public void launchDebugMenu() {
        this.activityLauncher.launchActivity(DebugMenuActivity.class, null);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Interactor
    public boolean needRefresh() {
        return this.needRefresh.get();
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Interactor
    public void processEarlyEventBusMessages(List<Message> list) {
        for (Message message : list) {
            this.homeSubscriber.onMessageReceived(message);
        }
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Interactor
    public void publishEventBusMessage(Message message) {
        this.eventBus.publish(message);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Interactor
    public void requestRefresh() {
        this.needRefresh.set(true);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Interactor
    public void resetNeedRefreshValue() {
        this.needRefresh.set(false);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Interactor
    public void saveDismissedCardId(DismissIdentifier dismissIdentifier, String str) {
        this.dismissedCardDataStore.putDismissedCardIds(dismissIdentifier, str);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Interactor
    public void subscribeToEventBus() {
        try {
            this.eventBus.subscribe(this.homeSubscriber);
        } catch (EventBusException e) {
            String str = TAG;
            Log.e(str, "Event bus rejected subscription: " + e);
        }
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Interactor
    public void unsubscribeFromEventBus() {
        try {
            this.eventBus.unsubscribe(this.homeSubscriber);
        } catch (EventBusException e) {
            String str = TAG;
            Log.e(str, "Event bus rejected subscription: " + e);
        }
    }

    @Override // com.amazon.alexa.redesign.HomeContract.Interactor
    public boolean validateNetworkConnection() {
        return this.networkService.isConnected();
    }
}
