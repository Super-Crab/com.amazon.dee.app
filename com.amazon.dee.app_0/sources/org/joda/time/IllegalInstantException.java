package org.joda.time;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.joda.time.format.DateTimeFormat;
/* loaded from: classes5.dex */
public class IllegalInstantException extends IllegalArgumentException {
    private static final long serialVersionUID = 2858712538216L;

    public IllegalInstantException(long j, String str) {
        super(createMessage(j, str));
    }

    public IllegalInstantException(String str) {
        super(str);
    }

    private static String createMessage(long j, String str) {
        return GeneratedOutlineSupport1.outline75("Illegal instant due to time zone offset transition (daylight savings time 'gap'): ", DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS").print(new Instant(j)), str != null ? GeneratedOutlineSupport1.outline75(" (", str, ")") : "");
    }

    public static boolean isIllegalInstant(Throwable th) {
        if (th instanceof IllegalInstantException) {
            return true;
        }
        if (th.getCause() != null && th.getCause() != th) {
            return isIllegalInstant(th.getCause());
        }
        return false;
    }
}
