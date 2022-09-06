package com.amazon.whisperjoin.deviceprovisioningservice.ble;

import android.annotation.TargetApi;
import android.bluetooth.le.ScanFilter;
import android.os.ParcelUuid;
import com.amazon.whisperjoin.common.sharedtypes.ble.BleConstants;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes13.dex */
public class ScanFilters {
    private static final byte BOND_STATUS = 5;
    private static final byte PLATFORM_ID = 16;
    public static final String UUID_PHILIPS_ADVERTISEMENT = "0000FE0F-0000-1000-8000-00805f9b34fb";
    private static final ParcelUuid PHILIPS_PARCEL_UUID = ParcelUuid.fromString(UUID_PHILIPS_ADVERTISEMENT);
    private static final byte[] SERVICE_DATA_FILTER_VALUE = {0, 16, 0, 0, 5};
    private static final byte[] SERVICE_DATA_FILTER_MASK = {0, -1, 0, 0, 15};

    @TargetApi(21)
    public static List<ScanFilter> getDefaultScanFilters() {
        return Arrays.asList(new ScanFilter.Builder().setServiceData(BleConstants.WHISPER_JOIN_PARCEL_UUID, new byte[]{0}).build(), new ScanFilter.Builder().setServiceData(BleConstants.WHISPER_JOIN_PARCEL_UUID, new byte[]{1}).build());
    }

    @TargetApi(21)
    public static List<ScanFilter> getScanFiltersForWJandPhilips() {
        return Arrays.asList(new ScanFilter.Builder().setServiceData(BleConstants.WHISPER_JOIN_PARCEL_UUID, new byte[]{0}).build(), new ScanFilter.Builder().setServiceData(BleConstants.WHISPER_JOIN_PARCEL_UUID, new byte[]{1}).build(), new ScanFilter.Builder().setServiceUuid(PHILIPS_PARCEL_UUID).setServiceData(PHILIPS_PARCEL_UUID, SERVICE_DATA_FILTER_VALUE, SERVICE_DATA_FILTER_MASK).build());
    }
}
