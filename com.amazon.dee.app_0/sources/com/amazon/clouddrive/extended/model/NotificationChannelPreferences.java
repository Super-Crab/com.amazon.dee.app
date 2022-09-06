package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.ObjectComparator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public class NotificationChannelPreferences implements Comparable<NotificationChannelPreferences> {
    private Boolean contentAdded;
    private Boolean memberJoined;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NotificationChannelPreferences)) {
            return false;
        }
        NotificationChannelPreferences notificationChannelPreferences = (NotificationChannelPreferences) obj;
        Boolean bool = this.contentAdded;
        if (bool == null ? notificationChannelPreferences.contentAdded == null : bool.equals(notificationChannelPreferences.contentAdded)) {
            Boolean bool2 = this.memberJoined;
            if (bool2 != null) {
                if (bool2.equals(notificationChannelPreferences.memberJoined)) {
                    return true;
                }
            } else if (notificationChannelPreferences.memberJoined == null) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        Boolean bool = this.contentAdded;
        int i = 0;
        int hashCode = (bool != null ? bool.hashCode() : 0) * 31;
        Boolean bool2 = this.memberJoined;
        if (bool2 != null) {
            i = bool2.hashCode();
        }
        return hashCode + i;
    }

    public Boolean isContentAdded() {
        return this.contentAdded;
    }

    public Boolean isMemberJoined() {
        return this.memberJoined;
    }

    public void setContentAdded(Boolean bool) {
        this.contentAdded = bool;
    }

    public void setMemberJoined(Boolean bool) {
        this.memberJoined = bool;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NotificationChannelPreferences{contentAdded=");
        outline107.append(this.contentAdded);
        outline107.append(", memberJoined=");
        outline107.append(this.memberJoined);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    public NotificationChannelPreferences withContentAdded(Boolean bool) {
        setContentAdded(bool);
        return this;
    }

    public NotificationChannelPreferences withMemberJoined(Boolean bool) {
        setMemberJoined(bool);
        return this;
    }

    @Override // java.lang.Comparable
    public int compareTo(NotificationChannelPreferences notificationChannelPreferences) {
        if (notificationChannelPreferences == null) {
            return -1;
        }
        if (notificationChannelPreferences == this) {
            return 0;
        }
        int compare = ObjectComparator.compare(isContentAdded(), notificationChannelPreferences.isContentAdded());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(isMemberJoined(), notificationChannelPreferences.isMemberJoined());
        if (compare2 == 0) {
            return 0;
        }
        return compare2;
    }
}
