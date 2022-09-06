package com.amazon.org.codehaus.jackson.map;

import com.amazon.org.codehaus.jackson.FormatSchema;
import com.amazon.org.codehaus.jackson.JsonEncoding;
import com.amazon.org.codehaus.jackson.JsonFactory;
import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.PrettyPrinter;
import com.amazon.org.codehaus.jackson.Version;
import com.amazon.org.codehaus.jackson.Versioned;
import com.amazon.org.codehaus.jackson.io.SegmentedStringWriter;
import com.amazon.org.codehaus.jackson.map.SerializationConfig;
import com.amazon.org.codehaus.jackson.map.ser.FilterProvider;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.amazon.org.codehaus.jackson.type.TypeReference;
import com.amazon.org.codehaus.jackson.util.ByteArrayBuilder;
import com.amazon.org.codehaus.jackson.util.DefaultPrettyPrinter;
import com.amazon.org.codehaus.jackson.util.MinimalPrettyPrinter;
import com.amazon.org.codehaus.jackson.util.VersionUtil;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.text.DateFormat;
/* loaded from: classes13.dex */
public class ObjectWriter implements Versioned {
    protected static final PrettyPrinter NULL_PRETTY_PRINTER = new MinimalPrettyPrinter();
    protected final SerializationConfig _config;
    protected final JsonFactory _jsonFactory;
    protected final PrettyPrinter _prettyPrinter;
    protected final SerializerProvider _provider;
    protected final JavaType _rootType;
    protected final FormatSchema _schema;
    protected final SerializerFactory _serializerFactory;

    /* JADX INFO: Access modifiers changed from: protected */
    public ObjectWriter(ObjectMapper objectMapper, SerializationConfig serializationConfig, JavaType javaType, PrettyPrinter prettyPrinter) {
        this._config = serializationConfig;
        this._provider = objectMapper._serializerProvider;
        this._serializerFactory = objectMapper._serializerFactory;
        this._jsonFactory = objectMapper._jsonFactory;
        this._rootType = javaType;
        this._prettyPrinter = prettyPrinter;
        this._schema = null;
    }

