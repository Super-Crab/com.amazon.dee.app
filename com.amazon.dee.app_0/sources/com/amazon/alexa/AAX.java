package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import javax.annotation.Nullable;
/* compiled from: $AutoValue_Target.java */
/* loaded from: classes.dex */
public abstract class AAX extends pUc {
    public final PMk BIo;
    public final GhS JTe;
    public final String Qle;
    public final String jiA;
    public final EnumC0198ddd zQM;
    public final SZm zZm;
    public final String zyO;

    public AAX(SZm sZm, PMk pMk, EnumC0198ddd enumC0198ddd, String str, String str2, @Nullable String str3, @Nullable GhS ghS) {
        if (sZm != null) {
            this.zZm = sZm;
            if (pMk != null) {
                this.BIo = pMk;
                if (enumC0198ddd != null) {
                    this.zQM = enumC0198ddd;
                    if (str != null) {
                        this.zyO = str;
                        if (str2 != null) {
                            this.jiA = str2;
                            this.Qle = str3;
                            this.JTe = ghS;
                            return;
                        }
                        throw new NullPointerException("Null name");
                    }
                    throw new NullPointerException("Null token");
                }
                throw new NullPointerException("Null identifierType");
            }
            throw new NullPointerException("Null identifier");
        }
        throw new NullPointerException("Null catalogType");
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof pUc)) {
            return false;
        }
        AAX aax = (AAX) obj;
        if (this.zZm.equals(aax.zZm) && this.BIo.equals(aax.BIo) && this.zQM.equals(aax.zQM) && this.zyO.equals(aax.zyO) && this.jiA.equals(aax.jiA) && ((str = this.Qle) != null ? str.equals(aax.Qle) : aax.Qle == null)) {
            GhS ghS = this.JTe;
            if (ghS == null) {
                if (aax.JTe == null) {
                    return true;
                }
            } else if (ghS.equals(aax.JTe)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((((((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode()) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode()) * 1000003;
        String str = this.Qle;
        int i = 0;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        GhS ghS = this.JTe;
        if (ghS != null) {
            i = ghS.hashCode();
        }
        return hashCode2 ^ i;
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("Target{catalogType=");
        zZm.append(this.zZm);
        zZm.append(", identifier=");
        zZm.append(this.BIo);
        zZm.append(", identifierType=");
        zZm.append(this.zQM);
        zZm.append(", token=");
        zZm.append(this.zyO);
        zZm.append(", name=");
        zZm.append(this.jiA);
        zZm.append(", catalogId=");
        zZm.append(this.Qle);
        zZm.append(", launchConfig=");
        return C0179Pya.BIo(zZm, this.JTe, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
