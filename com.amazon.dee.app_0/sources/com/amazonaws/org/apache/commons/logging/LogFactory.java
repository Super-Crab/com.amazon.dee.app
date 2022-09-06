package com.amazonaws.org.apache.commons.logging;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
/* loaded from: classes13.dex */
public abstract class LogFactory {
    public static final String DIAGNOSTICS_DEST_PROPERTY = "com.amazonaws.org.apache.commons.logging.diagnostics.dest";
    public static final String FACTORY_DEFAULT = "com.amazonaws.org.apache.commons.logging.impl.LogFactoryImpl";
    public static final String FACTORY_PROPERTIES = "commons-logging.properties";
    public static final String FACTORY_PROPERTY = "com.amazonaws.org.apache.commons.logging.LogFactory";
    public static final String HASHTABLE_IMPLEMENTATION_PROPERTY = "com.amazonaws.org.apache.commons.logging.LogFactory.HashtableImpl";
    public static final String PRIORITY_KEY = "priority";
    protected static final String SERVICE_ID = "META-INF/services/org.apache.commons.logging.LogFactory";
    public static final String TCCL_KEY = "use_tccl";
    private static final String WEAK_HASHTABLE_CLASSNAME = "com.amazonaws.org.apache.commons.logging.impl.WeakHashtable";
    static /* synthetic */ Class class$java$lang$Thread;
    static /* synthetic */ Class class$org$apache$commons$logging$LogFactory;
    private static String diagnosticPrefix;
    private static PrintStream diagnosticsStream;
    protected static Hashtable factories;
    protected static LogFactory nullClassLoaderFactory;
    private static ClassLoader thisClassLoader;

    static {
        Class cls = class$org$apache$commons$logging$LogFactory;
        if (cls == null) {
            cls = class$(FACTORY_PROPERTY);
            class$org$apache$commons$logging$LogFactory = cls;
        }
        thisClassLoader = getClassLoader(cls);
        initDiagnostics();
        Class cls2 = class$org$apache$commons$logging$LogFactory;
        if (cls2 == null) {
            cls2 = class$(FACTORY_PROPERTY);
            class$org$apache$commons$logging$LogFactory = cls2;
        }
        logClassLoaderEnvironment(cls2);
        factories = createFactoryStore();
        if (isDiagnosticsEnabled()) {
            logDiagnostic("BOOTSTRAP COMPLETED");
        }
    }

