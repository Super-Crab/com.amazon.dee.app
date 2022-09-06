package com.amazon.communication.devicetodevice;

import amazon.communication.identity.DeviceIdentity;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.EndpointIdentityFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class ApplicationIdentifier {
    private static final String DELIMITER = "_";
    private final String mApplicationName;
    private final DeviceIdentity mDeviceIdentity;

    public ApplicationIdentifier(DeviceIdentity deviceIdentity, String str) {
        if (str != null) {
            if (deviceIdentity != null) {
                if (!str.contains("_")) {
                    this.mDeviceIdentity = deviceIdentity;
                    this.mApplicationName = str;
                    return;
                }
                throw new IllegalArgumentException("application must not contain the following character: _");
            }
            throw new IllegalArgumentException("device must not be null");
        }
        throw new IllegalArgumentException("application must not be null");
    }

    private boolean hasValidFields(String[] strArr) {
        return strArr.length >= 2;
    }

    public String getApplicationName() {
        return this.mApplicationName;
    }

    public DeviceIdentity getDeviceIdentity() {
        return this.mDeviceIdentity;
    }

    public String toString() {
        return this.mDeviceIdentity.toString() + "_" + this.mApplicationName;
    }

    public ApplicationIdentifier(String str) {
        String[] split = str.split("_");
        if (hasValidFields(split)) {
            this.mApplicationName = split[split.length - 1];
            EndpointIdentity createFromUrn = EndpointIdentityFactory.createFromUrn(str.substring(0, str.length() - this.mApplicationName.length()));
            if (createFromUrn instanceof DeviceIdentity) {
                this.mDeviceIdentity = (DeviceIdentity) createFromUrn;
                return;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Application identifier URN is no valid because its EndpointIdentity part does not represent a DeviceIdentity: ", str));
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Application identifier URN is not valid: ", str));
    }
}
