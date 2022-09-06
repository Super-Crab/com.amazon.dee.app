package com.amazon.client.metrics.thirdparty;

import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.device.utils.thirdparty.DeviceUtil;
import com.amazon.dp.logger.DPLogger;
import java.security.SecureRandom;
import java.util.Map;
import java.util.UUID;
/* loaded from: classes11.dex */
public class AndroidDeviceInfoManager implements DeviceInfoManager {
    private static final String TAG = "Metrics.AndroidDeviceInfoManager";
    protected static final DPLogger log = new DPLogger();
    protected final DeviceUtil mDeviceUtil;
    protected final MetricsDeviceInfo mMetricsDeviceInfo;

    public AndroidDeviceInfoManager(DeviceUtil deviceUtil) {
        if (deviceUtil != null) {
            this.mDeviceUtil = deviceUtil;
            this.mMetricsDeviceInfo = new MetricsDeviceInfo();
            addToMetricsDeviceInfo("softwareVersion", Build.VERSION.INCREMENTAL);
            addToMetricsDeviceInfo(MetricsConfiguration.HARDWARE, Build.HARDWARE);
            addToMetricsDeviceInfo(MetricsConfiguration.BUILD_TYPE, Build.TYPE);
            addToMetricsDeviceInfo(MetricsConfiguration.PLATFORM, Build.DEVICE);
            addToMetricsDeviceInfo("model", Build.MODEL);
            log.verbose(TAG, "deviceInfo", "softwareVersion", this.mMetricsDeviceInfo.getDeviceInfo("softwareVersion"), MetricsConfiguration.HARDWARE, this.mMetricsDeviceInfo.getDeviceInfo(MetricsConfiguration.HARDWARE), MetricsConfiguration.BUILD_TYPE, this.mMetricsDeviceInfo.getDeviceInfo(MetricsConfiguration.BUILD_TYPE), MetricsConfiguration.PLATFORM, this.mMetricsDeviceInfo.getDeviceInfo(MetricsConfiguration.PLATFORM), "model", this.mMetricsDeviceInfo.getDeviceInfo("model"));
            return;
        }
        throw new IllegalArgumentException("Device Util must not be null.");
    }

    private String getCountryOfResidence() {
        return this.mDeviceUtil.fetchCountryOfResidence();
    }

    private String getDeviceLanguage() {
        return this.mDeviceUtil.fetchDeviceLanguage();
    }

    private String getDeviceMode() {
        return this.mDeviceUtil.fetchDeviceMode();
    }

    private String getOSFileTag() {
        return this.mDeviceUtil.fetchOSFileTag();
    }

    private String getOTAGroup() {
        return this.mDeviceUtil.fetchOTAGroupName();
    }

    private String getPreferredMarketplace() {
        return this.mDeviceUtil.fetchPreferredMarketplace();
    }

    private String getRsmGroup() {
        return this.mDeviceUtil.fetchRsmGroupName();
    }

    @Override // com.amazon.client.metrics.thirdparty.DeviceInfoManager
    public void addToMetricsDeviceInfo(String str, String str2) {
        if (str == null) {
            log.error(TAG, "Not adding key-value to metrics device info as key is null", new Object[0]);
        } else {
            this.mMetricsDeviceInfo.addDeviceInfoData(str, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String createCustomerID() {
        return this.mDeviceUtil.getRandomDigits(9, TextUtils.isEmpty(this.mDeviceUtil.fetchDeviceSerialNumber()) ? new SecureRandom() : new SecureRandom(this.mDeviceUtil.fetchDeviceSerialNumber().getBytes()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String createRandomDSN() {
        try {
            byte[] bArr = new byte[16];
            new SecureRandom(this.mDeviceUtil.fetchDeviceSerialNumber().getBytes()).nextBytes(bArr);
            return Base64.encodeToString(bArr, 2);
        } catch (Exception e) {
            log.error("createRandomDSN", "Failed to create a device id based on the actual DSN. Falling back on UUID", e);
            return UUID.randomUUID().toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String createSessionId() {
        SecureRandom secureRandom = TextUtils.isEmpty(this.mDeviceUtil.fetchDeviceSerialNumber()) ? new SecureRandom() : new SecureRandom(this.mDeviceUtil.fetchDeviceSerialNumber().getBytes());
        return String.format("%s-%s-%s", this.mDeviceUtil.getRandomDigits(3, secureRandom), this.mDeviceUtil.getRandomDigits(7, secureRandom), this.mDeviceUtil.getRandomDigits(7, secureRandom));
    }

    public String getCustomerID() {
        return this.mDeviceUtil.fetchCustomerID();
    }

    @Override // com.amazon.client.metrics.thirdparty.DeviceInfoManager
    public MetricsDeviceInfo getDeviceInfo() {
        addToMetricsDeviceInfo("deviceId", getDeviceSerialNumber());
        addToMetricsDeviceInfo("deviceType", getDeviceType());
        addToMetricsDeviceInfo(MetricsConfiguration.COUNTRY_OF_RESIDENCE, getCountryOfResidence());
        addToMetricsDeviceInfo("MarketplaceID", getPreferredMarketplace());
        addToMetricsDeviceInfo(MetricsConfiguration.DEVICE_LANGUAGE, getDeviceLanguage());
        addToMetricsDeviceInfo(MetricsConfiguration.DEVICE_MODE, getDeviceMode());
        addToMetricsDeviceInfo(MetricsConfiguration.RSM_GROUP, getRsmGroup());
        addToMetricsDeviceInfo(MetricsConfiguration.OTA_GROUP, getOTAGroup());
        addToMetricsDeviceInfo(MetricsConfiguration.OS_FILE_TAG, getOSFileTag());
        addToMetricsDeviceInfo(MetricsConfiguration.SESSION_ID, getSessionID());
        addToMetricsDeviceInfo(MetricsConfiguration.CUSTOMER_ID, getCustomerID());
        addToMetricsDeviceInfo(MetricsConfiguration.IP_ADDRESS, getIPAddress());
        return this.mMetricsDeviceInfo;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getDeviceSerialNumber() {
        return this.mDeviceUtil.fetchDeviceSerialNumberOrAnonymous();
    }

    protected String getDeviceType() {
        return this.mDeviceUtil.fetchDeviceType();
    }

    public String getIPAddress() {
        return this.mDeviceUtil.fetchRemoteIP();
    }

    public String getSessionID() {
        return this.mDeviceUtil.fetchSessionID();
    }

    public AndroidDeviceInfoManager(DeviceUtil deviceUtil, Map<String, String> map) {
        this(deviceUtil);
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                addToMetricsDeviceInfo(entry.getKey(), entry.getValue());
                log.verbose(TAG, "extraInfo", entry.getKey(), entry.getValue());
            }
            return;
        }
        throw new IllegalArgumentException("Extra info must not be null.");
    }
}
