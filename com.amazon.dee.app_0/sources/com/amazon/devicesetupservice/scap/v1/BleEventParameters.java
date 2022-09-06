package com.amazon.devicesetupservice.scap.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class BleEventParameters extends BleParameters implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.scap.v1.BleEventParameters");
    private ConnectionParameters connectionParameters;
    private ScanData scanData;

    @Override // com.amazon.devicesetupservice.scap.v1.BleParameters
    public boolean equals(Object obj) {
        if (!(obj instanceof BleEventParameters)) {
            return false;
        }
        BleEventParameters bleEventParameters = (BleEventParameters) obj;
        return super.equals(obj) && Helper.equals(this.connectionParameters, bleEventParameters.connectionParameters) && Helper.equals(this.scanData, bleEventParameters.scanData);
    }

    public ConnectionParameters getConnectionParameters() {
        return this.connectionParameters;
    }

    public ScanData getScanData() {
        return this.scanData;
    }

    @Override // com.amazon.devicesetupservice.scap.v1.BleParameters
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.connectionParameters, this.scanData);
    }

    public void setConnectionParameters(ConnectionParameters connectionParameters) {
        this.connectionParameters = connectionParameters;
    }

    public void setScanData(ScanData scanData) {
        this.scanData = scanData;
    }
}
