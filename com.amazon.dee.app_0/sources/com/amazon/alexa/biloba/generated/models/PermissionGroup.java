package com.amazon.alexa.biloba.generated.models;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.wakeword.speakerverification.profile.SpeakerVerificationProfileProvider;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes6.dex */
public class PermissionGroup {
    @SerializedName(SpeakerVerificationProfileProvider.COLUMN_PERSON_ID)
    private String personId = null;
    @SerializedName(MessagingControllerConstant.MESSAGING_CONTROLLER_ENDPOINT_PERMISSIONS_KEY)
    private List<String> permissions = new ArrayList();

    private String toIndentedString(Object obj) {
        return obj == null ? "null" : obj.toString().replace("\n", "\n    ");
    }

    public PermissionGroup addPermissionsItem(String str) {
        this.permissions.add(str);
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PermissionGroup.class != obj.getClass()) {
            return false;
        }
        PermissionGroup permissionGroup = (PermissionGroup) obj;
        return Objects.equals(this.personId, permissionGroup.personId) && Objects.equals(this.permissions, permissionGroup.permissions);
    }

    public List<String> getPermissions() {
        return this.permissions;
    }

    public String getPersonId() {
        return this.personId;
    }

    public int hashCode() {
        return Objects.hash(this.personId, this.permissions);
    }

    public PermissionGroup permissions(List<String> list) {
        this.permissions = list;
        return this;
    }

    public PermissionGroup personId(String str) {
        this.personId = str;
        return this;
    }

    public void setPermissions(List<String> list) {
        this.permissions = list;
    }

    public void setPersonId(String str) {
        this.personId = str;
    }

    public String toString() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("class PermissionGroup {\n", "    personId: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.personId), "\n", "    permissions: ");
        return GeneratedOutlineSupport1.outline92(outline113, toIndentedString(this.permissions), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
