package com.amazon.comms.config.util;

import android.os.Build;
import com.amazon.comms.log.CommsLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.collect.ImmutableMap;
/* loaded from: classes11.dex */
public class VegaDeviceTypeProviderUtil {
    private static final String VEGA_2020_DEVICE_TYPE_ID_CAPELLA = "A3V88YODYKUDL4";
    private static final String VEGA_2020_DEVICE_TYPE_ID_POLARIS = "A1GPRTT66OBCF0";
    public static final String VEGA_LEGACY_DEVICE_TYPE_ID = "A2J0R2SD7G9LPA";
    private static final CommsLogger log = CommsLogger.getLogger(VegaDeviceTypeProviderUtil.class);
    private static final ImmutableMap<String, String> MODEL_TO_DEVICE_TYPE_MAP = ImmutableMap.builder().mo7828put("Lenovo TB-X306FA", "A1GPRTT66OBCF0").mo7828put("Lenovo TB-X306XA", "A1GPRTT66OBCF0").mo7828put("Lenovo TB-X306F", "A1GPRTT66OBCF0").mo7828put("Lenovo TB-X306X", "A1GPRTT66OBCF0").mo7828put("Lenovo TB-X606FA", "A3V88YODYKUDL4").mo7828put("Lenovo TB-X606XA", "A3V88YODYKUDL4").mo7828put("Lenovo TB-X606F", "A3V88YODYKUDL4").mo7828put("Lenovo TB-X606X", "A3V88YODYKUDL4").mo7826build();

    public static String getDeviceType() {
        String str;
        String str2 = Build.MODEL;
        GeneratedOutlineSupport1.outline159("Got Model name: ", str2, log);
        if (MODEL_TO_DEVICE_TYPE_MAP.containsKey(str2)) {
            str = MODEL_TO_DEVICE_TYPE_MAP.mo7740get(str2);
        } else {
            str = "A2J0R2SD7G9LPA";
            CommsLogger commsLogger = log;
            commsLogger.e("No model mapping, will use legacy device type: " + str);
        }
        GeneratedOutlineSupport1.outline159("Providing model type: ", str, log);
        return str;
    }
}
