package com.amazon.alexa.statereporting.lib.model.api.discovery;

import com.amazon.deecomms.calling.incallcommands.constants.CommsFocusConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Generated;
/* loaded from: classes10.dex */
public class Capability<PropertyKey, Configuration> {
    @SerializedName(CommsFocusConstants.INTERFACE)
    @CheckForNull
    private final String capabilityInterface;
    @CheckForNull
    private final Configuration configuration;
    @CheckForNull
    private final Properties<PropertyKey> properties;
    @CheckForNull
    private final String type;
    @CheckForNull
    private final String version;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    /* loaded from: classes10.dex */
    public static class CapabilityBuilder<PropertyKey, Configuration> {
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private String capabilityInterface;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private Configuration configuration;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private Properties<PropertyKey> properties;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private String type;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private String version;

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        CapabilityBuilder() {
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public Capability<PropertyKey, Configuration> build() {
            return new Capability<>(this.type, this.capabilityInterface, this.version, this.properties, this.configuration);
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public CapabilityBuilder<PropertyKey, Configuration> capabilityInterface(String str) {
            this.capabilityInterface = str;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public CapabilityBuilder<PropertyKey, Configuration> configuration(Configuration configuration) {
            this.configuration = configuration;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public CapabilityBuilder<PropertyKey, Configuration> properties(Properties<PropertyKey> properties) {
            this.properties = properties;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Capability.CapabilityBuilder(type=");
            outline107.append(this.type);
            outline107.append(", capabilityInterface=");
            outline107.append(this.capabilityInterface);
            outline107.append(", version=");
            outline107.append(this.version);
            outline107.append(", properties=");
            outline107.append(this.properties);
            outline107.append(", configuration=");
            return GeneratedOutlineSupport1.outline88(outline107, this.configuration, ")");
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public CapabilityBuilder<PropertyKey, Configuration> type(String str) {
            this.type = str;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public CapabilityBuilder<PropertyKey, Configuration> version(String str) {
            this.version = str;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public Capability(String str, String str2, String str3, Properties<PropertyKey> properties, Configuration configuration) {
        this.type = str;
        this.capabilityInterface = str2;
        this.version = str3;
        this.properties = properties;
        this.configuration = configuration;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public static <PropertyKey, Configuration> CapabilityBuilder<PropertyKey, Configuration> builder() {
        return new CapabilityBuilder<>();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    protected boolean canEqual(Object obj) {
        return obj instanceof Capability;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Capability)) {
            return false;
        }
        Capability capability = (Capability) obj;
        if (!capability.canEqual(this)) {
            return false;
        }
        String type = getType();
        String type2 = capability.getType();
        if (type != null ? !type.equals(type2) : type2 != null) {
            return false;
        }
        String capabilityInterface = getCapabilityInterface();
        String capabilityInterface2 = capability.getCapabilityInterface();
        if (capabilityInterface != null ? !capabilityInterface.equals(capabilityInterface2) : capabilityInterface2 != null) {
            return false;
        }
        String version = getVersion();
        String version2 = capability.getVersion();
        if (version != null ? !version.equals(version2) : version2 != null) {
            return false;
        }
        Properties<PropertyKey> properties = getProperties();
        Properties<PropertyKey> properties2 = capability.getProperties();
        if (properties != null ? !properties.equals(properties2) : properties2 != null) {
            return false;
        }
        Configuration configuration = getConfiguration();
        Object configuration2 = capability.getConfiguration();
        return configuration != null ? configuration.equals(configuration2) : configuration2 == null;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String getCapabilityInterface() {
        return this.capabilityInterface;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public Configuration getConfiguration() {
        return this.configuration;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public Properties<PropertyKey> getProperties() {
        return this.properties;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String getType() {
        return this.type;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String getVersion() {
        return this.version;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public int hashCode() {
        String type = getType();
        int i = 43;
        int hashCode = type == null ? 43 : type.hashCode();
        String capabilityInterface = getCapabilityInterface();
        int hashCode2 = ((hashCode + 59) * 59) + (capabilityInterface == null ? 43 : capabilityInterface.hashCode());
        String version = getVersion();
        int hashCode3 = (hashCode2 * 59) + (version == null ? 43 : version.hashCode());
        Properties<PropertyKey> properties = getProperties();
        int hashCode4 = (hashCode3 * 59) + (properties == null ? 43 : properties.hashCode());
        Configuration configuration = getConfiguration();
        int i2 = hashCode4 * 59;
        if (configuration != null) {
            i = configuration.hashCode();
        }
        return i2 + i;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Capability(type=");
        outline107.append(getType());
        outline107.append(", capabilityInterface=");
        outline107.append(getCapabilityInterface());
        outline107.append(", version=");
        outline107.append(getVersion());
        outline107.append(", properties=");
        outline107.append(getProperties());
        outline107.append(", configuration=");
        outline107.append(getConfiguration());
        outline107.append(")");
        return outline107.toString();
    }
}
