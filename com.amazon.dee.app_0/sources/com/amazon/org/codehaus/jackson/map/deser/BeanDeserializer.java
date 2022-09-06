package com.amazon.org.codehaus.jackson.map.deser;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.amazon.org.codehaus.jackson.map.BeanDescription;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.DeserializationConfig;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.DeserializerProvider;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.ResolvableDeserializer;
import com.amazon.org.codehaus.jackson.map.TypeDeserializer;
import com.amazon.org.codehaus.jackson.map.annotate.JsonCachable;
import com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty;
import com.amazon.org.codehaus.jackson.map.deser.impl.BeanPropertyMap;
import com.amazon.org.codehaus.jackson.map.deser.impl.CreatorCollector;
import com.amazon.org.codehaus.jackson.map.deser.impl.ExternalTypeHandler;
import com.amazon.org.codehaus.jackson.map.deser.impl.PropertyBasedCreator;
import com.amazon.org.codehaus.jackson.map.deser.impl.PropertyValueBuffer;
import com.amazon.org.codehaus.jackson.map.deser.impl.UnwrappedPropertyHandler;
import com.amazon.org.codehaus.jackson.map.deser.impl.ValueInjector;
import com.amazon.org.codehaus.jackson.map.deser.std.ContainerDeserializerBase;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedClass;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.amazon.org.codehaus.jackson.map.type.ClassKey;
import com.amazon.org.codehaus.jackson.map.util.ClassUtil;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.amazon.org.codehaus.jackson.util.TokenBuffer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
@JsonCachable
/* loaded from: classes13.dex */
public class BeanDeserializer extends com.amazon.org.codehaus.jackson.map.deser.std.StdDeserializer<Object> implements ResolvableDeserializer {
    protected SettableAnyProperty _anySetter;
    protected final Map<String, SettableBeanProperty> _backRefs;
    protected final BeanPropertyMap _beanProperties;
    protected final JavaType _beanType;
    protected JsonDeserializer<Object> _delegateDeserializer;
    protected ExternalTypeHandler _externalTypeIdHandler;
    protected final AnnotatedClass _forClass;
    protected final HashSet<String> _ignorableProps;
    protected final boolean _ignoreAllUnknown;
    protected final ValueInjector[] _injectables;
    protected boolean _nonStandardCreation;
    protected final BeanProperty _property;
    protected final PropertyBasedCreator _propertyBasedCreator;
    protected HashMap<ClassKey, JsonDeserializer<Object>> _subDeserializers;
    protected UnwrappedPropertyHandler _unwrappedPropertyHandler;
    protected final ValueInstantiator _valueInstantiator;

