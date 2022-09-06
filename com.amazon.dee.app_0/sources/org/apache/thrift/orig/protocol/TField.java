package org.apache.thrift.orig.protocol;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes4.dex */
public class TField {
    public final short id;
    public final String name;
    public final byte type;

    public TField() {
        this("", (byte) 0, (short) 0);
    }

    public boolean equals(TField tField) {
        return this.type == tField.type && this.id == tField.id;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("<TField name:'");
        outline107.append(this.name);
        outline107.append("' type:");
        outline107.append((int) this.type);
        outline107.append(" field-id:");
        return GeneratedOutlineSupport1.outline86(outline107, this.id, Config.Compare.GREATER_THAN);
    }

    public TField(String str, byte b, short s) {
        this.name = str;
        this.type = b;
        this.id = s;
    }
}
