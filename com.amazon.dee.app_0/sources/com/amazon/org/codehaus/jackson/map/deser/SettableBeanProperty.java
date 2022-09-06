package com.amazon.org.codehaus.jackson.map.deser;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.DeserializationConfig;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.TypeDeserializer;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedField;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.amazon.org.codehaus.jackson.map.util.Annotations;
import com.amazon.org.codehaus.jackson.map.util.ClassUtil;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.amazon.org.codehaus.jackson.util.InternCache;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
/* loaded from: classes13.dex */
public abstract class SettableBeanProperty implements BeanProperty {
    protected final Annotations _contextAnnotations;
    protected String _managedReferenceName;
    protected NullProvider _nullProvider;
    protected final String _propName;
    protected int _propertyIndex;
    protected final JavaType _type;
    protected JsonDeserializer<Object> _valueDeserializer;
    protected TypeDeserializer _valueTypeDeserializer;

    /* loaded from: classes13.dex */
    public static final class FieldProperty extends SettableBeanProperty {
        protected final AnnotatedField _annotated;
        protected final Field _field;

        public FieldProperty(String str, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedField annotatedField) {
            super(str, javaType, typeDeserializer, annotations);
            this._annotated = annotatedField;
            this._field = annotatedField.mo4213getAnnotated();
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty
        public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
            set(obj, deserialize(jsonParser, deserializationContext));
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty, com.amazon.org.codehaus.jackson.map.BeanProperty
        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            return (A) this._annotated.getAnnotation(cls);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty, com.amazon.org.codehaus.jackson.map.BeanProperty
        public AnnotatedMember getMember() {
            return this._annotated;
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty
        public final void set(Object obj, Object obj2) throws IOException {
            try {
                this._field.set(obj, obj2);
            } catch (Exception e) {
                _throwAsIOE(e, obj2);
            }
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty
        /* renamed from: withValueDeserializer  reason: collision with other method in class */
        public /* bridge */ /* synthetic */ SettableBeanProperty mo4140withValueDeserializer(JsonDeserializer jsonDeserializer) {
            return mo4140withValueDeserializer((JsonDeserializer<Object>) jsonDeserializer);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty
        /* renamed from: withValueDeserializer */
        public FieldProperty mo4140withValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
            return new FieldProperty(this, jsonDeserializer);
        }

        protected FieldProperty(FieldProperty fieldProperty, JsonDeserializer<Object> jsonDeserializer) {
            super(fieldProperty, jsonDeserializer);
            this._annotated = fieldProperty._annotated;
            this._field = fieldProperty._field;
        }
    }

    /* loaded from: classes13.dex */
    public static final class InnerClassProperty extends SettableBeanProperty {
        protected final Constructor<?> _creator;
        protected final SettableBeanProperty _delegate;

        public InnerClassProperty(SettableBeanProperty settableBeanProperty, Constructor<?> constructor) {
            super(settableBeanProperty);
            this._delegate = settableBeanProperty;
            this._creator = constructor;
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty
        public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
            Object obj2 = null;
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
                NullProvider nullProvider = this._nullProvider;
                if (nullProvider != null) {
                    obj2 = nullProvider.nullValue(deserializationContext);
                }
            } else {
                TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
                if (typeDeserializer != null) {
                    obj2 = this._valueDeserializer.mo4196deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
                } else {
                    try {
                        obj2 = this._creator.newInstance(obj);
                    } catch (Exception e) {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to instantiate class ");
                        outline107.append(this._creator.getDeclaringClass().getName());
                        outline107.append(", problem: ");
                        outline107.append(e.getMessage());
                        ClassUtil.unwrapAndThrowAsIAE(e, outline107.toString());
                    }
                    this._valueDeserializer.deserialize(jsonParser, deserializationContext, obj2);
                }
            }
            set(obj, obj2);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty, com.amazon.org.codehaus.jackson.map.BeanProperty
        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            return (A) this._delegate.getAnnotation(cls);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty, com.amazon.org.codehaus.jackson.map.BeanProperty
        public AnnotatedMember getMember() {
            return this._delegate.getMember();
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty
        public final void set(Object obj, Object obj2) throws IOException {
            this._delegate.set(obj, obj2);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty
        /* renamed from: withValueDeserializer  reason: collision with other method in class */
        public /* bridge */ /* synthetic */ SettableBeanProperty mo4140withValueDeserializer(JsonDeserializer jsonDeserializer) {
            return mo4140withValueDeserializer((JsonDeserializer<Object>) jsonDeserializer);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty
        /* renamed from: withValueDeserializer */
        public InnerClassProperty mo4140withValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
            return new InnerClassProperty(this, jsonDeserializer);
        }

        protected InnerClassProperty(InnerClassProperty innerClassProperty, JsonDeserializer<Object> jsonDeserializer) {
            super(innerClassProperty, jsonDeserializer);
            this._delegate = innerClassProperty._delegate.mo4140withValueDeserializer(jsonDeserializer);
            this._creator = innerClassProperty._creator;
        }
    }

    /* loaded from: classes13.dex */
    public static final class ManagedReferenceProperty extends SettableBeanProperty {
        protected final SettableBeanProperty _backProperty;
        protected final boolean _isContainer;
        protected final SettableBeanProperty _managedProperty;
        protected final String _referenceName;

        public ManagedReferenceProperty(String str, SettableBeanProperty settableBeanProperty, SettableBeanProperty settableBeanProperty2, Annotations annotations, boolean z) {
            super(settableBeanProperty.getName(), settableBeanProperty.getType(), settableBeanProperty._valueTypeDeserializer, annotations);
            this._referenceName = str;
            this._managedProperty = settableBeanProperty;
            this._backProperty = settableBeanProperty2;
            this._isContainer = z;
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty
        public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
            set(obj, this._managedProperty.deserialize(jsonParser, deserializationContext));
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty, com.amazon.org.codehaus.jackson.map.BeanProperty
        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            return (A) this._managedProperty.getAnnotation(cls);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty, com.amazon.org.codehaus.jackson.map.BeanProperty
        public AnnotatedMember getMember() {
            return this._managedProperty.getMember();
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty
        public final void set(Object obj, Object obj2) throws IOException {
            Object[] objArr;
            this._managedProperty.set(obj, obj2);
            if (obj2 != null) {
                if (this._isContainer) {
                    if (obj2 instanceof Object[]) {
                        for (Object obj3 : (Object[]) obj2) {
                            if (obj3 != null) {
                                this._backProperty.set(obj3, obj);
                            }
                        }
                        return;
                    } else if (obj2 instanceof Collection) {
                        for (Object obj4 : (Collection) obj2) {
                            if (obj4 != null) {
                                this._backProperty.set(obj4, obj);
                            }
                        }
                        return;
                    } else if (obj2 instanceof Map) {
                        for (Object obj5 : ((Map) obj2).values()) {
                            if (obj5 != null) {
                                this._backProperty.set(obj5, obj);
                            }
                        }
                        return;
                    } else {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unsupported container type (");
                        outline107.append(obj2.getClass().getName());
                        outline107.append(") when resolving reference '");
                        throw new IllegalStateException(GeneratedOutlineSupport1.outline91(outline107, this._referenceName, "'"));
                    }
                }
                this._backProperty.set(obj2, obj);
            }
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty
        /* renamed from: withValueDeserializer  reason: collision with other method in class */
        public /* bridge */ /* synthetic */ SettableBeanProperty mo4140withValueDeserializer(JsonDeserializer jsonDeserializer) {
            return mo4140withValueDeserializer((JsonDeserializer<Object>) jsonDeserializer);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty
        /* renamed from: withValueDeserializer */
        public ManagedReferenceProperty mo4140withValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
            return new ManagedReferenceProperty(this, jsonDeserializer);
        }

        protected ManagedReferenceProperty(ManagedReferenceProperty managedReferenceProperty, JsonDeserializer<Object> jsonDeserializer) {
            super(managedReferenceProperty, jsonDeserializer);
            this._referenceName = managedReferenceProperty._referenceName;
            this._isContainer = managedReferenceProperty._isContainer;
            this._managedProperty = managedReferenceProperty._managedProperty;
            this._backProperty = managedReferenceProperty._backProperty;
        }
    }

    /* loaded from: classes13.dex */
    public static final class MethodProperty extends SettableBeanProperty {
        protected final AnnotatedMethod _annotated;
        protected final Method _setter;

        public MethodProperty(String str, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedMethod annotatedMethod) {
            super(str, javaType, typeDeserializer, annotations);
            this._annotated = annotatedMethod;
            this._setter = annotatedMethod.mo4213getAnnotated();
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty
        public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
            set(obj, deserialize(jsonParser, deserializationContext));
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty, com.amazon.org.codehaus.jackson.map.BeanProperty
        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            return (A) this._annotated.getAnnotation(cls);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty, com.amazon.org.codehaus.jackson.map.BeanProperty
        public AnnotatedMember getMember() {
            return this._annotated;
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty
        public final void set(Object obj, Object obj2) throws IOException {
            try {
                this._setter.invoke(obj, obj2);
            } catch (Exception e) {
                _throwAsIOE(e, obj2);
            }
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty
        /* renamed from: withValueDeserializer  reason: collision with other method in class */
        public /* bridge */ /* synthetic */ SettableBeanProperty mo4140withValueDeserializer(JsonDeserializer jsonDeserializer) {
            return mo4140withValueDeserializer((JsonDeserializer<Object>) jsonDeserializer);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty
        /* renamed from: withValueDeserializer */
        public MethodProperty mo4140withValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
            return new MethodProperty(this, jsonDeserializer);
        }

        protected MethodProperty(MethodProperty methodProperty, JsonDeserializer<Object> jsonDeserializer) {
            super(methodProperty, jsonDeserializer);
            this._annotated = methodProperty._annotated;
            this._setter = methodProperty._setter;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes13.dex */
    public static final class NullProvider {
        private final boolean _isPrimitive;
        private final Object _nullValue;
        private final Class<?> _rawType;

        protected NullProvider(JavaType javaType, Object obj) {
            this._nullValue = obj;
            this._isPrimitive = javaType.isPrimitive();
            this._rawType = javaType.getRawClass();
        }

        public Object nullValue(DeserializationContext deserializationContext) throws JsonProcessingException {
            if (this._isPrimitive && deserializationContext.isEnabled(DeserializationConfig.Feature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can not map JSON null into type ");
                outline107.append(this._rawType.getName());
                outline107.append(" (set DeserializationConfig.Feature.FAIL_ON_NULL_FOR_PRIMITIVES to 'false' to allow)");
                throw deserializationContext.mappingException(outline107.toString());
            }
            return this._nullValue;
        }
    }

    /* loaded from: classes13.dex */
    public static final class SetterlessProperty extends SettableBeanProperty {
        protected final AnnotatedMethod _annotated;
        protected final Method _getter;

        public SetterlessProperty(String str, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedMethod annotatedMethod) {
            super(str, javaType, typeDeserializer, annotations);
            this._annotated = annotatedMethod;
            this._getter = annotatedMethod.mo4213getAnnotated();
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty
        public final void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
                return;
            }
            try {
                Object invoke = this._getter.invoke(obj, new Object[0]);
                if (invoke != null) {
                    this._valueDeserializer.deserialize(jsonParser, deserializationContext, invoke);
                    return;
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Problem deserializing 'setterless' property '");
                outline107.append(getName());
                outline107.append("': get method returned null");
                throw new JsonMappingException(outline107.toString());
            } catch (Exception e) {
                _throwAsIOE(e);
            }
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty, com.amazon.org.codehaus.jackson.map.BeanProperty
        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            return (A) this._annotated.getAnnotation(cls);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty, com.amazon.org.codehaus.jackson.map.BeanProperty
        public AnnotatedMember getMember() {
            return this._annotated;
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty
        public final void set(Object obj, Object obj2) throws IOException {
            throw new UnsupportedOperationException("Should never call 'set' on setterless property");
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty
        /* renamed from: withValueDeserializer  reason: collision with other method in class */
        public /* bridge */ /* synthetic */ SettableBeanProperty mo4140withValueDeserializer(JsonDeserializer jsonDeserializer) {
            return mo4140withValueDeserializer((JsonDeserializer<Object>) jsonDeserializer);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty
        /* renamed from: withValueDeserializer */
        public SetterlessProperty mo4140withValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
            return new SetterlessProperty(this, jsonDeserializer);
        }

        protected SetterlessProperty(SetterlessProperty setterlessProperty, JsonDeserializer<Object> jsonDeserializer) {
            super(setterlessProperty, jsonDeserializer);
            this._annotated = setterlessProperty._annotated;
            this._getter = setterlessProperty._getter;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SettableBeanProperty(String str, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations) {
        this._propertyIndex = -1;
        if (str != null && str.length() != 0) {
            this._propName = InternCache.instance.intern(str);
        } else {
            this._propName = "";
        }
        this._type = javaType;
        this._contextAnnotations = annotations;
        this._valueTypeDeserializer = typeDeserializer;
    }

    protected void _throwAsIOE(Exception exc, Object obj) throws IOException {
        if (exc instanceof IllegalArgumentException) {
            String name = obj == null ? "[NULL]" : obj.getClass().getName();
            StringBuilder sb = new StringBuilder("Problem deserializing property '");
            sb.append(getPropertyName());
            sb.append("' (expected type: ");
            sb.append(getType());
            sb.append("; actual type: ");
            sb.append(name);
            sb.append(")");
            String message = exc.getMessage();
            if (message != null) {
                sb.append(", problem: ");
                sb.append(message);
            } else {
                sb.append(" (no error message provided)");
            }
            throw new JsonMappingException(sb.toString(), null, exc);
        }
        _throwAsIOE(exc);
    }

    public void assignIndex(int i) {
        if (this._propertyIndex == -1) {
            this._propertyIndex = i;
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Property '");
        outline107.append(getName());
        outline107.append("' already had index (");
        outline107.append(this._propertyIndex);
        outline107.append("), trying to assign ");
        outline107.append(i);
        throw new IllegalStateException(outline107.toString());
    }

    public final Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            NullProvider nullProvider = this._nullProvider;
            if (nullProvider != null) {
                return nullProvider.nullValue(deserializationContext);
            }
            return null;
        }
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        if (typeDeserializer != null) {
            return this._valueDeserializer.mo4196deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
        }
        return this._valueDeserializer.mo4206deserialize(jsonParser, deserializationContext);
    }

    public abstract void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException;

    @Override // com.amazon.org.codehaus.jackson.map.BeanProperty
    public abstract <A extends Annotation> A getAnnotation(Class<A> cls);

    @Override // com.amazon.org.codehaus.jackson.map.BeanProperty
    public <A extends Annotation> A getContextAnnotation(Class<A> cls) {
        return (A) this._contextAnnotations.get(cls);
    }

    protected final Class<?> getDeclaringClass() {
        return getMember().getDeclaringClass();
    }

    public Object getInjectableValueId() {
        return null;
    }

    public String getManagedReferenceName() {
        return this._managedReferenceName;
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanProperty
    public abstract AnnotatedMember getMember();

    @Override // com.amazon.org.codehaus.jackson.map.BeanProperty, com.amazon.org.codehaus.jackson.map.util.Named
    public final String getName() {
        return this._propName;
    }

    public int getPropertyIndex() {
        return this._propertyIndex;
    }

    @Deprecated
    public String getPropertyName() {
        return this._propName;
    }

    @Deprecated
    public int getProperytIndex() {
        return getPropertyIndex();
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanProperty
    public JavaType getType() {
        return this._type;
    }

    public JsonDeserializer<Object> getValueDeserializer() {
        return this._valueDeserializer;
    }

    public TypeDeserializer getValueTypeDeserializer() {
        return this._valueTypeDeserializer;
    }

    public boolean hasValueDeserializer() {
        return this._valueDeserializer != null;
    }

    public boolean hasValueTypeDeserializer() {
        return this._valueTypeDeserializer != null;
    }

    public abstract void set(Object obj, Object obj2) throws IOException;

    public void setManagedReferenceName(String str) {
        this._managedReferenceName = str;
    }

    @Deprecated
    public void setValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
        if (this._valueDeserializer == null) {
            this._valueDeserializer = jsonDeserializer;
            Object nullValue = this._valueDeserializer.getNullValue();
            this._nullProvider = nullValue == null ? null : new NullProvider(this._type, nullValue);
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Already had assigned deserializer for property '");
        outline107.append(getName());
        outline107.append("' (class ");
        throw new IllegalStateException(GeneratedOutlineSupport1.outline40(getDeclaringClass(), outline107, ")"));
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[property '");
        outline107.append(getName());
        outline107.append("']");
        return outline107.toString();
    }

    /* renamed from: withValueDeserializer */
    public abstract SettableBeanProperty mo4140withValueDeserializer(JsonDeserializer<Object> jsonDeserializer);

    protected SettableBeanProperty(SettableBeanProperty settableBeanProperty) {
        this._propertyIndex = -1;
        this._propName = settableBeanProperty._propName;
        this._type = settableBeanProperty._type;
        this._contextAnnotations = settableBeanProperty._contextAnnotations;
        this._valueDeserializer = settableBeanProperty._valueDeserializer;
        this._valueTypeDeserializer = settableBeanProperty._valueTypeDeserializer;
        this._nullProvider = settableBeanProperty._nullProvider;
        this._managedReferenceName = settableBeanProperty._managedReferenceName;
        this._propertyIndex = settableBeanProperty._propertyIndex;
    }

    protected IOException _throwAsIOE(Exception exc) throws IOException {
        if (!(exc instanceof IOException)) {
            boolean z = exc instanceof RuntimeException;
            Exception exc2 = exc;
            if (!z) {
                while (exc2.getCause() != null) {
                    exc2 = exc2.getCause();
                }
                throw new JsonMappingException(exc2.getMessage(), null, exc2);
            }
            throw ((RuntimeException) exc);
        }
        throw ((IOException) exc);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SettableBeanProperty(SettableBeanProperty settableBeanProperty, JsonDeserializer<Object> jsonDeserializer) {
        this._propertyIndex = -1;
        this._propName = settableBeanProperty._propName;
        this._type = settableBeanProperty._type;
        this._contextAnnotations = settableBeanProperty._contextAnnotations;
        this._valueTypeDeserializer = settableBeanProperty._valueTypeDeserializer;
        this._managedReferenceName = settableBeanProperty._managedReferenceName;
        this._propertyIndex = settableBeanProperty._propertyIndex;
        this._valueDeserializer = jsonDeserializer;
        NullProvider nullProvider = null;
        if (jsonDeserializer == null) {
            this._nullProvider = null;
            return;
        }
        Object nullValue = jsonDeserializer.getNullValue();
        this._nullProvider = nullValue != null ? new NullProvider(this._type, nullValue) : nullProvider;
    }
}
