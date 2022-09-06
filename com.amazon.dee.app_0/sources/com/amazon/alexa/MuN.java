package com.amazon.alexa;

import android.net.Uri;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_AudioItemMetadataAcquiredEvent.java */
/* loaded from: classes.dex */
public final class MuN extends pPd {
    public final xNT BIo;
    public final Uri JTe;
    public final Long LPk;
    public final Uri Qle;
    public final String jiA;
    public final String zQM;
    public final String zyO;

    public /* synthetic */ MuN(xNT xnt, String str, String str2, String str3, Uri uri, Uri uri2, Long l, ayN ayn) {
        this.BIo = xnt;
        this.zQM = str;
        this.zyO = str2;
        this.jiA = str3;
        this.Qle = uri;
        this.JTe = uri2;
        this.LPk = l;
    }

    public boolean equals(Object obj) {
        String str;
        String str2;
        String str3;
        Uri uri;
        Uri uri2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof pPd)) {
            return false;
        }
        MuN muN = (MuN) obj;
        if (this.BIo.equals(muN.BIo) && ((str = this.zQM) != null ? str.equals(muN.zQM) : muN.zQM == null) && ((str2 = this.zyO) != null ? str2.equals(muN.zyO) : muN.zyO == null) && ((str3 = this.jiA) != null ? str3.equals(muN.jiA) : muN.jiA == null) && ((uri = this.Qle) != null ? uri.equals(muN.Qle) : muN.Qle == null) && ((uri2 = this.JTe) != null ? uri2.equals(muN.JTe) : muN.JTe == null)) {
            Long l = this.LPk;
            if (l == null) {
                if (muN.LPk == null) {
                    return true;
                }
            } else if (l.equals(muN.LPk)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (this.BIo.hashCode() ^ 1000003) * 1000003;
        String str = this.zQM;
        int i = 0;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.zyO;
        int hashCode3 = (hashCode2 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.jiA;
        int hashCode4 = (hashCode3 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        Uri uri = this.Qle;
        int hashCode5 = (hashCode4 ^ (uri == null ? 0 : uri.hashCode())) * 1000003;
        Uri uri2 = this.JTe;
        int hashCode6 = (hashCode5 ^ (uri2 == null ? 0 : uri2.hashCode())) * 1000003;
        Long l = this.LPk;
        if (l != null) {
            i = l.hashCode();
        }
        return hashCode6 ^ i;
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("AudioItemMetadataAcquiredEvent{audioItemIdentifier=");
        zZm.append(this.BIo);
        zZm.append(", title=");
        zZm.append(this.zQM);
        zZm.append(", artist=");
        zZm.append(this.zyO);
        zZm.append(", album=");
        zZm.append(this.jiA);
        zZm.append(", backgroundImageUri=");
        zZm.append(this.Qle);
        zZm.append(", artImageUri=");
        zZm.append(this.JTe);
        zZm.append(", mediaLengthInSeconds=");
        return C0179Pya.BIo(zZm, this.LPk, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
