package com.amazon.alexa.biloba.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes6.dex */
public class ListGroupsResponse {
    @SerializedName("id")
    private String id = null;
    @SerializedName("status")
    private String status = null;
    @SerializedName("subscription")
    private Subscription subscription = null;
    @SerializedName("currentActor")
    private CareActor currentActor = null;
    @SerializedName("careGivers")
    private List<CareActor> careGivers = null;
    @SerializedName("careRecipients")
    private List<CareActor> careRecipients = null;
    @SerializedName("careRecipient")
    private CareActor careRecipient = null;

    private String toIndentedString(Object obj) {
        return obj == null ? "null" : obj.toString().replace("\n", "\n    ");
    }

    public ListGroupsResponse addCareGiversItem(CareActor careActor) {
        if (this.careGivers == null) {
            this.careGivers = new ArrayList();
        }
        this.careGivers.add(careActor);
        return this;
    }

    public ListGroupsResponse addCareRecipientsItem(CareActor careActor) {
        if (this.careRecipients == null) {
            this.careRecipients = new ArrayList();
        }
        this.careRecipients.add(careActor);
        return this;
    }

    public ListGroupsResponse careGivers(List<CareActor> list) {
        this.careGivers = list;
        return this;
    }

    public ListGroupsResponse careRecipient(CareActor careActor) {
        this.careRecipient = careActor;
        return this;
    }

    public ListGroupsResponse careRecipients(List<CareActor> list) {
        this.careRecipients = list;
        return this;
    }

    public ListGroupsResponse currentActor(CareActor careActor) {
        this.currentActor = careActor;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ListGroupsResponse.class != obj.getClass()) {
            return false;
        }
        ListGroupsResponse listGroupsResponse = (ListGroupsResponse) obj;
        return Objects.equals(this.id, listGroupsResponse.id) && Objects.equals(this.status, listGroupsResponse.status) && Objects.equals(this.subscription, listGroupsResponse.subscription) && Objects.equals(this.currentActor, listGroupsResponse.currentActor) && Objects.equals(this.careGivers, listGroupsResponse.careGivers) && Objects.equals(this.careRecipients, listGroupsResponse.careRecipients) && Objects.equals(this.careRecipient, listGroupsResponse.careRecipient);
    }

    public List<CareActor> getCareGivers() {
        return this.careGivers;
    }

    public CareActor getCareRecipient() {
        return this.careRecipient;
    }

    public List<CareActor> getCareRecipients() {
        return this.careRecipients;
    }

    public CareActor getCurrentActor() {
        return this.currentActor;
    }

    public String getId() {
        return this.id;
    }

    public String getStatus() {
        return this.status;
    }

    public Subscription getSubscription() {
        return this.subscription;
    }

    public int hashCode() {
        return Objects.hash(this.currentActor, this.careGivers, this.careRecipients, this.careRecipient);
    }

    public void setCareGivers(List<CareActor> list) {
        this.careGivers = list;
    }

    public void setCareRecipient(CareActor careActor) {
        this.careRecipient = careActor;
    }

    public void setCareRecipients(List<CareActor> list) {
        this.careRecipients = list;
    }

    public void setCurrentActor(CareActor careActor) {
        this.currentActor = careActor;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public String toString() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("class ListGroupsResponse {\n", "    id: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.id), "\n", "    status: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.status), "\n", "    subscription: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.subscription), "\n", "    currentActor: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.currentActor), "\n", "    careGivers: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.careGivers), "\n", "    careRecipients: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.careRecipients), "\n", "    careRecipient: ");
        return GeneratedOutlineSupport1.outline92(outline113, toIndentedString(this.careRecipient), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
