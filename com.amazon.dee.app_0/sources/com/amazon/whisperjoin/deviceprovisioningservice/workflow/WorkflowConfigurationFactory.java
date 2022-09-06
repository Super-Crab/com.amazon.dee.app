package com.amazon.whisperjoin.deviceprovisioningservice.workflow;

import com.amazon.whisperjoin.common.sharedtypes.barcode.BarcodeDetails;
import com.amazon.whisperjoin.common.sharedtypes.devices.DeviceFilter;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.BarcodeFormatException;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.PublicKeyDecompressionException;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
/* loaded from: classes13.dex */
public class WorkflowConfigurationFactory {
    private static final String TAG = "WorkflowConfigurationFactory";

    public static WorkflowConfiguration createDefault() {
        try {
            return WorkflowConfiguration.builder().build();
        } catch (PublicKeyDecompressionException e) {
            WJLog.e(TAG, "Barcode Public Key decompression error happened during non-barcode setup", e);
            return null;
        }
    }

    public static WorkflowConfiguration createWorkflowConfigurationForAllDevices(DeviceFilter.BeaconType beaconType) {
        try {
            return WorkflowConfiguration.builder().withBeaconType(beaconType).build();
        } catch (PublicKeyDecompressionException e) {
            WJLog.e(TAG, "Barcode Public Key decompression error happened during non-barcode setup", e);
            return null;
        }
    }

    public static WorkflowConfiguration createWorkflowConfigurationForPinSetup(String str, DeviceFilter.BeaconType beaconType) throws BarcodeFormatException, PublicKeyDecompressionException {
        if (str != null) {
            BarcodeDetails createFromContent = BarcodeDetails.createFromContent(str);
            return WorkflowConfiguration.builder().withBeaconType(beaconType).withPublicKey(createFromContent.getPublicKey()).withPin(createFromContent.getPin()).withBarcodeString(str).build();
        }
        throw new IllegalArgumentException("barcode can not be null");
    }

    public static WorkflowConfiguration createWorkflowConfigurationForTargetDevice(String str, DeviceFilter.BeaconType beaconType) {
        if (str != null) {
            try {
                return WorkflowConfiguration.builder().withBeaconType(beaconType).withDeviceId(str).build();
            } catch (PublicKeyDecompressionException e) {
                WJLog.e(TAG, "Barcode Public Key decompression error happened during non-barcode setup", e);
                return null;
            }
        }
        throw new IllegalArgumentException("deviceId can not be null");
    }

    public static WorkflowConfiguration createWorkflowConfigurationForTargetDistressedDevice(String str, DeviceFilter.BeaconType beaconType) {
        if (str != null) {
            try {
                return WorkflowConfiguration.builder().withBeaconType(beaconType).withDistressState(str).build();
            } catch (PublicKeyDecompressionException e) {
                WJLog.e(TAG, "Barcode Public Key decompression error happened during non-barcode setup", e);
                return null;
            }
        }
        throw new IllegalArgumentException("distressState can not be null");
    }

    public static WorkflowConfiguration createWorkflowConfigurationForTargetProduct(String str, DeviceFilter.BeaconType beaconType) {
        if (str != null) {
            try {
                return WorkflowConfiguration.builder().withBeaconType(beaconType).withProductId(str).build();
            } catch (PublicKeyDecompressionException e) {
                WJLog.e(TAG, "Barcode Public Key decompression error happened during non-barcode setup", e);
                return null;
            }
        }
        throw new IllegalArgumentException("productId can not be null");
    }

    public static WorkflowConfiguration createWorkflowConfigurationForTargetDevice(String str) {
        return createWorkflowConfigurationForTargetDevice(str, DeviceFilter.BeaconType.OOBE);
    }

    public static WorkflowConfiguration createWorkflowConfigurationForTargetProduct(String str) {
        return createWorkflowConfigurationForTargetProduct(str, DeviceFilter.BeaconType.OOBE);
    }

    public static WorkflowConfiguration createWorkflowConfigurationForPinSetup(String str) throws BarcodeFormatException, PublicKeyDecompressionException {
        return createWorkflowConfigurationForPinSetup(str, DeviceFilter.BeaconType.OOBE);
    }
}
