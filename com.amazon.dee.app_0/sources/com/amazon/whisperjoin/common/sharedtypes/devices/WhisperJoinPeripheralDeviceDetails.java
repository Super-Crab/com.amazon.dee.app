package com.amazon.whisperjoin.common.sharedtypes.devices;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.LinkedHashSet;
import java.util.Set;
/* loaded from: classes13.dex */
public class WhisperJoinPeripheralDeviceDetails extends AbstractPeripheralDeviceDetails {
    private static final String TAG = "com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails";
    private final int mAdvertisedSdkVersionIndex;
    private final String mClientNonce;
    private final String mDeviceIdentity;
    private final Set<DiscoveredRadio> mDiscoveredRadios;
    private final String mFriendlyName;
    private final DeviceNameType mNameType;
    private final String mProductIndex;
    private final String mSoftwareVersion;
    private final boolean mSupportedAuthenciatedEcdhe;
    private final boolean mSupportsPin;
    private final boolean mSupportsUnauthenciatedEcdhe;

    /* loaded from: classes13.dex */
    public static class Builder {
        public static final int FULL_NAME = 9;
        public static final int NO_NAME = -1;
        public static final int SHORT_NAME = 8;
        private String mClientNonce;
        private boolean mSupportedAuthenciatedEcdhe;
        private boolean mSupportsPin;
        private boolean mSupportsUnauthenciatedEcdhe;
        private Set<DiscoveredRadio> mDiscoveredRadios = new LinkedHashSet();
        private String mProductIndex = "";
        private String mDeviceIdentity = "";
        private String mFriendlyName = "";
        private DeviceNameType mNameType = DeviceNameType.NONE;
        private String mSoftwareVersion = "";
        private int mAdvertisedSdkVersionIndex = 0;

        /* renamed from: build */
        public WhisperJoinPeripheralDeviceDetails mo5391build() {
            return new WhisperJoinPeripheralDeviceDetails(this);
        }

        public Builder withAdvertisedSdkVersionIndex(int i) {
            this.mAdvertisedSdkVersionIndex = i;
            return this;
        }

        public Builder withClientNonce(String str) {
            if (str != null) {
                this.mClientNonce = str;
                return this;
            }
            throw new IllegalArgumentException("clientNonce can not be empty or null");
        }

        public Builder withDeviceIdentity(String str) {
            if (str != null && !str.isEmpty()) {
                this.mDeviceIdentity = str;
                return this;
            }
            throw new IllegalArgumentException("deviceIdentity can not be empty or null");
        }

        public Builder withFriendlyName(String str, int i) {
            if (str != null && !str.isEmpty()) {
                this.mFriendlyName = str;
                if (i == 8) {
                    this.mNameType = DeviceNameType.SHORT;
                } else if (i != 9) {
                    this.mNameType = DeviceNameType.NONE;
                } else {
                    this.mNameType = DeviceNameType.FULL;
                }
                return this;
            }
            this.mFriendlyName = "";
            this.mNameType = DeviceNameType.NONE;
            return this;
        }

        public Builder withProductIndex(String str) {
            if (str != null) {
                this.mProductIndex = str;
                return this;
            }
            throw new IllegalArgumentException("ProductIndex can not be empty or null");
        }

        public Builder withRadio(DiscoveredRadio discoveredRadio) {
            if (discoveredRadio != null) {
                this.mDiscoveredRadios.add(discoveredRadio);
                return this;
            }
            throw new IllegalArgumentException("radio can not be null");
        }

        public Builder withSoftwareVersion(String str) {
            if (str != null && !str.isEmpty()) {
                this.mSoftwareVersion = str;
                return this;
            }
            throw new IllegalArgumentException("softwareVersion can not be empty or null");
        }

        public Builder withSupportsAuthenticatedEcdhe(boolean z) {
            this.mSupportedAuthenciatedEcdhe = z;
            return this;
        }

        public Builder withSupportsPin(boolean z) {
            this.mSupportsPin = z;
            return this;
        }

        public Builder withSupportsUnauthenticatedEcdhe(boolean z) {
            this.mSupportsUnauthenciatedEcdhe = z;
            return this;
        }
    }

