package com.amazon.identity.auth.device;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
/* compiled from: DCP */
/* renamed from: com.amazon.identity.auth.device.if  reason: invalid class name */
/* loaded from: classes12.dex */
public final class Cif {
    private static final String TAG = "com.amazon.identity.auth.device.if";
    private static final WeakHashMap<Class<?>, Method> rg = new WeakHashMap<>();

    private Cif() {
    }

    private static synchronized Object a(Cloneable cloneable) throws CloneNotSupportedException {
        Object invoke;
        synchronized (Cif.class) {
            try {
                Class<?> cls = cloneable.getClass();
                Method method = rg.get(cls);
                if (method == null) {
                    method = cls.getMethod("clone", new Class[0]);
                    method.setAccessible(true);
                    rg.put(cls, method);
                }
                invoke = method.invoke(cloneable, new Object[0]);
            } catch (Exception e) {
                String format = String.format("Clone method failed on the object of type %s", cloneable.getClass().getName());
                io.e(TAG, format, e);
                throw new CloneNotSupportedException(format);
            }
        }
        return invoke;
    }

    public static boolean equals(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        } else if (obj2 != null && obj2.getClass() == obj2.getClass()) {
            return obj.equals(obj2);
        } else {
            return false;
        }
    }

    public static <T> T g(T t) throws CloneNotSupportedException {
        if (t == null) {
            return null;
        }
        if (t instanceof ix) {
            return (T) ((ix) t).ek();
        }
        if ((t instanceof String) || (t instanceof Integer)) {
            return t;
        }
        if (t instanceof Cloneable) {
            return (T) a((Cloneable) t);
        }
        throw new CloneNotSupportedException(String.format("Clone not supported on type %s", t.getClass().getName()));
    }

    public static <T extends ix<T>> Map<String, T> i(Map<String, T> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, T> entry : map.entrySet()) {
            T value = entry.getValue();
            hashMap.put(entry.getKey(), value == null ? null : value.ek());
        }
        return hashMap;
    }
}
