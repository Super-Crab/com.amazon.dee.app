package org.apache.logging.log4j.spi;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.Properties;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.status.StatusLogger;
/* loaded from: classes4.dex */
public class Provider {
    public static final String FACTORY_PRIORITY = "FactoryPriority";
    public static final String LOGGER_CONTEXT_FACTORY = "LoggerContextFactory";
    public static final String THREAD_CONTEXT_MAP = "ThreadContextMap";
    private final WeakReference<ClassLoader> classLoader;
    private final String className;
    private final Class<? extends LoggerContextFactory> loggerContextFactoryClass;
    private final Integer priority;
    private final String threadContextMap;
    private final Class<? extends ThreadContextMap> threadContextMapClass;
    private final URL url;
    private final String versions;
    private static final Integer DEFAULT_PRIORITY = -1;
    private static final Logger LOGGER = StatusLogger.getLogger();

    public Provider(Properties properties, URL url, ClassLoader classLoader) {
        this.url = url;
        this.classLoader = new WeakReference<>(classLoader);
        String property = properties.getProperty(FACTORY_PRIORITY);
        this.priority = property == null ? DEFAULT_PRIORITY : Integer.valueOf(property);
        this.className = properties.getProperty(LOGGER_CONTEXT_FACTORY);
        this.threadContextMap = properties.getProperty(THREAD_CONTEXT_MAP);
        this.loggerContextFactoryClass = null;
        this.threadContextMapClass = null;
        this.versions = null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Provider.class != obj.getClass()) {
            return false;
        }
        Provider provider = (Provider) obj;
        Integer num = this.priority;
        if (num == null ? provider.priority != null : !num.equals(provider.priority)) {
            return false;
        }
        String str = this.className;
        if (str == null ? provider.className != null : !str.equals(provider.className)) {
            return false;
        }
        Class<? extends LoggerContextFactory> cls = this.loggerContextFactoryClass;
        if (cls == null ? provider.loggerContextFactoryClass != null : !cls.equals(provider.loggerContextFactoryClass)) {
            return false;
        }
        String str2 = this.versions;
        String str3 = provider.versions;
        return str2 != null ? str2.equals(str3) : str3 == null;
    }

    public String getClassName() {
        Class<? extends LoggerContextFactory> cls = this.loggerContextFactoryClass;
        if (cls != null) {
            return cls.getName();
        }
        return this.className;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public String getThreadContextMap() {
        Class<? extends ThreadContextMap> cls = this.threadContextMapClass;
        if (cls != null) {
            return cls.getName();
        }
        return this.threadContextMap;
    }

    public URL getUrl() {
        return this.url;
    }

    public String getVersions() {
        return this.versions;
    }

    public int hashCode() {
        Integer num = this.priority;
        int i = 0;
        int hashCode = (num != null ? num.hashCode() : 0) * 31;
        String str = this.className;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        Class<? extends LoggerContextFactory> cls = this.loggerContextFactoryClass;
        int hashCode3 = (hashCode2 + (cls != null ? cls.hashCode() : 0)) * 31;
        String str2 = this.versions;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode3 + i;
    }

    public Class<? extends LoggerContextFactory> loadLoggerContextFactory() {
        ClassLoader classLoader;
        Class<? extends LoggerContextFactory> cls = this.loggerContextFactoryClass;
        if (cls != null) {
            return cls;
        }
        if (this.className == null || (classLoader = this.classLoader.get()) == null) {
            return null;
        }
        try {
            Class<?> loadClass = classLoader.loadClass(this.className);
            if (LoggerContextFactory.class.isAssignableFrom(loadClass)) {
                return loadClass.asSubclass(LoggerContextFactory.class);
            }
        } catch (Exception e) {
            LOGGER.error("Unable to create class {} specified in {}", this.className, this.url.toString(), e);
        }
        return null;
    }

    public Class<? extends ThreadContextMap> loadThreadContextMap() {
        ClassLoader classLoader;
        Class<? extends ThreadContextMap> cls = this.threadContextMapClass;
        if (cls != null) {
            return cls;
        }
        if (this.threadContextMap == null || (classLoader = this.classLoader.get()) == null) {
            return null;
        }
        try {
            Class<?> loadClass = classLoader.loadClass(this.threadContextMap);
            if (ThreadContextMap.class.isAssignableFrom(loadClass)) {
                return loadClass.asSubclass(ThreadContextMap.class);
            }
        } catch (Exception e) {
            LOGGER.error("Unable to create class {} specified in {}", this.threadContextMap, this.url.toString(), e);
        }
        return null;
    }

    public String toString() {
        ClassLoader classLoader;
        StringBuilder sb = new StringBuilder("Provider[");
        if (!DEFAULT_PRIORITY.equals(this.priority)) {
            sb.append("priority=");
            sb.append(this.priority);
            sb.append(", ");
        }
        if (this.threadContextMap != null) {
            sb.append("threadContextMap=");
            sb.append(this.threadContextMap);
            sb.append(", ");
        } else if (this.threadContextMapClass != null) {
            sb.append("threadContextMapClass=");
            sb.append(this.threadContextMapClass.getName());
        }
        if (this.className != null) {
            sb.append("className=");
            sb.append(this.className);
            sb.append(", ");
        } else if (this.loggerContextFactoryClass != null) {
            sb.append("class=");
            sb.append(this.loggerContextFactoryClass.getName());
        }
        if (this.url != null) {
            sb.append("url=");
            sb.append(this.url);
        }
        WeakReference<ClassLoader> weakReference = this.classLoader;
        if (weakReference != null && (classLoader = weakReference.get()) != null) {
            sb.append(", classLoader=");
            sb.append(classLoader);
        } else {
            sb.append(", classLoader=null(not reachable)");
        }
        sb.append("]");
        return sb.toString();
    }

    public Provider(Integer num, String str, Class<? extends LoggerContextFactory> cls) {
        this(num, str, cls, null);
    }

    public Provider(Integer num, String str, Class<? extends LoggerContextFactory> cls, Class<? extends ThreadContextMap> cls2) {
        this.url = null;
        this.classLoader = null;
        this.priority = num;
        this.loggerContextFactoryClass = cls;
        this.threadContextMapClass = cls2;
        this.className = null;
        this.threadContextMap = null;
        this.versions = str;
    }
}
