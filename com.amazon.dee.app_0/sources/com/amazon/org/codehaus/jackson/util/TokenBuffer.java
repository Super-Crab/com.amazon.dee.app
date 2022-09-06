package com.amazon.org.codehaus.jackson.util;

import com.amazon.org.codehaus.jackson.Base64Variant;
import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonLocation;
import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.JsonParseException;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonStreamContext;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.ObjectCodec;
import com.amazon.org.codehaus.jackson.SerializableString;
import com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase;
import com.amazon.org.codehaus.jackson.impl.JsonReadContext;
import com.amazon.org.codehaus.jackson.impl.JsonWriteContext;
import com.amazon.org.codehaus.jackson.io.SerializedString;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes13.dex */
public class TokenBuffer extends JsonGenerator {
    protected static final int DEFAULT_PARSER_FEATURES = JsonParser.Feature.collectDefaults();
    protected int _appendOffset;
    protected boolean _closed;
    protected Segment _first;
    protected Segment _last;
    protected ObjectCodec _objectCodec;
    protected int _generatorFeatures = DEFAULT_PARSER_FEATURES;
    protected JsonWriteContext _writeContext = JsonWriteContext.createRootContext();

    /* renamed from: com.amazon.org.codehaus.jackson.util.TokenBuffer$1  reason: invalid class name */
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
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonParser$NumberType[JsonParser.NumberType.BIG_INTEGER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonParser$NumberType[JsonParser.NumberType.BIG_DECIMAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonParser$NumberType[JsonParser.NumberType.FLOAT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonParser$NumberType[JsonParser.NumberType.LONG.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.START_OBJECT.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.END_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.START_ARRAY.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.END_ARRAY.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 6;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 7;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 9;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 10;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NULL.ordinal()] = 11;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = 12;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes13.dex */
    public static final class Parser extends JsonParserMinimalBase {
        protected transient ByteArrayBuilder _byteBuilder;
        protected boolean _closed;
        protected ObjectCodec _codec;
        protected JsonLocation _location;
        protected JsonReadContext _parsingContext;
        protected Segment _segment;
        protected int _segmentPtr;

        public Parser(Segment segment, ObjectCodec objectCodec) {
            super(0);
            this._location = null;
            this._segment = segment;
            this._segmentPtr = -1;
            this._codec = objectCodec;
            this._parsingContext = JsonReadContext.createRootContext(-1, -1);
        }

        protected final void _checkIsNumber() throws JsonParseException {
            JsonToken jsonToken = this._currToken;
            if (jsonToken == null || !jsonToken.isNumeric()) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Current token (");
                outline107.append(this._currToken);
                outline107.append(") not numeric, can not use numeric value accessors");
                throw _constructError(outline107.toString());
            }
        }

        protected final Object _currentObject() {
            return this._segment.get(this._segmentPtr);
        }

        @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase
        protected void _handleEOF() throws JsonParseException {
            _throwInternal();
        }

        @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (!this._closed) {
                this._closed = true;
            }
        }

        @Override // com.amazon.org.codehaus.jackson.JsonParser
        public BigInteger getBigIntegerValue() throws IOException, JsonParseException {
            Number numberValue = getNumberValue();
            if (numberValue instanceof BigInteger) {
                return (BigInteger) numberValue;
            }
            if (getNumberType().ordinal() != 5) {
                return BigInteger.valueOf(numberValue.longValue());
            }
            return ((BigDecimal) numberValue).toBigInteger();
        }

        @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
        public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException, JsonParseException {
            if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                Object _currentObject = _currentObject();
                if (_currentObject instanceof byte[]) {
                    return (byte[]) _currentObject;
                }
            }
            if (this._currToken == JsonToken.VALUE_STRING) {
                String text = getText();
                if (text == null) {
                    return null;
                }
                ByteArrayBuilder byteArrayBuilder = this._byteBuilder;
                if (byteArrayBuilder == null) {
                    byteArrayBuilder = new ByteArrayBuilder(100);
                    this._byteBuilder = byteArrayBuilder;
                } else {
                    byteArrayBuilder.reset();
                }
                _decodeBase64(text, byteArrayBuilder, base64Variant);
                return byteArrayBuilder.toByteArray();
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Current token (");
            outline107.append(this._currToken);
            outline107.append(") not VALUE_STRING (or VALUE_EMBEDDED_OBJECT with byte[]), can not access as binary");
            throw _constructError(outline107.toString());
        }

        @Override // com.amazon.org.codehaus.jackson.JsonParser
        public ObjectCodec getCodec() {
            return this._codec;
        }

        @Override // com.amazon.org.codehaus.jackson.JsonParser
        public JsonLocation getCurrentLocation() {
            JsonLocation jsonLocation = this._location;
            return jsonLocation == null ? JsonLocation.NA : jsonLocation;
        }

        @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
        public String getCurrentName() {
            return this._parsingContext.getCurrentName();
        }

        @Override // com.amazon.org.codehaus.jackson.JsonParser
        public BigDecimal getDecimalValue() throws IOException, JsonParseException {
            Number numberValue = getNumberValue();
            if (numberValue instanceof BigDecimal) {
                return (BigDecimal) numberValue;
            }
            int ordinal = getNumberType().ordinal();
            if (ordinal == 0 || ordinal == 1) {
                return BigDecimal.valueOf(numberValue.longValue());
            }
            if (ordinal != 2) {
                return BigDecimal.valueOf(numberValue.doubleValue());
            }
            return new BigDecimal((BigInteger) numberValue);
        }

        @Override // com.amazon.org.codehaus.jackson.JsonParser
        public double getDoubleValue() throws IOException, JsonParseException {
            return getNumberValue().doubleValue();
        }

        @Override // com.amazon.org.codehaus.jackson.JsonParser
        public Object getEmbeddedObject() {
            if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                return _currentObject();
            }
            return null;
        }

        @Override // com.amazon.org.codehaus.jackson.JsonParser
        public float getFloatValue() throws IOException, JsonParseException {
            return getNumberValue().floatValue();
        }

        @Override // com.amazon.org.codehaus.jackson.JsonParser
        public int getIntValue() throws IOException, JsonParseException {
            if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
                return ((Number) _currentObject()).intValue();
            }
            return getNumberValue().intValue();
        }

        @Override // com.amazon.org.codehaus.jackson.JsonParser
        public long getLongValue() throws IOException, JsonParseException {
            return getNumberValue().longValue();
        }

        @Override // com.amazon.org.codehaus.jackson.JsonParser
        public JsonParser.NumberType getNumberType() throws IOException, JsonParseException {
            Number numberValue = getNumberValue();
            if (numberValue instanceof Integer) {
                return JsonParser.NumberType.INT;
            }
            if (numberValue instanceof Long) {
                return JsonParser.NumberType.LONG;
            }
            if (numberValue instanceof Double) {
                return JsonParser.NumberType.DOUBLE;
            }
            if (numberValue instanceof BigDecimal) {
                return JsonParser.NumberType.BIG_DECIMAL;
            }
            if (numberValue instanceof Float) {
                return JsonParser.NumberType.FLOAT;
            }
            if (!(numberValue instanceof BigInteger)) {
                return null;
            }
            return JsonParser.NumberType.BIG_INTEGER;
        }

        @Override // com.amazon.org.codehaus.jackson.JsonParser
        public final Number getNumberValue() throws IOException, JsonParseException {
            _checkIsNumber();
            return (Number) _currentObject();
        }

        @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
        /* renamed from: getParsingContext */
        public JsonStreamContext mo4092getParsingContext() {
            return this._parsingContext;
        }

        @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
        public String getText() {
            JsonToken jsonToken = this._currToken;
            if (jsonToken == JsonToken.VALUE_STRING || jsonToken == JsonToken.FIELD_NAME) {
                Object _currentObject = _currentObject();
                if (_currentObject instanceof String) {
                    return (String) _currentObject;
                }
                if (_currentObject != null) {
                    return _currentObject.toString();
                }
                return null;
            } else if (jsonToken == null) {
                return null;
            } else {
                int ordinal = jsonToken.ordinal();
                if (ordinal != 8 && ordinal != 9) {
                    return this._currToken.asString();
                }
                Object _currentObject2 = _currentObject();
                if (_currentObject2 != null) {
                    return _currentObject2.toString();
                }
                return null;
            }
        }

        @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
        public char[] getTextCharacters() {
            String text = getText();
            if (text == null) {
                return null;
            }
            return text.toCharArray();
        }

        @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
        public int getTextLength() {
            String text = getText();
            if (text == null) {
                return 0;
            }
            return text.length();
        }

        @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
        public int getTextOffset() {
            return 0;
        }

        @Override // com.amazon.org.codehaus.jackson.JsonParser
        public JsonLocation getTokenLocation() {
            return getCurrentLocation();
        }

        @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
        public boolean hasTextCharacters() {
            return false;
        }

        @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
        public boolean isClosed() {
            return this._closed;
        }

        @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
        public JsonToken nextToken() throws IOException, JsonParseException {
            Segment segment;
            if (this._closed || (segment = this._segment) == null) {
                return null;
            }
            int i = this._segmentPtr + 1;
            this._segmentPtr = i;
            if (i >= 16) {
                this._segmentPtr = 0;
                this._segment = segment.next();
                if (this._segment == null) {
                    return null;
                }
            }
            this._currToken = this._segment.type(this._segmentPtr);
            JsonToken jsonToken = this._currToken;
            if (jsonToken == JsonToken.FIELD_NAME) {
                Object _currentObject = _currentObject();
                this._parsingContext.setCurrentName(_currentObject instanceof String ? (String) _currentObject : _currentObject.toString());
            } else if (jsonToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(-1, -1);
            } else if (jsonToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(-1, -1);
            } else if (jsonToken == JsonToken.END_OBJECT || jsonToken == JsonToken.END_ARRAY) {
                this._parsingContext = this._parsingContext.mo4257getParent();
                if (this._parsingContext == null) {
                    this._parsingContext = JsonReadContext.createRootContext(-1, -1);
                }
            }
            return this._currToken;
        }

        public JsonToken peekNextToken() throws IOException, JsonParseException {
            if (this._closed) {
                return null;
            }
            Segment segment = this._segment;
            int i = this._segmentPtr + 1;
            if (i >= 16) {
                i = 0;
                segment = segment == null ? null : segment.next();
            }
            if (segment != null) {
                return segment.type(i);
            }
            return null;
        }

        @Override // com.amazon.org.codehaus.jackson.JsonParser
        public void setCodec(ObjectCodec objectCodec) {
            this._codec = objectCodec;
        }

        public void setLocation(JsonLocation jsonLocation) {
            this._location = jsonLocation;
        }
    }

    public TokenBuffer(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
        Segment segment = new Segment();
        this._last = segment;
        this._first = segment;
        this._appendOffset = 0;
    }

    protected final void _append(JsonToken jsonToken) {
        Segment append = this._last.append(this._appendOffset, jsonToken);
        if (append == null) {
            this._appendOffset++;
            return;
        }
        this._last = append;
        this._appendOffset = 1;
    }

    protected void _reportUnsupportedOperation() {
        throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
    }

    public JsonParser asParser() {
        return asParser(this._objectCodec);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this._closed = true;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void copyCurrentEvent(JsonParser jsonParser) throws IOException, JsonProcessingException {
        switch (jsonParser.getCurrentToken().ordinal()) {
            case 1:
                writeStartObject();
                return;
            case 2:
                writeEndObject();
                return;
            case 3:
                writeStartArray();
                return;
            case 4:
                writeEndArray();
                return;
            case 5:
                writeFieldName(jsonParser.getCurrentName());
                return;
            case 6:
                writeObject(jsonParser.getEmbeddedObject());
                return;
            case 7:
                if (jsonParser.hasTextCharacters()) {
                    writeString(jsonParser.getTextCharacters(), jsonParser.getTextOffset(), jsonParser.getTextLength());
                    return;
                } else {
                    writeString(jsonParser.getText());
                    return;
                }
            case 8:
                int ordinal = jsonParser.getNumberType().ordinal();
                if (ordinal == 0) {
                    writeNumber(jsonParser.getIntValue());
                    return;
                } else if (ordinal != 2) {
                    writeNumber(jsonParser.getLongValue());
                    return;
                } else {
                    writeNumber(jsonParser.getBigIntegerValue());
                    return;
                }
            case 9:
                int ordinal2 = jsonParser.getNumberType().ordinal();
                if (ordinal2 == 3) {
                    writeNumber(jsonParser.getFloatValue());
                    return;
                } else if (ordinal2 != 5) {
                    writeNumber(jsonParser.getDoubleValue());
                    return;
                } else {
                    writeNumber(jsonParser.getDecimalValue());
                    return;
                }
            case 10:
                writeBoolean(true);
                return;
            case 11:
                writeBoolean(false);
                return;
            case 12:
                writeNull();
                return;
            default:
                throw new RuntimeException("Internal error: should never end up through this code path");
        }
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void copyCurrentStructure(JsonParser jsonParser) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.FIELD_NAME) {
            writeFieldName(jsonParser.getCurrentName());
            currentToken = jsonParser.nextToken();
        }
        int ordinal = currentToken.ordinal();
        if (ordinal == 1) {
            writeStartObject();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                copyCurrentStructure(jsonParser);
            }
            writeEndObject();
        } else if (ordinal != 3) {
            copyCurrentEvent(jsonParser);
        } else {
            writeStartArray();
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                copyCurrentStructure(jsonParser);
            }
            writeEndArray();
        }
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public JsonGenerator disable(JsonGenerator.Feature feature) {
        this._generatorFeatures = (~feature.getMask()) & this._generatorFeatures;
        return this;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public JsonGenerator enable(JsonGenerator.Feature feature) {
        this._generatorFeatures = feature.getMask() | this._generatorFeatures;
        return this;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void flush() throws IOException {
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public boolean isClosed() {
        return this._closed;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public boolean isEnabled(JsonGenerator.Feature feature) {
        return (feature.getMask() & this._generatorFeatures) != 0;
    }

    public void serialize(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        Segment segment = this._first;
        int i = -1;
        while (true) {
            i++;
            if (i >= 16) {
                segment = segment.next();
                if (segment == null) {
                    return;
                }
                i = 0;
            }
            JsonToken type = segment.type(i);
            if (type == null) {
                return;
            }
            switch (type.ordinal()) {
                case 1:
                    jsonGenerator.writeStartObject();
                    break;
                case 2:
                    jsonGenerator.writeEndObject();
                    break;
                case 3:
                    jsonGenerator.writeStartArray();
                    break;
                case 4:
                    jsonGenerator.writeEndArray();
                    break;
                case 5:
                    Object obj = segment.get(i);
                    if (obj instanceof SerializableString) {
                        jsonGenerator.writeFieldName((SerializableString) obj);
                        break;
                    } else {
                        jsonGenerator.writeFieldName((String) obj);
                        break;
                    }
                case 6:
                    jsonGenerator.writeObject(segment.get(i));
                    break;
                case 7:
                    Object obj2 = segment.get(i);
                    if (obj2 instanceof SerializableString) {
                        jsonGenerator.writeString((SerializableString) obj2);
                        break;
                    } else {
                        jsonGenerator.writeString((String) obj2);
                        break;
                    }
                case 8:
                    Number number = (Number) segment.get(i);
                    if (number instanceof BigInteger) {
                        jsonGenerator.writeNumber((BigInteger) number);
                        break;
                    } else if (number instanceof Long) {
                        jsonGenerator.writeNumber(number.longValue());
                        break;
                    } else {
                        jsonGenerator.writeNumber(number.intValue());
                        break;
                    }
                case 9:
                    Object obj3 = segment.get(i);
                    if (obj3 instanceof BigDecimal) {
                        jsonGenerator.writeNumber((BigDecimal) obj3);
                        break;
                    } else if (obj3 instanceof Float) {
                        jsonGenerator.writeNumber(((Float) obj3).floatValue());
                        break;
                    } else if (obj3 instanceof Double) {
                        jsonGenerator.writeNumber(((Double) obj3).doubleValue());
                        break;
                    } else if (obj3 == null) {
                        jsonGenerator.writeNull();
                        break;
                    } else if (obj3 instanceof String) {
                        jsonGenerator.writeNumber((String) obj3);
                        break;
                    } else {
                        throw new JsonGenerationException(GeneratedOutlineSupport1.outline46(obj3, GeneratedOutlineSupport1.outline107("Unrecognized value type for VALUE_NUMBER_FLOAT: "), ", can not serialize"));
                    }
                case 10:
                    jsonGenerator.writeBoolean(true);
                    break;
                case 11:
                    jsonGenerator.writeBoolean(false);
                    break;
                case 12:
                    jsonGenerator.writeNull();
                    break;
                default:
                    throw new RuntimeException("Internal error: should never end up through this code path");
            }
        }
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public JsonGenerator setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
        return this;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[TokenBuffer: ");
        JsonParser asParser = asParser();
        int i = 0;
        while (true) {
            try {
                JsonToken nextToken = asParser.nextToken();
                if (nextToken == null) {
                    break;
                }
                if (i < 100) {
                    if (i > 0) {
                        outline107.append(", ");
                    }
                    outline107.append(nextToken.toString());
                }
                i++;
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
        if (i >= 100) {
            outline107.append(" ... (truncated ");
            outline107.append(i - 100);
            outline107.append(" entries)");
        }
        outline107.append(JsonReaderKt.END_LIST);
        return outline107.toString();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public JsonGenerator useDefaultPrettyPrinter() {
        return this;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        writeObject(bArr2);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeBoolean(boolean z) throws IOException, JsonGenerationException {
        _append(z ? JsonToken.VALUE_TRUE : JsonToken.VALUE_FALSE);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public final void writeEndArray() throws IOException, JsonGenerationException {
        _append(JsonToken.END_ARRAY);
        JsonWriteContext mo4257getParent = this._writeContext.mo4257getParent();
        if (mo4257getParent != null) {
            this._writeContext = mo4257getParent;
        }
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public final void writeEndObject() throws IOException, JsonGenerationException {
        _append(JsonToken.END_OBJECT);
        JsonWriteContext mo4257getParent = this._writeContext.mo4257getParent();
        if (mo4257getParent != null) {
            this._writeContext = mo4257getParent;
        }
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public final void writeFieldName(String str) throws IOException, JsonGenerationException {
        _append(JsonToken.FIELD_NAME, str);
        this._writeContext.writeFieldName(str);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeNull() throws IOException, JsonGenerationException {
        _append(JsonToken.VALUE_NULL);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeNumber(int i) throws IOException, JsonGenerationException {
        _append(JsonToken.VALUE_NUMBER_INT, Integer.valueOf(i));
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeObject(Object obj) throws IOException, JsonProcessingException {
        _append(JsonToken.VALUE_EMBEDDED_OBJECT, obj);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeRaw(String str) throws IOException, JsonGenerationException {
        _reportUnsupportedOperation();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeRawUTF8String(byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        _reportUnsupportedOperation();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeRawValue(String str) throws IOException, JsonGenerationException {
        _reportUnsupportedOperation();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public final void writeStartArray() throws IOException, JsonGenerationException {
        _append(JsonToken.START_ARRAY);
        this._writeContext = this._writeContext.createChildArrayContext();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public final void writeStartObject() throws IOException, JsonGenerationException {
        _append(JsonToken.START_OBJECT);
        this._writeContext = this._writeContext.createChildObjectContext();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeString(String str) throws IOException, JsonGenerationException {
        if (str == null) {
            writeNull();
        } else {
            _append(JsonToken.VALUE_STRING, str);
        }
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeTree(JsonNode jsonNode) throws IOException, JsonProcessingException {
        _append(JsonToken.VALUE_EMBEDDED_OBJECT, jsonNode);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeUTF8String(byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        _reportUnsupportedOperation();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes13.dex */
    public static final class Segment {
        public static final int TOKENS_PER_SEGMENT = 16;
        private static final JsonToken[] TOKEN_TYPES_BY_INDEX = new JsonToken[16];
        protected Segment _next;
        protected long _tokenTypes;
        protected final Object[] _tokens = new Object[16];

        static {
            JsonToken[] values = JsonToken.values();
            System.arraycopy(values, 1, TOKEN_TYPES_BY_INDEX, 1, Math.min(15, values.length - 1));
        }

        public Segment append(int i, JsonToken jsonToken) {
            if (i < 16) {
                set(i, jsonToken);
                return null;
            }
            this._next = new Segment();
            this._next.set(0, jsonToken);
            return this._next;
        }

        public Object get(int i) {
            return this._tokens[i];
        }

        public Segment next() {
            return this._next;
        }

        public void set(int i, JsonToken jsonToken) {
            long ordinal = jsonToken.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            this._tokenTypes |= ordinal;
        }

        public JsonToken type(int i) {
            long j = this._tokenTypes;
            if (i > 0) {
                j >>= i << 2;
            }
            return TOKEN_TYPES_BY_INDEX[((int) j) & 15];
        }

        public void set(int i, JsonToken jsonToken, Object obj) {
            this._tokens[i] = obj;
            long ordinal = jsonToken.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            this._tokenTypes |= ordinal;
        }

        public Segment append(int i, JsonToken jsonToken, Object obj) {
            if (i < 16) {
                set(i, jsonToken, obj);
                return null;
            }
            this._next = new Segment();
            this._next.set(0, jsonToken, obj);
            return this._next;
        }
    }

    public JsonParser asParser(ObjectCodec objectCodec) {
        return new Parser(this._first, objectCodec);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    /* renamed from: getOutputContext  reason: collision with other method in class */
    public final JsonWriteContext mo4261getOutputContext() {
        return this._writeContext;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeNumber(long j) throws IOException, JsonGenerationException {
        _append(JsonToken.VALUE_NUMBER_INT, Long.valueOf(j));
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeRaw(String str, int i, int i2) throws IOException, JsonGenerationException {
        _reportUnsupportedOperation();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeRawValue(String str, int i, int i2) throws IOException, JsonGenerationException {
        _reportUnsupportedOperation();
    }

    public JsonParser asParser(JsonParser jsonParser) {
        Parser parser = new Parser(this._first, jsonParser.getCodec());
        parser.setLocation(jsonParser.getTokenLocation());
        return parser;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeFieldName(SerializableString serializableString) throws IOException, JsonGenerationException {
        _append(JsonToken.FIELD_NAME, serializableString);
        this._writeContext.writeFieldName(serializableString.getValue());
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeNumber(double d) throws IOException, JsonGenerationException {
        _append(JsonToken.VALUE_NUMBER_FLOAT, Double.valueOf(d));
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeRaw(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        _reportUnsupportedOperation();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeRawValue(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        _reportUnsupportedOperation();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeString(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        writeString(new String(cArr, i, i2));
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeNumber(float f) throws IOException, JsonGenerationException {
        _append(JsonToken.VALUE_NUMBER_FLOAT, Float.valueOf(f));
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeRaw(char c) throws IOException, JsonGenerationException {
        _reportUnsupportedOperation();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeString(SerializableString serializableString) throws IOException, JsonGenerationException {
        if (serializableString == null) {
            writeNull();
        } else {
            _append(JsonToken.VALUE_STRING, serializableString);
        }
    }

    protected final void _append(JsonToken jsonToken, Object obj) {
        Segment append = this._last.append(this._appendOffset, jsonToken, obj);
        if (append == null) {
            this._appendOffset++;
            return;
        }
        this._last = append;
        this._appendOffset = 1;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeFieldName(SerializedString serializedString) throws IOException, JsonGenerationException {
        _append(JsonToken.FIELD_NAME, serializedString);
        this._writeContext.writeFieldName(serializedString.getValue());
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeNumber(BigDecimal bigDecimal) throws IOException, JsonGenerationException {
        if (bigDecimal == null) {
            writeNull();
        } else {
            _append(JsonToken.VALUE_NUMBER_FLOAT, bigDecimal);
        }
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeNumber(BigInteger bigInteger) throws IOException, JsonGenerationException {
        if (bigInteger == null) {
            writeNull();
        } else {
            _append(JsonToken.VALUE_NUMBER_INT, bigInteger);
        }
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeNumber(String str) throws IOException, JsonGenerationException {
        _append(JsonToken.VALUE_NUMBER_FLOAT, str);
    }
}
