package com.amazonaws.util;

import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes.dex */
public class VersionInfoUtils {
    private static final int DEFAULT_STRING_LENGTH = 128;
    private static final Log log = LogFactory.getLog(VersionInfoUtils.class);
    private static volatile String platform = "android";
    private static volatile String userAgent = null;
    private static volatile String version = "2.12.0";

    public static String getPlatform() {
        return platform;
    }

    public static String getUserAgent() {
        if (userAgent == null) {
            synchronized (VersionInfoUtils.class) {
                if (userAgent == null) {
                    initializeUserAgent();
                }
            }
        }
        return userAgent;
    }

    public static String getVersion() {
        return version;
    }

    private static void initializeUserAgent() {
        userAgent = userAgent();
    }

    private static String replaceSpaces(String str) {
        return str.replace(Chars.SPACE, '_');
    }

    static String userAgent() {
        StringBuilder outline105 = GeneratedOutlineSupport1.outline105(128, "aws-sdk-");
        outline105.append(StringUtils.lowerCase(getPlatform()));
        outline105.append("/");
        outline105.append(getVersion());
        outline105.append(" ");
        outline105.append(replaceSpaces(System.getProperty("os.name")));
        outline105.append("/");
        outline105.append(replaceSpaces(System.getProperty("os.version")));
        outline105.append(" ");
        outline105.append(replaceSpaces(System.getProperty("java.vm.name")));
        outline105.append("/");
        outline105.append(replaceSpaces(System.getProperty("java.vm.version")));
        outline105.append("/");
        outline105.append(replaceSpaces(System.getProperty("java.version")));
        String property = System.getProperty("user.language");
        String property2 = System.getProperty("user.region");
        if (property != null && property2 != null) {
            outline105.append(" ");
            outline105.append(replaceSpaces(property));
            outline105.append("_");
            outline105.append(replaceSpaces(property2));
        }
        return outline105.toString();
    }
}
