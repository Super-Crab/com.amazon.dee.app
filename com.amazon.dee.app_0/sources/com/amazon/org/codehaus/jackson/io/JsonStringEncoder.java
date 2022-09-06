package com.amazon.org.codehaus.jackson.io;

import com.amazon.org.codehaus.jackson.util.BufferRecycler;
import com.amazon.org.codehaus.jackson.util.ByteArrayBuilder;
import com.amazon.org.codehaus.jackson.util.CharTypes;
import com.amazon.org.codehaus.jackson.util.TextBuffer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.ref.SoftReference;
/* loaded from: classes13.dex */
public final class JsonStringEncoder {
    private static final int INT_0 = 48;
    private static final int INT_BACKSLASH = 92;
    private static final int INT_U = 117;
    private static final int SURR1_FIRST = 55296;
    private static final int SURR1_LAST = 56319;
    private static final int SURR2_FIRST = 56320;
    private static final int SURR2_LAST = 57343;
    protected ByteArrayBuilder _byteBuilder;
    protected final char[] _quoteBuffer = new char[6];
    protected TextBuffer _textBuffer;
    private static final char[] HEX_CHARS = CharTypes.copyHexChars();
    private static final byte[] HEX_BYTES = CharTypes.copyHexBytes();
    protected static final ThreadLocal<SoftReference<JsonStringEncoder>> _threadEncoder = new ThreadLocal<>();

    public JsonStringEncoder() {
        char[] cArr = this._quoteBuffer;
        cArr[0] = '\\';
        cArr[2] = '0';
        cArr[3] = '0';
    }

    private int _appendByteEscape(int i, int i2, ByteArrayBuilder byteArrayBuilder, int i3) {
        byteArrayBuilder.setCurrentSegmentLength(i3);
        byteArrayBuilder.append(92);
        if (i2 < 0) {
            byteArrayBuilder.append(117);
            if (i > 255) {
                int i4 = i >> 8;
                byteArrayBuilder.append(HEX_BYTES[i4 >> 4]);
                byteArrayBuilder.append(HEX_BYTES[i4 & 15]);
                i &= 255;
            } else {
                byteArrayBuilder.append(48);
                byteArrayBuilder.append(48);
            }
            byteArrayBuilder.append(HEX_BYTES[i >> 4]);
            byteArrayBuilder.append(HEX_BYTES[i & 15]);
        } else {
            byteArrayBuilder.append((byte) i2);
        }
        return byteArrayBuilder.getCurrentSegmentLength();
    }

    private int _appendSingleEscape(int i, char[] cArr) {
        if (i < 0) {
            int i2 = -(i + 1);
            cArr[1] = 'u';
            char[] cArr2 = HEX_CHARS;
            cArr[4] = cArr2[i2 >> 4];
            cArr[5] = cArr2[i2 & 15];
            return 6;
        }
        cArr[1] = (char) i;
        return 2;
    }

