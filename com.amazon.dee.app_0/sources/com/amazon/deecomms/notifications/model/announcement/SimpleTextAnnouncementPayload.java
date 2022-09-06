package com.amazon.deecomms.notifications.model.announcement;

import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class SimpleTextAnnouncementPayload {
    public static final String TYPE = "announcement/text-simple";
    @JsonProperty("announcementText")
    private String announcementText;
    @JsonProperty("sourceName")
    private String sourceName;
    @JsonProperty(MessagingControllerConstant.MESSAGING_CONTROLLER_PAYLOAD_TYPE)
    private String type;

    public String getNotificationText() {
        return this.announcementText;
    }

    public String getType() {
        return "announcement/text-simple";
    }
}
