package com.amazon.org.codehaus.jackson.map.deser.std;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.TypeDeserializer;
import com.amazon.org.codehaus.jackson.map.util.EnumResolver;
import java.io.IOException;
import java.util.EnumMap;
/* loaded from: classes13.dex */
public class EnumMapDeserializer extends StdDeserializer<EnumMap<?, ?>> {
    protected final Class<?> _enumClass;
    protected final JsonDeserializer<Enum<?>> _keyDeserializer;
    protected final JsonDeserializer<Object> _valueDeserializer;

    @Deprecated
    public EnumMapDeserializer(EnumResolver<?> enumResolver, JsonDeserializer<Object> jsonDeserializer) {
        this(enumResolver.getEnumClass(), new EnumDeserializer(enumResolver), jsonDeserializer);
    }

    private EnumMap<?, ?> constructMap() {
        return new EnumMap<>(this._enumClass);
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdDeserializer, com.amazon.org.codehaus.jackson.map.JsonDeserializer
    /* renamed from: deserializeWithType */
    public Object mo4196deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public EnumMapDeserializer(Class<?> cls, JsonDeserializer<?> jsonDeserializer, JsonDeserializer<Object> jsonDeserializer2) {
        super(EnumMap.class);
        this._enumClass = cls;
        this._keyDeserializer = jsonDeserializer;
        this._valueDeserializer = jsonDeserializer2;
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
    /* renamed from: deserialize  reason: collision with other method in class */
    public EnumMap<?, ?> mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
            EnumMap<?, ?> constructMap = constructMap();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                Enum<?> mo4206deserialize = this._keyDeserializer.mo4206deserialize(jsonParser, deserializationContext);
                if (mo4206deserialize != null) {
                    constructMap.put((EnumMap<?, ?>) mo4206deserialize, (Enum<?>) (jsonParser.nextToken() == JsonToken.VALUE_NULL ? null : this._valueDeserializer.mo4206deserialize(jsonParser, deserializationContext)));
                } else {
                    throw deserializationContext.weirdStringException(this._enumClass, "value not one of declared Enum instance names");
                }
            }
            return constructMap;
        }
        throw deserializationContext.mappingException(EnumMap.class);
    }
}
