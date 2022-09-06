package com.amazon.org.codehaus.jackson.map;

import com.amazon.org.codehaus.jackson.FormatSchema;
import com.amazon.org.codehaus.jackson.JsonEncoding;
import com.amazon.org.codehaus.jackson.JsonFactory;
import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.JsonParseException;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.ObjectCodec;
import com.amazon.org.codehaus.jackson.PrettyPrinter;
import com.amazon.org.codehaus.jackson.Version;
import com.amazon.org.codehaus.jackson.Versioned;
import com.amazon.org.codehaus.jackson.annotate.JsonAutoDetect;
import com.amazon.org.codehaus.jackson.annotate.JsonMethod;
import com.amazon.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.amazon.org.codehaus.jackson.io.SegmentedStringWriter;
import com.amazon.org.codehaus.jackson.io.SerializedString;
import com.amazon.org.codehaus.jackson.map.DeserializationConfig;
import com.amazon.org.codehaus.jackson.map.Module;
import com.amazon.org.codehaus.jackson.map.SerializationConfig;
import com.amazon.org.codehaus.jackson.map.annotate.JsonSerialize;
import com.amazon.org.codehaus.jackson.map.deser.BeanDeserializerModifier;
import com.amazon.org.codehaus.jackson.map.deser.StdDeserializationContext;
import com.amazon.org.codehaus.jackson.map.deser.StdDeserializerProvider;
import com.amazon.org.codehaus.jackson.map.deser.ValueInstantiators;
import com.amazon.org.codehaus.jackson.map.introspect.BasicClassIntrospector;
import com.amazon.org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker;
import com.amazon.org.codehaus.jackson.map.jsontype.NamedType;
import com.amazon.org.codehaus.jackson.map.jsontype.SubtypeResolver;
import com.amazon.org.codehaus.jackson.map.jsontype.TypeIdResolver;
import com.amazon.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import com.amazon.org.codehaus.jackson.map.jsontype.impl.StdSubtypeResolver;
import com.amazon.org.codehaus.jackson.map.jsontype.impl.StdTypeResolverBuilder;
import com.amazon.org.codehaus.jackson.map.ser.BeanSerializerFactory;
import com.amazon.org.codehaus.jackson.map.ser.BeanSerializerModifier;
import com.amazon.org.codehaus.jackson.map.ser.FilterProvider;
import com.amazon.org.codehaus.jackson.map.ser.StdSerializerProvider;
import com.amazon.org.codehaus.jackson.map.type.SimpleType;
import com.amazon.org.codehaus.jackson.map.type.TypeFactory;
import com.amazon.org.codehaus.jackson.map.type.TypeModifier;
import com.amazon.org.codehaus.jackson.node.ArrayNode;
import com.amazon.org.codehaus.jackson.node.JsonNodeFactory;
import com.amazon.org.codehaus.jackson.node.NullNode;
import com.amazon.org.codehaus.jackson.node.ObjectNode;
import com.amazon.org.codehaus.jackson.node.TreeTraversingParser;
import com.amazon.org.codehaus.jackson.schema.JsonSchema;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.amazon.org.codehaus.jackson.type.TypeReference;
import com.amazon.org.codehaus.jackson.util.ByteArrayBuilder;
import com.amazon.org.codehaus.jackson.util.DefaultPrettyPrinter;
import com.amazon.org.codehaus.jackson.util.TokenBuffer;
import com.amazon.org.codehaus.jackson.util.VersionUtil;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.URL;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes13.dex */
public class ObjectMapper extends ObjectCodec implements Versioned {
    protected DeserializationConfig _deserializationConfig;
    protected DeserializerProvider _deserializerProvider;
    protected InjectableValues _injectableValues;
    protected final JsonFactory _jsonFactory;
    protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _rootDeserializers;
    protected SerializationConfig _serializationConfig;
    protected SerializerFactory _serializerFactory;
    protected SerializerProvider _serializerProvider;
    protected SubtypeResolver _subtypeResolver;
    protected TypeFactory _typeFactory;
    private static final JavaType JSON_NODE_TYPE = SimpleType.constructUnsafe(JsonNode.class);
    protected static final ClassIntrospector<? extends BeanDescription> DEFAULT_INTROSPECTOR = BasicClassIntrospector.instance;
    protected static final AnnotationIntrospector DEFAULT_ANNOTATION_INTROSPECTOR = new JacksonAnnotationIntrospector();
    protected static final VisibilityChecker<?> STD_VISIBILITY_CHECKER = VisibilityChecker.Std.defaultInstance();

    /* renamed from: com.amazon.org.codehaus.jackson.map.ObjectMapper$2  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$org$codehaus$jackson$map$ObjectMapper$DefaultTyping = new int[DefaultTyping.values().length];

        static {
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$map$ObjectMapper$DefaultTyping[DefaultTyping.NON_CONCRETE_AND_ARRAYS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$map$ObjectMapper$DefaultTyping[DefaultTyping.OBJECT_AND_NON_CONCRETE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$map$ObjectMapper$DefaultTyping[DefaultTyping.NON_FINAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes13.dex */
    public static class DefaultTypeResolverBuilder extends StdTypeResolverBuilder {
        protected final DefaultTyping _appliesFor;

        public DefaultTypeResolverBuilder(DefaultTyping defaultTyping) {
            this._appliesFor = defaultTyping;
        }

        @Override // com.amazon.org.codehaus.jackson.map.jsontype.impl.StdTypeResolverBuilder, com.amazon.org.codehaus.jackson.map.jsontype.TypeResolverBuilder
        public TypeDeserializer buildTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, Collection<NamedType> collection, BeanProperty beanProperty) {
            if (useForType(javaType)) {
                return super.buildTypeDeserializer(deserializationConfig, javaType, collection, beanProperty);
            }
            return null;
        }

        @Override // com.amazon.org.codehaus.jackson.map.jsontype.impl.StdTypeResolverBuilder, com.amazon.org.codehaus.jackson.map.jsontype.TypeResolverBuilder
        public TypeSerializer buildTypeSerializer(SerializationConfig serializationConfig, JavaType javaType, Collection<NamedType> collection, BeanProperty beanProperty) {
            if (useForType(javaType)) {
                return super.buildTypeSerializer(serializationConfig, javaType, collection, beanProperty);
            }
            return null;
        }

