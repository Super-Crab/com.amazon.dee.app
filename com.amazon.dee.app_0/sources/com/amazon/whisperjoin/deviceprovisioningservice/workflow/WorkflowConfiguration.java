package com.amazon.whisperjoin.deviceprovisioningservice.workflow;

import android.util.Base64;
import com.amazon.whisperjoin.common.sharedtypes.devices.DeviceFilter;
import com.amazon.whisperjoin.common.sharedtypes.devices.filters.FilterAcceptAllDevices;
import com.amazon.whisperjoin.common.sharedtypes.devices.filters.FilterByDeviceIdentity;
import com.amazon.whisperjoin.common.sharedtypes.devices.filters.FilterByDistressState;
import com.amazon.whisperjoin.common.sharedtypes.devices.filters.FilterByProductIndex;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.PublicKeyDecompressionException;
import com.amazon.whisperjoin.common.sharedtypes.utility.AuthMaterialIndexGenerator;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
/* loaded from: classes13.dex */
public class WorkflowConfiguration {
    private static final String TAG = "WorkflowConfiguration";
    private final String mBarcodeString;
    private final DeviceFilter.BeaconType mBeaconType;
    private final String mDeviceId;
    private final String mDistressState;
    private final String mPin;
    private final String mProductId;
    private final String mPublicKey;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public static class Builder {
        private String mBarcodeString;
        private DeviceFilter.BeaconType mBeaconType = DeviceFilter.BeaconType.OOBE;
        private String mDeviceId;
        private String mDistressState;
        private String mPin;
        private String mProductId;
        private String mPublicKey;

        Builder() {
        }

        public WorkflowConfiguration build() throws PublicKeyDecompressionException {
            return new WorkflowConfiguration(this.mBeaconType, this.mPublicKey, this.mPin, this.mProductId, this.mDeviceId, this.mBarcodeString, this.mDistressState);
        }

        public Builder withBarcodeString(String str) {
            this.mBarcodeString = str;
            return this;
        }

        public Builder withBeaconType(DeviceFilter.BeaconType beaconType) {
            this.mBeaconType = beaconType;
            return this;
        }

        public Builder withDeviceId(String str) {
            this.mDeviceId = str;
            return this;
        }

        public Builder withDistressState(String str) {
            this.mDistressState = str;
            return this;
        }

        public Builder withPin(String str) {
            this.mPin = str;
            return this;
        }

        public Builder withProductId(String str) {
            this.mProductId = str;
            return this;
        }

        public Builder withPublicKey(String str) {
            this.mPublicKey = str;
            return this;
        }
    }

    private String attemptToGenerateDeviceIdFromBarcodeData() throws PublicKeyDecompressionException {
        if (hasBarcodeData()) {
            try {
                Security.addProvider(new BouncyCastleProvider());
                return decompressPublicKey();
            } catch (NoSuchAlgorithmException unused) {
                WJLog.i(TAG, "Remove existing BouncyCastle provider");
                Security.removeProvider(BouncyCastleProvider.PROVIDER_NAME);
                Security.addProvider(new BouncyCastleProvider());
                try {
                    return decompressPublicKey();
                } catch (Exception e) {
                    WJLog.e(TAG, e.getMessage());
                    throw new PublicKeyDecompressionException(e);
                }
            } catch (NoSuchProviderException e2) {
                e = e2;
                WJLog.e(TAG, e.getMessage());
                throw new PublicKeyDecompressionException(e);
            } catch (InvalidKeySpecException e3) {
                e = e3;
                WJLog.e(TAG, e.getMessage());
                throw new PublicKeyDecompressionException(e);
            }
        }
        return null;
    }

    public static Builder builder() {
        return new Builder();
    }

    private String decompressPublicKey() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
        return Base64.encodeToString(AuthMaterialIndexGenerator.generate(Base64.decode(new String(Base64.encode(KeyFactory.getInstance("ECDSA", BouncyCastleProvider.PROVIDER_NAME).generatePublic(new X509EncodedKeySpec(Base64.decode(this.mPublicKey, 0))).getEncoded(), 0), Charset.forName("UTF-8")).replace("\n", ""), 0)), 2);
    }

    public DeviceFilter createDeviceFilter() {
        if (hasProductId()) {
            return new FilterByProductIndex(this.mProductId, this.mBeaconType);
        }
        if (hasDeviceId()) {
            return new FilterByDeviceIdentity(this.mDeviceId, this.mBeaconType);
        }
        if (hasDistressState()) {
            return new FilterByDistressState(this.mDistressState, this.mBeaconType);
        }
        return new FilterAcceptAllDevices(this.mBeaconType);
    }

    public String getBarcodeString() {
        return this.mBarcodeString;
    }

    public String getDistressState() {
        return this.mDistressState;
    }

    public String getPin() {
        return this.mPin;
    }

    public String getProductId() {
        return this.mProductId;
    }

    public String getPublicKey() {
        return this.mPublicKey;
    }

    public boolean hasBarcodeData() {
        return !Strings.isNullOrEmpty(this.mPin) && !Strings.isNullOrEmpty(this.mPublicKey);
    }

    public boolean hasBarcodeString() {
        return !Strings.isNullOrEmpty(this.mBarcodeString);
    }

    public boolean hasDeviceId() {
        return !Strings.isNullOrEmpty(this.mDeviceId);
    }

    public boolean hasDistressState() {
        return !Strings.isNullOrEmpty(this.mDistressState);
    }

    public boolean hasProductId() {
        return !Strings.isNullOrEmpty(this.mProductId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mPublicKey", this.mPublicKey).add("mPin", this.mPin).add("mProductId", this.mProductId).add("mDeviceId", this.mDeviceId).add("mBarcodeString", this.mBarcodeString).add("mDistressState", this.mDistressState).add("mBeaconType", this.mBeaconType.toString()).toString();
    }

    private WorkflowConfiguration(DeviceFilter.BeaconType beaconType, String str, String str2, String str3, String str4, String str5, String str6) throws PublicKeyDecompressionException {
        this.mBeaconType = beaconType;
        this.mPublicKey = str;
        this.mPin = str2;
        this.mProductId = str3;
        this.mDeviceId = str4 == null ? attemptToGenerateDeviceIdFromBarcodeData() : str4;
        this.mBarcodeString = str5;
        this.mDistressState = str6;
    }
}
