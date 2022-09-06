package com.amazon.deecomms.calling.incallexperiences.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes12.dex */
public class GetAvailableActionsResponse {
    @JsonProperty(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ACTIONS)
    private List<ApplicationAction> applicationActions;

    public List<ApplicationAction> getApplicationActions() {
        return this.applicationActions;
    }

    public void setApplicationActions(List<ApplicationAction> list) {
        this.applicationActions = list;
    }
}
