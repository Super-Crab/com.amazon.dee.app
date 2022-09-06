package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.ObjectComparator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes11.dex */
public final class GetGroupShareInfoResponse implements Comparable<GetGroupShareInfoResponse> {
    private final ContentAggregations contentAggregations;
    private final GroupCoverPhoto coverPhoto;
    private final String createdDate;
    private final List<FeaturedMember> featuredMembers;
    private final String groupId;
    private final Boolean hasMembership;
    private final int memberCount;
    private final String modifiedDate;
    private final String name;
    private final String nameType;
    private final Profile ownerProfile;
    private final GroupPrivacyPreferences privacyPreferences;
    private final long version;

    /* loaded from: classes11.dex */
    public static class GetGroupShareInfoResponseBuilder {
        private ContentAggregations contentAggregations;
        private GroupCoverPhoto coverPhoto;
        private String createdDate;
        private List<FeaturedMember> featuredMembers;
        private String groupId;
        private Boolean hasMembership;
        private int memberCount;
        private String modifiedDate;
        private String name;
        private String nameType;
        private Profile ownerProfile;
        private GroupPrivacyPreferences privacyPreferences;
        private long version;

        GetGroupShareInfoResponseBuilder() {
        }

        public GetGroupShareInfoResponse build() {
            return new GetGroupShareInfoResponse(this.coverPhoto, this.createdDate, this.featuredMembers, this.groupId, this.modifiedDate, this.name, this.nameType, this.version, this.contentAggregations, this.memberCount, this.hasMembership, this.privacyPreferences, this.ownerProfile);
        }

        public GetGroupShareInfoResponseBuilder contentAggregations(ContentAggregations contentAggregations) {
            this.contentAggregations = contentAggregations;
            return this;
        }

        public GetGroupShareInfoResponseBuilder coverPhoto(GroupCoverPhoto groupCoverPhoto) {
            this.coverPhoto = groupCoverPhoto;
            return this;
        }

        public GetGroupShareInfoResponseBuilder createdDate(String str) {
            this.createdDate = str;
            return this;
        }

        public GetGroupShareInfoResponseBuilder featuredMembers(List<FeaturedMember> list) {
            this.featuredMembers = list;
            return this;
        }

        public GetGroupShareInfoResponseBuilder groupId(String str) {
            this.groupId = str;
            return this;
        }

        public GetGroupShareInfoResponseBuilder hasMembership(Boolean bool) {
            this.hasMembership = bool;
            return this;
        }

        public GetGroupShareInfoResponseBuilder memberCount(int i) {
            this.memberCount = i;
            return this;
        }

        public GetGroupShareInfoResponseBuilder modifiedDate(String str) {
            this.modifiedDate = str;
            return this;
        }

        public GetGroupShareInfoResponseBuilder name(String str) {
            this.name = str;
            return this;
        }

        public GetGroupShareInfoResponseBuilder nameType(String str) {
            this.nameType = str;
            return this;
        }

        public GetGroupShareInfoResponseBuilder ownerProfile(Profile profile) {
            this.ownerProfile = profile;
            return this;
        }

        public GetGroupShareInfoResponseBuilder privacyPreferences(GroupPrivacyPreferences groupPrivacyPreferences) {
            this.privacyPreferences = groupPrivacyPreferences;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetGroupShareInfoResponse.GetGroupShareInfoResponseBuilder(coverPhoto=");
            outline107.append(this.coverPhoto);
            outline107.append(", createdDate=");
            outline107.append(this.createdDate);
            outline107.append(", featuredMembers=");
            outline107.append(this.featuredMembers);
            outline107.append(", groupId=");
            outline107.append(this.groupId);
            outline107.append(", modifiedDate=");
            outline107.append(this.modifiedDate);
            outline107.append(", name=");
            outline107.append(this.name);
            outline107.append(", nameType=");
            outline107.append(this.nameType);
            outline107.append(", version=");
            outline107.append(this.version);
            outline107.append(", contentAggregations=");
            outline107.append(this.contentAggregations);
            outline107.append(", memberCount=");
            outline107.append(this.memberCount);
            outline107.append(", hasMembership=");
            outline107.append(this.hasMembership);
            outline107.append(", privacyPreferences=");
            outline107.append(this.privacyPreferences);
            outline107.append(", ownerProfile=");
            outline107.append(this.ownerProfile);
            outline107.append(")");
            return outline107.toString();
        }

        public GetGroupShareInfoResponseBuilder version(long j) {
            this.version = j;
            return this;
        }
    }

