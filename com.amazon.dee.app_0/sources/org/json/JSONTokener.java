package org.json;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
/* loaded from: classes5.dex */
public class JSONTokener {
    private int index;
    private char lastChar;
    private Reader reader;
    private boolean useLastChar;

    public JSONTokener(Reader reader) {
        this.reader = !reader.markSupported() ? new BufferedReader(reader) : reader;
        this.useLastChar = false;
        this.index = 0;
    }

    public static int dehexchar(char c) {
        if (c < '0' || c > '9') {
            if (c >= 'A' && c <= 'F') {
                return c - '7';
            }
            if (c >= 'a' && c <= 'f') {
                return c - 'W';
            }
            return -1;
        }
        return c - '0';
    }

    public void back() throws JSONException {
        int i;
        if (!this.useLastChar && (i = this.index) > 0) {
            this.index = i - 1;
            this.useLastChar = true;
            return;
        }
        throw new JSONException("Stepping back two steps is not supported");
    }

    public boolean more() throws JSONException {
        if (next() == 0) {
            return false;
        }
        back();
        return true;
    }

    public char next() throws JSONException {
        if (this.useLastChar) {
            this.useLastChar = false;
            if (this.lastChar != 0) {
                this.index++;
            }
            return this.lastChar;
        }
        try {
            int read = this.reader.read();
            if (read <= 0) {
                this.lastChar = (char) 0;
                return (char) 0;
            }
            this.index++;
            this.lastChar = (char) read;
            return this.lastChar;
        } catch (IOException e) {
            throw new JSONException(e);
        }
    }

