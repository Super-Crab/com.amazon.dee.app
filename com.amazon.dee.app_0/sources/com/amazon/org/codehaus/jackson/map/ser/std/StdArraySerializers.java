package com.amazon.org.codehaus.jackson.map.ser.std;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.ResolvableSerializer;
import com.amazon.org.codehaus.jackson.map.SerializationConfig;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import com.amazon.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.amazon.org.codehaus.jackson.node.ObjectNode;
import java.io.IOException;
import java.lang.reflect.Type;
/* loaded from: classes13.dex */
public class StdArraySerializers {

    /* loaded from: classes13.dex */
    public static abstract class ArraySerializerBase<T> extends ContainerSerializerBase<T> {
        protected final BeanProperty _property;
        protected final TypeSerializer _valueTypeSerializer;

        /* JADX INFO: Access modifiers changed from: protected */
        public ArraySerializerBase(Class<T> cls, TypeSerializer typeSerializer, BeanProperty beanProperty) {
            super(cls);
            this._valueTypeSerializer = typeSerializer;
            this._property = beanProperty;
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
        public final void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeStartArray();
            serializeContents(t, jsonGenerator, serializerProvider);
            jsonGenerator.writeEndArray();
        }

        protected abstract void serializeContents(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException;

        @Override // com.amazon.org.codehaus.jackson.map.JsonSerializer
        public final void serializeWithType(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
            typeSerializer.writeTypePrefixForArray(t, jsonGenerator);
            serializeContents(t, jsonGenerator, serializerProvider);
            typeSerializer.writeTypeSuffixForArray(t, jsonGenerator);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static final class BooleanArraySerializer extends ArraySerializerBase<boolean[]> {
        public BooleanArraySerializer() {
            super(boolean[].class, null, null);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.ContainerSerializerBase
        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS, createSchemaNode("boolean"));
            return createSchemaNode;
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.StdArraySerializers.ArraySerializerBase
        public void serializeContents(boolean[] zArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            for (boolean z : zArr) {
                jsonGenerator.writeBoolean(z);
            }
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static final class ByteArraySerializer extends SerializerBase<byte[]> {
        public ByteArraySerializer() {
            super(byte[].class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS, createSchemaNode("string"));
            return createSchemaNode;
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
        public void serialize(byte[] bArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeBinary(bArr);
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonSerializer
        public void serializeWithType(byte[] bArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
            typeSerializer.writeTypePrefixForScalar(bArr, jsonGenerator);
            jsonGenerator.writeBinary(bArr);
            typeSerializer.writeTypeSuffixForScalar(bArr, jsonGenerator);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static final class CharArraySerializer extends SerializerBase<char[]> {
        public CharArraySerializer() {
            super(char[].class);
        }

        private final void _writeArrayContents(JsonGenerator jsonGenerator, char[] cArr) throws IOException, JsonGenerationException {
            int length = cArr.length;
            for (int i = 0; i < length; i++) {
                jsonGenerator.writeString(cArr, i, 1);
            }
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            ObjectNode createSchemaNode2 = createSchemaNode("string");
            createSchemaNode2.put("type", "string");
            createSchemaNode.put(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS, createSchemaNode2);
            return createSchemaNode;
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
        public void serialize(char[] cArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            if (serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
                jsonGenerator.writeStartArray();
                _writeArrayContents(jsonGenerator, cArr);
                jsonGenerator.writeEndArray();
                return;
            }
            jsonGenerator.writeString(cArr, 0, cArr.length);
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonSerializer
        public void serializeWithType(char[] cArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
            if (serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
                typeSerializer.writeTypePrefixForArray(cArr, jsonGenerator);
                _writeArrayContents(jsonGenerator, cArr);
                typeSerializer.writeTypeSuffixForArray(cArr, jsonGenerator);
                return;
            }
            typeSerializer.writeTypePrefixForScalar(cArr, jsonGenerator);
            jsonGenerator.writeString(cArr, 0, cArr.length);
            typeSerializer.writeTypeSuffixForScalar(cArr, jsonGenerator);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static final class DoubleArraySerializer extends ArraySerializerBase<double[]> {
        public DoubleArraySerializer() {
            super(double[].class, null, null);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.ContainerSerializerBase
        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS, createSchemaNode("number"));
            return createSchemaNode;
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.StdArraySerializers.ArraySerializerBase
        public void serializeContents(double[] dArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            for (double d : dArr) {
                jsonGenerator.writeNumber(d);
            }
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static final class FloatArraySerializer extends ArraySerializerBase<float[]> {
        public FloatArraySerializer() {
            this(null);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.ContainerSerializerBase
        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new FloatArraySerializer(typeSerializer);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS, createSchemaNode("number"));
            return createSchemaNode;
        }

        public FloatArraySerializer(TypeSerializer typeSerializer) {
            super(float[].class, typeSerializer, null);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.StdArraySerializers.ArraySerializerBase
        public void serializeContents(float[] fArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            for (float f : fArr) {
                jsonGenerator.writeNumber(f);
            }
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static final class IntArraySerializer extends ArraySerializerBase<int[]> {
        public IntArraySerializer() {
            super(int[].class, null, null);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.ContainerSerializerBase
        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS, createSchemaNode("integer"));
            return createSchemaNode;
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.StdArraySerializers.ArraySerializerBase
        public void serializeContents(int[] iArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            for (int i : iArr) {
                jsonGenerator.writeNumber(i);
            }
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static final class LongArraySerializer extends ArraySerializerBase<long[]> {
        public LongArraySerializer() {
            this(null);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.ContainerSerializerBase
        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new LongArraySerializer(typeSerializer);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS, createSchemaNode("number", true));
            return createSchemaNode;
        }

        public LongArraySerializer(TypeSerializer typeSerializer) {
            super(long[].class, typeSerializer, null);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.StdArraySerializers.ArraySerializerBase
        public void serializeContents(long[] jArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            for (long j : jArr) {
                jsonGenerator.writeNumber(j);
            }
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static final class ShortArraySerializer extends ArraySerializerBase<short[]> {
        public ShortArraySerializer() {
            this(null);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.ContainerSerializerBase
        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new ShortArraySerializer(typeSerializer);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS, createSchemaNode("integer"));
            return createSchemaNode;
        }

        public ShortArraySerializer(TypeSerializer typeSerializer) {
            super(short[].class, typeSerializer, null);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.StdArraySerializers.ArraySerializerBase
        public void serializeContents(short[] sArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            for (short s : sArr) {
                jsonGenerator.writeNumber((int) s);
            }
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static final class StringArraySerializer extends ArraySerializerBase<String[]> implements ResolvableSerializer {
        protected JsonSerializer<Object> _elementSerializer;

        public StringArraySerializer(BeanProperty beanProperty) {
            super(String[].class, null, beanProperty);
        }

        private void serializeContentsSlow(String[] strArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) throws IOException, JsonGenerationException {
            int length = strArr.length;
            for (int i = 0; i < length; i++) {
                if (strArr[i] == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else {
                    jsonSerializer.serialize(strArr[i], jsonGenerator, serializerProvider);
                }
            }
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.ContainerSerializerBase
        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS, createSchemaNode("string"));
            return createSchemaNode;
        }

        @Override // com.amazon.org.codehaus.jackson.map.ResolvableSerializer
        public void resolve(SerializerProvider serializerProvider) throws JsonMappingException {
            JsonSerializer<Object> findValueSerializer = serializerProvider.findValueSerializer(String.class, this._property);
            if (findValueSerializer == null || findValueSerializer.getClass().getAnnotation(JacksonStdImpl.class) != null) {
                return;
            }
            this._elementSerializer = findValueSerializer;
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.StdArraySerializers.ArraySerializerBase
        public void serializeContents(String[] strArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            int length = strArr.length;
            if (length == 0) {
                return;
            }
            JsonSerializer<Object> jsonSerializer = this._elementSerializer;
            if (jsonSerializer != null) {
                serializeContentsSlow(strArr, jsonGenerator, serializerProvider, jsonSerializer);
                return;
            }
            for (int i = 0; i < length; i++) {
                if (strArr[i] == null) {
                    jsonGenerator.writeNull();
                } else {
                    jsonGenerator.writeString(strArr[i]);
                }
            }
        }
    }
}
