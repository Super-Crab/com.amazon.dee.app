package com.amazonaws.services.securitytoken.model.transform;

import com.amazonaws.Request;
import com.amazonaws.services.securitytoken.model.AssumedRoleUser;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
class AssumedRoleUserStaxMarshaller {
    private static AssumedRoleUserStaxMarshaller instance;

    AssumedRoleUserStaxMarshaller() {
    }

    public static AssumedRoleUserStaxMarshaller getInstance() {
        if (instance == null) {
            instance = new AssumedRoleUserStaxMarshaller();
        }
        return instance;
    }

    public void marshall(AssumedRoleUser assumedRoleUser, Request<?> request, String str) {
        if (assumedRoleUser.getAssumedRoleId() != null) {
            request.addParameter(GeneratedOutlineSupport1.outline72(str, "AssumedRoleId"), StringUtils.fromString(assumedRoleUser.getAssumedRoleId()));
        }
        if (assumedRoleUser.getArn() != null) {
            request.addParameter(GeneratedOutlineSupport1.outline72(str, "Arn"), StringUtils.fromString(assumedRoleUser.getArn()));
        }
    }
}
