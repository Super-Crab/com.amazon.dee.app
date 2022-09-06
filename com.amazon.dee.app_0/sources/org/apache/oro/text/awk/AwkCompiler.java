package org.apache.oro.text.awk;

import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
/* loaded from: classes4.dex */
public final class AwkCompiler implements PatternCompiler {
    public static final int CASE_INSENSITIVE_MASK = 1;
    public static final int DEFAULT_MASK = 0;
    public static final int MULTILINE_MASK = 2;
    static final char _END_OF_INPUT = 65535;
    private boolean __beginAnchor;
    private int __bytesRead;
    private boolean __caseSensitive;
    private int __closeParen;
    private boolean __endAnchor;
    private int __expressionLength;
    private boolean __inCharacterClass;
    private char __lookahead;
    private boolean __multiline;
    private int __openParen;
    private int __position;
    private char[] __regularExpression;

    private SyntaxNode __atom() throws MalformedPatternException {
        char c = this.__lookahead;
        if (c == '(') {
            __match('(');
            this.__openParen++;
            SyntaxNode __regex = __regex();
            __match(')');
            this.__closeParen++;
            return __regex;
        } else if (c == '[') {
            return __characterClass();
        } else {
            if (c == '.') {
                __match('.');
                int i = this.__position;
                this.__position = i + 1;
                NegativeCharacterClassNode negativeCharacterClassNode = new NegativeCharacterClassNode(i);
                if (!this.__multiline) {
                    return negativeCharacterClassNode;
                }
                negativeCharacterClassNode._addToken(10);
                return negativeCharacterClassNode;
            } else if (c == '\\') {
                return __backslashToken();
            } else {
                if (__isMetachar(c)) {
                    StringBuffer outline103 = GeneratedOutlineSupport1.outline103("Parse error: unexpected character ");
                    outline103.append(this.__lookahead);
                    outline103.append(" at position ");
                    outline103.append(this.__bytesRead);
                    throw new MalformedPatternException(outline103.toString());
                }
                char c2 = this.__lookahead;
                int i2 = this.__position;
                this.__position = i2 + 1;
                SyntaxNode _newTokenNode = _newTokenNode(c2, i2);
                __match(this.__lookahead);
                return _newTokenNode;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [org.apache.oro.text.awk.CharacterClassNode] */
    /* JADX WARN: Type inference failed for: r0v12, types: [org.apache.oro.text.awk.CharacterClassNode] */
    /* JADX WARN: Type inference failed for: r0v13, types: [org.apache.oro.text.awk.CharacterClassNode] */
    private SyntaxNode __backslashToken() throws MalformedPatternException {
        char c;
        NegativeCharacterClassNode negativeCharacterClassNode;
        NegativeCharacterClassNode negativeCharacterClassNode2;
        NegativeCharacterClassNode negativeCharacterClassNode3;
        NegativeCharacterClassNode negativeCharacterClassNode4;
        int parseInt;
        __match('\\');
        char c2 = this.__lookahead;
        if (c2 != 'x') {
            if (c2 == 'c') {
                __match('c');
                char upperCase = Character.toUpperCase(this.__lookahead);
                int i = upperCase > '?' ? upperCase - '@' : upperCase + '@';
                int i2 = this.__position;
                this.__position = i2 + 1;
                TokenNode tokenNode = new TokenNode((char) i, i2);
                __match(this.__lookahead);
                return tokenNode;
            }
            if (c2 < '0' || c2 > '9') {
                c = this.__lookahead;
                if (c == 'b') {
                    int i3 = this.__position;
                    this.__position = i3 + 1;
                    TokenNode tokenNode2 = new TokenNode('\b', i3);
                    __match('b');
                    return tokenNode2;
                }
                if (c == 'f') {
                    c = '\f';
                } else if (c == 'n') {
                    c = '\n';
                } else if (c == 'r') {
                    c = '\r';
                } else if (c == 't') {
                    c = '\t';
                }
                if (c != 'D') {
                    if (c != 'S') {
                        if (c == 'W') {
                            int i4 = this.__position;
                            this.__position = i4 + 1;
                            negativeCharacterClassNode3 = new NegativeCharacterClassNode(i4);
                        } else if (c == 'd') {
                            int i5 = this.__position;
                            this.__position = i5 + 1;
                            negativeCharacterClassNode = new CharacterClassNode(i5);
                        } else if (c == 's') {
                            int i6 = this.__position;
                            this.__position = i6 + 1;
                            negativeCharacterClassNode2 = new CharacterClassNode(i6);
                        } else if (c == 'w') {
                            int i7 = this.__position;
                            this.__position = i7 + 1;
                            negativeCharacterClassNode3 = new CharacterClassNode(i7);
                        }
                        negativeCharacterClassNode3._addTokenRange(48, 57);
                        negativeCharacterClassNode3._addTokenRange(97, 122);
                        negativeCharacterClassNode3._addTokenRange(65, 90);
                        negativeCharacterClassNode3._addToken(95);
                        negativeCharacterClassNode4 = negativeCharacterClassNode3;
                        __match(this.__lookahead);
                        return negativeCharacterClassNode4;
                    }
                    int i8 = this.__position;
                    this.__position = i8 + 1;
                    negativeCharacterClassNode2 = new NegativeCharacterClassNode(i8);
                    negativeCharacterClassNode2._addToken(32);
                    negativeCharacterClassNode2._addToken(12);
                    negativeCharacterClassNode2._addToken(10);
                    negativeCharacterClassNode2._addToken(13);
                    negativeCharacterClassNode2._addToken(9);
                    negativeCharacterClassNode4 = negativeCharacterClassNode2;
                    __match(this.__lookahead);
                    return negativeCharacterClassNode4;
                }
                int i9 = this.__position;
                this.__position = i9 + 1;
                negativeCharacterClassNode = new NegativeCharacterClassNode(i9);
                negativeCharacterClassNode._addTokenRange(48, 57);
                negativeCharacterClassNode4 = negativeCharacterClassNode;
                __match(this.__lookahead);
                return negativeCharacterClassNode4;
            }
            __match(c2);
            char c3 = this.__lookahead;
            if (c3 < '0' || c3 > '9') {
                __putback();
                char c4 = this.__lookahead;
                if (c4 == '0') {
                    __match('0');
                    int i10 = this.__position;
                    this.__position = i10 + 1;
                    return new TokenNode((char) 0, i10);
                }
                Character.digit(c4, 10);
                c = this.__lookahead;
            } else {
                __putback();
                parseInt = Integer.parseInt(Integer.toString(__parseUnsignedInteger(10, 2, 3)), 8);
            }
            int i11 = this.__position;
            this.__position = i11 + 1;
            negativeCharacterClassNode4 = _newTokenNode(c, i11);
            __match(this.__lookahead);
            return negativeCharacterClassNode4;
        }
        __match('x');
        parseInt = __parseUnsignedInteger(16, 2, 2);
        int i12 = this.__position;
        this.__position = i12 + 1;
        return _newTokenNode((char) parseInt, i12);
    }

    private SyntaxNode __branch() throws MalformedPatternException {
        SyntaxNode __piece;
        SyntaxNode __piece2 = __piece();
        char c = this.__lookahead;
        if (c == ')') {
            if (this.__openParen > this.__closeParen) {
                return __piece2;
            }
            StringBuffer outline103 = GeneratedOutlineSupport1.outline103("Parse error: close parenthesis without matching open parenthesis at position ");
            outline103.append(this.__bytesRead);
            throw new MalformedPatternException(outline103.toString());
        } else if (c == '|' || c == 65535) {
            return __piece2;
        } else {
            CatNode catNode = new CatNode();
            catNode._left = __piece2;
            CatNode catNode2 = catNode;
            while (true) {
                __piece = __piece();
                char c2 = this.__lookahead;
                if (c2 != ')') {
                    if (c2 == '|' || c2 == 65535) {
                        break;
                    }
                    catNode2._right = new CatNode();
                    catNode2 = (CatNode) catNode2._right;
                    catNode2._left = __piece;
                } else if (this.__openParen <= this.__closeParen) {
                    StringBuffer outline1032 = GeneratedOutlineSupport1.outline103("Parse error: close parenthesis without matching open parenthesis at position ");
                    outline1032.append(this.__bytesRead);
                    throw new MalformedPatternException(outline1032.toString());
                }
            }
            catNode2._right = __piece;
            return catNode;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x00e9, code lost:
        __match(kotlinx.serialization.json.internal.JsonReaderKt.END_LIST);
        r8.__inCharacterClass = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00ee, code lost:
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private org.apache.oro.text.awk.SyntaxNode __characterClass() throws org.apache.oro.text.regex.MalformedPatternException {
        /*
            Method dump skipped, instructions count: 239
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.oro.text.awk.AwkCompiler.__characterClass():org.apache.oro.text.awk.SyntaxNode");
    }

    private static boolean __isMetachar(char c) {
        return c == '*' || c == '?' || c == '+' || c == '[' || c == ']' || c == '(' || c == ')' || c == '|' || c == '.';
    }

    private void __match(char c) throws MalformedPatternException {
        char c2;
        if (c == this.__lookahead) {
            int i = this.__bytesRead;
            if (i < this.__expressionLength) {
                char[] cArr = this.__regularExpression;
                this.__bytesRead = i + 1;
                c2 = cArr[i];
            } else {
                c2 = 65535;
            }
            this.__lookahead = c2;
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("token: ");
        stringBuffer.append(c);
        stringBuffer.append(" does not match lookahead: ");
        stringBuffer.append(this.__lookahead);
        stringBuffer.append(" at position: ");
        stringBuffer.append(this.__bytesRead);
        throw new MalformedPatternException(stringBuffer.toString());
    }

    private int __parseUnsignedInteger(int i, int i2, int i3) throws MalformedPatternException {
        StringBuffer stringBuffer = new StringBuffer(4);
        int i4 = 0;
        while (Character.digit(this.__lookahead, i) != -1 && i4 < i3) {
            stringBuffer.append(this.__lookahead);
            __match(this.__lookahead);
            i4++;
        }
        if (i4 < i2 || i4 > i3) {
            StringBuffer outline103 = GeneratedOutlineSupport1.outline103("Parse error: unexpected number of digits at position ");
            outline103.append(this.__bytesRead);
            throw new MalformedPatternException(outline103.toString());
        }
        try {
            return Integer.parseInt(stringBuffer.toString(), i);
        } catch (NumberFormatException unused) {
            StringBuffer outline1032 = GeneratedOutlineSupport1.outline103("Parse error: numeric value at position ");
            outline1032.append(this.__bytesRead);
            outline1032.append(" is invalid");
            throw new MalformedPatternException(outline1032.toString());
        }
    }

    private SyntaxNode __piece() throws MalformedPatternException {
        SyntaxNode __atom = __atom();
        char c = this.__lookahead;
        if (c == '*') {
            __match('*');
            return new StarNode(__atom);
        } else if (c == '+') {
            __match('+');
            return new PlusNode(__atom);
        } else if (c != '?') {
            return c != '{' ? __atom : __repetition(__atom);
        } else {
            __match(Constants.DEFAULT_IMAGE_CHAR);
            return new QuestionNode(__atom);
        }
    }

    private void __putback() {
        if (this.__lookahead != 65535) {
            this.__bytesRead--;
        }
        this.__lookahead = this.__regularExpression[this.__bytesRead - 1];
    }

    private SyntaxNode __regex() throws MalformedPatternException {
        SyntaxNode __branch = __branch();
        if (this.__lookahead == '|') {
            __match('|');
            return new OrNode(__branch, __regex());
        }
        return __branch;
    }

    private SyntaxNode __repetition(SyntaxNode syntaxNode) throws MalformedPatternException {
        CatNode catNode;
        CatNode catNode2;
        CatNode catNode3;
        CatNode catNode4;
        __match(JsonReaderKt.BEGIN_OBJ);
        int __parseUnsignedInteger = __parseUnsignedInteger(10, 1, Integer.MAX_VALUE);
        int[] iArr = {this.__position};
        char c = this.__lookahead;
        if (c != '}') {
            if (c != ',') {
                StringBuffer outline103 = GeneratedOutlineSupport1.outline103("Parse error: unexpected character ");
                outline103.append(this.__lookahead);
                outline103.append(" in interval at position ");
                outline103.append(this.__bytesRead);
                throw new MalformedPatternException(outline103.toString());
            }
            __match(JsonReaderKt.COMMA);
            if (this.__lookahead == '}') {
                __match(JsonReaderKt.END_OBJ);
                if (__parseUnsignedInteger == 0) {
                    return new StarNode(syntaxNode);
                }
                if (__parseUnsignedInteger == 1) {
                    return new PlusNode(syntaxNode);
                }
                catNode3 = new CatNode();
                catNode3._left = syntaxNode;
                CatNode catNode5 = catNode3;
                while (true) {
                    __parseUnsignedInteger--;
                    if (__parseUnsignedInteger <= 0) {
                        break;
                    }
                    syntaxNode = syntaxNode._clone(iArr);
                    catNode5._right = new CatNode();
                    catNode5 = (CatNode) catNode5._right;
                    catNode5._left = syntaxNode;
                }
                catNode5._right = new StarNode(syntaxNode._clone(iArr));
            } else {
                int __parseUnsignedInteger2 = __parseUnsignedInteger(10, 1, Integer.MAX_VALUE);
                __match(JsonReaderKt.END_OBJ);
                if (__parseUnsignedInteger2 < __parseUnsignedInteger) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Parse error: invalid interval; ");
                    stringBuffer.append(__parseUnsignedInteger2);
                    stringBuffer.append(" is less than ");
                    stringBuffer.append(__parseUnsignedInteger);
                    stringBuffer.append(" at position ");
                    stringBuffer.append(this.__bytesRead);
                    throw new MalformedPatternException(stringBuffer.toString());
                } else if (__parseUnsignedInteger2 == 0) {
                    StringBuffer outline1032 = GeneratedOutlineSupport1.outline103("Parse error: Superfluous interval specified at position ");
                    outline1032.append(this.__bytesRead);
                    outline1032.append(".  Number of occurences was set to zero.");
                    throw new MalformedPatternException(outline1032.toString());
                } else {
                    if (__parseUnsignedInteger == 0) {
                        if (__parseUnsignedInteger2 == 1) {
                            return new QuestionNode(syntaxNode);
                        }
                        catNode = new CatNode();
                        SyntaxNode questionNode = new QuestionNode(syntaxNode);
                        catNode._left = questionNode;
                        CatNode catNode6 = catNode;
                        while (true) {
                            __parseUnsignedInteger2--;
                            if (__parseUnsignedInteger2 <= 1) {
                                break;
                            }
                            questionNode = questionNode._clone(iArr);
                            catNode6._right = new CatNode();
                            catNode6 = (CatNode) catNode6._right;
                            catNode6._left = questionNode;
                        }
                        catNode6._right = questionNode._clone(iArr);
                    } else if (__parseUnsignedInteger != __parseUnsignedInteger2) {
                        catNode = new CatNode();
                        catNode._left = syntaxNode;
                        SyntaxNode syntaxNode2 = syntaxNode;
                        CatNode catNode7 = catNode;
                        for (int i = 1; i < __parseUnsignedInteger; i++) {
                            syntaxNode2 = syntaxNode2._clone(iArr);
                            catNode7._right = new CatNode();
                            catNode7 = (CatNode) catNode7._right;
                            catNode7._left = syntaxNode2;
                        }
                        SyntaxNode questionNode2 = new QuestionNode(syntaxNode2._clone(iArr));
                        int i2 = __parseUnsignedInteger2 - __parseUnsignedInteger;
                        if (i2 == 1) {
                            catNode7._right = questionNode2;
                        } else {
                            catNode7._right = new CatNode();
                            SyntaxNode syntaxNode3 = catNode7._right;
                            while (true) {
                                catNode2 = (CatNode) syntaxNode3;
                                catNode2._left = questionNode2;
                                i2--;
                                questionNode2 = questionNode2._clone(iArr);
                                if (i2 <= 1) {
                                    break;
                                }
                                catNode2._right = new CatNode();
                                syntaxNode3 = catNode2._right;
                            }
                            catNode2._right = questionNode2;
                        }
                    } else if (__parseUnsignedInteger == 1) {
                        return syntaxNode;
                    } else {
                        catNode3 = new CatNode();
                        catNode3._left = syntaxNode;
                        catNode4 = catNode3;
                        while (true) {
                            __parseUnsignedInteger--;
                            if (__parseUnsignedInteger <= 1) {
                                break;
                            }
                            syntaxNode = syntaxNode._clone(iArr);
                            catNode4._right = new CatNode();
                            catNode4 = (CatNode) catNode4._right;
                            catNode4._left = syntaxNode;
                        }
                    }
                    catNode3 = catNode;
                }
            }
            this.__position = iArr[0];
            return catNode3;
        }
        __match(JsonReaderKt.END_OBJ);
        if (__parseUnsignedInteger == 0) {
            StringBuffer outline1033 = GeneratedOutlineSupport1.outline103("Parse error: Superfluous interval specified at position ");
            outline1033.append(this.__bytesRead);
            outline1033.append(".  Number of occurences was set to zero.");
            throw new MalformedPatternException(outline1033.toString());
        } else if (__parseUnsignedInteger == 1) {
            return syntaxNode;
        } else {
            catNode3 = new CatNode();
            catNode3._left = syntaxNode;
            catNode4 = catNode3;
            while (true) {
                __parseUnsignedInteger--;
                if (__parseUnsignedInteger <= 1) {
                    break;
                }
                syntaxNode = syntaxNode._clone(iArr);
                catNode4._right = new CatNode();
                catNode4 = (CatNode) catNode4._right;
                catNode4._left = syntaxNode;
            }
        }
        catNode4._right = syntaxNode._clone(iArr);
        this.__position = iArr[0];
        return catNode3;
    }

    static boolean _isLowerCase(char c) {
        return c >= 'a' && c <= 'z';
    }

    static boolean _isUpperCase(char c) {
        return c >= 'A' && c <= 'Z';
    }

    static boolean _isWordCharacter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || ((c >= '0' && c <= '9') || c == '_');
    }

    static char _toggleCase(char c) {
        int i;
        if (_isUpperCase(c)) {
            i = c + Chars.SPACE;
        } else if (!_isLowerCase(c)) {
            return c;
        } else {
            i = c - ' ';
        }
        return (char) i;
    }

    SyntaxNode _newTokenNode(char c, int i) {
        if (this.__inCharacterClass || this.__caseSensitive || (!_isUpperCase(c) && !_isLowerCase(c))) {
            return new TokenNode(c, i);
        }
        CharacterClassNode characterClassNode = new CharacterClassNode(i);
        characterClassNode._addToken(c);
        characterClassNode._addToken(_toggleCase(c));
        return characterClassNode;
    }

    SyntaxTree _parse(char[] cArr) throws MalformedPatternException {
        SyntaxTree syntaxTree;
        this.__closeParen = 0;
        this.__openParen = 0;
        this.__regularExpression = cArr;
        this.__bytesRead = 0;
        this.__expressionLength = cArr.length;
        this.__inCharacterClass = false;
        this.__position = 0;
        __match(this.__lookahead);
        char c = this.__lookahead;
        if (c == '^') {
            this.__beginAnchor = true;
            __match(c);
        }
        int i = this.__expressionLength;
        if (i > 0 && cArr[i - 1] == '$') {
            this.__expressionLength = i - 1;
            this.__endAnchor = true;
        }
        int i2 = this.__expressionLength;
        if (i2 > 1 || (i2 == 1 && !this.__beginAnchor)) {
            CatNode catNode = new CatNode();
            catNode._left = __regex();
            int i3 = this.__position;
            this.__position = i3 + 1;
            catNode._right = new TokenNode((char) 256, i3);
            syntaxTree = new SyntaxTree(catNode, this.__position);
        } else {
            syntaxTree = new SyntaxTree(new TokenNode((char) 256, 0), 1);
        }
        syntaxTree._computeFollowPositions();
        return syntaxTree;
    }

    @Override // org.apache.oro.text.regex.PatternCompiler
    public Pattern compile(String str) throws MalformedPatternException {
        return compile(str, 0);
    }

    @Override // org.apache.oro.text.regex.PatternCompiler
    public Pattern compile(String str, int i) throws MalformedPatternException {
        boolean z = false;
        this.__endAnchor = false;
        this.__beginAnchor = false;
        this.__caseSensitive = (i & 1) == 0;
        if ((i & 2) != 0) {
            z = true;
        }
        this.__multiline = z;
        AwkPattern awkPattern = new AwkPattern(str, _parse(str.toCharArray()));
        awkPattern._options = i;
        awkPattern._hasBeginAnchor = this.__beginAnchor;
        awkPattern._hasEndAnchor = this.__endAnchor;
        return awkPattern;
    }

    @Override // org.apache.oro.text.regex.PatternCompiler
    public Pattern compile(char[] cArr) throws MalformedPatternException {
        return compile(cArr, 0);
    }

    @Override // org.apache.oro.text.regex.PatternCompiler
    public Pattern compile(char[] cArr, int i) throws MalformedPatternException {
        boolean z = false;
        this.__endAnchor = false;
        this.__beginAnchor = false;
        this.__caseSensitive = (i & 1) == 0;
        if ((i & 2) != 0) {
            z = true;
        }
        this.__multiline = z;
        AwkPattern awkPattern = new AwkPattern(new String(cArr), _parse(cArr));
        awkPattern._options = i;
        awkPattern._hasBeginAnchor = this.__beginAnchor;
        awkPattern._hasEndAnchor = this.__endAnchor;
        return awkPattern;
    }
}
