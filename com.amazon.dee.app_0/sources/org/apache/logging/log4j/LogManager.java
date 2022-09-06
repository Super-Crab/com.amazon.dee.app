package org.apache.logging.log4j;

import java.net.URI;
import java.util.Map;
import java.util.TreeMap;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;
import org.apache.logging.log4j.simple.SimpleLoggerContextFactory;
import org.apache.logging.log4j.spi.LoggerContext;
import org.apache.logging.log4j.spi.LoggerContextFactory;
import org.apache.logging.log4j.spi.Provider;
import org.apache.logging.log4j.spi.Terminable;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.LoaderUtil;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.ProviderUtil;
import org.apache.logging.log4j.util.StackLocatorUtil;
/* loaded from: classes4.dex */
public class LogManager {
    public static final String FACTORY_PROPERTY_NAME = "log4j2.loggerContextFactory";
    public static final String ROOT_LOGGER_NAME = "";
    private static volatile LoggerContextFactory factory;
    private static final Logger LOGGER = StatusLogger.getLogger();
    private static final String FQCN = LogManager.class.getName();

    static {
        String stringProperty = PropertiesUtil.getProperties().getStringProperty(FACTORY_PROPERTY_NAME);
        if (stringProperty != null) {
            try {
                factory = (LoggerContextFactory) LoaderUtil.newCheckedInstanceOf(stringProperty, LoggerContextFactory.class);
            } catch (ClassNotFoundException unused) {
                LOGGER.error("Unable to locate configured LoggerContextFactory {}", stringProperty);
            } catch (Exception e) {
                LOGGER.error("Unable to create configured LoggerContextFactory {}", stringProperty, e);
            }
        }
        if (factory == null) {
            TreeMap treeMap = new TreeMap();
            if (ProviderUtil.hasProviders()) {
                for (Provider provider : ProviderUtil.getProviders()) {
                    Class<? extends LoggerContextFactory> loadLoggerContextFactory = provider.loadLoggerContextFactory();
                    if (loadLoggerContextFactory != null) {
                        try {
                            treeMap.put(provider.getPriority(), loadLoggerContextFactory.newInstance());
                        } catch (Exception e2) {
                            LOGGER.error("Unable to create class {} specified in provider URL {}", loadLoggerContextFactory.getName(), provider.getUrl(), e2);
                        }
                    }
                }
                if (treeMap.isEmpty()) {
                    LOGGER.error("Log4j2 could not find a logging implementation. Please add log4j-core to the classpath. Using SimpleLogger to log to the console...");
                    factory = new SimpleLoggerContextFactory();
                    return;
                } else if (treeMap.size() == 1) {
                    factory = (LoggerContextFactory) treeMap.get(treeMap.lastKey());
                    return;
                } else {
                    StringBuilder sb = new StringBuilder("Multiple logging implementations found: \n");
                    for (Map.Entry entry : treeMap.entrySet()) {
                        sb.append("Factory: ");
                        sb.append(((LoggerContextFactory) entry.getValue()).getClass().getName());
                        sb.append(", Weighting: ");
                        sb.append(entry.getKey());
                        sb.append('\n');
                    }
                    factory = (LoggerContextFactory) treeMap.get(treeMap.lastKey());
                    sb.append("Using factory: ");
                    sb.append(factory.getClass().getName());
                    LOGGER.warn(sb.toString());
                    return;
                }
            }
            LOGGER.error("Log4j2 could not find a logging implementation. Please add log4j-core to the classpath. Using SimpleLogger to log to the console...");
            factory = new SimpleLoggerContextFactory();
        }
    }

    protected LogManager() {
    }

    private static Class<?> callerClass(Class<?> cls) {
        if (cls != null) {
            return cls;
        }
        Class<?> callerClass = StackLocatorUtil.getCallerClass(3);
        if (callerClass == null) {
            throw new UnsupportedOperationException("No class provided, and an appropriate one cannot be found.");
        }
        return callerClass;
    }

    public static boolean exists(String str) {
        return getContext().hasLogger(str);
    }

    public static LoggerContext getContext() {
        try {
            return factory.getContext(FQCN, null, null, true);
        } catch (IllegalStateException e) {
            Logger logger = LOGGER;
            logger.warn(e.getMessage() + " Using SimpleLogger");
            return new SimpleLoggerContextFactory().getContext(FQCN, null, null, true);
        }
    }

    public static LoggerContextFactory getFactory() {
        return factory;
    }

    public static Logger getFormatterLogger() {
        return getFormatterLogger(StackLocatorUtil.getCallerClass(2));
    }

    public static Logger getLogger() {
        return getLogger(StackLocatorUtil.getCallerClass(2));
    }

    public static Logger getRootLogger() {
        return getLogger("");
    }

    public static void setFactory(LoggerContextFactory loggerContextFactory) {
        factory = loggerContextFactory;
    }

    public static void shutdown() {
        shutdown(false);
    }

    public static Logger getFormatterLogger(Class<?> cls) {
        if (cls == null) {
            cls = StackLocatorUtil.getCallerClass(2);
        }
        return getLogger(cls, (MessageFactory) StringFormatterMessageFactory.INSTANCE);
    }

    public static Logger getLogger(Class<?> cls) {
        Class<?> callerClass = callerClass(cls);
        return getContext(callerClass.getClassLoader(), false).getLogger(callerClass);
    }

    public static void shutdown(boolean z) {
        factory.shutdown(FQCN, null, z, false);
    }

    public static Logger getFormatterLogger(Object obj) {
        return getLogger(obj != null ? obj.getClass() : StackLocatorUtil.getCallerClass(2), (MessageFactory) StringFormatterMessageFactory.INSTANCE);
    }

