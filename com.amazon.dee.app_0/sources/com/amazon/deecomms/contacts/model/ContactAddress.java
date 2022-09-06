package com.amazon.deecomms.contacts.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes12.dex */
public class ContactAddress implements Parcelable {
    public static final Parcelable.Creator<ContactAddress> CREATOR = new Parcelable.Creator<ContactAddress>() { // from class: com.amazon.deecomms.contacts.model.ContactAddress.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ContactAddress mo3646createFromParcel(Parcel parcel) {
            return new ContactAddress(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ContactAddress[] mo3647newArray(int i) {
            return new ContactAddress[i];
        }
    };
    @JsonProperty(ContactProviderContract.AddressEntry.COLUMN_ADDRESS_RAW_TYPE)
    private String rawType;
    @JsonProperty("type")
    private String type;
    @JsonProperty("value")
    private String value;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ContactAddress.class != obj.getClass()) {
            return false;
        }
        ContactAddress contactAddress = (ContactAddress) obj;
        return Objects.equal(this.value, contactAddress.value) && Objects.equal(this.rawType, contactAddress.rawType) && Objects.equal(this.type, contactAddress.type);
    }

    public String getRawType() {
        return this.rawType;
    }

    public String getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        return Objects.hashCode(this.value, this.rawType, this.type);
    }

    public void setRawType(String str) {
        this.rawType = str;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("value", this.value).add(ContactProviderContract.AddressEntry.COLUMN_ADDRESS_RAW_TYPE, this.rawType).add("type", this.type).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.value);
        parcel.writeString(this.rawType);
        parcel.writeString(this.type);
    }

    public ContactAddress() {
    }

    public ContactAddress(String str, String str2, String str3) {
        this.value = str;
        this.rawType = str2;
        this.type = str3;
    }

    private ContactAddress(Parcel parcel) {
        this.value = parcel.readString();
        this.rawType = parcel.readString();
        this.type = parcel.readString();
    }
}