    private static void cacheFactory(ClassLoader classLoader, LogFactory logFactory) {
        if (logFactory != null) {
            if (classLoader == null) {
                nullClassLoaderFactory = logFactory;
            } else {
                factories.put(classLoader, logFactory);
            }
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    protected static Object createFactory(String str, ClassLoader classLoader) {
        Class cls;
        String stringBuffer;
        Class cls2;
        Class cls3;
        Class<?> cls4 = null;
        try {
            if (classLoader != null) {
                try {
                    try {
                        cls4 = classLoader.loadClass(str);
                        if (class$org$apache$commons$logging$LogFactory == null) {
                            cls2 = class$(FACTORY_PROPERTY);
                            class$org$apache$commons$logging$LogFactory = cls2;
                        } else {
                            cls2 = class$org$apache$commons$logging$LogFactory;
                        }
                        if (cls2.isAssignableFrom(cls4)) {
                            if (isDiagnosticsEnabled()) {
                                StringBuffer stringBuffer2 = new StringBuffer();
                                stringBuffer2.append("Loaded class ");
                                stringBuffer2.append(cls4.getName());
                                stringBuffer2.append(" from classloader ");
                                stringBuffer2.append(objectId(classLoader));
                                logDiagnostic(stringBuffer2.toString());
                            }
                        } else if (isDiagnosticsEnabled()) {
                            StringBuffer stringBuffer3 = new StringBuffer();
                            stringBuffer3.append("Factory class ");
                            stringBuffer3.append(cls4.getName());
                            stringBuffer3.append(" loaded from classloader ");
                            stringBuffer3.append(objectId(cls4.getClassLoader()));
                            stringBuffer3.append(" does not extend '");
                            if (class$org$apache$commons$logging$LogFactory == null) {
                                cls3 = class$(FACTORY_PROPERTY);
                                class$org$apache$commons$logging$LogFactory = cls3;
                            } else {
                                cls3 = class$org$apache$commons$logging$LogFactory;
                            }
                            stringBuffer3.append(cls3.getName());
                            stringBuffer3.append("' as loaded by this classloader.");
                            logDiagnostic(stringBuffer3.toString());
                            logHierarchy("[BAD CL TREE] ", classLoader);
                        }
                        return (LogFactory) cls4.newInstance();
                    } catch (ClassCastException unused) {
                        if (classLoader == thisClassLoader) {
                            boolean implementsLogFactory = implementsLogFactory(cls4);
                            StringBuffer stringBuffer4 = new StringBuffer();
                            stringBuffer4.append("The application has specified that a custom LogFactory implementation should be used but Class '");
                            stringBuffer4.append(str);
                            stringBuffer4.append("' cannot be converted to '");
                            if (class$org$apache$commons$logging$LogFactory == null) {
                                cls = class$(FACTORY_PROPERTY);
                                class$org$apache$commons$logging$LogFactory = cls;
                            } else {
                                cls = class$org$apache$commons$logging$LogFactory;
                            }
                            stringBuffer4.append(cls.getName());
                            stringBuffer4.append("'. ");
                            String stringBuffer5 = stringBuffer4.toString();
                            if (implementsLogFactory) {
                                StringBuffer stringBuffer6 = new StringBuffer();
                                stringBuffer6.append(stringBuffer5);
                                stringBuffer6.append("The conflict is caused by the presence of multiple LogFactory classes in incompatible classloaders. ");
                                stringBuffer6.append("Background can be found in http://commons.apache.org/logging/tech.html. ");
                                stringBuffer6.append("If you have not explicitly specified a custom LogFactory then it is likely that ");
                                stringBuffer6.append("the container has set one without your knowledge. ");
                                stringBuffer6.append("In this case, consider using the commons-logging-adapters.jar file or ");
                                stringBuffer6.append("specifying the standard LogFactory from the command line. ");
                                stringBuffer = stringBuffer6.toString();
                            } else {
                                StringBuffer stringBuffer7 = new StringBuffer();
                                stringBuffer7.append(stringBuffer5);
                                stringBuffer7.append("Please check the custom implementation. ");
                                stringBuffer = stringBuffer7.toString();
                            }
                            StringBuffer stringBuffer8 = new StringBuffer();
                            stringBuffer8.append(stringBuffer);
                            stringBuffer8.append("Help can be found @http://commons.apache.org/logging/troubleshooting.html.");
                            String stringBuffer9 = stringBuffer8.toString();
                            if (isDiagnosticsEnabled()) {
                                logDiagnostic(stringBuffer9);
                            }
                            throw new ClassCastException(stringBuffer9);
                        }
                    }
                } catch (ClassNotFoundException e) {
                    if (classLoader == thisClassLoader) {
                        if (isDiagnosticsEnabled()) {
                            StringBuffer stringBuffer10 = new StringBuffer();
                            stringBuffer10.append("Unable to locate any class called '");
                            stringBuffer10.append(str);
                            stringBuffer10.append("' via classloader ");
                            stringBuffer10.append(objectId(classLoader));
                            logDiagnostic(stringBuffer10.toString());
                        }
                        throw e;
                    }
                } catch (NoClassDefFoundError e2) {
                    if (classLoader == thisClassLoader) {
                        if (isDiagnosticsEnabled()) {
                            StringBuffer stringBuffer11 = new StringBuffer();
                            stringBuffer11.append("Class '");
                            stringBuffer11.append(str);
                            stringBuffer11.append("' cannot be loaded");
                            stringBuffer11.append(" via classloader ");
                            stringBuffer11.append(objectId(classLoader));
                            stringBuffer11.append(" - it depends on some other class that cannot");
                            stringBuffer11.append(" be found.");
                            logDiagnostic(stringBuffer11.toString());
                        }
                        throw e2;
                    }
                }
            }
            if (isDiagnosticsEnabled()) {
                StringBuffer stringBuffer12 = new StringBuffer();
                stringBuffer12.append("Unable to load factory class via classloader ");
                stringBuffer12.append(objectId(classLoader));
                stringBuffer12.append(" - trying the classloader associated with this LogFactory.");
                logDiagnostic(stringBuffer12.toString());
            }
            return (LogFactory) Class.forName(str).newInstance();
        } catch (Exception e3) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("Unable to create LogFactory instance.");
            }
            if (cls4 != null) {
                Class cls5 = class$org$apache$commons$logging$LogFactory;
                if (cls5 == null) {
                    cls5 = class$(FACTORY_PROPERTY);
                    class$org$apache$commons$logging$LogFactory = cls5;
                }
                if (!cls5.isAssignableFrom(cls4)) {
                    return new LogConfigurationException("The chosen LogFactory implementation does not extend LogFactory. Please check your configuration.", e3);
                }
            }
            return new LogConfigurationException(e3);
        }
    }

