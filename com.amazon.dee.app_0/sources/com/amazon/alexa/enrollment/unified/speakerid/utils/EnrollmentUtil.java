package com.amazon.alexa.enrollment.unified.speakerid.utils;

import android.text.TextUtils;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.protocols.environment.EnvironmentService;
/* loaded from: classes7.dex */
public final class EnrollmentUtil {
    private static final String NA_PROD_ALEXA_API_ENDPOINT = "https://api.amazonalexa.com";
    private static final String TAG = "EnrollmentUtil";

    private EnrollmentUtil() {
    }

    public static String getAlexaAPIEndpoint(EnvironmentService environmentService) {
        String apiGatewayEndpoint = environmentService.getApiGatewayEndpoint();
        if (TextUtils.isEmpty(apiGatewayEndpoint)) {
            Log.e(TAG, "Unable to fetch Alexa API endpoint and defaulting to NA endpoint");
            return "https://api.amazonalexa.com";
        }
        String str = TAG;
        Log.i(str, "Fetched the Alexa API endpoint " + apiGatewayEndpoint);
        return apiGatewayEndpoint;
    }
}
