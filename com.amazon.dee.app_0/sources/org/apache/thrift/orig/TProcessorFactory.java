package org.apache.thrift.orig;

import org.apache.thrift.orig.transport.TTransport;
/* loaded from: classes4.dex */
public class TProcessorFactory {
    private final TProcessor processor_;

    public TProcessorFactory(TProcessor tProcessor) {
        this.processor_ = tProcessor;
    }

    public TProcessor getProcessor(TTransport tTransport) {
        return this.processor_;
    }
}