    public static void shutdown(boolean z, boolean z2) {
        factory.shutdown(FQCN, null, z, z2);
    }

    public static LoggerContext getContext(boolean z) {
        try {
            return factory.getContext(FQCN, null, null, z, null, null);
        } catch (IllegalStateException e) {
            Logger logger = LOGGER;
            logger.warn(e.getMessage() + " Using SimpleLogger");
            return new SimpleLoggerContextFactory().getContext(FQCN, null, null, z, null, null);
        }
    }

    public static Logger getFormatterLogger(String str) {
        return str == null ? getFormatterLogger(StackLocatorUtil.getCallerClass(2)) : getLogger(str, (MessageFactory) StringFormatterMessageFactory.INSTANCE);
    }

    public static Logger getLogger(Class<?> cls, MessageFactory messageFactory) {
        Class<?> callerClass = callerClass(cls);
        return getContext(callerClass.getClassLoader(), false).getLogger(callerClass, messageFactory);
    }

    public static void shutdown(LoggerContext loggerContext) {
        if (loggerContext == null || !(loggerContext instanceof Terminable)) {
            return;
        }
        ((Terminable) loggerContext).terminate();
    }

    public static Logger getLogger(MessageFactory messageFactory) {
        return getLogger(StackLocatorUtil.getCallerClass(2), messageFactory);
    }

    public static LoggerContext getContext(ClassLoader classLoader, boolean z) {
        try {
            return factory.getContext(FQCN, classLoader, null, z);
        } catch (IllegalStateException e) {
            Logger logger = LOGGER;
            logger.warn(e.getMessage() + " Using SimpleLogger");
            return new SimpleLoggerContextFactory().getContext(FQCN, classLoader, null, z);
        }
    }

    public static Logger getLogger(Object obj) {
        return getLogger(obj != null ? obj.getClass() : StackLocatorUtil.getCallerClass(2));
    }

    public static Logger getLogger(Object obj, MessageFactory messageFactory) {
        return getLogger(obj != null ? obj.getClass() : StackLocatorUtil.getCallerClass(2), messageFactory);
    }

    public static Logger getLogger(String str) {
        return str != null ? getContext(false).getLogger(str) : getLogger(StackLocatorUtil.getCallerClass(2));
    }

    public static LoggerContext getContext(ClassLoader classLoader, boolean z, Object obj) {
        try {
            return factory.getContext(FQCN, classLoader, obj, z);
        } catch (IllegalStateException e) {
            Logger logger = LOGGER;
            logger.warn(e.getMessage() + " Using SimpleLogger");
            return new SimpleLoggerContextFactory().getContext(FQCN, classLoader, obj, z);
        }
    }

    public static Logger getLogger(String str, MessageFactory messageFactory) {
        if (str != null) {
            return getContext(false).getLogger(str, messageFactory);
        }
        return getLogger(StackLocatorUtil.getCallerClass(2), messageFactory);
    }

    public static LoggerContext getContext(ClassLoader classLoader, boolean z, URI uri) {
        try {
            return factory.getContext(FQCN, classLoader, null, z, uri, null);
        } catch (IllegalStateException e) {
            Logger logger = LOGGER;
            logger.warn(e.getMessage() + " Using SimpleLogger");
            return new SimpleLoggerContextFactory().getContext(FQCN, classLoader, null, z, uri, null);
        }
    }

    protected static Logger getLogger(String str, String str2) {
        return factory.getContext(str, null, null, false).getLogger(str2);
    }

    public static LoggerContext getContext(ClassLoader classLoader, boolean z, Object obj, URI uri) {
        try {
            return factory.getContext(FQCN, classLoader, obj, z, uri, null);
        } catch (IllegalStateException e) {
            Logger logger = LOGGER;
            logger.warn(e.getMessage() + " Using SimpleLogger");
            return new SimpleLoggerContextFactory().getContext(FQCN, classLoader, obj, z, uri, null);
        }
    }

    public static LoggerContext getContext(ClassLoader classLoader, boolean z, Object obj, URI uri, String str) {
        try {
            return factory.getContext(FQCN, classLoader, obj, z, uri, str);
        } catch (IllegalStateException e) {
            Logger logger = LOGGER;
            logger.warn(e.getMessage() + " Using SimpleLogger");
            return new SimpleLoggerContextFactory().getContext(FQCN, classLoader, obj, z, uri, str);
        }
    }

    protected static LoggerContext getContext(String str, boolean z) {
        try {
            return factory.getContext(str, null, null, z);
        } catch (IllegalStateException e) {
            Logger logger = LOGGER;
            logger.warn(e.getMessage() + " Using SimpleLogger");
            return new SimpleLoggerContextFactory().getContext(str, null, null, z);
        }
    }

    protected static LoggerContext getContext(String str, ClassLoader classLoader, boolean z) {
        try {
            return factory.getContext(str, classLoader, null, z);
        } catch (IllegalStateException e) {
            Logger logger = LOGGER;
            logger.warn(e.getMessage() + " Using SimpleLogger");
            return new SimpleLoggerContextFactory().getContext(str, classLoader, null, z);
        }
    }

    protected static LoggerContext getContext(String str, ClassLoader classLoader, boolean z, URI uri, String str2) {
        try {
            return factory.getContext(str, classLoader, null, z, uri, str2);
        } catch (IllegalStateException e) {
            Logger logger = LOGGER;
            logger.warn(e.getMessage() + " Using SimpleLogger");
            return new SimpleLoggerContextFactory().getContext(str, classLoader, null, z);
        }
    }
}
