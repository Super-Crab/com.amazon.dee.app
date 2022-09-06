package org.codehaus.jackson.impl;

import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import kotlinx.serialization.json.internal.JsonReaderKt;
import okio.Utf8;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.SerializableString;
import org.codehaus.jackson.io.IOContext;
import org.codehaus.jackson.sym.BytesToNameCanonicalizer;
import org.codehaus.jackson.sym.Name;
import org.codehaus.jackson.util.ByteArrayBuilder;
import org.codehaus.jackson.util.CharTypes;
/* loaded from: classes5.dex */
public final class Utf8StreamParser extends JsonParserBase {
    static final byte BYTE_LF = 10;
    protected boolean _bufferRecyclable;
    protected byte[] _inputBuffer;
    protected InputStream _inputStream;
    protected ObjectCodec _objectCodec;
    private int _quad1;
    protected int[] _quadBuffer;
    protected final BytesToNameCanonicalizer _symbols;
    protected boolean _tokenIncomplete;
    private static final int[] sInputCodesUtf8 = CharTypes.getInputCodeUtf8();
    private static final int[] sInputCodesLatin1 = CharTypes.getInputCodeLatin1();

    /* renamed from: org.codehaus.jackson.impl.Utf8StreamParser$1  reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];

        static {
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public Utf8StreamParser(IOContext iOContext, int i, InputStream inputStream, ObjectCodec objectCodec, BytesToNameCanonicalizer bytesToNameCanonicalizer, byte[] bArr, int i2, int i3, boolean z) {
        super(iOContext, i);
        this._quadBuffer = new int[16];
        this._tokenIncomplete = false;
        this._inputStream = inputStream;
        this._objectCodec = objectCodec;
        this._symbols = bytesToNameCanonicalizer;
        this._inputBuffer = bArr;
        this._inputPtr = i2;
        this._inputEnd = i3;
        this._bufferRecyclable = z;
        if (!JsonParser.Feature.CANONICALIZE_FIELD_NAMES.enabledIn(i)) {
            _throwInternal();
        }
    }

    private final int _decodeUtf8_2(int i) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        return ((i & 31) << 6) | (b & Utf8.REPLACEMENT_BYTE);
    }

    private final int _decodeUtf8_3(int i) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        int i2 = i & 15;
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b = bArr[i3];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        int i4 = (i2 << 6) | (b & Utf8.REPLACEMENT_BYTE);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i5 = this._inputPtr;
        this._inputPtr = i5 + 1;
        byte b2 = bArr2[i5];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & 255, this._inputPtr);
        }
        return (i4 << 6) | (b2 & Utf8.REPLACEMENT_BYTE);
    }

    private final int _decodeUtf8_3fast(int i) throws IOException, JsonParseException {
        int i2 = i & 15;
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b = bArr[i3];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        int i4 = (i2 << 6) | (b & Utf8.REPLACEMENT_BYTE);
        byte[] bArr2 = this._inputBuffer;
        int i5 = this._inputPtr;
        this._inputPtr = i5 + 1;
        byte b2 = bArr2[i5];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & 255, this._inputPtr);
        }
        return (i4 << 6) | (b2 & Utf8.REPLACEMENT_BYTE);
    }

    private final int _decodeUtf8_4(int i) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        int i3 = ((i & 7) << 6) | (b & Utf8.REPLACEMENT_BYTE);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i4 = this._inputPtr;
        this._inputPtr = i4 + 1;
        byte b2 = bArr2[i4];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & 255, this._inputPtr);
        }
        int i5 = (i3 << 6) | (b2 & Utf8.REPLACEMENT_BYTE);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int i6 = this._inputPtr;
        this._inputPtr = i6 + 1;
        byte b3 = bArr3[i6];
        if ((b3 & 192) != 128) {
            _reportInvalidOther(b3 & 255, this._inputPtr);
        }
        return ((i5 << 6) | (b3 & Utf8.REPLACEMENT_BYTE)) - 65536;
    }

    private final void _finishString2(char[] cArr, int i) throws IOException, JsonParseException {
        int[] iArr = sInputCodesUtf8;
        byte[] bArr = this._inputBuffer;
        while (true) {
            int i2 = this._inputPtr;
            if (i2 >= this._inputEnd) {
                loadMoreGuaranteed();
                i2 = this._inputPtr;
            }
            if (i >= cArr.length) {
                cArr = this._textBuffer.finishCurrentSegment();
                i = 0;
            }
            int min = Math.min(this._inputEnd, (cArr.length - i) + i2);
            while (true) {
                if (i2 < min) {
                    int i3 = i2 + 1;
                    int i4 = bArr[i2] & 255;
                    if (iArr[i4] != 0) {
                        this._inputPtr = i3;
                        if (i4 == 34) {
                            this._textBuffer.setCurrentLength(i);
                            return;
                        }
                        int i5 = iArr[i4];
                        if (i5 == 1) {
                            i4 = _decodeEscaped();
                        } else if (i5 == 2) {
                            i4 = _decodeUtf8_2(i4);
                        } else if (i5 == 3) {
                            i4 = this._inputEnd - this._inputPtr >= 2 ? _decodeUtf8_3fast(i4) : _decodeUtf8_3(i4);
                        } else if (i5 == 4) {
                            int _decodeUtf8_4 = _decodeUtf8_4(i4);
                            int i6 = i + 1;
                            cArr[i] = (char) (55296 | (_decodeUtf8_4 >> 10));
                            if (i6 >= cArr.length) {
                                cArr = this._textBuffer.finishCurrentSegment();
                                i6 = 0;
                            }
                            i4 = (_decodeUtf8_4 & 1023) | 56320;
                            i = i6;
                        } else if (i4 < 32) {
                            _throwUnquotedSpace(i4, "string value");
                        } else {
                            _reportInvalidChar(i4);
                        }
                        if (i >= cArr.length) {
                            cArr = this._textBuffer.finishCurrentSegment();
                            i = 0;
                        }
                        cArr[i] = (char) i4;
                        i++;
                    } else {
                        cArr[i] = (char) i4;
                        i2 = i3;
                        i++;
                    }
                } else {
                    this._inputPtr = i2;
                    break;
                }
            }
        }
    }

    private final void _isNextTokenNameNo(int i) throws IOException, JsonParseException {
        JsonToken parseNumberText;
        this._parsingContext.setCurrentName(_parseFieldName(i).getName());
        this._currToken = JsonToken.FIELD_NAME;
        int _skipWS = _skipWS();
        if (_skipWS != 58) {
            _reportUnexpectedChar(_skipWS, "was expecting a colon to separate field name and value");
        }
        int _skipWS2 = _skipWS();
        if (_skipWS2 == 34) {
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
            return;
        }
        if (_skipWS2 != 45) {
            if (_skipWS2 != 91) {
                if (_skipWS2 != 93) {
                    if (_skipWS2 == 102) {
                        _matchToken(PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE, 1);
                        parseNumberText = JsonToken.VALUE_FALSE;
                    } else if (_skipWS2 == 110) {
                        _matchToken("null", 1);
                        parseNumberText = JsonToken.VALUE_NULL;
                    } else {
                        if (_skipWS2 != 116) {
                            if (_skipWS2 == 123) {
                                parseNumberText = JsonToken.START_OBJECT;
                            } else if (_skipWS2 != 125) {
                                switch (_skipWS2) {
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
                                        parseNumberText = _handleUnexpectedValue(_skipWS2);
                                        break;
                                }
                            }
                        }
                        _matchToken("true", 1);
                        parseNumberText = JsonToken.VALUE_TRUE;
                    }
                }
                _reportUnexpectedChar(_skipWS2, "expected a value");
                _matchToken("true", 1);
                parseNumberText = JsonToken.VALUE_TRUE;
            } else {
                parseNumberText = JsonToken.START_ARRAY;
            }
            this._nextToken = parseNumberText;
        }
        parseNumberText = parseNumberText(_skipWS2);
        this._nextToken = parseNumberText;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00ac  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void _isNextTokenNameYes() throws java.io.IOException, org.codehaus.jackson.JsonParseException {
        /*
            r8 = this;
            int r0 = r8._inputPtr
            int r1 = r8._inputEnd
            r2 = 1
            int r1 = r1 - r2
            r3 = 123(0x7b, float:1.72E-43)
            r4 = 91
            r5 = 34
            if (r0 >= r1) goto L4c
            byte[] r1 = r8._inputBuffer
            r6 = r1[r0]
            r7 = 58
            if (r6 != r7) goto L4c
            int r0 = r0 + r2
            r8._inputPtr = r0
            int r0 = r8._inputPtr
            int r6 = r0 + 1
            r8._inputPtr = r6
            r0 = r1[r0]
            if (r0 != r5) goto L2a
            r8._tokenIncomplete = r2
            org.codehaus.jackson.JsonToken r0 = org.codehaus.jackson.JsonToken.VALUE_STRING
            r8._nextToken = r0
            return
        L2a:
            if (r0 != r3) goto L31
            org.codehaus.jackson.JsonToken r0 = org.codehaus.jackson.JsonToken.START_OBJECT
            r8._nextToken = r0
            return
        L31:
            if (r0 != r4) goto L38
            org.codehaus.jackson.JsonToken r0 = org.codehaus.jackson.JsonToken.START_ARRAY
            r8._nextToken = r0
            return
        L38:
            r0 = r0 & 255(0xff, float:3.57E-43)
            r1 = 32
            if (r0 <= r1) goto L42
            r1 = 47
            if (r0 != r1) goto L50
        L42:
            int r0 = r8._inputPtr
            int r0 = r0 - r2
            r8._inputPtr = r0
            int r0 = r8._skipWS()
            goto L50
        L4c:
            int r0 = r8._skipColon()
        L50:
            if (r0 == r5) goto Lac
            r1 = 45
            if (r0 == r1) goto La5
            if (r0 == r4) goto La0
            r1 = 93
            if (r0 == r1) goto L91
            r1 = 102(0x66, float:1.43E-43)
            if (r0 == r1) goto L87
            r1 = 110(0x6e, float:1.54E-43)
            if (r0 == r1) goto L7d
            r1 = 116(0x74, float:1.63E-43)
            if (r0 == r1) goto L96
            if (r0 == r3) goto L78
            r1 = 125(0x7d, float:1.75E-43)
            if (r0 == r1) goto L91
            switch(r0) {
                case 48: goto La5;
                case 49: goto La5;
                case 50: goto La5;
                case 51: goto La5;
                case 52: goto La5;
                case 53: goto La5;
                case 54: goto La5;
                case 55: goto La5;
                case 56: goto La5;
                case 57: goto La5;
                default: goto L71;
            }
        L71:
            org.codehaus.jackson.JsonToken r0 = r8._handleUnexpectedValue(r0)
            r8._nextToken = r0
            return
        L78:
            org.codehaus.jackson.JsonToken r0 = org.codehaus.jackson.JsonToken.START_OBJECT
            r8._nextToken = r0
            return
        L7d:
            java.lang.String r0 = "null"
            r8._matchToken(r0, r2)
            org.codehaus.jackson.JsonToken r0 = org.codehaus.jackson.JsonToken.VALUE_NULL
            r8._nextToken = r0
            return
        L87:
            java.lang.String r0 = "false"
            r8._matchToken(r0, r2)
            org.codehaus.jackson.JsonToken r0 = org.codehaus.jackson.JsonToken.VALUE_FALSE
            r8._nextToken = r0
            return
        L91:
            java.lang.String r1 = "expected a value"
            r8._reportUnexpectedChar(r0, r1)
        L96:
            java.lang.String r0 = "true"
            r8._matchToken(r0, r2)
            org.codehaus.jackson.JsonToken r0 = org.codehaus.jackson.JsonToken.VALUE_TRUE
            r8._nextToken = r0
            return
        La0:
            org.codehaus.jackson.JsonToken r0 = org.codehaus.jackson.JsonToken.START_ARRAY
            r8._nextToken = r0
            return
        La5:
            org.codehaus.jackson.JsonToken r0 = r8.parseNumberText(r0)
            r8._nextToken = r0
            return
        Lac:
            r8._tokenIncomplete = r2
            org.codehaus.jackson.JsonToken r0 = org.codehaus.jackson.JsonToken.VALUE_STRING
            r8._nextToken = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.impl.Utf8StreamParser._isNextTokenNameYes():void");
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

    private final JsonToken _nextTokenNotInObject(int i) throws IOException, JsonParseException {
        if (i == 34) {
            this._tokenIncomplete = true;
            JsonToken jsonToken = JsonToken.VALUE_STRING;
            this._currToken = jsonToken;
            return jsonToken;
        }
        if (i != 45) {
            if (i != 91) {
                if (i != 93) {
                    if (i == 102) {
                        _matchToken(PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE, 1);
                        JsonToken jsonToken2 = JsonToken.VALUE_FALSE;
                        this._currToken = jsonToken2;
                        return jsonToken2;
                    } else if (i == 110) {
                        _matchToken("null", 1);
                        JsonToken jsonToken3 = JsonToken.VALUE_NULL;
                        this._currToken = jsonToken3;
                        return jsonToken3;
                    } else {
                        if (i != 116) {
                            if (i == 123) {
                                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
                                JsonToken jsonToken4 = JsonToken.START_OBJECT;
                                this._currToken = jsonToken4;
                                return jsonToken4;
                            } else if (i != 125) {
                                switch (i) {
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
                                        JsonToken _handleUnexpectedValue = _handleUnexpectedValue(i);
                                        this._currToken = _handleUnexpectedValue;
                                        return _handleUnexpectedValue;
                                }
                            }
                        }
                        _matchToken("true", 1);
                        JsonToken jsonToken5 = JsonToken.VALUE_TRUE;
                        this._currToken = jsonToken5;
                        return jsonToken5;
                    }
                }
                _reportUnexpectedChar(i, "expected a value");
                _matchToken("true", 1);
                JsonToken jsonToken52 = JsonToken.VALUE_TRUE;
                this._currToken = jsonToken52;
                return jsonToken52;
            }
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            JsonToken jsonToken6 = JsonToken.START_ARRAY;
            this._currToken = jsonToken6;
            return jsonToken6;
        }
        JsonToken parseNumberText = parseNumberText(i);
        this._currToken = parseNumberText;
        return parseNumberText;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0045, code lost:
        r5 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final org.codehaus.jackson.JsonToken _parseFloatText(char[] r11, int r12, int r13, boolean r14, int r15) throws java.io.IOException, org.codehaus.jackson.JsonParseException {
        /*
            Method dump skipped, instructions count: 256
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.impl.Utf8StreamParser._parseFloatText(char[], int, int, boolean, int):org.codehaus.jackson.JsonToken");
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0044, code lost:
        if (r3 == 46) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0048, code lost:
        if (r3 == 101) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004c, code lost:
        if (r3 != 69) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x004f, code lost:
        r6._inputPtr--;
        r6._textBuffer.setCurrentLength(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005e, code lost:
        return resetInt(r9, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0065, code lost:
        return _parseFloatText(r1, r2, r3, r9, r5);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final org.codehaus.jackson.JsonToken _parserNumber2(char[] r7, int r8, boolean r9, int r10) throws java.io.IOException, org.codehaus.jackson.JsonParseException {
        /*
            r6 = this;
            r1 = r7
            r2 = r8
            r5 = r10
        L3:
            int r7 = r6._inputPtr
            int r8 = r6._inputEnd
            if (r7 < r8) goto L19
            boolean r7 = r6.loadMore()
            if (r7 != 0) goto L19
            org.codehaus.jackson.util.TextBuffer r7 = r6._textBuffer
            r7.setCurrentLength(r2)
            org.codehaus.jackson.JsonToken r7 = r6.resetInt(r9, r5)
            return r7
        L19:
            byte[] r7 = r6._inputBuffer
            int r8 = r6._inputPtr
            int r10 = r8 + 1
            r6._inputPtr = r10
            r7 = r7[r8]
            r3 = r7 & 255(0xff, float:3.57E-43)
            r7 = 57
            if (r3 > r7) goto L42
            r7 = 48
            if (r3 >= r7) goto L2e
            goto L42
        L2e:
            int r7 = r1.length
            if (r2 < r7) goto L39
            org.codehaus.jackson.util.TextBuffer r7 = r6._textBuffer
            char[] r7 = r7.finishCurrentSegment()
            r2 = 0
            r1 = r7
        L39:
            int r7 = r2 + 1
            char r8 = (char) r3
            r1[r2] = r8
            int r5 = r5 + 1
            r2 = r7
            goto L3
        L42:
            r7 = 46
            if (r3 == r7) goto L5f
            r7 = 101(0x65, float:1.42E-43)
            if (r3 == r7) goto L5f
            r7 = 69
            if (r3 != r7) goto L4f
            goto L5f
        L4f:
            int r7 = r6._inputPtr
            int r7 = r7 + (-1)
            r6._inputPtr = r7
            org.codehaus.jackson.util.TextBuffer r7 = r6._textBuffer
            r7.setCurrentLength(r2)
            org.codehaus.jackson.JsonToken r7 = r6.resetInt(r9, r5)
            return r7
        L5f:
            r0 = r6
            r4 = r9
            org.codehaus.jackson.JsonToken r7 = r0._parseFloatText(r1, r2, r3, r4, r5)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.impl.Utf8StreamParser._parserNumber2(char[], int, boolean, int):org.codehaus.jackson.JsonToken");
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0045, code lost:
        _reportInvalidEOF(" in a comment");
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x004a, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void _skipCComment() throws java.io.IOException, org.codehaus.jackson.JsonParseException {
        /*
            r4 = this;
            int[] r0 = org.codehaus.jackson.util.CharTypes.getInputCodeComment()
        L4:
            int r1 = r4._inputPtr
            int r2 = r4._inputEnd
            if (r1 < r2) goto L10
            boolean r1 = r4.loadMore()
            if (r1 == 0) goto L45
        L10:
            byte[] r1 = r4._inputBuffer
            int r2 = r4._inputPtr
            int r3 = r2 + 1
            r4._inputPtr = r3
            r1 = r1[r2]
            r1 = r1 & 255(0xff, float:3.57E-43)
            r2 = r0[r1]
            if (r2 == 0) goto L4
            r3 = 2
            if (r2 == r3) goto L6a
            r3 = 3
            if (r2 == r3) goto L66
            r3 = 4
            if (r2 == r3) goto L62
            r3 = 10
            if (r2 == r3) goto L5e
            r3 = 13
            if (r2 == r3) goto L5a
            r3 = 42
            if (r2 == r3) goto L39
            r4._reportInvalidChar(r1)
            goto L4
        L39:
            int r1 = r4._inputPtr
            int r2 = r4._inputEnd
            if (r1 < r2) goto L4b
            boolean r1 = r4.loadMore()
            if (r1 != 0) goto L4b
        L45:
            java.lang.String r0 = " in a comment"
            r4._reportInvalidEOF(r0)
            return
        L4b:
            byte[] r1 = r4._inputBuffer
            int r2 = r4._inputPtr
            r1 = r1[r2]
            r3 = 47
            if (r1 != r3) goto L4
            int r2 = r2 + 1
            r4._inputPtr = r2
            return
        L5a:
            r4._skipCR()
            goto L4
        L5e:
            r4._skipLF()
            goto L4
        L62:
            r4._skipUtf8_4(r1)
            goto L4
        L66:
            r4._skipUtf8_3(r1)
            goto L4
        L6a:
            r4._skipUtf8_2(r1)
            goto L4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.impl.Utf8StreamParser._skipCComment():void");
    }

    private final int _skipColon() throws IOException, JsonParseException {
        int i;
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if (b == 58) {
            int i3 = this._inputPtr;
            if (i3 < this._inputEnd && (i = bArr[i3] & 255) > 32 && i != 47) {
                this._inputPtr = i3 + 1;
                return i;
            }
        } else {
            int i4 = b & 255;
            while (true) {
                if (i4 != 9) {
                    if (i4 == 10) {
                        _skipLF();
                    } else if (i4 == 13) {
                        _skipCR();
                    } else if (i4 != 32) {
                        if (i4 != 47) {
                            break;
                        }
                        _skipComment();
                    }
                }
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr2 = this._inputBuffer;
                int i5 = this._inputPtr;
                this._inputPtr = i5 + 1;
                i4 = bArr2[i5] & 255;
            }
            if (i4 < 32) {
                _throwInvalidSpace(i4);
            }
            if (i4 != 58) {
                _reportUnexpectedChar(i4, "was expecting a colon to separate field name and value");
            }
        }
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected end-of-input within/between ");
                outline107.append(this._parsingContext.getTypeDesc());
                outline107.append(" entries");
                throw _constructError(outline107.toString());
            }
            byte[] bArr3 = this._inputBuffer;
            int i6 = this._inputPtr;
            this._inputPtr = i6 + 1;
            int i7 = bArr3[i6] & 255;
            if (i7 > 32) {
                if (i7 != 47) {
                    return i7;
                }
                _skipComment();
            } else if (i7 != 32) {
                if (i7 == 10) {
                    _skipLF();
                } else if (i7 == 13) {
                    _skipCR();
                } else if (i7 != 9) {
                    _throwInvalidSpace(i7);
                }
            }
        }
    }

    private final void _skipComment() throws IOException, JsonParseException {
        if (!isEnabled(JsonParser.Feature.ALLOW_COMMENTS)) {
            _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(" in a comment");
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        int i2 = bArr[i] & 255;
        if (i2 == 47) {
            _skipCppComment();
        } else if (i2 == 42) {
            _skipCComment();
        } else {
            _reportUnexpectedChar(i2, "was expecting either '*' or '/' for a comment");
        }
    }

    private final void _skipCppComment() throws IOException, JsonParseException {
        int[] inputCodeComment = CharTypes.getInputCodeComment();
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i = this._inputPtr;
                this._inputPtr = i + 1;
                int i2 = bArr[i] & 255;
                int i3 = inputCodeComment[i2];
                if (i3 != 0) {
                    if (i3 == 2) {
                        _skipUtf8_2(i2);
                    } else if (i3 == 3) {
                        _skipUtf8_3(i2);
                    } else if (i3 == 4) {
                        _skipUtf8_4(i2);
                    } else if (i3 == 10) {
                        _skipLF();
                        return;
                    } else if (i3 == 13) {
                        _skipCR();
                        return;
                    } else if (i3 != 42) {
                        _reportInvalidChar(i2);
                    }
                }
            } else {
                return;
            }
        }
    }

    private final void _skipUtf8_2(int i) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
    }

    private final void _skipUtf8_3(int i) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b2 = bArr2[i3];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & 255, this._inputPtr);
        }
    }

    private final void _skipUtf8_4(int i) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b2 = bArr2[i3];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & 255, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int i4 = this._inputPtr;
        this._inputPtr = i4 + 1;
        byte b3 = bArr3[i4];
        if ((b3 & 192) != 128) {
            _reportInvalidOther(b3 & 255, this._inputPtr);
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
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            int i2 = bArr[i] & 255;
            if (i2 > 32) {
                if (i2 != 47) {
                    return i2;
                }
                _skipComment();
            } else if (i2 != 32) {
                if (i2 == 10) {
                    _skipLF();
                } else if (i2 == 13) {
                    _skipCR();
                } else if (i2 != 9) {
                    _throwInvalidSpace(i2);
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
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            int i2 = bArr[i] & 255;
            if (i2 > 32) {
                if (i2 != 47) {
                    return i2;
                }
                _skipComment();
            } else if (i2 != 32) {
                if (i2 == 10) {
                    _skipLF();
                } else if (i2 == 13) {
                    _skipCR();
                } else if (i2 != 9) {
                    _throwInvalidSpace(i2);
                }
            }
        }
    }

    private final int _verifyNoLeadingZeroes() throws IOException, JsonParseException {
        int i;
        if ((this._inputPtr < this._inputEnd || loadMore()) && (i = this._inputBuffer[this._inputPtr] & 255) >= 48 && i <= 57) {
            if (!isEnabled(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
                reportInvalidNumber("Leading zeroes not allowed");
            }
            this._inputPtr++;
            if (i == 48) {
                do {
                    if (this._inputPtr >= this._inputEnd && !loadMore()) {
                        break;
                    }
                    byte[] bArr = this._inputBuffer;
                    int i2 = this._inputPtr;
                    i = bArr[i2] & 255;
                    if (i < 48 || i > 57) {
                        return 48;
                    }
                    this._inputPtr = i2 + 1;
                } while (i == 48);
            }
            return i;
        }
        return 48;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00e3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final org.codehaus.jackson.sym.Name addName(int[] r18, int r19, int r20) throws org.codehaus.jackson.JsonParseException {
        /*
            Method dump skipped, instructions count: 267
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.impl.Utf8StreamParser.addName(int[], int, int):org.codehaus.jackson.sym.Name");
    }

    private final Name findName(int i, int i2) throws JsonParseException {
        Name findName = this._symbols.findName(i);
        if (findName != null) {
            return findName;
        }
        int[] iArr = this._quadBuffer;
        iArr[0] = i;
        return addName(iArr, 1, i2);
    }

    public static int[] growArrayBy(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        int length = iArr.length;
        int[] iArr2 = new int[i + length];
        System.arraycopy(iArr, 0, iArr2, 0, length);
        return iArr2;
    }

    private int nextByte() throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        return bArr[i] & 255;
    }

    private final Name parseFieldName(int i, int i2, int i3) throws IOException, JsonParseException {
        return parseEscapedFieldName(this._quadBuffer, 0, i, i2, i3);
    }

    @Override // org.codehaus.jackson.impl.JsonParserBase
    protected void _closeInput() throws IOException {
        if (this._inputStream != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE)) {
                this._inputStream.close();
            }
            this._inputStream = null;
        }
    }

    protected byte[] _decodeBase64(Base64Variant base64Variant) throws IOException, JsonParseException {
        ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            int i2 = bArr[i] & 255;
            if (i2 > 32) {
                int decodeBase64Char = base64Variant.decodeBase64Char(i2);
                if (decodeBase64Char < 0) {
                    if (i2 == 34) {
                        return _getByteArrayBuilder.toByteArray();
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant, i2, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr2 = this._inputBuffer;
                int i3 = this._inputPtr;
                this._inputPtr = i3 + 1;
                int i4 = bArr2[i3] & 255;
                int decodeBase64Char2 = base64Variant.decodeBase64Char(i4);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = _decodeBase64Escape(base64Variant, i4, 1);
                }
                int i5 = (decodeBase64Char << 6) | decodeBase64Char2;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr3 = this._inputBuffer;
                int i6 = this._inputPtr;
                this._inputPtr = i6 + 1;
                int i7 = bArr3[i6] & 255;
                int decodeBase64Char3 = base64Variant.decodeBase64Char(i7);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (i7 == 34 && !base64Variant.usesPadding()) {
                            _getByteArrayBuilder.append(i5 >> 4);
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char3 = _decodeBase64Escape(base64Variant, i7, 2);
                    }
                    if (decodeBase64Char3 == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        byte[] bArr4 = this._inputBuffer;
                        int i8 = this._inputPtr;
                        this._inputPtr = i8 + 1;
                        int i9 = bArr4[i8] & 255;
                        if (base64Variant.usesPaddingChar(i9)) {
                            _getByteArrayBuilder.append(i5 >> 4);
                        } else {
                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("expected padding character '");
                            outline107.append(base64Variant.getPaddingChar());
                            outline107.append("'");
                            throw reportInvalidBase64Char(base64Variant, i9, 3, outline107.toString());
                        }
                    }
                }
                int i10 = (i5 << 6) | decodeBase64Char3;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr5 = this._inputBuffer;
                int i11 = this._inputPtr;
                this._inputPtr = i11 + 1;
                int i12 = bArr5[i11] & 255;
                int decodeBase64Char4 = base64Variant.decodeBase64Char(i12);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (i12 == 34 && !base64Variant.usesPadding()) {
                            _getByteArrayBuilder.appendTwoBytes(i10 >> 2);
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char4 = _decodeBase64Escape(base64Variant, i12, 3);
                    }
                    if (decodeBase64Char4 == -2) {
                        _getByteArrayBuilder.appendTwoBytes(i10 >> 2);
                    }
                }
                _getByteArrayBuilder.appendThreeBytes((i10 << 6) | decodeBase64Char4);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected int _decodeCharForError(int r7) throws java.io.IOException, org.codehaus.jackson.JsonParseException {
        /*
            r6 = this;
            if (r7 >= 0) goto L64
            r0 = r7 & 224(0xe0, float:3.14E-43)
            r1 = 2
            r2 = 1
            r3 = 192(0xc0, float:2.69E-43)
            if (r0 != r3) goto Le
            r7 = r7 & 31
        Lc:
            r0 = r2
            goto L28
        Le:
            r0 = r7 & 240(0xf0, float:3.36E-43)
            r3 = 224(0xe0, float:3.14E-43)
            if (r0 != r3) goto L18
            r7 = r7 & 15
            r0 = r1
            goto L28
        L18:
            r0 = r7 & 248(0xf8, float:3.48E-43)
            r3 = 240(0xf0, float:3.36E-43)
            if (r0 != r3) goto L22
            r7 = r7 & 7
            r0 = 3
            goto L28
        L22:
            r0 = r7 & 255(0xff, float:3.57E-43)
            r6._reportInvalidInitial(r0)
            goto Lc
        L28:
            int r3 = r6.nextByte()
            r4 = r3 & 192(0xc0, float:2.69E-43)
            r5 = 128(0x80, float:1.794E-43)
            if (r4 == r5) goto L37
            r4 = r3 & 255(0xff, float:3.57E-43)
            r6._reportInvalidOther(r4)
        L37:
            int r7 = r7 << 6
            r3 = r3 & 63
            r7 = r7 | r3
            if (r0 <= r2) goto L64
            int r2 = r6.nextByte()
            r3 = r2 & 192(0xc0, float:2.69E-43)
            if (r3 == r5) goto L4b
            r3 = r2 & 255(0xff, float:3.57E-43)
            r6._reportInvalidOther(r3)
        L4b:
            int r7 = r7 << 6
            r2 = r2 & 63
            r7 = r7 | r2
            if (r0 <= r1) goto L64
            int r0 = r6.nextByte()
            r1 = r0 & 192(0xc0, float:2.69E-43)
            if (r1 == r5) goto L5f
            r1 = r0 & 255(0xff, float:3.57E-43)
            r6._reportInvalidOther(r1)
        L5f:
            int r7 = r7 << 6
            r0 = r0 & 63
            r7 = r7 | r0
        L64:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.impl.Utf8StreamParser._decodeCharForError(int):int");
    }

    @Override // org.codehaus.jackson.impl.JsonParserBase
    protected final char _decodeEscaped() throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(" in character escape sequence");
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte b = bArr[i];
        if (b == 34 || b == 47 || b == 92) {
            return (char) b;
        }
        if (b == 98) {
            return '\b';
        }
        if (b == 102) {
            return '\f';
        }
        if (b == 110) {
            return '\n';
        }
        if (b == 114) {
            return '\r';
        }
        if (b == 116) {
            return '\t';
        }
        if (b != 117) {
            return _handleUnrecognizedCharacterEscape((char) _decodeCharForError(b));
        }
        int i2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in character escape sequence");
            }
            byte[] bArr2 = this._inputBuffer;
            int i4 = this._inputPtr;
            this._inputPtr = i4 + 1;
            byte b2 = bArr2[i4];
            int charToHex = CharTypes.charToHex(b2);
            if (charToHex < 0) {
                _reportUnexpectedChar(b2, "expected a hex-digit for character escape sequence");
            }
            i2 = (i2 << 4) | charToHex;
        }
        return (char) i2;
    }

    @Override // org.codehaus.jackson.impl.JsonParserBase
    protected void _finishString() throws IOException, JsonParseException {
        int i = this._inputPtr;
        if (i >= this._inputEnd) {
            loadMoreGuaranteed();
            i = this._inputPtr;
        }
        int i2 = 0;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = sInputCodesUtf8;
        int min = Math.min(this._inputEnd, emptyAndGetCurrentSegment.length + i);
        byte[] bArr = this._inputBuffer;
        while (true) {
            if (i >= min) {
                break;
            }
            int i3 = bArr[i] & 255;
            if (iArr[i3] == 0) {
                i++;
                emptyAndGetCurrentSegment[i2] = (char) i3;
                i2++;
            } else if (i3 == 34) {
                this._inputPtr = i + 1;
                this._textBuffer.setCurrentLength(i2);
                return;
            }
        }
        this._inputPtr = i;
        _finishString2(emptyAndGetCurrentSegment, i2);
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

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0045, code lost:
        if (r6 != 39) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0047, code lost:
        r10._textBuffer.setCurrentLength(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004e, code lost:
        return org.codehaus.jackson.JsonToken.VALUE_STRING;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004f, code lost:
        r5 = r1[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0052, code lost:
        if (r5 == 1) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0055, code lost:
        if (r5 == 2) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0058, code lost:
        if (r5 == 3) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x005b, code lost:
        if (r5 == 4) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x005f, code lost:
        if (r6 >= 32) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0061, code lost:
        _throwUnquotedSpace(r6, "string value");
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0066, code lost:
        _reportInvalidChar(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x006a, code lost:
        r5 = _decodeUtf8_4(r6);
        r6 = r4 + 1;
        r0[r4] = (char) (55296 | (r5 >> 10));
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x007a, code lost:
        if (r6 < r0.length) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x007c, code lost:
        r0 = r10._textBuffer.finishCurrentSegment();
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0083, code lost:
        r9 = r6;
        r6 = 56320 | (r5 & 1023);
        r4 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0092, code lost:
        if ((r10._inputEnd - r10._inputPtr) < 2) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0094, code lost:
        r6 = _decodeUtf8_3fast(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0099, code lost:
        r6 = _decodeUtf8_3(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x009e, code lost:
        r6 = _decodeUtf8_2(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00a5, code lost:
        if (r6 == 34) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a7, code lost:
        r6 = _decodeEscaped();
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00ac, code lost:
        if (r4 < r0.length) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00ae, code lost:
        r0 = r10._textBuffer.finishCurrentSegment();
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00b5, code lost:
        r0[r4] = (char) r6;
        r4 = r4 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected org.codehaus.jackson.JsonToken _handleApostropheValue() throws java.io.IOException, org.codehaus.jackson.JsonParseException {
        /*
            r10 = this;
            org.codehaus.jackson.util.TextBuffer r0 = r10._textBuffer
            char[] r0 = r0.emptyAndGetCurrentSegment()
            int[] r1 = org.codehaus.jackson.impl.Utf8StreamParser.sInputCodesUtf8
            byte[] r2 = r10._inputBuffer
            r3 = 0
            r4 = r3
        Lc:
            int r5 = r10._inputPtr
            int r6 = r10._inputEnd
            if (r5 < r6) goto L15
            r10.loadMoreGuaranteed()
        L15:
            int r5 = r0.length
            if (r4 < r5) goto L1f
            org.codehaus.jackson.util.TextBuffer r0 = r10._textBuffer
            char[] r0 = r0.finishCurrentSegment()
            r4 = r3
        L1f:
            int r5 = r10._inputEnd
            int r6 = r10._inputPtr
            int r7 = r0.length
            int r7 = r7 - r4
            int r6 = r6 + r7
            if (r6 >= r5) goto L29
            r5 = r6
        L29:
            int r6 = r10._inputPtr
            if (r6 >= r5) goto Lc
            int r7 = r6 + 1
            r10._inputPtr = r7
            r6 = r2[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            r7 = 39
            if (r6 == r7) goto L45
            r8 = r1[r6]
            if (r8 == 0) goto L3e
            goto L45
        L3e:
            int r7 = r4 + 1
            char r6 = (char) r6
            r0[r4] = r6
            r4 = r7
            goto L29
        L45:
            if (r6 != r7) goto L4f
            org.codehaus.jackson.util.TextBuffer r0 = r10._textBuffer
            r0.setCurrentLength(r4)
            org.codehaus.jackson.JsonToken r0 = org.codehaus.jackson.JsonToken.VALUE_STRING
            return r0
        L4f:
            r5 = r1[r6]
            r7 = 1
            if (r5 == r7) goto La3
            r7 = 2
            if (r5 == r7) goto L9e
            r8 = 3
            if (r5 == r8) goto L8d
            r7 = 4
            if (r5 == r7) goto L6a
            r5 = 32
            if (r6 >= r5) goto L66
            java.lang.String r5 = "string value"
            r10._throwUnquotedSpace(r6, r5)
        L66:
            r10._reportInvalidChar(r6)
            goto Lab
        L6a:
            int r5 = r10._decodeUtf8_4(r6)
            int r6 = r4 + 1
            r7 = 55296(0xd800, float:7.7486E-41)
            int r8 = r5 >> 10
            r7 = r7 | r8
            char r7 = (char) r7
            r0[r4] = r7
            int r4 = r0.length
            if (r6 < r4) goto L83
            org.codehaus.jackson.util.TextBuffer r0 = r10._textBuffer
            char[] r0 = r0.finishCurrentSegment()
            r6 = r3
        L83:
            r4 = 56320(0xdc00, float:7.8921E-41)
            r5 = r5 & 1023(0x3ff, float:1.434E-42)
            r4 = r4 | r5
            r9 = r6
            r6 = r4
            r4 = r9
            goto Lab
        L8d:
            int r5 = r10._inputEnd
            int r8 = r10._inputPtr
            int r5 = r5 - r8
            if (r5 < r7) goto L99
            int r6 = r10._decodeUtf8_3fast(r6)
            goto Lab
        L99:
            int r6 = r10._decodeUtf8_3(r6)
            goto Lab
        L9e:
            int r6 = r10._decodeUtf8_2(r6)
            goto Lab
        La3:
            r5 = 34
            if (r6 == r5) goto Lab
            char r6 = r10._decodeEscaped()
        Lab:
            int r5 = r0.length
            if (r4 < r5) goto Lb5
            org.codehaus.jackson.util.TextBuffer r0 = r10._textBuffer
            char[] r0 = r0.finishCurrentSegment()
            r4 = r3
        Lb5:
            int r5 = r4 + 1
            char r6 = (char) r6
            r0[r4] = r6
            r4 = r5
            goto Lc
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.impl.Utf8StreamParser._handleApostropheValue():org.codehaus.jackson.JsonToken");
    }

    protected JsonToken _handleInvalidNumberStart(int i, boolean z) throws IOException, JsonParseException {
        if (i == 73) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOFInValue();
            }
            byte[] bArr = this._inputBuffer;
            int i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            i = bArr[i2];
            double d = Double.NEGATIVE_INFINITY;
            if (i == 78) {
                String str = z ? "-INF" : "+INF";
                _matchToken(str, 3);
                if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    if (!z) {
                        d = Double.POSITIVE_INFINITY;
                    }
                    return resetAsNaN(str, d);
                }
                _reportError(GeneratedOutlineSupport1.outline75("Non-standard token '", str, "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow"));
            } else if (i == 110) {
                String str2 = z ? "-Infinity" : "+Infinity";
                _matchToken(str2, 3);
                if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    if (!z) {
                        d = Double.POSITIVE_INFINITY;
                    }
                    return resetAsNaN(str2, d);
                }
                _reportError(GeneratedOutlineSupport1.outline75("Non-standard token '", str2, "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow"));
            }
        }
        reportUnexpectedNumberChar(i, "expected digit (0-9) to follow minus sign, for valid numeric value");
        return null;
    }

    protected JsonToken _handleUnexpectedValue(int i) throws IOException, JsonParseException {
        if (i != 39) {
            if (i == 43) {
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    _reportInvalidEOFInValue();
                }
                byte[] bArr = this._inputBuffer;
                int i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                return _handleInvalidNumberStart(bArr[i2] & 255, false);
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

    protected final Name _handleUnusualFieldName(int i) throws IOException, JsonParseException {
        if (i == 39 && isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
            return _parseApostropheFieldName();
        }
        if (!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
            _reportUnexpectedChar(i, "was expecting double-quote to start field name");
        }
        int[] inputCodeUtf8JsNames = CharTypes.getInputCodeUtf8JsNames();
        if (inputCodeUtf8JsNames[i] != 0) {
            _reportUnexpectedChar(i, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int[] iArr = this._quadBuffer;
        int i2 = 0;
        int i3 = i;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (i2 < 4) {
                i2++;
                i5 = (i5 << 8) | i3;
            } else {
                if (i4 >= iArr.length) {
                    iArr = growArrayBy(iArr, iArr.length);
                    this._quadBuffer = iArr;
                }
                iArr[i4] = i5;
                i5 = i3;
                i2 = 1;
                i4++;
            }
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in field name");
            }
            byte[] bArr = this._inputBuffer;
            int i6 = this._inputPtr;
            i3 = bArr[i6] & 255;
            if (inputCodeUtf8JsNames[i3] != 0) {
                break;
            }
            this._inputPtr = i6 + 1;
        }
        if (i2 > 0) {
            if (i4 >= iArr.length) {
                int[] growArrayBy = growArrayBy(iArr, iArr.length);
                this._quadBuffer = growArrayBy;
                iArr = growArrayBy;
            }
            iArr[i4] = i5;
            i4++;
        }
        Name findName = this._symbols.findName(iArr, i4);
        return findName == null ? addName(iArr, i4, i2) : findName;
    }

    protected final boolean _loadToHaveAtLeast(int i) throws IOException {
        if (this._inputStream == null) {
            return false;
        }
        int i2 = this._inputEnd;
        int i3 = this._inputPtr;
        int i4 = i2 - i3;
        if (i4 > 0 && i3 > 0) {
            this._currInputProcessed += i3;
            this._currInputRowStart -= i3;
            byte[] bArr = this._inputBuffer;
            System.arraycopy(bArr, i3, bArr, 0, i4);
            this._inputEnd = i4;
        } else {
            this._inputEnd = 0;
        }
        this._inputPtr = 0;
        while (true) {
            int i5 = this._inputEnd;
            if (i5 >= i) {
                return true;
            }
            InputStream inputStream = this._inputStream;
            byte[] bArr2 = this._inputBuffer;
            int read = inputStream.read(bArr2, i5, bArr2.length - i5);
            if (read < 1) {
                _closeInput();
                if (read == 0) {
                    throw new IOException(GeneratedOutlineSupport1.outline52("InputStream.read() returned 0 characters when trying to read ", i4, " bytes"));
                }
                return false;
            }
            this._inputEnd += read;
        }
    }

    protected final void _matchToken(String str, int i) throws IOException, JsonParseException {
        int i2;
        int length = str.length();
        do {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in a value");
            }
            if (this._inputBuffer[this._inputPtr] != str.charAt(i)) {
                _reportInvalidToken(str.substring(0, i), "'null', 'true', 'false' or NaN");
            }
            this._inputPtr++;
            i++;
        } while (i < length);
        if ((this._inputPtr < this._inputEnd || loadMore()) && (i2 = this._inputBuffer[this._inputPtr] & 255) >= 48 && i2 != 93 && i2 != 125 && Character.isJavaIdentifierPart((char) _decodeCharForError(i2))) {
            this._inputPtr++;
            _reportInvalidToken(str.substring(0, i), "'null', 'true', 'false' or NaN");
        }
    }

    protected final Name _parseApostropheFieldName() throws IOException, JsonParseException {
        int i;
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(": was expecting closing ''' for name");
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        int i3 = bArr[i2] & 255;
        if (i3 == 39) {
            return BytesToNameCanonicalizer.getEmptyName();
        }
        int[] iArr = this._quadBuffer;
        int[] iArr2 = sInputCodesLatin1;
        int[] iArr3 = iArr;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i3 != 39) {
            if (i3 != 34 && iArr2[i3] != 0) {
                if (i3 != 92) {
                    _throwUnquotedSpace(i3, "name");
                } else {
                    i3 = _decodeEscaped();
                }
                if (i3 > 127) {
                    if (i4 >= 4) {
                        if (i5 >= iArr3.length) {
                            int[] growArrayBy = growArrayBy(iArr3, iArr3.length);
                            this._quadBuffer = growArrayBy;
                            iArr3 = growArrayBy;
                        }
                        iArr3[i5] = i6;
                        i5++;
                        i4 = 0;
                        i6 = 0;
                    }
                    if (i3 < 2048) {
                        i6 = (i6 << 8) | (i3 >> 6) | 192;
                        i4++;
                    } else {
                        int i7 = (i6 << 8) | (i3 >> 12) | 224;
                        int i8 = i4 + 1;
                        if (i8 >= 4) {
                            if (i5 >= iArr3.length) {
                                int[] growArrayBy2 = growArrayBy(iArr3, iArr3.length);
                                this._quadBuffer = growArrayBy2;
                                iArr3 = growArrayBy2;
                            }
                            iArr3[i5] = i7;
                            i5++;
                            i8 = 0;
                            i7 = 0;
                        }
                        i6 = (i7 << 8) | ((i3 >> 6) & 63) | 128;
                        i4 = i8 + 1;
                    }
                    i3 = (i3 & 63) | 128;
                }
            }
            if (i4 < 4) {
                i4++;
                i6 = i3 | (i6 << 8);
            } else {
                if (i5 >= iArr3.length) {
                    iArr3 = growArrayBy(iArr3, iArr3.length);
                    this._quadBuffer = iArr3;
                }
                iArr3[i5] = i6;
                i6 = i3;
                i5++;
                i4 = 1;
            }
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in field name");
            }
            byte[] bArr2 = this._inputBuffer;
            int i9 = this._inputPtr;
            this._inputPtr = i9 + 1;
            i3 = bArr2[i9] & 255;
        }
        if (i4 > 0) {
            if (i5 >= iArr3.length) {
                int[] growArrayBy3 = growArrayBy(iArr3, iArr3.length);
                this._quadBuffer = growArrayBy3;
                iArr3 = growArrayBy3;
            }
            i = i5 + 1;
            iArr3[i5] = i6;
        } else {
            i = i5;
        }
        Name findName = this._symbols.findName(iArr3, i);
        return findName == null ? addName(iArr3, i, i4) : findName;
    }

    protected final Name _parseFieldName(int i) throws IOException, JsonParseException {
        if (i != 34) {
            return _handleUnusualFieldName(i);
        }
        int i2 = this._inputPtr;
        if (i2 + 9 > this._inputEnd) {
            return slowParseFieldName();
        }
        byte[] bArr = this._inputBuffer;
        int[] iArr = sInputCodesLatin1;
        this._inputPtr = i2 + 1;
        int i3 = bArr[i2] & 255;
        if (iArr[i3] != 0) {
            if (i3 == 34) {
                return BytesToNameCanonicalizer.getEmptyName();
            }
            return parseFieldName(0, i3, 0);
        }
        int i4 = this._inputPtr;
        this._inputPtr = i4 + 1;
        int i5 = bArr[i4] & 255;
        if (iArr[i5] != 0) {
            if (i5 == 34) {
                return findName(i3, 1);
            }
            return parseFieldName(i3, i5, 1);
        }
        int i6 = (i3 << 8) | i5;
        int i7 = this._inputPtr;
        this._inputPtr = i7 + 1;
        int i8 = bArr[i7] & 255;
        if (iArr[i8] != 0) {
            if (i8 == 34) {
                return findName(i6, 2);
            }
            return parseFieldName(i6, i8, 2);
        }
        int i9 = (i6 << 8) | i8;
        int i10 = this._inputPtr;
        this._inputPtr = i10 + 1;
        int i11 = bArr[i10] & 255;
        if (iArr[i11] != 0) {
            if (i11 == 34) {
                return findName(i9, 3);
            }
            return parseFieldName(i9, i11, 3);
        }
        int i12 = (i9 << 8) | i11;
        int i13 = this._inputPtr;
        this._inputPtr = i13 + 1;
        int i14 = bArr[i13] & 255;
        if (iArr[i14] == 0) {
            this._quad1 = i12;
            return parseMediumFieldName(i14, iArr);
        } else if (i14 == 34) {
            return findName(i12, 4);
        } else {
            return parseFieldName(i12, i14, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.codehaus.jackson.impl.JsonParserBase
    public void _releaseBuffers() throws IOException {
        byte[] bArr;
        super._releaseBuffers();
        if (!this._bufferRecyclable || (bArr = this._inputBuffer) == null) {
            return;
        }
        this._inputBuffer = null;
        this._ioContext.releaseReadIOBuffer(bArr);
    }

    protected void _reportInvalidChar(int i) throws JsonParseException {
        if (i < 32) {
            _throwInvalidSpace(i);
        }
        _reportInvalidInitial(i);
    }

    protected void _reportInvalidInitial(int i) throws JsonParseException {
        _reportError(GeneratedOutlineSupport1.outline32(i, GeneratedOutlineSupport1.outline107("Invalid UTF-8 start byte 0x")));
    }

    protected void _reportInvalidOther(int i) throws JsonParseException {
        _reportError(GeneratedOutlineSupport1.outline32(i, GeneratedOutlineSupport1.outline107("Invalid UTF-8 middle byte 0x")));
    }

    protected void _reportInvalidToken(String str, String str2) throws IOException, JsonParseException {
        StringBuilder sb = new StringBuilder(str);
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                break;
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char _decodeCharForError = (char) _decodeCharForError(bArr[i]);
            if (!Character.isJavaIdentifierPart(_decodeCharForError)) {
                break;
            }
            sb.append(_decodeCharForError);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unrecognized token '");
        outline107.append(sb.toString());
        outline107.append("': was expecting ");
        outline107.append(str2);
        _reportError(outline107.toString());
    }

    protected final void _skipCR() throws IOException {
        if (this._inputPtr < this._inputEnd || loadMore()) {
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            if (bArr[i] == 10) {
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
        int[] iArr = sInputCodesUtf8;
        byte[] bArr = this._inputBuffer;
        while (true) {
            int i = this._inputPtr;
            int i2 = this._inputEnd;
            if (i >= i2) {
                loadMoreGuaranteed();
                i = this._inputPtr;
                i2 = this._inputEnd;
            }
            while (true) {
                if (i < i2) {
                    int i3 = i + 1;
                    int i4 = bArr[i] & 255;
                    if (iArr[i4] != 0) {
                        this._inputPtr = i3;
                        if (i4 == 34) {
                            return;
                        }
                        int i5 = iArr[i4];
                        if (i5 == 1) {
                            _decodeEscaped();
                        } else if (i5 == 2) {
                            _skipUtf8_2(i4);
                        } else if (i5 == 3) {
                            _skipUtf8_3(i4);
                        } else if (i5 == 4) {
                            _skipUtf8_4(i4);
                        } else if (i4 < 32) {
                            _throwUnquotedSpace(i4, "string value");
                        } else {
                            _reportInvalidChar(i4);
                        }
                    } else {
                        i = i3;
                    }
                } else {
                    this._inputPtr = i;
                    break;
                }
            }
        }
    }

    @Override // org.codehaus.jackson.impl.JsonParserBase, org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        this._symbols.release();
    }

    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
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

    @Override // org.codehaus.jackson.JsonParser
    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    @Override // org.codehaus.jackson.JsonParser
    public Object getInputSource() {
        return this._inputStream;
    }

    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
    public String getText() throws IOException, JsonParseException {
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

    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
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

    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
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
    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int getTextOffset() throws java.io.IOException, org.codehaus.jackson.JsonParseException {
        /*
            r3 = this;
            org.codehaus.jackson.JsonToken r0 = r3._currToken
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
            org.codehaus.jackson.util.TextBuffer r0 = r3._textBuffer
            int r0 = r0.getTextOffset()
            return r0
        L28:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.impl.Utf8StreamParser.getTextOffset():int");
    }

    @Override // org.codehaus.jackson.impl.JsonParserBase
    protected final boolean loadMore() throws IOException {
        long j = this._currInputProcessed;
        int i = this._inputEnd;
        this._currInputProcessed = j + i;
        this._currInputRowStart -= i;
        InputStream inputStream = this._inputStream;
        if (inputStream != null) {
            byte[] bArr = this._inputBuffer;
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read > 0) {
                this._inputPtr = 0;
                this._inputEnd = read;
                return true;
            }
            _closeInput();
            if (read == 0) {
                throw new IOException(GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("InputStream.read() returned 0 characters when trying to read "), this._inputBuffer.length, " bytes"));
            }
        }
        return false;
    }

    @Override // org.codehaus.jackson.JsonParser
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

    @Override // org.codehaus.jackson.JsonParser
    public boolean nextFieldName(SerializableString serializableString) throws IOException, JsonParseException {
        this._numTypesValid = 0;
        if (this._currToken == JsonToken.FIELD_NAME) {
            _nextAfterName();
            return false;
        }
        if (this._tokenIncomplete) {
            _skipString();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this._currToken = null;
            return false;
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
            this._parsingContext = this._parsingContext.mo12871getParent();
            this._currToken = JsonToken.END_ARRAY;
            return false;
        } else if (_skipWSOrEnd == 125) {
            if (!this._parsingContext.inObject()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, JsonReaderKt.END_LIST);
            }
            this._parsingContext = this._parsingContext.mo12871getParent();
            this._currToken = JsonToken.END_OBJECT;
            return false;
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
            if (!this._parsingContext.inObject()) {
                _nextTokenNotInObject(_skipWSOrEnd);
                return false;
            }
            if (_skipWSOrEnd == 34) {
                byte[] asQuotedUTF8 = serializableString.asQuotedUTF8();
                int length = asQuotedUTF8.length;
                int i2 = this._inputPtr;
                if (i2 + length < this._inputEnd) {
                    int i3 = i2 + length;
                    if (this._inputBuffer[i3] == 34) {
                        for (int i4 = 0; i4 != length; i4++) {
                            if (asQuotedUTF8[i4] == this._inputBuffer[i2 + i4]) {
                            }
                        }
                        this._inputPtr = i3 + 1;
                        this._parsingContext.setCurrentName(serializableString.getValue());
                        this._currToken = JsonToken.FIELD_NAME;
                        _isNextTokenNameYes();
                        return true;
                    }
                }
            }
            _isNextTokenNameNo(_skipWSOrEnd);
            return false;
        }
    }

    @Override // org.codehaus.jackson.JsonParser
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

    @Override // org.codehaus.jackson.JsonParser
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

    @Override // org.codehaus.jackson.JsonParser
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

    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
    public JsonToken nextToken() throws IOException, JsonParseException {
        JsonToken parseNumberText;
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
            this._parsingContext = this._parsingContext.mo12871getParent();
            JsonToken jsonToken = JsonToken.END_ARRAY;
            this._currToken = jsonToken;
            return jsonToken;
        } else if (_skipWSOrEnd == 125) {
            if (!this._parsingContext.inObject()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, JsonReaderKt.END_LIST);
            }
            this._parsingContext = this._parsingContext.mo12871getParent();
            JsonToken jsonToken2 = JsonToken.END_OBJECT;
            this._currToken = jsonToken2;
            return jsonToken2;
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
            if (!this._parsingContext.inObject()) {
                return _nextTokenNotInObject(_skipWSOrEnd);
            }
            this._parsingContext.setCurrentName(_parseFieldName(_skipWSOrEnd).getName());
            this._currToken = JsonToken.FIELD_NAME;
            int _skipWS = _skipWS();
            if (_skipWS != 58) {
                _reportUnexpectedChar(_skipWS, "was expecting a colon to separate field name and value");
            }
            int _skipWS2 = _skipWS();
            if (_skipWS2 == 34) {
                this._tokenIncomplete = true;
                this._nextToken = JsonToken.VALUE_STRING;
                return this._currToken;
            }
            if (_skipWS2 != 45) {
                if (_skipWS2 != 91) {
                    if (_skipWS2 != 93) {
                        if (_skipWS2 == 102) {
                            _matchToken(PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE, 1);
                            parseNumberText = JsonToken.VALUE_FALSE;
                        } else if (_skipWS2 == 110) {
                            _matchToken("null", 1);
                            parseNumberText = JsonToken.VALUE_NULL;
                        } else {
                            if (_skipWS2 != 116) {
                                if (_skipWS2 == 123) {
                                    parseNumberText = JsonToken.START_OBJECT;
                                } else if (_skipWS2 != 125) {
                                    switch (_skipWS2) {
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
                                            parseNumberText = _handleUnexpectedValue(_skipWS2);
                                            break;
                                    }
                                }
                            }
                            _matchToken("true", 1);
                            parseNumberText = JsonToken.VALUE_TRUE;
                        }
                    }
                    _reportUnexpectedChar(_skipWS2, "expected a value");
                    _matchToken("true", 1);
                    parseNumberText = JsonToken.VALUE_TRUE;
                } else {
                    parseNumberText = JsonToken.START_ARRAY;
                }
                this._nextToken = parseNumberText;
                return this._currToken;
            }
            parseNumberText = parseNumberText(_skipWS2);
            this._nextToken = parseNumberText;
            return this._currToken;
        }
    }

    protected Name parseEscapedFieldName(int[] iArr, int i, int i2, int i3, int i4) throws IOException, JsonParseException {
        int[] iArr2 = sInputCodesLatin1;
        while (true) {
            if (iArr2[i3] != 0) {
                if (i3 == 34) {
                    break;
                }
                if (i3 != 92) {
                    _throwUnquotedSpace(i3, "name");
                } else {
                    i3 = _decodeEscaped();
                }
                if (i3 > 127) {
                    if (i4 >= 4) {
                        if (i >= iArr.length) {
                            iArr = growArrayBy(iArr, iArr.length);
                            this._quadBuffer = iArr;
                        }
                        iArr[i] = i2;
                        i++;
                        i2 = 0;
                        i4 = 0;
                    }
                    if (i3 < 2048) {
                        i2 = (i2 << 8) | (i3 >> 6) | 192;
                        i4++;
                    } else {
                        int i5 = (i2 << 8) | (i3 >> 12) | 224;
                        int i6 = i4 + 1;
                        if (i6 >= 4) {
                            if (i >= iArr.length) {
                                iArr = growArrayBy(iArr, iArr.length);
                                this._quadBuffer = iArr;
                            }
                            iArr[i] = i5;
                            i++;
                            i5 = 0;
                            i6 = 0;
                        }
                        i2 = (i5 << 8) | ((i3 >> 6) & 63) | 128;
                        i4 = i6 + 1;
                    }
                    i3 = (i3 & 63) | 128;
                }
            }
            if (i4 < 4) {
                i4++;
                i2 = (i2 << 8) | i3;
            } else {
                if (i >= iArr.length) {
                    iArr = growArrayBy(iArr, iArr.length);
                    this._quadBuffer = iArr;
                }
                iArr[i] = i2;
                i2 = i3;
                i++;
                i4 = 1;
            }
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in field name");
            }
            byte[] bArr = this._inputBuffer;
            int i7 = this._inputPtr;
            this._inputPtr = i7 + 1;
            i3 = bArr[i7] & 255;
        }
        if (i4 > 0) {
            if (i >= iArr.length) {
                iArr = growArrayBy(iArr, iArr.length);
                this._quadBuffer = iArr;
            }
            iArr[i] = i2;
            i++;
        }
        Name findName = this._symbols.findName(iArr, i);
        return findName == null ? addName(iArr, i, i4) : findName;
    }

    protected Name parseLongFieldName(int i) throws IOException, JsonParseException {
        int[] iArr = sInputCodesLatin1;
        int i2 = 2;
        while (true) {
            int i3 = this._inputEnd;
            int i4 = this._inputPtr;
            if (i3 - i4 < 4) {
                return parseEscapedFieldName(this._quadBuffer, i2, 0, i, 0);
            }
            byte[] bArr = this._inputBuffer;
            this._inputPtr = i4 + 1;
            int i5 = bArr[i4] & 255;
            if (iArr[i5] != 0) {
                if (i5 == 34) {
                    return findName(this._quadBuffer, i2, i, 1);
                }
                return parseEscapedFieldName(this._quadBuffer, i2, i, i5, 1);
            }
            int i6 = (i << 8) | i5;
            int i7 = this._inputPtr;
            this._inputPtr = i7 + 1;
            int i8 = bArr[i7] & 255;
            if (iArr[i8] != 0) {
                if (i8 == 34) {
                    return findName(this._quadBuffer, i2, i6, 2);
                }
                return parseEscapedFieldName(this._quadBuffer, i2, i6, i8, 2);
            }
            int i9 = (i6 << 8) | i8;
            int i10 = this._inputPtr;
            this._inputPtr = i10 + 1;
            int i11 = bArr[i10] & 255;
            if (iArr[i11] != 0) {
                if (i11 == 34) {
                    return findName(this._quadBuffer, i2, i9, 3);
                }
                return parseEscapedFieldName(this._quadBuffer, i2, i9, i11, 3);
            }
            int i12 = (i9 << 8) | i11;
            int i13 = this._inputPtr;
            this._inputPtr = i13 + 1;
            int i14 = bArr[i13] & 255;
            if (iArr[i14] != 0) {
                if (i14 == 34) {
                    return findName(this._quadBuffer, i2, i12, 4);
                }
                return parseEscapedFieldName(this._quadBuffer, i2, i12, i14, 4);
            }
            int[] iArr2 = this._quadBuffer;
            if (i2 >= iArr2.length) {
                this._quadBuffer = growArrayBy(iArr2, i2);
            }
            this._quadBuffer[i2] = i12;
            i2++;
            i = i14;
        }
    }

    protected final Name parseMediumFieldName(int i, int[] iArr) throws IOException, JsonParseException {
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        int i3 = bArr[i2] & 255;
        if (iArr[i3] != 0) {
            if (i3 == 34) {
                return findName(this._quad1, i, 1);
            }
            return parseFieldName(this._quad1, i, i3, 1);
        }
        int i4 = (i << 8) | i3;
        int i5 = this._inputPtr;
        this._inputPtr = i5 + 1;
        int i6 = bArr[i5] & 255;
        if (iArr[i6] != 0) {
            if (i6 == 34) {
                return findName(this._quad1, i4, 2);
            }
            return parseFieldName(this._quad1, i4, i6, 2);
        }
        int i7 = (i4 << 8) | i6;
        int i8 = this._inputPtr;
        this._inputPtr = i8 + 1;
        int i9 = bArr[i8] & 255;
        if (iArr[i9] != 0) {
            if (i9 == 34) {
                return findName(this._quad1, i7, 3);
            }
            return parseFieldName(this._quad1, i7, i9, 3);
        }
        int i10 = (i7 << 8) | i9;
        int i11 = this._inputPtr;
        this._inputPtr = i11 + 1;
        int i12 = bArr[i11] & 255;
        if (iArr[i12] != 0) {
            if (i12 == 34) {
                return findName(this._quad1, i10, 4);
            }
            return parseFieldName(this._quad1, i10, i12, 4);
        }
        int[] iArr2 = this._quadBuffer;
        iArr2[0] = this._quad1;
        iArr2[1] = i10;
        return parseLongFieldName(i12);
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0070, code lost:
        if (r8 == 46) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0074, code lost:
        if (r8 == 101) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0078, code lost:
        if (r8 != 69) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x007b, code lost:
        r10._inputPtr--;
        r10._textBuffer.setCurrentLength(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0089, code lost:
        return resetInt(r5, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0092, code lost:
        return _parseFloatText(r2, r0, r8, r5, r7);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected final org.codehaus.jackson.JsonToken parseNumberText(int r11) throws java.io.IOException, org.codehaus.jackson.JsonParseException {
        /*
            r10 = this;
            org.codehaus.jackson.util.TextBuffer r0 = r10._textBuffer
            char[] r2 = r0.emptyAndGetCurrentSegment()
            r0 = 45
            r1 = 0
            r3 = 1
            if (r11 != r0) goto Le
            r5 = r3
            goto Lf
        Le:
            r5 = r1
        Lf:
            r4 = 57
            r6 = 48
            if (r5 == 0) goto L38
            r2[r1] = r0
            int r11 = r10._inputPtr
            int r0 = r10._inputEnd
            if (r11 < r0) goto L20
            r10.loadMoreGuaranteed()
        L20:
            byte[] r11 = r10._inputBuffer
            int r0 = r10._inputPtr
            int r1 = r0 + 1
            r10._inputPtr = r1
            r11 = r11[r0]
            r11 = r11 & 255(0xff, float:3.57E-43)
            if (r11 < r6) goto L33
            if (r11 <= r4) goto L31
            goto L33
        L31:
            r1 = r3
            goto L38
        L33:
            org.codehaus.jackson.JsonToken r11 = r10._handleInvalidNumberStart(r11, r3)
            return r11
        L38:
            if (r11 != r6) goto L3e
            int r11 = r10._verifyNoLeadingZeroes()
        L3e:
            int r0 = r1 + 1
            char r11 = (char) r11
            r2[r1] = r11
            int r11 = r10._inputPtr
            int r1 = r2.length
            int r11 = r11 + r1
            int r1 = r10._inputEnd
            if (r11 <= r1) goto L4c
            r11 = r1
        L4c:
            r7 = r3
        L4d:
            int r1 = r10._inputPtr
            if (r1 < r11) goto L56
            org.codehaus.jackson.JsonToken r11 = r10._parserNumber2(r2, r0, r5, r7)
            return r11
        L56:
            byte[] r8 = r10._inputBuffer
            int r9 = r1 + 1
            r10._inputPtr = r9
            r1 = r8[r1]
            r8 = r1 & 255(0xff, float:3.57E-43)
            if (r8 < r6) goto L6e
            if (r8 <= r4) goto L65
            goto L6e
        L65:
            int r7 = r7 + 1
            int r1 = r0 + 1
            char r8 = (char) r8
            r2[r0] = r8
            r0 = r1
            goto L4d
        L6e:
            r11 = 46
            if (r8 == r11) goto L8a
            r11 = 101(0x65, float:1.42E-43)
            if (r8 == r11) goto L8a
            r11 = 69
            if (r8 != r11) goto L7b
            goto L8a
        L7b:
            int r11 = r10._inputPtr
            int r11 = r11 - r3
            r10._inputPtr = r11
            org.codehaus.jackson.util.TextBuffer r11 = r10._textBuffer
            r11.setCurrentLength(r0)
            org.codehaus.jackson.JsonToken r11 = r10.resetInt(r5, r7)
            return r11
        L8a:
            r1 = r10
            r3 = r0
            r4 = r8
            r6 = r7
            org.codehaus.jackson.JsonToken r11 = r1._parseFloatText(r2, r3, r4, r5, r6)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.impl.Utf8StreamParser.parseNumberText(int):org.codehaus.jackson.JsonToken");
    }

    @Override // org.codehaus.jackson.JsonParser
    public int releaseBuffered(OutputStream outputStream) throws IOException {
        int i = this._inputEnd;
        int i2 = this._inputPtr;
        int i3 = i - i2;
        if (i3 < 1) {
            return 0;
        }
        outputStream.write(this._inputBuffer, i2, i3);
        return i3;
    }

    @Override // org.codehaus.jackson.JsonParser
    public void setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
    }

    protected Name slowParseFieldName() throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(": was expecting closing '\"' for name");
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        int i2 = bArr[i] & 255;
        if (i2 == 34) {
            return BytesToNameCanonicalizer.getEmptyName();
        }
        return parseEscapedFieldName(this._quadBuffer, 0, 0, i2, 0);
    }

    private final Name parseFieldName(int i, int i2, int i3, int i4) throws IOException, JsonParseException {
        int[] iArr = this._quadBuffer;
        iArr[0] = i;
        return parseEscapedFieldName(iArr, 1, i2, i3, i4);
    }

    protected void _reportInvalidOther(int i, int i2) throws JsonParseException {
        this._inputPtr = i2;
        _reportInvalidOther(i);
    }

    private final Name findName(int i, int i2, int i3) throws JsonParseException {
        Name findName = this._symbols.findName(i, i2);
        if (findName != null) {
            return findName;
        }
        int[] iArr = this._quadBuffer;
        iArr[0] = i;
        iArr[1] = i2;
        return addName(iArr, 2, i3);
    }

    private final Name findName(int[] iArr, int i, int i2, int i3) throws JsonParseException {
        if (i >= iArr.length) {
            iArr = growArrayBy(iArr, iArr.length);
            this._quadBuffer = iArr;
        }
        int i4 = i + 1;
        iArr[i] = i2;
        Name findName = this._symbols.findName(iArr, i4);
        return findName == null ? addName(iArr, i4, i3) : findName;
    }
}
