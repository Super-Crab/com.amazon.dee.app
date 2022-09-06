package com.amazon.deecomms.contacts.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes12.dex */
public class ContactEmailAddress implements Parcelable {
    public static final Parcelable.Creator<ContactEmailAddress> CREATOR = new Parcelable.Creator<ContactEmailAddress>() { // from class: com.amazon.deecomms.contacts.model.ContactEmailAddress.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ContactEmailAddress mo3649createFromParcel(Parcel parcel) {
            return new ContactEmailAddress(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ContactEmailAddress[] mo3650newArray(int i) {
            return new ContactEmailAddress[i];
        }
    };
    @JsonProperty(ContactProviderContract.EMAIL_ADDRESS_PATH)
    private String emailAddress;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(ContactProviderContract.AddressEntry.COLUMN_ADDRESS_RAW_TYPE)
    private String rawType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("type")
    private EmailAddressType type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ContactEmailAddress.class != obj.getClass()) {
            return false;
        }
        ContactEmailAddress contactEmailAddress = (ContactEmailAddress) obj;
        return Objects.equal(this.type, contactEmailAddress.type) && Objects.equal(this.rawType, contactEmailAddress.rawType) && Objects.equal(this.emailAddress, contactEmailAddress.emailAddress);
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public String getRawType() {
        return this.rawType;
    }

    public EmailAddressType getType() {
        return this.type;
    }

    public int hashCode() {
        return Objects.hashCode(this.type, this.rawType, this.emailAddress);
    }

    public void setEmailAddress(String str) {
        this.emailAddress = str;
    }

    public void setRawType(String str) {
        this.rawType = str;
    }

    public void setType(EmailAddressType emailAddressType) {
        this.type = emailAddressType;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("type", this.type).add(ContactProviderContract.AddressEntry.COLUMN_ADDRESS_RAW_TYPE, this.rawType).add(ContactProviderContract.EMAIL_ADDRESS_PATH, this.emailAddress).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.emailAddress);
        parcel.writeString(this.rawType);
        parcel.writeParcelable(this.type, i);
    }

    public ContactEmailAddress() {
    }

    public ContactEmailAddress(EmailAddressType emailAddressType, String str) {
        this.type = emailAddressType;
        this.rawType = null;
        this.emailAddress = str;
    }

    public ContactEmailAddress(String str, String str2) {
        this.type = null;
        this.rawType = str;
        this.emailAddress = str2;
    }

    private ContactEmailAddress(Parcel parcel) {
        this.emailAddress = parcel.readString();
        this.rawType = parcel.readString();
        this.type = (EmailAddressType) parcel.readParcelable(EmailAddressType.class.getClassLoader());
    }
}
