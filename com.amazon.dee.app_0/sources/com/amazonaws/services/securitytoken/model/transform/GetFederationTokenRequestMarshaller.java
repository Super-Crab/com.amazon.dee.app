package com.amazonaws.services.securitytoken.model.transform;

import com.amazon.identity.auth.map.device.token.MAPCookie;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.services.securitytoken.model.GetFederationTokenRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
/* loaded from: classes13.dex */
public class GetFederationTokenRequestMarshaller implements Marshaller<Request<GetFederationTokenRequest>, GetFederationTokenRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetFederationTokenRequest> marshall(GetFederationTokenRequest getFederationTokenRequest) {
        if (getFederationTokenRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(getFederationTokenRequest, "AWSSecurityTokenService");
            defaultRequest.addParameter("Action", "GetFederationToken");
            defaultRequest.addParameter("Version", "2011-06-15");
            if (getFederationTokenRequest.getName() != null) {
                defaultRequest.addParameter(MAPCookie.KEY_NAME, StringUtils.fromString(getFederationTokenRequest.getName()));
            }
            if (getFederationTokenRequest.getPolicy() != null) {
                defaultRequest.addParameter("Policy", StringUtils.fromString(getFederationTokenRequest.getPolicy()));
            }
            if (getFederationTokenRequest.getDurationSeconds() != null) {
                defaultRequest.addParameter("DurationSeconds", StringUtils.fromInteger(getFederationTokenRequest.getDurationSeconds()));
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(GetFederationTokenRequest)");
    }
}
