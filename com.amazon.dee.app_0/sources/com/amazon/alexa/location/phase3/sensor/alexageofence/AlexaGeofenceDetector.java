package com.amazon.alexa.location.phase3.sensor.alexageofence;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.location.models.ALSGeofence;
import com.amazon.alexa.location.phase3.ActiveGeofences;
import com.amazon.alexa.location.phase3.LocationDataStore;
import com.amazon.alexa.location.phase3.LocationDataStoreService;
import com.amazon.alexa.location.phase3.evaluator.LocationEvent;
import com.amazon.alexa.location.phase3.sensor.nativelocation.NativeLocationInput;
import com.amazon.alexa.location.utils.ExceptionRecordUtil;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes9.dex */
public class AlexaGeofenceDetector {
    private static final String TAG = "AlexaGeofenceDetector";
    private ActiveGeofences activeFences;
    private final LazyComponent<CrashReporter> crashReporter;
    @NonNull
    private Map<String, AlexaGeofenceState> currentStates = new HashMap();
    private Disposable fenceUpdaterDisposable;
    private final LocationDataStore<AlexaGeofenceState> stateStore;

    public AlexaGeofenceDetector(@NonNull LocationDataStoreService locationDataStoreService, @NonNull LazyComponent<CrashReporter> lazyComponent) {
        this.stateStore = locationDataStoreService.getDataStore(LocationDataStoreService.ALEXA_GEOFENCE_STATES_TABLE, AlexaGeofenceState.class);
        this.crashReporter = lazyComponent;
    }

    static /* synthetic */ void lambda$null$1() throws Throwable {
    }

    static /* synthetic */ void lambda$updateStates$5() throws Throwable {
    }

    public void clearData() {
        this.stateStore.removeAll();
    }

