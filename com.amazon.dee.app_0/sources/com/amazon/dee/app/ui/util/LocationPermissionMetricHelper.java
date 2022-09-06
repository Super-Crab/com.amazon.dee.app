package com.amazon.dee.app.ui.util;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.util.PermissionsUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsService;
import java.util.HashMap;
/* loaded from: classes12.dex */
public class LocationPermissionMetricHelper {
    private static final String TAG = Log.tag(LocationPermissionMetricHelper.class);
    private Activity activity;
    @VisibleForTesting
    PermissionsUtils.LocationPermissionAuthStatus locationPermissionAuthStatus;
    private MetricsService metricsService;

    /* renamed from: com.amazon.dee.app.ui.util.LocationPermissionMetricHelper$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$dee$app$util$PermissionsUtils$LocationPermissionAuthStatus = new int[PermissionsUtils.LocationPermissionAuthStatus.values().length];

        static {
            try {
                $SwitchMap$com$amazon$dee$app$util$PermissionsUtils$LocationPermissionAuthStatus[PermissionsUtils.LocationPermissionAuthStatus.GRANTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$util$PermissionsUtils$LocationPermissionAuthStatus[PermissionsUtils.LocationPermissionAuthStatus.GRANTED_FOREGROUND_ONLY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$util$PermissionsUtils$LocationPermissionAuthStatus[PermissionsUtils.LocationPermissionAuthStatus.DENIED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public LocationPermissionMetricHelper(@NonNull Activity activity, MetricsService metricsService) {
        this.activity = activity;
        this.metricsService = metricsService;
        this.locationPermissionAuthStatus = PermissionsUtils.getLocationPermissionAuthStatus(activity);
    }

    private String metricNameForPermissionStatus(PermissionsUtils.LocationPermissionAuthStatus locationPermissionAuthStatus) {
        int ordinal = locationPermissionAuthStatus.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return AlexaMetricsConstants.MetricEvents.LOCATION_PERMISSION_GRANTED_FOREGROUND_ONLY;
            }
            if (ordinal == 2) {
                return AlexaMetricsConstants.MetricEvents.LOCATION_PERMISSION_DENIED;
            }
            Log.e(TAG, "Location permission was not one of granted, foreground, or denied.");
            return AlexaMetricsConstants.MetricEvents.LOCATION_PERMISSION_ERROR;
        }
        return AlexaMetricsConstants.MetricEvents.LOCATION_PERMISSION_GRANTED_ALWAYS;
    }

    public void recordPermission() {
        this.metricsService.recordOccurrence(metricNameForPermissionStatus(this.locationPermissionAuthStatus), "Application", true, GeneratedOutlineSupport1.outline133("ownerIdentifier", "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
    }

    public void recordTransitionIfPermissionChanged() {
        PermissionsUtils.LocationPermissionAuthStatus locationPermissionAuthStatus = PermissionsUtils.getLocationPermissionAuthStatus(this.activity);
        if (this.locationPermissionAuthStatus != locationPermissionAuthStatus) {
            HashMap hashMap = new HashMap();
            hashMap.put(AlexaMetricsConstants.EventConstants.OLD_VALUE, metricNameForPermissionStatus(this.locationPermissionAuthStatus));
            hashMap.put(AlexaMetricsConstants.EventConstants.NEW_VALUE, metricNameForPermissionStatus(locationPermissionAuthStatus));
            hashMap.put("ownerIdentifier", "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
            this.metricsService.recordOccurrence(AlexaMetricsConstants.MetricEvents.LOCATION_PERMISSION_TRANSITION, "Application", true, hashMap);
            this.locationPermissionAuthStatus = locationPermissionAuthStatus;
        }
    }
}
