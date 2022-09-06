package com.amazon.alexa.accessory.internal.util;

import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.repositories.device.v2.Device;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
/* loaded from: classes.dex */
public final class MetricsUtils {
    private static final char EXCEPTION_SEPARATOR = ':';
    private static final int MAX_METRIC_NAME_LENGTH = 256;

    /* loaded from: classes.dex */
    public enum AccessoryStreamIdentifier {
        CONTROL("control"),
        VOICE("voice"),
        FIRMWARE("firmware"),
        DIAGNOSTICS("diagnostics"),
        FITNESS("fitness"),
        BULKDATA("bulkdata"),
        ALEXAAPI("alexaapi"),
        SIDEWALK("sidewalk"),
        UNKNOWN("unknown");
        
        private static final AccessoryStreamIdentifier[] VALUES = values();
        private final String metricName;

        AccessoryStreamIdentifier(String str) {
            this.metricName = str;
        }

        public static String fromValue(int i) {
            if (i >= 0) {
                AccessoryStreamIdentifier[] accessoryStreamIdentifierArr = VALUES;
                if (i < accessoryStreamIdentifierArr.length - 1) {
                    return accessoryStreamIdentifierArr[i].metricName;
                }
            }
            return UNKNOWN.metricName;
        }
    }

    private MetricsUtils() {
    }

    public static String createMetricNameFromThrowable(String str, Throwable th) {
        Preconditions.notNull(str, "prefix");
        Preconditions.notNull(th, "throwable");
        StringBuilder sb = new StringBuilder(256);
        sb.append(str);
        while (th != null) {
            sb.append(':');
            sb.append(th.getClass().getSimpleName());
            th = th.getCause();
        }
        return sb.substring(0, Math.min(sb.length(), 256));
    }

    @Nullable
    public static String getDeviceTypeOfHighestId(DeviceGroup deviceGroup) {
        Device deviceWithHighestId = deviceGroup.getDeviceWithHighestId();
        return deviceWithHighestId != null ? deviceWithHighestId.getType() : "unknown";
    }
}
