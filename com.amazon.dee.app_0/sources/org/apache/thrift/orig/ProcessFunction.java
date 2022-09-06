package org.apache.thrift.orig;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.apache.thrift.orig.TBase;
import org.apache.thrift.orig.protocol.TMessage;
import org.apache.thrift.orig.protocol.TProtocol;
import org.apache.thrift.orig.protocol.TProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes4.dex */
public abstract class ProcessFunction<I, T extends TBase> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessFunction.class.getName());
    private final String methodName;

    public ProcessFunction(String str) {
        this.methodName = str;
    }

    /* renamed from: getEmptyArgsInstance */
    public abstract T mo3865getEmptyArgsInstance();

    public String getMethodName() {
        return this.methodName;
    }

    public abstract TBase getResult(I i, T t) throws TException;

    protected abstract boolean isOneway();

    public final void process(int i, TProtocol tProtocol, TProtocol tProtocol2, I i2) throws TException {
        T mo3865getEmptyArgsInstance = mo3865getEmptyArgsInstance();
        try {
            mo3865getEmptyArgsInstance.read(tProtocol);
            tProtocol.readMessageEnd();
            try {
                TBase result = getResult(i2, mo3865getEmptyArgsInstance);
                if (isOneway()) {
                    return;
                }
                tProtocol2.writeMessageBegin(new TMessage(getMethodName(), (byte) 2, i));
                result.write(tProtocol2);
                tProtocol2.writeMessageEnd();
                tProtocol2.getTransport().flush();
            } catch (Throwable th) {
                Logger logger = LOGGER;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Internal error processing ");
                outline107.append(getMethodName());
                logger.error(outline107.toString(), th);
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Internal error processing ");
                outline1072.append(getMethodName());
                TApplicationException tApplicationException = new TApplicationException(6, outline1072.toString());
                tProtocol2.writeMessageBegin(new TMessage(getMethodName(), (byte) 3, i));
                tApplicationException.write(tProtocol2);
                tProtocol2.writeMessageEnd();
                tProtocol2.getTransport().flush();
            }
        } catch (TProtocolException e) {
            tProtocol.readMessageEnd();
            TApplicationException tApplicationException2 = new TApplicationException(7, e.getMessage());
            tProtocol2.writeMessageBegin(new TMessage(getMethodName(), (byte) 3, i));
            tApplicationException2.write(tProtocol2);
            tProtocol2.writeMessageEnd();
            tProtocol2.getTransport().flush();
        }
    }
}
