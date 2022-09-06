package org.apache.oro.text.regex;

import java.util.HashMap;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes4.dex */
public final class Perl5Compiler implements PatternCompiler {
    public static final int CASE_INSENSITIVE_MASK = 1;
    public static final int DEFAULT_MASK = 0;
    public static final int EXTENDED_MASK = 32;
    public static final int MULTILINE_MASK = 8;
    public static final int READ_ONLY_MASK = 32768;
    public static final int SINGLELINE_MASK = 16;
    private static final char __CASE_INSENSITIVE = 1;
    private static final char __EXTENDED = ' ';
    private static final char __GLOBAL = 2;
    private static final String __HEX_DIGIT = "0123456789abcdef0123456789ABCDEFx";
    private static final char __KEEP = 4;
    private static final char __MULTILINE = '\b';
    private static final int __NONNULL = 1;
    private static final char __READ_ONLY = 32768;
    private static final int __SIMPLE = 2;
    private static final char __SINGLELINE = 16;
    private static final int __SPSTART = 4;
    private static final int __TRYAGAIN = 8;
    private static final int __WORSTCASE = 0;
    private static final HashMap __hashPOSIX = new HashMap();
    private int __cost;
    private CharStringPointer __input;
    private char[] __modifierFlags = {0};
    private int __numParentheses;
    private char[] __program;
    private int __programSize;
    private boolean __sawBackreference;

    static {
        __hashPOSIX.put("alnum", new Character('2'));
        __hashPOSIX.put("word", new Character((char) 18));
        __hashPOSIX.put("alpha", new Character(Typography.amp));
        __hashPOSIX.put("blank", new Character(Chars.QUOTE));
        __hashPOSIX.put("cntrl", new Character('('));
        __hashPOSIX.put("digit", new Character((char) 24));
        __hashPOSIX.put("graph", new Character(')'));
        __hashPOSIX.put("lower", new Character('*'));
        __hashPOSIX.put("print", new Character('+'));
        __hashPOSIX.put("punct", new Character(JsonReaderKt.COMMA));
        __hashPOSIX.put("space", new Character((char) 22));
        __hashPOSIX.put("upper", new Character('-'));
        __hashPOSIX.put("xdigit", new Character('.'));
        __hashPOSIX.put("ascii", new Character('3'));
    }

    private int __emitArgNode(char c, char c2) {
        int i = this.__programSize;
        char[] cArr = this.__program;
        if (cArr == null) {
            this.__programSize = i + 3;
        } else {
            this.__programSize = i + 1;
            cArr[i] = c;
            int i2 = this.__programSize;
            this.__programSize = i2 + 1;
            cArr[i2] = 0;
            int i3 = this.__programSize;
            this.__programSize = i3 + 1;
            cArr[i3] = c2;
        }
        return i;
    }

    private void __emitCode(char c) {
        char[] cArr = this.__program;
        if (cArr != null) {
            cArr[this.__programSize] = c;
        }
        this.__programSize++;
    }

    private int __emitNode(char c) {
        int i = this.__programSize;
        char[] cArr = this.__program;
        if (cArr == null) {
            this.__programSize = i + 2;
        } else {
            this.__programSize = i + 1;
            cArr[i] = c;
            int i2 = this.__programSize;
            this.__programSize = i2 + 1;
            cArr[i2] = 0;
        }
        return i;
    }

    private char __getNextChar() {
        char _postIncrement = this.__input._postIncrement();
        while (true) {
            char _getValue = this.__input._getValue();
            if (_getValue == '(' && this.__input._getValueRelative(1) == '?' && this.__input._getValueRelative(2) == '#') {
                while (_getValue != 65535 && _getValue != ')') {
                    _getValue = this.__input._increment();
                }
            } else if ((this.__modifierFlags[0] & ' ') == 0) {
                break;
            } else if (Character.isWhitespace(_getValue)) {
                continue;
            } else if (_getValue != '#') {
                break;
            } else {
                while (_getValue != 65535 && _getValue != '\n') {
                    _getValue = this.__input._increment();
                }
            }
            this.__input._increment();
        }
        return _postIncrement;
    }

    private static boolean __isComplexRepetitionOp(char[] cArr, int i) {
        if (i >= cArr.length || i < 0) {
            return false;
        }
        return cArr[i] == '*' || cArr[i] == '+' || cArr[i] == '?' || (cArr[i] == '{' && __parseRepetition(cArr, i));
    }

