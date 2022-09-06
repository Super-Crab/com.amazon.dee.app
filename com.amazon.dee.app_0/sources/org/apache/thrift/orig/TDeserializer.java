package org.apache.thrift.orig;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.apache.thrift.orig.protocol.TBinaryProtocol;
import org.apache.thrift.orig.protocol.TField;
import org.apache.thrift.orig.protocol.TProtocol;
import org.apache.thrift.orig.protocol.TProtocolFactory;
import org.apache.thrift.orig.protocol.TProtocolUtil;
import org.apache.thrift.orig.transport.TMemoryInputTransport;
/* loaded from: classes4.dex */
public class TDeserializer {
    private final TProtocol protocol_;
    private final TMemoryInputTransport trans_;

    public TDeserializer() {
        this(new TBinaryProtocol.Factory());
    }

    private TField locateField(byte[] bArr, TFieldIdEnum tFieldIdEnum, TFieldIdEnum... tFieldIdEnumArr) throws TException {
        this.trans_.reset(bArr);
        TFieldIdEnum[] tFieldIdEnumArr2 = new TFieldIdEnum[tFieldIdEnumArr.length + 1];
        int i = 0;
        tFieldIdEnumArr2[0] = tFieldIdEnum;
        int i2 = 0;
        while (i2 < tFieldIdEnumArr.length) {
            int i3 = i2 + 1;
            tFieldIdEnumArr2[i3] = tFieldIdEnumArr[i2];
            i2 = i3;
        }
        this.protocol_.readStructBegin();
        TField tField = null;
        while (i < tFieldIdEnumArr2.length) {
            tField = this.protocol_.readFieldBegin();
            if (tField.type == 0 || tField.id > tFieldIdEnumArr2[i].getThriftFieldId()) {
                return null;
            }
            if (tField.id != tFieldIdEnumArr2[i].getThriftFieldId()) {
                TProtocolUtil.skip(this.protocol_, tField.type);
                this.protocol_.readFieldEnd();
            } else {
                i++;
                if (i < tFieldIdEnumArr2.length) {
                    this.protocol_.readStructBegin();
                }
            }
        }
        return tField;
    }

    private Object partialDeserializeField(byte b, byte[] bArr, TFieldIdEnum tFieldIdEnum, TFieldIdEnum... tFieldIdEnumArr) throws TException {
        Object obj;
        try {
            try {
                TField locateField = locateField(bArr, tFieldIdEnum, tFieldIdEnumArr);
                if (locateField != null) {
                    if (b != 2) {
                        if (b != 3) {
                            if (b != 4) {
                                if (b != 6) {
                                    if (b != 8) {
                                        if (b != 100) {
                                            if (b != 10) {
                                                if (b == 11 && locateField.type == 11) {
                                                    obj = this.protocol_.readString();
                                                }
                                            } else if (locateField.type == 10) {
                                                obj = Long.valueOf(this.protocol_.readI64());
                                            }
                                        } else if (locateField.type == 11) {
                                            obj = this.protocol_.readBinary();
                                        }
                                    } else if (locateField.type == 8) {
                                        obj = Integer.valueOf(this.protocol_.readI32());
                                    }
                                } else if (locateField.type == 6) {
                                    obj = Short.valueOf(this.protocol_.readI16());
                                }
                            } else if (locateField.type == 4) {
                                obj = Double.valueOf(this.protocol_.readDouble());
                            }
                        } else if (locateField.type == 3) {
                            obj = Byte.valueOf(this.protocol_.readByte());
                        }
                    } else if (locateField.type == 2) {
                        obj = Boolean.valueOf(this.protocol_.readBool());
                    }
                    return obj;
                }
                obj = null;
                return obj;
            } catch (Exception e) {
                throw new TException(e);
            }
        } finally {
            this.trans_.clear();
            this.protocol_.reset();
        }
    }

    public void deserialize(TBase tBase, byte[] bArr) throws TException {
        try {
            this.trans_.reset(bArr);
            tBase.read(this.protocol_);
        } finally {
            this.trans_.clear();
            this.protocol_.reset();
        }
    }

    public void fromString(TBase tBase, String str) throws TException {
        deserialize(tBase, str.getBytes());
    }

