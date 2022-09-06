package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ElasticsearchAction implements Serializable {
    private String endpoint;
    private String id;
    private String index;
    private String roleArn;
    private String type;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ElasticsearchAction)) {
            return false;
        }
        ElasticsearchAction elasticsearchAction = (ElasticsearchAction) obj;
        if ((elasticsearchAction.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (elasticsearchAction.getRoleArn() != null && !elasticsearchAction.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((elasticsearchAction.getEndpoint() == null) ^ (getEndpoint() == null)) {
            return false;
        }
        if (elasticsearchAction.getEndpoint() != null && !elasticsearchAction.getEndpoint().equals(getEndpoint())) {
            return false;
        }
        if ((elasticsearchAction.getIndex() == null) ^ (getIndex() == null)) {
            return false;
        }
        if (elasticsearchAction.getIndex() != null && !elasticsearchAction.getIndex().equals(getIndex())) {
            return false;
        }
        if ((elasticsearchAction.getType() == null) ^ (getType() == null)) {
            return false;
        }
        if (elasticsearchAction.getType() != null && !elasticsearchAction.getType().equals(getType())) {
            return false;
        }
        if ((elasticsearchAction.getId() == null) ^ (getId() == null)) {
            return false;
        }
        return elasticsearchAction.getId() == null || elasticsearchAction.getId().equals(getId());
    }

    public String getEndpoint() {
        return this.endpoint;
    }

    public String getId() {
        return this.id;
    }

    public String getIndex() {
        return this.index;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31) + (getEndpoint() == null ? 0 : getEndpoint().hashCode())) * 31) + (getIndex() == null ? 0 : getIndex().hashCode())) * 31) + (getType() == null ? 0 : getType().hashCode())) * 31;
        if (getId() != null) {
            i = getId().hashCode();
        }
        return hashCode + i;
    }

    public void setEndpoint(String str) {
        this.endpoint = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setIndex(String str) {
        this.index = str;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getRoleArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("roleArn: ");
            outline1072.append(getRoleArn());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getEndpoint() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("endpoint: ");
            outline1073.append(getEndpoint());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getIndex() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("index: ");
            outline1074.append(getIndex());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getType() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("type: ");
            outline1075.append(getType());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getId() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("id: ");
            outline1076.append(getId());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ElasticsearchAction withEndpoint(String str) {
        this.endpoint = str;
        return this;
    }

    public ElasticsearchAction withId(String str) {
        this.id = str;
        return this;
    }

    public ElasticsearchAction withIndex(String str) {
        this.index = str;
        return this;
    }

    public ElasticsearchAction withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public ElasticsearchAction withType(String str) {
        this.type = str;
        return this;
    }
}
