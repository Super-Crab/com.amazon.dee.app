package com.amazon.org.codehaus.jackson.map.deser.std;

import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.DeserializationConfig;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.TypeDeserializer;
import com.amazon.org.codehaus.jackson.node.ArrayNode;
import com.amazon.org.codehaus.jackson.node.JsonNodeFactory;
import com.amazon.org.codehaus.jackson.node.ObjectNode;
import java.io.IOException;
/* compiled from: JsonNodeDeserializer.java */
/* loaded from: classes13.dex */
abstract class BaseNodeDeserializer<N extends JsonNode> extends StdDeserializer<N> {

    /* compiled from: JsonNodeDeserializer.java */
    /* renamed from: com.amazon.org.codehaus.jackson.map.deser.std.BaseNodeDeserializer$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];

        static {
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.START_OBJECT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.START_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.END_ARRAY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NULL.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    public BaseNodeDeserializer(Class<N> cls) {
        super((Class<?>) cls);
    }

    protected void _handleDuplicateField(String str, ObjectNode objectNode, JsonNode jsonNode, JsonNode jsonNode2) throws JsonProcessingException {
    }

    protected void _reportProblem(JsonParser jsonParser, String str) throws JsonMappingException {
        throw new JsonMappingException(str, jsonParser.getTokenLocation());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonNode deserializeAny(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException, JsonProcessingException {
        int ordinal = jsonParser.getCurrentToken().ordinal();
        if (ordinal != 1) {
            if (ordinal != 3) {
                switch (ordinal) {
                    case 5:
                        return deserializeObject(jsonParser, deserializationContext, jsonNodeFactory);
                    case 6:
                        Object embeddedObject = jsonParser.getEmbeddedObject();
                        if (embeddedObject == null) {
                            return jsonNodeFactory.nullNode();
                        }
                        if (embeddedObject.getClass() == byte[].class) {
                            return jsonNodeFactory.binaryNode((byte[]) embeddedObject);
                        }
                        return jsonNodeFactory.POJONode(embeddedObject);
                    case 7:
                        return jsonNodeFactory.textNode(jsonParser.getText());
                    case 8:
                        JsonParser.NumberType numberType = jsonParser.getNumberType();
                        if (numberType != JsonParser.NumberType.BIG_INTEGER && !deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_INTEGER_FOR_INTS)) {
                            if (numberType == JsonParser.NumberType.INT) {
                                return jsonNodeFactory.numberNode(jsonParser.getIntValue());
                            }
                            return jsonNodeFactory.numberNode(jsonParser.getLongValue());
                        }
                        return jsonNodeFactory.numberNode(jsonParser.getBigIntegerValue());
                    case 9:
                        if (jsonParser.getNumberType() != JsonParser.NumberType.BIG_DECIMAL && !deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                            return jsonNodeFactory.numberNode(jsonParser.getDoubleValue());
                        }
                        return jsonNodeFactory.numberNode(jsonParser.getDecimalValue());
                    case 10:
                        return jsonNodeFactory.booleanNode(true);
                    case 11:
                        return jsonNodeFactory.booleanNode(false);
                    case 12:
                        return jsonNodeFactory.nullNode();
                    default:
                        throw deserializationContext.mappingException(getValueClass());
                }
            }
            return deserializeArray(jsonParser, deserializationContext, jsonNodeFactory);
        }
        return deserializeObject(jsonParser, deserializationContext, jsonNodeFactory);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ArrayNode deserializeArray(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException, JsonProcessingException {
        ArrayNode arrayNode = jsonNodeFactory.arrayNode();
        while (true) {
            int ordinal = jsonParser.nextToken().ordinal();
            if (ordinal == 1) {
                arrayNode.add(deserializeObject(jsonParser, deserializationContext, jsonNodeFactory));
            } else if (ordinal == 7) {
                arrayNode.add(jsonNodeFactory.textNode(jsonParser.getText()));
            } else if (ordinal == 3) {
                arrayNode.add(deserializeArray(jsonParser, deserializationContext, jsonNodeFactory));
            } else if (ordinal == 4) {
                return arrayNode;
            } else {
                arrayNode.add(deserializeAny(jsonParser, deserializationContext, jsonNodeFactory));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ObjectNode deserializeObject(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException, JsonProcessingException {
        JsonNode deserializeObject;
        ObjectNode objectNode = jsonNodeFactory.objectNode();
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            int ordinal = jsonParser.nextToken().ordinal();
            if (ordinal == 1) {
                deserializeObject = deserializeObject(jsonParser, deserializationContext, jsonNodeFactory);
            } else if (ordinal == 3) {
                deserializeObject = deserializeArray(jsonParser, deserializationContext, jsonNodeFactory);
            } else if (ordinal != 7) {
                deserializeObject = deserializeAny(jsonParser, deserializationContext, jsonNodeFactory);
            } else {
                deserializeObject = jsonNodeFactory.textNode(jsonParser.getText());
            }
            JsonNode put = objectNode.put(currentName, deserializeObject);
            if (put != null) {
                _handleDuplicateField(currentName, objectNode, put, deserializeObject);
            }
            currentToken = jsonParser.nextToken();
        }
        return objectNode;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdDeserializer, com.amazon.org.codehaus.jackson.map.JsonDeserializer
    /* renamed from: deserializeWithType */
    public Object mo4196deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }
}
