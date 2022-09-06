package org.apache.thrift.orig.protocol;

import java.util.BitSet;
import org.apache.thrift.orig.TException;
import org.apache.thrift.orig.scheme.IScheme;
import org.apache.thrift.orig.scheme.TupleScheme;
import org.apache.thrift.orig.transport.TTransport;
/* loaded from: classes4.dex */
public final class TTupleProtocol extends TCompactProtocol {

    /* loaded from: classes4.dex */
    public static class Factory implements TProtocolFactory {
        @Override // org.apache.thrift.orig.protocol.TProtocolFactory
        public TProtocol getProtocol(TTransport tTransport) {
            return new TTupleProtocol(tTransport);
        }
    }

    public TTupleProtocol(TTransport tTransport) {
        super(tTransport);
    }

    public static BitSet fromByteArray(byte[] bArr) {
        BitSet bitSet = new BitSet();
        for (int i = 0; i < bArr.length * 8; i++) {
            if ((bArr[(bArr.length - (i / 8)) - 1] & (1 << (i % 8))) > 0) {
                bitSet.set(i);
            }
        }
        return bitSet;
    }

    public static byte[] toByteArray(BitSet bitSet, int i) {
        byte[] bArr = new byte[(int) Math.ceil(i / 8.0d)];
        for (int i2 = 0; i2 < bitSet.length(); i2++) {
            if (bitSet.get(i2)) {
                int length = (bArr.length - (i2 / 8)) - 1;
                bArr[length] = (byte) ((1 << (i2 % 8)) | bArr[length]);
            }
        }
        return bArr;
    }

    @Override // org.apache.thrift.orig.protocol.TProtocol
    public Class<? extends IScheme> getScheme() {
        return TupleScheme.class;
    }

    public BitSet readBitSet(int i) throws TException {
        int ceil = (int) Math.ceil(i / 8.0d);
        byte[] bArr = new byte[ceil];
        for (int i2 = 0; i2 < ceil; i2++) {
            bArr[i2] = readByte();
        }
        return fromByteArray(bArr);
    }

    public void writeBitSet(BitSet bitSet, int i) throws TException {
        for (byte b : toByteArray(bitSet, i)) {
            writeByte(b);
        }
    }
}
