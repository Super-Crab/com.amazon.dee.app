package com.amazon.identity.auth.device;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class fd {
    private boolean mg;
    private String mh;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a {
        private boolean mi;
        private String mj;

        public a bK(String str) {
            this.mj = str;
            return this;
        }

        public fd et() {
            return new fd(this.mi, this.mj);
        }

        public a f(boolean z) {
            this.mi = z;
            return this;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("RegisterMAPSmsReceiverResult.RegisterMAPSmsReceiverResultBuilder(isRegistered=");
            sb.append(this.mi);
            sb.append(", sms=");
            return GeneratedOutlineSupport1.outline91(sb, this.mj, ")");
        }
    }

    fd(boolean z, String str) {
        this.mg = z;
        this.mh = str;
    }

    protected boolean e(Object obj) {
        return obj instanceof fd;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fd)) {
            return false;
        }
        fd fdVar = (fd) obj;
        if (!fdVar.e(this) || isRegistered() != fdVar.isRegistered()) {
            return false;
        }
        String es = es();
        String es2 = fdVar.es();
        return es != null ? es.equals(es2) : es2 == null;
    }

    public String es() {
        return this.mh;
    }

    public int hashCode() {
        int i = isRegistered() ? 79 : 97;
        String es = es();
        return ((i + 59) * 59) + (es == null ? 43 : es.hashCode());
    }

    public boolean isRegistered() {
        return this.mg;
    }

    public String toString() {
        return "RegisterMAPSmsReceiverResult(mIsRegistered=" + isRegistered() + ", mSms=" + es() + ")";
    }
}
