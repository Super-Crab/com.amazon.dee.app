package com.amazon.identity.auth.device;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class fb {
    private String lQ;
    private String lR;
    private String lS;
    private String mName;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a {
        private String lT;
        private String lU;
        private String lV;
        private String name;

        public a bH(String str) {
            this.name = str;
            return this;
        }

        public a bI(String str) {
            this.lU = str;
            return this;
        }

        public a bJ(String str) {
            this.lV = str;
            return this;
        }

        public fb eo() {
            return new fb(this.name, this.lT, this.lU, this.lV);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("CustomerInformation.CustomerInformationBuilder(name=");
            sb.append(this.name);
            sb.append(", namePron=");
            sb.append(this.lT);
            sb.append(", email=");
            sb.append(this.lU);
            sb.append(", phoneNumber=");
            return GeneratedOutlineSupport1.outline91(sb, this.lV, ")");
        }
    }

    fb(String str, String str2, String str3, String str4) {
        this.mName = str;
        this.lQ = str2;
        this.lR = str3;
        this.lS = str4;
    }

    protected boolean e(Object obj) {
        return obj instanceof fb;
    }

    public String em() {
        return this.lQ;
    }

    public String en() {
        return this.lS;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fb)) {
            return false;
        }
        fb fbVar = (fb) obj;
        if (!fbVar.e(this)) {
            return false;
        }
        String name = getName();
        String name2 = fbVar.getName();
        if (name != null ? !name.equals(name2) : name2 != null) {
            return false;
        }
        String em = em();
        String em2 = fbVar.em();
        if (em != null ? !em.equals(em2) : em2 != null) {
            return false;
        }
        String email = getEmail();
        String email2 = fbVar.getEmail();
        if (email != null ? !email.equals(email2) : email2 != null) {
            return false;
        }
        String en = en();
        String en2 = fbVar.en();
        return en != null ? en.equals(en2) : en2 == null;
    }

    public String getEmail() {
        return this.lR;
    }

    public String getName() {
        return this.mName;
    }

    public int hashCode() {
        String name = getName();
        int i = 43;
        int hashCode = name == null ? 43 : name.hashCode();
        String em = em();
        int hashCode2 = ((hashCode + 59) * 59) + (em == null ? 43 : em.hashCode());
        String email = getEmail();
        int hashCode3 = (hashCode2 * 59) + (email == null ? 43 : email.hashCode());
        String en = en();
        int i2 = hashCode3 * 59;
        if (en != null) {
            i = en.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        return "CustomerInformation(mName=" + getName() + ", mNamePron=" + em() + ", mEmail=" + getEmail() + ", mPhoneNumber=" + en() + ")";
    }
}
