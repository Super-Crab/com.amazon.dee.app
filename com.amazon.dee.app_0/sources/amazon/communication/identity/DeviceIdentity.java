package amazon.communication.identity;

import amazon.communication.identity.EndpointIdentity;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.SecureRandom;
/* loaded from: classes.dex */
public class DeviceIdentity extends EndpointIdentity {
    private static final String CUSTOMER_ID = "customerId";
    private static final String DELIMITER = ":";
    private static final String DEVICE = "device";
    private static final String DEVICE_ACCOUNT_ID = "deviceAccountId";
    private static final String DEVICE_HEADER = "urn:tcomm-endpoint:device";
    private static final String DEVICE_SERIAL_NUMBER = "deviceSerialNumber";
    private static final String DEVICE_TYPE = "deviceType";
    private static final String DIRECTED_ID = "directedId";
    private static final int SEED = new SecureRandom().nextInt();
    private static final String TCOMM_ENDPOINT = "tcomm-endpoint";
    private static final String URN = "urn";
    private boolean isDeviceOnlyIdentity;
    private boolean isDirectedIdDeviceIdentity;
    private boolean isLegacyCustomerDeviceIdentity;
    private boolean isNewCustomerDeviceIdentity;
    private final String mCustomerId;
    private final String mDeviceAccountId;
    private final String mDeviceSerialNumber;
    private final String mDeviceType;
    private final String mDirectedId;

    /* JADX INFO: Access modifiers changed from: package-private */
    @FireOsSdk
    public DeviceIdentity(String str, String str2, String str3, String str4, String str5) {
        if (isLegacyCustomerDeviceArgs(str, str2, str3)) {
            this.mDeviceAccountId = str;
            this.mCustomerId = str2;
            this.mDirectedId = null;
            this.isLegacyCustomerDeviceIdentity = true;
        } else if (isNewCustomerDeviceArgs(str, str2, str3)) {
            this.mDeviceAccountId = null;
            this.mCustomerId = str2;
            this.mDirectedId = null;
            this.isNewCustomerDeviceIdentity = true;
        } else if (isDirectedIdDeviceArgs(str, str2, str3)) {
            this.mDeviceAccountId = null;
            this.mCustomerId = null;
            this.mDirectedId = str3;
            this.isDirectedIdDeviceIdentity = true;
        } else if (isDeviceOnlyArgs(str, str2, str3)) {
            this.mDeviceAccountId = null;
            this.mCustomerId = null;
            this.mDirectedId = null;
            this.isDeviceOnlyIdentity = true;
        } else {
            throw new IllegalArgumentException("Must provide deviceAccountId & customerId, customerId only, directedId only or none of the those three arguments");
        }
        if (str4 != null && str4.trim().length() != 0) {
            if (str5 != null && str5.trim().length() != 0) {
                this.mDeviceType = str4;
                this.mDeviceSerialNumber = str5;
                return;
            }
            throw new IllegalArgumentException("deviceSerialNumber must not be null or empty");
        }
        throw new IllegalArgumentException("deviceType must not be null or empty");
    }

    private boolean equalsDeviceOnlyEndpoint(DeviceIdentity deviceIdentity) {
        return deviceIdentity != null && fieldEquals(this.mDeviceType, deviceIdentity.mDeviceType) && fieldEquals(this.mDeviceSerialNumber, deviceIdentity.mDeviceSerialNumber);
    }

    private boolean equalsDirectedIdDeviceEndpoint(DeviceIdentity deviceIdentity) {
        return deviceIdentity != null && fieldEquals(this.mDeviceType, deviceIdentity.mDeviceType) && fieldEquals(this.mDeviceSerialNumber, deviceIdentity.mDeviceSerialNumber) && fieldEquals(this.mDirectedId, deviceIdentity.mDirectedId);
    }

    private boolean equalsLegacyCustomerDeviceEndpoint(DeviceIdentity deviceIdentity) {
        return deviceIdentity != null && fieldEquals(this.mDeviceType, deviceIdentity.mDeviceType) && fieldEquals(this.mDeviceSerialNumber, deviceIdentity.mDeviceSerialNumber) && fieldEquals(this.mDeviceAccountId, deviceIdentity.mDeviceAccountId) && fieldEquals(this.mCustomerId, deviceIdentity.mCustomerId);
    }

    private boolean equalsNewCustomerDeviceEndpoint(DeviceIdentity deviceIdentity) {
        return deviceIdentity != null && fieldEquals(this.mDeviceType, deviceIdentity.mDeviceType) && fieldEquals(this.mDeviceSerialNumber, deviceIdentity.mDeviceSerialNumber) && fieldEquals(this.mCustomerId, deviceIdentity.mCustomerId);
    }

