package org.apache.thrift.orig.protocol;

import java.io.Serializable;
import org.apache.thrift.orig.transport.TTransport;
/* loaded from: classes4.dex */
public interface TProtocolFactory extends Serializable {
    TProtocol getProtocol(TTransport tTransport);
}
