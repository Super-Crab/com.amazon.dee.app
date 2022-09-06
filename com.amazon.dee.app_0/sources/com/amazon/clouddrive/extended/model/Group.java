package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.ObjectComparator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes11.dex */
public class Group implements Comparable<Group> {
    private Profile addedBy;
    private FeaturedMember callerProfile;
    private ContentAggregations contentAggregations;
    private String coverNodeId;
    private GroupCoverPhoto coverPhoto;
    private String createdBy;
    private String createdDate;
    private String description;
    private List<FeaturedMember> featuredMembers;
    private String groupId;
    private GroupRestriction groupPreferences;
    private String inviteCallToAction;
    private String inviteDescription;
    private String kind;
    private String lastViewedDate;
    private int memberCount;
    private String modifiedBy;
    private String modifiedDate;
    private String name;
    private String nameType;
    private ReferralInfoResponse referralInfo;
    private ContentAggregations unseenContentAggregations;
    private long version;

    protected boolean canEqual(Object obj) {
        return obj instanceof Group;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Group)) {
            return false;
        }
        Group group = (Group) obj;
        if (!group.canEqual(this)) {
            return false;
        }
        String coverNodeId = getCoverNodeId();
        String coverNodeId2 = group.getCoverNodeId();
        if (coverNodeId != null ? !coverNodeId.equals(coverNodeId2) : coverNodeId2 != null) {
            return false;
        }
        GroupCoverPhoto coverPhoto = getCoverPhoto();
        GroupCoverPhoto coverPhoto2 = group.getCoverPhoto();
        if (coverPhoto != null ? !coverPhoto.equals(coverPhoto2) : coverPhoto2 != null) {
            return false;
        }
        String createdBy = getCreatedBy();
        String createdBy2 = group.getCreatedBy();
        if (createdBy != null ? !createdBy.equals(createdBy2) : createdBy2 != null) {
            return false;
        }
        String createdDate = getCreatedDate();
        String createdDate2 = group.getCreatedDate();
        if (createdDate != null ? !createdDate.equals(createdDate2) : createdDate2 != null) {
            return false;
        }
        String description = getDescription();
        String description2 = group.getDescription();
        if (description != null ? !description.equals(description2) : description2 != null) {
            return false;
        }
        List<FeaturedMember> featuredMembers = getFeaturedMembers();
        List<FeaturedMember> featuredMembers2 = group.getFeaturedMembers();
        if (featuredMembers != null ? !featuredMembers.equals(featuredMembers2) : featuredMembers2 != null) {
            return false;
        }
        String groupId = getGroupId();
        String groupId2 = group.getGroupId();
        if (groupId != null ? !groupId.equals(groupId2) : groupId2 != null) {
            return false;
        }
        String modifiedBy = getModifiedBy();
        String modifiedBy2 = group.getModifiedBy();
        if (modifiedBy != null ? !modifiedBy.equals(modifiedBy2) : modifiedBy2 != null) {
            return false;
        }
        String modifiedDate = getModifiedDate();
        String modifiedDate2 = group.getModifiedDate();
        if (modifiedDate != null ? !modifiedDate.equals(modifiedDate2) : modifiedDate2 != null) {
            return false;
        }
        String name = getName();
        String name2 = group.getName();
        if (name != null ? !name.equals(name2) : name2 != null) {
            return false;
        }
        String nameType = getNameType();
        String nameType2 = group.getNameType();
        if (nameType != null ? !nameType.equals(nameType2) : nameType2 != null) {
            return false;
        }
        FeaturedMember callerProfile = getCallerProfile();
        FeaturedMember callerProfile2 = group.getCallerProfile();
        if (callerProfile != null ? !callerProfile.equals(callerProfile2) : callerProfile2 != null) {
            return false;
        }
        ContentAggregations unseenContentAggregations = getUnseenContentAggregations();
        ContentAggregations unseenContentAggregations2 = group.getUnseenContentAggregations();
        if (unseenContentAggregations != null ? !unseenContentAggregations.equals(unseenContentAggregations2) : unseenContentAggregations2 != null) {
            return false;
        }
        if (getVersion() != group.getVersion()) {
            return false;
        }
        ContentAggregations contentAggregations = getContentAggregations();
        ContentAggregations contentAggregations2 = group.getContentAggregations();
        if (contentAggregations != null ? !contentAggregations.equals(contentAggregations2) : contentAggregations2 != null) {
            return false;
        }
        if (getMemberCount() != group.getMemberCount()) {
            return false;
        }
        Profile addedBy = getAddedBy();
        Profile addedBy2 = group.getAddedBy();
        if (addedBy != null ? !addedBy.equals(addedBy2) : addedBy2 != null) {
            return false;
        }
        String lastViewedDate = getLastViewedDate();
        String lastViewedDate2 = group.getLastViewedDate();
        if (lastViewedDate != null ? !lastViewedDate.equals(lastViewedDate2) : lastViewedDate2 != null) {
            return false;
        }
        String inviteCallToAction = getInviteCallToAction();
        String inviteCallToAction2 = group.getInviteCallToAction();
        if (inviteCallToAction != null ? !inviteCallToAction.equals(inviteCallToAction2) : inviteCallToAction2 != null) {
            return false;
        }
        String inviteDescription = getInviteDescription();
        String inviteDescription2 = group.getInviteDescription();
        if (inviteDescription != null ? !inviteDescription.equals(inviteDescription2) : inviteDescription2 != null) {
            return false;
        }
        GroupRestriction groupPreferences = getGroupPreferences();
        GroupRestriction groupPreferences2 = group.getGroupPreferences();
        if (groupPreferences != null ? !groupPreferences.equals(groupPreferences2) : groupPreferences2 != null) {
            return false;
        }
        ReferralInfoResponse referralInfo = getReferralInfo();
        ReferralInfoResponse referralInfo2 = group.getReferralInfo();
        if (referralInfo != null ? !referralInfo.equals(referralInfo2) : referralInfo2 != null) {
            return false;
        }
        String kind = getKind();
        String kind2 = group.getKind();
        return kind != null ? kind.equals(kind2) : kind2 == null;
    }

    public Profile getAddedBy() {
        return this.addedBy;
    }

    public FeaturedMember getCallerProfile() {
        return this.callerProfile;
    }

    public ContentAggregations getContentAggregations() {
        return this.contentAggregations;
    }

    public String getCoverNodeId() {
        return this.coverNodeId;
    }

    public GroupCoverPhoto getCoverPhoto() {
        return this.coverPhoto;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public String getDescription() {
        return this.description;
    }

    public List<FeaturedMember> getFeaturedMembers() {
        return this.featuredMembers;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public GroupRestriction getGroupPreferences() {
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

    public int getMemberCount() {
        return this.memberCount;
    }

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public String getModifiedDate() {
        return this.modifiedDate;
    }

    public String getName() {
        return this.name;
    }

    public String getNameType() {
        return this.nameType;
    }

    public ReferralInfoResponse getReferralInfo() {
        return this.referralInfo;
    }

    public ContentAggregations getUnseenContentAggregations() {
        return this.unseenContentAggregations;
    }

    public long getVersion() {
        return this.version;
    }

    public int hashCode() {
        String coverNodeId = getCoverNodeId();
        int i = 43;
        int hashCode = coverNodeId == null ? 43 : coverNodeId.hashCode();
        GroupCoverPhoto coverPhoto = getCoverPhoto();
        int hashCode2 = ((hashCode + 59) * 59) + (coverPhoto == null ? 43 : coverPhoto.hashCode());
        String createdBy = getCreatedBy();
        int hashCode3 = (hashCode2 * 59) + (createdBy == null ? 43 : createdBy.hashCode());
        String createdDate = getCreatedDate();
        int hashCode4 = (hashCode3 * 59) + (createdDate == null ? 43 : createdDate.hashCode());
        String description = getDescription();
        int hashCode5 = (hashCode4 * 59) + (description == null ? 43 : description.hashCode());
        List<FeaturedMember> featuredMembers = getFeaturedMembers();
        int hashCode6 = (hashCode5 * 59) + (featuredMembers == null ? 43 : featuredMembers.hashCode());
        String groupId = getGroupId();
        int hashCode7 = (hashCode6 * 59) + (groupId == null ? 43 : groupId.hashCode());
        String modifiedBy = getModifiedBy();
        int hashCode8 = (hashCode7 * 59) + (modifiedBy == null ? 43 : modifiedBy.hashCode());
        String modifiedDate = getModifiedDate();
        int hashCode9 = (hashCode8 * 59) + (modifiedDate == null ? 43 : modifiedDate.hashCode());
        String name = getName();
        int hashCode10 = (hashCode9 * 59) + (name == null ? 43 : name.hashCode());
        String nameType = getNameType();
        int hashCode11 = (hashCode10 * 59) + (nameType == null ? 43 : nameType.hashCode());
        FeaturedMember callerProfile = getCallerProfile();
        int hashCode12 = (hashCode11 * 59) + (callerProfile == null ? 43 : callerProfile.hashCode());
        ContentAggregations unseenContentAggregations = getUnseenContentAggregations();
        int hashCode13 = (hashCode12 * 59) + (unseenContentAggregations == null ? 43 : unseenContentAggregations.hashCode());
        long version = getVersion();
        int i2 = (hashCode13 * 59) + ((int) (version ^ (version >>> 32)));
        ContentAggregations contentAggregations = getContentAggregations();
        int memberCount = getMemberCount() + (((i2 * 59) + (contentAggregations == null ? 43 : contentAggregations.hashCode())) * 59);
        Profile addedBy = getAddedBy();
        int hashCode14 = (memberCount * 59) + (addedBy == null ? 43 : addedBy.hashCode());
        String lastViewedDate = getLastViewedDate();
        int hashCode15 = (hashCode14 * 59) + (lastViewedDate == null ? 43 : lastViewedDate.hashCode());
        String inviteCallToAction = getInviteCallToAction();
        int hashCode16 = (hashCode15 * 59) + (inviteCallToAction == null ? 43 : inviteCallToAction.hashCode());
        String inviteDescription = getInviteDescription();
        int hashCode17 = (hashCode16 * 59) + (inviteDescription == null ? 43 : inviteDescription.hashCode());
        GroupRestriction groupPreferences = getGroupPreferences();
        int hashCode18 = (hashCode17 * 59) + (groupPreferences == null ? 43 : groupPreferences.hashCode());
        ReferralInfoResponse referralInfo = getReferralInfo();
        int hashCode19 = (hashCode18 * 59) + (referralInfo == null ? 43 : referralInfo.hashCode());
        String kind = getKind();
        int i3 = hashCode19 * 59;
        if (kind != null) {
            i = kind.hashCode();
        }
        return i3 + i;
    }

    public void setAddedBy(Profile profile) {
        this.addedBy = profile;
    }

    public void setCallerProfile(FeaturedMember featuredMember) {
        this.callerProfile = featuredMember;
    }

    public void setContentAggregations(ContentAggregations contentAggregations) {
        this.contentAggregations = contentAggregations;
    }

    public void setCoverNodeId(String str) {
        this.coverNodeId = str;
    }

    public void setCoverPhoto(GroupCoverPhoto groupCoverPhoto) {
        this.coverPhoto = groupCoverPhoto;
    }

    public void setCreatedBy(String str) {
        this.createdBy = str;
    }

    public void setCreatedDate(String str) {
        this.createdDate = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setFeaturedMembers(List<FeaturedMember> list) {
        this.featuredMembers = list;
    }

    public void setGroupId(String str) {
        this.groupId = str;
    }

    public void setGroupPreferences(GroupRestriction groupRestriction) {
        this.groupPreferences = groupRestriction;
    }

    public void setInviteCallToAction(String str) {
        this.inviteCallToAction = str;
    }

    public void setInviteDescription(String str) {
        this.inviteDescription = str;
    }

    public void setKind(String str) {
        this.kind = str;
    }

    public void setLastViewedDate(String str) {
        this.lastViewedDate = str;
    }

    public void setMemberCount(int i) {
        this.memberCount = i;
    }

    public void setModifiedBy(String str) {
        this.modifiedBy = str;
    }

    public void setModifiedDate(String str) {
        this.modifiedDate = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setNameType(String str) {
        this.nameType = str;
    }

    public void setReferralInfo(ReferralInfoResponse referralInfoResponse) {
        this.referralInfo = referralInfoResponse;
    }

    public void setUnseenContentAggregations(ContentAggregations contentAggregations) {
        this.unseenContentAggregations = contentAggregations;
    }

    public void setVersion(long j) {
        this.version = j;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Group(coverNodeId=");
        outline107.append(getCoverNodeId());
        outline107.append(", coverPhoto=");
        outline107.append(getCoverPhoto());
        outline107.append(", createdBy=");
        outline107.append(getCreatedBy());
        outline107.append(", createdDate=");
        outline107.append(getCreatedDate());
        outline107.append(", description=");
        outline107.append(getDescription());
        outline107.append(", featuredMembers=");
        outline107.append(getFeaturedMembers());
        outline107.append(", groupId=");
        outline107.append(getGroupId());
        outline107.append(", modifiedBy=");
        outline107.append(getModifiedBy());
        outline107.append(", modifiedDate=");
        outline107.append(getModifiedDate());
        outline107.append(", name=");
        outline107.append(getName());
        outline107.append(", nameType=");
        outline107.append(getNameType());
        outline107.append(", callerProfile=");
        outline107.append(getCallerProfile());
        outline107.append(", unseenContentAggregations=");
        outline107.append(getUnseenContentAggregations());
        outline107.append(", version=");
        outline107.append(getVersion());
        outline107.append(", contentAggregations=");
        outline107.append(getContentAggregations());
        outline107.append(", memberCount=");
        outline107.append(getMemberCount());
        outline107.append(", addedBy=");
        outline107.append(getAddedBy());
        outline107.append(", lastViewedDate=");
        outline107.append(getLastViewedDate());
        outline107.append(", inviteCallToAction=");
        outline107.append(getInviteCallToAction());
        outline107.append(", inviteDescription=");
        outline107.append(getInviteDescription());
        outline107.append(", groupPreferences=");
        outline107.append(getGroupPreferences());
        outline107.append(", referralInfo=");
        outline107.append(getReferralInfo());
        outline107.append(", kind=");
        outline107.append(getKind());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(Group group) {
        if (group == null) {
            return -1;
        }
        if (group == this) {
            return 0;
        }
        int compare = ObjectComparator.compare(getCoverNodeId(), group.getCoverNodeId());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getCoverPhoto(), group.getCoverPhoto());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getCreatedBy(), group.getCreatedBy());
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(getCreatedDate(), group.getCreatedDate());
        if (compare4 != 0) {
            return compare4;
        }
        int compare5 = ObjectComparator.compare(getDescription(), group.getDescription());
        if (compare5 != 0) {
            return compare5;
        }
        int compareCollections = ObjectComparator.compareCollections(getFeaturedMembers(), group.getFeaturedMembers());
        if (compareCollections != 0) {
            return compareCollections;
        }
        int compare6 = ObjectComparator.compare(getGroupId(), group.getGroupId());
        if (compare6 != 0) {
            return compare6;
        }
        int compare7 = ObjectComparator.compare(getModifiedBy(), group.getModifiedBy());
        if (compare7 != 0) {
            return compare7;
        }
        int compare8 = ObjectComparator.compare(getModifiedDate(), group.getModifiedDate());
        if (compare8 != 0) {
            return compare8;
        }
        int compare9 = ObjectComparator.compare(getName(), group.getName());
        if (compare9 != 0) {
            return compare9;
        }
        int compare10 = ObjectComparator.compare(getNameType(), group.getNameType());
        if (compare10 != 0) {
            return compare10;
        }
        int compare11 = ObjectComparator.compare(getCallerProfile(), group.getCallerProfile());
        if (compare11 != 0) {
            return compare11;
        }
        int compare12 = ObjectComparator.compare(getUnseenContentAggregations(), group.getUnseenContentAggregations());
        if (compare12 != 0) {
            return compare12;
        }
        if (getVersion() > group.getVersion()) {
            return 1;
        }
        if (getVersion() < group.getVersion()) {
            return -1;
        }
        int compare13 = ObjectComparator.compare(getContentAggregations(), group.getContentAggregations());
        if (compare13 != 0) {
            return compare13;
        }
        if (getMemberCount() > group.getMemberCount()) {
            return 1;
        }
        if (getMemberCount() < group.getMemberCount()) {
            return -1;
        }
        int compare14 = ObjectComparator.compare(getAddedBy(), group.getAddedBy());
        if (compare14 != 0) {
            return compare14;
        }
        int compare15 = ObjectComparator.compare(getLastViewedDate(), group.getLastViewedDate());
        if (compare15 != 0) {
            return compare15;
        }
        int compare16 = ObjectComparator.compare(getInviteCallToAction(), group.getInviteCallToAction());
        if (compare16 != 0) {
            return compare16;
        }
        int compare17 = ObjectComparator.compare(getInviteDescription(), group.getInviteDescription());
        if (compare17 != 0) {
            return compare17;
        }
        int compare18 = ObjectComparator.compare(getGroupPreferences(), group.getGroupPreferences());
        if (compare18 != 0) {
            return compare18;
        }
        int compare19 = ObjectComparator.compare(getReferralInfo(), group.getReferralInfo());
        if (compare19 != 0) {
            return compare19;
        }
        int compare20 = ObjectComparator.compare(getKind(), group.getKind());
        if (compare20 == 0) {
            return 0;
        }
        return compare20;
    }
}
