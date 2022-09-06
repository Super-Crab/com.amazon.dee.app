package com.amazon.identity.auth.device.utils;

import android.text.TextUtils;
import com.amazon.identity.auth.device.Cif;
import com.amazon.identity.auth.device.en;
import com.amazon.identity.auth.device.io;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ReflectionHelper {
    private static final Map<String, en<Class<?>>> rv = new ConcurrentHashMap();
    private static final Map<a, en<Method>> rw = new ConcurrentHashMap();
    private static final String TAG = ReflectionHelper.class.getName();

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class CannotCallMethodException extends Exception {
        private static final long serialVersionUID = 1;

        public CannotCallMethodException(String str) {
            super(str);
        }

        public CannotCallMethodException(String str, Throwable th) {
            super(str, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a {

        /* renamed from: rx  reason: collision with root package name */
        private final Class f11rx;
        private final String ry;
        private final Class[] rz;

        public a(Class<?> cls, String str, Class... clsArr) {
            this.f11rx = cls;
            this.ry = str;
            this.rz = clsArr;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && a.class == obj.getClass()) {
                a aVar = (a) obj;
                if (TextUtils.equals(this.ry, aVar.ry) && Arrays.equals(this.rz, aVar.rz) && Cif.equals(this.f11rx, aVar.f11rx)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            Class cls = this.f11rx;
            int i = 0;
            int hashCode = ((cls == null ? 0 : cls.hashCode()) + 31) * 31;
            String str = this.ry;
            if (str != null) {
                i = str.hashCode();
            }
            return ((hashCode + i) * 31) + Arrays.hashCode(this.rz);
        }
    }

    private Class<?> dx(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            io.w(TAG, String.format("Failed to locate class named %s. If this is not a Kindle device, you can ignore this warning.", str));
            return null;
        }
    }

    private Method findMethod(Class<?> cls, String str, Class[] clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (IllegalArgumentException unused) {
            io.e(TAG, "IllegalArguemntException calling method");
            return null;
        } catch (NoSuchMethodException unused2) {
            io.e(TAG, "Method cannot be found. Are you sure it is public?");
            return null;
        } catch (SecurityException e) {
            String str2 = TAG;
            io.e(str2, "Security Exception! Error: " + e.getMessage());
            return null;
        }
    }

    public Object a(String str, Object obj, Class[] clsArr, Object... objArr) throws CannotCallMethodException {
        return a(str, obj.getClass(), obj, clsArr, objArr);
    }

    public Object b(Object obj, String str) throws CannotCallMethodException {
        return a(obj.getClass(), obj, str);
    }

    public Class<?> dw(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        en<Class<?>> enVar = rv.get(str);
        if (enVar == null) {
            en<Class<?>> enVar2 = new en<>(dx(str));
            rv.put(str, enVar2);
            enVar = enVar2;
        }
        return enVar.getValue();
    }

    public Object a(String str, String str2, Class[] clsArr, Object... objArr) throws CannotCallMethodException {
        Class<?> dw = dw(str2);
        if (dw != null) {
            return a(str, dw, null, clsArr, objArr);
        }
        String concat = "Cannot find class ".concat(String.valueOf(str2));
        io.e(TAG, concat);
        throw new CannotCallMethodException(concat);
    }

    public Object a(String str, Class<?> cls, Class[] clsArr, Object... objArr) throws CannotCallMethodException {
        return a(str, cls, null, clsArr, objArr);
    }

    public Object a(Class<?> cls, String str) throws CannotCallMethodException {
        return a(cls, null, str);
    }

    private Object a(Class<?> cls, Object obj, String str) throws CannotCallMethodException {
        try {
            return cls.getField(str).get(obj);
        } catch (IllegalAccessException e) {
            throw new CannotCallMethodException("Cannot get field because of IllegalAccessException", e);
        } catch (IllegalArgumentException e2) {
            throw new CannotCallMethodException("Cannot get field because of IllegalArgumentException", e2);
        } catch (NoSuchFieldException e3) {
            throw new CannotCallMethodException(String.format("Field %s cannot be found", str), e3);
        } catch (SecurityException e4) {
            throw new CannotCallMethodException("Cannot get field because of a security exception", e4);
        }
    }

    private Object a(String str, Class<?> cls, Object obj, Class[] clsArr, Object... objArr) throws CannotCallMethodException {
        try {
            a aVar = new a(cls, str, clsArr);
            en<Method> enVar = rw.get(aVar);
            if (enVar == null) {
                enVar = new en<>(findMethod(cls, str, clsArr));
                rw.put(aVar, enVar);
            }
            Method value = enVar.getValue();
            if (value != null) {
                return value.invoke(obj, objArr);
            }
            throw new CannotCallMethodException(String.format("Method %s cannot be found or accessed!", str));
        } catch (IllegalAccessException e) {
            throw new CannotCallMethodException("IllegalAccessException calling method", e);
        } catch (IllegalArgumentException e2) {
            throw new CannotCallMethodException("IllegalArguemntException calling method", e2);
        } catch (SecurityException e3) {
            throw new CannotCallMethodException(e3.getMessage(), e3);
        } catch (InvocationTargetException e4) {
            io.e(TAG, String.format("Exception thrown while calling method %s", str), e4.getCause());
            throw new CannotCallMethodException("Exception calling method", e4);
        }
    }
}
