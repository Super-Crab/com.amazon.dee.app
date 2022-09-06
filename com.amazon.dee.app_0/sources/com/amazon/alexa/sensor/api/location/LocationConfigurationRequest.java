package com.amazon.alexa.sensor.api.location;

import androidx.annotation.IntRange;
/* loaded from: classes10.dex */
public class LocationConfigurationRequest {
    public static final int DEFAULT_ACCURACY = 100;
    public static final float DEFAULT_DISTANCE = 500.0f;
    public static final long DEFAULT_INTERVAL = 3600000;
    public static final long FASTEST_INTERVAL_ALL_CHANGES = 10000;
    public static final long FASTEST_INTERVAL_SIGNIFICANT_CHANGES_ONLY = 60000;
    public int appStateRequirement = 0;
    public boolean keepTrackingAfterAppKills = false;
    public int locationAccuracy;
    public float minimumDeliveryDistance;
    @IntRange(from = 10000)
    public long minimumDeliveryTimeInterval;
    public int mode;
}
