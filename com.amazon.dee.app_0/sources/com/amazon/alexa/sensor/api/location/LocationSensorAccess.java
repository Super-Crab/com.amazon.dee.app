package com.amazon.alexa.sensor.api.location;
/* loaded from: classes10.dex */
public interface LocationSensorAccess {
    LocationAuthorization authorizationStatus(String str);

    LocationConfiguration currentConfiguration(String str);

    boolean isSubscribedForLocationUpdates(String str, int i);

    void locationForFeatureId(String str, LocationEventHandler locationEventHandler);

    Error subscribeForLocationUpdates(String str, LocationConfigurationRequest locationConfigurationRequest, LocationEventHandler locationEventHandler);

    void unsubscribeFromLocationUpdates(String str, int i);
}
