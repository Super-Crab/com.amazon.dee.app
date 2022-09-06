package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.util.EnumMap;
/* loaded from: classes2.dex */
public class EnumMapDeserializer extends ContainerDeserializerBase<EnumMap<?, ?>> implements ContextualDeserializer, ResolvableDeserializer {
    private static final long serialVersionUID = 1;
    protected JsonDeserializer<Object> _delegateDeserializer;
    protected final Class<?> _enumClass;
    protected KeyDeserializer _keyDeserializer;
    protected PropertyBasedCreator _propertyBasedCreator;
    protected JsonDeserializer<Object> _valueDeserializer;
    protected final ValueInstantiator _valueInstantiator;
    protected final TypeDeserializer _valueTypeDeserializer;

    public EnumMapDeserializer(JavaType javaType, ValueInstantiator valueInstantiator, KeyDeserializer keyDeserializer, JsonDeserializer<?> jsonDeserializer, TypeDeserializer typeDeserializer, NullValueProvider nullValueProvider) {
        super(javaType, nullValueProvider, (Boolean) null);
        this._enumClass = javaType.mo7229getKeyType().getRawClass();
        this._keyDeserializer = keyDeserializer;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = typeDeserializer;
        this._valueInstantiator = valueInstantiator;
    }

    public EnumMap<?, ?> _deserializeUsingProperties(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String currentName;
        Object mo7107deserializeWithType;
        PropertyBasedCreator propertyBasedCreator = this._propertyBasedCreator;
        PropertyValueBuffer startBuilding = propertyBasedCreator.startBuilding(jsonParser, deserializationContext, null);
        if (jsonParser.isExpectedStartObjectToken()) {
            currentName = jsonParser.nextFieldName();
        } else {
            currentName = jsonParser.hasToken(JsonToken.FIELD_NAME) ? jsonParser.currentName() : null;
        }
        while (currentName != null) {
            JsonToken nextToken = jsonParser.nextToken();
            SettableBeanProperty findCreatorProperty = propertyBasedCreator.findCreatorProperty(currentName);
            if (findCreatorProperty != null) {
                if (startBuilding.assignParameter(findCreatorProperty, findCreatorProperty.deserialize(jsonParser, deserializationContext))) {
                    jsonParser.nextToken();
                    try {
                        return deserialize(jsonParser, deserializationContext, (EnumMap) propertyBasedCreator.build(deserializationContext, startBuilding));
                    } catch (Exception e) {
                        return (EnumMap) wrapAndThrow(deserializationContext, e, this._containerType.getRawClass(), currentName);
                    }
                }
            } else {
                Enum r5 = (Enum) this._keyDeserializer.deserializeKey(currentName, deserializationContext);
                if (r5 == null) {
                    if (!deserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                        return (EnumMap) deserializationContext.handleWeirdStringValue(this._enumClass, currentName, "value not one of declared Enum instance names for %s", this._containerType.mo7229getKeyType());
                    }
                    jsonParser.nextToken();
                    jsonParser.skipChildren();
                } else {
                    try {
                        if (nextToken == JsonToken.VALUE_NULL) {
                            if (!this._skipNullValues) {
                                mo7107deserializeWithType = this._nullProvider.mo7075getNullValue(deserializationContext);
                            }
                        } else if (this._valueTypeDeserializer == null) {
                            mo7107deserializeWithType = this._valueDeserializer.mo7111deserialize(jsonParser, deserializationContext);
                        } else {
                            mo7107deserializeWithType = this._valueDeserializer.mo7107deserializeWithType(jsonParser, deserializationContext, this._valueTypeDeserializer);
                        }
                        startBuilding.bufferMapProperty(r5, mo7107deserializeWithType);
                    } catch (Exception e2) {
                        wrapAndThrow(deserializationContext, e2, this._containerType.getRawClass(), currentName);
                        return null;
                    }
                }
            }
            currentName = jsonParser.nextFieldName();
        }
        try {
            return (EnumMap) propertyBasedCreator.build(deserializationContext, startBuilding);
        } catch (Exception e3) {
            wrapAndThrow(deserializationContext, e3, this._containerType.getRawClass(), currentName);
            return null;
        }
    }

