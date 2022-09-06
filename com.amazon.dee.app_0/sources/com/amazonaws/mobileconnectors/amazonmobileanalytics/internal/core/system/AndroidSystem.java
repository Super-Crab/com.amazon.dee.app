package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
@Deprecated
/* loaded from: classes13.dex */
public class AndroidSystem implements System {
    private static final String PREFERENCES_AND_FILE_MANAGER_SUFFIX = "515d6767-01b7-49e5-8273-c8d11b0f331d";
    private final AppDetails appDetails;
    private final Connectivity connectivity;
    private final DeviceDetails deviceDetails;
    private final FileManager fileManager;
    private final Preferences preferences;

    public AndroidSystem(Context context, String str) {
        this.preferences = new AndroidPreferences(context, GeneratedOutlineSupport1.outline72(str, PREFERENCES_AND_FILE_MANAGER_SUFFIX));
        this.fileManager = new DefaultFileManager(context.getDir(str + PREFERENCES_AND_FILE_MANAGER_SUFFIX, 0));
        this.connectivity = new AndroidConnectivity(context);
        this.appDetails = new AndroidAppDetails(context, str);
        this.deviceDetails = new AndroidDeviceDetails(getCarrier(context));
    }

    private String getCarrier(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager.getNetworkOperatorName() != null && !telephonyManager.getNetworkOperatorName().equals("")) {
                return telephonyManager.getNetworkOperatorName();
            }
        } catch (Exception unused) {
        }
        return "Unknown";
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.System
    public AppDetails getAppDetails() {
        return this.appDetails;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.System
    public Connectivity getConnectivity() {
        return this.connectivity;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.System
    public DeviceDetails getDeviceDetails() {
        return this.deviceDetails;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.System
    public FileManager getFileManager() {
        return this.fileManager;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.System
    public Preferences getPreferences() {
        return this.preferences;
    }
}
