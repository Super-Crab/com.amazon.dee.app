package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.RawValue;
import java.io.IOException;
/* compiled from: JsonNodeDeserializer.java */
/* loaded from: classes2.dex */
abstract class BaseNodeDeserializer<T extends JsonNode> extends StdDeserializer<T> {
    protected final Boolean _supportsUpdates;

    public BaseNodeDeserializer(Class<T> cls, Boolean bool) {
        super((Class<?>) cls);
        this._supportsUpdates = bool;
    }

    protected final JsonNode _fromEmbedded(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException {
        Object embeddedObject = jsonParser.getEmbeddedObject();
        if (embeddedObject == null) {
            return jsonNodeFactory.mo7176nullNode();
        }
        if (embeddedObject.getClass() == byte[].class) {
            return jsonNodeFactory.mo7173binaryNode((byte[]) embeddedObject);
        }
        if (embeddedObject instanceof RawValue) {
            return jsonNodeFactory.rawValueNode((RawValue) embeddedObject);
        }
        if (embeddedObject instanceof JsonNode) {
            return (JsonNode) embeddedObject;
        }
        return jsonNodeFactory.pojoNode(embeddedObject);
    }

    protected final JsonNode _fromFloat(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException {
        JsonParser.NumberType numberType = jsonParser.getNumberType();
        if (numberType == JsonParser.NumberType.BIG_DECIMAL) {
            return jsonNodeFactory.numberNode(jsonParser.getDecimalValue());
        }
        if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
            if (jsonParser.isNaN()) {
                return jsonNodeFactory.mo7178numberNode(jsonParser.getDoubleValue());
            }
            return jsonNodeFactory.numberNode(jsonParser.getDecimalValue());
        } else if (numberType == JsonParser.NumberType.FLOAT) {
            return jsonNodeFactory.mo7179numberNode(jsonParser.getFloatValue());
        } else {
            return jsonNodeFactory.mo7178numberNode(jsonParser.getDoubleValue());
        }
    }

    protected final JsonNode _fromInt(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException {
        JsonParser.NumberType numberType;
        int deserializationFeatures = deserializationContext.getDeserializationFeatures();
        if ((StdDeserializer.F_MASK_INT_COERCIONS & deserializationFeatures) != 0) {
            if (DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.enabledIn(deserializationFeatures)) {
                numberType = JsonParser.NumberType.BIG_INTEGER;
            } else if (DeserializationFeature.USE_LONG_FOR_INTS.enabledIn(deserializationFeatures)) {
                numberType = JsonParser.NumberType.LONG;
            } else {
                numberType = jsonParser.getNumberType();
            }
        } else {
            numberType = jsonParser.getNumberType();
        }
        if (numberType == JsonParser.NumberType.INT) {
            return jsonNodeFactory.mo7180numberNode(jsonParser.getIntValue());
        }
        if (numberType == JsonParser.NumberType.LONG) {
            return jsonNodeFactory.mo7181numberNode(jsonParser.getLongValue());
        }
        return jsonNodeFactory.numberNode(jsonParser.getBigIntegerValue());
    }

    protected void _handleDuplicateField(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory, String str, ObjectNode objectNode, JsonNode jsonNode, JsonNode jsonNode2) throws JsonProcessingException {
        if (deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY)) {
            deserializationContext.reportInputMismatch(JsonNode.class, "Duplicate field '%s' for `ObjectNode`: not allowed when `DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY` enabled", str);
        }
        if (deserializationContext.isEnabled(StreamReadCapability.DUPLICATE_PROPERTIES)) {
            if (jsonNode.isArray()) {
                ((ArrayNode) jsonNode).add(jsonNode2);
                objectNode.replace(str, jsonNode);
                return;
            }
            ArrayNode arrayNode = jsonNodeFactory.arrayNode();
            arrayNode.add(jsonNode);
            arrayNode.add(jsonNode2);
            objectNode.replace(str, arrayNode);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonNode deserializeAny(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException {
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId != 2) {
            switch (currentTokenId) {
                case 5:
                    return deserializeObjectAtName(jsonParser, deserializationContext, jsonNodeFactory);
                case 6:
                    return jsonNodeFactory.mo7183textNode(jsonParser.getText());
                case 7:
                    return _fromInt(jsonParser, deserializationContext, jsonNodeFactory);
                case 8:
                    return _fromFloat(jsonParser, deserializationContext, jsonNodeFactory);
                case 9:
                    return jsonNodeFactory.mo7175booleanNode(true);
                case 10:
                    return jsonNodeFactory.mo7175booleanNode(false);
                case 11:
                    return jsonNodeFactory.mo7176nullNode();
                case 12:
                    return _fromEmbedded(jsonParser, deserializationContext, jsonNodeFactory);
                default:
                    return (JsonNode) deserializationContext.handleUnexpectedToken(handledType(), jsonParser);
            }
        }
        return jsonNodeFactory.objectNode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ArrayNode deserializeArray(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException {
        ArrayNode arrayNode = jsonNodeFactory.arrayNode();
        while (true) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken != null) {
                switch (nextToken.id()) {
                    case 1:
                        arrayNode.add(deserializeObject(jsonParser, deserializationContext, jsonNodeFactory));
                        break;
                    case 2:
                    case 5:
                    case 8:
                    default:
                        arrayNode.add(deserializeAny(jsonParser, deserializationContext, jsonNodeFactory));
                        break;
                    case 3:
                        arrayNode.add(deserializeArray(jsonParser, deserializationContext, jsonNodeFactory));
                        break;
                    case 4:
                        return arrayNode;
                    case 6:
                        arrayNode.add(jsonNodeFactory.mo7183textNode(jsonParser.getText()));
                        break;
                    case 7:
                        arrayNode.add(_fromInt(jsonParser, deserializationContext, jsonNodeFactory));
                        break;
                    case 9:
                        arrayNode.add(jsonNodeFactory.mo7175booleanNode(true));
                        break;
                    case 10:
                        arrayNode.add(jsonNodeFactory.mo7175booleanNode(false));
                        break;
                    case 11:
                        arrayNode.add(jsonNodeFactory.mo7176nullNode());
                        break;
                    case 12:
                        arrayNode.add(_fromEmbedded(jsonParser, deserializationContext, jsonNodeFactory));
                        break;
                }
            } else {
                return arrayNode;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ObjectNode deserializeObject(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException {
        JsonNode deserializeObject;
        ObjectNode objectNode = jsonNodeFactory.objectNode();
        String nextFieldName = jsonParser.nextFieldName();
        while (nextFieldName != null) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken == null) {
                nextToken = JsonToken.NOT_AVAILABLE;
            }
            int id = nextToken.id();
            if (id == 1) {
                deserializeObject = deserializeObject(jsonParser, deserializationContext, jsonNodeFactory);
            } else if (id == 3) {
                deserializeObject = deserializeArray(jsonParser, deserializationContext, jsonNodeFactory);
            } else if (id == 6) {
                deserializeObject = jsonNodeFactory.mo7183textNode(jsonParser.getText());
            } else if (id != 7) {
                switch (id) {
                    case 9:
                        deserializeObject = jsonNodeFactory.mo7175booleanNode(true);
                        break;
                    case 10:
                        deserializeObject = jsonNodeFactory.mo7175booleanNode(false);
                        break;
                    case 11:
                        deserializeObject = jsonNodeFactory.mo7176nullNode();
                        break;
                    case 12:
                        deserializeObject = _fromEmbedded(jsonParser, deserializationContext, jsonNodeFactory);
                        break;
                    default:
                        deserializeObject = deserializeAny(jsonParser, deserializationContext, jsonNodeFactory);
                        break;
                }
            } else {
                deserializeObject = _fromInt(jsonParser, deserializationContext, jsonNodeFactory);
            }
            JsonNode jsonNode = deserializeObject;
            JsonNode replace = objectNode.replace(nextFieldName, jsonNode);
            if (replace != null) {
                _handleDuplicateField(jsonParser, deserializationContext, jsonNodeFactory, nextFieldName, objectNode, replace, jsonNode);
            }
            nextFieldName = jsonParser.nextFieldName();
        }
        return objectNode;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ObjectNode deserializeObjectAtName(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException {
        JsonNode deserializeObject;
        ObjectNode objectNode = jsonNodeFactory.objectNode();
        String currentName = jsonParser.currentName();
        while (currentName != null) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken == null) {
                nextToken = JsonToken.NOT_AVAILABLE;
            }
            int id = nextToken.id();
            if (id == 1) {
                deserializeObject = deserializeObject(jsonParser, deserializationContext, jsonNodeFactory);
            } else if (id == 3) {
                deserializeObject = deserializeArray(jsonParser, deserializationContext, jsonNodeFactory);
            } else if (id == 6) {
                deserializeObject = jsonNodeFactory.mo7183textNode(jsonParser.getText());
            } else if (id != 7) {
                switch (id) {
                    case 9:
                        deserializeObject = jsonNodeFactory.mo7175booleanNode(true);
                        break;
                    case 10:
                        deserializeObject = jsonNodeFactory.mo7175booleanNode(false);
                        break;
                    case 11:
                        deserializeObject = jsonNodeFactory.mo7176nullNode();
                        break;
                    case 12:
                        deserializeObject = _fromEmbedded(jsonParser, deserializationContext, jsonNodeFactory);
                        break;
                    default:
                        deserializeObject = deserializeAny(jsonParser, deserializationContext, jsonNodeFactory);
                        break;
                }
            } else {
                deserializeObject = _fromInt(jsonParser, deserializationContext, jsonNodeFactory);
            }
            JsonNode jsonNode = deserializeObject;
            JsonNode replace = objectNode.replace(currentName, jsonNode);
            if (replace != null) {
                _handleDuplicateField(jsonParser, deserializationContext, jsonNodeFactory, currentName, objectNode, replace, jsonNode);
            }
            currentName = jsonParser.nextFieldName();
        }
        return objectNode;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    /* renamed from: deserializeWithType */
    public Object mo7107deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public boolean isCachable() {
        return true;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public LogicalType logicalType() {
        return LogicalType.Untyped;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return this._supportsUpdates;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonNode updateArray(JsonParser jsonParser, DeserializationContext deserializationContext, ArrayNode arrayNode) throws IOException {
        JsonNodeFactory nodeFactory = deserializationContext.getNodeFactory();
        while (true) {
            switch (jsonParser.nextToken().id()) {
                case 1:
                    arrayNode.add(deserializeObject(jsonParser, deserializationContext, nodeFactory));
                    break;
                case 2:
                case 5:
                case 8:
                default:
                    arrayNode.add(deserializeAny(jsonParser, deserializationContext, nodeFactory));
                    break;
                case 3:
                    arrayNode.add(deserializeArray(jsonParser, deserializationContext, nodeFactory));
                    break;
                case 4:
                    return arrayNode;
                case 6:
                    arrayNode.add(nodeFactory.mo7183textNode(jsonParser.getText()));
                    break;
                case 7:
                    arrayNode.add(_fromInt(jsonParser, deserializationContext, nodeFactory));
                    break;
                case 9:
                    arrayNode.add(nodeFactory.mo7175booleanNode(true));
                    break;
                case 10:
                    arrayNode.add(nodeFactory.mo7175booleanNode(false));
                    break;
                case 11:
                    arrayNode.add(nodeFactory.mo7176nullNode());
                    break;
                case 12:
                    arrayNode.add(_fromEmbedded(jsonParser, deserializationContext, nodeFactory));
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonNode updateObject(JsonParser jsonParser, DeserializationContext deserializationContext, ObjectNode objectNode) throws IOException {
        String currentName;
        JsonNode deserializeObject;
        if (jsonParser.isExpectedStartObjectToken()) {
            currentName = jsonParser.nextFieldName();
        } else if (!jsonParser.hasToken(JsonToken.FIELD_NAME)) {
            return (JsonNode) mo7111deserialize(jsonParser, deserializationContext);
        } else {
            currentName = jsonParser.currentName();
        }
        while (currentName != null) {
            JsonToken nextToken = jsonParser.nextToken();
            JsonNode mo7196get = objectNode.mo7196get(currentName);
            if (mo7196get != null) {
                if (mo7196get instanceof ObjectNode) {
                    if (nextToken == JsonToken.START_OBJECT) {
                        JsonNode updateObject = updateObject(jsonParser, deserializationContext, (ObjectNode) mo7196get);
                        if (updateObject != mo7196get) {
                            objectNode.set(currentName, updateObject);
                        }
                    }
                } else if ((mo7196get instanceof ArrayNode) && nextToken == JsonToken.START_ARRAY) {
                    JsonNode updateArray = updateArray(jsonParser, deserializationContext, (ArrayNode) mo7196get);
                    if (updateArray != mo7196get) {
                        objectNode.set(currentName, updateArray);
                    }
                }
                currentName = jsonParser.nextFieldName();
            }
            if (nextToken == null) {
                nextToken = JsonToken.NOT_AVAILABLE;
            }
            JsonNodeFactory nodeFactory = deserializationContext.getNodeFactory();
            int id = nextToken.id();
            if (id == 1) {
                deserializeObject = deserializeObject(jsonParser, deserializationContext, nodeFactory);
            } else if (id == 3) {
                deserializeObject = deserializeArray(jsonParser, deserializationContext, nodeFactory);
            } else if (id == 6) {
                deserializeObject = nodeFactory.mo7183textNode(jsonParser.getText());
            } else if (id != 7) {
                switch (id) {
                    case 9:
                        deserializeObject = nodeFactory.mo7175booleanNode(true);
                        break;
                    case 10:
                        deserializeObject = nodeFactory.mo7175booleanNode(false);
                        break;
                    case 11:
                        deserializeObject = nodeFactory.mo7176nullNode();
                        break;
                    case 12:
                        deserializeObject = _fromEmbedded(jsonParser, deserializationContext, nodeFactory);
                        break;
                    default:
                        deserializeObject = deserializeAny(jsonParser, deserializationContext, nodeFactory);
                        break;
                }
            } else {
                deserializeObject = _fromInt(jsonParser, deserializationContext, nodeFactory);
            }
            objectNode.set(currentName, deserializeObject);
            currentName = jsonParser.nextFieldName();
        }
        return objectNode;
    }
}
