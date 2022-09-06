package com.amazon.alexa;

import com.amazon.alexa.Cta;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_LegacyFlags.java */
/* loaded from: classes.dex */
public final class zoO extends Cta {
    public final boolean BIo;
    public final boolean HvC;
    public final boolean JTe;
    public final boolean LPk;
    public final boolean Mlj;
    public final String Qgh;
    public final boolean Qle;
    public final String Tbw;
    public final String XWf;
    public final boolean dMe;
    public final boolean jiA;
    public final boolean lOf;
    public final int noQ;
    public final boolean uzr;
    public final boolean vkx;
    public final boolean wDP;
    public final boolean yPL;
    public final boolean zQM;
    public final boolean zZm;
    public final boolean zyO;
    public final boolean zzR;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AutoValue_LegacyFlags.java */
    /* loaded from: classes.dex */
    public static final class zZm extends Cta.zZm {
        public Boolean BIo;
        public Boolean HvC;
        public Boolean JTe;
        public Boolean LPk;
        public Boolean Mlj;
        public String Qgh;
        public Boolean Qle;
        public String Tbw;
        public String XWf;
        public Boolean dMe;
        public Boolean jiA;
        public Boolean lOf;
        public Integer noQ;
        public Boolean uzr;
        public Boolean vkx;
        public Boolean wDP;
        public Boolean yPL;
        public Boolean zQM;
        public Boolean zZm;
        public Boolean zyO;
        public Boolean zzR;

        @Override // com.amazon.alexa.Cta.zZm
        public Cta.zZm BIo(boolean z) {
            this.zQM = Boolean.valueOf(z);
            return this;
        }

        @Override // com.amazon.alexa.Cta.zZm
        public Cta.zZm HvC(boolean z) {
            this.dMe = Boolean.valueOf(z);
            return this;
        }

        @Override // com.amazon.alexa.Cta.zZm
        public Cta.zZm JTe(boolean z) {
            this.LPk = Boolean.valueOf(z);
            return this;
        }

        @Override // com.amazon.alexa.Cta.zZm
        public Cta.zZm LPk(boolean z) {
            this.zyO = Boolean.valueOf(z);
            return this;
        }

        @Override // com.amazon.alexa.Cta.zZm
        public Cta.zZm Mlj(boolean z) {
            this.jiA = Boolean.valueOf(z);
            return this;
        }

        @Override // com.amazon.alexa.Cta.zZm
        public Cta.zZm Qle(boolean z) {
            this.JTe = Boolean.valueOf(z);
            return this;
        }

        @Override // com.amazon.alexa.Cta.zZm
        public Cta.zZm dMe(boolean z) {
            this.lOf = Boolean.valueOf(z);
            return this;
        }

        @Override // com.amazon.alexa.Cta.zZm
        public Cta.zZm jiA(boolean z) {
            this.zzR = Boolean.valueOf(z);
            return this;
        }

        @Override // com.amazon.alexa.Cta.zZm
        public Cta.zZm lOf(boolean z) {
            this.vkx = Boolean.valueOf(z);
            return this;
        }

        @Override // com.amazon.alexa.Cta.zZm
        public Cta.zZm uzr(boolean z) {
            this.BIo = Boolean.valueOf(z);
            return this;
        }

        @Override // com.amazon.alexa.Cta.zZm
        public Cta.zZm vkx(boolean z) {
            this.uzr = Boolean.valueOf(z);
            return this;
        }

        @Override // com.amazon.alexa.Cta.zZm
        public Cta.zZm yPL(boolean z) {
            this.yPL = Boolean.valueOf(z);
            return this;
        }

        @Override // com.amazon.alexa.Cta.zZm
        public Cta.zZm zQM(boolean z) {
            this.wDP = Boolean.valueOf(z);
            return this;
        }

        @Override // com.amazon.alexa.Cta.zZm
        public Cta.zZm zZm(boolean z) {
            this.HvC = Boolean.valueOf(z);
            return this;
        }

        @Override // com.amazon.alexa.Cta.zZm
        public Cta.zZm zyO(boolean z) {
            this.Mlj = Boolean.valueOf(z);
            return this;
        }

        @Override // com.amazon.alexa.Cta.zZm
        public Cta.zZm zzR(boolean z) {
            this.Qle = Boolean.valueOf(z);
            return this;
        }

        @Override // com.amazon.alexa.Cta.zZm
        public Cta.zZm BIo(String str) {
            if (str != null) {
                this.Tbw = str;
                return this;
            }
            throw new NullPointerException("Null supportsDatamartNamespace");
        }

