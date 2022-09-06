package com.amazon.whisperjoin.common.sharedtypes.devices.filters;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.amazon.whisperjoin.common.sharedtypes.devices.DeviceFilter;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes13.dex */
public class FilterByProductIndex extends DeviceFilter {
    private final String mProductIndex;

    public FilterByProductIndex(String str) {
        this.mProductIndex = str;
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.DeviceFilter
    public DeviceFilter.FilterResult filter(WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails) {
        if (whisperJoinPeripheralDeviceDetails.getProductIndex().equals(this.mProductIndex)) {
            return DeviceFilter.FilterResult.ACCEPT;
        }
        return DeviceFilter.FilterResult.REJECT;
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.DeviceFilter
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" FilterByProductIndex{mProductIndex='");
        return GeneratedOutlineSupport1.outline90(sb, this.mProductIndex, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.DeviceFilter
    public void writeToBundle(Bundle bundle) {
        super.writeToBundle(bundle);
        bundle.putInt(DeviceFilter.FilterType.FILTER_TYPE_KEY, DeviceFilter.FilterType.FILTER_BY_PRODUCT_INDEX.toInt());
        bundle.putString(DeviceFilter.FilterType.FILTER_VALUE_KEY, this.mProductIndex);
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.DeviceFilter
    public void writeToSharedPreferences(SharedPreferences.Editor editor) {
        super.writeToSharedPreferences(editor);
        editor.putInt(DeviceFilter.FilterType.FILTER_TYPE_KEY, DeviceFilter.FilterType.FILTER_BY_PRODUCT_INDEX.toInt());
        editor.putString(DeviceFilter.FilterType.FILTER_VALUE_KEY, this.mProductIndex);
    }

    public FilterByProductIndex(String str, DeviceFilter.BeaconType beaconType) {
        this.mProductIndex = str;
        this.mBeaconType = beaconType;
    }
}
