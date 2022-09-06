package com.amazon.identity.auth.device;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ff {
    private boolean mn;
    private String mo;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a {
        private boolean mp;
        private String mq;

        public a bM(String str) {
            this.mq = str;
            return this;
        }

        public ff ev() {
            return new ff(this.mp, this.mq);
        }

        public a g(boolean z) {
            this.mp = z;
            return this;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("SmsRetrieverSupportInfo.SmsRetrieverSupportInfoBuilder(isSupported=");
            sb.append(this.mp);
            sb.append(", appHash=");
            return GeneratedOutlineSupport1.outline91(sb, this.mq, ")");
        }
    }

    ff(boolean z, String str) {
        this.mn = z;
        this.mo = str;
    }

    protected boolean e(Object obj) {
        return obj instanceof ff;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ff)) {
            return false;
        }
        ff ffVar = (ff) obj;
        if (!ffVar.e(this) || isSupported() != ffVar.isSupported()) {
            return false;
        }
        String eu = eu();
        String eu2 = ffVar.eu();
        return eu != null ? eu.equals(eu2) : eu2 == null;
    }

    public String eu() {
        return this.mo;
    }

    public int hashCode() {
        int i = isSupported() ? 79 : 97;
        String eu = eu();
        return ((i + 59) * 59) + (eu == null ? 43 : eu.hashCode());
    }

    public boolean isSupported() {
        return this.mn;
    }

    public String toString() {
        return "SmsRetrieverSupportInfo(mIsSupported=" + isSupported() + ", mAppHash=" + eu() + ")";
    }
}