        @Override // com.amazon.alexa.Cta.zZm
        public Cta.zZm zQM(String str) {
            if (str != null) {
                this.XWf = str;
                return this;
            }
            throw new NullPointerException("Null supportsTargetPlatform");
        }

        @Override // com.amazon.alexa.Cta.zZm
        public Cta.zZm zZm(int i) {
            this.noQ = Integer.valueOf(i);
            return this;
        }

        @Override // com.amazon.alexa.Cta.zZm
        public Cta.zZm zZm(String str) {
            if (str != null) {
                this.Qgh = str;
                return this;
            }
            throw new NullPointerException("Null friendlyNameTemplate");
        }

        @Override // com.amazon.alexa.Cta.zZm
        public Cta zZm() {
            String str = "";
            if (this.zZm == null) {
                str = C0179Pya.zZm(str, " supportsTtsSpeechmarks");
            }
            if (this.BIo == null) {
                str = C0179Pya.zZm(str, " supportsSpeechSynthesizerTtsUrls");
            }
            if (this.zQM == null) {
                str = C0179Pya.zZm(str, " supportsAudioPlayerTtsUrls");
            }
            if (this.zyO == null) {
                str = C0179Pya.zZm(str, " supportsLyricsInCard");
            }
            if (this.jiA == null) {
                str = C0179Pya.zZm(str, " supportsPfmChanged");
            }
            if (this.Qle == null) {
                str = C0179Pya.zZm(str, " supportsScrubbing");
            }
            if (this.JTe == null) {
                str = C0179Pya.zZm(str, " supportsHomeAutomation");
            }
            if (this.LPk == null) {
                str = C0179Pya.zZm(str, " supportsKeysInHeader");
            }
            if (this.yPL == null) {
                str = C0179Pya.zZm(str, " supportsMixingBehaviorForAudioPlayer");
            }
            if (this.Mlj == null) {
                str = C0179Pya.zZm(str, " supportsComms");
            }
            if (this.zzR == null) {
                str = C0179Pya.zZm(str, " supportsDropinOutbound");
            }
            if (this.lOf == null) {
                str = C0179Pya.zZm(str, " supportsSipOutboundCalling");
            }
            if (this.dMe == null) {
                str = C0179Pya.zZm(str, " supportsVideoCalling");
            }
            if (this.uzr == null) {
                str = C0179Pya.zZm(str, " voiceProfileSwitchingDisabled");
            }
            if (this.HvC == null) {
                str = C0179Pya.zZm(str, " supportsArbitration");
            }
            if (this.vkx == null) {
                str = C0179Pya.zZm(str, " supportsSecureLockscreen");
            }
            if (this.wDP == null) {
                str = C0179Pya.zZm(str, " supportsAxon");
            }
            if (this.noQ == null) {
                str = C0179Pya.zZm(str, " screenWidth");
            }
            if (this.Qgh == null) {
                str = C0179Pya.zZm(str, " friendlyNameTemplate");
            }
            if (this.Tbw == null) {
                str = C0179Pya.zZm(str, " supportsDatamartNamespace");
            }
            if (this.XWf == null) {
                str = C0179Pya.zZm(str, " supportsTargetPlatform");
            }
            if (str.isEmpty()) {
                return new zoO(this.zZm.booleanValue(), this.BIo.booleanValue(), this.zQM.booleanValue(), this.zyO.booleanValue(), this.jiA.booleanValue(), this.Qle.booleanValue(), this.JTe.booleanValue(), this.LPk.booleanValue(), this.yPL.booleanValue(), this.Mlj.booleanValue(), this.zzR.booleanValue(), this.lOf.booleanValue(), this.dMe.booleanValue(), this.uzr.booleanValue(), this.HvC.booleanValue(), this.vkx.booleanValue(), this.wDP.booleanValue(), this.noQ.intValue(), this.Qgh, this.Tbw, this.XWf, null);
            }
            throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str));
        }
    }

    public /* synthetic */ zoO(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, boolean z16, boolean z17, int i, String str, String str2, String str3, khZ khz) {
        this.zZm = z;
        this.BIo = z2;
        this.zQM = z3;
        this.zyO = z4;
        this.jiA = z5;
        this.Qle = z6;
        this.JTe = z7;
        this.LPk = z8;
        this.yPL = z9;
        this.Mlj = z10;
        this.zzR = z11;
        this.lOf = z12;
        this.dMe = z13;
        this.uzr = z14;
        this.HvC = z15;
        this.vkx = z16;
        this.wDP = z17;
        this.noQ = i;
        this.Qgh = str;
        this.Tbw = str2;
        this.XWf = str3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Cta)) {
            return false;
        }
        zoO zoo = (zoO) obj;
        return this.zZm == zoo.zZm && this.BIo == zoo.BIo && this.zQM == zoo.zQM && this.zyO == zoo.zyO && this.jiA == zoo.jiA && this.Qle == zoo.Qle && this.JTe == zoo.JTe && this.LPk == zoo.LPk && this.yPL == zoo.yPL && this.Mlj == zoo.Mlj && this.zzR == zoo.zzR && this.lOf == zoo.lOf && this.dMe == zoo.dMe && this.uzr == zoo.uzr && this.HvC == zoo.HvC && this.vkx == zoo.vkx && this.wDP == zoo.wDP && this.noQ == zoo.noQ && this.Qgh.equals(zoo.Qgh) && this.Tbw.equals(zoo.Tbw) && this.XWf.equals(zoo.XWf);
    }

    public int hashCode() {
        int i = 1231;
        int i2 = ((((((((((((((((((((((((((((((((this.zZm ? 1231 : 1237) ^ 1000003) * 1000003) ^ (this.BIo ? 1231 : 1237)) * 1000003) ^ (this.zQM ? 1231 : 1237)) * 1000003) ^ (this.zyO ? 1231 : 1237)) * 1000003) ^ (this.jiA ? 1231 : 1237)) * 1000003) ^ (this.Qle ? 1231 : 1237)) * 1000003) ^ (this.JTe ? 1231 : 1237)) * 1000003) ^ (this.LPk ? 1231 : 1237)) * 1000003) ^ (this.yPL ? 1231 : 1237)) * 1000003) ^ (this.Mlj ? 1231 : 1237)) * 1000003) ^ (this.zzR ? 1231 : 1237)) * 1000003) ^ (this.lOf ? 1231 : 1237)) * 1000003) ^ (this.dMe ? 1231 : 1237)) * 1000003) ^ (this.uzr ? 1231 : 1237)) * 1000003) ^ (this.HvC ? 1231 : 1237)) * 1000003) ^ (this.vkx ? 1231 : 1237)) * 1000003;
        if (!this.wDP) {
            i = 1237;
        }
        return ((((((((i2 ^ i) * 1000003) ^ this.noQ) * 1000003) ^ this.Qgh.hashCode()) * 1000003) ^ this.Tbw.hashCode()) * 1000003) ^ this.XWf.hashCode();
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("LegacyFlags{supportsTtsSpeechmarks=");
        zZm2.append(this.zZm);
        zZm2.append(", supportsSpeechSynthesizerTtsUrls=");
        zZm2.append(this.BIo);
        zZm2.append(", supportsAudioPlayerTtsUrls=");
        zZm2.append(this.zQM);
        zZm2.append(", supportsLyricsInCard=");
        zZm2.append(this.zyO);
        zZm2.append(", supportsPfmChanged=");
        zZm2.append(this.jiA);
        zZm2.append(", supportsScrubbing=");
        zZm2.append(this.Qle);
        zZm2.append(", supportsHomeAutomation=");
        zZm2.append(this.JTe);
        zZm2.append(", supportsKeysInHeader=");
        zZm2.append(this.LPk);
        zZm2.append(", supportsMixingBehaviorForAudioPlayer=");
        zZm2.append(this.yPL);
        zZm2.append(", supportsComms=");
        zZm2.append(this.Mlj);
        zZm2.append(", supportsDropinOutbound=");
        zZm2.append(this.zzR);
        zZm2.append(", supportsSipOutboundCalling=");
        zZm2.append(this.lOf);
        zZm2.append(", supportsVideoCalling=");
        zZm2.append(this.dMe);
        zZm2.append(", voiceProfileSwitchingDisabled=");
        zZm2.append(this.uzr);
        zZm2.append(", supportsArbitration=");
        zZm2.append(this.HvC);
        zZm2.append(", supportsSecureLockscreen=");
        zZm2.append(this.vkx);
        zZm2.append(", supportsAxon=");
        zZm2.append(this.wDP);
        zZm2.append(", screenWidth=");
        zZm2.append(this.noQ);
        zZm2.append(", friendlyNameTemplate=");
        zZm2.append(this.Qgh);
        zZm2.append(", supportsDatamartNamespace=");
        zZm2.append(this.Tbw);
        zZm2.append(", supportsTargetPlatform=");
        return C0179Pya.zZm(zZm2, this.XWf, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
