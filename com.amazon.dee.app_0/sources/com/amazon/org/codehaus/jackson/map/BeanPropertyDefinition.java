package com.amazon.org.codehaus.jackson.map;

import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedField;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedParameter;
import com.amazon.org.codehaus.jackson.map.util.Named;
/* loaded from: classes13.dex */
public abstract class BeanPropertyDefinition implements Named {
    public boolean couldDeserialize() {
        return getMutator() != null;
    }

    public boolean couldSerialize() {
        return getAccessor() != null;
    }

    public abstract AnnotatedMember getAccessor();

    public abstract AnnotatedParameter getConstructorParameter();

    public abstract AnnotatedField getField();

    public abstract AnnotatedMethod getGetter();

    public abstract String getInternalName();

    public abstract AnnotatedMember getMutator();

    @Override // com.amazon.org.codehaus.jackson.map.util.Named
    public abstract String getName();

    public abstract AnnotatedMethod getSetter();

    public abstract boolean hasConstructorParameter();

    public abstract boolean hasField();

    public abstract boolean hasGetter();

    public abstract boolean hasSetter();

    public abstract boolean isExplicitlyIncluded();
}
