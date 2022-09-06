package com.amazon.org.codehaus.jackson.impl;

import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.org.codehaus.jackson.Base64Variant;
import com.amazon.org.codehaus.jackson.JsonParseException;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.ObjectCodec;
import com.amazon.org.codehaus.jackson.io.IOContext;
import com.amazon.org.codehaus.jackson.sym.CharsToNameCanonicalizer;
import com.amazon.org.codehaus.jackson.util.ByteArrayBuilder;
import com.amazon.org.codehaus.jackson.util.CharTypes;
import com.amazon.org.codehaus.jackson.util.TextBuffer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes13.dex */
public final class ReaderBasedParser extends JsonParserBase {
    protected char[] _inputBuffer;
    protected ObjectCodec _objectCodec;
    protected Reader _reader;
    protected final CharsToNameCanonicalizer _symbols;
    protected boolean _tokenIncomplete;

    /* renamed from: com.amazon.org.codehaus.jackson.impl.ReaderBasedParser$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];

        static {
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public ReaderBasedParser(IOContext iOContext, int i, Reader reader, ObjectCodec objectCodec, CharsToNameCanonicalizer charsToNameCanonicalizer) {
        super(iOContext, i);
        this._tokenIncomplete = false;
        this._reader = reader;
        this._inputBuffer = iOContext.allocTokenBuffer();
        this._objectCodec = objectCodec;
        this._symbols = charsToNameCanonicalizer;
    }

    private final JsonToken _nextAfterName() {
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        this._currToken = jsonToken;
        return jsonToken;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0087 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x007f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String _parseFieldName2(int r5, int r6, int r7) throws java.io.IOException, com.amazon.org.codehaus.jackson.JsonParseException {
        /*
            r4 = this;
            com.amazon.org.codehaus.jackson.util.TextBuffer r0 = r4._textBuffer
            char[] r1 = r4._inputBuffer
            int r2 = r4._inputPtr
            int r2 = r2 - r5
            r0.resetWithShared(r1, r5, r2)
            com.amazon.org.codehaus.jackson.util.TextBuffer r5 = r4._textBuffer
            char[] r5 = r5.getCurrentSegment()
            com.amazon.org.codehaus.jackson.util.TextBuffer r0 = r4._textBuffer
            int r0 = r0.getCurrentSegmentSize()
        L16:
            int r1 = r4._inputPtr
            int r2 = r4._inputEnd
            if (r1 < r2) goto L38
            boolean r1 = r4.loadMore()
            if (r1 != 0) goto L38
            java.lang.String r1 = ": was expecting closing '"
            java.lang.StringBuilder r1 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r1)
            char r2 = (char) r7
            r1.append(r2)
            java.lang.String r2 = "' for name"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r4._reportInvalidEOF(r1)
        L38:
            char[] r1 = r4._inputBuffer
            int r2 = r4._inputPtr
            int r3 = r2 + 1
            r4._inputPtr = r3
            char r1 = r1[r2]
            r2 = 92
            if (r1 > r2) goto L74
            if (r1 != r2) goto L4d
            char r2 = r4._decodeEscaped()
            goto L75
        L4d:
            if (r1 > r7) goto L74
            if (r1 != r7) goto L6b
            com.amazon.org.codehaus.jackson.util.TextBuffer r5 = r4._textBuffer
            r5.setCurrentLength(r0)
            com.amazon.org.codehaus.jackson.util.TextBuffer r5 = r4._textBuffer
            char[] r7 = r5.getTextBuffer()
            int r0 = r5.getTextOffset()
            int r5 = r5.size()
            com.amazon.org.codehaus.jackson.sym.CharsToNameCanonicalizer r1 = r4._symbols
            java.lang.String r5 = r1.findSymbol(r7, r0, r5, r6)
            return r5
        L6b:
            r2 = 32
            if (r1 >= r2) goto L74
            java.lang.String r2 = "name"
            r4._throwUnquotedSpace(r1, r2)
        L74:
            r2 = r1
        L75:
            int r6 = r6 * 31
            int r6 = r6 + r1
            int r1 = r0 + 1
            r5[r0] = r2
            int r0 = r5.length
            if (r1 < r0) goto L87
            com.amazon.org.codehaus.jackson.util.TextBuffer r5 = r4._textBuffer
            char[] r5 = r5.finishCurrentSegment()
            r0 = 0
            goto L16
        L87:
            r0 = r1
            goto L16
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.org.codehaus.jackson.impl.ReaderBasedParser._parseFieldName2(int, int, int):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0069 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0061 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String _parseUnusualFieldName2(int r5, int r6, int[] r7) throws java.io.IOException, com.amazon.org.codehaus.jackson.JsonParseException {
        /*
            r4 = this;
            com.amazon.org.codehaus.jackson.util.TextBuffer r0 = r4._textBuffer
            char[] r1 = r4._inputBuffer
            int r2 = r4._inputPtr
            int r2 = r2 - r5
            r0.resetWithShared(r1, r5, r2)
            com.amazon.org.codehaus.jackson.util.TextBuffer r5 = r4._textBuffer
            char[] r5 = r5.getCurrentSegment()
            com.amazon.org.codehaus.jackson.util.TextBuffer r0 = r4._textBuffer
            int r0 = r0.getCurrentSegmentSize()
            int r1 = r7.length
        L17:
            int r2 = r4._inputPtr
            int r3 = r4._inputEnd
            if (r2 < r3) goto L24
            boolean r2 = r4.loadMore()
            if (r2 != 0) goto L24
            goto L37
        L24:
            char[] r2 = r4._inputBuffer
            int r3 = r4._inputPtr
            char r2 = r2[r3]
            if (r2 > r1) goto L31
            r3 = r7[r2]
            if (r3 == 0) goto L51
            goto L37
        L31:
            boolean r3 = java.lang.Character.isJavaIdentifierPart(r2)
            if (r3 != 0) goto L51
        L37:
            com.amazon.org.codehaus.jackson.util.TextBuffer r5 = r4._textBuffer
            r5.setCurrentLength(r0)
            com.amazon.org.codehaus.jackson.util.TextBuffer r5 = r4._textBuffer
            char[] r7 = r5.getTextBuffer()
            int r0 = r5.getTextOffset()
            int r5 = r5.size()
            com.amazon.org.codehaus.jackson.sym.CharsToNameCanonicalizer r1 = r4._symbols
            java.lang.String r5 = r1.findSymbol(r7, r0, r5, r6)
            return r5
        L51:
            int r3 = r4._inputPtr
            int r3 = r3 + 1
            r4._inputPtr = r3
            int r6 = r6 * 31
            int r6 = r6 + r2
            int r3 = r0 + 1
            r5[r0] = r2
            int r0 = r5.length
            if (r3 < r0) goto L69
            com.amazon.org.codehaus.jackson.util.TextBuffer r5 = r4._textBuffer
            char[] r5 = r5.finishCurrentSegment()
            r0 = 0
            goto L17
        L69:
            r0 = r3
            goto L17
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.org.codehaus.jackson.impl.ReaderBasedParser._parseUnusualFieldName2(int, int, int[]):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0028, code lost:
        _reportInvalidEOF(" in a comment");
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002d, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void _skipCComment() throws java.io.IOException, com.amazon.org.codehaus.jackson.JsonParseException {
        /*
            r3 = this;
        L0:
            int r0 = r3._inputPtr
            int r1 = r3._inputEnd
            if (r0 < r1) goto Lc
            boolean r0 = r3.loadMore()
            if (r0 == 0) goto L28
        Lc:
            char[] r0 = r3._inputBuffer
            int r1 = r3._inputPtr
            int r2 = r1 + 1
            r3._inputPtr = r2
            char r0 = r0[r1]
            r1 = 42
            if (r0 > r1) goto L0
            if (r0 != r1) goto L3d
            int r0 = r3._inputPtr
            int r1 = r3._inputEnd
            if (r0 < r1) goto L2e
            boolean r0 = r3.loadMore()
            if (r0 != 0) goto L2e
        L28:
            java.lang.String r0 = " in a comment"
            r3._reportInvalidEOF(r0)
            return
        L2e:
            char[] r0 = r3._inputBuffer
            int r1 = r3._inputPtr
            char r0 = r0[r1]
            r2 = 47
            if (r0 != r2) goto L0
            int r1 = r1 + 1
            r3._inputPtr = r1
            return
        L3d:
            r1 = 32
            if (r0 >= r1) goto L0
            r1 = 10
            if (r0 != r1) goto L49
            r3._skipLF()
            goto L0
        L49:
            r1 = 13
            if (r0 != r1) goto L51
            r3._skipCR()
            goto L0
        L51:
            r1 = 9
            if (r0 == r1) goto L0
            r3._throwInvalidSpace(r0)
            goto L0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.org.codehaus.jackson.impl.ReaderBasedParser._skipCComment():void");
    }

    private final void _skipComment() throws IOException, JsonParseException {
        if (!isEnabled(JsonParser.Feature.ALLOW_COMMENTS)) {
            _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(" in a comment");
        }
        char[] cArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        char c = cArr[i];
        if (c == '/') {
            _skipCppComment();
        } else if (c == '*') {
            _skipCComment();
        } else {
            _reportUnexpectedChar(c, "was expecting either '*' or '/' for a comment");
        }
    }

    private final void _skipCppComment() throws IOException, JsonParseException {
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                char[] cArr = this._inputBuffer;
                int i = this._inputPtr;
                this._inputPtr = i + 1;
                char c = cArr[i];
                if (c < ' ') {
                    if (c == '\n') {
                        _skipLF();
                        return;
                    } else if (c == '\r') {
                        _skipCR();
                        return;
                    } else if (c != '\t') {
                        _throwInvalidSpace(c);
                    }
                }
            } else {
                return;
            }
        }
    }

    private final int _skipWS() throws IOException, JsonParseException {
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected end-of-input within/between ");
                outline107.append(this._parsingContext.getTypeDesc());
                outline107.append(" entries");
                throw _constructError(outline107.toString());
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c = cArr[i];
            if (c > ' ') {
                if (c != '/') {
                    return c;
                }
                _skipComment();
            } else if (c != ' ') {
                if (c == '\n') {
                    _skipLF();
                } else if (c == '\r') {
                    _skipCR();
                } else if (c != '\t') {
                    _throwInvalidSpace(c);
                }
            }
        }
    }

    private final int _skipWSOrEnd() throws IOException, JsonParseException {
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _handleEOF();
                return -1;
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c = cArr[i];
            if (c > ' ') {
                if (c != '/') {
                    return c;
                }
                _skipComment();
            } else if (c != ' ') {
                if (c == '\n') {
                    _skipLF();
                } else if (c == '\r') {
                    _skipCR();
                } else if (c != '\t') {
                    _throwInvalidSpace(c);
                }
            }
        }
    }

    private final char _verifyNoLeadingZeroes() throws IOException, JsonParseException {
        char c;
        if ((this._inputPtr < this._inputEnd || loadMore()) && (c = this._inputBuffer[this._inputPtr]) >= '0' && c <= '9') {
            if (!isEnabled(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
                reportInvalidNumber("Leading zeroes not allowed");
            }
            this._inputPtr++;
            if (c == '0') {
                do {
                    if (this._inputPtr >= this._inputEnd && !loadMore()) {
                        break;
                    }
                    char[] cArr = this._inputBuffer;
                    int i = this._inputPtr;
                    c = cArr[i];
                    if (c < '0' || c > '9') {
                        return '0';
                    }
                    this._inputPtr = i + 1;
                } while (c == '0');
            }
            return c;
        }
        return '0';
    }

    private final JsonToken parseNumberText2(boolean z) throws IOException, JsonParseException {
        int i;
        char nextChar;
        char[] cArr;
        boolean z2;
        boolean z3;
        int i2;
        char nextChar2;
        int i3;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int i4 = 0;
        if (z) {
            emptyAndGetCurrentSegment[0] = '-';
            i = 1;
        } else {
            i = 0;
        }
        int i5 = this._inputPtr;
        if (i5 < this._inputEnd) {
            char[] cArr2 = this._inputBuffer;
            this._inputPtr = i5 + 1;
            nextChar = cArr2[i5];
        } else {
            nextChar = getNextChar("No digit following minus sign");
        }
        if (nextChar == '0') {
            nextChar = _verifyNoLeadingZeroes();
        }
        int i6 = i;
        char[] cArr3 = emptyAndGetCurrentSegment;
        int i7 = 0;
        while (nextChar >= '0' && nextChar <= '9') {
            i7++;
            if (i6 >= cArr3.length) {
                cArr3 = this._textBuffer.finishCurrentSegment();
                i6 = 0;
            }
            int i8 = i6 + 1;
            cArr3[i6] = nextChar;
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                nextChar = 0;
                i6 = i8;
                cArr = cArr3;
                z2 = true;
                break;
            }
            char[] cArr4 = this._inputBuffer;
            int i9 = this._inputPtr;
            this._inputPtr = i9 + 1;
            nextChar = cArr4[i9];
            i6 = i8;
        }
        cArr = cArr3;
        z2 = false;
        if (i7 == 0) {
            reportInvalidNumber(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Missing integer part (next char "), JsonParserMinimalBase._getCharDesc(nextChar), ")"));
        }
        if (nextChar == '.') {
            int i10 = i6 + 1;
            cArr[i6] = nextChar;
            char c = nextChar;
            int i11 = 0;
            while (true) {
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    z2 = true;
                    break;
                }
                char[] cArr5 = this._inputBuffer;
                int i12 = this._inputPtr;
                this._inputPtr = i12 + 1;
                c = cArr5[i12];
                if (c < '0' || c > '9') {
                    break;
                }
                i11++;
                if (i10 >= cArr.length) {
                    cArr = this._textBuffer.finishCurrentSegment();
                    i10 = 0;
                }
                cArr[i10] = c;
                i10++;
            }
            if (i11 == 0) {
                reportUnexpectedNumberChar(c, "Decimal point not followed by a digit");
            }
            int i13 = i10;
            z3 = z2;
            i2 = i11;
            nextChar = c;
            i6 = i13;
        } else {
            z3 = z2;
            i2 = 0;
        }
        if (nextChar == 'e' || nextChar == 'E') {
            if (i6 >= cArr.length) {
                cArr = this._textBuffer.finishCurrentSegment();
                i6 = 0;
            }
            int i14 = i6 + 1;
            cArr[i6] = nextChar;
            int i15 = this._inputPtr;
            if (i15 < this._inputEnd) {
                char[] cArr6 = this._inputBuffer;
                this._inputPtr = i15 + 1;
                nextChar2 = cArr6[i15];
            } else {
                nextChar2 = getNextChar("expected a digit for number exponent");
            }
            if (nextChar2 == '-' || nextChar2 == '+') {
                if (i14 >= cArr.length) {
                    cArr = this._textBuffer.finishCurrentSegment();
                    i14 = 0;
                }
                i3 = i14 + 1;
                cArr[i14] = nextChar2;
                int i16 = this._inputPtr;
                if (i16 < this._inputEnd) {
                    char[] cArr7 = this._inputBuffer;
                    this._inputPtr = i16 + 1;
                    nextChar2 = cArr7[i16];
                } else {
                    nextChar2 = getNextChar("expected a digit for number exponent");
                }
            } else {
                i3 = i14;
            }
            i6 = i3;
            int i17 = 0;
            while (nextChar2 <= '9' && nextChar2 >= '0') {
                i17++;
                if (i6 >= cArr.length) {
                    cArr = this._textBuffer.finishCurrentSegment();
                    i6 = 0;
                }
                int i18 = i6 + 1;
                cArr[i6] = nextChar2;
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    i4 = i17;
                    z3 = true;
                    i6 = i18;
                    break;
                }
                char[] cArr8 = this._inputBuffer;
                int i19 = this._inputPtr;
                this._inputPtr = i19 + 1;
                nextChar2 = cArr8[i19];
                i6 = i18;
            }
            i4 = i17;
            if (i4 == 0) {
                reportUnexpectedNumberChar(nextChar2, "Exponent indicator not followed by a digit");
            }
        }
        if (!z3) {
            this._inputPtr--;
        }
        this._textBuffer.setCurrentLength(i6);
        return reset(z, i7, i2, i4);
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserBase
    protected void _closeInput() throws IOException {
        if (this._reader != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE)) {
                this._reader.close();
            }
            this._reader = null;
        }
    }

    protected byte[] _decodeBase64(Base64Variant base64Variant) throws IOException, JsonParseException {
        ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c = cArr[i];
            if (c > ' ') {
                int decodeBase64Char = base64Variant.decodeBase64Char(c);
                if (decodeBase64Char < 0) {
                    if (c == '\"') {
                        return _getByteArrayBuilder.toByteArray();
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant, c, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                char[] cArr2 = this._inputBuffer;
                int i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                char c2 = cArr2[i2];
                int decodeBase64Char2 = base64Variant.decodeBase64Char(c2);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = _decodeBase64Escape(base64Variant, c2, 1);
                }
                int i3 = (decodeBase64Char << 6) | decodeBase64Char2;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                char[] cArr3 = this._inputBuffer;
                int i4 = this._inputPtr;
                this._inputPtr = i4 + 1;
                char c3 = cArr3[i4];
                int decodeBase64Char3 = base64Variant.decodeBase64Char(c3);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (c3 == '\"' && !base64Variant.usesPadding()) {
                            _getByteArrayBuilder.append(i3 >> 4);
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char3 = _decodeBase64Escape(base64Variant, c3, 2);
                    }
                    if (decodeBase64Char3 == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        char[] cArr4 = this._inputBuffer;
                        int i5 = this._inputPtr;
                        this._inputPtr = i5 + 1;
                        char c4 = cArr4[i5];
                        if (base64Variant.usesPaddingChar(c4)) {
                            _getByteArrayBuilder.append(i3 >> 4);
                        } else {
                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("expected padding character '");
                            outline107.append(base64Variant.getPaddingChar());
                            outline107.append("'");
                            throw reportInvalidBase64Char(base64Variant, c4, 3, outline107.toString());
                        }
                    }
                }
                int i6 = (i3 << 6) | decodeBase64Char3;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                char[] cArr5 = this._inputBuffer;
                int i7 = this._inputPtr;
                this._inputPtr = i7 + 1;
                char c5 = cArr5[i7];
                int decodeBase64Char4 = base64Variant.decodeBase64Char(c5);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (c5 == '\"' && !base64Variant.usesPadding()) {
                            _getByteArrayBuilder.appendTwoBytes(i6 >> 2);
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char4 = _decodeBase64Escape(base64Variant, c5, 3);
                    }
                    if (decodeBase64Char4 == -2) {
                        _getByteArrayBuilder.appendTwoBytes(i6 >> 2);
                    }
                }
                _getByteArrayBuilder.appendThreeBytes((i6 << 6) | decodeBase64Char4);
            }
        }
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserBase
    protected final char _decodeEscaped() throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(" in character escape sequence");
        }
        char[] cArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        char c = cArr[i];
        if (c == '\"' || c == '/' || c == '\\') {
            return c;
        }
        if (c == 'b') {
            return '\b';
        }
        if (c == 'f') {
            return '\f';
        }
        if (c == 'n') {
            return '\n';
        }
        if (c == 'r') {
            return '\r';
        }
        if (c == 't') {
            return '\t';
        }
        if (c != 'u') {
            return _handleUnrecognizedCharacterEscape(c);
        }
        int i2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in character escape sequence");
            }
            char[] cArr2 = this._inputBuffer;
            int i4 = this._inputPtr;
            this._inputPtr = i4 + 1;
            char c2 = cArr2[i4];
            int charToHex = CharTypes.charToHex(c2);
            if (charToHex < 0) {
                _reportUnexpectedChar(c2, "expected a hex-digit for character escape sequence");
            }
            i2 = (i2 << 4) | charToHex;
        }
        return (char) i2;
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserBase
    protected void _finishString() throws IOException, JsonParseException {
        int i = this._inputPtr;
        int i2 = this._inputEnd;
        if (i < i2) {
            int[] inputCodeLatin1 = CharTypes.getInputCodeLatin1();
            int length = inputCodeLatin1.length;
            while (true) {
                char[] cArr = this._inputBuffer;
                char c = cArr[i];
                if (c >= length || inputCodeLatin1[c] == 0) {
                    i++;
                    if (i >= i2) {
                        break;
                    }
                } else if (c == '\"') {
                    TextBuffer textBuffer = this._textBuffer;
                    int i3 = this._inputPtr;
                    textBuffer.resetWithShared(cArr, i3, i - i3);
                    this._inputPtr = i + 1;
                    return;
                }
            }
        }
        TextBuffer textBuffer2 = this._textBuffer;
        char[] cArr2 = this._inputBuffer;
        int i4 = this._inputPtr;
        textBuffer2.resetWithCopy(cArr2, i4, i - i4);
        this._inputPtr = i;
        _finishString2();
    }

    protected void _finishString2() throws IOException, JsonParseException {
        char[] currentSegment = this._textBuffer.getCurrentSegment();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(": was expecting closing quote for a string value");
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c = cArr[i];
            if (c <= '\\') {
                if (c == '\\') {
                    c = _decodeEscaped();
                } else if (c <= '\"') {
                    if (c == '\"') {
                        this._textBuffer.setCurrentLength(currentSegmentSize);
                        return;
                    } else if (c < ' ') {
                        _throwUnquotedSpace(c, "string value");
                    }
                }
            }
            if (currentSegmentSize >= currentSegment.length) {
                currentSegment = this._textBuffer.finishCurrentSegment();
                currentSegmentSize = 0;
            }
            currentSegment[currentSegmentSize] = c;
            currentSegmentSize++;
        }
    }

    protected final String _getText2(JsonToken jsonToken) {
        if (jsonToken == null) {
            return null;
        }
        int ordinal = jsonToken.ordinal();
        if (ordinal == 5) {
            return this._parsingContext.getCurrentName();
        }
        if (ordinal != 7 && ordinal != 8 && ordinal != 9) {
            return jsonToken.asString();
        }
        return this._textBuffer.contentsAsString();
    }

    protected final JsonToken _handleApostropheValue() throws IOException, JsonParseException {
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(": was expecting closing quote for a string value");
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c = cArr[i];
            if (c <= '\\') {
                if (c == '\\') {
                    c = _decodeEscaped();
                } else if (c <= '\'') {
                    if (c == '\'') {
                        this._textBuffer.setCurrentLength(currentSegmentSize);
                        return JsonToken.VALUE_STRING;
                    } else if (c < ' ') {
                        _throwUnquotedSpace(c, "string value");
                    }
                }
            }
            if (currentSegmentSize >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                currentSegmentSize = 0;
            }
            emptyAndGetCurrentSegment[currentSegmentSize] = c;
            currentSegmentSize++;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, char], vars: [r9v0 ??, r9v1 ??, r9v5 ??]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:107)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:83)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:57)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:45)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:29)
        */
    protected com.amazon.org.codehaus.jackson.JsonToken _handleInvalidNumberStart(
    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, char], vars: [r9v0 ??, r9v1 ??, r9v5 ??]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:107)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:83)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:57)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:45)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r9v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:227)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:222)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:167)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:366)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:306)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:272)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
        */

    protected final JsonToken _handleUnexpectedValue(int i) throws IOException, JsonParseException {
        if (i != 39) {
            if (i == 43) {
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    _reportInvalidEOFInValue();
                }
                char[] cArr = this._inputBuffer;
                int i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                return _handleInvalidNumberStart(cArr[i2], false);
            } else if (i == 78) {
                _matchToken("NaN", 1);
                if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    return resetAsNaN("NaN", Double.NaN);
                }
                _reportError("Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
            }
        } else if (isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
            return _handleApostropheValue();
        }
        _reportUnexpectedChar(i, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
        return null;
    }

    protected final String _handleUnusualFieldName(int i) throws IOException, JsonParseException {
        boolean isJavaIdentifierPart;
        if (i == 39 && isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
            return _parseApostropheFieldName();
        }
        if (!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
            _reportUnexpectedChar(i, "was expecting double-quote to start field name");
        }
        int[] inputCodeLatin1JsNames = CharTypes.getInputCodeLatin1JsNames();
        int length = inputCodeLatin1JsNames.length;
        int i2 = 0;
        if (i < length) {
            isJavaIdentifierPart = inputCodeLatin1JsNames[i] == 0 && (i < 48 || i > 57);
        } else {
            isJavaIdentifierPart = Character.isJavaIdentifierPart((char) i);
        }
        if (!isJavaIdentifierPart) {
            _reportUnexpectedChar(i, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int i3 = this._inputPtr;
        int i4 = this._inputEnd;
        if (i3 < i4) {
            do {
                char[] cArr = this._inputBuffer;
                char c = cArr[i3];
                if (c < length) {
                    if (inputCodeLatin1JsNames[c] != 0) {
                        int i5 = this._inputPtr - 1;
                        this._inputPtr = i3;
                        return this._symbols.findSymbol(cArr, i5, i3 - i5, i2);
                    }
                } else if (!Character.isJavaIdentifierPart(c)) {
                    int i6 = this._inputPtr - 1;
                    this._inputPtr = i3;
                    return this._symbols.findSymbol(this._inputBuffer, i6, i3 - i6, i2);
                }
                i2 = (i2 * 31) + c;
                i3++;
            } while (i3 < i4);
            this._inputPtr = i3;
            return _parseUnusualFieldName2(this._inputPtr - 1, i2, inputCodeLatin1JsNames);
        }
        this._inputPtr = i3;
        return _parseUnusualFieldName2(this._inputPtr - 1, i2, inputCodeLatin1JsNames);
    }

    protected final void _matchToken(String str, int i) throws IOException, JsonParseException {
        char c;
        int length = str.length();
        do {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOFInValue();
            }
            if (this._inputBuffer[this._inputPtr] != str.charAt(i)) {
                _reportInvalidToken(str.substring(0, i), "'null', 'true', 'false' or NaN");
            }
            this._inputPtr++;
            i++;
        } while (i < length);
        if ((this._inputPtr < this._inputEnd || loadMore()) && (c = this._inputBuffer[this._inputPtr]) >= '0' && c != ']' && c != '}' && Character.isJavaIdentifierPart(c)) {
            _reportInvalidToken(str.substring(0, i), "'null', 'true', 'false' or NaN");
        }
    }

    protected final String _parseApostropheFieldName() throws IOException, JsonParseException {
        int i = this._inputPtr;
        int i2 = this._inputEnd;
        int i3 = 0;
        if (i < i2) {
            int[] inputCodeLatin1 = CharTypes.getInputCodeLatin1();
            int length = inputCodeLatin1.length;
            do {
                char[] cArr = this._inputBuffer;
                char c = cArr[i];
                if (c == '\'') {
                    int i4 = this._inputPtr;
                    this._inputPtr = i + 1;
                    return this._symbols.findSymbol(cArr, i4, i - i4, i3);
                } else if (c < length && inputCodeLatin1[c] != 0) {
                    break;
                } else {
                    i3 = (i3 * 31) + c;
                    i++;
                }
            } while (i < i2);
        }
        int i5 = this._inputPtr;
        this._inputPtr = i;
        return _parseFieldName2(i5, i3, 39);
    }

    protected final String _parseFieldName(int i) throws IOException, JsonParseException {
        if (i != 34) {
            return _handleUnusualFieldName(i);
        }
        int i2 = this._inputPtr;
        int i3 = 0;
        int i4 = this._inputEnd;
        if (i2 < i4) {
            int[] inputCodeLatin1 = CharTypes.getInputCodeLatin1();
            int length = inputCodeLatin1.length;
            while (true) {
                char[] cArr = this._inputBuffer;
                char c = cArr[i2];
                if (c >= length || inputCodeLatin1[c] == 0) {
                    i3 = (i3 * 31) + c;
                    i2++;
                    if (i2 >= i4) {
                        break;
                    }
                } else if (c == '\"') {
                    int i5 = this._inputPtr;
                    this._inputPtr = i2 + 1;
                    return this._symbols.findSymbol(cArr, i5, i2 - i5, i3);
                }
            }
        }
        int i6 = this._inputPtr;
        this._inputPtr = i2;
        return _parseFieldName2(i6, i3, 34);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserBase
    public void _releaseBuffers() throws IOException {
        super._releaseBuffers();
        char[] cArr = this._inputBuffer;
        if (cArr != null) {
            this._inputBuffer = null;
            this._ioContext.releaseTokenBuffer(cArr);
        }
    }

    protected void _reportInvalidToken(String str, String str2) throws IOException, JsonParseException {
        StringBuilder sb = new StringBuilder(str);
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                break;
            }
            char c = this._inputBuffer[this._inputPtr];
            if (!Character.isJavaIdentifierPart(c)) {
                break;
            }
            this._inputPtr++;
            sb.append(c);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unrecognized token '");
        outline107.append(sb.toString());
        outline107.append("': was expecting ");
        _reportError(outline107.toString());
    }

    protected final void _skipCR() throws IOException {
        if (this._inputPtr < this._inputEnd || loadMore()) {
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            if (cArr[i] == '\n') {
                this._inputPtr = i + 1;
            }
        }
        this._currInputRow++;
        this._currInputRowStart = this._inputPtr;
    }

    protected final void _skipLF() throws IOException {
        this._currInputRow++;
        this._currInputRowStart = this._inputPtr;
    }

    protected void _skipString() throws IOException, JsonParseException {
        this._tokenIncomplete = false;
        int i = this._inputPtr;
        int i2 = this._inputEnd;
        char[] cArr = this._inputBuffer;
        while (true) {
            if (i >= i2) {
                this._inputPtr = i;
                if (!loadMore()) {
                    _reportInvalidEOF(": was expecting closing quote for a string value");
                }
                i = this._inputPtr;
                i2 = this._inputEnd;
            }
            int i3 = i + 1;
            char c = cArr[i];
            if (c <= '\\') {
                if (c == '\\') {
                    this._inputPtr = i3;
                    _decodeEscaped();
                    i = this._inputPtr;
                    i2 = this._inputEnd;
                } else if (c <= '\"') {
                    if (c == '\"') {
                        this._inputPtr = i3;
                        return;
                    } else if (c < ' ') {
                        this._inputPtr = i3;
                        _throwUnquotedSpace(c, "string value");
                    }
                }
            }
            i = i3;
        }
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserBase, com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        this._symbols.release();
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException, JsonParseException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.VALUE_STRING && (jsonToken != JsonToken.VALUE_EMBEDDED_OBJECT || this._binaryValue == null)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Current token (");
            outline107.append(this._currToken);
            outline107.append(") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
            _reportError(outline107.toString());
        }
        if (this._tokenIncomplete) {
            try {
                this._binaryValue = _decodeBase64(base64Variant);
                this._tokenIncomplete = false;
            } catch (IllegalArgumentException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to decode VALUE_STRING as base64 (");
                sb.append(base64Variant);
                sb.append("): ");
                throw _constructError(GeneratedOutlineSupport1.outline43(e, sb));
            }
        } else if (this._binaryValue == null) {
            ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
            _decodeBase64(getText(), _getByteArrayBuilder, base64Variant);
            this._binaryValue = _getByteArrayBuilder.toByteArray();
        }
        return this._binaryValue;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public Object getInputSource() {
        return this._reader;
    }

    protected char getNextChar(String str) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(str);
        }
        char[] cArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        return cArr[i];
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
    public final String getText() throws IOException, JsonParseException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                _finishString();
            }
            return this._textBuffer.contentsAsString();
        }
        return _getText2(jsonToken);
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
    public char[] getTextCharacters() throws IOException, JsonParseException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != null) {
            int ordinal = jsonToken.ordinal();
            if (ordinal != 5) {
                if (ordinal != 7) {
                    if (ordinal != 8 && ordinal != 9) {
                        return this._currToken.asCharArray();
                    }
                } else if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    _finishString();
                }
                return this._textBuffer.getTextBuffer();
            }
            if (!this._nameCopied) {
                String currentName = this._parsingContext.getCurrentName();
                int length = currentName.length();
                char[] cArr = this._nameCopyBuffer;
                if (cArr == null) {
                    this._nameCopyBuffer = this._ioContext.allocNameCopyBuffer(length);
                } else if (cArr.length < length) {
                    this._nameCopyBuffer = new char[length];
                }
                currentName.getChars(0, length, this._nameCopyBuffer, 0);
                this._nameCopied = true;
            }
            return this._nameCopyBuffer;
        }
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
    public int getTextLength() throws IOException, JsonParseException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != null) {
            int ordinal = jsonToken.ordinal();
            if (ordinal != 5) {
                if (ordinal != 7) {
                    if (ordinal != 8 && ordinal != 9) {
                        return this._currToken.asCharArray().length;
                    }
                } else if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    _finishString();
                }
                return this._textBuffer.size();
            }
            return this._parsingContext.getCurrentName().length();
        }
        return 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0015, code lost:
        if (r0 != 9) goto L17;
     */
    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int getTextOffset() throws java.io.IOException, com.amazon.org.codehaus.jackson.JsonParseException {
        /*
            r3 = this;
            com.amazon.org.codehaus.jackson.JsonToken r0 = r3._currToken
            r1 = 0
            if (r0 == 0) goto L28
            int r0 = r0.ordinal()
            r2 = 5
            if (r0 == r2) goto L28
            r2 = 7
            if (r0 == r2) goto L18
            r2 = 8
            if (r0 == r2) goto L21
            r2 = 9
            if (r0 == r2) goto L21
            goto L28
        L18:
            boolean r0 = r3._tokenIncomplete
            if (r0 == 0) goto L21
            r3._tokenIncomplete = r1
            r3._finishString()
        L21:
            com.amazon.org.codehaus.jackson.util.TextBuffer r0 = r3._textBuffer
            int r0 = r0.getTextOffset()
            return r0
        L28:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.org.codehaus.jackson.impl.ReaderBasedParser.getTextOffset():int");
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserBase
    protected final boolean loadMore() throws IOException {
        long j = this._currInputProcessed;
        int i = this._inputEnd;
        this._currInputProcessed = j + i;
        this._currInputRowStart -= i;
        Reader reader = this._reader;
        if (reader != null) {
            char[] cArr = this._inputBuffer;
            int read = reader.read(cArr, 0, cArr.length);
            if (read > 0) {
                this._inputPtr = 0;
                this._inputEnd = read;
                return true;
            }
            _closeInput();
            if (read == 0) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Reader returned 0 characters when trying to read ");
                outline107.append(this._inputEnd);
                throw new IOException(outline107.toString());
            }
        }
        return false;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public Boolean nextBooleanValue() throws IOException, JsonParseException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken jsonToken = this._nextToken;
            this._nextToken = null;
            this._currToken = jsonToken;
            if (jsonToken == JsonToken.VALUE_TRUE) {
                return Boolean.TRUE;
            }
            if (jsonToken == JsonToken.VALUE_FALSE) {
                return Boolean.FALSE;
            }
            if (jsonToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (jsonToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return null;
        }
        int ordinal = nextToken().ordinal();
        if (ordinal == 10) {
            return Boolean.TRUE;
        }
        if (ordinal == 11) {
            return Boolean.FALSE;
        }
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public int nextIntValue(int i) throws IOException, JsonParseException {
        if (this._currToken != JsonToken.FIELD_NAME) {
            return nextToken() == JsonToken.VALUE_NUMBER_INT ? getIntValue() : i;
        }
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        this._currToken = jsonToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT) {
            return getIntValue();
        }
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        return i;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public long nextLongValue(long j) throws IOException, JsonParseException {
        if (this._currToken != JsonToken.FIELD_NAME) {
            return nextToken() == JsonToken.VALUE_NUMBER_INT ? getLongValue() : j;
        }
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        this._currToken = jsonToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT) {
            return getLongValue();
        }
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        return j;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public String nextTextValue() throws IOException, JsonParseException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken jsonToken = this._nextToken;
            this._nextToken = null;
            this._currToken = jsonToken;
            if (jsonToken == JsonToken.VALUE_STRING) {
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    _finishString();
                }
                return this._textBuffer.contentsAsString();
            }
            if (jsonToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (jsonToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return null;
        } else if (nextToken() != JsonToken.VALUE_STRING) {
            return null;
        } else {
            return getText();
        }
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
    public JsonToken nextToken() throws IOException, JsonParseException {
        JsonToken jsonToken;
        this._numTypesValid = 0;
        if (this._currToken == JsonToken.FIELD_NAME) {
            return _nextAfterName();
        }
        if (this._tokenIncomplete) {
            _skipString();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this._currToken = null;
            return null;
        }
        long j = this._currInputProcessed;
        int i = this._inputPtr;
        this._tokenInputTotal = (j + i) - 1;
        this._tokenInputRow = this._currInputRow;
        this._tokenInputCol = (i - this._currInputRowStart) - 1;
        this._binaryValue = null;
        if (_skipWSOrEnd == 93) {
            if (!this._parsingContext.inArray()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, JsonReaderKt.END_OBJ);
            }
            this._parsingContext = this._parsingContext.mo4257getParent();
            JsonToken jsonToken2 = JsonToken.END_ARRAY;
            this._currToken = jsonToken2;
            return jsonToken2;
        } else if (_skipWSOrEnd == 125) {
            if (!this._parsingContext.inObject()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, JsonReaderKt.END_LIST);
            }
            this._parsingContext = this._parsingContext.mo4257getParent();
            JsonToken jsonToken3 = JsonToken.END_OBJECT;
            this._currToken = jsonToken3;
            return jsonToken3;
        } else {
            if (this._parsingContext.expectComma()) {
                if (_skipWSOrEnd != 44) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("was expecting comma to separate ");
                    outline107.append(this._parsingContext.getTypeDesc());
                    outline107.append(" entries");
                    _reportUnexpectedChar(_skipWSOrEnd, outline107.toString());
                }
                _skipWSOrEnd = _skipWS();
            }
            boolean inObject = this._parsingContext.inObject();
            if (inObject) {
                this._parsingContext.setCurrentName(_parseFieldName(_skipWSOrEnd));
                this._currToken = JsonToken.FIELD_NAME;
                int _skipWS = _skipWS();
                if (_skipWS != 58) {
                    _reportUnexpectedChar(_skipWS, "was expecting a colon to separate field name and value");
                }
                _skipWSOrEnd = _skipWS();
            }
            if (_skipWSOrEnd != 34) {
                if (_skipWSOrEnd != 45) {
                    if (_skipWSOrEnd != 91) {
                        if (_skipWSOrEnd != 93) {
                            if (_skipWSOrEnd == 102) {
                                _matchToken(PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE, 1);
                                jsonToken = JsonToken.VALUE_FALSE;
                            } else if (_skipWSOrEnd == 110) {
                                _matchToken("null", 1);
                                jsonToken = JsonToken.VALUE_NULL;
                            } else {
                                if (_skipWSOrEnd != 116) {
                                    if (_skipWSOrEnd == 123) {
                                        if (!inObject) {
                                            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
                                        }
                                        jsonToken = JsonToken.START_OBJECT;
                                    } else if (_skipWSOrEnd != 125) {
                                        switch (_skipWSOrEnd) {
                                            case 48:
                                            case 49:
                                            case 50:
                                            case 51:
                                            case 52:
                                            case 53:
                                            case 54:
                                            case 55:
                                            case 56:
                                            case 57:
                                                break;
                                            default:
                                                jsonToken = _handleUnexpectedValue(_skipWSOrEnd);
                                                break;
                                        }
                                    }
                                }
                                _matchToken("true", 1);
                                jsonToken = JsonToken.VALUE_TRUE;
                            }
                        }
                        _reportUnexpectedChar(_skipWSOrEnd, "expected a value");
                        _matchToken("true", 1);
                        jsonToken = JsonToken.VALUE_TRUE;
                    } else {
                        if (!inObject) {
                            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
                        }
                        jsonToken = JsonToken.START_ARRAY;
                    }
                }
                jsonToken = parseNumberText(_skipWSOrEnd);
            } else {
                this._tokenIncomplete = true;
                jsonToken = JsonToken.VALUE_STRING;
            }
            if (inObject) {
                this._nextToken = jsonToken;
                return this._currToken;
            }
            this._currToken = jsonToken;
            return jsonToken;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, char], vars: [r13v0 ??, r13v1 ??, r13v22 ??]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:107)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:83)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:57)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:45)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:29)
        */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x008d -> B:57:0x008e). Please submit an issue!!! */
    protected final com.amazon.org.codehaus.jackson.JsonToken parseNumberText(
    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, char], vars: [r13v0 ??, r13v1 ??, r13v22 ??]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:107)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:83)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:57)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:45)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r13v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:227)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:222)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:167)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:366)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:306)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:272)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
        */

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public int releaseBuffered(Writer writer) throws IOException {
        int i = this._inputEnd;
        int i2 = this._inputPtr;
        int i3 = i - i2;
        if (i3 < 1) {
            return 0;
        }
        writer.write(this._inputBuffer, i2, i3);
        return i3;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public void setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
    }
}
