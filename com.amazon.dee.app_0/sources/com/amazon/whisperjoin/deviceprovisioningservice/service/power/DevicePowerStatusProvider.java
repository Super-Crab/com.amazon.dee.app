package com.amazon.whisperjoin.deviceprovisioningservice.service.power;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.amazon.alexa.accessorykit.ModelTransformer;
/* loaded from: classes13.dex */
public class DevicePowerStatusProvider {
    private final Context mContext;

    public DevicePowerStatusProvider(Context context) {
        this.mContext = context;
    }

    public DevicePowerStatus getDevicePowerStatus() {
        Intent registerReceiver = this.mContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        int intExtra = registerReceiver.getIntExtra("status", -1);
        boolean z = intExtra == 2 || intExtra == 5;
        int intExtra2 = registerReceiver.getIntExtra("plugged", -1);
        return new DevicePowerStatus(registerReceiver.getIntExtra(ModelTransformer.KEY_BATTERY_LEVEL, -1) / registerReceiver.getIntExtra(ModelTransformer.KEY_BATTERY_SCALE, -1), z, intExtra2 == 2, intExtra2 == 1);
    }
}
