package com.amazonaws.org.apache.commons.logging.impl;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazonaws.org.apache.commons.logging.Log;
import com.amazonaws.org.apache.commons.logging.LogConfigurationException;
import com.amazonaws.org.apache.commons.logging.LogFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
/* loaded from: classes13.dex */
public class LogFactoryImpl extends LogFactory {
    public static final String ALLOW_FLAWED_CONTEXT_PROPERTY = "com.amazonaws.org.apache.commons.logging.Log.allowFlawedContext";
    public static final String ALLOW_FLAWED_DISCOVERY_PROPERTY = "com.amazonaws.org.apache.commons.logging.Log.allowFlawedDiscovery";
    public static final String ALLOW_FLAWED_HIERARCHY_PROPERTY = "com.amazonaws.org.apache.commons.logging.Log.allowFlawedHierarchy";
    public static final String LOG_PROPERTY = "com.amazonaws.org.apache.commons.logging.Log";
    protected static final String LOG_PROPERTY_OLD = "com.amazonaws.org.apache.commons.logging.log";
    private static final String PKG_IMPL = "com.amazonaws.org.apache.commons.logging.impl.";
    private static final int PKG_LEN = 46;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$org$apache$commons$logging$Log;
    static /* synthetic */ Class class$org$apache$commons$logging$LogFactory;
    static /* synthetic */ Class class$org$apache$commons$logging$impl$LogFactoryImpl;
    private boolean allowFlawedContext;
    private boolean allowFlawedDiscovery;
    private boolean allowFlawedHierarchy;
    private String diagnosticPrefix;
    private String logClassName;
    protected Class[] logConstructorSignature;
    protected Method logMethod;
    protected Class[] logMethodSignature;
    private static final String LOGGING_IMPL_LOG4J_LOGGER = "com.amazonaws.org.apache.commons.logging.impl.Log4JLogger";
    private static final String LOGGING_IMPL_JDK14_LOGGER = "com.amazonaws.org.apache.commons.logging.impl.Jdk14Logger";
    private static final String LOGGING_IMPL_LUMBERJACK_LOGGER = "com.amazonaws.org.apache.commons.logging.impl.Jdk13LumberjackLogger";
    private static final String LOGGING_IMPL_SIMPLE_LOGGER = "com.amazonaws.org.apache.commons.logging.impl.SimpleLog";
    private static final String[] classesToDiscover = {LOGGING_IMPL_LOG4J_LOGGER, LOGGING_IMPL_JDK14_LOGGER, LOGGING_IMPL_LUMBERJACK_LOGGER, LOGGING_IMPL_SIMPLE_LOGGER};
    private boolean useTCCL = true;
    protected Hashtable attributes = new Hashtable();
    protected Hashtable instances = new Hashtable();
    protected Constructor logConstructor = null;