    GetGroupShareInfoResponse(GroupCoverPhoto groupCoverPhoto, String str, List<FeaturedMember> list, String str2, String str3, String str4, String str5, long j, ContentAggregations contentAggregations, int i, Boolean bool, GroupPrivacyPreferences groupPrivacyPreferences, Profile profile) {
        this.coverPhoto = groupCoverPhoto;
        this.createdDate = str;
        this.featuredMembers = list;
        this.groupId = str2;
        this.modifiedDate = str3;
        this.name = str4;
        this.nameType = str5;
        this.version = j;
        this.contentAggregations = contentAggregations;
        this.memberCount = i;
        this.hasMembership = bool;
        this.privacyPreferences = groupPrivacyPreferences;
        this.ownerProfile = profile;
    }

    public static GetGroupShareInfoResponseBuilder builder() {
        return new GetGroupShareInfoResponseBuilder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetGroupShareInfoResponse)) {
            return false;
        }
        GetGroupShareInfoResponse getGroupShareInfoResponse = (GetGroupShareInfoResponse) obj;
        GroupCoverPhoto coverPhoto = getCoverPhoto();
        GroupCoverPhoto coverPhoto2 = getGroupShareInfoResponse.getCoverPhoto();
        if (coverPhoto != null ? !coverPhoto.equals(coverPhoto2) : coverPhoto2 != null) {
            return false;
        }
        String createdDate = getCreatedDate();
        String createdDate2 = getGroupShareInfoResponse.getCreatedDate();
        if (createdDate != null ? !createdDate.equals(createdDate2) : createdDate2 != null) {
            return false;
        }
        List<FeaturedMember> featuredMembers = getFeaturedMembers();
        List<FeaturedMember> featuredMembers2 = getGroupShareInfoResponse.getFeaturedMembers();
        if (featuredMembers != null ? !featuredMembers.equals(featuredMembers2) : featuredMembers2 != null) {
            return false;
        }
        String groupId = getGroupId();
        String groupId2 = getGroupShareInfoResponse.getGroupId();
        if (groupId != null ? !groupId.equals(groupId2) : groupId2 != null) {
            return false;
        }
        String modifiedDate = getModifiedDate();
        String modifiedDate2 = getGroupShareInfoResponse.getModifiedDate();
        if (modifiedDate != null ? !modifiedDate.equals(modifiedDate2) : modifiedDate2 != null) {
            return false;
        }
        String name = getName();
        String name2 = getGroupShareInfoResponse.getName();
        if (name != null ? !name.equals(name2) : name2 != null) {
            return false;
        }
        String nameType = getNameType();
        String nameType2 = getGroupShareInfoResponse.getNameType();
        if (nameType != null ? !nameType.equals(nameType2) : nameType2 != null) {
            return false;
        }
        if (getVersion() != getGroupShareInfoResponse.getVersion()) {
            return false;
        }
        ContentAggregations contentAggregations = getContentAggregations();
        ContentAggregations contentAggregations2 = getGroupShareInfoResponse.getContentAggregations();
        if (contentAggregations != null ? !contentAggregations.equals(contentAggregations2) : contentAggregations2 != null) {
            return false;
        }
        if (getMemberCount() != getGroupShareInfoResponse.getMemberCount()) {
            return false;
        }
        Boolean hasMembership = getHasMembership();
        Boolean hasMembership2 = getGroupShareInfoResponse.getHasMembership();
        if (hasMembership != null ? !hasMembership.equals(hasMembership2) : hasMembership2 != null) {
            return false;
        }
        GroupPrivacyPreferences privacyPreferences = getPrivacyPreferences();
        GroupPrivacyPreferences privacyPreferences2 = getGroupShareInfoResponse.getPrivacyPreferences();
        if (privacyPreferences != null ? !privacyPreferences.equals(privacyPreferences2) : privacyPreferences2 != null) {
            return false;
        }
        Profile ownerProfile = getOwnerProfile();
        Profile ownerProfile2 = getGroupShareInfoResponse.getOwnerProfile();
        return ownerProfile != null ? ownerProfile.equals(ownerProfile2) : ownerProfile2 == null;
    }

    public ContentAggregations getContentAggregations() {
        return this.contentAggregations;
    }

    public GroupCoverPhoto getCoverPhoto() {
        return this.coverPhoto;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public List<FeaturedMember> getFeaturedMembers() {
        return this.featuredMembers;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public Boolean getHasMembership() {
        return this.hasMembership;
    }

    public int getMemberCount() {
        return this.memberCount;
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

    public Profile getOwnerProfile() {
        return this.ownerProfile;
    }

    public GroupPrivacyPreferences getPrivacyPreferences() {
        return this.privacyPreferences;
    }

    public long getVersion() {
        return this.version;
    }

    public int hashCode() {
        GroupCoverPhoto coverPhoto = getCoverPhoto();
        int i = 43;
        int hashCode = coverPhoto == null ? 43 : coverPhoto.hashCode();
        String createdDate = getCreatedDate();
        int hashCode2 = ((hashCode + 59) * 59) + (createdDate == null ? 43 : createdDate.hashCode());
        List<FeaturedMember> featuredMembers = getFeaturedMembers();
        int hashCode3 = (hashCode2 * 59) + (featuredMembers == null ? 43 : featuredMembers.hashCode());
        String groupId = getGroupId();
        int hashCode4 = (hashCode3 * 59) + (groupId == null ? 43 : groupId.hashCode());
        String modifiedDate = getModifiedDate();
        int hashCode5 = (hashCode4 * 59) + (modifiedDate == null ? 43 : modifiedDate.hashCode());
        String name = getName();
        int hashCode6 = (hashCode5 * 59) + (name == null ? 43 : name.hashCode());
        String nameType = getNameType();
        int hashCode7 = (hashCode6 * 59) + (nameType == null ? 43 : nameType.hashCode());
        long version = getVersion();
        int i2 = (hashCode7 * 59) + ((int) (version ^ (version >>> 32)));
        ContentAggregations contentAggregations = getContentAggregations();
        int memberCount = getMemberCount() + (((i2 * 59) + (contentAggregations == null ? 43 : contentAggregations.hashCode())) * 59);
        Boolean hasMembership = getHasMembership();
        int hashCode8 = (memberCount * 59) + (hasMembership == null ? 43 : hasMembership.hashCode());
        GroupPrivacyPreferences privacyPreferences = getPrivacyPreferences();
        int hashCode9 = (hashCode8 * 59) + (privacyPreferences == null ? 43 : privacyPreferences.hashCode());
        Profile ownerProfile = getOwnerProfile();
        int i3 = hashCode9 * 59;
        if (ownerProfile != null) {
            i = ownerProfile.hashCode();
        }
        return i3 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetGroupShareInfoResponse(coverPhoto=");
        outline107.append(getCoverPhoto());
        outline107.append(", createdDate=");
        outline107.append(getCreatedDate());
        outline107.append(", featuredMembers=");
        outline107.append(getFeaturedMembers());
        outline107.append(", groupId=");
        outline107.append(getGroupId());
        outline107.append(", modifiedDate=");
        outline107.append(getModifiedDate());
        outline107.append(", name=");
        outline107.append(getName());
        outline107.append(", nameType=");
        outline107.append(getNameType());
        outline107.append(", version=");
        outline107.append(getVersion());
        outline107.append(", contentAggregations=");
        outline107.append(getContentAggregations());
        outline107.append(", memberCount=");
        outline107.append(getMemberCount());
        outline107.append(", hasMembership=");
        outline107.append(getHasMembership());
        outline107.append(", privacyPreferences=");
        outline107.append(getPrivacyPreferences());
        outline107.append(", ownerProfile=");
        outline107.append(getOwnerProfile());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(GetGroupShareInfoResponse getGroupShareInfoResponse) {
        if (getGroupShareInfoResponse == null) {
            return -1;
        }
        if (getGroupShareInfoResponse == this) {
            return 0;
        }
        int compare = ObjectComparator.compare(getCoverPhoto(), getGroupShareInfoResponse.getCoverPhoto());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getCreatedDate(), getGroupShareInfoResponse.getCreatedDate());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getFeaturedMembers(), getGroupShareInfoResponse.getFeaturedMembers());
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(getGroupId(), getGroupShareInfoResponse.getGroupId());
        if (compare4 != 0) {
            return compare4;
        }
        int compare5 = ObjectComparator.compare(getModifiedDate(), getGroupShareInfoResponse.getModifiedDate());
        if (compare5 != 0) {
            return compare5;
        }
        int compare6 = ObjectComparator.compare(getName(), getGroupShareInfoResponse.getName());
        if (compare6 != 0) {
            return compare6;
        }
        int compare7 = ObjectComparator.compare(getNameType(), getGroupShareInfoResponse.getNameType());
        if (compare7 != 0) {
            return compare7;
        }
        int compare8 = ObjectComparator.compare(Long.valueOf(getVersion()), Long.valueOf(getGroupShareInfoResponse.getVersion()));
        if (compare8 != 0) {
            return compare8;
        }
        int compare9 = ObjectComparator.compare(getContentAggregations(), getGroupShareInfoResponse.getContentAggregations());
        if (compare9 != 0) {
            return compare9;
        }
        int compare10 = ObjectComparator.compare(Integer.valueOf(getMemberCount()), Integer.valueOf(getGroupShareInfoResponse.getMemberCount()));
        if (compare10 != 0) {
            return compare10;
        }
        int compare11 = ObjectComparator.compare(getHasMembership(), getGroupShareInfoResponse.getHasMembership());
        if (compare11 != 0) {
            return compare11;
        }
        int compare12 = ObjectComparator.compare(getPrivacyPreferences(), getGroupShareInfoResponse.getPrivacyPreferences());
        if (compare12 != 0) {
            return compare12;
        }
        int compare13 = ObjectComparator.compare(getOwnerProfile(), getGroupShareInfoResponse.getOwnerProfile());
        if (compare13 == 0) {
            return 0;
        }
        return compare13;
    }
}
