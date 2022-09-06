package com.google.android.gms.common.util;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.HashMap;
@KeepForSdk
/* loaded from: classes2.dex */
public class MapUtils {
    @KeepForSdk
    public static void writeStringMapToJson(StringBuilder sb, HashMap<String, String> hashMap) {
        sb.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        boolean z = true;
        for (String str : hashMap.keySet()) {
            if (!z) {
                sb.append(",");
            } else {
                z = false;
            }
            String str2 = hashMap.get(str);
            GeneratedOutlineSupport1.outline180(sb, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED, str, "\":");
            if (str2 == null) {
                sb.append("null");
            } else {
                GeneratedOutlineSupport1.outline180(sb, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED, str2, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            }
        }
        sb.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
