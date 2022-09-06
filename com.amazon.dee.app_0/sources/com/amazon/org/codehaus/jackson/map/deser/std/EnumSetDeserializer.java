package com.amazon.org.codehaus.jackson.map.deser.std;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.TypeDeserializer;
import com.amazon.org.codehaus.jackson.map.util.EnumResolver;
import java.io.IOException;
import java.util.EnumSet;
/* loaded from: classes13.dex */
public class EnumSetDeserializer extends StdDeserializer<EnumSet<?>> {
    protected final Class<Enum> _enumClass;
    protected final JsonDeserializer<Enum<?>> _enumDeserializer;

    public EnumSetDeserializer(EnumResolver enumResolver) {
        this(enumResolver.getEnumClass(), new EnumDeserializer(enumResolver));
    }

    private EnumSet constructSet() {
        return EnumSet.noneOf(this._enumClass);
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdDeserializer, com.amazon.org.codehaus.jackson.map.JsonDeserializer
    /* renamed from: deserializeWithType */
    public Object mo4196deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public EnumSetDeserializer(Class<?> cls, JsonDeserializer<?> jsonDeserializer) {
        super(EnumSet.class);
        this._enumClass = cls;
        this._enumDeserializer = jsonDeserializer;
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
    /* renamed from: deserialize  reason: collision with other method in class */
    public EnumSet<?> mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (jsonParser.isExpectedStartArrayToken()) {
            EnumSet<?> constructSet = constructSet();
            while (true) {
                JsonToken nextToken = jsonParser.nextToken();
                if (nextToken == JsonToken.END_ARRAY) {
                    return constructSet;
                }
                if (nextToken != JsonToken.VALUE_NULL) {
                    constructSet.add(this._enumDeserializer.mo4206deserialize(jsonParser, deserializationContext));
                } else {
                    throw deserializationContext.mappingException(this._enumClass);
                }
            }
        } else {
            throw deserializationContext.mappingException(EnumSet.class);
        }
    }
}
