package com.amazon.alexa.mobilytics.configuration;

import com.amazon.alexa.mobilytics.util.Log;
import com.amazon.dee.app.BuildConfig;
import com.amazonaws.regions.Regions;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes9.dex */
public final class CognitoPoolManager {
    private static final String TAG = Log.tag(CognitoPoolManager.class);
    private static final Map<Regions, String> GAMMA_COGNITO_POOL = new HashMap();
    private static final Map<Regions, String> PROD_COGNITO_POOL = new HashMap();
    private static final Map<Regions, String> BETA_COGNITO_POOL = new HashMap();

    static {
        BETA_COGNITO_POOL.put(Regions.US_EAST_1, "us-east-1:4514b170-a693-44fa-9288-4ae788f6757c");
        BETA_COGNITO_POOL.put(Regions.EU_WEST_1, "us-east-1:4514b170-a693-44fa-9288-4ae788f6757c");
        BETA_COGNITO_POOL.put(Regions.US_WEST_2, "us-east-1:4514b170-a693-44fa-9288-4ae788f6757c");
        GAMMA_COGNITO_POOL.put(Regions.US_EAST_1, "us-east-1:bf4fefbe-04ea-414a-87e0-a6eff7ebe6fc");
        GAMMA_COGNITO_POOL.put(Regions.EU_WEST_1, "eu-west-1:a3571904-3e99-47ae-a254-79cc4424e9c5");
        GAMMA_COGNITO_POOL.put(Regions.US_WEST_2, "us-west-2:89c7a0ca-997f-474d-9530-b9791e7087fa");
        PROD_COGNITO_POOL.put(Regions.US_EAST_1, BuildConfig.METRICS_MOBILYTICS_COGNITO_IDENTITY_POOL_ID_US_EAST_1);
        PROD_COGNITO_POOL.put(Regions.EU_WEST_1, BuildConfig.METRICS_MOBILYTICS_COGNITO_IDENTITY_POOL_ID_EU_WEST_1);
        PROD_COGNITO_POOL.put(Regions.US_WEST_2, BuildConfig.METRICS_MOBILYTICS_COGNITO_IDENTITY_POOL_ID_US_WEST_2);
    }

    private CognitoPoolManager() {
    }

    public static Map<Regions, String> cognitoPool(int i) {
        if (i == 2) {
            return Collections.unmodifiableMap(PROD_COGNITO_POOL);
        }
        return Collections.unmodifiableMap(GAMMA_COGNITO_POOL);
    }
}
