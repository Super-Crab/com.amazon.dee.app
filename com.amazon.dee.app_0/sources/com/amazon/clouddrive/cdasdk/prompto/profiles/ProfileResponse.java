package com.amazon.clouddrive.cdasdk.prompto.profiles;

import com.amazon.clouddrive.cdasdk.prompto.common.Names;
import com.amazon.clouddrive.cdasdk.prompto.photos.PhotoResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class ProfileResponse {
    @JsonProperty("avatar")
    private Avatar avatar;
    @JsonProperty("avatarNodeId")
    private String avatarNodeId;
    @JsonProperty("avatarPhoto")
    private PhotoResponse avatarPhoto;
    @JsonProperty("customerId")
    private String customerId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("names")
    private Names names;

    protected boolean canEqual(Object obj) {
        return obj instanceof ProfileResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ProfileResponse)) {
            return false;
        }
        ProfileResponse profileResponse = (ProfileResponse) obj;
        if (!profileResponse.canEqual(this)) {
            return false;
        }
        String customerId = getCustomerId();
        String customerId2 = profileResponse.getCustomerId();
        if (customerId != null ? !customerId.equals(customerId2) : customerId2 != null) {
            return false;
        }
        String name = getName();
        String name2 = profileResponse.getName();
        if (name != null ? !name.equals(name2) : name2 != null) {
            return false;
        }
        Names names = getNames();
        Names names2 = profileResponse.getNames();
        if (names != null ? !names.equals(names2) : names2 != null) {
            return false;
        }
        String avatarNodeId = getAvatarNodeId();
        String avatarNodeId2 = profileResponse.getAvatarNodeId();
        if (avatarNodeId != null ? !avatarNodeId.equals(avatarNodeId2) : avatarNodeId2 != null) {
            return false;
        }
        PhotoResponse avatarPhoto = getAvatarPhoto();
        PhotoResponse avatarPhoto2 = profileResponse.getAvatarPhoto();
        if (avatarPhoto != null ? !avatarPhoto.equals(avatarPhoto2) : avatarPhoto2 != null) {
            return false;
        }
        Avatar avatar = getAvatar();
        Avatar avatar2 = profileResponse.getAvatar();
        return avatar != null ? avatar.equals(avatar2) : avatar2 == null;
    }

    public Avatar getAvatar() {
        return this.avatar;
    }

    public String getAvatarNodeId() {
        return this.avatarNodeId;
    }

    public PhotoResponse getAvatarPhoto() {
        return this.avatarPhoto;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getName() {
        return this.name;
    }

    public Names getNames() {
        return this.names;
    }

    public int hashCode() {
        String customerId = getCustomerId();
        int i = 43;
        int hashCode = customerId == null ? 43 : customerId.hashCode();
        String name = getName();
        int hashCode2 = ((hashCode + 59) * 59) + (name == null ? 43 : name.hashCode());
        Names names = getNames();
        int hashCode3 = (hashCode2 * 59) + (names == null ? 43 : names.hashCode());
        String avatarNodeId = getAvatarNodeId();
        int hashCode4 = (hashCode3 * 59) + (avatarNodeId == null ? 43 : avatarNodeId.hashCode());
        PhotoResponse avatarPhoto = getAvatarPhoto();
        int hashCode5 = (hashCode4 * 59) + (avatarPhoto == null ? 43 : avatarPhoto.hashCode());
        Avatar avatar = getAvatar();
        int i2 = hashCode5 * 59;
        if (avatar != null) {
            i = avatar.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("avatar")
    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    @JsonProperty("avatarNodeId")
    public void setAvatarNodeId(String str) {
        this.avatarNodeId = str;
    }

    @JsonProperty("avatarPhoto")
    public void setAvatarPhoto(PhotoResponse photoResponse) {
        this.avatarPhoto = photoResponse;
    }

    @JsonProperty("customerId")
    public void setCustomerId(String str) {
        this.customerId = str;
    }

    @JsonProperty("name")
    public void setName(String str) {
        this.name = str;
    }

    @JsonProperty("names")
    public void setNames(Names names) {
        this.names = names;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ProfileResponse(customerId=");
        outline107.append(getCustomerId());
        outline107.append(", name=");
        outline107.append(getName());
        outline107.append(", names=");
        outline107.append(getNames());
        outline107.append(", avatarNodeId=");
        outline107.append(getAvatarNodeId());
        outline107.append(", avatarPhoto=");
        outline107.append(getAvatarPhoto());
        outline107.append(", avatar=");
        outline107.append(getAvatar());
        outline107.append(")");
        return outline107.toString();
    }
}
