package org.apache.logging.log4j.util;

import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Objects;
/* loaded from: classes4.dex */
public final class LoaderUtil {
    private static final boolean GET_CLASS_LOADER_DISABLED;
    public static final String IGNORE_TCCL_PROPERTY = "log4j.ignoreTCL";
    private static final SecurityManager SECURITY_MANAGER = System.getSecurityManager();
    private static final PrivilegedAction<ClassLoader> TCCL_GETTER = new ThreadContextClassLoaderGetter();
    private static Boolean ignoreTCCL;

    /* loaded from: classes4.dex */
    private static class ThreadContextClassLoaderGetter implements PrivilegedAction<ClassLoader> {
        private ThreadContextClassLoaderGetter() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.security.PrivilegedAction
        /* renamed from: run */
        public ClassLoader mo12843run() {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            if (contextClassLoader != null) {
                return contextClassLoader;
            }
            ClassLoader classLoader = LoaderUtil.class.getClassLoader();
            return (classLoader != null || LoaderUtil.GET_CLASS_LOADER_DISABLED) ? classLoader : ClassLoader.getSystemClassLoader();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class UrlResource {
        private final ClassLoader classLoader;
        private final URL url;

        UrlResource(ClassLoader classLoader, URL url) {
            this.classLoader = classLoader;
            this.url = url;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || UrlResource.class != obj.getClass()) {
                return false;
            }
            UrlResource urlResource = (UrlResource) obj;
            ClassLoader classLoader = this.classLoader;
            if (classLoader == null ? urlResource.classLoader != null : !classLoader.equals(urlResource.classLoader)) {
                return false;
            }
            URL url = this.url;
            URL url2 = urlResource.url;
            return url == null ? url2 == null : url.equals(url2);
        }

        public ClassLoader getClassLoader() {
            return this.classLoader;
        }

        public URL getUrl() {
            return this.url;
        }

        public int hashCode() {
            return Objects.hashCode(this.url) + Objects.hashCode(this.classLoader);
        }
    }

    static {
        SecurityManager securityManager = SECURITY_MANAGER;
        boolean z = false;
        if (securityManager != null) {
            try {
                securityManager.checkPermission(new RuntimePermission("getClassLoader"));
            } catch (SecurityException unused) {
                z = true;
            }
            GET_CLASS_LOADER_DISABLED = z;
            return;
        }
        GET_CLASS_LOADER_DISABLED = false;
    }

    private LoaderUtil() {
    }

    private static void accumulateClassLoaders(ClassLoader classLoader, Collection<ClassLoader> collection) {
        if (classLoader == null || !collection.add(classLoader)) {
            return;
        }
        accumulateClassLoaders(classLoader.getParent(), collection);
    }

    public static Collection<URL> findResources(String str) {
        Collection<UrlResource> findUrlResources = findUrlResources(str);
        LinkedHashSet linkedHashSet = new LinkedHashSet(findUrlResources.size());
        for (UrlResource urlResource : findUrlResources) {
            linkedHashSet.add(urlResource.getUrl());
        }
        return linkedHashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Collection<UrlResource> findUrlResources(String str) {
        ClassLoader[] classLoaderArr = new ClassLoader[3];
        classLoaderArr[0] = getThreadContextClassLoader();
        classLoaderArr[1] = LoaderUtil.class.getClassLoader();
        classLoaderArr[2] = GET_CLASS_LOADER_DISABLED ? null : ClassLoader.getSystemClassLoader();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (ClassLoader classLoader : classLoaderArr) {
            if (classLoader != null) {
                try {
                    Enumeration<URL> resources = classLoader.getResources(str);
                    while (resources.hasMoreElements()) {
                        linkedHashSet.add(new UrlResource(classLoader, resources.nextElement()));
                    }
                } catch (IOException e) {
                    LowLevelLogUtil.logException(e);
                }
            }
        }
        return linkedHashSet;
    }

    public static ClassLoader[] getClassLoaders() {
        ClassLoader systemClassLoader;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ClassLoader threadContextClassLoader = getThreadContextClassLoader();
        if (threadContextClassLoader != null) {
            linkedHashSet.add(threadContextClassLoader);
        }
        accumulateClassLoaders(LoaderUtil.class.getClassLoader(), linkedHashSet);
        accumulateClassLoaders(threadContextClassLoader == null ? null : threadContextClassLoader.getParent(), linkedHashSet);
        if (!GET_CLASS_LOADER_DISABLED && (systemClassLoader = ClassLoader.getSystemClassLoader()) != null) {
            linkedHashSet.add(systemClassLoader);
        }
        return (ClassLoader[]) linkedHashSet.toArray(new ClassLoader[linkedHashSet.size()]);
    }

    public static ClassLoader getThreadContextClassLoader() {
        if (GET_CLASS_LOADER_DISABLED) {
            return LoaderUtil.class.getClassLoader();
        }
        return (ClassLoader) (SECURITY_MANAGER == null ? TCCL_GETTER.run() : AccessController.doPrivileged(TCCL_GETTER));
    }

    public static boolean isClassAvailable(String str) {
        try {
            return loadClass(str) != null;
        } catch (ClassNotFoundException | LinkageError unused) {
            return false;
        } catch (Throwable th) {
            LowLevelLogUtil.logException(GeneratedOutlineSupport1.outline72("Unknown error checking for existence of class: ", str), th);
            return false;
        }
    }

    private static boolean isIgnoreTccl() {
        if (ignoreTCCL == null) {
            String stringProperty = PropertiesUtil.getProperties().getStringProperty(IGNORE_TCCL_PROPERTY, null);
            ignoreTCCL = Boolean.valueOf(stringProperty != null && !PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE.equalsIgnoreCase(stringProperty.trim()));
        }
        return ignoreTCCL.booleanValue();
    }

    public static Class<?> loadClass(String str) throws ClassNotFoundException {
        if (isIgnoreTccl()) {
            return Class.forName(str);
        }
        try {
            ClassLoader threadContextClassLoader = getThreadContextClassLoader();
            if (threadContextClassLoader != null) {
                return threadContextClassLoader.loadClass(str);
            }
        } catch (Throwable unused) {
        }
        return Class.forName(str);
    }

    public static <T> T newCheckedInstanceOf(String str, Class<T> cls) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return cls.cast(newInstanceOf(str));
    }

    public static <T> T newCheckedInstanceOfProperty(String str, Class<T> cls) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String stringProperty = PropertiesUtil.getProperties().getStringProperty(str);
        if (stringProperty == null) {
            return null;
        }
        return (T) newCheckedInstanceOf(stringProperty, cls);
    }

    public static <T> T newInstanceOf(Class<T> cls) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        try {
            return cls.getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (NoSuchMethodException unused) {
            return cls.newInstance();
        }
    }

    public static <T> T newInstanceOf(String str) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        return (T) newInstanceOf(loadClass(str));
    }
}
