package org.apache.thrift.orig.meta_data;
/* loaded from: classes4.dex */
public class ListMetaData extends FieldValueMetaData {
    public final FieldValueMetaData elemMetaData;

    public ListMetaData(byte b, FieldValueMetaData fieldValueMetaData) {
        super(b);
        this.elemMetaData = fieldValueMetaData;
    }
}
