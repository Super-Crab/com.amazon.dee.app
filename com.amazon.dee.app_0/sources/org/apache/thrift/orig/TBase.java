package org.apache.thrift.orig;

import java.io.Serializable;
import org.apache.thrift.orig.TBase;
import org.apache.thrift.orig.TFieldIdEnum;
import org.apache.thrift.orig.protocol.TProtocol;
/* loaded from: classes4.dex */
public interface TBase<T extends TBase<?, ?>, F extends TFieldIdEnum> extends Comparable<T>, Serializable {
    void clear();

    TBase<T, F> deepCopy();

    /* renamed from: fieldForId */
    F mo3968fieldForId(int i);

    Object getFieldValue(F f);

    boolean isSet(F f);

    void read(TProtocol tProtocol) throws TException;

    void setFieldValue(F f, Object obj);

    void write(TProtocol tProtocol) throws TException;
}
