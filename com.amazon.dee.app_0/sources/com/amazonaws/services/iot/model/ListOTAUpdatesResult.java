package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListOTAUpdatesResult implements Serializable {
    private String nextToken;
    private List<OTAUpdateSummary> otaUpdates;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListOTAUpdatesResult)) {
            return false;
        }
        ListOTAUpdatesResult listOTAUpdatesResult = (ListOTAUpdatesResult) obj;
        if ((listOTAUpdatesResult.getOtaUpdates() == null) ^ (getOtaUpdates() == null)) {
            return false;
        }
        if (listOTAUpdatesResult.getOtaUpdates() != null && !listOTAUpdatesResult.getOtaUpdates().equals(getOtaUpdates())) {
            return false;
        }
        if ((listOTAUpdatesResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listOTAUpdatesResult.getNextToken() == null || listOTAUpdatesResult.getNextToken().equals(getNextToken());
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public List<OTAUpdateSummary> getOtaUpdates() {
        return this.otaUpdates;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getOtaUpdates() == null ? 0 : getOtaUpdates().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setOtaUpdates(Collection<OTAUpdateSummary> collection) {
        if (collection == null) {
            this.otaUpdates = null;
        } else {
            this.otaUpdates = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getOtaUpdates() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("otaUpdates: ");
            outline1072.append(getOtaUpdates());
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

    public ListOTAUpdatesResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListOTAUpdatesResult withOtaUpdates(OTAUpdateSummary... oTAUpdateSummaryArr) {
        if (getOtaUpdates() == null) {
            this.otaUpdates = new ArrayList(oTAUpdateSummaryArr.length);
        }
        for (OTAUpdateSummary oTAUpdateSummary : oTAUpdateSummaryArr) {
            this.otaUpdates.add(oTAUpdateSummary);
        }
        return this;
    }

    public ListOTAUpdatesResult withOtaUpdates(Collection<OTAUpdateSummary> collection) {
        setOtaUpdates(collection);
        return this;
    }
}
