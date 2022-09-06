package com.amazon.alexa;

import android.content.ComponentName;
import com.amazon.alexa.yfS;
import java.util.Set;
/* compiled from: $AutoValue_ExternalMediaPlayerRegistration.java */
/* loaded from: classes.dex */
public abstract class bve extends yfS {
    public final vQe BIo;
    public final ZYY JTe;
    public final yfS.zZm LPk;
    public final zYH Qle;
    public final AbstractC0188bKf jiA;
    public final Set<String> yPL;
    public final FHI zQM;
    public final ComponentName zyO;

    public bve(vQe vqe, FHI fhi, ComponentName componentName, AbstractC0188bKf abstractC0188bKf, zYH zyh, ZYY zyy, yfS.zZm zzm, Set<String> set) {
        if (vqe != null) {
            this.BIo = vqe;
            if (fhi != null) {
                this.zQM = fhi;
                if (componentName != null) {
                    this.zyO = componentName;
                    if (abstractC0188bKf != null) {
                        this.jiA = abstractC0188bKf;
                        if (zyh != null) {
                            this.Qle = zyh;
                            if (zyy != null) {
                                this.JTe = zyy;
                                if (zzm != null) {
                                    this.LPk = zzm;
                                    if (set != null) {
                                        this.yPL = set;
                                        return;
                                    }
                                    throw new NullPointerException("Null validationData");
                                }
                                throw new NullPointerException("Null authorizedState");
                            }
                            throw new NullPointerException("Null playerVersion");
                        }
                        throw new NullPointerException("Null playerCookie");
                    }
                    throw new NullPointerException("Null spiVersion");
                }
                throw new NullPointerException("Null componentName");
            }
            throw new NullPointerException("Null localPlayerIdentifier");
        }
        throw new NullPointerException("Null playerID");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof yfS)) {
            return false;
        }
        bve bveVar = (bve) obj;
        return this.BIo.equals(bveVar.BIo) && this.zQM.equals(bveVar.zQM) && this.zyO.equals(bveVar.zyO) && this.jiA.equals(bveVar.jiA) && this.Qle.equals(bveVar.Qle) && this.JTe.equals(bveVar.JTe) && this.LPk.equals(bveVar.LPk) && this.yPL.equals(bveVar.yPL);
    }

    public int hashCode() {
        return ((((((((((((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode()) * 1000003) ^ this.Qle.hashCode()) * 1000003) ^ this.JTe.hashCode()) * 1000003) ^ this.LPk.hashCode()) * 1000003) ^ this.yPL.hashCode();
    }
}
