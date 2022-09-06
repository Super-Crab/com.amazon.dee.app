package org.codehaus.jackson;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import org.codehaus.jackson.type.TypeReference;
/* loaded from: classes5.dex */
public abstract class JsonParser implements Closeable, Versioned {
    private static final int MAX_BYTE_I = 255;
    private static final int MAX_SHORT_I = 32767;
    private static final int MIN_BYTE_I = -128;
    private static final int MIN_SHORT_I = -32768;
    protected JsonToken _currToken;
    protected int _features;
    protected JsonToken _lastClearedToken;

    /* renamed from: org.codehaus.jackson.JsonParser$1  reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];

        static {
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes5.dex */
    public enum Feature {
        AUTO_CLOSE_SOURCE(true),
        ALLOW_COMMENTS(false),
        ALLOW_UNQUOTED_FIELD_NAMES(false),
        ALLOW_SINGLE_QUOTES(false),
        ALLOW_UNQUOTED_CONTROL_CHARS(false),
        ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER(false),
        ALLOW_NUMERIC_LEADING_ZEROS(false),
        ALLOW_NON_NUMERIC_NUMBERS(false),
        INTERN_FIELD_NAMES(true),
        CANONICALIZE_FIELD_NAMES(true);
        
        final boolean _defaultState;

        Feature(boolean z) {
            this._defaultState = z;
        }

        public static int collectDefaults() {
            Feature[] values;
            int i = 0;
            for (Feature feature : values()) {
                if (feature.enabledByDefault()) {
                    i |= feature.getMask();
                }
            }
            return i;
        }

        public boolean enabledByDefault() {
            return this._defaultState;
        }

        public boolean enabledIn(int i) {
            return (i & getMask()) != 0;
        }

        public int getMask() {
            return 1 << ordinal();
        }
    }

    /* loaded from: classes5.dex */
    public enum NumberType {
        INT,
        LONG,
        BIG_INTEGER,
        FLOAT,
        DOUBLE,
        BIG_DECIMAL
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonParser() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonParseException _constructError(String str) {
        return new JsonParseException(str, getCurrentLocation());
    }

    public boolean canUseSchema(FormatSchema formatSchema) {
        return false;
    }

