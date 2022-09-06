package com.amazon.whisperjoin.provisionerSDK.utility;

import android.util.Base64;
import com.amazon.whisperjoin.common.sharedtypes.ble.BleScanData;
import com.amazon.whisperjoin.common.sharedtypes.devices.DiscoveredRadio;
import com.amazon.whisperjoin.common.sharedtypes.devices.ThirdPartyPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.radios.DiscoveryService;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.provisionerSDK.devices.PeripheralDeviceDetailsScanDataProcessor;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
/* loaded from: classes13.dex */
public class BleScanDataCoalescer implements DiscoveryService.BleScanDataCoalescer {
    private static final String TAG = "BleScanDataCoalescer";
    private final PeripheralDeviceDetailsScanDataProcessor mDeviceDetailsScanDataProcessor;
    private final Table<String, String, WhisperJoinPeripheralDeviceDetails> mDiscoveredWhisperJoinDevices = HashBasedTable.create();
    private final Table<String, String, ThirdPartyPeripheralDeviceDetails> mThirdPartyPeripheralDeviceDetailsTable = HashBasedTable.create();

    public BleScanDataCoalescer(PeripheralDeviceDetailsScanDataProcessor peripheralDeviceDetailsScanDataProcessor) {
        this.mDeviceDetailsScanDataProcessor = peripheralDeviceDetailsScanDataProcessor;
    }

    private DiscoveryService.DiscoveryResult processThirdPartyDevice(ThirdPartyPeripheralDeviceDetails thirdPartyPeripheralDeviceDetails) {
        String macAddress = thirdPartyPeripheralDeviceDetails.getMacAddress();
        String encodeToString = Base64.encodeToString(thirdPartyPeripheralDeviceDetails.getScanRecord(), 2);
        ThirdPartyPeripheralDeviceDetails thirdPartyPeripheralDeviceDetails2 = this.mThirdPartyPeripheralDeviceDetailsTable.get(macAddress, encodeToString);
        if (thirdPartyPeripheralDeviceDetails2 == null) {
            this.mThirdPartyPeripheralDeviceDetailsTable.put(macAddress, encodeToString, thirdPartyPeripheralDeviceDetails);
            return new DiscoveryService.DiscoveryResult(DiscoveryService.Event.THIRD_PARTY_ADDED, thirdPartyPeripheralDeviceDetails);
        }
        thirdPartyPeripheralDeviceDetails2.updateScanDataRadioData(thirdPartyPeripheralDeviceDetails);
        return new DiscoveryService.DiscoveryResult(DiscoveryService.Event.THIRD_PARTY_UPDATED, thirdPartyPeripheralDeviceDetails2);
    }

    private DiscoveryService.DiscoveryResult processWhisperJoinDevice(WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails) {
        if (StringUtils.isEmpty(whisperJoinPeripheralDeviceDetails.getFriendlyName())) {
            WJLog.d(TAG, String.format("Ignoring device due to missing name in advertisement data. PID: %s AuthID: %s", whisperJoinPeripheralDeviceDetails.getProductIndex(), whisperJoinPeripheralDeviceDetails.getDeviceIdentity()));
            return null;
        }
        WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails2 = this.mDiscoveredWhisperJoinDevices.get(whisperJoinPeripheralDeviceDetails.getDeviceIdentity(), whisperJoinPeripheralDeviceDetails.getClientNonce());
        if (whisperJoinPeripheralDeviceDetails2 == null) {
            this.mDiscoveredWhisperJoinDevices.put(whisperJoinPeripheralDeviceDetails.getDeviceIdentity(), whisperJoinPeripheralDeviceDetails.getClientNonce(), whisperJoinPeripheralDeviceDetails);
            return new DiscoveryService.DiscoveryResult(DiscoveryService.Event.WJ_DEVICE_ADDED, whisperJoinPeripheralDeviceDetails);
        }
        Set<DiscoveredRadio> radios = whisperJoinPeripheralDeviceDetails.getRadios();
        if (radios.size() == 1) {
            whisperJoinPeripheralDeviceDetails2.addRadio(radios.iterator().next());
            return new DiscoveryService.DiscoveryResult(DiscoveryService.Event.WJ_DEVICE_UPDATED, whisperJoinPeripheralDeviceDetails2);
        }
        throw new RuntimeException("A newly discovered device should only have one radio");
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.radios.DiscoveryService.BleScanDataCoalescer
    public DiscoveryService.DiscoveryResult coalesce(BleScanData bleScanData) {
        synchronized (this.mDiscoveredWhisperJoinDevices) {
            WhisperJoinPeripheralDeviceDetails attemptToCreateWhisperJoinDetails = this.mDeviceDetailsScanDataProcessor.attemptToCreateWhisperJoinDetails(bleScanData);
            if (attemptToCreateWhisperJoinDetails != null) {
                return processWhisperJoinDevice(attemptToCreateWhisperJoinDetails);
            }
            return processThirdPartyDevice(this.mDeviceDetailsScanDataProcessor.attemptToCreateThirdPartyPeripheralDeviceDetails(bleScanData));
        }
    }
}
