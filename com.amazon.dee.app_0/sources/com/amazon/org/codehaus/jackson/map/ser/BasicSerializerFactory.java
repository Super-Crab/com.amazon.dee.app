package com.amazon.org.codehaus.jackson.map.ser;

import com.amazon.org.codehaus.jackson.map.AnnotationIntrospector;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.ContextualSerializer;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.JsonSerializable;
import com.amazon.org.codehaus.jackson.map.JsonSerializableWithType;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.SerializationConfig;
import com.amazon.org.codehaus.jackson.map.SerializerFactory;
import com.amazon.org.codehaus.jackson.map.Serializers;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import com.amazon.org.codehaus.jackson.map.annotate.JsonSerialize;
import com.amazon.org.codehaus.jackson.map.ext.OptionalHandlerFactory;
import com.amazon.org.codehaus.jackson.map.introspect.Annotated;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedClass;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.amazon.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import com.amazon.org.codehaus.jackson.map.jsontype.NamedType;
import com.amazon.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import com.amazon.org.codehaus.jackson.map.ser.StdSerializers;
import com.amazon.org.codehaus.jackson.map.ser.std.CalendarSerializer;
import com.amazon.org.codehaus.jackson.map.ser.std.DateSerializer;
import com.amazon.org.codehaus.jackson.map.ser.std.EnumMapSerializer;
import com.amazon.org.codehaus.jackson.map.ser.std.IndexedStringListSerializer;
import com.amazon.org.codehaus.jackson.map.ser.std.InetAddressSerializer;
import com.amazon.org.codehaus.jackson.map.ser.std.JsonValueSerializer;
import com.amazon.org.codehaus.jackson.map.ser.std.NullSerializer;
import com.amazon.org.codehaus.jackson.map.ser.std.ObjectArraySerializer;
import com.amazon.org.codehaus.jackson.map.ser.std.SerializableSerializer;
import com.amazon.org.codehaus.jackson.map.ser.std.SerializableWithTypeSerializer;
import com.amazon.org.codehaus.jackson.map.ser.std.StdArraySerializers;
import com.amazon.org.codehaus.jackson.map.ser.std.StdContainerSerializers;
import com.amazon.org.codehaus.jackson.map.ser.std.StdJdkSerializers;
import com.amazon.org.codehaus.jackson.map.ser.std.StringCollectionSerializer;
import com.amazon.org.codehaus.jackson.map.ser.std.StringSerializer;
import com.amazon.org.codehaus.jackson.map.ser.std.TimeZoneSerializer;
import com.amazon.org.codehaus.jackson.map.ser.std.TokenBufferSerializer;
import com.amazon.org.codehaus.jackson.map.type.ArrayType;
import com.amazon.org.codehaus.jackson.map.type.CollectionLikeType;
import com.amazon.org.codehaus.jackson.map.type.CollectionType;
import com.amazon.org.codehaus.jackson.map.type.MapLikeType;
import com.amazon.org.codehaus.jackson.map.type.MapType;
import com.amazon.org.codehaus.jackson.map.type.TypeFactory;
import com.amazon.org.codehaus.jackson.map.util.ClassUtil;
import com.amazon.org.codehaus.jackson.map.util.EnumValues;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.amazon.org.codehaus.jackson.util.TokenBuffer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.RandomAccess;
import java.util.TimeZone;
/* loaded from: classes13.dex */
public abstract class BasicSerializerFactory extends SerializerFactory {
    protected static final HashMap<String, JsonSerializer<?>> _arraySerializers;
    protected static final HashMap<String, JsonSerializer<?>> _concrete = new HashMap<>();
    protected static final HashMap<String, Class<? extends JsonSerializer<?>>> _concreteLazy = new HashMap<>();
    protected OptionalHandlerFactory optionalHandlers = OptionalHandlerFactory.instance;

