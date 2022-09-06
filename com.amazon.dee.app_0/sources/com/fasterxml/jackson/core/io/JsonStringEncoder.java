package com.fasterxml.jackson.core.io;

import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import java.util.Arrays;
/* loaded from: classes2.dex */
public final class JsonStringEncoder {
    private static final int INITIAL_BYTE_BUFFER_SIZE = 200;
    private static final int INITIAL_CHAR_BUFFER_SIZE = 120;
    private static final int SURR1_FIRST = 55296;
    private static final int SURR1_LAST = 56319;
    private static final int SURR2_FIRST = 56320;
    private static final int SURR2_LAST = 57343;
    private static final char[] HC = CharTypes.copyHexChars();
    private static final byte[] HB = CharTypes.copyHexBytes();
    private static final JsonStringEncoder instance = new JsonStringEncoder();

    private int _appendByte(int i, int i2, ByteArrayBuilder byteArrayBuilder, int i3) {
        byteArrayBuilder.setCurrentSegmentLength(i3);
        byteArrayBuilder.append(92);
        if (i2 < 0) {
            byteArrayBuilder.append(117);
            if (i > 255) {
                int i4 = i >> 8;
                byteArrayBuilder.append(HB[i4 >> 4]);
                byteArrayBuilder.append(HB[i4 & 15]);
                i &= 255;
            } else {
                byteArrayBuilder.append(48);
                byteArrayBuilder.append(48);
            }
            byteArrayBuilder.append(HB[i >> 4]);
            byteArrayBuilder.append(HB[i & 15]);
        } else {
            byteArrayBuilder.append((byte) i2);
        }
        return byteArrayBuilder.getCurrentSegmentLength();
    }

    private int _appendNamed(int i, char[] cArr) {
        cArr[1] = (char) i;
        return 2;
    }

    private int _appendNumeric(int i, char[] cArr) {
        cArr[1] = 'u';
        char[] cArr2 = HC;
        cArr[4] = cArr2[i >> 4];
        cArr[5] = cArr2[i & 15];
        return 6;
    }

