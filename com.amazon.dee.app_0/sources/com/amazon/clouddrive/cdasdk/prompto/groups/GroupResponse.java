package com.amazon.clouddrive.cdasdk.prompto.groups;

import com.amazon.clouddrive.cdasdk.prompto.common.MemberResponse;
import com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceResponse;
import com.amazon.clouddrive.cdasdk.prompto.contentAggregations.ContentAggregationsResponse;
import com.amazon.clouddrive.cdasdk.prompto.photos.PhotoResponse;
import com.amazon.clouddrive.cdasdk.prompto.profiles.PublicProfileResponse;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class GroupResponse extends PromptoServiceResponse {
    @JsonProperty("addedBy")
    private PublicProfileResponse addedBy;
    @JsonProperty("contentAggregations")
    private ContentAggregationsResponse contentAggregations;
    @JsonProperty("coverNodeId")
    private String coverNodeId;
    @JsonProperty("coverPhoto")
    private PhotoResponse coverPhoto;
    @JsonProperty("createdBy")
    private String createdBy;
    @JsonProperty("featuredMembers")
    private List<MemberResponse> featuredMembers;
    @JsonProperty("groupId")
    private String groupId;
    @JsonProperty("groupPreferences")
    private GroupPreferences groupPreferences;
    @JsonProperty("inviteCallToAction")
    private String inviteCallToAction;
    @JsonProperty("inviteDescription")
    private String inviteDescription;
    @JsonProperty(ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME)
    private String kind;
    @JsonProperty("lastViewedDate")
    private String lastViewedDate;
    @JsonProperty("memberCount")
    private Long memberCount;
    @JsonProperty("modifiedBy")
    private String modifiedBy;
    @JsonProperty("name")
    private String name;
    @JsonProperty("nameType")
    private String nameType;
    @JsonProperty("ownedBy")
    private String ownedBy;
    @JsonProperty("unseenContentAggregations")
    private ContentAggregationsResponse unseenContentAggregations;

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceResponse
    protected boolean canEqual(Object obj) {
        return obj instanceof GroupResponse;
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GroupResponse)) {
            return false;
        }
        GroupResponse groupResponse = (GroupResponse) obj;
        if (!groupResponse.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String groupId = getGroupId();
        String groupId2 = groupResponse.getGroupId();
        if (groupId != null ? !groupId.equals(groupId2) : groupId2 != null) {
            return false;
        }
        String name = getName();
        String name2 = groupResponse.getName();
        if (name != null ? !name.equals(name2) : name2 != null) {
            return false;
        }
        String nameType = getNameType();
        String nameType2 = groupResponse.getNameType();
        if (nameType != null ? !nameType.equals(nameType2) : nameType2 != null) {
            return false;
        }
        String coverNodeId = getCoverNodeId();
        String coverNodeId2 = groupResponse.getCoverNodeId();
        if (coverNodeId != null ? !coverNodeId.equals(coverNodeId2) : coverNodeId2 != null) {
            return false;
        }
        PhotoResponse coverPhoto = getCoverPhoto();
        PhotoResponse coverPhoto2 = groupResponse.getCoverPhoto();
        if (coverPhoto != null ? !coverPhoto.equals(coverPhoto2) : coverPhoto2 != null) {
            return false;
        }
        String createdBy = getCreatedBy();
        String createdBy2 = groupResponse.getCreatedBy();
        if (createdBy != null ? !createdBy.equals(createdBy2) : createdBy2 != null) {
            return false;
        }
        String modifiedBy = getModifiedBy();
        String modifiedBy2 = groupResponse.getModifiedBy();
        if (modifiedBy != null ? !modifiedBy.equals(modifiedBy2) : modifiedBy2 != null) {
            return false;
        }
        String ownedBy = getOwnedBy();
        String ownedBy2 = groupResponse.getOwnedBy();
        if (ownedBy != null ? !ownedBy.equals(ownedBy2) : ownedBy2 != null) {
            return false;
        }
        List<MemberResponse> featuredMembers = getFeaturedMembers();
        List<MemberResponse> featuredMembers2 = groupResponse.getFeaturedMembers();
        if (featuredMembers != null ? !featuredMembers.equals(featuredMembers2) : featuredMembers2 != null) {
            return false;
        }
        Long memberCount = getMemberCount();
        Long memberCount2 = groupResponse.getMemberCount();
        if (memberCount != null ? !memberCount.equals(memberCount2) : memberCount2 != null) {
            return false;
        }
        ContentAggregationsResponse contentAggregations = getContentAggregations();
        ContentAggregationsResponse contentAggregations2 = groupResponse.getContentAggregations();
        if (contentAggregations != null ? !contentAggregations.equals(contentAggregations2) : contentAggregations2 != null) {
            return false;
        }
        ContentAggregationsResponse unseenContentAggregations = getUnseenContentAggregations();
        ContentAggregationsResponse unseenContentAggregations2 = groupResponse.getUnseenContentAggregations();
        if (unseenContentAggregations != null ? !unseenContentAggregations.equals(unseenContentAggregations2) : unseenContentAggregations2 != null) {
            return false;
        }
        String lastViewedDate = getLastViewedDate();
        String lastViewedDate2 = groupResponse.getLastViewedDate();
        if (lastViewedDate != null ? !lastViewedDate.equals(lastViewedDate2) : lastViewedDate2 != null) {
            return false;
        }
        PublicProfileResponse addedBy = getAddedBy();
        PublicProfileResponse addedBy2 = groupResponse.getAddedBy();
        if (addedBy != null ? !addedBy.equals(addedBy2) : addedBy2 != null) {
            return false;
        }
        String inviteCallToAction = getInviteCallToAction();
        String inviteCallToAction2 = groupResponse.getInviteCallToAction();
        if (inviteCallToAction != null ? !inviteCallToAction.equals(inviteCallToAction2) : inviteCallToAction2 != null) {
            return false;
        }
        String inviteDescription = getInviteDescription();
        String inviteDescription2 = groupResponse.getInviteDescription();
        if (inviteDescription != null ? !inviteDescription.equals(inviteDescription2) : inviteDescription2 != null) {
            return false;
        }
        String kind = getKind();
        String kind2 = groupResponse.getKind();
        if (kind != null ? !kind.equals(kind2) : kind2 != null) {
            return false;
        }
        GroupPreferences groupPreferences = getGroupPreferences();
        GroupPreferences groupPreferences2 = groupResponse.getGroupPreferences();
        return groupPreferences != null ? groupPreferences.equals(groupPreferences2) : groupPreferences2 == null;
    }

    public PublicProfileResponse getAddedBy() {
        return this.addedBy;
    }

    public ContentAggregationsResponse getContentAggregations() {
        return this.contentAggregations;
    }

    public String getCoverNodeId() {
        return this.coverNodeId;
    }

    public PhotoResponse getCoverPhoto() {
        return this.coverPhoto;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public List<MemberResponse> getFeaturedMembers() {
        return this.featuredMembers;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public GroupPreferences getGroupPreferences() {
        return this.groupPreferences;
    }

    public String getInviteCallToAction() {
        return this.inviteCallToAction;
    }

    public String getInviteDescription() {
        return this.inviteDescription;
    }

    public String getKind() {
        return this.kind;
    }

    public String getLastViewedDate() {
        return this.lastViewedDate;
    }

    public Long getMemberCount() {
        return this.memberCount;
    }

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public String getName() {
        return this.name;
    }

    public String getNameType() {
        return this.nameType;
    }

    public String getOwnedBy() {
        return this.ownedBy;
    }

    public ContentAggregationsResponse getUnseenContentAggregations() {
        return this.unseenContentAggregations;
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceResponse
    public int hashCode() {
        int hashCode = super.hashCode();
        String groupId = getGroupId();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (groupId == null ? 43 : groupId.hashCode());
        String name = getName();
        int hashCode3 = (hashCode2 * 59) + (name == null ? 43 : name.hashCode());
        String nameType = getNameType();
        int hashCode4 = (hashCode3 * 59) + (nameType == null ? 43 : nameType.hashCode());
        String coverNodeId = getCoverNodeId();
        int hashCode5 = (hashCode4 * 59) + (coverNodeId == null ? 43 : coverNodeId.hashCode());
        PhotoResponse coverPhoto = getCoverPhoto();
        int hashCode6 = (hashCode5 * 59) + (coverPhoto == null ? 43 : coverPhoto.hashCode());
        String createdBy = getCreatedBy();
        int hashCode7 = (hashCode6 * 59) + (createdBy == null ? 43 : createdBy.hashCode());
        String modifiedBy = getModifiedBy();
        int hashCode8 = (hashCode7 * 59) + (modifiedBy == null ? 43 : modifiedBy.hashCode());
        String ownedBy = getOwnedBy();
        int hashCode9 = (hashCode8 * 59) + (ownedBy == null ? 43 : ownedBy.hashCode());
        List<MemberResponse> featuredMembers = getFeaturedMembers();
        int hashCode10 = (hashCode9 * 59) + (featuredMembers == null ? 43 : featuredMembers.hashCode());
        Long memberCount = getMemberCount();
        int hashCode11 = (hashCode10 * 59) + (memberCount == null ? 43 : memberCount.hashCode());
        ContentAggregationsResponse contentAggregations = getContentAggregations();
        int hashCode12 = (hashCode11 * 59) + (contentAggregations == null ? 43 : contentAggregations.hashCode());
        ContentAggregationsResponse unseenContentAggregations = getUnseenContentAggregations();
        int hashCode13 = (hashCode12 * 59) + (unseenContentAggregations == null ? 43 : unseenContentAggregations.hashCode());
        String lastViewedDate = getLastViewedDate();
        int hashCode14 = (hashCode13 * 59) + (lastViewedDate == null ? 43 : lastViewedDate.hashCode());
        PublicProfileResponse addedBy = getAddedBy();
        int hashCode15 = (hashCode14 * 59) + (addedBy == null ? 43 : addedBy.hashCode());
        String inviteCallToAction = getInviteCallToAction();
        int hashCode16 = (hashCode15 * 59) + (inviteCallToAction == null ? 43 : inviteCallToAction.hashCode());
        String inviteDescription = getInviteDescription();
        int hashCode17 = (hashCode16 * 59) + (inviteDescription == null ? 43 : inviteDescription.hashCode());
        String kind = getKind();
        int hashCode18 = (hashCode17 * 59) + (kind == null ? 43 : kind.hashCode());
        GroupPreferences groupPreferences = getGroupPreferences();
        int i2 = hashCode18 * 59;
        if (groupPreferences != null) {
            i = groupPreferences.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("addedBy")
    public void setAddedBy(PublicProfileResponse publicProfileResponse) {
        this.addedBy = publicProfileResponse;
    }

    @JsonProperty("contentAggregations")
    public void setContentAggregations(ContentAggregationsResponse contentAggregationsResponse) {
        this.contentAggregations = contentAggregationsResponse;
    }

    @JsonProperty("coverNodeId")
    public void setCoverNodeId(String str) {
        this.coverNodeId = str;
    }

    @JsonProperty("coverPhoto")
    public void setCoverPhoto(PhotoResponse photoResponse) {
        this.coverPhoto = photoResponse;
    }

    @JsonProperty("createdBy")
    public void setCreatedBy(String str) {
        this.createdBy = str;
    }

    @JsonProperty("featuredMembers")
    public void setFeaturedMembers(List<MemberResponse> list) {
        this.featuredMembers = list;
    }

    @JsonProperty("groupId")
    public void setGroupId(String str) {
        this.groupId = str;
    }

    @JsonProperty("groupPreferences")
    public void setGroupPreferences(GroupPreferences groupPreferences) {
        this.groupPreferences = groupPreferences;
    }

    @JsonProperty("inviteCallToAction")
    public void setInviteCallToAction(String str) {
        this.inviteCallToAction = str;
    }

    @JsonProperty("inviteDescription")
    public void setInviteDescription(String str) {
        this.inviteDescription = str;
    }

    @JsonProperty(ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME)
    public void setKind(String str) {
        this.kind = str;
    }

    @JsonProperty("lastViewedDate")
    public void setLastViewedDate(String str) {
        this.lastViewedDate = str;
    }

    @JsonProperty("memberCount")
    public void setMemberCount(Long l) {
        this.memberCount = l;
    }

    @JsonProperty("modifiedBy")
    public void setModifiedBy(String str) {
        this.modifiedBy = str;
    }

    @JsonProperty("name")
    public void setName(String str) {
        this.name = str;
    }

    @JsonProperty("nameType")
    public void setNameType(String str) {
        this.nameType = str;
    }

    @JsonProperty("ownedBy")
    public void setOwnedBy(String str) {
        this.ownedBy = str;
    }

    @JsonProperty("unseenContentAggregations")
    public void setUnseenContentAggregations(ContentAggregationsResponse contentAggregationsResponse) {
        this.unseenContentAggregations = contentAggregationsResponse;
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceResponse
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GroupResponse(groupId=");
        outline107.append(getGroupId());
        outline107.append(", name=");
        outline107.append(getName());
        outline107.append(", nameType=");
        outline107.append(getNameType());
        outline107.append(", coverNodeId=");
        outline107.append(getCoverNodeId());
        outline107.append(", coverPhoto=");
        outline107.append(getCoverPhoto());
        outline107.append(", createdBy=");
        outline107.append(getCreatedBy());
        outline107.append(", modifiedBy=");
        outline107.append(getModifiedBy());
        outline107.append(", ownedBy=");
        outline107.append(getOwnedBy());
        outline107.append(", featuredMembers=");
        outline107.append(getFeaturedMembers());
        outline107.append(", memberCount=");
        outline107.append(getMemberCount());
        outline107.append(", contentAggregations=");
        outline107.append(getContentAggregations());
        outline107.append(", unseenContentAggregations=");
        outline107.append(getUnseenContentAggregations());
        outline107.append(", lastViewedDate=");
        outline107.append(getLastViewedDate());
        outline107.append(", addedBy=");
        outline107.append(getAddedBy());
        outline107.append(", inviteCallToAction=");
        outline107.append(getInviteCallToAction());
        outline107.append(", inviteDescription=");
        outline107.append(getInviteDescription());
        outline107.append(", kind=");
        outline107.append(getKind());
        outline107.append(", groupPreferences=");
        outline107.append(getGroupPreferences());
        outline107.append(")");
        return outline107.toString();
    }
}
