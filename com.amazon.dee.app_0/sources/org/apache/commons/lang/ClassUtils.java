package org.apache.commons.lang;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.text.StrBuilder;
/* loaded from: classes4.dex */
public class ClassUtils {
    public static final char INNER_CLASS_SEPARATOR_CHAR = '$';
    public static final char PACKAGE_SEPARATOR_CHAR = '.';
    private static final Map abbreviationMap;
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$java$lang$Byte;
    static /* synthetic */ Class class$java$lang$Character;
    static /* synthetic */ Class class$java$lang$Double;
    static /* synthetic */ Class class$java$lang$Float;
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$Long;
    static /* synthetic */ Class class$java$lang$Short;
    static /* synthetic */ Class class$org$apache$commons$lang$ClassUtils;
    private static final Map reverseAbbreviationMap;
    private static final Map wrapperPrimitiveMap;
    public static final String PACKAGE_SEPARATOR = String.valueOf('.');
    public static final String INNER_CLASS_SEPARATOR = String.valueOf('$');
    private static final Map primitiveWrapperMap = new HashMap();

    static {
        Map map = primitiveWrapperMap;
        Class cls = Boolean.TYPE;
        Class cls2 = class$java$lang$Boolean;
        if (cls2 == null) {
            cls2 = class$("java.lang.Boolean");
            class$java$lang$Boolean = cls2;
        }
        map.put(cls, cls2);
        Map map2 = primitiveWrapperMap;
        Class cls3 = Byte.TYPE;
        Class cls4 = class$java$lang$Byte;
        if (cls4 == null) {
            cls4 = class$("java.lang.Byte");
            class$java$lang$Byte = cls4;
        }
        map2.put(cls3, cls4);
        Map map3 = primitiveWrapperMap;
        Class cls5 = Character.TYPE;
        Class cls6 = class$java$lang$Character;
        if (cls6 == null) {
            cls6 = class$("java.lang.Character");
            class$java$lang$Character = cls6;
        }
        map3.put(cls5, cls6);
        Map map4 = primitiveWrapperMap;
        Class cls7 = Short.TYPE;
        Class cls8 = class$java$lang$Short;
        if (cls8 == null) {
            cls8 = class$("java.lang.Short");
            class$java$lang$Short = cls8;
        }
        map4.put(cls7, cls8);
        Map map5 = primitiveWrapperMap;
        Class cls9 = Integer.TYPE;
        Class cls10 = class$java$lang$Integer;
        if (cls10 == null) {
            cls10 = class$("java.lang.Integer");
            class$java$lang$Integer = cls10;
        }
        map5.put(cls9, cls10);
        Map map6 = primitiveWrapperMap;
        Class cls11 = Long.TYPE;
        Class cls12 = class$java$lang$Long;
        if (cls12 == null) {
            cls12 = class$("java.lang.Long");
            class$java$lang$Long = cls12;
        }
        map6.put(cls11, cls12);
        Map map7 = primitiveWrapperMap;
        Class cls13 = Double.TYPE;
        Class cls14 = class$java$lang$Double;
        if (cls14 == null) {
            cls14 = class$("java.lang.Double");
            class$java$lang$Double = cls14;
        }
        map7.put(cls13, cls14);
        Map map8 = primitiveWrapperMap;
        Class cls15 = Float.TYPE;
        Class cls16 = class$java$lang$Float;
        if (cls16 == null) {
            cls16 = class$("java.lang.Float");
            class$java$lang$Float = cls16;
        }
        map8.put(cls15, cls16);
        Map map9 = primitiveWrapperMap;
        Class cls17 = Void.TYPE;
        map9.put(cls17, cls17);
        wrapperPrimitiveMap = new HashMap();
        for (Class cls18 : primitiveWrapperMap.keySet()) {
            Class cls19 = (Class) primitiveWrapperMap.get(cls18);
            if (!cls18.equals(cls19)) {
                wrapperPrimitiveMap.put(cls19, cls18);
            }
        }
        abbreviationMap = new HashMap();
        reverseAbbreviationMap = new HashMap();
        addAbbreviation("int", "I");
        addAbbreviation("boolean", "Z");
        addAbbreviation("float", "F");
        addAbbreviation("long", "J");
        addAbbreviation("short", ExifInterface.LATITUDE_SOUTH);
        addAbbreviation("byte", "B");
        addAbbreviation("double", "D");
        addAbbreviation("char", "C");
    }

