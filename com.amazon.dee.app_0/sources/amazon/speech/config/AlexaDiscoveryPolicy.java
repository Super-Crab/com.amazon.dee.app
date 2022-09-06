package amazon.speech.config;

import amazon.speech.util.Preconditions;
/* loaded from: classes.dex */
public class AlexaDiscoveryPolicy {
    private final Builder mBuilder;

    /* loaded from: classes.dex */
    public static class Builder {
        private String mManufacturerName = null;
        private String mLocalizedDeviceDescription = null;
        private String mLocalizedDeviceFriendlyName = null;
        private String[] mLocalizedOptionalDeviceAliases = null;

        public AlexaDiscoveryPolicy build() {
            Preconditions.checkNotNull(this.mManufacturerName, this.mLocalizedDeviceDescription);
            return new AlexaDiscoveryPolicy(this);
        }

        public Builder setLocalizedDeviceDescription(String str) {
            this.mLocalizedDeviceDescription = str;
            return this;
        }

        public Builder setLocalizedDeviceFriendlyName(String str) {
            this.mLocalizedDeviceFriendlyName = str;
            return this;
        }

        public Builder setLocalizedOptionalDeviceAliases(String... strArr) {
            this.mLocalizedOptionalDeviceAliases = strArr;
            return this;
        }

        public Builder setManufacturerName(String str) {
            this.mManufacturerName = str;
            return this;
        }
    }

    AlexaDiscoveryPolicy(Builder builder) {
        this.mBuilder = builder;
    }

    public String getLocalizedDeviceDescription() {
        return this.mBuilder.mLocalizedDeviceDescription;
    }

    public String getLocalizedDeviceFriendlyName() {
        return this.mBuilder.mLocalizedDeviceFriendlyName;
    }

    public String[] getLocalizedOptionalDeviceAliases() {
        return this.mBuilder.mLocalizedOptionalDeviceAliases;
    }

    public String getManufacturerName() {
        return this.mBuilder.mManufacturerName;
    }
}
