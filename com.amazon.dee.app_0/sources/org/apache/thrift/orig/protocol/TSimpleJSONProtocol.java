package org.apache.thrift.orig.protocol;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Stack;
import org.apache.thrift.orig.TException;
import org.apache.thrift.orig.transport.TTransport;
/* loaded from: classes4.dex */
public class TSimpleJSONProtocol extends TProtocol {
    public static final char QUOTE = '\"';
    protected final Context BASE_CONTEXT;
    protected Stack<Context> writeContextStack_;
    protected Context writeContext_;
    public static final byte[] COMMA = {44};
    public static final byte[] COLON = {58};
    public static final byte[] LBRACE = {123};
    public static final byte[] RBRACE = {125};
    public static final byte[] LBRACKET = {91};
    public static final byte[] RBRACKET = {93};
    private static final TStruct ANONYMOUS_STRUCT = new TStruct();
    private static final TField ANONYMOUS_FIELD = new TField();
    private static final TMessage EMPTY_MESSAGE = new TMessage();
    private static final TSet EMPTY_SET = new TSet();
    private static final TList EMPTY_LIST = new TList();
    private static final TMap EMPTY_MAP = new TMap();

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public class Context {
        protected Context() {
        }

        protected void write() throws TException {
        }
    }

    /* loaded from: classes4.dex */
    public static class Factory implements TProtocolFactory {
        @Override // org.apache.thrift.orig.protocol.TProtocolFactory
        public TProtocol getProtocol(TTransport tTransport) {
            return new TSimpleJSONProtocol(tTransport);
        }
    }

    /* loaded from: classes4.dex */
    protected class ListContext extends Context {
        protected boolean first_;

        protected ListContext() {
            super();
            this.first_ = true;
        }

        @Override // org.apache.thrift.orig.protocol.TSimpleJSONProtocol.Context
        protected void write() throws TException {
            if (this.first_) {
                this.first_ = false;
            } else {
                TSimpleJSONProtocol.this.trans_.write(TSimpleJSONProtocol.COMMA);
            }
        }
    }

    /* loaded from: classes4.dex */
    protected class StructContext extends Context {
        protected boolean colon_;
        protected boolean first_;

        protected StructContext() {
            super();
            this.first_ = true;
            this.colon_ = true;
        }

        @Override // org.apache.thrift.orig.protocol.TSimpleJSONProtocol.Context
        protected void write() throws TException {
            if (this.first_) {
                this.first_ = false;
                this.colon_ = true;
                return;
            }
            TSimpleJSONProtocol.this.trans_.write(this.colon_ ? TSimpleJSONProtocol.COLON : TSimpleJSONProtocol.COMMA);
            this.colon_ = !this.colon_;
        }
    }

    public TSimpleJSONProtocol(TTransport tTransport) {
        super(tTransport);
        this.BASE_CONTEXT = new Context();
        this.writeContextStack_ = new Stack<>();
        this.writeContext_ = this.BASE_CONTEXT;
    }