    protected EnumMap<?, ?> constructMap(DeserializationContext deserializationContext) throws JsonMappingException {
        ValueInstantiator valueInstantiator = this._valueInstantiator;
        if (valueInstantiator == null) {
            return new EnumMap<>(this._enumClass);
        }
        try {
            if (!valueInstantiator.canCreateUsingDefault()) {
                return (EnumMap) deserializationContext.handleMissingInstantiator(handledType(), getValueInstantiator(), null, "no default constructor found", new Object[0]);
            }
            return (EnumMap) this._valueInstantiator.createUsingDefault(deserializationContext);
        } catch (IOException e) {
            return (EnumMap) ClassUtil.throwAsMappingException(deserializationContext, e);
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.ContextualDeserializer
    /* renamed from: createContextual */
    public JsonDeserializer<?> mo7066createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<?> handleSecondaryContextualization;
        KeyDeserializer keyDeserializer = this._keyDeserializer;
        if (keyDeserializer == null) {
            keyDeserializer = deserializationContext.findKeyDeserializer(this._containerType.mo7229getKeyType(), beanProperty);
        }
        JsonDeserializer<?> jsonDeserializer = this._valueDeserializer;
        JavaType mo7243getContentType = this._containerType.mo7243getContentType();
        if (jsonDeserializer == null) {
            handleSecondaryContextualization = deserializationContext.findContextualValueDeserializer(mo7243getContentType, beanProperty);
        } else {
            handleSecondaryContextualization = deserializationContext.handleSecondaryContextualization(jsonDeserializer, beanProperty, mo7243getContentType);
        }
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        if (typeDeserializer != null) {
            typeDeserializer = typeDeserializer.forProperty(beanProperty);
        }
        return withResolved(keyDeserializer, handleSecondaryContextualization, typeDeserializer, findContentNullProvider(deserializationContext, beanProperty, handleSecondaryContextualization));
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    /* renamed from: deserializeWithType */
    public Object mo7107deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase
    public JsonDeserializer<Object> getContentDeserializer() {
        return this._valueDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public Object getEmptyValue(DeserializationContext deserializationContext) throws JsonMappingException {
        return constructMap(deserializationContext);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.deser.ValueInstantiator.Gettable
    public ValueInstantiator getValueInstantiator() {
        return this._valueInstantiator;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public boolean isCachable() {
        return this._valueDeserializer == null && this._keyDeserializer == null && this._valueTypeDeserializer == null;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public LogicalType logicalType() {
        return LogicalType.Map;
    }

    @Override // com.fasterxml.jackson.databind.deser.ResolvableDeserializer
    public void resolve(DeserializationContext deserializationContext) throws JsonMappingException {
        ValueInstantiator valueInstantiator = this._valueInstantiator;
        if (valueInstantiator != null) {
            if (valueInstantiator.canCreateUsingDelegate()) {
                JavaType delegateType = this._valueInstantiator.getDelegateType(deserializationContext.mo7051getConfig());
                if (delegateType == null) {
                    JavaType javaType = this._containerType;
                    deserializationContext.reportBadDefinition(javaType, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'", javaType, this._valueInstantiator.getClass().getName()));
                }
                this._delegateDeserializer = findDeserializer(deserializationContext, delegateType, null);
            } else if (this._valueInstantiator.canCreateUsingArrayDelegate()) {
                JavaType arrayDelegateType = this._valueInstantiator.getArrayDelegateType(deserializationContext.mo7051getConfig());
                if (arrayDelegateType == null) {
                    JavaType javaType2 = this._containerType;
                    deserializationContext.reportBadDefinition(javaType2, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingArrayDelegate()', but null for 'getArrayDelegateType()'", javaType2, this._valueInstantiator.getClass().getName()));
                }
                this._delegateDeserializer = findDeserializer(deserializationContext, arrayDelegateType, null);
            } else if (!this._valueInstantiator.canCreateFromObjectWith()) {
            } else {
                this._propertyBasedCreator = PropertyBasedCreator.construct(deserializationContext, this._valueInstantiator, this._valueInstantiator.getFromObjectArguments(deserializationContext.mo7051getConfig()), deserializationContext.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES));
            }
        }
    }

    public EnumMapDeserializer withResolved(KeyDeserializer keyDeserializer, JsonDeserializer<?> jsonDeserializer, TypeDeserializer typeDeserializer, NullValueProvider nullValueProvider) {
        return (keyDeserializer == this._keyDeserializer && nullValueProvider == this._nullProvider && jsonDeserializer == this._valueDeserializer && typeDeserializer == this._valueTypeDeserializer) ? this : new EnumMapDeserializer(this, keyDeserializer, jsonDeserializer, typeDeserializer, nullValueProvider);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    /* renamed from: deserialize  reason: collision with other method in class */
    public EnumMap<?, ?> mo7111deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (this._propertyBasedCreator != null) {
            return _deserializeUsingProperties(jsonParser, deserializationContext);
        }
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            return (EnumMap) this._valueInstantiator.createUsingDelegate(deserializationContext, jsonDeserializer.mo7111deserialize(jsonParser, deserializationContext));
        }
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId != 1 && currentTokenId != 2) {
            if (currentTokenId == 3) {
                return _deserializeFromArray(jsonParser, deserializationContext);
            }
            if (currentTokenId != 5) {
                if (currentTokenId != 6) {
                    return (EnumMap) deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
                }
                return _deserializeFromString(jsonParser, deserializationContext);
            }
        }
        return deserialize(jsonParser, deserializationContext, (EnumMap) constructMap(deserializationContext));
    }

    protected EnumMapDeserializer(EnumMapDeserializer enumMapDeserializer, KeyDeserializer keyDeserializer, JsonDeserializer<?> jsonDeserializer, TypeDeserializer typeDeserializer, NullValueProvider nullValueProvider) {
        super(enumMapDeserializer, nullValueProvider, enumMapDeserializer._unwrapSingle);
        this._enumClass = enumMapDeserializer._enumClass;
        this._keyDeserializer = keyDeserializer;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = typeDeserializer;
        this._valueInstantiator = enumMapDeserializer._valueInstantiator;
        this._delegateDeserializer = enumMapDeserializer._delegateDeserializer;
        this._propertyBasedCreator = enumMapDeserializer._propertyBasedCreator;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public EnumMap<?, ?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, EnumMap enumMap) throws IOException {
        String currentName;
        Object mo7107deserializeWithType;
        jsonParser.setCurrentValue(enumMap);
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        if (jsonParser.isExpectedStartObjectToken()) {
            currentName = jsonParser.nextFieldName();
        } else {
            JsonToken currentToken = jsonParser.currentToken();
            if (currentToken != JsonToken.FIELD_NAME) {
                if (currentToken == JsonToken.END_OBJECT) {
                    return enumMap;
                }
                deserializationContext.reportWrongTokenException(this, JsonToken.FIELD_NAME, (String) null, new Object[0]);
            }
            currentName = jsonParser.currentName();
        }
        while (currentName != null) {
            Enum r4 = (Enum) this._keyDeserializer.deserializeKey(currentName, deserializationContext);
            JsonToken nextToken = jsonParser.nextToken();
            if (r4 == null) {
                if (!deserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                    return (EnumMap) deserializationContext.handleWeirdStringValue(this._enumClass, currentName, "value not one of declared Enum instance names for %s", this._containerType.mo7229getKeyType());
                }
                jsonParser.skipChildren();
            } else {
                try {
                    if (nextToken == JsonToken.VALUE_NULL) {
                        if (!this._skipNullValues) {
                            mo7107deserializeWithType = this._nullProvider.mo7075getNullValue(deserializationContext);
                        }
                    } else if (typeDeserializer == null) {
                        mo7107deserializeWithType = jsonDeserializer.mo7111deserialize(jsonParser, deserializationContext);
                    } else {
                        mo7107deserializeWithType = jsonDeserializer.mo7107deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
                    }
                    enumMap.put((EnumMap) r4, (Enum) mo7107deserializeWithType);
                } catch (Exception e) {
                    return (EnumMap) wrapAndThrow(deserializationContext, e, enumMap, currentName);
                }
            }
            currentName = jsonParser.nextFieldName();
        }
        return enumMap;
    }

    @Deprecated
    public EnumMapDeserializer(JavaType javaType, KeyDeserializer keyDeserializer, JsonDeserializer<?> jsonDeserializer, TypeDeserializer typeDeserializer) {
        this(javaType, null, keyDeserializer, jsonDeserializer, typeDeserializer, null);
    }
}
