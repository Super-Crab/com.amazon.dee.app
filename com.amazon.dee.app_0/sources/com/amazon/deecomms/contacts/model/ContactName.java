package com.amazon.deecomms.contacts.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes12.dex */
public class ContactName implements Parcelable {
    @JsonIgnore
    public static final Parcelable.Creator<ContactName> CREATOR = new Parcelable.Creator<ContactName>() { // from class: com.amazon.deecomms.contacts.model.ContactName.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ContactName mo3651createFromParcel(Parcel parcel) {
            return new ContactName(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ContactName[] mo3652newArray(int i) {
            return new ContactName[i];
        }
    };
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME)
    private String nickName;
    @JsonProperty(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_FIRST_NAME)
    private String phoneticFirstName;
    private String phoneticFirstNameSortFormat;
    @JsonProperty(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_LAST_NAME)
    private String phoneticLastName;
    private String phoneticLastNameSortFormat;
    @JsonProperty(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PRONUNCIATION_FIRST_NAME)
    private String pronunciationFirstName;
    @JsonProperty(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PRONUNCIATION_LAST_NAME)
    private String pronunciationLastName;
    @JsonProperty(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_NICK_NAME)
    private String sourceNickName;

    public ContactName() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ContactName.class != obj.getClass()) {
            return false;
        }
        ContactName contactName = (ContactName) obj;
        return Objects.equal(this.firstName, contactName.firstName) && Objects.equal(this.lastName, contactName.lastName) && Objects.equal(this.phoneticFirstName, contactName.phoneticFirstName) && Objects.equal(this.phoneticLastName, contactName.phoneticLastName) && Objects.equal(this.pronunciationFirstName, contactName.pronunciationFirstName) && Objects.equal(this.pronunciationLastName, contactName.pronunciationLastName) && Objects.equal(this.nickName, contactName.nickName) && Objects.equal(this.sourceNickName, contactName.sourceNickName);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getNickName() {
        return this.nickName;
    }

    public String getPhoneticFirstName() {
        return this.phoneticFirstName;
    }

    public String getPhoneticFirstNameSortFormat() {
        return this.phoneticFirstNameSortFormat;
    }

    public String getPhoneticLastName() {
        return this.phoneticLastName;
    }

    public String getPhoneticLastNameSortFormat() {
        return this.phoneticLastNameSortFormat;
    }

    public String getPronunciationFirstName() {
        return this.pronunciationFirstName;
    }

    public String getPronunciationLastName() {
        return this.pronunciationLastName;
    }

    public String getSourceNickName() {
        return this.sourceNickName;
    }

    public int hashCode() {
        return Objects.hashCode(this.firstName, this.lastName, this.phoneticFirstName, this.phoneticLastName, this.pronunciationFirstName, this.pronunciationLastName, this.nickName, this.sourceNickName);
    }

    public void setFirstName(String str) {
        this.firstName = str;
    }

    public void setLastName(String str) {
        this.lastName = str;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public void setPhoneticFirstName(String str) {
        this.phoneticFirstName = str;
    }

    public void setPhoneticFirstNameSortFormat(String str) {
        this.phoneticFirstNameSortFormat = str;
    }

    public void setPhoneticLastName(String str) {
        this.phoneticLastName = str;
    }

    public void setPhoneticLastNameSortFormat(String str) {
        this.phoneticLastNameSortFormat = str;
    }

    public void setPronunciationFirstName(String str) {
        this.pronunciationFirstName = str;
    }

    public void setPronunciationLastName(String str) {
        this.pronunciationLastName = str;
    }

    public void setSourceNickName(String str) {
        this.sourceNickName = str;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("first name : ", this.firstName).add("last name : ", this.lastName).add("phonetic first name : ", this.phoneticFirstName).add("phonetic last name : ", this.phoneticLastName).add("pronunciation first name : ", this.pronunciationFirstName).add("pronunciation last name : ", this.pronunciationLastName).add("nick name : ", this.nickName).add("sourceNickName: ", this.sourceNickName).add("phonetic first name sorting format : ", this.phoneticFirstNameSortFormat).add("phonetic last nme sorting format : ", this.phoneticLastNameSortFormat).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.firstName);
        parcel.writeString(this.lastName);
        parcel.writeString(this.phoneticFirstName);
        parcel.writeString(this.phoneticLastName);
        parcel.writeString(this.pronunciationFirstName);
        parcel.writeString(this.pronunciationLastName);
        parcel.writeString(this.nickName);
        parcel.writeString(this.sourceNickName);
        parcel.writeString(this.phoneticFirstNameSortFormat);
        parcel.writeString(this.phoneticLastNameSortFormat);
    }

    public ContactName(String str, String str2, String str3) {
        this(str, str2, null, null, null, null, str3, null);
    }

    public ContactName(String str, String str2, String str3, String str4, String str5) {
        this(str, str2, str3, str4, null, null, str5, null);
    }

    public ContactName(String str, String str2, String str3, String str4, String str5, String str6) {
        this(str, str2, str3, str4, null, null, str5, str6);
    }

    public ContactName(String str, String str2) {
        this(str, str2, null, null, null, null, null, null);
    }

    public ContactName(String str, String str2, String str3, String str4) {
        this(str, str2, str3, str4, null, null, null, null);
    }

    public ContactName(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.firstName = str;
        this.lastName = str2;
        this.phoneticFirstName = str3;
        this.phoneticLastName = str4;
        this.pronunciationFirstName = str5;
        this.pronunciationLastName = str6;
        this.nickName = str7;
        this.sourceNickName = str8;
    }

    @JsonIgnore
    public ContactName(Parcel parcel) {
        this.firstName = parcel.readString();
        this.lastName = parcel.readString();
        this.phoneticFirstName = parcel.readString();
        this.phoneticLastName = parcel.readString();
        this.pronunciationFirstName = parcel.readString();
        this.pronunciationLastName = parcel.readString();
        this.nickName = parcel.readString();
        this.sourceNickName = parcel.readString();
        this.phoneticFirstNameSortFormat = parcel.readString();
        this.phoneticLastNameSortFormat = parcel.readString();
    }
}
