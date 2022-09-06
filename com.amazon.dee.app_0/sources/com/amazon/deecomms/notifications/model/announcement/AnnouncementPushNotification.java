package com.amazon.deecomms.notifications.model.announcement;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import java.util.Date;
/* loaded from: classes12.dex */
public class AnnouncementPushNotification {
    private String announcementId;
    private String displayText;
    private String mediaId;
    final Integer notificationId;
    final Date originDate;
    private String sourceName;
    private String type;

    public AnnouncementPushNotification(String str, String str2, String str3, Date date, int i, String str4) {
        this.announcementId = str;
        this.sourceName = str2;
        this.displayText = str3;
        this.type = str4;
        this.originDate = date;
        this.notificationId = Integer.valueOf(i);
    }

    private String getDisplayTextOrPlaceholder(Context context) {
        if (!TextUtils.isEmpty(this.displayText) && !this.displayText.equalsIgnoreCase("null")) {
            return this.displayText;
        }
        return context.getResources().getString(R.string.notification_announcement_no_content);
    }

    public String getAnnouncementContent() {
        char c;
        String str = this.type;
        int hashCode = str.hashCode();
        if (hashCode != -389653234) {
            if (hashCode == 1068011914 && str.equals("announcement/text-simple")) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals("announcement/audio")) {
                c = 0;
            }
            c = 65535;
        }
        if (c != 0) {
            return this.displayText;
        }
        return this.mediaId;
    }

    public String getAnnouncementId() {
        return this.announcementId;
    }

    public String getDisplayText() {
        return this.displayText;
    }

    public String getMediaId() {
        return this.mediaId;
    }

    public Integer getNotificationId() {
        return this.notificationId;
    }

    public String getNotificationText(Context context) {
        return this.sourceName + RealTimeTextConstants.COLON_SPACE + getDisplayTextOrPlaceholder(context);
    }

    public Date getOriginDate() {
        return this.originDate;
    }

    public String getSourceName() {
        return this.sourceName;
    }

    public String getType() {
        return this.type;
    }

    public void setAnnouncementId(String str) {
        this.announcementId = str;
    }

    public void setDisplayText(String str) {
        this.displayText = str;
    }

    public void setMediaId(String str) {
        this.mediaId = str;
    }

    public void setSourceName(String str) {
        this.sourceName = str;
    }

    public void setType(String str) {
        this.type = str;
    }
}
