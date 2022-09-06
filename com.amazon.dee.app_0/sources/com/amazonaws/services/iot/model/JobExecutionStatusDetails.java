package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class JobExecutionStatusDetails implements Serializable {
    private Map<String, String> detailsMap;

    public JobExecutionStatusDetails adddetailsMapEntry(String str, String str2) {
        if (this.detailsMap == null) {
            this.detailsMap = new HashMap();
        }
        if (!this.detailsMap.containsKey(str)) {
            this.detailsMap.put(str, str2);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public JobExecutionStatusDetails cleardetailsMapEntries() {
        this.detailsMap = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof JobExecutionStatusDetails)) {
            return false;
        }
        JobExecutionStatusDetails jobExecutionStatusDetails = (JobExecutionStatusDetails) obj;
        if ((jobExecutionStatusDetails.getDetailsMap() == null) ^ (getDetailsMap() == null)) {
            return false;
        }
        return jobExecutionStatusDetails.getDetailsMap() == null || jobExecutionStatusDetails.getDetailsMap().equals(getDetailsMap());
    }

    public Map<String, String> getDetailsMap() {
        return this.detailsMap;
    }

    public int hashCode() {
        return 31 + (getDetailsMap() == null ? 0 : getDetailsMap().hashCode());
    }

    public void setDetailsMap(Map<String, String> map) {
        this.detailsMap = map;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getDetailsMap() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("detailsMap: ");
            outline1072.append(getDetailsMap());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public JobExecutionStatusDetails withDetailsMap(Map<String, String> map) {
        this.detailsMap = map;
        return this;
    }
}
