package com.amazon.org.codehaus.jackson.map.deser.std;

import com.amazon.org.codehaus.jackson.Base64Variants;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.DeserializationConfig;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.TypeDeserializer;
import com.amazon.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.amazon.org.codehaus.jackson.map.type.TypeFactory;
import com.amazon.org.codehaus.jackson.map.util.ArrayBuilders;
import com.amazon.org.codehaus.jackson.map.util.ObjectBuffer;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.HashMap;
/* loaded from: classes13.dex */
public class PrimitiveArrayDeserializers {
    static final PrimitiveArrayDeserializers instance = new PrimitiveArrayDeserializers();
    HashMap<JavaType, JsonDeserializer<Object>> _allDeserializers = new HashMap<>();

    /* loaded from: classes13.dex */
    static abstract class Base<T> extends StdDeserializer<T> {
        protected Base(Class<T> cls) {
            super((Class<?>) cls);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdDeserializer, com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserializeWithType */
        public Object mo4196deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
            return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    static final class BooleanDeser extends Base<boolean[]> {
        public BooleanDeser() {
            super(boolean[].class);
        }

        private final boolean[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
                return null;
            }
            if (!deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                throw deserializationContext.mappingException(this._valueClass);
            }
            return new boolean[]{_parseBooleanPrimitive(jsonParser, deserializationContext)};
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize  reason: collision with other method in class */
        public boolean[] mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.BooleanBuilder booleanBuilder = deserializationContext.getArrayBuilders().getBooleanBuilder();
            boolean[] resetAndStart = booleanBuilder.resetAndStart();
            int i = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                boolean _parseBooleanPrimitive = _parseBooleanPrimitive(jsonParser, deserializationContext);
                if (i >= resetAndStart.length) {
                    resetAndStart = booleanBuilder.appendCompletedChunk(resetAndStart, i);
                    i = 0;
                }
                resetAndStart[i] = _parseBooleanPrimitive;
                i++;
            }
            return booleanBuilder.completeAndClearBuffer(resetAndStart, i);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    static final class ByteDeser extends Base<byte[]> {
        public ByteDeser() {
            super(byte[].class);
        }

        private final byte[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            byte byteValue;
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
                return null;
            }
            if (deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                JsonToken currentToken = jsonParser.getCurrentToken();
                if (currentToken != JsonToken.VALUE_NUMBER_INT && currentToken != JsonToken.VALUE_NUMBER_FLOAT) {
                    if (currentToken != JsonToken.VALUE_NULL) {
                        throw deserializationContext.mappingException(this._valueClass.getComponentType());
                    }
                    byteValue = 0;
                } else {
                    byteValue = jsonParser.getByteValue();
                }
                return new byte[]{byteValue};
            }
            throw deserializationContext.mappingException(this._valueClass);
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize  reason: collision with other method in class */
        public byte[] mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            byte byteValue;
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_STRING) {
                return jsonParser.getBinaryValue(deserializationContext.getBase64Variant());
            }
            if (currentToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                Object embeddedObject = jsonParser.getEmbeddedObject();
                if (embeddedObject == null) {
                    return null;
                }
                if (embeddedObject instanceof byte[]) {
                    return (byte[]) embeddedObject;
                }
            }
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.ByteBuilder byteBuilder = deserializationContext.getArrayBuilders().getByteBuilder();
            byte[] resetAndStart = byteBuilder.resetAndStart();
            int i = 0;
            while (true) {
                JsonToken nextToken = jsonParser.nextToken();
                if (nextToken != JsonToken.END_ARRAY) {
                    if (nextToken != JsonToken.VALUE_NUMBER_INT && nextToken != JsonToken.VALUE_NUMBER_FLOAT) {
                        if (nextToken != JsonToken.VALUE_NULL) {
                            throw deserializationContext.mappingException(this._valueClass.getComponentType());
                        }
                        byteValue = 0;
                    } else {
                        byteValue = jsonParser.getByteValue();
                    }
                    if (i >= resetAndStart.length) {
                        resetAndStart = byteBuilder.appendCompletedChunk(resetAndStart, i);
                        i = 0;
                    }
                    resetAndStart[i] = byteValue;
                    i++;
                } else {
                    return byteBuilder.completeAndClearBuffer(resetAndStart, i);
                }
            }
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    static final class CharDeser extends Base<char[]> {
        public CharDeser() {
            super(char[].class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize  reason: collision with other method in class */
        public char[] mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_STRING) {
                char[] textCharacters = jsonParser.getTextCharacters();
                int textOffset = jsonParser.getTextOffset();
                int textLength = jsonParser.getTextLength();
                char[] cArr = new char[textLength];
                System.arraycopy(textCharacters, textOffset, cArr, 0, textLength);
                return cArr;
            } else if (jsonParser.isExpectedStartArrayToken()) {
                StringBuilder sb = new StringBuilder(64);
                while (true) {
                    JsonToken nextToken = jsonParser.nextToken();
                    if (nextToken != JsonToken.END_ARRAY) {
                        if (nextToken == JsonToken.VALUE_STRING) {
                            String text = jsonParser.getText();
                            if (text.length() == 1) {
                                sb.append(text.charAt(0));
                            } else {
                                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can not convert a JSON String of length ");
                                outline107.append(text.length());
                                outline107.append(" into a char element of char array");
                                throw JsonMappingException.from(jsonParser, outline107.toString());
                            }
                        } else {
                            throw deserializationContext.mappingException(Character.TYPE);
                        }
                    } else {
                        return sb.toString().toCharArray();
                    }
                }
            } else {
                if (currentToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                    Object embeddedObject = jsonParser.getEmbeddedObject();
                    if (embeddedObject == null) {
                        return null;
                    }
                    if (embeddedObject instanceof char[]) {
                        return (char[]) embeddedObject;
                    }
                    if (embeddedObject instanceof String) {
                        return ((String) embeddedObject).toCharArray();
                    }
                    if (embeddedObject instanceof byte[]) {
                        return Base64Variants.getDefaultVariant().encode((byte[]) embeddedObject, false).toCharArray();
                    }
                }
                throw deserializationContext.mappingException(this._valueClass);
            }
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    static final class DoubleDeser extends Base<double[]> {
        public DoubleDeser() {
            super(double[].class);
        }

        private final double[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
                return null;
            }
            if (!deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                throw deserializationContext.mappingException(this._valueClass);
            }
            return new double[]{_parseDoublePrimitive(jsonParser, deserializationContext)};
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize  reason: collision with other method in class */
        public double[] mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.DoubleBuilder doubleBuilder = deserializationContext.getArrayBuilders().getDoubleBuilder();
            double[] resetAndStart = doubleBuilder.resetAndStart();
            int i = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                double _parseDoublePrimitive = _parseDoublePrimitive(jsonParser, deserializationContext);
                if (i >= resetAndStart.length) {
                    resetAndStart = doubleBuilder.appendCompletedChunk(resetAndStart, i);
                    i = 0;
                }
                resetAndStart[i] = _parseDoublePrimitive;
                i++;
            }
            return doubleBuilder.completeAndClearBuffer(resetAndStart, i);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    static final class FloatDeser extends Base<float[]> {
        public FloatDeser() {
            super(float[].class);
        }

        private final float[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
                return null;
            }
            if (!deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                throw deserializationContext.mappingException(this._valueClass);
            }
            return new float[]{_parseFloatPrimitive(jsonParser, deserializationContext)};
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize  reason: collision with other method in class */
        public float[] mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.FloatBuilder floatBuilder = deserializationContext.getArrayBuilders().getFloatBuilder();
            float[] resetAndStart = floatBuilder.resetAndStart();
            int i = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                float _parseFloatPrimitive = _parseFloatPrimitive(jsonParser, deserializationContext);
                if (i >= resetAndStart.length) {
                    resetAndStart = floatBuilder.appendCompletedChunk(resetAndStart, i);
                    i = 0;
                }
                resetAndStart[i] = _parseFloatPrimitive;
                i++;
            }
            return floatBuilder.completeAndClearBuffer(resetAndStart, i);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    static final class IntDeser extends Base<int[]> {
        public IntDeser() {
            super(int[].class);
        }

        private final int[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
                return null;
            }
            if (!deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                throw deserializationContext.mappingException(this._valueClass);
            }
            return new int[]{_parseIntPrimitive(jsonParser, deserializationContext)};
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize  reason: collision with other method in class */
        public int[] mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.IntBuilder intBuilder = deserializationContext.getArrayBuilders().getIntBuilder();
            int[] resetAndStart = intBuilder.resetAndStart();
            int i = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                int _parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
                if (i >= resetAndStart.length) {
                    resetAndStart = intBuilder.appendCompletedChunk(resetAndStart, i);
                    i = 0;
                }
                resetAndStart[i] = _parseIntPrimitive;
                i++;
            }
            return intBuilder.completeAndClearBuffer(resetAndStart, i);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    static final class LongDeser extends Base<long[]> {
        public LongDeser() {
            super(long[].class);
        }

        private final long[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
                return null;
            }
            if (!deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                throw deserializationContext.mappingException(this._valueClass);
            }
            return new long[]{_parseLongPrimitive(jsonParser, deserializationContext)};
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize  reason: collision with other method in class */
        public long[] mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.LongBuilder longBuilder = deserializationContext.getArrayBuilders().getLongBuilder();
            long[] resetAndStart = longBuilder.resetAndStart();
            int i = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                long _parseLongPrimitive = _parseLongPrimitive(jsonParser, deserializationContext);
                if (i >= resetAndStart.length) {
                    resetAndStart = longBuilder.appendCompletedChunk(resetAndStart, i);
                    i = 0;
                }
                resetAndStart[i] = _parseLongPrimitive;
                i++;
            }
            return longBuilder.completeAndClearBuffer(resetAndStart, i);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    static final class ShortDeser extends Base<short[]> {
        public ShortDeser() {
            super(short[].class);
        }

        private final short[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
                return null;
            }
            if (!deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                throw deserializationContext.mappingException(this._valueClass);
            }
            return new short[]{_parseShortPrimitive(jsonParser, deserializationContext)};
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize  reason: collision with other method in class */
        public short[] mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.ShortBuilder shortBuilder = deserializationContext.getArrayBuilders().getShortBuilder();
            short[] resetAndStart = shortBuilder.resetAndStart();
            int i = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                short _parseShortPrimitive = _parseShortPrimitive(jsonParser, deserializationContext);
                if (i >= resetAndStart.length) {
                    resetAndStart = shortBuilder.appendCompletedChunk(resetAndStart, i);
                    i = 0;
                }
                resetAndStart[i] = _parseShortPrimitive;
                i++;
            }
            return shortBuilder.completeAndClearBuffer(resetAndStart, i);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    static final class StringDeser extends Base<String[]> {
        public StringDeser() {
            super(String[].class);
        }

        private final String[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            String str = null;
            if (!deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                if (jsonParser.getCurrentToken() != JsonToken.VALUE_STRING || !deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) || jsonParser.getText().length() != 0) {
                    throw deserializationContext.mappingException(this._valueClass);
                }
                return null;
            }
            String[] strArr = new String[1];
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                str = jsonParser.getText();
            }
            strArr[0] = str;
            return strArr;
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize  reason: collision with other method in class */
        public String[] mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ObjectBuffer leaseObjectBuffer = deserializationContext.leaseObjectBuffer();
            Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
            int i = 0;
            while (true) {
                JsonToken nextToken = jsonParser.nextToken();
                if (nextToken != JsonToken.END_ARRAY) {
                    String text = nextToken == JsonToken.VALUE_NULL ? null : jsonParser.getText();
                    if (i >= resetAndStart.length) {
                        resetAndStart = leaseObjectBuffer.appendCompletedChunk(resetAndStart);
                        i = 0;
                    }
                    resetAndStart[i] = text;
                    i++;
                } else {
                    String[] strArr = (String[]) leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i, String.class);
                    deserializationContext.returnObjectBuffer(leaseObjectBuffer);
                    return strArr;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PrimitiveArrayDeserializers() {
        add(Boolean.TYPE, new BooleanDeser());
        add(Byte.TYPE, new ByteDeser());
        add(Short.TYPE, new ShortDeser());
        add(Integer.TYPE, new IntDeser());
        add(Long.TYPE, new LongDeser());
        add(Float.TYPE, new FloatDeser());
        add(Double.TYPE, new DoubleDeser());
        add(String.class, new StringDeser());
        add(Character.TYPE, new CharDeser());
    }

    private void add(Class<?> cls, JsonDeserializer<?> jsonDeserializer) {
        this._allDeserializers.put(TypeFactory.defaultInstance().constructType(cls), jsonDeserializer);
    }

    public static HashMap<JavaType, JsonDeserializer<Object>> getAll() {
        return instance._allDeserializers;
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }
}
