package org.apache.oro.text.regex;
/* loaded from: classes4.dex */
public class Perl5Substitution extends StringSubstitution {
    public static final int INTERPOLATE_ALL = 0;
    public static final int INTERPOLATE_NONE = -1;
    static final int _OPCODE_COPY = -1;
    static final int _OPCODE_ENDCASE_MODE = -6;
    static final int _OPCODE_LOWERCASE_CHAR = -2;
    static final int _OPCODE_LOWERCASE_MODE = -4;
    static final int _OPCODE_UPPERCASE_CHAR = -3;
    static final int _OPCODE_UPPERCASE_MODE = -5;
    private static final int __MAX_GROUPS = 65535;
    private static final int __OPCODE_STORAGE_SIZE = 32;
    transient String _lastInterpolation;
    int _numInterpolations;
    int[] _subOpcodes;
    int _subOpcodesCount;
    char[] _substitutionChars;

    public Perl5Substitution() {
        this("", 0);
    }

    public Perl5Substitution(String str) {
        this(str, 0);
    }

    public Perl5Substitution(String str, int i) {
        setSubstitution(str, i);
    }

    private void __addElement(int i) {
        int[] iArr = this._subOpcodes;
        int length = iArr.length;
        if (this._subOpcodesCount == length) {
            int[] iArr2 = new int[length + 32];
            System.arraycopy(iArr, 0, iArr2, 0, length);
            this._subOpcodes = iArr2;
        }
        int[] iArr3 = this._subOpcodes;
        int i2 = this._subOpcodesCount;
        this._subOpcodesCount = i2 + 1;
        iArr3[i2] = i;
    }

    private static final boolean __isInterpolationCharacter(char c) {
        return Character.isDigit(c) || c == '&';
    }