    public char nextClean() throws JSONException {
        char next;
        do {
            next = next();
            if (next == 0) {
                break;
            }
        } while (next <= ' ');
        return next;
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0084, code lost:
        throw syntaxError("Unterminated string");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String nextString(char r6) throws org.json.JSONException {
        /*
            r5 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
        L5:
            char r1 = r5.next()
            if (r1 == 0) goto L7e
            r2 = 10
            if (r1 == r2) goto L7e
            r3 = 13
            if (r1 == r3) goto L7e
            r4 = 92
            if (r1 == r4) goto L22
            if (r1 != r6) goto L1e
            java.lang.String r6 = r0.toString()
            return r6
        L1e:
            r0.append(r1)
            goto L5
        L22:
            char r1 = r5.next()
            r4 = 98
            if (r1 == r4) goto L78
            r4 = 102(0x66, float:1.43E-43)
            if (r1 == r4) goto L72
            r4 = 110(0x6e, float:1.54E-43)
            if (r1 == r4) goto L6e
            r2 = 114(0x72, float:1.6E-43)
            if (r1 == r2) goto L6a
            r2 = 120(0x78, float:1.68E-43)
            r3 = 16
            if (r1 == r2) goto L5c
            r2 = 116(0x74, float:1.63E-43)
            if (r1 == r2) goto L56
            r2 = 117(0x75, float:1.64E-43)
            if (r1 == r2) goto L48
            r0.append(r1)
            goto L5
        L48:
            r1 = 4
            java.lang.String r1 = r5.next(r1)
            int r1 = java.lang.Integer.parseInt(r1, r3)
            char r1 = (char) r1
            r0.append(r1)
            goto L5
        L56:
            r1 = 9
            r0.append(r1)
            goto L5
        L5c:
            r1 = 2
            java.lang.String r1 = r5.next(r1)
            int r1 = java.lang.Integer.parseInt(r1, r3)
            char r1 = (char) r1
            r0.append(r1)
            goto L5
        L6a:
            r0.append(r3)
            goto L5
        L6e:
            r0.append(r2)
            goto L5
        L72:
            r1 = 12
            r0.append(r1)
            goto L5
        L78:
            r1 = 8
            r0.append(r1)
            goto L5
        L7e:
            java.lang.String r6 = "Unterminated string"
            org.json.JSONException r6 = r5.syntaxError(r6)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONTokener.nextString(char):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x001c, code lost:
        back();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String nextTo(char r4) throws org.json.JSONException {
        /*
            r3 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
        L5:
            char r1 = r3.next()
            if (r1 == r4) goto L1a
            if (r1 == 0) goto L1a
            r2 = 10
            if (r1 == r2) goto L1a
            r2 = 13
            if (r1 != r2) goto L16
            goto L1a
        L16:
            r0.append(r1)
            goto L5
        L1a:
            if (r1 == 0) goto L1f
            r3.back()
        L1f:
            java.lang.String r4 = r0.toString()
            java.lang.String r4 = r4.trim()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONTokener.nextTo(char):java.lang.String");
    }

    public Object nextValue() throws JSONException {
        char nextClean = nextClean();
        if (nextClean != '\"') {
            if (nextClean != '[') {
                if (nextClean == '{') {
                    back();
                    return new JSONObject(this);
                } else if (nextClean != '\'') {
                    if (nextClean != '(') {
                        StringBuffer stringBuffer = new StringBuffer();
                        while (nextClean >= ' ' && ",:]}/\\\"[{;=#".indexOf(nextClean) < 0) {
                            stringBuffer.append(nextClean);
                            nextClean = next();
                        }
                        back();
                        String trim = stringBuffer.toString().trim();
                        if (!trim.equals("")) {
                            return JSONObject.stringToValue(trim);
                        }
                        throw syntaxError("Missing value");
                    }
                }
            }
            back();
            return new JSONArray(this);
        }
        return nextString(nextClean);
    }

    public char skipTo(char c) throws JSONException {
        char next;
        try {
            int i = this.index;
            this.reader.mark(Integer.MAX_VALUE);
            do {
                next = next();
                if (next == 0) {
                    this.reader.reset();
                    this.index = i;
                    return next;
                }
            } while (next != c);
            back();
            return next;
        } catch (IOException e) {
            throw new JSONException(e);
        }
    }

    public JSONException syntaxError(String str) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str);
        outline107.append(toString());
        return new JSONException(outline107.toString());
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(" at character ");
        outline107.append(this.index);
        return outline107.toString();
    }

    public JSONTokener(String str) {
        this(new StringReader(str));
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0020, code lost:
        back();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String nextTo(java.lang.String r4) throws org.json.JSONException {
        /*
            r3 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
        L5:
            char r1 = r3.next()
            int r2 = r4.indexOf(r1)
            if (r2 >= 0) goto L1e
            if (r1 == 0) goto L1e
            r2 = 10
            if (r1 == r2) goto L1e
            r2 = 13
            if (r1 != r2) goto L1a
            goto L1e
        L1a:
            r0.append(r1)
            goto L5
        L1e:
            if (r1 == 0) goto L23
            r3.back()
        L23:
            java.lang.String r4 = r0.toString()
            java.lang.String r4 = r4.trim()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONTokener.nextTo(java.lang.String):java.lang.String");
    }

    public char next(char c) throws JSONException {
        char next = next();
        if (next == c) {
            return next;
        }
        throw syntaxError("Expected '" + c + "' and instead saw '" + next + "'");
    }

    public String next(int i) throws JSONException {
        if (i == 0) {
            return "";
        }
        char[] cArr = new char[i];
        int i2 = 0;
        if (this.useLastChar) {
            this.useLastChar = false;
            cArr[0] = this.lastChar;
            i2 = 1;
        }
        while (i2 < i) {
            try {
                int read = this.reader.read(cArr, i2, i - i2);
                if (read == -1) {
                    break;
                }
                i2 += read;
            } catch (IOException e) {
                throw new JSONException(e);
            }
        }
        this.index += i2;
        if (i2 >= i) {
            this.lastChar = cArr[i - 1];
            return new String(cArr);
        }
        throw syntaxError("Substring bounds error");
    }
}
