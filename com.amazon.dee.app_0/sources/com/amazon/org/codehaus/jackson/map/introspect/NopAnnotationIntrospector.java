package com.amazon.org.codehaus.jackson.map.introspect;

import com.amazon.org.codehaus.jackson.map.AnnotationIntrospector;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.KeyDeserializer;
import com.amazon.org.codehaus.jackson.map.annotate.JsonSerialize;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.lang.annotation.Annotation;
/* loaded from: classes13.dex */
public class NopAnnotationIntrospector extends AnnotationIntrospector {
    public static final NopAnnotationIntrospector instance = new NopAnnotationIntrospector();

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Class<JsonDeserializer<?>> findContentDeserializer(Annotated annotated) {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public String findDeserializablePropertyName(AnnotatedField annotatedField) {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Class<?> findDeserializationContentType(Annotated annotated, JavaType javaType, String str) {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Class<?> findDeserializationKeyType(Annotated annotated, JavaType javaType, String str) {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Class<?> findDeserializationType(Annotated annotated, JavaType javaType, String str) {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    /* renamed from: findDeserializer */
    public Object mo4217findDeserializer(Annotated annotated) {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public String findEnumValue(Enum<?> r1) {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public String findGettablePropertyName(AnnotatedMethod annotatedMethod) {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Boolean findIgnoreUnknownProperties(AnnotatedClass annotatedClass) {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Class<KeyDeserializer> findKeyDeserializer(Annotated annotated) {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public String[] findPropertiesToIgnore(AnnotatedClass annotatedClass) {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public String findPropertyNameForParam(AnnotatedParameter annotatedParameter) {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public String findRootName(AnnotatedClass annotatedClass) {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public String findSerializablePropertyName(AnnotatedField annotatedField) {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public String[] findSerializationPropertyOrder(AnnotatedClass annotatedClass) {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Boolean findSerializationSortAlphabetically(AnnotatedClass annotatedClass) {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Class<?> findSerializationType(Annotated annotated) {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public JsonSerialize.Typing findSerializationTyping(Annotated annotated) {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Class<?>[] findSerializationViews(Annotated annotated) {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public Object findSerializer(Annotated annotated) {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public String findSettablePropertyName(AnnotatedMethod annotatedMethod) {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public boolean hasAsValueAnnotation(AnnotatedMethod annotatedMethod) {
        return false;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public boolean hasIgnoreMarker(AnnotatedMember annotatedMember) {
        return false;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public boolean isHandled(Annotation annotation) {
        return false;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public boolean isIgnorableConstructor(AnnotatedConstructor annotatedConstructor) {
        return false;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public boolean isIgnorableField(AnnotatedField annotatedField) {
        return false;
    }

    @Override // com.amazon.org.codehaus.jackson.map.AnnotationIntrospector
    public boolean isIgnorableMethod(AnnotatedMethod annotatedMethod) {
        return false;
    }
}
