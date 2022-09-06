package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class RecordDevicePossessionIntentInnerBarcodeRequest {
    private String mBarcodeData;

    public RecordDevicePossessionIntentInnerBarcodeRequest(String str) {
        this.mBarcodeData = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && RecordDevicePossessionIntentInnerBarcodeRequest.class == obj.getClass()) {
            return Objects.equal(this.mBarcodeData, ((RecordDevicePossessionIntentInnerBarcodeRequest) obj).mBarcodeData);
        }
        return false;
    }

    public String getBarcodeData() {
        return this.mBarcodeData;
    }

    public int hashCode() {
        return Objects.hashCode(this.mBarcodeData);
    }

    public void setBarcodeData(String str) {
        this.mBarcodeData = str;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("barcodeData", this.mBarcodeData).toString();
    }
}
