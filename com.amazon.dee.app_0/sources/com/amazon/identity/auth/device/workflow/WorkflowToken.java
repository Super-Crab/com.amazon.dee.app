package com.amazon.identity.auth.device.workflow;

import android.net.Uri;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.utils.JSONUtils;
import com.amazon.identity.auth.device.utils.JWTDecoder;
import java.util.List;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class WorkflowToken {
    private static final String JWT_CLIENT_ID_KEY = "clientId";
    private static final String JWT_EXPECTED_ISSUER = "Amazon";
    private static final String JWT_EXPECTED_TYPE = "WorkflowToken";
    private static final String JWT_ISSUER_KEY = "iss";
    private static final String JWT_SCOPES_KEY = "scopes";
    private static final String JWT_TYPE_KEY = "type";
    private static final String JWT_WORKFLOW_ENDPOINTS_KEY = "workflowEndpoints";
    private final String clientId;
    private final String[] scopes;
    private final List<String> workflowEndpoints;

    public WorkflowToken(String str) throws AuthError {
        JSONObject decode = new JWTDecoder().decode(str);
        if (decode != null) {
            if (decode.optString("type").equals(JWT_EXPECTED_TYPE)) {
                if (decode.optString(JWT_ISSUER_KEY).equals("Amazon")) {
                    this.clientId = decode.optString("clientId");
                    if (this.clientId != null) {
                        this.scopes = JSONUtils.getStringArray(decode, "scopes");
                        if (this.scopes != null) {
                            this.workflowEndpoints = JSONUtils.getStringList(decode, JWT_WORKFLOW_ENDPOINTS_KEY);
                            if (this.workflowEndpoints == null) {
                                throw new AuthError("Workflow Token missing endpoints", AuthError.ERROR_TYPE.ERROR_ACCESS_DENIED);
                            }
                            return;
                        }
                        throw new AuthError("Workflow Token missing scopes", AuthError.ERROR_TYPE.ERROR_ACCESS_DENIED);
                    }
                    throw new AuthError("Workflow Token missing clientId", AuthError.ERROR_TYPE.ERROR_ACCESS_DENIED);
                }
                throw new AuthError("Workflow Token has invalid issuer", AuthError.ERROR_TYPE.ERROR_ACCESS_DENIED);
            }
            throw new AuthError("Workflow Token has invalid type", AuthError.ERROR_TYPE.ERROR_ACCESS_DENIED);
        }
        throw new AuthError("Workflow Token is invalid", AuthError.ERROR_TYPE.ERROR_ACCESS_DENIED);
    }

    private Uri normalizeWorkflowUrl(String str) {
        return Uri.parse(str).buildUpon().query("").fragment("").build();
    }

    public void assertWorkflowUrlIsAllowed(String str) throws AuthError {
        if (this.workflowEndpoints.contains(normalizeWorkflowUrl(str).toString())) {
            return;
        }
        throw new AuthError(String.format("Workflow URL %s is not allowed", str), AuthError.ERROR_TYPE.ERROR_ACCESS_DENIED);
    }

    public String getClientId() {
        return this.clientId;
    }

    public String[] getScopes() {
        return this.scopes;
    }
}
