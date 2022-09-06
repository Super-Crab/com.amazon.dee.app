package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.pdf417.PDF417ResultMetadata;
import java.math.BigInteger;
import java.util.Arrays;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes3.dex */
final class DecodedBitStreamParser {
    private static final int AL = 28;
    private static final int AS = 27;
    private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
    private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
    private static final int BYTE_COMPACTION_MODE_LATCH = 901;
    private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final int LL = 27;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_ADDRESSEE = 4;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_CHECKSUM = 6;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_FILE_NAME = 0;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_FILE_SIZE = 5;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_SEGMENT_COUNT = 1;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_SENDER = 3;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_TIME_STAMP = 2;
    private static final int MACRO_PDF417_TERMINATOR = 922;
    private static final int MAX_NUMERIC_CODEWORDS = 15;
    private static final int ML = 28;
    private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
    private static final int NUMBER_OF_SEQUENCE_CODEWORDS = 2;
    private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
    private static final int PAL = 29;
    private static final int PL = 25;
    private static final int PS = 29;
    private static final int TEXT_COMPACTION_MODE_LATCH = 900;
    private static final char[] PUNCT_CHARS = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'".toCharArray();
    private static final char[] MIXED_CHARS = "0123456789&\r\t,:#-.$/+%*=^".toCharArray();
    private static final BigInteger[] EXP900 = new BigInteger[16];

