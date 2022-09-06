package com.amazon.commscore.commsidentity.repo.model;
/* loaded from: classes12.dex */
public class IdentityV2 {
    private String aor;
    private String commsId;
    private String commsProvisionStatus;
    private String hashedCommsId;
    private String homeGroupId;
    private String homeGroupPceId;
    private Name name;
    private String pceId;

    public String getAor() {
        return this.aor;
    }

    public String getCommsId() {
        return this.commsId;
    }

    public String getCommsProvisionStatus() {
        return this.commsProvisionStatus;
    }

    public String getHashedCommsId() {
        return this.hashedCommsId;
    }

    public String getHomeGroupId() {
        return this.homeGroupId;
    }

    public String getHomeGroupPceId() {
        return this.homeGroupPceId;
    }

    public Name getName() {
        return this.name;
    }

    public String getPceId() {
        return this.pceId;
    }

    public void setAor(String str) {
        this.aor = str;
    }

    public void setCommsId(String str) {
        this.commsId = str;
    }

    public void setCommsProvisionStatus(String str) {
        this.commsProvisionStatus = str;
    }

    public void setHashedCommsId(String str) {
        this.hashedCommsId = str;
    }

    public void setHomeGroupId(String str) {
        this.homeGroupId = str;
    }

    public void setHomeGroupPceId(String str) {
        this.homeGroupPceId = str;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setPceId(String str) {
        this.pceId = str;
    }
}
