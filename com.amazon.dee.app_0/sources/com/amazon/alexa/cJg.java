package com.amazon.alexa;

import android.net.Uri;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.AutoValue_CoverUrls;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.zpo;
/* compiled from: $AutoValue_CoverUrls.java */
/* loaded from: classes.dex */
public abstract class cJg extends zpo {
    public final Uri BIo;
    public final Uri jiA;
    public final Uri zQM;
    public final Uri zZm;
    public final Uri zyO;

    public cJg(Uri uri, Uri uri2, Uri uri3, Uri uri4, Uri uri5) {
        if (uri != null) {
            this.zZm = uri;
            if (uri2 != null) {
                this.BIo = uri2;
                if (uri3 != null) {
                    this.zQM = uri3;
                    if (uri4 != null) {
                        this.zyO = uri4;
                        if (uri5 != null) {
                            this.jiA = uri5;
                            return;
                        }
                        throw new NullPointerException("Null full");
                    }
                    throw new NullPointerException("Null large");
                }
                throw new NullPointerException("Null medium");
            }
            throw new NullPointerException("Null small");
        }
        throw new NullPointerException("Null tiny");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zpo)) {
            return false;
        }
        cJg cjg = (cJg) obj;
        return this.zZm.equals(cjg.zZm) && this.BIo.equals(cjg.BIo) && this.zQM.equals(cjg.zQM) && this.zyO.equals(cjg.zyO) && this.jiA.equals(cjg.jiA);
    }

    public int hashCode() {
        return ((((((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode()) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode();
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("CoverUrls{tiny=");
        zZm2.append(this.zZm);
        zZm2.append(", small=");
        zZm2.append(this.BIo);
        zZm2.append(", medium=");
        zZm2.append(this.zQM);
        zZm2.append(", large=");
        zZm2.append(this.zyO);
        zZm2.append(", full=");
        return C0179Pya.BIo(zZm2, this.jiA, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    /* compiled from: $AutoValue_CoverUrls.java */
    /* loaded from: classes.dex */
    static final class zZm extends zpo.zZm {
        public Uri BIo;
        public Uri jiA;
        public Uri zQM;
        public Uri zZm;
        public Uri zyO;

        @Override // com.amazon.alexa.zpo.zZm
        public zpo.zZm BIo(Uri uri) {
            if (uri != null) {
                this.zQM = uri;
                return this;
            }
            throw new NullPointerException("Null medium");
        }

        @Override // com.amazon.alexa.zpo.zZm
        public zpo.zZm zQM(Uri uri) {
            if (uri != null) {
                this.BIo = uri;
                return this;
            }
            throw new NullPointerException("Null small");
        }

        @Override // com.amazon.alexa.zpo.zZm
        public zpo.zZm zZm(Uri uri) {
            if (uri != null) {
                this.zyO = uri;
                return this;
            }
            throw new NullPointerException("Null large");
        }

        @Override // com.amazon.alexa.zpo.zZm
        public zpo.zZm zyO(Uri uri) {
            if (uri != null) {
                this.zZm = uri;
                return this;
            }
            throw new NullPointerException("Null tiny");
        }

        @Override // com.amazon.alexa.zpo.zZm
        public zpo zZm() {
            String str = "";
            if (this.zZm == null) {
                str = C0179Pya.zZm(str, " tiny");
            }
            if (this.BIo == null) {
                str = C0179Pya.zZm(str, " small");
            }
            if (this.zQM == null) {
                str = C0179Pya.zZm(str, " medium");
            }
            if (this.zyO == null) {
                str = C0179Pya.zZm(str, " large");
            }
            if (this.jiA == null) {
                str = C0179Pya.zZm(str, " full");
            }
            if (str.isEmpty()) {
                return new AutoValue_CoverUrls(this.zZm, this.BIo, this.zQM, this.zyO, this.jiA);
            }
            throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str));
        }
    }
}
