package com.amazonaws.services.securitytoken.model.transform;

import com.amazonaws.Request;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
class CredentialsStaxMarshaller {
    private static CredentialsStaxMarshaller instance;

    CredentialsStaxMarshaller() {
    }

    public static CredentialsStaxMarshaller getInstance() {
        if (instance == null) {
            instance = new CredentialsStaxMarshaller();
        }
        return instance;
    }

    public void marshall(Credentials credentials, Request<?> request, String str) {
        if (credentials.getAccessKeyId() != null) {
            request.addParameter(GeneratedOutlineSupport1.outline72(str, "AccessKeyId"), StringUtils.fromString(credentials.getAccessKeyId()));
        }
        if (credentials.getSecretAccessKey() != null) {
            request.addParameter(GeneratedOutlineSupport1.outline72(str, "SecretAccessKey"), StringUtils.fromString(credentials.getSecretAccessKey()));
        }
        if (credentials.getSessionToken() != null) {
            request.addParameter(GeneratedOutlineSupport1.outline72(str, "SessionToken"), StringUtils.fromString(credentials.getSessionToken()));
        }
        if (credentials.getExpiration() != null) {
            request.addParameter(GeneratedOutlineSupport1.outline72(str, "Expiration"), StringUtils.fromDate(credentials.getExpiration()));
        }
    }
}
