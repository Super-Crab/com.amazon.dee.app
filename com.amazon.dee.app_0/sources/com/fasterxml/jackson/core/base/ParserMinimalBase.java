package com.fasterxml.jackson.core.base;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.exc.InputCoercionException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* loaded from: classes2.dex */
public abstract class ParserMinimalBase extends JsonParser {
    protected static final char CHAR_NULL = 0;
    protected static final int INT_0 = 48;
    protected static final int INT_9 = 57;
    protected static final int INT_APOS = 39;
    protected static final int INT_ASTERISK = 42;
    protected static final int INT_BACKSLASH = 92;
    protected static final int INT_COLON = 58;
    protected static final int INT_COMMA = 44;
    protected static final int INT_CR = 13;
    protected static final int INT_E = 69;
    protected static final int INT_HASH = 35;
    protected static final int INT_LBRACKET = 91;
    protected static final int INT_LCURLY = 123;
    protected static final int INT_LF = 10;
    protected static final int INT_MINUS = 45;
    protected static final int INT_PERIOD = 46;
    protected static final int INT_PLUS = 43;
    protected static final int INT_QUOTE = 34;
    protected static final int INT_RBRACKET = 93;
    protected static final int INT_RCURLY = 125;
    protected static final int INT_SLASH = 47;
    protected static final int INT_SPACE = 32;
    protected static final int INT_TAB = 9;
    protected static final int INT_e = 101;
    protected static final int MAX_ERROR_TOKEN_LENGTH = 256;
    protected static final double MAX_INT_D = 2.147483647E9d;
    protected static final double MAX_LONG_D = 9.223372036854776E18d;
    protected static final double MIN_INT_D = -2.147483648E9d;
    protected static final double MIN_LONG_D = -9.223372036854776E18d;
    protected static final int NR_BIGDECIMAL = 16;
    protected static final int NR_BIGINT = 4;
    protected static final int NR_DOUBLE = 8;
    protected static final int NR_FLOAT = 32;
    protected static final int NR_INT = 1;
    protected static final int NR_LONG = 2;
    protected static final int NR_UNKNOWN = 0;
    protected JsonToken _currToken;
    protected JsonToken _lastClearedToken;
    protected static final byte[] NO_BYTES = new byte[0];
    protected static final int[] NO_INTS = new int[0];
    protected static final long MIN_INT_L = -2147483648L;
    protected static final BigInteger BI_MIN_INT = BigInteger.valueOf(MIN_INT_L);
    protected static final long MAX_INT_L = 2147483647L;
    protected static final BigInteger BI_MAX_INT = BigInteger.valueOf(MAX_INT_L);
    protected static final BigInteger BI_MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
    protected static final BigInteger BI_MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);
    protected static final BigDecimal BD_MIN_LONG = new BigDecimal(BI_MIN_LONG);
    protected static final BigDecimal BD_MAX_LONG = new BigDecimal(BI_MAX_LONG);
    protected static final BigDecimal BD_MIN_INT = new BigDecimal(BI_MIN_INT);
    protected static final BigDecimal BD_MAX_INT = new BigDecimal(BI_MAX_INT);

    protected ParserMinimalBase() {
    }

    @Deprecated
    protected static String _ascii(byte[] bArr) {
        try {
            return new String(bArr, "US-ASCII");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Deprecated
    protected static byte[] _asciiBytes(String str) {
        byte[] bArr = new byte[str.length()];
        int length = str.length();
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) str.charAt(i);
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final String _getCharDesc(int i) {
        char c = (char) i;
        if (Character.isISOControl(c)) {
            return GeneratedOutlineSupport1.outline52("(CTRL-CHAR, code ", i, ")");
        }
        if (i > 255) {
            StringBuilder sb = new StringBuilder();
            sb.append("'");
            sb.append(c);
            sb.append("' (code ");
            sb.append(i);
            sb.append(" / 0x");
            return GeneratedOutlineSupport1.outline33(i, sb, ")");
        }
        return "'" + c + "' (code " + i + ")";
    }

    protected final JsonParseException _constructError(String str, Throwable th) {
        return new JsonParseException(this, str, th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _decodeBase64(String str, ByteArrayBuilder byteArrayBuilder, Base64Variant base64Variant) throws IOException {
        try {
            base64Variant.decode(str, byteArrayBuilder);
        } catch (IllegalArgumentException e) {
            _reportError(e.getMessage());
        }
    }

    protected abstract void _handleEOF() throws JsonParseException;

    protected boolean _hasTextualNull(String str) {
        return "null".equals(str);
    }

    protected String _longIntegerDesc(String str) {
        int length = str.length();
        if (length < 1000) {
            return str;
        }
        if (str.startsWith(ProcessIdUtil.DEFAULT_PROCESSID)) {
            length--;
        }
        return String.format("[Integer with %d digits]", Integer.valueOf(length));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String _longNumberDesc(String str) {
        int length = str.length();
        if (length < 1000) {
            return str;
        }
        if (str.startsWith(ProcessIdUtil.DEFAULT_PROCESSID)) {
            length--;
        }
        return String.format("[number with %d characters]", Integer.valueOf(length));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void _reportError(String str) throws JsonParseException {
        throw _constructError(str);
    }

    protected void _reportInputCoercion(String str, JsonToken jsonToken, Class<?> cls) throws InputCoercionException {
        throw new InputCoercionException(this, str, jsonToken, cls);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _reportInvalidEOF() throws JsonParseException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(" in ");
        outline107.append(this._currToken);
        _reportInvalidEOF(outline107.toString(), this._currToken);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _reportInvalidEOFInValue(JsonToken jsonToken) throws JsonParseException {
        String str;
        if (jsonToken == JsonToken.VALUE_STRING) {
            str = " in a String value";
        } else {
            str = (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) ? " in a Number value" : " in a value";
        }
        _reportInvalidEOF(str, jsonToken);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _reportMissingRootWS(int i) throws JsonParseException {
        _reportUnexpectedChar(i, "Expected space separating root-level values");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _reportUnexpectedChar(int i, String str) throws JsonParseException {
        if (i < 0) {
            _reportInvalidEOF();
        }
        String format = String.format("Unexpected character (%s)", _getCharDesc(i));
        if (str != null) {
            format = GeneratedOutlineSupport1.outline75(format, RealTimeTextConstants.COLON_SPACE, str);
        }
        _reportError(format);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void _throwInternal() {
        VersionUtil.throwInternal();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _throwInvalidSpace(int i) throws JsonParseException {
        _reportError(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Illegal character ("), _getCharDesc((char) i), "): only regular white space (\\r, \\n, \\t) is allowed between tokens"));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void _wrapError(String str, Throwable th) throws JsonParseException {
        throw _constructError(str, th);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public void clearCurrentToken() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != null) {
            this._lastClearedToken = jsonToken;
            this._currToken = null;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public abstract void close() throws IOException;

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonToken currentToken() {
        return this._currToken;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int currentTokenId() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == null) {
            return 0;
        }
        return jsonToken.id();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract byte[] getBinaryValue(Base64Variant base64Variant) throws IOException;

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract String getCurrentName() throws IOException;

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonToken getCurrentToken() {
        return this._currToken;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    @Deprecated
    public int getCurrentTokenId() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == null) {
            return 0;
        }
        return jsonToken.id();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonToken getLastClearedToken() {
        return this._lastClearedToken;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    /* renamed from: getParsingContext */
    public abstract JsonStreamContext mo7000getParsingContext();

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract String getText() throws IOException;

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract char[] getTextCharacters() throws IOException;

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract int getTextLength() throws IOException;

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract int getTextOffset() throws IOException;

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean getValueAsBoolean(boolean z) throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != null) {
            switch (jsonToken.id()) {
                case 6:
                    String trim = getText().trim();
                    if ("true".equals(trim)) {
                        return true;
                    }
                    if (PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE.equals(trim) || _hasTextualNull(trim)) {
                        return false;
                    }
                    break;
                case 7:
                    return getIntValue() != 0;
                case 9:
                    return true;
                case 10:
                case 11:
                    return false;
                case 12:
                    Object embeddedObject = getEmbeddedObject();
                    if (embeddedObject instanceof Boolean) {
                        return ((Boolean) embeddedObject).booleanValue();
                    }
                    break;
            }
        }
        return z;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public double getValueAsDouble(double d) throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != null) {
            switch (jsonToken.id()) {
                case 6:
                    String text = getText();
                    return _hasTextualNull(text) ? FrostVideoEffectController.VIDEO_STRENGTH_CLEAR : NumberInput.parseAsDouble(text, d);
                case 7:
                case 8:
                    return getDoubleValue();
                case 9:
                    return 1.0d;
                case 10:
                case 11:
                    return FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
                case 12:
                    Object embeddedObject = getEmbeddedObject();
                    return embeddedObject instanceof Number ? ((Number) embeddedObject).doubleValue() : d;
                default:
                    return d;
            }
        }
        return d;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt() throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.VALUE_NUMBER_INT && jsonToken != JsonToken.VALUE_NUMBER_FLOAT) {
            return getValueAsInt(0);
        }
        return getIntValue();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public long getValueAsLong() throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.VALUE_NUMBER_INT && jsonToken != JsonToken.VALUE_NUMBER_FLOAT) {
            return getValueAsLong(0L);
        }
        return getLongValue();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String getValueAsString() throws IOException {
        return getValueAsString(null);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean hasCurrentToken() {
        return this._currToken != null;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract boolean hasTextCharacters();

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean hasToken(JsonToken jsonToken) {
        return this._currToken == jsonToken;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean hasTokenId(int i) {
        JsonToken jsonToken = this._currToken;
        return jsonToken == null ? i == 0 : jsonToken.id() == i;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract boolean isClosed();

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean isExpectedNumberIntToken() {
        return this._currToken == JsonToken.VALUE_NUMBER_INT;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean isExpectedStartArrayToken() {
        return this._currToken == JsonToken.START_ARRAY;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean isExpectedStartObjectToken() {
        return this._currToken == JsonToken.START_OBJECT;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract JsonToken nextToken() throws IOException;

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonToken nextValue() throws IOException {
        JsonToken nextToken = nextToken();
        return nextToken == JsonToken.FIELD_NAME ? nextToken() : nextToken;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract void overrideCurrentName(String str);

    /* JADX INFO: Access modifiers changed from: protected */
    public void reportInvalidNumber(String str) throws JsonParseException {
        _reportError(GeneratedOutlineSupport1.outline72("Invalid numeric value: ", str));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reportOverflowInt() throws IOException {
        reportOverflowInt(getText());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reportOverflowLong() throws IOException {
        reportOverflowLong(getText());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reportUnexpectedNumberChar(int i, String str) throws JsonParseException {
        String format = String.format("Unexpected character (%s) in numeric value", _getCharDesc(i));
        if (str != null) {
            format = GeneratedOutlineSupport1.outline75(format, RealTimeTextConstants.COLON_SPACE, str);
        }
        _reportError(format);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonParser skipChildren() throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.START_OBJECT || jsonToken == JsonToken.START_ARRAY) {
            int i = 1;
            while (true) {
                JsonToken nextToken = nextToken();
                if (nextToken == null) {
                    _handleEOF();
                    return this;
                } else if (nextToken.isStructStart()) {
                    i++;
                } else if (nextToken.isStructEnd()) {
                    i--;
                    if (i == 0) {
                        return this;
                    }
                } else if (nextToken == JsonToken.NOT_AVAILABLE) {
                    _reportError("Not enough content available for `skipChildren()`: non-blocking parser? (%s)", getClass().getName());
                }
            }
        } else {
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ParserMinimalBase(int i) {
        super(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void _reportError(String str, Object obj) throws JsonParseException {
        throw _constructError(String.format(str, obj));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _reportInvalidEOF(String str, JsonToken jsonToken) throws JsonParseException {
        throw new JsonEOFException(this, jsonToken, GeneratedOutlineSupport1.outline72("Unexpected end-of-input", str));
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String getValueAsString(String str) throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            return getText();
        }
        if (jsonToken == JsonToken.FIELD_NAME) {
            return getCurrentName();
        }
        return (jsonToken == null || jsonToken == JsonToken.VALUE_NULL || !jsonToken.isScalarValue()) ? str : getText();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reportOverflowInt(String str) throws IOException {
        reportOverflowInt(str, currentToken());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reportOverflowLong(String str) throws IOException {
        reportOverflowLong(str, currentToken());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void _reportError(String str, Object obj, Object obj2) throws JsonParseException {
        throw _constructError(String.format(str, obj, obj2));
    }

    @Deprecated
    protected void _reportInvalidEOF(String str) throws JsonParseException {
        throw new JsonEOFException(this, null, GeneratedOutlineSupport1.outline72("Unexpected end-of-input", str));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reportOverflowInt(String str, JsonToken jsonToken) throws IOException {
        _reportInputCoercion(String.format("Numeric value (%s) out of range of int (%d - %s)", _longIntegerDesc(str), Integer.MIN_VALUE, Integer.MAX_VALUE), jsonToken, Integer.TYPE);
    }

    protected void reportOverflowLong(String str, JsonToken jsonToken) throws IOException {
        _reportInputCoercion(String.format("Numeric value (%s) out of range of long (%d - %s)", _longIntegerDesc(str), Long.MIN_VALUE, Long.MAX_VALUE), jsonToken, Long.TYPE);
    }

    @Deprecated
    protected void _reportInvalidEOFInValue() throws JsonParseException {
        _reportInvalidEOF(" in a value");
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt(int i) throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return getIntValue();
        }
        if (jsonToken == null) {
            return i;
        }
        int id = jsonToken.id();
        if (id != 6) {
            switch (id) {
                case 9:
                    return 1;
                case 10:
                case 11:
                    return 0;
                case 12:
                    Object embeddedObject = getEmbeddedObject();
                    return embeddedObject instanceof Number ? ((Number) embeddedObject).intValue() : i;
                default:
                    return i;
            }
        }
        String text = getText();
        if (!_hasTextualNull(text)) {
            return NumberInput.parseAsInt(text, i);
        }
        return 0;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public long getValueAsLong(long j) throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return getLongValue();
        }
        if (jsonToken == null) {
            return j;
        }
        int id = jsonToken.id();
        if (id != 6) {
            switch (id) {
                case 9:
                    return 1L;
                case 10:
                case 11:
                    return 0L;
                case 12:
                    Object embeddedObject = getEmbeddedObject();
                    return embeddedObject instanceof Number ? ((Number) embeddedObject).longValue() : j;
                default:
                    return j;
            }
        }
        String text = getText();
        if (!_hasTextualNull(text)) {
            return NumberInput.parseAsLong(text, j);
        }
        return 0L;
    }
}