    /* renamed from: com.google.zxing.pdf417.decoder.DecodedBitStreamParser$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode = new int[Mode.values().length];

        static {
            try {
                $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.ALPHA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.LOWER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.MIXED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.PUNCT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.ALPHA_SHIFT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.PUNCT_SHIFT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        EXP900[0] = BigInteger.ONE;
        BigInteger valueOf = BigInteger.valueOf(900L);
        EXP900[1] = valueOf;
        int i = 2;
        while (true) {
            BigInteger[] bigIntegerArr = EXP900;
            if (i < bigIntegerArr.length) {
                bigIntegerArr[i] = bigIntegerArr[i - 1].multiply(valueOf);
                i++;
            } else {
                return;
            }
        }
    }

    private DecodedBitStreamParser() {
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0045 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0021 A[ADDED_TO_REGION, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int byteCompaction(int r16, int[] r17, java.nio.charset.Charset r18, int r19, java.lang.StringBuilder r20) {
        /*
            Method dump skipped, instructions count: 260
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.byteCompaction(int, int[], java.nio.charset.Charset, int, java.lang.StringBuilder):int");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0051  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.zxing.common.DecoderResult decode(int[] r7, java.lang.String r8) throws com.google.zxing.FormatException {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            int r1 = r7.length
            r2 = 2
            int r1 = r1 * r2
            r0.<init>(r1)
            java.nio.charset.Charset r1 = java.nio.charset.StandardCharsets.ISO_8859_1
            r3 = 1
            r3 = r7[r3]
            com.google.zxing.pdf417.PDF417ResultMetadata r4 = new com.google.zxing.pdf417.PDF417ResultMetadata
            r4.<init>()
        L12:
            r5 = 0
            r5 = r7[r5]
            if (r2 >= r5) goto L74
            r5 = 913(0x391, float:1.28E-42)
            if (r3 == r5) goto L5b
            switch(r3) {
                case 900: goto L56;
                case 901: goto L51;
                case 902: goto L4c;
                default: goto L1e;
            }
        L1e:
            switch(r3) {
                case 922: goto L47;
                case 923: goto L47;
                case 924: goto L51;
                case 925: goto L44;
                case 926: goto L41;
                case 927: goto L2d;
                case 928: goto L28;
                default: goto L21;
            }
        L21:
            int r2 = r2 + (-1)
            int r2 = textCompaction(r7, r2, r0)
            goto L64
        L28:
            int r2 = decodeMacroBlock(r7, r2, r4)
            goto L64
        L2d:
            int r1 = r2 + 1
            r2 = r7[r2]
            com.google.zxing.common.CharacterSetECI r2 = com.google.zxing.common.CharacterSetECI.getCharacterSetECIByValue(r2)
            java.lang.String r2 = r2.name()
            java.nio.charset.Charset r2 = java.nio.charset.Charset.forName(r2)
            r6 = r2
            r2 = r1
            r1 = r6
            goto L64
        L41:
            int r2 = r2 + 2
            goto L64
        L44:
            int r2 = r2 + 1
            goto L64
        L47:
            com.google.zxing.FormatException r7 = com.google.zxing.FormatException.getFormatInstance()
            throw r7
        L4c:
            int r2 = numericCompaction(r7, r2, r0)
            goto L64
        L51:
            int r2 = byteCompaction(r3, r7, r1, r2, r0)
            goto L64
        L56:
            int r2 = textCompaction(r7, r2, r0)
            goto L64
        L5b:
            int r3 = r2 + 1
            r2 = r7[r2]
            char r2 = (char) r2
            r0.append(r2)
            r2 = r3
        L64:
            int r3 = r7.length
            if (r2 >= r3) goto L6f
            int r3 = r2 + 1
            r2 = r7[r2]
            r6 = r3
            r3 = r2
            r2 = r6
            goto L12
        L6f:
            com.google.zxing.FormatException r7 = com.google.zxing.FormatException.getFormatInstance()
            throw r7
        L74:
            int r7 = r0.length()
            if (r7 == 0) goto L88
            com.google.zxing.common.DecoderResult r7 = new com.google.zxing.common.DecoderResult
            java.lang.String r0 = r0.toString()
            r1 = 0
            r7.<init>(r1, r0, r1, r8)
            r7.setOther(r4)
            return r7
        L88:
            com.google.zxing.FormatException r7 = com.google.zxing.FormatException.getFormatInstance()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.decode(int[], java.lang.String):com.google.zxing.common.DecoderResult");
    }

    private static String decodeBase900toBase10(int[] iArr, int i) throws FormatException {
        BigInteger bigInteger = BigInteger.ZERO;
        for (int i2 = 0; i2 < i; i2++) {
            bigInteger = bigInteger.add(EXP900[(i - i2) - 1].multiply(BigInteger.valueOf(iArr[i2])));
        }
        String bigInteger2 = bigInteger.toString();
        if (bigInteger2.charAt(0) == '1') {
            return bigInteger2.substring(1);
        }
        throw FormatException.getFormatInstance();
    }

    static int decodeMacroBlock(int[] iArr, int i, PDF417ResultMetadata pDF417ResultMetadata) throws FormatException {
        if (i + 2 <= iArr[0]) {
            int[] iArr2 = new int[2];
            int i2 = i;
            int i3 = 0;
            while (i3 < 2) {
                iArr2[i3] = iArr[i2];
                i3++;
                i2++;
            }
            pDF417ResultMetadata.setSegmentIndex(Integer.parseInt(decodeBase900toBase10(iArr2, 2)));
            StringBuilder sb = new StringBuilder();
            int textCompaction = textCompaction(iArr, i2, sb);
            pDF417ResultMetadata.setFileId(sb.toString());
            int i4 = iArr[textCompaction] == BEGIN_MACRO_PDF417_OPTIONAL_FIELD ? textCompaction + 1 : -1;
            while (textCompaction < iArr[0]) {
                int i5 = iArr[textCompaction];
                if (i5 == MACRO_PDF417_TERMINATOR) {
                    textCompaction++;
                    pDF417ResultMetadata.setLastSegment(true);
                } else if (i5 == BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
                    int i6 = textCompaction + 1;
                    switch (iArr[i6]) {
                        case 0:
                            StringBuilder sb2 = new StringBuilder();
                            textCompaction = textCompaction(iArr, i6 + 1, sb2);
                            pDF417ResultMetadata.setFileName(sb2.toString());
                            continue;
                        case 1:
                            StringBuilder sb3 = new StringBuilder();
                            textCompaction = numericCompaction(iArr, i6 + 1, sb3);
                            pDF417ResultMetadata.setSegmentCount(Integer.parseInt(sb3.toString()));
                            continue;
                        case 2:
                            StringBuilder sb4 = new StringBuilder();
                            textCompaction = numericCompaction(iArr, i6 + 1, sb4);
                            pDF417ResultMetadata.setTimestamp(Long.parseLong(sb4.toString()));
                            continue;
                        case 3:
                            StringBuilder sb5 = new StringBuilder();
                            textCompaction = textCompaction(iArr, i6 + 1, sb5);
                            pDF417ResultMetadata.setSender(sb5.toString());
                            continue;
                        case 4:
                            StringBuilder sb6 = new StringBuilder();
                            textCompaction = textCompaction(iArr, i6 + 1, sb6);
                            pDF417ResultMetadata.setAddressee(sb6.toString());
                            continue;
                        case 5:
                            StringBuilder sb7 = new StringBuilder();
                            textCompaction = numericCompaction(iArr, i6 + 1, sb7);
                            pDF417ResultMetadata.setFileSize(Long.parseLong(sb7.toString()));
                            continue;
                        case 6:
                            StringBuilder sb8 = new StringBuilder();
                            textCompaction = numericCompaction(iArr, i6 + 1, sb8);
                            pDF417ResultMetadata.setChecksum(Integer.parseInt(sb8.toString()));
                            continue;
                        default:
                            throw FormatException.getFormatInstance();
                    }
                } else {
                    throw FormatException.getFormatInstance();
                }
            }
            if (i4 != -1) {
                int i7 = textCompaction - i4;
                if (pDF417ResultMetadata.isLastSegment()) {
                    i7--;
                }
                pDF417ResultMetadata.setOptionalData(Arrays.copyOfRange(iArr, i4, i7 + i4));
            }
            return textCompaction;
        }
        throw FormatException.getFormatInstance();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static void decodeTextCompaction(int[] iArr, int[] iArr2, int i, StringBuilder sb) {
        Mode mode;
        int i2;
        char c;
        char c2;
        Mode mode2 = Mode.ALPHA;
        Mode mode3 = mode2;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = iArr[i3];
            int ordinal = mode2.ordinal();
            char c3 = Chars.SPACE;
            if (ordinal != 0) {
                if (ordinal != 1) {
                    if (ordinal != 2) {
                        if (ordinal != 3) {
                            if (ordinal != 4) {
                                if (ordinal == 5) {
                                    if (i4 < 29) {
                                        c2 = PUNCT_CHARS[i4];
                                    } else if (i4 == 29) {
                                        mode2 = Mode.ALPHA;
                                    } else if (i4 != 900) {
                                        if (i4 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                            sb.append((char) iArr2[i3]);
                                        }
                                        c = 0;
                                        mode2 = mode3;
                                    } else {
                                        mode2 = Mode.ALPHA;
                                    }
                                }
                                c = 0;
                            } else if (i4 < 26) {
                                c2 = (char) (i4 + 65);
                            } else {
                                if (i4 != 26) {
                                    if (i4 != 900) {
                                        c3 = 0;
                                    } else {
                                        mode2 = Mode.ALPHA;
                                        c3 = 0;
                                        c = c3;
                                    }
                                }
                                mode2 = mode3;
                                c = c3;
                            }
                            c = c2;
                            mode2 = mode3;
                        } else if (i4 < 29) {
                            c = PUNCT_CHARS[i4];
                        } else {
                            if (i4 == 29) {
                                mode2 = Mode.ALPHA;
                            } else if (i4 == 900) {
                                mode2 = Mode.ALPHA;
                            } else if (i4 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                sb.append((char) iArr2[i3]);
                            }
                            c = 0;
                        }
                    } else if (i4 < 25) {
                        c = MIXED_CHARS[i4];
                    } else {
                        if (i4 == 900) {
                            mode2 = Mode.ALPHA;
                        } else if (i4 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                            switch (i4) {
                                case 25:
                                    mode2 = Mode.PUNCT;
                                    break;
                                case 27:
                                    mode2 = Mode.LOWER;
                                    break;
                                case 28:
                                    mode2 = Mode.ALPHA;
                                    break;
                                case 29:
                                    mode = Mode.PUNCT_SHIFT;
                                    c3 = 0;
                                    Mode mode4 = mode;
                                    mode3 = mode2;
                                    mode2 = mode4;
                                    break;
                            }
                            c = c3;
                        } else {
                            sb.append((char) iArr2[i3]);
                        }
                        c3 = 0;
                        c = c3;
                    }
                } else if (i4 < 26) {
                    i2 = i4 + 97;
                    c = (char) i2;
                } else {
                    if (i4 == 900) {
                        mode2 = Mode.ALPHA;
                    } else if (i4 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                        switch (i4) {
                            case 27:
                                mode = Mode.ALPHA_SHIFT;
                                c3 = 0;
                                Mode mode42 = mode;
                                mode3 = mode2;
                                mode2 = mode42;
                                break;
                            case 28:
                                mode2 = Mode.MIXED;
                                break;
                            case 29:
                                mode = Mode.PUNCT_SHIFT;
                                c3 = 0;
                                Mode mode422 = mode;
                                mode3 = mode2;
                                mode2 = mode422;
                                break;
                        }
                        c = c3;
                    } else {
                        sb.append((char) iArr2[i3]);
                    }
                    c3 = 0;
                    c = c3;
                }
            } else if (i4 < 26) {
                i2 = i4 + 65;
                c = (char) i2;
            } else {
                if (i4 == 900) {
                    mode2 = Mode.ALPHA;
                } else if (i4 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                    switch (i4) {
                        case 27:
                            mode2 = Mode.LOWER;
                            break;
                        case 28:
                            mode2 = Mode.MIXED;
                            break;
                        case 29:
                            mode = Mode.PUNCT_SHIFT;
                            c3 = 0;
                            Mode mode4222 = mode;
                            mode3 = mode2;
                            mode2 = mode4222;
                            break;
                    }
                    c = c3;
                } else {
                    sb.append((char) iArr2[i3]);
                }
                c3 = 0;
                c = c3;
            }
            if (c != 0) {
                sb.append(c);
            }
        }
    }

    private static int numericCompaction(int[] iArr, int i, StringBuilder sb) throws FormatException {
        int[] iArr2 = new int[15];
        boolean z = false;
        int i2 = 0;
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i3 == iArr[0]) {
                z = true;
            }
            if (i4 < 900) {
                iArr2[i2] = i4;
                i2++;
            } else {
                if (i4 != 900 && i4 != BYTE_COMPACTION_MODE_LATCH && i4 != 928) {
                    switch (i4) {
                    }
                }
                i3--;
                z = true;
            }
            if ((i2 % 15 == 0 || i4 == NUMERIC_COMPACTION_MODE_LATCH || z) && i2 > 0) {
                sb.append(decodeBase900toBase10(iArr2, i2));
                i2 = 0;
            }
            i = i3;
        }
        return i;
    }

    private static int textCompaction(int[] iArr, int i, StringBuilder sb) {
        int[] iArr2 = new int[(iArr[0] - i) * 2];
        int[] iArr3 = new int[(iArr[0] - i) * 2];
        boolean z = false;
        int i2 = 0;
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i4 < 900) {
                iArr2[i2] = i4 / 30;
                iArr2[i2 + 1] = i4 % 30;
                i2 += 2;
            } else if (i4 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                if (i4 != 928) {
                    switch (i4) {
                        case 900:
                            iArr2[i2] = 900;
                            i2++;
                            break;
                        default:
                            switch (i4) {
                            }
                        case BYTE_COMPACTION_MODE_LATCH /* 901 */:
                        case NUMERIC_COMPACTION_MODE_LATCH /* 902 */:
                            i3--;
                            z = true;
                            break;
                    }
                }
                i3--;
                z = true;
            } else {
                iArr2[i2] = MODE_SHIFT_TO_BYTE_COMPACTION_MODE;
                i = i3 + 1;
                iArr3[i2] = iArr[i3];
                i2++;
            }
            i = i3;
        }
        decodeTextCompaction(iArr2, iArr3, i2, sb);
        return i;
    }
}