    /* loaded from: classes13.dex */
    public enum DeviceNameType {
        FULL,
        SHORT,
        NONE
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WhisperJoinPeripheralDeviceDetails(Builder builder) {
        this.mDiscoveredRadios = builder.mDiscoveredRadios;
        this.mProductIndex = builder.mProductIndex;
        this.mDeviceIdentity = builder.mDeviceIdentity;
        this.mFriendlyName = builder.mFriendlyName;
        this.mNameType = builder.mNameType;
        this.mClientNonce = builder.mClientNonce;
        this.mSoftwareVersion = builder.mSoftwareVersion;
        this.mSupportsUnauthenciatedEcdhe = builder.mSupportsUnauthenciatedEcdhe;
        this.mSupportedAuthenciatedEcdhe = builder.mSupportedAuthenciatedEcdhe;
        this.mSupportsPin = builder.mSupportsPin;
        this.mAdvertisedSdkVersionIndex = builder.mAdvertisedSdkVersionIndex;
    }

    public boolean addRadio(DiscoveredRadio discoveredRadio) {
        if (this.mDiscoveredRadios.add(discoveredRadio)) {
            return true;
        }
        for (DiscoveredRadio discoveredRadio2 : this.mDiscoveredRadios) {
            if (discoveredRadio2.equals(discoveredRadio)) {
                discoveredRadio2.updateFreshness(discoveredRadio.getFreshness());
                discoveredRadio2.updateSignalStrength(discoveredRadio.getSignalStrength());
                return false;
            }
        }
        throw new RuntimeException("Radio was in set, but not found when trying to update");
    }

    public int getAdvertisedSdkVersionIndex() {
        return this.mAdvertisedSdkVersionIndex;
    }

    public int getAuthenticationMode() {
        int i = getSupportsUnauthenticatedEcdhe() ? 1 : 0;
        if (getSupportsAuthenticatedEcdhe()) {
            i |= 2;
        }
        return getSupportsPin() ? i | 4 : i;
    }

    public String getClientNonce() {
        return this.mClientNonce;
    }

    public String getDeviceIdentity() {
        return this.mDeviceIdentity;
    }

    public String getFriendlyName() {
        return this.mFriendlyName;
    }

    public DeviceNameType getNameType() {
        return this.mNameType;
    }

    public String getProductIndex() {
        return this.mProductIndex;
    }

    public Set<DiscoveredRadio> getRadios() {
        return this.mDiscoveredRadios;
    }

    public String getSoftwareVersion() {
        return this.mSoftwareVersion;
    }

    public boolean getSupportsAuthenticatedEcdhe() {
        return this.mSupportedAuthenciatedEcdhe;
    }

    public boolean getSupportsPin() {
        return this.mSupportsPin;
    }

    public boolean getSupportsUnauthenticatedEcdhe() {
        return this.mSupportsUnauthenciatedEcdhe;
    }

    public boolean isDistressed() {
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (DiscoveredRadio discoveredRadio : this.mDiscoveredRadios) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(sb.length() == 0 ? "" : "\n");
            sb2.append("mRadio : ");
            sb2.append(discoveredRadio.getRadio());
            sb.append(sb2.toString());
            sb.append("\n mFreshness : " + discoveredRadio.getFreshness());
            sb.append("\n mSignalStrength : " + discoveredRadio.getSignalStrength());
            sb.append("\n mHandleType : " + discoveredRadio.getRadioHandle().getClass().getName());
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("\n mRadio : ");
        outline107.append(this.mDiscoveredRadios.toString());
        sb.append(outline107.toString());
        sb.append("\n mProductIndex : " + this.mProductIndex);
        sb.append("\n mDeviceIdentity : " + this.mDeviceIdentity);
        sb.append("\n mFriendlyName : " + this.mFriendlyName);
        sb.append("\n mNameType : " + this.mNameType.toString());
        sb.append("\n mClientNonce : " + this.mClientNonce);
        sb.append("\n mSupportsUnauthenciatedEcdhe: " + this.mSupportsUnauthenciatedEcdhe);
        sb.append("\n mSupportedAuthenciatedEcdhe: " + this.mSupportedAuthenciatedEcdhe);
        sb.append("\n mSupportsPin: " + this.mSupportsPin);
        sb.append("\n mAdvertisedSdkVersionIndex: " + this.mAdvertisedSdkVersionIndex);
        return sb.toString();
    }
}
