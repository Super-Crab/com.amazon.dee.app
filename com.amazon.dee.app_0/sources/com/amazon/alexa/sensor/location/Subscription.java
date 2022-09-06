package com.amazon.alexa.sensor.location;

import android.location.Location;
import androidx.annotation.NonNull;
import com.amazon.alexa.sensor.api.location.LocationConfigurationRequest;
import com.amazon.alexa.sensor.api.location.LocationEventHandler;
/* loaded from: classes10.dex */
public class Subscription {
    LocationConfigurationRequest configuration;
    LocationEventHandler delegate;
    Runnable expirationCallback;
    String featureId;
    long forcedForegroundStartTime;
    boolean isExpired;
    Location lastSentPosition;
    long lastSentTime;
    long lastUpdateTime;

    public static String buildSubscriptionKey(@NonNull String str, int i) {
        return String.format("%s.%s", str, i != 0 ? i != 1 ? i != 2 ? i != 3 ? "UNKNOWN" : "ALL_CHANGES" : "ALL_CHANGES_DURING_FOREGROUND" : "SIGNIFICANT_CHANGES_ONLY" : "SINGLE_LOCATION");
    }
}
