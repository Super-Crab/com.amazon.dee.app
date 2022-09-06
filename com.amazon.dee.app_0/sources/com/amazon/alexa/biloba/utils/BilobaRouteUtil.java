package com.amazon.alexa.biloba.utils;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import okhttp3.HttpUrl;
/* loaded from: classes6.dex */
public final class BilobaRouteUtil {
    public static final String RAW_QUERY_STRING = "rawQueryString";
    private static final String SAMPLE_URL = "http://nothing.com?";
    private static final String TAG = "BilobaRouteUtil";

    private BilobaRouteUtil() {
    }

    public static void forceRouteTo(@NonNull RoutingService routingService, @NonNull String str) {
        logRoute(str);
        routingService.route(str).forceNavigate();
    }

    public static String getParameterValue(@NonNull RouteContext routeContext, @NonNull String str) {
        String string = routeContext.getString(str, "");
        if (string == null || string.isEmpty()) {
            String rawQueryString = getRawQueryString(routeContext);
            return !TextUtils.isEmpty(rawQueryString) ? getParameterValue(rawQueryString, str) : string;
        }
        return string;
    }

    public static String getRawQueryString(@NonNull RouteContext routeContext) {
        return (String) routeContext.get(RAW_QUERY_STRING);
    }

    private static void logRoute(String str) {
        String str2 = TAG;
        LogUtils.d(str2, "Routing to: " + str);
    }

    public static void routeTo(@NonNull RoutingService routingService, @NonNull String str) {
        logRoute(str);
        routingService.route(str).navigate();
    }

    public static void routeToUsingRouteMatch(@NonNull RoutingService routingService, @NonNull String str) {
        RoutingService.RoutingBuilder match = routingService.match(str);
        if (match != null) {
            logRoute(str);
            match.navigate();
            return;
        }
        String str2 = TAG;
        LogUtils.i(str2, "Route Not Found: " + str);
    }

    public static void routeToWithAddToBackStack(@NonNull RoutingService routingService, @NonNull String str) {
        logRoute(str);
        GeneratedOutlineSupport1.outline145(routingService, str);
    }

    public static String getParameterValue(@NonNull String str, @NonNull String str2) {
        HttpUrl parse = HttpUrl.parse(SAMPLE_URL + str);
        return parse != null ? parse.queryParameter(str2) : "";
    }
}
