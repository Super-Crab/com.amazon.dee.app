package com.amazon.alexa;

import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.fuM;
/* compiled from: AutoValue_RecordingEvent_StartedEvent.java */
/* loaded from: classes.dex */
public final class Don extends fuM.BIo {
    public final ExtendedClient BIo;
    public final AlexaDialogExtras jiA;
    public final fuM.zZm zQM;
    public final DialogRequestIdentifier zyO;

    public Don(ExtendedClient extendedClient, fuM.zZm zzm, DialogRequestIdentifier dialogRequestIdentifier, AlexaDialogExtras alexaDialogExtras) {
        if (extendedClient != null) {
            this.BIo = extendedClient;
            if (zzm != null) {
                this.zQM = zzm;
                if (dialogRequestIdentifier != null) {
                    this.zyO = dialogRequestIdentifier;
                    if (alexaDialogExtras != null) {
                        this.jiA = alexaDialogExtras;
                        return;
                    }
                    throw new NullPointerException("Null dialogExtras");
                }
                throw new NullPointerException("Null dialogRequestIdentifier");
            }
            throw new NullPointerException("Null source");
        }
        throw new NullPointerException("Null client");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fuM.BIo)) {
            return false;
        }
        Don don = (Don) obj;
        return this.BIo.equals(don.BIo) && this.zQM.equals(don.zQM) && this.zyO.equals(don.zyO) && this.jiA.equals(don.jiA);
    }

    public int hashCode() {
        return ((((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("StartedEvent{client=");
        zZm.append(this.BIo);
        zZm.append(", source=");
        zZm.append(this.zQM);
        zZm.append(", dialogRequestIdentifier=");
        zZm.append(this.zyO);
        zZm.append(", dialogExtras=");
        return C0179Pya.BIo(zZm, this.jiA, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
