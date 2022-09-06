package io.ktor.http.auth;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: AuthScheme.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\u0004H\u0007J\b\u0010\t\u001a\u00020\u0004H\u0007J\b\u0010\n\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lio/ktor/http/auth/AuthScheme;", "", "()V", AuthScheme.Basic, "", AuthScheme.Digest, AuthScheme.Negotiate, AuthScheme.OAuth, "getBasic", "getDigest", "getNegotiate", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class AuthScheme {
    @NotNull
    public static final String Basic = "Basic";
    @NotNull
    public static final String Digest = "Digest";
    public static final AuthScheme INSTANCE = new AuthScheme();
    @NotNull
    public static final String Negotiate = "Negotiate";
    @NotNull
    public static final String OAuth = "OAuth";

    private AuthScheme() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Compatibility")
    @NotNull
    public final /* synthetic */ String getBasic() {
        return Basic;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Compatibility")
    @NotNull
    public final /* synthetic */ String getDigest() {
        return Digest;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Compatibility")
    @NotNull
    public final /* synthetic */ String getNegotiate() {
        return Negotiate;
    }
}
