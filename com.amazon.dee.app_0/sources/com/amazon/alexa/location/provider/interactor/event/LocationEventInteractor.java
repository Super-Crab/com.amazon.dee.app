package com.amazon.alexa.location.provider.interactor.event;

import android.location.Location;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.location.networking.gateway.AlexaLocationPlatformServiceNetworkGateway;
import com.amazon.alexa.location.provider.util.Metrics;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.alexa.sensor.api.location.Error;
import com.amazon.alexa.sensor.api.location.LocationConfigurationRequest;
import com.amazon.alexa.sensor.api.location.LocationEventHandler;
import com.amazon.alexa.sensor.api.location.LocationSensorAccess;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;
/* loaded from: classes9.dex */
public class LocationEventInteractor implements SensorEventInteractor {
    private static final String CAUSE_PREFIX = "CONTEXTUAL_EVENT_UPDATE";
    public static final String FEATURE_ID = "feature_location_provider";
    private static final String METRIC_LOCATION_DATA_RECEIVED = "location_data_received";
    private static final String METRIC_LOCATION_REPORT_STARTED = "location_report_started";
    private static final String METRIC_LOCATION_REPORT_SUCCESS = "location_report_success";
    private static final String METRIC_SUBSCRIPTION_UPDATE_ATTEMPTED = "subscription_update_attempted";
    private static final String SUB_COMPONENT_LOCATION_EVENT_INTERACTOR = "location_event_interactor";
    private static final String TAG = "LocEventInteractor";
    private Callback callback;
    @NonNull
    private final LazyComponent<AlexaLocationPlatformServiceNetworkGateway> lazyAlpsNetworkGateway;
    @NonNull
    private final LazyComponent<LocationSensorAccess> lazyLocationSensorAccess;
    private LocationConfigurationRequest locationConfigurationRequest;
    private String subscriptionId;

    /* loaded from: classes9.dex */
    public interface Callback {
        void onInteractorStopped(String str);
    }

    public LocationEventInteractor(@NonNull LazyComponent<LocationSensorAccess> lazyComponent) {
        this.callback = null;
        this.lazyLocationSensorAccess = lazyComponent;
        this.lazyAlpsNetworkGateway = $$Lambda$CXFD74mKhWV2Hq_3OrYAD6E16p8.INSTANCE;
    }

    static /* synthetic */ void lambda$sendLocationToALPS$0() throws Throwable {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$sendLocationToALPS$2(Throwable th) throws Throwable {
        Log.w(TAG, "[ERROR] Fail to report location to ALPS.", th);
        Metrics.recordSuccess(METRIC_LOCATION_REPORT_SUCCESS, false, SUB_COMPONENT_LOCATION_EVENT_INTERACTOR);
    }

    @VisibleForTesting
    LocationConfigurationRequest createLocationConfigurationRequest() {
        LocationConfigurationRequest locationConfigurationRequest = new LocationConfigurationRequest();
        locationConfigurationRequest.mode = 3;
        locationConfigurationRequest.locationAccuracy = 0;
        locationConfigurationRequest.minimumDeliveryTimeInterval = 15000L;
        locationConfigurationRequest.minimumDeliveryDistance = 0.0f;
        return locationConfigurationRequest;
    }

    @VisibleForTesting
    LocationEventHandler createLocationEventHandler() {
        return new LocationEventHandler() { // from class: com.amazon.alexa.location.provider.interactor.event.LocationEventInteractor.1
            @Override // com.amazon.alexa.sensor.api.location.LocationEventHandler
            public void didReceiveLocation(Location location) {
                Metrics.recordOccurrence(LocationEventInteractor.METRIC_LOCATION_DATA_RECEIVED, LocationEventInteractor.SUB_COMPONENT_LOCATION_EVENT_INTERACTOR);
                LocationEventInteractor.this.sendLocationToALPS(location);
            }

            @Override // com.amazon.alexa.sensor.api.location.LocationEventHandler
            public void fineLocationUpdatesWillEnd() {
                Metrics.recordOccurrence(LocationEventInteractor.METRIC_SUBSCRIPTION_UPDATE_ATTEMPTED, LocationEventInteractor.SUB_COMPONENT_LOCATION_EVENT_INTERACTOR);
                LocationEventInteractor.this.subscribeForLocationUpdates();
            }
        };
    }

    @Override // com.amazon.alexa.location.provider.interactor.event.SensorEventInteractor
    public void execute() {
        if (this.locationConfigurationRequest == null) {
            this.locationConfigurationRequest = createLocationConfigurationRequest();
        }
        subscribeForLocationUpdates();
    }

    @VisibleForTesting
    void sendLocationToALPS(Location location) {
        String sb;
        if (location == null) {
            Log.e(TAG, "[ERROR] Location received from CLD is null");
            return;
        }
        Metrics.recordOccurrence(METRIC_LOCATION_REPORT_STARTED, SUB_COMPONENT_LOCATION_EVENT_INTERACTOR);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(CAUSE_PREFIX);
        if (TextUtils.isEmpty(this.subscriptionId)) {
            sb = "";
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("::");
            outline107.append(this.subscriptionId);
            sb = outline107.toString();
        }
        sb2.append(sb);
        Completable reportLocationRxSingle = this.lazyAlpsNetworkGateway.mo10268get().reportLocationRxSingle(location.getLatitude(), location.getLongitude(), location.getAccuracy(), sb2.toString());
        if (reportLocationRxSingle == null) {
            return;
        }
        reportLocationRxSingle.subscribeOn(Schedulers.io()).doFinally($$Lambda$LocationEventInteractor$k6xNbKGeixrZFlVrg7rmW2b19Ak.INSTANCE).subscribe($$Lambda$LocationEventInteractor$rP3JC7TYdio2qGzMZWJBdMlcsw.INSTANCE, $$Lambda$LocationEventInteractor$EK7L6FEPOHOv5tvPpxCyZFVGuU.INSTANCE);
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void setLocationConfigurationRequest(@NonNull LocationConfigurationRequest locationConfigurationRequest) {
        this.locationConfigurationRequest = locationConfigurationRequest;
    }

    public void setSubscriptionId(String str) {
        this.subscriptionId = str;
    }

    @Override // com.amazon.alexa.location.provider.interactor.event.SensorEventInteractor
    public void stop() {
        unsubscribeFromLocationUpdates();
        Callback callback = this.callback;
        if (callback != null) {
            callback.onInteractorStopped(this.subscriptionId);
        }
    }

    @VisibleForTesting
    void subscribeForLocationUpdates() {
        Error subscribeForLocationUpdates = this.lazyLocationSensorAccess.mo10268get().subscribeForLocationUpdates(FEATURE_ID, this.locationConfigurationRequest, createLocationEventHandler());
        if (subscribeForLocationUpdates != null) {
            Log.w(TAG, String.format("Received error code %d when subscribing for location updates; aborting setup process", Integer.valueOf(subscribeForLocationUpdates.errorCode)));
            stop();
        }
    }

    @VisibleForTesting
    void unsubscribeFromLocationUpdates() {
        this.lazyLocationSensorAccess.mo10268get().unsubscribeFromLocationUpdates(FEATURE_ID, 3);
    }

    public LocationEventInteractor(@NonNull LazyComponent<LocationSensorAccess> lazyComponent, @NonNull LazyComponent<AlexaLocationPlatformServiceNetworkGateway> lazyComponent2) {
        this.callback = null;
        this.lazyLocationSensorAccess = lazyComponent;
        this.lazyAlpsNetworkGateway = lazyComponent2;
    }
}