    private boolean fieldEquals(String str, String str2) {
        return str != null ? str.equals(str2) : str2 == null;
    }

    private static boolean hasValidHeader(String[] strArr) {
        return strArr.length >= 3 && strArr[2].equals("device") && strArr[1].equals(TCOMM_ENDPOINT) && strArr[0].equals(URN);
    }

    private static boolean isDeviceOnlyArgs(String str, String str2, String str3) {
        return (str == null || str.trim().length() == 0) && (str2 == null || str2.trim().length() == 0) && (str3 == null || str3.trim().length() == 0);
    }

    private static boolean isDeviceOnlyEndpoint(String[] strArr) {
        return strArr.length == 7 && strArr[3].equals("deviceType") && !strArr[4].equals("") && strArr[5].equals("deviceSerialNumber") && !strArr[6].equals("");
    }

    private static boolean isDirectedIdDeviceArgs(String str, String str2, String str3) {
        return (str == null || str.trim().length() == 0) && !((str2 != null && str2.trim().length() != 0) || str3 == null || str3.trim().length() == 0);
    }

    private static boolean isDirectedIdDeviceEndpoint(String[] strArr) {
        return strArr.length == 9 && strArr[3].equals("directedId") && !strArr[4].equals("") && strArr[5].equals("deviceType") && !strArr[6].equals("") && strArr[7].equals("deviceSerialNumber") && !strArr[8].equals("");
    }

    private static boolean isLegacyCustomerDeviceArgs(String str, String str2, String str3) {
        return (str == null || str.trim().length() == 0 || str2 == null || str2.trim().length() == 0 || (str3 != null && str3.trim().length() != 0)) ? false : true;
    }

    private static boolean isLegacyCustomerDeviceEndpoint(String[] strArr) {
        return strArr.length == 11 && strArr[3].equals(DEVICE_ACCOUNT_ID) && !strArr[4].equals("") && strArr[5].equals(CUSTOMER_ID) && !strArr[6].equals("") && strArr[7].equals("deviceType") && !strArr[8].equals("") && strArr[9].equals("deviceSerialNumber") && !strArr[10].equals("");
    }

    private static boolean isNewCustomerDeviceArgs(String str, String str2, String str3) {
        return (str == null || str.trim().length() == 0) && str2 != null && str2.trim().length() != 0 && (str3 == null || str3.trim().length() == 0);
    }

    private static boolean isNewCustomerDeviceEndpoint(String[] strArr) {
        return strArr.length == 9 && strArr[3].equals(CUSTOMER_ID) && !strArr[4].equals("") && strArr[5].equals("deviceType") && !strArr[6].equals("") && strArr[7].equals("deviceSerialNumber") && !strArr[8].equals("");
    }

    @FireOsSdk
    public static boolean matchesUrn(String str) {
        String[] split = str.split(":");
        return hasValidHeader(split) && (isDeviceOnlyEndpoint(split) || isLegacyCustomerDeviceEndpoint(split) || isNewCustomerDeviceEndpoint(split) || isDirectedIdDeviceEndpoint(split));
    }

    @Override // amazon.communication.identity.EndpointIdentity
    @FireOsSdk
    public boolean equals(Object obj) {
        if (obj instanceof DeviceIdentity) {
            return equalsDeviceOnlyEndpoint((DeviceIdentity) obj);
        }
        return false;
    }

    @FireOsSdk
    public String getCustomerId() {
        return this.mCustomerId;
    }

    @FireOsSdk
    @Deprecated
    public String getDeviceAccountId() {
        return this.mDeviceAccountId;
    }

    @FireOsSdk
    public String getDeviceSerialNumber() {
        return this.mDeviceSerialNumber;
    }

    @FireOsSdk
    public String getDeviceType() {
        return this.mDeviceType;
    }

    @FireOsSdk
    public String getDirectedId() {
        return this.mDirectedId;
    }

    @Override // amazon.communication.identity.EndpointIdentity
    @FireOsSdk
    public EndpointIdentity.Type getType() {
        return EndpointIdentity.Type.DEVICE;
    }

