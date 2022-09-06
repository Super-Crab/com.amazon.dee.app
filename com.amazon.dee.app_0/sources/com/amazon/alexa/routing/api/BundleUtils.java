package com.amazon.alexa.routing.api;

import android.os.Bundle;
import androidx.annotation.Nullable;
import java.util.Objects;
/* loaded from: classes10.dex */
final class BundleUtils {
    private BundleUtils() {
    }

    public static boolean equals(@Nullable Bundle bundle, @Nullable Bundle bundle2) {
        if (bundle == null || bundle2 == null) {
            return bundle == bundle2;
        } else if (bundle == bundle2) {
            return true;
        } else {
            if (bundle.size() != bundle2.size()) {
                return false;
            }
            for (String str : bundle.keySet()) {
                Object obj = bundle.get(str);
                Object obj2 = bundle2.get(str);
                if (!(obj instanceof Bundle) || !(obj2 instanceof Bundle)) {
                    if (obj == null) {
                        if (obj2 != null && bundle2.containsKey(str)) {
                            return false;
                        }
                    } else if (!Objects.equals(obj, obj2)) {
                        return false;
                    }
                } else if (!equals((Bundle) obj, (Bundle) obj2)) {
                    return false;
                }
            }
            return true;
        }
    }
}
