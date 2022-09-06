package com.fasterxml.jackson.databind;

import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatFeature;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.filter.FilteringParserDelegate;
import com.fasterxml.jackson.core.filter.JsonPointerBasedFilter;
import com.fasterxml.jackson.core.filter.TokenFilter;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.DataInput;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes2.dex */
public class ObjectReader extends ObjectCodec implements Versioned, Serializable {
    private static final long serialVersionUID = 2;
    protected final DeserializationConfig _config;
    protected final DefaultDeserializationContext _context;
    protected final DataFormatReaders _dataFormatReaders;
    private final TokenFilter _filter;
    protected final InjectableValues _injectableValues;
    protected transient JavaType _jsonNodeType;
    protected final JsonFactory _parserFactory;
    protected final JsonDeserializer<Object> _rootDeserializer;
    protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _rootDeserializers;
    protected final FormatSchema _schema;
    protected final boolean _unwrapRoot;
    protected final Object _valueToUpdate;
    protected final JavaType _valueType;

    /* JADX INFO: Access modifiers changed from: protected */
    public ObjectReader(ObjectMapper objectMapper, DeserializationConfig deserializationConfig) {
        this(objectMapper, deserializationConfig, null, null, null, null);
    }

    protected final void _assertNotNull(String str, Object obj) {
        if (obj != null) {
            return;
        }
        throw new IllegalArgumentException(String.format("argument \"%s\" is null", str));
    }

    protected Object _bind(JsonParser jsonParser, Object obj) throws IOException {
        DefaultDeserializationContext createDeserializationContext = createDeserializationContext(jsonParser);
        JsonToken _initForReading = _initForReading(createDeserializationContext, jsonParser);
        if (_initForReading == JsonToken.VALUE_NULL) {
            if (obj == null) {
                obj = _findRootDeserializer(createDeserializationContext).mo7075getNullValue(createDeserializationContext);
            }
        } else if (_initForReading != JsonToken.END_ARRAY && _initForReading != JsonToken.END_OBJECT) {
            obj = createDeserializationContext.readRootValue(jsonParser, this._valueType, _findRootDeserializer(createDeserializationContext), this._valueToUpdate);
        }
        jsonParser.clearCurrentToken();
        if (this._config.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
            _verifyNoTrailingTokens(jsonParser, createDeserializationContext, this._valueType);
        }
        return obj;
    }