    static {
        _concrete.put(String.class.getName(), new StringSerializer());
        com.amazon.org.codehaus.jackson.map.ser.std.ToStringSerializer toStringSerializer = com.amazon.org.codehaus.jackson.map.ser.std.ToStringSerializer.instance;
        _concrete.put(StringBuffer.class.getName(), toStringSerializer);
        _concrete.put(StringBuilder.class.getName(), toStringSerializer);
        _concrete.put(Character.class.getName(), toStringSerializer);
        _concrete.put(Character.TYPE.getName(), toStringSerializer);
        _concrete.put(Boolean.TYPE.getName(), new StdSerializers.BooleanSerializer(true));
        _concrete.put(Boolean.class.getName(), new StdSerializers.BooleanSerializer(false));
        StdSerializers.IntegerSerializer integerSerializer = new StdSerializers.IntegerSerializer();
        _concrete.put(Integer.class.getName(), integerSerializer);
        _concrete.put(Integer.TYPE.getName(), integerSerializer);
        _concrete.put(Long.class.getName(), StdSerializers.LongSerializer.instance);
        _concrete.put(Long.TYPE.getName(), StdSerializers.LongSerializer.instance);
        _concrete.put(Byte.class.getName(), StdSerializers.IntLikeSerializer.instance);
        _concrete.put(Byte.TYPE.getName(), StdSerializers.IntLikeSerializer.instance);
        _concrete.put(Short.class.getName(), StdSerializers.IntLikeSerializer.instance);
        _concrete.put(Short.TYPE.getName(), StdSerializers.IntLikeSerializer.instance);
        _concrete.put(Float.class.getName(), StdSerializers.FloatSerializer.instance);
        _concrete.put(Float.TYPE.getName(), StdSerializers.FloatSerializer.instance);
        _concrete.put(Double.class.getName(), StdSerializers.DoubleSerializer.instance);
        _concrete.put(Double.TYPE.getName(), StdSerializers.DoubleSerializer.instance);
        StdSerializers.NumberSerializer numberSerializer = new StdSerializers.NumberSerializer();
        _concrete.put(BigInteger.class.getName(), numberSerializer);
        _concrete.put(BigDecimal.class.getName(), numberSerializer);
        _concrete.put(Calendar.class.getName(), CalendarSerializer.instance);
        DateSerializer dateSerializer = DateSerializer.instance;
        _concrete.put(Date.class.getName(), dateSerializer);
        _concrete.put(Timestamp.class.getName(), dateSerializer);
        _concrete.put(java.sql.Date.class.getName(), new StdSerializers.SqlDateSerializer());
        _concrete.put(Time.class.getName(), new StdSerializers.SqlTimeSerializer());
        for (Map.Entry<Class<?>, Object> entry : new StdJdkSerializers().provide()) {
            Object value = entry.getValue();
            if (value instanceof JsonSerializer) {
                _concrete.put(entry.getKey().getName(), (JsonSerializer) value);
            } else if (value instanceof Class) {
                _concreteLazy.put(entry.getKey().getName(), (Class) value);
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Internal error: unrecognized value of type ");
                outline107.append(entry.getClass().getName());
                throw new IllegalStateException(outline107.toString());
            }
        }
        _concreteLazy.put(TokenBuffer.class.getName(), TokenBufferSerializer.class);
        _arraySerializers = new HashMap<>();
        _arraySerializers.put(boolean[].class.getName(), new StdArraySerializers.BooleanArraySerializer());
        _arraySerializers.put(byte[].class.getName(), new StdArraySerializers.ByteArraySerializer());
        _arraySerializers.put(char[].class.getName(), new StdArraySerializers.CharArraySerializer());
        _arraySerializers.put(short[].class.getName(), new StdArraySerializers.ShortArraySerializer());
        _arraySerializers.put(int[].class.getName(), new StdArraySerializers.IntArraySerializer());
        _arraySerializers.put(long[].class.getName(), new StdArraySerializers.LongArraySerializer());
        _arraySerializers.put(float[].class.getName(), new StdArraySerializers.FloatArraySerializer());
        _arraySerializers.put(double[].class.getName(), new StdArraySerializers.DoubleArraySerializer());
    }

    protected static JsonSerializer<Object> findContentSerializer(SerializationConfig serializationConfig, Annotated annotated, BeanProperty beanProperty) {
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        Class<? extends JsonSerializer<?>> findContentSerializer = annotationIntrospector.findContentSerializer(annotated);
        if ((findContentSerializer == null || findContentSerializer == JsonSerializer.None.class) && beanProperty != null) {
            findContentSerializer = annotationIntrospector.findContentSerializer(beanProperty.getMember());
        }
        if (findContentSerializer == null || findContentSerializer == JsonSerializer.None.class) {
            return null;
        }
        return serializationConfig.serializerInstance(annotated, findContentSerializer);
    }

