package com.amazon.alexa;

import com.amazon.alexa.api.ApiCallback;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.List;
import java.util.Locale;
/* compiled from: AutoValue_ApiCallEvent_Locale_SetLocaleEvent.java */
/* loaded from: classes.dex */
public final class nOx extends wzr {
    public final eOP BIo;
    public final boolean Qle;
    public final List<Locale> jiA;
    public final ExtendedClient zQM;
    public final ApiCallback zyO;

    public nOx(eOP eop, ExtendedClient extendedClient, ApiCallback apiCallback, List<Locale> list, boolean z) {
        if (eop != null) {
            this.BIo = eop;
            if (extendedClient != null) {
                this.zQM = extendedClient;
                if (apiCallback != null) {
                    this.zyO = apiCallback;
                    if (list != null) {
                        this.jiA = list;
                        this.Qle = z;
                        return;
                    }
                    throw new NullPointerException("Null locales");
                }
                throw new NullPointerException("Null apiCallback");
            }
            throw new NullPointerException("Null client");
        }
        throw new NullPointerException("Null apiCallMetadata");
    }

    @Override // com.amazon.alexa.NEv
    public eOP BIo() {
        return this.BIo;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof wzr)) {
            return false;
        }
        nOx nox = (nOx) obj;
        return this.BIo.equals(nox.BIo) && this.zQM.equals(nox.zQM) && this.zyO.equals(nox.zyO) && this.jiA.equals(nox.jiA) && this.Qle == nox.Qle;
    }

    public int hashCode() {
        return ((((((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode()) * 1000003) ^ (this.Qle ? 1231 : 1237);
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("SetLocaleEvent{apiCallMetadata=");
        zZm.append(this.BIo);
        zZm.append(", client=");
        zZm.append(this.zQM);
        zZm.append(", apiCallback=");
        zZm.append(this.zyO);
        zZm.append(", locales=");
        zZm.append(this.jiA);
        zZm.append(", forceUpdate=");
        return C0179Pya.zZm(zZm, this.Qle, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    @Override // com.amazon.alexa.NEv.BIo
    public ApiCallback zQM() {
        return this.zyO;
    }

    @Override // com.amazon.alexa.NEv.BIo
    public ExtendedClient zyO() {
        return this.zQM;
    }
}
