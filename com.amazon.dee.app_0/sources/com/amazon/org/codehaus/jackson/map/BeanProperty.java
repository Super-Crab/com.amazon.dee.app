package com.amazon.org.codehaus.jackson.map;

import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.amazon.org.codehaus.jackson.map.util.Annotations;
import com.amazon.org.codehaus.jackson.map.util.Named;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.lang.annotation.Annotation;
/* loaded from: classes13.dex */
public interface BeanProperty extends Named {

    /* loaded from: classes13.dex */
    public static class Std implements BeanProperty {
        protected final Annotations _contextAnnotations;
        protected final AnnotatedMember _member;
        protected final String _name;
        protected final JavaType _type;

        public Std(String str, JavaType javaType, Annotations annotations, AnnotatedMember annotatedMember) {
            this._name = str;
            this._type = javaType;
            this._member = annotatedMember;
            this._contextAnnotations = annotations;
        }

        @Override // com.amazon.org.codehaus.jackson.map.BeanProperty
        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            return (A) this._member.getAnnotation(cls);
        }

        @Override // com.amazon.org.codehaus.jackson.map.BeanProperty
        public <A extends Annotation> A getContextAnnotation(Class<A> cls) {
            Annotations annotations = this._contextAnnotations;
            if (annotations == null) {
                return null;
            }
            return (A) annotations.get(cls);
        }

        @Override // com.amazon.org.codehaus.jackson.map.BeanProperty
        public AnnotatedMember getMember() {
            return this._member;
        }

        @Override // com.amazon.org.codehaus.jackson.map.BeanProperty, com.amazon.org.codehaus.jackson.map.util.Named
        public String getName() {
            return this._name;
        }

        @Override // com.amazon.org.codehaus.jackson.map.BeanProperty
        public JavaType getType() {
            return this._type;
        }

        public Std withType(JavaType javaType) {
            return new Std(this._name, javaType, this._contextAnnotations, this._member);
        }
    }

    <A extends Annotation> A getAnnotation(Class<A> cls);

    <A extends Annotation> A getContextAnnotation(Class<A> cls);

    AnnotatedMember getMember();

    @Override // com.amazon.org.codehaus.jackson.map.util.Named
    String getName();

    JavaType getType();
}
