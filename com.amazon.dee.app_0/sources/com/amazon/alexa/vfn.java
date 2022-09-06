package com.amazon.alexa;

import com.amazon.alexa.WlR;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.AutoValue_Value;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_Value.java */
/* loaded from: classes.dex */
public abstract class vfn extends WlR {
    public final String BIo;
    public final long HvC;
    public final String JTe;
    public final String LPk;
    public final String Mlj;
    public final String Qle;
    public final String dMe;
    public final String jiA;
    public final String lOf;
    public final Wea uzr;
    public final String yPL;
    public final String zQM;
    public final String zyO;
    public final zpo zzR;

    public vfn(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, zpo zpoVar, String str10, String str11, Wea wea, long j) {
        if (str != null) {
            this.BIo = str;
            if (str2 != null) {
                this.zQM = str2;
                if (str3 != null) {
                    this.zyO = str3;
                    if (str4 != null) {
                        this.jiA = str4;
                        if (str5 != null) {
                            this.Qle = str5;
                            if (str6 != null) {
                                this.JTe = str6;
                                if (str7 != null) {
                                    this.LPk = str7;
                                    if (str8 != null) {
                                        this.yPL = str8;
                                        if (str9 != null) {
                                            this.Mlj = str9;
                                            if (zpoVar != null) {
                                                this.zzR = zpoVar;
                                                if (str10 != null) {
                                                    this.lOf = str10;
                                                    if (str11 != null) {
                                                        this.dMe = str11;
                                                        if (wea != null) {
                                                            this.uzr = wea;
                                                            this.HvC = j;
                                                            return;
                                                        }
                                                        throw new NullPointerException("Null mediaType");
                                                    }
                                                    throw new NullPointerException("Null mediaProvider");
                                                }
                                                throw new NullPointerException("Null coverId");
                                            }
                                            throw new NullPointerException("Null coverUrls");
                                        }
                                        throw new NullPointerException("Null albumId");
                                    }
                                    throw new NullPointerException("Null album");
                                }
                                throw new NullPointerException("Null artistId");
                            }
                            throw new NullPointerException("Null artist");
                        }
                        throw new NullPointerException("Null trackNumber");
                    }
                    throw new NullPointerException("Null trackId");
                }
                throw new NullPointerException("Null trackName");
            }
            throw new NullPointerException("Null playbackSourceId");
        }
        throw new NullPointerException("Null playbackSource");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WlR)) {
            return false;
        }
        vfn vfnVar = (vfn) obj;
        return this.BIo.equals(vfnVar.BIo) && this.zQM.equals(vfnVar.zQM) && this.zyO.equals(vfnVar.zyO) && this.jiA.equals(vfnVar.jiA) && this.Qle.equals(vfnVar.Qle) && this.JTe.equals(vfnVar.JTe) && this.LPk.equals(vfnVar.LPk) && this.yPL.equals(vfnVar.yPL) && this.Mlj.equals(vfnVar.Mlj) && this.zzR.equals(vfnVar.zzR) && this.lOf.equals(vfnVar.lOf) && this.dMe.equals(vfnVar.dMe) && this.uzr.equals(vfnVar.uzr) && this.HvC == vfnVar.HvC;
    }

    public int hashCode() {
        long j = this.HvC;
        return ((((((((((((((((((((((((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode()) * 1000003) ^ this.Qle.hashCode()) * 1000003) ^ this.JTe.hashCode()) * 1000003) ^ this.LPk.hashCode()) * 1000003) ^ this.yPL.hashCode()) * 1000003) ^ this.Mlj.hashCode()) * 1000003) ^ this.zzR.hashCode()) * 1000003) ^ this.lOf.hashCode()) * 1000003) ^ this.dMe.hashCode()) * 1000003) ^ this.uzr.hashCode()) * 1000003) ^ ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("Value{playbackSource=");
        zZm2.append(this.BIo);
        zZm2.append(", playbackSourceId=");
        zZm2.append(this.zQM);
        zZm2.append(", trackName=");
        zZm2.append(this.zyO);
        zZm2.append(", trackId=");
        zZm2.append(this.jiA);
        zZm2.append(", trackNumber=");
        zZm2.append(this.Qle);
        zZm2.append(", artist=");
        zZm2.append(this.JTe);
        zZm2.append(", artistId=");
        zZm2.append(this.LPk);
        zZm2.append(", album=");
        zZm2.append(this.yPL);
        zZm2.append(", albumId=");
        zZm2.append(this.Mlj);
        zZm2.append(", coverUrls=");
        zZm2.append(this.zzR);
        zZm2.append(", coverId=");
        zZm2.append(this.lOf);
        zZm2.append(", mediaProvider=");
        zZm2.append(this.dMe);
        zZm2.append(", mediaType=");
        zZm2.append(this.uzr);
        zZm2.append(", durationInMilliseconds=");
        return C0179Pya.zZm(zZm2, this.HvC, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    /* compiled from: $AutoValue_Value.java */
    /* loaded from: classes.dex */
    static final class zZm extends WlR.zZm {
        public String BIo;
        public String JTe;
        public String LPk;
        public zpo Mlj;
        public String Qle;
        public Wea dMe;
        public String jiA;
        public String lOf;
        public Long uzr;
        public String yPL;
        public String zQM;
        public String zZm;
        public String zyO;
        public String zzR;

        @Override // com.amazon.alexa.WlR.zZm
        public WlR.zZm BIo(String str) {
            if (str != null) {
                this.yPL = str;
                return this;
            }
            throw new NullPointerException("Null albumId");
        }

        @Override // com.amazon.alexa.WlR.zZm
        public WlR.zZm JTe(String str) {
            if (str != null) {
                this.zZm = str;
                return this;
            }
            throw new NullPointerException("Null playbackSource");
        }

        @Override // com.amazon.alexa.WlR.zZm
        public WlR.zZm LPk(String str) {
            if (str != null) {
                this.BIo = str;
                return this;
            }
            throw new NullPointerException("Null playbackSourceId");
        }

        @Override // com.amazon.alexa.WlR.zZm
        public WlR.zZm Mlj(String str) {
            if (str != null) {
                this.zQM = str;
                return this;
            }
            throw new NullPointerException("Null trackName");
        }

        @Override // com.amazon.alexa.WlR.zZm
        public WlR.zZm Qle(String str) {
            if (str != null) {
                this.lOf = str;
                return this;
            }
            throw new NullPointerException("Null mediaProvider");
        }

        @Override // com.amazon.alexa.WlR.zZm
        public WlR.zZm jiA(String str) {
            if (str != null) {
                this.zzR = str;
                return this;
            }
            throw new NullPointerException("Null coverId");
        }

        @Override // com.amazon.alexa.WlR.zZm
        public WlR.zZm yPL(String str) {
            if (str != null) {
                this.zyO = str;
                return this;
            }
            throw new NullPointerException("Null trackId");
        }

        @Override // com.amazon.alexa.WlR.zZm
        public WlR.zZm zQM(String str) {
            if (str != null) {
                this.Qle = str;
                return this;
            }
            throw new NullPointerException("Null artist");
        }

        @Override // com.amazon.alexa.WlR.zZm
        public WlR.zZm zZm(String str) {
            if (str != null) {
                this.LPk = str;
                return this;
            }
            throw new NullPointerException("Null album");
        }

        @Override // com.amazon.alexa.WlR.zZm
        public WlR.zZm zyO(String str) {
            if (str != null) {
                this.JTe = str;
                return this;
            }
            throw new NullPointerException("Null artistId");
        }

        @Override // com.amazon.alexa.WlR.zZm
        public WlR.zZm zzR(String str) {
            if (str != null) {
                this.jiA = str;
                return this;
            }
            throw new NullPointerException("Null trackNumber");
        }

        @Override // com.amazon.alexa.WlR.zZm
        public WlR.zZm zZm(zpo zpoVar) {
            if (zpoVar != null) {
                this.Mlj = zpoVar;
                return this;
            }
            throw new NullPointerException("Null coverUrls");
        }

        @Override // com.amazon.alexa.WlR.zZm
        public WlR.zZm zZm(Wea wea) {
            if (wea != null) {
                this.dMe = wea;
                return this;
            }
            throw new NullPointerException("Null mediaType");
        }

        @Override // com.amazon.alexa.WlR.zZm
        public WlR.zZm zZm(long j) {
            this.uzr = Long.valueOf(j);
            return this;
        }

        @Override // com.amazon.alexa.WlR.zZm
        public WlR zZm() {
            String str = "";
            if (this.zZm == null) {
                str = C0179Pya.zZm(str, " playbackSource");
            }
            if (this.BIo == null) {
                str = C0179Pya.zZm(str, " playbackSourceId");
            }
            if (this.zQM == null) {
                str = C0179Pya.zZm(str, " trackName");
            }
            if (this.zyO == null) {
                str = C0179Pya.zZm(str, " trackId");
            }
            if (this.jiA == null) {
                str = C0179Pya.zZm(str, " trackNumber");
            }
            if (this.Qle == null) {
                str = C0179Pya.zZm(str, " artist");
            }
            if (this.JTe == null) {
                str = C0179Pya.zZm(str, " artistId");
            }
            if (this.LPk == null) {
                str = C0179Pya.zZm(str, " album");
            }
            if (this.yPL == null) {
                str = C0179Pya.zZm(str, " albumId");
            }
            if (this.Mlj == null) {
                str = C0179Pya.zZm(str, " coverUrls");
            }
            if (this.zzR == null) {
                str = C0179Pya.zZm(str, " coverId");
            }
            if (this.lOf == null) {
                str = C0179Pya.zZm(str, " mediaProvider");
            }
            if (this.dMe == null) {
                str = C0179Pya.zZm(str, " mediaType");
            }
            if (this.uzr == null) {
                str = C0179Pya.zZm(str, " durationInMilliseconds");
            }
            if (str.isEmpty()) {
                return new AutoValue_Value(this.zZm, this.BIo, this.zQM, this.zyO, this.jiA, this.Qle, this.JTe, this.LPk, this.yPL, this.Mlj, this.zzR, this.lOf, this.dMe, this.uzr.longValue());
            }
            throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str));
        }
    }
}