    /* renamed from: com.amazon.org.codehaus.jackson.map.deser.BeanDeserializer$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$org$codehaus$jackson$JsonParser$NumberType = new int[JsonParser.NumberType.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken;

        static {
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonParser$NumberType[JsonParser.NumberType.INT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonParser$NumberType[JsonParser.NumberType.LONG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonParser$NumberType[JsonParser.NumberType.FLOAT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonParser$NumberType[JsonParser.NumberType.DOUBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.START_ARRAY.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 8;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    @Deprecated
    public BeanDeserializer(AnnotatedClass annotatedClass, JavaType javaType, BeanProperty beanProperty, CreatorCollector creatorCollector, BeanPropertyMap beanPropertyMap, Map<String, SettableBeanProperty> map, HashSet<String> hashSet, boolean z, SettableAnyProperty settableAnyProperty) {
        this(annotatedClass, javaType, beanProperty, creatorCollector.constructValueInstantiator(null), beanPropertyMap, map, hashSet, z, settableAnyProperty, null);
    }

    private final void _handleUnknown(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws IOException, JsonProcessingException {
        HashSet<String> hashSet = this._ignorableProps;
        if (hashSet != null && hashSet.contains(str)) {
            jsonParser.skipChildren();
            return;
        }
        SettableAnyProperty settableAnyProperty = this._anySetter;
        if (settableAnyProperty != null) {
            try {
                settableAnyProperty.deserializeAndSet(jsonParser, deserializationContext, obj, str);
                return;
            } catch (Exception e) {
                wrapAndThrow(e, obj, str, deserializationContext);
                return;
            }
        }
        handleUnknownProperty(jsonParser, deserializationContext, obj, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Object _deserializeUsingPropertyBased(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        PropertyBasedCreator propertyBasedCreator = this._propertyBasedCreator;
        PropertyValueBuffer startBuilding = propertyBasedCreator.startBuilding(jsonParser, deserializationContext);
        JsonToken currentToken = jsonParser.getCurrentToken();
        TokenBuffer tokenBuffer = null;
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            SettableBeanProperty findCreatorProperty = propertyBasedCreator.findCreatorProperty(currentName);
            if (findCreatorProperty != null) {
                if (startBuilding.assignParameter(findCreatorProperty.getPropertyIndex(), findCreatorProperty.deserialize(jsonParser, deserializationContext))) {
                    jsonParser.nextToken();
                    try {
                        Object build = propertyBasedCreator.build(startBuilding);
                        if (build.getClass() != this._beanType.getRawClass()) {
                            return handlePolymorphic(jsonParser, deserializationContext, build, tokenBuffer);
                        }
                        if (tokenBuffer != null) {
                            build = handleUnknownProperties(deserializationContext, build, tokenBuffer);
                        }
                        return deserialize(jsonParser, deserializationContext, build);
                    } catch (Exception e) {
                        wrapAndThrow(e, this._beanType.getRawClass(), currentName, deserializationContext);
                    }
                } else {
                    continue;
                }
            } else {
                SettableBeanProperty find = this._beanProperties.find(currentName);
                if (find != null) {
                    startBuilding.bufferProperty(find, find.deserialize(jsonParser, deserializationContext));
                } else {
                    HashSet<String> hashSet = this._ignorableProps;
                    if (hashSet != null && hashSet.contains(currentName)) {
                        jsonParser.skipChildren();
                    } else {
                        SettableAnyProperty settableAnyProperty = this._anySetter;
                        if (settableAnyProperty != null) {
                            startBuilding.bufferAnyProperty(settableAnyProperty, currentName, settableAnyProperty.deserialize(jsonParser, deserializationContext));
                        } else {
                            if (tokenBuffer == null) {
                                tokenBuffer = new TokenBuffer(jsonParser.getCodec());
                            }
                            tokenBuffer.writeFieldName(currentName);
                            tokenBuffer.copyCurrentStructure(jsonParser);
                        }
                    }
                }
            }
            currentToken = jsonParser.nextToken();
        }
        try {
            Object build2 = propertyBasedCreator.build(startBuilding);
            if (tokenBuffer == null) {
                return build2;
            }
            if (build2.getClass() != this._beanType.getRawClass()) {
                return handlePolymorphic(null, deserializationContext, build2, tokenBuffer);
            }
            return handleUnknownProperties(deserializationContext, build2, tokenBuffer);
        } catch (Exception e2) {
            wrapInstantiationProblem(e2, deserializationContext);
            return null;
        }
    }

    protected JsonDeserializer<Object> _findSubclassDeserializer(DeserializationContext deserializationContext, Object obj, TokenBuffer tokenBuffer) throws IOException, JsonProcessingException {
        JsonDeserializer<Object> jsonDeserializer;
        synchronized (this) {
            jsonDeserializer = this._subDeserializers == null ? null : this._subDeserializers.get(new ClassKey(obj.getClass()));
        }
        if (jsonDeserializer != null) {
            return jsonDeserializer;
        }
        DeserializerProvider deserializerProvider = deserializationContext.getDeserializerProvider();
        if (deserializerProvider != null) {
            jsonDeserializer = deserializerProvider.findValueDeserializer(deserializationContext.getConfig(), deserializationContext.constructType(obj.getClass()), this._property);
            if (jsonDeserializer != null) {
                synchronized (this) {
                    if (this._subDeserializers == null) {
                        this._subDeserializers = new HashMap<>();
                    }
                    this._subDeserializers.put(new ClassKey(obj.getClass()), jsonDeserializer);
                }
            }
        }
        return jsonDeserializer;
    }

    protected SettableBeanProperty _resolveInnerClassValuedProperty(DeserializationConfig deserializationConfig, SettableBeanProperty settableBeanProperty) {
        Class<?> rawClass;
        Class<?> outerClass;
        Constructor<?>[] constructors;
        JsonDeserializer<Object> valueDeserializer = settableBeanProperty.getValueDeserializer();
        if ((valueDeserializer instanceof BeanDeserializer) && !((BeanDeserializer) valueDeserializer).getValueInstantiator().canCreateUsingDefault() && (outerClass = ClassUtil.getOuterClass((rawClass = settableBeanProperty.getType().getRawClass()))) != null && outerClass == this._beanType.getRawClass()) {
            for (Constructor<?> constructor : rawClass.getConstructors()) {
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                if (parameterTypes.length == 1 && parameterTypes[0] == outerClass) {
                    if (deserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                        ClassUtil.checkAndFixAccess(constructor);
                    }
                    return new SettableBeanProperty.InnerClassProperty(settableBeanProperty, constructor);
                }
            }
        }
        return settableBeanProperty;
    }

    protected SettableBeanProperty _resolveManagedReferenceProperty(DeserializationConfig deserializationConfig, SettableBeanProperty settableBeanProperty) {
        SettableBeanProperty findBackReference;
        String managedReferenceName = settableBeanProperty.getManagedReferenceName();
        if (managedReferenceName == null) {
            return settableBeanProperty;
        }
        JsonDeserializer<Object> valueDeserializer = settableBeanProperty.getValueDeserializer();
        boolean z = false;
        if (valueDeserializer instanceof BeanDeserializer) {
            findBackReference = ((BeanDeserializer) valueDeserializer).findBackReference(managedReferenceName);
        } else if (valueDeserializer instanceof ContainerDeserializerBase) {
            JsonDeserializer<Object> contentDeserializer = ((ContainerDeserializerBase) valueDeserializer).getContentDeserializer();
            if (contentDeserializer instanceof BeanDeserializer) {
                findBackReference = ((BeanDeserializer) contentDeserializer).findBackReference(managedReferenceName);
                z = true;
            } else {
                throw new IllegalArgumentException("Can not handle managed/back reference '" + managedReferenceName + "': value deserializer is of type ContainerDeserializerBase, but content type is not handled by a BeanDeserializer  (instead it's of type " + contentDeserializer.getClass().getName() + ")");
            }
        } else if (valueDeserializer instanceof AbstractDeserializer) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can not handle managed/back reference for abstract types (property ");
            outline107.append(this._beanType.getRawClass().getName());
            outline107.append(".");
            outline107.append(settableBeanProperty.getName());
            outline107.append(")");
            throw new IllegalArgumentException(outline107.toString());
        } else {
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Can not handle managed/back reference '", managedReferenceName, "': type for value deserializer is not BeanDeserializer or ContainerDeserializerBase, but ");
            outline115.append(valueDeserializer.getClass().getName());
            throw new IllegalArgumentException(outline115.toString());
        }
        boolean z2 = z;
        if (findBackReference != null) {
            JavaType javaType = this._beanType;
            JavaType type = findBackReference.getType();
            if (type.getRawClass().isAssignableFrom(javaType.getRawClass())) {
                return new SettableBeanProperty.ManagedReferenceProperty(managedReferenceName, settableBeanProperty, findBackReference, this._forClass.getAnnotations(), z2);
            }
            StringBuilder outline1152 = GeneratedOutlineSupport1.outline115("Can not handle managed/back reference '", managedReferenceName, "': back reference type (");
            outline1152.append(type.getRawClass().getName());
            outline1152.append(") not compatible with managed type (");
            outline1152.append(javaType.getRawClass().getName());
            outline1152.append(")");
            throw new IllegalArgumentException(outline1152.toString());
        }
        StringBuilder outline1153 = GeneratedOutlineSupport1.outline115("Can not handle managed/back reference '", managedReferenceName, "': no back reference property found from type ");
        outline1153.append(settableBeanProperty.getType());
        throw new IllegalArgumentException(outline1153.toString());
    }

    protected SettableBeanProperty _resolveUnwrappedProperty(DeserializationConfig deserializationConfig, SettableBeanProperty settableBeanProperty) {
        JsonDeserializer<Object> valueDeserializer;
        JsonDeserializer<Object> unwrappingDeserializer;
        AnnotatedMember member = settableBeanProperty.getMember();
        if (member == null || deserializationConfig.getAnnotationIntrospector().shouldUnwrapProperty(member) != Boolean.TRUE || (unwrappingDeserializer = (valueDeserializer = settableBeanProperty.getValueDeserializer()).unwrappingDeserializer()) == valueDeserializer || unwrappingDeserializer == null) {
            return null;
        }
        return settableBeanProperty.mo4140withValueDeserializer(unwrappingDeserializer);
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
    /* renamed from: deserialize */
    public final Object mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            jsonParser.nextToken();
            return deserializeFromObject(jsonParser, deserializationContext);
        }
        switch (currentToken.ordinal()) {
            case 2:
            case 5:
                return deserializeFromObject(jsonParser, deserializationContext);
            case 3:
                return deserializeFromArray(jsonParser, deserializationContext);
            case 4:
            default:
                throw deserializationContext.mappingException(getBeanClass());
            case 6:
                return jsonParser.getEmbeddedObject();
            case 7:
                return deserializeFromString(jsonParser, deserializationContext);
            case 8:
                return deserializeFromNumber(jsonParser, deserializationContext);
            case 9:
                return deserializeFromDouble(jsonParser, deserializationContext);
            case 10:
            case 11:
                return deserializeFromBoolean(jsonParser, deserializationContext);
        }
    }

    public Object deserializeFromArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            try {
                Object createUsingDelegate = this._valueInstantiator.createUsingDelegate(jsonDeserializer.mo4206deserialize(jsonParser, deserializationContext));
                if (this._injectables != null) {
                    injectValues(deserializationContext, createUsingDelegate);
                }
                return createUsingDelegate;
            } catch (Exception e) {
                wrapInstantiationProblem(e, deserializationContext);
            }
        }
        throw deserializationContext.mappingException(getBeanClass());
    }

    public Object deserializeFromBoolean(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (this._delegateDeserializer != null && !this._valueInstantiator.canCreateFromBoolean()) {
            Object createUsingDelegate = this._valueInstantiator.createUsingDelegate(this._delegateDeserializer.mo4206deserialize(jsonParser, deserializationContext));
            if (this._injectables != null) {
                injectValues(deserializationContext, createUsingDelegate);
            }
            return createUsingDelegate;
        }
        return this._valueInstantiator.createFromBoolean(jsonParser.getCurrentToken() == JsonToken.VALUE_TRUE);
    }

    public Object deserializeFromDouble(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        int ordinal = jsonParser.getNumberType().ordinal();
        if (ordinal != 3 && ordinal != 4) {
            JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
            if (jsonDeserializer != null) {
                return this._valueInstantiator.createUsingDelegate(jsonDeserializer.mo4206deserialize(jsonParser, deserializationContext));
            }
            throw deserializationContext.instantiationException(getBeanClass(), "no suitable creator method found to deserialize from JSON floating-point number");
        } else if (this._delegateDeserializer != null && !this._valueInstantiator.canCreateFromDouble()) {
            Object createUsingDelegate = this._valueInstantiator.createUsingDelegate(this._delegateDeserializer.mo4206deserialize(jsonParser, deserializationContext));
            if (this._injectables != null) {
                injectValues(deserializationContext, createUsingDelegate);
            }
            return createUsingDelegate;
        } else {
            return this._valueInstantiator.createFromDouble(jsonParser.getDoubleValue());
        }
    }

    public Object deserializeFromNumber(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        int ordinal = jsonParser.getNumberType().ordinal();
        if (ordinal == 0) {
            if (this._delegateDeserializer != null && !this._valueInstantiator.canCreateFromInt()) {
                Object createUsingDelegate = this._valueInstantiator.createUsingDelegate(this._delegateDeserializer.mo4206deserialize(jsonParser, deserializationContext));
                if (this._injectables != null) {
                    injectValues(deserializationContext, createUsingDelegate);
                }
                return createUsingDelegate;
            }
            return this._valueInstantiator.createFromInt(jsonParser.getIntValue());
        } else if (ordinal != 1) {
            JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
            if (jsonDeserializer != null) {
                Object createUsingDelegate2 = this._valueInstantiator.createUsingDelegate(jsonDeserializer.mo4206deserialize(jsonParser, deserializationContext));
                if (this._injectables != null) {
                    injectValues(deserializationContext, createUsingDelegate2);
                }
                return createUsingDelegate2;
            }
            throw deserializationContext.instantiationException(getBeanClass(), "no suitable creator method found to deserialize from JSON integer number");
        } else if (this._delegateDeserializer != null && !this._valueInstantiator.canCreateFromInt()) {
            Object createUsingDelegate3 = this._valueInstantiator.createUsingDelegate(this._delegateDeserializer.mo4206deserialize(jsonParser, deserializationContext));
            if (this._injectables != null) {
                injectValues(deserializationContext, createUsingDelegate3);
            }
            return createUsingDelegate3;
        } else {
            return this._valueInstantiator.createFromLong(jsonParser.getLongValue());
        }
    }

    public Object deserializeFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (this._nonStandardCreation) {
            if (this._unwrappedPropertyHandler != null) {
                return deserializeWithUnwrapped(jsonParser, deserializationContext);
            }
            if (this._externalTypeIdHandler != null) {
                return deserializeWithExternalTypeId(jsonParser, deserializationContext);
            }
            return deserializeFromObjectUsingNonDefault(jsonParser, deserializationContext);
        }
        Object createUsingDefault = this._valueInstantiator.createUsingDefault();
        if (this._injectables != null) {
            injectValues(deserializationContext, createUsingDefault);
        }
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            SettableBeanProperty find = this._beanProperties.find(currentName);
            if (find != null) {
                try {
                    find.deserializeAndSet(jsonParser, deserializationContext, createUsingDefault);
                } catch (Exception e) {
                    wrapAndThrow(e, createUsingDefault, currentName, deserializationContext);
                }
            } else {
                _handleUnknown(jsonParser, deserializationContext, createUsingDefault, currentName);
            }
            jsonParser.nextToken();
        }
        return createUsingDefault;
    }

    protected Object deserializeFromObjectUsingNonDefault(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            return this._valueInstantiator.createUsingDelegate(jsonDeserializer.mo4206deserialize(jsonParser, deserializationContext));
        }
        if (this._propertyBasedCreator != null) {
            return _deserializeUsingPropertyBased(jsonParser, deserializationContext);
        }
        if (this._beanType.isAbstract()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can not instantiate abstract type ");
            outline107.append(this._beanType);
            outline107.append(" (need to add/enable type information?)");
            throw JsonMappingException.from(jsonParser, outline107.toString());
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("No suitable constructor found for type ");
        outline1072.append(this._beanType);
        outline1072.append(": can not instantiate from JSON object (need to add/enable type information?)");
        throw JsonMappingException.from(jsonParser, outline1072.toString());
    }

    public Object deserializeFromString(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (this._delegateDeserializer != null && !this._valueInstantiator.canCreateFromString()) {
            Object createUsingDelegate = this._valueInstantiator.createUsingDelegate(this._delegateDeserializer.mo4206deserialize(jsonParser, deserializationContext));
            if (this._injectables != null) {
                injectValues(deserializationContext, createUsingDelegate);
            }
            return createUsingDelegate;
        }
        return this._valueInstantiator.createFromString(jsonParser.getText());
    }

    protected Object deserializeUsingPropertyBasedWithExternalTypeId(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ExternalTypeHandler start = this._externalTypeIdHandler.start();
        PropertyBasedCreator propertyBasedCreator = this._propertyBasedCreator;
        PropertyValueBuffer startBuilding = propertyBasedCreator.startBuilding(jsonParser, deserializationContext);
        TokenBuffer tokenBuffer = new TokenBuffer(jsonParser.getCodec());
        tokenBuffer.writeStartObject();
        JsonToken currentToken = jsonParser.getCurrentToken();
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            SettableBeanProperty findCreatorProperty = propertyBasedCreator.findCreatorProperty(currentName);
            if (findCreatorProperty != null) {
                if (startBuilding.assignParameter(findCreatorProperty.getPropertyIndex(), findCreatorProperty.deserialize(jsonParser, deserializationContext))) {
                    JsonToken nextToken = jsonParser.nextToken();
                    try {
                        Object build = propertyBasedCreator.build(startBuilding);
                        while (nextToken == JsonToken.FIELD_NAME) {
                            jsonParser.nextToken();
                            tokenBuffer.copyCurrentStructure(jsonParser);
                            nextToken = jsonParser.nextToken();
                        }
                        if (build.getClass() == this._beanType.getRawClass()) {
                            return start.complete(jsonParser, deserializationContext, build);
                        }
                        throw deserializationContext.mappingException("Can not create polymorphic instances with unwrapped values");
                    } catch (Exception e) {
                        wrapAndThrow(e, this._beanType.getRawClass(), currentName, deserializationContext);
                    }
                } else {
                    continue;
                }
            } else {
                SettableBeanProperty find = this._beanProperties.find(currentName);
                if (find != null) {
                    startBuilding.bufferProperty(find, find.deserialize(jsonParser, deserializationContext));
                } else if (!start.handleToken(jsonParser, deserializationContext, currentName, null)) {
                    HashSet<String> hashSet = this._ignorableProps;
                    if (hashSet != null && hashSet.contains(currentName)) {
                        jsonParser.skipChildren();
                    } else {
                        SettableAnyProperty settableAnyProperty = this._anySetter;
                        if (settableAnyProperty != null) {
                            startBuilding.bufferAnyProperty(settableAnyProperty, currentName, settableAnyProperty.deserialize(jsonParser, deserializationContext));
                        }
                    }
                }
            }
            currentToken = jsonParser.nextToken();
        }
        try {
            return start.complete(jsonParser, deserializationContext, propertyBasedCreator.build(startBuilding));
        } catch (Exception e2) {
            wrapInstantiationProblem(e2, deserializationContext);
            return null;
        }
    }

    protected Object deserializeUsingPropertyBasedWithUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        PropertyBasedCreator propertyBasedCreator = this._propertyBasedCreator;
        PropertyValueBuffer startBuilding = propertyBasedCreator.startBuilding(jsonParser, deserializationContext);
        TokenBuffer tokenBuffer = new TokenBuffer(jsonParser.getCodec());
        tokenBuffer.writeStartObject();
        JsonToken currentToken = jsonParser.getCurrentToken();
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            SettableBeanProperty findCreatorProperty = propertyBasedCreator.findCreatorProperty(currentName);
            if (findCreatorProperty != null) {
                if (startBuilding.assignParameter(findCreatorProperty.getPropertyIndex(), findCreatorProperty.deserialize(jsonParser, deserializationContext))) {
                    JsonToken nextToken = jsonParser.nextToken();
                    try {
                        Object build = propertyBasedCreator.build(startBuilding);
                        while (nextToken == JsonToken.FIELD_NAME) {
                            jsonParser.nextToken();
                            tokenBuffer.copyCurrentStructure(jsonParser);
                            nextToken = jsonParser.nextToken();
                        }
                        tokenBuffer.writeEndObject();
                        if (build.getClass() == this._beanType.getRawClass()) {
                            return this._unwrappedPropertyHandler.processUnwrapped(jsonParser, deserializationContext, build, tokenBuffer);
                        }
                        throw deserializationContext.mappingException("Can not create polymorphic instances with unwrapped values");
                    } catch (Exception e) {
                        wrapAndThrow(e, this._beanType.getRawClass(), currentName, deserializationContext);
                    }
                } else {
                    continue;
                }
            } else {
                SettableBeanProperty find = this._beanProperties.find(currentName);
                if (find != null) {
                    startBuilding.bufferProperty(find, find.deserialize(jsonParser, deserializationContext));
                } else {
                    HashSet<String> hashSet = this._ignorableProps;
                    if (hashSet != null && hashSet.contains(currentName)) {
                        jsonParser.skipChildren();
                    } else {
                        tokenBuffer.writeFieldName(currentName);
                        tokenBuffer.copyCurrentStructure(jsonParser);
                        SettableAnyProperty settableAnyProperty = this._anySetter;
                        if (settableAnyProperty != null) {
                            startBuilding.bufferAnyProperty(settableAnyProperty, currentName, settableAnyProperty.deserialize(jsonParser, deserializationContext));
                        }
                    }
                }
            }
            currentToken = jsonParser.nextToken();
        }
        try {
            return this._unwrappedPropertyHandler.processUnwrapped(jsonParser, deserializationContext, propertyBasedCreator.build(startBuilding), tokenBuffer);
        } catch (Exception e2) {
            wrapInstantiationProblem(e2, deserializationContext);
            return null;
        }
    }

    protected Object deserializeWithExternalTypeId(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (this._propertyBasedCreator != null) {
            return deserializeUsingPropertyBasedWithExternalTypeId(jsonParser, deserializationContext);
        }
        return deserializeWithExternalTypeId(jsonParser, deserializationContext, this._valueInstantiator.createUsingDefault());
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdDeserializer, com.amazon.org.codehaus.jackson.map.JsonDeserializer
    /* renamed from: deserializeWithType */
    public Object mo4196deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }

    protected Object deserializeWithUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            return this._valueInstantiator.createUsingDelegate(jsonDeserializer.mo4206deserialize(jsonParser, deserializationContext));
        }
        if (this._propertyBasedCreator != null) {
            return deserializeUsingPropertyBasedWithUnwrapped(jsonParser, deserializationContext);
        }
        TokenBuffer tokenBuffer = new TokenBuffer(jsonParser.getCodec());
        tokenBuffer.writeStartObject();
        Object createUsingDefault = this._valueInstantiator.createUsingDefault();
        if (this._injectables != null) {
            injectValues(deserializationContext, createUsingDefault);
        }
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            SettableBeanProperty find = this._beanProperties.find(currentName);
            if (find != null) {
                try {
                    find.deserializeAndSet(jsonParser, deserializationContext, createUsingDefault);
                } catch (Exception e) {
                    wrapAndThrow(e, createUsingDefault, currentName, deserializationContext);
                }
            } else {
                HashSet<String> hashSet = this._ignorableProps;
                if (hashSet != null && hashSet.contains(currentName)) {
                    jsonParser.skipChildren();
                } else {
                    tokenBuffer.writeFieldName(currentName);
                    tokenBuffer.copyCurrentStructure(jsonParser);
                    SettableAnyProperty settableAnyProperty = this._anySetter;
                    if (settableAnyProperty != null) {
                        try {
                            settableAnyProperty.deserializeAndSet(jsonParser, deserializationContext, createUsingDefault, currentName);
                        } catch (Exception e2) {
                            wrapAndThrow(e2, createUsingDefault, currentName, deserializationContext);
                        }
                    }
                }
            }
            jsonParser.nextToken();
        }
        tokenBuffer.writeEndObject();
        this._unwrappedPropertyHandler.processUnwrapped(jsonParser, deserializationContext, createUsingDefault, tokenBuffer);
        return createUsingDefault;
    }

    public SettableBeanProperty findBackReference(String str) {
        Map<String, SettableBeanProperty> map = this._backRefs;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public final Class<?> getBeanClass() {
        return this._beanType.getRawClass();
    }

    public int getPropertyCount() {
        return this._beanProperties.size();
    }

    public ValueInstantiator getValueInstantiator() {
        return this._valueInstantiator;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdDeserializer
    public JavaType getValueType() {
        return this._beanType;
    }

    protected Object handlePolymorphic(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, TokenBuffer tokenBuffer) throws IOException, JsonProcessingException {
        JsonDeserializer<Object> _findSubclassDeserializer = _findSubclassDeserializer(deserializationContext, obj, tokenBuffer);
        if (_findSubclassDeserializer == null) {
            if (tokenBuffer != null) {
                obj = handleUnknownProperties(deserializationContext, obj, tokenBuffer);
            }
            return jsonParser != null ? deserialize(jsonParser, deserializationContext, obj) : obj;
        }
        if (tokenBuffer != null) {
            tokenBuffer.writeEndObject();
            JsonParser asParser = tokenBuffer.asParser();
            asParser.nextToken();
            obj = _findSubclassDeserializer.deserialize(asParser, deserializationContext, obj);
        }
        return jsonParser != null ? _findSubclassDeserializer.deserialize(jsonParser, deserializationContext, obj) : obj;
    }

    protected Object handleUnknownProperties(DeserializationContext deserializationContext, Object obj, TokenBuffer tokenBuffer) throws IOException, JsonProcessingException {
        tokenBuffer.writeEndObject();
        JsonParser asParser = tokenBuffer.asParser();
        while (asParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = asParser.getCurrentName();
            asParser.nextToken();
            handleUnknownProperty(asParser, deserializationContext, obj, currentName);
        }
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdDeserializer
    public void handleUnknownProperty(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws IOException, JsonProcessingException {
        HashSet<String> hashSet;
        if (!this._ignoreAllUnknown && ((hashSet = this._ignorableProps) == null || !hashSet.contains(str))) {
            super.handleUnknownProperty(jsonParser, deserializationContext, obj, str);
        } else {
            jsonParser.skipChildren();
        }
    }

    public boolean hasProperty(String str) {
        return this._beanProperties.find(str) != null;
    }

    protected void injectValues(DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        for (ValueInjector valueInjector : this._injectables) {
            valueInjector.inject(deserializationContext, obj);
        }
    }

    public Iterator<SettableBeanProperty> properties() {
        BeanPropertyMap beanPropertyMap = this._beanProperties;
        if (beanPropertyMap != null) {
            return beanPropertyMap.allProperties();
        }
        throw new IllegalStateException("Can only call before BeanDeserializer has been resolved");
    }

    @Override // com.amazon.org.codehaus.jackson.map.ResolvableDeserializer
    public void resolve(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider) throws JsonMappingException {
        Iterator<SettableBeanProperty> allProperties = this._beanProperties.allProperties();
        UnwrappedPropertyHandler unwrappedPropertyHandler = null;
        ExternalTypeHandler.Builder builder = null;
        while (allProperties.hasNext()) {
            SettableBeanProperty next = allProperties.next();
            SettableBeanProperty _resolveManagedReferenceProperty = _resolveManagedReferenceProperty(deserializationConfig, !next.hasValueDeserializer() ? next.mo4140withValueDeserializer(findDeserializer(deserializationConfig, deserializerProvider, next.getType(), next)) : next);
            SettableBeanProperty _resolveUnwrappedProperty = _resolveUnwrappedProperty(deserializationConfig, _resolveManagedReferenceProperty);
            if (_resolveUnwrappedProperty != null) {
                if (unwrappedPropertyHandler == null) {
                    unwrappedPropertyHandler = new UnwrappedPropertyHandler();
                }
                unwrappedPropertyHandler.addProperty(_resolveUnwrappedProperty);
                _resolveManagedReferenceProperty = _resolveUnwrappedProperty;
            }
            SettableBeanProperty _resolveInnerClassValuedProperty = _resolveInnerClassValuedProperty(deserializationConfig, _resolveManagedReferenceProperty);
            if (_resolveInnerClassValuedProperty != next) {
                this._beanProperties.replace(_resolveInnerClassValuedProperty);
            }
            if (_resolveInnerClassValuedProperty.hasValueTypeDeserializer()) {
                TypeDeserializer valueTypeDeserializer = _resolveInnerClassValuedProperty.getValueTypeDeserializer();
                if (valueTypeDeserializer.getTypeInclusion() == JsonTypeInfo.As.EXTERNAL_PROPERTY) {
                    if (builder == null) {
                        builder = new ExternalTypeHandler.Builder();
                    }
                    builder.addExternal(_resolveInnerClassValuedProperty, valueTypeDeserializer.getPropertyName());
                    this._beanProperties.remove(_resolveInnerClassValuedProperty);
                }
            }
        }
        SettableAnyProperty settableAnyProperty = this._anySetter;
        if (settableAnyProperty != null && !settableAnyProperty.hasValueDeserializer()) {
            SettableAnyProperty settableAnyProperty2 = this._anySetter;
            this._anySetter = settableAnyProperty2.withValueDeserializer(findDeserializer(deserializationConfig, deserializerProvider, settableAnyProperty2.getType(), this._anySetter.getProperty()));
        }
        if (this._valueInstantiator.canCreateUsingDelegate()) {
            JavaType delegateType = this._valueInstantiator.getDelegateType();
            if (delegateType != null) {
                this._delegateDeserializer = findDeserializer(deserializationConfig, deserializerProvider, delegateType, new BeanProperty.Std(null, delegateType, this._forClass.getAnnotations(), this._valueInstantiator.getDelegateCreator()));
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid delegate-creator definition for ");
                outline107.append(this._beanType);
                outline107.append(": value instantiator (");
                outline107.append(this._valueInstantiator.getClass().getName());
                outline107.append(") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'");
                throw new IllegalArgumentException(outline107.toString());
            }
        }
        PropertyBasedCreator propertyBasedCreator = this._propertyBasedCreator;
        if (propertyBasedCreator != null) {
            for (SettableBeanProperty settableBeanProperty : propertyBasedCreator.getCreatorProperties()) {
                if (!settableBeanProperty.hasValueDeserializer()) {
                    this._propertyBasedCreator.assignDeserializer(settableBeanProperty, findDeserializer(deserializationConfig, deserializerProvider, settableBeanProperty.getType(), settableBeanProperty));
                }
            }
        }
        if (builder != null) {
            this._externalTypeIdHandler = builder.build();
            this._nonStandardCreation = true;
        }
        this._unwrappedPropertyHandler = unwrappedPropertyHandler;
        if (unwrappedPropertyHandler != null) {
            this._nonStandardCreation = true;
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
    public JsonDeserializer<Object> unwrappingDeserializer() {
        return getClass() != BeanDeserializer.class ? this : new BeanDeserializer(this, true);
    }

    public void wrapAndThrow(Throwable th, Object obj, String str, DeserializationContext deserializationContext) throws IOException {
        while ((th instanceof InvocationTargetException) && th.getCause() != null) {
            th = th.getCause();
        }
        if (!(th instanceof Error)) {
            boolean z = deserializationContext == null || deserializationContext.isEnabled(DeserializationConfig.Feature.WRAP_EXCEPTIONS);
            if (th instanceof IOException) {
                if (!z || !(th instanceof JsonMappingException)) {
                    throw ((IOException) th);
                }
            } else if (!z && (th instanceof RuntimeException)) {
                throw ((RuntimeException) th);
            }
            throw JsonMappingException.wrapWithPath(th, obj, str);
        }
        throw ((Error) th);
    }

    protected void wrapInstantiationProblem(Throwable th, DeserializationContext deserializationContext) throws IOException {
        while ((th instanceof InvocationTargetException) && th.getCause() != null) {
            th = th.getCause();
        }
        if (!(th instanceof Error)) {
            boolean z = deserializationContext == null || deserializationContext.isEnabled(DeserializationConfig.Feature.WRAP_EXCEPTIONS);
            if (!(th instanceof IOException)) {
                if (!z && (th instanceof RuntimeException)) {
                    throw ((RuntimeException) th);
                }
                throw deserializationContext.instantiationException(this._beanType.getRawClass(), th);
            }
            throw ((IOException) th);
        }
        throw ((Error) th);
    }

    public BeanDeserializer(BeanDescription beanDescription, BeanProperty beanProperty, ValueInstantiator valueInstantiator, BeanPropertyMap beanPropertyMap, Map<String, SettableBeanProperty> map, HashSet<String> hashSet, boolean z, SettableAnyProperty settableAnyProperty, List<ValueInjector> list) {
        this(beanDescription.getClassInfo(), beanDescription.getType(), beanProperty, valueInstantiator, beanPropertyMap, map, hashSet, z, settableAnyProperty, list);
    }

    protected BeanDeserializer(AnnotatedClass annotatedClass, JavaType javaType, BeanProperty beanProperty, ValueInstantiator valueInstantiator, BeanPropertyMap beanPropertyMap, Map<String, SettableBeanProperty> map, HashSet<String> hashSet, boolean z, SettableAnyProperty settableAnyProperty, List<ValueInjector> list) {
        super(javaType);
        this._forClass = annotatedClass;
        this._beanType = javaType;
        this._property = beanProperty;
        this._valueInstantiator = valueInstantiator;
        ValueInjector[] valueInjectorArr = null;
        if (valueInstantiator.canCreateFromObjectWith()) {
            this._propertyBasedCreator = new PropertyBasedCreator(valueInstantiator);
        } else {
            this._propertyBasedCreator = null;
        }
        this._beanProperties = beanPropertyMap;
        this._backRefs = map;
        this._ignorableProps = hashSet;
        this._ignoreAllUnknown = z;
        this._anySetter = settableAnyProperty;
        if (list != null && !list.isEmpty()) {
            valueInjectorArr = (ValueInjector[]) list.toArray(new ValueInjector[list.size()]);
        }
        this._injectables = valueInjectorArr;
        this._nonStandardCreation = valueInstantiator.canCreateUsingDelegate() || this._propertyBasedCreator != null || !valueInstantiator.canCreateUsingDefault() || this._unwrappedPropertyHandler != null;
    }

    protected Object deserializeWithExternalTypeId(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        ExternalTypeHandler start = this._externalTypeIdHandler.start();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            SettableBeanProperty find = this._beanProperties.find(currentName);
            if (find != null) {
                if (jsonParser.getCurrentToken().isScalarValue()) {
                    start.handleTypePropertyValue(jsonParser, deserializationContext, currentName, obj);
                }
                try {
                    find.deserializeAndSet(jsonParser, deserializationContext, obj);
                } catch (Exception e) {
                    wrapAndThrow(e, obj, currentName, deserializationContext);
                }
            } else {
                HashSet<String> hashSet = this._ignorableProps;
                if (hashSet != null && hashSet.contains(currentName)) {
                    jsonParser.skipChildren();
                } else if (!start.handleToken(jsonParser, deserializationContext, currentName, obj)) {
                    SettableAnyProperty settableAnyProperty = this._anySetter;
                    if (settableAnyProperty != null) {
                        try {
                            settableAnyProperty.deserializeAndSet(jsonParser, deserializationContext, obj, currentName);
                        } catch (Exception e2) {
                            wrapAndThrow(e2, obj, currentName, deserializationContext);
                        }
                    } else {
                        handleUnknownProperty(jsonParser, deserializationContext, obj, currentName);
                    }
                }
            }
            jsonParser.nextToken();
        }
        return start.complete(jsonParser, deserializationContext, obj);
    }

    public void wrapAndThrow(Throwable th, Object obj, int i, DeserializationContext deserializationContext) throws IOException {
        while ((th instanceof InvocationTargetException) && th.getCause() != null) {
            th = th.getCause();
        }
        if (!(th instanceof Error)) {
            boolean z = deserializationContext == null || deserializationContext.isEnabled(DeserializationConfig.Feature.WRAP_EXCEPTIONS);
            if (th instanceof IOException) {
                if (!z || !(th instanceof JsonMappingException)) {
                    throw ((IOException) th);
                }
            } else if (!z && (th instanceof RuntimeException)) {
                throw ((RuntimeException) th);
            }
            throw JsonMappingException.wrapWithPath(th, obj, i);
        }
        throw ((Error) th);
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        if (this._injectables != null) {
            injectValues(deserializationContext, obj);
        }
        if (this._unwrappedPropertyHandler != null) {
            return deserializeWithUnwrapped(jsonParser, deserializationContext, obj);
        }
        if (this._externalTypeIdHandler != null) {
            return deserializeWithExternalTypeId(jsonParser, deserializationContext, obj);
        }
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            SettableBeanProperty find = this._beanProperties.find(currentName);
            if (find != null) {
                try {
                    find.deserializeAndSet(jsonParser, deserializationContext, obj);
                } catch (Exception e) {
                    wrapAndThrow(e, obj, currentName, deserializationContext);
                }
            } else {
                HashSet<String> hashSet = this._ignorableProps;
                if (hashSet != null && hashSet.contains(currentName)) {
                    jsonParser.skipChildren();
                } else {
                    SettableAnyProperty settableAnyProperty = this._anySetter;
                    if (settableAnyProperty != null) {
                        settableAnyProperty.deserializeAndSet(jsonParser, deserializationContext, obj, currentName);
                    } else {
                        handleUnknownProperty(jsonParser, deserializationContext, obj, currentName);
                    }
                }
            }
            currentToken = jsonParser.nextToken();
        }
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BeanDeserializer(BeanDeserializer beanDeserializer) {
        this(beanDeserializer, beanDeserializer._ignoreAllUnknown);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BeanDeserializer(BeanDeserializer beanDeserializer, boolean z) {
        super(beanDeserializer._beanType);
        this._forClass = beanDeserializer._forClass;
        this._beanType = beanDeserializer._beanType;
        this._property = beanDeserializer._property;
        this._valueInstantiator = beanDeserializer._valueInstantiator;
        this._delegateDeserializer = beanDeserializer._delegateDeserializer;
        this._propertyBasedCreator = beanDeserializer._propertyBasedCreator;
        this._beanProperties = beanDeserializer._beanProperties;
        this._backRefs = beanDeserializer._backRefs;
        this._ignorableProps = beanDeserializer._ignorableProps;
        this._ignoreAllUnknown = z;
        this._anySetter = beanDeserializer._anySetter;
        this._injectables = beanDeserializer._injectables;
        this._nonStandardCreation = beanDeserializer._nonStandardCreation;
        this._unwrappedPropertyHandler = beanDeserializer._unwrappedPropertyHandler;
    }

    @Deprecated
    public void wrapAndThrow(Throwable th, Object obj, String str) throws IOException {
        wrapAndThrow(th, obj, str, (DeserializationContext) null);
    }

    @Deprecated
    public void wrapAndThrow(Throwable th, Object obj, int i) throws IOException {
        wrapAndThrow(th, obj, i, (DeserializationContext) null);
    }

    protected Object deserializeWithUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        TokenBuffer tokenBuffer = new TokenBuffer(jsonParser.getCodec());
        tokenBuffer.writeStartObject();
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            SettableBeanProperty find = this._beanProperties.find(currentName);
            jsonParser.nextToken();
            if (find != null) {
                try {
                    find.deserializeAndSet(jsonParser, deserializationContext, obj);
                } catch (Exception e) {
                    wrapAndThrow(e, obj, currentName, deserializationContext);
                }
            } else {
                HashSet<String> hashSet = this._ignorableProps;
                if (hashSet != null && hashSet.contains(currentName)) {
                    jsonParser.skipChildren();
                } else {
                    tokenBuffer.writeFieldName(currentName);
                    tokenBuffer.copyCurrentStructure(jsonParser);
                    SettableAnyProperty settableAnyProperty = this._anySetter;
                    if (settableAnyProperty != null) {
                        settableAnyProperty.deserializeAndSet(jsonParser, deserializationContext, obj, currentName);
                    }
                }
            }
            currentToken = jsonParser.nextToken();
        }
        tokenBuffer.writeEndObject();
        this._unwrappedPropertyHandler.processUnwrapped(jsonParser, deserializationContext, obj, tokenBuffer);
        return obj;
    }
}
