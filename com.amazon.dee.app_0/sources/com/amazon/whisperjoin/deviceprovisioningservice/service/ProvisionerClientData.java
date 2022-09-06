package com.amazon.whisperjoin.deviceprovisioningservice.service;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class ProvisionerClientData {
    private static final String KEY_APP_NAME = "ProvisionerClientData.AppName";
    private static final String KEY_APP_VERSION = "ProvisionerClientData.AppVersion";
    private static final String KEY_CUSTOMER_ECID = "ProvisionerClientData.CustomerEcid";
    private static final String KEY_DEVICE_FIRMWARE_VERSION = "ProvisionerClientData.DeviceFirmwareVersion";
    private static final String KEY_DEVICE_MANUFACTURER = "ProvisionerClientData.DeviceManufacturer";
    private static final String KEY_DEVICE_MODEL = "ProvisionerClientData.DeviceModel";
    private static final String KEY_DEVICE_SERIAL_NUMBER = "ProvisionerClientData.DeviceSerialNumber";
    private static final String KEY_MARKETPLACE = "ProvisionerClientData.Marketplace";
    private static final String KEY_METRICS_DEVICE_GROUP = "ProvisionerClientData.MetricsDeviceGroup";
    private static final String PREFIX = "ProvisionerClientData.";
    private static final String TAG = "ProvisionerClientData";
    private final String mClientAppName;
    private final String mClientAppVersion;
    private final String mCustomerEcid;
    private final String mDeviceFirmwareVersion;
    private final String mDeviceManufacturer;
    private final String mDeviceModel;
    private final String mDeviceSerialNumber;
    private final String mMarketplace;
    private final String mMetricsDeviceGroup;

    /* loaded from: classes13.dex */
    public static class Builder {
        private String mClientAppName;
        private String mClientAppVersion;
        private String mCustomerEcid;
        private String mDeviceFirmwareVersion;
        private String mDeviceManufacturer;
        private String mDeviceModel;
        private String mDeviceSerialNumber;
        private String mMarketplace;
        private String mMetricsDeviceGroup;

        public Builder() {
        }

        public ProvisionerClientData createProvisionerClientData() {
            String str = this.mClientAppName;
            if (str != null) {
                String str2 = this.mClientAppVersion;
                if (str2 != null) {
                    String str3 = this.mDeviceManufacturer;
                    if (str3 != null) {
                        String str4 = this.mDeviceModel;
                        if (str4 != null) {
                            String str5 = this.mDeviceSerialNumber;
                            if (str5 != null) {
                                String str6 = this.mDeviceFirmwareVersion;
                                if (str6 != null) {
                                    String str7 = this.mMarketplace;
                                    if (str7 != null) {
                                        String str8 = this.mMetricsDeviceGroup;
                                        if (str8 != null) {
                                            return new ProvisionerClientData(str, str2, str3, str4, str5, str6, str7, str8, this.mCustomerEcid);
                                        }
                                        throw new IllegalArgumentException("mMetricsDeviceGroup can not be null");
                                    }
                                    throw new IllegalArgumentException("mMarketplace can not be null");
                                }
                                throw new IllegalArgumentException("mDeviceFirmwareVersion can not be null");
                            }
                            throw new IllegalArgumentException("mDeviceSerialNumber can not be null");
                        }
                        throw new IllegalArgumentException("mDeviceModel can not be null");
                    }
                    throw new IllegalArgumentException("mDeviceManufacturer can not be null");
                }
                throw new IllegalArgumentException("mClientAppVersion can not be null");
            }
            throw new IllegalArgumentException("mClientAppName can not be null");
        }

        public Builder setClientAppName(String str) {
            this.mClientAppName = str;
            return this;
        }

        public Builder setClientAppVersion(String str) {
            this.mClientAppVersion = str;
            return this;
        }

        public Builder setCustomerEcid(String str) {
            this.mCustomerEcid = str;
            return this;
        }

        public Builder setDeviceFirmwareVersion(String str) {
            this.mDeviceFirmwareVersion = str;
            return this;
        }

        public Builder setDeviceManufacturer(String str) {
            this.mDeviceManufacturer = str;
            return this;
        }

        public Builder setDeviceModel(String str) {
            this.mDeviceModel = str;
            return this;
        }

        public Builder setDeviceSerialNumber(String str) {
            this.mDeviceSerialNumber = str;
            return this;
        }

        public Builder setMarketplace(String str) {
            this.mMarketplace = str;
            return this;
        }

        public Builder setMetricsDeviceGroup(String str) {
            this.mMetricsDeviceGroup = str;
            return this;
        }

        public Builder(ProvisionerClientData provisionerClientData) {
            this.mClientAppName = provisionerClientData.mClientAppName;
            this.mClientAppVersion = provisionerClientData.mClientAppVersion;
            this.mDeviceModel = provisionerClientData.mDeviceModel;
            this.mDeviceSerialNumber = provisionerClientData.mDeviceSerialNumber;
            this.mDeviceManufacturer = provisionerClientData.mDeviceManufacturer;
            this.mDeviceFirmwareVersion = provisionerClientData.mDeviceFirmwareVersion;
            this.mMarketplace = provisionerClientData.mMarketplace;
            this.mMetricsDeviceGroup = provisionerClientData.mMetricsDeviceGroup;
            this.mCustomerEcid = provisionerClientData.mCustomerEcid;
        }
    }

    public ProvisionerClientData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        this.mClientAppName = str;
        this.mClientAppVersion = str2;
        this.mDeviceManufacturer = str3;
        this.mDeviceModel = str4;
        this.mDeviceSerialNumber = str5;
        this.mDeviceFirmwareVersion = str6;
        this.mMarketplace = str7;
        this.mMetricsDeviceGroup = str8;
        this.mCustomerEcid = str9;
    }

    public static ProvisionerClientData readFromBundle(Bundle bundle) {
        if (bundle != null) {
            try {
                return new Builder().setClientAppName(bundle.getString(KEY_APP_NAME)).setClientAppVersion(bundle.getString(KEY_APP_VERSION)).setDeviceManufacturer(bundle.getString(KEY_DEVICE_MANUFACTURER)).setDeviceModel(bundle.getString(KEY_DEVICE_MODEL)).setDeviceSerialNumber(bundle.getString(KEY_DEVICE_SERIAL_NUMBER)).setDeviceFirmwareVersion(bundle.getString(KEY_DEVICE_FIRMWARE_VERSION)).setMarketplace(bundle.getString(KEY_MARKETPLACE)).setMetricsDeviceGroup(bundle.getString(KEY_METRICS_DEVICE_GROUP)).setCustomerEcid(bundle.getString(KEY_CUSTOMER_ECID)).createProvisionerClientData();
            } catch (IllegalArgumentException unused) {
                WJLog.d(TAG, "No Valid ProvisionerClientData found in Bundle");
                return null;
            }
        }
        throw new IllegalArgumentException("bundle can not be null");
    }

    public static ProvisionerClientData readFromSharedPreferences(SharedPreferences sharedPreferences) {
        if (sharedPreferences != null) {
            try {
                return new Builder().setClientAppName(sharedPreferences.getString(KEY_APP_NAME, null)).setClientAppVersion(sharedPreferences.getString(KEY_APP_VERSION, null)).setDeviceManufacturer(sharedPreferences.getString(KEY_DEVICE_MANUFACTURER, null)).setDeviceModel(sharedPreferences.getString(KEY_DEVICE_MODEL, null)).setDeviceSerialNumber(sharedPreferences.getString(KEY_DEVICE_SERIAL_NUMBER, null)).setDeviceFirmwareVersion(sharedPreferences.getString(KEY_DEVICE_FIRMWARE_VERSION, null)).setMarketplace(sharedPreferences.getString(KEY_MARKETPLACE, null)).setMetricsDeviceGroup(sharedPreferences.getString(KEY_METRICS_DEVICE_GROUP, null)).setCustomerEcid(sharedPreferences.getString(KEY_CUSTOMER_ECID, null)).createProvisionerClientData();
            } catch (IllegalArgumentException unused) {
                WJLog.d(TAG, "No Valid ProvisionerClientData found in SharedPreferences");
                return null;
            }
        }
        throw new IllegalArgumentException("sharedPreferences can not be null");
    }

    public static void writeToBundle(ProvisionerClientData provisionerClientData, Bundle bundle) {
        if (provisionerClientData != null) {
            if (bundle != null) {
                bundle.putString(KEY_APP_NAME, provisionerClientData.getClientAppName());
                bundle.putString(KEY_APP_VERSION, provisionerClientData.getClientAppVersion());
                bundle.putString(KEY_DEVICE_MANUFACTURER, provisionerClientData.getDeviceManufacturer());
                bundle.putString(KEY_DEVICE_MODEL, provisionerClientData.getDeviceModel());
                bundle.putString(KEY_DEVICE_SERIAL_NUMBER, provisionerClientData.getDeviceSerialNumber());
                bundle.putString(KEY_DEVICE_FIRMWARE_VERSION, provisionerClientData.getDeviceFirmwareVersion());
                bundle.putString(KEY_MARKETPLACE, provisionerClientData.getMarketplace());
                bundle.putString(KEY_METRICS_DEVICE_GROUP, provisionerClientData.getMetricsDeviceGroup());
                bundle.putString(KEY_CUSTOMER_ECID, provisionerClientData.getCustomerEcid());
                return;
            }
            throw new IllegalArgumentException("bundle can not be null");
        }
        throw new IllegalArgumentException("provisionerClientData can not be null");
    }

    public static void writeToSharedPreferences(ProvisionerClientData provisionerClientData, SharedPreferences sharedPreferences) {
        if (provisionerClientData != null) {
            if (sharedPreferences != null) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString(KEY_APP_NAME, provisionerClientData.getClientAppName());
                edit.putString(KEY_APP_VERSION, provisionerClientData.getClientAppVersion());
                edit.putString(KEY_DEVICE_MANUFACTURER, provisionerClientData.getDeviceManufacturer());
                edit.putString(KEY_DEVICE_MODEL, provisionerClientData.getDeviceModel());
                edit.putString(KEY_DEVICE_SERIAL_NUMBER, provisionerClientData.getDeviceSerialNumber());
                edit.putString(KEY_DEVICE_FIRMWARE_VERSION, provisionerClientData.getDeviceFirmwareVersion());
                edit.putString(KEY_MARKETPLACE, provisionerClientData.getMarketplace());
                edit.putString(KEY_METRICS_DEVICE_GROUP, provisionerClientData.getMetricsDeviceGroup());
                edit.putString(KEY_CUSTOMER_ECID, provisionerClientData.getCustomerEcid());
                edit.apply();
                return;
            }
            throw new IllegalArgumentException("sharedPreferences can not be null");
        }
        throw new IllegalArgumentException("provisionerClientData can not be null");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ProvisionerClientData.class != obj.getClass()) {
            return false;
        }
        ProvisionerClientData provisionerClientData = (ProvisionerClientData) obj;
        return Objects.equal(this.mClientAppName, provisionerClientData.mClientAppName) && Objects.equal(this.mClientAppVersion, provisionerClientData.mClientAppVersion) && Objects.equal(this.mDeviceManufacturer, provisionerClientData.mDeviceManufacturer) && Objects.equal(this.mDeviceModel, provisionerClientData.mDeviceModel) && Objects.equal(this.mDeviceSerialNumber, provisionerClientData.mDeviceSerialNumber) && Objects.equal(this.mDeviceFirmwareVersion, provisionerClientData.mDeviceFirmwareVersion) && Objects.equal(this.mMarketplace, provisionerClientData.mMarketplace) && Objects.equal(this.mMetricsDeviceGroup, provisionerClientData.mMetricsDeviceGroup) && Objects.equal(this.mCustomerEcid, provisionerClientData.mCustomerEcid);
    }

    public String getClientAppName() {
        return this.mClientAppName;
    }

    public String getClientAppVersion() {
        return this.mClientAppVersion;
    }

    public String getCustomerEcid() {
        return this.mCustomerEcid;
    }

    public String getDeviceFirmwareVersion() {
        return this.mDeviceFirmwareVersion;
    }

    public String getDeviceManufacturer() {
        return this.mDeviceManufacturer;
    }

    public String getDeviceModel() {
        return this.mDeviceModel;
    }

    public String getDeviceSerialNumber() {
        return this.mDeviceSerialNumber;
    }

    public String getMarketplace() {
        return this.mMarketplace;
    }

    public String getMetricsDeviceGroup() {
        return this.mMetricsDeviceGroup;
    }

    public int hashCode() {
        return Objects.hashCode(this.mClientAppName, this.mClientAppVersion, this.mDeviceManufacturer, this.mDeviceModel, this.mDeviceSerialNumber, this.mDeviceFirmwareVersion, this.mMarketplace, this.mMetricsDeviceGroup, this.mCustomerEcid);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mClientAppName", this.mClientAppName).add("mClientAppVersion", this.mClientAppVersion).add("mDeviceManufacturer", this.mDeviceManufacturer).add("mDeviceModel", this.mDeviceModel).add("mDeviceSerialNumber", this.mDeviceSerialNumber).add("mDeviceFirmwareVersion", this.mDeviceFirmwareVersion).add("mMarketplace", this.mMarketplace).add("mMetricsDeviceGroup", this.mMetricsDeviceGroup).add("mCustomerEcid", this.mCustomerEcid).toString();
    }
}
