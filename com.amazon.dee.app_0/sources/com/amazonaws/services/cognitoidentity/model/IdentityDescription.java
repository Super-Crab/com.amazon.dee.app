package com.amazonaws.services.cognitoidentity.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
/* loaded from: classes13.dex */
public class IdentityDescription implements Serializable {
    private Date creationDate;
    private String identityId;
    private Date lastModifiedDate;
    private List<String> logins;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof IdentityDescription)) {
            return false;
        }
        IdentityDescription identityDescription = (IdentityDescription) obj;
        if ((identityDescription.getIdentityId() == null) ^ (getIdentityId() == null)) {
            return false;
        }
        if (identityDescription.getIdentityId() != null && !identityDescription.getIdentityId().equals(getIdentityId())) {
            return false;
        }
        if ((identityDescription.getLogins() == null) ^ (getLogins() == null)) {
            return false;
        }
        if (identityDescription.getLogins() != null && !identityDescription.getLogins().equals(getLogins())) {
            return false;
        }
        if ((identityDescription.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (identityDescription.getCreationDate() != null && !identityDescription.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((identityDescription.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        return identityDescription.getLastModifiedDate() == null || identityDescription.getLastModifiedDate().equals(getLastModifiedDate());
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public String getIdentityId() {
        return this.identityId;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public List<String> getLogins() {
        return this.logins;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getIdentityId() == null ? 0 : getIdentityId().hashCode()) + 31) * 31) + (getLogins() == null ? 0 : getLogins().hashCode())) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31;
        if (getLastModifiedDate() != null) {
            i = getLastModifiedDate().hashCode();
        }
        return hashCode + i;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public void setIdentityId(String str) {
        this.identityId = str;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public void setLogins(Collection<String> collection) {
        if (collection == null) {
            this.logins = null;
        } else {
            this.logins = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getIdentityId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("IdentityId: ");
            outline1072.append(getIdentityId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getLogins() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Logins: ");
            outline1073.append(getLogins());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getCreationDate() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("CreationDate: ");
            outline1074.append(getCreationDate());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getLastModifiedDate() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("LastModifiedDate: ");
            outline1075.append(getLastModifiedDate());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public IdentityDescription withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public IdentityDescription withIdentityId(String str) {
        this.identityId = str;
        return this;
    }

    public IdentityDescription withLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
        return this;
    }

    public IdentityDescription withLogins(String... strArr) {
        if (getLogins() == null) {
            this.logins = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.logins.add(str);
        }
        return this;
    }

    public IdentityDescription withLogins(Collection<String> collection) {
        setLogins(collection);
        return this;
    }
}
