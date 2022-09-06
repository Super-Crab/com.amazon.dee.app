package com.amazon.alexa.location.utils;

import com.amazon.alexa.location.LocationErrorCode;
import com.amazon.alexa.location.models.Code;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.google.common.collect.ImmutableMap;
/* loaded from: classes9.dex */
public final class Constants {
    public static final ImmutableMap<LocationErrorCode, Code> ERROR_CODE_MAP = new ImmutableMap.Builder().mo7828put(LocationErrorCode.ALS_TIMEOUT, Code.S202).mo7828put(LocationErrorCode.BEYOND_20_LIMIT, Code.A103).mo7828put(LocationErrorCode.GENERIC_ERROR, Code.A101).mo7828put(LocationErrorCode.PERMISSION_ERROR, Code.A102).mo7828put(LocationErrorCode.ALS_PAYLOAD_ERROR, Code.A101).mo7828put(LocationErrorCode.ALS_500, Code.S201).mo7826build();
    public static final ImmutableMap<Integer, String> GEOFENCE_TRANSITION_TO_METRICS_ID = new ImmutableMap.Builder().mo7828put(1, MetricsUtil.MetricsId.GEOFENCE_ENTER_TRIGGERED).mo7828put(2, MetricsUtil.MetricsId.GEOFENCE_EXIT_TRIGGERED).mo7826build();

    private Constants() {
    }
}
