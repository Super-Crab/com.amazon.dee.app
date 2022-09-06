package com.amazon.alexa.sensor.location;

import android.content.Context;
import android.location.Location;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.alexa.sensor.api.location.Error;
import com.amazon.alexa.sensor.api.location.LocationAuthorization;
import com.amazon.alexa.sensor.api.location.LocationConfiguration;
import com.amazon.alexa.sensor.api.location.LocationConfigurationRequest;
import com.amazon.alexa.sensor.api.location.LocationEventHandler;
import com.amazon.alexa.sensor.api.location.LocationSensorAccess;
import com.amazon.alexa.sensor.api.metrics.SensorAccessMetricsRecorder;
import com.amazon.alexa.sensor.location.Metrics;
import java.util.Collection;
/* loaded from: classes10.dex */
public class LocationSensorAccessor implements LocationSensorAccess, LocationApiManagerDelegate, ConfigurationChangeHandler {
    public static final String TAG = "LocationSensorAccessor";
    private final LocationApiManager locationApiManager;
    private final LazyComponent<SensorAccessMetricsRecorder> sensorAccessMetricsRecorder;
    @VisibleForTesting
    SubscriberManager subscriberManager;

    public LocationSensorAccessor(@NonNull ComponentGetter componentGetter, @NonNull Context context) {
        this.sensorAccessMetricsRecorder = componentGetter.get(SensorAccessMetricsRecorder.class);
        this.locationApiManager = new LocationApiManager(context, this.sensorAccessMetricsRecorder);
        this.subscriberManager = new SubscriberManager(this, this.sensorAccessMetricsRecorder);
    }

    public static void bindLocationSensorAccessor() {
        ComponentRegistry.getInstance().bind(LocationSensorAccess.class, "com.amazon.alexa.sensor.location.LocationSensorAccessor");
    }

    @Override // com.amazon.alexa.sensor.api.location.LocationSensorAccess
    public LocationAuthorization authorizationStatus(String str) {
        boolean isAuthorized = this.subscriberManager.isAuthorized(str);
        this.sensorAccessMetricsRecorder.mo10268get().recordOccurrence(Metrics.Events.AUTHORIZED_CALL, Metrics.fullComponentName(Metrics.SubComponents.CHECK_AUTHORIZATION), Metrics.SubComponents.CHECK_AUTHORIZATION, isAuthorized);
        if (!isAuthorized) {
            return new LocationAuthorization(false, false, false);
        }
        LocationApiAuthorization authorization = this.locationApiManager.authorization();
        return new LocationAuthorization(true, authorization.isLocationAccessAuthorized, authorization.isPreciseLocationAuthorized);
    }

    @Override // com.amazon.alexa.sensor.location.ConfigurationChangeHandler
    public void configurationDidChange(Collection<Subscription> collection) {
        this.locationApiManager.setConfiguration(collection);
    }

    @Override // com.amazon.alexa.sensor.api.location.LocationSensorAccess
    public LocationConfiguration currentConfiguration(String str) {
        boolean isAuthorized = this.subscriberManager.isAuthorized(str);
        this.sensorAccessMetricsRecorder.mo10268get().recordOccurrence(Metrics.Events.AUTHORIZED_CALL, Metrics.fullComponentName(Metrics.SubComponents.REPORT_CONFIGURATION), Metrics.SubComponents.REPORT_CONFIGURATION, isAuthorized);
        if (isAuthorized) {
            return this.locationApiManager.configuration();
        }
        return null;
    }

    @Override // com.amazon.alexa.sensor.location.LocationApiManagerDelegate
    public void didReceiveLocation(Location location) {
        this.subscriberManager.notifySubscribers(location, this.locationApiManager.configuration());
    }

    @Override // com.amazon.alexa.sensor.api.location.LocationSensorAccess
    public boolean isSubscribedForLocationUpdates(String str, int i) {
        return this.subscriberManager.isSubscribedForLocationUpdates(str, i);
    }

    @Override // com.amazon.alexa.sensor.api.location.LocationSensorAccess
    public void locationForFeatureId(String str, LocationEventHandler locationEventHandler) {
        boolean isAuthorized = this.subscriberManager.isAuthorized(str);
        this.sensorAccessMetricsRecorder.mo10268get().recordOccurrence(Metrics.Events.AUTHORIZED_CALL, Metrics.fullComponentName(Metrics.SubComponents.SINGLE_LOCATION), Metrics.SubComponents.SINGLE_LOCATION, isAuthorized);
        if (!isAuthorized) {
            Log.e("LocationSensorAccessor", "locationForFeatureId called by unauthorized feature");
            locationEventHandler.didReceiveLocation(null);
            return;
        }
        this.locationApiManager.location(locationEventHandler);
    }

    @Override // com.amazon.alexa.sensor.api.location.LocationSensorAccess
    public Error subscribeForLocationUpdates(String str, LocationConfigurationRequest locationConfigurationRequest, LocationEventHandler locationEventHandler) {
        return this.subscriberManager.subscribeForLocationUpdates(str, locationConfigurationRequest, locationEventHandler);
    }

    @Override // com.amazon.alexa.sensor.api.location.LocationSensorAccess
    public void unsubscribeFromLocationUpdates(String str, int i) {
        this.subscriberManager.unsubscribeFromLocationUpdates(str, i);
    }

    @VisibleForTesting
    LocationSensorAccessor(@NonNull LocationApiManager locationApiManager, @NonNull LazyComponent<SensorAccessMetricsRecorder> lazyComponent) {
        this.sensorAccessMetricsRecorder = lazyComponent;
        this.locationApiManager = locationApiManager;
        this.subscriberManager = new SubscriberManager(this, this.sensorAccessMetricsRecorder);
    }
}