    public LogFactoryImpl() {
        Class[] clsArr = new Class[1];
        Class cls = class$java$lang$String;
        if (cls == null) {
            cls = class$("java.lang.String");
            class$java$lang$String = cls;
        }
        clsArr[0] = cls;
        this.logConstructorSignature = clsArr;
        this.logMethod = null;
        Class[] clsArr2 = new Class[1];
        Class cls2 = class$org$apache$commons$logging$LogFactory;
        if (cls2 == null) {
            cls2 = class$(LogFactory.FACTORY_PROPERTY);
            class$org$apache$commons$logging$LogFactory = cls2;
        }
        clsArr2[0] = cls2;
        this.logMethodSignature = clsArr2;
        initDiagnostics();
        if (isDiagnosticsEnabled()) {
            logDiagnostic("Instance created.");
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0176 A[LOOP:0: B:6:0x003a->B:42:0x0176, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01f3 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01ef A[EDGE_INSN: B:69:0x01ef->B:49:0x01ef ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.amazonaws.org.apache.commons.logging.Log createLogFromClass(java.lang.String r17, java.lang.String r18, boolean r19) throws com.amazonaws.org.apache.commons.logging.LogConfigurationException {
        /*
            Method dump skipped, instructions count: 622
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazonaws.org.apache.commons.logging.impl.LogFactoryImpl.createLogFromClass(java.lang.String, java.lang.String, boolean):com.amazonaws.org.apache.commons.logging.Log");
    }

    private Log discoverLogImplementation(String str) throws LogConfigurationException {
        if (isDiagnosticsEnabled()) {
            logDiagnostic("Discovering a Log implementation...");
        }
        initConfiguration();
        Log log = null;
        String findUserSpecifiedLogClassName = findUserSpecifiedLogClassName();
        if (findUserSpecifiedLogClassName != null) {
            if (isDiagnosticsEnabled()) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Attempting to load user-specified log class '");
                stringBuffer.append(findUserSpecifiedLogClassName);
                stringBuffer.append("'...");
                logDiagnostic(stringBuffer.toString());
            }
            Log createLogFromClass = createLogFromClass(findUserSpecifiedLogClassName, str, true);
            if (createLogFromClass != null) {
                return createLogFromClass;
            }
            StringBuffer stringBuffer2 = new StringBuffer("User-specified log class '");
            stringBuffer2.append(findUserSpecifiedLogClassName);
            stringBuffer2.append("' cannot be found or is not useable.");
            informUponSimilarName(stringBuffer2, findUserSpecifiedLogClassName, LOGGING_IMPL_LOG4J_LOGGER);
            informUponSimilarName(stringBuffer2, findUserSpecifiedLogClassName, LOGGING_IMPL_JDK14_LOGGER);
            informUponSimilarName(stringBuffer2, findUserSpecifiedLogClassName, LOGGING_IMPL_LUMBERJACK_LOGGER);
            informUponSimilarName(stringBuffer2, findUserSpecifiedLogClassName, LOGGING_IMPL_SIMPLE_LOGGER);
            throw new LogConfigurationException(stringBuffer2.toString());
        }
        if (isDiagnosticsEnabled()) {
            logDiagnostic("No user-specified Log implementation; performing discovery using the standard supported logging implementations...");
        }
        int i = 0;
        while (true) {
            String[] strArr = classesToDiscover;
            if (i >= strArr.length || log != null) {
                break;
            }
            log = createLogFromClass(strArr[i], str, true);
            i++;
        }
        if (log == null) {
            throw new LogConfigurationException("No suitable Log implementation");
        }
        return log;
    }

    private String findUserSpecifiedLogClassName() {
        if (isDiagnosticsEnabled()) {
            logDiagnostic("Trying to get log class from attribute 'org.apache.commons.logging.Log'");
        }
        String str = (String) getAttribute(LOG_PROPERTY);
        if (str == null) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("Trying to get log class from attribute 'org.apache.commons.logging.log'");
            }
            str = (String) getAttribute(LOG_PROPERTY_OLD);
        }
        if (str == null) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("Trying to get log class from system property 'org.apache.commons.logging.Log'");
            }
            try {
                str = getSystemProperty(LOG_PROPERTY, null);
            } catch (SecurityException e) {
                if (isDiagnosticsEnabled()) {
                    StringBuffer outline103 = GeneratedOutlineSupport1.outline103("No access allowed to system property 'org.apache.commons.logging.Log' - ");
                    outline103.append(e.getMessage());
                    logDiagnostic(outline103.toString());
                }
            }
        }
        if (str == null) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("Trying to get log class from system property 'org.apache.commons.logging.log'");
            }
            try {
                str = getSystemProperty(LOG_PROPERTY_OLD, null);
            } catch (SecurityException e2) {
                if (isDiagnosticsEnabled()) {
                    StringBuffer outline1032 = GeneratedOutlineSupport1.outline103("No access allowed to system property 'org.apache.commons.logging.log' - ");
                    outline1032.append(e2.getMessage());
                    logDiagnostic(outline1032.toString());
                }
            }
        }
        return str != null ? str.trim() : str;
    }

    private ClassLoader getBaseClassLoader() throws LogConfigurationException {
        Class cls = class$org$apache$commons$logging$impl$LogFactoryImpl;
        if (cls == null) {
            cls = class$(LogFactory.FACTORY_DEFAULT);
            class$org$apache$commons$logging$impl$LogFactoryImpl = cls;
        }
        ClassLoader classLoader = getClassLoader(cls);
        if (!this.useTCCL) {
            return classLoader;
        }
        ClassLoader contextClassLoaderInternal = getContextClassLoaderInternal();
        ClassLoader lowestClassLoader = getLowestClassLoader(contextClassLoaderInternal, classLoader);
        if (lowestClassLoader == null) {
            if (this.allowFlawedContext) {
                if (isDiagnosticsEnabled()) {
                    logDiagnostic("[WARNING] the context classloader is not part of a parent-child relationship with the classloader that loaded LogFactoryImpl.");
                }
                return contextClassLoaderInternal;
            }
            throw new LogConfigurationException("Bad classloader hierarchy; LogFactoryImpl was loaded via a classloader that is not related to the current context classloader.");
        }
        if (lowestClassLoader != contextClassLoaderInternal) {
            if (this.allowFlawedContext) {
                if (isDiagnosticsEnabled()) {
                    logDiagnostic("Warning: the context classloader is an ancestor of the classloader that loaded LogFactoryImpl; it should be the same or a descendant. The application using commons-logging should ensure the context classloader is used correctly.");
                }
            } else {
                throw new LogConfigurationException("Bad classloader hierarchy; LogFactoryImpl was loaded via a classloader that is not related to the current context classloader.");
            }
        }
        return lowestClassLoader;
    }

    private boolean getBooleanConfiguration(String str, boolean z) {
        String configurationValue = getConfigurationValue(str);
        return configurationValue == null ? z : Boolean.valueOf(configurationValue).booleanValue();
    }

    protected static ClassLoader getClassLoader(Class cls) {
        return LogFactory.getClassLoader(cls);
    }

    private String getConfigurationValue(String str) {
        String systemProperty;
        if (isDiagnosticsEnabled()) {
            logDiagnostic(GeneratedOutlineSupport1.outline71("[ENV] Trying to get configuration for item ", str));
        }
        Object attribute = getAttribute(str);
        if (attribute != null) {
            if (isDiagnosticsEnabled()) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("[ENV] Found LogFactory attribute [");
                stringBuffer.append(attribute);
                stringBuffer.append("] for ");
                stringBuffer.append(str);
                logDiagnostic(stringBuffer.toString());
            }
            return attribute.toString();
        }
        if (isDiagnosticsEnabled()) {
            logDiagnostic(GeneratedOutlineSupport1.outline71("[ENV] No LogFactory attribute found for ", str));
        }
        try {
            systemProperty = getSystemProperty(str, null);
        } catch (SecurityException unused) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic(GeneratedOutlineSupport1.outline71("[ENV] Security prevented reading system property ", str));
            }
        }
        if (systemProperty != null) {
            if (isDiagnosticsEnabled()) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("[ENV] Found system property [");
                stringBuffer2.append(systemProperty);
                stringBuffer2.append("] for ");
                stringBuffer2.append(str);
                logDiagnostic(stringBuffer2.toString());
            }
            return systemProperty;
        }
        if (isDiagnosticsEnabled()) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("[ENV] No system property found for property ");
            stringBuffer3.append(str);
            logDiagnostic(stringBuffer3.toString());
        }
        if (isDiagnosticsEnabled()) {
            logDiagnostic(GeneratedOutlineSupport1.outline71("[ENV] No configuration defined for item ", str));
        }
        return null;
    }

    protected static ClassLoader getContextClassLoader() throws LogConfigurationException {
        return LogFactory.getContextClassLoader();
    }

    private static ClassLoader getContextClassLoaderInternal() throws LogConfigurationException {
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.amazonaws.org.apache.commons.logging.impl.LogFactoryImpl.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                return LogFactory.directGetContextClassLoader();
            }
        });
    }

    private ClassLoader getLowestClassLoader(ClassLoader classLoader, ClassLoader classLoader2) {
        if (classLoader == null) {
            return classLoader2;
        }
        if (classLoader2 == null) {
            return classLoader;
        }
        for (ClassLoader classLoader3 = classLoader; classLoader3 != null; classLoader3 = classLoader3.getParent()) {
            if (classLoader3 == classLoader2) {
                return classLoader;
            }
        }
        for (ClassLoader classLoader4 = classLoader2; classLoader4 != null; classLoader4 = classLoader4.getParent()) {
            if (classLoader4 == classLoader) {
                return classLoader2;
            }
        }
        return null;
    }

    private ClassLoader getParentClassLoader(final ClassLoader classLoader) {
        try {
            return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.amazonaws.org.apache.commons.logging.impl.LogFactoryImpl.3
                @Override // java.security.PrivilegedAction
                public Object run() {
                    return classLoader.getParent();
                }
            });
        } catch (SecurityException unused) {
            logDiagnostic("[SECURITY] Unable to obtain parent classloader");
            return null;
        }
    }

    private static String getSystemProperty(final String str, final String str2) throws SecurityException {
        return (String) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.amazonaws.org.apache.commons.logging.impl.LogFactoryImpl.2
            @Override // java.security.PrivilegedAction
            public Object run() {
                return System.getProperty(str, str2);
            }
        });
    }

    private void handleFlawedDiscovery(String str, ClassLoader classLoader, Throwable th) {
        Throwable targetException;
        Throwable exception;
        if (isDiagnosticsEnabled()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Could not instantiate Log '");
            stringBuffer.append(str);
            stringBuffer.append("' -- ");
            stringBuffer.append(th.getClass().getName());
            stringBuffer.append(RealTimeTextConstants.COLON_SPACE);
            stringBuffer.append(th.getLocalizedMessage());
            logDiagnostic(stringBuffer.toString());
            if ((th instanceof InvocationTargetException) && (targetException = ((InvocationTargetException) th).getTargetException()) != null) {
                StringBuffer outline103 = GeneratedOutlineSupport1.outline103("... InvocationTargetException: ");
                outline103.append(targetException.getClass().getName());
                outline103.append(RealTimeTextConstants.COLON_SPACE);
                outline103.append(targetException.getLocalizedMessage());
                logDiagnostic(outline103.toString());
                if ((targetException instanceof ExceptionInInitializerError) && (exception = ((ExceptionInInitializerError) targetException).getException()) != null) {
                    StringBuffer outline1032 = GeneratedOutlineSupport1.outline103("... ExceptionInInitializerError: ");
                    outline1032.append(exception.getClass().getName());
                    outline1032.append(RealTimeTextConstants.COLON_SPACE);
                    outline1032.append(exception.getLocalizedMessage());
                    logDiagnostic(outline1032.toString());
                }
            }
        }
        if (this.allowFlawedDiscovery) {
            return;
        }
        throw new LogConfigurationException(th);
    }

    private void handleFlawedHierarchy(ClassLoader classLoader, Class cls) throws LogConfigurationException {
        Class cls2;
        Class cls3 = class$org$apache$commons$logging$Log;
        if (cls3 == null) {
            cls3 = class$(LOG_PROPERTY);
            class$org$apache$commons$logging$Log = cls3;
        }
        String name = cls3.getName();
        Class<?>[] interfaces = cls.getInterfaces();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= interfaces.length) {
                break;
            } else if (name.equals(interfaces[i].getName())) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (z) {
            if (isDiagnosticsEnabled()) {
                try {
                    if (class$org$apache$commons$logging$Log == null) {
                        cls2 = class$(LOG_PROPERTY);
                        class$org$apache$commons$logging$Log = cls2;
                    } else {
                        cls2 = class$org$apache$commons$logging$Log;
                    }
                    ClassLoader classLoader2 = getClassLoader(cls2);
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Class '");
                    stringBuffer.append(cls.getName());
                    stringBuffer.append("' was found in classloader ");
                    stringBuffer.append(LogFactory.objectId(classLoader));
                    stringBuffer.append(". It is bound to a Log interface which is not");
                    stringBuffer.append(" the one loaded from classloader ");
                    stringBuffer.append(LogFactory.objectId(classLoader2));
                    logDiagnostic(stringBuffer.toString());
                } catch (Throwable unused) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Error while trying to output diagnostics about bad class '");
                    stringBuffer2.append(cls);
                    stringBuffer2.append("'");
                    logDiagnostic(stringBuffer2.toString());
                }
            }
            if (!this.allowFlawedHierarchy) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Terminating logging for this context ");
                stringBuffer3.append("due to bad log hierarchy. ");
                stringBuffer3.append("You have more than one version of '");
                Class cls4 = class$org$apache$commons$logging$Log;
                if (cls4 == null) {
                    cls4 = class$(LOG_PROPERTY);
                    class$org$apache$commons$logging$Log = cls4;
                }
                stringBuffer3.append(cls4.getName());
                stringBuffer3.append("' visible.");
                if (isDiagnosticsEnabled()) {
                    logDiagnostic(stringBuffer3.toString());
                }
                throw new LogConfigurationException(stringBuffer3.toString());
            } else if (!isDiagnosticsEnabled()) {
            } else {
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append("Warning: bad log hierarchy. ");
                stringBuffer4.append("You have more than one version of '");
                Class cls5 = class$org$apache$commons$logging$Log;
                if (cls5 == null) {
                    cls5 = class$(LOG_PROPERTY);
                    class$org$apache$commons$logging$Log = cls5;
                }
                stringBuffer4.append(cls5.getName());
                stringBuffer4.append("' visible.");
                logDiagnostic(stringBuffer4.toString());
            }
        } else if (!this.allowFlawedDiscovery) {
            StringBuffer stringBuffer5 = new StringBuffer();
            stringBuffer5.append("Terminating logging for this context. ");
            stringBuffer5.append("Log class '");
            stringBuffer5.append(cls.getName());
            stringBuffer5.append("' does not implement the Log interface.");
            if (isDiagnosticsEnabled()) {
                logDiagnostic(stringBuffer5.toString());
            }
            throw new LogConfigurationException(stringBuffer5.toString());
        } else if (!isDiagnosticsEnabled()) {
        } else {
            StringBuffer outline103 = GeneratedOutlineSupport1.outline103("[WARNING] Log class '");
            outline103.append(cls.getName());
            outline103.append("' does not implement the Log interface.");
            logDiagnostic(outline103.toString());
        }
    }

    private void informUponSimilarName(StringBuffer stringBuffer, String str, String str2) {
        if (!str.equals(str2) && str.regionMatches(true, 0, str2, 0, PKG_LEN + 5)) {
            stringBuffer.append(" Did you mean '");
            stringBuffer.append(str2);
            stringBuffer.append("'?");
        }
    }

    private void initConfiguration() {
        this.allowFlawedContext = getBooleanConfiguration(ALLOW_FLAWED_CONTEXT_PROPERTY, true);
        this.allowFlawedDiscovery = getBooleanConfiguration(ALLOW_FLAWED_DISCOVERY_PROPERTY, true);
        this.allowFlawedHierarchy = getBooleanConfiguration(ALLOW_FLAWED_HIERARCHY_PROPERTY, true);
    }

    private void initDiagnostics() {
        String str;
        ClassLoader classLoader = getClassLoader(LogFactoryImpl.class);
        if (classLoader == null) {
            str = "BOOTLOADER";
        } else {
            try {
                str = LogFactory.objectId(classLoader);
            } catch (SecurityException unused) {
                str = "UNKNOWN";
            }
        }
        StringBuffer outline103 = GeneratedOutlineSupport1.outline103("[LogFactoryImpl@");
        outline103.append(System.identityHashCode(this));
        outline103.append(" from ");
        outline103.append(str);
        outline103.append("] ");
        this.diagnosticPrefix = outline103.toString();
    }

    protected static boolean isDiagnosticsEnabled() {
        return LogFactory.isDiagnosticsEnabled();
    }

    private boolean isLogLibraryAvailable(String str, String str2) {
        if (isDiagnosticsEnabled()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Checking for '");
            stringBuffer.append(str);
            stringBuffer.append("'.");
            logDiagnostic(stringBuffer.toString());
        }
        try {
            if (createLogFromClass(str2, getClass().getName(), false) == null) {
                if (isDiagnosticsEnabled()) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Did not find '");
                    stringBuffer2.append(str);
                    stringBuffer2.append("'.");
                    logDiagnostic(stringBuffer2.toString());
                }
                return false;
            } else if (!isDiagnosticsEnabled()) {
                return true;
            } else {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Found '");
                stringBuffer3.append(str);
                stringBuffer3.append("'.");
                logDiagnostic(stringBuffer3.toString());
                return true;
            }
        } catch (LogConfigurationException unused) {
            if (isDiagnosticsEnabled()) {
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append("Logging system '");
                stringBuffer4.append(str);
                stringBuffer4.append("' is available but not useable.");
                logDiagnostic(stringBuffer4.toString());
            }
            return false;
        }
    }

    @Override // com.amazonaws.org.apache.commons.logging.LogFactory
    public Object getAttribute(String str) {
        return this.attributes.get(str);
    }

    @Override // com.amazonaws.org.apache.commons.logging.LogFactory
    public String[] getAttributeNames() {
        Vector vector = new Vector();
        Enumeration keys = this.attributes.keys();
        while (keys.hasMoreElements()) {
            vector.addElement((String) keys.nextElement());
        }
        String[] strArr = new String[vector.size()];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = (String) vector.elementAt(i);
        }
        return strArr;
    }

    @Override // com.amazonaws.org.apache.commons.logging.LogFactory
    public Log getInstance(Class cls) throws LogConfigurationException {
        return getInstance(cls.getName());
    }

    protected String getLogClassName() {
        if (this.logClassName == null) {
            discoverLogImplementation(LogFactoryImpl.class.getName());
        }
        return this.logClassName;
    }

    protected Constructor getLogConstructor() throws LogConfigurationException {
        if (this.logConstructor == null) {
            discoverLogImplementation(LogFactoryImpl.class.getName());
        }
        return this.logConstructor;
    }

    protected boolean isJdk13LumberjackAvailable() {
        return isLogLibraryAvailable("Jdk13Lumberjack", LOGGING_IMPL_LUMBERJACK_LOGGER);
    }

    protected boolean isJdk14Available() {
        return isLogLibraryAvailable("Jdk14", LOGGING_IMPL_JDK14_LOGGER);
    }

    protected boolean isLog4JAvailable() {
        return isLogLibraryAvailable("Log4J", LOGGING_IMPL_LOG4J_LOGGER);
    }

    protected void logDiagnostic(String str) {
        if (isDiagnosticsEnabled()) {
            LogFactory.logRawDiagnostic(GeneratedOutlineSupport1.outline83(new StringBuffer(), this.diagnosticPrefix, str));
        }
    }

    protected Log newInstance(String str) throws LogConfigurationException {
        Log log;
        try {
            if (this.logConstructor == null) {
                log = discoverLogImplementation(str);
            } else {
                log = (Log) this.logConstructor.newInstance(str);
            }
            if (this.logMethod != null) {
                this.logMethod.invoke(log, this);
            }
            return log;
        } catch (LogConfigurationException e) {
            throw e;
        } catch (InvocationTargetException e2) {
            Throwable targetException = e2.getTargetException();
            if (targetException != null) {
                throw new LogConfigurationException(targetException);
            }
            throw new LogConfigurationException(e2);
        } catch (Throwable th) {
            throw new LogConfigurationException(th);
        }
    }

    @Override // com.amazonaws.org.apache.commons.logging.LogFactory
    public void release() {
        logDiagnostic("Releasing all known loggers");
        this.instances.clear();
    }

    @Override // com.amazonaws.org.apache.commons.logging.LogFactory
    public void removeAttribute(String str) {
        this.attributes.remove(str);
    }

    @Override // com.amazonaws.org.apache.commons.logging.LogFactory
    public void setAttribute(String str, Object obj) {
        if (this.logConstructor != null) {
            logDiagnostic("setAttribute: call too late; configuration already performed.");
        }
        if (obj == null) {
            this.attributes.remove(str);
        } else {
            this.attributes.put(str, obj);
        }
        if (str.equals(LogFactory.TCCL_KEY)) {
            this.useTCCL = Boolean.valueOf(obj.toString()).booleanValue();
        }
    }

    @Override // com.amazonaws.org.apache.commons.logging.LogFactory
    public Log getInstance(String str) throws LogConfigurationException {
        Log log = (Log) this.instances.get(str);
        if (log == null) {
            Log newInstance = newInstance(str);
            this.instances.put(str, newInstance);
            return newInstance;
        }
        return log;
    }
}
