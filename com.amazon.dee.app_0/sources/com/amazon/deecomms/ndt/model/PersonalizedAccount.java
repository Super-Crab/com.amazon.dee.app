package com.amazon.deecomms.ndt.model;

import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class PersonalizedAccount {
    @JsonProperty("commsId")
    private String commsId;
    @JsonProperty("isChildAccount")
    private boolean isChildAccount;

    public String getCommsId() {
        return this.commsId;
    }

    public boolean isChildAccount() {
        return this.isChildAccount;
    }

    public void setChildAccount(boolean z) {
        this.isChildAccount = z;
    }

    public void setCommsId(String str) {
        this.commsId = str;
    }
}
