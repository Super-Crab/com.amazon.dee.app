package org.apache.logging.log4j.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.util.PropertySource;
/* loaded from: classes4.dex */
public final class PropertiesUtil {
    private static final String LOG4J_SYSTEM_PROPERTIES_FILE_NAME = "log4j2.system.properties";
    private static final String SYSTEM = "system:";
    private final Environment environment;
    private static final String LOG4J_PROPERTIES_FILE_NAME = "log4j2.component.properties";
    private static final PropertiesUtil LOG4J_PROPERTIES = new PropertiesUtil(LOG4J_PROPERTIES_FILE_NAME);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class Environment {
        private final Map<CharSequence, String> literal;
        private final Map<CharSequence, String> normalized;
        private final Set<PropertySource> sources;
        private final Map<List<CharSequence>, String> tokenized;

        /* JADX INFO: Access modifiers changed from: private */
        public boolean containsKey(String str) {
            return this.normalized.containsKey(str) || this.literal.containsKey(str) || hasSystemProperty(str) || this.tokenized.containsKey(PropertySource.Util.tokenize(str));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String get(String str) {
            if (this.normalized.containsKey(str)) {
                return this.normalized.get(str);
            }
            if (this.literal.containsKey(str)) {
                return this.literal.get(str);
            }
            if (hasSystemProperty(str)) {
                return System.getProperty(str);
            }
            for (PropertySource propertySource : this.sources) {
                if (propertySource.containsProperty(str)) {
                    return propertySource.getProperty(str);
                }
            }
            return this.tokenized.get(PropertySource.Util.tokenize(str));
        }

        private static boolean hasSystemProperty(String str) {
            try {
                return System.getProperties().containsKey(str);
            } catch (SecurityException unused) {
                return false;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized void reload() {
            this.literal.clear();
            this.normalized.clear();
            this.tokenized.clear();
            for (final PropertySource propertySource : this.sources) {
                propertySource.forEach(new BiConsumer<String, String>() { // from class: org.apache.logging.log4j.util.PropertiesUtil.Environment.2
                    @Override // org.apache.logging.log4j.util.BiConsumer
                    public void accept(String str, String str2) {
                        if (str == null || str2 == null) {
                            return;
                        }
                        Environment.this.literal.put(str, str2);
                        List<CharSequence> list = PropertySource.Util.tokenize(str);
                        if (list.isEmpty()) {
                            Environment.this.normalized.put(propertySource.getNormalForm(Collections.singleton(str)), str2);
                            return;
                        }
                        Environment.this.normalized.put(propertySource.getNormalForm(list), str2);
                        Environment.this.tokenized.put(list, str2);
                    }
                });
            }
        }

        private Environment(PropertySource propertySource) {
            this.sources = new TreeSet(new PropertySource.Comparator());
            this.literal = new ConcurrentHashMap();
            this.normalized = new ConcurrentHashMap();
            this.tokenized = new ConcurrentHashMap();
            try {
                new PropertyFilePropertySource(PropertiesUtil.LOG4J_SYSTEM_PROPERTIES_FILE_NAME).forEach(new BiConsumer<String, String>() { // from class: org.apache.logging.log4j.util.PropertiesUtil.Environment.1
                    @Override // org.apache.logging.log4j.util.BiConsumer
                    public void accept(String str, String str2) {
                        if (System.getProperty(str) == null) {
                            System.setProperty(str, str2);
                        }
                    }
                });
            } catch (SecurityException unused) {
            }
            this.sources.add(propertySource);
            for (ClassLoader classLoader : LoaderUtil.getClassLoaders()) {
                try {
                    Iterator it2 = ServiceLoader.load(PropertySource.class, classLoader).iterator();
                    while (it2.hasNext()) {
                        this.sources.add((PropertySource) it2.next());
                    }
                } catch (Throwable unused2) {
                }
            }
            reload();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public enum TimeUnit {
        NANOS("ns,nano,nanos,nanosecond,nanoseconds", ChronoUnit.NANOS),
        MICROS("us,micro,micros,microsecond,microseconds", ChronoUnit.MICROS),
        MILLIS("ms,milli,millis,millsecond,milliseconds", ChronoUnit.MILLIS),
        SECONDS("s,second,seconds", ChronoUnit.SECONDS),
        MINUTES("m,minute,minutes", ChronoUnit.MINUTES),
        HOURS("h,hour,hours", ChronoUnit.HOURS),
        DAYS("d,day,days", ChronoUnit.DAYS);
        
        private final String[] descriptions;
        private final ChronoUnit timeUnit;

        TimeUnit(String str, ChronoUnit chronoUnit) {
            this.descriptions = str.split(",");
            this.timeUnit = chronoUnit;
        }

        static Duration getDuration(String str) {
            String[] strArr;
            String trim = str.trim();
            ChronoUnit chronoUnit = ChronoUnit.MILLIS;
            TimeUnit[] values = values();
            int length = values.length;
            long j = 0;
            ChronoUnit chronoUnit2 = chronoUnit;
            int i = 0;
            while (i < length) {
                TimeUnit timeUnit = values[i];
                long j2 = j;
                for (String str2 : timeUnit.descriptions) {
                    if (trim.endsWith(str2)) {
                        chronoUnit2 = timeUnit.timeUnit;
                        j2 = Long.parseLong(trim.substring(0, trim.length() - str2.length()));
                    }
                }
                i++;
                j = j2;
            }
            return Duration.of(j, chronoUnit2);
        }

        ChronoUnit getTimeUnit() {
            return this.timeUnit;
        }
    }

    public PropertiesUtil(Properties properties) {
        this.environment = new Environment(new PropertiesPropertySource(properties));
    }

    public static Properties extractSubset(Properties properties, String str) {
        Properties properties2 = new Properties();
        if (str != null && str.length() != 0) {
            if (str.charAt(str.length() - 1) != '.') {
                str = GeneratedOutlineSupport1.outline47(str, '.');
            }
            ArrayList<String> arrayList = new ArrayList();
            for (String str2 : properties.stringPropertyNames()) {
                if (str2.startsWith(str)) {
                    properties2.setProperty(str2.substring(str.length()), properties.getProperty(str2));
                    arrayList.add(str2);
                }
            }
            for (String str3 : arrayList) {
                properties.remove(str3);
            }
        }
        return properties2;
    }

    static ResourceBundle getCharsetsResourceBundle() {
        return ResourceBundle.getBundle("Log4j-charsets");
    }

    public static PropertiesUtil getProperties() {
        return LOG4J_PROPERTIES;
    }

    public static Properties getSystemProperties() {
        try {
            return new Properties(System.getProperties());
        } catch (SecurityException e) {
            LowLevelLogUtil.logException("Unable to access system properties.", e);
            return new Properties();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Properties loadClose(InputStream inputStream, Object obj) {
        StringBuilder sb;
        Properties properties = new Properties();
        if (inputStream != null) {
            try {
                try {
                    properties.load(inputStream);
                } catch (IOException e) {
                    LowLevelLogUtil.logException("Unable to read " + obj, e);
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                        e = e2;
                        sb = new StringBuilder();
                        sb.append("Unable to close ");
                        sb.append(obj);
                        LowLevelLogUtil.logException(sb.toString(), e);
                        return properties;
                    }
                }
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e = e3;
                    sb = new StringBuilder();
                    sb.append("Unable to close ");
                    sb.append(obj);
                    LowLevelLogUtil.logException(sb.toString(), e);
                    return properties;
                }
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                    LowLevelLogUtil.logException(GeneratedOutlineSupport1.outline70("Unable to close ", obj), e4);
                }
                throw th;
            }
        }
        return properties;
    }

    public static Map<String, Properties> partitionOnCommonPrefixes(Properties properties) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        for (String str : properties.stringPropertyNames()) {
            String substring = str.substring(0, str.indexOf(46));
            if (!concurrentHashMap.containsKey(substring)) {
                concurrentHashMap.put(substring, new Properties());
            }
            ((Properties) concurrentHashMap.get(substring)).setProperty(str.substring(str.indexOf(46) + 1), properties.getProperty(str));
        }
        return concurrentHashMap;
    }

    public boolean getBooleanProperty(String str) {
        return getBooleanProperty(str, false);
    }

    public Charset getCharsetProperty(String str) {
        return getCharsetProperty(str, Charset.defaultCharset());
    }

    public double getDoubleProperty(String str, double d) {
        String stringProperty = getStringProperty(str);
        if (stringProperty != null) {
            try {
                return Double.parseDouble(stringProperty);
            } catch (Exception unused) {
            }
        }
        return d;
    }

    public Duration getDurationProperty(String str, Duration duration) {
        String stringProperty = getStringProperty(str);
        return stringProperty != null ? TimeUnit.getDuration(stringProperty) : duration;
    }

    public int getIntegerProperty(String str, int i) {
        String stringProperty = getStringProperty(str);
        if (stringProperty != null) {
            try {
                return Integer.parseInt(stringProperty);
            } catch (Exception unused) {
            }
        }
        return i;
    }

    public long getLongProperty(String str, long j) {
        String stringProperty = getStringProperty(str);
        if (stringProperty != null) {
            try {
                return Long.parseLong(stringProperty);
            } catch (Exception unused) {
            }
        }
        return j;
    }

    public String getStringProperty(String[] strArr, String str, Supplier<String> supplier) {
        for (String str2 : strArr) {
            String stringProperty = getStringProperty(str2 + str);
            if (stringProperty != null) {
                return stringProperty;
            }
        }
        if (supplier != null) {
            return supplier.get();
        }
        return null;
    }

    public boolean hasProperty(String str) {
        return this.environment.containsKey(str);
    }

    public boolean isOsWindows() {
        return getStringProperty("os.name", "").startsWith("Windows");
    }

    public void reload() {
        this.environment.reload();
    }

    public boolean getBooleanProperty(String str, boolean z) {
        String stringProperty = getStringProperty(str);
        return stringProperty == null ? z : "true".equalsIgnoreCase(stringProperty);
    }

    public Charset getCharsetProperty(String str, Charset charset) {
        String stringProperty = getStringProperty(str);
        if (stringProperty == null) {
            return charset;
        }
        if (Charset.isSupported(stringProperty)) {
            return Charset.forName(stringProperty);
        }
        ResourceBundle charsetsResourceBundle = getCharsetsResourceBundle();
        if (charsetsResourceBundle.containsKey(str)) {
            String string = charsetsResourceBundle.getString(str);
            if (Charset.isSupported(string)) {
                return Charset.forName(string);
            }
        }
        StringBuilder outline116 = GeneratedOutlineSupport1.outline116("Unable to get Charset '", stringProperty, "' for property '", str, "', using default ");
        outline116.append(charset);
        outline116.append(" and continuing.");
        LowLevelLogUtil.log(outline116.toString());
        return charset;
    }

    public PropertiesUtil(String str) {
        this.environment = new Environment(new PropertyFilePropertySource(str));
    }

    public Duration getDurationProperty(String[] strArr, String str, Supplier<Duration> supplier) {
        for (String str2 : strArr) {
            if (hasProperty(str2 + str)) {
                return getDurationProperty(str2 + str, null);
            }
        }
        if (supplier != null) {
            return supplier.get();
        }
        return null;
    }

    public Integer getIntegerProperty(String[] strArr, String str, Supplier<Integer> supplier) {
        for (String str2 : strArr) {
            if (hasProperty(str2 + str)) {
                return Integer.valueOf(getIntegerProperty(str2 + str, 0));
            }
        }
        if (supplier != null) {
            return supplier.get();
        }
        return null;
    }

    public Long getLongProperty(String[] strArr, String str, Supplier<Long> supplier) {
        for (String str2 : strArr) {
            if (hasProperty(str2 + str)) {
                return Long.valueOf(getLongProperty(str2 + str, 0L));
            }
        }
        if (supplier != null) {
            return supplier.get();
        }
        return null;
    }

    public boolean getBooleanProperty(String str, boolean z, boolean z2) {
        String stringProperty = getStringProperty(str);
        return stringProperty == null ? z : stringProperty.isEmpty() ? z2 : "true".equalsIgnoreCase(stringProperty);
    }

    public String getStringProperty(String str) {
        return this.environment.get(str);
    }

    public String getStringProperty(String str, String str2) {
        String stringProperty = getStringProperty(str);
        return stringProperty == null ? str2 : stringProperty;
    }

    public Boolean getBooleanProperty(String[] strArr, String str, Supplier<Boolean> supplier) {
        for (String str2 : strArr) {
            if (hasProperty(str2 + str)) {
                return Boolean.valueOf(getBooleanProperty(str2 + str));
            }
        }
        if (supplier != null) {
            return supplier.get();
        }
        return null;
    }
}
