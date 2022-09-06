package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes13.dex */
public class BarcodeIdentifier {
    private final String mBarcodeData;

    /* loaded from: classes13.dex */
    public static class Builder {
        private String mBarcodeData;

        private void validate() {
            RequestInputValidator.validateBarcodeData(this.mBarcodeData);
        }

        public BarcodeIdentifier build() {
            validate();
            return new BarcodeIdentifier(this);
        }

        public Builder setBarcodeData(String str) {
            this.mBarcodeData = str;
            return this;
        }
    }

    protected BarcodeIdentifier(Builder builder) {
        this.mBarcodeData = builder.mBarcodeData;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && BarcodeIdentifier.class == obj.getClass()) {
            return Objects.equal(this.mBarcodeData, ((BarcodeIdentifier) obj).mBarcodeData);
        }
        return false;
    }

    public String getBarcodeData() {
        return this.mBarcodeData;
    }

    public int hashCode() {
        return Objects.hashCode(this.mBarcodeData);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mBarcodeDatra", this.mBarcodeData).toString();
    }
}
