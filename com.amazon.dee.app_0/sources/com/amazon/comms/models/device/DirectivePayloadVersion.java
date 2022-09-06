package com.amazon.comms.models.device;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class DirectivePayloadVersion {
    private String CLIENT_IDENTIFIER;
    private String version;

    /* loaded from: classes11.dex */
    public static class DirectivePayloadVersionBuilder {
        private String CLIENT_IDENTIFIER;
        private String version;

        DirectivePayloadVersionBuilder() {
        }

        public DirectivePayloadVersionBuilder CLIENT_IDENTIFIER(String str) {
            this.CLIENT_IDENTIFIER = str;
            return this;
        }

        public DirectivePayloadVersion build() {
            return new DirectivePayloadVersion(this.CLIENT_IDENTIFIER, this.version);
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DirectivePayloadVersion.DirectivePayloadVersionBuilder(CLIENT_IDENTIFIER=");
            outline107.append(this.CLIENT_IDENTIFIER);
            outline107.append(", version=");
            return GeneratedOutlineSupport1.outline91(outline107, this.version, ")");
        }

        public DirectivePayloadVersionBuilder version(String str) {
            this.version = str;
            return this;
        }
    }

    public DirectivePayloadVersion() {
        this.CLIENT_IDENTIFIER = "Domain:Application:Communications:Calling";
    }

    public static DirectivePayloadVersionBuilder builder() {
        return new DirectivePayloadVersionBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof DirectivePayloadVersion;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DirectivePayloadVersion)) {
            return false;
        }
        DirectivePayloadVersion directivePayloadVersion = (DirectivePayloadVersion) obj;
        if (!directivePayloadVersion.canEqual(this)) {
            return false;
        }
        String client_identifier = getCLIENT_IDENTIFIER();
        String client_identifier2 = directivePayloadVersion.getCLIENT_IDENTIFIER();
        if (client_identifier != null ? !client_identifier.equals(client_identifier2) : client_identifier2 != null) {
            return false;
        }
        String version = getVersion();
        String version2 = directivePayloadVersion.getVersion();
        return version != null ? version.equals(version2) : version2 == null;
    }

    public String getCLIENT_IDENTIFIER() {
        return this.CLIENT_IDENTIFIER;
    }

    public String getVersion() {
        return this.version;
    }

    public int hashCode() {
        String client_identifier = getCLIENT_IDENTIFIER();
        int i = 43;
        int hashCode = client_identifier == null ? 43 : client_identifier.hashCode();
        String version = getVersion();
        int i2 = (hashCode + 59) * 59;
        if (version != null) {
            i = version.hashCode();
        }
        return i2 + i;
    }

    public void setCLIENT_IDENTIFIER(String str) {
        this.CLIENT_IDENTIFIER = str;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DirectivePayloadVersion(CLIENT_IDENTIFIER=");
        outline107.append(getCLIENT_IDENTIFIER());
        outline107.append(", version=");
        outline107.append(getVersion());
        outline107.append(")");
        return outline107.toString();
    }

    public DirectivePayloadVersion(String str, String str2) {
        this.CLIENT_IDENTIFIER = "Domain:Application:Communications:Calling";
        this.CLIENT_IDENTIFIER = str;
        this.version = str2;
    }
}
