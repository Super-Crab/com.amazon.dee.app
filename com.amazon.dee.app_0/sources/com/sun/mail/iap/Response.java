package com.sun.mail.iap;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.sun.mail.util.ASCIIUtility;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Vector;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes3.dex */
public class Response {
    private static String ASTRING_CHAR_DELIM = " (){%*\"\\";
    private static String ATOM_CHAR_DELIM = " (){%*\"\\]";
    public static final int BAD = 12;
    public static final int BYE = 16;
    public static final int CONTINUATION = 1;
    public static final int NO = 8;
    public static final int OK = 4;
    public static final int SYNTHETIC = 32;
    public static final int TAGGED = 2;
    public static final int TAG_MASK = 3;
    public static final int TYPE_MASK = 28;
    public static final int UNTAGGED = 3;
    private static final int increment = 100;
    protected byte[] buffer;
    protected Exception ex;
    protected int index;
    protected int pindex;
    protected int size;
    protected String tag;
    protected int type;

    public Response(String str) {
        this.buffer = null;
        this.type = 0;
        this.tag = null;
        this.buffer = ASCIIUtility.getBytes(str);
        this.size = this.buffer.length;
        parse();
    }

    public static Response byeResponse(Exception exc) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("* BYE JavaMail Exception: ");
        outline107.append(exc.toString());
        Response response = new Response(outline107.toString().replace('\r', Chars.SPACE).replace('\n', Chars.SPACE));
        response.type |= 32;
        response.ex = exc;
        return response;
    }

    private void parse() {
        this.index = 0;
        if (this.size == 0) {
            return;
        }
        byte[] bArr = this.buffer;
        int i = this.index;
        if (bArr[i] == 43) {
            this.type |= 1;
            this.index = i + 1;
            return;
        }
        if (bArr[i] == 42) {
            this.type |= 3;
            this.index = i + 1;
        } else {
            this.type |= 2;
            this.tag = readAtom();
            if (this.tag == null) {
                this.tag = "";
            }
        }
        int i2 = this.index;
        String readAtom = readAtom();
        if (readAtom == null) {
            readAtom = "";
        }
        if (readAtom.equalsIgnoreCase("OK")) {
            this.type |= 4;
        } else if (readAtom.equalsIgnoreCase("NO")) {
            this.type |= 8;
        } else if (readAtom.equalsIgnoreCase("BAD")) {
            this.type |= 12;
        } else if (readAtom.equalsIgnoreCase("BYE")) {
            this.type |= 16;
        } else {
            this.index = i2;
        }
        this.pindex = this.index;
    }

    private Object parseString(boolean z, boolean z2) {
        byte[] bArr;
        int i;
        byte b;
        skipSpaces();
        byte[] bArr2 = this.buffer;
        int i2 = this.index;
        byte b2 = bArr2[i2];
        if (b2 == 34) {
            this.index = i2 + 1;
            int i3 = this.index;
            int i4 = i3;
            while (true) {
                int i5 = this.index;
                if (i5 >= this.size || (b = this.buffer[i5]) == 34) {
                    break;
                }
                if (b == 92) {
                    this.index = i5 + 1;
                }
                int i6 = this.index;
                if (i6 != i4) {
                    byte[] bArr3 = this.buffer;
                    bArr3[i4] = bArr3[i6];
                }
                i4++;
                this.index++;
            }
            int i7 = this.index;
            if (i7 >= this.size) {
                return null;
            }
            this.index = i7 + 1;
            if (z2) {
                return ASCIIUtility.toString(this.buffer, i3, i4);
            }
            return new ByteArray(this.buffer, i3, i4 - i3);
        } else if (b2 != 123) {
            if (z) {
                return z2 ? readDelimString(ASTRING_CHAR_DELIM) : new ByteArray(this.buffer, i2, this.index);
            } else if (b2 != 78 && b2 != 110) {
                return null;
            } else {
                this.index += 3;
                return null;
            }
        } else {
            int i8 = i2 + 1;
            this.index = i8;
            while (true) {
                bArr = this.buffer;
                i = this.index;
                if (bArr[i] == 125) {
                    try {
                        break;
                    } catch (NumberFormatException unused) {
                        return null;
                    }
                }
                this.index = i + 1;
            }
            int parseInt = ASCIIUtility.parseInt(bArr, i8, i);
            int i9 = this.index + 3;
            int i10 = i9 + parseInt;
            this.index = i10;
            if (z2) {
                return ASCIIUtility.toString(this.buffer, i9, i10);
            }
            return new ByteArray(this.buffer, i9, parseInt);
        }
    }

    private String readDelimString(String str) {
        byte b;
        skipSpaces();
        int i = this.index;
        if (i >= this.size) {
            return null;
        }
        while (true) {
            int i2 = this.index;
            if (i2 >= this.size || (b = this.buffer[i2]) <= 32 || str.indexOf((char) b) >= 0 || b < 32 || b == Byte.MAX_VALUE) {
                break;
            }
            this.index++;
        }
        return ASCIIUtility.toString(this.buffer, i, this.index);
    }

    public Exception getException() {
        return this.ex;
    }

    public String getRest() {
        skipSpaces();
        return ASCIIUtility.toString(this.buffer, this.index, this.size);
    }

    public String getTag() {
        return this.tag;
    }

    public int getType() {
        return this.type;
    }

    public boolean isBAD() {
        return (this.type & 28) == 12;
    }

    public boolean isBYE() {
        return (this.type & 28) == 16;
    }

    public boolean isContinuation() {
        return (this.type & 3) == 1;
    }

    public boolean isNO() {
        return (this.type & 28) == 8;
    }

    public boolean isOK() {
        return (this.type & 28) == 4;
    }

    public boolean isSynthetic() {
        return (this.type & 32) == 32;
    }

    public boolean isTagged() {
        return (this.type & 3) == 2;
    }

    public boolean isUnTagged() {
        return (this.type & 3) == 3;
    }

    public byte peekByte() {
        int i = this.index;
        if (i < this.size) {
            return this.buffer[i];
        }
        return (byte) 0;
    }

    public String readAtom() {
        return readDelimString(ATOM_CHAR_DELIM);
    }

    public String readAtomString() {
        return (String) parseString(true, true);
    }

    public String[] readAtomStringList() {
        return readStringList(true);
    }

    public byte readByte() {
        int i = this.index;
        if (i < this.size) {
            byte[] bArr = this.buffer;
            this.index = i + 1;
            return bArr[i];
        }
        return (byte) 0;
    }

    public ByteArray readByteArray() {
        if (isContinuation()) {
            skipSpaces();
            byte[] bArr = this.buffer;
            int i = this.index;
            return new ByteArray(bArr, i, this.size - i);
        }
        return (ByteArray) parseString(false, false);
    }

    public ByteArrayInputStream readBytes() {
        ByteArray readByteArray = readByteArray();
        if (readByteArray != null) {
            return readByteArray.toByteArrayInputStream();
        }
        return null;
    }

    public long readLong() {
        skipSpaces();
        int i = this.index;
        while (true) {
            int i2 = this.index;
            if (i2 >= this.size || !Character.isDigit((char) this.buffer[i2])) {
                break;
            }
            this.index++;
        }
        int i3 = this.index;
        if (i3 > i) {
            try {
                return ASCIIUtility.parseLong(this.buffer, i, i3);
            } catch (NumberFormatException unused) {
                return -1L;
            }
        }
        return -1L;
    }

    public int readNumber() {
        skipSpaces();
        int i = this.index;
        while (true) {
            int i2 = this.index;
            if (i2 >= this.size || !Character.isDigit((char) this.buffer[i2])) {
                break;
            }
            this.index++;
        }
        int i3 = this.index;
        if (i3 > i) {
            try {
                return ASCIIUtility.parseInt(this.buffer, i, i3);
            } catch (NumberFormatException unused) {
                return -1;
            }
        }
        return -1;
    }

    public String readString(char c) {
        skipSpaces();
        int i = this.index;
        if (i >= this.size) {
            return null;
        }
        while (true) {
            int i2 = this.index;
            if (i2 >= this.size || this.buffer[i2] == c) {
                break;
            }
            this.index = i2 + 1;
        }
        return ASCIIUtility.toString(this.buffer, i, this.index);
    }

    public String[] readStringList() {
        return readStringList(false);
    }

    public void reset() {
        this.index = this.pindex;
    }

    public void skip(int i) {
        this.index += i;
    }

    public void skipSpaces() {
        while (true) {
            int i = this.index;
            if (i >= this.size || this.buffer[i] != 32) {
                return;
            }
            this.index = i + 1;
        }
    }

    public void skipToken() {
        while (true) {
            int i = this.index;
            if (i >= this.size || this.buffer[i] == 32) {
                return;
            }
            this.index = i + 1;
        }
    }

    public String toString() {
        return ASCIIUtility.toString(this.buffer, 0, this.size);
    }

    private String[] readStringList(boolean z) {
        byte[] bArr;
        int i;
        skipSpaces();
        byte[] bArr2 = this.buffer;
        int i2 = this.index;
        if (bArr2[i2] != 40) {
            return null;
        }
        this.index = i2 + 1;
        Vector vector = new Vector();
        do {
            vector.addElement(z ? readAtomString() : readString());
            bArr = this.buffer;
            i = this.index;
            this.index = i + 1;
        } while (bArr[i] != 41);
        int size = vector.size();
        if (size <= 0) {
            return null;
        }
        String[] strArr = new String[size];
        vector.copyInto(strArr);
        return strArr;
    }

    public String readString() {
        return (String) parseString(false, true);
    }

    public Response(Protocol protocol) throws IOException, ProtocolException {
        this.buffer = null;
        this.type = 0;
        this.tag = null;
        ByteArray readResponse = protocol.getInputStream().readResponse(protocol.getResponseBuffer());
        this.buffer = readResponse.getBytes();
        this.size = readResponse.getCount() - 2;
        parse();
    }

    public Response(Response response) {
        this.buffer = null;
        this.type = 0;
        this.tag = null;
        this.index = response.index;
        this.size = response.size;
        this.buffer = response.buffer;
        this.type = response.type;
        this.tag = response.tag;
    }
}
