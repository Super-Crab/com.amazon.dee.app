package org.apache.commons.fileupload;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.fileupload.util.mime.MimeUtility;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes4.dex */
public class ParameterParser {
    private char[] chars = null;
    private int pos = 0;
    private int len = 0;
    private int i1 = 0;
    private int i2 = 0;
    private boolean lowerCaseNames = false;

    private String getToken(boolean z) {
        while (true) {
            int i = this.i1;
            if (i >= this.i2 || !Character.isWhitespace(this.chars[i])) {
                break;
            }
            this.i1++;
        }
        while (true) {
            int i2 = this.i2;
            if (i2 <= this.i1 || !Character.isWhitespace(this.chars[i2 - 1])) {
                break;
            }
            this.i2--;
        }
        if (z) {
            int i3 = this.i2;
            int i4 = this.i1;
            if (i3 - i4 >= 2) {
                char[] cArr = this.chars;
                if (cArr[i4] == '\"' && cArr[i3 - 1] == '\"') {
                    this.i1 = i4 + 1;
                    this.i2 = i3 - 1;
                }
            }
        }
        int i5 = this.i2;
        int i6 = this.i1;
        if (i5 > i6) {
            return new String(this.chars, i6, i5 - i6);
        }
        return null;
    }

    private boolean hasChar() {
        return this.pos < this.len;
    }

    private boolean isOneOf(char c, char[] cArr) {
        for (char c2 : cArr) {
            if (c == c2) {
                return true;
            }
        }
        return false;
    }

    private String parseQuotedToken(char[] cArr) {
        int i = this.pos;
        this.i1 = i;
        this.i2 = i;
        boolean z = false;
        boolean z2 = false;
        while (hasChar()) {
            char c = this.chars[this.pos];
            if (!z && isOneOf(c, cArr)) {
                break;
            }
            if (!z2 && c == '\"') {
                z = !z;
            }
            z2 = !z2 && c == '\\';
            this.i2++;
            this.pos++;
        }
        return getToken(true);
    }

    private String parseToken(char[] cArr) {
        int i = this.pos;
        this.i1 = i;
        this.i2 = i;
        while (hasChar() && !isOneOf(this.chars[this.pos], cArr)) {
            this.i2++;
            this.pos++;
        }
        return getToken(false);
    }

    public boolean isLowerCaseNames() {
        return this.lowerCaseNames;
    }

    public Map<String, String> parse(String str, char[] cArr) {
        if (cArr != null && cArr.length != 0) {
            char c = cArr[0];
            if (str != null) {
                int length = str.length();
                for (char c2 : cArr) {
                    int indexOf = str.indexOf(c2);
                    if (indexOf != -1 && indexOf < length) {
                        c = c2;
                        length = indexOf;
                    }
                }
            }
            return parse(str, c);
        }
        return new HashMap();
    }

    public void setLowerCaseNames(boolean z) {
        this.lowerCaseNames = z;
    }

    public Map<String, String> parse(String str, char c) {
        if (str == null) {
            return new HashMap();
        }
        return parse(str.toCharArray(), c);
    }

    public Map<String, String> parse(char[] cArr, char c) {
        if (cArr == null) {
            return new HashMap();
        }
        return parse(cArr, 0, cArr.length, c);
    }

    public Map<String, String> parse(char[] cArr, int i, int i2, char c) {
        if (cArr == null) {
            return new HashMap();
        }
        HashMap hashMap = new HashMap();
        this.chars = cArr;
        this.pos = i;
        this.len = i2;
        while (hasChar()) {
            String parseToken = parseToken(new char[]{Chars.EQ, c});
            String str = null;
            if (hasChar()) {
                int i3 = this.pos;
                if (cArr[i3] == '=') {
                    this.pos = i3 + 1;
                    str = parseQuotedToken(new char[]{c});
                    if (str != null) {
                        try {
                            str = MimeUtility.decodeText(str);
                        } catch (UnsupportedEncodingException unused) {
                        }
                    }
                }
            }
            if (hasChar()) {
                int i4 = this.pos;
                if (cArr[i4] == c) {
                    this.pos = i4 + 1;
                }
            }
            if (parseToken != null && parseToken.length() > 0) {
                if (this.lowerCaseNames) {
                    parseToken = parseToken.toLowerCase(Locale.ENGLISH);
                }
                hashMap.put(parseToken, str);
            }
        }
        return hashMap;
    }
}
