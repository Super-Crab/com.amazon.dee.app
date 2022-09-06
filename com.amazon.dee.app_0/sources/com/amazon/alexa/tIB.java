package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import javax.annotation.Nullable;
/* compiled from: $AutoValue_LaunchConfig.java */
/* loaded from: classes.dex */
public abstract class tIB extends GhS {
    public final Boolean zZm;

    public tIB(@Nullable Boolean bool) {
        this.zZm = bool;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GhS)) {
            return false;
        }
        Boolean bool = this.zZm;
        if (bool != null) {
            return bool.equals(((tIB) obj).zZm);
        }
        return ((tIB) obj).zZm == null;
    }

    public int hashCode() {
        Boolean bool = this.zZm;
        return (bool == null ? 0 : bool.hashCode()) ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("LaunchConfig{isMandatoryToLaunchTarget="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
