package com.amazon.alexa.handsfree.devices;

import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.constants.Manufacturer;
import com.amazon.alexa.handsfree.devices.constants.VoiceApp;
import com.amazon.alexa.handsfree.devices.locales.CertifiedLocaleVoiceAppProvider;
import com.amazon.alexa.handsfree.devices.locales.NotificationsSupportedLocales;
import com.amazon.alexa.handsfree.devices.utils.BuildPropertyGetter;
import com.amazon.alexa.handsfree.devices.utils.comparators.VersionComparator;
/* loaded from: classes8.dex */
public class DeviceInformation {
    private static final String PROPERTY_OEM_KEY = "ro.oem.key1";
    public static final String TEST_MODE_DEVICE_TYPE = "A1XB9LI4HT62Q7";
    private final String mCarrier;
    private final CertifiedLocaleVoiceAppProvider mCertifiedLocaleVoiceAppProvider;
    private final String mDevice;
    private final boolean mIsDeviceLaunched;
    private final boolean mIsTestModeDevice;
    private final String mManufacturer;
    private final boolean mMetricsEnabled;
    private final String mModel;
    private final NotificationsSupportedLocales mNotificationsSupportedLocales;
    private final String mProduct;
    private final String mQsEditIntent;
    private final String mType;
    private final VersionComparator mVersionComparator;
    private final VoiceApp mVoiceApp;
    private boolean mIsEdgeSV = false;
    private boolean mIsBSR = false;
    private String mOemKey = null;
    private Integer mSupportedVoiceAppVersion = null;

    public DeviceInformation(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5, @Nullable String str6, boolean z, @Nullable String str7, @NonNull CertifiedLocaleVoiceAppProvider certifiedLocaleVoiceAppProvider, @NonNull NotificationsSupportedLocales notificationsSupportedLocales, @NonNull VoiceApp voiceApp, @Nullable VersionComparator versionComparator, boolean z2) {
        this.mType = str;
        this.mManufacturer = str3;
        this.mProduct = str4;
        this.mDevice = str5;
        this.mCarrier = str6;
        this.mModel = str2;
        this.mMetricsEnabled = z;
        this.mQsEditIntent = str7;
        this.mCertifiedLocaleVoiceAppProvider = certifiedLocaleVoiceAppProvider;
        this.mNotificationsSupportedLocales = notificationsSupportedLocales;
        this.mVoiceApp = voiceApp;
        this.mVersionComparator = versionComparator;
        this.mIsDeviceLaunched = z2;
        this.mIsTestModeDevice = TEST_MODE_DEVICE_TYPE.equals(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean compareByBuildInformation() {
        if (!this.mManufacturer.equalsIgnoreCase(Build.MANUFACTURER) || !this.mProduct.equalsIgnoreCase(Build.PRODUCT) || !this.mDevice.equalsIgnoreCase(Build.DEVICE)) {
            return false;
        }
        String str = this.mOemKey;
        if (str != null && !str.equalsIgnoreCase(getOemKeyProperty())) {
            return false;
        }
        return !Manufacturer.OSCAR.getManufacturer().equalsIgnoreCase(Build.MANUFACTURER) || this.mModel.equalsIgnoreCase(Build.MODEL);
    }

    @Nullable
    public String getCarrier() {
        return this.mCarrier;
    }

    public CertifiedLocaleVoiceAppProvider getCertifiedLocaleVoiceAppProvider() {
        return this.mCertifiedLocaleVoiceAppProvider;
    }

    @NonNull
    public String getDevice() {
        return this.mDevice;
    }

    @NonNull
    public String getManufacturer() {
        return this.mManufacturer;
    }

    @NonNull
    public String getModel() {
        return this.mModel;
    }

    public NotificationsSupportedLocales getNotificationsSupportedLocales() {
        return this.mNotificationsSupportedLocales;
    }

    @VisibleForTesting
    String getOemKeyProperty() {
        return new BuildPropertyGetter().readProperty(PROPERTY_OEM_KEY);
    }

    public String getProduct() {
        return this.mProduct;
    }

    public String getQsEditIntent() {
        return this.mQsEditIntent;
    }

    public Integer getSupportedVoiceAppVersion() {
        return this.mSupportedVoiceAppVersion;
    }

    @NonNull
    public String getType() {
        return this.mType;
    }

    @Nullable
    public VersionComparator getVersionComparator() {
        return this.mVersionComparator;
    }

    @NonNull
    public VoiceApp getVoiceApp() {
        return this.mVoiceApp;
    }

    public boolean hasMetricsEnabled() {
        return this.mMetricsEnabled;
    }

    public boolean isBSR() {
        return this.mIsBSR;
    }

    public boolean isDeviceLaunched() {
        return this.mIsDeviceLaunched;
    }

    public boolean isEdgeSV() {
        return this.mIsEdgeSV;
    }

    public boolean isTestModeDevice() {
        return this.mIsTestModeDevice;
    }

    public DeviceInformation withBSR() {
        this.mIsBSR = true;
        return this;
    }

    public DeviceInformation withEdgeSV() {
        this.mIsEdgeSV = true;
        return this;
    }

    public DeviceInformation withOemKey(@NonNull String str) {
        this.mOemKey = str;
        return this;
    }

    public DeviceInformation withSupportedVoiceAppVersion(@NonNull Integer num) {
        this.mSupportedVoiceAppVersion = num;
        return this;
    }
}