    private static boolean __isSimpleRepetitionOp(char c) {
        return c == '*' || c == '+' || c == '?';
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x005d, code lost:
        __emitNode(15);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int __parseAlternation(int[] r8) throws org.apache.oro.text.regex.MalformedPatternException {
        /*
            r7 = this;
            r0 = 0
            r8[r0] = r0
            r1 = 12
            int r1 = r7.__emitNode(r1)
            org.apache.oro.text.regex.CharStringPointer r2 = r7.__input
            int r2 = r2._getOffset()
            r3 = -1
            if (r2 != 0) goto L18
            org.apache.oro.text.regex.CharStringPointer r2 = r7.__input
            r2._setOffset(r3)
            goto L1d
        L18:
            org.apache.oro.text.regex.CharStringPointer r2 = r7.__input
            r2._decrement()
        L1d:
            r7.__getNextChar()
            org.apache.oro.text.regex.CharStringPointer r2 = r7.__input
            char r2 = r2._getValue()
            r4 = r3
        L27:
            r5 = 65535(0xffff, float:9.1834E-41)
            if (r2 == r5) goto L5b
            r5 = 124(0x7c, float:1.74E-43)
            if (r2 == r5) goto L5b
            r5 = 41
            if (r2 == r5) goto L5b
            int r2 = r7.__parseBranch(r8)
            if (r2 != r3) goto L3b
            return r3
        L3b:
            r5 = r8[r0]
            r5 = r5 | r0
            r8[r0] = r5
            if (r4 != r3) goto L48
            r4 = r8[r0]
            r4 = r4 | r0
            r8[r0] = r4
            goto L51
        L48:
            int r5 = r7.__cost
            int r5 = r5 + 1
            r7.__cost = r5
            r7.__programAddTail(r4, r2)
        L51:
            org.apache.oro.text.regex.CharStringPointer r4 = r7.__input
            char r4 = r4._getValue()
            r6 = r4
            r4 = r2
            r2 = r6
            goto L27
        L5b:
            if (r4 != r3) goto L62
            r8 = 15
            r7.__emitNode(r8)
        L62:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.oro.text.regex.Perl5Compiler.__parseAlternation(int[]):int");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0218, code lost:
        throw new org.apache.oro.text.regex.MalformedPatternException("?+* follows nothing in expression");
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x0252, code lost:
        r5 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x0253, code lost:
        r2 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01cd, code lost:
        if ((r2[0] & 8) == 0) goto L206;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x01cf, code lost:
        r17[0] = r17[0] | 8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01d4, code lost:
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x01d5, code lost:
        r2 = com.android.tools.r8.GeneratedOutlineSupport1.outline103("Error in expression at ");
        r3 = r16.__input;
        r2.append(r3._toString(r3._getOffset()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x01f1, code lost:
        throw new org.apache.oro.text.regex.MalformedPatternException(r2.toString());
     */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x03f9  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x040f  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x041b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int __parseAtom(int[] r17) throws org.apache.oro.text.regex.MalformedPatternException {
        /*
            Method dump skipped, instructions count: 1260
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.oro.text.regex.Perl5Compiler.__parseAtom(int[]):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x0217  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0232 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01a5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int __parseBranch(int[] r18) throws org.apache.oro.text.regex.MalformedPatternException {
        /*
            Method dump skipped, instructions count: 571
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.oro.text.regex.Perl5Compiler.__parseBranch(int[]):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00d8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00d9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int __parseExpression(boolean r14, int[] r15) throws org.apache.oro.text.regex.MalformedPatternException {
        /*
            Method dump skipped, instructions count: 421
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.oro.text.regex.Perl5Compiler.__parseExpression(boolean, int[]):int");
    }

    private static int __parseHex(char[] cArr, int i, int i2, int[] iArr) {
        int indexOf;
        iArr[0] = 0;
        int i3 = 0;
        while (i < cArr.length) {
            int i4 = i2 - 1;
            if (i2 <= 0 || (indexOf = __HEX_DIGIT.indexOf(cArr[i])) == -1) {
                break;
            }
            i3 = (i3 << 4) | (indexOf & 15);
            i++;
            iArr[0] = iArr[0] + 1;
            i2 = i4;
        }
        return i3;
    }

    private static int __parseOctal(char[] cArr, int i, int i2, int[] iArr) {
        iArr[0] = 0;
        int i3 = 0;
        while (i < cArr.length && i2 > 0 && cArr[i] >= '0' && cArr[i] <= '7') {
            i3 = (i3 << 3) | (cArr[i] - '0');
            i2--;
            i++;
            iArr[0] = iArr[0] + 1;
        }
        return i3;
    }

    private char __parsePOSIX(boolean[] zArr) throws MalformedPatternException {
        int i;
        Object obj;
        int _getOffset = this.__input._getOffset();
        int _getLength = this.__input._getLength();
        int i2 = _getOffset + 1;
        if (this.__input._getValue(_getOffset) != ':') {
            return (char) 0;
        }
        if (this.__input._getValue(i2) == '^') {
            zArr[0] = true;
            i2++;
        } else {
            zArr[0] = false;
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            try {
                i = i2 + 1;
                char _getValue = this.__input._getValue(i2);
                if (_getValue == ':' || i >= _getLength) {
                    break;
                }
                stringBuffer.append(_getValue);
                i2 = i;
            } catch (Exception unused) {
                return (char) 0;
            }
        }
        int i3 = i + 1;
        if (this.__input._getValue(i) != ']' || (obj = __hashPOSIX.get(stringBuffer.toString())) == null) {
            return (char) 0;
        }
        this.__input._setOffset(i3);
        return ((Character) obj).charValue();
    }

    private static boolean __parseRepetition(char[] cArr, int i) {
        int i2;
        if (cArr[i] == '{' && (i2 = i + 1) < cArr.length && Character.isDigit(cArr[i2])) {
            while (i2 < cArr.length && Character.isDigit(cArr[i2])) {
                i2++;
            }
            if (i2 < cArr.length && cArr[i2] == ',') {
                i2++;
            }
            while (i2 < cArr.length && Character.isDigit(cArr[i2])) {
                i2++;
            }
            if (i2 < cArr.length && cArr[i2] == '}') {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int __parseUnicodeClass() throws org.apache.oro.text.regex.MalformedPatternException {
        /*
            Method dump skipped, instructions count: 508
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.oro.text.regex.Perl5Compiler.__parseUnicodeClass():int");
    }

    private void __programAddOperatorTail(int i, int i2) {
        char[] cArr = this.__program;
        if (cArr == null || i == -1 || OpCode._opType[cArr[i]] != '\f') {
            return;
        }
        __programAddTail(OpCode._getNextOperator(i), i2);
    }

    private void __programAddTail(int i, int i2) {
        if (this.__program == null || i == -1) {
            return;
        }
        while (true) {
            int _getNext = OpCode._getNext(this.__program, i);
            if (_getNext == -1) {
                break;
            }
            i = _getNext;
        }
        this.__program[i + 1] = (char) (this.__program[i] == '\r' ? i - i2 : i2 - i);
    }

    private void __programInsertOperator(char c, int i) {
        int i2 = OpCode._opType[c] == '\n' ? 2 : 0;
        if (this.__program == null) {
            this.__programSize = i2 + 2 + this.__programSize;
            return;
        }
        int i3 = this.__programSize;
        this.__programSize = i2 + 2 + i3;
        int i4 = this.__programSize;
        while (i3 > i) {
            i3--;
            i4--;
            char[] cArr = this.__program;
            cArr[i4] = cArr[i3];
        }
        char[] cArr2 = this.__program;
        int i5 = i + 1;
        cArr2[i] = c;
        int i6 = i5 + 1;
        cArr2[i5] = 0;
        while (true) {
            int i7 = i2 - 1;
            if (i2 <= 0) {
                return;
            }
            this.__program[i6] = 0;
            i2 = i7;
            i6++;
        }
    }

    private static void __setModifierFlag(char[] cArr, char c) {
        if (c == 'g') {
            cArr[0] = (char) (cArr[0] | __GLOBAL);
        } else if (c == 'i') {
            cArr[0] = (char) (cArr[0] | __CASE_INSENSITIVE);
        } else if (c == 'm') {
            cArr[0] = (char) (cArr[0] | __MULTILINE);
        } else if (c == 'o') {
            cArr[0] = (char) (cArr[0] | __KEEP);
        } else if (c == 's') {
            cArr[0] = (char) (cArr[0] | __SINGLELINE);
        } else if (c != 'x') {
        } else {
            cArr[0] = (char) (cArr[0] | ' ');
        }
    }

    public static final String quotemeta(String str) {
        return quotemeta(str.toCharArray());
    }

    public static final String quotemeta(char[] cArr) {
        StringBuffer stringBuffer = new StringBuffer(cArr.length * 2);
        for (int i = 0; i < cArr.length; i++) {
            if (!OpCode._isWordCharacter(cArr[i])) {
                stringBuffer.append('\\');
            }
            stringBuffer.append(cArr[i]);
        }
        return stringBuffer.toString();
    }

    @Override // org.apache.oro.text.regex.PatternCompiler
    public Pattern compile(String str) throws MalformedPatternException {
        return compile(str.toCharArray(), 0);
    }

    @Override // org.apache.oro.text.regex.PatternCompiler
    public Pattern compile(String str, int i) throws MalformedPatternException {
        return compile(str.toCharArray(), i);
    }

    @Override // org.apache.oro.text.regex.PatternCompiler
    public Pattern compile(char[] cArr) throws MalformedPatternException {
        return compile(cArr, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x00a6, code lost:
        if (r15[org.apache.oro.text.regex.OpCode._getNext(r15, r10)] == '\f') goto L27;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:151:0x02ef  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0307  */
    /* JADX WARN: Type inference failed for: r18v12, types: [int] */
    /* JADX WARN: Type inference failed for: r18v4, types: [int] */
    /* JADX WARN: Type inference failed for: r18v9, types: [int] */
    /* JADX WARN: Type inference failed for: r2v24, types: [int] */
    @Override // org.apache.oro.text.regex.PatternCompiler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.apache.oro.text.regex.Pattern compile(char[] r22, int r23) throws org.apache.oro.text.regex.MalformedPatternException {
        /*
            Method dump skipped, instructions count: 802
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.oro.text.regex.Perl5Compiler.compile(char[], int):org.apache.oro.text.regex.Pattern");
    }
}
