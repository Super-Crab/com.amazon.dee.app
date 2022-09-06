package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class UpdateEventConfigurationsRequest extends AmazonWebServiceRequest implements Serializable {
    private Map<String, Configuration> eventConfigurations;

    public UpdateEventConfigurationsRequest addeventConfigurationsEntry(String str, Configuration configuration) {
        if (this.eventConfigurations == null) {
            this.eventConfigurations = new HashMap();
        }
        if (!this.eventConfigurations.containsKey(str)) {
            this.eventConfigurations.put(str, configuration);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public UpdateEventConfigurationsRequest cleareventConfigurationsEntries() {
        this.eventConfigurations = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateEventConfigurationsRequest)) {
            return false;
        }
        UpdateEventConfigurationsRequest updateEventConfigurationsRequest = (UpdateEventConfigurationsRequest) obj;
        if ((updateEventConfigurationsRequest.getEventConfigurations() == null) ^ (getEventConfigurations() == null)) {
            return false;
        }
        return updateEventConfigurationsRequest.getEventConfigurations() == null || updateEventConfigurationsRequest.getEventConfigurations().equals(getEventConfigurations());
    }

    public Map<String, Configuration> getEventConfigurations() {
        return this.eventConfigurations;
    }

    public int hashCode() {
        return 31 + (getEventConfigurations() == null ? 0 : getEventConfigurations().hashCode());
    }

    public void setEventConfigurations(Map<String, Configuration> map) {
        this.eventConfigurations = map;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getEventConfigurations() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("eventConfigurations: ");
            outline1072.append(getEventConfigurations());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public UpdateEventConfigurationsRequest withEventConfigurations(Map<String, Configuration> map) {
        this.eventConfigurations = map;
        return this;
    }
}
