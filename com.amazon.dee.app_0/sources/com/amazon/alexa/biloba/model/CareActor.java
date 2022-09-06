package com.amazon.alexa.biloba.model;

import com.amazon.alexa.biloba.generated.models.PermissionGroup;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes6.dex */
public class CareActor {
    @SerializedName("personIdV1")
    private String personIdV1 = null;
    @SerializedName("directedPersonIdV2")
    private String directedPersonIdV2 = null;
    @SerializedName("firstName")
    private String firstName = null;
    @SerializedName("lastName")
    private String lastName = null;
    @SerializedName(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME)
    private String nickName = null;
    @SerializedName("commsId")
    private String commsId = null;
    @SerializedName("contactId")
    private String contactId = null;
    @SerializedName("isDropInEnabled")
    private Boolean isDropInEnabled = null;
    @SerializedName("isAlexaEnabled")
    private Boolean isAlexaEnabled = null;
    @SerializedName("isCommsProvisioned")
    private Boolean isCommsProvisioned = null;
    @SerializedName("groupId")
    private String groupId = null;
    @SerializedName("subscriptionStatus")
    private String subscriptionStatus = null;
    @SerializedName(WebConstants.WebviewConstants.SUBSCRIPTION_ID)
    private String subscriptionId = null;
    @SerializedName("role")
    private String role = null;
    @SerializedName("commsStatus")
    private String commsStatus = null;
    @SerializedName("dropInStatus")
    private String dropInStatus = null;
    @SerializedName(MessagingControllerConstant.MESSAGING_CONTROLLER_ENDPOINT_PERMISSIONS_KEY)
    private List<String> permissions = new ArrayList();
    @SerializedName("permissionGroups")
    private List<PermissionGroup> permissionGroups = null;

    private String toIndentedString(Object obj) {
        return obj == null ? "null" : obj.toString().replace("\n", "\n    ");
    }

    public CareActor addPermissionGroupsItem(PermissionGroup permissionGroup) {
        if (this.permissionGroups == null) {
            this.permissionGroups = new ArrayList();
        }
        this.permissionGroups.add(permissionGroup);
        return this;
    }

    public CareActor commsId(String str) {
        this.commsId = str;
        return this;
    }

