package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class RecordDevicePossessionIntentInnerBarcodeInput extends AuthenticatedInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.RecordDevicePossessionIntentInnerBarcodeInput");
    private String barcodeData;

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public boolean equals(Object obj) {
        if (!(obj instanceof RecordDevicePossessionIntentInnerBarcodeInput)) {
            return false;
        }
        return super.equals(obj) && Helper.equals(this.barcodeData, ((RecordDevicePossessionIntentInnerBarcodeInput) obj).barcodeData);
    }

    public String getBarcodeData() {
        return this.barcodeData;
    }

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.barcodeData);
    }

    public void setBarcodeData(String str) {
        this.barcodeData = str;
    }
}
