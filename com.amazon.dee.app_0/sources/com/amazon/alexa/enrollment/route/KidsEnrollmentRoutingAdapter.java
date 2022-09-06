package com.amazon.alexa.enrollment.route;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.collection.SimpleArrayMap;
import com.amazon.alexa.enrollment.api.model.KidsEnrollmentMetadata;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.model.EnrollmentGateway;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingAdapter;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.internal.ImmutableList;
import dagger.Lazy;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes7.dex */
public class KidsEnrollmentRoutingAdapter implements RoutingAdapter {
    protected static final String BACK_ROUTE = "backRoute";
    protected static final String CHILD_CUSTOMER_ID = "childCustomerId";
    protected static final String CHILD_NAME = "childName";
    protected static final String ENROLLMENT_CONTEXT = "enrollmentContext";
    protected static final String FAILURE_ROUTE = "failureRoute";
    protected static final String PERSON_ECID = "personECID";
    protected static final String PERSON_ID = "personId";
    protected static final String SHOW_CONSENT_COLLECTED_TOAST = "showParentalConsentToast";
    protected static final String SUCCESS_ROUTE = "successRoute";
    private final SimpleArrayMap<String, RoutingAdapter.RouteConfiguration> configurations = new SimpleArrayMap<>();
    private final Lazy<Context> contextLazy;
    private Lazy<IdentityService> identityService;
    private final Lazy<EnrollmentGateway> kidsEnrollmentGatewayLazy;
    private static final String TAG = GeneratedOutlineSupport1.outline39(KidsEnrollmentRoutingAdapter.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    private static final List<String> KIDS_ENROLLMENT_ROUTES = ImmutableList.of((Object[]) new String[]{"kids-enrollment"});

    public KidsEnrollmentRoutingAdapter(Lazy<Context> lazy, Lazy<EnrollmentGateway> lazy2, Lazy<IdentityService> lazy3) {
        this.contextLazy = lazy;
        this.kidsEnrollmentGatewayLazy = lazy2;
        this.identityService = lazy3;
        this.configurations.put("kids-enrollment", RoutingAdapter.RouteConfiguration.all());
    }

    @VisibleForTesting
    String decodeUTF8Route(String str) throws UnsupportedEncodingException {
        return URLDecoder.decode(str, StandardCharsets.UTF_8.name());
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
        return 19;
    }

    @VisibleForTesting
    JSONObject getJSONObjectFromRouteContext(RouteContext routeContext) throws JSONException {
        return new JSONObject(routeContext.getString("enrollmentMetadata"));
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void leave(@NonNull Route route, Route route2) {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void navigate(@NonNull RouteContext routeContext, Runnable runnable) {
        Route route = routeContext.getRoute();
        if (KIDS_ENROLLMENT_ROUTES.contains(route.getName())) {
            Log.i(TAG, "Starting kid's voice enrollment training screens");
            try {
                EnrollmentGateway mo358get = this.kidsEnrollmentGatewayLazy.mo358get();
                Context mo358get2 = this.contextLazy.mo358get();
                String string = routeContext.getString(ENROLLMENT_CONTEXT, "");
                Log.i(TAG, "Enrollment context is " + string);
                try {
                    try {
                        JSONObject jSONObjectFromRouteContext = getJSONObjectFromRouteContext(routeContext);
                        String string2 = jSONObjectFromRouteContext.getString("personId");
                        Log.i(TAG, "Person id is " + string2);
                        String string3 = jSONObjectFromRouteContext.getString(PERSON_ECID);
                        Log.i(TAG, "PersonECID is " + string3);
                        String string4 = jSONObjectFromRouteContext.getString(CHILD_NAME);
                        String string5 = jSONObjectFromRouteContext.getString(CHILD_CUSTOMER_ID);
                        Log.i(TAG, "Child customer id is " + string5);
                        Log.i(TAG, "Decoding routes");
                        String decodeUTF8Route = decodeUTF8Route(jSONObjectFromRouteContext.getString("successRoute"));
                        Log.i(TAG, "Success route is " + decodeUTF8Route);
                        String decodeUTF8Route2 = decodeUTF8Route(jSONObjectFromRouteContext.getString(FAILURE_ROUTE));
                        Log.i(TAG, "Failure route is " + decodeUTF8Route2);
                        boolean z = jSONObjectFromRouteContext.getBoolean(SHOW_CONSENT_COLLECTED_TOAST);
                        Log.i(TAG, "Show Consent collected Toast " + z);
                        String str = null;
                        try {
                            str = decodeUTF8Route(jSONObjectFromRouteContext.getString(BACK_ROUTE));
                            Log.i(TAG, "Back route is " + str);
                        } catch (JSONException unused) {
                            Log.i(TAG, "Back route is not provided.");
                        }
                        mo358get.startKidsVoiceEnrollmentTraining(mo358get2, new KidsEnrollmentMetadata(string5, string2, string3, string4, decodeUTF8Route, decodeUTF8Route2, str, z), string);
                        return;
                    } catch (UnsupportedEncodingException e) {
                        e = e;
                        String str2 = "When parsing the enrollment metadata" + e;
                        return;
                    }
                } catch (JSONException e2) {
                    e = e2;
                    String str22 = "When parsing the enrollment metadata" + e;
                    return;
                }
            } catch (ActivityNotFoundException e3) {
                String str3 = "Activity not found to start voice enrollment for kids" + e3;
                return;
            }
        }
        String str4 = "Not starting voice training screens as the route is: " + route;
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
