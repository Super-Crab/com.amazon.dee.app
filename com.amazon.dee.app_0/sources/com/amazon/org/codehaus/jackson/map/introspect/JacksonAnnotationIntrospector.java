package com.amazon.org.codehaus.jackson.map.introspect;

import com.amazon.org.codehaus.jackson.annotate.JacksonAnnotation;
import com.amazon.org.codehaus.jackson.annotate.JsonAnyGetter;
import com.amazon.org.codehaus.jackson.annotate.JsonAnySetter;
import com.amazon.org.codehaus.jackson.annotate.JsonAutoDetect;
import com.amazon.org.codehaus.jackson.annotate.JsonBackReference;
import com.amazon.org.codehaus.jackson.annotate.JsonCreator;
import com.amazon.org.codehaus.jackson.annotate.JsonGetter;
import com.amazon.org.codehaus.jackson.annotate.JsonIgnore;
import com.amazon.org.codehaus.jackson.annotate.JsonIgnoreProperties;
import com.amazon.org.codehaus.jackson.annotate.JsonIgnoreType;
import com.amazon.org.codehaus.jackson.annotate.JsonManagedReference;
import com.amazon.org.codehaus.jackson.annotate.JsonProperty;
import com.amazon.org.codehaus.jackson.annotate.JsonPropertyOrder;
import com.amazon.org.codehaus.jackson.annotate.JsonRawValue;
import com.amazon.org.codehaus.jackson.annotate.JsonSetter;
import com.amazon.org.codehaus.jackson.annotate.JsonSubTypes;
import com.amazon.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.amazon.org.codehaus.jackson.annotate.JsonTypeName;
import com.amazon.org.codehaus.jackson.annotate.JsonUnwrapped;
import com.amazon.org.codehaus.jackson.annotate.JsonValue;
import com.amazon.org.codehaus.jackson.annotate.JsonWriteNullProperties;
import com.amazon.org.codehaus.jackson.map.AnnotationIntrospector;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.KeyDeserializer;
import com.amazon.org.codehaus.jackson.map.MapperConfig;
import com.amazon.org.codehaus.jackson.map.annotate.JacksonInject;
import com.amazon.org.codehaus.jackson.map.annotate.JsonCachable;
import com.amazon.org.codehaus.jackson.map.annotate.JsonDeserialize;
import com.amazon.org.codehaus.jackson.map.annotate.JsonFilter;
import com.amazon.org.codehaus.jackson.map.annotate.JsonRootName;
import com.amazon.org.codehaus.jackson.map.annotate.JsonSerialize;
import com.amazon.org.codehaus.jackson.map.annotate.JsonTypeIdResolver;
import com.amazon.org.codehaus.jackson.map.annotate.JsonTypeResolver;
import com.amazon.org.codehaus.jackson.map.annotate.JsonValueInstantiator;
import com.amazon.org.codehaus.jackson.map.annotate.JsonView;
import com.amazon.org.codehaus.jackson.map.annotate.NoClass;
import com.amazon.org.codehaus.jackson.map.jsontype.NamedType;
import com.amazon.org.codehaus.jackson.map.jsontype.TypeIdResolver;
import com.amazon.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import com.amazon.org.codehaus.jackson.map.jsontype.impl.StdTypeResolverBuilder;
import com.amazon.org.codehaus.jackson.map.ser.std.RawSerializer;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes13.dex */
public class JacksonAnnotationIntrospector extends AnnotationIntrospector {
    protected StdTypeResolverBuilder _constructNoTypeResolverBuilder() {
        return StdTypeResolverBuilder.noTypeInfoBuilder();
    }

    protected StdTypeResolverBuilder _constructStdTypeResolverBuilder() {
        return new StdTypeResolverBuilder();
    }

