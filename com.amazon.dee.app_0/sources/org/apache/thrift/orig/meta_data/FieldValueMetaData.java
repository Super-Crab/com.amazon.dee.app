package org.apache.thrift.orig.meta_data;

import java.io.Serializable;
/* loaded from: classes4.dex */
public class FieldValueMetaData implements Serializable {
    private final boolean isBinary;
    private final boolean isTypedefType;
    public final byte type;
    private final String typedefName;

    public FieldValueMetaData(byte b, boolean z) {
        this.type = b;
        this.isTypedefType = false;
        this.typedefName = null;
        this.isBinary = z;
    }

    public String getTypedefName() {
        return this.typedefName;
    }

    public boolean isBinary() {
        return this.isBinary;
    }

    public boolean isContainer() {
        byte b = this.type;
        return b == 15 || b == 13 || b == 14;
    }

    public boolean isStruct() {
        return this.type == 12;
    }

    public boolean isTypedef() {
        return this.isTypedefType;
    }

    public FieldValueMetaData(byte b) {
        this(b, false);
    }

    public FieldValueMetaData(byte b, String str) {
        this.type = b;
        this.isTypedefType = true;
        this.typedefName = str;
        this.isBinary = false;
    }
}
