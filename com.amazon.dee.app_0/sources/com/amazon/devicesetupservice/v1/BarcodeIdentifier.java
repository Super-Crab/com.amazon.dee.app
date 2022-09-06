package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetupservice.ProvisioneeIdentifier;
/* loaded from: classes12.dex */
public class BarcodeIdentifier extends ProvisioneeIdentifier implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.BarcodeIdentifier");
    private String barcodeData;

    @Override // com.amazon.devicesetupservice.ProvisioneeIdentifier
    public boolean equals(Object obj) {
        if (!(obj instanceof BarcodeIdentifier)) {
            return false;
        }
        return super.equals(obj) && Helper.equals(this.barcodeData, ((BarcodeIdentifier) obj).barcodeData);
    }

    public String getBarcodeData() {
        return this.barcodeData;
    }

    @Override // com.amazon.devicesetupservice.ProvisioneeIdentifier
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.barcodeData);
    }

    public void setBarcodeData(String str) {
        this.barcodeData = str;
    }
}
