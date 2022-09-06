package org.apache.thrift.orig;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.Map;
import org.apache.thrift.orig.protocol.TMessage;
import org.apache.thrift.orig.protocol.TProtocol;
import org.apache.thrift.orig.protocol.TProtocolUtil;
/* loaded from: classes4.dex */
public abstract class TBaseProcessor<I> implements TProcessor {
    private final I iface;
    private final Map<String, ProcessFunction<I, ? extends TBase>> processMap;

    /* JADX INFO: Access modifiers changed from: protected */
    public TBaseProcessor(I i, Map<String, ProcessFunction<I, ? extends TBase>> map) {
        this.iface = i;
        this.processMap = map;
    }

    public Map<String, ProcessFunction<I, ? extends TBase>> getProcessMapView() {
        return Collections.unmodifiableMap(this.processMap);
    }

    @Override // org.apache.thrift.orig.TProcessor
    public boolean process(TProtocol tProtocol, TProtocol tProtocol2) throws TException {
        TMessage readMessageBegin = tProtocol.readMessageBegin();
        ProcessFunction<I, ? extends TBase> processFunction = this.processMap.get(readMessageBegin.name);
        if (processFunction == null) {
            TProtocolUtil.skip(tProtocol, (byte) 12);
            tProtocol.readMessageEnd();
            TApplicationException tApplicationException = new TApplicationException(1, GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Invalid method name: '"), readMessageBegin.name, "'"));
            tProtocol2.writeMessageBegin(new TMessage(readMessageBegin.name, (byte) 3, readMessageBegin.seqid));
            tApplicationException.write(tProtocol2);
            tProtocol2.writeMessageEnd();
            tProtocol2.getTransport().flush();
            return true;
        }
        processFunction.process(readMessageBegin.seqid, tProtocol, tProtocol2, this.iface);
        return true;
    }
}
