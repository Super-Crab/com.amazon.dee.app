package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class DescribeEventConfigurationsResult implements Serializable {
    private Date creationDate;
    private Map<String, Configuration> eventConfigurations;
    private Date lastModifiedDate;

    public DescribeEventConfigurationsResult addeventConfigurationsEntry(String str, Configuration configuration) {
        if (this.eventConfigurations == null) {
            this.eventConfigurations = new HashMap();
        }
        if (!this.eventConfigurations.containsKey(str)) {
            this.eventConfigurations.put(str, configuration);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public DescribeEventConfigurationsResult cleareventConfigurationsEntries() {
        this.eventConfigurations = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeEventConfigurationsResult)) {
            return false;
        }
        DescribeEventConfigurationsResult describeEventConfigurationsResult = (DescribeEventConfigurationsResult) obj;
        if ((describeEventConfigurationsResult.getEventConfigurations() == null) ^ (getEventConfigurations() == null)) {
            return false;
        }
        if (describeEventConfigurationsResult.getEventConfigurations() != null && !describeEventConfigurationsResult.getEventConfigurations().equals(getEventConfigurations())) {
            return false;
        }
        if ((describeEventConfigurationsResult.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (describeEventConfigurationsResult.getCreationDate() != null && !describeEventConfigurationsResult.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((describeEventConfigurationsResult.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        return describeEventConfigurationsResult.getLastModifiedDate() == null || describeEventConfigurationsResult.getLastModifiedDate().equals(getLastModifiedDate());
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public Map<String, Configuration> getEventConfigurations() {
        return this.eventConfigurations;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getEventConfigurations() == null ? 0 : getEventConfigurations().hashCode()) + 31) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31;
        if (getLastModifiedDate() != null) {
            i = getLastModifiedDate().hashCode();
        }
        return hashCode + i;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public void setEventConfigurations(Map<String, Configuration> map) {
        this.eventConfigurations = map;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getEventConfigurations() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("eventConfigurations: ");
            outline1072.append(getEventConfigurations());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getCreationDate() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("creationDate: ");
            outline1073.append(getCreationDate());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getLastModifiedDate() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("lastModifiedDate: ");
            outline1074.append(getLastModifiedDate());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeEventConfigurationsResult withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public DescribeEventConfigurationsResult withEventConfigurations(Map<String, Configuration> map) {
        this.eventConfigurations = map;
        return this;
    }

    public DescribeEventConfigurationsResult withLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
        return this;
    }
}