    /* JADX WARN: Type inference failed for: r5v3, types: [com.amazon.org.codehaus.jackson.map.jsontype.TypeResolverBuilder] */
    /* JADX WARN: Type inference failed for: r5v6, types: [com.amazon.org.codehaus.jackson.map.jsontype.TypeResolverBuilder<?>, com.amazon.org.codehaus.jackson.map.jsontype.TypeResolverBuilder] */
    protected TypeResolverBuilder<?> _findTypeResolver(MapperConfig<?> mapperConfig, Annotated annotated, JavaType javaType) {
        TypeResolverBuilder<?> _constructStdTypeResolverBuilder;
        JsonTypeInfo jsonTypeInfo = (JsonTypeInfo) annotated.getAnnotation(JsonTypeInfo.class);
        JsonTypeResolver jsonTypeResolver = (JsonTypeResolver) annotated.getAnnotation(JsonTypeResolver.class);
        TypeIdResolver typeIdResolver = null;
        if (jsonTypeResolver != null) {
            if (jsonTypeInfo == null) {
                return null;
            }
            _constructStdTypeResolverBuilder = mapperConfig.typeResolverBuilderInstance(annotated, jsonTypeResolver.value());
        } else if (jsonTypeInfo == null) {
            return null;
        } else {
            if (jsonTypeInfo.use() == JsonTypeInfo.Id.NONE) {
                return _constructNoTypeResolverBuilder();
            }
            _constructStdTypeResolverBuilder = _constructStdTypeResolverBuilder();
        }
        JsonTypeIdResolver jsonTypeIdResolver = (JsonTypeIdResolver) annotated.getAnnotation(JsonTypeIdResolver.class);
        if (jsonTypeIdResolver != null) {
            typeIdResolver = mapperConfig.typeIdResolverInstance(annotated, jsonTypeIdResolver.value());
        }
        if (typeIdResolver != null) {
            typeIdResolver.init(javaType);
        }
        ?? init = _constructStdTypeResolverBuilder.init(jsonTypeInfo.use(), typeIdResolver);
        JsonTypeInfo.As include = jsonTypeInfo.include();
        if (include == JsonTypeInfo.As.EXTERNAL_PROPERTY && (annotated instanceof AnnotatedClass)) {
            include = JsonTypeInfo.As.PROPERTY;
        }
        TypeResolverBuilder<?> typeProperty = init.inclusion(include).typeProperty(jsonTypeInfo.property());
        Class<?> defaultImpl = jsonTypeInfo.defaultImpl();
        return defaultImpl != JsonTypeInfo.None.class ? typeProperty.defaultImpl(defaultImpl) : typeProperty;
    }