    public Observable<LocationEvent> evaluateEvent(@NonNull final NativeLocationInput nativeLocationInput) {
        return Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.alexa.location.phase3.sensor.alexageofence.-$$Lambda$AlexaGeofenceDetector$WTv30qDkUeBQfOki5sEvmqAz2nQ
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                AlexaGeofenceDetector.this.lambda$evaluateEvent$3$AlexaGeofenceDetector(nativeLocationInput, observableEmitter);
            }
        });
    }

    @NonNull
    @VisibleForTesting
    Map<String, ALSGeofence> getActiveFences() {
        ActiveGeofences activeGeofences = this.activeFences;
        return activeGeofences == null ? new HashMap() : activeGeofences;
    }

    @NonNull
    @VisibleForTesting
    Map<String, AlexaGeofenceState> getCurrentStates() {
        return this.currentStates;
    }

    public /* synthetic */ void lambda$evaluateEvent$3$AlexaGeofenceDetector(NativeLocationInput nativeLocationInput, ObservableEmitter observableEmitter) throws Throwable {
        for (ALSGeofence aLSGeofence : getActiveFences().values()) {
            AlexaGeofenceState alexaGeofenceState = new AlexaGeofenceState(aLSGeofence, nativeLocationInput);
            AlexaGeofenceState alexaGeofenceState2 = this.currentStates.get(aLSGeofence.getId());
            boolean z = true;
            if (alexaGeofenceState2 != null) {
                AlexaGeofenceTriggerEvent fromStates = AlexaGeofenceTriggerEvent.fromStates(alexaGeofenceState2, alexaGeofenceState);
                if (fromStates != null && fromStates.transitionType != -1) {
                    observableEmitter.onNext(fromStates);
                }
                z = true ^ alexaGeofenceState2.state.equals(alexaGeofenceState.state);
            }
            if (z) {
                this.currentStates.put(aLSGeofence.getId(), alexaGeofenceState);
                this.stateStore.storeValueAsRx(aLSGeofence.getId(), alexaGeofenceState).subscribe($$Lambda$AlexaGeofenceDetector$ltOLlklyG2nXB1wHc1XWiHbti8.INSTANCE, new Consumer() { // from class: com.amazon.alexa.location.phase3.sensor.alexageofence.-$$Lambda$AlexaGeofenceDetector$K6i3n3lR0pdhZTqv5WBSSybRM4U
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public final void accept(Object obj) {
                        AlexaGeofenceDetector.this.lambda$null$2$AlexaGeofenceDetector((Throwable) obj);
                    }
                });
            }
        }
        observableEmitter.onComplete();
    }

    public /* synthetic */ void lambda$null$2$AlexaGeofenceDetector(Throwable th) throws Throwable {
        ExceptionRecordUtil.recordExceptions(TAG, "stateStore.storeValueAsRx", th, this.crashReporter);
    }

    public /* synthetic */ void lambda$subscribeToActiveFences$0$AlexaGeofenceDetector(Throwable th) throws Throwable {
        ExceptionRecordUtil.recordExceptions(TAG, "activeFences.getUpdateSubject()", th, this.crashReporter);
    }

    public /* synthetic */ void lambda$updateStates$6$AlexaGeofenceDetector(Throwable th) throws Throwable {
        ExceptionRecordUtil.recordExceptions(TAG, "saveStates", th, this.crashReporter);
    }

    public void restoreStates() {
        Map<String, AlexaGeofenceState> retrieveAll = this.stateStore.retrieveAll();
        this.currentStates.clear();
        for (Map.Entry<String, AlexaGeofenceState> entry : retrieveAll.entrySet()) {
            if (getActiveFences().containsKey(entry.getKey())) {
                this.currentStates.put(entry.getKey(), entry.getValue());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public void saveStates() {
        this.stateStore.removeAll();
        for (Map.Entry<String, AlexaGeofenceState> entry : this.currentStates.entrySet()) {
            this.stateStore.lambda$storeValueAsRx$1$LocationDataStore(entry.getKey(), entry.getValue());
        }
    }

    public void subscribeToActiveFences(@NonNull ActiveGeofences activeGeofences) {
        this.activeFences = activeGeofences;
        this.fenceUpdaterDisposable = this.activeFences.getUpdateSubject().subscribe(new Consumer() { // from class: com.amazon.alexa.location.phase3.sensor.alexageofence.-$$Lambda$i5PF6Y6Z7ydFYWkWBggp8FrtMzk
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AlexaGeofenceDetector.this.updateStates((List) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.location.phase3.sensor.alexageofence.-$$Lambda$AlexaGeofenceDetector$FnqhnYlyCdbe29OMrK0Fo32UVII
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AlexaGeofenceDetector.this.lambda$subscribeToActiveFences$0$AlexaGeofenceDetector((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public void updateStates(@NonNull Collection<ALSGeofence> collection) {
        HashMap hashMap = new HashMap();
        for (ALSGeofence aLSGeofence : collection) {
            String id = aLSGeofence.getId();
            AlexaGeofenceState alexaGeofenceState = this.currentStates.get(id);
            if (alexaGeofenceState != null) {
                hashMap.put(id, alexaGeofenceState);
            }
        }
        this.currentStates.clear();
        this.currentStates.putAll(hashMap);
        Completable.fromAction(new Action() { // from class: com.amazon.alexa.location.phase3.sensor.alexageofence.-$$Lambda$OJ-e2bPpEcdP8lEFwJL60sAd7hY
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                AlexaGeofenceDetector.this.saveStates();
            }
        }).subscribeOn(Schedulers.io()).onErrorComplete($$Lambda$AlexaGeofenceDetector$cm3OTe3j2ZyStMF2mgL9ZNiOZ6o.INSTANCE).subscribe($$Lambda$AlexaGeofenceDetector$eT_BNFAQl22s2t5mIfYUO4gmcDQ.INSTANCE, new Consumer() { // from class: com.amazon.alexa.location.phase3.sensor.alexageofence.-$$Lambda$AlexaGeofenceDetector$vvdO49eSw8-s2wYIg7cgTkn-GFI
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AlexaGeofenceDetector.this.lambda$updateStates$6$AlexaGeofenceDetector((Throwable) obj);
            }
        });
    }

    @VisibleForTesting
    AlexaGeofenceDetector(@NonNull LocationDataStore<AlexaGeofenceState> locationDataStore, @NonNull LazyComponent<CrashReporter> lazyComponent) {
        this.stateStore = locationDataStore;
        this.crashReporter = lazyComponent;
    }
}
