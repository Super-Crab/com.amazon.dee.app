package com.amazon.org.codehaus.jackson.map;

import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedClass;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedField;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker;
import com.amazon.org.codehaus.jackson.map.type.TypeBindings;
import com.amazon.org.codehaus.jackson.map.util.Annotations;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes13.dex */
public abstract class BeanDescription {
    protected final JavaType _type;

    /* JADX INFO: Access modifiers changed from: protected */
    public BeanDescription(JavaType javaType) {
        this._type = javaType;
    }

    public abstract TypeBindings bindingsForBeanType();

    public abstract AnnotatedMethod findAnyGetter();

    public abstract AnnotatedMethod findAnySetter();

    public abstract AnnotatedConstructor findDefaultConstructor();

    @Deprecated
    public abstract LinkedHashMap<String, AnnotatedField> findDeserializableFields(VisibilityChecker<?> visibilityChecker, Collection<String> collection);

    @Deprecated
    public abstract LinkedHashMap<String, AnnotatedMethod> findGetters(VisibilityChecker<?> visibilityChecker, Collection<String> collection);

    public abstract Map<Object, AnnotatedMember> findInjectables();

    public abstract AnnotatedMethod findJsonValueMethod();

    public abstract List<BeanPropertyDefinition> findProperties();

    @Deprecated
    /* renamed from: findSerializableFields */
    public abstract Map<String, AnnotatedField> mo4216findSerializableFields(VisibilityChecker<?> visibilityChecker, Collection<String> collection);

    @Deprecated
    public abstract LinkedHashMap<String, AnnotatedMethod> findSetters(VisibilityChecker<?> visibilityChecker);

    public Class<?> getBeanClass() {
        return this._type.getRawClass();
    }

    public abstract Annotations getClassAnnotations();

    public abstract AnnotatedClass getClassInfo();

    public abstract Set<String> getIgnoredPropertyNames();

    public JavaType getType() {
        return this._type;
    }

    public abstract boolean hasKnownClassAnnotations();

    public abstract JavaType resolveType(Type type);
}
