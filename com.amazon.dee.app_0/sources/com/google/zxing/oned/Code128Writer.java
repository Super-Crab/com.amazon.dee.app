package com.google.zxing.oned;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.Map;
import org.apache.commons.net.telnet.TelnetCommand;
/* loaded from: classes3.dex */
public final class Code128Writer extends OneDimensionalCodeWriter {
    private static final int CODE_CODE_A = 101;
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_A = 101;
    private static final int CODE_FNC_4_B = 100;
    private static final int CODE_START_A = 103;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final char ESCAPE_FNC_1 = 241;
    private static final char ESCAPE_FNC_2 = 242;
    private static final char ESCAPE_FNC_3 = 243;
    private static final char ESCAPE_FNC_4 = 244;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public enum CType {
        UNCODABLE,
        ONE_DIGIT,
        TWO_DIGITS,
        FNC_1
    }

    private static int chooseCode(CharSequence charSequence, int i, int i2) {
        CType findCType;
        CType findCType2;
        char charAt;
        CType findCType3 = findCType(charSequence, i);
        if (findCType3 == CType.ONE_DIGIT) {
            return 100;
        }
        if (findCType3 == CType.UNCODABLE) {
            return (i >= charSequence.length() || ((charAt = charSequence.charAt(i)) >= ' ' && (i2 != 101 || charAt >= '`'))) ? 100 : 101;
        } else if (i2 == 99) {
            return 99;
        } else {
            if (i2 == 100) {
                if (findCType3 == CType.FNC_1 || (findCType = findCType(charSequence, i + 2)) == CType.UNCODABLE || findCType == CType.ONE_DIGIT) {
                    return 100;
                }
                if (findCType == CType.FNC_1) {
                    return findCType(charSequence, i + 3) == CType.TWO_DIGITS ? 99 : 100;
                }
                int i3 = i + 4;
                while (true) {
                    findCType2 = findCType(charSequence, i3);
                    if (findCType2 != CType.TWO_DIGITS) {
                        break;
                    }
                    i3 += 2;
                }
                return findCType2 == CType.ONE_DIGIT ? 100 : 99;
            }
            if (findCType3 == CType.FNC_1) {
                findCType3 = findCType(charSequence, i + 1);
            }
            return findCType3 == CType.TWO_DIGITS ? 99 : 100;
        }
    }

    private static CType findCType(CharSequence charSequence, int i) {
        int length = charSequence.length();
        if (i >= length) {
            return CType.UNCODABLE;
        }
        char charAt = charSequence.charAt(i);
        if (charAt == 241) {
            return CType.FNC_1;
        }
        if (charAt < '0' || charAt > '9') {
            return CType.UNCODABLE;
        }
        int i2 = i + 1;
        if (i2 >= length) {
            return CType.ONE_DIGIT;
        }
        char charAt2 = charSequence.charAt(i2);
        if (charAt2 >= '0' && charAt2 <= '9') {
            return CType.TWO_DIGITS;
        }
        return CType.ONE_DIGIT;
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter, com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.CODE_128) {
            return super.encode(str, barcodeFormat, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode CODE_128, but got " + barcodeFormat);
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public boolean[] encode(String str) {
        int length = str.length();
        if (length >= 1 && length <= 80) {
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                char charAt = str.charAt(i2);
                switch (charAt) {
                    case TelnetCommand.NOP /* 241 */:
                    case 242:
                    case TelnetCommand.BREAK /* 243 */:
                    case TelnetCommand.IP /* 244 */:
                        break;
                    default:
                        if (charAt > 127) {
                            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline47("Bad character in input: ", charAt));
                        }
                        break;
                }
            }
            ArrayList<int[]> arrayList = new ArrayList();
            int i3 = 1;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (true) {
                int i7 = 103;
                if (i4 < length) {
                    int chooseCode = chooseCode(str, i4, i6);
                    int i8 = 100;
                    if (chooseCode == i6) {
                        switch (str.charAt(i4)) {
                            case TelnetCommand.NOP /* 241 */:
                                i8 = 102;
                                break;
                            case 242:
                                i8 = 97;
                                break;
                            case TelnetCommand.BREAK /* 243 */:
                                i8 = 96;
                                break;
                            case TelnetCommand.IP /* 244 */:
                                if (i6 == 101) {
                                    i8 = 101;
                                    break;
                                }
                                break;
                            default:
                                if (i6 != 100) {
                                    if (i6 == 101) {
                                        i8 = str.charAt(i4) - ' ';
                                        if (i8 < 0) {
                                            i8 += 96;
                                            break;
                                        }
                                    } else {
                                        i8 = Integer.parseInt(str.substring(i4, i4 + 2));
                                        i4++;
                                        break;
                                    }
                                } else {
                                    i8 = str.charAt(i4) - ' ';
                                    break;
                                }
                                break;
                        }
                        i4++;
                    } else {
                        if (i6 == 0) {
                            if (chooseCode == 100) {
                                i7 = 104;
                            } else if (chooseCode != 101) {
                                i7 = 105;
                            }
                            i8 = i7;
                        } else {
                            i8 = chooseCode;
                        }
                        i6 = chooseCode;
                    }
                    arrayList.add(Code128Reader.CODE_PATTERNS[i8]);
                    i5 += i8 * i3;
                    if (i4 != 0) {
                        i3++;
                    }
                } else {
                    arrayList.add(Code128Reader.CODE_PATTERNS[i5 % 103]);
                    arrayList.add(Code128Reader.CODE_PATTERNS[106]);
                    int i9 = 0;
                    for (int[] iArr : arrayList) {
                        int i10 = i9;
                        for (int i11 : iArr) {
                            i10 += i11;
                        }
                        i9 = i10;
                    }
                    boolean[] zArr = new boolean[i9];
                    for (int[] iArr2 : arrayList) {
                        i += OneDimensionalCodeWriter.appendPattern(zArr, i, iArr2, true);
                    }
                    return zArr;
                }
            }
        } else {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Contents length should be between 1 and 80 characters, but got ", length));
        }
    }
}
