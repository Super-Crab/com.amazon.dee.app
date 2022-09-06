package com.amazon.org.codehaus.jackson.map.ser;

import com.amazon.org.codehaus.jackson.map.AnnotationIntrospector;
import com.amazon.org.codehaus.jackson.map.SerializationConfig;
import com.amazon.org.codehaus.jackson.map.annotate.JsonSerialize;
import com.amazon.org.codehaus.jackson.map.introspect.Annotated;
import com.amazon.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import com.amazon.org.codehaus.jackson.map.util.Annotations;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
/* loaded from: classes13.dex */
public class PropertyBuilder {
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final BasicBeanDescription _beanDesc;
    protected final SerializationConfig _config;
    protected Object _defaultBean;
    protected final JsonSerialize.Inclusion _outputProps;

    /* renamed from: com.amazon.org.codehaus.jackson.map.ser.PropertyBuilder$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$org$codehaus$jackson$map$annotate$JsonSerialize$Inclusion = new int[JsonSerialize.Inclusion.values().length];

        static {
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$map$annotate$JsonSerialize$Inclusion[JsonSerialize.Inclusion.NON_DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$map$annotate$JsonSerialize$Inclusion[JsonSerialize.Inclusion.NON_EMPTY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$map$annotate$JsonSerialize$Inclusion[JsonSerialize.Inclusion.NON_NULL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$map$annotate$JsonSerialize$Inclusion[JsonSerialize.Inclusion.ALWAYS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* loaded from: classes13.dex */
    public static class EmptyArrayChecker {
        public boolean equals(Object obj) {
            return obj == null || Array.getLength(obj) == 0;
        }
    }

    /* loaded from: classes13.dex */
    public static class EmptyCollectionChecker {
        public boolean equals(Object obj) {
            return obj == null || ((Collection) obj).size() == 0;
        }
    }

    /* loaded from: classes13.dex */
    public static class EmptyMapChecker {
        public boolean equals(Object obj) {
            return obj == null || ((Map) obj).size() == 0;
        }
    }

    /* loaded from: classes13.dex */
    public static class EmptyStringChecker {
        public boolean equals(Object obj) {
            return obj == null || ((String) obj).length() == 0;
        }
    }

    public PropertyBuilder(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription) {
        this._config = serializationConfig;
        this._beanDesc = basicBeanDescription;
        this._outputProps = basicBeanDescription.findSerializationInclusion(serializationConfig.getSerializationInclusion());
        this._annotationIntrospector = this._config.getAnnotationIntrospector();
    }

