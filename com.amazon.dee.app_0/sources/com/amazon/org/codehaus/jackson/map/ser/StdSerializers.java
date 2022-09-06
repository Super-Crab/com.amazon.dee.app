package com.amazon.org.codehaus.jackson.map.ser;

import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.amazon.org.codehaus.jackson.map.ser.std.DateSerializer;
import com.amazon.org.codehaus.jackson.map.ser.std.NonTypedScalarSerializerBase;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
/* loaded from: classes13.dex */
public class StdSerializers {

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static final class BooleanSerializer extends NonTypedScalarSerializerBase<Boolean> {
        final boolean _forPrimitive;

        public BooleanSerializer(boolean z) {
            super(Boolean.class);
            this._forPrimitive = z;
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("boolean", !this._forPrimitive);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
        public void serialize(Boolean bool, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeBoolean(bool.booleanValue());
        }
    }

    @JacksonStdImpl
    @Deprecated
    /* loaded from: classes13.dex */
    public static final class CalendarSerializer extends com.amazon.org.codehaus.jackson.map.ser.std.CalendarSerializer {
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static final class DoubleSerializer extends NonTypedScalarSerializerBase<Double> {
        static final DoubleSerializer instance = new DoubleSerializer();

        public DoubleSerializer() {
            super(Double.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("number", true);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
        public void serialize(Double d, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeNumber(d.doubleValue());
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static final class FloatSerializer extends com.amazon.org.codehaus.jackson.map.ser.std.ScalarSerializerBase<Float> {
        static final FloatSerializer instance = new FloatSerializer();

        public FloatSerializer() {
            super(Float.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("number", true);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
        public void serialize(Float f, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeNumber(f.floatValue());
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static final class IntLikeSerializer extends com.amazon.org.codehaus.jackson.map.ser.std.ScalarSerializerBase<Number> {
        static final IntLikeSerializer instance = new IntLikeSerializer();

        public IntLikeSerializer() {
            super(Number.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("integer", true);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
        public void serialize(Number number, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeNumber(number.intValue());
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static final class IntegerSerializer extends NonTypedScalarSerializerBase<Integer> {
        public IntegerSerializer() {
            super(Integer.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("integer", true);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
        public void serialize(Integer num, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeNumber(num.intValue());
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static final class LongSerializer extends com.amazon.org.codehaus.jackson.map.ser.std.ScalarSerializerBase<Long> {
        static final LongSerializer instance = new LongSerializer();

        public LongSerializer() {
            super(Long.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("number", true);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
        public void serialize(Long l, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeNumber(l.longValue());
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static final class NumberSerializer extends com.amazon.org.codehaus.jackson.map.ser.std.ScalarSerializerBase<Number> {
        public static final NumberSerializer instance = new NumberSerializer();

        public NumberSerializer() {
            super(Number.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("number", true);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
        public void serialize(Number number, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            if (number instanceof BigDecimal) {
                jsonGenerator.writeNumber((BigDecimal) number);
            } else if (number instanceof BigInteger) {
                jsonGenerator.writeNumber((BigInteger) number);
            } else if (number instanceof Integer) {
                jsonGenerator.writeNumber(number.intValue());
            } else if (number instanceof Long) {
                jsonGenerator.writeNumber(number.longValue());
            } else if (number instanceof Double) {
                jsonGenerator.writeNumber(number.doubleValue());
            } else if (number instanceof Float) {
                jsonGenerator.writeNumber(number.floatValue());
            } else if (!(number instanceof Byte) && !(number instanceof Short)) {
                jsonGenerator.writeNumber(number.toString());
            } else {
                jsonGenerator.writeNumber(number.intValue());
            }
        }
    }

    @JacksonStdImpl
    @Deprecated
    /* loaded from: classes13.dex */
    public static final class SerializableSerializer extends com.amazon.org.codehaus.jackson.map.ser.std.SerializableSerializer {
    }

    @JacksonStdImpl
    @Deprecated
    /* loaded from: classes13.dex */
    public static final class SerializableWithTypeSerializer extends com.amazon.org.codehaus.jackson.map.ser.std.SerializableWithTypeSerializer {
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static final class SqlDateSerializer extends com.amazon.org.codehaus.jackson.map.ser.std.ScalarSerializerBase<Date> {
        public SqlDateSerializer() {
            super(Date.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("string", true);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
        public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeString(date.toString());
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static final class SqlTimeSerializer extends com.amazon.org.codehaus.jackson.map.ser.std.ScalarSerializerBase<Time> {
        public SqlTimeSerializer() {
            super(Time.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("string", true);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
        public void serialize(Time time, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeString(time.toString());
        }
    }

    @JacksonStdImpl
    @Deprecated
    /* loaded from: classes13.dex */
    public static final class StringSerializer extends NonTypedScalarSerializerBase<String> {
        public StringSerializer() {
            super(String.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("string", true);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
        public void serialize(String str, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeString(str);
        }
    }

    @JacksonStdImpl
    @Deprecated
    /* loaded from: classes13.dex */
    public static final class UtilDateSerializer extends DateSerializer {
    }

    protected StdSerializers() {
    }
}
