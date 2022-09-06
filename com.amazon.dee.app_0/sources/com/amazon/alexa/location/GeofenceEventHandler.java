package com.amazon.alexa.location;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.location.models.ALSGeofence;
import com.amazon.alexa.location.models.Code;
import com.amazon.alexa.location.models.GeoFenceStatus;
import com.amazon.alexa.location.models.StatusCode;
import com.amazon.alexa.location.utils.Constants;
import com.amazon.alexa.location.utils.ExceptionRecordUtil;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.alexa.location.utils.MobilyticsUtil;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.functions.Action1;
/* loaded from: classes9.dex */
public class GeofenceEventHandler {
    private static final String COMPONENT_SYNC_GEOFENCE = MobilyticsUtil.getComponentName("sync_geofence");
    private static final String TAG = "GeofenceEventHandler";
    private final LazyComponent<CrashReporter> crashReporter;
    private final EventBus eventBus;
    private final LazyComponent<FeatureServiceV2> featureServiceV2 = ComponentRegistry.getInstance().getLazy(FeatureServiceV2.class);
    private MultiFilterSubscriber geofenceEventBusSubscriber;
    private LocationService locationService;
    private final LazyComponent<Mobilytics> mobilytics;
    private final Observable<UserIdentity> userChangeObservable;

