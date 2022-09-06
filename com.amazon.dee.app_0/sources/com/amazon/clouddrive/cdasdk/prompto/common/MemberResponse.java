package com.amazon.clouddrive.cdasdk.prompto.common;

import com.amazon.clouddrive.cdasdk.prompto.profiles.PublicProfileResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.common.Scopes;
/* loaded from: classes11.dex */
public class MemberResponse {
    @JsonProperty("createdBy")
    private String createdBy;
    @JsonProperty("customerId")
    private String customerId;
    @JsonProperty("groupId")
    private String groupId;
    @JsonProperty("lastViewStatus")
    private String lastViewStatus;
    @JsonProperty("lastViewedDate")
    private String lastViewedDate;
    @JsonProperty("modifiedBy")
    private String modifiedBy;
    @JsonProperty(Scopes.PROFILE)
    private PublicProfileResponse profile;
    @JsonProperty("role")
    private String role;

    protected boolean canEqual(Object obj) {
        return obj instanceof MemberResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MemberResponse)) {
            return false;
        }
        MemberResponse memberResponse = (MemberResponse) obj;
        if (!memberResponse.canEqual(this)) {
            return false;
        }
        String groupId = getGroupId();
        String groupId2 = memberResponse.getGroupId();
        if (groupId != null ? !groupId.equals(groupId2) : groupId2 != null) {
            return false;
        }
        String createdBy = getCreatedBy();
        String createdBy2 = memberResponse.getCreatedBy();
        if (createdBy != null ? !createdBy.equals(createdBy2) : createdBy2 != null) {
            return false;
        }
        String customerId = getCustomerId();
        String customerId2 = memberResponse.getCustomerId();
        if (customerId != null ? !customerId.equals(customerId2) : customerId2 != null) {
            return false;
        }
        String modifiedBy = getModifiedBy();
        String modifiedBy2 = memberResponse.getModifiedBy();
        if (modifiedBy != null ? !modifiedBy.equals(modifiedBy2) : modifiedBy2 != null) {
            return false;
        }
        String role = getRole();
        String role2 = memberResponse.getRole();
        if (role != null ? !role.equals(role2) : role2 != null) {
            return false;
        }
        String lastViewedDate = getLastViewedDate();
        String lastViewedDate2 = memberResponse.getLastViewedDate();
        if (lastViewedDate != null ? !lastViewedDate.equals(lastViewedDate2) : lastViewedDate2 != null) {
            return false;
        }
        String lastViewStatus = getLastViewStatus();
        String lastViewStatus2 = memberResponse.getLastViewStatus();
        if (lastViewStatus != null ? !lastViewStatus.equals(lastViewStatus2) : lastViewStatus2 != null) {
            return false;
        }
        PublicProfileResponse profile = getProfile();
        PublicProfileResponse profile2 = memberResponse.getProfile();
        return profile != null ? profile.equals(profile2) : profile2 == null;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public String getLastViewStatus() {
        return this.lastViewStatus;
    }

    public String getLastViewedDate() {
        return this.lastViewedDate;
    }

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public PublicProfileResponse getProfile() {
        return this.profile;
    }

    public String getRole() {
        return this.role;
    }

    public int hashCode() {
        String groupId = getGroupId();
        int i = 43;
        int hashCode = groupId == null ? 43 : groupId.hashCode();
        String createdBy = getCreatedBy();
        int hashCode2 = ((hashCode + 59) * 59) + (createdBy == null ? 43 : createdBy.hashCode());
        String customerId = getCustomerId();
        int hashCode3 = (hashCode2 * 59) + (customerId == null ? 43 : customerId.hashCode());
        String modifiedBy = getModifiedBy();
        int hashCode4 = (hashCode3 * 59) + (modifiedBy == null ? 43 : modifiedBy.hashCode());
        String role = getRole();
        int hashCode5 = (hashCode4 * 59) + (role == null ? 43 : role.hashCode());
        String lastViewedDate = getLastViewedDate();
        int hashCode6 = (hashCode5 * 59) + (lastViewedDate == null ? 43 : lastViewedDate.hashCode());
        String lastViewStatus = getLastViewStatus();
        int hashCode7 = (hashCode6 * 59) + (lastViewStatus == null ? 43 : lastViewStatus.hashCode());
        PublicProfileResponse profile = getProfile();
        int i2 = hashCode7 * 59;
        if (profile != null) {
            i = profile.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("createdBy")
    public void setCreatedBy(String str) {
        this.createdBy = str;
    }

    @JsonProperty("customerId")
    public void setCustomerId(String str) {
        this.customerId = str;
    }

    @JsonProperty("groupId")
    public void setGroupId(String str) {
        this.groupId = str;
    }

    @JsonProperty("lastViewStatus")
    public void setLastViewStatus(String str) {
        this.lastViewStatus = str;
    }

    @JsonProperty("lastViewedDate")
    public void setLastViewedDate(String str) {
        this.lastViewedDate = str;
    }

    @JsonProperty("modifiedBy")
    public void setModifiedBy(String str) {
        this.modifiedBy = str;
    }

    @JsonProperty(Scopes.PROFILE)
    public void setProfile(PublicProfileResponse publicProfileResponse) {
        this.profile = publicProfileResponse;
    }

    @JsonProperty("role")
    public void setRole(String str) {
        this.role = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MemberResponse(groupId=");
        outline107.append(getGroupId());
        outline107.append(", createdBy=");
        outline107.append(getCreatedBy());
        outline107.append(", customerId=");
        outline107.append(getCustomerId());
        outline107.append(", modifiedBy=");
        outline107.append(getModifiedBy());
        outline107.append(", role=");
        outline107.append(getRole());
        outline107.append(", lastViewedDate=");
        outline107.append(getLastViewedDate());
        outline107.append(", lastViewStatus=");
        outline107.append(getLastViewStatus());
        outline107.append(", profile=");
        outline107.append(getProfile());
        outline107.append(")");
        return outline107.toString();
    }
}
