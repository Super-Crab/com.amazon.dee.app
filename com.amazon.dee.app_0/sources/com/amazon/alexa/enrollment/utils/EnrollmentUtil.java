package com.amazon.alexa.enrollment.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.route.EnrollmentContext;
import com.amazon.alexa.location.utils.EndpointsUtil;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public final class EnrollmentUtil {
    private static final String TAG = GeneratedOutlineSupport1.outline39(EnrollmentUtil.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));

    private EnrollmentUtil() {
    }

    public static String getAlexaAPIEndpoint(EnvironmentService environmentService) {
        String apiGatewayEndpoint = environmentService.getApiGatewayEndpoint();
        if (TextUtils.isEmpty(apiGatewayEndpoint)) {
            Log.e(TAG, "Unable to fetch Alexa API endpoint and defaulting to NA endpoint");
            return EndpointsUtil.ENDPOINT_NA_PROD;
        }
        GeneratedOutlineSupport1.outline163("Fetched the Alexa API endpoint ", apiGatewayEndpoint, TAG);
        return apiGatewayEndpoint;
    }

    public static AlexaMobileFrameworkApis getAlexaMobileFrameworkApisInstance(Context context) {
        return new AlexaMobileFrameworkApis(context);
    }

    public static AlexaServicesConnection getAlexaServicesConnection(Context context) {
        Log.i(TAG, "inside getAlexaServicesConnection");
        return new AlexaServicesConnection(context);
    }

    public static boolean isOOBE(String str) {
        return EnrollmentContext.VOX_ENROLLMENT_FROM_COMMS_OOBE.name().equals(str) || EnrollmentContext.VOX_ENROLLMENT_FROM_PROFILE_OOBE.name().equals(str);
    }

    public static void playSound(Context context, int i) {
        Log.i(TAG, "playing the requested sound");
        MediaPlayer create = MediaPlayer.create(context, i);
        if (create != null) {
            create.start();
        }
    }
}
