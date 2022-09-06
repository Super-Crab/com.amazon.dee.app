package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import javax.annotation.Nullable;
/* compiled from: $AutoValue_SessionState.java */
/* loaded from: classes.dex */
public abstract class CMx extends PcE {
    public final String BIo;
    public final String jiA;
    public final long zQM;
    public final String zZm;
    public final String zyO;

    public CMx(String str, @Nullable String str2, long j, String str3, String str4) {
        if (str != null) {
            this.zZm = str;
            this.BIo = str2;
            this.zQM = j;
            if (str3 != null) {
                this.zyO = str3;
                if (str4 != null) {
                    this.jiA = str4;
                    return;
                }
                throw new NullPointerException("Null deviceSerialNumber");
            }
            throw new NullPointerException("Null deviceType");
        }
        throw new NullPointerException("Null sessionStartTime");
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PcE)) {
            return false;
        }
        CMx cMx = (CMx) obj;
        return this.zZm.equals(cMx.zZm) && ((str = this.BIo) != null ? str.equals(cMx.BIo) : cMx.BIo == null) && this.zQM == cMx.zQM && this.zyO.equals(cMx.zyO) && this.jiA.equals(cMx.jiA);
    }

    public int hashCode() {
        int hashCode = (this.zZm.hashCode() ^ 1000003) * 1000003;
        String str = this.BIo;
        int hashCode2 = str == null ? 0 : str.hashCode();
        long j = this.zQM;
        return ((((((hashCode ^ hashCode2) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("SessionState{sessionStartTime=");
        zZm.append(this.zZm);
        zZm.append(", trustSessionStartTime=");
        zZm.append(this.BIo);
        zZm.append(", longestTimeUntrustedInMilliseconds=");
        zZm.append(this.zQM);
        zZm.append(", deviceType=");
        zZm.append(this.zyO);
        zZm.append(", deviceSerialNumber=");
        return C0179Pya.zZm(zZm, this.jiA, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
