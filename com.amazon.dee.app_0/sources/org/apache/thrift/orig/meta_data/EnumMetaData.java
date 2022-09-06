package org.apache.thrift.orig.meta_data;

import org.apache.thrift.orig.TEnum;
/* loaded from: classes4.dex */
public class EnumMetaData extends FieldValueMetaData {
    public final Class<? extends TEnum> enumClass;

    public EnumMetaData(byte b, Class<? extends TEnum> cls) {
        super(b);
        this.enumClass = cls;
    }
}
