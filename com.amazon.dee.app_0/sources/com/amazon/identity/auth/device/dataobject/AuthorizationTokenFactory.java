package com.amazon.identity.auth.device.dataobject;

import com.amazon.identity.auth.device.dataobject.AuthorizationToken;
import com.amazon.identity.auth.device.token.AccessAtzToken;
import com.amazon.identity.auth.device.token.RefreshAtzToken;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public final class AuthorizationTokenFactory {

    /* renamed from: com.amazon.identity.auth.device.dataobject.AuthorizationTokenFactory$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$identity$auth$device$dataobject$AuthorizationToken$AUTHZ_TOKEN_TYPE = new int[AuthorizationToken.AUTHZ_TOKEN_TYPE.values().length];

        static {
            try {
                $SwitchMap$com$amazon$identity$auth$device$dataobject$AuthorizationToken$AUTHZ_TOKEN_TYPE[AuthorizationToken.AUTHZ_TOKEN_TYPE.ACCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$dataobject$AuthorizationToken$AUTHZ_TOKEN_TYPE[AuthorizationToken.AUTHZ_TOKEN_TYPE.REFRESH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private AuthorizationTokenFactory() {
    }

    public static AuthorizationToken getAuthorizationToken(AuthorizationToken.AUTHZ_TOKEN_TYPE authz_token_type) {
        int ordinal = authz_token_type.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return new RefreshAtzToken();
            }
            throw new IllegalArgumentException("Unknown token type for factory " + authz_token_type);
        }
        return new AccessAtzToken();
    }

    public static AuthorizationToken getCopyAuthorizationToken(AuthorizationToken authorizationToken) {
        int ordinal = authorizationToken.getTypeAsEnum().ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return new RefreshAtzToken((RefreshAtzToken) authorizationToken);
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown token type for copy ");
            outline107.append(authorizationToken.getType());
            throw new IllegalArgumentException(outline107.toString());
        }
        return new AccessAtzToken((AccessAtzToken) authorizationToken);
    }
}
