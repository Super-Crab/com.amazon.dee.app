package org.apache.thrift.orig;

import java.lang.reflect.InvocationTargetException;
/* loaded from: classes4.dex */
public class TEnumHelper {
    public static TEnum getByValue(Class<? extends TEnum> cls, int i) {
        try {
            return (TEnum) cls.getMethod("findByValue", Integer.TYPE).invoke(null, Integer.valueOf(i));
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return null;
        }
    }
}
