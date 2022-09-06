package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListAuditFindingsResult implements Serializable {
    private List<AuditFinding> findings;
    private String nextToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListAuditFindingsResult)) {
            return false;
        }
        ListAuditFindingsResult listAuditFindingsResult = (ListAuditFindingsResult) obj;
        if ((listAuditFindingsResult.getFindings() == null) ^ (getFindings() == null)) {
            return false;
        }
        if (listAuditFindingsResult.getFindings() != null && !listAuditFindingsResult.getFindings().equals(getFindings())) {
            return false;
        }
        if ((listAuditFindingsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listAuditFindingsResult.getNextToken() == null || listAuditFindingsResult.getNextToken().equals(getNextToken());
    }

    public List<AuditFinding> getFindings() {
        return this.findings;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getFindings() == null ? 0 : getFindings().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setFindings(Collection<AuditFinding> collection) {
        if (collection == null) {
            this.findings = null;
        } else {
            this.findings = new ArrayList(collection);
        }
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getFindings() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("findings: ");
            outline1072.append(getFindings());
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

    public ListAuditFindingsResult withFindings(AuditFinding... auditFindingArr) {
        if (getFindings() == null) {
            this.findings = new ArrayList(auditFindingArr.length);
        }
        for (AuditFinding auditFinding : auditFindingArr) {
            this.findings.add(auditFinding);
        }
        return this;
    }

    public ListAuditFindingsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListAuditFindingsResult withFindings(Collection<AuditFinding> collection) {
        setFindings(collection);
        return this;
    }
}
