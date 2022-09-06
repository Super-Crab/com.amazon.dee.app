package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_PlaylistFetchResultEvent.java */
/* renamed from: com.amazon.alexa.jfo  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0212jfo extends bOH {
    public final boolean BIo;

    public C0212jfo(boolean z) {
        this.BIo = z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof bOH) && this.BIo == ((C0212jfo) obj).BIo;
    }

    public int hashCode() {
        return (this.BIo ? 1231 : 1237) ^ 1000003;
    }

    public String toString() {
        return C0179Pya.zZm(C0179Pya.zZm("PlaylistFetchResultEvent{hasMoreItems="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