    protected boolean _isIgnorable(Annotated annotated) {
        JsonIgnore jsonIgnore = (JsonIgnore) annotated.getAnnotation(JsonIgnore.class);
        return jsonIgnore != null && jsonIgnore.value();
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker, com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker<?>] */
    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public VisibilityChecker<?> findAutoDetectVisibility(AnnotatedClass annotatedClass, VisibilityChecker<?> visibilityChecker) {
        JsonAutoDetect jsonAutoDetect = (JsonAutoDetect) annotatedClass.getAnnotation(JsonAutoDetect.class);
        return jsonAutoDetect == null ? visibilityChecker : visibilityChecker.mo4219with(jsonAutoDetect);
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Boolean findCachability(AnnotatedClass annotatedClass) {
        JsonCachable jsonCachable = (JsonCachable) annotatedClass.getAnnotation(JsonCachable.class);
        if (jsonCachable == null) {
            return null;
        }
        return jsonCachable.value() ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Class<? extends JsonDeserializer<?>> findContentDeserializer(Annotated annotated) {
        Class<? extends JsonDeserializer<?>> contentUsing;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize == null || (contentUsing = jsonDeserialize.contentUsing()) == JsonDeserializer.None.class) {
            return null;
        }
        return contentUsing;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Class<? extends JsonSerializer<?>> findContentSerializer(Annotated annotated) {
        Class<? extends JsonSerializer<?>> contentUsing;
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize == null || (contentUsing = jsonSerialize.contentUsing()) == JsonSerializer.None.class) {
            return null;
        }
        return contentUsing;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public String findDeserializablePropertyName(AnnotatedField annotatedField) {
        JsonProperty jsonProperty = (JsonProperty) annotatedField.getAnnotation(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        if (!annotatedField.hasAnnotation(JsonDeserialize.class) && !annotatedField.hasAnnotation(JsonView.class) && !annotatedField.hasAnnotation(JsonBackReference.class) && !annotatedField.hasAnnotation(JsonManagedReference.class)) {
            return null;
        }
        return "";
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Class<?> findDeserializationContentType(Annotated annotated, JavaType javaType, String str) {
        Class<?> contentAs;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize == null || (contentAs = jsonDeserialize.contentAs()) == NoClass.class) {
            return null;
        }
        return contentAs;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Class<?> findDeserializationKeyType(Annotated annotated, JavaType javaType, String str) {
        Class<?> keyAs;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize == null || (keyAs = jsonDeserialize.keyAs()) == NoClass.class) {
            return null;
        }
        return keyAs;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Class<?> findDeserializationType(Annotated annotated, JavaType javaType, String str) {
        Class<?> as;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize == null || (as = jsonDeserialize.as()) == NoClass.class) {
            return null;
        }
        return as;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public String findEnumValue(Enum<?> r1) {
        return r1.name();
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Object findFilterId(AnnotatedClass annotatedClass) {
        JsonFilter jsonFilter = (JsonFilter) annotatedClass.getAnnotation(JsonFilter.class);
        if (jsonFilter != null) {
            String value = jsonFilter.value();
            if (value.length() <= 0) {
                return null;
            }
            return value;
        }
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public String findGettablePropertyName(AnnotatedMethod annotatedMethod) {
        JsonProperty jsonProperty = (JsonProperty) annotatedMethod.getAnnotation(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        JsonGetter jsonGetter = (JsonGetter) annotatedMethod.getAnnotation(JsonGetter.class);
        if (jsonGetter != null) {
            return jsonGetter.value();
        }
        if (!annotatedMethod.hasAnnotation(JsonSerialize.class) && !annotatedMethod.hasAnnotation(JsonView.class)) {
            return null;
        }
        return "";
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Boolean findIgnoreUnknownProperties(AnnotatedClass annotatedClass) {
        JsonIgnoreProperties jsonIgnoreProperties = (JsonIgnoreProperties) annotatedClass.getAnnotation(JsonIgnoreProperties.class);
        if (jsonIgnoreProperties == null) {
            return null;
        }
        return Boolean.valueOf(jsonIgnoreProperties.ignoreUnknown());
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Object findInjectableValueId(AnnotatedMember annotatedMember) {
        JacksonInject jacksonInject = (JacksonInject) annotatedMember.getAnnotation(JacksonInject.class);
        if (jacksonInject == null) {
            return null;
        }
        String value = jacksonInject.value();
        if (value.length() != 0) {
            return value;
        }
        if (!(annotatedMember instanceof AnnotatedMethod)) {
            return annotatedMember.getRawType().getName();
        }
        AnnotatedMethod annotatedMethod = (AnnotatedMethod) annotatedMember;
        if (annotatedMethod.getParameterCount() == 0) {
            return annotatedMember.getRawType().getName();
        }
        return annotatedMethod.getParameterClass(0).getName();
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Class<? extends KeyDeserializer> findKeyDeserializer(Annotated annotated) {
        Class<? extends KeyDeserializer> keyUsing;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize == null || (keyUsing = jsonDeserialize.keyUsing()) == KeyDeserializer.None.class) {
            return null;
        }
        return keyUsing;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Class<? extends JsonSerializer<?>> findKeySerializer(Annotated annotated) {
        Class<? extends JsonSerializer<?>> keyUsing;
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize == null || (keyUsing = jsonSerialize.keyUsing()) == JsonSerializer.None.class) {
            return null;
        }
        return keyUsing;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public String[] findPropertiesToIgnore(AnnotatedClass annotatedClass) {
        JsonIgnoreProperties jsonIgnoreProperties = (JsonIgnoreProperties) annotatedClass.getAnnotation(JsonIgnoreProperties.class);
        if (jsonIgnoreProperties == null) {
            return null;
        }
        return jsonIgnoreProperties.value();
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public TypeResolverBuilder<?> findPropertyContentTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        if (javaType.isContainerType()) {
            return _findTypeResolver(mapperConfig, annotatedMember, javaType);
        }
        throw new IllegalArgumentException("Must call method with a container type (got " + javaType + ")");
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public String findPropertyNameForParam(AnnotatedParameter annotatedParameter) {
        JsonProperty jsonProperty;
        if (annotatedParameter == null || (jsonProperty = (JsonProperty) annotatedParameter.getAnnotation(JsonProperty.class)) == null) {
            return null;
        }
        return jsonProperty.value();
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public TypeResolverBuilder<?> findPropertyTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        if (javaType.isContainerType()) {
            return null;
        }
        return _findTypeResolver(mapperConfig, annotatedMember, javaType);
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public AnnotationIntrospector.ReferenceProperty findReferenceType(AnnotatedMember annotatedMember) {
        JsonManagedReference jsonManagedReference = (JsonManagedReference) annotatedMember.getAnnotation(JsonManagedReference.class);
        if (jsonManagedReference != null) {
            return AnnotationIntrospector.ReferenceProperty.managed(jsonManagedReference.value());
        }
        JsonBackReference jsonBackReference = (JsonBackReference) annotatedMember.getAnnotation(JsonBackReference.class);
        if (jsonBackReference == null) {
            return null;
        }
        return AnnotationIntrospector.ReferenceProperty.back(jsonBackReference.value());
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public String findRootName(AnnotatedClass annotatedClass) {
        JsonRootName jsonRootName = (JsonRootName) annotatedClass.getAnnotation(JsonRootName.class);
        if (jsonRootName == null) {
            return null;
        }
        return jsonRootName.value();
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public String findSerializablePropertyName(AnnotatedField annotatedField) {
        JsonProperty jsonProperty = (JsonProperty) annotatedField.getAnnotation(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        if (!annotatedField.hasAnnotation(JsonSerialize.class) && !annotatedField.hasAnnotation(JsonView.class)) {
            return null;
        }
        return "";
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Class<?> findSerializationContentType(Annotated annotated, JavaType javaType) {
        Class<?> contentAs;
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize == null || (contentAs = jsonSerialize.contentAs()) == NoClass.class) {
            return null;
        }
        return contentAs;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public JsonSerialize.Inclusion findSerializationInclusion(Annotated annotated, JsonSerialize.Inclusion inclusion) {
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize != null) {
            return jsonSerialize.include();
        }
        JsonWriteNullProperties jsonWriteNullProperties = (JsonWriteNullProperties) annotated.getAnnotation(JsonWriteNullProperties.class);
        return jsonWriteNullProperties != null ? jsonWriteNullProperties.value() ? JsonSerialize.Inclusion.ALWAYS : JsonSerialize.Inclusion.NON_NULL : inclusion;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Class<?> findSerializationKeyType(Annotated annotated, JavaType javaType) {
        Class<?> keyAs;
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize == null || (keyAs = jsonSerialize.keyAs()) == NoClass.class) {
            return null;
        }
        return keyAs;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public String[] findSerializationPropertyOrder(AnnotatedClass annotatedClass) {
        JsonPropertyOrder jsonPropertyOrder = (JsonPropertyOrder) annotatedClass.getAnnotation(JsonPropertyOrder.class);
        if (jsonPropertyOrder == null) {
            return null;
        }
        return jsonPropertyOrder.value();
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Boolean findSerializationSortAlphabetically(AnnotatedClass annotatedClass) {
        JsonPropertyOrder jsonPropertyOrder = (JsonPropertyOrder) annotatedClass.getAnnotation(JsonPropertyOrder.class);
        if (jsonPropertyOrder == null) {
            return null;
        }
        return Boolean.valueOf(jsonPropertyOrder.alphabetic());
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Class<?> findSerializationType(Annotated annotated) {
        Class<?> as;
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize == null || (as = jsonSerialize.as()) == NoClass.class) {
            return null;
        }
        return as;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public JsonSerialize.Typing findSerializationTyping(Annotated annotated) {
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize == null) {
            return null;
        }
        return jsonSerialize.typing();
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Class<?>[] findSerializationViews(Annotated annotated) {
        JsonView jsonView = (JsonView) annotated.getAnnotation(JsonView.class);
        if (jsonView == null) {
            return null;
        }
        return jsonView.value();
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Object findSerializer(Annotated annotated) {
        Class<? extends JsonSerializer<?>> using;
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize == null || (using = jsonSerialize.using()) == JsonSerializer.None.class) {
            JsonRawValue jsonRawValue = (JsonRawValue) annotated.getAnnotation(JsonRawValue.class);
            if (jsonRawValue != null && jsonRawValue.value()) {
                return new RawSerializer(annotated.getRawType());
            }
            return null;
        }
        return using;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public String findSettablePropertyName(AnnotatedMethod annotatedMethod) {
        JsonProperty jsonProperty = (JsonProperty) annotatedMethod.getAnnotation(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        JsonSetter jsonSetter = (JsonSetter) annotatedMethod.getAnnotation(JsonSetter.class);
        if (jsonSetter != null) {
            return jsonSetter.value();
        }
        if (!annotatedMethod.hasAnnotation(JsonDeserialize.class) && !annotatedMethod.hasAnnotation(JsonView.class) && !annotatedMethod.hasAnnotation(JsonBackReference.class) && !annotatedMethod.hasAnnotation(JsonManagedReference.class)) {
            return null;
        }
        return "";
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public List<NamedType> findSubtypes(Annotated annotated) {
        JsonSubTypes jsonSubTypes = (JsonSubTypes) annotated.getAnnotation(JsonSubTypes.class);
        if (jsonSubTypes == null) {
            return null;
        }
        JsonSubTypes.Type[] value = jsonSubTypes.value();
        ArrayList arrayList = new ArrayList(value.length);
        for (JsonSubTypes.Type type : value) {
            arrayList.add(new NamedType(type.value(), type.name()));
        }
        return arrayList;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public String findTypeName(AnnotatedClass annotatedClass) {
        JsonTypeName jsonTypeName = (JsonTypeName) annotatedClass.getAnnotation(JsonTypeName.class);
        if (jsonTypeName == null) {
            return null;
        }
        return jsonTypeName.value();
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, JavaType javaType) {
        return _findTypeResolver(mapperConfig, annotatedClass, javaType);
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Object findValueInstantiator(AnnotatedClass annotatedClass) {
        JsonValueInstantiator jsonValueInstantiator = (JsonValueInstantiator) annotatedClass.getAnnotation(JsonValueInstantiator.class);
        if (jsonValueInstantiator == null) {
            return null;
        }
        return jsonValueInstantiator.value();
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public boolean hasAnyGetterAnnotation(AnnotatedMethod annotatedMethod) {
        return annotatedMethod.hasAnnotation(JsonAnyGetter.class);
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public boolean hasAnySetterAnnotation(AnnotatedMethod annotatedMethod) {
        return annotatedMethod.hasAnnotation(JsonAnySetter.class);
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public boolean hasAsValueAnnotation(AnnotatedMethod annotatedMethod) {
        JsonValue jsonValue = (JsonValue) annotatedMethod.getAnnotation(JsonValue.class);
        return jsonValue != null && jsonValue.value();
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public boolean hasCreatorAnnotation(Annotated annotated) {
        return annotated.hasAnnotation(JsonCreator.class);
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public boolean hasIgnoreMarker(AnnotatedMember annotatedMember) {
        return _isIgnorable(annotatedMember);
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public boolean isHandled(Annotation annotation) {
        return annotation.annotationType().getAnnotation(JacksonAnnotation.class) != null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public boolean isIgnorableConstructor(AnnotatedConstructor annotatedConstructor) {
        return _isIgnorable(annotatedConstructor);
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public boolean isIgnorableField(AnnotatedField annotatedField) {
        return _isIgnorable(annotatedField);
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public boolean isIgnorableMethod(AnnotatedMethod annotatedMethod) {
        return _isIgnorable(annotatedMethod);
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Boolean isIgnorableType(AnnotatedClass annotatedClass) {
        JsonIgnoreType jsonIgnoreType = (JsonIgnoreType) annotatedClass.getAnnotation(JsonIgnoreType.class);
        if (jsonIgnoreType == null) {
            return null;
        }
        return Boolean.valueOf(jsonIgnoreType.value());
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Boolean shouldUnwrapProperty(AnnotatedMember annotatedMember) {
        JsonUnwrapped jsonUnwrapped = (JsonUnwrapped) annotatedMember.getAnnotation(JsonUnwrapped.class);
        if (jsonUnwrapped == null || !jsonUnwrapped.enabled()) {
            return null;
        }
        return Boolean.TRUE;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    /* renamed from: findDeserializer */
    public Class<? extends JsonDeserializer<?>> mo4217findDeserializer(Annotated annotated) {
        Class<? extends JsonDeserializer<?>> using;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize == null || (using = jsonDeserialize.using()) == JsonDeserializer.None.class) {
            return null;
        }
        return using;
    }
}
