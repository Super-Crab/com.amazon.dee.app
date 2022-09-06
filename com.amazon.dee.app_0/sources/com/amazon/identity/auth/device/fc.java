package com.amazon.identity.auth.device;

import com.amazon.identity.auth.device.framework.smartlock.CustomeInformationResultType;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class fc {
    private CustomeInformationResultType mc;
    private fb md;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a {
        private CustomeInformationResultType me;
        private fb mf;

        public a a(fb fbVar) {
            this.mf = fbVar;
            return this;
        }

        public fc er() {
            return new fc(this.me, this.mf);
        }

        public String toString() {
            return "CustomerInformationResult.CustomerInformationResultBuilder(resultType=" + this.me + ", information=" + this.mf + ")";
        }

        public a a(CustomeInformationResultType customeInformationResultType) {
            this.me = customeInformationResultType;
            return this;
        }
    }

    fc(CustomeInformationResultType customeInformationResultType, fb fbVar) {
        this.mc = customeInformationResultType;
        this.md = fbVar;
    }

    protected boolean e(Object obj) {
        return obj instanceof fc;
    }

    public CustomeInformationResultType ep() {
        return this.mc;
    }

    public fb eq() {
        return this.md;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fc)) {
            return false;
        }
        fc fcVar = (fc) obj;
        if (!fcVar.e(this)) {
            return false;
        }
        CustomeInformationResultType ep = ep();
        CustomeInformationResultType ep2 = fcVar.ep();
        if (ep != null ? !ep.equals(ep2) : ep2 != null) {
            return false;
        }
        fb eq = eq();
        fb eq2 = fcVar.eq();
        return eq != null ? eq.equals(eq2) : eq2 == null;
    }

    public int hashCode() {
        CustomeInformationResultType ep = ep();
        int i = 43;
        int hashCode = ep == null ? 43 : ep.hashCode();
        fb eq = eq();
        int i2 = (hashCode + 59) * 59;
        if (eq != null) {
            i = eq.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        return "CustomerInformationResult(mResultType=" + ep() + ", mInformation=" + eq() + ")";
    }
}
