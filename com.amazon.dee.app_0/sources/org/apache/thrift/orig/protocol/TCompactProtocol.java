package org.apache.thrift.orig.protocol;

import com.amazon.alexa.presence.bleconn.service.protocols.relationships.RequesterRelationshipsPacketV1;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.apache.thrift.orig.ShortStack;
import org.apache.thrift.orig.TException;
import org.apache.thrift.orig.transport.TTransport;
/* loaded from: classes4.dex */
public class TCompactProtocol extends TProtocol {
    private static final byte PROTOCOL_ID = -126;
    private static final byte TYPE_MASK = -32;
    private static final int TYPE_SHIFT_AMOUNT = 5;
    private static final byte VERSION = 1;
    private static final byte VERSION_MASK = 31;
    private Boolean boolValue_;
    private TField booleanField_;
    private byte[] byteDirectBuffer;
    byte[] byteRawBuf;
    byte[] i32buf;
    private short lastFieldId_;
    private ShortStack lastField_;
    private final long maxNetworkBytes_;
    byte[] varint64out;
    private static final TStruct ANONYMOUS_STRUCT = new TStruct("");
    private static final TField TSTOP = new TField("", (byte) 0, 0);
    private static final byte[] ttypeToCompactType = new byte[16];

    /* loaded from: classes4.dex */
    private static class Types {
        public static final byte BINARY = 8;
        public static final byte BOOLEAN_FALSE = 2;
        public static final byte BOOLEAN_TRUE = 1;
        public static final byte BYTE = 3;
        public static final byte DOUBLE = 7;
        public static final byte I16 = 4;
        public static final byte I32 = 5;
        public static final byte I64 = 6;
        public static final byte LIST = 9;
        public static final byte MAP = 11;
        public static final byte SET = 10;
        public static final byte STRUCT = 12;

        private Types() {
        }
    }

    static {
        byte[] bArr = ttypeToCompactType;
        bArr[0] = 0;
        bArr[2] = 1;
        bArr[3] = 3;
        bArr[6] = 4;
        bArr[8] = 5;
        bArr[10] = 6;
        bArr[4] = 7;
        bArr[11] = 8;
        bArr[15] = 9;
        bArr[14] = 10;
        bArr[13] = 11;
        bArr[12] = 12;
    }

    public TCompactProtocol(TTransport tTransport, long j) {
        super(tTransport);
        this.lastField_ = new ShortStack(15);
        this.lastFieldId_ = (short) 0;
        this.booleanField_ = null;
        this.boolValue_ = null;
        this.i32buf = new byte[5];
        this.varint64out = new byte[10];
        this.byteDirectBuffer = new byte[1];
        this.byteRawBuf = new byte[1];
        this.maxNetworkBytes_ = j;
    }

    private long bytesToLong(byte[] bArr) {
        return ((bArr[7] & 255) << 56) | ((bArr[6] & 255) << 48) | ((bArr[5] & 255) << 40) | ((bArr[4] & 255) << 32) | ((bArr[3] & 255) << 24) | ((bArr[2] & 255) << 16) | ((bArr[1] & 255) << 8) | (255 & bArr[0]);
    }

    private void checkReadLength(int i) throws TProtocolException {
        if (i >= 0) {
            long j = this.maxNetworkBytes_;
            if (j != -1 && i > j) {
                throw new TProtocolException(GeneratedOutlineSupport1.outline49("Length exceeded max allowed: ", i));
            }
            return;
        }
        throw new TProtocolException(GeneratedOutlineSupport1.outline49("Negative length: ", i));
    }

    private void fixedLongToBytes(long j, byte[] bArr, int i) {
        bArr[i + 0] = (byte) (j & 255);
        bArr[i + 1] = (byte) ((j >> 8) & 255);
        bArr[i + 2] = (byte) ((j >> 16) & 255);
        bArr[i + 3] = (byte) ((j >> 24) & 255);
        bArr[i + 4] = (byte) ((j >> 32) & 255);
        bArr[i + 5] = (byte) ((j >> 40) & 255);
        bArr[i + 6] = (byte) ((j >> 48) & 255);
        bArr[i + 7] = (byte) ((j >> 56) & 255);
    }

    private byte getCompactType(byte b) {
        return ttypeToCompactType[b];
    }

