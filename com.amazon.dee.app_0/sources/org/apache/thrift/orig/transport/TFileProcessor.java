package org.apache.thrift.orig.transport;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.apache.thrift.orig.TException;
import org.apache.thrift.orig.TProcessor;
import org.apache.thrift.orig.protocol.TProtocol;
import org.apache.thrift.orig.protocol.TProtocolFactory;
/* loaded from: classes4.dex */
public class TFileProcessor {
    private TProtocolFactory inputProtocolFactory_;
    private TFileTransport inputTransport_;
    private TProtocolFactory outputProtocolFactory_;
    private TTransport outputTransport_;
    private TProcessor processor_;

    public TFileProcessor(TProcessor tProcessor, TProtocolFactory tProtocolFactory, TFileTransport tFileTransport, TTransport tTransport) {
        this.processor_ = tProcessor;
        this.outputProtocolFactory_ = tProtocolFactory;
        this.inputProtocolFactory_ = tProtocolFactory;
        this.inputTransport_ = tFileTransport;
        this.outputTransport_ = tTransport;
    }

    private void processUntil(int i) throws TException {
        TProtocol protocol = this.inputProtocolFactory_.getProtocol(this.inputTransport_);
        TProtocol protocol2 = this.outputProtocolFactory_.getProtocol(this.outputTransport_);
        int curChunk = this.inputTransport_.getCurChunk();
        while (i >= curChunk) {
            try {
                this.processor_.process(protocol, protocol2);
                curChunk = this.inputTransport_.getCurChunk();
            } catch (TTransportException e) {
                if (e.getType() != 4) {
                    throw e;
                }
                return;
            }
        }
    }

    public void processChunk(int i, int i2) throws TException {
        int numChunks = this.inputTransport_.getNumChunks();
        if (i2 < 0) {
            i2 += numChunks;
        }
        if (i < 0) {
            i += numChunks;
        }
        if (i2 >= i) {
            this.inputTransport_.seekToChunk(i);
            processUntil(i2);
            return;
        }
        throw new TException(GeneratedOutlineSupport1.outline53("endChunkNum ", i2, " is less than ", i));
    }

    public void processChunk(int i) throws TException {
        processChunk(i, i);
    }

    public TFileProcessor(TProcessor tProcessor, TProtocolFactory tProtocolFactory, TProtocolFactory tProtocolFactory2, TFileTransport tFileTransport, TTransport tTransport) {
        this.processor_ = tProcessor;
        this.inputProtocolFactory_ = tProtocolFactory;
        this.outputProtocolFactory_ = tProtocolFactory2;
        this.inputTransport_ = tFileTransport;
        this.outputTransport_ = tTransport;
    }

    public void processChunk() throws TException {
        processChunk(this.inputTransport_.getCurChunk());
    }
}
