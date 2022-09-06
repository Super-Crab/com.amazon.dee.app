package com.esotericsoftware.reflectasm;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;
import java.util.WeakHashMap;
/* loaded from: classes2.dex */
class AccessClassLoader extends ClassLoader {
    private static final WeakHashMap<ClassLoader, WeakReference<AccessClassLoader>> accessClassLoaders = new WeakHashMap<>();
    private static final ClassLoader selfContextParentClassLoader = getParentClassLoader(AccessClassLoader.class);
    private static volatile AccessClassLoader selfContextAccessClassLoader = new AccessClassLoader(selfContextParentClassLoader);

    private AccessClassLoader(ClassLoader classLoader) {
        super(classLoader);
    }

    public static int activeAccessClassLoaders() {
        int size = accessClassLoaders.size();
        return selfContextAccessClassLoader != null ? size + 1 : size;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AccessClassLoader get(Class cls) {
        ClassLoader parentClassLoader = getParentClassLoader(cls);
        if (selfContextParentClassLoader.equals(parentClassLoader)) {
            if (selfContextAccessClassLoader == null) {
                synchronized (accessClassLoaders) {
                    if (selfContextAccessClassLoader == null) {
                        selfContextAccessClassLoader = new AccessClassLoader(selfContextParentClassLoader);
                    }
                }
            }
            return selfContextAccessClassLoader;
        }
        synchronized (accessClassLoaders) {
            WeakReference<AccessClassLoader> weakReference = accessClassLoaders.get(parentClassLoader);
            if (weakReference != null) {
                AccessClassLoader accessClassLoader = weakReference.get();
                if (accessClassLoader != null) {
                    return accessClassLoader;
                }
                accessClassLoaders.remove(parentClassLoader);
            }
            AccessClassLoader accessClassLoader2 = new AccessClassLoader(parentClassLoader);
            accessClassLoaders.put(parentClassLoader, new WeakReference<>(accessClassLoader2));
            return accessClassLoader2;
        }
    }

    private static ClassLoader getParentClassLoader(Class cls) {
        ClassLoader classLoader = cls.getClassLoader();
        return classLoader == null ? ClassLoader.getSystemClassLoader() : classLoader;
    }

    public static void remove(ClassLoader classLoader) {
        if (selfContextParentClassLoader.equals(classLoader)) {
            selfContextAccessClassLoader = null;
            return;
        }
        synchronized (accessClassLoaders) {
            accessClassLoaders.remove(classLoader);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Class<?> defineClass(String str, byte[] bArr) throws ClassFormatError {
        try {
            Method declaredMethod = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, ProtectionDomain.class);
            if (!declaredMethod.isAccessible()) {
                declaredMethod.setAccessible(true);
            }
            return (Class) declaredMethod.invoke(getParent(), str, bArr, 0, Integer.valueOf(bArr.length), getClass().getProtectionDomain());
        } catch (Exception unused) {
            return defineClass(str, bArr, 0, bArr.length, AccessClassLoader.class.getProtectionDomain());
        }
    }

    @Override // java.lang.ClassLoader
    protected synchronized Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
        if (str.equals(FieldAccess.class.getName())) {
            return FieldAccess.class;
        }
        if (str.equals(MethodAccess.class.getName())) {
            return MethodAccess.class;
        }
        if (str.equals(ConstructorAccess.class.getName())) {
            return ConstructorAccess.class;
        }
        return super.loadClass(str, z);
    }
}
