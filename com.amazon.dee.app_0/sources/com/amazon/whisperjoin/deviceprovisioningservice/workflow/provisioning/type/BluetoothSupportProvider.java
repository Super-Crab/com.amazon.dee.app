package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
/* loaded from: classes13.dex */
public interface BluetoothSupportProvider {

    /* loaded from: classes13.dex */
    public static class DefaultBluetoothSupportProvider implements BluetoothSupportProvider {
        private final Context mContext;

        public DefaultBluetoothSupportProvider(Context context) {
            this.mContext = context;
        }

        @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.BluetoothSupportProvider
        public boolean isBluetoothEnabled() {
            return BluetoothAdapter.getDefaultAdapter().isEnabled();
        }

        @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.BluetoothSupportProvider
        public boolean isBluetoothLowEnergySupported() {
            return this.mContext.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
        }
    }

    boolean isBluetoothEnabled();

    boolean isBluetoothLowEnergySupported();
}
