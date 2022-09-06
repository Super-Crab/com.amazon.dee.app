package com.amazon.identity.frc;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.frc.helper.EncryptionHelper;
import com.amazon.identity.frc.helper.PackageUtils;
import com.amazon.identity.frc.helper.TelephonyManagerUtil;
import com.amazon.identity.frc.helper.WindowManagerUtil;
import com.amazonaws.cognito.clientcontext.datacollection.DataRecordKey;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public final class FrcCookiesManager {
    private static final Set<String> MANDATORY_FRAUD_KEYS = new HashSet();
    private static final String TAG = FrcCookiesManager.class.getName();
    private Context mContext;

    static {
        MANDATORY_FRAUD_KEYS.add(DataRecordKey.APP_NAME);
        MANDATORY_FRAUD_KEYS.add(DataRecordKey.APP_VERSION);
        MANDATORY_FRAUD_KEYS.add(DataRecordKey.MODEL);
        MANDATORY_FRAUD_KEYS.add("DeviceOSVersion");
        MANDATORY_FRAUD_KEYS.add(DataRecordKey.DEVICE_WIDTH);
        MANDATORY_FRAUD_KEYS.add(DataRecordKey.DEVICE_HEIGHT);
        MANDATORY_FRAUD_KEYS.add(DataRecordKey.DEVICE_LANGUAGE);
        MANDATORY_FRAUD_KEYS.add("TimeZone");
    }

    public FrcCookiesManager(Context context) {
        this.mContext = context;
    }

    String getApplicationName() {
        return this.mContext.getPackageName();
    }

    String getApplicationVersion() {
        Integer packageVersion = PackageUtils.getPackageVersion(this.mContext, this.mContext.getPackageName());
        if (packageVersion != null) {
            return Integer.toString(packageVersion.intValue());
        }
        return null;
    }

    String getCarrier(Context context) {
        try {
            return TelephonyManagerUtil.getCarrier(context);
        } catch (Exception e) {
            Log.e(TAG, String.format("Unknown exception happened when try to get Carrier: %s", e.getClass().getName()), e);
            return null;
        }
    }

    String getDeviceLanguage() {
        return Locale.getDefault().toString().replace("_", ProcessIdUtil.DEFAULT_PROCESSID);
    }

    String getDeviceName() {
        return String.format("%s/%s/%s", Build.HARDWARE, Build.MANUFACTURER, Build.MODEL);
    }

    String getDeviceOSVersion() {
        return Build.FINGERPRINT;
    }

    public String getFrcCookies(String str) {
        Log.i(TAG, "Start generating frc cookies");
        JSONObject frcCookiesJson = getFrcCookiesJson();
        if (frcCookiesJson == null) {
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            return frcCookiesJson.toString();
        }
        String encrypt = EncryptionHelper.encrypt(frcCookiesJson.toString(), str);
        Log.i(TAG, "Finish generating frc cookies.");
        return encrypt;
    }

    JSONObject getFrcCookiesJson() {
        JSONObject jSONObject = new JSONObject() { // from class: com.amazon.identity.frc.FrcCookiesManager.1
            @Override // org.json.JSONObject
            public JSONObject put(String str, Object obj) throws JSONException {
                if ((obj == null || TextUtils.isEmpty(obj.toString().trim())) && FrcCookiesManager.MANDATORY_FRAUD_KEYS.contains(str)) {
                    String str2 = FrcCookiesManager.TAG;
                    Log.e(str2, "MissingMandatoryFrcCookie:" + str);
                }
                return (!(obj instanceof String) || !TextUtils.isEmpty((String) obj)) ? super.put(str, obj) : this;
            }
        };
        try {
            jSONObject.put(DataRecordKey.APP_NAME, getApplicationName());
            jSONObject.put(DataRecordKey.APP_VERSION, getApplicationVersion());
            jSONObject.put("DeviceOSVersion", getDeviceOSVersion());
            jSONObject.put(DataRecordKey.MODEL, getDeviceName());
            jSONObject.put(DataRecordKey.THIRD_PARTY_DEVICE_AGENT, getThirdPartyDeviceId(this.mContext));
            jSONObject.put(DataRecordKey.DEVICE_WIDTH, getScreenWidthPixels(this.mContext));
            jSONObject.put(DataRecordKey.DEVICE_HEIGHT, getScreenHeightPixels(this.mContext));
            jSONObject.put(DataRecordKey.DEVICE_LANGUAGE, getDeviceLanguage());
            jSONObject.put("TimeZone", getTimeZone());
            jSONObject.put(DataRecordKey.NETWORK_OPERATOR, getCarrier(this.mContext));
            jSONObject.put("PhoneNumber", getPhoneNumber(this.mContext));
            jSONObject.put("IpAddress", getIpAddress());
            new StringBuilder("Generated FRC cookies: ").append(jSONObject.toString());
            return jSONObject;
        } catch (Exception e) {
            Log.e(TAG, "FRC Cookies Generation Failed", e);
            return null;
        }
    }

    String getIpAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces == null) {
                Log.e(TAG, "Network interfaces is null, this should be a bug!");
                return null;
            }
            String str = null;
            for (NetworkInterface networkInterface : Collections.list(networkInterfaces)) {
                for (InetAddress inetAddress : Collections.list(networkInterface.getInetAddresses())) {
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
                        return inetAddress.getHostAddress();
                    }
                    if (inetAddress.isLoopbackAddress()) {
                        str = inetAddress.getHostAddress().toUpperCase(Locale.US);
                    }
                }
            }
            return str;
        } catch (Exception e) {
            Log.e(TAG, String.format("Exception happened when tring to get ip address: %s", e.getClass().getName()), e);
            return null;
        }
    }

    String getPhoneNumber(Context context) {
        try {
            return TelephonyManagerUtil.getLine1Number(context);
        } catch (SecurityException unused) {
            Log.w(TAG, "Cannot read the phone state on this device, mainly because the client app lacks the permission");
            return null;
        } catch (Exception e) {
            Log.e(TAG, String.format("Unknown exception happened why try to read phone state: %s", e.getClass().getName()), e);
            return null;
        }
    }

    String getScreenHeightPixels(Context context) {
        try {
            return WindowManagerUtil.getWindowHeight(context);
        } catch (Exception e) {
            Log.e(TAG, String.format("Unknown exception happened when try to get screen height: %s", e.getClass().getName()), e);
            return null;
        }
    }

    String getScreenWidthPixels(Context context) {
        try {
            return WindowManagerUtil.getWindowWidth(context);
        } catch (Exception e) {
            Log.e(TAG, String.format("Unknown exception happened when try to get screen width %s", e.getClass().getName()), e);
            return null;
        }
    }

    String getThirdPartyDeviceId(Context context) {
        try {
            ContentResolver contentResolver = context.getContentResolver();
            if (contentResolver == null) {
                Log.i(TAG, "Resolver is null, the 3P id will be null");
                return null;
            }
            return Settings.Secure.getString(contentResolver, "android_id");
        } catch (Exception e) {
            Log.e(TAG, String.format("Unknown error happens when collecting android id: %s", e.getClass().getName()), e);
            return null;
        }
    }

    String getTimeZone() {
        try {
            String format = new SimpleDateFormat("Z", Locale.getDefault()).format(Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault()).getTime());
            return format.substring(0, 3) + ":" + format.substring(3, 5);
        } catch (Exception e) {
            Log.e(TAG, String.format("Unknown exception happened when try to get time zone: %s", e.getClass().getName()), e);
            return null;
        }
    }
}
