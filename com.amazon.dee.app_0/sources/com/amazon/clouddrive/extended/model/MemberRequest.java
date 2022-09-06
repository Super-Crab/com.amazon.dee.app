package com.amazon.clouddrive.extended.model;
/* loaded from: classes11.dex */
public class MemberRequest {
    private String memberId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MemberRequest)) {
            return false;
        }
        MemberRequest memberRequest = (MemberRequest) obj;
        return getMemberId() != null ? getMemberId().equals(memberRequest.getMemberId()) : memberRequest.getMemberId() == null;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public int hashCode() {
        if (getMemberId() != null) {
            return getMemberId().hashCode();
        }
        return 0;
    }

    public void setMemberId(String str) {
        this.memberId = str;
    }
}
