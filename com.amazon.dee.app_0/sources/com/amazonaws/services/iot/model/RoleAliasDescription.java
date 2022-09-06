package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class RoleAliasDescription implements Serializable {
    private Date creationDate;
    private Integer credentialDurationSeconds;
    private Date lastModifiedDate;
    private String owner;
    private String roleAlias;
    private String roleAliasArn;
    private String roleArn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RoleAliasDescription)) {
            return false;
        }
        RoleAliasDescription roleAliasDescription = (RoleAliasDescription) obj;
        if ((roleAliasDescription.getRoleAlias() == null) ^ (getRoleAlias() == null)) {
            return false;
        }
        if (roleAliasDescription.getRoleAlias() != null && !roleAliasDescription.getRoleAlias().equals(getRoleAlias())) {
            return false;
        }
        if ((roleAliasDescription.getRoleAliasArn() == null) ^ (getRoleAliasArn() == null)) {
            return false;
        }
        if (roleAliasDescription.getRoleAliasArn() != null && !roleAliasDescription.getRoleAliasArn().equals(getRoleAliasArn())) {
            return false;
        }
        if ((roleAliasDescription.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (roleAliasDescription.getRoleArn() != null && !roleAliasDescription.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((roleAliasDescription.getOwner() == null) ^ (getOwner() == null)) {
            return false;
        }
        if (roleAliasDescription.getOwner() != null && !roleAliasDescription.getOwner().equals(getOwner())) {
            return false;
        }
        if ((roleAliasDescription.getCredentialDurationSeconds() == null) ^ (getCredentialDurationSeconds() == null)) {
            return false;
        }
        if (roleAliasDescription.getCredentialDurationSeconds() != null && !roleAliasDescription.getCredentialDurationSeconds().equals(getCredentialDurationSeconds())) {
            return false;
        }
        if ((roleAliasDescription.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (roleAliasDescription.getCreationDate() != null && !roleAliasDescription.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((roleAliasDescription.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        return roleAliasDescription.getLastModifiedDate() == null || roleAliasDescription.getLastModifiedDate().equals(getLastModifiedDate());
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public Integer getCredentialDurationSeconds() {
        return this.credentialDurationSeconds;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getRoleAlias() {
        return this.roleAlias;
    }

    public String getRoleAliasArn() {
        return this.roleAliasArn;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((getRoleAlias() == null ? 0 : getRoleAlias().hashCode()) + 31) * 31) + (getRoleAliasArn() == null ? 0 : getRoleAliasArn().hashCode())) * 31) + (getRoleArn() == null ? 0 : getRoleArn().hashCode())) * 31) + (getOwner() == null ? 0 : getOwner().hashCode())) * 31) + (getCredentialDurationSeconds() == null ? 0 : getCredentialDurationSeconds().hashCode())) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31;
        if (getLastModifiedDate() != null) {
            i = getLastModifiedDate().hashCode();
        }
        return hashCode + i;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public void setCredentialDurationSeconds(Integer num) {
        this.credentialDurationSeconds = num;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public void setOwner(String str) {
        this.owner = str;
    }

    public void setRoleAlias(String str) {
        this.roleAlias = str;
    }

    public void setRoleAliasArn(String str) {
        this.roleAliasArn = str;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getRoleAlias() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("roleAlias: ");
            outline1072.append(getRoleAlias());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getRoleAliasArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("roleAliasArn: ");
            outline1073.append(getRoleAliasArn());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getRoleArn() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("roleArn: ");
            outline1074.append(getRoleArn());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getOwner() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("owner: ");
            outline1075.append(getOwner());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getCredentialDurationSeconds() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("credentialDurationSeconds: ");
            outline1076.append(getCredentialDurationSeconds());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getCreationDate() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("creationDate: ");
            outline1077.append(getCreationDate());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getLastModifiedDate() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("lastModifiedDate: ");
            outline1078.append(getLastModifiedDate());
            outline107.append(outline1078.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public RoleAliasDescription withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public RoleAliasDescription withCredentialDurationSeconds(Integer num) {
        this.credentialDurationSeconds = num;
        return this;
    }

    public RoleAliasDescription withLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
        return this;
    }

    public RoleAliasDescription withOwner(String str) {
        this.owner = str;
        return this;
    }

    public RoleAliasDescription withRoleAlias(String str) {
        this.roleAlias = str;
        return this;
    }

    public RoleAliasDescription withRoleAliasArn(String str) {
        this.roleAliasArn = str;
        return this;
    }

    public RoleAliasDescription withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }
}
