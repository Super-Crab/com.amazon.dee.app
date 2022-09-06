package com.amazon.alexa.biloba.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
/* loaded from: classes6.dex */
public class CareContact implements Parcelable {
    public static final Parcelable.Creator<CareContact> CREATOR = new Parcelable.Creator<CareContact>() { // from class: com.amazon.alexa.biloba.model.CareContact.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public CareContact mo905createFromParcel(Parcel parcel) {
            return new CareContact(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public CareContact[] mo906newArray(int i) {
            return new CareContact[i];
        }
    };
    private String commsId;
    private String firstName;
    private boolean isAlexaEnabled;
    private boolean isCoboEnabled;
    private boolean isCommsEnabled;
    private boolean isDropInEnabled;
    private String lastName;
    private String nickName;
    private String phoneNumber;
    private String phoneNumberType;

    public CareContact() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getCommsId() {
        return this.commsId;
    }

    public String getDisplayName() {
        if (TextUtils.isEmpty(this.nickName)) {
            return this.firstName;
        }
        return this.nickName;
    }

    public String getEnclosedNickName() {
        return TextUtils.isEmpty(this.nickName) ? "" : String.format("(%1$s)", this.nickName);
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

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getPhoneNumberType() {
        return this.phoneNumberType;
    }

    public boolean isAlexaEnabled() {
        return this.isAlexaEnabled;
    }

    public boolean isCoboEnabled() {
        return this.isCoboEnabled;
    }

    public boolean isCommsEnabled() {
        return this.isCommsEnabled;
    }

    public boolean isDropInEnabled() {
        return this.isDropInEnabled;
    }

    public void setAlexaEnabled(boolean z) {
        this.isAlexaEnabled = z;
    }

    public void setCoboEnabled(boolean z) {
        this.isCoboEnabled = z;
    }

    public void setCommsEnabled(boolean z) {
        this.isCommsEnabled = z;
    }

    public void setCommsId(String str) {
        this.commsId = str;
    }

    public void setDropInEnabled(boolean z) {
        this.isDropInEnabled = z;
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

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public void setPhoneNumberType(String str) {
        this.phoneNumberType = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.firstName);
        parcel.writeString(this.lastName);
        parcel.writeString(this.nickName);
        parcel.writeString(this.phoneNumber);
        parcel.writeString(this.phoneNumberType);
        parcel.writeInt(this.isCoboEnabled ? 1 : 0);
        parcel.writeInt(this.isDropInEnabled ? 1 : 0);
        parcel.writeInt(this.isAlexaEnabled ? 1 : 0);
        parcel.writeString(this.commsId);
        parcel.writeInt(this.isCommsEnabled ? 1 : 0);
    }

    public CareContact(Parcel parcel) {
        this.firstName = parcel.readString();
        this.lastName = parcel.readString();
        this.nickName = parcel.readString();
        this.phoneNumber = parcel.readString();
        this.phoneNumberType = parcel.readString();
        boolean z = true;
        this.isCoboEnabled = parcel.readInt() != 0;
        this.isDropInEnabled = parcel.readInt() != 0;
        this.isAlexaEnabled = parcel.readInt() != 0;
        this.commsId = parcel.readString();
        this.isCommsEnabled = parcel.readInt() == 0 ? false : z;
    }
}
