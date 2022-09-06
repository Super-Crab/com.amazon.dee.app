package com.amazon.alexa.location;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.location.models.ALSGeofence;
import com.amazon.alexa.location.models.ALSTriggerEvent;
import com.amazon.alexa.location.models.GeoFenceStatus;
import com.amazon.alexa.location.phase3.LocationDataStore;
import com.amazon.alexa.location.phase3.LocationDataStoreService;
import com.amazon.alexa.location.phase3.Phase3LocationService;
import com.amazon.alexa.location.utils.ExceptionRecordUtil;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.alexa.location.utils.MobilyticsUtil;
import com.amazon.alexa.location.utils.WriteToFile;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.location.LocationSettingsStates;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;
import org.reactivestreams.Publisher;
import rx.Observable;
import rx.functions.Action1;
/* loaded from: classes9.dex */
public class DefaultLocationService implements LocationService, MainActivityLifecycleObserver {
    private static final int INITIAL_DELAY_INTERVAL_MILLI_SEC = 1000;
    private static final String OBSERVE_USER_CHANGES_METRICS_METHOD = "observeUserChanges";
    private static final int RETRY_COUNT = 3;
    @NonNull
    private final Context context;
    private final LazyComponent<CrashReporter> crashReporter;
    @Nullable
    private String currentPersonId;
    private final LazyComponent<FeatureServiceV2> featureServiceV2 = ComponentRegistry.getInstance().getLazy(FeatureServiceV2.class);
    private final GeofenceEventHandler geofenceEventHandler;
    private final IdentityService identityService;
    @VisibleForTesting
    boolean isGeofenceEnabled;
    @VisibleForTesting
    boolean isLocationPermissionEnabled;
    private LocationSettingsStates lastLocationSettingsStates;
    private final LocationDataStoreService locationDataStoreService;
    private final LocationManager locationManager;
    private final LocationPermissionService locationPermissionService;
    private final LocationSettingsRecorder locationSettingsRecorder;
    private final MainActivityLifecycleObserverRegistrar mainActivityLifecycleObserverRegistrar;
    private final LazyComponent<Mobilytics> mobilytics;
    private final LocationNetworkServiceConfigProvider networkServiceConfigProvider;
    private final PersonIdProvider personIdProvider;
    private final Observable<UserIdentity> userChangeObservable;
    private static final String TAG = "DefaultLocationService";
    private static final String COMPONENT_PHASE3 = MobilyticsUtil.getComponentName(TAG);
    private static final String COMPONENT_TRIGGER_GEOFENCE = MobilyticsUtil.getComponentName("trigger_geofence");
    private static final String COMPONENT_SYNC_GEOFENCE = MobilyticsUtil.getComponentName("sync_geofence");
    private static final String COMPONENT_PERMISSION = MobilyticsUtil.getComponentName("permission");
    private static final String COMPONENT_SETTING = MobilyticsUtil.getComponentName("setting");
    private static final String COMPONENT_FEATURE = MobilyticsUtil.getComponentName("feature");
    private static final Double EXPONENTIAL_RETRY_FACTOR = Double.valueOf(2.0d);
    private static final Map<LocationErrorCode, String> METRICS_MAP = MobilyticsUtil.METRICS_NAME_ERROR_PART;

