package javax.mail;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes3.dex */
public class Provider {
    private String className;
    private String protocol;
    private Type type;
    private String vendor;
    private String version;

    /* loaded from: classes3.dex */
    public static class Type {
        public static final Type STORE = new Type("STORE");
        public static final Type TRANSPORT = new Type("TRANSPORT");
        private String type;

        private Type(String str) {
            this.type = str;
        }

        public String toString() {
            return this.type;
        }
    }

    public Provider(Type type, String str, String str2, String str3, String str4) {
        this.type = type;
        this.protocol = str;
        this.className = str2;
        this.vendor = str3;
        this.version = str4;
    }

    public String getClassName() {
        return this.className;
    }

    public String getProtocol() {
        return this.protocol;
    }

    public Type getType() {
        return this.type;
    }

    public String getVendor() {
        return this.vendor;
    }

    public String getVersion() {
        return this.version;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("javax.mail.Provider[");
        outline107.append(this.type);
        outline107.append(",");
        outline107.append(this.protocol);
        outline107.append(",");
        outline107.append(this.className);
        String sb = outline107.toString();
        if (this.vendor != null) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(sb, ",");
            outline113.append(this.vendor);
            sb = outline113.toString();
        }
        if (this.version != null) {
            StringBuilder outline1132 = GeneratedOutlineSupport1.outline113(sb, ",");
            outline1132.append(this.version);
            sb = outline1132.toString();
        }
        return GeneratedOutlineSupport1.outline72(sb, "]");
    }
}