    private byte getTType(byte b) throws TProtocolException {
        byte b2 = (byte) (b & 15);
        switch (b2) {
            case 0:
                return (byte) 0;
            case 1:
            case 2:
                return (byte) 2;
            case 3:
                return (byte) 3;
            case 4:
                return (byte) 6;
            case 5:
                return (byte) 8;
            case 6:
                return (byte) 10;
            case 7:
                return (byte) 4;
            case 8:
                return (byte) 11;
            case 9:
                return (byte) 15;
            case 10:
                return (byte) 14;
            case 11:
                return (byte) 13;
            case 12:
                return (byte) 12;
            default:
                throw new TProtocolException(GeneratedOutlineSupport1.outline49("don't know what type: ", b2));
        }
    }

    private int intToZigZag(int i) {
        return (i >> 31) ^ (i << 1);
    }

    private boolean isBoolType(byte b) {
        int i = b & 15;
        return i == 1 || i == 2;
    }

    private long longToZigzag(long j) {
        return (j >> 63) ^ (j << 1);
    }

    private int readVarint32() throws TException {
        int i = 0;
        if (this.trans_.getBytesRemainingInBuffer() >= 5) {
            byte[] buffer = this.trans_.getBuffer();
            int bufferPosition = this.trans_.getBufferPosition();
            int i2 = 0;
            int i3 = 0;
            while (true) {
                byte b = buffer[bufferPosition + i];
                i2 |= (b & Byte.MAX_VALUE) << i3;
                if ((b & 128) != 128) {
                    this.trans_.consumeBuffer(i + 1);
                    return i2;
                }
                i3 += 7;
                i++;
            }
        } else {
            int i4 = 0;
            while (true) {
                byte readByte = readByte();
                i |= (readByte & Byte.MAX_VALUE) << i4;
                if ((readByte & 128) != 128) {
                    return i;
                }
                i4 += 7;
            }
        }
    }

    private long readVarint64() throws TException {
        byte readByte;
        byte b;
        int i = 0;
        long j = 0;
        if (this.trans_.getBytesRemainingInBuffer() >= 10) {
            byte[] buffer = this.trans_.getBuffer();
            int bufferPosition = this.trans_.getBufferPosition();
            int i2 = 0;
            while (true) {
                j |= (b & Byte.MAX_VALUE) << i2;
                if ((buffer[bufferPosition + i] & 128) != 128) {
                    break;
                }
                i2 += 7;
                i++;
            }
            this.trans_.consumeBuffer(i + 1);
        } else {
            while (true) {
                j |= (readByte & Byte.MAX_VALUE) << i;
                if ((readByte() & 128) != 128) {
                    break;
                }
                i += 7;
            }
        }
        return j;
    }

    private void writeByteDirect(byte b) throws TException {
        byte[] bArr = this.byteDirectBuffer;
        bArr[0] = b;
        this.trans_.write(bArr);
    }

    private void writeFieldBeginInternal(TField tField, byte b) throws TException {
        if (b == -1) {
            b = getCompactType(tField.type);
        }
        short s = tField.id;
        short s2 = this.lastFieldId_;
        if (s > s2 && s - s2 <= 15) {
            writeByteDirect(b | ((s - s2) << 4));
        } else {
            writeByteDirect(b);
            writeI16(tField.id);
        }
        this.lastFieldId_ = tField.id;
    }

    private void writeVarint32(int i) throws TException {
        int i2 = 0;
        while ((i & (-128)) != 0) {
            this.i32buf[i2] = (byte) ((i & 127) | 128);
            i >>>= 7;
            i2++;
        }
        byte[] bArr = this.i32buf;
        bArr[i2] = (byte) i;
        this.trans_.write(bArr, 0, i2 + 1);
    }

    private void writeVarint64(long j) throws TException {
        int i = 0;
        while (((-128) & j) != 0) {
            this.varint64out[i] = (byte) ((127 & j) | 128);
            j >>>= 7;
            i++;
        }
        byte[] bArr = this.varint64out;
        bArr[i] = (byte) j;
        this.trans_.write(bArr, 0, i + 1);
    }

