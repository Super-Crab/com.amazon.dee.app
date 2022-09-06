package com.amazon.whisperjoin.common.sharedtypes.barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.BarcodeFormatException;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class BarcodeDetails implements Parcelable {
    private static final BarcodeParser BARCODE_PARSER = new RegexBarcodeParser();
    public static final Parcelable.ClassLoaderCreator<BarcodeDetails> CREATOR = new Parcelable.ClassLoaderCreator<BarcodeDetails>() { // from class: com.amazon.whisperjoin.common.sharedtypes.barcode.BarcodeDetails.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public BarcodeDetails[] mo5375newArray(int i) {
            if (i >= 0) {
                return new BarcodeDetails[i];
            }
            throw new IllegalArgumentException("Size cannot be negative");
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public BarcodeDetails mo5373createFromParcel(Parcel parcel) {
            if (parcel != null) {
                return new BarcodeDetails(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
            }
            throw new IllegalArgumentException("source cannot be null.");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        /* renamed from: createFromParcel */
        public BarcodeDetails mo5374createFromParcel(Parcel parcel, ClassLoader classLoader) {
            if (parcel != null) {
                return new BarcodeDetails(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
            }
            throw new IllegalArgumentException("source cannot be null.");
        }
    };
    private final String mAmazonBarcodeVersion;
    private final String mPin;
    private final String mProductId;
    private final String mPublicKey;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BarcodeDetails(String str, String str2, String str3, String str4) {
        this.mAmazonBarcodeVersion = str;
        this.mProductId = str2;
        this.mPublicKey = str3;
        this.mPin = str4;
    }

    public static BarcodeDetails createFromContent(String str) throws BarcodeFormatException {
        return BARCODE_PARSER.parse(str);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || BarcodeDetails.class != obj.getClass()) {
            return false;
        }
        BarcodeDetails barcodeDetails = (BarcodeDetails) obj;
        return Objects.equal(this.mAmazonBarcodeVersion, barcodeDetails.mAmazonBarcodeVersion) && Objects.equal(this.mProductId, barcodeDetails.mProductId) && Objects.equal(this.mPublicKey, barcodeDetails.mPublicKey) && Objects.equal(this.mPin, barcodeDetails.mPin);
    }

    public String getAmazonBarcodeVersion() {
        return this.mAmazonBarcodeVersion;
    }

    public String getPin() {
        return this.mPin;
    }

    public String getProductId() {
        return this.mProductId;
    }

    public String getPublicKey() {
        return this.mPublicKey;
    }

    public int hashCode() {
        return Objects.hashCode(this.mAmazonBarcodeVersion, this.mProductId, this.mPublicKey, this.mPin);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mAmazonBarcodeVersion", this.mAmazonBarcodeVersion).add("mProductId", this.mProductId).add("mPublicKey", this.mPublicKey).add("mPin", this.mPin).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeString(this.mAmazonBarcodeVersion);
            parcel.writeString(this.mProductId);
            parcel.writeString(this.mPublicKey);
            parcel.writeString(this.mPin);
            return;
        }
        throw new IllegalArgumentException("Parcel cannot be null");
    }
}