    private static final Hashtable createFactoryStore() {
        String str;
        Hashtable hashtable = null;
        try {
            str = getSystemProperty(HASHTABLE_IMPLEMENTATION_PROPERTY, null);
        } catch (SecurityException unused) {
            str = null;
        }
        if (str == null) {
            str = WEAK_HASHTABLE_CLASSNAME;
        }
        try {
            hashtable = (Hashtable) Class.forName(str).newInstance();
        } catch (Throwable unused2) {
            if (!WEAK_HASHTABLE_CLASSNAME.equals(str)) {
                if (isDiagnosticsEnabled()) {
                    logDiagnostic("[ERROR] LogFactory: Load of custom hashtable failed");
                } else {
                    System.err.println("[ERROR] LogFactory: Load of custom hashtable failed");
                }
            }
        }
        return hashtable == null ? new Hashtable() : hashtable;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static ClassLoader directGetContextClassLoader() throws LogConfigurationException {
        Class cls;
        try {
            if (class$java$lang$Thread == null) {
                cls = class$("java.lang.Thread");
                class$java$lang$Thread = cls;
            } else {
                cls = class$java$lang$Thread;
            }
            try {
                return (ClassLoader) cls.getMethod("getContextClassLoader", null).invoke(Thread.currentThread(), null);
            } catch (IllegalAccessException e) {
                throw new LogConfigurationException("Unexpected IllegalAccessException", e);
            } catch (InvocationTargetException e2) {
                if (!(e2.getTargetException() instanceof SecurityException)) {
                    throw new LogConfigurationException("Unexpected InvocationTargetException", e2.getTargetException());
                }
                return null;
            }
        } catch (NoSuchMethodException unused) {
            Class cls2 = class$org$apache$commons$logging$LogFactory;
            if (cls2 == null) {
                cls2 = class$(FACTORY_PROPERTY);
                class$org$apache$commons$logging$LogFactory = cls2;
            }
            return getClassLoader(cls2);
        }
    }

    private static LogFactory getCachedFactory(ClassLoader classLoader) {
        if (classLoader == null) {
            return nullClassLoaderFactory;
        }
        return (LogFactory) factories.get(classLoader);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static ClassLoader getClassLoader(Class cls) {
        try {
            return cls.getClassLoader();
        } catch (SecurityException e) {
            if (isDiagnosticsEnabled()) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Unable to get classloader for class '");
                stringBuffer.append(cls);
                stringBuffer.append("' due to security restrictions - ");
                stringBuffer.append(e.getMessage());
                logDiagnostic(stringBuffer.toString());
            }
            throw e;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00ec  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final java.util.Properties getConfigurationFile(java.lang.ClassLoader r14, java.lang.String r15) {
        /*
            Method dump skipped, instructions count: 298
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazonaws.org.apache.commons.logging.LogFactory.getConfigurationFile(java.lang.ClassLoader, java.lang.String):java.util.Properties");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static ClassLoader getContextClassLoader() throws LogConfigurationException {
        return directGetContextClassLoader();
    }

    private static ClassLoader getContextClassLoaderInternal() throws LogConfigurationException {
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.amazonaws.org.apache.commons.logging.LogFactory.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                return LogFactory.directGetContextClassLoader();
            }
        });
    }

    public static LogFactory getFactory() throws LogConfigurationException {
        BufferedReader bufferedReader;
        String property;
        ClassLoader contextClassLoaderInternal = getContextClassLoaderInternal();
        if (contextClassLoaderInternal == null && isDiagnosticsEnabled()) {
            logDiagnostic("Context classloader is null.");
        }
        LogFactory cachedFactory = getCachedFactory(contextClassLoaderInternal);
        if (cachedFactory != null) {
            return cachedFactory;
        }
        if (isDiagnosticsEnabled()) {
            StringBuffer outline103 = GeneratedOutlineSupport1.outline103("[LOOKUP] LogFactory implementation requested for the first time for context classloader ");
            outline103.append(objectId(contextClassLoaderInternal));
            logDiagnostic(outline103.toString());
            logHierarchy("[LOOKUP] ", contextClassLoaderInternal);
        }
        Properties configurationFile = getConfigurationFile(contextClassLoaderInternal, FACTORY_PROPERTIES);
        ClassLoader classLoader = (configurationFile == null || (property = configurationFile.getProperty(TCCL_KEY)) == null || Boolean.valueOf(property).booleanValue()) ? contextClassLoaderInternal : thisClassLoader;
        if (isDiagnosticsEnabled()) {
            logDiagnostic("[LOOKUP] Looking for system property [org.apache.commons.logging.LogFactory] to define the LogFactory subclass to use...");
        }
        try {
            String systemProperty = getSystemProperty(FACTORY_PROPERTY, null);
            if (systemProperty != null) {
                if (isDiagnosticsEnabled()) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("[LOOKUP] Creating an instance of LogFactory class '");
                    stringBuffer.append(systemProperty);
                    stringBuffer.append("' as specified by system property ");
                    stringBuffer.append(FACTORY_PROPERTY);
                    logDiagnostic(stringBuffer.toString());
                }
                cachedFactory = newFactory(systemProperty, classLoader, contextClassLoaderInternal);
            } else if (isDiagnosticsEnabled()) {
                logDiagnostic("[LOOKUP] No system property [org.apache.commons.logging.LogFactory] defined.");
            }
        } catch (SecurityException e) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic(GeneratedOutlineSupport1.outline83(GeneratedOutlineSupport1.outline103("[LOOKUP] A security exception occurred while trying to create an instance of the custom factory class: ["), trim(e.getMessage()), "]. Trying alternative implementations..."));
            }
        } catch (RuntimeException e2) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic(GeneratedOutlineSupport1.outline83(GeneratedOutlineSupport1.outline103("[LOOKUP] An exception occurred while trying to create an instance of the custom factory class: ["), trim(e2.getMessage()), "] as specified by a system property."));
            }
            throw e2;
        }
        if (cachedFactory == null) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("[LOOKUP] Looking for a resource file of name [META-INF/services/org.apache.commons.logging.LogFactory] to define the LogFactory subclass to use...");
            }
            try {
                InputStream resourceAsStream = getResourceAsStream(contextClassLoaderInternal, SERVICE_ID);
                if (resourceAsStream != null) {
                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, "UTF-8"));
                    } catch (UnsupportedEncodingException unused) {
                        bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
                    }
                    String readLine = bufferedReader.readLine();
                    bufferedReader.close();
                    if (readLine != null && !"".equals(readLine)) {
                        if (isDiagnosticsEnabled()) {
                            StringBuffer stringBuffer2 = new StringBuffer();
                            stringBuffer2.append("[LOOKUP]  Creating an instance of LogFactory class ");
                            stringBuffer2.append(readLine);
                            stringBuffer2.append(" as specified by file '");
                            stringBuffer2.append(SERVICE_ID);
                            stringBuffer2.append("' which was present in the path of the context");
                            stringBuffer2.append(" classloader.");
                            logDiagnostic(stringBuffer2.toString());
                        }
                        cachedFactory = newFactory(readLine, classLoader, contextClassLoaderInternal);
                    }
                } else if (isDiagnosticsEnabled()) {
                    logDiagnostic("[LOOKUP] No resource file with name 'META-INF/services/org.apache.commons.logging.LogFactory' found.");
                }
            } catch (Exception e3) {
                if (isDiagnosticsEnabled()) {
                    logDiagnostic(GeneratedOutlineSupport1.outline83(GeneratedOutlineSupport1.outline103("[LOOKUP] A security exception occurred while trying to create an instance of the custom factory class: ["), trim(e3.getMessage()), "]. Trying alternative implementations..."));
                }
            }
        }
        if (cachedFactory == null) {
            if (configurationFile != null) {
                if (isDiagnosticsEnabled()) {
                    logDiagnostic("[LOOKUP] Looking in properties file for entry with key 'org.apache.commons.logging.LogFactory' to define the LogFactory subclass to use...");
                }
                String property2 = configurationFile.getProperty(FACTORY_PROPERTY);
                if (property2 != null) {
                    if (isDiagnosticsEnabled()) {
                        StringBuffer stringBuffer3 = new StringBuffer();
                        stringBuffer3.append("[LOOKUP] Properties file specifies LogFactory subclass '");
                        stringBuffer3.append(property2);
                        stringBuffer3.append("'");
                        logDiagnostic(stringBuffer3.toString());
                    }
                    cachedFactory = newFactory(property2, classLoader, contextClassLoaderInternal);
                } else if (isDiagnosticsEnabled()) {
                    logDiagnostic("[LOOKUP] Properties file has no entry specifying LogFactory subclass.");
                }
            } else if (isDiagnosticsEnabled()) {
                logDiagnostic("[LOOKUP] No properties file available to determine LogFactory subclass from..");
            }
        }
        if (cachedFactory == null) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("[LOOKUP] Loading the default LogFactory implementation 'org.apache.commons.logging.impl.LogFactoryImpl' via the same classloader that loaded this LogFactory class (ie not looking in the context classloader).");
            }
            cachedFactory = newFactory(FACTORY_DEFAULT, thisClassLoader, contextClassLoaderInternal);
        }
        if (cachedFactory != null) {
            cacheFactory(contextClassLoaderInternal, cachedFactory);
            if (configurationFile != null) {
                Enumeration<?> propertyNames = configurationFile.propertyNames();
                while (propertyNames.hasMoreElements()) {
                    String str = (String) propertyNames.nextElement();
                    cachedFactory.setAttribute(str, configurationFile.getProperty(str));
                }
            }
        }
        return cachedFactory;
    }

    public static Log getLog(Class cls) throws LogConfigurationException {
        return getFactory().getInstance(cls);
    }

    private static Properties getProperties(final URL url) {
        return (Properties) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.amazonaws.org.apache.commons.logging.LogFactory.5
            @Override // java.security.PrivilegedAction
            public Object run() {
                try {
                    InputStream openStream = url.openStream();
                    if (openStream == null) {
                        return null;
                    }
                    Properties properties = new Properties();
                    properties.load(openStream);
                    openStream.close();
                    return properties;
                } catch (IOException unused) {
                    if (!LogFactory.isDiagnosticsEnabled()) {
                        return null;
                    }
                    StringBuffer outline103 = GeneratedOutlineSupport1.outline103("Unable to read URL ");
                    outline103.append(url);
                    LogFactory.logDiagnostic(outline103.toString());
                    return null;
                }
            }
        });
    }

    private static InputStream getResourceAsStream(final ClassLoader classLoader, final String str) {
        return (InputStream) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.amazonaws.org.apache.commons.logging.LogFactory.3
            @Override // java.security.PrivilegedAction
            public Object run() {
                ClassLoader classLoader2 = classLoader;
                if (classLoader2 != null) {
                    return classLoader2.getResourceAsStream(str);
                }
                return ClassLoader.getSystemResourceAsStream(str);
            }
        });
    }

    private static Enumeration getResources(final ClassLoader classLoader, final String str) {
        return (Enumeration) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.amazonaws.org.apache.commons.logging.LogFactory.4
            @Override // java.security.PrivilegedAction
            public Object run() {
                try {
                    if (classLoader != null) {
                        return classLoader.getResources(str);
                    }
                    return ClassLoader.getSystemResources(str);
                } catch (IOException e) {
                    if (LogFactory.isDiagnosticsEnabled()) {
                        StringBuffer outline103 = GeneratedOutlineSupport1.outline103("Exception while trying to find configuration file ");
                        outline103.append(str);
                        outline103.append(":");
                        outline103.append(e.getMessage());
                        LogFactory.logDiagnostic(outline103.toString());
                    }
                    return null;
                } catch (NoSuchMethodError unused) {
                    return null;
                }
            }
        });
    }

    private static String getSystemProperty(final String str, final String str2) throws SecurityException {
        return (String) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.amazonaws.org.apache.commons.logging.LogFactory.6
            @Override // java.security.PrivilegedAction
            public Object run() {
                return System.getProperty(str, str2);
            }
        });
    }

    private static boolean implementsLogFactory(Class cls) {
        boolean z = false;
        if (cls != null) {
            try {
                ClassLoader classLoader = cls.getClassLoader();
                if (classLoader == null) {
                    logDiagnostic("[CUSTOM LOG FACTORY] was loaded by the boot classloader");
                } else {
                    logHierarchy("[CUSTOM LOG FACTORY] ", classLoader);
                    z = Class.forName(FACTORY_PROPERTY, false, classLoader).isAssignableFrom(cls);
                    if (z) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("[CUSTOM LOG FACTORY] ");
                        stringBuffer.append(cls.getName());
                        stringBuffer.append(" implements LogFactory but was loaded by an incompatible classloader.");
                        logDiagnostic(stringBuffer.toString());
                    } else {
                        StringBuffer stringBuffer2 = new StringBuffer();
                        stringBuffer2.append("[CUSTOM LOG FACTORY] ");
                        stringBuffer2.append(cls.getName());
                        stringBuffer2.append(" does not implement LogFactory.");
                        logDiagnostic(stringBuffer2.toString());
                    }
                }
            } catch (ClassNotFoundException unused) {
                logDiagnostic("[CUSTOM LOG FACTORY] LogFactory class cannot be loaded by classloader which loaded the custom LogFactory implementation. Is the custom factory in the right classloader?");
            } catch (LinkageError e) {
                StringBuffer outline103 = GeneratedOutlineSupport1.outline103("[CUSTOM LOG FACTORY] LinkageError thrown whilst trying to determine whether the compatibility was caused by a classloader conflict: ");
                outline103.append(e.getMessage());
                logDiagnostic(outline103.toString());
            } catch (SecurityException e2) {
                StringBuffer outline1032 = GeneratedOutlineSupport1.outline103("[CUSTOM LOG FACTORY] SecurityException thrown whilst trying to determine whether the compatibility was caused by a classloader conflict: ");
                outline1032.append(e2.getMessage());
                logDiagnostic(outline1032.toString());
            }
        }
        return z;
    }

    private static void initDiagnostics() {
        String str;
        try {
            String systemProperty = getSystemProperty(DIAGNOSTICS_DEST_PROPERTY, null);
            if (systemProperty == null) {
                return;
            }
            if (systemProperty.equals("STDOUT")) {
                diagnosticsStream = System.out;
            } else if (systemProperty.equals("STDERR")) {
                diagnosticsStream = System.err;
            } else {
                diagnosticsStream = new PrintStream(new FileOutputStream(systemProperty, true));
            }
            try {
                str = thisClassLoader == null ? "BOOTLOADER" : objectId(thisClassLoader);
            } catch (SecurityException unused) {
                str = "UNKNOWN";
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("[LogFactory from ");
            stringBuffer.append(str);
            stringBuffer.append("] ");
            diagnosticPrefix = stringBuffer.toString();
        } catch (IOException | SecurityException unused2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean isDiagnosticsEnabled() {
        return diagnosticsStream != null;
    }

    private static void logClassLoaderEnvironment(Class cls) {
        if (!isDiagnosticsEnabled()) {
            return;
        }
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("[ENV] Extension directories (java.ext.dir): ");
            stringBuffer.append(System.getProperty("java.ext.dir"));
            logDiagnostic(stringBuffer.toString());
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("[ENV] Application classpath (java.class.path): ");
            stringBuffer2.append(System.getProperty("java.class.path"));
            logDiagnostic(stringBuffer2.toString());
        } catch (SecurityException unused) {
            logDiagnostic("[ENV] Security setting prevent interrogation of system classpaths.");
        }
        String name = cls.getName();
        try {
            ClassLoader classLoader = getClassLoader(cls);
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("[ENV] Class ");
            stringBuffer3.append(name);
            stringBuffer3.append(" was loaded via classloader ");
            stringBuffer3.append(objectId(classLoader));
            logDiagnostic(stringBuffer3.toString());
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("[ENV] Ancestry of classloader which loaded ");
            stringBuffer4.append(name);
            stringBuffer4.append(" is ");
            logHierarchy(stringBuffer4.toString(), classLoader);
        } catch (SecurityException unused2) {
            logDiagnostic(GeneratedOutlineSupport1.outline71("[ENV] Security forbids determining the classloader for ", name));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void logDiagnostic(String str) {
        PrintStream printStream = diagnosticsStream;
        if (printStream != null) {
            printStream.print(diagnosticPrefix);
            diagnosticsStream.println(str);
            diagnosticsStream.flush();
        }
    }

    private static void logHierarchy(String str, ClassLoader classLoader) {
        if (!isDiagnosticsEnabled()) {
            return;
        }
        if (classLoader != null) {
            String obj = classLoader.toString();
            StringBuffer outline103 = GeneratedOutlineSupport1.outline103(str);
            outline103.append(objectId(classLoader));
            outline103.append(" == '");
            outline103.append(obj);
            outline103.append("'");
            logDiagnostic(outline103.toString());
        }
        try {
            ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
            if (classLoader == null) {
                return;
            }
            StringBuffer stringBuffer = new StringBuffer(GeneratedOutlineSupport1.outline71(str, "ClassLoader tree:"));
            do {
                stringBuffer.append(objectId(classLoader));
                if (classLoader == systemClassLoader) {
                    stringBuffer.append(" (SYSTEM) ");
                }
                try {
                    classLoader = classLoader.getParent();
                    stringBuffer.append(" --> ");
                } catch (SecurityException unused) {
                    stringBuffer.append(" --> SECRET");
                }
            } while (classLoader != null);
            stringBuffer.append("BOOT");
            logDiagnostic(stringBuffer.toString());
        } catch (SecurityException unused2) {
            logDiagnostic(GeneratedOutlineSupport1.outline71(str, "Security forbids determining the system classloader."));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final void logRawDiagnostic(String str) {
        PrintStream printStream = diagnosticsStream;
        if (printStream != null) {
            printStream.println(str);
            diagnosticsStream.flush();
        }
    }

    protected static LogFactory newFactory(final String str, final ClassLoader classLoader, ClassLoader classLoader2) throws LogConfigurationException {
        Object doPrivileged = AccessController.doPrivileged(new PrivilegedAction() { // from class: com.amazonaws.org.apache.commons.logging.LogFactory.2
            @Override // java.security.PrivilegedAction
            public Object run() {
                return LogFactory.createFactory(str, classLoader);
            }
        });
        if (doPrivileged instanceof LogConfigurationException) {
            LogConfigurationException logConfigurationException = (LogConfigurationException) doPrivileged;
            if (isDiagnosticsEnabled()) {
                StringBuffer outline103 = GeneratedOutlineSupport1.outline103("An error occurred while loading the factory class:");
                outline103.append(logConfigurationException.getMessage());
                logDiagnostic(outline103.toString());
            }
            throw logConfigurationException;
        }
        if (isDiagnosticsEnabled()) {
            StringBuffer outline1032 = GeneratedOutlineSupport1.outline103("Created object ");
            outline1032.append(objectId(doPrivileged));
            outline1032.append(" to manage classloader ");
            outline1032.append(objectId(classLoader2));
            logDiagnostic(outline1032.toString());
        }
        return (LogFactory) doPrivileged;
    }

    public static String objectId(Object obj) {
        if (obj == null) {
            return "null";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(obj.getClass().getName());
        stringBuffer.append("@");
        stringBuffer.append(System.identityHashCode(obj));
        return stringBuffer.toString();
    }

    public static void release(ClassLoader classLoader) {
        if (isDiagnosticsEnabled()) {
            StringBuffer outline103 = GeneratedOutlineSupport1.outline103("Releasing factory for classloader ");
            outline103.append(objectId(classLoader));
            logDiagnostic(outline103.toString());
        }
        synchronized (factories) {
            if (classLoader == null) {
                if (nullClassLoaderFactory != null) {
                    nullClassLoaderFactory.release();
                    nullClassLoaderFactory = null;
                }
            } else {
                LogFactory logFactory = (LogFactory) factories.get(classLoader);
                if (logFactory != null) {
                    logFactory.release();
                    factories.remove(classLoader);
                }
            }
        }
    }

    public static void releaseAll() {
        if (isDiagnosticsEnabled()) {
            logDiagnostic("Releasing factory for all classloaders.");
        }
        synchronized (factories) {
            Enumeration elements = factories.elements();
            while (elements.hasMoreElements()) {
                ((LogFactory) elements.nextElement()).release();
            }
            factories.clear();
            if (nullClassLoaderFactory != null) {
                nullClassLoaderFactory.release();
                nullClassLoaderFactory = null;
            }
        }
    }

    private static String trim(String str) {
        if (str == null) {
            return null;
        }
        return str.trim();
    }

    public abstract Object getAttribute(String str);

    public abstract String[] getAttributeNames();

    public abstract Log getInstance(Class cls) throws LogConfigurationException;

    public abstract Log getInstance(String str) throws LogConfigurationException;

    public abstract void release();

    public abstract void removeAttribute(String str);

    public abstract void setAttribute(String str, Object obj);

    public static Log getLog(String str) throws LogConfigurationException {
        return getFactory().getInstance(str);
    }

    protected static LogFactory newFactory(String str, ClassLoader classLoader) {
        return newFactory(str, classLoader, null);
    }
}
