package com.amazon.org.codehaus.jackson.map.deser.std;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.io.NumberInput;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.DeserializationConfig;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.DeserializerProvider;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.TypeDeserializer;
import com.amazon.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.devsupport.StackTraceHelper;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
/* loaded from: classes13.dex */
public abstract class StdDeserializer<T> extends JsonDeserializer<T> {
    protected final Class<?> _valueClass;

    /* renamed from: com.amazon.org.codehaus.jackson.map.deser.std.StdDeserializer$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$org$codehaus$jackson$JsonParser$NumberType = new int[JsonParser.NumberType.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken;

        static {
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonParser$NumberType[JsonParser.NumberType.INT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonParser$NumberType[JsonParser.NumberType.LONG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static class BigDecimalDeserializer extends StdScalarDeserializer<BigDecimal> {
        public BigDecimalDeserializer() {
            super(BigDecimal.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize  reason: collision with other method in class */
        public BigDecimal mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken != JsonToken.VALUE_NUMBER_INT && currentToken != JsonToken.VALUE_NUMBER_FLOAT) {
                if (currentToken == JsonToken.VALUE_STRING) {
                    String trim = jsonParser.getText().trim();
                    if (trim.length() == 0) {
                        return null;
                    }
                    try {
                        return new BigDecimal(trim);
                    } catch (IllegalArgumentException unused) {
                        throw deserializationContext.weirdStringException(this._valueClass, "not a valid representation");
                    }
                }
                throw deserializationContext.mappingException(this._valueClass, currentToken);
            }
            return jsonParser.getDecimalValue();
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static class BigIntegerDeserializer extends StdScalarDeserializer<BigInteger> {
        public BigIntegerDeserializer() {
            super(BigInteger.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize  reason: collision with other method in class */
        public BigInteger mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                int ordinal = jsonParser.getNumberType().ordinal();
                if (ordinal == 0 || ordinal == 1) {
                    return BigInteger.valueOf(jsonParser.getLongValue());
                }
            } else if (currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                return jsonParser.getDecimalValue().toBigInteger();
            } else {
                if (currentToken != JsonToken.VALUE_STRING) {
                    throw deserializationContext.mappingException(this._valueClass, currentToken);
                }
            }
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return null;
            }
            try {
                return new BigInteger(trim);
            } catch (IllegalArgumentException unused) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid representation");
            }
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static final class BooleanDeserializer extends PrimitiveOrWrapperDeserializer<Boolean> {
        public BooleanDeserializer(Class<Boolean> cls, Boolean bool) {
            super(cls, bool);
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize */
        public Boolean mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return _parseBoolean(jsonParser, deserializationContext);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdScalarDeserializer, com.amazon.org.codehaus.jackson.map.deser.std.StdDeserializer, com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserializeWithType */
        public Boolean mo4196deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
            return _parseBoolean(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static final class ByteDeserializer extends PrimitiveOrWrapperDeserializer<Byte> {
        public ByteDeserializer(Class<Byte> cls, Byte b) {
            super(cls, b);
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize */
        public Byte mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return _parseByte(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static final class CharacterDeserializer extends PrimitiveOrWrapperDeserializer<Character> {
        public CharacterDeserializer(Class<Character> cls, Character ch) {
            super(cls, ch);
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize */
        public Character mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                int intValue = jsonParser.getIntValue();
                if (intValue >= 0 && intValue <= 65535) {
                    return Character.valueOf((char) intValue);
                }
            } else if (currentToken == JsonToken.VALUE_STRING) {
                String text = jsonParser.getText();
                if (text.length() == 1) {
                    return Character.valueOf(text.charAt(0));
                }
                if (text.length() == 0) {
                    return getEmptyValue();
                }
            }
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static final class DoubleDeserializer extends PrimitiveOrWrapperDeserializer<Double> {
        public DoubleDeserializer(Class<Double> cls, Double d) {
            super(cls, d);
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize */
        public Double mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return _parseDouble(jsonParser, deserializationContext);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdScalarDeserializer, com.amazon.org.codehaus.jackson.map.deser.std.StdDeserializer, com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserializeWithType */
        public Double mo4196deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
            return _parseDouble(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static final class FloatDeserializer extends PrimitiveOrWrapperDeserializer<Float> {
        public FloatDeserializer(Class<Float> cls, Float f) {
            super(cls, f);
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize */
        public Float mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return _parseFloat(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static final class IntegerDeserializer extends PrimitiveOrWrapperDeserializer<Integer> {
        public IntegerDeserializer(Class<Integer> cls, Integer num) {
            super(cls, num);
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize */
        public Integer mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return _parseInteger(jsonParser, deserializationContext);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdScalarDeserializer, com.amazon.org.codehaus.jackson.map.deser.std.StdDeserializer, com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserializeWithType */
        public Integer mo4196deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
            return _parseInteger(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static final class LongDeserializer extends PrimitiveOrWrapperDeserializer<Long> {
        public LongDeserializer(Class<Long> cls, Long l) {
            super(cls, l);
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize */
        public Long mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return _parseLong(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static final class NumberDeserializer extends StdScalarDeserializer<Number> {
        public NumberDeserializer() {
            super(Number.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdScalarDeserializer, com.amazon.org.codehaus.jackson.map.deser.std.StdDeserializer, com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserializeWithType */
        public Object mo4196deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
            int ordinal = jsonParser.getCurrentToken().ordinal();
            if (ordinal != 7 && ordinal != 8 && ordinal != 9) {
                return typeDeserializer.deserializeTypedFromScalar(jsonParser, deserializationContext);
            }
            return mo4206deserialize(jsonParser, deserializationContext);
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize */
        public Number mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                if (deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_INTEGER_FOR_INTS)) {
                    return jsonParser.getBigIntegerValue();
                }
                return jsonParser.getNumberValue();
            } else if (currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                if (deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return jsonParser.getDecimalValue();
                }
                return Double.valueOf(jsonParser.getDoubleValue());
            } else if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                try {
                    if (trim.indexOf(46) >= 0) {
                        if (deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                            return new BigDecimal(trim);
                        }
                        return new Double(trim);
                    } else if (deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_INTEGER_FOR_INTS)) {
                        return new BigInteger(trim);
                    } else {
                        long parseLong = Long.parseLong(trim);
                        if (parseLong <= 2147483647L && parseLong >= -2147483648L) {
                            return Integer.valueOf((int) parseLong);
                        }
                        return Long.valueOf(parseLong);
                    }
                } catch (IllegalArgumentException unused) {
                    throw deserializationContext.weirdStringException(this._valueClass, "not a valid number");
                }
            } else {
                throw deserializationContext.mappingException(this._valueClass, currentToken);
            }
        }
    }

    /* loaded from: classes13.dex */
    protected static abstract class PrimitiveOrWrapperDeserializer<T> extends StdScalarDeserializer<T> {
        final T _nullValue;

        protected PrimitiveOrWrapperDeserializer(Class<T> cls, T t) {
            super((Class<?>) cls);
            this._nullValue = t;
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        public final T getNullValue() {
            return this._nullValue;
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static final class ShortDeserializer extends PrimitiveOrWrapperDeserializer<Short> {
        public ShortDeserializer(Class<Short> cls, Short sh) {
            super(cls, sh);
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize  reason: collision with other method in class */
        public Short mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return _parseShort(jsonParser, deserializationContext);
        }
    }

    /* loaded from: classes13.dex */
    public static class SqlDateDeserializer extends StdScalarDeserializer<Date> {
        public SqlDateDeserializer() {
            super(Date.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize  reason: collision with other method in class */
        public Date mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            java.util.Date _parseDate = _parseDate(jsonParser, deserializationContext);
            if (_parseDate == null) {
                return null;
            }
            return new Date(_parseDate.getTime());
        }
    }

    /* loaded from: classes13.dex */
    public static class StackTraceElementDeserializer extends StdScalarDeserializer<StackTraceElement> {
        public StackTraceElementDeserializer() {
            super(StackTraceElement.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize  reason: collision with other method in class */
        public StackTraceElement mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.START_OBJECT) {
                String str = "";
                int i = -1;
                String str2 = str;
                String str3 = str2;
                while (true) {
                    JsonToken nextValue = jsonParser.nextValue();
                    if (nextValue != JsonToken.END_OBJECT) {
                        String currentName = jsonParser.getCurrentName();
                        if ("className".equals(currentName)) {
                            str = jsonParser.getText();
                        } else if ("fileName".equals(currentName)) {
                            str3 = jsonParser.getText();
                        } else if (StackTraceHelper.LINE_NUMBER_KEY.equals(currentName)) {
                            if (nextValue.isNumeric()) {
                                i = jsonParser.getIntValue();
                            } else {
                                throw JsonMappingException.from(jsonParser, "Non-numeric token (" + nextValue + ") for property 'lineNumber'");
                            }
                        } else if ("methodName".equals(currentName)) {
                            str2 = jsonParser.getText();
                        } else if (!"nativeMethod".equals(currentName)) {
                            handleUnknownProperty(jsonParser, deserializationContext, this._valueClass, currentName);
                        }
                    } else {
                        return new StackTraceElement(str, str2, str3, i);
                    }
                }
            } else {
                throw deserializationContext.mappingException(this._valueClass, currentToken);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StdDeserializer(Class<?> cls) {
        this._valueClass = cls;
    }

    protected static final double parseDouble(String str) throws NumberFormatException {
        if ("2.2250738585072012e-308".equals(str)) {
            return Double.MIN_NORMAL;
        }
        return Double.parseDouble(str);
    }

    protected final Boolean _parseBoolean(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_TRUE) {
            return Boolean.TRUE;
        }
        if (currentToken == JsonToken.VALUE_FALSE) {
            return Boolean.FALSE;
        }
        if (currentToken == JsonToken.VALUE_NUMBER_INT) {
            if (jsonParser.getNumberType() != JsonParser.NumberType.INT) {
                return Boolean.valueOf(_parseBooleanFromNumber(jsonParser, deserializationContext));
            }
            return jsonParser.getIntValue() == 0 ? Boolean.FALSE : Boolean.TRUE;
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return (Boolean) getNullValue();
        } else {
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                if ("true".equals(trim)) {
                    return Boolean.TRUE;
                }
                if (PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE.equals(trim)) {
                    return Boolean.FALSE;
                }
                if (trim.length() == 0) {
                    return (Boolean) getEmptyValue();
                }
                throw deserializationContext.weirdStringException(this._valueClass, "only \"true\" or \"false\" recognized");
            }
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    protected final boolean _parseBooleanFromNumber(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (jsonParser.getNumberType() == JsonParser.NumberType.LONG) {
            return (jsonParser.getLongValue() == 0 ? Boolean.FALSE : Boolean.TRUE).booleanValue();
        }
        String text = jsonParser.getText();
        if (!"0.0".equals(text) && !"0".equals(text)) {
            return Boolean.TRUE.booleanValue();
        }
        return Boolean.FALSE.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean _parseBooleanPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_TRUE) {
            return true;
        }
        if (currentToken == JsonToken.VALUE_FALSE || currentToken == JsonToken.VALUE_NULL) {
            return false;
        }
        if (currentToken == JsonToken.VALUE_NUMBER_INT) {
            if (jsonParser.getNumberType() != JsonParser.NumberType.INT) {
                return _parseBooleanFromNumber(jsonParser, deserializationContext);
            }
            return jsonParser.getIntValue() != 0;
        } else if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if ("true".equals(trim)) {
                return true;
            }
            if (!PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE.equals(trim) && trim.length() != 0) {
                throw deserializationContext.weirdStringException(this._valueClass, "only \"true\" or \"false\" recognized");
            }
            return Boolean.FALSE.booleanValue();
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    protected Byte _parseByte(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken != JsonToken.VALUE_NUMBER_INT && currentToken != JsonToken.VALUE_NUMBER_FLOAT) {
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                try {
                    if (trim.length() == 0) {
                        return (Byte) getEmptyValue();
                    }
                    int parseInt = NumberInput.parseInt(trim);
                    if (parseInt >= -128 && parseInt <= 255) {
                        return Byte.valueOf((byte) parseInt);
                    }
                    throw deserializationContext.weirdStringException(this._valueClass, "overflow, value can not be represented as 8-bit value");
                } catch (IllegalArgumentException unused) {
                    throw deserializationContext.weirdStringException(this._valueClass, "not a valid Byte value");
                }
            } else if (currentToken == JsonToken.VALUE_NULL) {
                return (Byte) getNullValue();
            } else {
                throw deserializationContext.mappingException(this._valueClass, currentToken);
            }
        }
        return Byte.valueOf(jsonParser.getByteValue());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public java.util.Date _parseDate(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT) {
            return new java.util.Date(jsonParser.getLongValue());
        }
        if (currentToken == JsonToken.VALUE_NULL) {
            return (java.util.Date) getNullValue();
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            try {
                String trim = jsonParser.getText().trim();
                if (trim.length() == 0) {
                    return (java.util.Date) getEmptyValue();
                }
                return deserializationContext.parseDate(trim);
            } catch (IllegalArgumentException e) {
                Class<?> cls = this._valueClass;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("not a valid representation (error: ");
                outline107.append(e.getMessage());
                outline107.append(")");
                throw deserializationContext.weirdStringException(cls, outline107.toString());
            }
        }
        throw deserializationContext.mappingException(this._valueClass, currentToken);
    }

    protected final Double _parseDouble(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken != JsonToken.VALUE_NUMBER_INT && currentToken != JsonToken.VALUE_NUMBER_FLOAT) {
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                if (trim.length() == 0) {
                    return (Double) getEmptyValue();
                }
                char charAt = trim.charAt(0);
                if (charAt != '-') {
                    if (charAt != 'I') {
                        if (charAt == 'N' && "NaN".equals(trim)) {
                            return Double.valueOf(Double.NaN);
                        }
                    } else if ("Infinity".equals(trim) || "INF".equals(trim)) {
                        return Double.valueOf(Double.POSITIVE_INFINITY);
                    }
                } else if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                    return Double.valueOf(Double.NEGATIVE_INFINITY);
                }
                try {
                    return Double.valueOf(parseDouble(trim));
                } catch (IllegalArgumentException unused) {
                    throw deserializationContext.weirdStringException(this._valueClass, "not a valid Double value");
                }
            } else if (currentToken == JsonToken.VALUE_NULL) {
                return (Double) getNullValue();
            } else {
                throw deserializationContext.mappingException(this._valueClass, currentToken);
            }
        }
        return Double.valueOf(jsonParser.getDoubleValue());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final double _parseDoublePrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken != JsonToken.VALUE_NUMBER_INT && currentToken != JsonToken.VALUE_NUMBER_FLOAT) {
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                if (trim.length() == 0) {
                    return FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
                }
                char charAt = trim.charAt(0);
                if (charAt != '-') {
                    if (charAt != 'I') {
                        if (charAt == 'N' && "NaN".equals(trim)) {
                            return Double.NaN;
                        }
                    } else if ("Infinity".equals(trim) || "INF".equals(trim)) {
                        return Double.POSITIVE_INFINITY;
                    }
                } else if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                    return Double.NEGATIVE_INFINITY;
                }
                try {
                    return parseDouble(trim);
                } catch (IllegalArgumentException unused) {
                    throw deserializationContext.weirdStringException(this._valueClass, "not a valid double value");
                }
            } else if (currentToken != JsonToken.VALUE_NULL) {
                throw deserializationContext.mappingException(this._valueClass, currentToken);
            } else {
                return FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            }
        }
        return jsonParser.getDoubleValue();
    }

    protected final Float _parseFloat(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken != JsonToken.VALUE_NUMBER_INT && currentToken != JsonToken.VALUE_NUMBER_FLOAT) {
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                if (trim.length() == 0) {
                    return (Float) getEmptyValue();
                }
                char charAt = trim.charAt(0);
                if (charAt != '-') {
                    if (charAt != 'I') {
                        if (charAt == 'N' && "NaN".equals(trim)) {
                            return Float.valueOf(Float.NaN);
                        }
                    } else if ("Infinity".equals(trim) || "INF".equals(trim)) {
                        return Float.valueOf(Float.POSITIVE_INFINITY);
                    }
                } else if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                    return Float.valueOf(Float.NEGATIVE_INFINITY);
                }
                try {
                    return Float.valueOf(Float.parseFloat(trim));
                } catch (IllegalArgumentException unused) {
                    throw deserializationContext.weirdStringException(this._valueClass, "not a valid Float value");
                }
            } else if (currentToken == JsonToken.VALUE_NULL) {
                return (Float) getNullValue();
            } else {
                throw deserializationContext.mappingException(this._valueClass, currentToken);
            }
        }
        return Float.valueOf(jsonParser.getFloatValue());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final float _parseFloatPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken != JsonToken.VALUE_NUMBER_INT && currentToken != JsonToken.VALUE_NUMBER_FLOAT) {
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                if (trim.length() == 0) {
                    return 0.0f;
                }
                char charAt = trim.charAt(0);
                if (charAt != '-') {
                    if (charAt != 'I') {
                        if (charAt == 'N' && "NaN".equals(trim)) {
                            return Float.NaN;
                        }
                    } else if ("Infinity".equals(trim) || "INF".equals(trim)) {
                        return Float.POSITIVE_INFINITY;
                    }
                } else if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                    return Float.NEGATIVE_INFINITY;
                }
                try {
                    return Float.parseFloat(trim);
                } catch (IllegalArgumentException unused) {
                    throw deserializationContext.weirdStringException(this._valueClass, "not a valid float value");
                }
            } else if (currentToken != JsonToken.VALUE_NULL) {
                throw deserializationContext.mappingException(this._valueClass, currentToken);
            } else {
                return 0.0f;
            }
        }
        return jsonParser.getFloatValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int _parseIntPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken != JsonToken.VALUE_NUMBER_INT && currentToken != JsonToken.VALUE_NUMBER_FLOAT) {
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                try {
                    int length = trim.length();
                    if (length <= 9) {
                        if (length != 0) {
                            return NumberInput.parseInt(trim);
                        }
                        return 0;
                    }
                    long parseLong = Long.parseLong(trim);
                    if (parseLong >= -2147483648L && parseLong <= 2147483647L) {
                        return (int) parseLong;
                    }
                    Class<?> cls = this._valueClass;
                    throw deserializationContext.weirdStringException(cls, "Overflow: numeric value (" + trim + ") out of range of int (-2147483648 - 2147483647)");
                } catch (IllegalArgumentException unused) {
                    throw deserializationContext.weirdStringException(this._valueClass, "not a valid int value");
                }
            } else if (currentToken != JsonToken.VALUE_NULL) {
                throw deserializationContext.mappingException(this._valueClass, currentToken);
            } else {
                return 0;
            }
        }
        return jsonParser.getIntValue();
    }

    protected final Integer _parseInteger(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken != JsonToken.VALUE_NUMBER_INT && currentToken != JsonToken.VALUE_NUMBER_FLOAT) {
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                try {
                    int length = trim.length();
                    if (length <= 9) {
                        if (length == 0) {
                            return (Integer) getEmptyValue();
                        }
                        return Integer.valueOf(NumberInput.parseInt(trim));
                    }
                    long parseLong = Long.parseLong(trim);
                    if (parseLong >= -2147483648L && parseLong <= 2147483647L) {
                        return Integer.valueOf((int) parseLong);
                    }
                    Class<?> cls = this._valueClass;
                    throw deserializationContext.weirdStringException(cls, "Overflow: numeric value (" + trim + ") out of range of Integer (-2147483648 - 2147483647)");
                } catch (IllegalArgumentException unused) {
                    throw deserializationContext.weirdStringException(this._valueClass, "not a valid Integer value");
                }
            } else if (currentToken == JsonToken.VALUE_NULL) {
                return (Integer) getNullValue();
            } else {
                throw deserializationContext.mappingException(this._valueClass, currentToken);
            }
        }
        return Integer.valueOf(jsonParser.getIntValue());
    }

    protected final Long _parseLong(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken != JsonToken.VALUE_NUMBER_INT && currentToken != JsonToken.VALUE_NUMBER_FLOAT) {
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                if (trim.length() == 0) {
                    return (Long) getEmptyValue();
                }
                try {
                    return Long.valueOf(NumberInput.parseLong(trim));
                } catch (IllegalArgumentException unused) {
                    throw deserializationContext.weirdStringException(this._valueClass, "not a valid Long value");
                }
            } else if (currentToken == JsonToken.VALUE_NULL) {
                return (Long) getNullValue();
            } else {
                throw deserializationContext.mappingException(this._valueClass, currentToken);
            }
        }
        return Long.valueOf(jsonParser.getLongValue());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long _parseLongPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken != JsonToken.VALUE_NUMBER_INT && currentToken != JsonToken.VALUE_NUMBER_FLOAT) {
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                if (trim.length() == 0) {
                    return 0L;
                }
                try {
                    return NumberInput.parseLong(trim);
                } catch (IllegalArgumentException unused) {
                    throw deserializationContext.weirdStringException(this._valueClass, "not a valid long value");
                }
            } else if (currentToken != JsonToken.VALUE_NULL) {
                throw deserializationContext.mappingException(this._valueClass, currentToken);
            } else {
                return 0L;
            }
        }
        return jsonParser.getLongValue();
    }

    protected Short _parseShort(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken != JsonToken.VALUE_NUMBER_INT && currentToken != JsonToken.VALUE_NUMBER_FLOAT) {
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                try {
                    if (trim.length() == 0) {
                        return (Short) getEmptyValue();
                    }
                    int parseInt = NumberInput.parseInt(trim);
                    if (parseInt >= -32768 && parseInt <= 32767) {
                        return Short.valueOf((short) parseInt);
                    }
                    throw deserializationContext.weirdStringException(this._valueClass, "overflow, value can not be represented as 16-bit value");
                } catch (IllegalArgumentException unused) {
                    throw deserializationContext.weirdStringException(this._valueClass, "not a valid Short value");
                }
            } else if (currentToken == JsonToken.VALUE_NULL) {
                return (Short) getNullValue();
            } else {
                throw deserializationContext.mappingException(this._valueClass, currentToken);
            }
        }
        return Short.valueOf(jsonParser.getShortValue());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final short _parseShortPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        int _parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
        if (_parseIntPrimitive < -32768 || _parseIntPrimitive > 32767) {
            throw deserializationContext.weirdStringException(this._valueClass, "overflow, value can not be represented as 16-bit value");
        }
        return (short) _parseIntPrimitive;
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
    /* renamed from: deserializeWithType */
    public Object mo4196deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonDeserializer<Object> findDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        return deserializerProvider.findValueDeserializer(deserializationConfig, javaType, beanProperty);
    }

    public Class<?> getValueClass() {
        return this._valueClass;
    }

    public JavaType getValueType() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleUnknownProperty(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws IOException, JsonProcessingException {
        if (obj == null) {
            obj = getValueClass();
        }
        if (deserializationContext.handleUnknownProperty(jsonParser, this, obj, str)) {
            return;
        }
        reportUnknownProperty(deserializationContext, obj, str);
        jsonParser.skipChildren();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isDefaultSerializer(JsonDeserializer<?> jsonDeserializer) {
        return (jsonDeserializer == null || jsonDeserializer.getClass().getAnnotation(JacksonStdImpl.class) == null) ? false : true;
    }

    protected void reportUnknownProperty(DeserializationContext deserializationContext, Object obj, String str) throws IOException, JsonProcessingException {
        if (!deserializationContext.isEnabled(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES)) {
            return;
        }
        throw deserializationContext.unknownFieldException(obj, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StdDeserializer(JavaType javaType) {
        this._valueClass = javaType == null ? null : javaType.getRawClass();
    }
}
