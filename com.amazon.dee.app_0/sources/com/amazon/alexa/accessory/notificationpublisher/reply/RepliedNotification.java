package com.amazon.alexa.accessory.notificationpublisher.reply;

import java.util.Objects;
/* loaded from: classes.dex */
public class RepliedNotification {
    private final String notificationId;
    private final String replyText;
    private final String title;

    public RepliedNotification(String str, String str2, String str3) {
        this.notificationId = str;
        this.title = str2;
        this.replyText = str3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RepliedNotification)) {
            return false;
        }
        RepliedNotification repliedNotification = (RepliedNotification) obj;
        return Objects.equals(this.notificationId, repliedNotification.notificationId) && Objects.equals(this.title, repliedNotification.title) && Objects.equals(this.replyText, repliedNotification.replyText);
    }

    public int hashCode() {
        return Objects.hash(this.notificationId, this.title, this.replyText);
    }
}
