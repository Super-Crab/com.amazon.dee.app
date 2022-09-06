package com.amazon.alexa.sensor.api.location;

import android.location.Location;
/* loaded from: classes10.dex */
public interface LocationEventHandler {
    void didReceiveLocation(Location location);

    void fineLocationUpdatesWillEnd();
}