    protected Object _bindAndClose(JsonParser jsonParser) throws IOException {
        Object obj;
        try {
            DefaultDeserializationContext createDeserializationContext = createDeserializationContext(jsonParser);
            JsonToken _initForReading = _initForReading(createDeserializationContext, jsonParser);
            if (_initForReading == JsonToken.VALUE_NULL) {
                if (this._valueToUpdate == null) {
                    obj = _findRootDeserializer(createDeserializationContext).mo7075getNullValue(createDeserializationContext);
                } else {
                    obj = this._valueToUpdate;
                }
            } else {
                if (_initForReading != JsonToken.END_ARRAY && _initForReading != JsonToken.END_OBJECT) {
                    obj = createDeserializationContext.readRootValue(jsonParser, this._valueType, _findRootDeserializer(createDeserializationContext), this._valueToUpdate);
                }
                obj = this._valueToUpdate;
            }
            if (this._config.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
                _verifyNoTrailingTokens(jsonParser, createDeserializationContext, this._valueType);
            }
            if (jsonParser != null) {
                jsonParser.close();
            }
            return obj;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (jsonParser != null) {
                    try {
                        jsonParser.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    protected final JsonNode _bindAndCloseAsTree(JsonParser jsonParser) throws IOException {
        try {
            JsonNode _bindAsTree = _bindAsTree(jsonParser);
            if (jsonParser != null) {
                jsonParser.close();
            }
            return _bindAsTree;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (jsonParser != null) {
                    try {
                        jsonParser.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    protected <T> MappingIterator<T> _bindAndReadValues(JsonParser jsonParser) throws IOException {
        DefaultDeserializationContext createDeserializationContext = createDeserializationContext(jsonParser);
        _initForMultiRead(createDeserializationContext, jsonParser);
        jsonParser.nextToken();
        return _newIterator(jsonParser, createDeserializationContext, _findRootDeserializer(createDeserializationContext), true);
    }

    protected final JsonNode _bindAsTree(JsonParser jsonParser) throws IOException {
        JsonNode jsonNode;
        this._config.initialize(jsonParser);
        FormatSchema formatSchema = this._schema;
        if (formatSchema != null) {
            jsonParser.setSchema(formatSchema);
        }
        JsonToken currentToken = jsonParser.currentToken();
        if (currentToken == null && (currentToken = jsonParser.nextToken()) == null) {
            return this._config.getNodeFactory().missingNode();
        }
        DefaultDeserializationContext createDeserializationContext = createDeserializationContext(jsonParser);
        if (currentToken == JsonToken.VALUE_NULL) {
            jsonNode = this._config.getNodeFactory().mo7176nullNode();
        } else {
            jsonNode = (JsonNode) createDeserializationContext.readRootValue(jsonParser, _jsonNodeType(), _findTreeDeserializer(createDeserializationContext), null);
        }
        if (this._config.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
            _verifyNoTrailingTokens(jsonParser, createDeserializationContext, _jsonNodeType());
        }
        return jsonNode;
    }

    protected final JsonNode _bindAsTreeOrNull(JsonParser jsonParser) throws IOException {
        JsonNode jsonNode;
        this._config.initialize(jsonParser);
        FormatSchema formatSchema = this._schema;
        if (formatSchema != null) {
            jsonParser.setSchema(formatSchema);
        }
        JsonToken currentToken = jsonParser.currentToken();
        if (currentToken == null && (currentToken = jsonParser.nextToken()) == null) {
            return null;
        }
        DefaultDeserializationContext createDeserializationContext = createDeserializationContext(jsonParser);
        if (currentToken == JsonToken.VALUE_NULL) {
            jsonNode = this._config.getNodeFactory().mo7176nullNode();
        } else {
            jsonNode = (JsonNode) createDeserializationContext.readRootValue(jsonParser, _jsonNodeType(), _findTreeDeserializer(createDeserializationContext), null);
        }
        if (this._config.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
            _verifyNoTrailingTokens(jsonParser, createDeserializationContext, _jsonNodeType());
        }
        return jsonNode;
    }

    protected JsonParser _considerFilter(JsonParser jsonParser, boolean z) {
        return (this._filter == null || FilteringParserDelegate.class.isInstance(jsonParser)) ? jsonParser : new FilteringParserDelegate(jsonParser, this._filter, TokenFilter.Inclusion.ONLY_INCLUDE_ALL, z);
    }

    protected Object _detectBindAndClose(byte[] bArr, int i, int i2) throws IOException {
        DataFormatReaders.Match findFormat = this._dataFormatReaders.findFormat(bArr, i, i2);
        if (!findFormat.hasMatch()) {
            _reportUnkownFormat(this._dataFormatReaders, findFormat);
        }
        return findFormat.getReader()._bindAndClose(findFormat.createParserWithMatch());
    }

    protected JsonNode _detectBindAndCloseAsTree(InputStream inputStream) throws IOException {
        DataFormatReaders.Match findFormat = this._dataFormatReaders.findFormat(inputStream);
        if (!findFormat.hasMatch()) {
            _reportUnkownFormat(this._dataFormatReaders, findFormat);
        }
        JsonParser createParserWithMatch = findFormat.createParserWithMatch();
        createParserWithMatch.enable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
        return findFormat.getReader()._bindAndCloseAsTree(createParserWithMatch);
    }

    protected <T> MappingIterator<T> _detectBindAndReadValues(DataFormatReaders.Match match, boolean z) throws IOException {
        if (!match.hasMatch()) {
            _reportUnkownFormat(this._dataFormatReaders, match);
        }
        JsonParser createParserWithMatch = match.createParserWithMatch();
        if (z) {
            createParserWithMatch.enable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
        }
        return match.getReader()._bindAndReadValues(createParserWithMatch);
    }

    protected JsonDeserializer<Object> _findRootDeserializer(DeserializationContext deserializationContext) throws JsonMappingException {
        JsonDeserializer<Object> jsonDeserializer = this._rootDeserializer;
        if (jsonDeserializer != null) {
            return jsonDeserializer;
        }
        JavaType javaType = this._valueType;
        if (javaType == null) {
            deserializationContext.reportBadDefinition((JavaType) null, "No value type configured for ObjectReader");
        }
        JsonDeserializer<Object> jsonDeserializer2 = this._rootDeserializers.get(javaType);
        if (jsonDeserializer2 != null) {
            return jsonDeserializer2;
        }
        JsonDeserializer<Object> findRootValueDeserializer = deserializationContext.findRootValueDeserializer(javaType);
        if (findRootValueDeserializer == null) {
            deserializationContext.reportBadDefinition(javaType, "Cannot find a deserializer for type " + javaType);
        }
        this._rootDeserializers.put(javaType, findRootValueDeserializer);
        return findRootValueDeserializer;
    }

    protected JsonDeserializer<Object> _findTreeDeserializer(DeserializationContext deserializationContext) throws JsonMappingException {
        JavaType _jsonNodeType = _jsonNodeType();
        JsonDeserializer<Object> jsonDeserializer = this._rootDeserializers.get(_jsonNodeType);
        if (jsonDeserializer == null) {
            jsonDeserializer = deserializationContext.findRootValueDeserializer(_jsonNodeType);
            if (jsonDeserializer == null) {
                deserializationContext.reportBadDefinition(_jsonNodeType, "Cannot find a deserializer for type " + _jsonNodeType);
            }
            this._rootDeserializers.put(_jsonNodeType, jsonDeserializer);
        }
        return jsonDeserializer;
    }

    protected void _initForMultiRead(DeserializationContext deserializationContext, JsonParser jsonParser) throws IOException {
        this._config.initialize(jsonParser, this._schema);
    }

    protected JsonToken _initForReading(DeserializationContext deserializationContext, JsonParser jsonParser) throws IOException {
        this._config.initialize(jsonParser, this._schema);
        JsonToken currentToken = jsonParser.currentToken();
        if (currentToken == null && (currentToken = jsonParser.nextToken()) == null) {
            deserializationContext.reportInputMismatch(this._valueType, "No content to map due to end-of-input", new Object[0]);
        }
        return currentToken;
    }

    protected InputStream _inputStream(URL url) throws IOException {
        return url.openStream();
    }

    protected final JavaType _jsonNodeType() {
        JavaType javaType = this._jsonNodeType;
        if (javaType == null) {
            JavaType constructType = getTypeFactory().constructType(JsonNode.class);
            this._jsonNodeType = constructType;
            return constructType;
        }
        return javaType;
    }

    protected ObjectReader _new(ObjectReader objectReader, JsonFactory jsonFactory) {
        return new ObjectReader(objectReader, jsonFactory);
    }

    protected <T> MappingIterator<T> _newIterator(JsonParser jsonParser, DeserializationContext deserializationContext, JsonDeserializer<?> jsonDeserializer, boolean z) {
        return new MappingIterator<>(this._valueType, jsonParser, deserializationContext, jsonDeserializer, z, this._valueToUpdate);
    }

    protected JsonDeserializer<Object> _prefetchRootDeserializer(JavaType javaType) {
        if (javaType == null || !this._config.isEnabled(DeserializationFeature.EAGER_DESERIALIZER_FETCH)) {
            return null;
        }
        JsonDeserializer<Object> jsonDeserializer = this._rootDeserializers.get(javaType);
        if (jsonDeserializer == null) {
            try {
                jsonDeserializer = createDummyDeserializationContext().findRootValueDeserializer(javaType);
                if (jsonDeserializer != null) {
                    this._rootDeserializers.put(javaType, jsonDeserializer);
                }
            } catch (JsonProcessingException unused) {
            }
        }
        return jsonDeserializer;
    }

    protected void _reportUndetectableSource(Object obj) throws JsonParseException {
        throw new JsonParseException((JsonParser) null, GeneratedOutlineSupport1.outline46(obj, GeneratedOutlineSupport1.outline107("Cannot use source of type "), " with format auto-detection: must be byte- not char-based"));
    }

    protected void _reportUnkownFormat(DataFormatReaders dataFormatReaders, DataFormatReaders.Match match) throws JsonProcessingException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cannot detect format from input, does not look like any of detectable formats ");
        outline107.append(dataFormatReaders.toString());
        throw new JsonParseException((JsonParser) null, outline107.toString());
    }

    protected final void _verifyNoTrailingTokens(JsonParser jsonParser, DeserializationContext deserializationContext, JavaType javaType) throws IOException {
        Object obj;
        JsonToken nextToken = jsonParser.nextToken();
        if (nextToken != null) {
            Class<?> rawClass = ClassUtil.rawClass(javaType);
            if (rawClass == null && (obj = this._valueToUpdate) != null) {
                rawClass = obj.getClass();
            }
            deserializationContext.reportTrailingTokens(rawClass, jsonParser, nextToken);
        }
    }

    protected void _verifySchemaType(FormatSchema formatSchema) {
        if (formatSchema == null || this._parserFactory.canUseSchema(formatSchema)) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cannot use FormatSchema of type ");
        outline107.append(formatSchema.getClass().getName());
        outline107.append(" for format ");
        outline107.append(this._parserFactory.getFormatName());
        throw new IllegalArgumentException(outline107.toString());
    }

    protected ObjectReader _with(DeserializationConfig deserializationConfig) {
        if (deserializationConfig == this._config) {
            return this;
        }
        ObjectReader _new = _new(this, deserializationConfig);
        DataFormatReaders dataFormatReaders = this._dataFormatReaders;
        return dataFormatReaders != null ? _new.withFormatDetection(dataFormatReaders.with(deserializationConfig)) : _new;
    }

    public ObjectReader at(String str) {
        _assertNotNull("pointerExpr", str);
        return new ObjectReader(this, new JsonPointerBasedFilter(str));
    }

    protected DefaultDeserializationContext createDeserializationContext(JsonParser jsonParser) {
        return this._context.createInstance(this._config, jsonParser, this._injectableValues);
    }

    protected DefaultDeserializationContext createDummyDeserializationContext() {
        return this._context.createDummyInstance(this._config);
    }

    public JsonParser createNonBlockingByteArrayParser() throws IOException {
        return this._config.initialize(this._parserFactory.createNonBlockingByteArrayParser(), this._schema);
    }

    public JsonParser createParser(File file) throws IOException {
        _assertNotNull("src", file);
        return this._config.initialize(this._parserFactory.createParser(file), this._schema);
    }

    public ObjectReader forType(JavaType javaType) {
        if (javaType == null || !javaType.equals(this._valueType)) {
            JsonDeserializer<Object> _prefetchRootDeserializer = _prefetchRootDeserializer(javaType);
            DataFormatReaders dataFormatReaders = this._dataFormatReaders;
            if (dataFormatReaders != null) {
                dataFormatReaders = dataFormatReaders.withType(javaType);
            }
            return _new(this, this._config, javaType, _prefetchRootDeserializer, this._valueToUpdate, this._schema, this._injectableValues, dataFormatReaders);
        }
        return this;
    }

    public ContextAttributes getAttributes() {
        return this._config.getAttributes();
    }

    public DeserializationConfig getConfig() {
        return this._config;
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    public JsonFactory getFactory() {
        return this._parserFactory;
    }

    public InjectableValues getInjectableValues() {
        return this._injectableValues;
    }

    public TypeFactory getTypeFactory() {
        return this._config.getTypeFactory();
    }

    public JavaType getValueType() {
        return this._valueType;
    }

    public boolean isEnabled(DeserializationFeature deserializationFeature) {
        return this._config.isEnabled(deserializationFeature);
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec, com.fasterxml.jackson.core.TreeCodec
    public <T extends TreeNode> T readTree(JsonParser jsonParser) throws IOException {
        _assertNotNull("p", jsonParser);
        return _bindAsTreeOrNull(jsonParser);
    }

    public <T> T readValue(JsonParser jsonParser) throws IOException {
        _assertNotNull("p", jsonParser);
        return (T) _bind(jsonParser, this._valueToUpdate);
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    /* renamed from: readValues */
    public <T> Iterator<T> mo7038readValues(JsonParser jsonParser, Class<T> cls) throws IOException {
        _assertNotNull("p", jsonParser);
        return forType((Class<?>) cls).readValues(jsonParser);
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec, com.fasterxml.jackson.core.TreeCodec
    public JsonParser treeAsTokens(TreeNode treeNode) {
        _assertNotNull(JsonReportFormat.COUNT, treeNode);
        return new TreeTraversingParser((JsonNode) treeNode, withValueToUpdate(null));
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    public <T> T treeToValue(TreeNode treeNode, Class<T> cls) throws JsonProcessingException {
        _assertNotNull(JsonReportFormat.COUNT, treeNode);
        try {
            return (T) readValue(treeAsTokens(treeNode), cls);
        } catch (JsonProcessingException e) {
            throw e;
        } catch (IOException e2) {
            throw JsonMappingException.fromUnexpectedIOE(e2);
        }
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec, com.fasterxml.jackson.core.Versioned
    public Version version() {
        return PackageVersion.VERSION;
    }

    public ObjectReader with(DeserializationFeature deserializationFeature) {
        return _with(this._config.with(deserializationFeature));
    }

    public ObjectReader withAttribute(Object obj, Object obj2) {
        return _with(this._config.withAttribute(obj, obj2));
    }

    public ObjectReader withAttributes(Map<?, ?> map) {
        return _with(this._config.withAttributes(map));
    }

    public ObjectReader withFeatures(DeserializationFeature... deserializationFeatureArr) {
        return _with(this._config.withFeatures(deserializationFeatureArr));
    }

    public ObjectReader withFormatDetection(ObjectReader... objectReaderArr) {
        return withFormatDetection(new DataFormatReaders(objectReaderArr));
    }

    public ObjectReader withHandler(DeserializationProblemHandler deserializationProblemHandler) {
        return _with(this._config.withHandler(deserializationProblemHandler));
    }

    public ObjectReader withRootName(String str) {
        return _with(this._config.withRootName(str));
    }

    @Deprecated
    public ObjectReader withType(JavaType javaType) {
        return forType(javaType);
    }

    public ObjectReader withValueToUpdate(Object obj) {
        if (obj == this._valueToUpdate) {
            return this;
        }
        if (obj == null) {
            return _new(this, this._config, this._valueType, this._rootDeserializer, null, this._schema, this._injectableValues, this._dataFormatReaders);
        }
        JavaType javaType = this._valueType;
        if (javaType == null) {
            javaType = this._config.constructType(obj.getClass());
        }
        return _new(this, this._config, javaType, this._rootDeserializer, obj, this._schema, this._injectableValues, this._dataFormatReaders);
    }

    public ObjectReader withView(Class<?> cls) {
        return _with(this._config.mo7050withView(cls));
    }

    public ObjectReader without(DeserializationFeature deserializationFeature) {
        return _with(this._config.without(deserializationFeature));
    }

    public ObjectReader withoutAttribute(Object obj) {
        return _with(this._config.withoutAttribute(obj));
    }

    public ObjectReader withoutFeatures(DeserializationFeature... deserializationFeatureArr) {
        return _with(this._config.withoutFeatures(deserializationFeatureArr));
    }

    public ObjectReader withoutRootName() {
        return _with(this._config.mo7049withRootName(PropertyName.NO_NAME));
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec, com.fasterxml.jackson.core.TreeCodec
    public void writeTree(JsonGenerator jsonGenerator, TreeNode treeNode) {
        throw new UnsupportedOperationException();
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    public void writeValue(JsonGenerator jsonGenerator, Object obj) throws IOException {
        throw new UnsupportedOperationException("Not implemented for ObjectReader");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ObjectReader(ObjectMapper objectMapper, DeserializationConfig deserializationConfig, JavaType javaType, Object obj, FormatSchema formatSchema, InjectableValues injectableValues) {
        this._config = deserializationConfig;
        this._context = objectMapper._deserializationContext;
        this._rootDeserializers = objectMapper._rootDeserializers;
        this._parserFactory = objectMapper._jsonFactory;
        this._valueType = javaType;
        this._valueToUpdate = obj;
        this._schema = formatSchema;
        this._injectableValues = injectableValues;
        this._unwrapRoot = deserializationConfig.useRootWrapping();
        this._rootDeserializer = _prefetchRootDeserializer(javaType);
        this._dataFormatReaders = null;
        this._filter = null;
    }

    protected InputStream _inputStream(File file) throws IOException {
        return new FileInputStream(file);
    }

    protected ObjectReader _new(ObjectReader objectReader, DeserializationConfig deserializationConfig) {
        return new ObjectReader(objectReader, deserializationConfig);
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec, com.fasterxml.jackson.core.TreeCodec
    /* renamed from: createArrayNode  reason: collision with other method in class */
    public JsonNode mo7040createArrayNode() {
        return this._config.getNodeFactory().arrayNode();
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec, com.fasterxml.jackson.core.TreeCodec
    /* renamed from: createObjectNode  reason: collision with other method in class */
    public JsonNode mo7041createObjectNode() {
        return this._config.getNodeFactory().objectNode();
    }

    public boolean isEnabled(MapperFeature mapperFeature) {
        return this._config.isEnabled(mapperFeature);
    }

    @Override // com.fasterxml.jackson.core.TreeCodec
    /* renamed from: missingNode  reason: collision with other method in class */
    public JsonNode mo7042missingNode() {
        return this._config.getNodeFactory().missingNode();
    }

    @Override // com.fasterxml.jackson.core.TreeCodec
    /* renamed from: nullNode  reason: collision with other method in class */
    public JsonNode mo7043nullNode() {
        return this._config.getNodeFactory().mo7176nullNode();
    }

    public ObjectReader with(DeserializationFeature deserializationFeature, DeserializationFeature... deserializationFeatureArr) {
        return _with(this._config.with(deserializationFeature, deserializationFeatureArr));
    }

    public ObjectReader withFeatures(JsonParser.Feature... featureArr) {
        return _with(this._config.withFeatures(featureArr));
    }

    public ObjectReader withFormatDetection(DataFormatReaders dataFormatReaders) {
        return _new(this, this._config, this._valueType, this._rootDeserializer, this._valueToUpdate, this._schema, this._injectableValues, dataFormatReaders);
    }

    public ObjectReader withRootName(PropertyName propertyName) {
        return _with(this._config.mo7049withRootName(propertyName));
    }

    @Deprecated
    public ObjectReader withType(Class<?> cls) {
        return forType(this._config.constructType(cls));
    }

    public ObjectReader without(DeserializationFeature deserializationFeature, DeserializationFeature... deserializationFeatureArr) {
        return _with(this._config.without(deserializationFeature, deserializationFeatureArr));
    }

    public ObjectReader withoutFeatures(JsonParser.Feature... featureArr) {
        return _with(this._config.withoutFeatures(featureArr));
    }

    protected ObjectReader _new(ObjectReader objectReader, DeserializationConfig deserializationConfig, JavaType javaType, JsonDeserializer<Object> jsonDeserializer, Object obj, FormatSchema formatSchema, InjectableValues injectableValues, DataFormatReaders dataFormatReaders) {
        return new ObjectReader(objectReader, deserializationConfig, javaType, jsonDeserializer, obj, formatSchema, injectableValues, dataFormatReaders);
    }

    public ObjectReader at(JsonPointer jsonPointer) {
        _assertNotNull("pointer", jsonPointer);
        return new ObjectReader(this, new JsonPointerBasedFilter(jsonPointer));
    }

    public JsonParser createParser(URL url) throws IOException {
        _assertNotNull("src", url);
        return this._config.initialize(this._parserFactory.createParser(url), this._schema);
    }

    public boolean isEnabled(JsonParser.Feature feature) {
        return this._config.isEnabled(feature, this._parserFactory);
    }

    public JsonNode readTree(InputStream inputStream) throws IOException {
        if (this._dataFormatReaders != null) {
            return _detectBindAndCloseAsTree(inputStream);
        }
        return _bindAndCloseAsTree(_considerFilter(createParser(inputStream), false));
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    public <T> T readValue(JsonParser jsonParser, Class<T> cls) throws IOException {
        _assertNotNull("p", jsonParser);
        return (T) forType((Class<?>) cls).readValue(jsonParser);
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    /* renamed from: readValues */
    public <T> Iterator<T> mo7037readValues(JsonParser jsonParser, TypeReference<T> typeReference) throws IOException {
        _assertNotNull("p", jsonParser);
        return forType((TypeReference<?>) typeReference).readValues(jsonParser);
    }

    public ObjectReader with(JsonParser.Feature feature) {
        return _with(this._config.with(feature));
    }

    public ObjectReader withFeatures(FormatFeature... formatFeatureArr) {
        return _with(this._config.withFeatures(formatFeatureArr));
    }

    @Deprecated
    public ObjectReader withType(Type type) {
        return forType(this._config.getTypeFactory().constructType(type));
    }

    public ObjectReader without(JsonParser.Feature feature) {
        return _with(this._config.without(feature));
    }

    public ObjectReader withoutFeatures(FormatFeature... formatFeatureArr) {
        return _with(this._config.withoutFeatures(formatFeatureArr));
    }

    public boolean isEnabled(StreamReadFeature streamReadFeature) {
        return this._config.isEnabled(streamReadFeature.mappedFeature(), this._parserFactory);
    }

    public ObjectReader with(StreamReadFeature streamReadFeature) {
        return _with(this._config.with(streamReadFeature.mappedFeature()));
    }

    @Deprecated
    public ObjectReader withType(TypeReference<?> typeReference) {
        return forType(this._config.getTypeFactory().constructType(typeReference.getType()));
    }

    public ObjectReader without(StreamReadFeature streamReadFeature) {
        return _with(this._config.without(streamReadFeature.mappedFeature()));
    }

    public JsonParser createParser(InputStream inputStream) throws IOException {
        _assertNotNull("in", inputStream);
        return this._config.initialize(this._parserFactory.createParser(inputStream), this._schema);
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    public <T> T readValue(JsonParser jsonParser, TypeReference<T> typeReference) throws IOException {
        _assertNotNull("p", jsonParser);
        return (T) forType((TypeReference<?>) typeReference).readValue(jsonParser);
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    /* renamed from: readValues */
    public <T> Iterator<T> mo7036readValues(JsonParser jsonParser, ResolvedType resolvedType) throws IOException {
        _assertNotNull("p", jsonParser);
        return readValues(jsonParser, (JavaType) resolvedType);
    }

    public ObjectReader with(FormatFeature formatFeature) {
        return _with(this._config.with(formatFeature));
    }

    public ObjectReader without(FormatFeature formatFeature) {
        return _with(this._config.without(formatFeature));
    }

    protected Object _detectBindAndClose(DataFormatReaders.Match match, boolean z) throws IOException {
        if (!match.hasMatch()) {
            _reportUnkownFormat(this._dataFormatReaders, match);
        }
        JsonParser createParserWithMatch = match.createParserWithMatch();
        if (z) {
            createParserWithMatch.enable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
        }
        return match.getReader()._bindAndClose(createParserWithMatch);
    }

    public ObjectReader forType(Class<?> cls) {
        return forType(this._config.constructType(cls));
    }

    public JsonNode readTree(Reader reader) throws IOException {
        if (this._dataFormatReaders != null) {
            _reportUndetectableSource(reader);
        }
        return _bindAndCloseAsTree(_considerFilter(createParser(reader), false));
    }

    public ObjectReader with(DeserializationConfig deserializationConfig) {
        return _with(deserializationConfig);
    }

    public JsonParser createParser(Reader reader) throws IOException {
        _assertNotNull("r", reader);
        return this._config.initialize(this._parserFactory.createParser(reader), this._schema);
    }

    public ObjectReader forType(TypeReference<?> typeReference) {
        return forType(this._config.getTypeFactory().constructType(typeReference.getType()));
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    public <T> T readValue(JsonParser jsonParser, ResolvedType resolvedType) throws IOException {
        _assertNotNull("p", jsonParser);
        return (T) forType((JavaType) resolvedType).readValue(jsonParser);
    }

    public <T> Iterator<T> readValues(JsonParser jsonParser, JavaType javaType) throws IOException {
        _assertNotNull("p", jsonParser);
        return forType(javaType).readValues(jsonParser);
    }

    public ObjectReader with(InjectableValues injectableValues) {
        return this._injectableValues == injectableValues ? this : _new(this, this._config, this._valueType, this._rootDeserializer, this._valueToUpdate, this._schema, injectableValues, this._dataFormatReaders);
    }

    public JsonParser createParser(byte[] bArr) throws IOException {
        _assertNotNull("content", bArr);
        return this._config.initialize(this._parserFactory.createParser(bArr), this._schema);
    }

    public JsonNode readTree(String str) throws JsonProcessingException, JsonMappingException {
        if (this._dataFormatReaders != null) {
            _reportUndetectableSource(str);
        }
        try {
            return _bindAndCloseAsTree(_considerFilter(createParser(str), false));
        } catch (JsonProcessingException e) {
            throw e;
        } catch (IOException e2) {
            throw JsonMappingException.fromUnexpectedIOE(e2);
        }
    }

    public <T> T readValue(JsonParser jsonParser, JavaType javaType) throws IOException {
        _assertNotNull("p", jsonParser);
        return (T) forType(javaType).readValue(jsonParser);
    }

    public <T> MappingIterator<T> readValues(JsonParser jsonParser) throws IOException {
        _assertNotNull("p", jsonParser);
        DefaultDeserializationContext createDeserializationContext = createDeserializationContext(jsonParser);
        return _newIterator(jsonParser, createDeserializationContext, _findRootDeserializer(createDeserializationContext), false);
    }

    public ObjectReader with(JsonNodeFactory jsonNodeFactory) {
        return _with(this._config.with(jsonNodeFactory));
    }

    public ObjectReader with(JsonFactory jsonFactory) {
        if (jsonFactory == this._parserFactory) {
            return this;
        }
        ObjectReader _new = _new(this, jsonFactory);
        if (jsonFactory.mo7031getCodec() == null) {
            jsonFactory.setCodec(_new);
        }
        return _new;
    }

    public JsonParser createParser(byte[] bArr, int i, int i2) throws IOException {
        _assertNotNull("content", bArr);
        return this._config.initialize(this._parserFactory.createParser(bArr, i, i2), this._schema);
    }

    public <T> T readValue(InputStream inputStream) throws IOException {
        DataFormatReaders dataFormatReaders = this._dataFormatReaders;
        if (dataFormatReaders != null) {
            return (T) _detectBindAndClose(dataFormatReaders.findFormat(inputStream), false);
        }
        return (T) _bindAndClose(_considerFilter(createParser(inputStream), false));
    }

    public <T> MappingIterator<T> readValues(InputStream inputStream) throws IOException {
        DataFormatReaders dataFormatReaders = this._dataFormatReaders;
        if (dataFormatReaders != null) {
            return _detectBindAndReadValues(dataFormatReaders.findFormat(inputStream), false);
        }
        return _bindAndReadValues(_considerFilter(createParser(inputStream), true));
    }

    public JsonParser createParser(String str) throws IOException {
        _assertNotNull("content", str);
        return this._config.initialize(this._parserFactory.createParser(str), this._schema);
    }

    public JsonNode readTree(byte[] bArr) throws IOException {
        _assertNotNull("json", bArr);
        if (this._dataFormatReaders != null) {
            _reportUndetectableSource(bArr);
        }
        return _bindAndCloseAsTree(_considerFilter(createParser(bArr), false));
    }

    public <T> T readValue(InputStream inputStream, Class<T> cls) throws IOException {
        return (T) forType((Class<?>) cls).readValue(inputStream);
    }

    public ObjectReader with(FormatSchema formatSchema) {
        if (this._schema == formatSchema) {
            return this;
        }
        _verifySchemaType(formatSchema);
        return _new(this, this._config, this._valueType, this._rootDeserializer, this._valueToUpdate, formatSchema, this._injectableValues, this._dataFormatReaders);
    }

    protected ObjectReader(ObjectReader objectReader, DeserializationConfig deserializationConfig, JavaType javaType, JsonDeserializer<Object> jsonDeserializer, Object obj, FormatSchema formatSchema, InjectableValues injectableValues, DataFormatReaders dataFormatReaders) {
        this._config = deserializationConfig;
        this._context = objectReader._context;
        this._rootDeserializers = objectReader._rootDeserializers;
        this._parserFactory = objectReader._parserFactory;
        this._valueType = javaType;
        this._rootDeserializer = jsonDeserializer;
        this._valueToUpdate = obj;
        this._schema = formatSchema;
        this._injectableValues = injectableValues;
        this._unwrapRoot = deserializationConfig.useRootWrapping();
        this._dataFormatReaders = dataFormatReaders;
        this._filter = objectReader._filter;
    }

    public JsonParser createParser(char[] cArr) throws IOException {
        _assertNotNull("content", cArr);
        return this._config.initialize(this._parserFactory.createParser(cArr), this._schema);
    }

    public <T> T readValue(Reader reader) throws IOException {
        if (this._dataFormatReaders != null) {
            _reportUndetectableSource(reader);
        }
        return (T) _bindAndClose(_considerFilter(createParser(reader), false));
    }

    public <T> MappingIterator<T> readValues(Reader reader) throws IOException {
        if (this._dataFormatReaders != null) {
            _reportUndetectableSource(reader);
        }
        JsonParser _considerFilter = _considerFilter(createParser(reader), true);
        DefaultDeserializationContext createDeserializationContext = createDeserializationContext(_considerFilter);
        _initForMultiRead(createDeserializationContext, _considerFilter);
        _considerFilter.nextToken();
        return _newIterator(_considerFilter, createDeserializationContext, _findRootDeserializer(createDeserializationContext), true);
    }

    public JsonParser createParser(char[] cArr, int i, int i2) throws IOException {
        _assertNotNull("content", cArr);
        return this._config.initialize(this._parserFactory.createParser(cArr, i, i2), this._schema);
    }

    public ObjectReader with(Locale locale) {
        return _with(this._config.with(locale));
    }

    public JsonNode readTree(byte[] bArr, int i, int i2) throws IOException {
        if (this._dataFormatReaders != null) {
            _reportUndetectableSource(bArr);
        }
        return _bindAndCloseAsTree(_considerFilter(createParser(bArr, i, i2), false));
    }

    public <T> T readValue(Reader reader, Class<T> cls) throws IOException {
        return (T) forType((Class<?>) cls).readValue(reader);
    }

    public ObjectReader with(TimeZone timeZone) {
        return _with(this._config.with(timeZone));
    }

    public JsonParser createParser(DataInput dataInput) throws IOException {
        _assertNotNull("content", dataInput);
        return this._config.initialize(this._parserFactory.createParser(dataInput), this._schema);
    }

    public <T> T readValue(String str) throws JsonProcessingException, JsonMappingException {
        if (this._dataFormatReaders != null) {
            _reportUndetectableSource(str);
        }
        try {
            return (T) _bindAndClose(_considerFilter(createParser(str), false));
        } catch (JsonProcessingException e) {
            throw e;
        } catch (IOException e2) {
            throw JsonMappingException.fromUnexpectedIOE(e2);
        }
    }

    public ObjectReader with(Base64Variant base64Variant) {
        return _with(this._config.with(base64Variant));
    }

    public ObjectReader with(ContextAttributes contextAttributes) {
        return _with(this._config.mo7046with(contextAttributes));
    }

    public JsonNode readTree(DataInput dataInput) throws IOException {
        if (this._dataFormatReaders != null) {
            _reportUndetectableSource(dataInput);
        }
        return _bindAndCloseAsTree(_considerFilter(createParser(dataInput), false));
    }

    public <T> MappingIterator<T> readValues(String str) throws IOException {
        if (this._dataFormatReaders != null) {
            _reportUndetectableSource(str);
        }
        JsonParser _considerFilter = _considerFilter(createParser(str), true);
        DefaultDeserializationContext createDeserializationContext = createDeserializationContext(_considerFilter);
        _initForMultiRead(createDeserializationContext, _considerFilter);
        _considerFilter.nextToken();
        return _newIterator(_considerFilter, createDeserializationContext, _findRootDeserializer(createDeserializationContext), true);
    }

    public <T> T readValue(String str, Class<T> cls) throws IOException {
        return (T) forType((Class<?>) cls).readValue(str);
    }

    public <T> T readValue(byte[] bArr) throws IOException {
        if (this._dataFormatReaders != null) {
            return (T) _detectBindAndClose(bArr, 0, bArr.length);
        }
        return (T) _bindAndClose(_considerFilter(createParser(bArr), false));
    }

    protected ObjectReader(ObjectReader objectReader, DeserializationConfig deserializationConfig) {
        this._config = deserializationConfig;
        this._context = objectReader._context;
        this._rootDeserializers = objectReader._rootDeserializers;
        this._parserFactory = objectReader._parserFactory;
        this._valueType = objectReader._valueType;
        this._rootDeserializer = objectReader._rootDeserializer;
        this._valueToUpdate = objectReader._valueToUpdate;
        this._schema = objectReader._schema;
        this._injectableValues = objectReader._injectableValues;
        this._unwrapRoot = deserializationConfig.useRootWrapping();
        this._dataFormatReaders = objectReader._dataFormatReaders;
        this._filter = objectReader._filter;
    }

    public <T> T readValue(byte[] bArr, Class<T> cls) throws IOException {
        return (T) forType((Class<?>) cls).readValue(bArr);
    }

    public <T> T readValue(byte[] bArr, int i, int i2) throws IOException {
        if (this._dataFormatReaders != null) {
            return (T) _detectBindAndClose(bArr, i, i2);
        }
        return (T) _bindAndClose(_considerFilter(createParser(bArr, i, i2), false));
    }

    public <T> MappingIterator<T> readValues(byte[] bArr, int i, int i2) throws IOException {
        DataFormatReaders dataFormatReaders = this._dataFormatReaders;
        if (dataFormatReaders != null) {
            return _detectBindAndReadValues(dataFormatReaders.findFormat(bArr, i, i2), false);
        }
        return _bindAndReadValues(_considerFilter(createParser(bArr, i, i2), true));
    }

    public <T> T readValue(byte[] bArr, int i, int i2, Class<T> cls) throws IOException {
        return (T) forType((Class<?>) cls).readValue(bArr, i, i2);
    }

    public final <T> MappingIterator<T> readValues(byte[] bArr) throws IOException {
        _assertNotNull("src", bArr);
        return readValues(bArr, 0, bArr.length);
    }

    public <T> T readValue(File file) throws IOException {
        DataFormatReaders dataFormatReaders = this._dataFormatReaders;
        if (dataFormatReaders != null) {
            return (T) _detectBindAndClose(dataFormatReaders.findFormat(_inputStream(file)), true);
        }
        return (T) _bindAndClose(_considerFilter(createParser(file), false));
    }

    public <T> MappingIterator<T> readValues(File file) throws IOException {
        DataFormatReaders dataFormatReaders = this._dataFormatReaders;
        if (dataFormatReaders != null) {
            return _detectBindAndReadValues(dataFormatReaders.findFormat(_inputStream(file)), false);
        }
        return _bindAndReadValues(_considerFilter(createParser(file), true));
    }

    public <T> T readValue(File file, Class<T> cls) throws IOException {
        return (T) forType((Class<?>) cls).readValue(file);
    }

    public <T> T readValue(URL url) throws IOException {
        DataFormatReaders dataFormatReaders = this._dataFormatReaders;
        if (dataFormatReaders != null) {
            return (T) _detectBindAndClose(dataFormatReaders.findFormat(_inputStream(url)), true);
        }
        return (T) _bindAndClose(_considerFilter(createParser(url), false));
    }

    public <T> MappingIterator<T> readValues(URL url) throws IOException {
        DataFormatReaders dataFormatReaders = this._dataFormatReaders;
        if (dataFormatReaders != null) {
            return _detectBindAndReadValues(dataFormatReaders.findFormat(_inputStream(url)), true);
        }
        return _bindAndReadValues(_considerFilter(createParser(url), true));
    }

    public <T> T readValue(URL url, Class<T> cls) throws IOException {
        return (T) forType((Class<?>) cls).readValue(url);
    }

    protected ObjectReader(ObjectReader objectReader, JsonFactory jsonFactory) {
        this._config = objectReader._config.mo7054with(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, jsonFactory.requiresPropertyOrdering());
        this._context = objectReader._context;
        this._rootDeserializers = objectReader._rootDeserializers;
        this._parserFactory = jsonFactory;
        this._valueType = objectReader._valueType;
        this._rootDeserializer = objectReader._rootDeserializer;
        this._valueToUpdate = objectReader._valueToUpdate;
        this._schema = objectReader._schema;
        this._injectableValues = objectReader._injectableValues;
        this._unwrapRoot = objectReader._unwrapRoot;
        this._dataFormatReaders = objectReader._dataFormatReaders;
        this._filter = objectReader._filter;
    }

    public <T> T readValue(JsonNode jsonNode) throws IOException {
        _assertNotNull("content", jsonNode);
        if (this._dataFormatReaders != null) {
            _reportUndetectableSource(jsonNode);
        }
        return (T) _bindAndClose(_considerFilter(treeAsTokens(jsonNode), false));
    }

    public <T> MappingIterator<T> readValues(DataInput dataInput) throws IOException {
        if (this._dataFormatReaders != null) {
            _reportUndetectableSource(dataInput);
        }
        return _bindAndReadValues(_considerFilter(createParser(dataInput), true));
    }

    public <T> T readValue(JsonNode jsonNode, Class<T> cls) throws IOException {
        return (T) forType((Class<?>) cls).readValue(jsonNode);
    }

    public <T> T readValue(DataInput dataInput) throws IOException {
        if (this._dataFormatReaders != null) {
            _reportUndetectableSource(dataInput);
        }
        return (T) _bindAndClose(_considerFilter(createParser(dataInput), false));
    }

    public <T> T readValue(DataInput dataInput, Class<T> cls) throws IOException {
        return (T) forType((Class<?>) cls).readValue(dataInput);
    }

    protected ObjectReader(ObjectReader objectReader, TokenFilter tokenFilter) {
        this._config = objectReader._config;
        this._context = objectReader._context;
        this._rootDeserializers = objectReader._rootDeserializers;
        this._parserFactory = objectReader._parserFactory;
        this._valueType = objectReader._valueType;
        this._rootDeserializer = objectReader._rootDeserializer;
        this._valueToUpdate = objectReader._valueToUpdate;
        this._schema = objectReader._schema;
        this._injectableValues = objectReader._injectableValues;
        this._unwrapRoot = objectReader._unwrapRoot;
        this._dataFormatReaders = objectReader._dataFormatReaders;
        this._filter = tokenFilter;
    }
}
