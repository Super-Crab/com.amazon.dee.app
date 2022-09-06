package com.amazon.alexa.location.phase3.sensor.osgeofence;

import android.content.Intent;
import com.amazon.alexa.location.utils.Clock;
import com.amazon.alexa.location.utils.MobilyticsUtil;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.google.android.gms.location.GeofencingEvent;
import io.reactivex.rxjava3.core.Observable;
/* loaded from: classes9.dex */
public class OsGeofenceDetector {
    private final Clock clock;
    private final LazyComponent<Mobilytics> mobilytics;
    private static final String TAG = "OsGeofenceDetector";
    private static final String COMPONENT_NAME = MobilyticsUtil.getComponentName(TAG);

    public OsGeofenceDetector(LazyComponent<Mobilytics> lazyComponent, Clock clock) {
        this.mobilytics = lazyComponent;
        this.clock = clock;
    }

    public Observable<OsGeofenceTriggerEvent> onTriggerGeofence(Intent intent) {
        String str = COMPONENT_NAME;
        this.mobilytics.mo10268get().recordOccurrence(MobilyticsUtil.MetricsID.OS_GEOFENCE_TRIGGERED, true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        return Observable.fromIterable(OsGeofenceTriggerEvent.fromGeofencingEvent(GeofencingEvent.fromIntent(intent), this.clock.millis()));
    }
}
