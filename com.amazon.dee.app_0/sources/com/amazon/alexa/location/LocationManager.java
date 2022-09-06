package com.amazon.alexa.location;

import android.content.Intent;
import android.location.Location;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.location.models.ALSGeofence;
import com.amazon.alexa.location.models.GeoFenceStatus;
import com.amazon.alexa.location.models.PersonalizationLocationItem;
import com.amazon.alexa.location.networkModels.GetGeofenceByDeviceResponseBody;
import com.amazon.alexa.location.networkModels.PersonalizationLocationResponseBody;
import com.amazon.alexa.location.utils.Constants;
import com.amazon.alexa.location.utils.ExceptionRecordUtil;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.alexa.location.utils.MobilyticsUtil;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingEvent;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.gson.Gson;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Function3;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.Subject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class LocationManager {
    private static final String COMPONENT_SYNC_GEOFENCE = MobilyticsUtil.getComponentName("sync_geofence");
    private static final String COMPONENT_TRIGGER_GEOFENCE = MobilyticsUtil.getComponentName("trigger_geofence");
    public static final String GEOFENCE_OS_TRIGGER_DETECTED_TIME = "geofence_os_trigger_detected_time";
    private final LazyComponent<CrashReporter> crashReporter;
    private LocationDataService dataService;
    private GeofenceController geofenceController;
    private Subject<List<ALSGeofence>> geofencesSubject = BehaviorSubject.create();
    private Gson gson;
    private LocationProvider locationProvider;
    private final LazyComponent<Mobilytics> mobilytics;
    private LocationNetworkService networkService;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public enum Label {
        ADD(0),
        REMOVE(1),
        NEW(2);
        
        int label;

        Label(int i) {
            this.label = i;
        }
    }

    public LocationManager(LocationNetworkService locationNetworkService, LocationDataService locationDataService, LazyComponent<Mobilytics> lazyComponent, GeofenceController geofenceController, LocationProvider locationProvider, Gson gson, @NonNull final LazyComponent<CrashReporter> lazyComponent2) {
        this.networkService = locationNetworkService;
        this.dataService = locationDataService;
        this.mobilytics = lazyComponent;
        this.geofenceController = geofenceController;
        this.locationProvider = locationProvider;
        this.gson = gson;
        this.crashReporter = lazyComponent2;
        Single<List<ALSGeofence>> geofences = locationDataService.getGeofences();
        final Subject<List<ALSGeofence>> subject = this.geofencesSubject;
        subject.getClass();
        geofences.subscribe(new Consumer() { // from class: com.amazon.alexa.location.-$$Lambda$Gmg4bgS88HEQwGtcckxifG1YkVM
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Subject.this.onNext((List) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.location.-$$Lambda$LocationManager$610wv6RfTy9TONmbbJ-TCW3L7_M
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                LocationManager.this.lambda$new$0$LocationManager(lazyComponent2, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<Label, List<ALSGeofence>> classify(List<ALSGeofence> list, List<ALSGeofence> list2, List<ALSGeofence> list3) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        for (ALSGeofence aLSGeofence : list) {
            hashMap2.put(aLSGeofence.getId(), aLSGeofence);
        }
        for (ALSGeofence aLSGeofence2 : list3) {
            hashMap2.put(aLSGeofence2.getId(), aLSGeofence2);
        }
        ArrayList<ALSGeofence> arrayList = new ArrayList(hashMap2.values());
        hashMap.put(Label.ADD, arrayList);
        HashMap hashMap3 = new HashMap();
        for (ALSGeofence aLSGeofence3 : list2) {
            hashMap3.put(aLSGeofence3.getId(), aLSGeofence3);
        }
        for (ALSGeofence aLSGeofence4 : arrayList) {
            if (hashMap3.containsKey(aLSGeofence4.getId())) {
                hashMap3.remove(aLSGeofence4.getId());
            }
        }
        hashMap.put(Label.REMOVE, new ArrayList(hashMap3.values()));
        hashMap.put(Label.NEW, list3);
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Single<JSONObject> convertGeofencesToJsonObject(final List<ALSGeofence> list) {
        return Single.fromCallable(new Callable() { // from class: com.amazon.alexa.location.-$$Lambda$LocationManager$JSgR-ujnFkeFmLMM1qcUshGEYwM
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return LocationManager.this.lambda$convertGeofencesToJsonObject$11$LocationManager(list);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Single<JSONObject> convertLocationItemsToJsonObject(final List<PersonalizationLocationItem> list) {
        return Single.fromCallable(new Callable() { // from class: com.amazon.alexa.location.-$$Lambda$LocationManager$Ru2v-Yi5ip7kbJU9_w8zqJXtwVQ
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return LocationManager.this.lambda$convertLocationItemsToJsonObject$12$LocationManager(list);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$getGeofenceStates$9(List list, Location location) throws Throwable {
        ArrayList arrayList = new ArrayList();
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList.add(new PersonalizationLocationItem.Builder(location).withALSGeofence((ALSGeofence) it2.next()).build());
        }
        arrayList.add(new PersonalizationLocationItem.Builder(location).build());
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$syncGeofence$7(Map map) throws Throwable {
        return (List) map.get(Label.ADD);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Map lambda$syncToOS$13(Map map, List list, List list2) throws Throwable {
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Single<Map<Label, List<ALSGeofence>>> syncToDataStorage(Map<Label, List<ALSGeofence>> map) {
        this.geofencesSubject.onNext(map.get(Label.ADD));
        return this.dataService.saveGeofences(map.get(Label.ADD)).andThen(Single.just(map));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Single<Map<Label, List<ALSGeofence>>> syncToOS(final Map<Label, List<ALSGeofence>> map) {
        return Single.zip(this.geofenceController.addGeofences(map.get(Label.ADD)), this.geofenceController.removeGeofences(map.get(Label.REMOVE)), new BiFunction() { // from class: com.amazon.alexa.location.-$$Lambda$LocationManager$ROJi4XC87FITLxH4Gksll1GzyvE
            @Override // io.reactivex.rxjava3.functions.BiFunction
            public final Object apply(Object obj, Object obj2) {
                Map map2 = map;
                LocationManager.lambda$syncToOS$13(map2, (List) obj, (List) obj2);
                return map2;
            }
        });
    }

    public Completable clearGeofences() {
        return Single.zip(Single.just(new ArrayList()), this.dataService.getGeofences(), new BiFunction<List<ALSGeofence>, List<ALSGeofence>, Map<Label, List<ALSGeofence>>>() { // from class: com.amazon.alexa.location.LocationManager.7
            @Override // io.reactivex.rxjava3.functions.BiFunction
            public Map<Label, List<ALSGeofence>> apply(@NonNull List<ALSGeofence> list, @NonNull List<ALSGeofence> list2) throws Exception {
                return LocationManager.this.classify(list, list2, new ArrayList());
            }
        }).flatMap(new Function<Map<Label, List<ALSGeofence>>, Single<Map<Label, List<ALSGeofence>>>>() { // from class: com.amazon.alexa.location.LocationManager.6
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public Single<Map<Label, List<ALSGeofence>>> mo10358apply(@NonNull Map<Label, List<ALSGeofence>> map) throws Exception {
                return LocationManager.this.syncToOS(map);
            }
        }).flatMap(new Function<Map<Label, List<ALSGeofence>>, Single<Map<Label, List<ALSGeofence>>>>() { // from class: com.amazon.alexa.location.LocationManager.5
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public Single<Map<Label, List<ALSGeofence>>> mo10358apply(@NonNull Map<Label, List<ALSGeofence>> map) throws Exception {
                return LocationManager.this.syncToDataStorage(map);
            }
        }).ignoreElement();
    }

    public Single<String> createGeofence(@NonNull String str, @NonNull String str2, double d, double d2, double d3) {
        return Single.zip(this.networkService.getGeofencesByDevice(str, str2, null), this.networkService.createGeofence(str, str2, d, d2, d3), this.dataService.getGeofences(), new Function3() { // from class: com.amazon.alexa.location.-$$Lambda$LocationManager$HkSOQRizlsIka8eiNYdKMCUZKwo
            @Override // io.reactivex.rxjava3.functions.Function3
            public final Object apply(Object obj, Object obj2, Object obj3) {
                return LocationManager.this.lambda$createGeofence$1$LocationManager((List) obj, (ALSGeofence) obj2, (List) obj3);
            }
        }).flatMap(new Function<Map<Label, List<ALSGeofence>>, Single<Map<Label, List<ALSGeofence>>>>() { // from class: com.amazon.alexa.location.LocationManager.2
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public Single<Map<Label, List<ALSGeofence>>> mo10358apply(@NonNull Map<Label, List<ALSGeofence>> map) throws Exception {
                return LocationManager.this.syncToOS(map);
            }
        }).flatMap(new Function<Map<Label, List<ALSGeofence>>, Single<Map<Label, List<ALSGeofence>>>>() { // from class: com.amazon.alexa.location.LocationManager.1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public Single<Map<Label, List<ALSGeofence>>> mo10358apply(@NonNull Map<Label, List<ALSGeofence>> map) throws Exception {
                return LocationManager.this.syncToDataStorage(map);
            }
        }).map($$Lambda$LocationManager$5B718xTxuM3aHtV8wF_zNWKRRuI.INSTANCE);
    }

    public Single<JSONObject> getGeofenceStates() {
        return Single.zip(this.dataService.getGeofences(), this.locationProvider.getMostRecentLocation(), $$Lambda$LocationManager$sbQ8tNXLFT8sFM2pK3vrMXgqP5w.INSTANCE).flatMap(new Function() { // from class: com.amazon.alexa.location.-$$Lambda$LocationManager$622FE6loES-XMj0TkGt5j0owqS0
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                Single convertLocationItemsToJsonObject;
                convertLocationItemsToJsonObject = LocationManager.this.convertLocationItemsToJsonObject((List) obj);
                return convertLocationItemsToJsonObject;
            }
        });
    }

    public Single<LocationSettingsStates> getLocationSettings() {
        return this.geofenceController.getLocationSettings();
    }

    public Single<JSONObject> getRegisteredGeofences() {
        return this.dataService.getGeofences().flatMap(new Function() { // from class: com.amazon.alexa.location.-$$Lambda$LocationManager$5LT_7ka6rBMaOhCUwjytrwdCqiw
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                Single convertGeofencesToJsonObject;
                convertGeofencesToJsonObject = LocationManager.this.convertGeofencesToJsonObject((List) obj);
                return convertGeofencesToJsonObject;
            }
        });
    }

    public Observable<TriggeringGeofenceInfo> getTriggeringGeofenceInfo(final Intent intent) {
        final long longExtra = intent.getLongExtra(GEOFENCE_OS_TRIGGER_DETECTED_TIME, Calendar.getInstance().getTimeInMillis());
        return Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.alexa.location.-$$Lambda$LocationManager$L1wuQoefK9YVrlScMcOKlNbgmNE
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                LocationManager.this.lambda$getTriggeringGeofenceInfo$10$LocationManager(intent, longExtra, observableEmitter);
            }
        });
    }

    public /* synthetic */ JSONObject lambda$convertGeofencesToJsonObject$11$LocationManager(List list) throws Exception {
        return new JSONObject(this.gson.toJson(new GetGeofenceByDeviceResponseBody(list)));
    }

    public /* synthetic */ JSONObject lambda$convertLocationItemsToJsonObject$12$LocationManager(List list) throws Exception {
        return new JSONObject(this.gson.toJson(new PersonalizationLocationResponseBody(list)));
    }

    public /* synthetic */ Map lambda$createGeofence$1$LocationManager(List list, ALSGeofence aLSGeofence, List list2) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(aLSGeofence);
        return classify(list, list2, arrayList);
    }

    public /* synthetic */ void lambda$getTriggeringGeofenceInfo$10$LocationManager(Intent intent, long j, ObservableEmitter observableEmitter) throws Throwable {
        GeofencingEvent fromIntent = GeofencingEvent.fromIntent(intent);
        if (fromIntent != null && !fromIntent.hasError()) {
            int geofenceTransition = fromIntent.getGeofenceTransition();
            boolean z = false;
            boolean z2 = geofenceTransition == 1;
            if (geofenceTransition == 2) {
                z = true;
            }
            if (z | z2) {
                for (Geofence geofence : fromIntent.getTriggeringGeofences()) {
                    MetricsUtil.recordOccurrence(this.mobilytics, (String) Objects.requireNonNull(Constants.GEOFENCE_TRANSITION_TO_METRICS_ID.mo7740get(Integer.valueOf(geofenceTransition))), "", true);
                    observableEmitter.onNext(new TriggeringGeofenceInfo(geofence.getRequestId(), geofenceTransition, j));
                }
            }
            observableEmitter.onComplete();
            return;
        }
        String str = GeofenceController.TAG;
        if (fromIntent != null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(MobilyticsUtil.MetricsID.GEOFENCE_INFO_FAILURE);
            outline107.append(GeofenceStatusCodes.getStatusCodeString(fromIntent.getErrorCode()));
            String sb = outline107.toString();
            String str2 = COMPONENT_TRIGGER_GEOFENCE;
            this.mobilytics.mo10268get().recordOccurrence(sb, true, str2, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        } else {
            String str3 = COMPONENT_TRIGGER_GEOFENCE;
            this.mobilytics.mo10268get().recordOccurrence("geofence_info_failure_GEOFENCE_EVENT_NULL", true, str3, str3, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        }
        observableEmitter.onError(new LocationException(LocationErrorCode.GENERIC_ERROR, "[ERROR] Trigger events happened, but fail to initiate the ALS call. "));
    }

    public /* synthetic */ void lambda$new$0$LocationManager(LazyComponent lazyComponent, Throwable th) throws Throwable {
        this.geofencesSubject.onNext(Collections.EMPTY_LIST);
        ExceptionRecordUtil.recordExceptions(GeofenceController.TAG, "dataService.getGeofences() will continue with empty list", th, lazyComponent);
    }

    public /* synthetic */ SingleSource lambda$restoreGeofences$8$LocationManager(List list) throws Throwable {
        return this.geofenceController.addGeofences(list);
    }

    public /* synthetic */ Map lambda$syncGeofence$5$LocationManager(List list, List list2) throws Throwable {
        String str = COMPONENT_SYNC_GEOFENCE;
        MobilyticsMetricsCounter createCounter = this.mobilytics.mo10268get().createCounter("fetch_successful", str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        createCounter.incrementCounterByValue(list.size());
        this.mobilytics.mo10268get().recordCounter(createCounter);
        return classify(list, list2, new ArrayList());
    }

    public /* synthetic */ void lambda$syncGeofence$6$LocationManager(Map map) throws Throwable {
        String str = COMPONENT_SYNC_GEOFENCE;
        MobilyticsMetricsCounter createCounter = this.mobilytics.mo10268get().createCounter(MobilyticsUtil.MetricsID.REGISTERED_NEW_FENCES, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        createCounter.incrementCounterByValue(((List) map.get(Label.ADD)).size());
        this.mobilytics.mo10268get().recordCounter(createCounter);
        String str2 = COMPONENT_SYNC_GEOFENCE;
        MobilyticsMetricsCounter createCounter2 = this.mobilytics.mo10268get().createCounter(MobilyticsUtil.MetricsID.REMOVED_FENCES, str2, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        createCounter2.incrementCounterByValue(((List) map.get(Label.REMOVE)).size());
        this.mobilytics.mo10268get().recordCounter(createCounter2);
    }

    public /* synthetic */ Map lambda$updateGeofence$3$LocationManager(List list, ALSGeofence aLSGeofence, List list2) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(aLSGeofence);
        return classify(list, list2, arrayList);
    }

    public Observable<List<ALSGeofence>> onGeofencesServed() {
        return this.geofencesSubject;
    }

    public Completable reportGeoFenceStatus(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull List<GeoFenceStatus> list) {
        if (list.isEmpty()) {
            String str4 = GeofenceController.TAG;
            return Completable.complete();
        }
        return this.networkService.reportGeofenceStatus(str, str2, str3, list);
    }

    public Single<List<ALSGeofence>> restoreGeofences() {
        return this.dataService.getGeofences().flatMap(new Function() { // from class: com.amazon.alexa.location.-$$Lambda$LocationManager$E30K6M09MaTvlKuPr-Yb2Ck-MNA
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return LocationManager.this.lambda$restoreGeofences$8$LocationManager((List) obj);
            }
        });
    }

    public Single<List<ALSGeofence>> syncGeofence(@NonNull String str, @NonNull String str2, @Nullable String str3) {
        return Single.zip(this.networkService.getGeofencesByDevice(str, str2, str3), this.dataService.getGeofences(), new BiFunction() { // from class: com.amazon.alexa.location.-$$Lambda$LocationManager$cirjcN01_uv1rTdsrhXnsXZyaCg
            @Override // io.reactivex.rxjava3.functions.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return LocationManager.this.lambda$syncGeofence$5$LocationManager((List) obj, (List) obj2);
            }
        }).flatMap(new Function() { // from class: com.amazon.alexa.location.-$$Lambda$LocationManager$2zPcbYALM9QEVRh2Czcftwy7OUU
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                Single syncToOS;
                syncToOS = LocationManager.this.syncToOS((Map) obj);
                return syncToOS;
            }
        }).doOnSuccess(new Consumer() { // from class: com.amazon.alexa.location.-$$Lambda$LocationManager$rbE0iesSucdCBxIUKKVntufa3rA
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                LocationManager.this.lambda$syncGeofence$6$LocationManager((Map) obj);
            }
        }).flatMap(new Function() { // from class: com.amazon.alexa.location.-$$Lambda$LocationManager$bTjuNNZ_jq1u7WWDj_1HTQrM54o
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                Single syncToDataStorage;
                syncToDataStorage = LocationManager.this.syncToDataStorage((Map) obj);
                return syncToDataStorage;
            }
        }).map($$Lambda$LocationManager$rwClHh_40Ql_0iisLFi2e5w8A9Q.INSTANCE);
    }

    public Observable<String> triggerGeofence(@NonNull String str, @NonNull String str2, @NonNull String str3, TriggeringGeofenceInfo triggeringGeofenceInfo) {
        return this.networkService.triggerGeofence(str, str2, triggeringGeofenceInfo.getFenceId(), str3, triggeringGeofenceInfo.getALSTrigerEvent(), triggeringGeofenceInfo.getTime()).toObservable();
    }

    public Single<String> updateGeofence(@NonNull String str, @NonNull String str2, @NonNull String str3, double d, double d2, double d3) {
        return Single.zip(this.networkService.getGeofencesByDevice(str, str2, null), this.networkService.updateGeofence(str, str2, str3, d, d2, d3), this.dataService.getGeofences(), new Function3() { // from class: com.amazon.alexa.location.-$$Lambda$LocationManager$8H-Idr3I_esmXFIwDfgpI0_vQw4
            @Override // io.reactivex.rxjava3.functions.Function3
            public final Object apply(Object obj, Object obj2, Object obj3) {
                return LocationManager.this.lambda$updateGeofence$3$LocationManager((List) obj, (ALSGeofence) obj2, (List) obj3);
            }
        }).flatMap(new Function<Map<Label, List<ALSGeofence>>, Single<Map<Label, List<ALSGeofence>>>>() { // from class: com.amazon.alexa.location.LocationManager.4
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public Single<Map<Label, List<ALSGeofence>>> mo10358apply(@NonNull Map<Label, List<ALSGeofence>> map) throws Exception {
                return LocationManager.this.syncToOS(map);
            }
        }).flatMap(new Function<Map<Label, List<ALSGeofence>>, Single<Map<Label, List<ALSGeofence>>>>() { // from class: com.amazon.alexa.location.LocationManager.3
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public Single<Map<Label, List<ALSGeofence>>> mo10358apply(@NonNull Map<Label, List<ALSGeofence>> map) throws Exception {
                return LocationManager.this.syncToDataStorage(map);
            }
        }).map($$Lambda$LocationManager$iGSgOfg356XrvnQihZNVyvGxbS8.INSTANCE);
    }
}