    private static void addAbbreviation(String str, String str2) {
        abbreviationMap.put(str, str2);
        reverseAbbreviationMap.put(str2, str);
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public static List convertClassNamesToClasses(List list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            try {
                arrayList.add(Class.forName((String) it2.next()));
            } catch (Exception unused) {
                arrayList.add(null);
            }
        }
        return arrayList;
    }

    public static List convertClassesToClassNames(List list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            Class cls = (Class) it2.next();
            if (cls == null) {
                arrayList.add(null);
            } else {
                arrayList.add(cls.getName());
            }
        }
        return arrayList;
    }

    public static List getAllInterfaces(Class cls) {
        if (cls == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        getAllInterfaces(cls, arrayList);
        return arrayList;
    }

    public static List getAllSuperclasses(Class cls) {
        if (cls == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Class superclass = cls.getSuperclass(); superclass != null; superclass = superclass.getSuperclass()) {
            arrayList.add(superclass);
        }
        return arrayList;
    }

    private static String getCanonicalName(String str) {
        String deleteWhitespace = StringUtils.deleteWhitespace(str);
        if (deleteWhitespace == null) {
            return null;
        }
        int i = 0;
        while (deleteWhitespace.startsWith("[")) {
            i++;
            deleteWhitespace = deleteWhitespace.substring(1);
        }
        if (i < 1) {
            return deleteWhitespace;
        }
        if (deleteWhitespace.startsWith("L")) {
            deleteWhitespace = deleteWhitespace.substring(1, deleteWhitespace.endsWith(";") ? deleteWhitespace.length() - 1 : deleteWhitespace.length());
        } else if (deleteWhitespace.length() > 0) {
            deleteWhitespace = (String) reverseAbbreviationMap.get(deleteWhitespace.substring(0, 1));
        }
        StrBuilder strBuilder = new StrBuilder(deleteWhitespace);
        for (int i2 = 0; i2 < i; i2++) {
            strBuilder.append("[]");
        }
        return strBuilder.toString();
    }

    public static Class getClass(ClassLoader classLoader, String str, boolean z) throws ClassNotFoundException {
        try {
            if (abbreviationMap.containsKey(str)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("[");
                stringBuffer.append(abbreviationMap.get(str));
                return Class.forName(stringBuffer.toString(), z, classLoader).getComponentType();
            }
            return Class.forName(toCanonicalName(str), z, classLoader);
        } catch (ClassNotFoundException e) {
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf != -1) {
                try {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append(str.substring(0, lastIndexOf));
                    stringBuffer2.append('$');
                    stringBuffer2.append(str.substring(lastIndexOf + 1));
                    return getClass(classLoader, stringBuffer2.toString(), z);
                } catch (ClassNotFoundException unused) {
                    throw e;
                }
            }
            throw e;
        }
    }

    public static String getPackageCanonicalName(Object obj, String str) {
        return obj == null ? str : getPackageCanonicalName(obj.getClass().getName());
    }

    public static String getPackageName(Object obj, String str) {
        return obj == null ? str : getPackageName(obj.getClass());
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.reflect.Method getPublicMethod(java.lang.Class r2, java.lang.String r3, java.lang.Class[] r4) throws java.lang.SecurityException, java.lang.NoSuchMethodException {
        /*
            java.lang.reflect.Method r0 = r2.getMethod(r3, r4)
            java.lang.Class r1 = r0.getDeclaringClass()
            int r1 = r1.getModifiers()
            boolean r1 = java.lang.reflect.Modifier.isPublic(r1)
            if (r1 == 0) goto L13
            return r0
        L13:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.List r1 = getAllInterfaces(r2)
            r0.addAll(r1)
            java.util.List r2 = getAllSuperclasses(r2)
            r0.addAll(r2)
            java.util.Iterator r2 = r0.iterator()
        L2a:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L54
            java.lang.Object r0 = r2.next()
            java.lang.Class r0 = (java.lang.Class) r0
            int r1 = r0.getModifiers()
            boolean r1 = java.lang.reflect.Modifier.isPublic(r1)
            if (r1 != 0) goto L41
            goto L2a
        L41:
            java.lang.reflect.Method r0 = r0.getMethod(r3, r4)     // Catch: java.lang.NoSuchMethodException -> L2a
            java.lang.Class r1 = r0.getDeclaringClass()
            int r1 = r1.getModifiers()
            boolean r1 = java.lang.reflect.Modifier.isPublic(r1)
            if (r1 == 0) goto L2a
            return r0
        L54:
            java.lang.NoSuchMethodException r2 = new java.lang.NoSuchMethodException
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = "Can't find a public method for "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r3 = " "
            r0.append(r3)
            java.lang.String r3 = org.apache.commons.lang.ArrayUtils.toString(r4)
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.ClassUtils.getPublicMethod(java.lang.Class, java.lang.String, java.lang.Class[]):java.lang.reflect.Method");
    }

    public static String getShortCanonicalName(Object obj, String str) {
        return obj == null ? str : getShortCanonicalName(obj.getClass().getName());
    }

    public static String getShortClassName(Object obj, String str) {
        return obj == null ? str : getShortClassName(obj.getClass());
    }

    public static boolean isAssignable(Class[] clsArr, Class[] clsArr2) {
        return isAssignable(clsArr, clsArr2, false);
    }

    public static boolean isInnerClass(Class cls) {
        return cls != null && cls.getName().indexOf(36) >= 0;
    }

    public static Class primitiveToWrapper(Class cls) {
        return (cls == null || !cls.isPrimitive()) ? cls : (Class) primitiveWrapperMap.get(cls);
    }

    public static Class[] primitivesToWrappers(Class[] clsArr) {
        if (clsArr == null) {
            return null;
        }
        if (clsArr.length == 0) {
            return clsArr;
        }
        Class[] clsArr2 = new Class[clsArr.length];
        for (int i = 0; i < clsArr.length; i++) {
            clsArr2[i] = primitiveToWrapper(clsArr[i]);
        }
        return clsArr2;
    }

    private static String toCanonicalName(String str) {
        String deleteWhitespace = StringUtils.deleteWhitespace(str);
        if (deleteWhitespace != null) {
            if (!deleteWhitespace.endsWith("[]")) {
                return deleteWhitespace;
            }
            StrBuilder strBuilder = new StrBuilder();
            while (deleteWhitespace.endsWith("[]")) {
                deleteWhitespace = deleteWhitespace.substring(0, deleteWhitespace.length() - 2);
                strBuilder.append("[");
            }
            String str2 = (String) abbreviationMap.get(deleteWhitespace);
            if (str2 != null) {
                strBuilder.append(str2);
            } else {
                strBuilder.append("L").append(deleteWhitespace).append(";");
            }
            return strBuilder.toString();
        }
        throw new NullArgumentException("className");
    }

    public static Class[] toClass(Object[] objArr) {
        if (objArr == null) {
            return null;
        }
        if (objArr.length == 0) {
            return ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        Class[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            clsArr[i] = objArr[i] == null ? null : objArr[i].getClass();
        }
        return clsArr;
    }

    public static Class wrapperToPrimitive(Class cls) {
        return (Class) wrapperPrimitiveMap.get(cls);
    }

    public static Class[] wrappersToPrimitives(Class[] clsArr) {
        if (clsArr == null) {
            return null;
        }
        if (clsArr.length == 0) {
            return clsArr;
        }
        Class[] clsArr2 = new Class[clsArr.length];
        for (int i = 0; i < clsArr.length; i++) {
            clsArr2[i] = wrapperToPrimitive(clsArr[i]);
        }
        return clsArr2;
    }

    public static String getPackageCanonicalName(Class cls) {
        return cls == null ? "" : getPackageCanonicalName(cls.getName());
    }

    public static String getPackageName(Class cls) {
        return cls == null ? "" : getPackageName(cls.getName());
    }

    public static String getShortCanonicalName(Class cls) {
        return cls == null ? "" : getShortCanonicalName(cls.getName());
    }

    public static String getShortClassName(Class cls) {
        return cls == null ? "" : getShortClassName(cls.getName());
    }

    public static boolean isAssignable(Class[] clsArr, Class[] clsArr2, boolean z) {
        if (!ArrayUtils.isSameLength(clsArr, clsArr2)) {
            return false;
        }
        if (clsArr == null) {
            clsArr = ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        if (clsArr2 == null) {
            clsArr2 = ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        for (int i = 0; i < clsArr.length; i++) {
            if (!isAssignable(clsArr[i], clsArr2[i], z)) {
                return false;
            }
        }
        return true;
    }

    private static void getAllInterfaces(Class cls, List list) {
        while (cls != null) {
            Class<?>[] interfaces = cls.getInterfaces();
            for (int i = 0; i < interfaces.length; i++) {
                if (!list.contains(interfaces[i])) {
                    list.add(interfaces[i]);
                    getAllInterfaces(interfaces[i], list);
                }
            }
            cls = cls.getSuperclass();
        }
    }

    public static String getPackageCanonicalName(String str) {
        return getPackageName(getCanonicalName(str));
    }

    public static String getPackageName(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        while (str.charAt(0) == '[') {
            str = str.substring(1);
        }
        if (str.charAt(0) == 'L' && str.charAt(str.length() - 1) == ';') {
            str = str.substring(1);
        }
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf == -1 ? "" : str.substring(0, lastIndexOf);
    }

    public static String getShortCanonicalName(String str) {
        return getShortClassName(getCanonicalName(str));
    }

    public static String getShortClassName(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        StrBuilder strBuilder = new StrBuilder();
        int i = 0;
        if (str.startsWith("[")) {
            while (str.charAt(0) == '[') {
                str = str.substring(1);
                strBuilder.append("[]");
            }
            if (str.charAt(0) == 'L' && str.charAt(str.length() - 1) == ';') {
                str = GeneratedOutlineSupport1.outline51(str, 1, 1);
            }
        }
        if (reverseAbbreviationMap.containsKey(str)) {
            str = (String) reverseAbbreviationMap.get(str);
        }
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf != -1) {
            i = lastIndexOf + 1;
        }
        int indexOf = str.indexOf(36, i);
        String substring = str.substring(lastIndexOf + 1);
        if (indexOf != -1) {
            substring = substring.replace('$', '.');
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(substring);
        stringBuffer.append(strBuilder);
        return stringBuffer.toString();
    }

    public static boolean isAssignable(Class cls, Class cls2) {
        return isAssignable(cls, cls2, false);
    }

    public static Class getClass(ClassLoader classLoader, String str) throws ClassNotFoundException {
        return getClass(classLoader, str, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean isAssignable(Class cls, Class cls2, boolean z) {
        if (cls2 == 0) {
            return false;
        }
        if (cls == null) {
            return !cls2.isPrimitive();
        }
        if (z) {
            if (cls.isPrimitive() && !cls2.isPrimitive() && (cls = primitiveToWrapper(cls)) == null) {
                return false;
            }
            if (cls2.isPrimitive() && !cls.isPrimitive() && (cls = wrapperToPrimitive(cls)) == null) {
                return false;
            }
        }
        if (cls.equals(cls2)) {
            return true;
        }
        if (cls.isPrimitive()) {
            if (!cls2.isPrimitive()) {
                return false;
            }
            if (Integer.TYPE.equals(cls)) {
                return Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
            } else if (Long.TYPE.equals(cls)) {
                return Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
            } else if (Boolean.TYPE.equals(cls) || Double.TYPE.equals(cls)) {
                return false;
            } else {
                if (Float.TYPE.equals(cls)) {
                    return Double.TYPE.equals(cls2);
                }
                if (Character.TYPE.equals(cls)) {
                    return Integer.TYPE.equals(cls2) || Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
                } else if (Short.TYPE.equals(cls)) {
                    return Integer.TYPE.equals(cls2) || Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
                } else if (!Byte.TYPE.equals(cls)) {
                    return false;
                } else {
                    return Short.TYPE.equals(cls2) || Integer.TYPE.equals(cls2) || Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
                }
            }
        }
        return cls2.isAssignableFrom(cls);
    }

    public static Class getClass(String str) throws ClassNotFoundException {
        return getClass(str, true);
    }

    public static Class getClass(String str, boolean z) throws ClassNotFoundException {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader == null) {
            Class cls = class$org$apache$commons$lang$ClassUtils;
            if (cls == null) {
                cls = class$("org.apache.commons.lang.ClassUtils");
                class$org$apache$commons$lang$ClassUtils = cls;
            }
            contextClassLoader = cls.getClassLoader();
        }
        return getClass(contextClassLoader, str, z);
    }
}
