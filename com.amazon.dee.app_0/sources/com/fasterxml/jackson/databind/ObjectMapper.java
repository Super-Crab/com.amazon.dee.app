package com.fasterxml.jackson.databind;

import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.core.StreamWriteFeature;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SegmentedStringWriter;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.cfg.BaseSettings;
import com.fasterxml.jackson.databind.cfg.CoercionConfigs;
import com.fasterxml.jackson.databind.cfg.ConfigOverrides;
import com.fasterxml.jackson.databind.cfg.ConstructorDetector;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.cfg.MutableCoercionConfig;
import com.fasterxml.jackson.databind.cfg.MutableConfigOverride;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.deser.KeyDeserializers;
import com.fasterxml.jackson.databind.deser.ValueInstantiators;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.introspect.AccessorNamingStrategy;
import com.fasterxml.jackson.databind.introspect.BasicClassIntrospector;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.introspect.DefaultAccessorNamingStrategy;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.SimpleMixInResolver;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.databind.jsontype.impl.StdSubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.POJONode;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.type.TypeModifier;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes2.dex */
public class ObjectMapper extends ObjectCodec implements Versioned, Serializable {
    protected static final AnnotationIntrospector DEFAULT_ANNOTATION_INTROSPECTOR = new JacksonAnnotationIntrospector();
    protected static final BaseSettings DEFAULT_BASE = new BaseSettings(null, DEFAULT_ANNOTATION_INTROSPECTOR, null, TypeFactory.defaultInstance(), null, StdDateFormat.instance, null, Locale.getDefault(), null, Base64Variants.getDefaultVariant(), LaissezFaireSubTypeValidator.instance, new DefaultAccessorNamingStrategy.Provider());
    private static final long serialVersionUID = 2;
    protected final CoercionConfigs _coercionConfigs;
    protected final ConfigOverrides _configOverrides;
    protected DeserializationConfig _deserializationConfig;
    protected DefaultDeserializationContext _deserializationContext;
    protected InjectableValues _injectableValues;
    protected final JsonFactory _jsonFactory;
    protected SimpleMixInResolver _mixIns;
    protected Set<Object> _registeredModuleTypes;
    protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _rootDeserializers;
    protected SerializationConfig _serializationConfig;
    protected SerializerFactory _serializerFactory;
    protected DefaultSerializerProvider _serializerProvider;
    protected SubtypeResolver _subtypeResolver;
    protected TypeFactory _typeFactory;

    /* renamed from: com.fasterxml.jackson.databind.ObjectMapper$3  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$databind$ObjectMapper$DefaultTyping = new int[DefaultTyping.values().length];

        static {
            try {
                $SwitchMap$com$fasterxml$jackson$databind$ObjectMapper$DefaultTyping[DefaultTyping.NON_CONCRETE_AND_ARRAYS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$databind$ObjectMapper$DefaultTyping[DefaultTyping.OBJECT_AND_NON_CONCRETE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$databind$ObjectMapper$DefaultTyping[DefaultTyping.NON_FINAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$databind$ObjectMapper$DefaultTyping[DefaultTyping.EVERYTHING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$databind$ObjectMapper$DefaultTyping[DefaultTyping.JAVA_LANG_OBJECT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class DefaultTypeResolverBuilder extends StdTypeResolverBuilder implements Serializable {
        private static final long serialVersionUID = 1;
        protected final DefaultTyping _appliesFor;
        protected final PolymorphicTypeValidator _subtypeValidator;

        @Deprecated
        public DefaultTypeResolverBuilder(DefaultTyping defaultTyping) {
            this(defaultTyping, LaissezFaireSubTypeValidator.instance);
        }

        private static <T> T _requireNonNull(T t, String str) {
            if (t != null) {
                return t;
            }
            throw new NullPointerException(str);
        }

        public static DefaultTypeResolverBuilder construct(DefaultTyping defaultTyping, PolymorphicTypeValidator polymorphicTypeValidator) {
            return new DefaultTypeResolverBuilder(defaultTyping, polymorphicTypeValidator);
        }

        @Override // com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder, com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder
        public TypeDeserializer buildTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, Collection<NamedType> collection) {
            if (useForType(javaType)) {
                return super.buildTypeDeserializer(deserializationConfig, javaType, collection);
            }
            return null;
        }

        @Override // com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder, com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder
        public TypeSerializer buildTypeSerializer(SerializationConfig serializationConfig, JavaType javaType, Collection<NamedType> collection) {
            if (useForType(javaType)) {
                return super.buildTypeSerializer(serializationConfig, javaType, collection);
            }
            return null;
        }

        @Override // com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder
        public PolymorphicTypeValidator subTypeValidator(MapperConfig<?> mapperConfig) {
            return this._subtypeValidator;
        }

        public boolean useForType(JavaType javaType) {
            if (javaType.isPrimitive()) {
                return false;
            }
            int ordinal = this._appliesFor.ordinal();
            if (ordinal != 1) {
                if (ordinal != 2) {
                    if (ordinal != 3) {
                        if (ordinal == 4) {
                            return true;
                        }
                        return javaType.isJavaLangObject();
                    }
                    while (javaType.isArrayType()) {
                        javaType = javaType.mo7243getContentType();
                    }
                    while (javaType.isReferenceType()) {
                        javaType = javaType.mo7244getReferencedType();
                    }
                    return !javaType.isFinal() && !TreeNode.class.isAssignableFrom(javaType.getRawClass());
                }
                while (javaType.isArrayType()) {
                    javaType = javaType.mo7243getContentType();
                }
            }
            while (javaType.isReferenceType()) {
                javaType = javaType.mo7244getReferencedType();
            }
            return javaType.isJavaLangObject() || (!javaType.isConcrete() && !TreeNode.class.isAssignableFrom(javaType.getRawClass()));
        }

        public DefaultTypeResolverBuilder(DefaultTyping defaultTyping, PolymorphicTypeValidator polymorphicTypeValidator) {
            this._appliesFor = (DefaultTyping) _requireNonNull(defaultTyping, "Can not pass `null` DefaultTyping");
            this._subtypeValidator = (PolymorphicTypeValidator) _requireNonNull(polymorphicTypeValidator, "Can not pass `null` PolymorphicTypeValidator");
        }
    }

    /* loaded from: classes2.dex */
    public enum DefaultTyping {
        JAVA_LANG_OBJECT,
        OBJECT_AND_NON_CONCRETE,
        NON_CONCRETE_AND_ARRAYS,
        NON_FINAL,
        EVERYTHING
    }

    public ObjectMapper() {
        this(null, null, null);
    }

    private final void _writeCloseable(JsonGenerator jsonGenerator, Object obj, SerializationConfig serializationConfig) throws IOException {
        Closeable closeable = (Closeable) obj;
        try {
            _serializerProvider(serializationConfig).serializeValue(jsonGenerator, obj);
        } catch (Exception e) {
            e = e;
        }
        try {
            closeable.close();
            jsonGenerator.close();
        } catch (Exception e2) {
            e = e2;
            closeable = null;
            ClassUtil.closeOnFailAndThrowAsIOE(jsonGenerator, closeable, e);
        }
    }

    private final void _writeCloseableValue(JsonGenerator jsonGenerator, Object obj, SerializationConfig serializationConfig) throws IOException {
        Closeable closeable = (Closeable) obj;
        try {
            _serializerProvider(serializationConfig).serializeValue(jsonGenerator, obj);
            if (serializationConfig.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
                jsonGenerator.flush();
            }
            closeable.close();
        } catch (Exception e) {
            ClassUtil.closeOnFailAndThrowAsIOE(null, closeable, e);
        }
    }

    public static List<Module> findModules() {
        return findModules(null);
    }

    private static <T> ServiceLoader<T> secureGetServiceLoader(final Class<T> cls, final ClassLoader classLoader) {
        if (System.getSecurityManager() == null) {
            return classLoader == null ? ServiceLoader.load(cls) : ServiceLoader.load(cls, classLoader);
        }
        return (ServiceLoader) AccessController.doPrivileged(new PrivilegedAction<ServiceLoader<T>>() { // from class: com.fasterxml.jackson.databind.ObjectMapper.2
            @Override // java.security.PrivilegedAction
            public ServiceLoader<T> run() {
                ClassLoader classLoader2 = classLoader;
                return classLoader2 == null ? ServiceLoader.load(cls) : ServiceLoader.load(cls, classLoader2);
            }
        });
    }

