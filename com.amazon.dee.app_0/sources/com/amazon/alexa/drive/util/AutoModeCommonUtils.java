package com.amazon.alexa.drive.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.drive.metrics.DriveModeMetricsHelper;
import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
/* loaded from: classes7.dex */
public class AutoModeCommonUtils {
    private static final String ALEXA_APP_PACKAGE_NAME = "com.amazon.dee.app";
    private static final String ALEXA_VOICE_ACTIVITY_CLASS_NAME = "com.amazon.alexa.voice.ui.VoiceActivity";
    private static final String TAG = "AutoModeCommonUtils";
    private final DriveModeMetrics mDriveModeMetrics;
    private final DriveModeMetricsHelper mDriveModeMetricsHelper;

    public AutoModeCommonUtils(DriveModeMetricsHelper driveModeMetricsHelper, DriveModeMetrics driveModeMetrics) {
        this.mDriveModeMetricsHelper = driveModeMetricsHelper;
        this.mDriveModeMetrics = driveModeMetrics;
    }

    public void launchAlexa(Context context) {
        RoutingService routingService = (RoutingService) GeneratedOutlineSupport1.outline21(RoutingService.class);
        Preconditions.checkNotNull(routingService);
        RouteContext currentRoute = routingService.getCurrentRoute();
        if (currentRoute != null) {
            String channel = this.mDriveModeMetricsHelper.getChannel(currentRoute.getRoute().getName());
            GeneratedOutlineSupport1.outline163("launchAlexa", channel, TAG);
            if (channel != null) {
                this.mDriveModeMetrics.logTTTButtonPressed(channel);
            }
        }
        if (context instanceof Activity) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.amazon.dee.app", ALEXA_VOICE_ACTIVITY_CLASS_NAME));
            intent.setFlags(67108864);
            ((Activity) context).startActivity(intent);
        }
    }
}
