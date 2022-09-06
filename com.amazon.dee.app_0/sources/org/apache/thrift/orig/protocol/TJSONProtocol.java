package org.apache.thrift.orig.protocol;

import com.amazon.alexa.accessory.internal.bluetooth.GenericAccessProfile;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Stack;
import org.apache.thrift.orig.TByteArrayOutputStream;
import org.apache.thrift.orig.TException;
import org.apache.thrift.orig.transport.TTransport;
/* loaded from: classes4.dex */
public class TJSONProtocol extends TProtocol {
    private static final int DEF_STRING_SIZE = 16;
    private static final String ESCAPE_CHARS = "\"\\bfnrt";
    private static final long VERSION = 1;
    private Stack<JSONBaseContext> contextStack_;
    private JSONBaseContext context_;
    private LookaheadReader reader_;
    private byte[] tmpbuf_;
    private static final byte[] COMMA = {44};
    private static final byte[] COLON = {58};
    private static final byte[] LBRACE = {123};
    private static final byte[] RBRACE = {125};
    private static final byte[] LBRACKET = {91};
    private static final byte[] RBRACKET = {93};
    private static final byte[] QUOTE = {GenericAccessProfile.LE_SECURE_CONNECTIONS_CONFIRMATION_VALUE};
    private static final byte[] BACKSLASH = {92};
    private static final byte[] ZERO = {48};
    private static final byte[] ESCSEQ = {92, 117, 48, 48};
    private static final byte[] JSON_CHAR_TABLE = {0, 0, 0, 0, 0, 0, 0, 0, 98, 116, 110, 0, 102, 114, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, GenericAccessProfile.LE_SECURE_CONNECTIONS_CONFIRMATION_VALUE, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    private static final byte[] ESCAPE_CHAR_VALS = {GenericAccessProfile.LE_SECURE_CONNECTIONS_CONFIRMATION_VALUE, 92, 8, 12, 10, 13, 9};
    private static final byte[] NAME_BOOL = {116, 102};
    private static final byte[] NAME_BYTE = {105, 56};
    private static final byte[] NAME_I16 = {105, 49, 54};
    private static final byte[] NAME_I32 = {105, 51, 50};
    private static final byte[] NAME_I64 = {105, 54, 52};
    private static final byte[] NAME_DOUBLE = {100, 98, 108};
    private static final byte[] NAME_STRUCT = {114, 101, 99};
    private static final byte[] NAME_STRING = {115, 116, 114};
    private static final byte[] NAME_MAP = {109, 97, 112};
    private static final byte[] NAME_LIST = {108, 115, 116};
    private static final byte[] NAME_SET = {115, 101, 116};
    private static final TStruct ANONYMOUS_STRUCT = new TStruct();

    /* loaded from: classes4.dex */
    public static class Factory implements TProtocolFactory {
        @Override // org.apache.thrift.orig.protocol.TProtocolFactory
        public TProtocol getProtocol(TTransport tTransport) {
            return new TJSONProtocol(tTransport);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public class JSONBaseContext {
        protected JSONBaseContext() {
        }

        protected boolean escapeNum() {
            return false;
        }

        protected void read() throws TException {
        }

        protected void write() throws TException {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public class JSONListContext extends JSONBaseContext {
        private boolean first_;

        protected JSONListContext() {
            super();
            this.first_ = true;
        }

        @Override // org.apache.thrift.orig.protocol.TJSONProtocol.JSONBaseContext
        protected void read() throws TException {
            if (!this.first_) {
                TJSONProtocol.this.readJSONSyntaxChar(TJSONProtocol.COMMA);
            } else {
                this.first_ = false;
            }
        }

        @Override // org.apache.thrift.orig.protocol.TJSONProtocol.JSONBaseContext
        protected void write() throws TException {
            if (!this.first_) {
                TJSONProtocol.this.trans_.write(TJSONProtocol.COMMA);
            } else {
                this.first_ = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public class JSONPairContext extends JSONBaseContext {
        private boolean colon_;
        private boolean first_;

        protected JSONPairContext() {
            super();
            this.first_ = true;
            this.colon_ = true;
        }

        @Override // org.apache.thrift.orig.protocol.TJSONProtocol.JSONBaseContext
        protected boolean escapeNum() {
            return this.colon_;
        }

        @Override // org.apache.thrift.orig.protocol.TJSONProtocol.JSONBaseContext
        protected void read() throws TException {
            if (!this.first_) {
                TJSONProtocol.this.readJSONSyntaxChar(this.colon_ ? TJSONProtocol.COLON : TJSONProtocol.COMMA);
                this.colon_ = !this.colon_;
                return;
            }
            this.first_ = false;
            this.colon_ = true;
        }

        @Override // org.apache.thrift.orig.protocol.TJSONProtocol.JSONBaseContext
        protected void write() throws TException {
            if (!this.first_) {
                TJSONProtocol.this.trans_.write(this.colon_ ? TJSONProtocol.COLON : TJSONProtocol.COMMA);
                this.colon_ = !this.colon_;
                return;
            }
            this.first_ = false;
            this.colon_ = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public class LookaheadReader {
        private byte[] data_ = new byte[1];
        private boolean hasData_;

        protected LookaheadReader() {
        }

        protected byte peek() throws TException {
            if (!this.hasData_) {
                TJSONProtocol.this.trans_.readAll(this.data_, 0, 1);
            }
            this.hasData_ = true;
            return this.data_[0];
        }

        protected byte read() throws TException {
            if (this.hasData_) {
                this.hasData_ = false;
            } else {
                TJSONProtocol.this.trans_.readAll(this.data_, 0, 1);
            }
            return this.data_[0];
        }
    }

    public TJSONProtocol(TTransport tTransport) {
        super(tTransport);
        this.contextStack_ = new Stack<>();
        this.context_ = new JSONBaseContext();
        this.reader_ = new LookaheadReader();
        this.tmpbuf_ = new byte[4];
    }

    private static final byte getTypeIDForTypeName(byte[] bArr) throws TException {
        byte b = 0;
        if (bArr.length > 1) {
            byte b2 = bArr[0];
            if (b2 == 100) {
                b = 4;
            } else if (b2 == 105) {
                byte b3 = bArr[1];
                if (b3 == 49) {
                    b = 6;
                } else if (b3 == 51) {
                    b = 8;
                } else if (b3 == 54) {
                    b = 10;
                } else if (b3 == 56) {
                    b = 3;
                }
            } else if (b2 == 108) {
                b = 15;
            } else if (b2 != 109) {
                switch (b2) {
                    case 114:
                        b = 12;
                        break;
                    case 115:
                        if (bArr[1] != 116) {
                            if (bArr[1] == 101) {
                                b = 14;
                                break;
                            }
                        } else {
                            b = 11;
                            break;
                        }
                        break;
                    case 116:
                        b = 2;
                        break;
                }
            } else {
                b = 13;
            }
        }
        if (b != 0) {
            return b;
        }
        throw new TProtocolException(5, "Unrecognized type");
    }

    private static final byte[] getTypeNameForTypeID(byte b) throws TException {
        switch (b) {
            case 2:
                return NAME_BOOL;
            case 3:
                return NAME_BYTE;
            case 4:
                return NAME_DOUBLE;
            case 5:
            case 7:
            case 9:
            default:
                throw new TProtocolException(5, "Unrecognized type");
            case 6:
                return NAME_I16;
            case 8:
                return NAME_I32;
            case 10:
                return NAME_I64;
            case 11:
                return NAME_STRING;
            case 12:
                return NAME_STRUCT;
            case 13:
                return NAME_MAP;
            case 14:
                return NAME_SET;
            case 15:
                return NAME_LIST;
        }
    }

    private static final byte hexChar(byte b) {
        byte b2 = (byte) (b & 15);
        return (byte) (b2 < 10 ? ((char) b2) + '0' : ((char) (b2 - 10)) + 'a');
    }

    private static final byte hexVal(byte b) throws TException {
        int i;
        if (b >= 48 && b <= 57) {
            i = ((char) b) - '0';
        } else if (b < 97 || b > 102) {
            throw new TProtocolException(1, "Expected hex character");
        } else {
            i = (((char) b) - 'a') + 10;
        }
        return (byte) i;
    }

    private boolean isJSONNumeric(byte b) {
        if (b == 43 || b == 69 || b == 101 || b == 45 || b == 46) {
            return true;
        }
        switch (b) {
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
                return true;
            default:
                return false;
        }
    }

    private void popContext() {
        this.context_ = this.contextStack_.pop();
    }

    private void pushContext(JSONBaseContext jSONBaseContext) {
        this.contextStack_.push(this.context_);
        this.context_ = jSONBaseContext;
    }

    private void readJSONArrayEnd() throws TException {
        readJSONSyntaxChar(RBRACKET);
        popContext();
    }

    private void readJSONArrayStart() throws TException {
        this.context_.read();
        readJSONSyntaxChar(LBRACKET);
        pushContext(new JSONListContext());
    }

    private byte[] readJSONBase64() throws TException {
        TByteArrayOutputStream readJSONString = readJSONString(false);
        byte[] bArr = readJSONString.get();
        int len = readJSONString.len();
        int i = 0;
        int i2 = 0;
        while (len >= 4) {
            TBase64Utils.decode(bArr, i, 4, bArr, i2);
            i += 4;
            len -= 4;
            i2 += 3;
        }
        if (len > 1) {
            TBase64Utils.decode(bArr, i, len, bArr, i2);
            i2 += len - 1;
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        return bArr2;
    }

    private double readJSONDouble() throws TException {
        this.context_.read();
        if (this.reader_.peek() == QUOTE[0]) {
            try {
                double doubleValue = Double.valueOf(readJSONString(true).toString("UTF-8")).doubleValue();
                if (!this.context_.escapeNum() && !Double.isNaN(doubleValue) && !Double.isInfinite(doubleValue)) {
                    throw new TProtocolException(1, "Numeric data unexpectedly quoted");
                }
                return doubleValue;
            } catch (UnsupportedEncodingException unused) {
                throw new TException("JVM DOES NOT SUPPORT UTF-8");
            }
        }
        if (this.context_.escapeNum()) {
            readJSONSyntaxChar(QUOTE);
        }
        try {
            return Double.valueOf(readJSONNumericChars()).doubleValue();
        } catch (NumberFormatException unused2) {
            throw new TProtocolException(1, "Bad data encounted in numeric data");
        }
    }

    private long readJSONInteger() throws TException {
        this.context_.read();
        if (this.context_.escapeNum()) {
            readJSONSyntaxChar(QUOTE);
        }
        String readJSONNumericChars = readJSONNumericChars();
        if (this.context_.escapeNum()) {
            readJSONSyntaxChar(QUOTE);
        }
        try {
            return Long.valueOf(readJSONNumericChars).longValue();
        } catch (NumberFormatException unused) {
            throw new TProtocolException(1, "Bad data encounted in numeric data");
        }
    }

    private String readJSONNumericChars() throws TException {
        StringBuilder sb = new StringBuilder();
        while (isJSONNumeric(this.reader_.peek())) {
            sb.append((char) this.reader_.read());
        }
        return sb.toString();
    }

    private void readJSONObjectEnd() throws TException {
        readJSONSyntaxChar(RBRACE);
        popContext();
    }

    private void readJSONObjectStart() throws TException {
        this.context_.read();
        readJSONSyntaxChar(LBRACE);
        pushContext(new JSONPairContext());
    }

    private TByteArrayOutputStream readJSONString(boolean z) throws TException {
        TByteArrayOutputStream tByteArrayOutputStream = new TByteArrayOutputStream(16);
        if (!z) {
            this.context_.read();
        }
        readJSONSyntaxChar(QUOTE);
        while (true) {
            byte read = this.reader_.read();
            if (read == QUOTE[0]) {
                return tByteArrayOutputStream;
            }
            if (read == ESCSEQ[0]) {
                byte read2 = this.reader_.read();
                if (read2 == ESCSEQ[1]) {
                    readJSONSyntaxChar(ZERO);
                    readJSONSyntaxChar(ZERO);
                    this.trans_.readAll(this.tmpbuf_, 0, 2);
                    read = (byte) ((hexVal(this.tmpbuf_[0]) << 4) + hexVal(this.tmpbuf_[1]));
                } else {
                    int indexOf = ESCAPE_CHARS.indexOf(read2);
                    if (indexOf != -1) {
                        read = ESCAPE_CHAR_VALS[indexOf];
                    } else {
                        throw new TProtocolException(1, "Expected control char");
                    }
                }
            }
            tByteArrayOutputStream.write(read);
        }
    }

    private void writeJSONArrayEnd() throws TException {
        popContext();
        this.trans_.write(RBRACKET);
    }

    private void writeJSONArrayStart() throws TException {
        this.context_.write();
        this.trans_.write(LBRACKET);
        pushContext(new JSONListContext());
    }

    private void writeJSONBase64(byte[] bArr, int i, int i2) throws TException {
        this.context_.write();
        this.trans_.write(QUOTE);
        while (i2 >= 3) {
            TBase64Utils.encode(bArr, i, 3, this.tmpbuf_, 0);
            this.trans_.write(this.tmpbuf_, 0, 4);
            i += 3;
            i2 -= 3;
        }
        if (i2 > 0) {
            TBase64Utils.encode(bArr, i, i2, this.tmpbuf_, 0);
            this.trans_.write(this.tmpbuf_, 0, i2 + 1);
        }
        this.trans_.write(QUOTE);
    }

    private void writeJSONDouble(double d) throws TException {
        this.context_.write();
        String d2 = Double.toString(d);
        char charAt = d2.charAt(0);
        boolean z = true;
        if (!(charAt == '-' ? d2.charAt(1) == 'I' : charAt == 'I' || charAt == 'N') && !this.context_.escapeNum()) {
            z = false;
        }
        if (z) {
            this.trans_.write(QUOTE);
        }
        try {
            byte[] bytes = d2.getBytes("UTF-8");
            this.trans_.write(bytes, 0, bytes.length);
            if (!z) {
                return;
            }
            this.trans_.write(QUOTE);
        } catch (UnsupportedEncodingException unused) {
            throw new TException("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    private void writeJSONInteger(long j) throws TException {
        this.context_.write();
        String l = Long.toString(j);
        boolean escapeNum = this.context_.escapeNum();
        if (escapeNum) {
            this.trans_.write(QUOTE);
        }
        try {
            this.trans_.write(l.getBytes("UTF-8"));
            if (!escapeNum) {
                return;
            }
            this.trans_.write(QUOTE);
        } catch (UnsupportedEncodingException unused) {
            throw new TException("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    private void writeJSONObjectEnd() throws TException {
        popContext();
        this.trans_.write(RBRACE);
    }

    private void writeJSONObjectStart() throws TException {
        this.context_.write();
        this.trans_.write(LBRACE);
        pushContext(new JSONPairContext());
    }

    private void writeJSONString(byte[] bArr) throws TException {
        this.context_.write();
        this.trans_.write(QUOTE);
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            if ((bArr[i] & 255) >= 48) {
                byte b = bArr[i];
                byte[] bArr2 = BACKSLASH;
                if (b == bArr2[0]) {
                    this.trans_.write(bArr2);
                    this.trans_.write(BACKSLASH);
                } else {
                    this.trans_.write(bArr, i, 1);
                }
            } else {
                byte[] bArr3 = this.tmpbuf_;
                bArr3[0] = JSON_CHAR_TABLE[bArr[i]];
                if (bArr3[0] == 1) {
                    this.trans_.write(bArr, i, 1);
                } else if (bArr3[0] > 1) {
                    this.trans_.write(BACKSLASH);
                    this.trans_.write(this.tmpbuf_, 0, 1);
                } else {
                    this.trans_.write(ESCSEQ);
                    this.tmpbuf_[0] = hexChar((byte) (bArr[i] >> 4));
                    this.tmpbuf_[1] = hexChar(bArr[i]);
                    this.trans_.write(this.tmpbuf_, 0, 2);
                }
            }
        }
        this.trans_.write(QUOTE);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public ByteBuffer readBinary() throws TException {
        return ByteBuffer.wrap(readJSONBase64());
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public boolean readBool() throws TException {
        return readJSONInteger() != 0;
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public byte readByte() throws TException {
        return (byte) readJSONInteger();
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public double readDouble() throws TException {
        return readJSONDouble();
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public TField readFieldBegin() throws TException {
        short readJSONInteger;
        byte b = 0;
        if (this.reader_.peek() == RBRACE[0]) {
            readJSONInteger = 0;
        } else {
            readJSONInteger = (short) readJSONInteger();
            readJSONObjectStart();
            b = getTypeIDForTypeName(readJSONString(false).get());
        }
        return new TField("", b, readJSONInteger);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void readFieldEnd() throws TException {
        readJSONObjectEnd();
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public short readI16() throws TException {
        return (short) readJSONInteger();
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public int readI32() throws TException {
        return (int) readJSONInteger();
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public long readI64() throws TException {
        return readJSONInteger();
    }

    protected void readJSONSyntaxChar(byte[] bArr) throws TException {
        byte read = this.reader_.read();
        if (read == bArr[0]) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected character:");
        outline107.append((char) read);
        throw new TProtocolException(1, outline107.toString());
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public TList readListBegin() throws TException {
        readJSONArrayStart();
        return new TList(getTypeIDForTypeName(readJSONString(false).get()), (int) readJSONInteger());
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void readListEnd() throws TException {
        readJSONArrayEnd();
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public TMap readMapBegin() throws TException {
        readJSONArrayStart();
        readJSONObjectStart();
        return new TMap(getTypeIDForTypeName(readJSONString(false).get()), getTypeIDForTypeName(readJSONString(false).get()), (int) readJSONInteger());
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void readMapEnd() throws TException {
        readJSONObjectEnd();
        readJSONArrayEnd();
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public TMessage readMessageBegin() throws TException {
        readJSONArrayStart();
        if (readJSONInteger() == 1) {
            try {
                return new TMessage(readJSONString(false).toString("UTF-8"), (byte) readJSONInteger(), (int) readJSONInteger());
            } catch (UnsupportedEncodingException unused) {
                throw new TException("JVM DOES NOT SUPPORT UTF-8");
            }
        }
        throw new TProtocolException(4, "Message contained bad version.");
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void readMessageEnd() throws TException {
        readJSONArrayEnd();
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public TSet readSetBegin() throws TException {
        readJSONArrayStart();
        return new TSet(getTypeIDForTypeName(readJSONString(false).get()), (int) readJSONInteger());
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void readSetEnd() throws TException {
        readJSONArrayEnd();
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public String readString() throws TException {
        try {
            return readJSONString(false).toString("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            throw new TException("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public TStruct readStructBegin() throws TException {
        readJSONObjectStart();
        return ANONYMOUS_STRUCT;
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void readStructEnd() throws TException {
        readJSONObjectEnd();
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void reset() {
        this.contextStack_.clear();
        this.context_ = new JSONBaseContext();
        this.reader_ = new LookaheadReader();
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeBinary(ByteBuffer byteBuffer) throws TException {
        writeJSONBase64(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), (byteBuffer.limit() - byteBuffer.position()) - byteBuffer.arrayOffset());
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeBool(boolean z) throws TException {
        writeJSONInteger(z ? 1L : 0L);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeByte(byte b) throws TException {
        writeJSONInteger(b);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeDouble(double d) throws TException {
        writeJSONDouble(d);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeFieldBegin(TField tField) throws TException {
        writeJSONInteger(tField.id);
        writeJSONObjectStart();
        writeJSONString(getTypeNameForTypeID(tField.type));
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeFieldEnd() throws TException {
        writeJSONObjectEnd();
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeFieldStop() {
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeI16(short s) throws TException {
        writeJSONInteger(s);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeI32(int i) throws TException {
        writeJSONInteger(i);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeI64(long j) throws TException {
        writeJSONInteger(j);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeListBegin(TList tList) throws TException {
        writeJSONArrayStart();
        writeJSONString(getTypeNameForTypeID(tList.elemType));
        writeJSONInteger(tList.size);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeListEnd() throws TException {
        writeJSONArrayEnd();
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeMapBegin(TMap tMap) throws TException {
        writeJSONArrayStart();
        writeJSONString(getTypeNameForTypeID(tMap.keyType));
        writeJSONString(getTypeNameForTypeID(tMap.valueType));
        writeJSONInteger(tMap.size);
        writeJSONObjectStart();
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeMapEnd() throws TException {
        writeJSONObjectEnd();
        writeJSONArrayEnd();
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeMessageBegin(TMessage tMessage) throws TException {
        writeJSONArrayStart();
        writeJSONInteger(1L);
        try {
            writeJSONString(tMessage.name.getBytes("UTF-8"));
            writeJSONInteger(tMessage.type);
            writeJSONInteger(tMessage.seqid);
        } catch (UnsupportedEncodingException unused) {
            throw new TException("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeMessageEnd() throws TException {
        writeJSONArrayEnd();
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeSetBegin(TSet tSet) throws TException {
        writeJSONArrayStart();
        writeJSONString(getTypeNameForTypeID(tSet.elemType));
        writeJSONInteger(tSet.size);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeSetEnd() throws TException {
        writeJSONArrayEnd();
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeString(String str) throws TException {
        try {
            writeJSONString(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException unused) {
            throw new TException("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeStructBegin(TStruct tStruct) throws TException {
        writeJSONObjectStart();
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeStructEnd() throws TException {
        writeJSONObjectEnd();
    }
}
