package com.amazonaws.mobileconnectors.remoteconfiguration.clientcontextdecorator;

import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
/* loaded from: classes13.dex */
public class ReflectionHelper {
    private static final String TAG = "com.amazonaws.mobileconnectors.remoteconfiguration.clientcontextdecorator.ReflectionHelper";

    private <T extends Throwable> void checkNotNull(Object obj, Class<T> cls, String str, Object... objArr) throws Throwable {
        if (obj == null) {
            throwException(cls, str, objArr);
        }
    }

    private <T> Method getHiddenMethodOrThrow(Class<T> cls, String str) throws IllegalAccessException {
        Method hiddenMethod = getHiddenMethod(cls, str);
        checkNotNull(hiddenMethod, IllegalAccessException.class, "Hidden method: %s is not accessible", str);
        return hiddenMethod;
    }

    private <T extends Throwable> void throwException(Class<T> cls, String str, Object... objArr) throws Throwable {
        if (cls == null) {
            throw new IllegalArgumentException("clazz may not be null");
        }
        try {
            throw cls.getConstructor(String.class).newInstance(String.format(str, objArr));
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalStateException(GeneratedOutlineSupport1.outline38(cls, GeneratedOutlineSupport1.outline107("Failed to throw the requested exception: ")), e);
        }
    }

    public <T> T createInstance(Class<T> cls) {
        try {
            return cls.newInstance();
        } catch (IllegalAccessException unused) {
            Log.i(TAG, String.format("IllegalAccessException creating class %s with default constructor.", cls.getName()));
            return null;
        } catch (InstantiationException unused2) {
            Log.i(TAG, String.format("InstantiationException creating class %s with default constructor.", cls.getName()));
            return null;
        }
    }

    public Object getField(Object obj, String str) {
        try {
            return obj.getClass().getField(str).get(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Class<?> getHiddenClass(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            Log.w(TAG, String.format("Failed to locate class named %s. If this is not a Kindle device, you can ignore this warning.", str));
            return null;
        }
    }

    public <T> Method getHiddenMethod(Class<T> cls, String str) {
        Method method;
        Method[] methods = cls.getMethods();
        int length = methods.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                method = null;
                break;
            }
            method = methods[i];
            if (method.getName().equals(str)) {
                break;
            }
            i++;
        }
        if (method == null) {
            String str2 = TAG;
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Failed to find method ", str, " in class ");
            outline115.append(cls.getName());
            Log.e(str2, outline115.toString());
        }
        return method;
    }

    public Object invoke(Method method, Object obj, Object... objArr) throws IllegalAccessException, InvocationTargetException {
        return method.invoke(obj, objArr);
    }

    public <T> Object invokeHiddenMethod(Class<T> cls, String str, Object... objArr) throws IllegalAccessException, InvocationTargetException {
        return invoke(getHiddenMethodOrThrow(cls, str), null, objArr);
    }

    public <T> Object invokeHiddenMethodWithDefault(Class<T> cls, String str, String str2, Object obj) {
        try {
            return invokeHiddenMethod(cls, str, str2);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return obj;
        }
    }

    public Object invokeStatic(String str, String str2, Class<?>[] clsArr, Object... objArr) {
        try {
            return invoke(str, Class.forName(str2), null, clsArr, objArr);
        } catch (ClassNotFoundException unused) {
            GeneratedOutlineSupport1.outline162("Cannot find class ", str2, TAG);
            return null;
        }
    }

    public Object invoke(String str, Object obj) {
        return invoke(str, obj.getClass(), obj, new Class[0], new Object[0]);
    }

    public Object invoke(String str, Object obj, Class<?>[] clsArr, Object... objArr) {
        return invoke(str, obj.getClass(), obj, clsArr, objArr);
    }

    private Object invoke(String str, Class<?> cls, Object obj, Class<?>[] clsArr, Object... objArr) {
        try {
            return cls.getMethod(str, clsArr).invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "IllegalAccessException calling method", e);
            return null;
        } catch (IllegalArgumentException e2) {
            Log.e(TAG, "IllegalArguemntException calling method", e2);
            return null;
        } catch (NoSuchMethodException e3) {
            Log.e(TAG, "Method cannot be found. Are you sure it is public?", e3);
            return null;
        } catch (SecurityException e4) {
            Log.e(TAG, e4.getMessage(), e4);
            return null;
        } catch (InvocationTargetException e5) {
            Log.e(TAG, String.format("Exception thrown while calling method %s", str), e5.getCause());
            Log.e(TAG, "Exception calling method", e5);
            return null;
        }
    }

    public <T> T createInstance(Class<T> cls, List<Class<?>> list, List<?> list2) {
        Class<?>[] clsArr = (Class[]) list.toArray(new Class[0]);
        Object[] array = list2.toArray();
        if (clsArr.length == array.length) {
            for (int i = 0; i < clsArr.length; i++) {
                Class<?> cls2 = clsArr[i];
                Object obj = array[i];
                if (obj != null && !cls2.isAssignableFrom(obj.getClass())) {
                    throw new IllegalArgumentException("paramaterTypes and parameterArgs types do not match");
                }
            }
            try {
                return cls.getConstructor(clsArr).newInstance(array);
            } catch (IllegalAccessException e) {
                Log.e(TAG, String.format("Could not create class %s because the constructor was not visible.", cls.getName()), e);
                return null;
            } catch (IllegalArgumentException e2) {
                Log.e(TAG, String.format("Could not create class %s because of an illegal argument.", cls.getName()), e2);
                return null;
            } catch (InstantiationException e3) {
                Log.e(TAG, String.format("Could not create class %s because of an instantiation exception.", cls.getName()), e3);
                return null;
            } catch (NoSuchMethodException unused) {
                Log.i(TAG, String.format("Could not create class %s because there was no such constructor.", cls.getName()));
                return null;
            } catch (SecurityException e4) {
                Log.e(TAG, String.format("Could not create class %s because we did not have permission", cls.getName()), e4);
                return null;
            } catch (InvocationTargetException e5) {
                Log.e(TAG, String.format("Could not create class %s because constructor threw an exception", cls.getName()), e5);
                return null;
            }
        }
        throw new IllegalArgumentException("paramaterTypes and parameterArgs have different number of args.");
    }
}
