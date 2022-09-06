package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_PackageRemovedEvent.java */
/* renamed from: com.amazon.alexa.bkj  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0191bkj extends ery {
    public final String BIo;

    public C0191bkj(String str) {
        if (str != null) {
            this.BIo = str;
            return;
        }
        throw new NullPointerException("Null packageName");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ery)) {
            return false;
        }
        return this.BIo.equals(((C0191bkj) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.zZm(C0179Pya.zZm("PackageRemovedEvent{packageName="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