    protected static JsonSerializer<Object> findKeySerializer(SerializationConfig serializationConfig, Annotated annotated, BeanProperty beanProperty) {
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        Class<? extends JsonSerializer<?>> findKeySerializer = annotationIntrospector.findKeySerializer(annotated);
        if ((findKeySerializer == null || findKeySerializer == JsonSerializer.None.class) && beanProperty != null) {
            findKeySerializer = annotationIntrospector.findKeySerializer(beanProperty.getMember());
        }
        if (findKeySerializer == null || findKeySerializer == JsonSerializer.None.class) {
            return null;
        }
        return serializationConfig.serializerInstance(annotated, findKeySerializer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <T extends JavaType> T modifySecondaryTypesByAnnotation(SerializationConfig serializationConfig, Annotated annotated, T t) {
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        if (t.isContainerType()) {
            Class<?> findSerializationKeyType = annotationIntrospector.findSerializationKeyType(annotated, t.getKeyType());
            if (findSerializationKeyType != null) {
                if (t instanceof MapType) {
                    try {
                        t = (T) ((MapType) t).widenKey(findSerializationKeyType);
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("Failed to narrow key type " + t + " with key-type annotation (" + findSerializationKeyType.getName() + "): " + e.getMessage());
                    }
                } else {
                    throw new IllegalArgumentException("Illegal key-type annotation: type " + t + " is not a Map type");
                }
            }
            Class<?> findSerializationContentType = annotationIntrospector.findSerializationContentType(annotated, t.getContentType());
            if (findSerializationContentType == null) {
                return t;
            }
            try {
                return (T) t.widenContentsBy(findSerializationContentType);
            } catch (IllegalArgumentException e2) {
                throw new IllegalArgumentException("Failed to narrow content type " + t + " with content-type annotation (" + findSerializationContentType.getName() + "): " + e2.getMessage());
            }
        }
        return t;
    }

    protected JsonSerializer<?> buildArraySerializer(SerializationConfig serializationConfig, ArrayType arrayType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        Class<?> rawClass = arrayType.getRawClass();
        if (String[].class == rawClass) {
            return new StdArraySerializers.StringArraySerializer(beanProperty);
        }
        JsonSerializer<?> jsonSerializer2 = _arraySerializers.get(rawClass.getName());
        return jsonSerializer2 != null ? jsonSerializer2 : new ObjectArraySerializer(arrayType.getContentType(), z, typeSerializer, beanProperty, jsonSerializer);
    }

    protected JsonSerializer<?> buildCollectionLikeSerializer(SerializationConfig serializationConfig, CollectionLikeType collectionLikeType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        for (Serializers serializers : customSerializers()) {
            JsonSerializer<?> findCollectionLikeSerializer = serializers.findCollectionLikeSerializer(serializationConfig, collectionLikeType, basicBeanDescription, beanProperty, typeSerializer, jsonSerializer);
            if (findCollectionLikeSerializer != null) {
                return findCollectionLikeSerializer;
            }
        }
        return null;
    }

    protected JsonSerializer<?> buildCollectionSerializer(SerializationConfig serializationConfig, CollectionType collectionType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        for (Serializers serializers : customSerializers()) {
            JsonSerializer<?> findCollectionSerializer = serializers.findCollectionSerializer(serializationConfig, collectionType, basicBeanDescription, beanProperty, typeSerializer, jsonSerializer);
            if (findCollectionSerializer != null) {
                return findCollectionSerializer;
            }
        }
        Class<?> rawClass = collectionType.getRawClass();
        if (EnumSet.class.isAssignableFrom(rawClass)) {
            return buildEnumSetSerializer(serializationConfig, collectionType, basicBeanDescription, beanProperty, z, typeSerializer, jsonSerializer);
        }
        Class<?> rawClass2 = collectionType.getContentType().getRawClass();
        if (isIndexedList(rawClass)) {
            if (rawClass2 == String.class) {
                return new IndexedStringListSerializer(beanProperty, jsonSerializer);
            }
            return StdContainerSerializers.indexedListSerializer(collectionType.getContentType(), z, typeSerializer, beanProperty, jsonSerializer);
        } else if (rawClass2 == String.class) {
            return new StringCollectionSerializer(beanProperty, jsonSerializer);
        } else {
            return StdContainerSerializers.collectionSerializer(collectionType.getContentType(), z, typeSerializer, beanProperty, jsonSerializer);
        }
    }

    public JsonSerializer<?> buildContainerSerializer(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z) {
        TypeSerializer createTypeSerializer = createTypeSerializer(serializationConfig, javaType.getContentType(), beanProperty);
        if (createTypeSerializer != null) {
            z = false;
        } else if (!z) {
            z = usesStaticTyping(serializationConfig, basicBeanDescription, createTypeSerializer, beanProperty);
        }
        boolean z2 = z;
        JsonSerializer<Object> findContentSerializer = findContentSerializer(serializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (javaType.isMapLikeType()) {
            MapLikeType mapLikeType = (MapLikeType) javaType;
            JsonSerializer<Object> findKeySerializer = findKeySerializer(serializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
            if (mapLikeType.isTrueMapType()) {
                return buildMapSerializer(serializationConfig, (MapType) mapLikeType, basicBeanDescription, beanProperty, z2, findKeySerializer, createTypeSerializer, findContentSerializer);
            }
            return buildMapLikeSerializer(serializationConfig, mapLikeType, basicBeanDescription, beanProperty, z2, findKeySerializer, createTypeSerializer, findContentSerializer);
        } else if (javaType.isCollectionLikeType()) {
            CollectionLikeType collectionLikeType = (CollectionLikeType) javaType;
            if (collectionLikeType.isTrueCollectionType()) {
                return buildCollectionSerializer(serializationConfig, (CollectionType) collectionLikeType, basicBeanDescription, beanProperty, z2, createTypeSerializer, findContentSerializer);
            }
            return buildCollectionLikeSerializer(serializationConfig, collectionLikeType, basicBeanDescription, beanProperty, z2, createTypeSerializer, findContentSerializer);
        } else if (!javaType.isArrayType()) {
            return null;
        } else {
            return buildArraySerializer(serializationConfig, (ArrayType) javaType, basicBeanDescription, beanProperty, z2, createTypeSerializer, findContentSerializer);
        }
    }

    protected JsonSerializer<?> buildEnumMapSerializer(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        JavaType keyType = javaType.getKeyType();
        return new EnumMapSerializer(javaType.getContentType(), z, keyType.isEnumType() ? EnumValues.construct(keyType.getRawClass(), serializationConfig.getAnnotationIntrospector()) : null, typeSerializer, beanProperty, jsonSerializer);
    }

    protected JsonSerializer<?> buildEnumSetSerializer(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        JavaType contentType = javaType.getContentType();
        if (!contentType.isEnumType()) {
            contentType = null;
        }
        return StdContainerSerializers.enumSetSerializer(contentType, beanProperty);
    }

    protected JsonSerializer<?> buildIterableSerializer(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z) {
        JavaType containedType = javaType.containedType(0);
        if (containedType == null) {
            containedType = TypeFactory.unknownType();
        }
        TypeSerializer createTypeSerializer = createTypeSerializer(serializationConfig, containedType, beanProperty);
        return StdContainerSerializers.iterableSerializer(containedType, usesStaticTyping(serializationConfig, basicBeanDescription, createTypeSerializer, beanProperty), createTypeSerializer, beanProperty);
    }

    protected JsonSerializer<?> buildIteratorSerializer(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z) {
        JavaType containedType = javaType.containedType(0);
        if (containedType == null) {
            containedType = TypeFactory.unknownType();
        }
        TypeSerializer createTypeSerializer = createTypeSerializer(serializationConfig, containedType, beanProperty);
        return StdContainerSerializers.iteratorSerializer(containedType, usesStaticTyping(serializationConfig, basicBeanDescription, createTypeSerializer, beanProperty), createTypeSerializer, beanProperty);
    }

    protected JsonSerializer<?> buildMapLikeSerializer(SerializationConfig serializationConfig, MapLikeType mapLikeType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer2) {
        for (Serializers serializers : customSerializers()) {
            JsonSerializer<?> findMapLikeSerializer = serializers.findMapLikeSerializer(serializationConfig, mapLikeType, basicBeanDescription, beanProperty, jsonSerializer, typeSerializer, jsonSerializer2);
            if (findMapLikeSerializer != null) {
                return findMapLikeSerializer;
            }
        }
        return null;
    }

    protected JsonSerializer<?> buildMapSerializer(SerializationConfig serializationConfig, MapType mapType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer2) {
        for (Serializers serializers : customSerializers()) {
            JsonSerializer<?> findMapSerializer = serializers.findMapSerializer(serializationConfig, mapType, basicBeanDescription, beanProperty, jsonSerializer, typeSerializer, jsonSerializer2);
            if (findMapSerializer != null) {
                return findMapSerializer;
            }
        }
        if (EnumMap.class.isAssignableFrom(mapType.getRawClass())) {
            return buildEnumMapSerializer(serializationConfig, mapType, basicBeanDescription, beanProperty, z, typeSerializer, jsonSerializer2);
        }
        return com.amazon.org.codehaus.jackson.map.ser.std.MapSerializer.construct(serializationConfig.getAnnotationIntrospector().findPropertiesToIgnore(basicBeanDescription.getClassInfo()), mapType, z, typeSerializer, beanProperty, jsonSerializer, jsonSerializer2);
    }

    @Override // com.amazon.org.codehaus.jackson.map.SerializerFactory
    public abstract JsonSerializer<Object> createSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException;

    @Override // com.amazon.org.codehaus.jackson.map.SerializerFactory
    public TypeSerializer createTypeSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanProperty beanProperty) {
        Collection<NamedType> collectAndResolveSubtypes;
        AnnotatedClass classInfo = ((BasicBeanDescription) serializationConfig.introspectClassAnnotations(javaType.getRawClass())).getClassInfo();
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder<?> findTypeResolver = annotationIntrospector.findTypeResolver(serializationConfig, classInfo, javaType);
        if (findTypeResolver == null) {
            findTypeResolver = serializationConfig.getDefaultTyper(javaType);
            collectAndResolveSubtypes = null;
        } else {
            collectAndResolveSubtypes = serializationConfig.getSubtypeResolver().collectAndResolveSubtypes(classInfo, serializationConfig, annotationIntrospector);
        }
        if (findTypeResolver == null) {
            return null;
        }
        return findTypeResolver.buildTypeSerializer(serializationConfig, javaType, collectAndResolveSubtypes, beanProperty);
    }

    protected abstract Iterable<Serializers> customSerializers();

    public final JsonSerializer<?> findSerializerByAddonType(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z) throws JsonMappingException {
        Class<?> rawClass = javaType.getRawClass();
        if (Iterator.class.isAssignableFrom(rawClass)) {
            return buildIteratorSerializer(serializationConfig, javaType, basicBeanDescription, beanProperty, z);
        }
        if (Iterable.class.isAssignableFrom(rawClass)) {
            return buildIterableSerializer(serializationConfig, javaType, basicBeanDescription, beanProperty, z);
        }
        if (!CharSequence.class.isAssignableFrom(rawClass)) {
            return null;
        }
        return com.amazon.org.codehaus.jackson.map.ser.std.ToStringSerializer.instance;
    }

    public final JsonSerializer<?> findSerializerByLookup(JavaType javaType, SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z) {
        String name = javaType.getRawClass().getName();
        JsonSerializer<?> jsonSerializer = _concrete.get(name);
        if (jsonSerializer != null) {
            return jsonSerializer;
        }
        Class<? extends JsonSerializer<?>> cls = _concreteLazy.get(name);
        if (cls == null) {
            return null;
        }
        try {
            return cls.newInstance();
        } catch (Exception e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to instantiate standard serializer (of type ");
            outline107.append(cls.getName());
            outline107.append("): ");
            outline107.append(e.getMessage());
            throw new IllegalStateException(outline107.toString(), e);
        }
    }

    public final JsonSerializer<?> findSerializerByPrimaryType(JavaType javaType, SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z) throws JsonMappingException {
        Class<?> rawClass = javaType.getRawClass();
        if (JsonSerializable.class.isAssignableFrom(rawClass)) {
            if (JsonSerializableWithType.class.isAssignableFrom(rawClass)) {
                return SerializableWithTypeSerializer.instance;
            }
            return SerializableSerializer.instance;
        }
        AnnotatedMethod findJsonValueMethod = basicBeanDescription.findJsonValueMethod();
        if (findJsonValueMethod != null) {
            Method mo4213getAnnotated = findJsonValueMethod.mo4213getAnnotated();
            if (serializationConfig.isEnabled(SerializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                ClassUtil.checkAndFixAccess(mo4213getAnnotated);
            }
            return new JsonValueSerializer(mo4213getAnnotated, findSerializerFromAnnotation(serializationConfig, findJsonValueMethod, beanProperty), beanProperty);
        } else if (InetAddress.class.isAssignableFrom(rawClass)) {
            return InetAddressSerializer.instance;
        } else {
            if (TimeZone.class.isAssignableFrom(rawClass)) {
                return TimeZoneSerializer.instance;
            }
            if (Charset.class.isAssignableFrom(rawClass)) {
                return com.amazon.org.codehaus.jackson.map.ser.std.ToStringSerializer.instance;
            }
            JsonSerializer<?> findSerializer = this.optionalHandlers.findSerializer(serializationConfig, javaType);
            if (findSerializer != null) {
                return findSerializer;
            }
            if (Number.class.isAssignableFrom(rawClass)) {
                return StdSerializers.NumberSerializer.instance;
            }
            if (Enum.class.isAssignableFrom(rawClass)) {
                return com.amazon.org.codehaus.jackson.map.ser.std.EnumSerializer.construct(rawClass, serializationConfig, basicBeanDescription);
            }
            if (Calendar.class.isAssignableFrom(rawClass)) {
                return CalendarSerializer.instance;
            }
            if (!Date.class.isAssignableFrom(rawClass)) {
                return null;
            }
            return DateSerializer.instance;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonSerializer<Object> findSerializerFromAnnotation(SerializationConfig serializationConfig, Annotated annotated, BeanProperty beanProperty) throws JsonMappingException {
        Object findSerializer = serializationConfig.getAnnotationIntrospector().findSerializer(annotated);
        if (findSerializer == null) {
            return null;
        }
        if (findSerializer instanceof JsonSerializer) {
            JsonSerializer<Object> jsonSerializer = (JsonSerializer) findSerializer;
            return jsonSerializer instanceof ContextualSerializer ? ((ContextualSerializer) jsonSerializer).createContextual(serializationConfig, beanProperty) : jsonSerializer;
        } else if (findSerializer instanceof Class) {
            Class<? extends JsonSerializer<?>> cls = (Class) findSerializer;
            if (JsonSerializer.class.isAssignableFrom(cls)) {
                JsonSerializer<Object> serializerInstance = serializationConfig.serializerInstance(annotated, cls);
                return serializerInstance instanceof ContextualSerializer ? ((ContextualSerializer) serializerInstance).createContextual(serializationConfig, beanProperty) : serializerInstance;
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("AnnotationIntrospector returned Class "), "; expected Class<JsonSerializer>"));
        } else {
            throw new IllegalStateException(GeneratedOutlineSupport1.outline46(findSerializer, GeneratedOutlineSupport1.outline107("AnnotationIntrospector returned value of type "), "; expected type JsonSerializer or Class<JsonSerializer> instead"));
        }
    }

    public final JsonSerializer<?> getNullSerializer() {
        return NullSerializer.instance;
    }

    protected boolean isIndexedList(Class<?> cls) {
        return RandomAccess.class.isAssignableFrom(cls);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <T extends JavaType> T modifyTypeByAnnotation(SerializationConfig serializationConfig, Annotated annotated, T t) {
        Class<?> findSerializationType = serializationConfig.getAnnotationIntrospector().findSerializationType(annotated);
        if (findSerializationType != null) {
            try {
                t = (T) t.widenBy(findSerializationType);
            } catch (IllegalArgumentException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to widen type ");
                sb.append(t);
                sb.append(" with concrete-type annotation (value ");
                sb.append(findSerializationType.getName());
                sb.append("), method '");
                sb.append(annotated.getName());
                sb.append("': ");
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline43(e, sb));
            }
        }
        return (T) modifySecondaryTypesByAnnotation(serializationConfig, annotated, t);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean usesStaticTyping(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, TypeSerializer typeSerializer, BeanProperty beanProperty) {
        if (typeSerializer != null) {
            return false;
        }
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        JsonSerialize.Typing findSerializationTyping = annotationIntrospector.findSerializationTyping(basicBeanDescription.getClassInfo());
        if (findSerializationTyping != null) {
            if (findSerializationTyping == JsonSerialize.Typing.STATIC) {
                return true;
            }
        } else if (serializationConfig.isEnabled(SerializationConfig.Feature.USE_STATIC_TYPING)) {
            return true;
        }
        if (beanProperty != null) {
            JavaType type = beanProperty.getType();
            if (type.isContainerType()) {
                if (annotationIntrospector.findSerializationContentType(beanProperty.getMember(), beanProperty.getType()) != null) {
                    return true;
                }
                if ((type instanceof MapType) && annotationIntrospector.findSerializationKeyType(beanProperty.getMember(), beanProperty.getType()) != null) {
                    return true;
                }
            }
        }
        return false;
    }
}