    private static int _convert(int i, int i2) {
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

    private static void _illegal(int i) {
        throw new IllegalArgumentException(UTF8Writer.illegalSurrogateDesc(i));
    }

    private char[] _qbuf() {
        return new char[]{'\\', 0, '0', '0'};
    }

    public static JsonStringEncoder getInstance() {
        return instance;
    }

    public byte[] encodeAsUTF8(String str) {
        int i;
        char c;
        int length = str.length();
        byte[] bArr = new byte[200];
        ByteArrayBuilder byteArrayBuilder = null;
        int length2 = bArr.length;
        int i2 = 0;
        byte[] bArr2 = bArr;
        int i3 = 0;
        loop0: while (true) {
            if (i3 >= length) {
                break;
            }
            int i4 = i3 + 1;
            char c2 = str.charAt(i3);
            while (c2 <= 127) {
                if (i2 >= length2) {
                    if (byteArrayBuilder == null) {
                        byteArrayBuilder = ByteArrayBuilder.fromInitial(bArr2, i2);
                    }
                    bArr2 = byteArrayBuilder.finishCurrentSegment();
                    length2 = bArr2.length;
                    i2 = 0;
                }
                int i5 = i2 + 1;
                bArr2[i2] = (byte) c2;
                if (i4 >= length) {
                    i2 = i5;
                    break loop0;
                }
                char charAt = str.charAt(i4);
                i4++;
                c2 = charAt;
                i2 = i5;
            }
            if (byteArrayBuilder == null) {
                byteArrayBuilder = ByteArrayBuilder.fromInitial(bArr2, i2);
            }
            if (i2 >= length2) {
                bArr2 = byteArrayBuilder.finishCurrentSegment();
                length2 = bArr2.length;
                i2 = 0;
            }
            if (c2 < 2048) {
                bArr2[i2] = (byte) ((c2 >> 6) | 192);
                i = i2 + 1;
                c = c2;
            } else if (c2 >= 55296 && c2 <= 57343) {
                if (c2 > 56319) {
                    _illegal(c2);
                }
                if (i4 >= length) {
                    _illegal(c2);
                }
                int i6 = i4 + 1;
                int _convert = _convert(c2, str.charAt(i4));
                if (_convert > 1114111) {
                    _illegal(_convert);
                }
                int i7 = i2 + 1;
                bArr2[i2] = (byte) ((_convert >> 18) | 240);
                if (i7 >= length2) {
                    bArr2 = byteArrayBuilder.finishCurrentSegment();
                    length2 = bArr2.length;
                    i7 = 0;
                }
                int i8 = i7 + 1;
                bArr2[i7] = (byte) (((_convert >> 12) & 63) | 128);
                if (i8 >= length2) {
                    bArr2 = byteArrayBuilder.finishCurrentSegment();
                    length2 = bArr2.length;
                    i8 = 0;
                }
                bArr2[i8] = (byte) (((_convert >> 6) & 63) | 128);
                i = i8 + 1;
                i4 = i6;
                c = _convert;
            } else {
                int i9 = i2 + 1;
                bArr2[i2] = (byte) ((c2 >> '\f') | 224);
                if (i9 >= length2) {
                    bArr2 = byteArrayBuilder.finishCurrentSegment();
                    length2 = bArr2.length;
                    i9 = 0;
                }
                i = i9 + 1;
                bArr2[i9] = (byte) (((c2 >> 6) & 63) | 128);
                c = c2;
            }
            if (i >= length2) {
                bArr2 = byteArrayBuilder.finishCurrentSegment();
                length2 = bArr2.length;
                i = 0;
            }
            bArr2[i] = (byte) ((c & Constants.DEFAULT_IMAGE_CHAR) | 128);
            i3 = i4;
            i2 = i + 1;
        }
        if (byteArrayBuilder == null) {
            return Arrays.copyOfRange(bArr2, 0, i2);
        }
        return byteArrayBuilder.completeAndCoalesce(i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0026, code lost:
        r9 = r0 + 1;
        r0 = r13.charAt(r0);
        r10 = r1[r0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x002e, code lost:
        if (r10 >= 0) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0030, code lost:
        r0 = _appendNumeric(r0, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0035, code lost:
        r0 = _appendNamed(r10, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0039, code lost:
        r10 = r6 + r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003c, code lost:
        if (r10 <= r4.length) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003e, code lost:
        r10 = r4.length - r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0040, code lost:
        if (r10 <= 0) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0042, code lost:
        java.lang.System.arraycopy(r8, 0, r4, r6, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0045, code lost:
        if (r7 != null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0047, code lost:
        r7 = com.fasterxml.jackson.core.util.TextBuffer.fromInitial(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004b, code lost:
        r4 = r7.finishCurrentSegment();
        r0 = r0 - r10;
        java.lang.System.arraycopy(r8, r10, r4, 0, r0);
        r6 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0055, code lost:
        java.lang.System.arraycopy(r8, 0, r4, r6, r0);
        r6 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0020, code lost:
        if (r8 != null) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0022, code lost:
        r8 = _qbuf();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public char[] quoteAsString(java.lang.String r13) {
        /*
            r12 = this;
            r0 = 120(0x78, float:1.68E-43)
            char[] r0 = new char[r0]
            int[] r1 = com.fasterxml.jackson.core.io.CharTypes.get7BitOutputEscapes()
            int r2 = r1.length
            int r3 = r13.length()
            r4 = 0
            r5 = 0
            r7 = r4
            r8 = r7
            r6 = r5
            r4 = r0
            r0 = r6
        L14:
            if (r0 >= r3) goto L75
        L16:
            char r9 = r13.charAt(r0)
            if (r9 >= r2) goto L5b
            r10 = r1[r9]
            if (r10 == 0) goto L5b
            if (r8 != 0) goto L26
            char[] r8 = r12._qbuf()
        L26:
            int r9 = r0 + 1
            char r0 = r13.charAt(r0)
            r10 = r1[r0]
            if (r10 >= 0) goto L35
            int r0 = r12._appendNumeric(r0, r8)
            goto L39
        L35:
            int r0 = r12._appendNamed(r10, r8)
        L39:
            int r10 = r6 + r0
            int r11 = r4.length
            if (r10 <= r11) goto L55
            int r10 = r4.length
            int r10 = r10 - r6
            if (r10 <= 0) goto L45
            java.lang.System.arraycopy(r8, r5, r4, r6, r10)
        L45:
            if (r7 != 0) goto L4b
            com.fasterxml.jackson.core.util.TextBuffer r7 = com.fasterxml.jackson.core.util.TextBuffer.fromInitial(r4)
        L4b:
            char[] r4 = r7.finishCurrentSegment()
            int r0 = r0 - r10
            java.lang.System.arraycopy(r8, r10, r4, r5, r0)
            r6 = r0
            goto L59
        L55:
            java.lang.System.arraycopy(r8, r5, r4, r6, r0)
            r6 = r10
        L59:
            r0 = r9
            goto L14
        L5b:
            int r10 = r4.length
            if (r6 < r10) goto L69
            if (r7 != 0) goto L64
            com.fasterxml.jackson.core.util.TextBuffer r7 = com.fasterxml.jackson.core.util.TextBuffer.fromInitial(r4)
        L64:
            char[] r4 = r7.finishCurrentSegment()
            r6 = r5
        L69:
            int r10 = r6 + 1
            r4[r6] = r9
            int r0 = r0 + 1
            if (r0 < r3) goto L73
            r6 = r10
            goto L75
        L73:
            r6 = r10
            goto L16
        L75:
            if (r7 != 0) goto L7c
            char[] r13 = java.util.Arrays.copyOfRange(r4, r5, r6)
            return r13
        L7c:
            r7.setCurrentLength(r6)
            char[] r13 = r7.contentsAsArray()
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.quoteAsString(java.lang.String):char[]");
    }

    public byte[] quoteAsUTF8(String str) {
        int i;
        int i2;
        int length = str.length();
        int i3 = 0;
        ByteArrayBuilder byteArrayBuilder = null;
        byte[] bArr = new byte[200];
        int i4 = 0;
        loop0: while (true) {
            if (i4 >= length) {
                break;
            }
            int[] iArr = CharTypes.get7BitOutputEscapes();
            while (true) {
                char charAt = str.charAt(i4);
                if (charAt > 127 || iArr[charAt] != 0) {
                    break;
                }
                if (i3 >= bArr.length) {
                    if (byteArrayBuilder == null) {
                        byteArrayBuilder = ByteArrayBuilder.fromInitial(bArr, i3);
                    }
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    i3 = 0;
                }
                int i5 = i3 + 1;
                bArr[i3] = (byte) charAt;
                i4++;
                if (i4 >= length) {
                    i3 = i5;
                    break loop0;
                }
                i3 = i5;
            }
            if (byteArrayBuilder == null) {
                byteArrayBuilder = ByteArrayBuilder.fromInitial(bArr, i3);
            }
            if (i3 >= bArr.length) {
                bArr = byteArrayBuilder.finishCurrentSegment();
                i3 = 0;
            }
            int i6 = i4 + 1;
            char charAt2 = str.charAt(i4);
            if (charAt2 <= 127) {
                i3 = _appendByte(charAt2, iArr[charAt2], byteArrayBuilder, i3);
                bArr = byteArrayBuilder.getCurrentSegment();
            } else {
                if (charAt2 <= 2047) {
                    bArr[i3] = (byte) ((charAt2 >> 6) | 192);
                    i2 = (charAt2 & Constants.DEFAULT_IMAGE_CHAR) | 128;
                    i = i3 + 1;
                } else if (charAt2 >= 55296 && charAt2 <= 57343) {
                    if (charAt2 > 56319) {
                        _illegal(charAt2);
                    }
                    if (i6 >= length) {
                        _illegal(charAt2);
                    }
                    int i7 = i6 + 1;
                    int _convert = _convert(charAt2, str.charAt(i6));
                    if (_convert > 1114111) {
                        _illegal(_convert);
                    }
                    int i8 = i3 + 1;
                    bArr[i3] = (byte) ((_convert >> 18) | 240);
                    if (i8 >= bArr.length) {
                        bArr = byteArrayBuilder.finishCurrentSegment();
                        i8 = 0;
                    }
                    int i9 = i8 + 1;
                    bArr[i8] = (byte) (((_convert >> 12) & 63) | 128);
                    if (i9 >= bArr.length) {
                        bArr = byteArrayBuilder.finishCurrentSegment();
                        i9 = 0;
                    }
                    bArr[i9] = (byte) (((_convert >> 6) & 63) | 128);
                    i2 = (_convert & 63) | 128;
                    i = i9 + 1;
                    i6 = i7;
                } else {
                    int i10 = i3 + 1;
                    bArr[i3] = (byte) ((charAt2 >> '\f') | 224);
                    if (i10 >= bArr.length) {
                        bArr = byteArrayBuilder.finishCurrentSegment();
                        i10 = 0;
                    }
                    i = i10 + 1;
                    bArr[i10] = (byte) (((charAt2 >> 6) & 63) | 128);
                    i2 = (charAt2 & Constants.DEFAULT_IMAGE_CHAR) | 128;
                }
                if (i >= bArr.length) {
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    i = 0;
                }
                bArr[i] = (byte) i2;
                i3 = i + 1;
            }
            i4 = i6;
        }
        if (byteArrayBuilder == null) {
            return Arrays.copyOfRange(bArr, 0, i3);
        }
        return byteArrayBuilder.completeAndCoalesce(i3);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002a, code lost:
        if (r8 != null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002c, code lost:
        r8 = _qbuf();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0030, code lost:
        r9 = r0 + 1;
        r0 = r13.charAt(r0);
        r10 = r1[r0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0038, code lost:
        if (r10 >= 0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003a, code lost:
        r0 = _appendNumeric(r0, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003f, code lost:
        r0 = _appendNamed(r10, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0043, code lost:
        r10 = r7 + r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0046, code lost:
        if (r10 <= r6.length) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0048, code lost:
        r10 = r6.length - r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004a, code lost:
        if (r10 <= 0) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004c, code lost:
        java.lang.System.arraycopy(r8, 0, r6, r7, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004f, code lost:
        if (r4 != null) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0051, code lost:
        r4 = com.fasterxml.jackson.core.util.TextBuffer.fromInitial(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0055, code lost:
        r6 = r4.finishCurrentSegment();
        r0 = r0 - r10;
        java.lang.System.arraycopy(r8, r10, r6, 0, r0);
        r7 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005f, code lost:
        java.lang.System.arraycopy(r8, 0, r6, r7, r0);
        r7 = r10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public char[] quoteAsString(java.lang.CharSequence r13) {
        /*
            r12 = this;
            boolean r0 = r13 instanceof java.lang.String
            if (r0 == 0) goto Lb
            java.lang.String r13 = (java.lang.String) r13
            char[] r13 = r12.quoteAsString(r13)
            return r13
        Lb:
            r0 = 120(0x78, float:1.68E-43)
            char[] r0 = new char[r0]
            int[] r1 = com.fasterxml.jackson.core.io.CharTypes.get7BitOutputEscapes()
            int r2 = r1.length
            int r3 = r13.length()
            r4 = 0
            r5 = 0
            r6 = r0
            r8 = r4
            r0 = r5
            r7 = r0
        L1e:
            if (r0 >= r3) goto L7f
        L20:
            char r9 = r13.charAt(r0)
            if (r9 >= r2) goto L65
            r10 = r1[r9]
            if (r10 == 0) goto L65
            if (r8 != 0) goto L30
            char[] r8 = r12._qbuf()
        L30:
            int r9 = r0 + 1
            char r0 = r13.charAt(r0)
            r10 = r1[r0]
            if (r10 >= 0) goto L3f
            int r0 = r12._appendNumeric(r0, r8)
            goto L43
        L3f:
            int r0 = r12._appendNamed(r10, r8)
        L43:
            int r10 = r7 + r0
            int r11 = r6.length
            if (r10 <= r11) goto L5f
            int r10 = r6.length
            int r10 = r10 - r7
            if (r10 <= 0) goto L4f
            java.lang.System.arraycopy(r8, r5, r6, r7, r10)
        L4f:
            if (r4 != 0) goto L55
            com.fasterxml.jackson.core.util.TextBuffer r4 = com.fasterxml.jackson.core.util.TextBuffer.fromInitial(r6)
        L55:
            char[] r6 = r4.finishCurrentSegment()
            int r0 = r0 - r10
            java.lang.System.arraycopy(r8, r10, r6, r5, r0)
            r7 = r0
            goto L63
        L5f:
            java.lang.System.arraycopy(r8, r5, r6, r7, r0)
            r7 = r10
        L63:
            r0 = r9
            goto L1e
        L65:
            int r10 = r6.length
            if (r7 < r10) goto L73
            if (r4 != 0) goto L6e
            com.fasterxml.jackson.core.util.TextBuffer r4 = com.fasterxml.jackson.core.util.TextBuffer.fromInitial(r6)
        L6e:
            char[] r6 = r4.finishCurrentSegment()
            r7 = r5
        L73:
            int r10 = r7 + 1
            r6[r7] = r9
            int r0 = r0 + 1
            if (r0 < r3) goto L7d
            r7 = r10
            goto L7f
        L7d:
            r7 = r10
            goto L20
        L7f:
            if (r4 != 0) goto L86
            char[] r13 = java.util.Arrays.copyOfRange(r6, r5, r7)
            return r13
        L86:
            r4.setCurrentLength(r7)
            char[] r13 = r4.contentsAsArray()
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.quoteAsString(java.lang.CharSequence):char[]");
    }

    public byte[] encodeAsUTF8(CharSequence charSequence) {
        int i;
        char c;
        int length = charSequence.length();
        byte[] bArr = new byte[200];
        ByteArrayBuilder byteArrayBuilder = null;
        int length2 = bArr.length;
        int i2 = 0;
        byte[] bArr2 = bArr;
        int i3 = 0;
        loop0: while (true) {
            if (i3 >= length) {
                break;
            }
            int i4 = i3 + 1;
            char c2 = charSequence.charAt(i3);
            while (c2 <= 127) {
                if (i2 >= length2) {
                    if (byteArrayBuilder == null) {
                        byteArrayBuilder = ByteArrayBuilder.fromInitial(bArr2, i2);
                    }
                    bArr2 = byteArrayBuilder.finishCurrentSegment();
                    length2 = bArr2.length;
                    i2 = 0;
                }
                int i5 = i2 + 1;
                bArr2[i2] = (byte) c2;
                if (i4 >= length) {
                    i2 = i5;
                    break loop0;
                }
                char charAt = charSequence.charAt(i4);
                i4++;
                c2 = charAt;
                i2 = i5;
            }
            if (byteArrayBuilder == null) {
                byteArrayBuilder = ByteArrayBuilder.fromInitial(bArr2, i2);
            }
            if (i2 >= length2) {
                bArr2 = byteArrayBuilder.finishCurrentSegment();
                length2 = bArr2.length;
                i2 = 0;
            }
            if (c2 < 2048) {
                bArr2[i2] = (byte) ((c2 >> 6) | 192);
                i = i2 + 1;
                c = c2;
            } else if (c2 >= 55296 && c2 <= 57343) {
                if (c2 > 56319) {
                    _illegal(c2);
                }
                if (i4 >= length) {
                    _illegal(c2);
                }
                int i6 = i4 + 1;
                int _convert = _convert(c2, charSequence.charAt(i4));
                if (_convert > 1114111) {
                    _illegal(_convert);
                }
                int i7 = i2 + 1;
                bArr2[i2] = (byte) ((_convert >> 18) | 240);
                if (i7 >= length2) {
                    bArr2 = byteArrayBuilder.finishCurrentSegment();
                    length2 = bArr2.length;
                    i7 = 0;
                }
                int i8 = i7 + 1;
                bArr2[i7] = (byte) (((_convert >> 12) & 63) | 128);
                if (i8 >= length2) {
                    bArr2 = byteArrayBuilder.finishCurrentSegment();
                    length2 = bArr2.length;
                    i8 = 0;
                }
                bArr2[i8] = (byte) (((_convert >> 6) & 63) | 128);
                i = i8 + 1;
                i4 = i6;
                c = _convert;
            } else {
                int i9 = i2 + 1;
                bArr2[i2] = (byte) ((c2 >> '\f') | 224);
                if (i9 >= length2) {
                    bArr2 = byteArrayBuilder.finishCurrentSegment();
                    length2 = bArr2.length;
                    i9 = 0;
                }
                i = i9 + 1;
                bArr2[i9] = (byte) (((c2 >> 6) & 63) | 128);
                c = c2;
            }
            if (i >= length2) {
                bArr2 = byteArrayBuilder.finishCurrentSegment();
                length2 = bArr2.length;
                i = 0;
            }
            bArr2[i] = (byte) ((c & Constants.DEFAULT_IMAGE_CHAR) | 128);
            i3 = i4;
            i2 = i + 1;
        }
        if (byteArrayBuilder == null) {
            return Arrays.copyOfRange(bArr2, 0, i2);
        }
        return byteArrayBuilder.completeAndCoalesce(i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001f, code lost:
        r6 = r4 + 1;
        r4 = r9.charAt(r4);
        r7 = r0[r4];
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0027, code lost:
        if (r7 >= 0) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0029, code lost:
        r4 = _appendNumeric(r4, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002e, code lost:
        r4 = _appendNamed(r7, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0032, code lost:
        r10.append(r5, 0, r4);
        r4 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0019, code lost:
        if (r5 != null) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001b, code lost:
        r5 = _qbuf();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void quoteAsString(java.lang.CharSequence r9, java.lang.StringBuilder r10) {
        /*
            r8 = this;
            int[] r0 = com.fasterxml.jackson.core.io.CharTypes.get7BitOutputEscapes()
            int r1 = r0.length
            int r2 = r9.length()
            r3 = 0
            r4 = 0
            r5 = r4
            r4 = r3
        Ld:
            if (r4 >= r2) goto L3e
        Lf:
            char r6 = r9.charAt(r4)
            if (r6 >= r1) goto L37
            r7 = r0[r6]
            if (r7 == 0) goto L37
            if (r5 != 0) goto L1f
            char[] r5 = r8._qbuf()
        L1f:
            int r6 = r4 + 1
            char r4 = r9.charAt(r4)
            r7 = r0[r4]
            if (r7 >= 0) goto L2e
            int r4 = r8._appendNumeric(r4, r5)
            goto L32
        L2e:
            int r4 = r8._appendNamed(r7, r5)
        L32:
            r10.append(r5, r3, r4)
            r4 = r6
            goto Ld
        L37:
            r10.append(r6)
            int r4 = r4 + 1
            if (r4 < r2) goto Lf
        L3e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.quoteAsString(java.lang.CharSequence, java.lang.StringBuilder):void");
    }
}
