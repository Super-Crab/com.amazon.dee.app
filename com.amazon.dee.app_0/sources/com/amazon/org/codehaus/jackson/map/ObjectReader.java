package com.amazon.org.codehaus.jackson.map;

import com.amazon.org.codehaus.jackson.FormatSchema;
import com.amazon.org.codehaus.jackson.JsonFactory;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.JsonParseException;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.ObjectCodec;
import com.amazon.org.codehaus.jackson.Version;
import com.amazon.org.codehaus.jackson.Versioned;
import com.amazon.org.codehaus.jackson.io.SerializedString;
import com.amazon.org.codehaus.jackson.map.DeserializationConfig;
import com.amazon.org.codehaus.jackson.map.deser.StdDeserializationContext;
import com.amazon.org.codehaus.jackson.map.type.SimpleType;
import com.amazon.org.codehaus.jackson.node.JsonNodeFactory;
import com.amazon.org.codehaus.jackson.node.NullNode;
import com.amazon.org.codehaus.jackson.node.TreeTraversingParser;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.amazon.org.codehaus.jackson.type.TypeReference;
import com.amazon.org.codehaus.jackson.util.VersionUtil;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes13.dex */
public class ObjectReader extends ObjectCodec implements Versioned {
    private static final JavaType JSON_NODE_TYPE = SimpleType.constructUnsafe(JsonNode.class);
    protected final DeserializationConfig _config;
    protected final InjectableValues _injectableValues;
    protected final JsonFactory _jsonFactory;
    protected final DeserializerProvider _provider;
    protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _rootDeserializers;
    protected final FormatSchema _schema;
    protected final boolean _unwrapRoot;
    protected final Object _valueToUpdate;
    protected final JavaType _valueType;

    /* JADX INFO: Access modifiers changed from: protected */
    public ObjectReader(ObjectMapper objectMapper, DeserializationConfig deserializationConfig) {
        this(objectMapper, deserializationConfig, (JavaType) null, (Object) null, (FormatSchema) null, (InjectableValues) null);
    }

