package com.amazon.whisperjoin.provisionerSDK.configuration.defaults;

import com.amazon.whisperjoin.common.sharedtypes.radios.RadioPreferences;
import com.amazon.whisperjoin.common.sharedtypes.radios.SupportedRadios;
import java.util.concurrent.TimeUnit;
/* loaded from: classes13.dex */
public class BasicAmazonBLERadioPreference implements RadioPreferences.RadioPreference {
    @Override // com.amazon.whisperjoin.common.sharedtypes.radios.RadioPreferences.RadioPreference
    public long getMaximumWaitTime(TimeUnit timeUnit) {
        return TimeUnit.MILLISECONDS.convert(10L, timeUnit);
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.radios.RadioPreferences.RadioPreference
    public long getMinimumSignalStrength() {
        return 10L;
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.radios.RadioPreferences.RadioPreference
    public String getRadioType() {
        return SupportedRadios.BLE.getString();
    }
}
