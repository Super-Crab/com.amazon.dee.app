package com.amazon.whisperjoin.common.sharedtypes.devices.filters;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.amazon.whisperjoin.common.sharedtypes.devices.DeviceFilter;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetailsV2;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class FilterByDistressState extends DeviceFilter {
    private final String mDistressState;

    public FilterByDistressState(String str) {
        this.mDistressState = str;
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.DeviceFilter
    public DeviceFilter.FilterResult filter(WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails) {
        if (whisperJoinPeripheralDeviceDetails.isDistressed() && Byte.toString(((WhisperJoinPeripheralDeviceDetailsV2) whisperJoinPeripheralDeviceDetails).getDistressCode()).equals(this.mDistressState)) {
            return DeviceFilter.FilterResult.ACCEPT;
        }
        return DeviceFilter.FilterResult.REJECT;
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.DeviceFilter
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" FilterByDistressState{mDistressState='");
        return GeneratedOutlineSupport1.outline91(sb, this.mDistressState, "'}");
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.DeviceFilter
    public void writeToBundle(Bundle bundle) {
        super.writeToBundle(bundle);
        bundle.putInt(DeviceFilter.FilterType.FILTER_TYPE_KEY, DeviceFilter.FilterType.FILTER_BY_DISTRESS_STATE.toInt());
        bundle.putString(DeviceFilter.FilterType.FILTER_VALUE_KEY, this.mDistressState);
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.DeviceFilter
    public void writeToSharedPreferences(SharedPreferences.Editor editor) {
        super.writeToSharedPreferences(editor);
        editor.putInt(DeviceFilter.FilterType.FILTER_TYPE_KEY, DeviceFilter.FilterType.FILTER_BY_DISTRESS_STATE.toInt());
        editor.putString(DeviceFilter.FilterType.FILTER_VALUE_KEY, this.mDistressState);
    }

    public FilterByDistressState(String str, DeviceFilter.BeaconType beaconType) {
        this.mDistressState = str;
        this.mBeaconType = beaconType;
    }
}
