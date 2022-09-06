package com.amazon.client.metrics.thirdparty;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.client.metrics.thirdparty.utils.DeviceCategoryUtil;
import com.amazon.device.utils.thirdparty.DeviceUtil;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
/* loaded from: classes11.dex */
public class OverridingDeviceInfoManager extends AndroidDeviceInfoManager {
    private static final String LEGACY_DEVICE_ID_KEYNAME = "secondaryUUID";
    protected static final String SHARED_PREFS_FILENAME = "com.amazon.client.metrics";
    protected static final DPLogger log = new DPLogger();
    protected String mCachedCustomerID;
    protected String mCachedDSN;
    protected String mCachedSessionID;
    private Context mContext;
    protected String mLegacyDeviceID;
    protected SharedPreferences mSharedPrefs;
    protected String mSharedPrefsCustomerIDKey;
    protected String mSharedPrefsDSNKey;
    protected String mSharedPrefsSessionIDKey;
    private final boolean mUseRealDSN;

    public OverridingDeviceInfoManager(Context context, DeviceUtil deviceUtil, String str, String str2, String str3, boolean z) {
        super(deviceUtil);
        this.mUseRealDSN = z;
        this.mContext = context;
        initialize(context, str, str2, str3, z);
    }

    private String getOrCreateCustomerIDSharedPref(String str) {
        String string = this.mSharedPrefs.getString(str, null);
        log.debug("getOrCreateCustomerIDSharedPref", GeneratedOutlineSupport1.outline72("Obtained CustomerID: ", string), new Object[0]);
        if (TextUtils.isEmpty(string)) {
            log.debug("getOrCreateCustomerIDSharedPref", "CID is empty", new Object[0]);
            String createCustomerID = super.createCustomerID();
            log.debug("getOrCreateCustomerIDSharedPref", GeneratedOutlineSupport1.outline72("Created CustomerID: ", createCustomerID), new Object[0]);
            saveToSharedPref(str, createCustomerID);
            return createCustomerID;
        }
        return string;
    }

    private String getOrCreateDSNSharedPref(String str) {
        String string = this.mSharedPrefs.getString(str, null);
        if (TextUtils.isEmpty(string)) {
            String createRandomDSN = super.createRandomDSN();
            saveToSharedPref(str, createRandomDSN);
            return createRandomDSN;
        }
        return string;
    }

    private String getOrCreateSessionIDSharedPref(String str) {
        String string = this.mSharedPrefs.getString(str, null);
        if (TextUtils.isEmpty(string)) {
            String createSessionId = super.createSessionId();
            saveToSharedPref(str, createSessionId);
            return createSessionId;
        }
        return string;
    }

    private void initialize(Context context, String str, String str2, String str3, boolean z) {
        if (context != null) {
            this.mSharedPrefsDSNKey = str;
            this.mSharedPrefsSessionIDKey = str2;
            this.mSharedPrefsCustomerIDKey = str3;
            this.mSharedPrefs = context.getSharedPreferences(SHARED_PREFS_FILENAME, 0);
            this.mCachedDSN = getOrCreateDSNSharedPref(this.mSharedPrefsDSNKey);
            this.mCachedSessionID = getOrCreateSessionIDSharedPref(this.mSharedPrefsSessionIDKey);
            this.mCachedCustomerID = getOrCreateCustomerIDSharedPref(this.mSharedPrefsCustomerIDKey);
            this.mLegacyDeviceID = getLegacyDSNSharedPref();
            return;
        }
        throw new IllegalArgumentException("Context must not be null.");
    }

    private void saveToSharedPref(String str, String str2) {
        SharedPreferences.Editor edit = this.mSharedPrefs.edit();
        edit.putString(str, str2);
        edit.apply();
    }

    @Override // com.amazon.client.metrics.thirdparty.AndroidDeviceInfoManager
    public String getCustomerID() {
        if (this.mDeviceUtil.isDeviceSerialNumberAnonymous()) {
            if (this.mCachedCustomerID == null) {
                log.debug("getCustomerId", "Cached customer id is null. Creating a new one", new Object[0]);
                this.mCachedCustomerID = getOrCreateCustomerIDSharedPref(this.mSharedPrefsCustomerIDKey);
            }
            return this.mCachedCustomerID;
        }
        return super.getCustomerID();
    }

    @Override // com.amazon.client.metrics.thirdparty.AndroidDeviceInfoManager, com.amazon.client.metrics.thirdparty.DeviceInfoManager
    public MetricsDeviceInfo getDeviceInfo() {
        super.getDeviceInfo();
        if (this.mLegacyDeviceID != null && DeviceCategoryUtil.TABLET.equals(DeviceCategoryUtil.getDeviceCategory(this.mContext))) {
            addToMetricsDeviceInfo(MetricsConfiguration.LEGACY_DEVICE_ID, this.mLegacyDeviceID);
        }
        return this.mMetricsDeviceInfo;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.client.metrics.thirdparty.AndroidDeviceInfoManager
    public String getDeviceSerialNumber() {
        if (this.mUseRealDSN) {
            return this.mDeviceUtil.fetchDeviceSerialNumber();
        }
        if (this.mDeviceUtil.isDeviceSerialNumberAnonymous()) {
            log.debug("getDeviceSerialNumber", "Returning unique device id for non-anonymous metrics", new Object[0]);
            if (this.mCachedDSN == null) {
                this.mCachedDSN = getOrCreateDSNSharedPref(this.mSharedPrefsDSNKey);
            }
            return this.mCachedDSN;
        }
        return super.getDeviceSerialNumber();
    }

    protected String getLegacyDSNSharedPref() {
        return this.mSharedPrefs.getString(LEGACY_DEVICE_ID_KEYNAME, null);
    }

    @Override // com.amazon.client.metrics.thirdparty.AndroidDeviceInfoManager
    public String getSessionID() {
        if (this.mDeviceUtil.isDeviceSerialNumberAnonymous()) {
            log.debug("getSessionID", "Returning unique session id for non-anonymous metrics", new Object[0]);
            if (this.mCachedSessionID == null) {
                this.mCachedSessionID = getOrCreateSessionIDSharedPref(this.mSharedPrefsSessionIDKey);
            }
            return this.mCachedSessionID;
        }
        return super.getSessionID();
    }

    public OverridingDeviceInfoManager(Context context, DeviceUtil deviceUtil, Map<String, String> map, String str, String str2, String str3, boolean z) {
        super(deviceUtil, map);
        this.mUseRealDSN = z;
        this.mContext = context;
        initialize(context, str, str2, str3, z);
    }
}
