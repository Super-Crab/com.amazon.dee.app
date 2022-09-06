package com.amazon.identity.auth.device.api.authorization;

import com.google.android.gms.common.Scopes;
/* loaded from: classes12.dex */
public final class ProfileScope {
    private ProfileScope() {
    }

    public static Scope postalCode() {
        return ScopeFactory.scopeNamed("postal_code");
    }

    public static Scope profile() {
        return ScopeFactory.scopeNamed(Scopes.PROFILE);
    }

    public static Scope userId() {
        return ScopeFactory.scopeNamed("profile:user_id");
    }
}
