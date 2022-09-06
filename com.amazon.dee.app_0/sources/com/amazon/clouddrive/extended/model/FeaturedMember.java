package com.amazon.clouddrive.extended.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class FeaturedMember {
    private String createdBy;
    private String createdDate;
    private String customerId;
    private String groupId;
    private String lastViewStatus;
    private String lastViewedDate;
    private String modifiedBy;
    private String modifiedDate;
    private Profile profile;
    private String role;
    private long version;

    protected boolean canEqual(Object obj) {
        return obj instanceof FeaturedMember;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FeaturedMember)) {
            return false;
        }
        FeaturedMember featuredMember = (FeaturedMember) obj;
        if (!featuredMember.canEqual(this)) {
            return false;
        }
        String createdBy = getCreatedBy();
        String createdBy2 = featuredMember.getCreatedBy();
        if (createdBy != null ? !createdBy.equals(createdBy2) : createdBy2 != null) {
            return false;
        }
        String createdDate = getCreatedDate();
        String createdDate2 = featuredMember.getCreatedDate();
        if (createdDate != null ? !createdDate.equals(createdDate2) : createdDate2 != null) {
            return false;
        }
        String customerId = getCustomerId();
        String customerId2 = featuredMember.getCustomerId();
        if (customerId != null ? !customerId.equals(customerId2) : customerId2 != null) {
            return false;
        }
        String groupId = getGroupId();
        String groupId2 = featuredMember.getGroupId();
        if (groupId != null ? !groupId.equals(groupId2) : groupId2 != null) {
            return false;
        }
        String modifiedBy = getModifiedBy();
        String modifiedBy2 = featuredMember.getModifiedBy();
        if (modifiedBy != null ? !modifiedBy.equals(modifiedBy2) : modifiedBy2 != null) {
            return false;
        }
        String modifiedDate = getModifiedDate();
        String modifiedDate2 = featuredMember.getModifiedDate();
        if (modifiedDate != null ? !modifiedDate.equals(modifiedDate2) : modifiedDate2 != null) {
            return false;
        }
        String lastViewedDate = getLastViewedDate();
        String lastViewedDate2 = featuredMember.getLastViewedDate();
        if (lastViewedDate != null ? !lastViewedDate.equals(lastViewedDate2) : lastViewedDate2 != null) {
            return false;
        }
        String lastViewStatus = getLastViewStatus();
        String lastViewStatus2 = featuredMember.getLastViewStatus();
        if (lastViewStatus != null ? !lastViewStatus.equals(lastViewStatus2) : lastViewStatus2 != null) {
            return false;
        }
        Profile profile = getProfile();
        Profile profile2 = featuredMember.getProfile();
        if (profile != null ? !profile.equals(profile2) : profile2 != null) {
            return false;
        }
        String role = getRole();
        String role2 = featuredMember.getRole();
        if (role != null ? !role.equals(role2) : role2 != null) {
            return false;
        }
        return getVersion() == featuredMember.getVersion();
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public String getCreatedDate() {
        return this.createdDate;
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

    public String getModifiedDate() {
        return this.modifiedDate;
    }

    public Profile getProfile() {
        return this.profile;
    }

    public String getRole() {
        return this.role;
    }

    public long getVersion() {
        return this.version;
    }

    public int hashCode() {
        String createdBy = getCreatedBy();
        int i = 43;
        int hashCode = createdBy == null ? 43 : createdBy.hashCode();
        String createdDate = getCreatedDate();
        int hashCode2 = ((hashCode + 59) * 59) + (createdDate == null ? 43 : createdDate.hashCode());
        String customerId = getCustomerId();
        int hashCode3 = (hashCode2 * 59) + (customerId == null ? 43 : customerId.hashCode());
        String groupId = getGroupId();
        int hashCode4 = (hashCode3 * 59) + (groupId == null ? 43 : groupId.hashCode());
        String modifiedBy = getModifiedBy();
        int hashCode5 = (hashCode4 * 59) + (modifiedBy == null ? 43 : modifiedBy.hashCode());
        String modifiedDate = getModifiedDate();
        int hashCode6 = (hashCode5 * 59) + (modifiedDate == null ? 43 : modifiedDate.hashCode());
        String lastViewedDate = getLastViewedDate();
        int hashCode7 = (hashCode6 * 59) + (lastViewedDate == null ? 43 : lastViewedDate.hashCode());
        String lastViewStatus = getLastViewStatus();
        int hashCode8 = (hashCode7 * 59) + (lastViewStatus == null ? 43 : lastViewStatus.hashCode());
        Profile profile = getProfile();
        int hashCode9 = (hashCode8 * 59) + (profile == null ? 43 : profile.hashCode());
        String role = getRole();
        int i2 = hashCode9 * 59;
        if (role != null) {
            i = role.hashCode();
        }
        long version = getVersion();
        return ((i2 + i) * 59) + ((int) ((version >>> 32) ^ version));
    }

    public void setCreatedBy(String str) {
        this.createdBy = str;
    }

    public void setCreatedDate(String str) {
        this.createdDate = str;
    }

    public void setCustomerId(String str) {
        this.customerId = str;
    }

    public void setGroupId(String str) {
        this.groupId = str;
    }

    public void setLastViewStatus(String str) {
        this.lastViewStatus = str;
    }

    public void setLastViewedDate(String str) {
        this.lastViewedDate = str;
    }

    public void setModifiedBy(String str) {
        this.modifiedBy = str;
    }

    public void setModifiedDate(String str) {
        this.modifiedDate = str;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setRole(String str) {
        this.role = str;
    }

    public void setVersion(long j) {
        this.version = j;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FeaturedMember(createdBy=");
        outline107.append(getCreatedBy());
        outline107.append(", createdDate=");
        outline107.append(getCreatedDate());
        outline107.append(", customerId=");
        outline107.append(getCustomerId());
        outline107.append(", groupId=");
        outline107.append(getGroupId());
        outline107.append(", modifiedBy=");
        outline107.append(getModifiedBy());
        outline107.append(", modifiedDate=");
        outline107.append(getModifiedDate());
        outline107.append(", lastViewedDate=");
        outline107.append(getLastViewedDate());
        outline107.append(", lastViewStatus=");
        outline107.append(getLastViewStatus());
        outline107.append(", profile=");
        outline107.append(getProfile());
        outline107.append(", role=");
        outline107.append(getRole());
        outline107.append(", version=");
        outline107.append(getVersion());
        outline107.append(")");
        return outline107.toString();
    }
}
