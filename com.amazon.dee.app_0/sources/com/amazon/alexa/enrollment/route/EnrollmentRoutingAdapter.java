package com.amazon.alexa.enrollment.route;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SimpleArrayMap;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.model.EnrollmentGateway;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingAdapter;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.internal.ImmutableList;
import dagger.Lazy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes7.dex */
public class EnrollmentRoutingAdapter implements RoutingAdapter {
    protected static final String ELEMENTS_RECOGNIZED_SETTIGNS = "ELEMENTS_RECOGNIZED_VOICES";
    protected static final String ENROLLMENT_CONTEXT = "enrollmentContext";
    private static final String FAILURE_ROUTE_NAME = "failure_route_name";
    private static final String FAILURE_ROUTE_URI = "failure_route_uri";
    private static final String SUCCESS_ROUTE_NAME = "success_route_name";
    private static final String SUCCESS_ROUTE_URI = "success_route_uri";
    private static final String TAG = GeneratedOutlineSupport1.outline39(EnrollmentRoutingAdapter.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    private static final Map<String, String> VOX_DEVICE_SETTINGS_OOBE_ROUTES_MAP = new HashMap();
    private static final List<String> VOX_ENROLLMENT_ROUTES = ImmutableList.of((Object[]) new String[]{"voice-enrollment", "voice-enrollment-oobe"});
    private final SimpleArrayMap<String, RoutingAdapter.RouteConfiguration> configurations = new SimpleArrayMap<>();
    private final Lazy<Context> contextLazy;
    private final Lazy<EnrollmentGateway> enrollmentServiceLazy;
    private Lazy<IdentityService> identityService;

    static {
        VOX_DEVICE_SETTINGS_OOBE_ROUTES_MAP.put("elements_success_route_name", "v2/settings/your-voice");
        VOX_DEVICE_SETTINGS_OOBE_ROUTES_MAP.put("elements_failure_route_name", "v2/settings/recognized-voices");
        VOX_DEVICE_SETTINGS_OOBE_ROUTES_MAP.put("web_success_route_name", "web");
        VOX_DEVICE_SETTINGS_OOBE_ROUTES_MAP.put("web_failure_route_name", "web");
        VOX_DEVICE_SETTINGS_OOBE_ROUTES_MAP.put("web_success_route_uri", "voiceprofile/home");
        VOX_DEVICE_SETTINGS_OOBE_ROUTES_MAP.put("web_failure_route_uri", "enrollment-consent/optout");
    }

    public EnrollmentRoutingAdapter(Lazy<Context> lazy, Lazy<EnrollmentGateway> lazy2) {
        this.contextLazy = lazy;
        this.enrollmentServiceLazy = lazy2;
        this.configurations.put("voice-enrollment", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("voice-enrollment-oobe", RoutingAdapter.RouteConfiguration.all());
    }

    private Map<String, String> getRoutesMap(String str) {
        if (Arrays.asList("VOX_ENROLLMENT_FROM_OOBE", "VOX_ENROLLMENT_FROM_ACTIVITY_CARDS", "VOX_ENROLLMENT_FROM_APP_NOTIFICATIONS").contains(str)) {
            return VOX_DEVICE_SETTINGS_OOBE_ROUTES_MAP;
        }
        return new HashMap();
    }

    private boolean isUserEnabledForElementsRecognizedVoicesSettings() {
        FeatureServiceV2 featureServiceV2 = (FeatureServiceV2) GeneratedOutlineSupport1.outline20(FeatureServiceV2.class);
        if (featureServiceV2 != null && featureServiceV2.hasAccess("ELEMENTS_RECOGNIZED_VOICES", false)) {
            Log.i(TAG, "ELEMENTS_RECOGNIZED_VOICES: Feature enabled");
            return true;
        }
        Log.i(TAG, "ELEMENTS_RECOGNIZED_VOICES: Feature not enabled");
        return false;
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void enter(@NonNull Route route, Route route2) {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void exit() {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    @Nullable
    public RoutingAdapter.RouteConfiguration getConfiguration(@NonNull Route route) {
        return this.configurations.get(route.getName());
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public int getId() {
        return 7;
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void leave(@NonNull Route route, Route route2) {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void navigate(@NonNull RouteContext routeContext, Runnable runnable) {
        String str;
        String str2;
        String str3;
        String str4;
        Route route = routeContext.getRoute();
        if (VOX_ENROLLMENT_ROUTES.contains(route.getName())) {
            Log.i(TAG, "Starting voice enrollment training screens");
            try {
                EnrollmentGateway mo358get = this.enrollmentServiceLazy.mo358get();
                Context mo358get2 = this.contextLazy.mo358get();
                String string = routeContext.getString(ENROLLMENT_CONTEXT, "");
                String str5 = TAG;
                Log.i(str5, "Enrollment context is " + string);
                boolean isUserEnabledForElementsRecognizedVoicesSettings = isUserEnabledForElementsRecognizedVoicesSettings();
                Map<String, String> routesMap = getRoutesMap(string);
                if (isUserEnabledForElementsRecognizedVoicesSettings) {
                    str = routesMap.get("elements_success_route_name");
                    str2 = routesMap.get("elements_success_route_uri");
                    str3 = routesMap.get("elements_failure_route_name");
                    str4 = routesMap.get("elements_failure_route_uri");
                } else {
                    str = routesMap.get("web_success_route_name");
                    str2 = routesMap.get("web_success_route_uri");
                    str3 = routesMap.get("web_failure_route_name");
                    str4 = routesMap.get("web_failure_route_uri");
                }
                String string2 = routeContext.getString(SUCCESS_ROUTE_NAME, str);
                String string3 = routeContext.getString(SUCCESS_ROUTE_URI, str2);
                String string4 = routeContext.getString(FAILURE_ROUTE_NAME, str3);
                String string5 = routeContext.getString(FAILURE_ROUTE_URI, str4);
                String str6 = TAG;
                Log.i(str6, "SuccessRouteName :- " + string2 + " SuccessRouteURI " + string3 + " FailureRouteName " + string4 + " FailureRouteURI " + string5);
                mo358get.startVoiceEnrollmentTraining(mo358get2, string2, string3, string4, string5, string);
                runnable.run();
                return;
            } catch (ActivityNotFoundException e) {
                Log.e(TAG, "Activity not found to start voice enrollment", e);
                return;
            }
        }
        String str7 = TAG;
        Log.w(str7, "Not starting voice trailing screens as the route is: " + route);
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void push(RouteContext routeContext) {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void reenter() {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void replace(@NonNull RouteContext routeContext) {
    }
}
