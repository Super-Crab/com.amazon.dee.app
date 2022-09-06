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
public class TodaysActivitiesResponse {
    @SerializedName("activities")
    private List<Activity> activities = new ArrayList();
    @SerializedName("messages")
    private Map<String, Message> messages = null;

    private String toIndentedString(Object obj) {
        return obj == null ? "null" : obj.toString().replace("\n", "\n    ");
    }

    public TodaysActivitiesResponse activities(List<Activity> list) {
        this.activities = list;
        return this;
    }

    public TodaysActivitiesResponse addActivitiesItem(Activity activity) {
        this.activities.add(activity);
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TodaysActivitiesResponse.class != obj.getClass()) {
            return false;
        }
        TodaysActivitiesResponse todaysActivitiesResponse = (TodaysActivitiesResponse) obj;
        return Objects.equals(this.activities, todaysActivitiesResponse.activities) && Objects.equals(this.messages, todaysActivitiesResponse.messages);
    }

    public List<Activity> getActivities() {
        return this.activities;
    }

    public Map<String, Message> getMessages() {
        return this.messages;
    }

    public int hashCode() {
        return Objects.hash(this.activities, this.messages);
    }

    public TodaysActivitiesResponse messages(Map<String, Message> map) {
        this.messages = map;
        return this;
    }

    public TodaysActivitiesResponse putMessagesItem(String str, Message message) {
        if (this.messages == null) {
            this.messages = new HashMap();
        }
        this.messages.put(str, message);
        return this;
    }

    public void setActivities(List<Activity> list) {
        this.activities = list;
    }

    public void setMessages(Map<String, Message> map) {
        this.messages = map;
    }

    public String toString() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("class TodaysActivitiesResponse {\n", "    activities: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.activities), "\n", "    messages: ");
        return GeneratedOutlineSupport1.outline92(outline113, toIndentedString(this.messages), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
