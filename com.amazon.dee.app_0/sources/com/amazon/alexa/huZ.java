package com.amazon.alexa;

import com.amazon.alexa.VZt;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ClientMetricData.java */
/* loaded from: classes.dex */
public final class huZ extends VZt {
    public final String BIo;
    public final String JTe;
    public final String LPk;
    public final Long Mlj;
    public final String Qle;
    public final String jiA;
    public final String yPL;
    public final String zQM;
    public final long zyO;
    public final String zzR;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AutoValue_ClientMetricData.java */
    /* loaded from: classes.dex */
    public static final class zZm extends VZt.zZm {
        public String BIo;
        public String JTe;
        public String LPk;
        public String Mlj;
        public String Qle;
        public String jiA;
        public Long yPL;
        public Long zQM;
        public String zZm;
        public String zyO;

        @Override // com.amazon.alexa.VZt.zZm
        public VZt.zZm BIo(String str) {
            if (str != null) {
                this.jiA = str;
                return this;
            }
            throw new NullPointerException("Null clientPackageName");
        }

        @Override // com.amazon.alexa.VZt.zZm
        public VZt.zZm jiA(String str) {
            if (str != null) {
                this.zyO = str;
                return this;
            }
            throw new NullPointerException("Null sourcePackageName");
        }

        @Override // com.amazon.alexa.VZt.zZm
        public VZt.zZm zQM(String str) {
            if (str != null) {
                this.BIo = str;
                return this;
            }
            throw new NullPointerException("Null eventId");
        }

        @Override // com.amazon.alexa.VZt.zZm
        public VZt.zZm zZm(long j) {
            this.zQM = Long.valueOf(j);
            return this;
        }

        @Override // com.amazon.alexa.VZt.zZm
        public VZt.zZm zyO(String str) {
            if (str != null) {
                this.Qle = str;
                return this;
            }
            throw new NullPointerException("Null softwareVersion");
        }

        @Override // com.amazon.alexa.VZt.zZm
        public VZt.zZm zZm(String str) {
            if (str != null) {
                this.Mlj = str;
                return this;
            }
            throw new NullPointerException("Null apiCallId");
        }
    }

    public /* synthetic */ huZ(String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, Long l, String str8, hSF hsf) {
        this.BIo = str;
        this.zQM = str2;
        this.zyO = j;
        this.jiA = str3;
        this.Qle = str4;
        this.JTe = str5;
        this.LPk = str6;
        this.yPL = str7;
        this.Mlj = l;
        this.zzR = str8;
    }

    public boolean equals(Object obj) {
        String str;
        String str2;
        Long l;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof VZt)) {
            return false;
        }
        huZ huz = (huZ) obj;
        return this.BIo.equals(huz.BIo) && this.zQM.equals(huz.zQM) && this.zyO == huz.zyO && this.jiA.equals(huz.jiA) && this.Qle.equals(huz.Qle) && this.JTe.equals(huz.JTe) && ((str = this.LPk) != null ? str.equals(huz.LPk) : huz.LPk == null) && ((str2 = this.yPL) != null ? str2.equals(huz.yPL) : huz.yPL == null) && ((l = this.Mlj) != null ? l.equals(huz.Mlj) : huz.Mlj == null) && this.zzR.equals(huz.zzR);
    }

    public int hashCode() {
        long j = this.zyO;
        int hashCode = (((((((((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.jiA.hashCode()) * 1000003) ^ this.Qle.hashCode()) * 1000003) ^ this.JTe.hashCode()) * 1000003;
        String str = this.LPk;
        int i = 0;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.yPL;
        int hashCode3 = (hashCode2 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        Long l = this.Mlj;
        if (l != null) {
            i = l.hashCode();
        }
        return ((hashCode3 ^ i) * 1000003) ^ this.zzR.hashCode();
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("ClientMetricData{eventName=");
        zZm2.append(this.BIo);
        zZm2.append(", eventId=");
        zZm2.append(this.zQM);
        zZm2.append(", eventTimestampMs=");
        zZm2.append(this.zyO);
        zZm2.append(", sourcePackageName=");
        zZm2.append(this.jiA);
        zZm2.append(", clientPackageName=");
        zZm2.append(this.Qle);
        zZm2.append(", softwareVersion=");
        zZm2.append(this.JTe);
        zZm2.append(", invocationType=");
        zZm2.append(this.LPk);
        zZm2.append(", dialogTurnId=");
        zZm2.append(this.yPL);
        zZm2.append(", latencyMs=");
        zZm2.append(this.Mlj);
        zZm2.append(", apiCallId=");
        return C0179Pya.zZm(zZm2, this.zzR, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
