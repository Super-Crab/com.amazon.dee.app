package com.amazon.whisperjoin.provisionerSDK.devices;

import com.amazon.whisperbridge.ble.AdvertisementData;
import com.amazon.whisperjoin.common.sharedtypes.ble.BleScanData;
import com.amazon.whisperjoin.common.sharedtypes.devices.DiscoveredRadio;
import com.amazon.whisperjoin.common.sharedtypes.devices.ThirdPartyPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetailsV2;
import com.amazon.whisperjoin.common.sharedtypes.radios.ConnectionFactory;
import com.amazon.whisperjoin.common.sharedtypes.radios.SupportedRadios;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
/* loaded from: classes13.dex */
public class PeripheralDeviceDetailsScanDataProcessor {
    private static final String TAG = "PeripheralDeviceDetailsScanDataProcessor";
    private AdvertisementData.Factory mAdvertisementDataFactory;
    private ConnectionFactory mBleWJConnectionFactory;

    public PeripheralDeviceDetailsScanDataProcessor(ConnectionFactory connectionFactory, AdvertisementData.Factory factory) {
        this.mBleWJConnectionFactory = connectionFactory;
        this.mAdvertisementDataFactory = factory;
    }

    public ThirdPartyPeripheralDeviceDetails attemptToCreateThirdPartyPeripheralDeviceDetails(BleScanData bleScanData) {
        return new ThirdPartyPeripheralDeviceDetails(bleScanData);
    }

    public WhisperJoinPeripheralDeviceDetails attemptToCreateWhisperJoinDetails(BleScanData bleScanData) {
        WhisperJoinPeripheralDeviceDetails.Builder withDistressCode;
        try {
            AdvertisementData create = this.mAdvertisementDataFactory.create(bleScanData.getRawScanRecord());
            if (create == null) {
                return null;
            }
            if (create.getAdvertisementVersion() == 0) {
                withDistressCode = new WhisperJoinPeripheralDeviceDetails.Builder();
            } else if (create.getAdvertisementVersion() == 1) {
                withDistressCode = new WhisperJoinPeripheralDeviceDetailsV2.Builder().withDistressCode(create.getDistressCode());
            } else {
                WJLog.e(TAG, "Unexpected BLE advertisement version");
                return null;
            }
            return withDistressCode.withRadio(new DiscoveredRadio(SupportedRadios.BLE.getString(), bleScanData.getRssi(), bleScanData.getDevice())).withProductIndex(create.getProductIndex()).withDeviceIdentity(create.getDeviceIdentity()).withFriendlyName(create.getDeviceName(), create.getNameType()).withClientNonce(create.getClientNonce()).withSoftwareVersion(create.getSoftwareVersion()).withSupportsUnauthenticatedEcdhe(create.getSupportsUnauthenticatedEcdhe()).withSupportsAuthenticatedEcdhe(create.getSupportsAuthenticatedEcdhe()).withSupportsPin(create.getSupportsPin()).withAdvertisedSdkVersionIndex(create.getAdvertisedSdkVersionIndex()).mo5391build();
        } catch (Exception e) {
            WJLog.e(TAG, "An exception occurred while parsing scan data to create WhisperJoinPeripheralDevice", e);
            return null;
        }
    }
}
