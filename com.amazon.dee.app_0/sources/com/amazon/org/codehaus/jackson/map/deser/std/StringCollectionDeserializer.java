package com.amazon.org.codehaus.jackson.map.deser.std;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.DeserializationConfig;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.DeserializerProvider;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.ResolvableDeserializer;
import com.amazon.org.codehaus.jackson.map.TypeDeserializer;
import com.amazon.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.amazon.org.codehaus.jackson.map.deser.ValueInstantiator;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedWithParams;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.io.IOException;
import java.util.Collection;
@JacksonStdImpl
/* loaded from: classes13.dex */
public final class StringCollectionDeserializer extends ContainerDeserializerBase<Collection<String>> implements ResolvableDeserializer {
    protected final JavaType _collectionType;
    protected JsonDeserializer<Object> _delegateDeserializer;
    protected final boolean _isDefaultDeserializer;
    protected final JsonDeserializer<String> _valueDeserializer;
    protected final ValueInstantiator _valueInstantiator;

    /* JADX WARN: Multi-variable type inference failed */
    public StringCollectionDeserializer(JavaType javaType, JsonDeserializer<?> jsonDeserializer, ValueInstantiator valueInstantiator) {
        super(javaType.getRawClass());
        this._collectionType = javaType;
        this._valueDeserializer = jsonDeserializer;
        this._valueInstantiator = valueInstantiator;
        this._isDefaultDeserializer = isDefaultSerializer(jsonDeserializer);
    }

    private Collection<String> deserializeUsingCustom(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<String> collection) throws IOException, JsonProcessingException {
        JsonDeserializer<String> jsonDeserializer = this._valueDeserializer;
        while (true) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken != JsonToken.END_ARRAY) {
                collection.add(nextToken == JsonToken.VALUE_NULL ? null : jsonDeserializer.mo4206deserialize(jsonParser, deserializationContext));
            } else {
                return collection;
            }
        }
    }

    private final Collection<String> handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<String> collection) throws IOException, JsonProcessingException {
        String text;
        if (deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            JsonDeserializer<String> jsonDeserializer = this._valueDeserializer;
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
                text = null;
            } else {
                text = jsonDeserializer == null ? jsonParser.getText() : jsonDeserializer.mo4206deserialize(jsonParser, deserializationContext);
            }
            collection.add(text);
            return collection;
        }
        throw deserializationContext.mappingException(this._collectionType.getRawClass());
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdDeserializer, com.amazon.org.codehaus.jackson.map.JsonDeserializer
    /* renamed from: deserializeWithType */
    public Object mo4196deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.std.ContainerDeserializerBase
    public JsonDeserializer<Object> getContentDeserializer() {
        return this._valueDeserializer;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.std.ContainerDeserializerBase
    public JavaType getContentType() {
        return this._collectionType.getContentType();
    }

    @Override // com.amazon.org.codehaus.jackson.map.ResolvableDeserializer
    public void resolve(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider) throws JsonMappingException {
        AnnotatedWithParams delegateCreator = this._valueInstantiator.getDelegateCreator();
        if (delegateCreator != null) {
            JavaType delegateType = this._valueInstantiator.getDelegateType();
            this._delegateDeserializer = findDeserializer(deserializationConfig, deserializerProvider, delegateType, new BeanProperty.Std(null, delegateType, null, delegateCreator));
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
    /* renamed from: deserialize  reason: collision with other method in class */
    public Collection<String> mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            return (Collection) this._valueInstantiator.createUsingDelegate(jsonDeserializer.mo4206deserialize(jsonParser, deserializationContext));
        }
        return deserialize(jsonParser, deserializationContext, (Collection) this._valueInstantiator.createUsingDefault());
    }

    protected StringCollectionDeserializer(StringCollectionDeserializer stringCollectionDeserializer) {
        super(stringCollectionDeserializer._valueClass);
        this._collectionType = stringCollectionDeserializer._collectionType;
        this._valueDeserializer = stringCollectionDeserializer._valueDeserializer;
        this._valueInstantiator = stringCollectionDeserializer._valueInstantiator;
        this._isDefaultDeserializer = stringCollectionDeserializer._isDefaultDeserializer;
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
    public Collection<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<String> collection) throws IOException, JsonProcessingException {
        if (!jsonParser.isExpectedStartArrayToken()) {
            return handleNonArray(jsonParser, deserializationContext, collection);
        }
        if (!this._isDefaultDeserializer) {
            return deserializeUsingCustom(jsonParser, deserializationContext, collection);
        }
        while (true) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken == JsonToken.END_ARRAY) {
                return collection;
            }
            collection.add(nextToken == JsonToken.VALUE_NULL ? null : jsonParser.getText());
        }
    }
}
