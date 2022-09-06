package com.amazon.alexa.mobilytics.configuration;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import com.amazon.alexa.mobilytics.identity.MobilyticsDeviceProvider;
import com.amazon.alexa.mobilytics.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import java.util.Locale;
import java.util.TimeZone;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes9.dex */
public class DefaultDeviceConfiguration implements DeviceConfiguration {
    private static final String NETWORK_NOT_CONNECTED = "NOT_CONNECTED";
    private static final String TAG = Log.tag(DefaultDeviceConfiguration.class);
    private final Context context;
    private final MobilyticsDeviceProvider deviceProvider;
    private final DisplayMetrics displayMetrics;
    private final String osType;
    private final String osVersion;

    @Inject
    public DefaultDeviceConfiguration(@NonNull Context context, @NonNull MobilyticsConfiguration mobilyticsConfiguration) {
        String androidVersion;
        String str;
        this.context = (Context) Preconditions.checkNotNull(context);
        try {
            Class.forName("amazon.os.Build$VERSION");
            str = "FIRE_OS";
            androidVersion = getFireOSVersion();
        } catch (ClassNotFoundException unused) {
            androidVersion = getAndroidVersion();
            str = "ANDROID";
        }
        this.deviceProvider = mobilyticsConfiguration.deviceProvider();
        this.osType = str;
        this.osVersion = androidVersion;
        this.displayMetrics = Resources.getSystem().getDisplayMetrics();
    }

    @NonNull
    private String getAndroidVersion() {
        return Build.VERSION.RELEASE;
    }

