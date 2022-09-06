package com.amazon.commscore.api.identity;

import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public class Name {
    private String firstName;
    private String lastName;
    private String nickName;
    private String phoneticFirstName;
    private String phoneticLastName;
    private String pronunciationFirstName;
    private String pronunciationLastName;
    private String sourceNickName;

    @Nullable
    public String getFirstName() {
        return this.firstName;
    }

    @Nullable
    public String getLastName() {
        return this.lastName;
    }

    @Nullable
    public String getNickName() {
        return this.nickName;
    }

    @Nullable
    public String getPhoneticFirstName() {
        return this.phoneticFirstName;
    }

    @Nullable
    public String getPhoneticLastName() {
        return this.phoneticLastName;
    }

    @Nullable
    public String getPronunciationFirstName() {
        return this.pronunciationFirstName;
    }

    @Nullable
    public String getPronunciationLastName() {
        return this.pronunciationLastName;
    }

    @Nullable
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