    protected final void _assertNotNull(String str, Object obj) {
        if (obj != null) {
            return;
        }
        throw new IllegalArgumentException(String.format("argument \"%s\" is null", str));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _checkInvalidCopy(Class<?> cls) {
        if (getClass() == cls) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed copy(): ");
        outline107.append(getClass().getName());
        outline107.append(" (version: ");
        outline107.append(version());
        outline107.append(") does not override copy(); it has to");
        throw new IllegalStateException(outline107.toString());
    }

    @Deprecated
    protected final void _configAndWriteValue(JsonGenerator jsonGenerator, Object obj) throws IOException {
        getSerializationConfig().initialize(jsonGenerator);
        _writeValueAndClose(jsonGenerator, obj);
    }

    protected TypeResolverBuilder<?> _constructDefaultTypeResolverBuilder(DefaultTyping defaultTyping, PolymorphicTypeValidator polymorphicTypeValidator) {
        return DefaultTypeResolverBuilder.construct(defaultTyping, polymorphicTypeValidator);
    }

    protected Object _convert(Object obj, JavaType javaType) throws IllegalArgumentException {
        Object obj2;
        TokenBuffer tokenBuffer = new TokenBuffer((ObjectCodec) this, false);
        if (isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
            tokenBuffer = tokenBuffer.forceUseOfBigDecimal(true);
        }
        try {
            _serializerProvider(getSerializationConfig().without(SerializationFeature.WRAP_ROOT_VALUE)).serializeValue(tokenBuffer, obj);
            JsonParser asParser = tokenBuffer.asParser();
            DeserializationConfig deserializationConfig = getDeserializationConfig();
            JsonToken _initForReading = _initForReading(asParser, javaType);
            if (_initForReading == JsonToken.VALUE_NULL) {
                DefaultDeserializationContext createDeserializationContext = createDeserializationContext(asParser, deserializationConfig);
                obj2 = _findRootDeserializer(createDeserializationContext, javaType).mo7075getNullValue(createDeserializationContext);
            } else {
                if (_initForReading != JsonToken.END_ARRAY && _initForReading != JsonToken.END_OBJECT) {
                    DefaultDeserializationContext createDeserializationContext2 = createDeserializationContext(asParser, deserializationConfig);
                    obj2 = _findRootDeserializer(createDeserializationContext2, javaType).mo7111deserialize(asParser, createDeserializationContext2);
                }
                obj2 = null;
            }
            asParser.close();
            return obj2;
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    protected JsonDeserializer<Object> _findRootDeserializer(DeserializationContext deserializationContext, JavaType javaType) throws JsonMappingException {
        JsonDeserializer<Object> jsonDeserializer = this._rootDeserializers.get(javaType);
        if (jsonDeserializer != null) {
            return jsonDeserializer;
        }
        JsonDeserializer<Object> findRootValueDeserializer = deserializationContext.findRootValueDeserializer(javaType);
        if (findRootValueDeserializer == null) {
            return (JsonDeserializer) deserializationContext.reportBadDefinition(javaType, "Cannot find a deserializer for type " + javaType);
        }
        this._rootDeserializers.put(javaType, findRootValueDeserializer);
        return findRootValueDeserializer;
    }

    protected JsonToken _initForReading(JsonParser jsonParser, JavaType javaType) throws IOException {
        this._deserializationConfig.initialize(jsonParser);
        JsonToken currentToken = jsonParser.currentToken();
        if (currentToken == null && (currentToken = jsonParser.nextToken()) == null) {
            throw MismatchedInputException.from(jsonParser, javaType, "No content to map due to end-of-input");
        }
        return currentToken;
    }

    protected ObjectReader _newReader(DeserializationConfig deserializationConfig) {
        return new ObjectReader(this, deserializationConfig);
    }

    protected ObjectWriter _newWriter(SerializationConfig serializationConfig) {
        return new ObjectWriter(this, serializationConfig);
    }

    protected Object _readMapAndClose(JsonParser jsonParser, JavaType javaType) throws IOException {
        try {
            DeserializationConfig deserializationConfig = getDeserializationConfig();
            DefaultDeserializationContext createDeserializationContext = createDeserializationContext(jsonParser, deserializationConfig);
            JsonToken _initForReading = _initForReading(jsonParser, javaType);
            Object obj = null;
            if (_initForReading == JsonToken.VALUE_NULL) {
                obj = _findRootDeserializer(createDeserializationContext, javaType).mo7075getNullValue(createDeserializationContext);
            } else if (_initForReading != JsonToken.END_ARRAY && _initForReading != JsonToken.END_OBJECT) {
                obj = createDeserializationContext.readRootValue(jsonParser, javaType, _findRootDeserializer(createDeserializationContext, javaType), null);
                createDeserializationContext.checkUnresolvedObjectId();
            }
            if (deserializationConfig.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
                _verifyNoTrailingTokens(jsonParser, createDeserializationContext, javaType);
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

    protected JsonNode _readTreeAndClose(JsonParser jsonParser) throws IOException {
        JsonNode jsonNode;
        try {
            JavaType constructType = constructType(JsonNode.class);
            DeserializationConfig deserializationConfig = getDeserializationConfig();
            deserializationConfig.initialize(jsonParser);
            JsonToken currentToken = jsonParser.currentToken();
            if (currentToken == null && (currentToken = jsonParser.nextToken()) == null) {
                JsonNode missingNode = deserializationConfig.getNodeFactory().missingNode();
                jsonParser.close();
                return missingNode;
            }
            DefaultDeserializationContext createDeserializationContext = createDeserializationContext(jsonParser, deserializationConfig);
            if (currentToken == JsonToken.VALUE_NULL) {
                jsonNode = deserializationConfig.getNodeFactory().mo7176nullNode();
            } else {
                jsonNode = (JsonNode) createDeserializationContext.readRootValue(jsonParser, constructType, _findRootDeserializer(createDeserializationContext, constructType), null);
            }
            if (deserializationConfig.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
                _verifyNoTrailingTokens(jsonParser, createDeserializationContext, constructType);
            }
            jsonParser.close();
            return jsonNode;
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

    protected Object _readValue(DeserializationConfig deserializationConfig, JsonParser jsonParser, JavaType javaType) throws IOException {
        JsonToken _initForReading = _initForReading(jsonParser, javaType);
        DefaultDeserializationContext createDeserializationContext = createDeserializationContext(jsonParser, deserializationConfig);
        Object obj = null;
        if (_initForReading == JsonToken.VALUE_NULL) {
            obj = _findRootDeserializer(createDeserializationContext, javaType).mo7075getNullValue(createDeserializationContext);
        } else if (_initForReading != JsonToken.END_ARRAY && _initForReading != JsonToken.END_OBJECT) {
            obj = createDeserializationContext.readRootValue(jsonParser, javaType, _findRootDeserializer(createDeserializationContext, javaType), null);
        }
        jsonParser.clearCurrentToken();
        if (deserializationConfig.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
            _verifyNoTrailingTokens(jsonParser, createDeserializationContext, javaType);
        }
        return obj;
    }

    protected DefaultSerializerProvider _serializerProvider(SerializationConfig serializationConfig) {
        return this._serializerProvider.mo7200createInstance(serializationConfig, this._serializerFactory);
    }

    protected final void _verifyNoTrailingTokens(JsonParser jsonParser, DeserializationContext deserializationContext, JavaType javaType) throws IOException {
        JsonToken nextToken = jsonParser.nextToken();
        if (nextToken != null) {
            deserializationContext.reportTrailingTokens(ClassUtil.rawClass(javaType), jsonParser, nextToken);
        }
    }

    protected void _verifySchemaType(FormatSchema formatSchema) {
        if (formatSchema == null || this._jsonFactory.canUseSchema(formatSchema)) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cannot use FormatSchema of type ");
        outline107.append(formatSchema.getClass().getName());
        outline107.append(" for format ");
        outline107.append(this._jsonFactory.getFormatName());
        throw new IllegalArgumentException(outline107.toString());
    }

    protected final void _writeValueAndClose(JsonGenerator jsonGenerator, Object obj) throws IOException {
        SerializationConfig serializationConfig = getSerializationConfig();
        if (serializationConfig.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) && (obj instanceof Closeable)) {
            _writeCloseable(jsonGenerator, obj, serializationConfig);
            return;
        }
        try {
            _serializerProvider(serializationConfig).serializeValue(jsonGenerator, obj);
            jsonGenerator.close();
        } catch (Exception e) {
            ClassUtil.closeOnFailAndThrowAsIOE(jsonGenerator, e);
        }
    }

    public void acceptJsonFormatVisitor(Class<?> cls, JsonFormatVisitorWrapper jsonFormatVisitorWrapper) throws JsonMappingException {
        acceptJsonFormatVisitor(this._typeFactory.constructType(cls), jsonFormatVisitorWrapper);
    }

    public ObjectMapper activateDefaultTyping(PolymorphicTypeValidator polymorphicTypeValidator) {
        return activateDefaultTyping(polymorphicTypeValidator, DefaultTyping.OBJECT_AND_NON_CONCRETE);
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder] */
    public ObjectMapper activateDefaultTypingAsProperty(PolymorphicTypeValidator polymorphicTypeValidator, DefaultTyping defaultTyping, String str) {
        return setDefaultTyping(_constructDefaultTypeResolverBuilder(defaultTyping, polymorphicTypeValidator).init(JsonTypeInfo.Id.CLASS, null).inclusion(JsonTypeInfo.As.PROPERTY).typeProperty(str));
    }

    public ObjectMapper addHandler(DeserializationProblemHandler deserializationProblemHandler) {
        this._deserializationConfig = this._deserializationConfig.withHandler(deserializationProblemHandler);
        return this;
    }

    public ObjectMapper addMixIn(Class<?> cls, Class<?> cls2) {
        this._mixIns.addLocalDefinition(cls, cls2);
        return this;
    }

    @Deprecated
    public final void addMixInAnnotations(Class<?> cls, Class<?> cls2) {
        addMixIn(cls, cls2);
    }

    public boolean canDeserialize(JavaType javaType) {
        return createDeserializationContext(null, getDeserializationConfig()).hasValueDeserializerFor(javaType, null);
    }

    public boolean canSerialize(Class<?> cls) {
        return _serializerProvider(getSerializationConfig()).hasSerializerFor(cls, null);
    }

    public ObjectMapper clearProblemHandlers() {
        this._deserializationConfig = this._deserializationConfig.withNoProblemHandlers();
        return this;
    }

    public MutableCoercionConfig coercionConfigDefaults() {
        return this._coercionConfigs.defaultCoercions();
    }

    public MutableCoercionConfig coercionConfigFor(LogicalType logicalType) {
        return this._coercionConfigs.findOrCreateCoercion(logicalType);
    }

    public MutableConfigOverride configOverride(Class<?> cls) {
        return this._configOverrides.findOrCreateOverride(cls);
    }

    public ObjectMapper configure(MapperFeature mapperFeature, boolean z) {
        this._serializationConfig = z ? this._serializationConfig.mo7055with(mapperFeature) : this._serializationConfig.mo7056without(mapperFeature);
        this._deserializationConfig = z ? this._deserializationConfig.mo7055with(mapperFeature) : this._deserializationConfig.mo7056without(mapperFeature);
        return this;
    }

    public JavaType constructType(Type type) {
        _assertNotNull("t", type);
        return this._typeFactory.constructType(type);
    }

    public <T> T convertValue(Object obj, Class<T> cls) throws IllegalArgumentException {
        return (T) _convert(obj, this._typeFactory.constructType(cls));
    }

    /* renamed from: copy */
    public ObjectMapper mo7146copy() {
        _checkInvalidCopy(ObjectMapper.class);
        return new ObjectMapper(this);
    }

    protected DefaultDeserializationContext createDeserializationContext(JsonParser jsonParser, DeserializationConfig deserializationConfig) {
        return this._deserializationContext.createInstance(deserializationConfig, jsonParser, this._injectableValues);
    }

    public JsonGenerator createGenerator(OutputStream outputStream) throws IOException {
        _assertNotNull("out", outputStream);
        JsonGenerator createGenerator = this._jsonFactory.createGenerator(outputStream, JsonEncoding.UTF8);
        this._serializationConfig.initialize(createGenerator);
        return createGenerator;
    }

    public JsonParser createNonBlockingByteArrayParser() throws IOException {
        return this._deserializationConfig.initialize(this._jsonFactory.createNonBlockingByteArrayParser());
    }

    public JsonParser createParser(File file) throws IOException {
        _assertNotNull("src", file);
        return this._deserializationConfig.initialize(this._jsonFactory.createParser(file));
    }

    public ObjectMapper deactivateDefaultTyping() {
        return setDefaultTyping(null);
    }

    protected ClassIntrospector defaultClassIntrospector() {
        return new BasicClassIntrospector();
    }

    public ObjectMapper disable(MapperFeature... mapperFeatureArr) {
        this._deserializationConfig = this._deserializationConfig.mo7056without(mapperFeatureArr);
        this._serializationConfig = this._serializationConfig.mo7056without(mapperFeatureArr);
        return this;
    }

    @Deprecated
    public ObjectMapper disableDefaultTyping() {
        return setDefaultTyping(null);
    }

    public ObjectMapper enable(MapperFeature... mapperFeatureArr) {
        this._deserializationConfig = this._deserializationConfig.mo7055with(mapperFeatureArr);
        this._serializationConfig = this._serializationConfig.mo7055with(mapperFeatureArr);
        return this;
    }

    @Deprecated
    public ObjectMapper enableDefaultTyping() {
        return activateDefaultTyping(getPolymorphicTypeValidator());
    }

    @Deprecated
    public ObjectMapper enableDefaultTypingAsProperty(DefaultTyping defaultTyping, String str) {
        return activateDefaultTypingAsProperty(getPolymorphicTypeValidator(), defaultTyping, str);
    }

    public ObjectMapper findAndRegisterModules() {
        return registerModules(findModules());
    }

    public Class<?> findMixInClassFor(Class<?> cls) {
        return this._mixIns.findMixInClassFor(cls);
    }

    @Deprecated
    public JsonSchema generateJsonSchema(Class<?> cls) throws JsonMappingException {
        return _serializerProvider(getSerializationConfig()).generateJsonSchema(cls);
    }

    public DateFormat getDateFormat() {
        return this._serializationConfig.getDateFormat();
    }

    public DeserializationConfig getDeserializationConfig() {
        return this._deserializationConfig;
    }

    public DeserializationContext getDeserializationContext() {
        return this._deserializationContext;
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    public JsonFactory getFactory() {
        return this._jsonFactory;
    }

    public InjectableValues getInjectableValues() {
        return this._injectableValues;
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    @Deprecated
    public JsonFactory getJsonFactory() {
        return getFactory();
    }

    public JsonNodeFactory getNodeFactory() {
        return this._deserializationConfig.getNodeFactory();
    }

    public PolymorphicTypeValidator getPolymorphicTypeValidator() {
        return this._deserializationConfig.getBaseSettings().getPolymorphicTypeValidator();
    }

    public PropertyNamingStrategy getPropertyNamingStrategy() {
        return this._serializationConfig.getPropertyNamingStrategy();
    }

    public Set<Object> getRegisteredModuleIds() {
        Set<Object> set = this._registeredModuleTypes;
        return set == null ? Collections.emptySet() : Collections.unmodifiableSet(set);
    }

    public SerializationConfig getSerializationConfig() {
        return this._serializationConfig;
    }

    public SerializerFactory getSerializerFactory() {
        return this._serializerFactory;
    }

    public SerializerProvider getSerializerProvider() {
        return this._serializerProvider;
    }

    public SerializerProvider getSerializerProviderInstance() {
        return _serializerProvider(this._serializationConfig);
    }

    public SubtypeResolver getSubtypeResolver() {
        return this._subtypeResolver;
    }

    public TypeFactory getTypeFactory() {
        return this._typeFactory;
    }

    public VisibilityChecker<?> getVisibilityChecker() {
        return this._serializationConfig.getDefaultVisibilityChecker();
    }

    public boolean isEnabled(MapperFeature mapperFeature) {
        return this._serializationConfig.isEnabled(mapperFeature);
    }

    public int mixInCount() {
        return this._mixIns.localSize();
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec, com.fasterxml.jackson.core.TreeCodec
    public <T extends TreeNode> T readTree(JsonParser jsonParser) throws IOException, JsonProcessingException {
        _assertNotNull("p", jsonParser);
        DeserializationConfig deserializationConfig = getDeserializationConfig();
        if (jsonParser.currentToken() == null && jsonParser.nextToken() == null) {
            return null;
        }
        JsonNode jsonNode = (JsonNode) _readValue(deserializationConfig, jsonParser, constructType(JsonNode.class));
        return jsonNode == null ? getNodeFactory().mo7176nullNode() : jsonNode;
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    public <T> T readValue(JsonParser jsonParser, Class<T> cls) throws IOException, JsonParseException, JsonMappingException {
        _assertNotNull("p", jsonParser);
        return (T) _readValue(getDeserializationConfig(), jsonParser, this._typeFactory.constructType(cls));
    }

    public ObjectReader reader() {
        return _newReader(getDeserializationConfig()).with(this._injectableValues);
    }

    public ObjectReader readerFor(JavaType javaType) {
        return _newReader(getDeserializationConfig(), javaType, null, null, this._injectableValues);
    }

    public ObjectReader readerForArrayOf(Class<?> cls) {
        return _newReader(getDeserializationConfig(), this._typeFactory.constructArrayType(cls), null, null, this._injectableValues);
    }

    public ObjectReader readerForListOf(Class<?> cls) {
        return _newReader(getDeserializationConfig(), this._typeFactory.constructCollectionType(List.class, cls), null, null, this._injectableValues);
    }

    public ObjectReader readerForMapOf(Class<?> cls) {
        return _newReader(getDeserializationConfig(), this._typeFactory.constructMapType(Map.class, String.class, cls), null, null, this._injectableValues);
    }

    public ObjectReader readerForUpdating(Object obj) {
        return _newReader(getDeserializationConfig(), this._typeFactory.constructType(obj.getClass()), obj, null, this._injectableValues);
    }

    public ObjectReader readerWithView(Class<?> cls) {
        return _newReader(getDeserializationConfig().mo7050withView(cls));
    }

    public ObjectMapper registerModule(Module module) {
        Object typeId;
        _assertNotNull(HttpClientModule.ElementsRequestKey.MODULE, module);
        if (module.getModuleName() != null) {
            if (module.version() != null) {
                for (Module module2 : module.getDependencies()) {
                    registerModule(module2);
                }
                if (isEnabled(MapperFeature.IGNORE_DUPLICATE_MODULE_REGISTRATIONS) && (typeId = module.getTypeId()) != null) {
                    if (this._registeredModuleTypes == null) {
                        this._registeredModuleTypes = new LinkedHashSet();
                    }
                    if (!this._registeredModuleTypes.add(typeId)) {
                        return this;
                    }
                }
                module.setupModule(new Module.SetupContext() { // from class: com.fasterxml.jackson.databind.ObjectMapper.1
                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public void addAbstractTypeResolver(AbstractTypeResolver abstractTypeResolver) {
                        DeserializerFactory withAbstractTypeResolver = ObjectMapper.this._deserializationContext._factory.withAbstractTypeResolver(abstractTypeResolver);
                        ObjectMapper objectMapper = ObjectMapper.this;
                        objectMapper._deserializationContext = objectMapper._deserializationContext.with(withAbstractTypeResolver);
                    }

                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public void addBeanDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier) {
                        DeserializerFactory withDeserializerModifier = ObjectMapper.this._deserializationContext._factory.withDeserializerModifier(beanDeserializerModifier);
                        ObjectMapper objectMapper = ObjectMapper.this;
                        objectMapper._deserializationContext = objectMapper._deserializationContext.with(withDeserializerModifier);
                    }

                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public void addBeanSerializerModifier(BeanSerializerModifier beanSerializerModifier) {
                        ObjectMapper objectMapper = ObjectMapper.this;
                        objectMapper._serializerFactory = objectMapper._serializerFactory.withSerializerModifier(beanSerializerModifier);
                    }

                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public void addDeserializationProblemHandler(DeserializationProblemHandler deserializationProblemHandler) {
                        ObjectMapper.this.addHandler(deserializationProblemHandler);
                    }

                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public void addDeserializers(Deserializers deserializers) {
                        DeserializerFactory withAdditionalDeserializers = ObjectMapper.this._deserializationContext._factory.withAdditionalDeserializers(deserializers);
                        ObjectMapper objectMapper = ObjectMapper.this;
                        objectMapper._deserializationContext = objectMapper._deserializationContext.with(withAdditionalDeserializers);
                    }

                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public void addKeyDeserializers(KeyDeserializers keyDeserializers) {
                        DeserializerFactory withAdditionalKeyDeserializers = ObjectMapper.this._deserializationContext._factory.withAdditionalKeyDeserializers(keyDeserializers);
                        ObjectMapper objectMapper = ObjectMapper.this;
                        objectMapper._deserializationContext = objectMapper._deserializationContext.with(withAdditionalKeyDeserializers);
                    }

                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public void addKeySerializers(Serializers serializers) {
                        ObjectMapper objectMapper = ObjectMapper.this;
                        objectMapper._serializerFactory = objectMapper._serializerFactory.withAdditionalKeySerializers(serializers);
                    }

                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public void addSerializers(Serializers serializers) {
                        ObjectMapper objectMapper = ObjectMapper.this;
                        objectMapper._serializerFactory = objectMapper._serializerFactory.withAdditionalSerializers(serializers);
                    }

                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public void addTypeModifier(TypeModifier typeModifier) {
                        ObjectMapper.this.setTypeFactory(ObjectMapper.this._typeFactory.withModifier(typeModifier));
                    }

                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public void addValueInstantiators(ValueInstantiators valueInstantiators) {
                        DeserializerFactory withValueInstantiators = ObjectMapper.this._deserializationContext._factory.withValueInstantiators(valueInstantiators);
                        ObjectMapper objectMapper = ObjectMapper.this;
                        objectMapper._deserializationContext = objectMapper._deserializationContext.with(withValueInstantiators);
                    }

                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public void appendAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
                        ObjectMapper objectMapper = ObjectMapper.this;
                        objectMapper._deserializationConfig = objectMapper._deserializationConfig.withAppendedAnnotationIntrospector(annotationIntrospector);
                        ObjectMapper objectMapper2 = ObjectMapper.this;
                        objectMapper2._serializationConfig = objectMapper2._serializationConfig.withAppendedAnnotationIntrospector(annotationIntrospector);
                    }

                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public MutableConfigOverride configOverride(Class<?> cls) {
                        return ObjectMapper.this.configOverride(cls);
                    }

                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public Version getMapperVersion() {
                        return ObjectMapper.this.version();
                    }

                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public <C extends ObjectCodec> C getOwner() {
                        return ObjectMapper.this;
                    }

                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public TypeFactory getTypeFactory() {
                        return ObjectMapper.this._typeFactory;
                    }

                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public void insertAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
                        ObjectMapper objectMapper = ObjectMapper.this;
                        objectMapper._deserializationConfig = objectMapper._deserializationConfig.withInsertedAnnotationIntrospector(annotationIntrospector);
                        ObjectMapper objectMapper2 = ObjectMapper.this;
                        objectMapper2._serializationConfig = objectMapper2._serializationConfig.withInsertedAnnotationIntrospector(annotationIntrospector);
                    }

                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public boolean isEnabled(MapperFeature mapperFeature) {
                        return ObjectMapper.this.isEnabled(mapperFeature);
                    }

                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public void registerSubtypes(Class<?>... clsArr) {
                        ObjectMapper.this.registerSubtypes(clsArr);
                    }

                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public void setClassIntrospector(ClassIntrospector classIntrospector) {
                        ObjectMapper objectMapper = ObjectMapper.this;
                        objectMapper._deserializationConfig = objectMapper._deserializationConfig.with(classIntrospector);
                        ObjectMapper objectMapper2 = ObjectMapper.this;
                        objectMapper2._serializationConfig = objectMapper2._serializationConfig.with(classIntrospector);
                    }

                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public void setMixInAnnotations(Class<?> cls, Class<?> cls2) {
                        ObjectMapper.this.addMixIn(cls, cls2);
                    }

                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public void setNamingStrategy(PropertyNamingStrategy propertyNamingStrategy) {
                        ObjectMapper.this.setPropertyNamingStrategy(propertyNamingStrategy);
                    }

                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public boolean isEnabled(DeserializationFeature deserializationFeature) {
                        return ObjectMapper.this.isEnabled(deserializationFeature);
                    }

                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public void registerSubtypes(NamedType... namedTypeArr) {
                        ObjectMapper.this.registerSubtypes(namedTypeArr);
                    }

                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public boolean isEnabled(SerializationFeature serializationFeature) {
                        return ObjectMapper.this.isEnabled(serializationFeature);
                    }

                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public void registerSubtypes(Collection<Class<?>> collection) {
                        ObjectMapper.this.registerSubtypes(collection);
                    }

                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public boolean isEnabled(JsonFactory.Feature feature) {
                        return ObjectMapper.this.isEnabled(feature);
                    }

                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public boolean isEnabled(JsonParser.Feature feature) {
                        return ObjectMapper.this.isEnabled(feature);
                    }

                    @Override // com.fasterxml.jackson.databind.Module.SetupContext
                    public boolean isEnabled(JsonGenerator.Feature feature) {
                        return ObjectMapper.this.isEnabled(feature);
                    }
                });
                return this;
            }
            throw new IllegalArgumentException("Module without defined version");
        }
        throw new IllegalArgumentException("Module without defined name");
    }

    public ObjectMapper registerModules(Module... moduleArr) {
        for (Module module : moduleArr) {
            registerModule(module);
        }
        return this;
    }

    public void registerSubtypes(Class<?>... clsArr) {
        getSubtypeResolver().registerSubtypes(clsArr);
    }

    public ObjectMapper setAccessorNaming(AccessorNamingStrategy.Provider provider) {
        this._serializationConfig = this._serializationConfig.with(provider);
        this._deserializationConfig = this._deserializationConfig.with(provider);
        return this;
    }

    public ObjectMapper setAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        this._serializationConfig = this._serializationConfig.with(annotationIntrospector);
        this._deserializationConfig = this._deserializationConfig.with(annotationIntrospector);
        return this;
    }

    public ObjectMapper setAnnotationIntrospectors(AnnotationIntrospector annotationIntrospector, AnnotationIntrospector annotationIntrospector2) {
        this._serializationConfig = this._serializationConfig.with(annotationIntrospector);
        this._deserializationConfig = this._deserializationConfig.with(annotationIntrospector2);
        return this;
    }

    public ObjectMapper setBase64Variant(Base64Variant base64Variant) {
        this._serializationConfig = this._serializationConfig.with(base64Variant);
        this._deserializationConfig = this._deserializationConfig.with(base64Variant);
        return this;
    }

    public ObjectMapper setConfig(DeserializationConfig deserializationConfig) {
        _assertNotNull("config", deserializationConfig);
        this._deserializationConfig = deserializationConfig;
        return this;
    }

    public ObjectMapper setConstructorDetector(ConstructorDetector constructorDetector) {
        this._deserializationConfig = this._deserializationConfig.with(constructorDetector);
        return this;
    }

    public ObjectMapper setDateFormat(DateFormat dateFormat) {
        this._deserializationConfig = this._deserializationConfig.mo7048with(dateFormat);
        this._serializationConfig = this._serializationConfig.mo7048with(dateFormat);
        return this;
    }

    public ObjectMapper setDefaultLeniency(Boolean bool) {
        this._configOverrides.setDefaultLeniency(bool);
        return this;
    }

    public ObjectMapper setDefaultMergeable(Boolean bool) {
        this._configOverrides.setDefaultMergeable(bool);
        return this;
    }

    public ObjectMapper setDefaultPrettyPrinter(PrettyPrinter prettyPrinter) {
        this._serializationConfig = this._serializationConfig.withDefaultPrettyPrinter(prettyPrinter);
        return this;
    }

    public ObjectMapper setDefaultPropertyInclusion(JsonInclude.Value value) {
        this._configOverrides.setDefaultInclusion(value);
        return this;
    }

    public ObjectMapper setDefaultSetterInfo(JsonSetter.Value value) {
        this._configOverrides.setDefaultSetterInfo(value);
        return this;
    }

    public ObjectMapper setDefaultTyping(TypeResolverBuilder<?> typeResolverBuilder) {
        this._deserializationConfig = this._deserializationConfig.with(typeResolverBuilder);
        this._serializationConfig = this._serializationConfig.with(typeResolverBuilder);
        return this;
    }

    public ObjectMapper setDefaultVisibility(JsonAutoDetect.Value value) {
        this._configOverrides.setDefaultVisibility(VisibilityChecker.Std.construct(value));
        return this;
    }

    public ObjectMapper setFilterProvider(FilterProvider filterProvider) {
        this._serializationConfig = this._serializationConfig.withFilters(filterProvider);
        return this;
    }

    @Deprecated
    public void setFilters(FilterProvider filterProvider) {
        this._serializationConfig = this._serializationConfig.withFilters(filterProvider);
    }

    public Object setHandlerInstantiator(HandlerInstantiator handlerInstantiator) {
        this._deserializationConfig = this._deserializationConfig.with(handlerInstantiator);
        this._serializationConfig = this._serializationConfig.with(handlerInstantiator);
        return this;
    }

    public ObjectMapper setInjectableValues(InjectableValues injectableValues) {
        this._injectableValues = injectableValues;
        return this;
    }

    public ObjectMapper setLocale(Locale locale) {
        this._deserializationConfig = this._deserializationConfig.with(locale);
        this._serializationConfig = this._serializationConfig.with(locale);
        return this;
    }

    @Deprecated
    public void setMixInAnnotations(Map<Class<?>, Class<?>> map) {
        setMixIns(map);
    }

    public ObjectMapper setMixInResolver(ClassIntrospector.MixInResolver mixInResolver) {
        SimpleMixInResolver withOverrides = this._mixIns.withOverrides(mixInResolver);
        if (withOverrides != this._mixIns) {
            this._mixIns = withOverrides;
            this._deserializationConfig = new DeserializationConfig(this._deserializationConfig, withOverrides);
            this._serializationConfig = new SerializationConfig(this._serializationConfig, withOverrides);
        }
        return this;
    }

    public ObjectMapper setMixIns(Map<Class<?>, Class<?>> map) {
        this._mixIns.setLocalDefinitions(map);
        return this;
    }

    public ObjectMapper setNodeFactory(JsonNodeFactory jsonNodeFactory) {
        this._deserializationConfig = this._deserializationConfig.with(jsonNodeFactory);
        return this;
    }

    public ObjectMapper setPolymorphicTypeValidator(PolymorphicTypeValidator polymorphicTypeValidator) {
        this._deserializationConfig = this._deserializationConfig.mo7044_withBase(this._deserializationConfig.getBaseSettings().with(polymorphicTypeValidator));
        return this;
    }

    @Deprecated
    public ObjectMapper setPropertyInclusion(JsonInclude.Value value) {
        return setDefaultPropertyInclusion(value);
    }

    public ObjectMapper setPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy) {
        this._serializationConfig = this._serializationConfig.with(propertyNamingStrategy);
        this._deserializationConfig = this._deserializationConfig.with(propertyNamingStrategy);
        return this;
    }

    public ObjectMapper setSerializationInclusion(JsonInclude.Include include) {
        setPropertyInclusion(JsonInclude.Value.construct(include, include));
        return this;
    }

    public ObjectMapper setSerializerFactory(SerializerFactory serializerFactory) {
        this._serializerFactory = serializerFactory;
        return this;
    }

    public ObjectMapper setSerializerProvider(DefaultSerializerProvider defaultSerializerProvider) {
        this._serializerProvider = defaultSerializerProvider;
        return this;
    }

    public ObjectMapper setSubtypeResolver(SubtypeResolver subtypeResolver) {
        this._subtypeResolver = subtypeResolver;
        this._deserializationConfig = this._deserializationConfig.mo7047with(subtypeResolver);
        this._serializationConfig = this._serializationConfig.mo7047with(subtypeResolver);
        return this;
    }

    public ObjectMapper setTimeZone(TimeZone timeZone) {
        this._deserializationConfig = this._deserializationConfig.with(timeZone);
        this._serializationConfig = this._serializationConfig.with(timeZone);
        return this;
    }

    public ObjectMapper setTypeFactory(TypeFactory typeFactory) {
        this._typeFactory = typeFactory;
        this._deserializationConfig = this._deserializationConfig.with(typeFactory);
        this._serializationConfig = this._serializationConfig.with(typeFactory);
        return this;
    }

    public ObjectMapper setVisibility(VisibilityChecker<?> visibilityChecker) {
        this._configOverrides.setDefaultVisibility(visibilityChecker);
        return this;
    }

    @Deprecated
    public void setVisibilityChecker(VisibilityChecker<?> visibilityChecker) {
        setVisibility(visibilityChecker);
    }

    public JsonFactory tokenStreamFactory() {
        return this._jsonFactory;
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec, com.fasterxml.jackson.core.TreeCodec
    public JsonParser treeAsTokens(TreeNode treeNode) {
        _assertNotNull(JsonReportFormat.COUNT, treeNode);
        return new TreeTraversingParser((JsonNode) treeNode, this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.fasterxml.jackson.core.ObjectCodec
    public <T> T treeToValue(TreeNode treeNode, Class<T> cls) throws IllegalArgumentException, JsonProcessingException {
        T t;
        if (treeNode == 0) {
            return null;
        }
        try {
            return (!TreeNode.class.isAssignableFrom(cls) || !cls.isAssignableFrom(treeNode.getClass())) ? (treeNode.asToken() != JsonToken.VALUE_EMBEDDED_OBJECT || !(treeNode instanceof POJONode) || ((t = (T) ((POJONode) treeNode).getPojo()) != null && !cls.isInstance(t))) ? (T) readValue(treeAsTokens(treeNode), cls) : t : treeNode;
        } catch (JsonProcessingException e) {
            throw e;
        } catch (IOException e2) {
            throw new IllegalArgumentException(e2.getMessage(), e2);
        }
    }

    public <T> T updateValue(T t, Object obj) throws JsonMappingException {
        if (t == null || obj == null) {
            return t;
        }
        TokenBuffer tokenBuffer = new TokenBuffer((ObjectCodec) this, false);
        if (isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
            tokenBuffer = tokenBuffer.forceUseOfBigDecimal(true);
        }
        try {
            _serializerProvider(getSerializationConfig().without(SerializationFeature.WRAP_ROOT_VALUE)).serializeValue(tokenBuffer, obj);
            JsonParser asParser = tokenBuffer.asParser();
            T t2 = (T) readerForUpdating(t).readValue(asParser);
            asParser.close();
            return t2;
        } catch (IOException e) {
            if (e instanceof JsonMappingException) {
                throw ((JsonMappingException) e);
            }
            throw JsonMappingException.fromUnexpectedIOE(e);
        }
    }

    public <T extends JsonNode> T valueToTree(Object obj) throws IllegalArgumentException {
        if (obj == null) {
            return getNodeFactory().mo7176nullNode();
        }
        TokenBuffer tokenBuffer = new TokenBuffer((ObjectCodec) this, false);
        if (isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
            tokenBuffer = tokenBuffer.forceUseOfBigDecimal(true);
        }
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

    @Override // com.fasterxml.jackson.core.ObjectCodec, com.fasterxml.jackson.core.Versioned
    public Version version() {
        return PackageVersion.VERSION;
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec, com.fasterxml.jackson.core.TreeCodec
    public void writeTree(JsonGenerator jsonGenerator, TreeNode treeNode) throws IOException, JsonProcessingException {
        _assertNotNull("g", jsonGenerator);
        SerializationConfig serializationConfig = getSerializationConfig();
        _serializerProvider(serializationConfig).serializeValue(jsonGenerator, treeNode);
        if (serializationConfig.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
            jsonGenerator.flush();
        }
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    public void writeValue(JsonGenerator jsonGenerator, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        _assertNotNull("g", jsonGenerator);
        SerializationConfig serializationConfig = getSerializationConfig();
        if (serializationConfig.isEnabled(SerializationFeature.INDENT_OUTPUT) && jsonGenerator.getPrettyPrinter() == null) {
            jsonGenerator.setPrettyPrinter(serializationConfig.constructDefaultPrettyPrinter());
        }
        if (serializationConfig.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) && (obj instanceof Closeable)) {
            _writeCloseableValue(jsonGenerator, obj, serializationConfig);
            return;
        }
        _serializerProvider(serializationConfig).serializeValue(jsonGenerator, obj);
        if (!serializationConfig.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
            return;
        }
        jsonGenerator.flush();
    }

    public byte[] writeValueAsBytes(Object obj) throws JsonProcessingException {
        ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder(this._jsonFactory._getBufferRecycler());
        try {
            _writeValueAndClose(createGenerator(byteArrayBuilder, JsonEncoding.UTF8), obj);
            byte[] byteArray = byteArrayBuilder.toByteArray();
            byteArrayBuilder.release();
            return byteArray;
        } catch (JsonProcessingException e) {
            throw e;
        } catch (IOException e2) {
            throw JsonMappingException.fromUnexpectedIOE(e2);
        }
    }

    public String writeValueAsString(Object obj) throws JsonProcessingException {
        SegmentedStringWriter segmentedStringWriter = new SegmentedStringWriter(this._jsonFactory._getBufferRecycler());
        try {
            _writeValueAndClose(createGenerator(segmentedStringWriter), obj);
            return segmentedStringWriter.getAndClear();
        } catch (JsonProcessingException e) {
            throw e;
        } catch (IOException e2) {
            throw JsonMappingException.fromUnexpectedIOE(e2);
        }
    }

    public ObjectWriter writer() {
        return _newWriter(getSerializationConfig());
    }

    public ObjectWriter writerFor(Class<?> cls) {
        return _newWriter(getSerializationConfig(), cls == null ? null : this._typeFactory.constructType(cls), null);
    }

    public ObjectWriter writerWithDefaultPrettyPrinter() {
        SerializationConfig serializationConfig = getSerializationConfig();
        return _newWriter(serializationConfig, null, serializationConfig.getDefaultPrettyPrinter());
    }

    @Deprecated
    public ObjectWriter writerWithType(Class<?> cls) {
        return _newWriter(getSerializationConfig(), cls == null ? null : this._typeFactory.constructType(cls), null);
    }

    public ObjectWriter writerWithView(Class<?> cls) {
        return _newWriter(getSerializationConfig().mo7050withView(cls));
    }

    public ObjectMapper(JsonFactory jsonFactory) {
        this(jsonFactory, null, null);
    }

    public static List<Module> findModules(ClassLoader classLoader) {
        ArrayList arrayList = new ArrayList();
        Iterator it2 = secureGetServiceLoader(Module.class, classLoader).iterator();
        while (it2.hasNext()) {
            arrayList.add((Module) it2.next());
        }
        return arrayList;
    }

    protected ObjectReader _newReader(DeserializationConfig deserializationConfig, JavaType javaType, Object obj, FormatSchema formatSchema, InjectableValues injectableValues) {
        return new ObjectReader(this, deserializationConfig, javaType, obj, formatSchema, injectableValues);
    }

    protected ObjectWriter _newWriter(SerializationConfig serializationConfig, FormatSchema formatSchema) {
        return new ObjectWriter(this, serializationConfig, formatSchema);
    }

    public void acceptJsonFormatVisitor(JavaType javaType, JsonFormatVisitorWrapper jsonFormatVisitorWrapper) throws JsonMappingException {
        if (javaType != null) {
            _serializerProvider(getSerializationConfig()).acceptJsonFormatVisitor(javaType, jsonFormatVisitorWrapper);
            return;
        }
        throw new IllegalArgumentException("type must be provided");
    }

    public ObjectMapper activateDefaultTyping(PolymorphicTypeValidator polymorphicTypeValidator, DefaultTyping defaultTyping) {
        return activateDefaultTyping(polymorphicTypeValidator, defaultTyping, JsonTypeInfo.As.WRAPPER_ARRAY);
    }

    public boolean canSerialize(Class<?> cls, AtomicReference<Throwable> atomicReference) {
        return _serializerProvider(getSerializationConfig()).hasSerializerFor(cls, atomicReference);
    }

    public MutableCoercionConfig coercionConfigFor(Class<?> cls) {
        return this._coercionConfigs.findOrCreateCoercion(cls);
    }

    public <T> T convertValue(Object obj, TypeReference<T> typeReference) throws IllegalArgumentException {
        return (T) _convert(obj, this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec, com.fasterxml.jackson.core.TreeCodec
    /* renamed from: createArrayNode  reason: collision with other method in class */
    public ArrayNode mo7040createArrayNode() {
        return this._deserializationConfig.getNodeFactory().arrayNode();
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec, com.fasterxml.jackson.core.TreeCodec
    /* renamed from: createObjectNode  reason: collision with other method in class */
    public ObjectNode mo7041createObjectNode() {
        return this._deserializationConfig.getNodeFactory().objectNode();
    }

    @Deprecated
    public ObjectMapper enableDefaultTyping(DefaultTyping defaultTyping) {
        return enableDefaultTyping(defaultTyping, JsonTypeInfo.As.WRAPPER_ARRAY);
    }

    public boolean isEnabled(SerializationFeature serializationFeature) {
        return this._serializationConfig.isEnabled(serializationFeature);
    }

    @Override // com.fasterxml.jackson.core.TreeCodec
    /* renamed from: missingNode  reason: collision with other method in class */
    public JsonNode mo7042missingNode() {
        return this._deserializationConfig.getNodeFactory().missingNode();
    }

    @Override // com.fasterxml.jackson.core.TreeCodec
    /* renamed from: nullNode  reason: collision with other method in class */
    public JsonNode mo7043nullNode() {
        return this._deserializationConfig.getNodeFactory().mo7176nullNode();
    }

    public ObjectReader reader(DeserializationFeature deserializationFeature) {
        return _newReader(getDeserializationConfig().with(deserializationFeature));
    }

    public ObjectReader readerFor(Class<?> cls) {
        return _newReader(getDeserializationConfig(), this._typeFactory.constructType(cls), null, null, this._injectableValues);
    }

    public void registerSubtypes(NamedType... namedTypeArr) {
        getSubtypeResolver().registerSubtypes(namedTypeArr);
    }

    public ObjectMapper setDefaultPropertyInclusion(JsonInclude.Include include) {
        this._configOverrides.setDefaultInclusion(JsonInclude.Value.construct(include, include));
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [com.fasterxml.jackson.databind.introspect.VisibilityChecker] */
    public ObjectMapper setVisibility(PropertyAccessor propertyAccessor, JsonAutoDetect.Visibility visibility) {
        this._configOverrides.setDefaultVisibility(this._configOverrides.getDefaultVisibility().mo7145withVisibility(propertyAccessor, visibility));
        return this;
    }

    public ObjectWriter writer(SerializationFeature serializationFeature) {
        return _newWriter(getSerializationConfig().with(serializationFeature));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ObjectMapper(ObjectMapper objectMapper) {
        this._rootDeserializers = new ConcurrentHashMap<>(64, 0.6f, 2);
        this._jsonFactory = objectMapper._jsonFactory.copy();
        this._jsonFactory.setCodec(this);
        this._subtypeResolver = objectMapper._subtypeResolver.copy();
        this._typeFactory = objectMapper._typeFactory;
        this._injectableValues = objectMapper._injectableValues;
        this._configOverrides = objectMapper._configOverrides.copy();
        this._coercionConfigs = objectMapper._coercionConfigs.copy();
        this._mixIns = objectMapper._mixIns.mo7135copy();
        RootNameLookup rootNameLookup = new RootNameLookup();
        this._serializationConfig = new SerializationConfig(objectMapper._serializationConfig, this._subtypeResolver, this._mixIns, rootNameLookup, this._configOverrides);
        this._deserializationConfig = new DeserializationConfig(objectMapper._deserializationConfig, this._subtypeResolver, this._mixIns, rootNameLookup, this._configOverrides, this._coercionConfigs);
        this._serializerProvider = objectMapper._serializerProvider.copy();
        this._deserializationContext = objectMapper._deserializationContext.copy();
        this._serializerFactory = objectMapper._serializerFactory;
        Set<Object> set = objectMapper._registeredModuleTypes;
        if (set == null) {
            this._registeredModuleTypes = null;
        } else {
            this._registeredModuleTypes = new LinkedHashSet(set);
        }
    }

    protected ObjectWriter _newWriter(SerializationConfig serializationConfig, JavaType javaType, PrettyPrinter prettyPrinter) {
        return new ObjectWriter(this, serializationConfig, javaType, prettyPrinter);
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder] */
    public ObjectMapper activateDefaultTyping(PolymorphicTypeValidator polymorphicTypeValidator, DefaultTyping defaultTyping, JsonTypeInfo.As as) {
        if (as != JsonTypeInfo.As.EXTERNAL_PROPERTY) {
            return setDefaultTyping(_constructDefaultTypeResolverBuilder(defaultTyping, polymorphicTypeValidator).init(JsonTypeInfo.Id.CLASS, null).inclusion(as));
        }
        throw new IllegalArgumentException("Cannot use includeAs of " + as);
    }

    public JavaType constructType(TypeReference<?> typeReference) {
        _assertNotNull("typeRef", typeReference);
        return this._typeFactory.constructType(typeReference);
    }

    public <T> T convertValue(Object obj, JavaType javaType) throws IllegalArgumentException {
        return (T) _convert(obj, javaType);
    }

    public JsonParser createParser(URL url) throws IOException {
        _assertNotNull("src", url);
        return this._deserializationConfig.initialize(this._jsonFactory.createParser(url));
    }

    public ObjectMapper disable(SerializationFeature serializationFeature) {
        this._serializationConfig = this._serializationConfig.without(serializationFeature);
        return this;
    }

    public ObjectMapper enable(SerializationFeature serializationFeature) {
        this._serializationConfig = this._serializationConfig.with(serializationFeature);
        return this;
    }

    @Deprecated
    public ObjectMapper enableDefaultTyping(DefaultTyping defaultTyping, JsonTypeInfo.As as) {
        return activateDefaultTyping(getPolymorphicTypeValidator(), defaultTyping, as);
    }

    public boolean isEnabled(DeserializationFeature deserializationFeature) {
        return this._deserializationConfig.isEnabled(deserializationFeature);
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    public <T> T readValue(JsonParser jsonParser, TypeReference<T> typeReference) throws IOException, JsonParseException, JsonMappingException {
        _assertNotNull("p", jsonParser);
        return (T) _readValue(getDeserializationConfig(), jsonParser, this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    public ObjectReader reader(DeserializationFeature deserializationFeature, DeserializationFeature... deserializationFeatureArr) {
        return _newReader(getDeserializationConfig().with(deserializationFeature, deserializationFeatureArr));
    }

    public ObjectReader readerFor(TypeReference<?> typeReference) {
        return _newReader(getDeserializationConfig(), this._typeFactory.constructType(typeReference), null, null, this._injectableValues);
    }

    public ObjectMapper registerModules(Iterable<? extends Module> iterable) {
        _assertNotNull("modules", iterable);
        for (Module module : iterable) {
            registerModule(module);
        }
        return this;
    }

    public void registerSubtypes(Collection<Class<?>> collection) {
        getSubtypeResolver().registerSubtypes(collection);
    }

    public ObjectMapper setConfig(SerializationConfig serializationConfig) {
        _assertNotNull("config", serializationConfig);
        this._serializationConfig = serializationConfig;
        return this;
    }

    public ObjectWriter writer(SerializationFeature serializationFeature, SerializationFeature... serializationFeatureArr) {
        return _newWriter(getSerializationConfig().with(serializationFeature, serializationFeatureArr));
    }

    public boolean canDeserialize(JavaType javaType, AtomicReference<Throwable> atomicReference) {
        return createDeserializationContext(null, getDeserializationConfig()).hasValueDeserializerFor(javaType, atomicReference);
    }

    public JsonGenerator createGenerator(OutputStream outputStream, JsonEncoding jsonEncoding) throws IOException {
        _assertNotNull("out", outputStream);
        JsonGenerator createGenerator = this._jsonFactory.createGenerator(outputStream, jsonEncoding);
        this._serializationConfig.initialize(createGenerator);
        return createGenerator;
    }

    public ObjectMapper disable(SerializationFeature serializationFeature, SerializationFeature... serializationFeatureArr) {
        this._serializationConfig = this._serializationConfig.without(serializationFeature, serializationFeatureArr);
        return this;
    }

    public ObjectMapper enable(SerializationFeature serializationFeature, SerializationFeature... serializationFeatureArr) {
        this._serializationConfig = this._serializationConfig.with(serializationFeature, serializationFeatureArr);
        return this;
    }

    public boolean isEnabled(JsonParser.Feature feature) {
        return this._deserializationConfig.isEnabled(feature, this._jsonFactory);
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    /* renamed from: readValues */
    public <T> MappingIterator<T> mo7036readValues(JsonParser jsonParser, ResolvedType resolvedType) throws IOException, JsonProcessingException {
        return readValues(jsonParser, (JavaType) resolvedType);
    }

    public ObjectReader reader(JsonNodeFactory jsonNodeFactory) {
        return _newReader(getDeserializationConfig()).with(jsonNodeFactory);
    }

    public ObjectWriter writer(DateFormat dateFormat) {
        return _newWriter(getSerializationConfig().mo7048with(dateFormat));
    }

    public ObjectWriter writerFor(TypeReference<?> typeReference) {
        return _newWriter(getSerializationConfig(), typeReference == null ? null : this._typeFactory.constructType(typeReference), null);
    }

    @Deprecated
    public ObjectWriter writerWithType(TypeReference<?> typeReference) {
        return _newWriter(getSerializationConfig(), typeReference == null ? null : this._typeFactory.constructType(typeReference), null);
    }

    @Deprecated
    protected JsonToken _initForReading(JsonParser jsonParser) throws IOException {
        return _initForReading(jsonParser, null);
    }

    public ObjectMapper configure(SerializationFeature serializationFeature, boolean z) {
        this._serializationConfig = z ? this._serializationConfig.with(serializationFeature) : this._serializationConfig.without(serializationFeature);
        return this;
    }

    public JsonParser createParser(InputStream inputStream) throws IOException {
        _assertNotNull("in", inputStream);
        return this._deserializationConfig.initialize(this._jsonFactory.createParser(inputStream));
    }

    public ObjectMapper disable(DeserializationFeature deserializationFeature) {
        this._deserializationConfig = this._deserializationConfig.without(deserializationFeature);
        return this;
    }

    public ObjectMapper enable(DeserializationFeature deserializationFeature) {
        this._deserializationConfig = this._deserializationConfig.with(deserializationFeature);
        return this;
    }

    public boolean isEnabled(JsonGenerator.Feature feature) {
        return this._serializationConfig.isEnabled(feature, this._jsonFactory);
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    public final <T> T readValue(JsonParser jsonParser, ResolvedType resolvedType) throws IOException, JsonParseException, JsonMappingException {
        _assertNotNull("p", jsonParser);
        return (T) _readValue(getDeserializationConfig(), jsonParser, (JavaType) resolvedType);
    }

    public <T> MappingIterator<T> readValues(JsonParser jsonParser, JavaType javaType) throws IOException, JsonProcessingException {
        _assertNotNull("p", jsonParser);
        DefaultDeserializationContext createDeserializationContext = createDeserializationContext(jsonParser, getDeserializationConfig());
        return new MappingIterator<>(javaType, jsonParser, createDeserializationContext, _findRootDeserializer(createDeserializationContext, javaType), false, null);
    }

    public ObjectReader reader(FormatSchema formatSchema) {
        _verifySchemaType(formatSchema);
        return _newReader(getDeserializationConfig(), null, null, formatSchema, this._injectableValues);
    }

    public ObjectWriter writer(PrettyPrinter prettyPrinter) {
        if (prettyPrinter == null) {
            prettyPrinter = ObjectWriter.NULL_PRETTY_PRINTER;
        }
        return _newWriter(getSerializationConfig(), null, prettyPrinter);
    }

    public ObjectMapper disable(DeserializationFeature deserializationFeature, DeserializationFeature... deserializationFeatureArr) {
        this._deserializationConfig = this._deserializationConfig.without(deserializationFeature, deserializationFeatureArr);
        return this;
    }

    public ObjectMapper enable(DeserializationFeature deserializationFeature, DeserializationFeature... deserializationFeatureArr) {
        this._deserializationConfig = this._deserializationConfig.with(deserializationFeature, deserializationFeatureArr);
        return this;
    }

    public boolean isEnabled(JsonFactory.Feature feature) {
        return this._jsonFactory.isEnabled(feature);
    }

    public void writeTree(JsonGenerator jsonGenerator, JsonNode jsonNode) throws IOException, JsonProcessingException {
        _assertNotNull("g", jsonGenerator);
        SerializationConfig serializationConfig = getSerializationConfig();
        _serializerProvider(serializationConfig).serializeValue(jsonGenerator, jsonNode);
        if (serializationConfig.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
            jsonGenerator.flush();
        }
    }

    public ObjectMapper configure(DeserializationFeature deserializationFeature, boolean z) {
        this._deserializationConfig = z ? this._deserializationConfig.with(deserializationFeature) : this._deserializationConfig.without(deserializationFeature);
        return this;
    }

    public JsonGenerator createGenerator(Writer writer) throws IOException {
        _assertNotNull("w", writer);
        JsonGenerator createGenerator = this._jsonFactory.createGenerator(writer);
        this._serializationConfig.initialize(createGenerator);
        return createGenerator;
    }

    public JsonParser createParser(Reader reader) throws IOException {
        _assertNotNull("r", reader);
        return this._deserializationConfig.initialize(this._jsonFactory.createParser(reader));
    }

    public ObjectMapper disable(JsonParser.Feature... featureArr) {
        for (JsonParser.Feature feature : featureArr) {
            this._jsonFactory.disable(feature);
        }
        return this;
    }

    public ObjectMapper enable(JsonParser.Feature... featureArr) {
        for (JsonParser.Feature feature : featureArr) {
            this._jsonFactory.enable(feature);
        }
        return this;
    }

    public boolean isEnabled(StreamReadFeature streamReadFeature) {
        return isEnabled(streamReadFeature.mappedFeature());
    }

    public JsonNode readTree(InputStream inputStream) throws IOException {
        _assertNotNull("in", inputStream);
        return _readTreeAndClose(this._jsonFactory.createParser(inputStream));
    }

    public <T> T readValue(JsonParser jsonParser, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        _assertNotNull("p", jsonParser);
        return (T) _readValue(getDeserializationConfig(), jsonParser, javaType);
    }

    public ObjectReader reader(InjectableValues injectableValues) {
        return _newReader(getDeserializationConfig(), null, null, null, injectableValues);
    }

    public ObjectWriter writer(FilterProvider filterProvider) {
        return _newWriter(getSerializationConfig().withFilters(filterProvider));
    }

    public ObjectWriter writerFor(JavaType javaType) {
        return _newWriter(getSerializationConfig(), javaType, null);
    }

    @Deprecated
    public ObjectWriter writerWithType(JavaType javaType) {
        return _newWriter(getSerializationConfig(), javaType, null);
    }

    public boolean isEnabled(StreamWriteFeature streamWriteFeature) {
        return isEnabled(streamWriteFeature.mappedFeature());
    }

    public ObjectReader reader(Base64Variant base64Variant) {
        return _newReader(getDeserializationConfig().with(base64Variant));
    }

    public ObjectWriter writer(FormatSchema formatSchema) {
        _verifySchemaType(formatSchema);
        return _newWriter(getSerializationConfig(), formatSchema);
    }

    public ObjectMapper configure(JsonParser.Feature feature, boolean z) {
        this._jsonFactory.configure(feature, z);
        return this;
    }

    public JsonParser createParser(byte[] bArr) throws IOException {
        _assertNotNull("content", bArr);
        return this._deserializationConfig.initialize(this._jsonFactory.createParser(bArr));
    }

    public ObjectMapper disable(JsonGenerator.Feature... featureArr) {
        for (JsonGenerator.Feature feature : featureArr) {
            this._jsonFactory.disable(feature);
        }
        return this;
    }

    public ObjectMapper enable(JsonGenerator.Feature... featureArr) {
        for (JsonGenerator.Feature feature : featureArr) {
            this._jsonFactory.enable(feature);
        }
        return this;
    }

    public JsonNode readTree(Reader reader) throws IOException {
        _assertNotNull("r", reader);
        return _readTreeAndClose(this._jsonFactory.createParser(reader));
    }

    public <T> T readValue(File file, Class<T> cls) throws IOException, JsonParseException, JsonMappingException {
        _assertNotNull("src", file);
        return (T) _readMapAndClose(this._jsonFactory.createParser(file), this._typeFactory.constructType(cls));
    }

    public ObjectReader reader(ContextAttributes contextAttributes) {
        return _newReader(getDeserializationConfig().mo7046with(contextAttributes));
    }

    public ObjectMapper configure(JsonGenerator.Feature feature, boolean z) {
        this._jsonFactory.configure(feature, z);
        return this;
    }

    public JsonGenerator createGenerator(File file, JsonEncoding jsonEncoding) throws IOException {
        _assertNotNull("outputFile", file);
        JsonGenerator createGenerator = this._jsonFactory.createGenerator(file, jsonEncoding);
        this._serializationConfig.initialize(createGenerator);
        return createGenerator;
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    /* renamed from: readValues */
    public <T> MappingIterator<T> mo7038readValues(JsonParser jsonParser, Class<T> cls) throws IOException, JsonProcessingException {
        return readValues(jsonParser, this._typeFactory.constructType(cls));
    }

    @Deprecated
    public ObjectReader reader(JavaType javaType) {
        return _newReader(getDeserializationConfig(), javaType, null, null, this._injectableValues);
    }

    public ObjectWriter writer(Base64Variant base64Variant) {
        return _newWriter(getSerializationConfig().with(base64Variant));
    }

    public JsonParser createParser(byte[] bArr, int i, int i2) throws IOException {
        _assertNotNull("content", bArr);
        return this._deserializationConfig.initialize(this._jsonFactory.createParser(bArr, i, i2));
    }

    public JsonNode readTree(String str) throws JsonProcessingException, JsonMappingException {
        _assertNotNull("content", str);
        try {
            return _readTreeAndClose(this._jsonFactory.createParser(str));
        } catch (JsonProcessingException e) {
            throw e;
        } catch (IOException e2) {
            throw JsonMappingException.fromUnexpectedIOE(e2);
        }
    }

    public <T> T readValue(File file, TypeReference<T> typeReference) throws IOException, JsonParseException, JsonMappingException {
        _assertNotNull("src", file);
        return (T) _readMapAndClose(this._jsonFactory.createParser(file), this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    /* renamed from: readValues */
    public <T> MappingIterator<T> mo7037readValues(JsonParser jsonParser, TypeReference<T> typeReference) throws IOException, JsonProcessingException {
        return readValues(jsonParser, this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    @Deprecated
    public ObjectReader reader(Class<?> cls) {
        return _newReader(getDeserializationConfig(), this._typeFactory.constructType(cls), null, null, this._injectableValues);
    }

    public void writeValue(File file, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        _writeValueAndClose(createGenerator(file, JsonEncoding.UTF8), obj);
    }

    public ObjectWriter writer(CharacterEscapes characterEscapes) {
        return _newWriter(getSerializationConfig()).with(characterEscapes);
    }

    @Deprecated
    public ObjectReader reader(TypeReference<?> typeReference) {
        return _newReader(getDeserializationConfig(), this._typeFactory.constructType(typeReference), null, null, this._injectableValues);
    }

    public void writeValue(OutputStream outputStream, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        _writeValueAndClose(createGenerator(outputStream, JsonEncoding.UTF8), obj);
    }

    public ObjectWriter writer(ContextAttributes contextAttributes) {
        return _newWriter(getSerializationConfig().mo7046with(contextAttributes));
    }

    public JsonGenerator createGenerator(DataOutput dataOutput) throws IOException {
        _assertNotNull("out", dataOutput);
        JsonGenerator createGenerator = this._jsonFactory.createGenerator(dataOutput);
        this._serializationConfig.initialize(createGenerator);
        return createGenerator;
    }

    public JsonParser createParser(String str) throws IOException {
        _assertNotNull("content", str);
        return this._deserializationConfig.initialize(this._jsonFactory.createParser(str));
    }

    public <T> T readValue(File file, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        _assertNotNull("src", file);
        return (T) _readMapAndClose(this._jsonFactory.createParser(file), javaType);
    }

    public void writeValue(DataOutput dataOutput, Object obj) throws IOException {
        _writeValueAndClose(createGenerator(dataOutput), obj);
    }

    public void writeValue(Writer writer, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        _writeValueAndClose(createGenerator(writer), obj);
    }

    public JsonParser createParser(char[] cArr) throws IOException {
        _assertNotNull("content", cArr);
        return this._deserializationConfig.initialize(this._jsonFactory.createParser(cArr));
    }

    public JsonNode readTree(byte[] bArr) throws IOException {
        _assertNotNull("content", bArr);
        return _readTreeAndClose(this._jsonFactory.createParser(bArr));
    }

    public <T> T readValue(URL url, Class<T> cls) throws IOException, JsonParseException, JsonMappingException {
        _assertNotNull("src", url);
        return (T) _readMapAndClose(this._jsonFactory.createParser(url), this._typeFactory.constructType(cls));
    }

    public JsonParser createParser(char[] cArr, int i, int i2) throws IOException {
        _assertNotNull("content", cArr);
        return this._deserializationConfig.initialize(this._jsonFactory.createParser(cArr, i, i2));
    }

    public JsonNode readTree(byte[] bArr, int i, int i2) throws IOException {
        _assertNotNull("content", bArr);
        return _readTreeAndClose(this._jsonFactory.createParser(bArr, i, i2));
    }

    public <T> T readValue(URL url, TypeReference<T> typeReference) throws IOException, JsonParseException, JsonMappingException {
        _assertNotNull("src", url);
        return (T) _readMapAndClose(this._jsonFactory.createParser(url), this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    public JsonParser createParser(DataInput dataInput) throws IOException {
        _assertNotNull("content", dataInput);
        return this._deserializationConfig.initialize(this._jsonFactory.createParser(dataInput));
    }

    public JsonNode readTree(File file) throws IOException, JsonProcessingException {
        _assertNotNull("file", file);
        return _readTreeAndClose(this._jsonFactory.createParser(file));
    }

    public <T> T readValue(URL url, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        _assertNotNull("src", url);
        return (T) _readMapAndClose(this._jsonFactory.createParser(url), javaType);
    }

    public JsonNode readTree(URL url) throws IOException {
        _assertNotNull("source", url);
        return _readTreeAndClose(this._jsonFactory.createParser(url));
    }

    public <T> T readValue(String str, Class<T> cls) throws JsonProcessingException, JsonMappingException {
        _assertNotNull("content", str);
        return (T) readValue(str, this._typeFactory.constructType(cls));
    }

    public ObjectMapper(JsonFactory jsonFactory, DefaultSerializerProvider defaultSerializerProvider, DefaultDeserializationContext defaultDeserializationContext) {
        this._rootDeserializers = new ConcurrentHashMap<>(64, 0.6f, 2);
        if (jsonFactory == null) {
            this._jsonFactory = new MappingJsonFactory(this);
        } else {
            this._jsonFactory = jsonFactory;
            if (jsonFactory.mo7031getCodec() == null) {
                this._jsonFactory.setCodec(this);
            }
        }
        this._subtypeResolver = new StdSubtypeResolver();
        RootNameLookup rootNameLookup = new RootNameLookup();
        this._typeFactory = TypeFactory.defaultInstance();
        SimpleMixInResolver simpleMixInResolver = new SimpleMixInResolver(null);
        this._mixIns = simpleMixInResolver;
        BaseSettings withClassIntrospector = DEFAULT_BASE.withClassIntrospector(defaultClassIntrospector());
        this._configOverrides = new ConfigOverrides();
        this._coercionConfigs = new CoercionConfigs();
        this._serializationConfig = new SerializationConfig(withClassIntrospector, this._subtypeResolver, simpleMixInResolver, rootNameLookup, this._configOverrides);
        this._deserializationConfig = new DeserializationConfig(withClassIntrospector, this._subtypeResolver, simpleMixInResolver, rootNameLookup, this._configOverrides, this._coercionConfigs);
        boolean requiresPropertyOrdering = this._jsonFactory.requiresPropertyOrdering();
        if (this._serializationConfig.isEnabled(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY) ^ requiresPropertyOrdering) {
            configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, requiresPropertyOrdering);
        }
        this._serializerProvider = defaultSerializerProvider == null ? new DefaultSerializerProvider.Impl() : defaultSerializerProvider;
        this._deserializationContext = defaultDeserializationContext == null ? new DefaultDeserializationContext.Impl(BeanDeserializerFactory.instance) : defaultDeserializationContext;
        this._serializerFactory = BeanSerializerFactory.instance;
    }

    public <T> T readValue(String str, TypeReference<T> typeReference) throws JsonProcessingException, JsonMappingException {
        _assertNotNull("content", str);
        return (T) readValue(str, this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    public <T> T readValue(String str, JavaType javaType) throws JsonProcessingException, JsonMappingException {
        _assertNotNull("content", str);
        try {
            return (T) _readMapAndClose(this._jsonFactory.createParser(str), javaType);
        } catch (JsonProcessingException e) {
            throw e;
        } catch (IOException e2) {
            throw JsonMappingException.fromUnexpectedIOE(e2);
        }
    }

    public <T> T readValue(Reader reader, Class<T> cls) throws IOException, JsonParseException, JsonMappingException {
        _assertNotNull("src", reader);
        return (T) _readMapAndClose(this._jsonFactory.createParser(reader), this._typeFactory.constructType(cls));
    }

    public <T> T readValue(Reader reader, TypeReference<T> typeReference) throws IOException, JsonParseException, JsonMappingException {
        _assertNotNull("src", reader);
        return (T) _readMapAndClose(this._jsonFactory.createParser(reader), this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    public <T> T readValue(Reader reader, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        _assertNotNull("src", reader);
        return (T) _readMapAndClose(this._jsonFactory.createParser(reader), javaType);
    }

    public <T> T readValue(InputStream inputStream, Class<T> cls) throws IOException, JsonParseException, JsonMappingException {
        _assertNotNull("src", inputStream);
        return (T) _readMapAndClose(this._jsonFactory.createParser(inputStream), this._typeFactory.constructType(cls));
    }

    public <T> T readValue(InputStream inputStream, TypeReference<T> typeReference) throws IOException, JsonParseException, JsonMappingException {
        _assertNotNull("src", inputStream);
        return (T) _readMapAndClose(this._jsonFactory.createParser(inputStream), this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    public <T> T readValue(InputStream inputStream, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        _assertNotNull("src", inputStream);
        return (T) _readMapAndClose(this._jsonFactory.createParser(inputStream), javaType);
    }

    public <T> T readValue(byte[] bArr, Class<T> cls) throws IOException, JsonParseException, JsonMappingException {
        _assertNotNull("src", bArr);
        return (T) _readMapAndClose(this._jsonFactory.createParser(bArr), this._typeFactory.constructType(cls));
    }

    public <T> T readValue(byte[] bArr, int i, int i2, Class<T> cls) throws IOException, JsonParseException, JsonMappingException {
        _assertNotNull("src", bArr);
        return (T) _readMapAndClose(this._jsonFactory.createParser(bArr, i, i2), this._typeFactory.constructType(cls));
    }

    public <T> T readValue(byte[] bArr, TypeReference<T> typeReference) throws IOException, JsonParseException, JsonMappingException {
        _assertNotNull("src", bArr);
        return (T) _readMapAndClose(this._jsonFactory.createParser(bArr), this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    public <T> T readValue(byte[] bArr, int i, int i2, TypeReference<T> typeReference) throws IOException, JsonParseException, JsonMappingException {
        _assertNotNull("src", bArr);
        return (T) _readMapAndClose(this._jsonFactory.createParser(bArr, i, i2), this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    public <T> T readValue(byte[] bArr, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        _assertNotNull("src", bArr);
        return (T) _readMapAndClose(this._jsonFactory.createParser(bArr), javaType);
    }

    public <T> T readValue(byte[] bArr, int i, int i2, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        _assertNotNull("src", bArr);
        return (T) _readMapAndClose(this._jsonFactory.createParser(bArr, i, i2), javaType);
    }

    public <T> T readValue(DataInput dataInput, Class<T> cls) throws IOException {
        _assertNotNull("src", dataInput);
        return (T) _readMapAndClose(this._jsonFactory.createParser(dataInput), this._typeFactory.constructType(cls));
    }

    public <T> T readValue(DataInput dataInput, JavaType javaType) throws IOException {
        _assertNotNull("src", dataInput);
        return (T) _readMapAndClose(this._jsonFactory.createParser(dataInput), javaType);
    }
}
