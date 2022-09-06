package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.GkO;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.AutoValue_PlaybackStatePayload;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.Set;
/* compiled from: $AutoValue_PlaybackStatePayload.java */
/* loaded from: classes.dex */
public abstract class xxR extends GkO {
    public final Set<rjL> BIo;
    public final MAh JTe;
    public final Set<Kyp> LPk;
    public final XSR Qle;
    public final AKJ jiA;
    public final AbstractC0197ddD zQM;
    public final NdN zZm;
    public final long zyO;

    public xxR(NdN ndN, Set<rjL> set, AbstractC0197ddD abstractC0197ddD, long j, AKJ akj, XSR xsr, MAh mAh, @Nullable Set<Kyp> set2) {
        if (ndN != null) {
            this.zZm = ndN;
            if (set != null) {
                this.BIo = set;
                if (abstractC0197ddD != null) {
                    this.zQM = abstractC0197ddD;
                    this.zyO = j;
                    if (akj != null) {
                        this.jiA = akj;
                        if (xsr != null) {
                            this.Qle = xsr;
                            if (mAh != null) {
                                this.JTe = mAh;
                                this.LPk = set2;
                                return;
                            }
                            throw new NullPointerException("Null favorite");
                        }
                        throw new NullPointerException("Null repeat");
                    }
                    throw new NullPointerException("Null shuffle");
                }
                throw new NullPointerException("Null media");
            }
            throw new NullPointerException("Null supportedOperations");
        }
        throw new NullPointerException("Null state");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GkO)) {
            return false;
        }
        xxR xxr = (xxR) obj;
        if (this.zZm.equals(xxr.zZm) && this.BIo.equals(xxr.BIo) && this.zQM.equals(xxr.zQM) && this.zyO == xxr.zyO && this.jiA.equals(xxr.jiA) && this.Qle.equals(xxr.Qle) && this.JTe.equals(xxr.JTe)) {
            Set<Kyp> set = this.LPk;
            if (set == null) {
                if (xxr.LPk == null) {
                    return true;
                }
            } else if (set.equals(xxr.LPk)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        long j = this.zyO;
        int hashCode = (((((((((((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode()) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.jiA.hashCode()) * 1000003) ^ this.Qle.hashCode()) * 1000003) ^ this.JTe.hashCode()) * 1000003;
        Set<Kyp> set = this.LPk;
        return hashCode ^ (set == null ? 0 : set.hashCode());
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("PlaybackStatePayload{state=");
        zZm2.append(this.zZm);
        zZm2.append(", supportedOperations=");
        zZm2.append(this.BIo);
        zZm2.append(", media=");
        zZm2.append(this.zQM);
        zZm2.append(", positionMilliseconds=");
        zZm2.append(this.zyO);
        zZm2.append(", shuffle=");
        zZm2.append(this.jiA);
        zZm2.append(", repeat=");
        zZm2.append(this.Qle);
        zZm2.append(", favorite=");
        zZm2.append(this.JTe);
        zZm2.append(", players=");
        return C0179Pya.BIo(zZm2, this.LPk, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: $AutoValue_PlaybackStatePayload.java */
    /* loaded from: classes.dex */
    public static final class zZm extends GkO.zZm {
        public Set<rjL> BIo;
        public MAh JTe;
        public Set<Kyp> LPk;
        public XSR Qle;
        public AKJ jiA;
        public AbstractC0197ddD zQM;
        public NdN zZm;
        public Long zyO;

        @Override // com.amazon.alexa.GkO.zZm
        public GkO.zZm BIo(Set<rjL> set) {
            if (set != null) {
                this.BIo = set;
                return this;
            }
            throw new NullPointerException("Null supportedOperations");
        }

        @Override // com.amazon.alexa.GkO.zZm
        public GkO.zZm zZm(NdN ndN) {
            if (ndN != null) {
                this.zZm = ndN;
                return this;
            }
            throw new NullPointerException("Null state");
        }

        @Override // com.amazon.alexa.GkO.zZm
        public GkO.zZm zZm(AbstractC0197ddD abstractC0197ddD) {
            if (abstractC0197ddD != null) {
                this.zQM = abstractC0197ddD;
                return this;
            }
            throw new NullPointerException("Null media");
        }

        @Override // com.amazon.alexa.GkO.zZm
        public GkO.zZm zZm(long j) {
            this.zyO = Long.valueOf(j);
            return this;
        }

        @Override // com.amazon.alexa.GkO.zZm
        public GkO.zZm zZm(AKJ akj) {
            if (akj != null) {
                this.jiA = akj;
                return this;
            }
            throw new NullPointerException("Null shuffle");
        }

        @Override // com.amazon.alexa.GkO.zZm
        public GkO.zZm zZm(XSR xsr) {
            if (xsr != null) {
                this.Qle = xsr;
                return this;
            }
            throw new NullPointerException("Null repeat");
        }

        @Override // com.amazon.alexa.GkO.zZm
        public GkO.zZm zZm(MAh mAh) {
            if (mAh != null) {
                this.JTe = mAh;
                return this;
            }
            throw new NullPointerException("Null favorite");
        }

        @Override // com.amazon.alexa.GkO.zZm
        public GkO.zZm zZm(@Nullable Set<Kyp> set) {
            this.LPk = set;
            return this;
        }

        @Override // com.amazon.alexa.GkO.zZm
        public GkO zZm() {
            String str = "";
            if (this.zZm == null) {
                str = C0179Pya.zZm(str, " state");
            }
            if (this.BIo == null) {
                str = C0179Pya.zZm(str, " supportedOperations");
            }
            if (this.zQM == null) {
                str = C0179Pya.zZm(str, " media");
            }
            if (this.zyO == null) {
                str = C0179Pya.zZm(str, " positionMilliseconds");
            }
            if (this.jiA == null) {
                str = C0179Pya.zZm(str, " shuffle");
            }
            if (this.Qle == null) {
                str = C0179Pya.zZm(str, " repeat");
            }
            if (this.JTe == null) {
                str = C0179Pya.zZm(str, " favorite");
            }
            if (str.isEmpty()) {
                return new AutoValue_PlaybackStatePayload(this.zZm, this.BIo, this.zQM, this.zyO.longValue(), this.jiA, this.Qle, this.JTe, this.LPk);
            }
            throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str));
        }
    }
}