    public void partialDeserialize(TBase tBase, byte[] bArr, TFieldIdEnum tFieldIdEnum, TFieldIdEnum... tFieldIdEnumArr) throws TException {
        try {
            try {
                if (locateField(bArr, tFieldIdEnum, tFieldIdEnumArr) != null) {
                    tBase.read(this.protocol_);
                }
            } catch (Exception e) {
                throw new TException(e);
            }
        } finally {
            this.trans_.clear();
            this.protocol_.reset();
        }
    }

    public Boolean partialDeserializeBool(byte[] bArr, TFieldIdEnum tFieldIdEnum, TFieldIdEnum... tFieldIdEnumArr) throws TException {
        return (Boolean) partialDeserializeField((byte) 2, bArr, tFieldIdEnum, tFieldIdEnumArr);
    }

    public Byte partialDeserializeByte(byte[] bArr, TFieldIdEnum tFieldIdEnum, TFieldIdEnum... tFieldIdEnumArr) throws TException {
        return (Byte) partialDeserializeField((byte) 3, bArr, tFieldIdEnum, tFieldIdEnumArr);
    }

    public ByteBuffer partialDeserializeByteArray(byte[] bArr, TFieldIdEnum tFieldIdEnum, TFieldIdEnum... tFieldIdEnumArr) throws TException {
        return (ByteBuffer) partialDeserializeField((byte) 100, bArr, tFieldIdEnum, tFieldIdEnumArr);
    }

    public Double partialDeserializeDouble(byte[] bArr, TFieldIdEnum tFieldIdEnum, TFieldIdEnum... tFieldIdEnumArr) throws TException {
        return (Double) partialDeserializeField((byte) 4, bArr, tFieldIdEnum, tFieldIdEnumArr);
    }

    public Short partialDeserializeI16(byte[] bArr, TFieldIdEnum tFieldIdEnum, TFieldIdEnum... tFieldIdEnumArr) throws TException {
        return (Short) partialDeserializeField((byte) 6, bArr, tFieldIdEnum, tFieldIdEnumArr);
    }

    public Integer partialDeserializeI32(byte[] bArr, TFieldIdEnum tFieldIdEnum, TFieldIdEnum... tFieldIdEnumArr) throws TException {
        return (Integer) partialDeserializeField((byte) 8, bArr, tFieldIdEnum, tFieldIdEnumArr);
    }

    public Long partialDeserializeI64(byte[] bArr, TFieldIdEnum tFieldIdEnum, TFieldIdEnum... tFieldIdEnumArr) throws TException {
        return (Long) partialDeserializeField((byte) 10, bArr, tFieldIdEnum, tFieldIdEnumArr);
    }

    public Short partialDeserializeSetFieldIdInUnion(byte[] bArr, TFieldIdEnum tFieldIdEnum, TFieldIdEnum... tFieldIdEnumArr) throws TException {
        Short sh;
        try {
            try {
                if (locateField(bArr, tFieldIdEnum, tFieldIdEnumArr) != null) {
                    this.protocol_.readStructBegin();
                    sh = Short.valueOf(this.protocol_.readFieldBegin().id);
                } else {
                    sh = null;
                }
                return sh;
            } catch (Exception e) {
                throw new TException(e);
            }
        } finally {
            this.trans_.clear();
            this.protocol_.reset();
        }
    }

    public String partialDeserializeString(byte[] bArr, TFieldIdEnum tFieldIdEnum, TFieldIdEnum... tFieldIdEnumArr) throws TException {
        return (String) partialDeserializeField((byte) 11, bArr, tFieldIdEnum, tFieldIdEnumArr);
    }

    public TDeserializer(TProtocolFactory tProtocolFactory) {
        this.trans_ = new TMemoryInputTransport();
        this.protocol_ = tProtocolFactory.getProtocol(this.trans_);
    }

    public void deserialize(TBase tBase, String str, String str2) throws TException {
        try {
            try {
                deserialize(tBase, str.getBytes(str2));
            } catch (UnsupportedEncodingException unused) {
                throw new TException("JVM DOES NOT SUPPORT ENCODING: " + str2);
            }
        } finally {
            this.protocol_.reset();
        }
    }
}
