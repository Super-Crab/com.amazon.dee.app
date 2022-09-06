package com.amazon.device.utils.det;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.os.Build;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;
/* loaded from: classes12.dex */
public abstract class AbstractDeviceUtil extends ContentObserver implements DeviceUtil {
    private static final String DEFAULT_CLICKSTREAM_CUSTOMER_ID = "clickstreamCustomerID";
    private static final String DEFAULT_CLICKSTREAM_SESSION_ID = "clickstreamSessionID";
    private static final String SHARED_PREF_FILENAME = "com.amazon.device.utils.det";
    private static final String TAG = "AbstractDeviceUtil";
    private static String sCustomerID;
    private static String sSessionID;
    private String mRemoteIP;
    protected final SharedPreferences mSharedPrefs;
    private String mUserAgent;

    public AbstractDeviceUtil(Context context) {
        super(null);
        if (context != null) {
            this.mSharedPrefs = context.getSharedPreferences(SHARED_PREF_FILENAME, 0);
            return;
        }
        throw new IllegalArgumentException("Context must not be null");
    }

    private void createRemoteIp() {
        this.mRemoteIP = "10.0.0.1";
    }

    private void createUserAgent() {
        if (fetchDeviceType() != null) {
            this.mUserAgent = String.format(Locale.US, "%s:::%s:::%s:::%s", "", "", fetchDeviceType(), Build.VERSION.INCREMENTAL);
        }
    }

    private static String getRandomDigitsUtil(int i, Random random) {
        Locale locale = Locale.US;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("%0");
        outline107.append(String.format(Locale.US, "%dd", Integer.valueOf(i)));
        return String.format(locale, outline107.toString(), Integer.valueOf(random.nextInt(((int) Math.pow(10.0d, i)) - 1)));
    }

    private static synchronized void initializeCustomerID(SharedPreferences sharedPreferences) {
        synchronized (AbstractDeviceUtil.class) {
            sCustomerID = sharedPreferences.getString(DEFAULT_CLICKSTREAM_CUSTOMER_ID, null);
            if (sCustomerID != null) {
                return;
            }
            sCustomerID = getRandomDigitsUtil(9, new Random());
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(DEFAULT_CLICKSTREAM_CUSTOMER_ID, sCustomerID);
            edit.apply();
            String str = "Generated a new CustomerId " + sCustomerID;
        }
    }

    private static synchronized void initializeSessionID(SharedPreferences sharedPreferences) {
        synchronized (AbstractDeviceUtil.class) {
            sSessionID = sharedPreferences.getString(DEFAULT_CLICKSTREAM_SESSION_ID, null);
            if (sSessionID != null) {
                return;
            }
            Random random = new Random();
            sSessionID = String.format(Locale.US, "%s-%s-%s", getRandomDigitsUtil(3, random), getRandomDigitsUtil(7, random), getRandomDigitsUtil(7, random));
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(DEFAULT_CLICKSTREAM_SESSION_ID, sSessionID);
            edit.apply();
            String str = "Generated a new SessionID " + sSessionID;
        }
    }

    @Override // com.amazon.device.utils.det.DeviceUtil
    public String fetchCustomerID() {
        if (sCustomerID == null) {
            initializeCustomerID(this.mSharedPrefs);
        }
        return sCustomerID;
    }

    @Override // com.amazon.device.utils.det.DeviceUtil
    public String fetchRemoteIP() {
        if (this.mRemoteIP == null) {
            createRemoteIp();
        }
        return this.mRemoteIP;
    }

    @Override // com.amazon.device.utils.det.DeviceUtil
    public String fetchSessionID() {
        if (sSessionID == null) {
            initializeSessionID(this.mSharedPrefs);
        }
        return sSessionID;
    }

    @Override // com.amazon.device.utils.det.DeviceUtil
    public String fetchSoftwareVersion() {
        return Build.VERSION.INCREMENTAL;
    }

    @Override // com.amazon.device.utils.det.DeviceUtil
    public String fetchUserAgent() {
        if (this.mUserAgent == null) {
            createUserAgent();
        }
        return this.mUserAgent;
    }

    @Override // com.amazon.device.utils.det.DeviceUtil
    public String getRandomDigits(int i, SecureRandom secureRandom) {
        return getRandomDigitsUtil(i, secureRandom);
    }

    public void shutdown() {
    }
}
