package com.amazon.alexa;

import com.amazon.alexa.HkJ;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.AutoValue_Player;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_Player.java */
/* loaded from: classes.dex */
public abstract class Roh extends HkJ {
    public final String BIo;
    public final boolean JTe;
    public final AbstractC0188bKf LPk;
    public final ZYY Mlj;
    public final boolean Qle;
    public final boolean jiA;
    public final GWl lOf;
    public final zYH yPL;
    public final boolean zQM;
    public final vQe zZm;
    public final String zyO;
    public final Hir zzR;

    public Roh(vQe vqe, String str, boolean z, String str2, boolean z2, boolean z3, boolean z4, AbstractC0188bKf abstractC0188bKf, zYH zyh, ZYY zyy, Hir hir, GWl gWl) {
        if (vqe != null) {
            this.zZm = vqe;
            if (str != null) {
                this.BIo = str;
                this.zQM = z;
                if (str2 != null) {
                    this.zyO = str2;
                    this.jiA = z2;
                    this.Qle = z3;
                    this.JTe = z4;
                    if (abstractC0188bKf != null) {
                        this.LPk = abstractC0188bKf;
                        if (zyh != null) {
                            this.yPL = zyh;
                            if (zyy != null) {
                                this.Mlj = zyy;
                                if (hir != null) {
                                    this.zzR = hir;
                                    if (gWl != null) {
                                        this.lOf = gWl;
                                        return;
                                    }
                                    throw new NullPointerException("Null playbackSessionId");
                                }
                                throw new NullPointerException("Null skillToken");
                            }
                            throw new NullPointerException("Null playerVersion");
                        }
                        throw new NullPointerException("Null playerCookie");
                    }
                    throw new NullPointerException("Null spiVersion");
                }
                throw new NullPointerException("Null username");
            }
            throw new NullPointerException("Null endpointId");
        }
        throw new NullPointerException("Null playerId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HkJ)) {
            return false;
        }
        Roh roh = (Roh) obj;
        return this.zZm.equals(roh.zZm) && this.BIo.equals(roh.BIo) && this.zQM == roh.zQM && this.zyO.equals(roh.zyO) && this.jiA == roh.jiA && this.Qle == roh.Qle && this.JTe == roh.JTe && this.LPk.equals(roh.LPk) && this.yPL.equals(roh.yPL) && this.Mlj.equals(roh.Mlj) && this.zzR.equals(roh.zzR) && this.lOf.equals(roh.lOf);
    }

