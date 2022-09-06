package amazon.alexa.locale.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public final class LogUtil {
    static final int LOG_TAG_MAX_LENGTH = 23;
    static final String LOG_TAG_PREFIX = "ALL-";

    private LogUtil() {
    }

    public static String getTag(Class<?> cls) {
        String outline39 = GeneratedOutlineSupport1.outline39(cls, GeneratedOutlineSupport1.outline107(LOG_TAG_PREFIX));
        return outline39.length() <= 23 ? outline39 : outline39.substring(0, 23);
    }
}
