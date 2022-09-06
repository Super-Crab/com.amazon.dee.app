package com.amazon.alexa.biloba.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
/* loaded from: classes6.dex */
public class RelationshipGroupModel implements Parcelable {
    public static final Parcelable.Creator<RelationshipGroupModel> CREATOR = new Parcelable.Creator<RelationshipGroupModel>() { // from class: com.amazon.alexa.biloba.model.RelationshipGroupModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public RelationshipGroupModel mo918createFromParcel(Parcel parcel) {
            return new RelationshipGroupModel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public RelationshipGroupModel[] mo919newArray(int i) {
            return new RelationshipGroupModel[i];
        }
    };
    private String groupId;
    private String groupName;
    private CareContact groupOwner;
    private String groupType;
    private List<CareContact> members;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || RelationshipGroupModel.class != obj.getClass()) {
            return false;
        }
        RelationshipGroupModel relationshipGroupModel = (RelationshipGroupModel) obj;
        String str = this.groupId;
        if (str == null ? relationshipGroupModel.groupId != null : str.equals(relationshipGroupModel.groupId)) {
            return false;
        }
        String str2 = this.groupName;
        if (str2 == null ? relationshipGroupModel.groupName != null : str2.equals(relationshipGroupModel.groupName)) {
            return false;
        }
        String str3 = this.groupType;
        if (str3 == null ? relationshipGroupModel.groupType != null : str3.equals(relationshipGroupModel.groupType)) {
            return false;
        }
        CareContact careContact = this.groupOwner;
        if (careContact == null ? relationshipGroupModel.groupOwner != null : careContact.equals(relationshipGroupModel.groupOwner)) {
            return false;
        }
        List<CareContact> list = this.members;
        if (list != null) {
            if (relationshipGroupModel.members == null || list.size() != relationshipGroupModel.members.size()) {
                return false;
            }
            for (int i = 0; i < this.members.size(); i++) {
                if (this.members.get(i) != null) {
                    if (this.members.get(i).equals(relationshipGroupModel.members.get(i))) {
                        return false;
                    }
                } else if (relationshipGroupModel.members.get(i) != null) {
                    return false;
                }
            }
        } else if (relationshipGroupModel.members != null) {
            return false;
        }
        return true;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public CareContact getGroupOwner() {
        return this.groupOwner;
    }

    public String getGroupType() {
        return this.groupType;
    }

    public List<CareContact> getMembers() {
        return this.members;
    }

    public int hashCode() {
        String str = this.groupId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.groupName;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.groupType;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        CareContact careContact = this.groupOwner;
        int hashCode4 = (hashCode3 + (careContact != null ? careContact.hashCode() : 0)) * 31;
        List<CareContact> list = this.members;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode4 + i;
    }

    public void setGroupId(String str) {
        this.groupId = str;
    }

    public void setGroupName(String str) {
        this.groupName = str;
    }

    public void setGroupOwner(CareContact careContact) {
        this.groupOwner = careContact;
    }

    public void setGroupType(String str) {
        this.groupType = str;
    }

    public void setMembers(List<CareContact> list) {
        this.members = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.groupId);
        parcel.writeString(this.groupName);
        parcel.writeString(this.groupType);
        parcel.writeParcelable(this.groupOwner, i);
        parcel.writeTypedList(this.members);
    }

    public RelationshipGroupModel() {
    }

    private RelationshipGroupModel(Parcel parcel) {
        this.groupId = parcel.readString();
        this.groupName = parcel.readString();
        this.groupType = parcel.readString();
        this.groupOwner = (CareContact) parcel.readParcelable(CareContact.class.getClassLoader());
        this.members = parcel.createTypedArrayList(CareContact.CREATOR);
    }
}
