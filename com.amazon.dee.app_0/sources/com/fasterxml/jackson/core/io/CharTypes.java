package com.fasterxml.jackson.core.io;

import java.util.Arrays;
import org.apache.commons.net.telnet.TelnetCommand;
/* loaded from: classes2.dex */
public final class CharTypes {
    protected static final byte[] HB;
    protected static final char[] HC = "0123456789ABCDEF".toCharArray();
    protected static final int[] sHexValues;
    protected static final int[] sInputCodes;
    protected static final int[] sInputCodesComment;
    protected static final int[] sInputCodesJsNames;
    protected static final int[] sInputCodesUTF8;
    protected static final int[] sInputCodesUtf8JsNames;
    protected static final int[] sInputCodesWS;
    protected static final int[] sOutputEscapes128;

    /* loaded from: classes2.dex */
    private static class AltEscapes {
        public static final AltEscapes instance = new AltEscapes();
        private int[][] _altEscapes = new int[128];

        private AltEscapes() {
        }

        public int[] escapesFor(int i) {
            int[] iArr = this._altEscapes[i];
            if (iArr == null) {
                iArr = Arrays.copyOf(CharTypes.sOutputEscapes128, 128);
                if (iArr[i] == 0) {
                    iArr[i] = -1;
                }
                this._altEscapes[i] = iArr;
            }
            return iArr;
        }
    }

    static {
        int length = HC.length;
        HB = new byte[length];
        for (int i = 0; i < length; i++) {
            HB[i] = (byte) HC[i];
        }
        int[] iArr = new int[256];
        for (int i2 = 0; i2 < 32; i2++) {
            iArr[i2] = -1;
        }
        iArr[34] = 1;
        iArr[92] = 1;
        sInputCodes = iArr;
        int[] iArr2 = sInputCodes;
        int[] iArr3 = new int[iArr2.length];
        System.arraycopy(iArr2, 0, iArr3, 0, iArr3.length);
        for (int i3 = 128; i3 < 256; i3++) {
            iArr3[i3] = (i3 & 224) == 192 ? 2 : (i3 & 240) == 224 ? 3 : (i3 & TelnetCommand.EL) == 240 ? 4 : -1;
        }
        sInputCodesUTF8 = iArr3;
        int[] iArr4 = new int[256];
        Arrays.fill(iArr4, -1);
        for (int i4 = 33; i4 < 256; i4++) {
            if (Character.isJavaIdentifierPart((char) i4)) {
                iArr4[i4] = 0;
            }
        }
        iArr4[64] = 0;
        iArr4[35] = 0;
        iArr4[42] = 0;
        iArr4[45] = 0;
        iArr4[43] = 0;
        sInputCodesJsNames = iArr4;
        int[] iArr5 = new int[256];
        System.arraycopy(sInputCodesJsNames, 0, iArr5, 0, iArr5.length);
        Arrays.fill(iArr5, 128, 128, 0);
        sInputCodesUtf8JsNames = iArr5;
        int[] iArr6 = new int[256];
        System.arraycopy(sInputCodesUTF8, 128, iArr6, 128, 128);
        Arrays.fill(iArr6, 0, 32, -1);
        iArr6[9] = 0;
        iArr6[10] = 10;
        iArr6[13] = 13;
        iArr6[42] = 42;
        sInputCodesComment = iArr6;
        int[] iArr7 = new int[256];
        System.arraycopy(sInputCodesUTF8, 128, iArr7, 128, 128);
        Arrays.fill(iArr7, 0, 32, -1);
        iArr7[32] = 1;
        iArr7[9] = 1;
        iArr7[10] = 10;
        iArr7[13] = 13;
        iArr7[47] = 47;
        iArr7[35] = 35;
        sInputCodesWS = iArr7;
        int[] iArr8 = new int[128];
        for (int i5 = 0; i5 < 32; i5++) {
            iArr8[i5] = -1;
        }
        iArr8[34] = 34;
        iArr8[92] = 92;
        iArr8[8] = 98;
        iArr8[9] = 116;
        iArr8[12] = 102;
        iArr8[10] = 110;
        iArr8[13] = 114;
        sOutputEscapes128 = iArr8;
        sHexValues = new int[256];
        Arrays.fill(sHexValues, -1);
        for (int i6 = 0; i6 < 10; i6++) {
            sHexValues[i6 + 48] = i6;
        }
        for (int i7 = 0; i7 < 6; i7++) {
            int[] iArr9 = sHexValues;
            int i8 = i7 + 10;
            iArr9[i7 + 97] = i8;
            iArr9[i7 + 65] = i8;
        }
    }

    public static void appendQuoted(StringBuilder sb, String str) {
        int[] iArr = sOutputEscapes128;
        int length = iArr.length;
        int length2 = str.length();
        for (int i = 0; i < length2; i++) {
            char charAt = str.charAt(i);
            if (charAt < length && iArr[charAt] != 0) {
                sb.append('\\');
                int i2 = iArr[charAt];
                if (i2 < 0) {
                    sb.append('u');
                    sb.append('0');
                    sb.append('0');
                    sb.append(HC[charAt >> 4]);
                    sb.append(HC[charAt & 15]);
                } else {
                    sb.append((char) i2);
                }
            } else {
                sb.append(charAt);
            }
        }
    }

    public static int charToHex(int i) {
        return sHexValues[i & 255];
    }

    public static byte[] copyHexBytes() {
        return (byte[]) HB.clone();
    }

    public static char[] copyHexChars() {
        return (char[]) HC.clone();
    }

    public static int[] get7BitOutputEscapes() {
        return sOutputEscapes128;
    }

    public static int[] getInputCodeComment() {
        return sInputCodesComment;
    }

    public static int[] getInputCodeLatin1() {
        return sInputCodes;
    }

    public static int[] getInputCodeLatin1JsNames() {
        return sInputCodesJsNames;
    }

    public static int[] getInputCodeUtf8() {
        return sInputCodesUTF8;
    }

    public static int[] getInputCodeUtf8JsNames() {
        return sInputCodesUtf8JsNames;
    }

    public static int[] getInputCodeWS() {
        return sInputCodesWS;
    }

    public static int[] get7BitOutputEscapes(int i) {
        if (i == 34) {
            return sOutputEscapes128;
        }
        return AltEscapes.instance.escapesFor(i);
    }
}