        public boolean useForType(JavaType javaType) {
            int ordinal = this._appliesFor.ordinal();
            if (ordinal != 1) {
                if (ordinal != 2) {
                    if (ordinal != 3) {
                        return javaType.getRawClass() == Object.class;
                    }
                    while (javaType.isArrayType()) {
                        javaType = javaType.getContentType();
                    }
                    return !javaType.isFinal();
                }
                while (javaType.isArrayType()) {
                    javaType = javaType.getContentType();
                }
            }
            return javaType.getRawClass() == Object.class || !javaType.isConcrete();
        }
    }

    /* loaded from: classes13.dex */
    public enum DefaultTyping {
        JAVA_LANG_OBJECT,
        OBJECT_AND_NON_CONCRETE,
        NON_CONCRETE_AND_ARRAYS,
        NON_FINAL
    }

    public ObjectMapper() {
        this(null, null, null);
    }

    private final void _configAndWriteCloseable(JsonGenerator jsonGenerator, Object obj, SerializationConfig serializationConfig) throws IOException, JsonGenerationException, JsonMappingException {
        Closeable closeable = (Closeable) obj;
        try {
            this._serializerProvider.serializeValue(serializationConfig, jsonGenerator, obj, this._serializerFactory);
            try {
                jsonGenerator.close();
                try {
                    closeable.close();
                } catch (Throwable th) {
                    th = th;
                    jsonGenerator = null;
                    closeable = null;
                    if (jsonGenerator != null) {
                        try {
                            jsonGenerator.close();
                        } catch (IOException unused) {
                        }
                    }
                    if (closeable != null) {
                        try {
                            closeable.close();
                        } catch (IOException unused2) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                jsonGenerator = null;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    private final void _writeCloseableValue(JsonGenerator jsonGenerator, Object obj, SerializationConfig serializationConfig) throws IOException, JsonGenerationException, JsonMappingException {
        Closeable closeable = (Closeable) obj;
        try {
            this._serializerProvider.serializeValue(serializationConfig, jsonGenerator, obj, this._serializerFactory);
            if (serializationConfig.isEnabled(SerializationConfig.Feature.FLUSH_AFTER_WRITE_VALUE)) {
                jsonGenerator.flush();
            }
            try {
                closeable.close();
            } catch (Throwable th) {
                closeable = null;
                th = th;
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (IOException unused) {
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    protected final void _configAndWriteValue(JsonGenerator jsonGenerator, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        SerializationConfig copySerializationConfig = copySerializationConfig();
        if (copySerializationConfig.isEnabled(SerializationConfig.Feature.INDENT_OUTPUT)) {
            jsonGenerator.useDefaultPrettyPrinter();
        }
        if (copySerializationConfig.isEnabled(SerializationConfig.Feature.CLOSE_CLOSEABLE) && (obj instanceof Closeable)) {
            _configAndWriteCloseable(jsonGenerator, obj, copySerializationConfig);
            return;
        }
        boolean z = false;
        try {
            this._serializerProvider.serializeValue(copySerializationConfig, jsonGenerator, obj, this._serializerFactory);
            z = true;
            jsonGenerator.close();
        } catch (Throwable th) {
            if (!z) {
                try {
                    jsonGenerator.close();
                } catch (IOException unused) {
                }
            }
            throw th;
        }
    }

    protected Object _convert(Object obj, JavaType javaType) throws IllegalArgumentException {
        if (obj == null) {
            return null;
        }
        TokenBuffer tokenBuffer = new TokenBuffer(this);
        try {
            writeValue(tokenBuffer, obj);
            JsonParser asParser = tokenBuffer.asParser();
            Object readValue = readValue(asParser, javaType);
            asParser.close();
            return readValue;
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    protected DeserializationContext _createDeserializationContext(JsonParser jsonParser, DeserializationConfig deserializationConfig) {
        return new StdDeserializationContext(deserializationConfig, jsonParser, this._deserializerProvider, this._injectableValues);
    }

    protected PrettyPrinter _defaultPrettyPrinter() {
        return new DefaultPrettyPrinter();
    }

    protected JsonDeserializer<Object> _findRootDeserializer(DeserializationConfig deserializationConfig, JavaType javaType) throws JsonMappingException {
        JsonDeserializer<Object> jsonDeserializer = this._rootDeserializers.get(javaType);
        if (jsonDeserializer != null) {
            return jsonDeserializer;
        }
        JsonDeserializer<Object> findTypedValueDeserializer = this._deserializerProvider.findTypedValueDeserializer(deserializationConfig, javaType, null);
        if (findTypedValueDeserializer != null) {
            this._rootDeserializers.put(javaType, findTypedValueDeserializer);
            return findTypedValueDeserializer;
        }
        throw new JsonMappingException("Can not find a deserializer for type " + javaType);
    }

    protected JsonToken _initForReading(JsonParser jsonParser) throws IOException, JsonParseException, JsonMappingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == null && (currentToken = jsonParser.nextToken()) == null) {
            throw new EOFException("No content to map to Object due to end of input");
        }
        return currentToken;
    }

    protected Object _readMapAndClose(JsonParser jsonParser, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        Object obj;
        try {
            JsonToken _initForReading = _initForReading(jsonParser);
            if (_initForReading == JsonToken.VALUE_NULL) {
                obj = _findRootDeserializer(this._deserializationConfig, javaType).getNullValue();
            } else {
                if (_initForReading != JsonToken.END_ARRAY && _initForReading != JsonToken.END_OBJECT) {
                    DeserializationConfig copyDeserializationConfig = copyDeserializationConfig();
                    DeserializationContext _createDeserializationContext = _createDeserializationContext(jsonParser, copyDeserializationConfig);
                    JsonDeserializer<Object> _findRootDeserializer = _findRootDeserializer(copyDeserializationConfig, javaType);
                    if (copyDeserializationConfig.isEnabled(DeserializationConfig.Feature.UNWRAP_ROOT_VALUE)) {
                        obj = _unwrapAndDeserialize(jsonParser, javaType, _createDeserializationContext, _findRootDeserializer);
                    } else {
                        obj = _findRootDeserializer.mo4206deserialize(jsonParser, _createDeserializationContext);
                    }
                }
                obj = null;
            }
            jsonParser.clearCurrentToken();
            return obj;
        } finally {
            try {
                jsonParser.close();
            } catch (IOException unused) {
            }
        }
    }

    protected Object _readValue(DeserializationConfig deserializationConfig, JsonParser jsonParser, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        Object obj;
        JsonToken _initForReading = _initForReading(jsonParser);
        if (_initForReading == JsonToken.VALUE_NULL) {
            obj = _findRootDeserializer(deserializationConfig, javaType).getNullValue();
        } else if (_initForReading == JsonToken.END_ARRAY || _initForReading == JsonToken.END_OBJECT) {
            obj = null;
        } else {
            DeserializationContext _createDeserializationContext = _createDeserializationContext(jsonParser, deserializationConfig);
            JsonDeserializer<Object> _findRootDeserializer = _findRootDeserializer(deserializationConfig, javaType);
            if (deserializationConfig.isEnabled(DeserializationConfig.Feature.UNWRAP_ROOT_VALUE)) {
                obj = _unwrapAndDeserialize(jsonParser, javaType, _createDeserializationContext, _findRootDeserializer);
            } else {
                obj = _findRootDeserializer.mo4206deserialize(jsonParser, _createDeserializationContext);
            }
        }
        jsonParser.clearCurrentToken();
        return obj;
    }

    protected Object _unwrapAndDeserialize(JsonParser jsonParser, JavaType javaType, DeserializationContext deserializationContext, JsonDeserializer<Object> jsonDeserializer) throws IOException, JsonParseException, JsonMappingException {
        SerializedString findExpectedRootName = this._deserializerProvider.findExpectedRootName(deserializationContext.getConfig(), javaType);
        if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
            if (jsonParser.nextToken() == JsonToken.FIELD_NAME) {
                String currentName = jsonParser.getCurrentName();
                if (findExpectedRootName.getValue().equals(currentName)) {
                    jsonParser.nextToken();
                    Object mo4206deserialize = jsonDeserializer.mo4206deserialize(jsonParser, deserializationContext);
                    if (jsonParser.nextToken() == JsonToken.END_OBJECT) {
                        return mo4206deserialize;
                    }
                    throw JsonMappingException.from(jsonParser, "Current token not END_OBJECT (to match wrapper object with root name '" + findExpectedRootName + "'), but " + jsonParser.getCurrentToken());
                }
                throw JsonMappingException.from(jsonParser, "Root name '" + currentName + "' does not match expected ('" + findExpectedRootName + "') for type " + javaType);
            }
            throw JsonMappingException.from(jsonParser, "Current token not FIELD_NAME (to contain expected root name '" + findExpectedRootName + "'), but " + jsonParser.getCurrentToken());
        }
        throw JsonMappingException.from(jsonParser, "Current token not START_OBJECT (needed to unwrap root name '" + findExpectedRootName + "'), but " + jsonParser.getCurrentToken());
    }

    public boolean canDeserialize(JavaType javaType) {
        return this._deserializerProvider.hasValueDeserializerFor(copyDeserializationConfig(), javaType);
    }

    public boolean canSerialize(Class<?> cls) {
        return this._serializerProvider.hasSerializerFor(copySerializationConfig(), cls, this._serializerFactory);
    }

    public ObjectMapper configure(SerializationConfig.Feature feature, boolean z) {
        this._serializationConfig.set(feature, z);
        return this;
    }

    public JavaType constructType(Type type) {
        return this._typeFactory.constructType(type);
    }

    public <T> T convertValue(Object obj, Class<T> cls) throws IllegalArgumentException {
        return (T) _convert(obj, this._typeFactory.constructType(cls));
    }

    public DeserializationConfig copyDeserializationConfig() {
        return this._deserializationConfig.mo4118createUnshared(this._subtypeResolver).passSerializationFeatures(this._serializationConfig._featureFlags);
    }

    public SerializationConfig copySerializationConfig() {
        return this._serializationConfig.mo4118createUnshared(this._subtypeResolver);
    }

    @Deprecated
    public ObjectWriter defaultPrettyPrintingWriter() {
        return writerWithDefaultPrettyPrinter();
    }

    public ObjectMapper disable(DeserializationConfig.Feature... featureArr) {
        this._deserializationConfig = this._deserializationConfig.without(featureArr);
        return this;
    }

    public ObjectMapper disableDefaultTyping() {
        return setDefaultTyping(null);
    }

    public ObjectMapper enable(DeserializationConfig.Feature... featureArr) {
        this._deserializationConfig = this._deserializationConfig.with(featureArr);
        return this;
    }

    public ObjectMapper enableDefaultTyping() {
        return enableDefaultTyping(DefaultTyping.OBJECT_AND_NON_CONCRETE);
    }

    public ObjectMapper enableDefaultTypingAsProperty(DefaultTyping defaultTyping, String str) {
        return setDefaultTyping(new DefaultTypeResolverBuilder(defaultTyping).init(JsonTypeInfo.Id.CLASS, (TypeIdResolver) null).inclusion(JsonTypeInfo.As.PROPERTY).typeProperty(str));
    }

    @Deprecated
    public ObjectWriter filteredWriter(FilterProvider filterProvider) {
        return writer(filterProvider);
    }

    public JsonSchema generateJsonSchema(Class<?> cls) throws JsonMappingException {
        return generateJsonSchema(cls, copySerializationConfig());
    }

    public DeserializationConfig getDeserializationConfig() {
        return this._deserializationConfig;
    }

    public DeserializerProvider getDeserializerProvider() {
        return this._deserializerProvider;
    }

    public JsonFactory getJsonFactory() {
        return this._jsonFactory;
    }

    public JsonNodeFactory getNodeFactory() {
        return this._deserializationConfig.getNodeFactory();
    }

    public SerializationConfig getSerializationConfig() {
        return this._serializationConfig;
    }

    public SerializerProvider getSerializerProvider() {
        return this._serializerProvider;
    }

    public SubtypeResolver getSubtypeResolver() {
        if (this._subtypeResolver == null) {
            this._subtypeResolver = new StdSubtypeResolver();
        }
        return this._subtypeResolver;
    }

    public TypeFactory getTypeFactory() {
        return this._typeFactory;
    }

    public VisibilityChecker<?> getVisibilityChecker() {
        return this._serializationConfig.getDefaultVisibilityChecker();
    }

    public boolean isEnabled(SerializationConfig.Feature feature) {
        return this._serializationConfig.isEnabled(feature);
    }

    @Deprecated
    public ObjectWriter prettyPrintingWriter(PrettyPrinter prettyPrinter) {
        return writer(prettyPrinter);
    }

    @Override // com.amazon.org.codehaus.jackson.ObjectCodec
    public JsonNode readTree(JsonParser jsonParser) throws IOException, JsonProcessingException {
        DeserializationConfig copyDeserializationConfig = copyDeserializationConfig();
        if (jsonParser.getCurrentToken() == null && jsonParser.nextToken() == null) {
            return null;
        }
        JsonNode jsonNode = (JsonNode) _readValue(copyDeserializationConfig, jsonParser, JSON_NODE_TYPE);
        return jsonNode == null ? getNodeFactory().nullNode() : jsonNode;
    }

    @Override // com.amazon.org.codehaus.jackson.ObjectCodec
    public <T> T readValue(JsonParser jsonParser, Class<T> cls) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readValue(copyDeserializationConfig(), jsonParser, this._typeFactory.constructType(cls));
    }

    public ObjectReader reader() {
        return new ObjectReader(this, copyDeserializationConfig()).withInjectableValues(this._injectableValues);
    }

    public ObjectReader readerForUpdating(Object obj) {
        return new ObjectReader(this, copyDeserializationConfig(), this._typeFactory.constructType(obj.getClass()), obj, (FormatSchema) null, this._injectableValues);
    }

    public void registerModule(Module module) {
        if (module.getModuleName() != null) {
            if (module.version() != null) {
                module.setupModule(new Module.SetupContext() { // from class: com.amazon.org.codehaus.jackson.map.ObjectMapper.1
                    @Override // com.amazon.org.codehaus.jackson.map.Module.SetupContext
                    public void addAbstractTypeResolver(AbstractTypeResolver abstractTypeResolver) {
                        ObjectMapper objectMapper = this;
                        objectMapper._deserializerProvider = objectMapper._deserializerProvider.withAbstractTypeResolver(abstractTypeResolver);
                    }

                    @Override // com.amazon.org.codehaus.jackson.map.Module.SetupContext
                    public void addBeanDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier) {
                        ObjectMapper objectMapper = this;
                        objectMapper._deserializerProvider = objectMapper._deserializerProvider.withDeserializerModifier(beanDeserializerModifier);
                    }

                    @Override // com.amazon.org.codehaus.jackson.map.Module.SetupContext
                    public void addBeanSerializerModifier(BeanSerializerModifier beanSerializerModifier) {
                        ObjectMapper objectMapper = this;
                        objectMapper._serializerFactory = objectMapper._serializerFactory.withSerializerModifier(beanSerializerModifier);
                    }

                    @Override // com.amazon.org.codehaus.jackson.map.Module.SetupContext
                    public void addDeserializers(Deserializers deserializers) {
                        ObjectMapper objectMapper = this;
                        objectMapper._deserializerProvider = objectMapper._deserializerProvider.withAdditionalDeserializers(deserializers);
                    }

                    @Override // com.amazon.org.codehaus.jackson.map.Module.SetupContext
                    public void addKeyDeserializers(KeyDeserializers keyDeserializers) {
                        ObjectMapper objectMapper = this;
                        objectMapper._deserializerProvider = objectMapper._deserializerProvider.withAdditionalKeyDeserializers(keyDeserializers);
                    }

                    @Override // com.amazon.org.codehaus.jackson.map.Module.SetupContext
                    public void addKeySerializers(Serializers serializers) {
                        ObjectMapper objectMapper = this;
                        objectMapper._serializerFactory = objectMapper._serializerFactory.withAdditionalKeySerializers(serializers);
                    }

                    @Override // com.amazon.org.codehaus.jackson.map.Module.SetupContext
                    public void addSerializers(Serializers serializers) {
                        ObjectMapper objectMapper = this;
                        objectMapper._serializerFactory = objectMapper._serializerFactory.withAdditionalSerializers(serializers);
                    }

                    @Override // com.amazon.org.codehaus.jackson.map.Module.SetupContext
                    public void addTypeModifier(TypeModifier typeModifier) {
                        this.setTypeFactory(this._typeFactory.withModifier(typeModifier));
                    }

                    @Override // com.amazon.org.codehaus.jackson.map.Module.SetupContext
                    public void addValueInstantiators(ValueInstantiators valueInstantiators) {
                        ObjectMapper objectMapper = this;
                        objectMapper._deserializerProvider = objectMapper._deserializerProvider.withValueInstantiators(valueInstantiators);
                    }

                    @Override // com.amazon.org.codehaus.jackson.map.Module.SetupContext
                    public void appendAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
                        ObjectMapper objectMapper = this;
                        objectMapper._deserializationConfig = objectMapper._deserializationConfig.mo4120withAppendedAnnotationIntrospector(annotationIntrospector);
                        ObjectMapper objectMapper2 = this;
                        objectMapper2._serializationConfig = objectMapper2._serializationConfig.mo4120withAppendedAnnotationIntrospector(annotationIntrospector);
                    }

                    @Override // com.amazon.org.codehaus.jackson.map.Module.SetupContext
                    public DeserializationConfig getDeserializationConfig() {
                        return this.getDeserializationConfig();
                    }

                    @Override // com.amazon.org.codehaus.jackson.map.Module.SetupContext
                    public Version getMapperVersion() {
                        return ObjectMapper.this.version();
                    }

                    @Override // com.amazon.org.codehaus.jackson.map.Module.SetupContext
                    public SerializationConfig getSerializationConfig() {
                        return this.getSerializationConfig();
                    }

                    @Override // com.amazon.org.codehaus.jackson.map.Module.SetupContext
                    public void insertAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
                        ObjectMapper objectMapper = this;
                        objectMapper._deserializationConfig = objectMapper._deserializationConfig.mo4124withInsertedAnnotationIntrospector(annotationIntrospector);
                        ObjectMapper objectMapper2 = this;
                        objectMapper2._serializationConfig = objectMapper2._serializationConfig.mo4124withInsertedAnnotationIntrospector(annotationIntrospector);
                    }

                    @Override // com.amazon.org.codehaus.jackson.map.Module.SetupContext
                    public boolean isEnabled(DeserializationConfig.Feature feature) {
                        return this.isEnabled(feature);
                    }

                    @Override // com.amazon.org.codehaus.jackson.map.Module.SetupContext
                    public void setMixInAnnotations(Class<?> cls, Class<?> cls2) {
                        this._deserializationConfig.addMixInAnnotations(cls, cls2);
                        this._serializationConfig.addMixInAnnotations(cls, cls2);
                    }

                    @Override // com.amazon.org.codehaus.jackson.map.Module.SetupContext
                    public boolean isEnabled(SerializationConfig.Feature feature) {
                        return this.isEnabled(feature);
                    }

                    @Override // com.amazon.org.codehaus.jackson.map.Module.SetupContext
                    public boolean isEnabled(JsonParser.Feature feature) {
                        return this.isEnabled(feature);
                    }

                    @Override // com.amazon.org.codehaus.jackson.map.Module.SetupContext
                    public boolean isEnabled(JsonGenerator.Feature feature) {
                        return this.isEnabled(feature);
                    }
                });
                return;
            }
            throw new IllegalArgumentException("Module without defined version");
        }
        throw new IllegalArgumentException("Module without defined name");
    }

    public void registerSubtypes(Class<?>... clsArr) {
        getSubtypeResolver().registerSubtypes(clsArr);
    }

    @Deprecated
    public ObjectReader schemaBasedReader(FormatSchema formatSchema) {
        return reader(formatSchema);
    }

    @Deprecated
    public ObjectWriter schemaBasedWriter(FormatSchema formatSchema) {
        return writer(formatSchema);
    }

    public ObjectMapper setAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        this._serializationConfig = this._serializationConfig.mo4119withAnnotationIntrospector(annotationIntrospector);
        this._deserializationConfig = this._deserializationConfig.mo4119withAnnotationIntrospector(annotationIntrospector);
        return this;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this._deserializationConfig = this._deserializationConfig.mo4122withDateFormat(dateFormat);
        this._serializationConfig = this._serializationConfig.mo4122withDateFormat(dateFormat);
    }

    public ObjectMapper setDefaultTyping(TypeResolverBuilder<?> typeResolverBuilder) {
        this._deserializationConfig = this._deserializationConfig.mo4128withTypeResolverBuilder(typeResolverBuilder);
        this._serializationConfig = this._serializationConfig.mo4128withTypeResolverBuilder(typeResolverBuilder);
        return this;
    }

    public ObjectMapper setDeserializationConfig(DeserializationConfig deserializationConfig) {
        this._deserializationConfig = deserializationConfig;
        return this;
    }

    public ObjectMapper setDeserializerProvider(DeserializerProvider deserializerProvider) {
        this._deserializerProvider = deserializerProvider;
        return this;
    }

    public void setFilters(FilterProvider filterProvider) {
        this._serializationConfig = this._serializationConfig.withFilters(filterProvider);
    }

    public void setHandlerInstantiator(HandlerInstantiator handlerInstantiator) {
        this._deserializationConfig = this._deserializationConfig.mo4123withHandlerInstantiator(handlerInstantiator);
        this._serializationConfig = this._serializationConfig.mo4123withHandlerInstantiator(handlerInstantiator);
    }

    public ObjectMapper setInjectableValues(InjectableValues injectableValues) {
        this._injectableValues = injectableValues;
        return this;
    }

    public ObjectMapper setNodeFactory(JsonNodeFactory jsonNodeFactory) {
        this._deserializationConfig = this._deserializationConfig.withNodeFactory(jsonNodeFactory);
        return this;
    }

    public ObjectMapper setPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy) {
        this._serializationConfig = this._serializationConfig.mo4125withPropertyNamingStrategy(propertyNamingStrategy);
        this._deserializationConfig = this._deserializationConfig.mo4125withPropertyNamingStrategy(propertyNamingStrategy);
        return this;
    }

    public ObjectMapper setSerializationConfig(SerializationConfig serializationConfig) {
        this._serializationConfig = serializationConfig;
        return this;
    }

    public ObjectMapper setSerializationInclusion(JsonSerialize.Inclusion inclusion) {
        this._serializationConfig = this._serializationConfig.withSerializationInclusion(inclusion);
        return this;
    }

    public ObjectMapper setSerializerFactory(SerializerFactory serializerFactory) {
        this._serializerFactory = serializerFactory;
        return this;
    }

    public ObjectMapper setSerializerProvider(SerializerProvider serializerProvider) {
        this._serializerProvider = serializerProvider;
        return this;
    }

    public void setSubtypeResolver(SubtypeResolver subtypeResolver) {
        this._subtypeResolver = subtypeResolver;
    }

    public ObjectMapper setTypeFactory(TypeFactory typeFactory) {
        this._typeFactory = typeFactory;
        this._deserializationConfig = this._deserializationConfig.mo4127withTypeFactory(typeFactory);
        this._serializationConfig = this._serializationConfig.mo4127withTypeFactory(typeFactory);
        return this;
    }

    public ObjectMapper setVisibility(JsonMethod jsonMethod, JsonAutoDetect.Visibility visibility) {
        this._deserializationConfig = this._deserializationConfig.mo4129withVisibility(jsonMethod, visibility);
        this._serializationConfig = this._serializationConfig.mo4129withVisibility(jsonMethod, visibility);
        return this;
    }

    public void setVisibilityChecker(VisibilityChecker<?> visibilityChecker) {
        this._deserializationConfig = this._deserializationConfig.mo4130withVisibilityChecker(visibilityChecker);
        this._serializationConfig = this._serializationConfig.mo4130withVisibilityChecker(visibilityChecker);
    }

    @Override // com.amazon.org.codehaus.jackson.ObjectCodec
    public JsonParser treeAsTokens(JsonNode jsonNode) {
        return new TreeTraversingParser(jsonNode, this);
    }

    @Override // com.amazon.org.codehaus.jackson.ObjectCodec
    public <T> T treeToValue(JsonNode jsonNode, Class<T> cls) throws IOException, JsonParseException, JsonMappingException {
        return (T) readValue(treeAsTokens(jsonNode), cls);
    }

    @Deprecated
    public ObjectWriter typedWriter(Class<?> cls) {
        return writerWithType(cls);
    }

    @Deprecated
    public ObjectReader updatingReader(Object obj) {
        return readerForUpdating(obj);
    }

    public <T extends JsonNode> T valueToTree(Object obj) throws IllegalArgumentException {
        if (obj == null) {
            return null;
        }
        TokenBuffer tokenBuffer = new TokenBuffer(this);
        try {
            writeValue(tokenBuffer, obj);
            JsonParser asParser = tokenBuffer.asParser();
            T t = (T) readTree(asParser);
            asParser.close();
            return t;
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    @Override // com.amazon.org.codehaus.jackson.Versioned
    public Version version() {
        return VersionUtil.versionFor(ObjectMapper.class);
    }

    @Deprecated
    public ObjectWriter viewWriter(Class<?> cls) {
        return writerWithView(cls);
    }

    public ObjectMapper withModule(Module module) {
        registerModule(module);
        return this;
    }

    @Override // com.amazon.org.codehaus.jackson.ObjectCodec
    public void writeTree(JsonGenerator jsonGenerator, JsonNode jsonNode) throws IOException, JsonProcessingException {
        SerializationConfig copySerializationConfig = copySerializationConfig();
        this._serializerProvider.serializeValue(copySerializationConfig, jsonGenerator, jsonNode, this._serializerFactory);
        if (copySerializationConfig.isEnabled(SerializationConfig.Feature.FLUSH_AFTER_WRITE_VALUE)) {
            jsonGenerator.flush();
        }
    }

    @Override // com.amazon.org.codehaus.jackson.ObjectCodec
    public void writeValue(JsonGenerator jsonGenerator, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        SerializationConfig copySerializationConfig = copySerializationConfig();
        if (copySerializationConfig.isEnabled(SerializationConfig.Feature.CLOSE_CLOSEABLE) && (obj instanceof Closeable)) {
            _writeCloseableValue(jsonGenerator, obj, copySerializationConfig);
            return;
        }
        this._serializerProvider.serializeValue(copySerializationConfig, jsonGenerator, obj, this._serializerFactory);
        if (!copySerializationConfig.isEnabled(SerializationConfig.Feature.FLUSH_AFTER_WRITE_VALUE)) {
            return;
        }
        jsonGenerator.flush();
    }

    public byte[] writeValueAsBytes(Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder(this._jsonFactory._getBufferRecycler());
        _configAndWriteValue(this._jsonFactory.createJsonGenerator(byteArrayBuilder, JsonEncoding.UTF8), obj);
        byte[] byteArray = byteArrayBuilder.toByteArray();
        byteArrayBuilder.release();
        return byteArray;
    }

    public String writeValueAsString(Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        SegmentedStringWriter segmentedStringWriter = new SegmentedStringWriter(this._jsonFactory._getBufferRecycler());
        _configAndWriteValue(this._jsonFactory.createJsonGenerator(segmentedStringWriter), obj);
        return segmentedStringWriter.getAndClear();
    }

    public ObjectWriter writer() {
        return new ObjectWriter(this, copySerializationConfig());
    }

    public ObjectWriter writerWithDefaultPrettyPrinter() {
        return new ObjectWriter(this, copySerializationConfig(), null, _defaultPrettyPrinter());
    }

    public ObjectWriter writerWithType(Class<?> cls) {
        return new ObjectWriter(this, copySerializationConfig(), cls == null ? null : this._typeFactory.constructType(cls), null);
    }

    public ObjectWriter writerWithView(Class<?> cls) {
        return new ObjectWriter(this, copySerializationConfig().withView(cls));
    }

    public ObjectMapper(JsonFactory jsonFactory) {
        this(jsonFactory, null, null);
    }

    public ObjectMapper configure(DeserializationConfig.Feature feature, boolean z) {
        this._deserializationConfig.set(feature, z);
        return this;
    }

    public <T> T convertValue(Object obj, TypeReference typeReference) throws IllegalArgumentException {
        return (T) _convert(obj, this._typeFactory.constructType(typeReference));
    }

    @Override // com.amazon.org.codehaus.jackson.ObjectCodec
    /* renamed from: createArrayNode  reason: collision with other method in class */
    public ArrayNode mo4113createArrayNode() {
        return this._deserializationConfig.getNodeFactory().arrayNode();
    }

    @Override // com.amazon.org.codehaus.jackson.ObjectCodec
    /* renamed from: createObjectNode  reason: collision with other method in class */
    public ObjectNode mo4114createObjectNode() {
        return this._deserializationConfig.getNodeFactory().objectNode();
    }

    public ObjectMapper disable(SerializationConfig.Feature... featureArr) {
        this._serializationConfig = this._serializationConfig.without(featureArr);
        return this;
    }

    public ObjectMapper enable(SerializationConfig.Feature... featureArr) {
        this._serializationConfig = this._serializationConfig.with(featureArr);
        return this;
    }

    public ObjectMapper enableDefaultTyping(DefaultTyping defaultTyping) {
        return enableDefaultTyping(defaultTyping, JsonTypeInfo.As.WRAPPER_ARRAY);
    }

    public JsonSchema generateJsonSchema(Class<?> cls, SerializationConfig serializationConfig) throws JsonMappingException {
        return this._serializerProvider.generateJsonSchema(cls, serializationConfig, this._serializerFactory);
    }

    public boolean isEnabled(DeserializationConfig.Feature feature) {
        return this._deserializationConfig.isEnabled(feature);
    }

    @Override // com.amazon.org.codehaus.jackson.ObjectCodec
    public <T> T readValue(JsonParser jsonParser, TypeReference<?> typeReference) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readValue(copyDeserializationConfig(), jsonParser, this._typeFactory.constructType(typeReference));
    }

    @Override // com.amazon.org.codehaus.jackson.ObjectCodec
    /* renamed from: readValues  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Iterator mo4116readValues(JsonParser jsonParser, TypeReference typeReference) throws IOException, JsonProcessingException {
        return mo4116readValues(jsonParser, (TypeReference<?>) typeReference);
    }

    public ObjectReader reader(JavaType javaType) {
        return new ObjectReader(this, copyDeserializationConfig(), javaType, (Object) null, (FormatSchema) null, this._injectableValues);
    }

    public void registerSubtypes(NamedType... namedTypeArr) {
        getSubtypeResolver().registerSubtypes(namedTypeArr);
    }

    @Deprecated
    public ObjectWriter typedWriter(JavaType javaType) {
        return writerWithType(javaType);
    }

    public ObjectWriter writer(DateFormat dateFormat) {
        return new ObjectWriter(this, copySerializationConfig().mo4122withDateFormat(dateFormat));
    }

    @Deprecated
    public ObjectMapper(SerializerFactory serializerFactory) {
        this(null, null, null);
        setSerializerFactory(serializerFactory);
    }

    public ObjectMapper configure(JsonParser.Feature feature, boolean z) {
        this._jsonFactory.configure(feature, z);
        return this;
    }

    public <T> T convertValue(Object obj, JavaType javaType) throws IllegalArgumentException {
        return (T) _convert(obj, javaType);
    }

    public ObjectMapper enableDefaultTyping(DefaultTyping defaultTyping, JsonTypeInfo.As as) {
        return setDefaultTyping(new DefaultTypeResolverBuilder(defaultTyping).init(JsonTypeInfo.Id.CLASS, (TypeIdResolver) null).inclusion(as));
    }

    public boolean isEnabled(JsonParser.Feature feature) {
        return this._jsonFactory.isEnabled(feature);
    }

    @Override // com.amazon.org.codehaus.jackson.ObjectCodec
    public <T> T readValue(JsonParser jsonParser, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readValue(copyDeserializationConfig(), jsonParser, javaType);
    }

    public ObjectReader reader(Class<?> cls) {
        return reader(this._typeFactory.constructType(cls));
    }

    @Deprecated
    public ObjectWriter typedWriter(TypeReference<?> typeReference) {
        return writerWithType(typeReference);
    }

    public ObjectWriter writer(PrettyPrinter prettyPrinter) {
        if (prettyPrinter == null) {
            prettyPrinter = ObjectWriter.NULL_PRETTY_PRINTER;
        }
        return new ObjectWriter(this, copySerializationConfig(), null, prettyPrinter);
    }

    public ObjectWriter writerWithType(JavaType javaType) {
        return new ObjectWriter(this, copySerializationConfig(), javaType, null);
    }

    public ObjectMapper configure(JsonGenerator.Feature feature, boolean z) {
        this._jsonFactory.configure(feature, z);
        return this;
    }

    public boolean isEnabled(JsonGenerator.Feature feature) {
        return this._jsonFactory.isEnabled(feature);
    }

    public <T> T readValue(JsonParser jsonParser, Class<T> cls, DeserializationConfig deserializationConfig) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readValue(deserializationConfig, jsonParser, this._typeFactory.constructType(cls));
    }

    @Override // com.amazon.org.codehaus.jackson.ObjectCodec
    /* renamed from: readValues */
    public <T> MappingIterator<T> mo4115readValues(JsonParser jsonParser, JavaType javaType) throws IOException, JsonProcessingException {
        DeserializationConfig copyDeserializationConfig = copyDeserializationConfig();
        return new MappingIterator<>(javaType, jsonParser, _createDeserializationContext(jsonParser, copyDeserializationConfig), _findRootDeserializer(copyDeserializationConfig, javaType), false, null);
    }

    public ObjectReader reader(TypeReference<?> typeReference) {
        return reader(this._typeFactory.constructType(typeReference));
    }

    public ObjectWriter writerWithType(TypeReference<?> typeReference) {
        return new ObjectWriter(this, copySerializationConfig(), typeReference == null ? null : this._typeFactory.constructType(typeReference), null);
    }

    public ObjectMapper(JsonFactory jsonFactory, SerializerProvider serializerProvider, DeserializerProvider deserializerProvider) {
        this(jsonFactory, serializerProvider, deserializerProvider, null, null);
    }

    public <T> T readValue(JsonParser jsonParser, TypeReference<?> typeReference, DeserializationConfig deserializationConfig) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readValue(deserializationConfig, jsonParser, this._typeFactory.constructType(typeReference));
    }

    public ObjectReader reader(JsonNodeFactory jsonNodeFactory) {
        return new ObjectReader(this, copyDeserializationConfig()).withNodeFactory(jsonNodeFactory);
    }

    public void writeTree(JsonGenerator jsonGenerator, JsonNode jsonNode, SerializationConfig serializationConfig) throws IOException, JsonProcessingException {
        this._serializerProvider.serializeValue(serializationConfig, jsonGenerator, jsonNode, this._serializerFactory);
        if (serializationConfig.isEnabled(SerializationConfig.Feature.FLUSH_AFTER_WRITE_VALUE)) {
            jsonGenerator.flush();
        }
    }

    public ObjectWriter writer(FilterProvider filterProvider) {
        return new ObjectWriter(this, copySerializationConfig().withFilters(filterProvider));
    }

    public ObjectMapper(JsonFactory jsonFactory, SerializerProvider serializerProvider, DeserializerProvider deserializerProvider, SerializationConfig serializationConfig, DeserializationConfig deserializationConfig) {
        this._rootDeserializers = new ConcurrentHashMap<>(64, 0.6f, 2);
        if (jsonFactory == null) {
            this._jsonFactory = new MappingJsonFactory(this);
        } else {
            this._jsonFactory = jsonFactory;
            if (jsonFactory.mo4112getCodec() == null) {
                this._jsonFactory.setCodec(this);
            }
        }
        this._typeFactory = TypeFactory.defaultInstance();
        this._serializationConfig = serializationConfig != null ? serializationConfig : new SerializationConfig(DEFAULT_INTROSPECTOR, DEFAULT_ANNOTATION_INTROSPECTOR, STD_VISIBILITY_CHECKER, null, null, this._typeFactory, null);
        this._deserializationConfig = deserializationConfig != null ? deserializationConfig : new DeserializationConfig(DEFAULT_INTROSPECTOR, DEFAULT_ANNOTATION_INTROSPECTOR, STD_VISIBILITY_CHECKER, null, null, this._typeFactory, null);
        this._serializerProvider = serializerProvider == null ? new StdSerializerProvider() : serializerProvider;
        this._deserializerProvider = deserializerProvider == null ? new StdDeserializerProvider() : deserializerProvider;
        this._serializerFactory = BeanSerializerFactory.instance;
    }

    public JsonNode readTree(JsonParser jsonParser, DeserializationConfig deserializationConfig) throws IOException, JsonProcessingException {
        JsonNode jsonNode = (JsonNode) _readValue(deserializationConfig, jsonParser, JSON_NODE_TYPE);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public <T> T readValue(JsonParser jsonParser, JavaType javaType, DeserializationConfig deserializationConfig) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readValue(deserializationConfig, jsonParser, javaType);
    }

    public ObjectReader reader(FormatSchema formatSchema) {
        return new ObjectReader(this, copyDeserializationConfig(), (JavaType) null, (Object) null, formatSchema, this._injectableValues);
    }

    public ObjectWriter writer(FormatSchema formatSchema) {
        return new ObjectWriter(this, copySerializationConfig(), formatSchema);
    }

    public <T> T readValue(File file, Class<T> cls) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readMapAndClose(this._jsonFactory.createJsonParser(file), this._typeFactory.constructType(cls));
    }

    public ObjectReader reader(InjectableValues injectableValues) {
        return new ObjectReader(this, copyDeserializationConfig(), (JavaType) null, (Object) null, (FormatSchema) null, injectableValues);
    }

    public void writeValue(JsonGenerator jsonGenerator, Object obj, SerializationConfig serializationConfig) throws IOException, JsonGenerationException, JsonMappingException {
        if (serializationConfig.isEnabled(SerializationConfig.Feature.CLOSE_CLOSEABLE) && (obj instanceof Closeable)) {
            _writeCloseableValue(jsonGenerator, obj, serializationConfig);
            return;
        }
        this._serializerProvider.serializeValue(serializationConfig, jsonGenerator, obj, this._serializerFactory);
        if (!serializationConfig.isEnabled(SerializationConfig.Feature.FLUSH_AFTER_WRITE_VALUE)) {
            return;
        }
        jsonGenerator.flush();
    }

    public JsonNode readTree(InputStream inputStream) throws IOException, JsonProcessingException {
        JsonNode jsonNode = (JsonNode) _readMapAndClose(this._jsonFactory.createJsonParser(inputStream), JSON_NODE_TYPE);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public <T> T readValue(File file, TypeReference typeReference) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readMapAndClose(this._jsonFactory.createJsonParser(file), this._typeFactory.constructType(typeReference));
    }

    @Override // com.amazon.org.codehaus.jackson.ObjectCodec
    /* renamed from: readValues */
    public <T> MappingIterator<T> mo4117readValues(JsonParser jsonParser, Class<T> cls) throws IOException, JsonProcessingException {
        return mo4115readValues(jsonParser, this._typeFactory.constructType(cls));
    }

    public <T> T readValue(File file, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readMapAndClose(this._jsonFactory.createJsonParser(file), javaType);
    }

    @Override // com.amazon.org.codehaus.jackson.ObjectCodec
    /* renamed from: readValues */
    public <T> MappingIterator<T> mo4116readValues(JsonParser jsonParser, TypeReference<?> typeReference) throws IOException, JsonProcessingException {
        return mo4115readValues(jsonParser, this._typeFactory.constructType(typeReference));
    }

    protected final void _configAndWriteValue(JsonGenerator jsonGenerator, Object obj, Class<?> cls) throws IOException, JsonGenerationException, JsonMappingException {
        SerializationConfig withView = copySerializationConfig().withView(cls);
        if (withView.isEnabled(SerializationConfig.Feature.INDENT_OUTPUT)) {
            jsonGenerator.useDefaultPrettyPrinter();
        }
        if (withView.isEnabled(SerializationConfig.Feature.CLOSE_CLOSEABLE) && (obj instanceof Closeable)) {
            _configAndWriteCloseable(jsonGenerator, obj, withView);
            return;
        }
        boolean z = false;
        try {
            this._serializerProvider.serializeValue(withView, jsonGenerator, obj, this._serializerFactory);
            z = true;
            jsonGenerator.close();
        } catch (Throwable th) {
            if (!z) {
                try {
                    jsonGenerator.close();
                } catch (IOException unused) {
                }
            }
            throw th;
        }
    }

    public JsonNode readTree(Reader reader) throws IOException, JsonProcessingException {
        JsonNode jsonNode = (JsonNode) _readMapAndClose(this._jsonFactory.createJsonParser(reader), JSON_NODE_TYPE);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public <T> T readValue(URL url, Class<T> cls) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readMapAndClose(this._jsonFactory.createJsonParser(url), this._typeFactory.constructType(cls));
    }

    public <T> T readValue(URL url, TypeReference typeReference) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readMapAndClose(this._jsonFactory.createJsonParser(url), this._typeFactory.constructType(typeReference));
    }

    public JsonNode readTree(String str) throws IOException, JsonProcessingException {
        JsonNode jsonNode = (JsonNode) _readMapAndClose(this._jsonFactory.createJsonParser(str), JSON_NODE_TYPE);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public <T> T readValue(URL url, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readMapAndClose(this._jsonFactory.createJsonParser(url), javaType);
    }

    public void writeValue(File file, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        _configAndWriteValue(this._jsonFactory.createJsonGenerator(file, JsonEncoding.UTF8), obj);
    }

    public <T> T readValue(String str, Class<T> cls) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readMapAndClose(this._jsonFactory.createJsonParser(str), this._typeFactory.constructType(cls));
    }

    public void writeValue(OutputStream outputStream, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        _configAndWriteValue(this._jsonFactory.createJsonGenerator(outputStream, JsonEncoding.UTF8), obj);
    }

    public JsonNode readTree(byte[] bArr) throws IOException, JsonProcessingException {
        JsonNode jsonNode = (JsonNode) _readMapAndClose(this._jsonFactory.createJsonParser(bArr), JSON_NODE_TYPE);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public <T> T readValue(String str, TypeReference typeReference) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readMapAndClose(this._jsonFactory.createJsonParser(str), this._typeFactory.constructType(typeReference));
    }

    public void writeValue(Writer writer, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        _configAndWriteValue(this._jsonFactory.createJsonGenerator(writer), obj);
    }

    public <T> T readValue(String str, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readMapAndClose(this._jsonFactory.createJsonParser(str), javaType);
    }

    public JsonNode readTree(File file) throws IOException, JsonProcessingException {
        JsonNode jsonNode = (JsonNode) _readMapAndClose(this._jsonFactory.createJsonParser(file), JSON_NODE_TYPE);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public <T> T readValue(Reader reader, Class<T> cls) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readMapAndClose(this._jsonFactory.createJsonParser(reader), this._typeFactory.constructType(cls));
    }

    public <T> T readValue(Reader reader, TypeReference typeReference) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readMapAndClose(this._jsonFactory.createJsonParser(reader), this._typeFactory.constructType(typeReference));
    }

    public JsonNode readTree(URL url) throws IOException, JsonProcessingException {
        JsonNode jsonNode = (JsonNode) _readMapAndClose(this._jsonFactory.createJsonParser(url), JSON_NODE_TYPE);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public <T> T readValue(Reader reader, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readMapAndClose(this._jsonFactory.createJsonParser(reader), javaType);
    }

    public <T> T readValue(InputStream inputStream, Class<T> cls) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readMapAndClose(this._jsonFactory.createJsonParser(inputStream), this._typeFactory.constructType(cls));
    }

    public <T> T readValue(InputStream inputStream, TypeReference typeReference) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readMapAndClose(this._jsonFactory.createJsonParser(inputStream), this._typeFactory.constructType(typeReference));
    }

    public <T> T readValue(InputStream inputStream, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readMapAndClose(this._jsonFactory.createJsonParser(inputStream), javaType);
    }

    public <T> T readValue(byte[] bArr, Class<T> cls) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readMapAndClose(this._jsonFactory.createJsonParser(bArr), this._typeFactory.constructType(cls));
    }

    public <T> T readValue(byte[] bArr, int i, int i2, Class<T> cls) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readMapAndClose(this._jsonFactory.createJsonParser(bArr, i, i2), this._typeFactory.constructType(cls));
    }

    public <T> T readValue(byte[] bArr, TypeReference typeReference) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readMapAndClose(this._jsonFactory.createJsonParser(bArr), this._typeFactory.constructType(typeReference));
    }

    public <T> T readValue(byte[] bArr, int i, int i2, TypeReference typeReference) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readMapAndClose(this._jsonFactory.createJsonParser(bArr, i, i2), this._typeFactory.constructType(typeReference));
    }

    public <T> T readValue(byte[] bArr, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readMapAndClose(this._jsonFactory.createJsonParser(bArr), javaType);
    }

    public <T> T readValue(byte[] bArr, int i, int i2, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readMapAndClose(this._jsonFactory.createJsonParser(bArr, i, i2), javaType);
    }

    public <T> T readValue(JsonNode jsonNode, Class<T> cls) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readValue(copyDeserializationConfig(), treeAsTokens(jsonNode), this._typeFactory.constructType(cls));
    }

    public <T> T readValue(JsonNode jsonNode, TypeReference typeReference) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readValue(copyDeserializationConfig(), treeAsTokens(jsonNode), this._typeFactory.constructType(typeReference));
    }

    public <T> T readValue(JsonNode jsonNode, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        return (T) _readValue(copyDeserializationConfig(), treeAsTokens(jsonNode), javaType);
    }
}
