package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.api.ApiName;
import com.google.auto.value.AutoValue;
/* compiled from: ApiCallMetadata.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class eOP {
    public static final eOP zZm = zZm(MNR.zZm, ApiName.zZm);

    public static eOP zZm(MNR mnr, ApiName apiName) {
        return new vhv(mnr, apiName);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof eOP) {
            return ((vhv) this).BIo.equals(((vhv) obj).BIo);
        }
        return false;
    }

    public int hashCode() {
        return ((vhv) this).BIo.hashCode();
    }

    public boolean zZm() {
        vhv vhvVar = (vhv) this;
        return vhvVar.BIo == null || vhvVar.zQM == null;
    }
}
