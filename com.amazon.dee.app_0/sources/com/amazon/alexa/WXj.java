package com.amazon.alexa;

import com.amazon.alexa.JjI;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.Set;
/* compiled from: AutoValue_SendMessageEvent.java */
/* loaded from: classes.dex */
public final class WXj extends JjI {
    public final ExtendedClient BIo;
    public final cIy JTe;
    public final LFH LPk;
    public final ojb Mlj;
    public final Set<ComponentState> Qle;
    public final LFH jiA;
    public final boolean lOf;
    public final TtM yPL;
    public final Message zQM;
    public final cIy zyO;
    public final eOP zzR;

    public /* synthetic */ WXj(ExtendedClient extendedClient, Message message, cIy ciy, LFH lfh, Set set, cIy ciy2, LFH lfh2, TtM ttM, ojb ojbVar, eOP eop, boolean z, dDD ddd) {
        this.BIo = extendedClient;
        this.zQM = message;
        this.zyO = ciy;
        this.jiA = lfh;
        this.Qle = set;
        this.JTe = ciy2;
        this.LPk = lfh2;
        this.yPL = ttM;
        this.Mlj = ojbVar;
        this.zzR = eop;
        this.lOf = z;
    }

    public boolean equals(Object obj) {
        cIy ciy;
        LFH lfh;
        Set<ComponentState> set;
        cIy ciy2;
        LFH lfh2;
        ojb ojbVar;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof JjI)) {
            return false;
        }
        ExtendedClient extendedClient = this.BIo;
        if (extendedClient != null ? extendedClient.equals(((WXj) obj).BIo) : ((WXj) obj).BIo == null) {
            WXj wXj = (WXj) obj;
            if (this.zQM.equals(wXj.zQM) && ((ciy = this.zyO) != null ? ciy.equals(wXj.zyO) : wXj.zyO == null) && ((lfh = this.jiA) != null ? lfh.equals(wXj.jiA) : wXj.jiA == null) && ((set = this.Qle) != null ? set.equals(wXj.Qle) : wXj.Qle == null) && ((ciy2 = this.JTe) != null ? ciy2.equals(wXj.JTe) : wXj.JTe == null) && ((lfh2 = this.LPk) != null ? lfh2.equals(wXj.LPk) : wXj.LPk == null) && this.yPL.equals(wXj.yPL) && ((ojbVar = this.Mlj) != null ? ojbVar.equals(wXj.Mlj) : wXj.Mlj == null) && this.zzR.equals(wXj.zzR) && this.lOf == wXj.lOf) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        ExtendedClient extendedClient = this.BIo;
        int i = 0;
        int hashCode = ((((extendedClient == null ? 0 : extendedClient.hashCode()) ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003;
        cIy ciy = this.zyO;
        int hashCode2 = (hashCode ^ (ciy == null ? 0 : ciy.hashCode())) * 1000003;
        LFH lfh = this.jiA;
        int hashCode3 = (hashCode2 ^ (lfh == null ? 0 : lfh.hashCode())) * 1000003;
        Set<ComponentState> set = this.Qle;
        int hashCode4 = (hashCode3 ^ (set == null ? 0 : set.hashCode())) * 1000003;
        cIy ciy2 = this.JTe;
        int hashCode5 = (hashCode4 ^ (ciy2 == null ? 0 : ciy2.hashCode())) * 1000003;
        LFH lfh2 = this.LPk;
        int hashCode6 = (((hashCode5 ^ (lfh2 == null ? 0 : lfh2.hashCode())) * 1000003) ^ this.yPL.hashCode()) * 1000003;
        ojb ojbVar = this.Mlj;
        if (ojbVar != null) {
            i = ojbVar.hashCode();
        }
        return ((((hashCode6 ^ i) * 1000003) ^ this.zzR.hashCode()) * 1000003) ^ (this.lOf ? 1231 : 1237);
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("SendMessageEvent{client=");
        zZm2.append(this.BIo);
        zZm2.append(", message=");
        zZm2.append(this.zQM);
        zZm2.append(", attachmentIdentifier=");
        zZm2.append(this.zyO);
        zZm2.append(", attachmentWriteCallbacks=");
        zZm2.append(this.jiA);
        zZm2.append(", context=");
        zZm2.append(this.Qle);
        zZm2.append(", dataAttachmentIdentifier=");
        zZm2.append(this.JTe);
        zZm2.append(", dataAttachmentWriteCallbacks=");
        zZm2.append(this.LPk);
        zZm2.append(", sendMessageCallback=");
        zZm2.append(this.yPL);
        zZm2.append(", timeoutsConfiguration=");
        zZm2.append(this.Mlj);
        zZm2.append(", apiCallMetadata=");
        zZm2.append(this.zzR);
        zZm2.append(", guaranteedDelivery=");
        return C0179Pya.zZm(zZm2, this.lOf, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AutoValue_SendMessageEvent.java */
    /* loaded from: classes.dex */
    public static final class zZm extends JjI.zZm {
        public Message BIo;
        public LFH JTe;
        public TtM LPk;
        public eOP Mlj;
        public cIy Qle;
        public Set<ComponentState> jiA;
        public ojb yPL;
        public cIy zQM;
        public ExtendedClient zZm;
        public LFH zyO;
        public Boolean zzR;

        @Override // com.amazon.alexa.JjI.zZm
        public JjI.zZm zZm(Message message) {
            if (message != null) {
                this.BIo = message;
                return this;
            }
            throw new NullPointerException("Null message");
        }

        @Override // com.amazon.alexa.JjI.zZm
        public JjI.zZm zZm(TtM ttM) {
            if (ttM != null) {
                this.LPk = ttM;
                return this;
            }
            throw new NullPointerException("Null sendMessageCallback");
        }

        @Override // com.amazon.alexa.JjI.zZm
        public JjI.zZm zZm(eOP eop) {
            if (eop != null) {
                this.Mlj = eop;
                return this;
            }
            throw new NullPointerException("Null apiCallMetadata");
        }

        @Override // com.amazon.alexa.JjI.zZm
        public JjI.zZm zZm(boolean z) {
            this.zzR = Boolean.valueOf(z);
            return this;
        }
    }
}