    public void clearCurrentToken() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != null) {
            this._lastClearedToken = jsonToken;
            this._currToken = null;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close() throws IOException;

    public JsonParser configure(Feature feature, boolean z) {
        if (z) {
            enableFeature(feature);
        } else {
            disableFeature(feature);
        }
        return this;
    }

    public JsonParser disable(Feature feature) {
        this._features = (~feature.getMask()) & this._features;
        return this;
    }

    public void disableFeature(Feature feature) {
        disable(feature);
    }

    public JsonParser enable(Feature feature) {
        this._features = feature.getMask() | this._features;
        return this;
    }

    public void enableFeature(Feature feature) {
        enable(feature);
    }

    public abstract BigInteger getBigIntegerValue() throws IOException, JsonParseException;

    public byte[] getBinaryValue() throws IOException, JsonParseException {
        return getBinaryValue(Base64Variants.getDefaultVariant());
    }

    public abstract byte[] getBinaryValue(Base64Variant base64Variant) throws IOException, JsonParseException;

    public boolean getBooleanValue() throws IOException, JsonParseException {
        if (getCurrentToken() == JsonToken.VALUE_TRUE) {
            return true;
        }
        if (getCurrentToken() == JsonToken.VALUE_FALSE) {
            return false;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Current token (");
        outline107.append(this._currToken);
        outline107.append(") not of boolean type");
        throw new JsonParseException(outline107.toString(), getCurrentLocation());
    }

    public byte getByteValue() throws IOException, JsonParseException {
        int intValue = getIntValue();
        if (intValue < MIN_BYTE_I || intValue > 255) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Numeric value (");
            outline107.append(getText());
            outline107.append(") out of range of Java byte");
            throw _constructError(outline107.toString());
        }
        return (byte) intValue;
    }

    public abstract ObjectCodec getCodec();

    public abstract JsonLocation getCurrentLocation();

    public abstract String getCurrentName() throws IOException, JsonParseException;

    public JsonToken getCurrentToken() {
        return this._currToken;
    }

    public abstract BigDecimal getDecimalValue() throws IOException, JsonParseException;

    public abstract double getDoubleValue() throws IOException, JsonParseException;

    public Object getEmbeddedObject() throws IOException, JsonParseException {
        return null;
    }

    public abstract float getFloatValue() throws IOException, JsonParseException;

    public Object getInputSource() {
        return null;
    }

    public abstract int getIntValue() throws IOException, JsonParseException;

    public JsonToken getLastClearedToken() {
        return this._lastClearedToken;
    }

    public abstract long getLongValue() throws IOException, JsonParseException;

    public abstract NumberType getNumberType() throws IOException, JsonParseException;

    public abstract Number getNumberValue() throws IOException, JsonParseException;

    /* renamed from: getParsingContext */
    public abstract JsonStreamContext mo12869getParsingContext();

    public short getShortValue() throws IOException, JsonParseException {
        int intValue = getIntValue();
        if (intValue < MIN_SHORT_I || intValue > MAX_SHORT_I) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Numeric value (");
            outline107.append(getText());
            outline107.append(") out of range of Java short");
            throw _constructError(outline107.toString());
        }
        return (short) intValue;
    }

    public abstract String getText() throws IOException, JsonParseException;

    public abstract char[] getTextCharacters() throws IOException, JsonParseException;

    public abstract int getTextLength() throws IOException, JsonParseException;

    public abstract int getTextOffset() throws IOException, JsonParseException;

    public abstract JsonLocation getTokenLocation();

    public boolean getValueAsBoolean() throws IOException, JsonParseException {
        return getValueAsBoolean(false);
    }

    public boolean getValueAsBoolean(boolean z) throws IOException, JsonParseException {
        return z;
    }

    public double getValueAsDouble() throws IOException, JsonParseException {
        return getValueAsDouble(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
    }

    public double getValueAsDouble(double d) throws IOException, JsonParseException {
        return d;
    }

    public int getValueAsInt() throws IOException, JsonParseException {
        return getValueAsInt(0);
    }

    public int getValueAsInt(int i) throws IOException, JsonParseException {
        return i;
    }

    public long getValueAsLong() throws IOException, JsonParseException {
        return getValueAsLong(0L);
    }

    public long getValueAsLong(long j) throws IOException, JsonParseException {
        return j;
    }

    public boolean hasCurrentToken() {
        return this._currToken != null;
    }

    public boolean hasTextCharacters() {
        return false;
    }

    public abstract boolean isClosed();

    public boolean isEnabled(Feature feature) {
        return (feature.getMask() & this._features) != 0;
    }

    public boolean isExpectedStartArrayToken() {
        return getCurrentToken() == JsonToken.START_ARRAY;
    }

    public final boolean isFeatureEnabled(Feature feature) {
        return isEnabled(feature);
    }

    public Boolean nextBooleanValue() throws IOException, JsonParseException {
        int ordinal = nextToken().ordinal();
        if (ordinal != 10) {
            if (ordinal == 11) {
                return Boolean.FALSE;
            }
            return null;
        }
        return Boolean.TRUE;
    }

    public boolean nextFieldName(SerializableString serializableString) throws IOException, JsonParseException {
        return nextToken() == JsonToken.FIELD_NAME && serializableString.getValue().equals(getCurrentName());
    }

    public int nextIntValue(int i) throws IOException, JsonParseException {
        return nextToken() == JsonToken.VALUE_NUMBER_INT ? getIntValue() : i;
    }

    public long nextLongValue(long j) throws IOException, JsonParseException {
        return nextToken() == JsonToken.VALUE_NUMBER_INT ? getLongValue() : j;
    }

    public String nextTextValue() throws IOException, JsonParseException {
        if (nextToken() == JsonToken.VALUE_STRING) {
            return getText();
        }
        return null;
    }

    public abstract JsonToken nextToken() throws IOException, JsonParseException;

    public JsonToken nextValue() throws IOException, JsonParseException {
        JsonToken nextToken = nextToken();
        return nextToken == JsonToken.FIELD_NAME ? nextToken() : nextToken;
    }

    public <T> T readValueAs(Class<T> cls) throws IOException, JsonProcessingException {
        ObjectCodec codec = getCodec();
        if (codec != null) {
            return (T) codec.readValue(this, cls);
        }
        throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
    }

    public JsonNode readValueAsTree() throws IOException, JsonProcessingException {
        ObjectCodec codec = getCodec();
        if (codec != null) {
            return codec.readTree(this);
        }
        throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into JsonNode tree");
    }

    public <T> Iterator<T> readValuesAs(Class<T> cls) throws IOException, JsonProcessingException {
        ObjectCodec codec = getCodec();
        if (codec != null) {
            return codec.readValues(this, cls);
        }
        throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
    }

    public int releaseBuffered(OutputStream outputStream) throws IOException {
        return -1;
    }

    public int releaseBuffered(Writer writer) throws IOException {
        return -1;
    }

    public abstract void setCodec(ObjectCodec objectCodec);

    public void setFeature(Feature feature, boolean z) {
        configure(feature, z);
    }

    public void setSchema(FormatSchema formatSchema) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Parser of type ");
        outline107.append(getClass().getName());
        outline107.append(" does not support schema of type '");
        outline107.append(formatSchema.getSchemaType());
        outline107.append("'");
        throw new UnsupportedOperationException(outline107.toString());
    }

    public abstract JsonParser skipChildren() throws IOException, JsonParseException;

    @Override // org.codehaus.jackson.Versioned
    public Version version() {
        return Version.unknownVersion();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonParser(int i) {
        this._features = i;
    }

    public <T> T readValueAs(TypeReference<?> typeReference) throws IOException, JsonProcessingException {
        ObjectCodec codec = getCodec();
        if (codec != null) {
            return (T) codec.readValue(this, typeReference);
        }
        throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
    }

    public <T> Iterator<T> readValuesAs(TypeReference<?> typeReference) throws IOException, JsonProcessingException {
        ObjectCodec codec = getCodec();
        if (codec != null) {
            return codec.readValues(this, typeReference);
        }
        throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
    }
}
