package org.apache.oro.text.regex;
/* loaded from: classes4.dex */
final class OpCode {
    static final char _ALPHA = '&';
    static final char _ANYOF = '\t';
    static final char _BACK = '\r';
    static final char _BLANK = '\'';
    static final char _CLOSE = 28;
    static final char _CURLY = '\n';
    static final char _END = 0;
    static final char _GBOL = 30;
    static final char _IFMATCH = 31;
    static final char _MBOL = 2;
    static final char _MEOL = 5;
    static final char _NANYOFUN = '$';
    static final int _NULL_OFFSET = -1;
    static final char _NULL_POINTER = 0;
    static final char _OPCODE = '/';
    static final char _PUNCT = ',';
    static final char _SBOL = 3;
    static final char _SEOL = 6;
    static final char _SUCCEED = '!';
    static final char _UNLESSM = ' ';
    static final char _WHILEM = '\"';
    static final char _XDIGIT = '.';
    static final int[] _operandLength = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static final char _BOL = 1;
    static final char _EOL = 4;
    static final char _ANY = 7;
    static final char _BRANCH = '\f';
    static final char _EXACTLY = 14;
    static final char _NOTHING = 15;
    static final char _STAR = 16;
    static final char _PLUS = 17;
    static final char _ALNUM = 18;
    static final char _NALNUM = 19;
    static final char _BOUND = 20;
    static final char _NBOUND = 21;
    static final char _SPACE = 22;
    static final char _NSPACE = 23;
    static final char _DIGIT = 24;
    static final char _NDIGIT = 25;
    static final char _REF = 26;
    static final char _OPEN = 27;
    static final char _MINMOD = 29;
    static final char _ANYOFUN = '#';
    static final char _RANGE = '%';
    static final char _CNTRL = '(';
    static final char _GRAPH = ')';
    static final char _LOWER = '*';
    static final char _PRINT = '+';
    static final char _UPPER = '-';
    static final char _NOPCODE = '0';
    static final char _ONECHAR = '1';
    static final char _ALNUMC = '2';
    static final char _ASCII = '3';
    static final char[] _opType = {0, _BOL, _BOL, _BOL, _EOL, _EOL, _EOL, _ANY, _ANY, '\t', '\n', '\n', _BRANCH, '\r', _EXACTLY, _NOTHING, _STAR, _PLUS, _ALNUM, _NALNUM, _BOUND, _NBOUND, _SPACE, _NSPACE, _DIGIT, _NDIGIT, _REF, _OPEN, 28, _MINMOD, _BOL, _BRANCH, _BRANCH, 0, '\"', _ANYOFUN, '$', _RANGE, '&', '\'', _CNTRL, _GRAPH, _LOWER, _PRINT, ',', _UPPER, '.', '/', _NOPCODE, _ONECHAR, _ALNUMC, _ASCII};
    static final char _CURLYX = 11;
    static final char[] _opLengthVaries = {_BRANCH, '\r', _STAR, _PLUS, '\n', _CURLYX, _REF, '\"'};
    static final char _SANY = '\b';
    static final char[] _opLengthOne = {_ANY, _SANY, '\t', _ALNUM, _NALNUM, _SPACE, _NSPACE, _DIGIT, _NDIGIT, _ANYOFUN, '$', '&', '\'', _CNTRL, _GRAPH, _LOWER, _PRINT, ',', _UPPER, '.', '/', _NOPCODE, _ONECHAR, _ALNUMC, _ASCII};

    private OpCode() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final char _getArg1(char[] cArr, int i) {
        return cArr[i + 2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final char _getArg2(char[] cArr, int i) {
        return cArr[i + 3];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final int _getNext(char[] cArr, int i) {
        int _getNextOffset;
        if (cArr == null || (_getNextOffset = _getNextOffset(cArr, i)) == 0) {
            return -1;
        }
        return cArr[i] == '\r' ? i - _getNextOffset : i + _getNextOffset;
    }

    static final int _getNextOffset(char[] cArr, int i) {
        return cArr[i + 1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final int _getNextOperator(int i) {
        return i + 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final int _getOperand(int i) {
        return i + 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final int _getPrevOperator(int i) {
        return i - 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final boolean _isInArray(char c, char[] cArr, int i) {
        while (i < cArr.length) {
            int i2 = i + 1;
            if (c == cArr[i]) {
                return true;
            }
            i = i2;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final boolean _isWordCharacter(char c) {
        return Character.isLetterOrDigit(c) || c == '_';
    }
}
