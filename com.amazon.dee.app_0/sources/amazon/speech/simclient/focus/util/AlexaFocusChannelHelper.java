package amazon.speech.simclient.focus.util;

import com.amazon.deecomms.calling.incallcommands.constants.CommsFocusConstants;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public final class AlexaFocusChannelHelper {
    private static final Map<Integer, String> CHANNEL_MAP = new HashMap();

    static {
        CHANNEL_MAP.put(3, "dialog");
        CHANNEL_MAP.put(1, "alert");
        CHANNEL_MAP.put(2, "content");
        CHANNEL_MAP.put(4, CommsFocusConstants.COMMUNICATIONS);
        CHANNEL_MAP.put(51, "inFocus");
    }

    public static String getName(int i) {
        String str = CHANNEL_MAP.get(Integer.valueOf(i));
        if (str != null) {
            return str;
        }
        throw new IllegalArgumentException("Unexpected Channel Value");
    }

    public static boolean isValid(int i) {
        return CHANNEL_MAP.get(Integer.valueOf(i)) != null;
    }
}