    @NonNull
    private String getFireOSVersion() {
        try {
            return (String) Class.forName("amazon.os.Build$VERSION").getField("FIREOS").get(null);
        } catch (ClassNotFoundException unused) {
            Log.d(TAG, "Could not find amazon.os.Build$VERSION class.");
            return "Unknown";
        } catch (Exception e) {
            Log.i(TAG, "Could not read FireOS version field - unable to determine FireOS version.", e);
            return "Unknown";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    @Override // com.amazon.alexa.mobilytics.configuration.DeviceConfiguration
    @androidx.annotation.NonNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String country() {
        /*
            r4 = this;
            android.content.Context r0 = r4.context
            java.lang.String r1 = "phone"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0
            if (r0 == 0) goto L1d
            java.lang.String r0 = r0.getSimCountryIso()
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L1d
            java.util.Locale r1 = java.util.Locale.US
            java.lang.String r0 = r0.toUpperCase(r1)
            goto L1e
        L1d:
            r0 = 0
        L1e:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L3f
            java.lang.String r0 = r4.locale()
            java.lang.String r1 = "Unknown"
            boolean r2 = r1.equals(r0)
            if (r2 == 0) goto L31
            return r1
        L31:
            java.lang.String r2 = "_"
            java.lang.String[] r0 = r0.split(r2)
            int r2 = r0.length
            r3 = 1
            if (r2 <= r3) goto L3e
            r0 = r0[r3]
            goto L3f
        L3e:
            r0 = r1
        L3f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.mobilytics.configuration.DefaultDeviceConfiguration.country():java.lang.String");
    }

    @Override // com.amazon.alexa.mobilytics.configuration.DeviceConfiguration
    public String deviceSerialNumber() {
        MobilyticsDeviceProvider mobilyticsDeviceProvider = this.deviceProvider;
        if (mobilyticsDeviceProvider == null || mobilyticsDeviceProvider.device() == null) {
            return "Unknown";
        }
        String deviceSerialNumber = this.deviceProvider.device().deviceSerialNumber();
        return !TextUtils.isEmpty(deviceSerialNumber) ? deviceSerialNumber : "Unknown";
    }

    @Override // com.amazon.alexa.mobilytics.configuration.DeviceConfiguration
    public String deviceType() {
        MobilyticsDeviceProvider mobilyticsDeviceProvider = this.deviceProvider;
        if (mobilyticsDeviceProvider != null && mobilyticsDeviceProvider.device() != null) {
            String deviceType = this.deviceProvider.device().deviceType();
            if (!TextUtils.isEmpty(deviceType)) {
                return deviceType;
            }
        }
        return "Unknown";
    }

    @Override // com.amazon.alexa.mobilytics.configuration.DeviceConfiguration
    @NonNull
    public String language() {
        String locale = locale();
        if ("Unknown".equals(locale)) {
            return "Unknown";
        }
        String[] split = locale.split("_");
        return split.length > 0 ? split[0] : "Unknown";
    }

    @Override // com.amazon.alexa.mobilytics.configuration.DeviceConfiguration
    @NonNull
    public String locale() {
        Locale locale = Locale.getDefault();
        return locale != null ? locale.toString() : "Unknown";
    }

    @Override // com.amazon.alexa.mobilytics.configuration.DeviceConfiguration
    @NonNull
    public String manufacturer() {
        return Build.MANUFACTURER;
    }

    @Override // com.amazon.alexa.mobilytics.configuration.DeviceConfiguration
    @NonNull
    public String model() {
        return Build.MODEL;
    }

    @Override // com.amazon.alexa.mobilytics.configuration.DeviceConfiguration
    @NonNull
    public String networkType() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return "NOT_CONNECTED";
        }
        String typeName = activeNetworkInfo.getTypeName();
        String subtypeName = activeNetworkInfo.getSubtypeName();
        return (subtypeName == null || subtypeName.length() <= 0) ? typeName : GeneratedOutlineSupport1.outline75(typeName, " ", subtypeName);
    }

    @Override // com.amazon.alexa.mobilytics.configuration.DeviceConfiguration
    @NonNull
    public String operatingSystemType() {
        return this.osType;
    }

    @Override // com.amazon.alexa.mobilytics.configuration.DeviceConfiguration
    @NonNull
    public String operatingSystemVersion() {
        return this.osVersion;
    }

    @Override // com.amazon.alexa.mobilytics.configuration.DeviceConfiguration
    @NonNull
    public String platform() {
        return "ANDROID";
    }

    @Override // com.amazon.alexa.mobilytics.configuration.DeviceConfiguration
    @NonNull
    public String platformVersion() {
        return Build.VERSION.RELEASE;
    }

    @Override // com.amazon.alexa.mobilytics.configuration.DeviceConfiguration
    @NonNull
    public float screenDensity() {
        return this.displayMetrics.density;
    }

    @Override // com.amazon.alexa.mobilytics.configuration.DeviceConfiguration
    @NonNull
    public int screenHeight() {
        return this.displayMetrics.heightPixels;
    }

    @Override // com.amazon.alexa.mobilytics.configuration.DeviceConfiguration
    @NonNull
    public int screenWidth() {
        return this.displayMetrics.widthPixels;
    }

    @Override // com.amazon.alexa.mobilytics.configuration.DeviceConfiguration
    public String timezone() {
        String id = TimeZone.getDefault().getID();
        return id != null ? id : "Unknown";
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DeviceConfiguration[");
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("operatingSystemType: ");
        outline1072.append(this.osType);
        outline1072.append("; ");
        outline107.append(outline1072.toString());
        outline107.append("operatingSystemVersion: " + this.osVersion + "; ");
        outline107.append("platform: " + platform() + "; ");
        outline107.append("platformVersion: " + platformVersion() + "; ");
        outline107.append("manufacturer: " + manufacturer() + "; ");
        outline107.append("model: " + model() + "; ");
        outline107.append("locale: " + locale() + "; ");
        outline107.append("language: " + language() + "; ");
        outline107.append("country: " + country() + "; ");
        outline107.append("deviceType: " + this.deviceProvider.device().deviceType() + "; ");
        outline107.append("deviceSerialNumber: " + this.deviceProvider.device().deviceSerialNumber() + "; ");
        outline107.append("timezone: " + timezone() + "; ");
        outline107.append("]");
        return outline107.toString();
    }
}
