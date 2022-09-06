package com.amazon.alexa.voice.routing;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.biloba.utils.BilobaRouteUtil;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.voice.tta.TypeToAlexaFeatureEnabler;
import com.amazon.alexa.voice.utils.RouteHelper;
import java.util.Map;
/* loaded from: classes11.dex */
class RouteToTypeAction {
    @VisibleForTesting
    static final String ACTION_SHOW_TYPE_UI = "amazon.intent.action.SHOW_ALEXA_TYPE_UI";
    @VisibleForTesting
    static final String EXTRA_HINT = "hint";
    @VisibleForTesting
    static final String EXTRA_REFERRER = "referer";
    @VisibleForTesting
    static final String EXTRA_START_TIMESTAMP = "startTimestamp";
    @VisibleForTesting
    static final String EXTRA_UTTERANCE = "utterance";
    private static final String TAG = "RouteToTypeAction";
    private final TypeToAlexaFeatureEnabler ttaFeatureEnabler;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RouteToTypeAction(TypeToAlexaFeatureEnabler typeToAlexaFeatureEnabler) {
        this.ttaFeatureEnabler = typeToAlexaFeatureEnabler;
    }

    private long getStartTimestamp(@NonNull Map<String, String> map) {
        String str = map.get("startTimestamp");
        if (str != null) {
            try {
                return Long.parseLong(str);
            } catch (NumberFormatException e) {
                String str2 = TAG;
                Log.e(str2, "getStartTimestamp: Invalid startTimestamp " + str, e);
            }
        }
        return getSystemCurrentTimeMillis();
    }

    private boolean launchTypeExperience(Activity activity, String str, String str2, long j) {
        Intent intent = new Intent();
        intent.setPackage(activity.getPackageName());
        intent.setAction(ACTION_SHOW_TYPE_UI);
        intent.addFlags(65536);
        intent.putExtra("referer", str);
        intent.putExtra("hint", str2);
        intent.putExtra("startTimestamp", j);
        try {
            activity.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "Unable to show UI. Could not find Type UI Activity", e);
            return false;
        }
    }

    @VisibleForTesting
    long getSystemCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean startTypeExperience(@NonNull Activity activity, @NonNull RouteContext routeContext) {
        if (!this.ttaFeatureEnabler.isTypeToAlexaEnabled()) {
            return false;
        }
        Map<String, String> parseQueryParameters = RouteHelper.parseQueryParameters(routeContext.getString(BilobaRouteUtil.RAW_QUERY_STRING, ""));
        String referer = RouteHelper.getReferer(parseQueryParameters);
        String str = parseQueryParameters.get("utterance");
        String str2 = "Started type experience from route request initiated by " + referer + " with " + str;
        return launchTypeExperience(activity, referer, str, getStartTimestamp(parseQueryParameters));
    }
}
