package com.amazon.clouddrive.extended.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class GetGroupShareResponse extends GroupShareResponse {
    @Override // com.amazon.clouddrive.extended.model.GroupShareResponse
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetGroupShareResponse{} ");
        outline107.append(super.toString());
        return outline107.toString();
    }

    public GetGroupShareResponse withInviteUrl(String str) {
        setInviteUrl(str);
        return this;
    }

    public GetGroupShareResponse withShareToken(String str) {
        setShareToken(str);
        return this;
    }

    public GetGroupShareResponse withShareUrl(String str) {
        setShareUrl(str);
        return this;
    }
}
