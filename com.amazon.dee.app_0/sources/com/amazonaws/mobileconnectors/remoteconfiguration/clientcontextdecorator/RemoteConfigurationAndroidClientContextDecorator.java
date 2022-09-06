package com.amazonaws.mobileconnectors.remoteconfiguration.clientcontextdecorator;

import android.content.Context;
import android.util.Log;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.amazonaws.mobileconnectors.remoteconfiguration.RemoteConfigurationManager;
/* loaded from: classes13.dex */
public class RemoteConfigurationAndroidClientContextDecorator {
    public static final String BUILD_NUMBER = "BuildNumber";
    public static final String BUILD_TYPE = "BuildType";
    public static final String DEVICE_CODE_NAME = "DeviceCodeName";
    public static final String DEVICE_SERIAL_NUMBER = "DeviceSerialNumber";
    public static final String DEVICE_TYPE = "DeviceType";
    private static final String TAG = "com.amazonaws.mobileconnectors.remoteconfiguration.clientcontextdecorator.RemoteConfigurationAndroidClientContextDecorator";
    private final MapR5Wrapper mMapR5Wrapper;
    private final SystemPropertiesWrapper mSystemPropertiesWrapper;

    public RemoteConfigurationAndroidClientContextDecorator(Context context) {
        this(new MapR5Wrapper(context), new SystemPropertiesWrapper());
    }

    private boolean areMapR5AttributesAlreadySet(Attributes attributes) {
        return (attributes.getString("DeviceType") == null || attributes.getString(DEVICE_SERIAL_NUMBER) == null) ? false : true;
    }

    private boolean areSystemAttributesAlreadySet(Attributes attributes) {
        return (attributes.getString(DEVICE_CODE_NAME) == null || attributes.getLong(BUILD_NUMBER) == null || attributes.getString("BuildType") == null) ? false : true;
    }

    private void setMapR5Attributes(Attributes attributes) {
        if (!this.mMapR5Wrapper.isMapR5Present()) {
            Log.i(TAG, "No MapR5 Present, not augmenting ARCUS client context attributes");
        } else if (areMapR5AttributesAlreadySet(attributes)) {
        } else {
            Log.i(TAG, "Augmenting ARCUS client context with MAP R5 attributes");
            if (this.mMapR5Wrapper.getDeviceType() != null) {
                attributes.addAttribute("DeviceType", this.mMapR5Wrapper.getDeviceType());
            } else {
                Log.i(TAG, "MapR5 is present, but DeviceType is missing.");
            }
            if (this.mMapR5Wrapper.getDeviceSerialNumber() != null) {
                attributes.addAttribute(DEVICE_SERIAL_NUMBER, this.mMapR5Wrapper.getDeviceSerialNumber());
            } else {
                Log.i(TAG, "MapR5 is present, but DeviceSerialNumber is missing.");
            }
        }
    }

    private void setSystemAttributes(Attributes attributes) {
        if (!this.mSystemPropertiesWrapper.isSystemPropertiesPresent()) {
            Log.i(TAG, "No SystemProperties present, not augmenting ARCUS client context attributes");
        } else if (areSystemAttributesAlreadySet(attributes)) {
        } else {
            Log.i(TAG, "Augmenting ARCUS client context with system attributes");
            if (this.mSystemPropertiesWrapper.getProductName() != null) {
                attributes.addAttribute(DEVICE_CODE_NAME, this.mSystemPropertiesWrapper.getProductName());
            } else {
                Log.i(TAG, "SystemProperties is present, but DeviceCodeName is missing.");
            }
            if (this.mSystemPropertiesWrapper.getBuildType() != null) {
                attributes.addAttribute("BuildType", this.mSystemPropertiesWrapper.getBuildType());
            } else {
                Log.i(TAG, "SystemProperties is present, but BuildType is missing.");
            }
            if (this.mSystemPropertiesWrapper.getVersionNumber() != null) {
                attributes.addAttribute(BUILD_NUMBER, this.mSystemPropertiesWrapper.getVersionNumber());
            } else {
                Log.i(TAG, "SystemProperties is present, but BuildNumber is missing.");
            }
        }
    }

    public RemoteConfigurationManager decorate(RemoteConfigurationManager remoteConfigurationManager) {
        Attributes openAttributes = remoteConfigurationManager.openAttributes();
        setMapR5Attributes(openAttributes);
        setSystemAttributes(openAttributes);
        return remoteConfigurationManager;
    }

    RemoteConfigurationAndroidClientContextDecorator(MapR5Wrapper mapR5Wrapper, SystemPropertiesWrapper systemPropertiesWrapper) {
        this.mMapR5Wrapper = mapR5Wrapper;
        this.mSystemPropertiesWrapper = systemPropertiesWrapper;
    }
}
