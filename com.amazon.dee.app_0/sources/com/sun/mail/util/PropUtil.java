package com.sun.mail.util;

import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import java.util.Properties;
import javax.mail.Session;
/* loaded from: classes3.dex */
public class PropUtil {
    private PropUtil() {
    }

    private static boolean getBoolean(Object obj, boolean z) {
        if (obj == null) {
            return z;
        }
        if (!(obj instanceof String)) {
            return obj instanceof Boolean ? ((Boolean) obj).booleanValue() : z;
        } else if (z) {
            return !((String) obj).equalsIgnoreCase(PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE);
        } else {
            return ((String) obj).equalsIgnoreCase("true");
        }
    }

    public static boolean getBooleanProperty(Properties properties, String str, boolean z) {
        return getBoolean(getProp(properties, str), z);
    }

    public static boolean getBooleanSessionProperty(Session session, String str, boolean z) {
        return getBoolean(getProp(session.getProperties(), str), z);
    }

    public static boolean getBooleanSystemProperty(String str, boolean z) {
        try {
            try {
                return getBoolean(getProp(System.getProperties(), str), z);
            } catch (SecurityException unused) {
                return z;
            }
        } catch (SecurityException unused2) {
            String property = System.getProperty(str);
            if (property == null) {
                return z;
            }
            if (z) {
                return !property.equalsIgnoreCase(PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE);
            }
            return property.equalsIgnoreCase("true");
        }
    }

    private static int getInt(Object obj, int i) {
        if (obj == null) {
            return i;
        }
        if (obj instanceof String) {
            try {
                return Integer.parseInt((String) obj);
            } catch (NumberFormatException unused) {
            }
        }
        return obj instanceof Integer ? ((Integer) obj).intValue() : i;
    }

    public static int getIntProperty(Properties properties, String str, int i) {
        return getInt(getProp(properties, str), i);
    }

    public static int getIntSessionProperty(Session session, String str, int i) {
        return getInt(getProp(session.getProperties(), str), i);
    }

    private static Object getProp(Properties properties, String str) {
        Object obj = properties.get(str);
        return obj != null ? obj : properties.getProperty(str);
    }
}