    private int zigzagToInt(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    private long zigzagToLong(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public ByteBuffer readBinary() throws TException {
        int readVarint32 = readVarint32();
        checkReadLength(readVarint32);
        if (readVarint32 == 0) {
            return ByteBuffer.wrap(new byte[0]);
        }
        byte[] bArr = new byte[readVarint32];
        this.trans_.readAll(bArr, 0, readVarint32);
        return ByteBuffer.wrap(bArr);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public boolean readBool() throws TException {
        Boolean bool = this.boolValue_;
        if (bool == null) {
            return readByte() == 1;
        }
        boolean booleanValue = bool.booleanValue();
        this.boolValue_ = null;
        return booleanValue;
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public byte readByte() throws TException {
        if (this.trans_.getBytesRemainingInBuffer() > 0) {
            byte b = this.trans_.getBuffer()[this.trans_.getBufferPosition()];
            this.trans_.consumeBuffer(1);
            return b;
        }
        this.trans_.readAll(this.byteRawBuf, 0, 1);
        return this.byteRawBuf[0];
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public double readDouble() throws TException {
        byte[] bArr = new byte[8];
        this.trans_.readAll(bArr, 0, 8);
        return Double.longBitsToDouble(bytesToLong(bArr));
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public TField readFieldBegin() throws TException {
        short s;
        byte readByte = readByte();
        if (readByte == 0) {
            return TSTOP;
        }
        short s2 = (short) ((readByte & RequesterRelationshipsPacketV1.HEADER_MASK_VERSION) >> 4);
        if (s2 == 0) {
            s = readI16();
        } else {
            s = (short) (this.lastFieldId_ + s2);
        }
        byte b = (byte) (readByte & 15);
        TField tField = new TField("", getTType(b), s);
        if (isBoolType(readByte)) {
            this.boolValue_ = b == 1 ? Boolean.TRUE : Boolean.FALSE;
        }
        this.lastFieldId_ = tField.id;
        return tField;
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void readFieldEnd() throws TException {
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public short readI16() throws TException {
        return (short) zigzagToInt(readVarint32());
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public int readI32() throws TException {
        return zigzagToInt(readVarint32());
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public long readI64() throws TException {
        return zigzagToLong(readVarint64());
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public TList readListBegin() throws TException {
        byte readByte = readByte();
        int i = (readByte >> 4) & 15;
        if (i == 15) {
            i = readVarint32();
        }
        return new TList(getTType(readByte), i);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void readListEnd() throws TException {
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public TMap readMapBegin() throws TException {
        int readVarint32 = readVarint32();
        byte readByte = readVarint32 == 0 ? (byte) 0 : readByte();
        return new TMap(getTType((byte) (readByte >> 4)), getTType((byte) (readByte & 15)), readVarint32);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void readMapEnd() throws TException {
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public TMessage readMessageBegin() throws TException {
        byte readByte = readByte();
        if (readByte == -126) {
            byte readByte2 = readByte();
            byte b = (byte) (readByte2 & 31);
            if (b == 1) {
                int readVarint32 = readVarint32();
                return new TMessage(readString(), (byte) ((readByte2 >> 5) & 3), readVarint32);
            }
            throw new TProtocolException(GeneratedOutlineSupport1.outline49("Expected version 1 but got ", b));
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expected protocol id ");
        outline107.append(Integer.toHexString(-126));
        outline107.append(" but got ");
        outline107.append(Integer.toHexString(readByte));
        throw new TProtocolException(outline107.toString());
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void readMessageEnd() throws TException {
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public TSet readSetBegin() throws TException {
        return new TSet(readListBegin());
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void readSetEnd() throws TException {
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public String readString() throws TException {
        int readVarint32 = readVarint32();
        checkReadLength(readVarint32);
        if (readVarint32 == 0) {
            return "";
        }
        try {
            if (this.trans_.getBytesRemainingInBuffer() >= readVarint32) {
                String str = new String(this.trans_.getBuffer(), this.trans_.getBufferPosition(), readVarint32, "UTF-8");
                this.trans_.consumeBuffer(readVarint32);
                return str;
            }
            return new String(readBinary(readVarint32), "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            throw new TException("UTF-8 not supported!");
        }
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public TStruct readStructBegin() throws TException {
        this.lastField_.push(this.lastFieldId_);
        this.lastFieldId_ = (short) 0;
        return ANONYMOUS_STRUCT;
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void readStructEnd() throws TException {
        this.lastFieldId_ = this.lastField_.pop();
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void reset() {
        this.lastField_.clear();
        this.lastFieldId_ = (short) 0;
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeBinary(ByteBuffer byteBuffer) throws TException {
        int limit = byteBuffer.limit() - byteBuffer.position();
        writeBinary(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), limit);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeBool(boolean z) throws TException {
        TField tField = this.booleanField_;
        byte b = 1;
        if (tField != null) {
            if (!z) {
                b = 2;
            }
            writeFieldBeginInternal(tField, b);
            this.booleanField_ = null;
            return;
        }
        if (!z) {
            b = 2;
        }
        writeByteDirect(b);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeByte(byte b) throws TException {
        writeByteDirect(b);
    }

    protected void writeCollectionBegin(byte b, int i) throws TException {
        if (i <= 14) {
            writeByteDirect(getCompactType(b) | (i << 4));
            return;
        }
        writeByteDirect(getCompactType(b) | RequesterRelationshipsPacketV1.HEADER_MASK_VERSION);
        writeVarint32(i);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeDouble(double d) throws TException {
        byte[] bArr = {0, 0, 0, 0, 0, 0, 0, 0};
        fixedLongToBytes(Double.doubleToLongBits(d), bArr, 0);
        this.trans_.write(bArr);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeFieldBegin(TField tField) throws TException {
        if (tField.type == 2) {
            this.booleanField_ = tField;
        } else {
            writeFieldBeginInternal(tField, (byte) -1);
        }
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeFieldEnd() throws TException {
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeFieldStop() throws TException {
        writeByteDirect((byte) 0);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeI16(short s) throws TException {
        writeVarint32(intToZigZag(s));
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeI32(int i) throws TException {
        writeVarint32(intToZigZag(i));
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeI64(long j) throws TException {
        writeVarint64(longToZigzag(j));
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeListBegin(TList tList) throws TException {
        writeCollectionBegin(tList.elemType, tList.size);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeListEnd() throws TException {
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeMapBegin(TMap tMap) throws TException {
        int i = tMap.size;
        if (i == 0) {
            writeByteDirect(0);
            return;
        }
        writeVarint32(i);
        writeByteDirect(getCompactType(tMap.valueType) | (getCompactType(tMap.keyType) << 4));
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeMapEnd() throws TException {
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeMessageBegin(TMessage tMessage) throws TException {
        writeByteDirect(PROTOCOL_ID);
        writeByteDirect(((tMessage.type << 5) & (-32)) | 1);
        writeVarint32(tMessage.seqid);
        writeString(tMessage.name);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeMessageEnd() throws TException {
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeSetBegin(TSet tSet) throws TException {
        writeCollectionBegin(tSet.elemType, tSet.size);
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeSetEnd() throws TException {
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeString(String str) throws TException {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            writeBinary(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException unused) {
            throw new TException("UTF-8 not supported!");
        }
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeStructBegin(TStruct tStruct) throws TException {
        this.lastField_.push(this.lastFieldId_);
        this.lastFieldId_ = (short) 0;
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public void writeStructEnd() throws TException {
        this.lastFieldId_ = this.lastField_.pop();
    }

    /* loaded from: classes4.dex */
    public static class Factory implements TProtocolFactory {
        private final long maxNetworkBytes_;

        public Factory() {
            this.maxNetworkBytes_ = -1L;
        }

        @Override // org.apache.thrift.orig.protocol.TProtocolFactory
        public TProtocol getProtocol(TTransport tTransport) {
            return new TCompactProtocol(tTransport, this.maxNetworkBytes_);
        }

        public Factory(int i) {
            this.maxNetworkBytes_ = i;
        }
    }

    private void writeBinary(byte[] bArr, int i, int i2) throws TException {
        writeVarint32(i2);
        this.trans_.write(bArr, i, i2);
    }

    private void writeByteDirect(int i) throws TException {
        writeByteDirect((byte) i);
    }

    private byte[] readBinary(int i) throws TException {
        if (i == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i];
        this.trans_.readAll(bArr, 0, i);
        return bArr;
    }

    public TCompactProtocol(TTransport tTransport) {
        this(tTransport, -1L);
    }
}
