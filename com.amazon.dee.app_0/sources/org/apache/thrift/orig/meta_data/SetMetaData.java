package org.apache.thrift.orig.meta_data;
/* loaded from: classes4.dex */
public class SetMetaData extends FieldValueMetaData {
    public final FieldValueMetaData elemMetaData;

    public SetMetaData(byte b, FieldValueMetaData fieldValueMetaData) {
        super(b);
        this.elemMetaData = fieldValueMetaData;
    }
}
