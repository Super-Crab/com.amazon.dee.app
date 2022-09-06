package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.introspect.DefaultAccessorNamingStrategy;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
/* loaded from: classes2.dex */
public class BasicClassIntrospector extends ClassIntrospector implements Serializable {
    private static final long serialVersionUID = 2;
    private static final Class<?> CLS_OBJECT = Object.class;
    private static final Class<?> CLS_STRING = String.class;
    private static final Class<?> CLS_JSON_NODE = JsonNode.class;
    protected static final BasicBeanDescription STRING_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(String.class), AnnotatedClassResolver.createPrimordial(CLS_STRING));
    protected static final BasicBeanDescription BOOLEAN_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(Boolean.TYPE), AnnotatedClassResolver.createPrimordial(Boolean.TYPE));
    protected static final BasicBeanDescription INT_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(Integer.TYPE), AnnotatedClassResolver.createPrimordial(Integer.TYPE));
    protected static final BasicBeanDescription LONG_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(Long.TYPE), AnnotatedClassResolver.createPrimordial(Long.TYPE));
    protected static final BasicBeanDescription OBJECT_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(Object.class), AnnotatedClassResolver.createPrimordial(CLS_OBJECT));

    protected BasicBeanDescription _findStdJdkCollectionDesc(MapperConfig<?> mapperConfig, JavaType javaType) {
        if (_isStdJDKCollection(javaType)) {
            return BasicBeanDescription.forOtherUse(mapperConfig, javaType, _resolveAnnotatedClass(mapperConfig, javaType, mapperConfig));
        }
        return null;
    }

    protected BasicBeanDescription _findStdTypeDesc(MapperConfig<?> mapperConfig, JavaType javaType) {
        Class<?> rawClass = javaType.getRawClass();
        if (rawClass.isPrimitive()) {
            if (rawClass == Integer.TYPE) {
                return INT_DESC;
            }
            if (rawClass == Long.TYPE) {
                return LONG_DESC;
            }
            if (rawClass != Boolean.TYPE) {
                return null;
            }
            return BOOLEAN_DESC;
        } else if (ClassUtil.isJDKClass(rawClass)) {
            if (rawClass == CLS_OBJECT) {
                return OBJECT_DESC;
            }
            if (rawClass == CLS_STRING) {
                return STRING_DESC;
            }
            if (rawClass == Integer.class) {
                return INT_DESC;
            }
            if (rawClass == Long.class) {
                return LONG_DESC;
            }
            if (rawClass != Boolean.class) {
                return null;
            }
            return BOOLEAN_DESC;
        } else if (!CLS_JSON_NODE.isAssignableFrom(rawClass)) {
            return null;
        } else {
            return BasicBeanDescription.forOtherUse(mapperConfig, javaType, AnnotatedClassResolver.createPrimordial(rawClass));
        }
    }

    protected boolean _isStdJDKCollection(JavaType javaType) {
        if (javaType.isContainerType() && !javaType.isArrayType()) {
            Class<?> rawClass = javaType.getRawClass();
            if (ClassUtil.isJDKClass(rawClass) && (Collection.class.isAssignableFrom(rawClass) || Map.class.isAssignableFrom(rawClass))) {
                return true;
            }
        }
        return false;
    }

    protected AnnotatedClass _resolveAnnotatedClass(MapperConfig<?> mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        return AnnotatedClassResolver.resolve(mapperConfig, javaType, mixInResolver);
    }

    protected AnnotatedClass _resolveAnnotatedWithoutSuperTypes(MapperConfig<?> mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        return AnnotatedClassResolver.resolveWithoutSuperTypes(mapperConfig, javaType, mixInResolver);
    }

    protected POJOPropertiesCollector collectProperties(MapperConfig<?> mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver, boolean z) {
        AccessorNamingStrategy forPOJO;
        AnnotatedClass _resolveAnnotatedClass = _resolveAnnotatedClass(mapperConfig, javaType, mixInResolver);
        if (javaType.isRecordType()) {
            forPOJO = mapperConfig.getAccessorNaming().forRecord(mapperConfig, _resolveAnnotatedClass);
        } else {
            forPOJO = mapperConfig.getAccessorNaming().forPOJO(mapperConfig, _resolveAnnotatedClass);
        }
        return constructPropertyCollector(mapperConfig, _resolveAnnotatedClass, javaType, z, forPOJO);
    }

    protected POJOPropertiesCollector collectPropertiesWithBuilder(MapperConfig<?> mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver, BeanDescription beanDescription, boolean z) {
        AnnotatedClass _resolveAnnotatedClass = _resolveAnnotatedClass(mapperConfig, javaType, mixInResolver);
        return constructPropertyCollector(mapperConfig, _resolveAnnotatedClass, javaType, z, mapperConfig.getAccessorNaming().forBuilder(mapperConfig, _resolveAnnotatedClass, beanDescription));
    }

    protected POJOPropertiesCollector constructPropertyCollector(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, JavaType javaType, boolean z, AccessorNamingStrategy accessorNamingStrategy) {
        return new POJOPropertiesCollector(mapperConfig, z, javaType, annotatedClass, accessorNamingStrategy);
    }

    @Override // com.fasterxml.jackson.databind.introspect.ClassIntrospector
    public ClassIntrospector copy() {
        return new BasicClassIntrospector();
    }

    @Override // com.fasterxml.jackson.databind.introspect.ClassIntrospector
    /* renamed from: forClassAnnotations */
    public /* bridge */ /* synthetic */ BeanDescription mo7121forClassAnnotations(MapperConfig mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        return mo7121forClassAnnotations((MapperConfig<?>) mapperConfig, javaType, mixInResolver);
    }

    @Override // com.fasterxml.jackson.databind.introspect.ClassIntrospector
    /* renamed from: forDirectClassAnnotations */
    public /* bridge */ /* synthetic */ BeanDescription mo7126forDirectClassAnnotations(MapperConfig mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        return mo7126forDirectClassAnnotations((MapperConfig<?>) mapperConfig, javaType, mixInResolver);
    }

    @Deprecated
    protected POJOPropertiesCollector constructPropertyCollector(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, JavaType javaType, boolean z, String str) {
        return new POJOPropertiesCollector(mapperConfig, z, javaType, annotatedClass, str);
    }

    @Override // com.fasterxml.jackson.databind.introspect.ClassIntrospector
    /* renamed from: forClassAnnotations  reason: collision with other method in class */
    public BasicBeanDescription mo7121forClassAnnotations(MapperConfig<?> mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        BasicBeanDescription _findStdTypeDesc = _findStdTypeDesc(mapperConfig, javaType);
        return _findStdTypeDesc == null ? BasicBeanDescription.forOtherUse(mapperConfig, javaType, _resolveAnnotatedClass(mapperConfig, javaType, mixInResolver)) : _findStdTypeDesc;
    }

    @Override // com.fasterxml.jackson.databind.introspect.ClassIntrospector
    /* renamed from: forCreation  reason: collision with other method in class */
    public BasicBeanDescription mo7122forCreation(DeserializationConfig deserializationConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        BasicBeanDescription _findStdTypeDesc = _findStdTypeDesc(deserializationConfig, javaType);
        if (_findStdTypeDesc == null) {
            BasicBeanDescription _findStdJdkCollectionDesc = _findStdJdkCollectionDesc(deserializationConfig, javaType);
            return _findStdJdkCollectionDesc == null ? BasicBeanDescription.forDeserialization(collectProperties(deserializationConfig, javaType, mixInResolver, false)) : _findStdJdkCollectionDesc;
        }
        return _findStdTypeDesc;
    }

    @Override // com.fasterxml.jackson.databind.introspect.ClassIntrospector
    /* renamed from: forDeserialization  reason: collision with other method in class */
    public BasicBeanDescription mo7123forDeserialization(DeserializationConfig deserializationConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        BasicBeanDescription _findStdTypeDesc = _findStdTypeDesc(deserializationConfig, javaType);
        if (_findStdTypeDesc == null) {
            BasicBeanDescription _findStdJdkCollectionDesc = _findStdJdkCollectionDesc(deserializationConfig, javaType);
            return _findStdJdkCollectionDesc == null ? BasicBeanDescription.forDeserialization(collectProperties(deserializationConfig, javaType, mixInResolver, false)) : _findStdJdkCollectionDesc;
        }
        return _findStdTypeDesc;
    }

    @Override // com.fasterxml.jackson.databind.introspect.ClassIntrospector
    /* renamed from: forDirectClassAnnotations  reason: collision with other method in class */
    public BasicBeanDescription mo7126forDirectClassAnnotations(MapperConfig<?> mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        BasicBeanDescription _findStdTypeDesc = _findStdTypeDesc(mapperConfig, javaType);
        return _findStdTypeDesc == null ? BasicBeanDescription.forOtherUse(mapperConfig, javaType, _resolveAnnotatedWithoutSuperTypes(mapperConfig, javaType, mixInResolver)) : _findStdTypeDesc;
    }

    @Override // com.fasterxml.jackson.databind.introspect.ClassIntrospector
    /* renamed from: forSerialization  reason: collision with other method in class */
    public BasicBeanDescription mo7127forSerialization(SerializationConfig serializationConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        BasicBeanDescription _findStdTypeDesc = _findStdTypeDesc(serializationConfig, javaType);
        if (_findStdTypeDesc == null) {
            BasicBeanDescription _findStdJdkCollectionDesc = _findStdJdkCollectionDesc(serializationConfig, javaType);
            return _findStdJdkCollectionDesc == null ? BasicBeanDescription.forSerialization(collectProperties(serializationConfig, javaType, mixInResolver, true)) : _findStdJdkCollectionDesc;
        }
        return _findStdTypeDesc;
    }

    @Override // com.fasterxml.jackson.databind.introspect.ClassIntrospector
    /* renamed from: forDeserializationWithBuilder  reason: collision with other method in class */
    public BasicBeanDescription mo7125forDeserializationWithBuilder(DeserializationConfig deserializationConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver, BeanDescription beanDescription) {
        return BasicBeanDescription.forDeserialization(collectPropertiesWithBuilder(deserializationConfig, javaType, mixInResolver, beanDescription, false));
    }

    @Override // com.fasterxml.jackson.databind.introspect.ClassIntrospector
    @Deprecated
    /* renamed from: forDeserializationWithBuilder  reason: collision with other method in class */
    public BasicBeanDescription mo7124forDeserializationWithBuilder(DeserializationConfig deserializationConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        return BasicBeanDescription.forDeserialization(collectPropertiesWithBuilder(deserializationConfig, javaType, mixInResolver, null, false));
    }

    @Deprecated
    protected POJOPropertiesCollector collectPropertiesWithBuilder(MapperConfig<?> mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver, boolean z) {
        return collectPropertiesWithBuilder(mapperConfig, javaType, mixInResolver, null, z);
    }

    @Deprecated
    protected POJOPropertiesCollector collectProperties(MapperConfig<?> mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver, boolean z, String str) {
        AnnotatedClass _resolveAnnotatedClass = _resolveAnnotatedClass(mapperConfig, javaType, mixInResolver);
        return constructPropertyCollector(mapperConfig, _resolveAnnotatedClass, javaType, z, new DefaultAccessorNamingStrategy.Provider().withSetterPrefix(str).forPOJO(mapperConfig, _resolveAnnotatedClass));
    }
}
