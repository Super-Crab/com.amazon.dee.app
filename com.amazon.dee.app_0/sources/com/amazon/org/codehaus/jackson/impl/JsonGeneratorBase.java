package com.amazon.org.codehaus.jackson.impl;

import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.ObjectCodec;
import com.amazon.org.codehaus.jackson.PrettyPrinter;
import com.amazon.org.codehaus.jackson.Version;
import com.amazon.org.codehaus.jackson.util.VersionUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes13.dex */
public abstract class JsonGeneratorBase extends JsonGenerator {
    protected boolean _closed;
    protected int _features;
    protected ObjectCodec _objectCodec;
    protected JsonWriteContext _writeContext = JsonWriteContext.createRootContext();
    protected boolean _cfgNumbersAsStrings = isEnabled(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS);

    /* renamed from: com.amazon.org.codehaus.jackson.impl.JsonGeneratorBase$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$org$codehaus$jackson$JsonParser$NumberType;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];

        static {
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.START_OBJECT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.END_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.START_ARRAY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.END_ARRAY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NULL.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            $SwitchMap$com$amazon$org$codehaus$jackson$JsonParser$NumberType = new int[JsonParser.NumberType.values().length];
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonParser$NumberType[JsonParser.NumberType.INT.ordinal()] = 1;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonParser$NumberType[JsonParser.NumberType.BIG_INTEGER.ordinal()] = 2;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonParser$NumberType[JsonParser.NumberType.BIG_DECIMAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonParser$NumberType[JsonParser.NumberType.FLOAT.ordinal()] = 4;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonGeneratorBase(int i, ObjectCodec objectCodec) {
        this._features = i;
        this._objectCodec = objectCodec;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _cantHappen() {
        throw new RuntimeException("Internal error: should never end up through this code path");
    }

    protected abstract void _releaseBuffers();

    /* JADX INFO: Access modifiers changed from: protected */
    public void _reportError(String str) throws JsonGenerationException {
        throw new JsonGenerationException(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _reportUnsupportedOperation() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Operation not supported by generator of type ");
        outline107.append(getClass().getName());
        throw new UnsupportedOperationException(outline107.toString());
    }

    protected final void _throwInternal() {
        throw new RuntimeException("Internal error: this code path should never get executed");
    }

    protected abstract void _verifyValueWrite(String str) throws IOException, JsonGenerationException;

    @Deprecated
    protected void _writeEndArray() throws IOException, JsonGenerationException {
    }

    @Deprecated
    protected void _writeEndObject() throws IOException, JsonGenerationException {
    }

    protected void _writeSimpleObject(Object obj) throws IOException, JsonGenerationException {
        if (obj == null) {
            writeNull();
        } else if (obj instanceof String) {
            writeString((String) obj);
        } else {
            if (obj instanceof Number) {
                Number number = (Number) obj;
                if (number instanceof Integer) {
                    writeNumber(number.intValue());
                    return;
                } else if (number instanceof Long) {
                    writeNumber(number.longValue());
                    return;
                } else if (number instanceof Double) {
                    writeNumber(number.doubleValue());
                    return;
                } else if (number instanceof Float) {
                    writeNumber(number.floatValue());
                    return;
                } else if (number instanceof Short) {
                    writeNumber((int) number.shortValue());
                    return;
                } else if (number instanceof Byte) {
                    writeNumber((int) number.byteValue());
                    return;
                } else if (number instanceof BigInteger) {
                    writeNumber((BigInteger) number);
                    return;
                } else if (number instanceof BigDecimal) {
                    writeNumber((BigDecimal) number);
                    return;
                } else if (number instanceof AtomicInteger) {
                    writeNumber(((AtomicInteger) number).get());
                    return;
                } else if (number instanceof AtomicLong) {
                    writeNumber(((AtomicLong) number).get());
                    return;
                }
            } else if (obj instanceof byte[]) {
                writeBinary((byte[]) obj);
                return;
            } else if (obj instanceof Boolean) {
                writeBoolean(((Boolean) obj).booleanValue());
                return;
            } else if (obj instanceof AtomicBoolean) {
                writeBoolean(((AtomicBoolean) obj).get());
                return;
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline46(obj, GeneratedOutlineSupport1.outline107("No ObjectCodec defined for the generator, can only serialize simple wrapper types (type passed "), ")"));
        }
    }

    @Deprecated
    protected void _writeStartArray() throws IOException, JsonGenerationException {
    }

    @Deprecated
    protected void _writeStartObject() throws IOException, JsonGenerationException {
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this._closed = true;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public final void copyCurrentEvent(JsonParser jsonParser) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == null) {
            _reportError("No current event to copy");
        }
        switch (currentToken.ordinal()) {
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
                _cantHappen();
                return;
        }
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public final void copyCurrentStructure(JsonParser jsonParser) throws IOException, JsonProcessingException {
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
        this._features &= ~feature.getMask();
        if (feature == JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS) {
            this._cfgNumbersAsStrings = false;
        } else if (feature == JsonGenerator.Feature.ESCAPE_NON_ASCII) {
            setHighestNonEscapedChar(0);
        }
        return this;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public JsonGenerator enable(JsonGenerator.Feature feature) {
        this._features |= feature.getMask();
        if (feature == JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS) {
            this._cfgNumbersAsStrings = true;
        } else if (feature == JsonGenerator.Feature.ESCAPE_NON_ASCII) {
            setHighestNonEscapedChar(127);
        }
        return this;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public abstract void flush() throws IOException;

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public final ObjectCodec getCodec() {
        return this._objectCodec;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public boolean isClosed() {
        return this._closed;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public final boolean isEnabled(JsonGenerator.Feature feature) {
        return (feature.getMask() & this._features) != 0;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public JsonGenerator setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
        return this;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public JsonGenerator useDefaultPrettyPrinter() {
        return setPrettyPrinter(new com.amazon.org.codehaus.jackson.util.DefaultPrettyPrinter());
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator, com.amazon.org.codehaus.jackson.Versioned
    public Version version() {
        return VersionUtil.versionFor(getClass());
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeEndArray() throws IOException, JsonGenerationException {
        if (!this._writeContext.inArray()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Current context not an ARRAY but ");
            outline107.append(this._writeContext.getTypeDesc());
            _reportError(outline107.toString());
        }
        PrettyPrinter prettyPrinter = this._cfgPrettyPrinter;
        if (prettyPrinter != null) {
            prettyPrinter.writeEndArray(this, this._writeContext.getEntryCount());
        } else {
            _writeEndArray();
        }
        this._writeContext = this._writeContext.mo4257getParent();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeEndObject() throws IOException, JsonGenerationException {
        if (!this._writeContext.inObject()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Current context not an object but ");
            outline107.append(this._writeContext.getTypeDesc());
            _reportError(outline107.toString());
        }
        this._writeContext = this._writeContext.mo4257getParent();
        PrettyPrinter prettyPrinter = this._cfgPrettyPrinter;
        if (prettyPrinter != null) {
            prettyPrinter.writeEndObject(this, this._writeContext.getEntryCount());
        } else {
            _writeEndObject();
        }
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeObject(Object obj) throws IOException, JsonProcessingException {
        if (obj == null) {
            writeNull();
            return;
        }
        ObjectCodec objectCodec = this._objectCodec;
        if (objectCodec != null) {
            objectCodec.writeValue(this, obj);
        } else {
            _writeSimpleObject(obj);
        }
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeRawValue(String str) throws IOException, JsonGenerationException {
        _verifyValueWrite("write raw value");
        writeRaw(str);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeStartArray() throws IOException, JsonGenerationException {
        _verifyValueWrite("start an array");
        this._writeContext = this._writeContext.createChildArrayContext();
        PrettyPrinter prettyPrinter = this._cfgPrettyPrinter;
        if (prettyPrinter != null) {
            prettyPrinter.writeStartArray(this);
        } else {
            _writeStartArray();
        }
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeStartObject() throws IOException, JsonGenerationException {
        _verifyValueWrite("start an object");
        this._writeContext = this._writeContext.createChildObjectContext();
        PrettyPrinter prettyPrinter = this._cfgPrettyPrinter;
        if (prettyPrinter != null) {
            prettyPrinter.writeStartObject(this);
        } else {
            _writeStartObject();
        }
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeTree(JsonNode jsonNode) throws IOException, JsonProcessingException {
        if (jsonNode == null) {
            writeNull();
            return;
        }
        ObjectCodec objectCodec = this._objectCodec;
        if (objectCodec != null) {
            objectCodec.writeTree(this, jsonNode);
            return;
        }
        throw new IllegalStateException("No ObjectCodec defined for the generator, can not serialize JsonNode-based trees");
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    /* renamed from: getOutputContext  reason: collision with other method in class */
    public final JsonWriteContext mo4261getOutputContext() {
        return this._writeContext;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeRawValue(String str, int i, int i2) throws IOException, JsonGenerationException {
        _verifyValueWrite("write raw value");
        writeRaw(str, i, i2);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeRawValue(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        _verifyValueWrite("write raw value");
        writeRaw(cArr, i, i2);
    }
}
