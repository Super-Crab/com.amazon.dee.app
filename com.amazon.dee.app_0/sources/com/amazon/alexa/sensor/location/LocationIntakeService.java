package com.amazon.alexa.sensor.location;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.sensor.api.location.LocationSensorAccess;
import com.amazon.alexa.sensor.api.metrics.SensorAccessMetricsRecorder;
import com.amazon.alexa.sensor.api.metrics.events.CounterEvent;
import com.amazon.alexa.sensor.location.Metrics;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationResult;
/* loaded from: classes10.dex */
public class LocationIntakeService extends IntentService {
    private static final String TAG = "LocationIntakeService";

    public LocationIntakeService() {
        super(TAG);
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        if (intent == null) {
            return;
        }
        SensorAccessMetricsRecorder sensorAccessMetricsRecorder = (SensorAccessMetricsRecorder) ComponentRegistry.getInstance().getLazy(SensorAccessMetricsRecorder.class).mo10268get();
        LocationSensorAccess locationSensorAccess = (LocationSensorAccess) ComponentRegistry.getInstance().getLazy(LocationSensorAccess.class).mo10268get();
        if (!(locationSensorAccess instanceof LocationSensorAccessor)) {
            sensorAccessMetricsRecorder.recordEvent(Metrics.Events.INCOMPATIBLE_ACCESSOR, Metrics.fullComponentName(Metrics.SubComponents.LOCATION_UPDATES), Metrics.SubComponents.LOCATION_UPDATES);
            return;
        }
        LocationSensorAccessor locationSensorAccessor = (LocationSensorAccessor) locationSensorAccess;
        LocationResult extractResult = LocationResult.extractResult(intent);
        boolean z = true;
        sensorAccessMetricsRecorder.recordOccurrence(Metrics.Events.LOCATION_RESULTS_RECEIVED, Metrics.fullComponentName(Metrics.SubComponents.LOCATION_UPDATES), Metrics.SubComponents.LOCATION_UPDATES, extractResult != null);
        if (extractResult != null) {
            CounterEvent counterEvent = new CounterEvent(Metrics.Events.LOCATIONS_RECEIVED, Metrics.fullComponentName(Metrics.SubComponents.LOCATION_UPDATES), Metrics.SubComponents.LOCATION_UPDATES);
            counterEvent.setValue(extractResult.getLocations().size());
            sensorAccessMetricsRecorder.recordCounter(counterEvent);
            for (Location location : extractResult.getLocations()) {
                locationSensorAccessor.didReceiveLocation(location);
            }
        }
        LocationAvailability extractLocationAvailability = LocationAvailability.extractLocationAvailability(intent);
        String fullComponentName = Metrics.fullComponentName(Metrics.SubComponents.LOCATION_UPDATES);
        if (extractLocationAvailability == null) {
            z = false;
        }
        sensorAccessMetricsRecorder.recordOccurrence(Metrics.Events.LOCATION_AVAILABILITIES_RECEIVED, fullComponentName, Metrics.SubComponents.LOCATION_UPDATES, z);
    }
}
