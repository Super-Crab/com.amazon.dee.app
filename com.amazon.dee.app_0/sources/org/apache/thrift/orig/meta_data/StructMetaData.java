package org.apache.thrift.orig.meta_data;

import org.apache.thrift.orig.TBase;
/* loaded from: classes4.dex */
public class StructMetaData extends FieldValueMetaData {
    public final Class<? extends TBase> structClass;

    public StructMetaData(byte b, Class<? extends TBase> cls) {
        super(b);
        this.structClass = cls;
    }
}
