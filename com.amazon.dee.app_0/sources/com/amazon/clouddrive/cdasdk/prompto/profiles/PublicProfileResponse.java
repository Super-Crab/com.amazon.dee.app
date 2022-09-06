package com.amazon.clouddrive.cdasdk.prompto.profiles;
/* loaded from: classes11.dex */
public class PublicProfileResponse extends ProfileResponse {
    @Override // com.amazon.clouddrive.cdasdk.prompto.profiles.ProfileResponse
    protected boolean canEqual(Object obj) {
        return obj instanceof PublicProfileResponse;
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.profiles.ProfileResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof PublicProfileResponse) && ((PublicProfileResponse) obj).canEqual(this) && super.equals(obj);
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.profiles.ProfileResponse
    public int hashCode() {
        return super.hashCode();
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.profiles.ProfileResponse
    public String toString() {
        return "PublicProfileResponse()";
    }
}
