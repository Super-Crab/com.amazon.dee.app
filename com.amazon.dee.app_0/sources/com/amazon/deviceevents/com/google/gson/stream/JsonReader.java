package com.amazon.deviceevents.com.google.gson.stream;

import com.amazon.alexa.client.metrics.core.AppInformation;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.deviceevents.com.google.gson.internal.JsonReaderInternalAccess;
import com.amazon.deviceevents.com.google.gson.internal.bind.JsonTreeReader;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes12.dex */
public class JsonReader implements Closeable {
    private static final long MIN_INCOMPLETE_INTEGER = -922337203685477580L;
    private static final char[] NON_EXECUTE_PREFIX = ")]}'\n".toCharArray();
    private static final int NUMBER_CHAR_DECIMAL = 3;
    private static final int NUMBER_CHAR_DIGIT = 2;
    private static final int NUMBER_CHAR_EXP_DIGIT = 7;
    private static final int NUMBER_CHAR_EXP_E = 5;
    private static final int NUMBER_CHAR_EXP_SIGN = 6;
    private static final int NUMBER_CHAR_FRACTION_DIGIT = 4;
    private static final int NUMBER_CHAR_NONE = 0;
    private static final int NUMBER_CHAR_SIGN = 1;
    private static final int PEEKED_BEGIN_ARRAY = 3;
    private static final int PEEKED_BEGIN_OBJECT = 1;
    private static final int PEEKED_BUFFERED = 11;
    private static final int PEEKED_DOUBLE_QUOTED = 9;
    private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
    private static final int PEEKED_END_ARRAY = 4;
    private static final int PEEKED_END_OBJECT = 2;
    private static final int PEEKED_EOF = 17;
    private static final int PEEKED_FALSE = 6;
    private static final int PEEKED_LONG = 15;
    private static final int PEEKED_NONE = 0;
    private static final int PEEKED_NULL = 7;
    private static final int PEEKED_NUMBER = 16;
    private static final int PEEKED_SINGLE_QUOTED = 8;
    private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
    private static final int PEEKED_TRUE = 5;
    private static final int PEEKED_UNQUOTED = 10;
    private static final int PEEKED_UNQUOTED_NAME = 14;
    private final Reader in;
    private int[] pathIndices;
    private String[] pathNames;
    private long peekedLong;
    private int peekedNumberLength;
    private String peekedString;
    private int stackSize;
    private boolean lenient = false;
    private final char[] buffer = new char[1024];
    private int pos = 0;
    private int limit = 0;
    private int lineNumber = 0;
    private int lineStart = 0;
    private int peeked = 0;
    private int[] stack = new int[32];

    static {
        JsonReaderInternalAccess.INSTANCE = new JsonReaderInternalAccess() { // from class: com.amazon.deviceevents.com.google.gson.stream.JsonReader.1
            @Override // com.amazon.deviceevents.com.google.gson.internal.JsonReaderInternalAccess
            public void promoteNameToValue(JsonReader jsonReader) throws IOException {
                if (!(jsonReader instanceof JsonTreeReader)) {
                    int i = jsonReader.peeked;
                    if (i == 0) {
                        i = jsonReader.doPeek();
                    }
                    if (i == 13) {
                        jsonReader.peeked = 9;
                        return;
                    } else if (i == 12) {
                        jsonReader.peeked = 8;
                        return;
                    } else if (i == 14) {
                        jsonReader.peeked = 10;
                        return;
                    } else {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expected a name but was ");
                        outline107.append(jsonReader.peek());
                        outline107.append(" ");
                        outline107.append(" at line ");
                        outline107.append(jsonReader.getLineNumber());
                        outline107.append(" column ");
                        outline107.append(jsonReader.getColumnNumber());
                        outline107.append(" path ");
                        outline107.append(jsonReader.getPath());
                        throw new IllegalStateException(outline107.toString());
                    }
                }
                ((JsonTreeReader) jsonReader).promoteNameToValue();
            }
        };
    }

    public JsonReader(Reader reader) {
        this.stackSize = 0;
        int[] iArr = this.stack;
        int i = this.stackSize;
        this.stackSize = i + 1;
        iArr[i] = 6;
        this.pathNames = new String[32];
        this.pathIndices = new int[32];
        if (reader != null) {
            this.in = reader;
            return;
        }
        throw new NullPointerException("in == null");
    }

    private void checkLenient() throws IOException {
        if (this.lenient) {
            return;
        }
        throw syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
    }

