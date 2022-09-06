package com.amazon.alexa.biloba.generated.models;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes6.dex */
public class ActivitiesResponse {
    @SerializedName("paginationContext")
    private PaginationContext paginationContext = null;
    @SerializedName("activities")
    private List<Activity> activities = new ArrayList();
    @SerializedName("messages")
    private Map<String, Message> messages = new HashMap();

    private String toIndentedString(Object obj) {
        return obj == null ? "null" : obj.toString().replace("\n", "\n    ");
    }

    public ActivitiesResponse activities(List<Activity> list) {
        this.activities = list;
        return this;
    }

    public ActivitiesResponse addActivitiesItem(Activity activity) {
        this.activities.add(activity);
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ActivitiesResponse.class != obj.getClass()) {
            return false;
        }
        ActivitiesResponse activitiesResponse = (ActivitiesResponse) obj;
        return Objects.equals(this.paginationContext, activitiesResponse.paginationContext) && Objects.equals(this.activities, activitiesResponse.activities) && Objects.equals(this.messages, activitiesResponse.messages);
    }

    public List<Activity> getActivities() {
        return this.activities;
    }

    public Map<String, Message> getMessages() {
        return this.messages;
    }

    public PaginationContext getPaginationContext() {
        return this.paginationContext;
    }

    public int hashCode() {
        return Objects.hash(this.paginationContext, this.activities, this.messages);
    }

    public ActivitiesResponse messages(Map<String, Message> map) {
        this.messages = map;
        return this;
    }

    public ActivitiesResponse paginationContext(PaginationContext paginationContext) {
        this.paginationContext = paginationContext;
        return this;
    }

    public ActivitiesResponse putMessagesItem(String str, Message message) {
        this.messages.put(str, message);
        return this;
    }

    public void setActivities(List<Activity> list) {
        this.activities = list;
    }

    public void setMessages(Map<String, Message> map) {
        this.messages = map;
    }

    public void setPaginationContext(PaginationContext paginationContext) {
        this.paginationContext = paginationContext;
    }

    public String toString() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("class ActivitiesResponse {\n", "    paginationContext: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.paginationContext), "\n", "    activities: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.activities), "\n", "    messages: ");
        return GeneratedOutlineSupport1.outline92(outline113, toIndentedString(this.messages), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
