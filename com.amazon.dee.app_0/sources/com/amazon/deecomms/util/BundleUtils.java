package com.amazon.deecomms.util;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import java.io.Serializable;
/* loaded from: classes12.dex */
public final class BundleUtils {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, BundleUtils.class);

    private BundleUtils() {
    }

    public static void merge(@NonNull Bundle bundle, @NonNull Bundle bundle2, boolean z) {
        if (z) {
            bundle.putAll(bundle2);
            return;
        }
        for (String str : bundle2.keySet()) {
            if (!bundle.containsKey(str)) {
                Object obj = bundle2.get(str);
                if (obj instanceof String) {
                    bundle.putString(str, (String) obj);
                } else if (obj instanceof Boolean) {
                    bundle.putBoolean(str, ((Boolean) obj).booleanValue());
                } else if (obj instanceof Byte) {
                    bundle.putByte(str, ((Byte) obj).byteValue());
                } else if (obj instanceof Short) {
                    bundle.putShort(str, ((Short) obj).shortValue());
                } else if (obj instanceof Integer) {
                    bundle.putInt(str, ((Integer) obj).intValue());
                } else if (obj instanceof Long) {
                    bundle.putLong(str, ((Long) obj).longValue());
                } else if (obj instanceof Float) {
                    bundle.putFloat(str, ((Float) obj).floatValue());
                } else if (obj instanceof Double) {
                    bundle.putDouble(str, ((Double) obj).doubleValue());
                } else if (obj instanceof Serializable) {
                    bundle.putSerializable(str, (Serializable) obj);
                } else if (obj instanceof Parcelable) {
                    bundle.putParcelable(str, (Parcelable) obj);
                } else if (obj != null) {
                    LOG.w(String.format("Invalid value type: (%s) %s", obj.getClass().getSimpleName(), obj));
                }
            }
        }
    }
}