    /* renamed from: com.amazon.alexa.location.DefaultLocationService$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$location$LocationErrorCode = new int[LocationErrorCode.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$location$LocationErrorCode[LocationErrorCode.ALS_TIMEOUT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$location$LocationErrorCode[LocationErrorCode.ALS_401.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public DefaultLocationService(IdentityService identityService, PersonIdProvider personIdProvider, MainActivityLifecycleObserverRegistrar mainActivityLifecycleObserverRegistrar, LazyComponent<Mobilytics> lazyComponent, LocationManager locationManager, LocationPermissionService locationPermissionService, GeofenceEventHandler geofenceEventHandler, LocationDataStoreService locationDataStoreService, LocationSettingsRecorder locationSettingsRecorder, @NonNull LazyComponent<CrashReporter> lazyComponent2, LocationNetworkServiceConfigProvider locationNetworkServiceConfigProvider, Observable<UserIdentity> observable, @NonNull Context context) {
        this.identityService = identityService;
        this.personIdProvider = personIdProvider;
        this.mainActivityLifecycleObserverRegistrar = mainActivityLifecycleObserverRegistrar;
        this.mobilytics = lazyComponent;
        this.locationManager = locationManager;
        this.locationPermissionService = locationPermissionService;
        this.geofenceEventHandler = geofenceEventHandler;
        this.locationDataStoreService = locationDataStoreService;
        this.locationSettingsRecorder = locationSettingsRecorder;
        this.crashReporter = lazyComponent2;
        this.networkServiceConfigProvider = locationNetworkServiceConfigProvider;
        this.isLocationPermissionEnabled = locationPermissionService.hasFullLocationPermission();
        this.userChangeObservable = observable;
        this.context = context;
    }

    private boolean couldRefreshAuthToken() {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.identityService.refresh(TAG).toBlocking().subscribe(new Action1() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$Se_hxWZmRcoj5dvfA_gyPvhXuCM
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                UserIdentity userIdentity = (UserIdentity) obj;
                atomicBoolean.set(true);
            }
        }, new Action1() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$zyS-MDwUGE_dWeD88m8S1YaheZI
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DefaultLocationService.this.lambda$couldRefreshAuthToken$29$DefaultLocationService((Throwable) obj);
            }
        });
        return atomicBoolean.get();
    }

    private long getBackoffInterval(int i, double d, int i2) {
        return (long) (Math.pow(d, i2 - 1) * i);
    }

    private String getTriggerEventType(TriggeringGeofenceInfo triggeringGeofenceInfo) {
        ALSTriggerEvent aLSTrigerEvent = triggeringGeofenceInfo.getALSTrigerEvent();
        return aLSTrigerEvent.equals(ALSTriggerEvent.ENTER) ? "_enter" : aLSTrigerEvent.equals(ALSTriggerEvent.EXIT) ? "_exit" : "";
    }

    private String getWorkflowFailureMetricNameByReason(Throwable th) {
        String sb;
        if (th instanceof LocationException) {
            LocationErrorCode errorCode = ((LocationException) th).getErrorCode();
            if (errorCode.equals(LocationErrorCode.ALS_AUTH_TOKEN_ERROR)) {
                sb = MobilyticsUtil.MetricsID.ALS_WORKFLOW_FAILURE_TOKEN_ACCESS_FAIL;
            } else if (!METRICS_MAP.containsKey(errorCode)) {
                return MobilyticsUtil.MetricsID.ALS_WORKFLOW_FAILURE_OTHERS;
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107(MobilyticsUtil.MetricsID.ALS_WORKFLOW_FAILURE_RETRIES_EXCEEDS);
                outline107.append(METRICS_MAP.get(errorCode));
                sb = outline107.toString();
            }
            return sb;
        }
        return MobilyticsUtil.MetricsID.ALS_WORKFLOW_FAILURE_OTHERS;
    }

    private Function<io.reactivex.rxjava3.core.Observable<Throwable>, ObservableSource<?>> integrateRetryTriggerGeofence(final TriggeringGeofenceInfo triggeringGeofenceInfo, final int i, final double d, final int i2, final MobilyticsMetricsCounter mobilyticsMetricsCounter, final MobilyticsMetricsCounter mobilyticsMetricsCounter2) {
        return new Function() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$ehCWZYt2BGrtjMjgR-cLQNN7q4w
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultLocationService.this.lambda$integrateRetryTriggerGeofence$27$DefaultLocationService(triggeringGeofenceInfo, i2, i, d, mobilyticsMetricsCounter, mobilyticsMetricsCounter2, (io.reactivex.rxjava3.core.Observable) obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Integer lambda$null$17(Throwable th, Integer num) throws Throwable {
        return num;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ObservableSource lambda$null$26(io.reactivex.rxjava3.core.Observable observable) throws Throwable {
        return observable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$30(List list) throws Throwable {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$32() throws Throwable {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$34(List list) throws Throwable {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Publisher lambda$null$5(AtomicInteger atomicInteger, Throwable th) throws Throwable {
        if (atomicInteger.get() != 3) {
            return Flowable.just(th);
        }
        return Flowable.error(th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$null$9() throws Throwable {
        return "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onActivityCreated$38(List list) throws Throwable {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onActivityResume$40(List list) throws Throwable {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ObservableSource lambda$triggerGeofence$15(io.reactivex.rxjava3.core.Observable observable, LocationNetworkServiceConfig locationNetworkServiceConfig) throws Throwable {
        return observable;
    }

    private void observeFeatureAvailability() {
        this.isGeofenceEnabled = this.featureServiceV2.mo10268get().hasAccess("GEOFENCE_ANDROID", false);
        this.currentPersonId = this.personIdProvider.getPersonId();
        this.userChangeObservable.subscribe(new Action1() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$9zOkTMmK_J3n790A0IL0UZmVSuo
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DefaultLocationService.this.lambda$observeFeatureAvailability$36$DefaultLocationService((UserIdentity) obj);
            }
        }, new Action1() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$ha2m30w0xNuCVGVLXLbrSPj0nXI
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DefaultLocationService.this.lambda$observeFeatureAvailability$37$DefaultLocationService((Throwable) obj);
            }
        });
    }

    private Function<? super io.reactivex.rxjava3.core.Observable<? extends Throwable>, ? extends io.reactivex.rxjava3.core.Observable<?>> onErrorRetryHandler(final int i, final double d, final int i2) {
        return new Function() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$DChaGJJ0VxVYyQuBB1iI5FMuAw8
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultLocationService.this.lambda$onErrorRetryHandler$19$DefaultLocationService(i2, i, d, (io.reactivex.rxjava3.core.Observable) obj);
            }
        };
    }

    private Function<? super Flowable<Throwable>, ? extends Publisher<?>> onSyncErrorRetryHandler(final MobilyticsMetricsCounter mobilyticsMetricsCounter) {
        return new Function() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$8yDTPJ6kN3Vbp8HyQVeJI-MErEM
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultLocationService.this.lambda$onSyncErrorRetryHandler$8$DefaultLocationService(mobilyticsMetricsCounter, (Flowable) obj);
            }
        };
    }

    private void recordMismatchMetrics() {
        boolean hasAccess = this.featureServiceV2.mo10268get().hasAccess("GEOFENCE_ANDROID_PHASE3", false);
        boolean hasAccess2 = this.featureServiceV2.mo10268get().hasAccess("GEOFENCE_ANDROID", false);
        LocationService locationService = (LocationService) GeneratedOutlineSupport1.outline20(LocationService.class);
        if (hasAccess) {
            String str = COMPONENT_PHASE3;
            this.mobilytics.mo10268get().recordOccurrence(MobilyticsUtil.MetricsID.PHASE3_USER_WEBLAB_LOCATION_SERVICE_MISMATCH, !(locationService instanceof Phase3LocationService), str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        } else if (!hasAccess2) {
        } else {
            String str2 = COMPONENT_PHASE3;
            this.mobilytics.mo10268get().recordOccurrence(MobilyticsUtil.MetricsID.PHASE3_CONTROLLED_USER_WEBLAB_LOCATION_SERVICE_MISMATCH, locationService instanceof Phase3LocationService, str2, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        }
    }

    private void recordMissedTriggerMetrics(String str, TriggeringGeofenceInfo triggeringGeofenceInfo) {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, "_");
        outline113.append(triggeringGeofenceInfo.getFenceId());
        String sb = outline113.toString();
        LocationDataStore dataStore = this.locationDataStoreService.getDataStore(LocationDataStoreService.LAST_TRIGGER_TABLE, ALSTriggerEvent.class);
        if (Objects.equals(dataStore.retrieveValue(sb), triggeringGeofenceInfo.getALSTrigerEvent())) {
            String outline72 = GeneratedOutlineSupport1.outline72(str, getTriggerEventType(triggeringGeofenceInfo));
            String str2 = COMPONENT_TRIGGER_GEOFENCE;
            this.mobilytics.mo10268get().recordOccurrence(outline72, true, str2, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
            return;
        }
        dataStore.lambda$storeValueAsRx$1$LocationDataStore(sb, triggeringGeofenceInfo.getALSTrigerEvent());
    }

    private void recordMobilyticsTriggerGeofenceException(Throwable th, TriggeringGeofenceInfo triggeringGeofenceInfo) {
        if (th instanceof LocationException) {
            int ordinal = ((LocationException) th).getErrorCode().ordinal();
            if (ordinal == 4) {
                MetricsUtil.recordOccurrence(this.mobilytics, MetricsUtil.MetricsId.WORKFLOW_ALS_TIMEOUT_FAILURE, "trigger_geofence", true);
            } else if (ordinal != 9) {
                MetricsUtil.recordOccurrence(this.mobilytics, MetricsUtil.MetricsId.WORKFLOW_ALS_OTHER_FAILURE, "trigger_geofence", true);
            } else {
                MetricsUtil.recordOccurrence(this.mobilytics, MetricsUtil.MetricsId.WORKFLOW_ALS_401_FAILURE, "trigger_geofence", true);
            }
        }
        String workflowFailureMetricNameByReason = getWorkflowFailureMetricNameByReason(th);
        String triggerEventType = getTriggerEventType(triggeringGeofenceInfo);
        String outline72 = GeneratedOutlineSupport1.outline72(workflowFailureMetricNameByReason, triggerEventType);
        String str = COMPONENT_TRIGGER_GEOFENCE;
        this.mobilytics.mo10268get().recordOccurrence(outline72, true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        String outline722 = GeneratedOutlineSupport1.outline72(MobilyticsUtil.MetricsID.ALS_WORKFLOW_FAILURE, triggerEventType);
        String str2 = COMPONENT_TRIGGER_GEOFENCE;
        this.mobilytics.mo10268get().recordOccurrence(outline722, true, str2, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
    }

    private void recordMobilyticsTriggerGeofenceStart(TriggeringGeofenceInfo triggeringGeofenceInfo) {
        String triggerEventType = getTriggerEventType(triggeringGeofenceInfo);
        String outline72 = GeneratedOutlineSupport1.outline72(MobilyticsUtil.MetricsID.OS_TRIGGER_RECEIVED, triggerEventType);
        String str = COMPONENT_TRIGGER_GEOFENCE;
        this.mobilytics.mo10268get().recordOccurrence(outline72, true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        String outline722 = GeneratedOutlineSupport1.outline72(MobilyticsUtil.MetricsID.ALS_WORKFLOW_START, triggerEventType);
        String str2 = COMPONENT_TRIGGER_GEOFENCE;
        this.mobilytics.mo10268get().recordOccurrence(outline722, true, str2, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        String outline723 = GeneratedOutlineSupport1.outline72(MobilyticsUtil.MetricsID.ALS_WORKFLOW_INITIAL_CALL, triggerEventType);
        String str3 = COMPONENT_TRIGGER_GEOFENCE;
        this.mobilytics.mo10268get().recordOccurrence(outline723, true, str3, str3, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
    }

    private void recordMobilyticsTriggerGeofenceSuccess(TriggeringGeofenceInfo triggeringGeofenceInfo) {
        String outline72 = GeneratedOutlineSupport1.outline72(MobilyticsUtil.MetricsID.ALS_WORKFLOW_SUCCESS, getTriggerEventType(triggeringGeofenceInfo));
        String str = COMPONENT_TRIGGER_GEOFENCE;
        this.mobilytics.mo10268get().recordOccurrence(outline72, true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
    }

    private void startRecordingAvailableFeatures() {
        this.userChangeObservable.subscribe(new Action1() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$cZJTjIuiM1tmwSSNkuxKPd73IHw
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DefaultLocationService.this.lambda$startRecordingAvailableFeatures$44$DefaultLocationService((UserIdentity) obj);
            }
        }, new Action1() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$w1pSnL3RfZOxIBJyAPqZ2ggrJKg
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DefaultLocationService.this.lambda$startRecordingAvailableFeatures$45$DefaultLocationService((Throwable) obj);
            }
        });
    }

    @Override // com.amazon.alexa.location.LocationService
    public Completable clearGeofences() {
        return this.locationManager.clearGeofences();
    }

    @Override // com.amazon.alexa.location.LocationService
    public Single<String> createGeofence(final double d, final double d2, final double d3) {
        if (!this.isGeofenceEnabled) {
            return Single.error(new LocationException(LocationErrorCode.FEATURE_NOT_AVAILABLE, "[ERROR] GEOFENCE_ANDROID feature is off."));
        }
        return this.networkServiceConfigProvider.getConfig(MobilyticsUtil.getComponentName("create_geofence")).flatMap(new Function() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$TMTgR-H3P6AgDVlZmGz6xuu5e4k
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultLocationService.this.lambda$createGeofence$0$DefaultLocationService(d, d2, d3, (LocationNetworkServiceConfig) obj);
            }
        });
    }

    @Override // com.amazon.alexa.location.LocationService
    public Single<JSONObject> getGeofenceStates() {
        return this.locationManager.getGeofenceStates();
    }

    @Override // com.amazon.alexa.location.LocationService
    public Single<JSONObject> getRegisteredGeofences() {
        return this.locationManager.getRegisteredGeofences();
    }

    public /* synthetic */ void lambda$couldRefreshAuthToken$29$DefaultLocationService(Throwable th) {
        ExceptionRecordUtil.recordExceptions(TAG, "failed to get new auth token", th, this.crashReporter);
    }

