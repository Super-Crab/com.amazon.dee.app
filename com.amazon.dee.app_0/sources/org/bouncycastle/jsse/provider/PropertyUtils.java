package org.bouncycastle.jsse.provider;

import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Security;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes4.dex */
class PropertyUtils {
    private static final Logger LOG = Logger.getLogger(PropertyUtils.class.getName());

    PropertyUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean getBooleanSecurityProperty(String str, boolean z) {
        Logger logger;
        Level level;
        StringBuilder outline115;
        boolean z2;
        String securityProperty = getSecurityProperty(str);
        if (securityProperty != null) {
            if ("true".equalsIgnoreCase(securityProperty)) {
                logger = LOG;
                level = Level.INFO;
                outline115 = GeneratedOutlineSupport1.outline115("Found boolean security property [", str, "]: ");
                z2 = true;
            } else if (PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE.equalsIgnoreCase(securityProperty)) {
                logger = LOG;
                level = Level.INFO;
                outline115 = GeneratedOutlineSupport1.outline115("Found boolean security property [", str, "]: ");
                z2 = false;
            } else {
                LOG.log(Level.WARNING, GeneratedOutlineSupport1.outline76("Unrecognized value for boolean security property [", str, "]: ", securityProperty));
            }
            outline115.append(z2);
            logger.log(level, outline115.toString());
            return z2;
        }
        Logger logger2 = LOG;
        Level level2 = Level.FINE;
        logger2.log(level2, "Boolean security property [" + str + "] defaulted to: " + z);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean getBooleanSystemProperty(String str, boolean z) {
        Logger logger;
        Level level;
        StringBuilder outline115;
        boolean z2;
        String systemProperty = getSystemProperty(str);
        if (systemProperty != null) {
            if ("true".equalsIgnoreCase(systemProperty)) {
                logger = LOG;
                level = Level.INFO;
                outline115 = GeneratedOutlineSupport1.outline115("Found boolean system property [", str, "]: ");
                z2 = true;
            } else if (PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE.equalsIgnoreCase(systemProperty)) {
                logger = LOG;
                level = Level.INFO;
                outline115 = GeneratedOutlineSupport1.outline115("Found boolean system property [", str, "]: ");
                z2 = false;
            } else {
                LOG.log(Level.WARNING, GeneratedOutlineSupport1.outline76("Unrecognized value for boolean system property [", str, "]: ", systemProperty));
            }
            outline115.append(z2);
            logger.log(level, outline115.toString());
            return z2;
        }
        Logger logger2 = LOG;
        Level level2 = Level.FINE;
        logger2.log(level2, "Boolean system property [" + str + "] defaulted to: " + z);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getIntegerSystemProperty(String str, int i, int i2, int i3) {
        String systemProperty = getSystemProperty(str);
        if (systemProperty != null) {
            try {
                int parseInt = Integer.parseInt(systemProperty);
                if (parseInt >= i2 && parseInt <= i3) {
                    Logger logger = LOG;
                    Level level = Level.INFO;
                    logger.log(level, "Found integer system property [" + str + "]: " + parseInt);
                    return parseInt;
                } else if (LOG.isLoggable(Level.WARNING)) {
                    String rangeString = getRangeString(i2, i3);
                    Logger logger2 = LOG;
                    Level level2 = Level.WARNING;
                    logger2.log(level2, "Out-of-range (" + rangeString + ") integer system property [" + str + "]: " + systemProperty);
                }
            } catch (Exception unused) {
                LOG.log(Level.WARNING, GeneratedOutlineSupport1.outline76("Unrecognized value for integer system property [", str, "]: ", systemProperty));
            }
        }
        Logger logger3 = LOG;
        Level level3 = Level.FINE;
        logger3.log(level3, "Integer system property [" + str + "] defaulted to: " + i);
        return i;
    }

    private static String getRangeString(int i, int i2) {
        StringBuilder sb = new StringBuilder(32);
        if (Integer.MIN_VALUE != i) {
            sb.append(i);
            sb.append(" <= ");
        }
        sb.append('x');
        if (Integer.MAX_VALUE != i2) {
            sb.append(" <= ");
            sb.append(i2);
        }
        return sb.toString();
    }

    static String getSecurityProperty(final String str) {
        return (String) AccessController.doPrivileged(new PrivilegedAction<String>() { // from class: org.bouncycastle.jsse.provider.PropertyUtils.1
            @Override // java.security.PrivilegedAction
            public String run() {
                return Security.getProperty(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] getStringArraySecurityProperty(String str, String str2) {
        return parseStringArray(getStringSecurityProperty(str, str2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] getStringArraySystemProperty(String str) {
        return parseStringArray(getStringSystemProperty(str));
    }

    static String getStringSecurityProperty(String str, String str2) {
        String securityProperty = getSecurityProperty(str);
        if (securityProperty != null) {
            LOG.log(Level.INFO, GeneratedOutlineSupport1.outline76("Found string security property [", str, "]: ", securityProperty));
            return securityProperty;
        }
        LOG.log(Level.WARNING, GeneratedOutlineSupport1.outline76("String security property [", str, "] defaulted to: ", str2));
        return str2;
    }

    static String getStringSystemProperty(String str) {
        String systemProperty = getSystemProperty(str);
        if (systemProperty != null) {
            LOG.log(Level.INFO, GeneratedOutlineSupport1.outline76("Found string system property [", str, "]: ", systemProperty));
            return systemProperty;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getSystemProperty(final String str) {
        try {
            return (String) AccessController.doPrivileged(new PrivilegedAction<String>() { // from class: org.bouncycastle.jsse.provider.PropertyUtils.2
                @Override // java.security.PrivilegedAction
                public String run() {
                    return System.getProperty(str);
                }
            });
        } catch (RuntimeException e) {
            LOG.log(Level.WARNING, "Failed to get system property", (Throwable) e);
            return null;
        }
    }

    private static String[] parseStringArray(String str) {
        if (str == null) {
            return null;
        }
        String[] split = JsseUtils.stripDoubleQuotes(str.trim()).split(",");
        String[] strArr = new String[split.length];
        int i = 0;
        for (String str2 : split) {
            String trim = str2.trim();
            if (trim.length() >= 1) {
                strArr[i] = trim;
                i++;
            }
        }
        return JsseUtils.resize(strArr, i);
    }
}