    public void _writeStringData(String str) throws TException {
        try {
            this.trans_.write(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException unused) {
            throw new TException("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    protected void popWriteContext() {
        this.writeContext_ = this.writeContextStack_.pop();
    }

    protected void pushWriteContext(Context context) {
        this.writeContextStack_.push(this.writeContext_);
        this.writeContext_ = context;
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public ByteBuffer readBinary() throws TException {
        return ByteBuffer.wrap(new byte[0]);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public boolean readBool() throws TException {
        return readByte() == 1;
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public byte readByte() throws TException {
        return (byte) 0;
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public double readDouble() throws TException {
        return FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public TField readFieldBegin() throws TException {
        return ANONYMOUS_FIELD;
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void readFieldEnd() {
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public short readI16() throws TException {
        return (short) 0;
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public int readI32() throws TException {
        return 0;
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public long readI64() throws TException {
        return 0L;
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public TList readListBegin() throws TException {
        return EMPTY_LIST;
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void readListEnd() {
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public TMap readMapBegin() throws TException {
        return EMPTY_MAP;
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void readMapEnd() {
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public TMessage readMessageBegin() throws TException {
        return EMPTY_MESSAGE;
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void readMessageEnd() {
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public TSet readSetBegin() throws TException {
        return EMPTY_SET;
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void readSetEnd() {
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public String readString() throws TException {
        return "";
    }

    public String readStringBody(int i) throws TException {
        return "";
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public TStruct readStructBegin() {
        return ANONYMOUS_STRUCT;
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void readStructEnd() {
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeBinary(ByteBuffer byteBuffer) throws TException {
        try {
            writeString(new String(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), (byteBuffer.limit() - byteBuffer.position()) - byteBuffer.arrayOffset(), "UTF-8"));
        } catch (UnsupportedEncodingException unused) {
            throw new TException("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeBool(boolean z) throws TException {
        writeByte(z ? (byte) 1 : (byte) 0);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeByte(byte b) throws TException {
        writeI32(b);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeDouble(double d) throws TException {
        this.writeContext_.write();
        _writeStringData(Double.toString(d));
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeFieldBegin(TField tField) throws TException {
        writeString(tField.name);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeFieldEnd() {
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeFieldStop() {
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeI16(short s) throws TException {
        writeI32(s);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeI32(int i) throws TException {
        this.writeContext_.write();
        _writeStringData(Integer.toString(i));
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeI64(long j) throws TException {
        this.writeContext_.write();
        _writeStringData(Long.toString(j));
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeListBegin(TList tList) throws TException {
        this.writeContext_.write();
        this.trans_.write(LBRACKET);
        pushWriteContext(new ListContext());
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeListEnd() throws TException {
        popWriteContext();
        this.trans_.write(RBRACKET);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeMapBegin(TMap tMap) throws TException {
        this.writeContext_.write();
        this.trans_.write(LBRACE);
        pushWriteContext(new StructContext());
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeMapEnd() throws TException {
        popWriteContext();
        this.trans_.write(RBRACE);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeMessageBegin(TMessage tMessage) throws TException {
        this.trans_.write(LBRACKET);
        pushWriteContext(new ListContext());
        writeString(tMessage.name);
        writeByte(tMessage.type);
        writeI32(tMessage.seqid);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeMessageEnd() throws TException {
        popWriteContext();
        this.trans_.write(RBRACKET);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeSetBegin(TSet tSet) throws TException {
        this.writeContext_.write();
        this.trans_.write(LBRACKET);
        pushWriteContext(new ListContext());
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeSetEnd() throws TException {
        popWriteContext();
        this.trans_.write(RBRACKET);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeString(String str) throws TException {
        this.writeContext_.write();
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length + 16);
        stringBuffer.append('\"');
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '\f') {
                stringBuffer.append('\\');
                stringBuffer.append('f');
            } else if (charAt == '\r') {
                stringBuffer.append('\\');
                stringBuffer.append('r');
            } else if (charAt != '\"' && charAt != '\\') {
                switch (charAt) {
                    case '\b':
                        stringBuffer.append('\\');
                        stringBuffer.append('b');
                        continue;
                    case '\t':
                        stringBuffer.append('\\');
                        stringBuffer.append('t');
                        continue;
                    case '\n':
                        stringBuffer.append('\\');
                        stringBuffer.append('n');
                        continue;
                    default:
                        if (charAt < ' ') {
                            String hexString = Integer.toHexString(charAt);
                            stringBuffer.append('\\');
                            stringBuffer.append('u');
                            for (int i2 = 4; i2 > hexString.length(); i2--) {
                                stringBuffer.append('0');
                            }
                            stringBuffer.append(hexString);
                            continue;
                        } else {
                            stringBuffer.append(charAt);
                            break;
                        }
                }
            } else {
                stringBuffer.append('\\');
                stringBuffer.append(charAt);
            }
        }
        stringBuffer.append('\"');
        _writeStringData(stringBuffer.toString());
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeStructBegin(TStruct tStruct) throws TException {
        this.writeContext_.write();
        this.trans_.write(LBRACE);
        pushWriteContext(new StructContext());
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeStructEnd() throws TException {
        popWriteContext();
        this.trans_.write(RBRACE);
    }
}
