package com.amazon.alexa.sensor.location;

import android.location.Location;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.alexa.sensor.SensorUtils;
import com.amazon.alexa.sensor.api.location.Error;
import com.amazon.alexa.sensor.api.location.LocationConfiguration;
import com.amazon.alexa.sensor.api.location.LocationConfigurationRequest;
import com.amazon.alexa.sensor.api.location.LocationEventHandler;
import com.amazon.alexa.sensor.api.metrics.SensorAccessMetricsRecorder;
import com.amazon.alexa.sensor.api.metrics.events.CounterEvent;
import com.amazon.alexa.sensor.location.Metrics;
import com.amazon.alexa.sensor.location.RequestingFeatures;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public class SubscriberManager {
    private static final long FORCE_FOREGROUND_TIME_LIMIT_DEBUG = 600000;
    private static final long FORCE_FOREGROUND_TIME_LIMIT_RELEASE = 10800000;
    private static final long SUBSCRIPTION_PERIOD = 30000;
    private static final String TAG = "SubscriberManager";
    private final ConfigurationChangeHandler configurationChangeHandler;
    private final Handler handler;
    private final LazyComponent<SensorAccessMetricsRecorder> sensorAccessMetricsRecorder;
    @VisibleForTesting
    final Map<String, Subscription> subscriptions;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SubscriberManager(@NonNull ConfigurationChangeHandler configurationChangeHandler, @NonNull LazyComponent<SensorAccessMetricsRecorder> lazyComponent) {
        this(configurationChangeHandler, lazyComponent, new Handler(SensorUtils.getLooper(TAG)));
    }

    @VisibleForTesting
    static boolean authorizeTrackingRequest(@Nullable String str, @Nullable LocationConfigurationRequest locationConfigurationRequest) {
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "authorizeTrackingRequest() called with no feature ID to authorize");
            return false;
        } else if (locationConfigurationRequest == null) {
            Log.w(TAG, "authorizeTrackingRequest() called with no request to authorize");
            return false;
        } else {
            RequestingFeatures.Feature feature = RequestingFeatures.AUTHORIZED_FEATURES.get(str);
            if (feature == null) {
                Log.w(TAG, String.format("Feature %s not authorized to track location", str));
                return false;
            }
            int i = locationConfigurationRequest.appStateRequirement;
            if (i != 0 && !feature.appStateRequirements.contains(Integer.valueOf(i))) {
                Log.w(TAG, String.format("App state requirements from feature %s had exceeded its authority", str));
                return false;
            } else if (!locationConfigurationRequest.keepTrackingAfterAppKills || feature.allowTrackingAfterAppKills) {
                return true;
            } else {
                Log.w(TAG, String.format("Feature %s not authorized to track location after app kills", str));
                return false;
            }
        }
    }

    private static long getForceForegroundTimeLimit() {
        if (SensorUtils.isProdRelease()) {
            return FORCE_FOREGROUND_TIME_LIMIT_RELEASE;
        }
        return 600000L;
    }

    private void resetTimers(Subscription subscription) {
        Runnable runnable = subscription.expirationCallback;
        if (runnable != null) {
            this.handler.removeCallbacks(runnable);
            subscription.expirationCallback = null;
        }
    }

    private void updateConfiguration() {
        this.configurationChangeHandler.configurationDidChange(this.subscriptions.values());
    }

    public boolean isAuthorized(@Nullable String str) {
        return !TextUtils.isEmpty(str) && RequestingFeatures.AUTHORIZED_FEATURES.containsKey(str);
    }

    public boolean isSubscribedForLocationUpdates(String str, int i) {
        return !TextUtils.isEmpty(str) && this.subscriptions.get(Subscription.buildSubscriptionKey(str, i)) != null;
    }

    public /* synthetic */ void lambda$setTimers$0$SubscriberManager(Subscription subscription) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Subscription is about to end for feature ");
        outline107.append(subscription.featureId);
        outline107.toString();
        subscription.isExpired = true;
        subscription.delegate.fineLocationUpdatesWillEnd();
        if (subscription.isExpired) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Unsubscribing feature ");
            outline1072.append(subscription.featureId);
            outline1072.toString();
            unsubscribeFromLocationUpdates(subscription.featureId, subscription.configuration.mode);
        }
    }

    public void notifySubscribers(Location location, LocationConfiguration locationConfiguration) {
        CounterEvent counterEvent = new CounterEvent(Metrics.Events.LOCATION_DELIVERY_ATTEMPTED, Metrics.fullComponentName(Metrics.SubComponents.LOCATION_UPDATES), Metrics.SubComponents.LOCATION_UPDATES);
        counterEvent.setValue(this.subscriptions.size());
        CounterEvent counterEvent2 = new CounterEvent(Metrics.Events.LOCATION_DELIVERED, Metrics.fullComponentName(Metrics.SubComponents.LOCATION_UPDATES), Metrics.SubComponents.LOCATION_UPDATES);
        for (Subscription subscription : this.subscriptions.values()) {
            LocationConfigurationRequest locationConfigurationRequest = subscription.configuration;
            if (locationConfiguration.locationAccuracy != 100 || locationConfigurationRequest.locationAccuracy != 0) {
                if (locationConfigurationRequest.mode == 0) {
                    subscription.delegate.didReceiveLocation(location);
                    counterEvent2.setValue(counterEvent2.getValue() + 1);
                    unsubscribeFromLocationUpdates(subscription.featureId, 0);
                } else {
                    long currentTimeMillis = System.currentTimeMillis();
                    long j = subscription.lastSentTime;
                    if (j <= 0 || currentTimeMillis - j >= locationConfigurationRequest.minimumDeliveryTimeInterval) {
                        Location location2 = subscription.lastSentPosition;
                        if (location2 == null || location.distanceTo(location2) >= locationConfigurationRequest.minimumDeliveryDistance) {
                            subscription.delegate.didReceiveLocation(location);
                            subscription.lastSentPosition = location;
                            subscription.lastSentTime = currentTimeMillis;
                            counterEvent2.setValue(counterEvent2.getValue() + 1);
                        }
                    }
                }
            }
        }
        this.sensorAccessMetricsRecorder.mo10268get().recordCounter(counterEvent);
        this.sensorAccessMetricsRecorder.mo10268get().recordCounter(counterEvent2);
    }

    @VisibleForTesting
    void setTimers(final Subscription subscription) {
        Runnable runnable = new Runnable() { // from class: com.amazon.alexa.sensor.location.-$$Lambda$SubscriberManager$u3T1vV4fxOKmoRuj9JelUv6rixk
            @Override // java.lang.Runnable
            public final void run() {
                SubscriberManager.this.lambda$setTimers$0$SubscriberManager(subscription);
            }
        };
        resetTimers(subscription);
        subscription.expirationCallback = runnable;
        this.handler.postDelayed(runnable, 30000L);
    }

    public Error subscribeForLocationUpdates(String str, LocationConfigurationRequest locationConfigurationRequest, LocationEventHandler locationEventHandler) {
        boolean authorizeTrackingRequest = authorizeTrackingRequest(str, locationConfigurationRequest);
        this.sensorAccessMetricsRecorder.mo10268get().recordOccurrence(Metrics.Events.AUTHORIZED_CALL, Metrics.fullComponentName(Metrics.SubComponents.LOCATION_SUBSCRIPTION), Metrics.SubComponents.LOCATION_SUBSCRIPTION, authorizeTrackingRequest);
        if (!authorizeTrackingRequest) {
            return new Error(1);
        }
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = locationConfigurationRequest.appStateRequirement == 2;
        String buildSubscriptionKey = Subscription.buildSubscriptionKey(str, locationConfigurationRequest.mode);
        Subscription subscription = this.subscriptions.get(buildSubscriptionKey);
        long j = -1;
        if (subscription == null) {
            subscription = new Subscription();
            subscription.featureId = str;
            subscription.lastSentPosition = null;
            subscription.lastSentTime = -1L;
            if (z) {
                j = currentTimeMillis;
            }
            subscription.forcedForegroundStartTime = j;
        } else {
            this.sensorAccessMetricsRecorder.mo10268get().recordEvent(Metrics.Events.SUBSCRIPTION_UPDATE_REQUESTED, Metrics.fullComponentName(Metrics.SubComponents.LOCATION_SUBSCRIPTION), Metrics.SubComponents.LOCATION_SUBSCRIPTION);
            boolean z2 = subscription.configuration.appStateRequirement == 2;
            if (!z) {
                subscription.forcedForegroundStartTime = -1L;
            } else if (!z2) {
                subscription.forcedForegroundStartTime = currentTimeMillis;
            } else if (currentTimeMillis - subscription.forcedForegroundStartTime > getForceForegroundTimeLimit()) {
                Log.w(TAG, String.format("Feature %s has forced foreground for more than %d milliseconds; rejecting update request", str, Long.valueOf(getForceForegroundTimeLimit())));
                return new Error(31);
            }
        }
        subscription.configuration = locationConfigurationRequest;
        subscription.delegate = locationEventHandler;
        subscription.lastUpdateTime = currentTimeMillis;
        subscription.isExpired = false;
        int i = locationConfigurationRequest.mode;
        if (i == 2 || i == 3) {
            setTimers(subscription);
        }
        this.subscriptions.put(buildSubscriptionKey, subscription);
        updateConfiguration();
        return null;
    }

    public void unsubscribeFromLocationUpdates(String str, int i) {
        boolean isAuthorized = isAuthorized(str);
        this.sensorAccessMetricsRecorder.mo10268get().recordOccurrence(Metrics.Events.AUTHORIZED_CALL, Metrics.fullComponentName(Metrics.SubComponents.CANCEL_SUBSCRIPTION), Metrics.SubComponents.CANCEL_SUBSCRIPTION, isAuthorized);
        if (!isAuthorized) {
            return;
        }
        String buildSubscriptionKey = Subscription.buildSubscriptionKey(str, i);
        Subscription subscription = this.subscriptions.get(buildSubscriptionKey);
        if (subscription == null) {
            this.sensorAccessMetricsRecorder.mo10268get().recordEvent(Metrics.Events.NO_SUBSCRIPTION_TO_CANCEL, Metrics.fullComponentName(Metrics.SubComponents.CANCEL_SUBSCRIPTION), Metrics.SubComponents.CANCEL_SUBSCRIPTION);
            this.subscriptions.remove(buildSubscriptionKey);
            return;
        }
        resetTimers(subscription);
        this.subscriptions.remove(buildSubscriptionKey);
        updateConfiguration();
    }

    @VisibleForTesting
    SubscriberManager(@NonNull ConfigurationChangeHandler configurationChangeHandler, @NonNull LazyComponent<SensorAccessMetricsRecorder> lazyComponent, @NonNull Handler handler) {
        this.configurationChangeHandler = configurationChangeHandler;
        this.sensorAccessMetricsRecorder = lazyComponent;
        this.handler = handler;
        this.subscriptions = new HashMap();
    }
}
