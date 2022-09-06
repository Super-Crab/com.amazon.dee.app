package com.amazon.deecomms.contacts.model;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes12.dex */
public class ContactInfo {
    private String mCompany;
    private final String mContactId;
    private String mFirstName;
    private String mLastName;
    private String mNickName;
    private String mRelationship;
    private List<ContactPhoneNumber> mPhoneList = new ArrayList();
    private List<ContactEmailAddress> mEmailAddressList = new ArrayList();

    public ContactInfo(String str) {
        this.mContactId = str;
    }

    public void addPhoneNumber(String str, PhoneNumberType phoneNumberType) {
        if (!TextUtils.isEmpty(str)) {
            ContactPhoneNumber contactPhoneNumber = new ContactPhoneNumber();
            contactPhoneNumber.setType(phoneNumberType);
            contactPhoneNumber.setPhoneNumber(str);
            this.mPhoneList.add(contactPhoneNumber);
        }
    }

    public String getCompany() {
        return this.mCompany;
    }

    public String getContactId() {
        return this.mContactId;
    }

    public List<ContactEmailAddress> getEmailAddressList() {
        return this.mEmailAddressList;
    }

    public String getFirstName() {
        return this.mFirstName;
    }

    public String getLastName() {
        return this.mLastName;
    }

    public String getNickName() {
        return this.mNickName;
    }

    public List<ContactPhoneNumber> getPhoneList() {
        return this.mPhoneList;
    }

    public String getRelationship() {
        return this.mRelationship;
    }

    public void setCompany(String str) {
        this.mCompany = str;
    }

    public void setEmailAddressList(@NonNull List<ContactEmailAddress> list) {
        this.mEmailAddressList = list;
    }

    public void setFirstName(String str) {
        this.mFirstName = str;
    }

    public void setLastName(String str) {
        this.mLastName = str;
    }

    public void setNickName(String str) {
        this.mNickName = str;
    }

    public void setPhoneList(@NonNull List<ContactPhoneNumber> list) {
        this.mPhoneList = list;
    }

    public void setRelationship(String str) {
        this.mRelationship = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(" firstName: ");
        outline107.append(this.mFirstName);
        outline107.append(" lastName: ");
        outline107.append(this.mLastName);
        outline107.append(" nickName: ");
        outline107.append(this.mNickName);
        outline107.append(" relationship: ");
        outline107.append(this.mRelationship);
        outline107.append(" company: ");
        outline107.append(this.mCompany);
        outline107.append(" contactId: ");
        outline107.append(this.mContactId);
        for (ContactPhoneNumber contactPhoneNumber : this.mPhoneList) {
            outline107.append(" Phone no: ");
            outline107.append(contactPhoneNumber.getPhoneNumber());
            outline107.append(" Phone type: ");
            outline107.append(contactPhoneNumber.getType());
        }
        for (ContactEmailAddress contactEmailAddress : this.mEmailAddressList) {
            outline107.append(" Email Address: ");
            outline107.append(contactEmailAddress.getEmailAddress());
            outline107.append(" Email Address type: ");
            outline107.append(contactEmailAddress.getType());
        }
        return outline107.toString();
    }
}