    private final void _configAndWriteCloseable(JsonGenerator jsonGenerator, Object obj, SerializationConfig serializationConfig) throws IOException, JsonGenerationException, JsonMappingException {
        Closeable closeable = (Closeable) obj;
        try {
            if (this._rootType == null) {
                this._provider.serializeValue(serializationConfig, jsonGenerator, obj, this._serializerFactory);
            } else {
                this._provider.serializeValue(serializationConfig, jsonGenerator, obj, this._rootType, this._serializerFactory);
            }
            if (this._schema != null) {
                jsonGenerator.setSchema(this._schema);
            }
            try {
                jsonGenerator.close();
            } catch (Throwable th) {
                th = th;
                jsonGenerator = null;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            closeable.close();
        } catch (Throwable th3) {
            th = th3;
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
    }

    private final void _writeCloseableValue(JsonGenerator jsonGenerator, Object obj, SerializationConfig serializationConfig) throws IOException, JsonGenerationException, JsonMappingException {
        Closeable closeable = (Closeable) obj;
        try {
            if (this._rootType == null) {
                this._provider.serializeValue(serializationConfig, jsonGenerator, obj, this._serializerFactory);
            } else {
                this._provider.serializeValue(serializationConfig, jsonGenerator, obj, this._rootType, this._serializerFactory);
            }
            if (this._config.isEnabled(SerializationConfig.Feature.FLUSH_AFTER_WRITE_VALUE)) {
                jsonGenerator.flush();
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            closeable.close();
        } catch (Throwable th2) {
            closeable = null;
            th = th2;
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException unused) {
                }
            }
            throw th;
        }
    }

    protected final void _configAndWriteValue(JsonGenerator jsonGenerator, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        PrettyPrinter prettyPrinter = this._prettyPrinter;
        if (prettyPrinter != null) {
            if (prettyPrinter == NULL_PRETTY_PRINTER) {
                prettyPrinter = null;
            }
            jsonGenerator.setPrettyPrinter(prettyPrinter);
        } else if (this._config.isEnabled(SerializationConfig.Feature.INDENT_OUTPUT)) {
            jsonGenerator.useDefaultPrettyPrinter();
        }
        FormatSchema formatSchema = this._schema;
        if (formatSchema != null) {
            jsonGenerator.setSchema(formatSchema);
        }
        if (this._config.isEnabled(SerializationConfig.Feature.CLOSE_CLOSEABLE) && (obj instanceof Closeable)) {
            _configAndWriteCloseable(jsonGenerator, obj, this._config);
            return;
        }
        boolean z = false;
        try {
            if (this._rootType == null) {
                this._provider.serializeValue(this._config, jsonGenerator, obj, this._serializerFactory);
            } else {
                this._provider.serializeValue(this._config, jsonGenerator, obj, this._rootType, this._serializerFactory);
            }
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

    public boolean canSerialize(Class<?> cls) {
        return this._provider.hasSerializerFor(this._config, cls, this._serializerFactory);
    }

    @Override // com.amazon.org.codehaus.jackson.Versioned
    public Version version() {
        return VersionUtil.versionFor(ObjectWriter.class);
    }

    public ObjectWriter withDateFormat(DateFormat dateFormat) {
        SerializationConfig mo4122withDateFormat = this._config.mo4122withDateFormat(dateFormat);
        return mo4122withDateFormat == this._config ? this : new ObjectWriter(this, mo4122withDateFormat);
    }

    public ObjectWriter withDefaultPrettyPrinter() {
        return withPrettyPrinter(new DefaultPrettyPrinter());
    }

    public ObjectWriter withFilters(FilterProvider filterProvider) {
        return filterProvider == this._config.getFilterProvider() ? this : new ObjectWriter(this, this._config.withFilters(filterProvider));
    }

    public ObjectWriter withPrettyPrinter(PrettyPrinter prettyPrinter) {
        if (prettyPrinter == this._prettyPrinter) {
            return this;
        }
        if (prettyPrinter == null) {
            prettyPrinter = NULL_PRETTY_PRINTER;
        }
        return new ObjectWriter(this, this._config, this._rootType, prettyPrinter, this._schema);
    }

    public ObjectWriter withSchema(FormatSchema formatSchema) {
        return this._schema == formatSchema ? this : new ObjectWriter(this, this._config, this._rootType, this._prettyPrinter, formatSchema);
    }

    public ObjectWriter withType(JavaType javaType) {
        return javaType == this._rootType ? this : new ObjectWriter(this, this._config, javaType, this._prettyPrinter, this._schema);
    }

    public ObjectWriter withView(Class<?> cls) {
        return cls == this._config.getSerializationView() ? this : new ObjectWriter(this, this._config.withView(cls));
    }

    public void writeValue(JsonGenerator jsonGenerator, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        if (this._config.isEnabled(SerializationConfig.Feature.CLOSE_CLOSEABLE) && (obj instanceof Closeable)) {
            _writeCloseableValue(jsonGenerator, obj, this._config);
            return;
        }
        JavaType javaType = this._rootType;
        if (javaType == null) {
            this._provider.serializeValue(this._config, jsonGenerator, obj, this._serializerFactory);
        } else {
            this._provider.serializeValue(this._config, jsonGenerator, obj, javaType, this._serializerFactory);
        }
        if (!this._config.isEnabled(SerializationConfig.Feature.FLUSH_AFTER_WRITE_VALUE)) {
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

    public ObjectWriter withType(Class<?> cls) {
        return withType(this._config.constructType(cls));
    }

    public ObjectWriter withType(TypeReference<?> typeReference) {
        return withType(this._config.getTypeFactory().constructType(typeReference.getType()));
    }

    public void writeValue(File file, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        _configAndWriteValue(this._jsonFactory.createJsonGenerator(file, JsonEncoding.UTF8), obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ObjectWriter(ObjectMapper objectMapper, SerializationConfig serializationConfig) {
        this._config = serializationConfig;
        this._provider = objectMapper._serializerProvider;
        this._serializerFactory = objectMapper._serializerFactory;
        this._jsonFactory = objectMapper._jsonFactory;
        this._rootType = null;
        this._prettyPrinter = null;
        this._schema = null;
    }

    public void writeValue(OutputStream outputStream, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        _configAndWriteValue(this._jsonFactory.createJsonGenerator(outputStream, JsonEncoding.UTF8), obj);
    }

    public void writeValue(Writer writer, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        _configAndWriteValue(this._jsonFactory.createJsonGenerator(writer), obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ObjectWriter(ObjectMapper objectMapper, SerializationConfig serializationConfig, FormatSchema formatSchema) {
        this._config = serializationConfig;
        this._provider = objectMapper._serializerProvider;
        this._serializerFactory = objectMapper._serializerFactory;
        this._jsonFactory = objectMapper._jsonFactory;
        this._rootType = null;
        this._prettyPrinter = null;
        this._schema = formatSchema;
    }

    protected ObjectWriter(ObjectWriter objectWriter, SerializationConfig serializationConfig, JavaType javaType, PrettyPrinter prettyPrinter, FormatSchema formatSchema) {
        this._config = serializationConfig;
        this._provider = objectWriter._provider;
        this._serializerFactory = objectWriter._serializerFactory;
        this._jsonFactory = objectWriter._jsonFactory;
        this._rootType = javaType;
        this._prettyPrinter = prettyPrinter;
        this._schema = formatSchema;
    }

    protected ObjectWriter(ObjectWriter objectWriter, SerializationConfig serializationConfig) {
        this._config = serializationConfig;
        this._provider = objectWriter._provider;
        this._serializerFactory = objectWriter._serializerFactory;
        this._jsonFactory = objectWriter._jsonFactory;
        this._schema = objectWriter._schema;
        this._rootType = objectWriter._rootType;
        this._prettyPrinter = objectWriter._prettyPrinter;
    }
}