    public CareActor contactId(String str) {
        this.contactId = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CareActor.class != obj.getClass()) {
            return false;
        }
        CareActor careActor = (CareActor) obj;
        return Objects.equals(this.personIdV1, careActor.personIdV1) && Objects.equals(this.directedPersonIdV2, careActor.directedPersonIdV2) && Objects.equals(this.firstName, careActor.firstName) && Objects.equals(this.lastName, careActor.lastName) && Objects.equals(this.nickName, careActor.nickName) && Objects.equals(this.commsId, careActor.commsId) && Objects.equals(this.role, careActor.role) && Objects.equals(this.permissions, careActor.permissions) && Objects.equals(this.commsStatus, careActor.commsStatus) && Objects.equals(this.dropInStatus, careActor.dropInStatus) && Objects.equals(this.contactId, careActor.contactId) && Objects.equals(this.isDropInEnabled, careActor.isDropInEnabled) && Objects.equals(this.isAlexaEnabled, careActor.isAlexaEnabled) && Objects.equals(this.isCommsProvisioned, careActor.isCommsProvisioned) && Objects.equals(this.groupId, careActor.groupId) && Objects.equals(this.subscriptionStatus, careActor.subscriptionStatus) && Objects.equals(this.subscriptionId, careActor.subscriptionId) && Objects.equals(this.permissionGroups, careActor.permissionGroups);
    }

    public CareActor firstName(String str) {
        this.firstName = str;
        return this;
    }

    public String getCommsId() {
        return this.commsId;
    }

    public String getCommsStatus() {
        return this.commsStatus;
    }

    public String getContactId() {
        return this.contactId;
    }

    public String getDirectedPersonIdV2() {
        return this.directedPersonIdV2;
    }

    public String getDropInStatus() {
        return this.dropInStatus;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public Boolean getIsAlexaEnabled() {
        return this.isAlexaEnabled;
    }

    public Boolean getIsCommsProvisioned() {
        return this.isCommsProvisioned;
    }

    public Boolean getIsDropInEnabled() {
        return this.isDropInEnabled;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getNickName() {
        return this.nickName;
    }

    public List<PermissionGroup> getPermissionGroups() {
        return this.permissionGroups;
    }

    public List<String> getPermissions() {
        return this.permissions;
    }

    public String getPersonIdV1() {
        return this.personIdV1;
    }

    public String getRole() {
        return this.role;
    }

    public String getSubscriptionId() {
        return this.subscriptionId;
    }

    public String getSubscriptionStatus() {
        return this.subscriptionStatus;
    }

    public CareActor groupId(String str) {
        this.groupId = str;
        return this;
    }

    public int hashCode() {
        return Objects.hash(this.personIdV1, this.directedPersonIdV2, this.firstName, this.lastName, this.nickName, this.commsId, this.contactId, this.isDropInEnabled, this.isAlexaEnabled, this.isCommsProvisioned, this.groupId, this.subscriptionStatus, this.subscriptionId, this.permissionGroups, this.role, this.permissions, this.commsStatus, this.dropInStatus);
    }

    public CareActor isAlexaEnabled(Boolean bool) {
        this.isAlexaEnabled = bool;
        return this;
    }

    public CareActor isCommsProvisioned(Boolean bool) {
        this.isCommsProvisioned = bool;
        return this;
    }

    public CareActor isDropInEnabled(Boolean bool) {
        this.isDropInEnabled = bool;
        return this;
    }

    public CareActor lastName(String str) {
        this.lastName = str;
        return this;
    }

    public CareActor nickName(String str) {
        this.nickName = str;
        return this;
    }

    public CareActor permissionGroups(List<PermissionGroup> list) {
        this.permissionGroups = list;
        return this;
    }

    public CareActor personIdV1(String str) {
        this.personIdV1 = str;
        return this;
    }

    public void setCommsId(String str) {
        this.commsId = str;
    }

    public void setCommsStatus(String str) {
        this.commsStatus = str;
    }

    public void setContactId(String str) {
        this.contactId = str;
    }

    public void setDirectedPersonIdV2(String str) {
        this.directedPersonIdV2 = str;
    }

    public void setDropInStatus(String str) {
        this.dropInStatus = str;
    }

    public void setFirstName(String str) {
        this.firstName = str;
    }

    public void setGroupId(String str) {
        this.groupId = str;
    }

    public void setIsAlexaEnabled(Boolean bool) {
        this.isAlexaEnabled = bool;
    }

    public void setIsCommsProvisioned(Boolean bool) {
        this.isCommsProvisioned = bool;
    }

    public void setIsDropInEnabled(Boolean bool) {
        this.isDropInEnabled = bool;
    }

    public void setLastName(String str) {
        this.lastName = str;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public void setPermissionGroups(List<PermissionGroup> list) {
        this.permissionGroups = list;
    }

    public void setPermissions(List<String> list) {
        this.permissions = list;
    }

    public void setPersonIdV1(String str) {
        this.personIdV1 = str;
    }

    public void setRole(String str) {
        this.role = str;
    }

    public void setSubscriptionId(String str) {
        this.subscriptionId = str;
    }

    public void setSubscriptionStatus(String str) {
        this.subscriptionStatus = str;
    }

    public CareActor subscriptionId(String str) {
        this.subscriptionId = str;
        return this;
    }

    public String toString() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("class CareActor {\n", "    personIdV1: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.personIdV1), "\n", "    directedPersonIdV2: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.directedPersonIdV2), "\n", "    firstName: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.firstName), "\n", "    lastName: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.lastName), "\n", "    nickName: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.nickName), "\n", "    commsId: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.commsId), "\n", "    contactId: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.contactId), "\n", "    isDropInEnabled: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.isDropInEnabled), "\n", "    isAlexaEnabled: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.isAlexaEnabled), "\n", "    isCommsProvisioned: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.isCommsProvisioned), "\n", "    groupId: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.groupId), "\n", "    role: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.role), "\n", "    permissions: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.permissions), "\n", "    commsStatus: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.commsStatus), "\n", "    dropInStatus: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.dropInStatus), "\n", "    subscriptionStatus: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.subscriptionStatus), "\n", "    subscriptionId: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.subscriptionId), "\n", "    permissionGroups: ");
        return GeneratedOutlineSupport1.outline92(outline113, toIndentedString(this.permissionGroups), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
