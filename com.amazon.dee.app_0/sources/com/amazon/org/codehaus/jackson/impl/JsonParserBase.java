package com.amazon.org.codehaus.jackson.impl;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.org.codehaus.jackson.Base64Variant;
import com.amazon.org.codehaus.jackson.JsonLocation;
import com.amazon.org.codehaus.jackson.JsonParseException;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.Version;
import com.amazon.org.codehaus.jackson.io.IOContext;
import com.amazon.org.codehaus.jackson.io.NumberInput;
import com.amazon.org.codehaus.jackson.util.ByteArrayBuilder;
import com.amazon.org.codehaus.jackson.util.TextBuffer;
import com.amazon.org.codehaus.jackson.util.VersionUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
/* loaded from: classes13.dex */
public abstract class JsonParserBase extends JsonParserMinimalBase {
    protected static final char CHAR_NULL = 0;
    protected static final int INT_0 = 48;
    protected static final int INT_1 = 49;
    protected static final int INT_2 = 50;
    protected static final int INT_3 = 51;
    protected static final int INT_4 = 52;
    protected static final int INT_5 = 53;
    protected static final int INT_6 = 54;
    protected static final int INT_7 = 55;
    protected static final int INT_8 = 56;
    protected static final int INT_9 = 57;
    protected static final int INT_DECIMAL_POINT = 46;
    protected static final int INT_E = 69;
    protected static final int INT_MINUS = 45;
    protected static final int INT_PLUS = 43;
    protected static final int INT_e = 101;
    static final double MAX_INT_D = 2.147483647E9d;
    static final double MAX_LONG_D = 9.223372036854776E18d;
    static final double MIN_INT_D = -2.147483648E9d;
    static final double MIN_LONG_D = -9.223372036854776E18d;
    protected static final int NR_BIGDECIMAL = 16;
    protected static final int NR_BIGINT = 4;
    protected static final int NR_DOUBLE = 8;
    protected static final int NR_INT = 1;
    protected static final int NR_LONG = 2;
    protected static final int NR_UNKNOWN = 0;
    protected byte[] _binaryValue;
    protected boolean _closed;
    protected int _expLength;
    protected int _fractLength;
    protected int _intLength;
    protected final IOContext _ioContext;
    protected JsonToken _nextToken;
    protected BigDecimal _numberBigDecimal;
    protected BigInteger _numberBigInt;
    protected double _numberDouble;
    protected int _numberInt;
    protected long _numberLong;
    protected boolean _numberNegative;
    protected JsonReadContext _parsingContext;
    protected final TextBuffer _textBuffer;
    static final long MIN_INT_L = -2147483648L;
    static final BigInteger BI_MIN_INT = BigInteger.valueOf(MIN_INT_L);
    static final long MAX_INT_L = 2147483647L;
    static final BigInteger BI_MAX_INT = BigInteger.valueOf(MAX_INT_L);
    static final BigInteger BI_MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
    static final BigInteger BI_MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);
    static final BigDecimal BD_MIN_LONG = new BigDecimal(BI_MIN_LONG);
    static final BigDecimal BD_MAX_LONG = new BigDecimal(BI_MAX_LONG);
    static final BigDecimal BD_MIN_INT = new BigDecimal(BI_MIN_INT);
    static final BigDecimal BD_MAX_INT = new BigDecimal(BI_MAX_INT);
    protected int _inputPtr = 0;
    protected int _inputEnd = 0;
    protected long _currInputProcessed = 0;
    protected int _currInputRow = 1;
    protected int _currInputRowStart = 0;
    protected long _tokenInputTotal = 0;
    protected int _tokenInputRow = 1;
    protected int _tokenInputCol = 0;
    protected char[] _nameCopyBuffer = null;
    protected boolean _nameCopied = false;
    protected ByteArrayBuilder _byteArrayBuilder = null;
    protected int _numTypesValid = 0;

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonParserBase(IOContext iOContext, int i) {
        this._features = i;
        this._ioContext = iOContext;
        this._textBuffer = iOContext.constructTextBuffer();
        this._parsingContext = JsonReadContext.createRootContext();
    }

    private final void _parseSlowFloatValue(int i) throws IOException, JsonParseException {
        try {
            if (i == 16) {
                this._numberBigDecimal = this._textBuffer.contentsAsDecimal();
                this._numTypesValid = 16;
            } else {
                this._numberDouble = this._textBuffer.contentsAsDouble();
                this._numTypesValid = 8;
            }
        } catch (NumberFormatException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Malformed numeric value '");
            outline107.append(this._textBuffer.contentsAsString());
            outline107.append("'");
            _wrapError(outline107.toString(), e);
        }
    }

    private final void _parseSlowIntValue(int i, char[] cArr, int i2, int i3) throws IOException, JsonParseException {
        String contentsAsString = this._textBuffer.contentsAsString();
        try {
            if (NumberInput.inLongRange(cArr, i2, i3, this._numberNegative)) {
                this._numberLong = Long.parseLong(contentsAsString);
                this._numTypesValid = 2;
            } else {
                this._numberBigInt = new BigInteger(contentsAsString);
                this._numTypesValid = 4;
            }
        } catch (NumberFormatException e) {
            _wrapError(GeneratedOutlineSupport1.outline75("Malformed numeric value '", contentsAsString, "'"), e);
        }
    }

    protected abstract void _closeInput() throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public final int _decodeBase64Escape(Base64Variant base64Variant, int i, int i2) throws IOException, JsonParseException {
        if (i == 92) {
            char _decodeEscaped = _decodeEscaped();
            if (_decodeEscaped <= ' ' && i2 == 0) {
                return -1;
            }
            int decodeBase64Char = base64Variant.decodeBase64Char((int) _decodeEscaped);
            if (decodeBase64Char < 0) {
                throw reportInvalidBase64Char(base64Variant, _decodeEscaped, i2);
            }
            return decodeBase64Char;
        }
        throw reportInvalidBase64Char(base64Variant, i, i2);
    }

    protected char _decodeEscaped() throws IOException, JsonParseException {
        throw new UnsupportedOperationException();
    }

    protected abstract void _finishString() throws IOException, JsonParseException;

    public ByteArrayBuilder _getByteArrayBuilder() {
        ByteArrayBuilder byteArrayBuilder = this._byteArrayBuilder;
        if (byteArrayBuilder == null) {
            this._byteArrayBuilder = new ByteArrayBuilder();
        } else {
            byteArrayBuilder.reset();
        }
        return this._byteArrayBuilder;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase
    public void _handleEOF() throws JsonParseException {
        if (!this._parsingContext.inRoot()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(": expected close marker for ");
            outline107.append(this._parsingContext.getTypeDesc());
            outline107.append(" (from ");
            outline107.append(this._parsingContext.getStartLocation(this._ioContext.getSourceReference()));
            outline107.append(")");
            _reportInvalidEOF(outline107.toString());
        }
    }

    protected void _parseNumericValue(int i) throws IOException, JsonParseException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT) {
            char[] textBuffer = this._textBuffer.getTextBuffer();
            int textOffset = this._textBuffer.getTextOffset();
            int i2 = this._intLength;
            if (this._numberNegative) {
                textOffset++;
            }
            if (i2 <= 9) {
                int parseInt = NumberInput.parseInt(textBuffer, textOffset, i2);
                if (this._numberNegative) {
                    parseInt = -parseInt;
                }
                this._numberInt = parseInt;
                this._numTypesValid = 1;
            } else if (i2 <= 18) {
                long parseLong = NumberInput.parseLong(textBuffer, textOffset, i2);
                if (this._numberNegative) {
                    parseLong = -parseLong;
                }
                if (i2 == 10) {
                    if (this._numberNegative) {
                        if (parseLong >= MIN_INT_L) {
                            this._numberInt = (int) parseLong;
                            this._numTypesValid = 1;
                            return;
                        }
                    } else if (parseLong <= MAX_INT_L) {
                        this._numberInt = (int) parseLong;
                        this._numTypesValid = 1;
                        return;
                    }
                }
                this._numberLong = parseLong;
                this._numTypesValid = 2;
            } else {
                _parseSlowIntValue(i, textBuffer, textOffset, i2);
            }
        } else if (jsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
            _parseSlowFloatValue(i);
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Current token (");
            outline107.append(this._currToken);
            outline107.append(") not numeric, can not use numeric value accessors");
            _reportError(outline107.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _releaseBuffers() throws IOException {
        this._textBuffer.releaseBuffers();
        char[] cArr = this._nameCopyBuffer;
        if (cArr != null) {
            this._nameCopyBuffer = null;
            this._ioContext.releaseNameCopyBuffer(cArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _reportMismatchedEndMarker(int i, char c) throws JsonParseException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("");
        outline107.append(this._parsingContext.getStartLocation(this._ioContext.getSourceReference()));
        String sb = outline107.toString();
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Unexpected close marker '");
        outline1072.append((char) i);
        outline1072.append("': expected '");
        outline1072.append(c);
        outline1072.append("' (for ");
        outline1072.append(this._parsingContext.getTypeDesc());
        outline1072.append(" starting at ");
        outline1072.append(sb);
        outline1072.append(")");
        _reportError(outline1072.toString());
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this._closed) {
            this._closed = true;
            try {
                _closeInput();
            } finally {
                _releaseBuffers();
            }
        }
    }

    protected void convertNumberToBigDecimal() throws IOException, JsonParseException {
        int i = this._numTypesValid;
        if ((i & 8) != 0) {
            this._numberBigDecimal = new BigDecimal(getText());
        } else if ((i & 4) != 0) {
            this._numberBigDecimal = new BigDecimal(this._numberBigInt);
        } else if ((i & 2) != 0) {
            this._numberBigDecimal = BigDecimal.valueOf(this._numberLong);
        } else if ((i & 1) != 0) {
            this._numberBigDecimal = BigDecimal.valueOf(this._numberInt);
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 16;
    }

    protected void convertNumberToBigInteger() throws IOException, JsonParseException {
        int i = this._numTypesValid;
        if ((i & 16) != 0) {
            this._numberBigInt = this._numberBigDecimal.toBigInteger();
        } else if ((i & 2) != 0) {
            this._numberBigInt = BigInteger.valueOf(this._numberLong);
        } else if ((i & 1) != 0) {
            this._numberBigInt = BigInteger.valueOf(this._numberInt);
        } else if ((i & 8) != 0) {
            this._numberBigInt = BigDecimal.valueOf(this._numberDouble).toBigInteger();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 4;
    }

    protected void convertNumberToDouble() throws IOException, JsonParseException {
        int i = this._numTypesValid;
        if ((i & 16) != 0) {
            this._numberDouble = this._numberBigDecimal.doubleValue();
        } else if ((i & 4) != 0) {
            this._numberDouble = this._numberBigInt.doubleValue();
        } else if ((i & 2) != 0) {
            this._numberDouble = this._numberLong;
        } else if ((i & 1) != 0) {
            this._numberDouble = this._numberInt;
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 8;
    }

    protected void convertNumberToInt() throws IOException, JsonParseException {
        int i = this._numTypesValid;
        if ((i & 2) != 0) {
            long j = this._numberLong;
            int i2 = (int) j;
            if (i2 != j) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Numeric value (");
                outline107.append(getText());
                outline107.append(") out of range of int");
                _reportError(outline107.toString());
            }
            this._numberInt = i2;
        } else if ((i & 4) != 0) {
            if (BI_MIN_INT.compareTo(this._numberBigInt) > 0 || BI_MAX_INT.compareTo(this._numberBigInt) < 0) {
                reportOverflowInt();
            }
            this._numberInt = this._numberBigInt.intValue();
        } else if ((i & 8) != 0) {
            double d = this._numberDouble;
            if (d < MIN_INT_D || d > MAX_INT_D) {
                reportOverflowInt();
            }
            this._numberInt = (int) this._numberDouble;
        } else if ((i & 16) != 0) {
            if (BD_MIN_INT.compareTo(this._numberBigDecimal) > 0 || BD_MAX_INT.compareTo(this._numberBigDecimal) < 0) {
                reportOverflowInt();
            }
            this._numberInt = this._numberBigDecimal.intValue();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 1;
    }

    protected void convertNumberToLong() throws IOException, JsonParseException {
        int i = this._numTypesValid;
        if ((i & 1) != 0) {
            this._numberLong = this._numberInt;
        } else if ((i & 4) != 0) {
            if (BI_MIN_LONG.compareTo(this._numberBigInt) > 0 || BI_MAX_LONG.compareTo(this._numberBigInt) < 0) {
                reportOverflowLong();
            }
            this._numberLong = this._numberBigInt.longValue();
        } else if ((i & 8) != 0) {
            double d = this._numberDouble;
            if (d < MIN_LONG_D || d > MAX_LONG_D) {
                reportOverflowLong();
            }
            this._numberLong = (long) this._numberDouble;
        } else if ((i & 16) != 0) {
            if (BD_MIN_LONG.compareTo(this._numberBigDecimal) > 0 || BD_MAX_LONG.compareTo(this._numberBigDecimal) < 0) {
                reportOverflowLong();
            }
            this._numberLong = this._numberBigDecimal.longValue();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 2;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public BigInteger getBigIntegerValue() throws IOException, JsonParseException {
        int i = this._numTypesValid;
        if ((i & 4) == 0) {
            if (i == 0) {
                _parseNumericValue(4);
            }
            if ((this._numTypesValid & 4) == 0) {
                convertNumberToBigInteger();
            }
        }
        return this._numberBigInt;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public JsonLocation getCurrentLocation() {
        return new JsonLocation(this._ioContext.getSourceReference(), (this._currInputProcessed + this._inputPtr) - 1, this._currInputRow, (this._inputPtr - this._currInputRowStart) + 1);
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
    public String getCurrentName() throws IOException, JsonParseException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.START_OBJECT && jsonToken != JsonToken.START_ARRAY) {
            return this._parsingContext.getCurrentName();
        }
        return this._parsingContext.mo4257getParent().getCurrentName();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public BigDecimal getDecimalValue() throws IOException, JsonParseException {
        int i = this._numTypesValid;
        if ((i & 16) == 0) {
            if (i == 0) {
                _parseNumericValue(16);
            }
            if ((this._numTypesValid & 16) == 0) {
                convertNumberToBigDecimal();
            }
        }
        return this._numberBigDecimal;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public double getDoubleValue() throws IOException, JsonParseException {
        int i = this._numTypesValid;
        if ((i & 8) == 0) {
            if (i == 0) {
                _parseNumericValue(8);
            }
            if ((this._numTypesValid & 8) == 0) {
                convertNumberToDouble();
            }
        }
        return this._numberDouble;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public float getFloatValue() throws IOException, JsonParseException {
        return (float) getDoubleValue();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public int getIntValue() throws IOException, JsonParseException {
        int i = this._numTypesValid;
        if ((i & 1) == 0) {
            if (i == 0) {
                _parseNumericValue(1);
            }
            if ((this._numTypesValid & 1) == 0) {
                convertNumberToInt();
            }
        }
        return this._numberInt;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public long getLongValue() throws IOException, JsonParseException {
        int i = this._numTypesValid;
        if ((i & 2) == 0) {
            if (i == 0) {
                _parseNumericValue(2);
            }
            if ((this._numTypesValid & 2) == 0) {
                convertNumberToLong();
            }
        }
        return this._numberLong;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public JsonParser.NumberType getNumberType() throws IOException, JsonParseException {
        if (this._numTypesValid == 0) {
            _parseNumericValue(0);
        }
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            int i = this._numTypesValid;
            if ((i & 1) != 0) {
                return JsonParser.NumberType.INT;
            }
            if ((i & 2) != 0) {
                return JsonParser.NumberType.LONG;
            }
            return JsonParser.NumberType.BIG_INTEGER;
        } else if ((this._numTypesValid & 16) != 0) {
            return JsonParser.NumberType.BIG_DECIMAL;
        } else {
            return JsonParser.NumberType.DOUBLE;
        }
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public Number getNumberValue() throws IOException, JsonParseException {
        if (this._numTypesValid == 0) {
            _parseNumericValue(0);
        }
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            int i = this._numTypesValid;
            if ((i & 1) != 0) {
                return Integer.valueOf(this._numberInt);
            }
            if ((i & 2) != 0) {
                return Long.valueOf(this._numberLong);
            }
            if ((i & 4) != 0) {
                return this._numberBigInt;
            }
            return this._numberBigDecimal;
        }
        int i2 = this._numTypesValid;
        if ((i2 & 16) != 0) {
            return this._numberBigDecimal;
        }
        if ((i2 & 8) == 0) {
            _throwInternal();
        }
        return Double.valueOf(this._numberDouble);
    }

    public final long getTokenCharacterOffset() {
        return this._tokenInputTotal;
    }

    public final int getTokenColumnNr() {
        int i = this._tokenInputCol;
        return i < 0 ? i : i + 1;
    }

    public final int getTokenLineNr() {
        return this._tokenInputRow;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public JsonLocation getTokenLocation() {
        return new JsonLocation(this._ioContext.getSourceReference(), getTokenCharacterOffset(), getTokenLineNr(), getTokenColumnNr());
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
    public boolean hasTextCharacters() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            return true;
        }
        if (jsonToken != JsonToken.FIELD_NAME) {
            return false;
        }
        return this._nameCopied;
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
    public boolean isClosed() {
        return this._closed;
    }

    protected abstract boolean loadMore() throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public final void loadMoreGuaranteed() throws IOException {
        if (!loadMore()) {
            _reportInvalidEOF();
        }
    }

    protected IllegalArgumentException reportInvalidBase64Char(Base64Variant base64Variant, int i, int i2) throws IllegalArgumentException {
        return reportInvalidBase64Char(base64Variant, i, i2, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reportInvalidNumber(String str) throws JsonParseException {
        _reportError(GeneratedOutlineSupport1.outline72("Invalid numeric value: ", str));
    }

    protected void reportOverflowInt() throws IOException, JsonParseException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Numeric value (");
        outline107.append(getText());
        outline107.append(") out of range of int (");
        outline107.append(Integer.MIN_VALUE);
        outline107.append(" - ");
        _reportError(GeneratedOutlineSupport1.outline86(outline107, Integer.MAX_VALUE, ")"));
    }

    protected void reportOverflowLong() throws IOException, JsonParseException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Numeric value (");
        outline107.append(getText());
        outline107.append(") out of range of long (");
        outline107.append(Long.MIN_VALUE);
        outline107.append(" - ");
        outline107.append(Long.MAX_VALUE);
        outline107.append(")");
        _reportError(outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reportUnexpectedNumberChar(int i, String str) throws JsonParseException {
        String outline91 = GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Unexpected character ("), JsonParserMinimalBase._getCharDesc(i), ") in numeric value");
        if (str != null) {
            outline91 = GeneratedOutlineSupport1.outline75(outline91, RealTimeTextConstants.COLON_SPACE, str);
        }
        _reportError(outline91);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonToken reset(boolean z, int i, int i2, int i3) {
        if (i2 < 1 && i3 < 1) {
            return resetInt(z, i);
        }
        return resetFloat(z, i, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonToken resetAsNaN(String str, double d) {
        this._textBuffer.resetWithString(str);
        this._numberDouble = d;
        this._numTypesValid = 8;
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonToken resetFloat(boolean z, int i, int i2, int i3) {
        this._numberNegative = z;
        this._intLength = i;
        this._fractLength = i2;
        this._expLength = i3;
        this._numTypesValid = 0;
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonToken resetInt(boolean z, int i) {
        this._numberNegative = z;
        this._intLength = i;
        this._fractLength = 0;
        this._expLength = 0;
        this._numTypesValid = 0;
        return JsonToken.VALUE_NUMBER_INT;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser, com.amazon.org.codehaus.jackson.Versioned
    public Version version() {
        return VersionUtil.versionFor(getClass());
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
    /* renamed from: getParsingContext  reason: collision with other method in class */
    public JsonReadContext mo4092getParsingContext() {
        return this._parsingContext;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public IllegalArgumentException reportInvalidBase64Char(Base64Variant base64Variant, int i, int i2, String str) throws IllegalArgumentException {
        String outline33;
        if (i <= 32) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Illegal white space character (code 0x");
            outline107.append(Integer.toHexString(i));
            outline107.append(") as character #");
            outline107.append(i2 + 1);
            outline107.append(" of 4-char base64 unit: can only used between units");
            outline33 = outline107.toString();
        } else if (base64Variant.usesPaddingChar(i)) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Unexpected padding character ('");
            outline1072.append(base64Variant.getPaddingChar());
            outline1072.append("') as character #");
            outline1072.append(i2 + 1);
            outline1072.append(" of 4-char base64 unit: padding only legal as 3rd or 4th character");
            outline33 = outline1072.toString();
        } else if (Character.isDefined(i) && !Character.isISOControl(i)) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Illegal character '");
            outline1073.append((char) i);
            outline1073.append("' (code 0x");
            outline1073.append(Integer.toHexString(i));
            outline1073.append(") in base64 content");
            outline33 = outline1073.toString();
        } else {
            outline33 = GeneratedOutlineSupport1.outline33(i, GeneratedOutlineSupport1.outline107("Illegal character (code 0x"), ") in base64 content");
        }
        if (str != null) {
            outline33 = GeneratedOutlineSupport1.outline75(outline33, RealTimeTextConstants.COLON_SPACE, str);
        }
        return new IllegalArgumentException(outline33);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int _decodeBase64Escape(Base64Variant base64Variant, char c, int i) throws IOException, JsonParseException {
        if (c == '\\') {
            char _decodeEscaped = _decodeEscaped();
            if (_decodeEscaped <= ' ' && i == 0) {
                return -1;
            }
            int decodeBase64Char = base64Variant.decodeBase64Char(_decodeEscaped);
            if (decodeBase64Char < 0) {
                throw reportInvalidBase64Char(base64Variant, _decodeEscaped, i);
            }
            return decodeBase64Char;
        }
        throw reportInvalidBase64Char(base64Variant, c, i);
    }
}