    private void consumeNonExecutePrefix() throws IOException {
        nextNonWhitespace(true);
        this.pos--;
        int i = this.pos;
        char[] cArr = NON_EXECUTE_PREFIX;
        if (i + cArr.length <= this.limit || fillBuffer(cArr.length)) {
            int i2 = 0;
            while (true) {
                char[] cArr2 = NON_EXECUTE_PREFIX;
                if (i2 < cArr2.length) {
                    if (this.buffer[this.pos + i2] != cArr2[i2]) {
                        return;
                    }
                    i2++;
                } else {
                    this.pos += cArr2.length;
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int doPeek() throws IOException {
        int nextNonWhitespace;
        int[] iArr = this.stack;
        int i = this.stackSize;
        int i2 = iArr[i - 1];
        if (i2 == 1) {
            iArr[i - 1] = 2;
        } else if (i2 == 2) {
            int nextNonWhitespace2 = nextNonWhitespace(true);
            if (nextNonWhitespace2 != 44) {
                if (nextNonWhitespace2 != 59) {
                    if (nextNonWhitespace2 == 93) {
                        this.peeked = 4;
                        return 4;
                    }
                    throw syntaxError("Unterminated array");
                }
                checkLenient();
            }
        } else if (i2 == 3 || i2 == 5) {
            this.stack[this.stackSize - 1] = 4;
            if (i2 == 5 && (nextNonWhitespace = nextNonWhitespace(true)) != 44) {
                if (nextNonWhitespace != 59) {
                    if (nextNonWhitespace == 125) {
                        this.peeked = 2;
                        return 2;
                    }
                    throw syntaxError("Unterminated object");
                }
                checkLenient();
            }
            int nextNonWhitespace3 = nextNonWhitespace(true);
            if (nextNonWhitespace3 == 34) {
                this.peeked = 13;
                return 13;
            } else if (nextNonWhitespace3 == 39) {
                checkLenient();
                this.peeked = 12;
                return 12;
            } else if (nextNonWhitespace3 == 125) {
                if (i2 != 5) {
                    this.peeked = 2;
                    return 2;
                }
                throw syntaxError("Expected name");
            } else {
                checkLenient();
                this.pos--;
                if (isLiteral((char) nextNonWhitespace3)) {
                    this.peeked = 14;
                    return 14;
                }
                throw syntaxError("Expected name");
            }
        } else if (i2 == 4) {
            iArr[i - 1] = 5;
            int nextNonWhitespace4 = nextNonWhitespace(true);
            if (nextNonWhitespace4 != 58) {
                if (nextNonWhitespace4 == 61) {
                    checkLenient();
                    if (this.pos < this.limit || fillBuffer(1)) {
                        char[] cArr = this.buffer;
                        int i3 = this.pos;
                        if (cArr[i3] == '>') {
                            this.pos = i3 + 1;
                        }
                    }
                } else {
                    throw syntaxError("Expected ':'");
                }
            }
        } else if (i2 == 6) {
            if (this.lenient) {
                consumeNonExecutePrefix();
            }
            this.stack[this.stackSize - 1] = 7;
        } else if (i2 == 7) {
            if (nextNonWhitespace(false) == -1) {
                this.peeked = 17;
                return 17;
            }
            checkLenient();
            this.pos--;
        } else if (i2 == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        int nextNonWhitespace5 = nextNonWhitespace(true);
        if (nextNonWhitespace5 == 34) {
            if (this.stackSize == 1) {
                checkLenient();
            }
            this.peeked = 9;
            return 9;
        } else if (nextNonWhitespace5 == 39) {
            checkLenient();
            this.peeked = 8;
            return 8;
        } else {
            if (nextNonWhitespace5 != 44 && nextNonWhitespace5 != 59) {
                if (nextNonWhitespace5 == 91) {
                    this.peeked = 3;
                    return 3;
                } else if (nextNonWhitespace5 != 93) {
                    if (nextNonWhitespace5 != 123) {
                        this.pos--;
                        if (this.stackSize == 1) {
                            checkLenient();
                        }
                        int peekKeyword = peekKeyword();
                        if (peekKeyword != 0) {
                            return peekKeyword;
                        }
                        int peekNumber = peekNumber();
                        if (peekNumber != 0) {
                            return peekNumber;
                        }
                        if (isLiteral(this.buffer[this.pos])) {
                            checkLenient();
                            this.peeked = 10;
                            return 10;
                        }
                        throw syntaxError("Expected value");
                    }
                    this.peeked = 1;
                    return 1;
                } else if (i2 == 1) {
                    this.peeked = 4;
                    return 4;
                }
            }
            if (i2 != 1 && i2 != 2) {
                throw syntaxError("Unexpected value");
            }
            checkLenient();
            this.pos--;
            this.peeked = 7;
            return 7;
        }
    }

    private boolean fillBuffer(int i) throws IOException {
        int i2;
        char[] cArr = this.buffer;
        int i3 = this.lineStart;
        int i4 = this.pos;
        this.lineStart = i3 - i4;
        int i5 = this.limit;
        if (i5 != i4) {
            this.limit = i5 - i4;
            System.arraycopy(cArr, i4, cArr, 0, this.limit);
        } else {
            this.limit = 0;
        }
        this.pos = 0;
        do {
            Reader reader = this.in;
            int i6 = this.limit;
            int read = reader.read(cArr, i6, cArr.length - i6);
            if (read == -1) {
                return false;
            }
            this.limit += read;
            if (this.lineNumber == 0 && (i2 = this.lineStart) == 0 && this.limit > 0 && cArr[0] == 65279) {
                this.pos++;
                this.lineStart = i2 + 1;
                i++;
            }
        } while (this.limit < i);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getColumnNumber() {
        return (this.pos - this.lineStart) + 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getLineNumber() {
        return this.lineNumber + 1;
    }

    private boolean isLiteral(char c) throws IOException {
        if (c == '\t' || c == '\n' || c == '\f' || c == '\r' || c == ' ') {
            return false;
        }
        if (c != '#') {
            if (c == ',') {
                return false;
            }
            if (c != '/' && c != '=') {
                if (c == '{' || c == '}' || c == ':') {
                    return false;
                }
                if (c != ';') {
                    switch (c) {
                        case '[':
                        case ']':
                            return false;
                        case '\\':
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        checkLenient();
        return false;
    }

    private int nextNonWhitespace(boolean z) throws IOException {
        char[] cArr = this.buffer;
        int i = this.pos;
        int i2 = this.limit;
        while (true) {
            if (i == i2) {
                this.pos = i;
                if (!fillBuffer(1)) {
                    if (!z) {
                        return -1;
                    }
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("End of input at line ");
                    outline107.append(getLineNumber());
                    outline107.append(" column ");
                    outline107.append(getColumnNumber());
                    throw new EOFException(outline107.toString());
                }
                i = this.pos;
                i2 = this.limit;
            }
            int i3 = i + 1;
            char c = cArr[i];
            if (c == '\n') {
                this.lineNumber++;
                this.lineStart = i3;
            } else if (c != ' ' && c != '\r' && c != '\t') {
                if (c == '/') {
                    this.pos = i3;
                    if (i3 == i2) {
                        this.pos--;
                        boolean fillBuffer = fillBuffer(2);
                        this.pos++;
                        if (!fillBuffer) {
                            return c;
                        }
                    }
                    checkLenient();
                    int i4 = this.pos;
                    char c2 = cArr[i4];
                    if (c2 == '*') {
                        this.pos = i4 + 1;
                        if (skipTo("*/")) {
                            i = this.pos + 2;
                            i2 = this.limit;
                        } else {
                            throw syntaxError("Unterminated comment");
                        }
                    } else if (c2 != '/') {
                        return c;
                    } else {
                        this.pos = i4 + 1;
                        skipToEndOfLine();
                        i = this.pos;
                        i2 = this.limit;
                    }
                } else if (c == '#') {
                    this.pos = i3;
                    checkLenient();
                    skipToEndOfLine();
                    i = this.pos;
                    i2 = this.limit;
                } else {
                    this.pos = i3;
                    return c;
                }
            }
            i = i3;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0045, code lost:
        r1.append(r0, r4, r2 - r4);
        r8.pos = r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String nextQuotedValue(char r9) throws java.io.IOException {
        /*
            r8 = this;
            char[] r0 = r8.buffer
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
        L7:
            int r2 = r8.pos
            int r3 = r8.limit
        Lb:
            r4 = r2
        Lc:
            r5 = 1
            if (r2 >= r3) goto L45
            int r6 = r2 + 1
            char r2 = r0[r2]
            if (r2 != r9) goto L21
            r8.pos = r6
            int r6 = r6 - r4
            int r6 = r6 - r5
            r1.append(r0, r4, r6)
            java.lang.String r9 = r1.toString()
            return r9
        L21:
            r7 = 92
            if (r2 != r7) goto L38
            r8.pos = r6
            int r6 = r6 - r4
            int r6 = r6 - r5
            r1.append(r0, r4, r6)
            char r2 = r8.readEscapeCharacter()
            r1.append(r2)
            int r2 = r8.pos
            int r3 = r8.limit
            goto Lb
        L38:
            r7 = 10
            if (r2 != r7) goto L43
            int r2 = r8.lineNumber
            int r2 = r2 + r5
            r8.lineNumber = r2
            r8.lineStart = r6
        L43:
            r2 = r6
            goto Lc
        L45:
            int r3 = r2 - r4
            r1.append(r0, r4, r3)
            r8.pos = r2
            boolean r2 = r8.fillBuffer(r5)
            if (r2 == 0) goto L53
            goto L7
        L53:
            java.lang.String r9 = "Unterminated string"
            java.io.IOException r9 = r8.syntaxError(r9)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.deviceevents.com.google.gson.stream.JsonReader.nextQuotedValue(char):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x004b, code lost:
        checkLenient();
     */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0085  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String nextUnquotedValue() throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
            r2 = r1
        L3:
            r1 = r0
        L4:
            int r3 = r6.pos
            int r4 = r3 + r1
            int r5 = r6.limit
            if (r4 >= r5) goto L4f
            char[] r4 = r6.buffer
            int r3 = r3 + r1
            char r3 = r4[r3]
            r4 = 9
            if (r3 == r4) goto L5d
            r4 = 10
            if (r3 == r4) goto L5d
            r4 = 12
            if (r3 == r4) goto L5d
            r4 = 13
            if (r3 == r4) goto L5d
            r4 = 32
            if (r3 == r4) goto L5d
            r4 = 35
            if (r3 == r4) goto L4b
            r4 = 44
            if (r3 == r4) goto L5d
            r4 = 47
            if (r3 == r4) goto L4b
            r4 = 61
            if (r3 == r4) goto L4b
            r4 = 123(0x7b, float:1.72E-43)
            if (r3 == r4) goto L5d
            r4 = 125(0x7d, float:1.75E-43)
            if (r3 == r4) goto L5d
            r4 = 58
            if (r3 == r4) goto L5d
            r4 = 59
            if (r3 == r4) goto L4b
            switch(r3) {
                case 91: goto L5d;
                case 92: goto L4b;
                case 93: goto L5d;
                default: goto L48;
            }
        L48:
            int r1 = r1 + 1
            goto L4
        L4b:
            r6.checkLenient()
            goto L5d
        L4f:
            char[] r3 = r6.buffer
            int r3 = r3.length
            if (r1 >= r3) goto L5f
            int r3 = r1 + 1
            boolean r3 = r6.fillBuffer(r3)
            if (r3 == 0) goto L5d
            goto L4
        L5d:
            r0 = r1
            goto L79
        L5f:
            if (r2 != 0) goto L66
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
        L66:
            char[] r3 = r6.buffer
            int r4 = r6.pos
            r2.append(r3, r4, r1)
            int r3 = r6.pos
            int r3 = r3 + r1
            r6.pos = r3
            r1 = 1
            boolean r1 = r6.fillBuffer(r1)
            if (r1 != 0) goto L3
        L79:
            if (r2 != 0) goto L85
            java.lang.String r1 = new java.lang.String
            char[] r2 = r6.buffer
            int r3 = r6.pos
            r1.<init>(r2, r3, r0)
            goto L90
        L85:
            char[] r1 = r6.buffer
            int r3 = r6.pos
            r2.append(r1, r3, r0)
            java.lang.String r1 = r2.toString()
        L90:
            int r2 = r6.pos
            int r2 = r2 + r0
            r6.pos = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.deviceevents.com.google.gson.stream.JsonReader.nextUnquotedValue():java.lang.String");
    }

    private int peekKeyword() throws IOException {
        int i;
        String str;
        String str2;
        char c = this.buffer[this.pos];
        if (c == 't' || c == 'T') {
            i = 5;
            str = "true";
            str2 = "TRUE";
        } else if (c == 'f' || c == 'F') {
            i = 6;
            str = PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE;
            str2 = "FALSE";
        } else if (c != 'n' && c != 'N') {
            return 0;
        } else {
            i = 7;
            str = "null";
            str2 = AppInformation.NULL;
        }
        int length = str.length();
        for (int i2 = 1; i2 < length; i2++) {
            if (this.pos + i2 >= this.limit && !fillBuffer(i2 + 1)) {
                return 0;
            }
            char c2 = this.buffer[this.pos + i2];
            if (c2 != str.charAt(i2) && c2 != str2.charAt(i2)) {
                return 0;
            }
        }
        if ((this.pos + length < this.limit || fillBuffer(length + 1)) && isLiteral(this.buffer[this.pos + length])) {
            return 0;
        }
        this.pos += length;
        this.peeked = i;
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x0098, code lost:
        if (isLiteral(r14) != false) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x009a, code lost:
        if (r9 != 2) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x009c, code lost:
        if (r10 == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00a2, code lost:
        if (r11 != Long.MIN_VALUE) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00a4, code lost:
        if (r13 == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00a6, code lost:
        if (r13 == false) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00a9, code lost:
        r11 = -r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00aa, code lost:
        r18.peekedLong = r11;
        r18.pos += r3;
        r18.peeked = 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00b5, code lost:
        return 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00b6, code lost:
        if (r9 == 2) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00b9, code lost:
        if (r9 == 4) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00bc, code lost:
        if (r9 != 7) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00bf, code lost:
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00c1, code lost:
        r18.peekedNumberLength = r3;
        r18.peeked = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00c7, code lost:
        return 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00c8, code lost:
        return 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int peekNumber() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 246
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.deviceevents.com.google.gson.stream.JsonReader.peekNumber():int");
    }

    private void push(int i) {
        int i2 = this.stackSize;
        int[] iArr = this.stack;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[i2 * 2];
            int[] iArr3 = new int[i2 * 2];
            String[] strArr = new String[i2 * 2];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            System.arraycopy(this.pathIndices, 0, iArr3, 0, this.stackSize);
            System.arraycopy(this.pathNames, 0, strArr, 0, this.stackSize);
            this.stack = iArr2;
            this.pathIndices = iArr3;
            this.pathNames = strArr;
        }
        int[] iArr4 = this.stack;
        int i3 = this.stackSize;
        this.stackSize = i3 + 1;
        iArr4[i3] = i;
    }

    private char readEscapeCharacter() throws IOException {
        int i;
        int i2;
        if (this.pos == this.limit && !fillBuffer(1)) {
            throw syntaxError("Unterminated escape sequence");
        }
        char[] cArr = this.buffer;
        int i3 = this.pos;
        this.pos = i3 + 1;
        char c = cArr[i3];
        if (c == '\n') {
            this.lineNumber++;
            this.lineStart = this.pos;
        } else if (c == 'b') {
            return '\b';
        } else {
            if (c == 'f') {
                return '\f';
            }
            if (c == 'n') {
                return '\n';
            }
            if (c == 'r') {
                return '\r';
            }
            if (c == 't') {
                return '\t';
            }
            if (c == 'u') {
                if (this.pos + 4 > this.limit && !fillBuffer(4)) {
                    throw syntaxError("Unterminated escape sequence");
                }
                char c2 = 0;
                int i4 = this.pos;
                int i5 = i4 + 4;
                while (i4 < i5) {
                    char c3 = this.buffer[i4];
                    char c4 = (char) (c2 << 4);
                    if (c3 < '0' || c3 > '9') {
                        if (c3 >= 'a' && c3 <= 'f') {
                            i = c3 - 'a';
                        } else if (c3 < 'A' || c3 > 'F') {
                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("\\u");
                            outline107.append(new String(this.buffer, this.pos, 4));
                            throw new NumberFormatException(outline107.toString());
                        } else {
                            i = c3 - 'A';
                        }
                        i2 = i + 10;
                    } else {
                        i2 = c3 - '0';
                    }
                    c2 = (char) (i2 + c4);
                    i4++;
                }
                this.pos += 4;
                return c2;
            }
        }
        return c;
    }

    private void skipQuotedValue(char c) throws IOException {
        char[] cArr = this.buffer;
        do {
            int i = this.pos;
            int i2 = this.limit;
            while (i < i2) {
                int i3 = i + 1;
                char c2 = cArr[i];
                if (c2 == c) {
                    this.pos = i3;
                    return;
                } else if (c2 == '\\') {
                    this.pos = i3;
                    readEscapeCharacter();
                    i = this.pos;
                    i2 = this.limit;
                } else {
                    if (c2 == '\n') {
                        this.lineNumber++;
                        this.lineStart = i3;
                    }
                    i = i3;
                }
            }
            this.pos = i;
        } while (fillBuffer(1));
        throw syntaxError("Unterminated string");
    }

    private boolean skipTo(String str) throws IOException {
        while (true) {
            if (str.length() + this.pos <= this.limit || fillBuffer(str.length())) {
                char[] cArr = this.buffer;
                int i = this.pos;
                if (cArr[i] == '\n') {
                    this.lineNumber++;
                    this.lineStart = i + 1;
                } else {
                    for (int i2 = 0; i2 < str.length(); i2++) {
                        if (this.buffer[this.pos + i2] != str.charAt(i2)) {
                            break;
                        }
                    }
                    return true;
                }
                this.pos++;
            } else {
                return false;
            }
        }
    }

    private void skipToEndOfLine() throws IOException {
        char c;
        do {
            if (this.pos >= this.limit && !fillBuffer(1)) {
                return;
            }
            char[] cArr = this.buffer;
            int i = this.pos;
            this.pos = i + 1;
            c = cArr[i];
            if (c == '\n') {
                this.lineNumber++;
                this.lineStart = this.pos;
                return;
            }
        } while (c != '\r');
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0048, code lost:
        checkLenient();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void skipUnquotedValue() throws java.io.IOException {
        /*
            r4 = this;
        L0:
            r0 = 0
        L1:
            int r1 = r4.pos
            int r2 = r1 + r0
            int r3 = r4.limit
            if (r2 >= r3) goto L51
            char[] r2 = r4.buffer
            int r1 = r1 + r0
            char r1 = r2[r1]
            r2 = 9
            if (r1 == r2) goto L4b
            r2 = 10
            if (r1 == r2) goto L4b
            r2 = 12
            if (r1 == r2) goto L4b
            r2 = 13
            if (r1 == r2) goto L4b
            r2 = 32
            if (r1 == r2) goto L4b
            r2 = 35
            if (r1 == r2) goto L48
            r2 = 44
            if (r1 == r2) goto L4b
            r2 = 47
            if (r1 == r2) goto L48
            r2 = 61
            if (r1 == r2) goto L48
            r2 = 123(0x7b, float:1.72E-43)
            if (r1 == r2) goto L4b
            r2 = 125(0x7d, float:1.75E-43)
            if (r1 == r2) goto L4b
            r2 = 58
            if (r1 == r2) goto L4b
            r2 = 59
            if (r1 == r2) goto L48
            switch(r1) {
                case 91: goto L4b;
                case 92: goto L48;
                case 93: goto L4b;
                default: goto L45;
            }
        L45:
            int r0 = r0 + 1
            goto L1
        L48:
            r4.checkLenient()
        L4b:
            int r1 = r4.pos
            int r1 = r1 + r0
            r4.pos = r1
            return
        L51:
            int r1 = r1 + r0
            r4.pos = r1
            r0 = 1
            boolean r0 = r4.fillBuffer(r0)
            if (r0 != 0) goto L0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.deviceevents.com.google.gson.stream.JsonReader.skipUnquotedValue():void");
    }

    private IOException syntaxError(String str) throws IOException {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, " at line ");
        outline113.append(getLineNumber());
        outline113.append(" column ");
        outline113.append(getColumnNumber());
        outline113.append(" path ");
        outline113.append(getPath());
        throw new MalformedJsonException(outline113.toString());
    }

    public void beginArray() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 3) {
            push(1);
            this.pathIndices[this.stackSize - 1] = 0;
            this.peeked = 0;
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expected BEGIN_ARRAY but was ");
        outline107.append(peek());
        outline107.append(" at line ");
        outline107.append(getLineNumber());
        outline107.append(" column ");
        outline107.append(getColumnNumber());
        outline107.append(" path ");
        outline107.append(getPath());
        throw new IllegalStateException(outline107.toString());
    }

    public void beginObject() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 1) {
            push(3);
            this.peeked = 0;
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expected BEGIN_OBJECT but was ");
        outline107.append(peek());
        outline107.append(" at line ");
        outline107.append(getLineNumber());
        outline107.append(" column ");
        outline107.append(getColumnNumber());
        outline107.append(" path ");
        outline107.append(getPath());
        throw new IllegalStateException(outline107.toString());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.peeked = 0;
        this.stack[0] = 8;
        this.stackSize = 1;
        this.in.close();
    }

    public void endArray() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 4) {
            this.stackSize--;
            int[] iArr = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr[i2] = iArr[i2] + 1;
            this.peeked = 0;
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expected END_ARRAY but was ");
        outline107.append(peek());
        outline107.append(" at line ");
        outline107.append(getLineNumber());
        outline107.append(" column ");
        outline107.append(getColumnNumber());
        outline107.append(" path ");
        outline107.append(getPath());
        throw new IllegalStateException(outline107.toString());
    }

    public void endObject() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 2) {
            this.stackSize--;
            String[] strArr = this.pathNames;
            int i2 = this.stackSize;
            strArr[i2] = null;
            int[] iArr = this.pathIndices;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
            this.peeked = 0;
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expected END_OBJECT but was ");
        outline107.append(peek());
        outline107.append(" at line ");
        outline107.append(getLineNumber());
        outline107.append(" column ");
        outline107.append(getColumnNumber());
        outline107.append(" path ");
        outline107.append(getPath());
        throw new IllegalStateException(outline107.toString());
    }

    public String getPath() {
        StringBuilder outline104 = GeneratedOutlineSupport1.outline104('$');
        int i = this.stackSize;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = this.stack[i2];
            if (i3 == 1 || i3 == 2) {
                outline104.append(JsonReaderKt.BEGIN_LIST);
                outline104.append(this.pathIndices[i2]);
                outline104.append(JsonReaderKt.END_LIST);
            } else if (i3 == 3 || i3 == 4 || i3 == 5) {
                outline104.append('.');
                String[] strArr = this.pathNames;
                if (strArr[i2] != null) {
                    outline104.append(strArr[i2]);
                }
            }
        }
        return outline104.toString();
    }

    public boolean hasNext() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        return (i == 2 || i == 4) ? false : true;
    }

    public final boolean isLenient() {
        return this.lenient;
    }

    public boolean nextBoolean() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 5) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        } else if (i == 6) {
            this.peeked = 0;
            int[] iArr2 = this.pathIndices;
            int i3 = this.stackSize - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return false;
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expected a boolean but was ");
            outline107.append(peek());
            outline107.append(" at line ");
            outline107.append(getLineNumber());
            outline107.append(" column ");
            outline107.append(getColumnNumber());
            outline107.append(" path ");
            outline107.append(getPath());
            throw new IllegalStateException(outline107.toString());
        }
    }

    public double nextDouble() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 15) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.peekedLong;
        }
        if (i == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (i == 8 || i == 9) {
            this.peekedString = nextQuotedValue(i == 8 ? Chars.QUOTE : '\"');
        } else if (i == 10) {
            this.peekedString = nextUnquotedValue();
        } else if (i != 11) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expected a double but was ");
            outline107.append(peek());
            outline107.append(" at line ");
            outline107.append(getLineNumber());
            outline107.append(" column ");
            outline107.append(getColumnNumber());
            outline107.append(" path ");
            outline107.append(getPath());
            throw new IllegalStateException(outline107.toString());
        }
        this.peeked = 11;
        double parseDouble = Double.parseDouble(this.peekedString);
        if (!this.lenient && (Double.isNaN(parseDouble) || Double.isInfinite(parseDouble))) {
            throw new MalformedJsonException("JSON forbids NaN and infinities: " + parseDouble + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
        }
        this.peekedString = null;
        this.peeked = 0;
        int[] iArr2 = this.pathIndices;
        int i3 = this.stackSize - 1;
        iArr2[i3] = iArr2[i3] + 1;
        return parseDouble;
    }

    public int nextInt() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 15) {
            long j = this.peekedLong;
            int i2 = (int) j;
            if (j == i2) {
                this.peeked = 0;
                int[] iArr = this.pathIndices;
                int i3 = this.stackSize - 1;
                iArr[i3] = iArr[i3] + 1;
                return i2;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expected an int but was ");
            outline107.append(this.peekedLong);
            outline107.append(" at line ");
            outline107.append(getLineNumber());
            outline107.append(" column ");
            outline107.append(getColumnNumber());
            outline107.append(" path ");
            outline107.append(getPath());
            throw new NumberFormatException(outline107.toString());
        }
        if (i == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (i != 8 && i != 9) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Expected an int but was ");
            outline1072.append(peek());
            outline1072.append(" at line ");
            outline1072.append(getLineNumber());
            outline1072.append(" column ");
            outline1072.append(getColumnNumber());
            outline1072.append(" path ");
            outline1072.append(getPath());
            throw new IllegalStateException(outline1072.toString());
        } else {
            this.peekedString = nextQuotedValue(i == 8 ? Chars.QUOTE : '\"');
            try {
                int parseInt = Integer.parseInt(this.peekedString);
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i4 = this.stackSize - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return parseInt;
            } catch (NumberFormatException unused) {
            }
        }
        this.peeked = 11;
        double parseDouble = Double.parseDouble(this.peekedString);
        int i5 = (int) parseDouble;
        if (i5 == parseDouble) {
            this.peekedString = null;
            this.peeked = 0;
            int[] iArr3 = this.pathIndices;
            int i6 = this.stackSize - 1;
            iArr3[i6] = iArr3[i6] + 1;
            return i5;
        }
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Expected an int but was ");
        outline1073.append(this.peekedString);
        outline1073.append(" at line ");
        outline1073.append(getLineNumber());
        outline1073.append(" column ");
        outline1073.append(getColumnNumber());
        outline1073.append(" path ");
        outline1073.append(getPath());
        throw new NumberFormatException(outline1073.toString());
    }

    public long nextLong() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 15) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.peekedLong;
        }
        if (i == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (i != 8 && i != 9) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expected a long but was ");
            outline107.append(peek());
            outline107.append(" at line ");
            outline107.append(getLineNumber());
            outline107.append(" column ");
            outline107.append(getColumnNumber());
            outline107.append(" path ");
            outline107.append(getPath());
            throw new IllegalStateException(outline107.toString());
        } else {
            this.peekedString = nextQuotedValue(i == 8 ? Chars.QUOTE : '\"');
            try {
                long parseLong = Long.parseLong(this.peekedString);
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i3 = this.stackSize - 1;
                iArr2[i3] = iArr2[i3] + 1;
                return parseLong;
            } catch (NumberFormatException unused) {
            }
        }
        this.peeked = 11;
        double parseDouble = Double.parseDouble(this.peekedString);
        long j = (long) parseDouble;
        if (j == parseDouble) {
            this.peekedString = null;
            this.peeked = 0;
            int[] iArr3 = this.pathIndices;
            int i4 = this.stackSize - 1;
            iArr3[i4] = iArr3[i4] + 1;
            return j;
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Expected a long but was ");
        outline1072.append(this.peekedString);
        outline1072.append(" at line ");
        outline1072.append(getLineNumber());
        outline1072.append(" column ");
        outline1072.append(getColumnNumber());
        outline1072.append(" path ");
        outline1072.append(getPath());
        throw new NumberFormatException(outline1072.toString());
    }

    public String nextName() throws IOException {
        String nextQuotedValue;
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 14) {
            nextQuotedValue = nextUnquotedValue();
        } else if (i == 12) {
            nextQuotedValue = nextQuotedValue(Chars.QUOTE);
        } else if (i == 13) {
            nextQuotedValue = nextQuotedValue('\"');
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expected a name but was ");
            outline107.append(peek());
            outline107.append(" at line ");
            outline107.append(getLineNumber());
            outline107.append(" column ");
            outline107.append(getColumnNumber());
            outline107.append(" path ");
            outline107.append(getPath());
            throw new IllegalStateException(outline107.toString());
        }
        this.peeked = 0;
        this.pathNames[this.stackSize - 1] = nextQuotedValue;
        return nextQuotedValue;
    }

    public void nextNull() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 7) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expected null but was ");
        outline107.append(peek());
        outline107.append(" at line ");
        outline107.append(getLineNumber());
        outline107.append(" column ");
        outline107.append(getColumnNumber());
        outline107.append(" path ");
        outline107.append(getPath());
        throw new IllegalStateException(outline107.toString());
    }

    public String nextString() throws IOException {
        String str;
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 10) {
            str = nextUnquotedValue();
        } else if (i == 8) {
            str = nextQuotedValue(Chars.QUOTE);
        } else if (i == 9) {
            str = nextQuotedValue('\"');
        } else if (i == 11) {
            str = this.peekedString;
            this.peekedString = null;
        } else if (i == 15) {
            str = Long.toString(this.peekedLong);
        } else if (i == 16) {
            str = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expected a string but was ");
            outline107.append(peek());
            outline107.append(" at line ");
            outline107.append(getLineNumber());
            outline107.append(" column ");
            outline107.append(getColumnNumber());
            outline107.append(" path ");
            outline107.append(getPath());
            throw new IllegalStateException(outline107.toString());
        }
        this.peeked = 0;
        int[] iArr = this.pathIndices;
        int i2 = this.stackSize - 1;
        iArr[i2] = iArr[i2] + 1;
        return str;
    }

    public JsonToken peek() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        switch (i) {
            case 1:
                return JsonToken.BEGIN_OBJECT;
            case 2:
                return JsonToken.END_OBJECT;
            case 3:
                return JsonToken.BEGIN_ARRAY;
            case 4:
                return JsonToken.END_ARRAY;
            case 5:
            case 6:
                return JsonToken.BOOLEAN;
            case 7:
                return JsonToken.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return JsonToken.STRING;
            case 12:
            case 13:
            case 14:
                return JsonToken.NAME;
            case 15:
            case 16:
                return JsonToken.NUMBER;
            case 17:
                return JsonToken.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    public final void setLenient(boolean z) {
        this.lenient = z;
    }

    public void skipValue() throws IOException {
        int i = 0;
        do {
            int i2 = this.peeked;
            if (i2 == 0) {
                i2 = doPeek();
            }
            if (i2 == 3) {
                push(1);
            } else if (i2 == 1) {
                push(3);
            } else {
                if (i2 == 4) {
                    this.stackSize--;
                } else if (i2 == 2) {
                    this.stackSize--;
                } else {
                    if (i2 == 14 || i2 == 10) {
                        skipUnquotedValue();
                    } else if (i2 == 8 || i2 == 12) {
                        skipQuotedValue(Chars.QUOTE);
                    } else if (i2 == 9 || i2 == 13) {
                        skipQuotedValue('\"');
                    } else if (i2 == 16) {
                        this.pos += this.peekedNumberLength;
                    }
                    this.peeked = 0;
                }
                i--;
                this.peeked = 0;
            }
            i++;
            this.peeked = 0;
        } while (i != 0);
        int[] iArr = this.pathIndices;
        int i3 = this.stackSize;
        int i4 = i3 - 1;
        iArr[i4] = iArr[i4] + 1;
        this.pathNames[i3 - 1] = "null";
    }

    public String toString() {
        return getClass().getSimpleName() + " at line " + getLineNumber() + " column " + getColumnNumber();
    }
}
