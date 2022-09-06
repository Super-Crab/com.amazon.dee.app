package com.amazon.org.codehaus.jackson.map.jsontype;

import com.amazon.org.codehaus.jackson.map.AnnotationIntrospector;
import com.amazon.org.codehaus.jackson.map.MapperConfig;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedClass;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember;
import java.util.Collection;
/* loaded from: classes13.dex */
public abstract class SubtypeResolver {
    public abstract Collection<NamedType> collectAndResolveSubtypes(AnnotatedClass annotatedClass, MapperConfig<?> mapperConfig, AnnotationIntrospector annotationIntrospector);

    public abstract Collection<NamedType> collectAndResolveSubtypes(AnnotatedMember annotatedMember, MapperConfig<?> mapperConfig, AnnotationIntrospector annotationIntrospector);

    public abstract void registerSubtypes(NamedType... namedTypeArr);

    public abstract void registerSubtypes(Class<?>... clsArr);
}