    protected static JsonToken _initForReading(JsonParser jsonParser) throws IOException, JsonParseException, JsonMappingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == null && (currentToken = jsonParser.nextToken()) == null) {
            throw new EOFException("No content to map to Object due to end of input");
        }
        return currentToken;
    }

    protected Object _bind(JsonParser jsonParser) throws IOException, JsonParseException, JsonMappingException {
        Object obj;
        JsonToken _initForReading = _initForReading(jsonParser);
        if (_initForReading == JsonToken.VALUE_NULL) {
            obj = this._valueToUpdate;
            if (obj == null) {
                obj = _findRootDeserializer(this._config, this._valueType).getNullValue();
            }
        } else if (_initForReading != JsonToken.END_ARRAY && _initForReading != JsonToken.END_OBJECT) {
            DeserializationContext _createDeserializationContext = _createDeserializationContext(jsonParser, this._config);
            JsonDeserializer<Object> _findRootDeserializer = _findRootDeserializer(this._config, this._valueType);
            if (this._unwrapRoot) {
                obj = _unwrapAndDeserialize(jsonParser, _createDeserializationContext, this._valueType, _findRootDeserializer);
            } else {
                Object obj2 = this._valueToUpdate;
                if (obj2 == null) {
                    obj = _findRootDeserializer.mo4206deserialize(jsonParser, _createDeserializationContext);
                } else {
                    _findRootDeserializer.deserialize(jsonParser, _createDeserializationContext, obj2);
                    obj = this._valueToUpdate;
                }
            }
        } else {
            obj = this._valueToUpdate;
        }
        jsonParser.clearCurrentToken();
        return obj;
    }

    protected Object _bindAndClose(JsonParser jsonParser) throws IOException, JsonParseException, JsonMappingException {
        Object obj;
        FormatSchema formatSchema = this._schema;
        if (formatSchema != null) {
            jsonParser.setSchema(formatSchema);
        }
        try {
            JsonToken _initForReading = _initForReading(jsonParser);
            if (_initForReading == JsonToken.VALUE_NULL) {
                if (this._valueToUpdate == null) {
                    obj = _findRootDeserializer(this._config, this._valueType).getNullValue();
                } else {
                    obj = this._valueToUpdate;
                }
            } else {
                if (_initForReading != JsonToken.END_ARRAY && _initForReading != JsonToken.END_OBJECT) {
                    DeserializationContext _createDeserializationContext = _createDeserializationContext(jsonParser, this._config);
                    JsonDeserializer<Object> _findRootDeserializer = _findRootDeserializer(this._config, this._valueType);
                    if (this._unwrapRoot) {
                        obj = _unwrapAndDeserialize(jsonParser, _createDeserializationContext, this._valueType, _findRootDeserializer);
                    } else if (this._valueToUpdate == null) {
                        obj = _findRootDeserializer.mo4206deserialize(jsonParser, _createDeserializationContext);
                    } else {
                        _findRootDeserializer.deserialize(jsonParser, _createDeserializationContext, this._valueToUpdate);
                        obj = this._valueToUpdate;
                    }
                }
                obj = this._valueToUpdate;
            }
            return obj;
        } finally {
            try {
                jsonParser.close();
            } catch (IOException unused) {
            }
        }
    }

    protected JsonNode _bindAndCloseAsTree(JsonParser jsonParser) throws IOException, JsonParseException, JsonMappingException {
        FormatSchema formatSchema = this._schema;
        if (formatSchema != null) {
            jsonParser.setSchema(formatSchema);
        }
        try {
            return _bindAsTree(jsonParser);
        } finally {
            try {
                jsonParser.close();
            } catch (IOException unused) {
            }
        }
    }

    protected JsonNode _bindAsTree(JsonParser jsonParser) throws IOException, JsonParseException, JsonMappingException {
        JsonNode jsonNode;
        JsonToken _initForReading = _initForReading(jsonParser);
        if (_initForReading != JsonToken.VALUE_NULL && _initForReading != JsonToken.END_ARRAY && _initForReading != JsonToken.END_OBJECT) {
            DeserializationContext _createDeserializationContext = _createDeserializationContext(jsonParser, this._config);
            JsonDeserializer<Object> _findRootDeserializer = _findRootDeserializer(this._config, JSON_NODE_TYPE);
            if (this._unwrapRoot) {
                jsonNode = (JsonNode) _unwrapAndDeserialize(jsonParser, _createDeserializationContext, JSON_NODE_TYPE, _findRootDeserializer);
            } else {
                jsonNode = (JsonNode) _findRootDeserializer.mo4206deserialize(jsonParser, _createDeserializationContext);
            }
        } else {
            jsonNode = NullNode.instance;
        }
        jsonParser.clearCurrentToken();
        return jsonNode;
    }

    protected DeserializationContext _createDeserializationContext(JsonParser jsonParser, DeserializationConfig deserializationConfig) {
        return new StdDeserializationContext(deserializationConfig, jsonParser, this._provider, this._injectableValues);
    }

    protected JsonDeserializer<Object> _findRootDeserializer(DeserializationConfig deserializationConfig, JavaType javaType) throws JsonMappingException {
        if (javaType != null) {
            JsonDeserializer<Object> jsonDeserializer = this._rootDeserializers.get(javaType);
            if (jsonDeserializer != null) {
                return jsonDeserializer;
            }
            JsonDeserializer<Object> findTypedValueDeserializer = this._provider.findTypedValueDeserializer(deserializationConfig, javaType, null);
            if (findTypedValueDeserializer != null) {
                this._rootDeserializers.put(javaType, findTypedValueDeserializer);
                return findTypedValueDeserializer;
            }
            throw new JsonMappingException("Can not find a deserializer for type " + javaType);
        }
        throw new JsonMappingException("No value type configured for ObjectReader");
    }

    protected Object _unwrapAndDeserialize(JsonParser jsonParser, DeserializationContext deserializationContext, JavaType javaType, JsonDeserializer<Object> jsonDeserializer) throws IOException, JsonParseException, JsonMappingException {
        Object obj;
        SerializedString findExpectedRootName = this._provider.findExpectedRootName(deserializationContext.getConfig(), javaType);
        if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
            if (jsonParser.nextToken() == JsonToken.FIELD_NAME) {
                String currentName = jsonParser.getCurrentName();
                if (findExpectedRootName.getValue().equals(currentName)) {
                    jsonParser.nextToken();
                    Object obj2 = this._valueToUpdate;
                    if (obj2 == null) {
                        obj = jsonDeserializer.mo4206deserialize(jsonParser, deserializationContext);
                    } else {
                        jsonDeserializer.deserialize(jsonParser, deserializationContext, obj2);
                        obj = this._valueToUpdate;
                    }
                    if (jsonParser.nextToken() == JsonToken.END_OBJECT) {
                        return obj;
                    }
                    throw JsonMappingException.from(jsonParser, "Current token not END_OBJECT (to match wrapper object with root name '" + findExpectedRootName + "'), but " + jsonParser.getCurrentToken());
                }
                throw JsonMappingException.from(jsonParser, "Root name '" + currentName + "' does not match expected ('" + findExpectedRootName + "') for type " + javaType);
            }
            throw JsonMappingException.from(jsonParser, "Current token not FIELD_NAME (to contain expected root name '" + findExpectedRootName + "'), but " + jsonParser.getCurrentToken());
        }
        throw JsonMappingException.from(jsonParser, "Current token not START_OBJECT (needed to unwrap root name '" + findExpectedRootName + "'), but " + jsonParser.getCurrentToken());
    }

    @Override // com.amazon.org.codehaus.jackson.ObjectCodec
    /* renamed from: createArrayNode */
    public JsonNode mo4113createArrayNode() {
        return this._config.getNodeFactory().arrayNode();
    }

    @Override // com.amazon.org.codehaus.jackson.ObjectCodec
    /* renamed from: createObjectNode */
    public JsonNode mo4114createObjectNode() {
        return this._config.getNodeFactory().objectNode();
    }

    @Override // com.amazon.org.codehaus.jackson.ObjectCodec
    public JsonNode readTree(JsonParser jsonParser) throws IOException, JsonProcessingException {
        return _bindAsTree(jsonParser);
    }

    public <T> T readValue(JsonParser jsonParser) throws IOException, JsonProcessingException {
        return (T) _bind(jsonParser);
    }

    @Override // com.amazon.org.codehaus.jackson.ObjectCodec
    /* renamed from: readValues */
    public <T> Iterator<T> mo4117readValues(JsonParser jsonParser, Class<T> cls) throws IOException, JsonProcessingException {
        return withType((Class<?>) cls).readValues(jsonParser);
    }

    @Override // com.amazon.org.codehaus.jackson.ObjectCodec
    public JsonParser treeAsTokens(JsonNode jsonNode) {
        return new TreeTraversingParser(jsonNode, this);
    }

    @Override // com.amazon.org.codehaus.jackson.ObjectCodec
    public <T> T treeToValue(JsonNode jsonNode, Class<T> cls) throws IOException, JsonProcessingException {
        return (T) readValue(treeAsTokens(jsonNode), cls);
    }

    @Override // com.amazon.org.codehaus.jackson.Versioned
    public Version version() {
        return VersionUtil.versionFor(ObjectReader.class);
    }

    public ObjectReader withInjectableValues(InjectableValues injectableValues) {
        return this._injectableValues == injectableValues ? this : new ObjectReader(this, this._config, this._valueType, this._valueToUpdate, this._schema, injectableValues);
    }

    public ObjectReader withNodeFactory(JsonNodeFactory jsonNodeFactory) {
        return jsonNodeFactory == this._config.getNodeFactory() ? this : new ObjectReader(this, this._config.withNodeFactory(jsonNodeFactory), this._valueType, this._valueToUpdate, this._schema, this._injectableValues);
    }

    public ObjectReader withSchema(FormatSchema formatSchema) {
        return this._schema == formatSchema ? this : new ObjectReader(this, this._config, this._valueType, this._valueToUpdate, formatSchema, this._injectableValues);
    }

    public ObjectReader withType(JavaType javaType) {
        return javaType == this._valueType ? this : new ObjectReader(this, this._config, javaType, this._valueToUpdate, this._schema, this._injectableValues);
    }

    public ObjectReader withValueToUpdate(Object obj) {
        if (obj == this._valueToUpdate) {
            return this;
        }
        if (obj != null) {
            JavaType javaType = this._valueType;
            if (javaType == null) {
                javaType = this._config.constructType(obj.getClass());
            }
            return new ObjectReader(this, this._config, javaType, obj, this._schema, this._injectableValues);
        }
        throw new IllegalArgumentException("cat not update null value");
    }

    @Override // com.amazon.org.codehaus.jackson.ObjectCodec
    public void writeTree(JsonGenerator jsonGenerator, JsonNode jsonNode) throws IOException, JsonProcessingException {
        throw new UnsupportedOperationException("Not implemented for ObjectReader");
    }

    @Override // com.amazon.org.codehaus.jackson.ObjectCodec
    public void writeValue(JsonGenerator jsonGenerator, Object obj) throws IOException, JsonProcessingException {
        throw new UnsupportedOperationException("Not implemented for ObjectReader");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ObjectReader(ObjectMapper objectMapper, DeserializationConfig deserializationConfig, JavaType javaType, Object obj, FormatSchema formatSchema, InjectableValues injectableValues) {
        this._config = deserializationConfig;
        this._rootDeserializers = objectMapper._rootDeserializers;
        this._provider = objectMapper._deserializerProvider;
        this._jsonFactory = objectMapper._jsonFactory;
        this._valueType = javaType;
        this._valueToUpdate = obj;
        if (obj != null && javaType.isArrayType()) {
            throw new IllegalArgumentException("Can not update an array value");
        }
        this._schema = formatSchema;
        this._injectableValues = injectableValues;
        this._unwrapRoot = deserializationConfig.isEnabled(DeserializationConfig.Feature.UNWRAP_ROOT_VALUE);
    }

    public JsonNode readTree(InputStream inputStream) throws IOException, JsonProcessingException {
        return _bindAndCloseAsTree(this._jsonFactory.createJsonParser(inputStream));
    }

    @Override // com.amazon.org.codehaus.jackson.ObjectCodec
    public <T> T readValue(JsonParser jsonParser, Class<T> cls) throws IOException, JsonProcessingException {
        return (T) withType((Class<?>) cls).readValue(jsonParser);
    }

    @Override // com.amazon.org.codehaus.jackson.ObjectCodec
    /* renamed from: readValues */
    public <T> Iterator<T> mo4116readValues(JsonParser jsonParser, TypeReference<?> typeReference) throws IOException, JsonProcessingException {
        return withType(typeReference).readValues(jsonParser);
    }

    public JsonNode readTree(Reader reader) throws IOException, JsonProcessingException {
        return _bindAndCloseAsTree(this._jsonFactory.createJsonParser(reader));
    }

    @Override // com.amazon.org.codehaus.jackson.ObjectCodec
    public <T> T readValue(JsonParser jsonParser, TypeReference<?> typeReference) throws IOException, JsonProcessingException {
        return (T) withType(typeReference).readValue(jsonParser);
    }

    @Override // com.amazon.org.codehaus.jackson.ObjectCodec
    /* renamed from: readValues */
    public <T> Iterator<T> mo4115readValues(JsonParser jsonParser, JavaType javaType) throws IOException, JsonProcessingException {
        return withType(javaType).readValues(jsonParser);
    }

    public ObjectReader withType(Class<?> cls) {
        return withType(this._config.constructType(cls));
    }

    public JsonNode readTree(String str) throws IOException, JsonProcessingException {
        return _bindAndCloseAsTree(this._jsonFactory.createJsonParser(str));
    }

    @Override // com.amazon.org.codehaus.jackson.ObjectCodec
    public <T> T readValue(JsonParser jsonParser, JavaType javaType) throws IOException, JsonProcessingException {
        return (T) withType(javaType).readValue(jsonParser);
    }

    public <T> MappingIterator<T> readValues(JsonParser jsonParser) throws IOException, JsonProcessingException {
        DeserializationContext _createDeserializationContext = _createDeserializationContext(jsonParser, this._config);
        JavaType javaType = this._valueType;
        return new MappingIterator<>(javaType, jsonParser, _createDeserializationContext, _findRootDeserializer(this._config, javaType), false, this._valueToUpdate);
    }

    public ObjectReader withType(Type type) {
        return withType(this._config.getTypeFactory().constructType(type));
    }

    public <T> T readValue(InputStream inputStream) throws IOException, JsonProcessingException {
        return (T) _bindAndClose(this._jsonFactory.createJsonParser(inputStream));
    }

    public ObjectReader withType(TypeReference<?> typeReference) {
        return withType(this._config.getTypeFactory().constructType(typeReference.getType()));
    }

    public <T> T readValue(Reader reader) throws IOException, JsonProcessingException {
        return (T) _bindAndClose(this._jsonFactory.createJsonParser(reader));
    }

    public <T> MappingIterator<T> readValues(InputStream inputStream) throws IOException, JsonProcessingException {
        JsonParser createJsonParser = this._jsonFactory.createJsonParser(inputStream);
        FormatSchema formatSchema = this._schema;
        if (formatSchema != null) {
            createJsonParser.setSchema(formatSchema);
        }
        DeserializationContext _createDeserializationContext = _createDeserializationContext(createJsonParser, this._config);
        JavaType javaType = this._valueType;
        return new MappingIterator<>(javaType, createJsonParser, _createDeserializationContext, _findRootDeserializer(this._config, javaType), true, this._valueToUpdate);
    }

    public <T> T readValue(String str) throws IOException, JsonProcessingException {
        return (T) _bindAndClose(this._jsonFactory.createJsonParser(str));
    }

    public <T> T readValue(byte[] bArr) throws IOException, JsonProcessingException {
        return (T) _bindAndClose(this._jsonFactory.createJsonParser(bArr));
    }

    public <T> T readValue(byte[] bArr, int i, int i2) throws IOException, JsonProcessingException {
        return (T) _bindAndClose(this._jsonFactory.createJsonParser(bArr, i, i2));
    }

    public <T> T readValue(File file) throws IOException, JsonProcessingException {
        return (T) _bindAndClose(this._jsonFactory.createJsonParser(file));
    }

    public <T> T readValue(URL url) throws IOException, JsonProcessingException {
        return (T) _bindAndClose(this._jsonFactory.createJsonParser(url));
    }

    public <T> MappingIterator<T> readValues(Reader reader) throws IOException, JsonProcessingException {
        JsonParser createJsonParser = this._jsonFactory.createJsonParser(reader);
        FormatSchema formatSchema = this._schema;
        if (formatSchema != null) {
            createJsonParser.setSchema(formatSchema);
        }
        DeserializationContext _createDeserializationContext = _createDeserializationContext(createJsonParser, this._config);
        JavaType javaType = this._valueType;
        return new MappingIterator<>(javaType, createJsonParser, _createDeserializationContext, _findRootDeserializer(this._config, javaType), true, this._valueToUpdate);
    }

    public <T> T readValue(JsonNode jsonNode) throws IOException, JsonProcessingException {
        return (T) _bindAndClose(treeAsTokens(jsonNode));
    }

    protected ObjectReader(ObjectReader objectReader, DeserializationConfig deserializationConfig, JavaType javaType, Object obj, FormatSchema formatSchema, InjectableValues injectableValues) {
        this._config = deserializationConfig;
        this._rootDeserializers = objectReader._rootDeserializers;
        this._provider = objectReader._provider;
        this._jsonFactory = objectReader._jsonFactory;
        this._valueType = javaType;
        this._valueToUpdate = obj;
        if (obj != null && javaType.isArrayType()) {
            throw new IllegalArgumentException("Can not update an array value");
        }
        this._schema = formatSchema;
        this._injectableValues = injectableValues;
        this._unwrapRoot = deserializationConfig.isEnabled(DeserializationConfig.Feature.UNWRAP_ROOT_VALUE);
    }

    public <T> MappingIterator<T> readValues(String str) throws IOException, JsonProcessingException {
        JsonParser createJsonParser = this._jsonFactory.createJsonParser(str);
        FormatSchema formatSchema = this._schema;
        if (formatSchema != null) {
            createJsonParser.setSchema(formatSchema);
        }
        DeserializationContext _createDeserializationContext = _createDeserializationContext(createJsonParser, this._config);
        JavaType javaType = this._valueType;
        return new MappingIterator<>(javaType, createJsonParser, _createDeserializationContext, _findRootDeserializer(this._config, javaType), true, this._valueToUpdate);
    }

    public <T> MappingIterator<T> readValues(byte[] bArr, int i, int i2) throws IOException, JsonProcessingException {
        JsonParser createJsonParser = this._jsonFactory.createJsonParser(bArr, i, i2);
        FormatSchema formatSchema = this._schema;
        if (formatSchema != null) {
            createJsonParser.setSchema(formatSchema);
        }
        DeserializationContext _createDeserializationContext = _createDeserializationContext(createJsonParser, this._config);
        JavaType javaType = this._valueType;
        return new MappingIterator<>(javaType, createJsonParser, _createDeserializationContext, _findRootDeserializer(this._config, javaType), true, this._valueToUpdate);
    }

    public final <T> MappingIterator<T> readValues(byte[] bArr) throws IOException, JsonProcessingException {
        return readValues(bArr, 0, bArr.length);
    }

    public <T> MappingIterator<T> readValues(File file) throws IOException, JsonProcessingException {
        JsonParser createJsonParser = this._jsonFactory.createJsonParser(file);
        FormatSchema formatSchema = this._schema;
        if (formatSchema != null) {
            createJsonParser.setSchema(formatSchema);
        }
        DeserializationContext _createDeserializationContext = _createDeserializationContext(createJsonParser, this._config);
        JavaType javaType = this._valueType;
        return new MappingIterator<>(javaType, createJsonParser, _createDeserializationContext, _findRootDeserializer(this._config, javaType), true, this._valueToUpdate);
    }

    public <T> MappingIterator<T> readValues(URL url) throws IOException, JsonProcessingException {
        JsonParser createJsonParser = this._jsonFactory.createJsonParser(url);
        FormatSchema formatSchema = this._schema;
        if (formatSchema != null) {
            createJsonParser.setSchema(formatSchema);
        }
        DeserializationContext _createDeserializationContext = _createDeserializationContext(createJsonParser, this._config);
        JavaType javaType = this._valueType;
        return new MappingIterator<>(javaType, createJsonParser, _createDeserializationContext, _findRootDeserializer(this._config, javaType), true, this._valueToUpdate);
    }
}