    public int hashCode() {
        int i = 1231;
        int hashCode = (((((((((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode()) * 1000003) ^ (this.zQM ? 1231 : 1237)) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ (this.jiA ? 1231 : 1237)) * 1000003) ^ (this.Qle ? 1231 : 1237)) * 1000003;
        if (!this.JTe) {
            i = 1237;
        }
        return ((((((((((hashCode ^ i) * 1000003) ^ this.LPk.hashCode()) * 1000003) ^ this.yPL.hashCode()) * 1000003) ^ this.Mlj.hashCode()) * 1000003) ^ this.zzR.hashCode()) * 1000003) ^ this.lOf.hashCode();
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("Player{playerId=");
        zZm2.append(this.zZm);
        zZm2.append(", endpointId=");
        zZm2.append(this.BIo);
        zZm2.append(", loggedIn=");
        zZm2.append(this.zQM);
        zZm2.append(", username=");
        zZm2.append(this.zyO);
        zZm2.append(", isGuest=");
        zZm2.append(this.jiA);
        zZm2.append(", launched=");
        zZm2.append(this.Qle);
        zZm2.append(", active=");
        zZm2.append(this.JTe);
        zZm2.append(", spiVersion=");
        zZm2.append(this.LPk);
        zZm2.append(", playerCookie=");
        zZm2.append(this.yPL);
        zZm2.append(", playerVersion=");
        zZm2.append(this.Mlj);
        zZm2.append(", skillToken=");
        zZm2.append(this.zzR);
        zZm2.append(", playbackSessionId=");
        return C0179Pya.BIo(zZm2, this.lOf, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: $AutoValue_Player.java */
    /* loaded from: classes.dex */
    public static final class zZm extends HkJ.zZm {
        public String BIo;
        public Boolean JTe;
        public AbstractC0188bKf LPk;
        public ZYY Mlj;
        public Boolean Qle;
        public Boolean jiA;
        public GWl lOf;
        public zYH yPL;
        public Boolean zQM;
        public vQe zZm;
        public String zyO;
        public Hir zzR;

        @Override // com.amazon.alexa.HkJ.zZm
        public HkJ.zZm BIo(String str) {
            if (str != null) {
                this.zyO = str;
                return this;
            }
            throw new NullPointerException("Null username");
        }

        @Override // com.amazon.alexa.HkJ.zZm
        public HkJ.zZm zQM(boolean z) {
            this.Qle = Boolean.valueOf(z);
            return this;
        }

        @Override // com.amazon.alexa.HkJ.zZm
        public HkJ.zZm zZm(vQe vqe) {
            if (vqe != null) {
                this.zZm = vqe;
                return this;
            }
            throw new NullPointerException("Null playerId");
        }

        @Override // com.amazon.alexa.HkJ.zZm
        public HkJ.zZm zyO(boolean z) {
            this.zQM = Boolean.valueOf(z);
            return this;
        }

        @Override // com.amazon.alexa.HkJ.zZm
        public HkJ.zZm BIo(boolean z) {
            this.jiA = Boolean.valueOf(z);
            return this;
        }

        @Override // com.amazon.alexa.HkJ.zZm
        public HkJ.zZm zZm(String str) {
            if (str != null) {
                this.BIo = str;
                return this;
            }
            throw new NullPointerException("Null endpointId");
        }

        @Override // com.amazon.alexa.HkJ.zZm
        public HkJ.zZm zZm(boolean z) {
            this.JTe = Boolean.valueOf(z);
            return this;
        }

        @Override // com.amazon.alexa.HkJ.zZm
        public HkJ.zZm zZm(AbstractC0188bKf abstractC0188bKf) {
            if (abstractC0188bKf != null) {
                this.LPk = abstractC0188bKf;
                return this;
            }
            throw new NullPointerException("Null spiVersion");
        }

        @Override // com.amazon.alexa.HkJ.zZm
        public HkJ.zZm zZm(zYH zyh) {
            if (zyh != null) {
                this.yPL = zyh;
                return this;
            }
            throw new NullPointerException("Null playerCookie");
        }

        @Override // com.amazon.alexa.HkJ.zZm
        public HkJ.zZm zZm(ZYY zyy) {
            if (zyy != null) {
                this.Mlj = zyy;
                return this;
            }
            throw new NullPointerException("Null playerVersion");
        }

        @Override // com.amazon.alexa.HkJ.zZm
        public HkJ.zZm zZm(Hir hir) {
            if (hir != null) {
                this.zzR = hir;
                return this;
            }
            throw new NullPointerException("Null skillToken");
        }

        @Override // com.amazon.alexa.HkJ.zZm
        public HkJ.zZm zZm(GWl gWl) {
            if (gWl != null) {
                this.lOf = gWl;
                return this;
            }
            throw new NullPointerException("Null playbackSessionId");
        }

        @Override // com.amazon.alexa.HkJ.zZm
        public HkJ zZm() {
            String str = "";
            if (this.zZm == null) {
                str = C0179Pya.zZm(str, " playerId");
            }
            if (this.BIo == null) {
                str = C0179Pya.zZm(str, " endpointId");
            }
            if (this.zQM == null) {
                str = C0179Pya.zZm(str, " loggedIn");
            }
            if (this.zyO == null) {
                str = C0179Pya.zZm(str, " username");
            }
            if (this.jiA == null) {
                str = C0179Pya.zZm(str, " isGuest");
            }
            if (this.Qle == null) {
                str = C0179Pya.zZm(str, " launched");
            }
            if (this.JTe == null) {
                str = C0179Pya.zZm(str, " active");
            }
            if (this.LPk == null) {
                str = C0179Pya.zZm(str, " spiVersion");
            }
            if (this.yPL == null) {
                str = C0179Pya.zZm(str, " playerCookie");
            }
            if (this.Mlj == null) {
                str = C0179Pya.zZm(str, " playerVersion");
            }
            if (this.zzR == null) {
                str = C0179Pya.zZm(str, " skillToken");
            }
            if (this.lOf == null) {
                str = C0179Pya.zZm(str, " playbackSessionId");
            }
            if (str.isEmpty()) {
                return new AutoValue_Player(this.zZm, this.BIo, this.zQM.booleanValue(), this.zyO, this.jiA.booleanValue(), this.Qle.booleanValue(), this.JTe.booleanValue(), this.LPk, this.yPL, this.Mlj, this.zzR, this.lOf);
            }
            throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str));
        }
    }
}
