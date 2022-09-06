package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.alexa.mobilytics.configuration.Config;
import java.util.HashMap;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class hw {
    public static Bundle K(Bundle bundle) {
        return bundle == null ? new Bundle() : bundle;
    }

    public static Map<String, String> L(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj instanceof String) {
                hashMap.put(str, (String) obj);
            }
        }
        return hashMap;
    }

    public static String M(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder("[");
        for (String str : bundle.keySet()) {
            sb.append(str);
            sb.append(Config.Compare.EQUAL_TO);
            sb.append(bundle.get(str));
            sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void N(Bundle bundle) {
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj instanceof Enum) {
                bundle.putString(str, obj.toString());
            }
        }
    }
}
