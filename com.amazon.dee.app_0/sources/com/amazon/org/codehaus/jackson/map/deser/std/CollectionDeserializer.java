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
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Collection;
@JacksonStdImpl
/* loaded from: classes13.dex */
public class CollectionDeserializer extends ContainerDeserializerBase<Collection<Object>> implements ResolvableDeserializer {
    protected final JavaType _collectionType;
    protected JsonDeserializer<Object> _delegateDeserializer;
    protected final JsonDeserializer<Object> _valueDeserializer;
    protected final ValueInstantiator _valueInstantiator;
    protected final TypeDeserializer _valueTypeDeserializer;

    /* JADX INFO: Access modifiers changed from: protected */
    @Deprecated
    public CollectionDeserializer(JavaType javaType, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer, Constructor<Collection<Object>> constructor) {
        super(javaType.getRawClass());
        this._collectionType = javaType;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = typeDeserializer;
        StdValueInstantiator stdValueInstantiator = new StdValueInstantiator((DeserializationConfig) null, javaType);
        if (constructor != null) {
            stdValueInstantiator.configureFromObjectSettings(new AnnotatedConstructor(constructor, null, null), null, null, null, null);
        }
        this._valueInstantiator = stdValueInstantiator;
    }

    private final Collection<Object> handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<Object> collection) throws IOException, JsonProcessingException {
        Object mo4196deserializeWithType;
        if (deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
            TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
                mo4196deserializeWithType = null;
            } else if (typeDeserializer == null) {
                mo4196deserializeWithType = jsonDeserializer.mo4206deserialize(jsonParser, deserializationContext);
            } else {
                mo4196deserializeWithType = jsonDeserializer.mo4196deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
            }
            collection.add(mo4196deserializeWithType);
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
        if (this._valueInstantiator.canCreateUsingDelegate()) {
            JavaType delegateType = this._valueInstantiator.getDelegateType();
            if (delegateType != null) {
                this._delegateDeserializer = findDeserializer(deserializationConfig, deserializerProvider, delegateType, new BeanProperty.Std(null, delegateType, null, this._valueInstantiator.getDelegateCreator()));
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid delegate-creator definition for ");
            outline107.append(this._collectionType);
            outline107.append(": value instantiator (");
            outline107.append(this._valueInstantiator.getClass().getName());
            outline107.append(") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'");
            throw new IllegalArgumentException(outline107.toString());
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
    /* renamed from: deserialize  reason: collision with other method in class */
    public Collection<Object> mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            return (Collection) this._valueInstantiator.createUsingDelegate(jsonDeserializer.mo4206deserialize(jsonParser, deserializationContext));
        }
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING) {
            String text = jsonParser.getText();
            if (text.length() == 0) {
                return (Collection) this._valueInstantiator.createFromString(text);
            }
        }
        return deserialize(jsonParser, deserializationContext, (Collection) this._valueInstantiator.createUsingDefault());
    }

    public CollectionDeserializer(JavaType javaType, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer, ValueInstantiator valueInstantiator) {
        super(javaType.getRawClass());
        this._collectionType = javaType;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = typeDeserializer;
        this._valueInstantiator = valueInstantiator;
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
    public Collection<Object> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<Object> collection) throws IOException, JsonProcessingException {
        Object mo4196deserializeWithType;
        if (!jsonParser.isExpectedStartArrayToken()) {
            return handleNonArray(jsonParser, deserializationContext, collection);
        }
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        while (true) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken == JsonToken.END_ARRAY) {
                return collection;
            }
            if (nextToken == JsonToken.VALUE_NULL) {
                mo4196deserializeWithType = null;
            } else if (typeDeserializer == null) {
                mo4196deserializeWithType = jsonDeserializer.mo4206deserialize(jsonParser, deserializationContext);
            } else {
                mo4196deserializeWithType = jsonDeserializer.mo4196deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
            }
            collection.add(mo4196deserializeWithType);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CollectionDeserializer(CollectionDeserializer collectionDeserializer) {
        super(collectionDeserializer._valueClass);
        this._collectionType = collectionDeserializer._collectionType;
        this._valueDeserializer = collectionDeserializer._valueDeserializer;
        this._valueTypeDeserializer = collectionDeserializer._valueTypeDeserializer;
        this._valueInstantiator = collectionDeserializer._valueInstantiator;
        this._delegateDeserializer = collectionDeserializer._delegateDeserializer;
    }
}
