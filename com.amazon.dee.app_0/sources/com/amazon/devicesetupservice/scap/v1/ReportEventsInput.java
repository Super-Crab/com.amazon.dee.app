package com.amazon.devicesetupservice.scap.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.util.List;
/* loaded from: classes12.dex */
public class ReportEventsInput extends BaseInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.scap.v1.ReportEventsInput");
    private List<Event> eventList;
    private ScapProvisionerDetails provisionerDetails;
    private ProvisionerNetwork provisionerNetwork;
    private String sessionId;

    @Override // com.amazon.devicesetupservice.scap.v1.BaseInput
    public boolean equals(Object obj) {
        if (!(obj instanceof ReportEventsInput)) {
            return false;
        }
        ReportEventsInput reportEventsInput = (ReportEventsInput) obj;
        return super.equals(obj) && Helper.equals(this.sessionId, reportEventsInput.sessionId) && Helper.equals(this.provisionerDetails, reportEventsInput.provisionerDetails) && Helper.equals(this.provisionerNetwork, reportEventsInput.provisionerNetwork) && Helper.equals(this.eventList, reportEventsInput.eventList);
    }

    public List<Event> getEventList() {
        return this.eventList;
    }

    public ScapProvisionerDetails getProvisionerDetails() {
        return this.provisionerDetails;
    }

    public ProvisionerNetwork getProvisionerNetwork() {
        return this.provisionerNetwork;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    @Override // com.amazon.devicesetupservice.scap.v1.BaseInput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.sessionId, this.provisionerDetails, this.provisionerNetwork, this.eventList);
    }

    public void setEventList(List<Event> list) {
        this.eventList = list;
    }

    public void setProvisionerDetails(ScapProvisionerDetails scapProvisionerDetails) {
        this.provisionerDetails = scapProvisionerDetails;
    }

    public void setProvisionerNetwork(ProvisionerNetwork provisionerNetwork) {
        this.provisionerNetwork = provisionerNetwork;
    }

    public void setSessionId(String str) {
        this.sessionId = str;
    }
}