    /* JADX WARN: Code restructure failed: missing block: B:0:?, code lost:
        r3 = r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected java.lang.Object _throwWrapped(java.lang.Exception r3, java.lang.String r4, java.lang.Object r5) {
        /*
            r2 = this;
        L0:
            java.lang.Throwable r0 = r3.getCause()
            if (r0 == 0) goto Lb
            java.lang.Throwable r3 = r3.getCause()
            goto L0
        Lb:
            boolean r0 = r3 instanceof java.lang.Error
            if (r0 != 0) goto L2a
            boolean r0 = r3 instanceof java.lang.RuntimeException
            if (r0 == 0) goto L16
            java.lang.RuntimeException r3 = (java.lang.RuntimeException) r3
            throw r3
        L16:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Failed to get property '"
            java.lang.String r1 = "' of default "
            java.lang.StringBuilder r4 = com.android.tools.r8.GeneratedOutlineSupport1.outline115(r0, r4, r1)
            java.lang.String r0 = " instance"
            java.lang.String r4 = com.android.tools.r8.GeneratedOutlineSupport1.outline46(r5, r4, r0)
            r3.<init>(r4)
            throw r3
        L2a:
            java.lang.Error r3 = (java.lang.Error) r3
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.org.codehaus.jackson.map.ser.PropertyBuilder._throwWrapped(java.lang.Exception, java.lang.String, java.lang.Object):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.amazon.org.codehaus.jackson.map.ser.BeanPropertyWriter buildWriter(java.lang.String r16, com.amazon.org.codehaus.jackson.type.JavaType r17, com.amazon.org.codehaus.jackson.map.JsonSerializer<java.lang.Object> r18, com.amazon.org.codehaus.jackson.map.TypeSerializer r19, com.amazon.org.codehaus.jackson.map.TypeSerializer r20, com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember r21, boolean r22) {
        /*
            r15 = this;
            r0 = r15
            r4 = r16
            r1 = r20
            r13 = r21
            boolean r2 = r13 instanceof com.amazon.org.codehaus.jackson.map.introspect.AnnotatedField
            r3 = 0
            if (r2 == 0) goto L18
            r2 = r13
            com.amazon.org.codehaus.jackson.map.introspect.AnnotatedField r2 = (com.amazon.org.codehaus.jackson.map.introspect.AnnotatedField) r2
            java.lang.reflect.Field r2 = r2.mo4213getAnnotated()
            r5 = r17
            r10 = r2
            r9 = r3
            goto L23
        L18:
            r2 = r13
            com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMethod r2 = (com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMethod) r2
            java.lang.reflect.Method r2 = r2.mo4213getAnnotated()
            r5 = r17
            r9 = r2
            r10 = r3
        L23:
            r2 = r22
            com.amazon.org.codehaus.jackson.type.JavaType r2 = r15.findSerializationType(r13, r2, r5)
            if (r1 == 0) goto L65
            if (r2 != 0) goto L2e
            r2 = r5
        L2e:
            com.amazon.org.codehaus.jackson.type.JavaType r6 = r2.getContentType()
            if (r6 == 0) goto L3d
            com.amazon.org.codehaus.jackson.type.JavaType r1 = r2.mo4242withContentTypeHandler(r1)
            r1.getContentType()
            r8 = r1
            goto L66
        L3d:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r3 = "Problem trying to create BeanPropertyWriter for property '"
            java.lang.String r5 = "' (of type "
            java.lang.StringBuilder r3 = com.android.tools.r8.GeneratedOutlineSupport1.outline115(r3, r4, r5)
            com.amazon.org.codehaus.jackson.map.introspect.BasicBeanDescription r4 = r0._beanDesc
            com.amazon.org.codehaus.jackson.type.JavaType r4 = r4.getType()
            r3.append(r4)
            java.lang.String r4 = "); serialization type "
            r3.append(r4)
            r3.append(r2)
            java.lang.String r2 = " has no content"
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r1.<init>(r2)
            throw r1
        L65:
            r8 = r2
        L66:
            r1 = 0
            com.amazon.org.codehaus.jackson.map.AnnotationIntrospector r2 = r0._annotationIntrospector
            com.amazon.org.codehaus.jackson.map.annotate.JsonSerialize$Inclusion r6 = r0._outputProps
            com.amazon.org.codehaus.jackson.map.annotate.JsonSerialize$Inclusion r2 = r2.findSerializationInclusion(r13, r6)
            r6 = 1
            if (r2 == 0) goto Lad
            int r2 = r2.ordinal()
            if (r2 == 0) goto La0
            if (r2 == r6) goto L9f
            r7 = 2
            if (r2 == r7) goto L86
            r7 = 3
            if (r2 == r7) goto L81
            goto Lad
        L81:
            java.lang.Object r1 = r15.getEmptyValueChecker(r16, r17)
            goto L8d
        L86:
            java.lang.Object r2 = r15.getDefaultValue(r4, r9, r10)
            if (r2 != 0) goto L90
            r1 = r2
        L8d:
            r12 = r1
            r11 = r6
            goto Laf
        L90:
            java.lang.Class r3 = r2.getClass()
            boolean r3 = r3.isArray()
            if (r3 == 0) goto Laa
            java.lang.Object r2 = com.amazon.org.codehaus.jackson.map.util.Comparators.getArrayComparator(r2)
            goto Laa
        L9f:
            r1 = r6
        La0:
            boolean r2 = r17.isContainerType()
            if (r2 == 0) goto Lad
            java.lang.Object r2 = r15.getContainerValueChecker(r16, r17)
        Laa:
            r11 = r1
            r12 = r2
            goto Laf
        Lad:
            r11 = r1
            r12 = r3
        Laf:
            com.amazon.org.codehaus.jackson.map.ser.BeanPropertyWriter r14 = new com.amazon.org.codehaus.jackson.map.ser.BeanPropertyWriter
            com.amazon.org.codehaus.jackson.map.introspect.BasicBeanDescription r1 = r0._beanDesc
            com.amazon.org.codehaus.jackson.map.util.Annotations r3 = r1.getClassAnnotations()
            r1 = r14
            r2 = r21
            r4 = r16
            r5 = r17
            r6 = r18
            r7 = r19
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            com.amazon.org.codehaus.jackson.map.AnnotationIntrospector r1 = r0._annotationIntrospector
            java.lang.Boolean r1 = r1.shouldUnwrapProperty(r13)
            if (r1 == 0) goto Ld7
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto Ld7
            com.amazon.org.codehaus.jackson.map.ser.BeanPropertyWriter r14 = r14.unwrappingWriter()
        Ld7:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.org.codehaus.jackson.map.ser.PropertyBuilder.buildWriter(java.lang.String, com.amazon.org.codehaus.jackson.type.JavaType, com.amazon.org.codehaus.jackson.map.JsonSerializer, com.amazon.org.codehaus.jackson.map.TypeSerializer, com.amazon.org.codehaus.jackson.map.TypeSerializer, com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember, boolean):com.amazon.org.codehaus.jackson.map.ser.BeanPropertyWriter");
    }

    protected JavaType findSerializationType(Annotated annotated, boolean z, JavaType javaType) {
        JsonSerialize.Typing findSerializationTyping;
        JavaType constructSpecializedType;
        Class<?> findSerializationType = this._annotationIntrospector.findSerializationType(annotated);
        boolean z2 = true;
        if (findSerializationType != null) {
            Class<?> rawClass = javaType.getRawClass();
            if (findSerializationType.isAssignableFrom(rawClass)) {
                constructSpecializedType = javaType.widenBy(findSerializationType);
            } else if (rawClass.isAssignableFrom(findSerializationType)) {
                constructSpecializedType = this._config.constructSpecializedType(javaType, findSerializationType);
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Illegal concrete-type annotation for method '");
                outline107.append(annotated.getName());
                outline107.append("': class ");
                outline107.append(findSerializationType.getName());
                outline107.append(" not a super-type of (declared) class ");
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline38(rawClass, outline107));
            }
            javaType = constructSpecializedType;
            z = true;
        }
        JavaType modifySecondaryTypesByAnnotation = BasicSerializerFactory.modifySecondaryTypesByAnnotation(this._config, annotated, javaType);
        if (modifySecondaryTypesByAnnotation != javaType) {
            javaType = modifySecondaryTypesByAnnotation;
            z = true;
        }
        if (!z && (findSerializationTyping = this._annotationIntrospector.findSerializationTyping(annotated)) != null) {
            if (findSerializationTyping != JsonSerialize.Typing.STATIC) {
                z2 = false;
            }
            z = z2;
        }
        if (z) {
            return javaType;
        }
        return null;
    }

    public Annotations getClassAnnotations() {
        return this._beanDesc.getClassAnnotations();
    }

    protected Object getContainerValueChecker(String str, JavaType javaType) {
        if (!this._config.isEnabled(SerializationConfig.Feature.WRITE_EMPTY_JSON_ARRAYS)) {
            if (javaType.isArrayType()) {
                return new EmptyArrayChecker();
            }
            if (!Collection.class.isAssignableFrom(javaType.getRawClass())) {
                return null;
            }
            return new EmptyCollectionChecker();
        }
        return null;
    }

    protected Object getDefaultBean() {
        if (this._defaultBean == null) {
            this._defaultBean = this._beanDesc.instantiateBean(this._config.isEnabled(SerializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS));
            if (this._defaultBean == null) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline40(this._beanDesc.getClassInfo().mo4213getAnnotated(), GeneratedOutlineSupport1.outline107("Class "), " has no default constructor; can not instantiate default bean value to support 'properties=JsonSerialize.Inclusion.NON_DEFAULT' annotation"));
            }
        }
        return this._defaultBean;
    }

    protected Object getDefaultValue(String str, Method method, Field field) {
        Object defaultBean = getDefaultBean();
        try {
            if (method != null) {
                return method.invoke(defaultBean, new Object[0]);
            }
            return field.get(defaultBean);
        } catch (Exception e) {
            return _throwWrapped(e, str, defaultBean);
        }
    }

    protected Object getEmptyValueChecker(String str, JavaType javaType) {
        Class<?> rawClass = javaType.getRawClass();
        if (rawClass == String.class) {
            return new EmptyStringChecker();
        }
        if (javaType.isArrayType()) {
            return new EmptyArrayChecker();
        }
        if (Collection.class.isAssignableFrom(rawClass)) {
            return new EmptyCollectionChecker();
        }
        if (!Map.class.isAssignableFrom(rawClass)) {
            return null;
        }
        return new EmptyMapChecker();
    }
}
