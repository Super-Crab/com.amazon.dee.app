package com.amazon.alexa.voice.routing;

import android.app.Activity;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.biloba.utils.BilobaRouteUtil;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.voice.metrics.VoxLaunchConstants;
import com.amazon.alexa.voice.metrics.VoxMetricEvent;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.alexa.voice.tta.TypeToAlexaFeatureEnabler;
import com.amazon.alexa.voice.ui.VoiceActivity;
import com.amazon.alexa.voice.ui.internal.Logger;
import com.amazon.alexa.voice.ui.onedesign.scrim.ScrimParameters;
import com.amazon.alexa.voice.utils.RouteHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes11.dex */
public class RouteToVoiceAction {
    private static final String PARAMETER_HINT = "hint";
    private static final String PARAMETER_START_TIMESTAMP = "startTimestamp";
    private static final String SEPARATOR = ":";
    private final TypeToAlexaFeatureEnabler ttaFeatureEnabler;
    private final VoiceService voiceService;
    private final VoxMetricEventProcessingService voxMetricEventProcessingService;

    public RouteToVoiceAction(VoiceService voiceService, VoxMetricEventProcessingService voxMetricEventProcessingService, TypeToAlexaFeatureEnabler typeToAlexaFeatureEnabler) {
        this.voxMetricEventProcessingService = voxMetricEventProcessingService;
        this.voiceService = voiceService;
        this.ttaFeatureEnabler = typeToAlexaFeatureEnabler;
    }

    public static String getEventNameForVoxLaunchRoute(String str) {
        return GeneratedOutlineSupport1.outline72("VOX_LAUNCH_ROUTE:", str);
    }

    public static String getRefererFromMetricEventName(String str) {
        int indexOf = str.indexOf(":");
        return indexOf != -1 ? str.substring(indexOf + 1) : "";
    }

    private long getStartTimestamp(Map<String, String> map) {
        String str = map.get("startTimestamp");
        if (str != null) {
            try {
                return SystemClock.elapsedRealtime() - (getSystemCurrentTimeMillis() - Long.parseLong(str));
            } catch (Exception e) {
                Logger.error("Error parsing startTimestamp in voice route url", e);
            }
        }
        return 0L;
    }

    public static boolean isLaunchedFromRoute(String str) {
        return str != null && str.startsWith(VoxLaunchConstants.VOX_LAUNCH_ROUTE);
    }

    private void recordRouteToVoiceEvent(String str, long j) {
        this.voxMetricEventProcessingService.process(VoxMetricEvent.create(getEventNameForVoxLaunchRoute(str), j));
    }

    @VisibleForTesting
    long getSystemCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    public void startVoiceExperience(@NonNull Activity activity, @NonNull RouteContext routeContext) {
        Map<String, String> parseQueryParameters = RouteHelper.parseQueryParameters(routeContext.getString(BilobaRouteUtil.RAW_QUERY_STRING, ""));
        String referer = RouteHelper.getReferer(parseQueryParameters);
        recordRouteToVoiceEvent(referer, getStartTimestamp(parseQueryParameters));
        HashMap outline133 = GeneratedOutlineSupport1.outline133("referer", referer);
        String str = parseQueryParameters.get("hint");
        ScrimParameters.Builder builder = new ScrimParameters.Builder();
        if (!TextUtils.isEmpty(str)) {
            builder.showHint(true).hint(str);
            outline133.put("hint", str);
        }
        builder.showTTAIngress(this.ttaFeatureEnabler.isTypeToAlexaEnabled());
        this.voiceService.startVoiceExperience(activity, VoxLaunchConstants.ROUTE, VoiceActivity.ACTION_LAUNCH_FROM_ROUTE, outline133, VoiceService.InvocationType.ROUTE, builder.build());
    }
}
