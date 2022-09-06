package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class MetricFilterMatchRecord implements Serializable {
    private String eventMessage;
    private Long eventNumber;
    private Map<String, String> extractedValues;

    public MetricFilterMatchRecord addextractedValuesEntry(String str, String str2) {
        if (this.extractedValues == null) {
            this.extractedValues = new HashMap();
        }
        if (!this.extractedValues.containsKey(str)) {
            this.extractedValues.put(str, str2);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public MetricFilterMatchRecord clearextractedValuesEntries() {
        this.extractedValues = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MetricFilterMatchRecord)) {
            return false;
        }
        MetricFilterMatchRecord metricFilterMatchRecord = (MetricFilterMatchRecord) obj;
        if ((metricFilterMatchRecord.getEventNumber() == null) ^ (getEventNumber() == null)) {
            return false;
        }
        if (metricFilterMatchRecord.getEventNumber() != null && !metricFilterMatchRecord.getEventNumber().equals(getEventNumber())) {
            return false;
        }
        if ((metricFilterMatchRecord.getEventMessage() == null) ^ (getEventMessage() == null)) {
            return false;
        }
        if (metricFilterMatchRecord.getEventMessage() != null && !metricFilterMatchRecord.getEventMessage().equals(getEventMessage())) {
            return false;
        }
        if ((metricFilterMatchRecord.getExtractedValues() == null) ^ (getExtractedValues() == null)) {
            return false;
        }
        return metricFilterMatchRecord.getExtractedValues() == null || metricFilterMatchRecord.getExtractedValues().equals(getExtractedValues());
    }

    public String getEventMessage() {
        return this.eventMessage;
    }

    public Long getEventNumber() {
        return this.eventNumber;
    }

    public Map<String, String> getExtractedValues() {
        return this.extractedValues;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getEventNumber() == null ? 0 : getEventNumber().hashCode()) + 31) * 31) + (getEventMessage() == null ? 0 : getEventMessage().hashCode())) * 31;
        if (getExtractedValues() != null) {
            i = getExtractedValues().hashCode();
        }
        return hashCode + i;
    }

    public void setEventMessage(String str) {
        this.eventMessage = str;
    }

    public void setEventNumber(Long l) {
        this.eventNumber = l;
    }

    public void setExtractedValues(Map<String, String> map) {
        this.extractedValues = map;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getEventNumber() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("eventNumber: ");
            outline1072.append(getEventNumber());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getEventMessage() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("eventMessage: ");
            outline1073.append(getEventMessage());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getExtractedValues() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("extractedValues: ");
            outline1074.append(getExtractedValues());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public MetricFilterMatchRecord withEventMessage(String str) {
        this.eventMessage = str;
        return this;
    }

    public MetricFilterMatchRecord withEventNumber(Long l) {
        this.eventNumber = l;
        return this;
    }

    public MetricFilterMatchRecord withExtractedValues(Map<String, String> map) {
        this.extractedValues = map;
        return this;
    }
}
