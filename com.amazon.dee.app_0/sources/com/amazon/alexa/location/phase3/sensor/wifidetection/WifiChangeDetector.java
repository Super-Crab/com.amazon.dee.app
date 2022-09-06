package com.amazon.alexa.location.phase3.sensor.wifidetection;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.location.phase3.LocationDataStore;
import com.amazon.alexa.location.phase3.LocationDataStoreService;
import com.amazon.alexa.location.phase3.evaluator.LocationEvent;
import com.amazon.alexa.location.phase3.sensor.wifidetection.WifiDetectionEvent;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
/* loaded from: classes9.dex */
public class WifiChangeDetector {
    private static final String WIFI_KEY = "WIFI_KEY";
    private final LocationDataStore<WifiDataInput> dataStore;

    public WifiChangeDetector(LocationDataStoreService locationDataStoreService) {
        this.dataStore = locationDataStoreService.getDataStore(LocationDataStoreService.LAST_WIFI_STATUS_TABLE, WifiDataInput.class);
    }

    public void clearData() {
        this.dataStore.removeAll();
    }

    public Observable<LocationEvent> evaluateEvent(final WifiDataInput wifiDataInput) {
        return Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.alexa.location.phase3.sensor.wifidetection.-$$Lambda$WifiChangeDetector$MquuWaZvyNGp87FYEEwXHwWLDW0
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                WifiChangeDetector.this.lambda$evaluateEvent$0$WifiChangeDetector(wifiDataInput, observableEmitter);
            }
        });
    }

    public /* synthetic */ void lambda$evaluateEvent$0$WifiChangeDetector(WifiDataInput wifiDataInput, ObservableEmitter observableEmitter) throws Throwable {
        WifiDataInput retrieveValue = this.dataStore.retrieveValue(WIFI_KEY);
        if ((retrieveValue != null ? retrieveValue.wifiStatus : 0) != wifiDataInput.wifiStatus) {
            this.dataStore.lambda$storeValueAsRx$1$LocationDataStore(WIFI_KEY, wifiDataInput);
            observableEmitter.onNext(new WifiDetectionEvent(wifiDataInput.timestamp, wifiDataInput.wifiStatus == 1 ? WifiDetectionEvent.TransitionType.ARRIVE : WifiDetectionEvent.TransitionType.LEAVE));
        }
        observableEmitter.onComplete();
    }

    @VisibleForTesting
    WifiChangeDetector(@NonNull LocationDataStore<WifiDataInput> locationDataStore) {
        this.dataStore = locationDataStore;
    }
}
