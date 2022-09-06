package org.apache.commons.lang.p001enum;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* renamed from: org.apache.commons.lang.enum.EnumUtils  reason: invalid package */
/* loaded from: classes4.dex */
public class EnumUtils {
    public static Enum getEnum(Class cls, String str) {
        return Enum.getEnum(cls, str);
    }

    public static List getEnumList(Class cls) {
        return Enum.getEnumList(cls);
    }

    public static Map getEnumMap(Class cls) {
        return Enum.getEnumMap(cls);
    }

    public static Iterator iterator(Class cls) {
        return Enum.getEnumList(cls).iterator();
    }

    public static ValuedEnum getEnum(Class cls, int i) {
        return (ValuedEnum) ValuedEnum.getEnum(cls, i);
    }
}
