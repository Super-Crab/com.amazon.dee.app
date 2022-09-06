package com.amazon.org.codehaus.jackson.map.deser;

import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.map.AnnotationIntrospector;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.ContextualDeserializer;
import com.amazon.org.codehaus.jackson.map.DeserializationConfig;
import com.amazon.org.codehaus.jackson.map.DeserializerFactory;
import com.amazon.org.codehaus.jackson.map.DeserializerProvider;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.KeyDeserializer;
import com.amazon.org.codehaus.jackson.map.TypeDeserializer;
import com.amazon.org.codehaus.jackson.map.deser.std.AtomicReferenceDeserializer;
import com.amazon.org.codehaus.jackson.map.deser.std.EnumMapDeserializer;
import com.amazon.org.codehaus.jackson.map.deser.std.EnumSetDeserializer;
import com.amazon.org.codehaus.jackson.map.deser.std.ObjectArrayDeserializer;
import com.amazon.org.codehaus.jackson.map.deser.std.PrimitiveArrayDeserializers;
import com.amazon.org.codehaus.jackson.map.deser.std.StringCollectionDeserializer;
import com.amazon.org.codehaus.jackson.map.ext.OptionalHandlerFactory;
import com.amazon.org.codehaus.jackson.map.introspect.Annotated;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedClass;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.amazon.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import com.amazon.org.codehaus.jackson.map.jsontype.NamedType;
import com.amazon.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import com.amazon.org.codehaus.jackson.map.type.ArrayType;
import com.amazon.org.codehaus.jackson.map.type.ClassKey;
import com.amazon.org.codehaus.jackson.map.type.CollectionLikeType;
import com.amazon.org.codehaus.jackson.map.type.CollectionType;
import com.amazon.org.codehaus.jackson.map.type.MapLikeType;
import com.amazon.org.codehaus.jackson.map.type.MapType;
import com.amazon.org.codehaus.jackson.map.type.TypeFactory;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes13.dex */
public abstract class BasicDeserializerFactory extends DeserializerFactory {
    protected static final HashMap<JavaType, JsonDeserializer<Object>> _arrayDeserializers;
    static final HashMap<String, Class<? extends Collection>> _collectionFallbacks;
    protected OptionalHandlerFactory optionalHandlers = OptionalHandlerFactory.instance;
    static final HashMap<ClassKey, JsonDeserializer<Object>> _simpleDeserializers = StdDeserializers.constructAll();
    static final HashMap<JavaType, KeyDeserializer> _keyDeserializers = com.amazon.org.codehaus.jackson.map.deser.std.StdKeyDeserializers.constructAll();
    static final HashMap<String, Class<? extends Map>> _mapFallbacks = new HashMap<>();

    /* JADX WARN: Multi-variable type inference failed */
    static {
        _mapFallbacks.put(Map.class.getName(), LinkedHashMap.class);
        _mapFallbacks.put(ConcurrentMap.class.getName(), ConcurrentHashMap.class);
        _mapFallbacks.put(SortedMap.class.getName(), TreeMap.class);
        _mapFallbacks.put("java.util.NavigableMap", TreeMap.class);
        try {
            _mapFallbacks.put(Class.forName("java.util.ConcurrentNavigableMap").getName(), Class.forName("java.util.ConcurrentSkipListMap"));
        } catch (ClassNotFoundException unused) {
        }
        _collectionFallbacks = new HashMap<>();
        _collectionFallbacks.put(Collection.class.getName(), ArrayList.class);
        _collectionFallbacks.put(List.class.getName(), ArrayList.class);
        _collectionFallbacks.put(Set.class.getName(), HashSet.class);
        _collectionFallbacks.put(SortedSet.class.getName(), TreeSet.class);
        _collectionFallbacks.put(Queue.class.getName(), LinkedList.class);
        _collectionFallbacks.put("java.util.Deque", LinkedList.class);
        _collectionFallbacks.put("java.util.NavigableSet", TreeSet.class);
        _arrayDeserializers = PrimitiveArrayDeserializers.getAll();
    }

