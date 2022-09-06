package com.amazonaws.services.securitytoken.model.transform;

import com.amazonaws.Request;
import com.amazonaws.services.securitytoken.model.FederatedUser;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
class FederatedUserStaxMarshaller {
    private static FederatedUserStaxMarshaller instance;

    FederatedUserStaxMarshaller() {
    }

    public static FederatedUserStaxMarshaller getInstance() {
        if (instance == null) {
            instance = new FederatedUserStaxMarshaller();
        }
        return instance;
    }

    public void marshall(FederatedUser federatedUser, Request<?> request, String str) {
        if (federatedUser.getFederatedUserId() != null) {
            request.addParameter(GeneratedOutlineSupport1.outline72(str, "FederatedUserId"), StringUtils.fromString(federatedUser.getFederatedUserId()));
        }
        if (federatedUser.getArn() != null) {
            request.addParameter(GeneratedOutlineSupport1.outline72(str, "Arn"), StringUtils.fromString(federatedUser.getArn()));
        }
    }
}
