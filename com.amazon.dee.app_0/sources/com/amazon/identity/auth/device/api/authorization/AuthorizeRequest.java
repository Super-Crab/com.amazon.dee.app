package com.amazon.identity.auth.device.api.authorization;

import android.os.Bundle;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.api.workflow.RequestContext;
import com.amazon.identity.auth.device.interactive.InteractiveRequest;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
/* loaded from: classes12.dex */
public final class AuthorizeRequest extends InteractiveRequest<AuthorizeListener, AuthorizeResult, AuthCancellation, AuthError> {
    static final String EXTRA_REQUESTED_SCOPES = "requestedScopes";
    static final String EXTRA_SHOULD_RETURN_USER_DATA = "shouldReturnUserData";
    static final String REQUEST_TYPE = "com.amazon.identity.auth.device.authorization.request.authorize";
    private String codeChallenge;
    private String codeChallengeMethod;
    private GrantType grantType;
    private List<Scope> scopes;
    private boolean shouldReturnUserData;

    /* loaded from: classes12.dex */
    public static final class Builder extends InteractiveRequest.Builder<AuthorizeRequest> {
        private final AuthorizeRequest request;

        public Builder(RequestContext requestContext) {
            super(requestContext);
            this.request = new AuthorizeRequest(this.requestContext);
        }

        public Builder addScope(Scope scope) {
            this.request.addScope(scope);
            return this;
        }

        public Builder addScopes(Scope... scopeArr) {
            this.request.addScopes(scopeArr);
            return this;
        }

        public Builder forGrantType(GrantType grantType) {
            this.request.setGrantType(grantType);
            return this;
        }

        public Builder shouldReturnUserData(boolean z) {
            this.request.setShouldReturnUserData(z);
            return this;
        }

        public Builder withProofKeyParameters(String str, String str2) {
            this.request.setProofKeyParameters(str, str2);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.identity.auth.device.interactive.InteractiveRequest.Builder
        /* renamed from: build */
        public AuthorizeRequest mo4049build() {
            return this.request;
        }
    }

    /* loaded from: classes12.dex */
    public enum GrantType {
        ACCESS_TOKEN,
        AUTHORIZATION_CODE
    }

    AuthorizeRequest(RequestContext requestContext) {
        super(requestContext);
        this.scopes = new LinkedList();
        this.grantType = GrantType.ACCESS_TOKEN;
        this.shouldReturnUserData = true;
    }

    public void addScope(Scope scope) {
        this.scopes.add(scope);
    }

    public void addScopes(Scope... scopeArr) {
        Collections.addAll(this.scopes, scopeArr);
    }

    public String getCodeChallenge() {
        return this.codeChallenge;
    }

    public String getCodeChallengeMethod() {
        return this.codeChallengeMethod;
    }

    public GrantType getGrantType() {
        return this.grantType;
    }

    @Override // com.amazon.identity.auth.device.interactive.InteractiveRequest
    public final Class<AuthorizeListener> getListenerClass() {
        return AuthorizeListener.class;
    }

    @Override // com.amazon.identity.auth.device.interactive.InteractiveRequest
    public final Bundle getRequestExtras() {
        Bundle bundle = new Bundle();
        String[] strArr = new String[this.scopes.size()];
        for (int i = 0; i < this.scopes.size(); i++) {
            strArr[i] = this.scopes.get(i).getName();
        }
        bundle.putStringArray(EXTRA_REQUESTED_SCOPES, strArr);
        bundle.putBoolean(EXTRA_SHOULD_RETURN_USER_DATA, shouldReturnUserData());
        return bundle;
    }

    @Override // com.amazon.identity.auth.device.interactive.InteractiveAPI
    public final String getRequestType() {
        return "com.amazon.identity.auth.device.authorization.request.authorize";
    }

    public List<Scope> getScopes() {
        return this.scopes;
    }

    public void setCodeChallenge(String str) {
        this.codeChallenge = str;
    }

    public void setCodeChallengeMethod(String str) {
        this.codeChallengeMethod = str;
    }

    public void setGrantType(GrantType grantType) {
        this.grantType = grantType;
    }

    public void setProofKeyParameters(String str, String str2) {
        setCodeChallenge(str);
        setCodeChallengeMethod(str2);
    }

    public void setScopes(List<Scope> list) {
        this.scopes = list;
    }

    public void setShouldReturnUserData(boolean z) {
        this.shouldReturnUserData = z;
    }

    public boolean shouldReturnUserData() {
        return this.shouldReturnUserData;
    }
}
