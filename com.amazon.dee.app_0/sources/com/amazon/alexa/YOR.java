package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.Date;
/* compiled from: $AutoValue_AlexaLocation.java */
/* loaded from: classes.dex */
public abstract class YOR extends DRc {
    public final hYy BIo;
    public final rfd jiA;
    public final Gkq zQM;
    public final Date zZm;
    public final wze zyO;

    public YOR(Date date, hYy hyy, @Nullable Gkq gkq, @Nullable wze wzeVar, @Nullable rfd rfdVar) {
        if (date != null) {
            this.zZm = date;
            if (hyy != null) {
                this.BIo = hyy;
                this.zQM = gkq;
                this.zyO = wzeVar;
                this.jiA = rfdVar;
                return;
            }
            throw new NullPointerException("Null coordinate");
        }
        throw new NullPointerException("Null timestamp");
    }

    public boolean equals(Object obj) {
        Gkq gkq;
        wze wzeVar;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DRc)) {
            return false;
        }
        YOR yor = (YOR) obj;
        if (this.zZm.equals(yor.zZm) && this.BIo.equals(yor.BIo) && ((gkq = this.zQM) != null ? gkq.equals(yor.zQM) : yor.zQM == null) && ((wzeVar = this.zyO) != null ? wzeVar.equals(yor.zyO) : yor.zyO == null)) {
            rfd rfdVar = this.jiA;
            if (rfdVar == null) {
                if (yor.jiA == null) {
                    return true;
                }
            } else if (rfdVar.equals(yor.jiA)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode()) * 1000003;
        Gkq gkq = this.zQM;
        int i = 0;
        int hashCode2 = (hashCode ^ (gkq == null ? 0 : gkq.hashCode())) * 1000003;
        wze wzeVar = this.zyO;
        int hashCode3 = (hashCode2 ^ (wzeVar == null ? 0 : wzeVar.hashCode())) * 1000003;
        rfd rfdVar = this.jiA;
        if (rfdVar != null) {
            i = rfdVar.hashCode();
        }
        return hashCode3 ^ i;
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("AlexaLocation{timestamp=");
        zZm.append(this.zZm);
        zZm.append(", coordinate=");
        zZm.append(this.BIo);
        zZm.append(", altitude=");
        zZm.append(this.zQM);
        zZm.append(", heading=");
        zZm.append(this.zyO);
        zZm.append(", speed=");
        return C0179Pya.BIo(zZm, this.jiA, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