    private int _convertSurrogate(int i, int i2) {
        if (i2 >= 56320 && i2 <= 57343) {
            return (i2 - 56320) + ((i - 55296) << 10) + 65536;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Broken surrogate pair: first char 0x");
        outline107.append(Integer.toHexString(i));
        outline107.append(", second 0x");
        outline107.append(Integer.toHexString(i2));
        outline107.append("; illegal combination");
        throw new IllegalArgumentException(outline107.toString());
    }

    private void _throwIllegalSurrogate(int i) {
        if (i <= 1114111) {
            if (i < 55296) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline33(i, GeneratedOutlineSupport1.outline107("Illegal character point (0x"), ") to output"));
            }
            if (i <= 56319) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline33(i, GeneratedOutlineSupport1.outline107("Unmatched first part of surrogate pair (0x"), ")"));
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline33(i, GeneratedOutlineSupport1.outline107("Unmatched second part of surrogate pair (0x"), ")"));
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline33(i, GeneratedOutlineSupport1.outline107("Illegal character point (0x"), ") to output; max is 0x10FFFF as per RFC 4627"));
    }

    public static JsonStringEncoder getInstance() {
        SoftReference<JsonStringEncoder> softReference = _threadEncoder.get();
        JsonStringEncoder jsonStringEncoder = softReference == null ? null : softReference.get();
        if (jsonStringEncoder == null) {
            JsonStringEncoder jsonStringEncoder2 = new JsonStringEncoder();
            _threadEncoder.set(new SoftReference<>(jsonStringEncoder2));
            return jsonStringEncoder2;
        }
        return jsonStringEncoder;
    }

    public byte[] encodeAsUTF8(String str) {
        int i;
        ByteArrayBuilder byteArrayBuilder = this._byteBuilder;
        if (byteArrayBuilder == null) {
            byteArrayBuilder = new ByteArrayBuilder((BufferRecycler) null);
            this._byteBuilder = byteArrayBuilder;
        }
        int length = str.length();
        byte[] resetAndGetFirstSegment = byteArrayBuilder.resetAndGetFirstSegment();
        int length2 = resetAndGetFirstSegment.length;
        byte[] bArr = resetAndGetFirstSegment;
        int i2 = 0;
        int i3 = 0;
        loop0: while (true) {
            if (i2 >= length) {
                break;
            }
            int i4 = i2 + 1;
            char charAt = str.charAt(i2);
            while (charAt <= 127) {
                if (i3 >= length2) {
                    byte[] finishCurrentSegment = byteArrayBuilder.finishCurrentSegment();
                    i3 = 0;
                    bArr = finishCurrentSegment;
                    length2 = finishCurrentSegment.length;
                }
                int i5 = i3 + 1;
                bArr[i3] = (byte) charAt;
                if (i4 >= length) {
                    i3 = i5;
                    break loop0;
                }
                char charAt2 = str.charAt(i4);
                i4++;
                charAt = charAt2;
                i3 = i5;
            }
            if (i3 >= length2) {
                bArr = byteArrayBuilder.finishCurrentSegment();
                length2 = bArr.length;
                i3 = 0;
            }
            if (charAt < 2048) {
                bArr[i3] = (byte) ((charAt >> 6) | 192);
                i = i3 + 1;
            } else if (charAt >= 55296 && charAt <= 57343) {
                if (charAt > 56319) {
                    _throwIllegalSurrogate(charAt);
                }
                if (i4 >= length) {
                    _throwIllegalSurrogate(charAt);
                }
                int i6 = i4 + 1;
                charAt = _convertSurrogate(charAt, str.charAt(i4));
                if (charAt > 1114111) {
                    _throwIllegalSurrogate(charAt);
                }
                int i7 = i3 + 1;
                bArr[i3] = (byte) ((charAt >> 18) | 240);
                if (i7 >= length2) {
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    length2 = bArr.length;
                    i7 = 0;
                }
                int i8 = i7 + 1;
                bArr[i7] = (byte) (((charAt >> 12) & 63) | 128);
                if (i8 >= length2) {
                    byte[] finishCurrentSegment2 = byteArrayBuilder.finishCurrentSegment();
                    i8 = 0;
                    bArr = finishCurrentSegment2;
                    length2 = finishCurrentSegment2.length;
                }
                bArr[i8] = (byte) (((charAt >> 6) & 63) | 128);
                i = i8 + 1;
                i4 = i6;
            } else {
                int i9 = i3 + 1;
                bArr[i3] = (byte) ((charAt >> 12) | 224);
                if (i9 >= length2) {
                    byte[] finishCurrentSegment3 = byteArrayBuilder.finishCurrentSegment();
                    i9 = 0;
                    bArr = finishCurrentSegment3;
                    length2 = finishCurrentSegment3.length;
                }
                i = i9 + 1;
                bArr[i9] = (byte) (((charAt >> 6) & 63) | 128);
            }
            if (i >= length2) {
                byte[] finishCurrentSegment4 = byteArrayBuilder.finishCurrentSegment();
                i = 0;
                bArr = finishCurrentSegment4;
                length2 = finishCurrentSegment4.length;
            }
            bArr[i] = (byte) ((charAt & 63) | 128);
            i2 = i4;
            i3 = i + 1;
        }
        return this._byteBuilder.completeAndCoalesce(i3);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0029, code lost:
        r8 = r1 + 1;
        r1 = _appendSingleEscape(r2[r12.charAt(r1)], r11._quoteBuffer);
        r9 = r7 + r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x003a, code lost:
        if (r9 <= r6.length) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x003c, code lost:
        r9 = r6.length - r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x003e, code lost:
        if (r9 <= 0) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0040, code lost:
        java.lang.System.arraycopy(r11._quoteBuffer, 0, r6, r7, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0045, code lost:
        r6 = r0.finishCurrentSegment();
        r1 = r1 - r9;
        java.lang.System.arraycopy(r11._quoteBuffer, r9, r6, 0, r1);
        r7 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0051, code lost:
        java.lang.System.arraycopy(r11._quoteBuffer, 0, r6, r7, r1);
        r7 = r9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public char[] quoteAsString(java.lang.String r12) {
        /*
            r11 = this;
            com.amazon.org.codehaus.jackson.util.TextBuffer r0 = r11._textBuffer
            if (r0 != 0) goto Lc
            com.amazon.org.codehaus.jackson.util.TextBuffer r0 = new com.amazon.org.codehaus.jackson.util.TextBuffer
            r1 = 0
            r0.<init>(r1)
            r11._textBuffer = r0
        Lc:
            char[] r1 = r0.emptyAndGetCurrentSegment()
            int[] r2 = com.amazon.org.codehaus.jackson.util.CharTypes.get7BitOutputEscapes()
            int r3 = r2.length
            int r4 = r12.length()
            r5 = 0
            r6 = r1
            r1 = r5
            r7 = r1
        L1d:
            if (r1 >= r4) goto L6d
        L1f:
            char r8 = r12.charAt(r1)
            if (r8 >= r3) goto L59
            r9 = r2[r8]
            if (r9 == 0) goto L59
            int r8 = r1 + 1
            char r1 = r12.charAt(r1)
            r1 = r2[r1]
            char[] r9 = r11._quoteBuffer
            int r1 = r11._appendSingleEscape(r1, r9)
            int r9 = r7 + r1
            int r10 = r6.length
            if (r9 <= r10) goto L51
            int r9 = r6.length
            int r9 = r9 - r7
            if (r9 <= 0) goto L45
            char[] r10 = r11._quoteBuffer
            java.lang.System.arraycopy(r10, r5, r6, r7, r9)
        L45:
            char[] r6 = r0.finishCurrentSegment()
            int r1 = r1 - r9
            char[] r7 = r11._quoteBuffer
            java.lang.System.arraycopy(r7, r9, r6, r5, r1)
            r7 = r1
            goto L57
        L51:
            char[] r10 = r11._quoteBuffer
            java.lang.System.arraycopy(r10, r5, r6, r7, r1)
            r7 = r9
        L57:
            r1 = r8
            goto L1d
        L59:
            int r9 = r6.length
            if (r7 < r9) goto L61
            char[] r6 = r0.finishCurrentSegment()
            r7 = r5
        L61:
            int r9 = r7 + 1
            r6[r7] = r8
            int r1 = r1 + 1
            if (r1 < r4) goto L6b
            r7 = r9
            goto L6d
        L6b:
            r7 = r9
            goto L1f
        L6d:
            r0.setCurrentLength(r7)
            char[] r12 = r0.contentsAsArray()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.org.codehaus.jackson.io.JsonStringEncoder.quoteAsString(java.lang.String):char[]");
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0042, code lost:
        if (r5 < r4.length) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0044, code lost:
        r4 = r0.finishCurrentSegment();
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0049, code lost:
        r7 = r2 + 1;
        r2 = r11.charAt(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004f, code lost:
        if (r2 > 127) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0051, code lost:
        r5 = _appendByteEscape(r2, r6[r2], r0, r5);
        r4 = r0.getCurrentSegment();
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005f, code lost:
        if (r2 > 2047) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0061, code lost:
        r4[r5] = (byte) ((r2 >> 6) | 192);
        r2 = (r2 & com.amazon.deecomms.common.Constants.DEFAULT_IMAGE_CHAR) | 128;
        r5 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0074, code lost:
        if (r2 < 55296) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0079, code lost:
        if (r2 <= 57343) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x007f, code lost:
        if (r2 <= 56319) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0081, code lost:
        _throwIllegalSurrogate(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0084, code lost:
        if (r7 < r1) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0086, code lost:
        _throwIllegalSurrogate(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0089, code lost:
        r6 = r7 + 1;
        r2 = _convertSurrogate(r2, r11.charAt(r7));
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0096, code lost:
        if (r2 <= 1114111) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0098, code lost:
        _throwIllegalSurrogate(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x009b, code lost:
        r7 = r5 + 1;
        r4[r5] = (byte) ((r2 >> 18) | 240);
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00a5, code lost:
        if (r7 < r4.length) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00a7, code lost:
        r4 = r0.finishCurrentSegment();
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00ac, code lost:
        r5 = r7 + 1;
        r4[r7] = (byte) (((r2 >> 12) & 63) | 128);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00b8, code lost:
        if (r5 < r4.length) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00ba, code lost:
        r4 = r0.finishCurrentSegment();
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00bf, code lost:
        r4[r5] = (byte) (((r2 >> 6) & 63) | 128);
        r2 = (r2 & 63) | 128;
        r5 = r5 + 1;
        r7 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00d1, code lost:
        r6 = r5 + 1;
        r4[r5] = (byte) ((r2 >> '\f') | 224);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00db, code lost:
        if (r6 < r4.length) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00dd, code lost:
        r4 = r0.finishCurrentSegment();
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00e2, code lost:
        r5 = r6 + 1;
        r4[r6] = (byte) (((r2 >> 6) & 63) | 128);
        r2 = (r2 & com.amazon.deecomms.common.Constants.DEFAULT_IMAGE_CHAR) | 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00f2, code lost:
        if (r5 < r4.length) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00f4, code lost:
        r4 = r0.finishCurrentSegment();
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00f9, code lost:
        r4[r5] = (byte) r2;
        r5 = r5 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public byte[] quoteAsUTF8(java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 264
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.org.codehaus.jackson.io.JsonStringEncoder.quoteAsUTF8(java.lang.String):byte[]");
    }
}