    @Override // amazon.communication.identity.EndpointIdentity
    @FireOsSdk
    public int hashCode() {
        String str = this.mDeviceType;
        int i = 0;
        int hashCode = (527 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.mDeviceSerialNumber;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    @Override // amazon.communication.identity.EndpointIdentity
    @FireOsSdk
    public String toLogSafeString() {
        int length = String.valueOf(getDeviceSerialNumber().hashCode() + SEED).length() + getDeviceType().length() + 37 + 1 + 18 + 1;
        if (getCustomerId() != null) {
            length += String.valueOf(getCustomerId().hashCode() + SEED).length() + 12;
        }
        if (getDirectedId() != null) {
            length += String.valueOf(getDirectedId().hashCode() + SEED).length() + 12;
        }
        StringBuilder outline105 = GeneratedOutlineSupport1.outline105(length, DEVICE_HEADER);
        if (getCustomerId() != null) {
            GeneratedOutlineSupport1.outline180(outline105, ":", CUSTOMER_ID, ":");
            outline105.append(getCustomerId().hashCode() + SEED);
        }
        if (getDirectedId() != null) {
            GeneratedOutlineSupport1.outline180(outline105, ":", "directedId", ":");
            outline105.append(getDirectedId().hashCode() + SEED);
        }
        outline105.append(":");
        outline105.append("deviceType");
        outline105.append(":");
        outline105.append(getDeviceType());
        GeneratedOutlineSupport1.outline180(outline105, ":", "deviceSerialNumber", ":");
        outline105.append(getDeviceSerialNumber().hashCode() + SEED);
        return outline105.toString();
    }

    @FireOsSdk
    public DeviceIdentity toNonAccountEndpoint() {
        return (this.mDeviceAccountId == null && this.mCustomerId == null && this.mDirectedId == null) ? this : new DeviceIdentity(null, null, null, this.mDeviceType, this.mDeviceSerialNumber);
    }

    @Override // amazon.communication.identity.EndpointIdentity
    @FireOsSdk
    public String toString() {
        int length = getDeviceSerialNumber().length() + getDeviceType().length() + 37 + 1 + 18 + 1;
        if (getCustomerId() != null) {
            length += getCustomerId().length() + 12;
        }
        if (getDirectedId() != null) {
            length += getDirectedId().length() + 12;
        }
        StringBuilder outline105 = GeneratedOutlineSupport1.outline105(length, DEVICE_HEADER);
        if (getCustomerId() != null) {
            outline105.append(":");
            outline105.append(CUSTOMER_ID);
            outline105.append(":");
            outline105.append(getCustomerId());
        }
        if (getDirectedId() != null) {
            outline105.append(":");
            outline105.append("directedId");
            outline105.append(":");
            outline105.append(getDirectedId());
        }
        outline105.append(":");
        outline105.append("deviceType");
        outline105.append(":");
        outline105.append(getDeviceType());
        outline105.append(":");
        outline105.append("deviceSerialNumber");
        outline105.append(":");
        outline105.append(getDeviceSerialNumber());
        return outline105.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DeviceIdentity(String str) {
        if (str != null) {
            String[] split = str.split(":");
            if (hasValidHeader(split)) {
                if (isDeviceOnlyEndpoint(split)) {
                    this.mDeviceAccountId = null;
                    this.mCustomerId = null;
                    this.mDirectedId = null;
                    this.mDeviceType = split[4];
                    this.mDeviceSerialNumber = split[6];
                    this.isDeviceOnlyIdentity = true;
                    return;
                } else if (isLegacyCustomerDeviceEndpoint(split)) {
                    this.mDeviceAccountId = split[4];
                    this.mCustomerId = split[6];
                    this.mDirectedId = null;
                    this.mDeviceType = split[8];
                    this.mDeviceSerialNumber = split[10];
                    this.isLegacyCustomerDeviceIdentity = true;
                    return;
                } else if (isNewCustomerDeviceEndpoint(split)) {
                    this.mDeviceAccountId = null;
                    this.mCustomerId = split[4];
                    this.mDirectedId = null;
                    this.mDeviceType = split[6];
                    this.mDeviceSerialNumber = split[8];
                    this.isNewCustomerDeviceIdentity = true;
                    return;
                } else if (isDirectedIdDeviceEndpoint(split)) {
                    this.mDeviceAccountId = null;
                    this.mCustomerId = null;
                    this.mDirectedId = split[4];
                    this.mDeviceType = split[6];
                    this.mDeviceSerialNumber = split[8];
                    this.isDirectedIdDeviceIdentity = true;
                    return;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Device endpoint URN is not valid: ", str));
                }
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Device endpoint URN is not valid: ", str));
        }
        throw new IllegalArgumentException("Device endpoint must not be null.");
    }
}
