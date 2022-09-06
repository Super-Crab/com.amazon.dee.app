package com.fasterxml.jackson.core.util;

import com.amazon.deecomms.remoteConfig.ArcusConfig;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Pattern;
/* loaded from: classes2.dex */
public class VersionUtil {
    private static final Pattern V_SEP = Pattern.compile("[-_./;:]");

    protected VersionUtil() {
    }

    private static final void _close(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException unused) {
        }
    }

    @Deprecated
    public static Version mavenVersionFor(ClassLoader classLoader, String str, String str2) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("META-INF/maven/");
        outline107.append(str.replaceAll(ArcusConfig.PATH_SEPARATOR, "/"));
        outline107.append("/");
        outline107.append(str2);
        outline107.append("/pom.properties");
        InputStream resourceAsStream = classLoader.getResourceAsStream(outline107.toString());
        if (resourceAsStream != null) {
            try {
                Properties properties = new Properties();
                properties.load(resourceAsStream);
                return parseVersion(properties.getProperty("version"), properties.getProperty("groupId"), properties.getProperty("artifactId"));
            } catch (IOException unused) {
            } finally {
                _close(resourceAsStream);
            }
        }
        return Version.unknownVersion();
    }

    @Deprecated
    public static Version packageVersionFor(Class<?> cls) {
        return versionFor(cls);
    }

    public static Version parseVersion(String str, String str2, String str3) {
        if (str != null) {
            String trim = str.trim();
            if (trim.length() > 0) {
                String[] split = V_SEP.split(trim);
                int i = 0;
                int parseVersionPart = parseVersionPart(split[0]);
                int parseVersionPart2 = split.length > 1 ? parseVersionPart(split[1]) : 0;
                if (split.length > 2) {
                    i = parseVersionPart(split[2]);
                }
                return new Version(parseVersionPart, parseVersionPart2, i, split.length > 3 ? split[3] : null, str2, str3);
            }
        }
        return Version.unknownVersion();
    }

    protected static int parseVersionPart(String str) {
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt > '9' || charAt < '0') {
                break;
            }
            i = (i * 10) + (charAt - '0');
        }
        return i;
    }

    public static final void throwInternal() {
        throw new RuntimeException("Internal error: this code path should never get executed");
    }

    public static Version versionFor(Class<?> cls) {
        Version version;
        Object obj;
        try {
            try {
                version = ((Versioned) Class.forName(cls.getPackage().getName() + ".PackageVersion", true, cls.getClassLoader()).getDeclaredConstructor(new Class[0]).newInstance(new Object[0])).version();
            } catch (Exception unused) {
                throw new IllegalArgumentException("Failed to get Versioned out of " + obj);
            }
        } catch (Exception unused2) {
            version = null;
        }
        return version == null ? Version.unknownVersion() : version;
    }

    @Deprecated
    public Version version() {
        return Version.unknownVersion();
    }
}
