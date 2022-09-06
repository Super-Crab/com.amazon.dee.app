package org.apache.thrift.orig;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.apache.thrift.orig.protocol.TMessage;
import org.apache.thrift.orig.protocol.TProtocol;
/* loaded from: classes4.dex */
public abstract class TServiceClient {
    protected TProtocol iprot_;
    protected TProtocol oprot_;
    protected int seqid_;

    public TServiceClient(TProtocol tProtocol) {
        this(tProtocol, tProtocol);
    }

    public TProtocol getInputProtocol() {
        return this.iprot_;
    }

    public TProtocol getOutputProtocol() {
        return this.oprot_;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void receiveBase(TBase tBase, String str) throws TException {
        TMessage readMessageBegin = this.iprot_.readMessageBegin();
        if (readMessageBegin.type != 3) {
            if (readMessageBegin.seqid == this.seqid_) {
                tBase.read(this.iprot_);
                this.iprot_.readMessageEnd();
                return;
            }
            throw new TApplicationException(4, GeneratedOutlineSupport1.outline72(str, " failed: out of sequence response"));
        }
        TApplicationException read = TApplicationException.read(this.iprot_);
        this.iprot_.readMessageEnd();
        throw read;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendBase(String str, TBase tBase) throws TException {
        TProtocol tProtocol = this.oprot_;
        int i = this.seqid_ + 1;
        this.seqid_ = i;
        tProtocol.writeMessageBegin(new TMessage(str, (byte) 1, i));
        tBase.write(this.oprot_);
        this.oprot_.writeMessageEnd();
        this.oprot_.getTransport().flush();
    }

    public TServiceClient(TProtocol tProtocol, TProtocol tProtocol2) {
        this.iprot_ = tProtocol;
        this.oprot_ = tProtocol2;
    }
}
