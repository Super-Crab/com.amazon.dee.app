package com.amazon.org.codehaus.jackson.map.introspect;

import com.amazon.org.codehaus.jackson.map.AnnotationIntrospector;
import com.amazon.org.codehaus.jackson.map.ClassIntrospector;
import com.amazon.org.codehaus.jackson.map.DeserializationConfig;
import com.amazon.org.codehaus.jackson.map.MapperConfig;
import com.amazon.org.codehaus.jackson.map.SerializationConfig;
import com.amazon.org.codehaus.jackson.map.type.SimpleType;
import com.amazon.org.codehaus.jackson.map.util.ClassUtil;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Map;
/* loaded from: classes13.dex */
public class BasicClassIntrospector extends ClassIntrospector<BasicBeanDescription> {
    protected static final BasicBeanDescription STRING_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(String.class), AnnotatedClass.constructWithoutSuperTypes(String.class, null, null));
    protected static final BasicBeanDescription BOOLEAN_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(Boolean.TYPE), AnnotatedClass.constructWithoutSuperTypes(Boolean.TYPE, null, null));
    protected static final BasicBeanDescription INT_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(Integer.TYPE), AnnotatedClass.constructWithoutSuperTypes(Integer.TYPE, null, null));
    protected static final BasicBeanDescription LONG_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(Long.TYPE), AnnotatedClass.constructWithoutSuperTypes(Long.TYPE, null, null));
    @Deprecated
    public static final GetterMethodFilter DEFAULT_GETTER_FILTER = new GetterMethodFilter();
    @Deprecated
    public static final SetterMethodFilter DEFAULT_SETTER_FILTER = new SetterMethodFilter();
    @Deprecated
    public static final SetterAndGetterMethodFilter DEFAULT_SETTER_AND_GETTER_FILTER = new SetterAndGetterMethodFilter();
    protected static final MethodFilter MINIMAL_FILTER = new MinimalMethodFilter();
    public static final BasicClassIntrospector instance = new BasicClassIntrospector();

    @Deprecated
    /* loaded from: classes13.dex */
    public static class GetterMethodFilter implements MethodFilter {
        @Override // com.amazon.org.codehaus.jackson.map.introspect.MethodFilter
        public boolean includeMethod(Method method) {
            return ClassUtil.hasGetterSignature(method);
        }

        private GetterMethodFilter() {
        }
    }

    /* loaded from: classes13.dex */
    private static class MinimalMethodFilter implements MethodFilter {
        private MinimalMethodFilter() {
        }

        @Override // com.amazon.org.codehaus.jackson.map.introspect.MethodFilter
        public boolean includeMethod(Method method) {
            return !Modifier.isStatic(method.getModifiers()) && method.getParameterTypes().length <= 2;
        }
    }

    @Deprecated
    /* loaded from: classes13.dex */
    public static final class SetterAndGetterMethodFilter extends SetterMethodFilter {
        @Override // com.amazon.org.codehaus.jackson.map.introspect.BasicClassIntrospector.SetterMethodFilter, com.amazon.org.codehaus.jackson.map.introspect.MethodFilter
        public boolean includeMethod(Method method) {
            if (super.includeMethod(method)) {
                return true;
            }
            if (!ClassUtil.hasGetterSignature(method)) {
                return false;
            }
            Class<?> returnType = method.getReturnType();
            return Collection.class.isAssignableFrom(returnType) || Map.class.isAssignableFrom(returnType);
        }
    }

    @Deprecated
    /* loaded from: classes13.dex */
    public static class SetterMethodFilter implements MethodFilter {
        @Override // com.amazon.org.codehaus.jackson.map.introspect.MethodFilter
        public boolean includeMethod(Method method) {
            if (Modifier.isStatic(method.getModifiers())) {
                return false;
            }
            int length = method.getParameterTypes().length;
            return length == 1 || length == 2;
        }
    }

    protected BasicBeanDescription _findCachedDesc(JavaType javaType) {
        Class<?> rawClass = javaType.getRawClass();
        if (rawClass == String.class) {
            return STRING_DESC;
        }
        if (rawClass == Boolean.TYPE) {
            return BOOLEAN_DESC;
        }
        if (rawClass == Integer.TYPE) {
            return INT_DESC;
        }
        if (rawClass != Long.TYPE) {
            return null;
        }
        return LONG_DESC;
    }

    public AnnotatedClass classWithCreators(MapperConfig<?> mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        boolean isAnnotationProcessingEnabled = mapperConfig.isAnnotationProcessingEnabled();
        AnnotationIntrospector annotationIntrospector = mapperConfig.getAnnotationIntrospector();
        Class<?> rawClass = javaType.getRawClass();
        if (!isAnnotationProcessingEnabled) {
            annotationIntrospector = null;
        }
        AnnotatedClass construct = AnnotatedClass.construct(rawClass, annotationIntrospector, mixInResolver);
        construct.resolveMemberMethods(MINIMAL_FILTER);
        construct.resolveCreators(true);
        return construct;
    }

    public POJOPropertiesCollector collectProperties(MapperConfig<?> mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver, boolean z) {
        AnnotatedClass classWithCreators = classWithCreators(mapperConfig, javaType, mixInResolver);
        classWithCreators.resolveMemberMethods(MINIMAL_FILTER);
        classWithCreators.resolveFields();
        return constructPropertyCollector(mapperConfig, classWithCreators, javaType, z).collect();
    }

    protected POJOPropertiesCollector constructPropertyCollector(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, JavaType javaType, boolean z) {
        return new POJOPropertiesCollector(mapperConfig, z, javaType, annotatedClass);
    }

    @Override // com.amazon.org.codehaus.jackson.map.ClassIntrospector
    public /* bridge */ /* synthetic */ BasicBeanDescription forClassAnnotations(MapperConfig mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        return forClassAnnotations2((MapperConfig<?>) mapperConfig, javaType, mixInResolver);
    }

    @Override // com.amazon.org.codehaus.jackson.map.ClassIntrospector
    public /* bridge */ /* synthetic */ BasicBeanDescription forDirectClassAnnotations(MapperConfig mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        return forDirectClassAnnotations2((MapperConfig<?>) mapperConfig, javaType, mixInResolver);
    }

    @Deprecated
    protected MethodFilter getDeserializationMethodFilter(DeserializationConfig deserializationConfig) {
        if (deserializationConfig.isEnabled(DeserializationConfig.Feature.USE_GETTERS_AS_SETTERS)) {
            return DEFAULT_SETTER_AND_GETTER_FILTER;
        }
        return DEFAULT_SETTER_FILTER;
    }

    @Deprecated
    protected MethodFilter getSerializationMethodFilter(SerializationConfig serializationConfig) {
        return DEFAULT_GETTER_FILTER;
    }

    @Override // com.amazon.org.codehaus.jackson.map.ClassIntrospector
    /* renamed from: forClassAnnotations  reason: avoid collision after fix types in other method */
    public BasicBeanDescription forClassAnnotations2(MapperConfig<?> mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        boolean isAnnotationProcessingEnabled = mapperConfig.isAnnotationProcessingEnabled();
        AnnotationIntrospector annotationIntrospector = mapperConfig.getAnnotationIntrospector();
        Class<?> rawClass = javaType.getRawClass();
        if (!isAnnotationProcessingEnabled) {
            annotationIntrospector = null;
        }
        return BasicBeanDescription.forOtherUse(mapperConfig, javaType, AnnotatedClass.construct(rawClass, annotationIntrospector, mixInResolver));
    }

    @Override // com.amazon.org.codehaus.jackson.map.ClassIntrospector
    public BasicBeanDescription forCreation(DeserializationConfig deserializationConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        BasicBeanDescription _findCachedDesc = _findCachedDesc(javaType);
        return _findCachedDesc == null ? BasicBeanDescription.forDeserialization(collectProperties(deserializationConfig, javaType, mixInResolver, false)) : _findCachedDesc;
    }

    @Override // com.amazon.org.codehaus.jackson.map.ClassIntrospector
    public BasicBeanDescription forDeserialization(DeserializationConfig deserializationConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        BasicBeanDescription _findCachedDesc = _findCachedDesc(javaType);
        return _findCachedDesc == null ? BasicBeanDescription.forDeserialization(collectProperties(deserializationConfig, javaType, mixInResolver, false)) : _findCachedDesc;
    }

    @Override // com.amazon.org.codehaus.jackson.map.ClassIntrospector
    /* renamed from: forDirectClassAnnotations  reason: avoid collision after fix types in other method */
    public BasicBeanDescription forDirectClassAnnotations2(MapperConfig<?> mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        boolean isAnnotationProcessingEnabled = mapperConfig.isAnnotationProcessingEnabled();
        AnnotationIntrospector annotationIntrospector = mapperConfig.getAnnotationIntrospector();
        Class<?> rawClass = javaType.getRawClass();
        if (!isAnnotationProcessingEnabled) {
            annotationIntrospector = null;
        }
        return BasicBeanDescription.forOtherUse(mapperConfig, javaType, AnnotatedClass.constructWithoutSuperTypes(rawClass, annotationIntrospector, mixInResolver));
    }

    @Override // com.amazon.org.codehaus.jackson.map.ClassIntrospector
    public BasicBeanDescription forSerialization(SerializationConfig serializationConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        BasicBeanDescription _findCachedDesc = _findCachedDesc(javaType);
        return _findCachedDesc == null ? BasicBeanDescription.forSerialization(collectProperties(serializationConfig, javaType, mixInResolver, true)) : _findCachedDesc;
    }
}
