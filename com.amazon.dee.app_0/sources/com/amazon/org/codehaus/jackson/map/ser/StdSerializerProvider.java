package com.amazon.org.codehaus.jackson.map.ser;

import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.ContextualSerializer;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.ResolvableSerializer;
import com.amazon.org.codehaus.jackson.map.SerializationConfig;
import com.amazon.org.codehaus.jackson.map.SerializerFactory;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import com.amazon.org.codehaus.jackson.map.ser.impl.FailingSerializer;
import com.amazon.org.codehaus.jackson.map.ser.impl.ReadOnlyClassToSerializerMap;
import com.amazon.org.codehaus.jackson.map.ser.impl.SerializerCache;
import com.amazon.org.codehaus.jackson.map.ser.impl.UnknownSerializer;
import com.amazon.org.codehaus.jackson.map.ser.std.NullSerializer;
import com.amazon.org.codehaus.jackson.map.ser.std.StdKeySerializers;
import com.amazon.org.codehaus.jackson.map.util.ClassUtil;
import com.amazon.org.codehaus.jackson.map.util.RootNameLookup;
import com.amazon.org.codehaus.jackson.node.ObjectNode;
import com.amazon.org.codehaus.jackson.schema.JsonSchema;
import com.amazon.org.codehaus.jackson.schema.SchemaAware;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
/* loaded from: classes13.dex */
public class StdSerializerProvider extends SerializerProvider {
    static final boolean CACHE_UNKNOWN_MAPPINGS = false;
    protected DateFormat _dateFormat;
    protected JsonSerializer<Object> _keySerializer;
    protected final ReadOnlyClassToSerializerMap _knownSerializers;
    protected JsonSerializer<Object> _nullKeySerializer;
    protected JsonSerializer<Object> _nullValueSerializer;
    protected final RootNameLookup _rootNames;
    protected final SerializerCache _serializerCache;
    protected final SerializerFactory _serializerFactory;
    protected JsonSerializer<Object> _unknownTypeSerializer;
    public static final JsonSerializer<Object> DEFAULT_NULL_KEY_SERIALIZER = new FailingSerializer("Null key for a Map not allowed in JSON (use a converting NullKeySerializer?)");
    @Deprecated
    public static final JsonSerializer<Object> DEFAULT_KEY_SERIALIZER = new com.amazon.org.codehaus.jackson.map.ser.std.StdKeySerializer();
    public static final JsonSerializer<Object> DEFAULT_UNKNOWN_SERIALIZER = new UnknownSerializer();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static final class WrappedSerializer extends JsonSerializer<Object> {
        protected final JsonSerializer<Object> _serializer;
        protected final TypeSerializer _typeSerializer;

        public WrappedSerializer(TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
            this._typeSerializer = typeSerializer;
            this._serializer = jsonSerializer;
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonSerializer
        public Class<Object> handledType() {
            return Object.class;
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonSerializer
        public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            this._serializer.serializeWithType(obj, jsonGenerator, serializerProvider, this._typeSerializer);
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonSerializer
        public void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonProcessingException {
            this._serializer.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
        }
    }

    public StdSerializerProvider() {
        super(null);
        this._unknownTypeSerializer = DEFAULT_UNKNOWN_SERIALIZER;
        this._nullValueSerializer = NullSerializer.instance;
        this._nullKeySerializer = DEFAULT_NULL_KEY_SERIALIZER;
        this._serializerFactory = null;
        this._serializerCache = new SerializerCache();
        this._knownSerializers = null;
        this._rootNames = new RootNameLookup();
    }

    protected JsonSerializer<Object> _createAndCacheUntypedSerializer(Class<?> cls, BeanProperty beanProperty) throws JsonMappingException {
        try {
            JsonSerializer<Object> _createUntypedSerializer = _createUntypedSerializer(this._config.constructType(cls), beanProperty);
            if (_createUntypedSerializer != null) {
                this._serializerCache.addAndResolveNonTypedSerializer(cls, _createUntypedSerializer, this);
            }
            return _createUntypedSerializer;
        } catch (IllegalArgumentException e) {
            throw new JsonMappingException(e.getMessage(), null, e);
        }
    }

    protected JsonSerializer<Object> _createUntypedSerializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        return this._serializerFactory.createSerializer(this._config, javaType, beanProperty);
    }

    protected JsonSerializer<Object> _findExplicitUntypedSerializer(Class<?> cls, BeanProperty beanProperty) {
        JsonSerializer<Object> untypedValueSerializer = this._knownSerializers.untypedValueSerializer(cls);
        if (untypedValueSerializer != null) {
            return untypedValueSerializer;
        }
        JsonSerializer<Object> untypedValueSerializer2 = this._serializerCache.untypedValueSerializer(cls);
        if (untypedValueSerializer2 != null) {
            return untypedValueSerializer2;
        }
        try {
            return _createAndCacheUntypedSerializer(cls, beanProperty);
        } catch (Exception unused) {
            return null;
        }
    }

    protected JsonSerializer<Object> _handleContextualResolvable(JsonSerializer<Object> jsonSerializer, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> createContextual;
        if ((jsonSerializer instanceof ContextualSerializer) && (createContextual = ((ContextualSerializer) jsonSerializer).createContextual(this._config, beanProperty)) != jsonSerializer) {
            if (createContextual instanceof ResolvableSerializer) {
                ((ResolvableSerializer) createContextual).resolve(this);
            }
            return createContextual;
        }
        return jsonSerializer;
    }

    protected void _reportIncompatibleRootType(Object obj, JavaType javaType) throws IOException, JsonProcessingException {
        if (!javaType.isPrimitive() || !ClassUtil.wrapperType(javaType.getRawClass()).isAssignableFrom(obj.getClass())) {
            StringBuilder sb = new StringBuilder();
            sb.append("Incompatible types: declared root type (");
            sb.append(javaType);
            sb.append(") vs ");
            throw new JsonMappingException(GeneratedOutlineSupport1.outline45(obj, sb));
        }
    }

    protected void _serializeValue(JsonGenerator jsonGenerator, Object obj) throws IOException, JsonProcessingException {
        JsonSerializer<Object> findTypedValueSerializer;
        boolean isEnabled;
        if (obj == null) {
            findTypedValueSerializer = getNullValueSerializer();
            isEnabled = false;
        } else {
            findTypedValueSerializer = findTypedValueSerializer(obj.getClass(), true, (BeanProperty) null);
            isEnabled = this._config.isEnabled(SerializationConfig.Feature.WRAP_ROOT_VALUE);
            if (isEnabled) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeFieldName(this._rootNames.findRootName(obj.getClass(), this._config));
            }
        }
        try {
            findTypedValueSerializer.serialize(obj, jsonGenerator, this);
            if (!isEnabled) {
                return;
            }
            jsonGenerator.writeEndObject();
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            String message = e2.getMessage();
            if (message == null) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[no message for ");
                outline107.append(e2.getClass().getName());
                outline107.append("]");
                message = outline107.toString();
            }
            throw new JsonMappingException(message, e2);
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.SerializerProvider
    public int cachedSerializersCount() {
        return this._serializerCache.size();
    }

    protected StdSerializerProvider createInstance(SerializationConfig serializationConfig, SerializerFactory serializerFactory) {
        return new StdSerializerProvider(serializationConfig, this, serializerFactory);
    }

    @Override // com.amazon.org.codehaus.jackson.map.SerializerProvider
    public void defaultSerializeDateKey(long j, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        if (isEnabled(SerializationConfig.Feature.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
            jsonGenerator.writeFieldName(String.valueOf(j));
            return;
        }
        if (this._dateFormat == null) {
            this._dateFormat = (DateFormat) this._config.getDateFormat().clone();
        }
        jsonGenerator.writeFieldName(this._dateFormat.format(new Date(j)));
    }

    @Override // com.amazon.org.codehaus.jackson.map.SerializerProvider
    public final void defaultSerializeDateValue(long j, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        if (isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
            jsonGenerator.writeNumber(j);
            return;
        }
        if (this._dateFormat == null) {
            this._dateFormat = (DateFormat) this._config.getDateFormat().clone();
        }
        jsonGenerator.writeString(this._dateFormat.format(new Date(j)));
    }

    @Override // com.amazon.org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer<Object> findKeySerializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> createKeySerializer = this._serializerFactory.createKeySerializer(this._config, javaType, beanProperty);
        if (createKeySerializer == null && (createKeySerializer = this._keySerializer) == null) {
            createKeySerializer = StdKeySerializers.getStdKeySerializer(javaType);
        }
        return createKeySerializer instanceof ContextualSerializer ? ((ContextualSerializer) createKeySerializer).createContextual(this._config, beanProperty) : createKeySerializer;
    }

    @Override // com.amazon.org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer<Object> findTypedValueSerializer(Class<?> cls, boolean z, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> typedValueSerializer = this._knownSerializers.typedValueSerializer(cls);
        if (typedValueSerializer != null) {
            return typedValueSerializer;
        }
        JsonSerializer<Object> typedValueSerializer2 = this._serializerCache.typedValueSerializer(cls);
        if (typedValueSerializer2 != null) {
            return typedValueSerializer2;
        }
        JsonSerializer<Object> findValueSerializer = findValueSerializer(cls, beanProperty);
        SerializerFactory serializerFactory = this._serializerFactory;
        SerializationConfig serializationConfig = this._config;
        TypeSerializer createTypeSerializer = serializerFactory.createTypeSerializer(serializationConfig, serializationConfig.constructType(cls), beanProperty);
        if (createTypeSerializer != null) {
            findValueSerializer = new WrappedSerializer(createTypeSerializer, findValueSerializer);
        }
        if (z) {
            this._serializerCache.addTypedSerializer(cls, findValueSerializer);
        }
        return findValueSerializer;
    }

    @Override // com.amazon.org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer<Object> findValueSerializer(Class<?> cls, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> untypedValueSerializer = this._knownSerializers.untypedValueSerializer(cls);
        if (untypedValueSerializer == null && (untypedValueSerializer = this._serializerCache.untypedValueSerializer(cls)) == null && (untypedValueSerializer = this._serializerCache.untypedValueSerializer(this._config.constructType(cls))) == null && (untypedValueSerializer = _createAndCacheUntypedSerializer(cls, beanProperty)) == null) {
            return getUnknownTypeSerializer(cls);
        }
        return _handleContextualResolvable(untypedValueSerializer, beanProperty);
    }

