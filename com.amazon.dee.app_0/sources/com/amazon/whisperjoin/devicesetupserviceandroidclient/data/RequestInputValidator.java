package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import android.util.Base64;
import com.amazon.devicesetupservice.reporting.ProvisioningMethod;
import com.amazon.devicesetupservice.reporting.TrustMethod;
import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
/* loaded from: classes13.dex */
class RequestInputValidator {
    private static final int ADVERTISEMENT_NONCE_LENGTH = 4;
    private static final int AUTH_MATERIAL_INDEX_LENGTH = 12;
    private static final int PRODUCT_INDEX_LENGTH = 4;
    private static final int RSSI_MAX = 0;
    private static final int RSSI_MIN = -255;
    private static final int SOFTWARE_VERSION_INDEX_MIN_LENGTH = 2;
    private static final Set<String> VALID_PROVISIONING_METHODS = Collections.unmodifiableSet(new HashSet(Arrays.asList(ProvisioningMethod.values())));
    private static final Set<String> VALID_TRUST_METHODS = Collections.unmodifiableSet(new HashSet(Arrays.asList(TrustMethod.values())));

    private RequestInputValidator() {
    }

    private static boolean isBase64Encoded(String str) {
        try {
            Base64.decode(str, 0);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private static boolean isStringAllNumericCharacters(String str) {
        return str.matches("[0-9]+");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validateAdvertisedSdkVersionIndex(int i) {
        if (i >= 0) {
            return;
        }
        throw new IllegalArgumentException("AdvertisedSdkVersionIndex cannot be negative");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validateAdvertisementNonce(String str) {
        if (str != null) {
            if (str.length() == 4) {
                if (!isBase64Encoded(str)) {
                    throw new IllegalArgumentException("Advertisement Nonce must be base64 encoded");
                }
                return;
            }
            throw new IllegalArgumentException(String.format(Locale.ENGLISH, "Advertisement Nonce has to be exactly %d characters", 4));
        }
        throw new IllegalArgumentException("Advertisement Nonce can not be null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validateAuthMaterialIndex(String str) {
        if (str != null) {
            if (isBase64Encoded(str)) {
                if (str.length() != 12) {
                    throw new IllegalArgumentException(String.format(Locale.ENGLISH, "Auth Material Index has to be exactly %d characters", 12));
                }
                return;
            }
            throw new IllegalArgumentException("Auth Material Index has to be Base64 encoded");
        }
        throw new IllegalArgumentException("Auth Material Index can not be null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validateBarcodeData(String str) {
        if (str != null) {
            return;
        }
        throw new IllegalArgumentException("Barcode data can not be null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validateDeviceName(String str) {
        if (str != null) {
            return;
        }
        throw new IllegalArgumentException("Device Name can not be null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validateProductIndex(String str) {
        if (str != null) {
            if (str.length() != 4) {
                throw new IllegalArgumentException(String.format(Locale.ENGLISH, "Product Index is required to be exactly %d characters", 4));
            }
            return;
        }
        throw new IllegalArgumentException("Product Index can not be null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validateProvisionerApplicationName(String str) {
        if (str != null) {
            return;
        }
        throw new IllegalArgumentException("Provisioner Application Name can not be null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validateProvisionerApplicationVersion(String str) {
        if (str != null) {
            return;
        }
        throw new IllegalArgumentException("Provisioner Application Version can not be null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validateProvisionerInfo(ProvisionerInfo provisionerInfo) {
        if (provisionerInfo != null) {
            return;
        }
        throw new IllegalArgumentException("ProvisionerInfo can not be null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validateProvisioningMethod(String str) {
        if (str != null) {
            if (!VALID_PROVISIONING_METHODS.contains(str)) {
                throw new IllegalArgumentException("invalid provisioningMethod");
            }
            return;
        }
        throw new IllegalArgumentException("provisioningMethod can not be null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validateRSSI(int i) {
        if (i >= 0 || i < RSSI_MIN) {
            throw new IllegalArgumentException(String.format(Locale.ENGLISH, "RSSI is not within the valid range of %d to %d", 0, Integer.valueOf((int) RSSI_MIN)));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validateSoftwareVersionIndex(String str) {
        if (str != null) {
            if (str.length() >= 2) {
                if (!isStringAllNumericCharacters(str)) {
                    throw new IllegalArgumentException("Software Version Index must be all numeric characters");
                }
                return;
            }
            throw new IllegalArgumentException(String.format(Locale.ENGLISH, "Software Version Index has to be %d or more characters", 2));
        }
        throw new IllegalArgumentException("Software Version Index can not be null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validateTrustMethod(String str) {
        if (str != null) {
            if (!VALID_TRUST_METHODS.contains(str)) {
                throw new IllegalArgumentException("invalid trustMethod");
            }
            return;
        }
        throw new IllegalArgumentException("trustMethod can not be null");
    }
}