    public GeofenceEventHandler(EventBus eventBus, Observable<UserIdentity> observable, LazyComponent<Mobilytics> lazyComponent, @NonNull LazyComponent<CrashReporter> lazyComponent2) {
        this.eventBus = eventBus;
        this.userChangeObservable = observable;
        this.mobilytics = lazyComponent;
        this.crashReporter = lazyComponent2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$reportGeoFenceStatusToALS$7() throws Throwable {
    }

    private void reportGeofencesErrorStatus(Throwable th, MobilyticsMetricsTimer mobilyticsMetricsTimer) {
        if (th instanceof GeoFenceException) {
            GeoFenceException geoFenceException = (GeoFenceException) th;
            Code mo7740get = Constants.ERROR_CODE_MAP.mo7740get(geoFenceException.getErrorCode());
            if (mo7740get == null) {
                mo7740get = Code.A101;
            }
            reportGeoFenceStatusToALS(mo7740get, geoFenceException.getListOfGeofences(), mobilyticsMetricsTimer);
        } else if (!(th instanceof Exception)) {
        } else {
            Log.w(TAG, "[ERROR] Fail to sync geofences", th);
            if (mobilyticsMetricsTimer == null) {
                return;
            }
            mobilyticsMetricsTimer.finishTimer();
            this.mobilytics.mo10268get().recordTimer(mobilyticsMetricsTimer);
        }
    }

    @VisibleForTesting
    void init() {
        this.locationService = (LocationService) GeneratedOutlineSupport1.outline20(LocationService.class);
    }

    public /* synthetic */ void lambda$processGeofenceSyncEvent$3$GeofenceEventHandler(MobilyticsMetricsTimer mobilyticsMetricsTimer) throws Throwable {
        if (mobilyticsMetricsTimer != null) {
            mobilyticsMetricsTimer.finishTimer();
            this.mobilytics.mo10268get().recordTimer(mobilyticsMetricsTimer);
        }
    }

    public /* synthetic */ void lambda$processGeofenceSyncEvent$4$GeofenceEventHandler(MobilyticsMetricsTimer mobilyticsMetricsTimer, List list) throws Throwable {
        if (!MetricsUtil.isMobilyticsMissing(this.mobilytics)) {
            this.mobilytics.mo10268get().recordOccurrence(MetricsUtil.LegacyEventType.GEOFENCE_SYNC_ERROR_RATE, false, "location", MetricsUtil.LegacySubComponentName.LOCATION_SERVICE, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        }
        reportGeoFenceStatusToALS(Code.A200, list, mobilyticsMetricsTimer);
    }

    public /* synthetic */ void lambda$processGeofenceSyncEvent$5$GeofenceEventHandler(MobilyticsMetricsTimer mobilyticsMetricsTimer, Throwable th) throws Throwable {
        if (!MetricsUtil.isMobilyticsMissing(this.mobilytics)) {
            this.mobilytics.mo10268get().recordOccurrence(MetricsUtil.LegacyEventType.GEOFENCE_SYNC_ERROR_RATE, true, "location", MetricsUtil.LegacySubComponentName.LOCATION_SERVICE, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        }
        reportGeofencesErrorStatus(th, mobilyticsMetricsTimer);
        ExceptionRecordUtil.recordExceptions(TAG, "locationService.syncGeofence()", th, this.crashReporter);
    }

    public /* synthetic */ void lambda$reportGeoFenceStatusToALS$6$GeofenceEventHandler(MobilyticsMetricsTimer mobilyticsMetricsTimer) throws Throwable {
        if (mobilyticsMetricsTimer != null) {
            mobilyticsMetricsTimer.finishTimer();
            this.mobilytics.mo10268get().recordTimer(mobilyticsMetricsTimer);
        }
    }

    public /* synthetic */ void lambda$reportGeoFenceStatusToALS$8$GeofenceEventHandler(Throwable th) throws Throwable {
        Log.w(TAG, "[ERROR] Fail to report geofence status", th);
        ExceptionRecordUtil.recordExceptions(TAG, "locationService.reportStatusToALS", th, this.crashReporter);
    }

    public /* synthetic */ void lambda$startListening$0$GeofenceEventHandler(UserIdentity userIdentity) {
        if (userIdentity == null) {
            toggleEventBusSubscription(false);
            return;
        }
        boolean hasAccess = this.featureServiceV2.mo10268get().hasAccess("GEOFENCE_ANDROID", false);
        if (!MetricsUtil.isMobilyticsMissing(this.mobilytics)) {
            this.mobilytics.mo10268get().recordOccurrence(MetricsUtil.LegacyEventType.GEOFENCE_NO_ACCESS_TO_FEATURE, !hasAccess, "location", MetricsUtil.LegacySubComponentName.LOCATION_SERVICE, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        }
        toggleEventBusSubscription(hasAccess);
    }

    public /* synthetic */ void lambda$startListening$1$GeofenceEventHandler(Throwable th) {
        ExceptionRecordUtil.recordExceptions(TAG, "identityService.onUserChangedOrNull()", th, this.crashReporter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public void processGeofenceSyncEvent(Message message) {
        final MobilyticsMetricsTimer mobilyticsMetricsTimer = null;
        final MobilyticsMetricsTimer createTimer = MetricsUtil.isMobilyticsMissing(this.mobilytics) ? null : this.mobilytics.mo10268get().createTimer(MetricsUtil.LegacyEventType.GEOFENCE_PROCESS_SYNC_MESSAGE, "location", MetricsUtil.LegacySubComponentName.LOCATION_SERVICE, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        if (!this.featureServiceV2.mo10268get().hasAccess("GEOFENCE_ANDROID", false)) {
            if (createTimer == null) {
                return;
            }
            createTimer.finishTimer();
            this.mobilytics.mo10268get().recordTimer(createTimer);
            return;
        }
        this.locationService.recordLocationPermission();
        this.locationService.recordLocationSetting();
        if (!MetricsUtil.isMobilyticsMissing(this.mobilytics)) {
            mobilyticsMetricsTimer = this.mobilytics.mo10268get().createTimer(MetricsUtil.LegacyEventType.GEOFENCE_SYNC, "location", MetricsUtil.LegacySubComponentName.LOCATION_SERVICE, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        }
        String str = COMPONENT_SYNC_GEOFENCE;
        this.mobilytics.mo10268get().recordOccurrence(MobilyticsUtil.MetricsID.GEOFENCE_SYNC_REQUESTED_THROUGH_EVENT_BUS, true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        this.locationService.syncGeofence().subscribeOn(Schedulers.io()).doFinally(new Action() { // from class: com.amazon.alexa.location.-$$Lambda$GeofenceEventHandler$YuyuJJe2qbbV3ciHdW-34xet8JI
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                GeofenceEventHandler.this.lambda$processGeofenceSyncEvent$3$GeofenceEventHandler(mobilyticsMetricsTimer);
            }
        }).subscribe(new Consumer() { // from class: com.amazon.alexa.location.-$$Lambda$GeofenceEventHandler$gYgrKbYJrchCUlqQPMGYhuN_MpE
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                GeofenceEventHandler.this.lambda$processGeofenceSyncEvent$4$GeofenceEventHandler(createTimer, (List) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.location.-$$Lambda$GeofenceEventHandler$K2sDXR1cEmkUjlcy8OQpOchWSR4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                GeofenceEventHandler.this.lambda$processGeofenceSyncEvent$5$GeofenceEventHandler(createTimer, (Throwable) obj);
            }
        });
    }

    @VisibleForTesting
    void reportGeoFenceStatusToALS(Code code, List<ALSGeofence> list, final MobilyticsMetricsTimer mobilyticsMetricsTimer) {
        ArrayList arrayList = new ArrayList(0);
        for (ALSGeofence aLSGeofence : list) {
            arrayList.add(new GeoFenceStatus(aLSGeofence.getId(), new StatusCode(code.getValue(), code)));
        }
        this.locationService.reportStatusToALS(arrayList).subscribeOn(Schedulers.io()).doFinally(new Action() { // from class: com.amazon.alexa.location.-$$Lambda$GeofenceEventHandler$A7BEQM-rENJsvrTSSxGr6v9yiC0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                GeofenceEventHandler.this.lambda$reportGeoFenceStatusToALS$6$GeofenceEventHandler(mobilyticsMetricsTimer);
            }
        }).subscribe($$Lambda$GeofenceEventHandler$XaWYYNsXE2Do3Yd3YwC8VRJ2Z4.INSTANCE, new Consumer() { // from class: com.amazon.alexa.location.-$$Lambda$GeofenceEventHandler$NBV9WR1WSyzD0FEP4tGOF7Jofd4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                GeofenceEventHandler.this.lambda$reportGeoFenceStatusToALS$8$GeofenceEventHandler((Throwable) obj);
            }
        });
    }

    public void startListening() {
        init();
        toggleEventBusSubscription(this.featureServiceV2.mo10268get().hasAccess("GEOFENCE_ANDROID", false));
        this.userChangeObservable.subscribe(new Action1() { // from class: com.amazon.alexa.location.-$$Lambda$GeofenceEventHandler$AJvMHXlufJx79_3EVKtiHMIJMsU
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                GeofenceEventHandler.this.lambda$startListening$0$GeofenceEventHandler((UserIdentity) obj);
            }
        }, new Action1() { // from class: com.amazon.alexa.location.-$$Lambda$GeofenceEventHandler$WCxbfd4DXjGG4aKBzrBfQsAMRmg
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                GeofenceEventHandler.this.lambda$startListening$1$GeofenceEventHandler((Throwable) obj);
            }
        });
    }

    void toggleEventBusSubscription(boolean z) {
        if (z) {
            if (this.geofenceEventBusSubscriber != null) {
                return;
            }
            this.geofenceEventBusSubscriber = this.eventBus.getSubscriber();
            this.geofenceEventBusSubscriber.subscribe($$Lambda$GeofenceEventHandler$5OGCF1BaD5jeo58MTwxPfJ63n6Y.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.location.-$$Lambda$JTqr-ChaWFxakXISvIDzaW2_zMU
                @Override // com.amazon.alexa.eventbus.api.MessageHandler
                public final void handle(Message message) {
                    GeofenceEventHandler.this.processGeofenceSyncEvent(message);
                }
            });
            return;
        }
        MultiFilterSubscriber multiFilterSubscriber = this.geofenceEventBusSubscriber;
        if (multiFilterSubscriber == null) {
            return;
        }
        this.eventBus.unsubscribe(multiFilterSubscriber);
        this.geofenceEventBusSubscriber = null;
    }
}