    private void __parseSubs(String str) {
        int i;
        int i2;
        char[] charArray = str.toCharArray();
        this._substitutionChars = charArray;
        int length = charArray.length;
        this._subOpcodes = new int[32];
        int i3 = 0;
        this._subOpcodesCount = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        boolean z = false;
        int i8 = -1;
        while (i4 < length) {
            char c = charArray[i4];
            int i9 = i4 + 1;
            if (i5 != 0) {
                int digit = Character.digit(c, 10);
                if (digit > -1) {
                    if (i6 <= 65535) {
                        i6 = (i6 * 10) + digit;
                    }
                    if (i9 == length) {
                        __addElement(i6);
                    }
                } else if (c == '&' && charArray[i4 - 1] == '$') {
                    __addElement(i3);
                    i5 = i3;
                    i6 = i5;
                } else {
                    __addElement(i6);
                    i5 = i3;
                    i6 = i5;
                }
                i4++;
                i3 = 0;
            }
            if ((c == '$' || c == '\\') && i7 == 0) {
                if (i8 >= 0) {
                    __addElement(i4 - i8);
                    i8 = -1;
                }
                if (i9 != length) {
                    char c2 = charArray[i9];
                    if (c == '$') {
                        i5 = __isInterpolationCharacter(c2) ? 1 : 0;
                    } else if (c == '\\') {
                        if (c2 == 'l') {
                            if (!z) {
                                i2 = -2;
                                __addElement(i2);
                                i4 = i9;
                            }
                        } else if (c2 != 'u') {
                            if (c2 == 'L') {
                                i = -4;
                            } else if (c2 == 'U') {
                                i = -5;
                            } else if (c2 == 'E') {
                                __addElement(-6);
                                i4 = i9;
                                z = false;
                            } else {
                                i7 = 1;
                            }
                            __addElement(i);
                            i4 = i9;
                            z = true;
                        } else if (!z) {
                            i2 = -3;
                            __addElement(i2);
                            i4 = i9;
                        }
                    }
                }
            } else {
                if (i8 < 0) {
                    __addElement(-1);
                    __addElement(i4);
                    i8 = i4;
                }
                if (i9 == length) {
                    __addElement(i9 - i8);
                }
                i7 = i3;
            }
            i4++;
            i3 = 0;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void _calcSub(java.lang.StringBuffer r17, org.apache.oro.text.regex.MatchResult r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            int[] r3 = r0._subOpcodes
            char[] r4 = r0._substitutionChars
            r5 = 0
            java.lang.String r6 = r2.group(r5)
            char[] r6 = r6.toCharArray()
            int r7 = r0._subOpcodesCount
            r8 = r5
            r9 = r8
        L17:
            if (r8 >= r7) goto Lb9
            r10 = r3[r8]
            r11 = -3
            r12 = -2
            r13 = -5
            r14 = -4
            if (r10 < 0) goto L46
            int r15 = r18.groups()
            if (r10 >= r15) goto L46
            int r15 = r2.begin(r10)
            if (r15 >= 0) goto L2f
            goto Lb4
        L2f:
            int r10 = r2.end(r10)
            if (r10 >= 0) goto L37
            goto Lb4
        L37:
            int r5 = r18.length()
            if (r15 >= r5) goto Lb4
            if (r10 > r5) goto Lb4
            if (r15 < r10) goto L43
            goto Lb4
        L43:
            int r10 = r10 - r15
            r5 = r6
            goto L5a
        L46:
            r5 = -1
            if (r10 != r5) goto La1
            int r8 = r8 + 1
            if (r8 < r7) goto L4f
            goto Lb4
        L4f:
            r15 = r3[r8]
            int r8 = r8 + 1
            if (r8 < r7) goto L57
            goto Lb4
        L57:
            r10 = r3[r8]
            r5 = r4
        L5a:
            if (r9 != r12) goto L6e
            int r9 = r15 + 1
            char r11 = r5[r15]
            char r11 = java.lang.Character.toLowerCase(r11)
        L64:
            r1.append(r11)
            int r10 = r10 + (-1)
            r1.append(r5, r9, r10)
        L6c:
            r9 = 0
            goto Lb4
        L6e:
            if (r9 != r11) goto L79
            int r9 = r15 + 1
            char r11 = r5[r15]
            char r11 = java.lang.Character.toUpperCase(r11)
            goto L64
        L79:
            if (r9 != r14) goto L8b
            int r10 = r10 + r15
        L7c:
            if (r15 >= r10) goto Lb4
            int r11 = r15 + 1
            char r12 = r5[r15]
            char r12 = java.lang.Character.toLowerCase(r12)
            r1.append(r12)
            r15 = r11
            goto L7c
        L8b:
            if (r9 != r13) goto L9d
            int r10 = r10 + r15
        L8e:
            if (r15 >= r10) goto Lb4
            int r11 = r15 + 1
            char r12 = r5[r15]
            char r12 = java.lang.Character.toUpperCase(r12)
            r1.append(r12)
            r15 = r11
            goto L8e
        L9d:
            r1.append(r5, r15, r10)
            goto Lb4
        La1:
            if (r10 == r12) goto Laf
            if (r10 != r11) goto La6
            goto Laf
        La6:
            if (r10 == r14) goto Lb3
            if (r10 != r13) goto Lab
            goto Lb3
        Lab:
            r5 = -6
            if (r10 != r5) goto Lb4
            goto L6c
        Laf:
            if (r9 == r14) goto Lb4
            if (r9 == r13) goto Lb4
        Lb3:
            r9 = r10
        Lb4:
            int r8 = r8 + 1
            r5 = 0
            goto L17
        Lb9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.oro.text.regex.Perl5Substitution._calcSub(java.lang.StringBuffer, org.apache.oro.text.regex.MatchResult):void");
    }

    String _finalInterpolatedSub(MatchResult matchResult) {
        StringBuffer stringBuffer = new StringBuffer(10);
        _calcSub(stringBuffer, matchResult);
        return stringBuffer.toString();
    }

    @Override // org.apache.oro.text.regex.StringSubstitution, org.apache.oro.text.regex.Substitution
    public void appendSubstitution(StringBuffer stringBuffer, MatchResult matchResult, int i, PatternMatcherInput patternMatcherInput, PatternMatcher patternMatcher, Pattern pattern) {
        if (this._subOpcodes == null) {
            super.appendSubstitution(stringBuffer, matchResult, i, patternMatcherInput, patternMatcher, pattern);
            return;
        }
        int i2 = this._numInterpolations;
        if (i2 < 1 || i < i2) {
            _calcSub(stringBuffer, matchResult);
            return;
        }
        if (i == i2) {
            this._lastInterpolation = _finalInterpolatedSub(matchResult);
        }
        stringBuffer.append(this._lastInterpolation);
    }

    @Override // org.apache.oro.text.regex.StringSubstitution
    public void setSubstitution(String str) {
        setSubstitution(str, 0);
    }

    public void setSubstitution(String str, int i) {
        super.setSubstitution(str);
        this._numInterpolations = i;
        if (i == -1 || (str.indexOf(36) == -1 && str.indexOf(92) == -1)) {
            this._subOpcodes = null;
        } else {
            __parseSubs(str);
        }
        this._lastInterpolation = null;
    }
}