    public /* synthetic */ SingleSource lambda$createGeofence$0$DefaultLocationService(double d, double d2, double d3, LocationNetworkServiceConfig locationNetworkServiceConfig) throws Throwable {
        return this.locationManager.createGeofence(locationNetworkServiceConfig.getBuildStage(), locationNetworkServiceConfig.getPreferredMarketplace(), d, d2, d3);
    }

    public /* synthetic */ ObservableSource lambda$integrateRetryTriggerGeofence$27$DefaultLocationService(final TriggeringGeofenceInfo triggeringGeofenceInfo, final int i, final int i2, final double d, final MobilyticsMetricsCounter mobilyticsMetricsCounter, final MobilyticsMetricsCounter mobilyticsMetricsCounter2, io.reactivex.rxjava3.core.Observable observable) throws Throwable {
        return observable.flatMap(new Function() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$25C3WhW_WDlEaVNXq5YdeLklErI
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultLocationService.this.lambda$null$24$DefaultLocationService(triggeringGeofenceInfo, (Throwable) obj);
            }
        }).zipWith(io.reactivex.rxjava3.core.Observable.range(1, i + 1), new BiFunction() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$OiwmDvvrzgX2TzRBvn5_ZSpZF3M
            @Override // io.reactivex.rxjava3.functions.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return DefaultLocationService.this.lambda$null$25$DefaultLocationService(triggeringGeofenceInfo, i2, d, i, mobilyticsMetricsCounter, mobilyticsMetricsCounter2, (Throwable) obj, (Integer) obj2);
            }
        }).flatMap($$Lambda$DefaultLocationService$gnTVaQpg_2bHMaXf7DCaZny3EeQ.INSTANCE);
    }

    public /* synthetic */ void lambda$null$11$DefaultLocationService(MobilyticsMetricsTimer mobilyticsMetricsTimer, TriggeringGeofenceInfo triggeringGeofenceInfo) throws Throwable {
        mobilyticsMetricsTimer.finishTimer();
        this.mobilytics.mo10268get().recordTimer(mobilyticsMetricsTimer);
        recordMobilyticsTriggerGeofenceSuccess(triggeringGeofenceInfo);
        recordMissedTriggerMetrics(MobilyticsUtil.MetricsID.ALS_WORKFLOW_SUCCESS_TRIGGER_MISSED, triggeringGeofenceInfo);
    }

    public /* synthetic */ ObservableSource lambda$null$12$DefaultLocationService(MobilyticsMetricsCounter mobilyticsMetricsCounter, MobilyticsMetricsTimer mobilyticsMetricsTimer, TriggeringGeofenceInfo triggeringGeofenceInfo, Throwable th) throws Throwable {
        mobilyticsMetricsCounter.incrementCounter();
        mobilyticsMetricsTimer.finishTimer();
        MobilyticsMetricsTimer createTimerWithNewName = MobilyticsUtil.createTimerWithNewName(this.mobilytics, mobilyticsMetricsTimer, MobilyticsUtil.MetricsID.ALS_WORKFLOW_TIME_FAILURE, COMPONENT_TRIGGER_GEOFENCE);
        Log.e(TAG, "[ERROR] Triggering geofence process has failed.", th);
        this.mobilytics.mo10268get().recordTimer(createTimerWithNewName);
        recordMobilyticsTriggerGeofenceException(th, triggeringGeofenceInfo);
        ExceptionRecordUtil.recordExceptions(TAG, "triggerGeofence", th, this.crashReporter);
        return io.reactivex.rxjava3.core.Observable.empty();
    }

    public /* synthetic */ ObservableSource lambda$null$16$DefaultLocationService(io.reactivex.rxjava3.core.Observable observable, Throwable th) throws Throwable {
        String outline72 = GeneratedOutlineSupport1.outline72("fetch_config_exception_", observable.getClass().getSimpleName());
        String str = COMPONENT_TRIGGER_GEOFENCE;
        this.mobilytics.mo10268get().recordOccurrence(outline72, true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Trigger geofence auth: ");
        outline107.append(th.getMessage());
        outline107.toString();
        return io.reactivex.rxjava3.core.Observable.just(th);
    }

    public /* synthetic */ ObservableSource lambda$null$18$DefaultLocationService(int i, double d, Integer num) throws Throwable {
        String.format(Locale.US, "Trigger geofence auth: retry time [%d] wait [%d %s]", num, Long.valueOf(getBackoffInterval(i, d, num.intValue())), TimeUnit.MILLISECONDS);
        return io.reactivex.rxjava3.core.Observable.timer(getBackoffInterval(i, d, num.intValue()), TimeUnit.MILLISECONDS);
    }

    public /* synthetic */ ObservableSource lambda$null$24$DefaultLocationService(TriggeringGeofenceInfo triggeringGeofenceInfo, Throwable th) throws Throwable {
        String.format(Locale.US, "Send trigger event for fenceId [%s] error: [%s]", triggeringGeofenceInfo.getFenceId(), th.getMessage());
        if (th instanceof LocationException) {
            LocationErrorCode errorCode = ((LocationException) th).getErrorCode();
            Context context = this.context;
            WriteToFile.appendLogForDebugBuild(context, "sendTriggerEvent + errorcode " + errorCode);
            if (errorCode != LocationErrorCode.ALS_TIMEOUT && errorCode != LocationErrorCode.ALS_500 && errorCode != LocationErrorCode.ALS_503 && errorCode != LocationErrorCode.ALS_AUTH_TOKEN_ERROR) {
                if (errorCode == LocationErrorCode.ALS_401) {
                    if (couldRefreshAuthToken()) {
                        return io.reactivex.rxjava3.core.Observable.just(th);
                    }
                    return io.reactivex.rxjava3.core.Observable.error(new LocationException(LocationErrorCode.ALS_AUTH_TOKEN_ERROR, "Auth token is failed to get."));
                }
            } else {
                return io.reactivex.rxjava3.core.Observable.just(th);
            }
        }
        return io.reactivex.rxjava3.core.Observable.error(th);
    }

    public /* synthetic */ io.reactivex.rxjava3.core.Observable lambda$null$25$DefaultLocationService(TriggeringGeofenceInfo triggeringGeofenceInfo, int i, double d, int i2, MobilyticsMetricsCounter mobilyticsMetricsCounter, MobilyticsMetricsCounter mobilyticsMetricsCounter2, Throwable th, Integer num) throws Throwable {
        Context context = this.context;
        WriteToFile.appendLogForDebugBuild(context, "sendTriggerEvent + retryCount=" + num);
        String.format(Locale.US, "Send trigger event for fenceId [%s]: retry time [%d] wait [%d %s]", triggeringGeofenceInfo.getFenceId(), num, Long.valueOf(getBackoffInterval(i, d, num.intValue())), TimeUnit.MILLISECONDS);
        if (num.intValue() > i2) {
            return io.reactivex.rxjava3.core.Observable.error(th);
        }
        mobilyticsMetricsCounter.incrementCounter();
        if ((th instanceof LocationException) && ((LocationException) th).getErrorCode() == LocationErrorCode.ALS_AUTH_TOKEN_ERROR) {
            mobilyticsMetricsCounter2.incrementCounter();
        }
        return io.reactivex.rxjava3.core.Observable.timer(getBackoffInterval(i, d, num.intValue()), TimeUnit.MILLISECONDS);
    }

    public /* synthetic */ Publisher lambda$null$7$DefaultLocationService(AtomicInteger atomicInteger, Throwable th) throws Throwable {
        Long valueOf = Long.valueOf(getBackoffInterval(1000, EXPONENTIAL_RETRY_FACTOR.doubleValue(), atomicInteger.get()));
        String.format("Sync geofence retry time [%d] wait [%d %s]", Integer.valueOf(atomicInteger.get()), valueOf, TimeUnit.MILLISECONDS);
        return Flowable.timer(valueOf.longValue(), TimeUnit.MILLISECONDS);
    }

    public /* synthetic */ void lambda$observeFeatureAvailability$36$DefaultLocationService(UserIdentity userIdentity) {
        recordMismatchMetrics();
        boolean hasAccess = this.featureServiceV2.mo10268get().hasAccess("GEOFENCE_ANDROID", false);
        boolean z = hasAccess != this.isGeofenceEnabled;
        boolean z2 = !TextUtils.equals(this.currentPersonId, this.personIdProvider.getPersonId());
        if (!z) {
            if (!hasAccess || !z2 || !this.featureServiceV2.mo10268get().hasAccess("GEOFENCE_ANDROID_USE_IDENTITY_API_V2", false)) {
                return;
            }
            this.currentPersonId = this.personIdProvider.getPersonId();
            if (!this.isLocationPermissionEnabled) {
                return;
            }
            String str = COMPONENT_SYNC_GEOFENCE;
            this.mobilytics.mo10268get().recordOccurrence(MobilyticsUtil.MetricsID.GEOFENCE_SYNC_REQUESTED_USER_CHANGED, true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
            syncGeofence().subscribeOn(Schedulers.io()).subscribe($$Lambda$DefaultLocationService$P83bkvOnQ4xfHGSC9C_9hsnXLHM.INSTANCE, $$Lambda$DefaultLocationService$26DWcxv3r4_iAv6sEuFLMP37c.INSTANCE);
        } else if (hasAccess) {
            this.isGeofenceEnabled = true;
            MetricsUtil.recordOccurrence(this.mobilytics, MetricsUtil.MetricsId.GEOFENCE_USER_CHANGE_SYNC_TRIGGER, OBSERVE_USER_CHANGES_METRICS_METHOD, true);
            if (!this.isLocationPermissionEnabled) {
                return;
            }
            String str2 = COMPONENT_SYNC_GEOFENCE;
            this.mobilytics.mo10268get().recordOccurrence(MobilyticsUtil.MetricsID.GEOFENCE_SYNC_REQUESTED_USER_CHANGED, true, str2, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
            syncGeofence().subscribeOn(Schedulers.io()).subscribe($$Lambda$DefaultLocationService$5IKSzKlj3zZWYnt2XQchujW024k.INSTANCE, $$Lambda$DefaultLocationService$iIkgWP4Zm54xS3TanCWfeFfZ34.INSTANCE);
        } else {
            this.isGeofenceEnabled = false;
            clearGeofences().subscribeOn(Schedulers.io()).subscribe($$Lambda$DefaultLocationService$74oNQmr1i90jAueJoJyx8smOhI.INSTANCE, $$Lambda$DefaultLocationService$8Bx76bRY9yr4RSpanhpSulpPdIQ.INSTANCE);
        }
    }

    public /* synthetic */ void lambda$observeFeatureAvailability$37$DefaultLocationService(Throwable th) {
        ExceptionRecordUtil.recordExceptions(TAG, "identityService", th, this.crashReporter);
    }

    public /* synthetic */ io.reactivex.rxjava3.core.Observable lambda$onErrorRetryHandler$19$DefaultLocationService(int i, final int i2, final double d, final io.reactivex.rxjava3.core.Observable observable) throws Throwable {
        return observable.flatMap(new Function() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$TW_7_h40esvdMhydY1_kLXIfiZ4
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultLocationService.this.lambda$null$16$DefaultLocationService(observable, (Throwable) obj);
            }
        }).zipWith(io.reactivex.rxjava3.core.Observable.range(1, i), $$Lambda$DefaultLocationService$ateivw3n0Sj2tvbyoonac6UH78.INSTANCE).flatMap(new Function() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$dXfPiNVXyoaN8hpTpdm7cledalM
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultLocationService.this.lambda$null$18$DefaultLocationService(i2, d, (Integer) obj);
            }
        });
    }

    public /* synthetic */ Publisher lambda$onSyncErrorRetryHandler$8$DefaultLocationService(final MobilyticsMetricsCounter mobilyticsMetricsCounter, Flowable flowable) throws Throwable {
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        return flowable.flatMap(new Function() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$1BbQdZIbx951KR2rgC6gmD8lGKE
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultLocationService.lambda$null$5(atomicInteger, (Throwable) obj);
            }
        }).takeWhile(new Predicate() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$wDaAS4jG6V000XzAiLzzGPeB8ng
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                MobilyticsMetricsCounter mobilyticsMetricsCounter2 = MobilyticsMetricsCounter.this;
                AtomicInteger atomicInteger2 = atomicInteger;
                Throwable th = (Throwable) obj;
                return mobilyticsMetricsCounter2.incrementCounter();
            }
        }).flatMap(new Function() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$9EFyMIFE5Jl_8jV2GBWp-GpY5hM
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultLocationService.this.lambda$null$7$DefaultLocationService(atomicInteger, (Throwable) obj);
            }
        });
    }

    public /* synthetic */ void lambda$recordLocationSetting$42$DefaultLocationService(LocationSettingsStates locationSettingsStates) throws Throwable {
        LocationSettingsStates locationSettingsStates2 = this.lastLocationSettingsStates;
        if (locationSettingsStates2 != null && locationSettingsStates2.isGpsPresent() == locationSettingsStates.isGpsPresent() && this.lastLocationSettingsStates.isGpsUsable() == locationSettingsStates.isGpsUsable() && this.lastLocationSettingsStates.isNetworkLocationPresent() == locationSettingsStates.isNetworkLocationPresent() && this.lastLocationSettingsStates.isNetworkLocationUsable() == locationSettingsStates.isNetworkLocationUsable()) {
            return;
        }
        String str = MobilyticsUtil.MetricsID.GPS_PRESENT;
        boolean isGpsPresent = locationSettingsStates.isGpsPresent();
        String str2 = COMPONENT_SETTING;
        this.mobilytics.mo10268get().recordOccurrence(str, isGpsPresent, str2, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        String str3 = MobilyticsUtil.MetricsID.GPS_USABLE;
        boolean isGpsUsable = locationSettingsStates.isGpsUsable();
        String str4 = COMPONENT_SETTING;
        this.mobilytics.mo10268get().recordOccurrence(str3, isGpsUsable, str4, str4, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        String str5 = MobilyticsUtil.MetricsID.NETWORK_LOCATION_PRESENT;
        boolean isNetworkLocationPresent = locationSettingsStates.isNetworkLocationPresent();
        String str6 = COMPONENT_SETTING;
        this.mobilytics.mo10268get().recordOccurrence(str5, isNetworkLocationPresent, str6, str6, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        String str7 = MobilyticsUtil.MetricsID.NETWORK_LOCATION_USABLE;
        boolean isNetworkLocationUsable = locationSettingsStates.isNetworkLocationUsable();
        String str8 = COMPONENT_SETTING;
        this.mobilytics.mo10268get().recordOccurrence(str7, isNetworkLocationUsable, str8, str8, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        this.lastLocationSettingsStates = locationSettingsStates;
    }

    public /* synthetic */ SingleSource lambda$reportStatusToALS$10$DefaultLocationService(List list, LocationNetworkServiceConfig locationNetworkServiceConfig) throws Throwable {
        return this.locationManager.reportGeoFenceStatus(locationNetworkServiceConfig.getBuildStage(), locationNetworkServiceConfig.getPreferredMarketplace(), locationNetworkServiceConfig.getPersonId(), list).toSingle($$Lambda$DefaultLocationService$UMBFn4O5RlMuNdItMZZ6sq4kKg.INSTANCE);
    }

    public /* synthetic */ ObservableSource lambda$sendTriggerEvent$20$DefaultLocationService(TriggeringGeofenceInfo triggeringGeofenceInfo, LocationNetworkServiceConfig locationNetworkServiceConfig) throws Throwable {
        Mobilytics mo10268get = this.mobilytics.mo10268get();
        boolean isEmpty = TextUtils.isEmpty(locationNetworkServiceConfig.getPersonId());
        String str = COMPONENT_TRIGGER_GEOFENCE;
        mo10268get.recordOccurrence(MobilyticsUtil.MetricsID.ALS_WORKFLOW_EMPTY_PERSON_ID, isEmpty, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        Mobilytics mo10268get2 = this.mobilytics.mo10268get();
        boolean isEmpty2 = TextUtils.isEmpty(locationNetworkServiceConfig.getAuthToken());
        String str2 = COMPONENT_TRIGGER_GEOFENCE;
        mo10268get2.recordOccurrence(MobilyticsUtil.MetricsID.ALS_WORKFLOW_EMPTY_AUTHTOKEN, isEmpty2, str2, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        String personId = locationNetworkServiceConfig.getPersonId();
        LocationManager locationManager = this.locationManager;
        String buildStage = locationNetworkServiceConfig.getBuildStage();
        String preferredMarketplace = locationNetworkServiceConfig.getPreferredMarketplace();
        if (personId == null) {
            personId = "";
        }
        return locationManager.triggerGeofence(buildStage, preferredMarketplace, personId, triggeringGeofenceInfo);
    }

    public /* synthetic */ void lambda$sendTriggerEvent$21$DefaultLocationService(String str, Throwable th) throws Throwable {
        String outline72;
        if (th instanceof LocationException) {
            LocationErrorCode errorCode = ((LocationException) th).getErrorCode();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(MobilyticsUtil.MetricsID.ALS_CALL_FAILURE);
            outline107.append(METRICS_MAP.get(errorCode));
            outline72 = outline107.toString();
        } else {
            outline72 = GeneratedOutlineSupport1.outline72(MobilyticsUtil.MetricsID.ALS_CALL_FAILURE, "unknown");
        }
        String outline722 = GeneratedOutlineSupport1.outline72(outline72, str);
        String str2 = COMPONENT_TRIGGER_GEOFENCE;
        this.mobilytics.mo10268get().recordOccurrence(outline722, true, str2, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
    }

    public /* synthetic */ void lambda$sendTriggerEvent$22$DefaultLocationService(String str, MobilyticsMetricsCounter mobilyticsMetricsCounter, MobilyticsMetricsCounter mobilyticsMetricsCounter2, Throwable th) throws Throwable {
        String outline72 = GeneratedOutlineSupport1.outline72(MobilyticsUtil.MetricsID.ALS_WORKFLOW_FAILURE_INTEGRATE_RETRY_COUNT, str);
        String str2 = COMPONENT_TRIGGER_GEOFENCE;
        MobilyticsMetricsCounter createCounter = this.mobilytics.mo10268get().createCounter(outline72, str2, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        createCounter.incrementCounterByValue(mobilyticsMetricsCounter.getCount());
        this.mobilytics.mo10268get().recordCounter(createCounter);
        String outline722 = GeneratedOutlineSupport1.outline72(MobilyticsUtil.MetricsID.ALS_WORKFLOW_FAILURE_GET_AUTH_RETRY_COUNT, str);
        String str3 = COMPONENT_TRIGGER_GEOFENCE;
        MobilyticsMetricsCounter createCounter2 = this.mobilytics.mo10268get().createCounter(outline722, str3, str3, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        createCounter2.incrementCounterByValue(mobilyticsMetricsCounter2.getCount());
        this.mobilytics.mo10268get().recordCounter(createCounter2);
    }

    public /* synthetic */ void lambda$sendTriggerEvent$23$DefaultLocationService(MobilyticsMetricsCounter mobilyticsMetricsCounter, MobilyticsMetricsCounter mobilyticsMetricsCounter2) throws Throwable {
        this.mobilytics.mo10268get().recordCounter(mobilyticsMetricsCounter);
        this.mobilytics.mo10268get().recordCounter(mobilyticsMetricsCounter2);
    }

    public /* synthetic */ void lambda$startRecordingAvailableFeatures$44$DefaultLocationService(UserIdentity userIdentity) {
        String[] strArr;
        if (userIdentity != null) {
            for (String str : Features.ALL_FEATURES) {
                Mobilytics mo10268get = this.mobilytics.mo10268get();
                String legacyName = MobilyticsUtil.getLegacyName(str, "count");
                boolean hasAccess = this.featureServiceV2.mo10268get().hasAccess(str, false);
                String str2 = COMPONENT_FEATURE;
                mo10268get.recordOccurrence(legacyName, hasAccess, str2, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
            }
        }
    }

    public /* synthetic */ void lambda$startRecordingAvailableFeatures$45$DefaultLocationService(Throwable th) {
        ExceptionRecordUtil.recordExceptions(TAG, "startRecordingAvailableFeatures", th, this.crashReporter);
    }

    public /* synthetic */ SingleSource lambda$syncGeofence$2$DefaultLocationService(LocationNetworkServiceConfig locationNetworkServiceConfig) throws Throwable {
        return this.locationManager.syncGeofence(locationNetworkServiceConfig.getBuildStage(), locationNetworkServiceConfig.getPreferredMarketplace(), locationNetworkServiceConfig.getPersonId());
    }

    public /* synthetic */ void lambda$syncGeofence$3$DefaultLocationService(MobilyticsMetricsTimer mobilyticsMetricsTimer, MobilyticsMetricsCounter mobilyticsMetricsCounter, Throwable th) throws Throwable {
        mobilyticsMetricsTimer.finishTimer();
        MobilyticsMetricsTimer createTimerWithNewName = MobilyticsUtil.createTimerWithNewName(this.mobilytics, mobilyticsMetricsTimer, MobilyticsUtil.MetricsID.GEOFENCE_SYNC_PROCESS_TIME_FAILURE, COMPONENT_SYNC_GEOFENCE);
        MobilyticsMetricsCounter createCouterWithNewName = MobilyticsUtil.createCouterWithNewName(this.mobilytics, mobilyticsMetricsCounter, MobilyticsUtil.MetricsID.GEOFENCE_SYNC_RETRY_COUNT_FAILURE, COMPONENT_SYNC_GEOFENCE);
        this.mobilytics.mo10268get().recordTimer(createTimerWithNewName);
        this.mobilytics.mo10268get().recordCounter(createCouterWithNewName);
    }

    public /* synthetic */ void lambda$syncGeofence$4$DefaultLocationService(MobilyticsMetricsTimer mobilyticsMetricsTimer, MobilyticsMetricsCounter mobilyticsMetricsCounter, List list) throws Throwable {
        mobilyticsMetricsTimer.finishTimer();
        this.mobilytics.mo10268get().recordCounter(mobilyticsMetricsCounter);
        this.mobilytics.mo10268get().recordTimer(mobilyticsMetricsTimer);
        String str = COMPONENT_SYNC_GEOFENCE;
        this.mobilytics.mo10268get().recordOccurrence("success", true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
    }

    public /* synthetic */ ObservableSource lambda$triggerGeofence$13$DefaultLocationService(MobilyticsMetricsCounter mobilyticsMetricsCounter, int i, double d, int i2, final MobilyticsMetricsCounter mobilyticsMetricsCounter2, final TriggeringGeofenceInfo triggeringGeofenceInfo) throws Throwable {
        Mobilytics mo10268get = this.mobilytics.mo10268get();
        boolean z = this.identityService.getUser(TAG) == null;
        String str = COMPONENT_TRIGGER_GEOFENCE;
        mo10268get.recordOccurrence(MobilyticsUtil.MetricsID.NO_USER_IDENTITY, z, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        String str2 = COMPONENT_TRIGGER_GEOFENCE;
        final MobilyticsMetricsTimer createTimer = this.mobilytics.mo10268get().createTimer(MobilyticsUtil.MetricsID.ALS_WORKFLOW_TIME_SUCCESS, str2, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        recordMobilyticsTriggerGeofenceStart(triggeringGeofenceInfo);
        recordMissedTriggerMetrics(MobilyticsUtil.MetricsID.OS_TRIGGER_MISSED, triggeringGeofenceInfo);
        recordMissedTriggerMetrics(MobilyticsUtil.MetricsID.ALS_WORKFLOW_TRIGGER_MISSED, triggeringGeofenceInfo);
        mobilyticsMetricsCounter.incrementCounter();
        return sendTriggerEvent(triggeringGeofenceInfo, i, d, i2).doOnComplete(new Action() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$F1ujOj2oeREOEVUVH-tedR9DD8w
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                DefaultLocationService.this.lambda$null$11$DefaultLocationService(createTimer, triggeringGeofenceInfo);
            }
        }).onErrorResumeNext(new Function() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$aWMPuH1wPrzaTQXCQJq1dba8npg
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultLocationService.this.lambda$null$12$DefaultLocationService(mobilyticsMetricsCounter2, createTimer, triggeringGeofenceInfo, (Throwable) obj);
            }
        });
    }

    public /* synthetic */ void lambda$triggerGeofence$14$DefaultLocationService(MobilyticsMetricsCounter mobilyticsMetricsCounter, MobilyticsMetricsCounter mobilyticsMetricsCounter2) throws Throwable {
        this.mobilytics.mo10268get().recordCounter(mobilyticsMetricsCounter);
        this.mobilytics.mo10268get().recordCounter(mobilyticsMetricsCounter2);
    }

    public /* synthetic */ SingleSource lambda$updateGeofence$1$DefaultLocationService(String str, double d, double d2, double d3, LocationNetworkServiceConfig locationNetworkServiceConfig) throws Throwable {
        return this.locationManager.updateGeofence(locationNetworkServiceConfig.getBuildStage(), locationNetworkServiceConfig.getPreferredMarketplace(), str, d, d2, d3);
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityCreated() {
        if (!this.isGeofenceEnabled) {
            Log.i(TAG, "onActivityCreated: ", new LocationException(LocationErrorCode.FEATURE_NOT_AVAILABLE, "[ERROR] GEOFENCE_ANDROID feature is off."));
        } else if (!this.isLocationPermissionEnabled) {
        } else {
            String str = COMPONENT_SYNC_GEOFENCE;
            this.mobilytics.mo10268get().recordOccurrence(MobilyticsUtil.MetricsID.GEOFENCE_SYNC_REQUESTED_FORGROUND, true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
            syncGeofence().subscribeOn(Schedulers.io()).subscribe($$Lambda$DefaultLocationService$ZiBeouRn7sGBXSqDxiYmFYmff04.INSTANCE, $$Lambda$DefaultLocationService$btl8dWaMXtrDI8DtzC9HBhXb_8k.INSTANCE);
        }
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityDestroy() {
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityPause() {
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityResume() {
        boolean hasFullLocationPermission = this.locationPermissionService.hasFullLocationPermission();
        if (this.isLocationPermissionEnabled != hasFullLocationPermission) {
            if (hasFullLocationPermission) {
                String str = COMPONENT_SYNC_GEOFENCE;
                this.mobilytics.mo10268get().recordOccurrence(MobilyticsUtil.MetricsID.GEOFENCE_SYNC_REQUESTED_PERMISSION_CHANGED, true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
                syncGeofence().subscribeOn(Schedulers.io()).subscribe($$Lambda$DefaultLocationService$01JkZE1xtlCxiZyu4nEmshDOAhY.INSTANCE, $$Lambda$DefaultLocationService$3GaxZwti0xsCwR5HRV2h1n1dJKU.INSTANCE);
            }
            this.isLocationPermissionEnabled = hasFullLocationPermission;
            recordLocationPermission();
        }
        recordLocationSetting();
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityStart() {
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityStop() {
    }

    public io.reactivex.rxjava3.core.Observable<List<ALSGeofence>> onGeofencesServed() {
        return this.locationManager.onGeofencesServed();
    }

    @Override // com.amazon.alexa.location.LocationService
    public void recordLocationPermission() {
        if (this.featureServiceV2.mo10268get().hasAccess("GEOFENCE_ANDROID_REDUCE_SETTING_METRICS", false)) {
            return;
        }
        Mobilytics mo10268get = this.mobilytics.mo10268get();
        String str = this.isLocationPermissionEnabled ? MobilyticsUtil.MetricsID.PERMISSION_ALWAYS : MobilyticsUtil.MetricsID.PERMISSION_DENIED;
        String str2 = COMPONENT_PERMISSION;
        mo10268get.recordOccurrence(str, true, str2, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
    }

    @Override // com.amazon.alexa.location.LocationService
    public void recordLocationSetting() {
        if (this.featureServiceV2.mo10268get().hasAccess("GEOFENCE_ANDROID_REDUCE_SETTING_METRICS", false)) {
            this.locationSettingsRecorder.recordLocationSettings();
        } else if (!this.isLocationPermissionEnabled) {
        } else {
            this.locationManager.getLocationSettings().subscribe(new Consumer() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$12F4_JBtvtt32vHq5rkWfxUw1WE
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    DefaultLocationService.this.lambda$recordLocationSetting$42$DefaultLocationService((LocationSettingsStates) obj);
                }
            }, $$Lambda$DefaultLocationService$gRILqBtjwGF7sSdSEP51Eb497_I.INSTANCE);
        }
    }

    @Override // com.amazon.alexa.location.LocationService
    public Completable reportStatusToALS(@NonNull final List<GeoFenceStatus> list) {
        if (!this.featureServiceV2.mo10268get().hasAccess("GEOFENCE_ANDROID", false)) {
            return Completable.error(new LocationException(LocationErrorCode.FEATURE_NOT_AVAILABLE, "[ERROR] GEOFENCE_ANDROID feature is off."));
        }
        return this.networkServiceConfigProvider.getConfig(MobilyticsUtil.getComponentName("report_geofences")).flatMap(new Function() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$ENB3NlM31TZuhR6_jrxdO8wfZl8
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultLocationService.this.lambda$reportStatusToALS$10$DefaultLocationService(list, (LocationNetworkServiceConfig) obj);
            }
        }).ignoreElement();
    }

    @Override // com.amazon.alexa.location.LocationService
    public Single<List<ALSGeofence>> restoreGeofences() {
        return this.locationManager.restoreGeofences();
    }

    public io.reactivex.rxjava3.core.Observable<String> sendTriggerEvent(final TriggeringGeofenceInfo triggeringGeofenceInfo, int i, double d, int i2) {
        final String triggerEventType = getTriggerEventType(triggeringGeofenceInfo);
        String outline72 = GeneratedOutlineSupport1.outline72(MobilyticsUtil.MetricsID.ALS_WORKFLOW_SUCCESS_INTEGRATE_RETRY_COUNT, triggerEventType);
        String str = COMPONENT_TRIGGER_GEOFENCE;
        final MobilyticsMetricsCounter createCounter = this.mobilytics.mo10268get().createCounter(outline72, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        String outline722 = GeneratedOutlineSupport1.outline72(MobilyticsUtil.MetricsID.ALS_WORKFLOW_SUCCESS_GET_AUTH_RETRY_COUNT, triggerEventType);
        String str2 = COMPONENT_TRIGGER_GEOFENCE;
        final MobilyticsMetricsCounter createCounter2 = this.mobilytics.mo10268get().createCounter(outline722, str2, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        return this.networkServiceConfigProvider.getConfig(COMPONENT_TRIGGER_GEOFENCE).toObservable().flatMap(new Function() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$AEPI1ea7Kb1ffWfoBPYMGbnMACw
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultLocationService.this.lambda$sendTriggerEvent$20$DefaultLocationService(triggeringGeofenceInfo, (LocationNetworkServiceConfig) obj);
            }
        }).doOnError(new Consumer() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$1R-9qIVxq9YOP99bXb0S_H8fOto
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DefaultLocationService.this.lambda$sendTriggerEvent$21$DefaultLocationService(triggerEventType, (Throwable) obj);
            }
        }).retryWhen(integrateRetryTriggerGeofence(triggeringGeofenceInfo, i, d, i2, createCounter, createCounter2)).doOnError(new Consumer() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$XTP3-3eYKzSyNkVtss_Iyo1E5pU
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DefaultLocationService.this.lambda$sendTriggerEvent$22$DefaultLocationService(triggerEventType, createCounter, createCounter2, (Throwable) obj);
            }
        }).doOnComplete(new Action() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$klSs0-12kZonXL01h59P8UPdsU4
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                DefaultLocationService.this.lambda$sendTriggerEvent$23$DefaultLocationService(createCounter, createCounter2);
            }
        });
    }

    @Override // com.amazon.alexa.location.LocationService
    public void start() {
        this.mainActivityLifecycleObserverRegistrar.addObserver(this);
        this.geofenceEventHandler.startListening();
        observeFeatureAvailability();
        startRecordingAvailableFeatures();
        if (!this.featureServiceV2.mo10268get().hasAccess("GEOFENCE_ANDROID_REDUCE_SETTING_METRICS", false)) {
            recordLocationPermission();
            recordLocationSetting();
        }
    }

    @Override // com.amazon.alexa.location.LocationService
    public Single<List<ALSGeofence>> syncGeofence() {
        if (!this.featureServiceV2.mo10268get().hasAccess("GEOFENCE_ANDROID", false)) {
            return Single.error(new LocationException(LocationErrorCode.FEATURE_NOT_AVAILABLE, "[ERROR] GEOFENCE_ANDROID feature is off."));
        }
        String str = COMPONENT_SYNC_GEOFENCE;
        this.mobilytics.mo10268get().recordOccurrence("start", true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        recordLocationPermission();
        String str2 = COMPONENT_SYNC_GEOFENCE;
        final MobilyticsMetricsCounter createCounter = this.mobilytics.mo10268get().createCounter(MobilyticsUtil.MetricsID.GEOFENCE_SYNC_RETRY_COUNT_SUCESS, str2, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        String str3 = COMPONENT_SYNC_GEOFENCE;
        final MobilyticsMetricsTimer createTimer = this.mobilytics.mo10268get().createTimer(MobilyticsUtil.MetricsID.GEOFENCE_SYNC_PROCESS_TIME_SUCCESS, str3, str3, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        return this.networkServiceConfigProvider.getConfig(COMPONENT_SYNC_GEOFENCE).flatMap(new Function() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$nDxZcWfwivsVUVNLDC8Cd22e8JE
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultLocationService.this.lambda$syncGeofence$2$DefaultLocationService((LocationNetworkServiceConfig) obj);
            }
        }).retryWhen(onSyncErrorRetryHandler(createCounter)).doOnError(new Consumer() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$ubgs8g0R7dOOVUPMssB6FwUpV9E
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DefaultLocationService.this.lambda$syncGeofence$3$DefaultLocationService(createTimer, createCounter, (Throwable) obj);
            }
        }).doOnSuccess(new Consumer() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$gl5ud6xXIPRPmgkWQYcirOIRGGg
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DefaultLocationService.this.lambda$syncGeofence$4$DefaultLocationService(createTimer, createCounter, (List) obj);
            }
        });
    }

    @Override // com.amazon.alexa.location.LocationService
    public io.reactivex.rxjava3.core.Observable<String> triggerGeofence(Intent intent, final int i, final double d, final int i2) {
        String str = COMPONENT_TRIGGER_GEOFENCE;
        final MobilyticsMetricsCounter createCounter = this.mobilytics.mo10268get().createCounter(MobilyticsUtil.MetricsID.REGIONS_PER_INTENT, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        String str2 = COMPONENT_TRIGGER_GEOFENCE;
        final MobilyticsMetricsCounter createCounter2 = this.mobilytics.mo10268get().createCounter(MobilyticsUtil.MetricsID.FAILURES_PER_INTENT, str2, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        final io.reactivex.rxjava3.core.Observable<String> doOnTerminate = this.locationManager.getTriggeringGeofenceInfo(intent).flatMap(new Function() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$8GR-N5uVO7XUWtzk2a19PdQekjc
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultLocationService.this.lambda$triggerGeofence$13$DefaultLocationService(createCounter, i, d, i2, createCounter2, (TriggeringGeofenceInfo) obj);
            }
        }).doOnTerminate(new Action() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$aFGOJQ_J494jlNwV37Zac4p8Td4
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                DefaultLocationService.this.lambda$triggerGeofence$14$DefaultLocationService(createCounter, createCounter2);
            }
        });
        return this.featureServiceV2.mo10268get().hasAccess("GEOFENCE_ANDROID_SIMPLIFY_TRIGGER_AUTH_LOGIC", false) ? doOnTerminate : this.networkServiceConfigProvider.getConfig(COMPONENT_TRIGGER_GEOFENCE).toObservable().retryWhen(onErrorRetryHandler(i, d, i2)).flatMap(new Function() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$IzsCMgYhELJXlJ8Df9RbyvSQcnA
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                io.reactivex.rxjava3.core.Observable observable = io.reactivex.rxjava3.core.Observable.this;
                DefaultLocationService.lambda$triggerGeofence$15(observable, (LocationNetworkServiceConfig) obj);
                return observable;
            }
        });
    }

    @Override // com.amazon.alexa.location.LocationService
    public Single<String> updateGeofence(@NonNull final String str, final double d, final double d2, final double d3) {
        if (!this.isGeofenceEnabled) {
            return Single.error(new LocationException(LocationErrorCode.FEATURE_NOT_AVAILABLE, "[ERROR] GEOFENCE_ANDROID feature is off."));
        }
        return this.networkServiceConfigProvider.getConfig(MobilyticsUtil.getComponentName("update_geofence")).flatMap(new Function() { // from class: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$ka732ZtQx5O8MOIYkZQkGZZ_eSU
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultLocationService.this.lambda$updateGeofence$1$DefaultLocationService(str, d, d2, d3, (LocationNetworkServiceConfig) obj);
            }
        });
    }
}
