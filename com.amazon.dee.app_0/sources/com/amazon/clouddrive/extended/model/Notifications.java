package com.amazon.clouddrive.extended.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public class Notifications {
    private NotificationChannelPreferences email;
    private Boolean muted;
    private NotificationChannelPreferences push;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Notifications)) {
            return false;
        }
        Notifications notifications = (Notifications) obj;
        Boolean bool = this.muted;
        if (bool == null ? notifications.muted == null : bool.equals(notifications.muted)) {
            if (getEmail() == null ? notifications.getEmail() == null : getEmail().equals(notifications.getEmail())) {
                if (getPush() != null) {
                    if (getPush().equals(notifications.getPush())) {
                        return true;
                    }
                } else if (notifications.getPush() == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public NotificationChannelPreferences getEmail() {
        return this.email;
    }

    public NotificationChannelPreferences getPush() {
        return this.push;
    }

    public int hashCode() {
        Boolean bool = this.muted;
        int i = 0;
        int hashCode = (((bool != null ? bool.hashCode() : 0) * 31) + (getEmail() != null ? getEmail().hashCode() : 0)) * 31;
        if (getPush() != null) {
            i = getPush().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isMuted() {
        return this.muted;
    }

    public void setEmail(NotificationChannelPreferences notificationChannelPreferences) {
        this.email = notificationChannelPreferences;
    }

    public void setMuted(Boolean bool) {
        this.muted = bool;
    }

    public void setPush(NotificationChannelPreferences notificationChannelPreferences) {
        this.push = notificationChannelPreferences;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Notifications{muted=");
        outline107.append(this.muted);
        outline107.append(", email=");
        outline107.append(this.email);
        outline107.append(", push=");
        outline107.append(this.push);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    public Notifications withEmail(NotificationChannelPreferences notificationChannelPreferences) {
        setEmail(notificationChannelPreferences);
        return this;
    }

    public Notifications withMuted(Boolean bool) {
        setMuted(bool);
        return this;
    }

    public Notifications withPush(NotificationChannelPreferences notificationChannelPreferences) {
        setPush(notificationChannelPreferences);
        return this;
    }
}
