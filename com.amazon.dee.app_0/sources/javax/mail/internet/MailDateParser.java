package javax.mail.internet;
/* compiled from: MailDateFormat.java */
/* loaded from: classes3.dex */
class MailDateParser {
    int index;
    char[] orig;

    public MailDateParser(char[] cArr, int i) {
        this.index = 0;
        this.orig = null;
        this.orig = cArr;
        this.index = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getIndex() {
        return this.index;
    }

    public int parseAlphaTimeZone() throws java.text.ParseException {
        try {
            char[] cArr = this.orig;
            int i = this.index;
            this.index = i + 1;
            char c = cArr[i];
            int i2 = 0;
            boolean z = true;
            switch (c) {
                case 'C':
                case 'c':
                    i2 = 360;
                    break;
                case 'E':
                case 'e':
                    i2 = 300;
                    break;
                case 'G':
                case 'g':
                    char[] cArr2 = this.orig;
                    int i3 = this.index;
                    this.index = i3 + 1;
                    char c2 = cArr2[i3];
                    if (c2 == 'M' || c2 == 'm') {
                        char[] cArr3 = this.orig;
                        int i4 = this.index;
                        this.index = i4 + 1;
                        char c3 = cArr3[i4];
                        if (c3 != 'T') {
                            if (c3 == 't') {
                            }
                        }
                        z = false;
                    }
                    throw new java.text.ParseException("Bad Alpha TimeZone", this.index);
                case 'M':
                case 'm':
                    i2 = 420;
                    break;
                case 'P':
                case 'p':
                    i2 = 480;
                    break;
                case 'U':
                case 'u':
                    char[] cArr4 = this.orig;
                    int i5 = this.index;
                    this.index = i5 + 1;
                    char c4 = cArr4[i5];
                    if (c4 != 'T' && c4 != 't') {
                        throw new java.text.ParseException("Bad Alpha TimeZone", this.index);
                    }
                    z = false;
                default:
                    throw new java.text.ParseException("Bad Alpha TimeZone", this.index);
            }
            if (!z) {
                return i2;
            }
            char[] cArr5 = this.orig;
            int i6 = this.index;
            this.index = i6 + 1;
            char c5 = cArr5[i6];
            if (c5 == 'S' || c5 == 's') {
                char[] cArr6 = this.orig;
                int i7 = this.index;
                this.index = i7 + 1;
                char c6 = cArr6[i7];
                if (c6 != 'T' && c6 != 't') {
                    throw new java.text.ParseException("Bad Alpha TimeZone", this.index);
                }
                return i2;
            } else if (c5 != 'D' && c5 != 'd') {
                return i2;
            } else {
                char[] cArr7 = this.orig;
                int i8 = this.index;
                this.index = i8 + 1;
                char c7 = cArr7[i8];
                if (c7 != 'T' && c7 == 't') {
                    throw new java.text.ParseException("Bad Alpha TimeZone", this.index);
                }
                return i2 - 60;
            }
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new java.text.ParseException("Bad Alpha TimeZone", this.index);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:70:0x00f4, code lost:
        if (r0 == 'u') goto L76;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004e A[Catch: ArrayIndexOutOfBoundsException -> 0x0199, TryCatch #0 {ArrayIndexOutOfBoundsException -> 0x0199, blocks: (B:2:0x0000, B:21:0x0046, B:22:0x0049, B:24:0x004e, B:27:0x005c, B:33:0x0071, B:37:0x0083, B:43:0x0098, B:46:0x00a6, B:58:0x00c1, B:61:0x00cf, B:66:0x00e0, B:71:0x00f6, B:83:0x0111, B:88:0x0121, B:91:0x012f, B:97:0x0143, B:100:0x0151, B:105:0x0162, B:111:0x0175, B:117:0x0189), top: B:126:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0071 A[Catch: ArrayIndexOutOfBoundsException -> 0x0199, TryCatch #0 {ArrayIndexOutOfBoundsException -> 0x0199, blocks: (B:2:0x0000, B:21:0x0046, B:22:0x0049, B:24:0x004e, B:27:0x005c, B:33:0x0071, B:37:0x0083, B:43:0x0098, B:46:0x00a6, B:58:0x00c1, B:61:0x00cf, B:66:0x00e0, B:71:0x00f6, B:83:0x0111, B:88:0x0121, B:91:0x012f, B:97:0x0143, B:100:0x0151, B:105:0x0162, B:111:0x0175, B:117:0x0189), top: B:126:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0098 A[Catch: ArrayIndexOutOfBoundsException -> 0x0199, TryCatch #0 {ArrayIndexOutOfBoundsException -> 0x0199, blocks: (B:2:0x0000, B:21:0x0046, B:22:0x0049, B:24:0x004e, B:27:0x005c, B:33:0x0071, B:37:0x0083, B:43:0x0098, B:46:0x00a6, B:58:0x00c1, B:61:0x00cf, B:66:0x00e0, B:71:0x00f6, B:83:0x0111, B:88:0x0121, B:91:0x012f, B:97:0x0143, B:100:0x0151, B:105:0x0162, B:111:0x0175, B:117:0x0189), top: B:126:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int parseMonth() throws java.text.ParseException {
        /*
            Method dump skipped, instructions count: 440
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.MailDateParser.parseMonth():int");
    }

    public int parseNumber() throws java.text.ParseException {
        int length = this.orig.length;
        boolean z = false;
        int i = 0;
        while (true) {
            int i2 = this.index;
            if (i2 >= length) {
                if (!z) {
                    throw new java.text.ParseException("No Number found", i2);
                }
                return i;
            }
            switch (this.orig[i2]) {
                case '0':
                    i *= 10;
                    break;
                case '1':
                    i = (i * 10) + 1;
                    break;
                case '2':
                    i = (i * 10) + 2;
                    break;
                case '3':
                    i = (i * 10) + 3;
                    break;
                case '4':
                    i = (i * 10) + 4;
                    break;
                case '5':
                    i = (i * 10) + 5;
                    break;
                case '6':
                    i = (i * 10) + 6;
                    break;
                case '7':
                    i = (i * 10) + 7;
                    break;
                case '8':
                    i = (i * 10) + 8;
                    break;
                case '9':
                    i = (i * 10) + 9;
                    break;
                default:
                    if (!z) {
                        throw new java.text.ParseException("No Number found", i2);
                    }
                    return i;
            }
            this.index++;
            z = true;
        }
    }

    public int parseNumericTimeZone() throws java.text.ParseException {
        boolean z;
        char[] cArr = this.orig;
        int i = this.index;
        this.index = i + 1;
        char c = cArr[i];
        if (c == '+') {
            z = true;
        } else if (c != '-') {
            throw new java.text.ParseException("Bad Numeric TimeZone", this.index);
        } else {
            z = false;
        }
        int i2 = this.index;
        int parseNumber = parseNumber();
        if (parseNumber < 2400) {
            int i3 = (parseNumber % 100) + ((parseNumber / 100) * 60);
            return z ? -i3 : i3;
        }
        throw new java.text.ParseException("Numeric TimeZone out of range", i2);
    }

    public int parseTimeZone() throws java.text.ParseException {
        int i = this.index;
        char[] cArr = this.orig;
        if (i < cArr.length) {
            char c = cArr[i];
            if (c != '+' && c != '-') {
                return parseAlphaTimeZone();
            }
            return parseNumericTimeZone();
        }
        throw new java.text.ParseException("No more characters", i);
    }

    public int peekChar() throws java.text.ParseException {
        int i = this.index;
        char[] cArr = this.orig;
        if (i < cArr.length) {
            return cArr[i];
        }
        throw new java.text.ParseException("No more characters", i);
    }

    public void skipChar(char c) throws java.text.ParseException {
        int i = this.index;
        char[] cArr = this.orig;
        if (i < cArr.length) {
            if (cArr[i] == c) {
                this.index = i + 1;
                return;
            }
            throw new java.text.ParseException("Wrong char", i);
        }
        throw new java.text.ParseException("No more characters", i);
    }

    public boolean skipIfChar(char c) throws java.text.ParseException {
        int i = this.index;
        char[] cArr = this.orig;
        if (i < cArr.length) {
            if (cArr[i] != c) {
                return false;
            }
            this.index = i + 1;
            return true;
        }
        throw new java.text.ParseException("No more characters", i);
    }

    public void skipUntilNumber() throws java.text.ParseException {
        while (true) {
            try {
                switch (this.orig[this.index]) {
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        return;
                    default:
                        this.index++;
                }
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new java.text.ParseException("No Number Found", this.index);
            }
        }
    }

    public void skipWhiteSpace() {
        int length = this.orig.length;
        while (true) {
            int i = this.index;
            if (i < length) {
                char c = this.orig[i];
                if (c != '\t' && c != '\n' && c != '\r' && c != ' ') {
                    return;
                }
                this.index++;
            } else {
                return;
            }
        }
    }
}
