package com.amazon.commscore.commsidentity.remote.model;

import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes12.dex */
public class Name {
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME)
    private String nickName;
    @SerializedName(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_FIRST_NAME)
    private String phoneticFirstName;
    @SerializedName(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_LAST_NAME)
    private String phoneticLastName;
    @SerializedName(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PRONUNCIATION_FIRST_NAME)
    private String pronunciationFirstName;
    @SerializedName(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PRONUNCIATION_LAST_NAME)
    private String pronunciationLastName;
    @SerializedName(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_NICK_NAME)
    private String sourceNickName;

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

    public String getPhoneticLastName() {
        return this.phoneticLastName;
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

    public void setPhoneticLastName(String str) {
        this.phoneticLastName = str;
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
}
