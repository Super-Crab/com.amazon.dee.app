package org.apache.thrift.orig;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import org.apache.thrift.orig.protocol.TBinaryProtocol;
import org.apache.thrift.orig.protocol.TProtocol;
import org.apache.thrift.orig.protocol.TProtocolFactory;
import org.apache.thrift.orig.transport.TIOStreamTransport;
/* loaded from: classes4.dex */
public class TSerializer {
    private final ByteArrayOutputStream baos_;
    private TProtocol protocol_;
    private final TIOStreamTransport transport_;

    public TSerializer() {
        this(new TBinaryProtocol.Factory());
    }

    public byte[] serialize(TBase tBase) throws TException {
        this.baos_.reset();
        tBase.write(this.protocol_);
        return this.baos_.toByteArray();
    }

    public String toString(TBase tBase, String str) throws TException {
        try {
            return new String(serialize(tBase), str);
        } catch (UnsupportedEncodingException unused) {
            throw new TException(GeneratedOutlineSupport1.outline72("JVM DOES NOT SUPPORT ENCODING: ", str));
        }
    }

    public TSerializer(TProtocolFactory tProtocolFactory) {
        this.baos_ = new ByteArrayOutputStream();
        this.transport_ = new TIOStreamTransport(this.baos_);
        this.protocol_ = tProtocolFactory.getProtocol(this.transport_);
    }

    public String toString(TBase tBase) throws TException {
        return new String(serialize(tBase));
    }
}
