package com.amazon.alexa.accessoryclient.common.transformers;

import android.os.Bundle;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes6.dex */
public class BundleUtils {
    public static void assertEqualsBundle(Bundle bundle, Bundle bundle2) {
        if (equalsBundle(bundle, bundle2)) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Bundle a: ");
        outline107.append(bundleToString(bundle));
        outline107.append(" is not equal to bundle b: ");
        outline107.append(bundleToString(bundle2));
        throw new IllegalStateException(outline107.toString());
    }

    public static String bundleToString(Bundle bundle) {
        return bundle == null ? "null!" : bundleToStringIndent(bundle, "");
    }

    private static String bundleToStringIndent(Bundle bundle, String str) {
        StringBuilder sb = new StringBuilder(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (!bundle.keySet().isEmpty()) {
            GeneratedOutlineSupport1.outline181(sb, "\n", str, str, "keys: ");
            sb.append(Arrays.toString(bundle.keySet().toArray()));
            sb.append("\n");
            sb.append(str);
            sb.append(str);
            sb.append("values: ");
            for (String str2 : bundle.keySet()) {
                Object obj = bundle.get(str2);
                if (obj instanceof Bundle) {
                    GeneratedOutlineSupport1.outline181(sb, "\n", str, str, "[");
                    sb.append(bundleToStringIndent((Bundle) obj, GeneratedOutlineSupport1.outline72(str, "  ")));
                    sb.append("] ");
                } else {
                    GeneratedOutlineSupport1.outline181(sb, "\n", str, str, "[");
                    sb.append(obj);
                    sb.append("] ");
                }
            }
            sb.append("\n");
            sb.append(str);
        }
        sb.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return sb.toString();
    }

    public static boolean equalsBundle(Bundle bundle, Bundle bundle2) {
        if (!bundle.keySet().equals(bundle2.keySet())) {
            return false;
        }
        loop0: while (true) {
            boolean z = true;
            for (String str : bundle.keySet()) {
                Object obj = bundle2.get(str);
                Object obj2 = bundle.get(str);
                if (obj2 != null || obj != null) {
                    if (obj2 == null || obj == null || obj2.getClass() != obj.getClass()) {
                        return false;
                    }
                    if (obj2 instanceof Bundle) {
                        if (z && equalsBundle((Bundle) obj2, (Bundle) obj)) {
                            break;
                        }
                        z = false;
                    } else {
                        if (z && Objects.equals(obj2, obj)) {
                            break;
                        }
                        z = false;
                    }
                }
            }
            return z;
        }
        return false;
    }
}
