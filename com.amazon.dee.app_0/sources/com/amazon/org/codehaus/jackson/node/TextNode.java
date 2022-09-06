package com.amazon.org.codehaus.jackson.node;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.org.codehaus.jackson.Base64Variant;
import com.amazon.org.codehaus.jackson.Base64Variants;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonLocation;
import com.amazon.org.codehaus.jackson.JsonParseException;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.io.NumberInput;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.util.CharTypes;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
/* loaded from: classes13.dex */
public final class TextNode extends ValueNode {
    static final TextNode EMPTY_STRING_NODE = new TextNode("");
    static final int INT_SPACE = 32;
    final String _value;

    public TextNode(String str) {
        this._value = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void appendQuoted(StringBuilder sb, String str) {
        sb.append('\"');
        CharTypes.appendQuoted(sb, str);
        sb.append('\"');
    }

    public static TextNode valueOf(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return EMPTY_STRING_NODE;
        }
        return new TextNode(str);
    }

    protected void _reportBase64EOF() throws JsonParseException {
        throw new JsonParseException("Unexpected end-of-String when base64 content", JsonLocation.NA);
    }

    protected void _reportInvalidBase64(Base64Variant base64Variant, char c, int i) throws JsonParseException {
        _reportInvalidBase64(base64Variant, c, i, null);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean asBoolean(boolean z) {
        String str = this._value;
        if (str == null || !"true".equals(str.trim())) {
            return z;
        }
        return true;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public double asDouble(double d) {
        return NumberInput.parseAsDouble(this._value, d);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public int asInt(int i) {
        return NumberInput.parseAsInt(this._value, i);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public long asLong(long j) {
        return NumberInput.parseAsLong(this._value, j);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public String asText() {
        return this._value;
    }

    @Override // com.amazon.org.codehaus.jackson.node.ValueNode, com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.JsonNode
    public JsonToken asToken() {
        return JsonToken.VALUE_STRING;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != TextNode.class) {
            return false;
        }
        return ((TextNode) obj)._value.equals(this._value);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0023, code lost:
        if (r6 >= 0) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0025, code lost:
        _reportInvalidBase64(r12, r4, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0028, code lost:
        if (r5 < r2) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002a, code lost:
        _reportBase64EOF();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002d, code lost:
        r4 = r5 + 1;
        r5 = r1.charAt(r5);
        r7 = r12.decodeBase64Char(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0037, code lost:
        if (r7 >= 0) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0039, code lost:
        _reportInvalidBase64(r12, r5, 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003d, code lost:
        r5 = (r6 << 6) | r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0040, code lost:
        if (r4 < r2) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0046, code lost:
        if (r12.usesPadding() != false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0048, code lost:
        r0.append(r5 >> 4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004f, code lost:
        _reportBase64EOF();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0052, code lost:
        r6 = r4 + 1;
        r4 = r1.charAt(r4);
        r7 = r12.decodeBase64Char(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x005f, code lost:
        if (r7 >= 0) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0061, code lost:
        if (r7 == (-2)) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0063, code lost:
        _reportInvalidBase64(r12, r4, 2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0066, code lost:
        if (r6 < r2) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0068, code lost:
        _reportBase64EOF();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006b, code lost:
        r4 = r6 + 1;
        r6 = r1.charAt(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0075, code lost:
        if (r12.usesPaddingChar(r6) != false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0077, code lost:
        r7 = com.android.tools.r8.GeneratedOutlineSupport1.outline107("expected padding character '");
        r7.append(r12.getPaddingChar());
        r7.append("'");
        _reportInvalidBase64(r12, r6, 3, r7.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0090, code lost:
        r0.append(r5 >> 4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0097, code lost:
        r4 = (r5 << 6) | r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x009a, code lost:
        if (r6 < r2) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00a0, code lost:
        if (r12.usesPadding() != false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00a2, code lost:
        r0.appendTwoBytes(r4 >> 2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00a8, code lost:
        _reportBase64EOF();
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00ab, code lost:
        r5 = r6 + 1;
        r6 = r1.charAt(r6);
        r7 = r12.decodeBase64Char(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00b5, code lost:
        if (r7 >= 0) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00b7, code lost:
        if (r7 == (-2)) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00b9, code lost:
        _reportInvalidBase64(r12, r6, 3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00bc, code lost:
        r0.appendTwoBytes(r4 >> 2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00c2, code lost:
        r0.appendThreeBytes((r4 << 6) | r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00c8, code lost:
        r4 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
        r6 = r12.decodeBase64Char(r4);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public byte[] getBinaryValue(com.amazon.org.codehaus.jackson.Base64Variant r12) throws java.io.IOException {
        /*
            r11 = this;
            com.amazon.org.codehaus.jackson.util.ByteArrayBuilder r0 = new com.amazon.org.codehaus.jackson.util.ByteArrayBuilder
            r1 = 100
            r0.<init>(r1)
            java.lang.String r1 = r11._value
            int r2 = r1.length()
            r3 = 0
            r4 = r3
        Lf:
            if (r4 >= r2) goto Lce
        L11:
            int r5 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r5 < r2) goto L1b
            goto Lce
        L1b:
            r6 = 32
            if (r4 <= r6) goto Lcb
            int r6 = r12.decodeBase64Char(r4)
            if (r6 >= 0) goto L28
            r11._reportInvalidBase64(r12, r4, r3)
        L28:
            if (r5 < r2) goto L2d
            r11._reportBase64EOF()
        L2d:
            int r4 = r5 + 1
            char r5 = r1.charAt(r5)
            int r7 = r12.decodeBase64Char(r5)
            if (r7 >= 0) goto L3d
            r8 = 1
            r11._reportInvalidBase64(r12, r5, r8)
        L3d:
            int r5 = r6 << 6
            r5 = r5 | r7
            if (r4 < r2) goto L52
            boolean r6 = r12.usesPadding()
            if (r6 != 0) goto L4f
            int r12 = r5 >> 4
            r0.append(r12)
            goto Lce
        L4f:
            r11._reportBase64EOF()
        L52:
            int r6 = r4 + 1
            char r4 = r1.charAt(r4)
            int r7 = r12.decodeBase64Char(r4)
            r8 = 3
            r9 = -2
            r10 = 2
            if (r7 >= 0) goto L97
            if (r7 == r9) goto L66
            r11._reportInvalidBase64(r12, r4, r10)
        L66:
            if (r6 < r2) goto L6b
            r11._reportBase64EOF()
        L6b:
            int r4 = r6 + 1
            char r6 = r1.charAt(r6)
            boolean r7 = r12.usesPaddingChar(r6)
            if (r7 != 0) goto L90
            java.lang.String r7 = "expected padding character '"
            java.lang.StringBuilder r7 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r7)
            char r9 = r12.getPaddingChar()
            r7.append(r9)
            java.lang.String r9 = "'"
            r7.append(r9)
            java.lang.String r7 = r7.toString()
            r11._reportInvalidBase64(r12, r6, r8, r7)
        L90:
            int r5 = r5 >> 4
            r0.append(r5)
            goto Lf
        L97:
            int r4 = r5 << 6
            r4 = r4 | r7
            if (r6 < r2) goto Lab
            boolean r5 = r12.usesPadding()
            if (r5 != 0) goto La8
            int r12 = r4 >> 2
            r0.appendTwoBytes(r12)
            goto Lce
        La8:
            r11._reportBase64EOF()
        Lab:
            int r5 = r6 + 1
            char r6 = r1.charAt(r6)
            int r7 = r12.decodeBase64Char(r6)
            if (r7 >= 0) goto Lc2
            if (r7 == r9) goto Lbc
            r11._reportInvalidBase64(r12, r6, r8)
        Lbc:
            int r4 = r4 >> 2
            r0.appendTwoBytes(r4)
            goto Lc8
        Lc2:
            int r4 = r4 << 6
            r4 = r4 | r7
            r0.appendThreeBytes(r4)
        Lc8:
            r4 = r5
            goto Lf
        Lcb:
            r4 = r5
            goto L11
        Lce:
            byte[] r12 = r0.toByteArray()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.org.codehaus.jackson.node.TextNode.getBinaryValue(com.amazon.org.codehaus.jackson.Base64Variant):byte[]");
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public String getTextValue() {
        return this._value;
    }

    public int hashCode() {
        return this._value.hashCode();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean isTextual() {
        return true;
    }

    @Override // com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        String str = this._value;
        if (str == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeString(str);
        }
    }

    @Override // com.amazon.org.codehaus.jackson.node.ValueNode, com.amazon.org.codehaus.jackson.JsonNode
    public String toString() {
        int length = this._value.length();
        StringBuilder sb = new StringBuilder(length + 2 + (length >> 4));
        appendQuoted(sb, this._value);
        return sb.toString();
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
        throw new JsonParseException(outline33, JsonLocation.NA);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public byte[] getBinaryValue() throws IOException {
        return getBinaryValue(Base64Variants.getDefaultVariant());
    }
}
