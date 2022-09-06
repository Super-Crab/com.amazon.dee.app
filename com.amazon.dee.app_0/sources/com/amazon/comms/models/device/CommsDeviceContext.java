package com.amazon.comms.models.device;

import com.android.tools.r8.GeneratedOutlineSupport1;
@Deprecated
/* loaded from: classes11.dex */
public class CommsDeviceContext {
    public String COMMS_DEVICE_CONTEXT_NAME;
    private String version;

    /* loaded from: classes11.dex */
    public static class CommsDeviceContextBuilder {
        private String COMMS_DEVICE_CONTEXT_NAME;
        private String version;

        CommsDeviceContextBuilder() {
        }

        public CommsDeviceContextBuilder COMMS_DEVICE_CONTEXT_NAME(String str) {
            this.COMMS_DEVICE_CONTEXT_NAME = str;
            return this;
        }

        public CommsDeviceContext build() {
            return new CommsDeviceContext(this.COMMS_DEVICE_CONTEXT_NAME, this.version);
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CommsDeviceContext.CommsDeviceContextBuilder(COMMS_DEVICE_CONTEXT_NAME=");
            outline107.append(this.COMMS_DEVICE_CONTEXT_NAME);
            outline107.append(", version=");
            return GeneratedOutlineSupport1.outline91(outline107, this.version, ")");
        }

        public CommsDeviceContextBuilder version(String str) {
            this.version = str;
            return this;
        }
    }

    public CommsDeviceContext() {
        this.COMMS_DEVICE_CONTEXT_NAME = "Comms";
    }

    public static CommsDeviceContextBuilder builder() {
        return new CommsDeviceContextBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof CommsDeviceContext;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CommsDeviceContext)) {
            return false;
        }
        CommsDeviceContext commsDeviceContext = (CommsDeviceContext) obj;
        if (!commsDeviceContext.canEqual(this)) {
            return false;
        }
        String comms_device_context_name = getCOMMS_DEVICE_CONTEXT_NAME();
        String comms_device_context_name2 = commsDeviceContext.getCOMMS_DEVICE_CONTEXT_NAME();
        if (comms_device_context_name != null ? !comms_device_context_name.equals(comms_device_context_name2) : comms_device_context_name2 != null) {
            return false;
        }
        String version = getVersion();
        String version2 = commsDeviceContext.getVersion();
        return version != null ? version.equals(version2) : version2 == null;
    }

    public String getCOMMS_DEVICE_CONTEXT_NAME() {
        return this.COMMS_DEVICE_CONTEXT_NAME;
    }

    public String getVersion() {
        return this.version;
    }

    public int hashCode() {
        String comms_device_context_name = getCOMMS_DEVICE_CONTEXT_NAME();
        int i = 43;
        int hashCode = comms_device_context_name == null ? 43 : comms_device_context_name.hashCode();
        String version = getVersion();
        int i2 = (hashCode + 59) * 59;
        if (version != null) {
            i = version.hashCode();
        }
        return i2 + i;
    }

    public void setCOMMS_DEVICE_CONTEXT_NAME(String str) {
        this.COMMS_DEVICE_CONTEXT_NAME = str;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CommsDeviceContext(COMMS_DEVICE_CONTEXT_NAME=");
        outline107.append(getCOMMS_DEVICE_CONTEXT_NAME());
        outline107.append(", version=");
        outline107.append(getVersion());
        outline107.append(")");
        return outline107.toString();
    }

    public CommsDeviceContext(String str, String str2) {
        this.COMMS_DEVICE_CONTEXT_NAME = "Comms";
        this.COMMS_DEVICE_CONTEXT_NAME = str;
        this.version = str2;
    }
}
