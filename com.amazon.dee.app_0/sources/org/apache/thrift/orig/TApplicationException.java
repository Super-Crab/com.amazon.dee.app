package org.apache.thrift.orig;

import org.apache.thrift.orig.protocol.TField;
import org.apache.thrift.orig.protocol.TProtocol;
import org.apache.thrift.orig.protocol.TProtocolUtil;
import org.apache.thrift.orig.protocol.TStruct;
/* loaded from: classes4.dex */
public class TApplicationException extends TException {
    public static final int BAD_SEQUENCE_ID = 4;
    public static final int INTERNAL_ERROR = 6;
    public static final int INVALID_MESSAGE_TYPE = 2;
    public static final int MISSING_RESULT = 5;
    public static final int PROTOCOL_ERROR = 7;
    public static final int UNKNOWN = 0;
    public static final int UNKNOWN_METHOD = 1;
    public static final int WRONG_METHOD_NAME = 3;
    private static final long serialVersionUID = 1;
    protected int type_;
    private static final TStruct TAPPLICATION_EXCEPTION_STRUCT = new TStruct("TApplicationException");
    private static final TField MESSAGE_FIELD = new TField("message", (byte) 11, 1);
    private static final TField TYPE_FIELD = new TField("type", (byte) 8, 2);

    public TApplicationException() {
        this.type_ = 0;
    }

    public static TApplicationException read(TProtocol tProtocol) throws TException {
        tProtocol.readStructBegin();
        String str = null;
        int i = 0;
        while (true) {
            TField readFieldBegin = tProtocol.readFieldBegin();
            byte b = readFieldBegin.type;
            if (b == 0) {
                tProtocol.readStructEnd();
                return new TApplicationException(i, str);
            }
            short s = readFieldBegin.id;
            if (s != 1) {
                if (s != 2) {
                    TProtocolUtil.skip(tProtocol, b);
                } else if (b == 8) {
                    i = tProtocol.readI32();
                } else {
                    TProtocolUtil.skip(tProtocol, b);
                }
            } else if (b == 11) {
                str = tProtocol.readString();
            } else {
                TProtocolUtil.skip(tProtocol, b);
            }
            tProtocol.readFieldEnd();
        }
    }

    public int getType() {
        return this.type_;
    }

    public void write(TProtocol tProtocol) throws TException {
        tProtocol.writeStructBegin(TAPPLICATION_EXCEPTION_STRUCT);
        if (getMessage() != null) {
            tProtocol.writeFieldBegin(MESSAGE_FIELD);
            tProtocol.writeString(getMessage());
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldBegin(TYPE_FIELD);
        tProtocol.writeI32(this.type_);
        tProtocol.writeFieldEnd();
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }

    public TApplicationException(int i) {
        this.type_ = 0;
        this.type_ = i;
    }

    public TApplicationException(int i, String str) {
        super(str);
        this.type_ = 0;
        this.type_ = i;
    }

    public TApplicationException(String str) {
        super(str);
        this.type_ = 0;
    }
}
