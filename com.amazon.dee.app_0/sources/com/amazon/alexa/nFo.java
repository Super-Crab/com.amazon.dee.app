package com.amazon.alexa;

import com.amazon.alexa.WSC;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_DialogInteractionState.java */
/* loaded from: classes.dex */
public final class nFo extends WSC {
    public final long JTe;
    public final YOj LPk;
    public final String Qle;
    public final String jiA;
    public final XWx zQM;
    public final DialogRequestIdentifier zyO;

    public /* synthetic */ nFo(XWx xWx, DialogRequestIdentifier dialogRequestIdentifier, String str, String str2, long j, YOj yOj, iEF ief) {
        this.zQM = xWx;
        this.zyO = dialogRequestIdentifier;
        this.jiA = str;
        this.Qle = str2;
        this.JTe = j;
        this.LPk = yOj;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WSC)) {
            return false;
        }
        XWx xWx = this.zQM;
        if (xWx != null ? xWx.equals(((nFo) obj).zQM) : ((nFo) obj).zQM == null) {
            DialogRequestIdentifier dialogRequestIdentifier = this.zyO;
            if (dialogRequestIdentifier != null ? dialogRequestIdentifier.equals(((nFo) obj).zyO) : ((nFo) obj).zyO == null) {
                String str = this.jiA;
                if (str != null ? str.equals(((nFo) obj).jiA) : ((nFo) obj).jiA == null) {
                    nFo nfo = (nFo) obj;
                    if (this.Qle.equals(nfo.Qle) && this.JTe == nfo.JTe && this.LPk.equals(nfo.LPk)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        XWx xWx = this.zQM;
        int i = 0;
        int hashCode = ((xWx == null ? 0 : xWx.hashCode()) ^ 1000003) * 1000003;
        DialogRequestIdentifier dialogRequestIdentifier = this.zyO;
        int hashCode2 = (hashCode ^ (dialogRequestIdentifier == null ? 0 : dialogRequestIdentifier.hashCode())) * 1000003;
        String str = this.jiA;
        if (str != null) {
            i = str.hashCode();
        }
        long j = this.JTe;
        return ((((((hashCode2 ^ i) * 1000003) ^ this.Qle.hashCode()) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.LPk.hashCode();
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("DialogInteractionState{dialogTurnId=");
        zZm2.append(this.zQM);
        zZm2.append(", dialogRequestId=");
        zZm2.append(this.zyO);
        zZm2.append(", invocationType=");
        zZm2.append(this.jiA);
        zZm2.append(", softwareVersion=");
        zZm2.append(this.Qle);
        zZm2.append(", attemptStartedTime=");
        zZm2.append(this.JTe);
        zZm2.append(", progress=");
        return C0179Pya.BIo(zZm2, this.LPk, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AutoValue_DialogInteractionState.java */
    /* loaded from: classes.dex */
    public static final class zZm extends WSC.zZm {
        public DialogRequestIdentifier BIo;
        public YOj Qle;
        public Long jiA;
        public String zQM;
        public XWx zZm;
        public String zyO;

        @Override // com.amazon.alexa.WSC.zZm
        public WSC.zZm zZm(String str) {
            if (str != null) {
                this.zyO = str;
                return this;
            }
            throw new NullPointerException("Null softwareVersion");
        }

        @Override // com.amazon.alexa.WSC.zZm
        public WSC.zZm zZm(long j) {
            this.jiA = Long.valueOf(j);
            return this;
        }

        @Override // com.amazon.alexa.WSC.zZm
        public WSC.zZm zZm(YOj yOj) {
            if (yOj != null) {
                this.Qle = yOj;
                return this;
            }
            throw new NullPointerException("Null progress");
        }

        @Override // com.amazon.alexa.WSC.zZm
        public WSC zZm() {
            String str = "";
            if (this.zyO == null) {
                str = C0179Pya.zZm(str, " softwareVersion");
            }
            if (this.jiA == null) {
                str = C0179Pya.zZm(str, " attemptStartedTime");
            }
            if (this.Qle == null) {
                str = C0179Pya.zZm(str, " progress");
            }
            if (str.isEmpty()) {
                return new nFo(this.zZm, this.BIo, this.zQM, this.zyO, this.jiA.longValue(), this.Qle, null);
            }
            throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str));
        }
    }
}
