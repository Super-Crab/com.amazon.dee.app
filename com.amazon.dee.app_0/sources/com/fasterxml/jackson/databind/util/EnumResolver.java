package com.fasterxml.jackson.databind.util;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public class EnumResolver implements Serializable {
    private static final long serialVersionUID = 1;
    protected final Enum<?> _defaultValue;
    protected final Class<Enum<?>> _enumClass;
    protected final Enum<?>[] _enums;
    protected final HashMap<String, Enum<?>> _enumsById;
    protected final boolean _isIgnoreCase;

    protected EnumResolver(Class<Enum<?>> cls, Enum<?>[] enumArr, HashMap<String, Enum<?>> hashMap, Enum<?> r4, boolean z) {
        this._enumClass = cls;
        this._enums = enumArr;
        this._enumsById = hashMap;
        this._defaultValue = r4;
        this._isIgnoreCase = z;
    }

    protected static EnumResolver _constructFor(Class<?> cls, AnnotationIntrospector annotationIntrospector, boolean z) {
        Class<Enum<?>> _enumClass = _enumClass(cls);
        Enum<?>[] _enumConstants = _enumConstants(cls);
        String[] findEnumValues = annotationIntrospector.findEnumValues(_enumClass, _enumConstants, new String[_enumConstants.length]);
        String[][] strArr = new String[findEnumValues.length];
        annotationIntrospector.findEnumAliases(_enumClass, _enumConstants, strArr);
        HashMap hashMap = new HashMap();
        int length = _enumConstants.length;
        for (int i = 0; i < length; i++) {
            Enum<?> r7 = _enumConstants[i];
            String str = findEnumValues[i];
            if (str == null) {
                str = r7.name();
            }
            hashMap.put(str, r7);
            String[] strArr2 = strArr[i];
            if (strArr2 != null) {
                for (String str2 : strArr2) {
                    if (!hashMap.containsKey(str2)) {
                        hashMap.put(str2, r7);
                    }
                }
            }
        }
        return new EnumResolver(_enumClass, _enumConstants, hashMap, _enumDefault(annotationIntrospector, _enumClass), z);
    }

    protected static EnumResolver _constructUsingMethod(Class<?> cls, AnnotatedMember annotatedMember, AnnotationIntrospector annotationIntrospector, boolean z) {
        Class<Enum<?>> _enumClass = _enumClass(cls);
        Enum<?>[] _enumConstants = _enumConstants(cls);
        HashMap hashMap = new HashMap();
        int length = _enumConstants.length;
        while (true) {
            length--;
            if (length >= 0) {
                Enum<?> r0 = _enumConstants[length];
                try {
                    Object value = annotatedMember.getValue(r0);
                    if (value != null) {
                        hashMap.put(value.toString(), r0);
                    }
                } catch (Exception e) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to access @JsonValue of Enum value ");
                    sb.append(r0);
                    sb.append(RealTimeTextConstants.COLON_SPACE);
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline41(e, sb));
                }
            } else {
                return new EnumResolver(_enumClass, _enumConstants, hashMap, _enumDefault(annotationIntrospector, _enumClass), z);
            }
        }
    }

    protected static EnumResolver _constructUsingToString(Class<?> cls, AnnotationIntrospector annotationIntrospector, boolean z) {
        Class<Enum<?>> _enumClass = _enumClass(cls);
        Enum<?>[] _enumConstants = _enumConstants(cls);
        HashMap hashMap = new HashMap();
        String[][] strArr = new String[_enumConstants.length];
        annotationIntrospector.findEnumAliases(_enumClass, _enumConstants, strArr);
        int length = _enumConstants.length;
        while (true) {
            length--;
            if (length >= 0) {
                Enum<?> r4 = _enumConstants[length];
                hashMap.put(r4.toString(), r4);
                String[] strArr2 = strArr[length];
                if (strArr2 != null) {
                    for (String str : strArr2) {
                        if (!hashMap.containsKey(str)) {
                            hashMap.put(str, r4);
                        }
                    }
                }
            } else {
                return new EnumResolver(_enumClass, _enumConstants, hashMap, _enumDefault(annotationIntrospector, _enumClass), z);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected static Class<Enum<?>> _enumClass(Class<?> cls) {
        return cls;
    }

    protected static Enum<?>[] _enumConstants(Class<?> cls) {
        Enum<?>[] enumConstants = _enumClass(cls).getEnumConstants();
        if (enumConstants != null) {
            return enumConstants;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline38(cls, GeneratedOutlineSupport1.outline107("No enum constants for class ")));
    }

    protected static Enum<?> _enumDefault(AnnotationIntrospector annotationIntrospector, Class<?> cls) {
        if (annotationIntrospector != null) {
            return annotationIntrospector.findDefaultEnumValue(_enumClass(cls));
        }
        return null;
    }

    public static EnumResolver constructFor(DeserializationConfig deserializationConfig, Class<?> cls) {
        return _constructFor(cls, deserializationConfig.getAnnotationIntrospector(), deserializationConfig.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS));
    }

    @Deprecated
    public static EnumResolver constructUnsafe(Class<?> cls, AnnotationIntrospector annotationIntrospector) {
        return _constructFor(cls, annotationIntrospector, false);
    }

    @Deprecated
    public static EnumResolver constructUnsafeUsingMethod(Class<?> cls, AnnotatedMember annotatedMember, AnnotationIntrospector annotationIntrospector) {
        return _constructUsingMethod(cls, annotatedMember, annotationIntrospector, false);
    }

    @Deprecated
    public static EnumResolver constructUnsafeUsingToString(Class<?> cls, AnnotationIntrospector annotationIntrospector) {
        return _constructUsingToString(cls, annotationIntrospector, false);
    }

    public static EnumResolver constructUsingMethod(DeserializationConfig deserializationConfig, Class<?> cls, AnnotatedMember annotatedMember) {
        return _constructUsingMethod(cls, annotatedMember, deserializationConfig.getAnnotationIntrospector(), deserializationConfig.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS));
    }

    public static EnumResolver constructUsingToString(DeserializationConfig deserializationConfig, Class<?> cls) {
        return _constructUsingToString(cls, deserializationConfig.getAnnotationIntrospector(), deserializationConfig.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS));
    }

    protected Enum<?> _findEnumCaseInsensitive(String str) {
        for (Map.Entry<String, Enum<?>> entry : this._enumsById.entrySet()) {
            if (str.equalsIgnoreCase(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public CompactStringObjectMap constructLookup() {
        return CompactStringObjectMap.construct(this._enumsById);
    }

    public Enum<?> findEnum(String str) {
        Enum<?> r0 = this._enumsById.get(str);
        return (r0 != null || !this._isIgnoreCase) ? r0 : _findEnumCaseInsensitive(str);
    }

    public Enum<?> getDefaultValue() {
        return this._defaultValue;
    }

    public Enum<?> getEnum(int i) {
        if (i >= 0) {
            Enum<?>[] enumArr = this._enums;
            if (i < enumArr.length) {
                return enumArr[i];
            }
            return null;
        }
        return null;
    }

    public Class<Enum<?>> getEnumClass() {
        return this._enumClass;
    }

    public Collection<String> getEnumIds() {
        return this._enumsById.keySet();
    }

    public List<Enum<?>> getEnums() {
        ArrayList arrayList = new ArrayList(this._enums.length);
        for (Enum<?> r4 : this._enums) {
            arrayList.add(r4);
        }
        return arrayList;
    }

    public Enum<?>[] getRawEnums() {
        return this._enums;
    }

    public int lastValidIndex() {
        return this._enums.length - 1;
    }

    @Deprecated
    public static EnumResolver constructFor(Class<Enum<?>> cls, AnnotationIntrospector annotationIntrospector) {
        return _constructFor(cls, annotationIntrospector, false);
    }

    @Deprecated
    public static EnumResolver constructUsingMethod(Class<Enum<?>> cls, AnnotatedMember annotatedMember, AnnotationIntrospector annotationIntrospector) {
        return _constructUsingMethod(cls, annotatedMember, annotationIntrospector, false);
    }

    @Deprecated
    public static EnumResolver constructUsingToString(Class<Enum<?>> cls, AnnotationIntrospector annotationIntrospector) {
        return _constructUsingToString(cls, annotationIntrospector, false);
    }

    @Deprecated
    public static EnumResolver constructUsingToString(Class<Enum<?>> cls) {
        return _constructUsingToString(cls, null, false);
    }

    @Deprecated
    protected EnumResolver(Class<Enum<?>> cls, Enum<?>[] enumArr, HashMap<String, Enum<?>> hashMap, Enum<?> r10) {
        this(cls, enumArr, hashMap, r10, false);
    }
}
