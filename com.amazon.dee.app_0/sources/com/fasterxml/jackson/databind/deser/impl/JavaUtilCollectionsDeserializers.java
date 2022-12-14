package com.fasterxml.jackson.databind.deser.impl;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes2.dex */
public abstract class JavaUtilCollectionsDeserializers {
    private static final Class<?> CLASS_AS_ARRAYS_LIST = Arrays.asList(null, null).getClass();
    private static final Class<?> CLASS_SINGLETON_LIST;
    private static final Class<?> CLASS_SINGLETON_MAP;
    private static final Class<?> CLASS_SINGLETON_SET;
    private static final Class<?> CLASS_UNMODIFIABLE_LIST;
    private static final Class<?> CLASS_UNMODIFIABLE_LIST_ALIAS;
    private static final Class<?> CLASS_UNMODIFIABLE_MAP;
    private static final Class<?> CLASS_UNMODIFIABLE_SET;
    private static final String PREFIX_JAVA_UTIL_COLLECTIONS = "java.util.Collections$";
    public static final int TYPE_AS_LIST = 11;
    private static final int TYPE_SINGLETON_LIST = 2;
    private static final int TYPE_SINGLETON_MAP = 3;
    private static final int TYPE_SINGLETON_SET = 1;
    private static final int TYPE_SYNC_COLLECTION = 8;
    private static final int TYPE_SYNC_LIST = 9;
    private static final int TYPE_SYNC_MAP = 10;
    private static final int TYPE_SYNC_SET = 7;
    private static final int TYPE_UNMODIFIABLE_LIST = 5;
    private static final int TYPE_UNMODIFIABLE_MAP = 6;
    private static final int TYPE_UNMODIFIABLE_SET = 4;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class JavaUtilCollectionsConverter implements Converter<Object, Object> {
        private final JavaType _inputType;
        private final int _kind;

        JavaUtilCollectionsConverter(int i, JavaType javaType) {
            this._inputType = javaType;
            this._kind = i;
        }

        private void _checkSingleton(int i) {
            if (i == 1) {
                return;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Can not deserialize Singleton container from ", i, " entries"));
        }

        @Override // com.fasterxml.jackson.databind.util.Converter
        public Object convert(Object obj) {
            if (obj == null) {
                return null;
            }
            switch (this._kind) {
                case 1:
                    Set set = (Set) obj;
                    _checkSingleton(set.size());
                    return Collections.singleton(set.iterator().next());
                case 2:
                    List list = (List) obj;
                    _checkSingleton(list.size());
                    return Collections.singletonList(list.get(0));
                case 3:
                    Map map = (Map) obj;
                    _checkSingleton(map.size());
                    Map.Entry entry = (Map.Entry) map.entrySet().iterator().next();
                    return Collections.singletonMap(entry.getKey(), entry.getValue());
                case 4:
                    return Collections.unmodifiableSet((Set) obj);
                case 5:
                    return Collections.unmodifiableList((List) obj);
                case 6:
                    return Collections.unmodifiableMap((Map) obj);
                case 7:
                    return Collections.synchronizedSet((Set) obj);
                case 8:
                    return Collections.synchronizedCollection((Collection) obj);
                case 9:
                    return Collections.synchronizedList((List) obj);
                case 10:
                    return Collections.synchronizedMap((Map) obj);
                default:
                    return obj;
            }
        }

        @Override // com.fasterxml.jackson.databind.util.Converter
        public JavaType getInputType(TypeFactory typeFactory) {
            return this._inputType;
        }

        @Override // com.fasterxml.jackson.databind.util.Converter
        public JavaType getOutputType(TypeFactory typeFactory) {
            return this._inputType;
        }
    }

    static {
        Set singleton = Collections.singleton(Boolean.TRUE);
        CLASS_SINGLETON_SET = singleton.getClass();
        CLASS_UNMODIFIABLE_SET = Collections.unmodifiableSet(singleton).getClass();
        List singletonList = Collections.singletonList(Boolean.TRUE);
        CLASS_SINGLETON_LIST = singletonList.getClass();
        CLASS_UNMODIFIABLE_LIST = Collections.unmodifiableList(singletonList).getClass();
        CLASS_UNMODIFIABLE_LIST_ALIAS = Collections.unmodifiableList(new LinkedList()).getClass();
        Map singletonMap = Collections.singletonMap("a", "b");
        CLASS_SINGLETON_MAP = singletonMap.getClass();
        CLASS_UNMODIFIABLE_MAP = Collections.unmodifiableMap(singletonMap).getClass();
    }

    private static String _findUtilCollectionsTypeName(Class<?> cls) {
        String name = cls.getName();
        return name.startsWith(PREFIX_JAVA_UTIL_COLLECTIONS) ? name.substring(22) : "";
    }

    private static String _findUtilSyncTypeName(Class<?> cls) {
        String _findUtilCollectionsTypeName = _findUtilCollectionsTypeName(cls);
        return (_findUtilCollectionsTypeName == null || !_findUtilCollectionsTypeName.startsWith("Synchronized")) ? "" : _findUtilCollectionsTypeName.substring(12);
    }

    static JavaUtilCollectionsConverter converter(int i, JavaType javaType, Class<?> cls) {
        return new JavaUtilCollectionsConverter(i, javaType.findSuperType(cls));
    }

    public static JsonDeserializer<?> findForCollection(DeserializationContext deserializationContext, JavaType javaType) throws JsonMappingException {
        JavaUtilCollectionsConverter converter;
        if (javaType.hasRawClass(CLASS_AS_ARRAYS_LIST)) {
            converter = converter(11, javaType, List.class);
        } else if (javaType.hasRawClass(CLASS_SINGLETON_LIST)) {
            converter = converter(2, javaType, List.class);
        } else if (javaType.hasRawClass(CLASS_SINGLETON_SET)) {
            converter = converter(1, javaType, Set.class);
        } else if (!javaType.hasRawClass(CLASS_UNMODIFIABLE_LIST) && !javaType.hasRawClass(CLASS_UNMODIFIABLE_LIST_ALIAS)) {
            if (javaType.hasRawClass(CLASS_UNMODIFIABLE_SET)) {
                converter = converter(4, javaType, Set.class);
            } else {
                String _findUtilSyncTypeName = _findUtilSyncTypeName(javaType.getRawClass());
                if (_findUtilSyncTypeName.endsWith("Set")) {
                    converter = converter(7, javaType, Set.class);
                } else if (_findUtilSyncTypeName.endsWith("List")) {
                    converter = converter(9, javaType, List.class);
                } else if (!_findUtilSyncTypeName.endsWith("Collection")) {
                    return null;
                } else {
                    converter = converter(8, javaType, Collection.class);
                }
            }
        } else {
            converter = converter(5, javaType, List.class);
        }
        return new StdDelegatingDeserializer(converter);
    }

    public static JsonDeserializer<?> findForMap(DeserializationContext deserializationContext, JavaType javaType) throws JsonMappingException {
        JavaUtilCollectionsConverter converter;
        if (javaType.hasRawClass(CLASS_SINGLETON_MAP)) {
            converter = converter(3, javaType, Map.class);
        } else if (javaType.hasRawClass(CLASS_UNMODIFIABLE_MAP)) {
            converter = converter(6, javaType, Map.class);
        } else if (!_findUtilSyncTypeName(javaType.getRawClass()).endsWith("Map")) {
            return null;
        } else {
            converter = converter(10, javaType, Map.class);
        }
        return new StdDelegatingDeserializer(converter);
    }
}
