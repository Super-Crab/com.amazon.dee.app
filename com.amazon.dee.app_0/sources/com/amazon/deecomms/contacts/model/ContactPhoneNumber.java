package com.amazon.deecomms.contacts.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes12.dex */
public class ContactPhoneNumber implements Parcelable {
    public static final Parcelable.Creator<ContactPhoneNumber> CREATOR = new Parcelable.Creator<ContactPhoneNumber>() { // from class: com.amazon.deecomms.contacts.model.ContactPhoneNumber.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ContactPhoneNumber mo3653createFromParcel(Parcel parcel) {
            return new ContactPhoneNumber(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ContactPhoneNumber[] mo3654newArray(int i) {
            return new ContactPhoneNumber[i];
        }
    };
    @JsonProperty("contactSource")
    private String contactSource;
    @JsonProperty("isCOBOEnabled")
    private boolean isCOBOEnabled;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("obfuscatedPhoneNumber")
    private String obfuscatedPhoneNumber;
    @JsonProperty("number")
    private String phoneNumber;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(ContactProviderContract.AddressEntry.COLUMN_ADDRESS_RAW_TYPE)
    private String rawType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("type")
    private PhoneNumberType type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ContactPhoneNumber.class != obj.getClass()) {
            return false;
        }
        ContactPhoneNumber contactPhoneNumber = (ContactPhoneNumber) obj;
        return Objects.equal(this.type, contactPhoneNumber.type) && Objects.equal(this.rawType, contactPhoneNumber.rawType) && Objects.equal(this.phoneNumber, contactPhoneNumber.phoneNumber) && Objects.equal(this.obfuscatedPhoneNumber, contactPhoneNumber.obfuscatedPhoneNumber) && Objects.equal(Boolean.valueOf(this.isCOBOEnabled), Boolean.valueOf(contactPhoneNumber.isCOBOEnabled)) && Objects.equal(this.contactSource, contactPhoneNumber.contactSource);
    }

    public String getContactSource() {
        return this.contactSource;
    }

    public String getObfuscatedPhoneNumber() {
        return this.obfuscatedPhoneNumber;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getRawType() {
        return this.rawType;
    }

    public PhoneNumberType getType() {
        return this.type;
    }

    public int hashCode() {
        return Objects.hashCode(this.type, this.rawType, this.phoneNumber, this.obfuscatedPhoneNumber, Boolean.valueOf(this.isCOBOEnabled), this.contactSource);
    }

    public boolean isCOBOEnabled() {
        return this.isCOBOEnabled;
    }

    public void setCoboEnabled(boolean z) {
        this.isCOBOEnabled = z;
    }

    public void setContactSource(String str) {
        this.contactSource = str;
    }

    public void setObfuscatedPhoneNumber(String str) {
        this.obfuscatedPhoneNumber = str;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public void setRawType(String str) {
        this.rawType = str;
    }

    public void setType(PhoneNumberType phoneNumberType) {
        this.type = phoneNumberType;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("type", this.type).add(ContactProviderContract.AddressEntry.COLUMN_ADDRESS_RAW_TYPE, this.rawType).add("phoneNumber", this.phoneNumber).add("obfuscatedPhoneNumber", this.obfuscatedPhoneNumber).add("isCOBOEnabled", this.isCOBOEnabled).add("contactSource", this.contactSource).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.phoneNumber);
        String str = this.rawType;
        if (str == null) {
            str = "";
        }
        parcel.writeString(str);
        parcel.writeParcelable(this.type, i);
        parcel.writeString(this.obfuscatedPhoneNumber);
        parcel.writeInt(this.isCOBOEnabled ? 1 : 0);
        parcel.writeString(this.contactSource);
    }

    public ContactPhoneNumber() {
        this.isCOBOEnabled = false;
        this.contactSource = "";
    }

    public ContactPhoneNumber(@NonNull ContactPhoneNumber contactPhoneNumber) {
        this.isCOBOEnabled = false;
        this.contactSource = "";
        this.type = contactPhoneNumber.getType();
        this.rawType = contactPhoneNumber.getRawType();
        this.phoneNumber = contactPhoneNumber.getPhoneNumber();
        this.obfuscatedPhoneNumber = contactPhoneNumber.getObfuscatedPhoneNumber();
        this.isCOBOEnabled = contactPhoneNumber.isCOBOEnabled();
        this.contactSource = contactPhoneNumber.getContactSource();
    }

    public ContactPhoneNumber(PhoneNumberType phoneNumberType, String str) {
        this.isCOBOEnabled = false;
        this.contactSource = "";
        this.type = phoneNumberType;
        this.rawType = null;
        this.phoneNumber = str;
        this.contactSource = ContactSourceType.UnsetContact.getName();
    }

    public ContactPhoneNumber(String str, String str2) {
        this.isCOBOEnabled = false;
        this.contactSource = "";
        this.type = null;
        this.rawType = str;
        this.phoneNumber = str2;
        this.contactSource = ContactSourceType.UnsetContact.getName();
    }

    private ContactPhoneNumber(Parcel parcel) {
        boolean z = false;
        this.isCOBOEnabled = false;
        this.contactSource = "";
        this.phoneNumber = parcel.readString();
        this.rawType = parcel.readString();
        this.type = (PhoneNumberType) parcel.readParcelable(PhoneNumberType.class.getClassLoader());
        this.obfuscatedPhoneNumber = parcel.readString();
        this.isCOBOEnabled = parcel.readInt() != 0 ? true : z;
        this.contactSource = parcel.readString();
    }
}
