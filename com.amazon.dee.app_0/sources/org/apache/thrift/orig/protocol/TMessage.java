package org.apache.thrift.orig.protocol;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes4.dex */
public final class TMessage {
    public final String name;
    public final int seqid;
    public final byte type;

    public TMessage() {
        this("", (byte) 0, 0);
    }

    public boolean equals(Object obj) {
        if (obj instanceof TMessage) {
            return equals((TMessage) obj);
        }
        return false;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("<TMessage name:'");
        outline107.append(this.name);
        outline107.append("' type: ");
        outline107.append((int) this.type);
        outline107.append(" seqid:");
        return GeneratedOutlineSupport1.outline86(outline107, this.seqid, Config.Compare.GREATER_THAN);
    }

    public TMessage(String str, byte b, int i) {
        this.name = str;
        this.type = b;
        this.seqid = i;
    }

    public boolean equals(TMessage tMessage) {
        return this.name.equals(tMessage.name) && this.type == tMessage.type && this.seqid == tMessage.seqid;
    }
}
