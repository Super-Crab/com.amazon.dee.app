package com.amazon.blueshift.bluefront.android.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Build;
import com.amazon.blueshift.bluefront.android.common.NetworkAnalyzer;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.util.UUID;
import javax.annotation.Nullable;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* loaded from: classes11.dex */
public final class AndroidDevice {
    private final String mAppVersion;
    private final String mBrand;
    private final String mCarrier;
    private final NetworkAnalyzer.NetworkType mConnectedNetworkType;
    private final Context mContext;
    private final String mDeviceId;
    private final Location mLocation;
    private final String mManufacturer;
    private final String mModel;
    private final String mSerialNumber;

    /* loaded from: classes11.dex */
    public static class Builder {
        @VisibleForTesting
        public static final String PREF_UNIQUE_ID = "MSHOP_ALEXA_FAKE_SERIAL_NUMBER";
        private static String fakeSerialNumber;
        private String mAppVersion;
        private String mCarrier;
        private NetworkAnalyzer.NetworkType mConnectedNetworkType;
        private final Context mContext;
        private String mDeviceId;
        private Location mLocation;
        private String mSerialNumber;
        private String mBrand = Build.BRAND;
        private String mManufacturer = Build.MANUFACTURER;
        private String mModel = Build.MODEL;

        public Builder(Context context) {
            Preconditions.checkNotNull(context, "Context cannot be null.");
            this.mContext = context;
            this.mDeviceId = null;
        }

        private static String generateFakeSerialNumber() {
            return UUID.randomUUID().toString().replaceAll(ProcessIdUtil.DEFAULT_PROCESSID, "").substring(0, 10);
        }

        private static synchronized String getFakeSerialNumber(Context context) {
            String str;
            synchronized (Builder.class) {
                if (fakeSerialNumber == null) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_UNIQUE_ID, 0);
                    fakeSerialNumber = sharedPreferences.getString(PREF_UNIQUE_ID, null);
                    if (fakeSerialNumber == null) {
                        fakeSerialNumber = generateFakeSerialNumber();
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putString(PREF_UNIQUE_ID, fakeSerialNumber);
                        edit.commit();
                    }
                }
                str = fakeSerialNumber;
            }
            return str;
        }

        public final Builder appVersion(String str) {
            Preconditions.checkArgument(!Strings.isNullOrEmpty(str), "App version cannot be null.");
            this.mAppVersion = str;
            return this;
        }

        public final Builder brand(String str) {
            Preconditions.checkArgument(!Strings.isNullOrEmpty(str), "Brand cannot be null or empty.");
            this.mBrand = str;
            return this;
        }

        public final AndroidDevice build() {
            if (this.mSerialNumber == null) {
                this.mSerialNumber = getFakeSerialNumber(this.mContext);
            }
            if (this.mLocation == null) {
                this.mLocation = DeviceUtils.getLocation(this.mContext);
            }
            if (this.mAppVersion == null) {
                this.mAppVersion = DeviceUtils.getAppVersion(this.mContext);
            }
            if (this.mCarrier == null) {
                this.mCarrier = DeviceUtils.getCarrierName(this.mContext);
            }
            if (this.mConnectedNetworkType == null) {
                this.mConnectedNetworkType = NetworkAnalyzer.getNetworkType(this.mContext);
            }
            return new AndroidDevice(this);
        }

        public final Builder carrier(String str) {
            Preconditions.checkArgument(!Strings.isNullOrEmpty(str), "Carrier cannot be null or empty.");
            this.mCarrier = str;
            return this;
        }

        public final Builder connectedNetworkType(NetworkAnalyzer.NetworkType networkType) {
            this.mConnectedNetworkType = networkType;
            return this;
        }

        public final Builder deviceId(String str) {
            Preconditions.checkArgument(!Strings.isNullOrEmpty(str), "Device id cannot be null or empty.");
            this.mDeviceId = str;
            return this;
        }

        public final Builder location(Location location) {
            Preconditions.checkNotNull(location, "Location cannot be null.");
            this.mLocation = location;
            return this;
        }

        public final Builder manufacturer(String str) {
            Preconditions.checkArgument(!Strings.isNullOrEmpty(str), "Manufactuer cannot be null or empty.");
            this.mManufacturer = str;
            return this;
        }

        public final Builder model(String str) {
            Preconditions.checkArgument(!Strings.isNullOrEmpty(str), "Model cannot be null or empty.");
            this.mModel = str;
            return this;
        }

        public final Builder serialNumber(String str) {
            Preconditions.checkArgument(!Strings.isNullOrEmpty(str), "Device serial number cannot be null or empty.");
            this.mSerialNumber = str;
            return this;
        }
    }

    public String getAppVersion() {
        return this.mAppVersion;
    }

    public String getBrand() {
        return this.mBrand;
    }

    public String getCarrier() {
        return this.mCarrier;
    }

    public NetworkAnalyzer.NetworkType getConnectedNetworkType() {
        return this.mConnectedNetworkType;
    }

    public Context getContext() {
        return this.mContext;
    }

    @Nullable
    public String getId() {
        return this.mDeviceId;
    }

    public Location getLocation() {
        return this.mLocation;
    }

    public String getManufacturer() {
        return this.mManufacturer;
    }

    public String getModel() {
        return this.mModel;
    }

    public String getSerialNumber() {
        return this.mSerialNumber;
    }

    private AndroidDevice(Builder builder) {
        this.mDeviceId = builder.mDeviceId;
        this.mConnectedNetworkType = builder.mConnectedNetworkType;
        this.mLocation = builder.mLocation;
        this.mAppVersion = builder.mAppVersion;
        this.mCarrier = builder.mCarrier;
        this.mSerialNumber = builder.mSerialNumber;
        this.mBrand = builder.mBrand;
        this.mManufacturer = builder.mManufacturer;
        this.mModel = builder.mModel;
        this.mContext = builder.mContext;
    }
}
