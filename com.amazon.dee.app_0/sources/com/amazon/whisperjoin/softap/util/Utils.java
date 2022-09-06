package com.amazon.whisperjoin.softap.util;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class Utils {
    public static String getStringWithoutWrappingQuotesIfPresent(String str) {
        if (str == null) {
            return null;
        }
        return isStringWrappedInQuotes(str) ? GeneratedOutlineSupport1.outline51(str, 1, 1) : str;
    }

    public static boolean isStringWrappedInQuotes(String str) {
        return str.startsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED) && str.endsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
    }
}