    @Override // com.amazon.org.codehaus.jackson.map.SerializerProvider
    public void flushCachedSerializers() {
        this._serializerCache.flush();
    }

    @Override // com.amazon.org.codehaus.jackson.map.SerializerProvider
    public JsonSchema generateJsonSchema(Class<?> cls, SerializationConfig serializationConfig, SerializerFactory serializerFactory) throws JsonMappingException {
        if (cls != null) {
            StdSerializerProvider createInstance = createInstance(serializationConfig, serializerFactory);
            if (createInstance.getClass() != StdSerializerProvider.class) {
                throw new IllegalStateException("Broken serializer provider: createInstance returned instance of type " + StdSerializerProvider.class + "; blueprint of type " + StdSerializerProvider.class);
            }
            JsonSerializer<Object> findValueSerializer = createInstance.findValueSerializer(cls, (BeanProperty) null);
            JsonNode schema = findValueSerializer instanceof SchemaAware ? ((SchemaAware) findValueSerializer).getSchema(createInstance, null) : JsonSchema.getDefaultSchemaNode();
            if (schema instanceof ObjectNode) {
                return new JsonSchema((ObjectNode) schema);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("Class "), " would not be serialized as a JSON object and therefore has no schema"));
        }
        throw new IllegalArgumentException("A class must be provided");
    }

    @Override // com.amazon.org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer<Object> getNullKeySerializer() {
        return this._nullKeySerializer;
    }

    @Override // com.amazon.org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer<Object> getNullValueSerializer() {
        return this._nullValueSerializer;
    }

    @Override // com.amazon.org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer<Object> getUnknownTypeSerializer(Class<?> cls) {
        return this._unknownTypeSerializer;
    }

    @Override // com.amazon.org.codehaus.jackson.map.SerializerProvider
    public boolean hasSerializerFor(SerializationConfig serializationConfig, Class<?> cls, SerializerFactory serializerFactory) {
        return createInstance(serializationConfig, serializerFactory)._findExplicitUntypedSerializer(cls, null) != null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.SerializerProvider
    public final void serializeValue(SerializationConfig serializationConfig, JsonGenerator jsonGenerator, Object obj, SerializerFactory serializerFactory) throws IOException, JsonGenerationException {
        if (serializerFactory != null) {
            StdSerializerProvider createInstance = createInstance(serializationConfig, serializerFactory);
            if (createInstance.getClass() != StdSerializerProvider.class) {
                throw new IllegalStateException("Broken serializer provider: createInstance returned instance of type " + StdSerializerProvider.class + "; blueprint of type " + StdSerializerProvider.class);
            }
            createInstance._serializeValue(jsonGenerator, obj);
            return;
        }
        throw new IllegalArgumentException("Can not pass null serializerFactory");
    }

    @Override // com.amazon.org.codehaus.jackson.map.SerializerProvider
    public void setDefaultKeySerializer(JsonSerializer<Object> jsonSerializer) {
        if (jsonSerializer != null) {
            this._keySerializer = jsonSerializer;
            return;
        }
        throw new IllegalArgumentException("Can not pass null JsonSerializer");
    }

    @Override // com.amazon.org.codehaus.jackson.map.SerializerProvider
    public void setNullKeySerializer(JsonSerializer<Object> jsonSerializer) {
        if (jsonSerializer != null) {
            this._nullKeySerializer = jsonSerializer;
            return;
        }
        throw new IllegalArgumentException("Can not pass null JsonSerializer");
    }

    @Override // com.amazon.org.codehaus.jackson.map.SerializerProvider
    public void setNullValueSerializer(JsonSerializer<Object> jsonSerializer) {
        if (jsonSerializer != null) {
            this._nullValueSerializer = jsonSerializer;
            return;
        }
        throw new IllegalArgumentException("Can not pass null JsonSerializer");
    }

    protected JsonSerializer<Object> _createAndCacheUntypedSerializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        try {
            JsonSerializer<Object> _createUntypedSerializer = _createUntypedSerializer(javaType, beanProperty);
            if (_createUntypedSerializer != null) {
                this._serializerCache.addAndResolveNonTypedSerializer(javaType, _createUntypedSerializer, this);
            }
            return _createUntypedSerializer;
        } catch (IllegalArgumentException e) {
            throw new JsonMappingException(e.getMessage(), null, e);
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.SerializerProvider
    public final void defaultSerializeDateValue(Date date, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        if (isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
            jsonGenerator.writeNumber(date.getTime());
            return;
        }
        if (this._dateFormat == null) {
            this._dateFormat = (DateFormat) this._config.getDateFormat().clone();
        }
        jsonGenerator.writeString(this._dateFormat.format(date));
    }

    @Override // com.amazon.org.codehaus.jackson.map.SerializerProvider
    public void defaultSerializeDateKey(Date date, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        if (isEnabled(SerializationConfig.Feature.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
            jsonGenerator.writeFieldName(String.valueOf(date.getTime()));
            return;
        }
        if (this._dateFormat == null) {
            this._dateFormat = (DateFormat) this._config.getDateFormat().clone();
        }
        jsonGenerator.writeFieldName(this._dateFormat.format(date));
    }

    @Override // com.amazon.org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer<Object> findTypedValueSerializer(JavaType javaType, boolean z, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> typedValueSerializer = this._knownSerializers.typedValueSerializer(javaType);
        if (typedValueSerializer != null) {
            return typedValueSerializer;
        }
        JsonSerializer<Object> typedValueSerializer2 = this._serializerCache.typedValueSerializer(javaType);
        if (typedValueSerializer2 != null) {
            return typedValueSerializer2;
        }
        JsonSerializer<Object> findValueSerializer = findValueSerializer(javaType, beanProperty);
        TypeSerializer createTypeSerializer = this._serializerFactory.createTypeSerializer(this._config, javaType, beanProperty);
        if (createTypeSerializer != null) {
            findValueSerializer = new WrappedSerializer(createTypeSerializer, findValueSerializer);
        }
        if (z) {
            this._serializerCache.addTypedSerializer(javaType, findValueSerializer);
        }
        return findValueSerializer;
    }

    @Override // com.amazon.org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer<Object> findValueSerializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> untypedValueSerializer = this._knownSerializers.untypedValueSerializer(javaType);
        if (untypedValueSerializer == null && (untypedValueSerializer = this._serializerCache.untypedValueSerializer(javaType)) == null && (untypedValueSerializer = _createAndCacheUntypedSerializer(javaType, beanProperty)) == null) {
            return getUnknownTypeSerializer(javaType.getRawClass());
        }
        return _handleContextualResolvable(untypedValueSerializer, beanProperty);
    }

    @Override // com.amazon.org.codehaus.jackson.map.SerializerProvider
    public final void serializeValue(SerializationConfig serializationConfig, JsonGenerator jsonGenerator, Object obj, JavaType javaType, SerializerFactory serializerFactory) throws IOException, JsonGenerationException {
        if (serializerFactory != null) {
            StdSerializerProvider createInstance = createInstance(serializationConfig, serializerFactory);
            if (createInstance.getClass() != StdSerializerProvider.class) {
                throw new IllegalStateException("Broken serializer provider: createInstance returned instance of type " + StdSerializerProvider.class + "; blueprint of type " + StdSerializerProvider.class);
            }
            createInstance._serializeValue(jsonGenerator, obj, javaType);
            return;
        }
        throw new IllegalArgumentException("Can not pass null serializerFactory");
    }

    protected StdSerializerProvider(SerializationConfig serializationConfig, StdSerializerProvider stdSerializerProvider, SerializerFactory serializerFactory) {
        super(serializationConfig);
        this._unknownTypeSerializer = DEFAULT_UNKNOWN_SERIALIZER;
        this._nullValueSerializer = NullSerializer.instance;
        this._nullKeySerializer = DEFAULT_NULL_KEY_SERIALIZER;
        if (serializationConfig != null) {
            this._serializerFactory = serializerFactory;
            this._serializerCache = stdSerializerProvider._serializerCache;
            this._unknownTypeSerializer = stdSerializerProvider._unknownTypeSerializer;
            this._keySerializer = stdSerializerProvider._keySerializer;
            this._nullValueSerializer = stdSerializerProvider._nullValueSerializer;
            this._nullKeySerializer = stdSerializerProvider._nullKeySerializer;
            this._rootNames = stdSerializerProvider._rootNames;
            this._knownSerializers = this._serializerCache.getReadOnlyLookupMap();
            return;
        }
        throw new NullPointerException();
    }

    protected void _serializeValue(JsonGenerator jsonGenerator, Object obj, JavaType javaType) throws IOException, JsonProcessingException {
        JsonSerializer<Object> jsonSerializer;
        boolean z;
        if (obj == null) {
            jsonSerializer = getNullValueSerializer();
            z = false;
        } else {
            if (!javaType.getRawClass().isAssignableFrom(obj.getClass())) {
                _reportIncompatibleRootType(obj, javaType);
            }
            JsonSerializer<Object> findTypedValueSerializer = findTypedValueSerializer(javaType, true, (BeanProperty) null);
            boolean isEnabled = this._config.isEnabled(SerializationConfig.Feature.WRAP_ROOT_VALUE);
            if (isEnabled) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeFieldName(this._rootNames.findRootName(javaType, this._config));
            }
            jsonSerializer = findTypedValueSerializer;
            z = isEnabled;
        }
        try {
            jsonSerializer.serialize(obj, jsonGenerator, this);
            if (!z) {
                return;
            }
            jsonGenerator.writeEndObject();
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            String message = e2.getMessage();
            if (message == null) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[no message for ");
                outline107.append(e2.getClass().getName());
                outline107.append("]");
                message = outline107.toString();
            }
            throw new JsonMappingException(message, e2);
        }
    }
}
