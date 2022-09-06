package com.amazon.org.codehaus.jackson.impl;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.org.codehaus.jackson.Base64Variant;
import com.amazon.org.codehaus.jackson.JsonParseException;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonStreamContext;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.io.NumberInput;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
/* loaded from: classes13.dex */
public abstract class JsonParserMinimalBase extends JsonParser {
    protected static final int INT_APOSTROPHE = 39;
    protected static final int INT_ASTERISK = 42;
    protected static final int INT_BACKSLASH = 92;
    protected static final int INT_COLON = 58;
    protected static final int INT_COMMA = 44;
    protected static final int INT_CR = 13;
    protected static final int INT_LBRACKET = 91;
    protected static final int INT_LCURLY = 123;
    protected static final int INT_LF = 10;
    protected static final int INT_QUOTE = 34;
    protected static final int INT_RBRACKET = 93;
    protected static final int INT_RCURLY = 125;
    protected static final int INT_SLASH = 47;
    protected static final int INT_SPACE = 32;
    protected static final int INT_TAB = 9;
    protected static final int INT_b = 98;
    protected static final int INT_f = 102;
    protected static final int INT_n = 110;
    protected static final int INT_r = 114;
    protected static final int INT_t = 116;
    protected static final int INT_u = 117;

    /* renamed from: com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];

        static {
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.START_OBJECT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.START_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.END_OBJECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.END_ARRAY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NULL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonParserMinimalBase() {
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
        return new JsonParseException(str, getCurrentLocation(), th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x001b, code lost:
        if (r4 >= 0) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x001d, code lost:
        _reportInvalidBase64(r13, r2, 0, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0020, code lost:
        if (r3 < r0) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0022, code lost:
        _reportBase64EOF();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0025, code lost:
        r2 = r3 + 1;
        r3 = r11.charAt(r3);
        r6 = r13.decodeBase64Char(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002f, code lost:
        if (r6 >= 0) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0031, code lost:
        _reportInvalidBase64(r13, r3, 1, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0035, code lost:
        r3 = (r4 << 6) | r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0038, code lost:
        if (r2 < r0) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003e, code lost:
        if (r13.usesPadding() != false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0040, code lost:
        r12.append(r3 >> 4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0047, code lost:
        _reportBase64EOF();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004a, code lost:
        r4 = r2 + 1;
        r2 = r11.charAt(r2);
        r6 = r13.decodeBase64Char(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0057, code lost:
        if (r6 >= 0) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0059, code lost:
        if (r6 == (-2)) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005b, code lost:
        _reportInvalidBase64(r13, r2, 2, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x005e, code lost:
        if (r4 < r0) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0060, code lost:
        _reportBase64EOF();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0063, code lost:
        r2 = r4 + 1;
        r4 = r11.charAt(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x006d, code lost:
        if (r13.usesPaddingChar(r4) != false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x006f, code lost:
        r5 = com.android.tools.r8.GeneratedOutlineSupport1.outline107("expected padding character '");
        r5.append(r13.getPaddingChar());
        r5.append("'");
        _reportInvalidBase64(r13, r4, 3, r5.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0088, code lost:
        r12.append(r3 >> 4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x008f, code lost:
        r2 = (r3 << 6) | r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0092, code lost:
        if (r4 < r0) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0098, code lost:
        if (r13.usesPadding() != false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x009a, code lost:
        r12.appendTwoBytes(r2 >> 2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00a0, code lost:
        _reportBase64EOF();
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00a3, code lost:
        r3 = r4 + 1;
        r4 = r11.charAt(r4);
        r6 = r13.decodeBase64Char(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00ad, code lost:
        if (r6 >= 0) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00af, code lost:
        if (r6 == (-2)) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00b1, code lost:
        _reportInvalidBase64(r13, r4, 3, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00b4, code lost:
        r12.appendTwoBytes(r2 >> 2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00ba, code lost:
        r12.appendThreeBytes((r2 << 6) | r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00c0, code lost:
        r2 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00c6, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0016, code lost:
        r4 = r13.decodeBase64Char(r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void _decodeBase64(java.lang.String r11, com.amazon.org.codehaus.jackson.util.ByteArrayBuilder r12, com.amazon.org.codehaus.jackson.Base64Variant r13) throws java.io.IOException, com.amazon.org.codehaus.jackson.JsonParseException {
        /*
            r10 = this;
            int r0 = r11.length()
            r1 = 0
            r2 = r1
        L6:
            if (r2 >= r0) goto Lc6
        L8:
            int r3 = r2 + 1
            char r2 = r11.charAt(r2)
            if (r3 < r0) goto L12
            goto Lc6
        L12:
            r4 = 32
            if (r2 <= r4) goto Lc3
            int r4 = r13.decodeBase64Char(r2)
            r5 = 0
            if (r4 >= 0) goto L20
            r10._reportInvalidBase64(r13, r2, r1, r5)
        L20:
            if (r3 < r0) goto L25
            r10._reportBase64EOF()
        L25:
            int r2 = r3 + 1
            char r3 = r11.charAt(r3)
            int r6 = r13.decodeBase64Char(r3)
            if (r6 >= 0) goto L35
            r7 = 1
            r10._reportInvalidBase64(r13, r3, r7, r5)
        L35:
            int r3 = r4 << 6
            r3 = r3 | r6
            if (r2 < r0) goto L4a
            boolean r4 = r13.usesPadding()
            if (r4 != 0) goto L47
            int r11 = r3 >> 4
            r12.append(r11)
            goto Lc6
        L47:
            r10._reportBase64EOF()
        L4a:
            int r4 = r2 + 1
            char r2 = r11.charAt(r2)
            int r6 = r13.decodeBase64Char(r2)
            r7 = 3
            r8 = -2
            r9 = 2
            if (r6 >= 0) goto L8f
            if (r6 == r8) goto L5e
            r10._reportInvalidBase64(r13, r2, r9, r5)
        L5e:
            if (r4 < r0) goto L63
            r10._reportBase64EOF()
        L63:
            int r2 = r4 + 1
            char r4 = r11.charAt(r4)
            boolean r5 = r13.usesPaddingChar(r4)
            if (r5 != 0) goto L88
            java.lang.String r5 = "expected padding character '"
            java.lang.StringBuilder r5 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r5)
            char r6 = r13.getPaddingChar()
            r5.append(r6)
            java.lang.String r6 = "'"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r10._reportInvalidBase64(r13, r4, r7, r5)
        L88:
            int r3 = r3 >> 4
            r12.append(r3)
            goto L6
        L8f:
            int r2 = r3 << 6
            r2 = r2 | r6
            if (r4 < r0) goto La3
            boolean r3 = r13.usesPadding()
            if (r3 != 0) goto La0
            int r11 = r2 >> 2
            r12.appendTwoBytes(r11)
            goto Lc6
        La0:
            r10._reportBase64EOF()
        La3:
            int r3 = r4 + 1
            char r4 = r11.charAt(r4)
            int r6 = r13.decodeBase64Char(r4)
            if (r6 >= 0) goto Lba
            if (r6 == r8) goto Lb4
            r10._reportInvalidBase64(r13, r4, r7, r5)
        Lb4:
            int r2 = r2 >> 2
            r12.appendTwoBytes(r2)
            goto Lc0
        Lba:
            int r2 = r2 << 6
            r2 = r2 | r6
            r12.appendThreeBytes(r2)
        Lc0:
            r2 = r3
            goto L6
        Lc3:
            r2 = r3
            goto L8
        Lc6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase._decodeBase64(java.lang.String, com.amazon.org.codehaus.jackson.util.ByteArrayBuilder, com.amazon.org.codehaus.jackson.Base64Variant):void");
    }

    protected abstract void _handleEOF() throws JsonParseException;

    /* JADX INFO: Access modifiers changed from: protected */
    public char _handleUnrecognizedCharacterEscape(char c) throws JsonProcessingException {
        if (isEnabled(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)) {
            return c;
        }
        if (c == '\'' && isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
            return c;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unrecognized character escape ");
        outline107.append(_getCharDesc(c));
        _reportError(outline107.toString());
        return c;
    }

    protected void _reportBase64EOF() throws JsonParseException {
        throw _constructError("Unexpected end-of-String in base64 content");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void _reportError(String str) throws JsonParseException {
        throw _constructError(str);
    }

    protected void _reportInvalidBase64(Base64Variant base64Variant, char c, int i, String str) throws JsonParseException {
        String outline33;
        if (c <= ' ') {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Illegal white space character (code 0x");
            outline107.append(Integer.toHexString(c));
            outline107.append(") as character #");
            outline107.append(i + 1);
            outline107.append(" of 4-char base64 unit: can only used between units");
            outline33 = outline107.toString();
        } else if (base64Variant.usesPaddingChar(c)) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Unexpected padding character ('");
            outline1072.append(base64Variant.getPaddingChar());
            outline1072.append("') as character #");
            outline1072.append(i + 1);
            outline1072.append(" of 4-char base64 unit: padding only legal as 3rd or 4th character");
            outline33 = outline1072.toString();
        } else if (Character.isDefined(c) && !Character.isISOControl(c)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Illegal character '");
            sb.append(c);
            sb.append("' (code 0x");
            outline33 = GeneratedOutlineSupport1.outline33(c, sb, ") in base64 content");
        } else {
            outline33 = GeneratedOutlineSupport1.outline33(c, GeneratedOutlineSupport1.outline107("Illegal character (code 0x"), ") in base64 content");
        }
        if (str != null) {
            outline33 = GeneratedOutlineSupport1.outline75(outline33, RealTimeTextConstants.COLON_SPACE, str);
        }
        throw _constructError(outline33);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _reportInvalidEOF() throws JsonParseException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(" in ");
        outline107.append(this._currToken);
        _reportInvalidEOF(outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _reportInvalidEOFInValue() throws JsonParseException {
        _reportInvalidEOF(" in a value");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _reportUnexpectedChar(int i, String str) throws JsonParseException {
        String outline91 = GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Unexpected character ("), _getCharDesc(i), ")");
        if (str != null) {
            outline91 = GeneratedOutlineSupport1.outline75(outline91, RealTimeTextConstants.COLON_SPACE, str);
        }
        _reportError(outline91);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void _throwInternal() {
        throw new RuntimeException("Internal error: this code path should never get executed");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _throwInvalidSpace(int i) throws JsonParseException {
        _reportError(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Illegal character ("), _getCharDesc((char) i), "): only regular white space (\\r, \\n, \\t) is allowed between tokens"));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _throwUnquotedSpace(int i, String str) throws JsonParseException {
        if (!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS) || i >= 32) {
            _reportError(GeneratedOutlineSupport1.outline92(GeneratedOutlineSupport1.outline107("Illegal unquoted character ("), _getCharDesc((char) i), "): has to be escaped using backslash to be included in ", str));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void _wrapError(String str, Throwable th) throws JsonParseException {
        throw _constructError(str, th);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public abstract void close() throws IOException;

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public abstract byte[] getBinaryValue(Base64Variant base64Variant) throws IOException, JsonParseException;

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public abstract String getCurrentName() throws IOException, JsonParseException;

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    /* renamed from: getParsingContext */
    public abstract JsonStreamContext mo4092getParsingContext();

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public abstract String getText() throws IOException, JsonParseException;

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public abstract char[] getTextCharacters() throws IOException, JsonParseException;

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public abstract int getTextLength() throws IOException, JsonParseException;

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public abstract int getTextOffset() throws IOException, JsonParseException;

    /* JADX WARN: Removed duplicated region for block: B:13:0x002d A[RETURN] */
    @Override // com.amazon.org.codehaus.jackson.JsonParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean getValueAsBoolean(boolean r4) throws java.io.IOException, com.amazon.org.codehaus.jackson.JsonParseException {
        /*
            r3 = this;
            com.amazon.org.codehaus.jackson.JsonToken r0 = r3._currToken
            if (r0 == 0) goto L38
            int r0 = r0.ordinal()
            r1 = 0
            r2 = 1
            switch(r0) {
                case 6: goto Le;
                case 7: goto L1d;
                case 8: goto L30;
                case 9: goto Ld;
                case 10: goto L2f;
                case 11: goto L2e;
                case 12: goto L2e;
                default: goto Ld;
            }
        Ld:
            goto L38
        Le:
            java.lang.Object r0 = r3.getEmbeddedObject()
            boolean r1 = r0 instanceof java.lang.Boolean
            if (r1 == 0) goto L1d
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r4 = r0.booleanValue()
            return r4
        L1d:
            java.lang.String r0 = r3.getText()
            java.lang.String r0 = r0.trim()
            java.lang.String r1 = "true"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L38
            return r2
        L2e:
            return r1
        L2f:
            return r2
        L30:
            int r4 = r3.getIntValue()
            if (r4 == 0) goto L37
            r1 = r2
        L37:
            return r1
        L38:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase.getValueAsBoolean(boolean):boolean");
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public double getValueAsDouble(double d) throws IOException, JsonParseException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != null) {
            switch (jsonToken.ordinal()) {
                case 6:
                    Object embeddedObject = getEmbeddedObject();
                    return embeddedObject instanceof Number ? ((Number) embeddedObject).doubleValue() : d;
                case 7:
                    return NumberInput.parseAsDouble(getText(), d);
                case 8:
                case 9:
                    return getDoubleValue();
                case 10:
                    return 1.0d;
                case 11:
                case 12:
                    return FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
                default:
                    return d;
            }
        }
        return d;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public int getValueAsInt(int i) throws IOException, JsonParseException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != null) {
            switch (jsonToken.ordinal()) {
                case 6:
                    Object embeddedObject = getEmbeddedObject();
                    return embeddedObject instanceof Number ? ((Number) embeddedObject).intValue() : i;
                case 7:
                    return NumberInput.parseAsInt(getText(), i);
                case 8:
                case 9:
                    return getIntValue();
                case 10:
                    return 1;
                case 11:
                case 12:
                    return 0;
                default:
                    return i;
            }
        }
        return i;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public long getValueAsLong(long j) throws IOException, JsonParseException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != null) {
            switch (jsonToken.ordinal()) {
                case 6:
                    Object embeddedObject = getEmbeddedObject();
                    return embeddedObject instanceof Number ? ((Number) embeddedObject).longValue() : j;
                case 7:
                    return NumberInput.parseAsLong(getText(), j);
                case 8:
                case 9:
                    return getLongValue();
                case 10:
                    return 1L;
                case 11:
                case 12:
                    return 0L;
                default:
                    return j;
            }
        }
        return j;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public abstract boolean hasTextCharacters();

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public abstract boolean isClosed();

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public abstract JsonToken nextToken() throws IOException, JsonParseException;

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public JsonParser skipChildren() throws IOException, JsonParseException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.START_OBJECT || jsonToken == JsonToken.START_ARRAY) {
            int i = 1;
            while (true) {
                JsonToken nextToken = nextToken();
                if (nextToken == null) {
                    _handleEOF();
                    return this;
                }
                int ordinal = nextToken.ordinal();
                if (ordinal != 1) {
                    if (ordinal != 2) {
                        if (ordinal != 3) {
                            if (ordinal != 4) {
                                continue;
                            }
                        }
                    }
                    i--;
                    if (i == 0) {
                        return this;
                    }
                }
                i++;
            }
        } else {
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonParserMinimalBase(int i) {
        super(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _reportInvalidEOF(String str) throws JsonParseException {
        _reportError(GeneratedOutlineSupport1.outline72("Unexpected end-of-input", str));
    }
}
