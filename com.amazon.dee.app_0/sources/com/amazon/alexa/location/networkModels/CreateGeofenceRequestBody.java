package com.amazon.alexa.location.networkModels;

import com.amazon.alexa.location.models.ALSCircularRegion;
/* loaded from: classes9.dex */
public class CreateGeofenceRequestBody {
    private ALSCircularRegion circularRegion;

    public CreateGeofenceRequestBody(double d, double d2, double d3) {
        this.circularRegion = new ALSCircularRegion(d, d2, d3);
    }

    public ALSCircularRegion getCircularRegion() {
        return this.circularRegion;
    }
}
