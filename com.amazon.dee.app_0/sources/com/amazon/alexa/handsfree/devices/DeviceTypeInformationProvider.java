package com.amazon.alexa.handsfree.devices;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.constants.Carrier;
import com.amazon.alexa.handsfree.devices.constants.VoiceApp;
import com.amazon.alexa.handsfree.devices.locales.CertifiedLocaleVoiceAppProvider;
import com.amazon.alexa.handsfree.devices.locales.NotificationsSupportedLocales;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes8.dex */
public class DeviceTypeInformationProvider {
    private static final String TAG = "DeviceTypeInfoProvider";
    private static DeviceTypeInformationProvider sInstance;
    private Context mContext;
    private List<DeviceConstant> mDeviceConstants;
    private DeviceInformation mDeviceInformation;
    private String testModeVoiceApp = null;

    @VisibleForTesting
    DeviceTypeInformationProvider(@NonNull Context context) {
        this.mContext = context;
    }

    @NonNull
    public static synchronized DeviceTypeInformationProvider getInstance(@NonNull Context context) {
        DeviceTypeInformationProvider deviceTypeInformationProvider;
        synchronized (DeviceTypeInformationProvider.class) {
            if (sInstance == null) {
                sInstance = new DeviceTypeInformationProvider(context);
            }
            deviceTypeInformationProvider = sInstance;
        }
        return deviceTypeInformationProvider;
    }

    @NonNull
    public synchronized DeviceInformation getDeviceInformationForCurrentDevice(@NonNull Resources resources) {
        DeviceInformation supportedDeviceInformation = getSupportedDeviceInformation();
        if (supportedDeviceInformation != null) {
            return supportedDeviceInformation;
        }
        return new DeviceInformation(resources.getString(R.string.com_amazon_pmet_device_type), Build.MODEL, Build.MANUFACTURER, Build.PRODUCT, Build.DEVICE, Carrier.NO_CARRIER.getCarrier(), false, null, new CertifiedLocaleVoiceAppProvider(R.array.voice_app_certified_locales_default_list, R.string.voice_app_no_default_provided), new NotificationsSupportedLocales(R.array.notifications_supported_locales_default_list), null, null, false);
    }

    @Nullable
    public synchronized DeviceInformation getSupportedDeviceInformation() {
        if (this.mDeviceConstants == null) {
            this.mDeviceConstants = Arrays.asList(DeviceConstant.values());
            Iterator<DeviceConstant> it2 = this.mDeviceConstants.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                DeviceConstant next = it2.next();
                if (next.getDeviceInformation().compareByBuildInformation()) {
                    String.format("DeviceType found for Manufacturer %s - Device: %s - Product: %s.", Build.MANUFACTURER, Build.DEVICE, Build.PRODUCT);
                    this.mDeviceInformation = next.getDeviceInformation();
                    break;
                }
            }
        }
        if (this.mDeviceInformation == null) {
            Log.w(TAG, String.format("DeviceType not found for Manufacturer %s - Device: %s - Product: %s.", Build.MANUFACTURER, Build.DEVICE, Build.PRODUCT));
        }
        return this.mDeviceInformation;
    }

    @NonNull
    public synchronized List<DeviceInformation> getSupportedDevicesList() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (DeviceConstant deviceConstant : DeviceConstant.values()) {
            arrayList.add(deviceConstant.getDeviceInformation());
        }
        return arrayList;
    }

    DeviceInformation getTestModeDeviceInformation(@NonNull String str) {
        VoiceApp voiceApp;
        if (VoiceApp.QUEBEC.getPackageName().equals(str)) {
            voiceApp = VoiceApp.QUEBEC;
        } else {
            voiceApp = VoiceApp.METRO.getPackageName().equals(str) ? VoiceApp.METRO : null;
        }
        VoiceApp voiceApp2 = voiceApp;
        Log.i(TAG, "TestMode voice app package is: " + voiceApp2);
        return new DeviceInformation(DeviceInformation.TEST_MODE_DEVICE_TYPE, Build.MODEL, Build.MANUFACTURER, Build.PRODUCT, Build.DEVICE, Carrier.NO_CARRIER.getCarrier(), false, null, new CertifiedLocaleVoiceAppProvider(R.array.voice_app_certified_locales_default_list, R.string.voice_app_no_default_provided), new NotificationsSupportedLocales(R.array.notifications_supported_locales_default_list), voiceApp2, null, true).withEdgeSV();
    }

    @NonNull
    public synchronized DeviceInformation getDeviceInformationForCurrentDevice(@NonNull Context context) {
        return getDeviceInformationForCurrentDevice(context.getResources());
    }

    @Nullable
    public synchronized DeviceInformation getSupportedDeviceInformation(@Nullable Context context) {
        if (this.mDeviceConstants == null) {
            this.mDeviceConstants = Arrays.asList(DeviceConstant.values());
            Iterator<DeviceConstant> it2 = this.mDeviceConstants.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                DeviceConstant next = it2.next();
                if (next.getDeviceInformation().compareByBuildInformation()) {
                    String.format("DeviceType found for Manufacturer %s - Device: %s - Product: %s.", Build.MANUFACTURER, Build.DEVICE, Build.PRODUCT);
                    this.mDeviceInformation = next.getDeviceInformation();
                    break;
                }
            }
        }
        if (this.mDeviceInformation == null) {
            Log.w(TAG, String.format("DeviceType not found for Manufacturer %s - Device: %s - Product: %s.", Build.MANUFACTURER, Build.DEVICE, Build.PRODUCT));
            if (context != null && this.testModeVoiceApp == null) {
                this.testModeVoiceApp = TestModeVoiceAppProvider.getInstance(context).getTestModeVoiceApp();
            }
            if (this.testModeVoiceApp != null) {
                this.mDeviceInformation = getTestModeDeviceInformation(this.testModeVoiceApp);
            }
        }
        return this.mDeviceInformation;
    }
}
