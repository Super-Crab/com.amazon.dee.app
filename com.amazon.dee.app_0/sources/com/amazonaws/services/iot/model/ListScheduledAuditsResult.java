package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListScheduledAuditsResult implements Serializable {
    private String nextToken;
    private List<ScheduledAuditMetadata> scheduledAudits;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListScheduledAuditsResult)) {
            return false;
        }
        ListScheduledAuditsResult listScheduledAuditsResult = (ListScheduledAuditsResult) obj;
        if ((listScheduledAuditsResult.getScheduledAudits() == null) ^ (getScheduledAudits() == null)) {
            return false;
        }
        if (listScheduledAuditsResult.getScheduledAudits() != null && !listScheduledAuditsResult.getScheduledAudits().equals(getScheduledAudits())) {
            return false;
        }
        if ((listScheduledAuditsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listScheduledAuditsResult.getNextToken() == null || listScheduledAuditsResult.getNextToken().equals(getNextToken());
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public List<ScheduledAuditMetadata> getScheduledAudits() {
        return this.scheduledAudits;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getScheduledAudits() == null ? 0 : getScheduledAudits().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setScheduledAudits(Collection<ScheduledAuditMetadata> collection) {
        if (collection == null) {
            this.scheduledAudits = null;
        } else {
            this.scheduledAudits = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getScheduledAudits() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("scheduledAudits: ");
            outline1072.append(getScheduledAudits());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1073.append(getNextToken());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListScheduledAuditsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListScheduledAuditsResult withScheduledAudits(ScheduledAuditMetadata... scheduledAuditMetadataArr) {
        if (getScheduledAudits() == null) {
            this.scheduledAudits = new ArrayList(scheduledAuditMetadataArr.length);
        }
        for (ScheduledAuditMetadata scheduledAuditMetadata : scheduledAuditMetadataArr) {
            this.scheduledAudits.add(scheduledAuditMetadata);
        }
        return this;
    }

    public ListScheduledAuditsResult withScheduledAudits(Collection<ScheduledAuditMetadata> collection) {
        setScheduledAudits(collection);
        return this;
    }
}
