package com.amazon.alexa.location.provider.interactor;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.location.provider.interactor.sync.SyncInteractor;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes9.dex */
public class EventBusInteractor {
    private static final String TAG = "EventBusInteractor";
    @VisibleForTesting
    final Map<SyncInteractor, MultiFilterSubscriber.FilterUuid> activeSyncInteractors = new HashMap();
    private final LazyComponent<EventBus> eventBus;
    @VisibleForTesting
    MultiFilterSubscriber eventBusSubscriber;

    public EventBusInteractor(@NonNull LazyComponent<EventBus> lazyComponent) {
        this.eventBus = lazyComponent;
    }

    public void subscribeToEventBus(@NonNull SyncInteractor syncInteractor) {
        if (this.activeSyncInteractors.containsKey(syncInteractor)) {
            String.format("Interactor %s is already subscribing to Event Bus; ignoring latest subscribe request", syncInteractor.getName());
            return;
        }
        if (this.eventBusSubscriber == null) {
            this.eventBusSubscriber = this.eventBus.mo10268get().getNewSubscriber();
        }
        String.format("Starting interactor %s's subscription to Event Bus", syncInteractor.getName());
        this.activeSyncInteractors.put(syncInteractor, this.eventBusSubscriber.subscribeFilter(syncInteractor.getMessageFilter(), syncInteractor.getMessageHandler()));
    }

    public void unsubscribeToEventBus(@NonNull SyncInteractor syncInteractor) {
        if (!this.activeSyncInteractors.containsKey(syncInteractor)) {
            String.format("Interactor %s isn't subscribing to Event Bus; ignoring unsubscribe request", syncInteractor.getName());
        } else if (this.eventBusSubscriber == null) {
            Log.e(TAG, "Subscriber lost - clearing remaining sync interactor entries");
            this.activeSyncInteractors.clear();
        } else {
            String.format("Stopping interactor %s's subscription to Event Bus", syncInteractor.getName());
            this.eventBusSubscriber.unsubscribeFilter((MultiFilterSubscriber.FilterUuid) Objects.requireNonNull(this.activeSyncInteractors.get(syncInteractor)));
            this.activeSyncInteractors.remove(syncInteractor);
            if (!this.activeSyncInteractors.isEmpty()) {
                return;
            }
            this.eventBusSubscriber = null;
        }
    }
}