    JsonDeserializer<Object> _constructDeserializer(DeserializationConfig deserializationConfig, Annotated annotated, BeanProperty beanProperty, Object obj) throws JsonMappingException {
        if (obj instanceof JsonDeserializer) {
            JsonDeserializer<Object> jsonDeserializer = (JsonDeserializer) obj;
            return jsonDeserializer instanceof ContextualDeserializer ? ((ContextualDeserializer) jsonDeserializer).createContextual(deserializationConfig, beanProperty) : jsonDeserializer;
        } else if (obj instanceof Class) {
            Class<? extends JsonDeserializer<?>> cls = (Class) obj;
            if (JsonDeserializer.class.isAssignableFrom(cls)) {
                JsonDeserializer<Object> deserializerInstance = deserializationConfig.deserializerInstance(annotated, cls);
                return deserializerInstance instanceof ContextualDeserializer ? ((ContextualDeserializer) deserializerInstance).createContextual(deserializationConfig, beanProperty) : deserializerInstance;
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("AnnotationIntrospector returned Class "), "; expected Class<JsonDeserializer>"));
        } else {
            throw new IllegalStateException(GeneratedOutlineSupport1.outline46(obj, GeneratedOutlineSupport1.outline107("AnnotationIntrospector returned deserializer definition of type "), "; expected type JsonDeserializer or Class<JsonDeserializer> instead"));
        }
    }

    protected abstract JsonDeserializer<?> _findCustomArrayDeserializer(ArrayType arrayType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException;

    protected abstract JsonDeserializer<?> _findCustomCollectionDeserializer(CollectionType collectionType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException;

    protected abstract JsonDeserializer<?> _findCustomCollectionLikeDeserializer(CollectionLikeType collectionLikeType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException;

    protected abstract JsonDeserializer<?> _findCustomEnumDeserializer(Class<?> cls, DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty) throws JsonMappingException;

    protected abstract JsonDeserializer<?> _findCustomMapDeserializer(MapType mapType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException;

    protected abstract JsonDeserializer<?> _findCustomMapLikeDeserializer(MapLikeType mapLikeType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException;

    protected abstract JsonDeserializer<?> _findCustomTreeNodeDeserializer(Class<? extends JsonNode> cls, DeserializationConfig deserializationConfig, BeanProperty beanProperty) throws JsonMappingException;

    /* JADX INFO: Access modifiers changed from: protected */
    public com.amazon.org.codehaus.jackson.map.util.EnumResolver<?> constructEnumResolver(Class<?> cls, DeserializationConfig deserializationConfig) {
        if (deserializationConfig.isEnabled(DeserializationConfig.Feature.READ_ENUMS_USING_TO_STRING)) {
            return com.amazon.org.codehaus.jackson.map.util.EnumResolver.constructUnsafeUsingToString(cls);
        }
        return com.amazon.org.codehaus.jackson.map.util.EnumResolver.constructUnsafe(cls, deserializationConfig.getAnnotationIntrospector());
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer<?> createArrayDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, ArrayType arrayType, BeanProperty beanProperty) throws JsonMappingException {
        JavaType contentType = arrayType.getContentType();
        JsonDeserializer<Object> jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        if (jsonDeserializer == null) {
            JsonDeserializer<?> jsonDeserializer2 = _arrayDeserializers.get(contentType);
            if (jsonDeserializer2 != null) {
                JsonDeserializer<?> _findCustomArrayDeserializer = _findCustomArrayDeserializer(arrayType, deserializationConfig, deserializerProvider, beanProperty, null, null);
                return _findCustomArrayDeserializer != null ? _findCustomArrayDeserializer : jsonDeserializer2;
            } else if (contentType.isPrimitive()) {
                throw new IllegalArgumentException("Internal error: primitive type (" + arrayType + ") passed, no array deserializer found");
            }
        }
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        if (typeDeserializer == null) {
            typeDeserializer = findTypeDeserializer(deserializationConfig, contentType, beanProperty);
        }
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        JsonDeserializer<?> _findCustomArrayDeserializer2 = _findCustomArrayDeserializer(arrayType, deserializationConfig, deserializerProvider, beanProperty, typeDeserializer2, jsonDeserializer);
        if (_findCustomArrayDeserializer2 != null) {
            return _findCustomArrayDeserializer2;
        }
        if (jsonDeserializer == null) {
            jsonDeserializer = deserializerProvider.findValueDeserializer(deserializationConfig, contentType, beanProperty);
        }
        return new ObjectArrayDeserializer(arrayType, jsonDeserializer, typeDeserializer2);
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer<?> createCollectionDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, CollectionType collectionType, BeanProperty beanProperty) throws JsonMappingException {
        BasicBeanDescription basicBeanDescription;
        CollectionType collectionType2 = (CollectionType) mapAbstractType(deserializationConfig, collectionType);
        Class<?> rawClass = collectionType2.getRawClass();
        BasicBeanDescription basicBeanDescription2 = (BasicBeanDescription) deserializationConfig.introspectForCreation(collectionType2);
        JsonDeserializer<?> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription2.getClassInfo(), beanProperty);
        if (findDeserializerFromAnnotation != null) {
            return findDeserializerFromAnnotation;
        }
        CollectionType collectionType3 = (CollectionType) modifyTypeByAnnotation(deserializationConfig, basicBeanDescription2.getClassInfo(), collectionType2, null);
        JavaType contentType = collectionType3.getContentType();
        JsonDeserializer<Object> jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        if (typeDeserializer == null) {
            typeDeserializer = findTypeDeserializer(deserializationConfig, contentType, beanProperty);
        }
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        JsonDeserializer<?> _findCustomCollectionDeserializer = _findCustomCollectionDeserializer(collectionType3, deserializationConfig, deserializerProvider, basicBeanDescription2, beanProperty, typeDeserializer2, jsonDeserializer);
        if (_findCustomCollectionDeserializer != null) {
            return _findCustomCollectionDeserializer;
        }
        if (jsonDeserializer == null) {
            if (EnumSet.class.isAssignableFrom(rawClass)) {
                return new EnumSetDeserializer(contentType.getRawClass(), createEnumDeserializer(deserializationConfig, deserializerProvider, contentType, beanProperty));
            }
            jsonDeserializer = deserializerProvider.findValueDeserializer(deserializationConfig, contentType, beanProperty);
        }
        JsonDeserializer<Object> jsonDeserializer2 = jsonDeserializer;
        if (collectionType3.isInterface() || collectionType3.isAbstract()) {
            Class<? extends Collection> cls = _collectionFallbacks.get(rawClass.getName());
            if (cls != null) {
                collectionType3 = (CollectionType) deserializationConfig.constructSpecializedType(collectionType3, cls);
                basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectForCreation(collectionType3);
            } else {
                throw new IllegalArgumentException("Can not find a deserializer for non-concrete Collection type " + collectionType3);
            }
        } else {
            basicBeanDescription = basicBeanDescription2;
        }
        ValueInstantiator findValueInstantiator = findValueInstantiator(deserializationConfig, basicBeanDescription);
        if (contentType.getRawClass() == String.class) {
            return new StringCollectionDeserializer(collectionType3, jsonDeserializer2, findValueInstantiator);
        }
        return new com.amazon.org.codehaus.jackson.map.deser.std.CollectionDeserializer(collectionType3, jsonDeserializer2, typeDeserializer2, findValueInstantiator);
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer<?> createCollectionLikeDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, CollectionLikeType collectionLikeType, BeanProperty beanProperty) throws JsonMappingException {
        CollectionLikeType collectionLikeType2 = (CollectionLikeType) mapAbstractType(deserializationConfig, collectionLikeType);
        BasicBeanDescription basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectClassAnnotations(collectionLikeType2.getRawClass());
        JsonDeserializer<?> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (findDeserializerFromAnnotation != null) {
            return findDeserializerFromAnnotation;
        }
        CollectionLikeType collectionLikeType3 = (CollectionLikeType) modifyTypeByAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), collectionLikeType2, null);
        JavaType contentType = collectionLikeType3.getContentType();
        JsonDeserializer<?> jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        return _findCustomCollectionLikeDeserializer(collectionLikeType3, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty, typeDeserializer == null ? findTypeDeserializer(deserializationConfig, contentType, beanProperty) : typeDeserializer, jsonDeserializer);
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer<?> createEnumDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        BasicBeanDescription basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectForCreation(javaType);
        JsonDeserializer<?> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (findDeserializerFromAnnotation != null) {
            return findDeserializerFromAnnotation;
        }
        Class<?> rawClass = javaType.getRawClass();
        JsonDeserializer<?> _findCustomEnumDeserializer = _findCustomEnumDeserializer(rawClass, deserializationConfig, basicBeanDescription, beanProperty);
        if (_findCustomEnumDeserializer != null) {
            return _findCustomEnumDeserializer;
        }
        for (AnnotatedMethod annotatedMethod : basicBeanDescription.getFactoryMethods()) {
            if (deserializationConfig.getAnnotationIntrospector().hasCreatorAnnotation(annotatedMethod)) {
                if (annotatedMethod.getParameterCount() == 1 && annotatedMethod.getRawType().isAssignableFrom(rawClass)) {
                    return com.amazon.org.codehaus.jackson.map.deser.std.EnumDeserializer.deserializerForCreator(deserializationConfig, rawClass, annotatedMethod);
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Unsuitable method (");
                sb.append(annotatedMethod);
                sb.append(") decorated with @JsonCreator (for Enum type ");
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline40(rawClass, sb, ")"));
            }
        }
        return new com.amazon.org.codehaus.jackson.map.deser.std.EnumDeserializer(constructEnumResolver(rawClass, deserializationConfig));
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer<?> createMapDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, MapType mapType, BeanProperty beanProperty) throws JsonMappingException {
        BasicBeanDescription basicBeanDescription;
        MapType mapType2 = (MapType) mapAbstractType(deserializationConfig, mapType);
        BasicBeanDescription basicBeanDescription2 = (BasicBeanDescription) deserializationConfig.introspectForCreation(mapType2);
        JsonDeserializer<?> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription2.getClassInfo(), beanProperty);
        if (findDeserializerFromAnnotation != null) {
            return findDeserializerFromAnnotation;
        }
        MapType mapType3 = (MapType) modifyTypeByAnnotation(deserializationConfig, basicBeanDescription2.getClassInfo(), mapType2, null);
        JavaType keyType = mapType3.getKeyType();
        JavaType contentType = mapType3.getContentType();
        JsonDeserializer<Object> jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        KeyDeserializer keyDeserializer = (KeyDeserializer) keyType.getValueHandler();
        if (keyDeserializer == null) {
            keyDeserializer = deserializerProvider.findKeyDeserializer(deserializationConfig, keyType, beanProperty);
        }
        KeyDeserializer keyDeserializer2 = keyDeserializer;
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        if (typeDeserializer == null) {
            typeDeserializer = findTypeDeserializer(deserializationConfig, contentType, beanProperty);
        }
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        JsonDeserializer<?> _findCustomMapDeserializer = _findCustomMapDeserializer(mapType3, deserializationConfig, deserializerProvider, basicBeanDescription2, beanProperty, keyDeserializer2, typeDeserializer2, jsonDeserializer);
        if (_findCustomMapDeserializer != null) {
            return _findCustomMapDeserializer;
        }
        if (jsonDeserializer == null) {
            jsonDeserializer = deserializerProvider.findValueDeserializer(deserializationConfig, contentType, beanProperty);
        }
        JsonDeserializer<Object> jsonDeserializer2 = jsonDeserializer;
        Class<?> rawClass = mapType3.getRawClass();
        if (EnumMap.class.isAssignableFrom(rawClass)) {
            Class<?> rawClass2 = keyType.getRawClass();
            if (rawClass2 != null && rawClass2.isEnum()) {
                return new EnumMapDeserializer(keyType.getRawClass(), createEnumDeserializer(deserializationConfig, deserializerProvider, keyType, beanProperty), jsonDeserializer2);
            }
            throw new IllegalArgumentException("Can not construct EnumMap; generic (key) type not available");
        }
        if (mapType3.isInterface() || mapType3.isAbstract()) {
            Class<? extends Map> cls = _mapFallbacks.get(rawClass.getName());
            if (cls != null) {
                mapType3 = (MapType) deserializationConfig.constructSpecializedType(mapType3, cls);
                basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectForCreation(mapType3);
            } else {
                throw new IllegalArgumentException("Can not find a deserializer for non-concrete Map type " + mapType3);
            }
        } else {
            basicBeanDescription = basicBeanDescription2;
        }
        com.amazon.org.codehaus.jackson.map.deser.std.MapDeserializer mapDeserializer = new com.amazon.org.codehaus.jackson.map.deser.std.MapDeserializer(mapType3, findValueInstantiator(deserializationConfig, basicBeanDescription), keyDeserializer2, jsonDeserializer2, typeDeserializer2);
        mapDeserializer.setIgnorableProperties(deserializationConfig.getAnnotationIntrospector().findPropertiesToIgnore(basicBeanDescription.getClassInfo()));
        return mapDeserializer;
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer<?> createMapLikeDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, MapLikeType mapLikeType, BeanProperty beanProperty) throws JsonMappingException {
        MapLikeType mapLikeType2 = (MapLikeType) mapAbstractType(deserializationConfig, mapLikeType);
        BasicBeanDescription basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectForCreation(mapLikeType2);
        JsonDeserializer<?> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (findDeserializerFromAnnotation != null) {
            return findDeserializerFromAnnotation;
        }
        MapLikeType mapLikeType3 = (MapLikeType) modifyTypeByAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), mapLikeType2, null);
        JavaType keyType = mapLikeType3.getKeyType();
        JavaType contentType = mapLikeType3.getContentType();
        JsonDeserializer<?> jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        KeyDeserializer keyDeserializer = (KeyDeserializer) keyType.getValueHandler();
        KeyDeserializer findKeyDeserializer = keyDeserializer == null ? deserializerProvider.findKeyDeserializer(deserializationConfig, keyType, beanProperty) : keyDeserializer;
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        if (typeDeserializer == null) {
            typeDeserializer = findTypeDeserializer(deserializationConfig, contentType, beanProperty);
        }
        return _findCustomMapLikeDeserializer(mapLikeType3, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty, findKeyDeserializer, typeDeserializer, jsonDeserializer);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer<?> createTreeDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        Class<?> rawClass = javaType.getRawClass();
        JsonDeserializer<?> _findCustomTreeNodeDeserializer = _findCustomTreeNodeDeserializer(rawClass, deserializationConfig, beanProperty);
        return _findCustomTreeNodeDeserializer != null ? _findCustomTreeNodeDeserializer : com.amazon.org.codehaus.jackson.map.deser.std.JsonNodeDeserializer.getDeserializer(rawClass);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonDeserializer<Object> findDeserializerFromAnnotation(DeserializationConfig deserializationConfig, Annotated annotated, BeanProperty beanProperty) throws JsonMappingException {
        Object mo4217findDeserializer = deserializationConfig.getAnnotationIntrospector().mo4217findDeserializer(annotated);
        if (mo4217findDeserializer != null) {
            return _constructDeserializer(deserializationConfig, annotated, beanProperty, mo4217findDeserializer);
        }
        return null;
    }

    public TypeDeserializer findPropertyContentTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, AnnotatedMember annotatedMember, BeanProperty beanProperty) throws JsonMappingException {
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder<?> findPropertyContentTypeResolver = annotationIntrospector.findPropertyContentTypeResolver(deserializationConfig, annotatedMember, javaType);
        JavaType contentType = javaType.getContentType();
        if (findPropertyContentTypeResolver == null) {
            return findTypeDeserializer(deserializationConfig, contentType, beanProperty);
        }
        return findPropertyContentTypeResolver.buildTypeDeserializer(deserializationConfig, contentType, deserializationConfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember, deserializationConfig, annotationIntrospector), beanProperty);
    }

    public TypeDeserializer findPropertyTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, AnnotatedMember annotatedMember, BeanProperty beanProperty) throws JsonMappingException {
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder<?> findPropertyTypeResolver = annotationIntrospector.findPropertyTypeResolver(deserializationConfig, annotatedMember, javaType);
        if (findPropertyTypeResolver == null) {
            return findTypeDeserializer(deserializationConfig, javaType, beanProperty);
        }
        return findPropertyTypeResolver.buildTypeDeserializer(deserializationConfig, javaType, deserializationConfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember, deserializationConfig, annotationIntrospector), beanProperty);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonDeserializer<Object> findStdBeanDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        JavaType unknownType;
        Class<?> rawClass = javaType.getRawClass();
        JsonDeserializer<Object> jsonDeserializer = _simpleDeserializers.get(new ClassKey(rawClass));
        if (jsonDeserializer != null) {
            return jsonDeserializer;
        }
        if (AtomicReference.class.isAssignableFrom(rawClass)) {
            JavaType[] findTypeParameters = deserializationConfig.getTypeFactory().findTypeParameters(javaType, AtomicReference.class);
            if (findTypeParameters != null && findTypeParameters.length >= 1) {
                unknownType = findTypeParameters[0];
            } else {
                unknownType = TypeFactory.unknownType();
            }
            return new AtomicReferenceDeserializer(unknownType, beanProperty);
        }
        JsonDeserializer<?> findDeserializer = this.optionalHandlers.findDeserializer(javaType, deserializationConfig, deserializerProvider);
        if (findDeserializer == null) {
            return null;
        }
        return findDeserializer;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory
    public TypeDeserializer findTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        AnnotatedClass classInfo = ((BasicBeanDescription) deserializationConfig.introspectClassAnnotations(javaType.getRawClass())).getClassInfo();
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder<?> findTypeResolver = annotationIntrospector.findTypeResolver(deserializationConfig, classInfo, javaType);
        Collection<NamedType> collection = null;
        if (findTypeResolver == null) {
            findTypeResolver = deserializationConfig.getDefaultTyper(javaType);
            if (findTypeResolver == null) {
                return null;
            }
        } else {
            collection = deserializationConfig.getSubtypeResolver().collectAndResolveSubtypes(classInfo, deserializationConfig, annotationIntrospector);
        }
        Class<?> defaultImpl = findTypeResolver.getDefaultImpl();
        TypeResolverBuilder<?> typeResolverBuilder = findTypeResolver;
        if (defaultImpl == null) {
            typeResolverBuilder = findTypeResolver;
            if (javaType.isAbstract()) {
                JavaType mapAbstractType = mapAbstractType(deserializationConfig, javaType);
                typeResolverBuilder = findTypeResolver;
                if (mapAbstractType != null) {
                    typeResolverBuilder = findTypeResolver;
                    if (mapAbstractType.getRawClass() != javaType.getRawClass()) {
                        typeResolverBuilder = findTypeResolver.defaultImpl(mapAbstractType.getRawClass());
                    }
                }
            }
        }
        return typeResolverBuilder.buildTypeDeserializer(deserializationConfig, javaType, collection, beanProperty);
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory
    public abstract ValueInstantiator findValueInstantiator(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription) throws JsonMappingException;

    @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory
    public abstract JavaType mapAbstractType(DeserializationConfig deserializationConfig, JavaType javaType) throws JsonMappingException;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v26 */
    public <T extends JavaType> T modifyTypeByAnnotation(DeserializationConfig deserializationConfig, Annotated annotated, T t, String str) throws JsonMappingException {
        Class<? extends KeyDeserializer> findKeyDeserializer;
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        Class<?> findDeserializationType = annotationIntrospector.findDeserializationType(annotated, t, str);
        if (findDeserializationType != null) {
            try {
                t = (T) t.narrowBy(findDeserializationType);
            } catch (IllegalArgumentException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to narrow type ");
                sb.append(t);
                sb.append(" with concrete-type annotation (value ");
                sb.append(findDeserializationType.getName());
                sb.append("), method '");
                sb.append(annotated.getName());
                sb.append("': ");
                throw new JsonMappingException(GeneratedOutlineSupport1.outline43(e, sb), null, e);
            }
        }
        boolean isContainerType = t.isContainerType();
        JavaType javaType = t;
        if (isContainerType) {
            Class<?> findDeserializationKeyType = annotationIntrospector.findDeserializationKeyType(annotated, t.getKeyType(), str);
            T t2 = t;
            if (findDeserializationKeyType != null) {
                if (t instanceof MapLikeType) {
                    try {
                        t2 = (T) t.narrowKey(findDeserializationKeyType);
                    } catch (IllegalArgumentException e2) {
                        throw new JsonMappingException("Failed to narrow key type " + t + " with key-type annotation (" + findDeserializationKeyType.getName() + "): " + e2.getMessage(), null, e2);
                    }
                } else {
                    throw new JsonMappingException("Illegal key-type annotation: type " + t + " is not a Map(-like) type");
                }
            }
            JavaType keyType = t2.getKeyType();
            if (keyType != null && keyType.getValueHandler() == null && (findKeyDeserializer = annotationIntrospector.findKeyDeserializer(annotated)) != null && findKeyDeserializer != KeyDeserializer.None.class) {
                keyType.setValueHandler(deserializationConfig.keyDeserializerInstance(annotated, findKeyDeserializer));
            }
            Class<?> findDeserializationContentType = annotationIntrospector.findDeserializationContentType(annotated, t2.getContentType(), str);
            T t3 = t2;
            if (findDeserializationContentType != null) {
                try {
                    t3 = t2.narrowContentsBy(findDeserializationContentType);
                } catch (IllegalArgumentException e3) {
                    throw new JsonMappingException("Failed to narrow content type " + t2 + " with content-type annotation (" + findDeserializationContentType.getName() + "): " + e3.getMessage(), null, e3);
                }
            }
            Object valueHandler = t3.getContentType().getValueHandler();
            javaType = t3;
            if (valueHandler == null) {
                Class<? extends JsonDeserializer<?>> findContentDeserializer = annotationIntrospector.findContentDeserializer(annotated);
                javaType = t3;
                if (findContentDeserializer != null) {
                    javaType = t3;
                    if (findContentDeserializer != JsonDeserializer.None.class) {
                        t3.getContentType().setValueHandler(deserializationConfig.deserializerInstance(annotated, findContentDeserializer));
                        javaType = t3;
                    }
                }
            }
        }
        return (T) javaType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JavaType resolveType(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, JavaType javaType, AnnotatedMember annotatedMember, BeanProperty beanProperty) throws JsonMappingException {
        TypeDeserializer findTypeDeserializer;
        TypeDeserializer findPropertyContentTypeDeserializer;
        Class<? extends KeyDeserializer> findKeyDeserializer;
        if (javaType.isContainerType()) {
            AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
            JavaType keyType = javaType.getKeyType();
            if (keyType != null && (findKeyDeserializer = annotationIntrospector.findKeyDeserializer(annotatedMember)) != null && findKeyDeserializer != KeyDeserializer.None.class) {
                keyType.setValueHandler(deserializationConfig.keyDeserializerInstance(annotatedMember, findKeyDeserializer));
            }
            Class<? extends JsonDeserializer<?>> findContentDeserializer = annotationIntrospector.findContentDeserializer(annotatedMember);
            if (findContentDeserializer != null && findContentDeserializer != JsonDeserializer.None.class) {
                javaType.getContentType().setValueHandler(deserializationConfig.deserializerInstance(annotatedMember, findContentDeserializer));
            }
            if ((annotatedMember instanceof AnnotatedMember) && (findPropertyContentTypeDeserializer = findPropertyContentTypeDeserializer(deserializationConfig, javaType, annotatedMember, beanProperty)) != null) {
                javaType = javaType.mo4242withContentTypeHandler(findPropertyContentTypeDeserializer);
            }
        }
        if (annotatedMember instanceof AnnotatedMember) {
            findTypeDeserializer = findPropertyTypeDeserializer(deserializationConfig, javaType, annotatedMember, beanProperty);
        } else {
            findTypeDeserializer = findTypeDeserializer(deserializationConfig, javaType, null);
        }
        return findTypeDeserializer != null ? javaType.mo4249withTypeHandler(findTypeDeserializer) : javaType;
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory
    public abstract DeserializerFactory withConfig(DeserializerFactory.Config config);
}
