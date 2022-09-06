package com.amazon.alexa.accessory.notificationpublisher.notificationsource;

import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.notificationpublisher.utils.GroupNotificationHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.util.Objects;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class GroupNotificationSource extends NotificationSource {
    private static final int MAXIMUM_GROUP_LENGTH = 64;
    private static final String NAMED_GROUP_KEY = "GROUP";
    private static final String UNNAMED_GROUP_KEY = "UNNAMED-GROUP";
    private String groupName;
    private String groupType;

    public GroupNotificationSource(@NonNull String str, @NonNull String str2, @NonNull String str3) throws IllegalArgumentException {
        super(str);
        if (!Strings.isNullOrEmpty(str2) && !Strings.isNullOrEmpty(str3)) {
            this.groupName = str2;
            this.groupType = str3;
            return;
        }
        throw new IllegalArgumentException("Cannot create a group source without valid group name and group type");
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.notificationsource.NotificationSource
    @NonNull
    public String displayString() {
        return String.format("Package: %s, GroupName: %s, GroupType: %s", this.packageId, this.groupName, this.groupType);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.notificationsource.NotificationSource
    public boolean equals(Object obj) {
        if (!(obj instanceof GroupNotificationSource)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        GroupNotificationSource groupNotificationSource = (GroupNotificationSource) obj;
        return groupNotificationSource.packageId.equals(this.packageId) && groupNotificationSource.groupType.equals(this.groupType) && groupNotificationSource.getSourceId().equals(getSourceId());
    }

    public String getGroupName() {
        return this.groupName;
    }

    public String getGroupType() {
        return this.groupType;
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.notificationsource.NotificationSource
    public String getSourceId() throws IllegalArgumentException {
        char c;
        String str = this.groupType;
        int hashCode = str.hashCode();
        if (hashCode != -282460096) {
            if (hashCode == 104585017 && str.equals(GroupNotificationHelper.GROUP_TYPE_NAMED)) {
                c = 0;
            }
            c = 65535;
        } else {
            if (str.equals(GroupNotificationHelper.GROUP_TYPE_UNNAMED)) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            return getPackageId() + ProcessIdUtil.DEFAULT_PROCESSID + "GROUP" + ProcessIdUtil.DEFAULT_PROCESSID + getGroupName().substring(0, Math.min(getGroupName().length(), 64));
        } else if (c == 1) {
            return getPackageId() + ProcessIdUtil.DEFAULT_PROCESSID + UNNAMED_GROUP_KEY;
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cannot get sourceId with invalid group type: ");
            outline107.append(this.groupType);
            throw new IllegalArgumentException(outline107.toString());
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.notificationsource.NotificationSource
    public int hashCode() {
        return Objects.hash(this.packageId, this.groupName, this.groupType);
    }

    public boolean isUnnamedGroupType() {
        return GroupNotificationHelper.GROUP_TYPE_UNNAMED.equals(this.groupType);
    }

    public GroupNotificationSource(@NonNull JSONObject jSONObject) throws IllegalArgumentException {
        super(jSONObject);
        String optString = jSONObject.optString(GroupNotificationHelper.PARSER_GROUP_NAME_KEY);
        String optString2 = jSONObject.optString(GroupNotificationHelper.PARSER_GROUP_TYPE_KEY);
        if (!Strings.isNullOrEmpty(optString) && !Strings.isNullOrEmpty(optString2)) {
            this.groupName = optString;
            this.groupType = optString2;
            return;
        }
        throw new IllegalArgumentException("Cannot create a group source without valid group name and group type");
    }
}
