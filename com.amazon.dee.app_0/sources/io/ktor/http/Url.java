package io.ktor.http;

import com.amazon.identity.auth.device.api.MAPAccountManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: URLBuilder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b!\b\u0086\b\u0018\u0000 /2\u00020\u0001:\u0001/BQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\u0007HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\t\u0010%\u001a\u00020\nHÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010)\u001a\u00020\u000fHÆ\u0003Jg\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u000fHÆ\u0001J\u0013\u0010+\u001a\u00020\u000f2\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010-\u001a\u00020\u0007HÖ\u0001J\b\u0010.\u001a\u00020\u0005H\u0016R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0011\u0010\u0018\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0012¨\u00060"}, d2 = {"Lio/ktor/http/Url;", "", "protocol", "Lio/ktor/http/URLProtocol;", "host", "", "specifiedPort", "", "encodedPath", "parameters", "Lio/ktor/http/Parameters;", "fragment", "user", MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD, "trailingQuery", "", "(Lio/ktor/http/URLProtocol;Ljava/lang/String;ILjava/lang/String;Lio/ktor/http/Parameters;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getEncodedPath", "()Ljava/lang/String;", "getFragment", "getHost", "getParameters", "()Lio/ktor/http/Parameters;", "getPassword", "port", "getPort", "()I", "getProtocol", "()Lio/ktor/http/URLProtocol;", "getSpecifiedPort", "getTrailingQuery", "()Z", "getUser", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "Companion", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class Url {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String encodedPath;
    @NotNull
    private final String fragment;
    @NotNull
    private final String host;
    @NotNull
    private final Parameters parameters;
    @Nullable
    private final String password;
    @NotNull
    private final URLProtocol protocol;
    private final int specifiedPort;
    private final boolean trailingQuery;
    @Nullable
    private final String user;

    /* compiled from: URLBuilder.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/http/Url$Companion;", "", "()V", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public Url(@NotNull URLProtocol protocol, @NotNull String host, int i, @NotNull String encodedPath, @NotNull Parameters parameters, @NotNull String fragment, @Nullable String str, @Nullable String str2, boolean z) {
        Intrinsics.checkParameterIsNotNull(protocol, "protocol");
        Intrinsics.checkParameterIsNotNull(host, "host");
        Intrinsics.checkParameterIsNotNull(encodedPath, "encodedPath");
        Intrinsics.checkParameterIsNotNull(parameters, "parameters");
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        this.protocol = protocol;
        this.host = host;
        this.specifiedPort = i;
        this.encodedPath = encodedPath;
        this.parameters = parameters;
        this.fragment = fragment;
        this.user = str;
        this.password = str2;
        this.trailingQuery = z;
        int i2 = this.specifiedPort;
        boolean z2 = true;
        if ((1 > i2 || 65536 < i2) && this.specifiedPort != 0) {
            z2 = false;
        }
        if (z2) {
            return;
        }
        throw new IllegalArgumentException("port must be between 1 and 65536, or 0 if not set".toString());
    }

    @NotNull
    public final URLProtocol component1() {
        return this.protocol;
    }

    @NotNull
    public final String component2() {
        return this.host;
    }

    public final int component3() {
        return this.specifiedPort;
    }

    @NotNull
    public final String component4() {
        return this.encodedPath;
    }

    @NotNull
    public final Parameters component5() {
        return this.parameters;
    }

    @NotNull
    public final String component6() {
        return this.fragment;
    }

    @Nullable
    public final String component7() {
        return this.user;
    }

    @Nullable
    public final String component8() {
        return this.password;
    }

    public final boolean component9() {
        return this.trailingQuery;
    }

    @NotNull
    public final Url copy(@NotNull URLProtocol protocol, @NotNull String host, int i, @NotNull String encodedPath, @NotNull Parameters parameters, @NotNull String fragment, @Nullable String str, @Nullable String str2, boolean z) {
        Intrinsics.checkParameterIsNotNull(protocol, "protocol");
        Intrinsics.checkParameterIsNotNull(host, "host");
        Intrinsics.checkParameterIsNotNull(encodedPath, "encodedPath");
        Intrinsics.checkParameterIsNotNull(parameters, "parameters");
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        return new Url(protocol, host, i, encodedPath, parameters, fragment, str, str2, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof Url) {
                Url url = (Url) obj;
                if (Intrinsics.areEqual(this.protocol, url.protocol) && Intrinsics.areEqual(this.host, url.host)) {
                    if ((this.specifiedPort == url.specifiedPort) && Intrinsics.areEqual(this.encodedPath, url.encodedPath) && Intrinsics.areEqual(this.parameters, url.parameters) && Intrinsics.areEqual(this.fragment, url.fragment) && Intrinsics.areEqual(this.user, url.user) && Intrinsics.areEqual(this.password, url.password)) {
                        if (this.trailingQuery == url.trailingQuery) {
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    @NotNull
    public final String getEncodedPath() {
        return this.encodedPath;
    }

    @NotNull
    public final String getFragment() {
        return this.fragment;
    }

    @NotNull
    public final String getHost() {
        return this.host;
    }

    @NotNull
    public final Parameters getParameters() {
        return this.parameters;
    }

    @Nullable
    public final String getPassword() {
        return this.password;
    }

    public final int getPort() {
        Integer valueOf = Integer.valueOf(this.specifiedPort);
        if (valueOf.intValue() == 0) {
            valueOf = null;
        }
        return valueOf != null ? valueOf.intValue() : this.protocol.getDefaultPort();
    }

    @NotNull
    public final URLProtocol getProtocol() {
        return this.protocol;
    }

    public final int getSpecifiedPort() {
        return this.specifiedPort;
    }

    public final boolean getTrailingQuery() {
        return this.trailingQuery;
    }

    @Nullable
    public final String getUser() {
        return this.user;
    }

    public int hashCode() {
        URLProtocol uRLProtocol = this.protocol;
        int i = 0;
        int hashCode = (uRLProtocol != null ? uRLProtocol.hashCode() : 0) * 31;
        String str = this.host;
        int hashCode2 = (((hashCode + (str != null ? str.hashCode() : 0)) * 31) + this.specifiedPort) * 31;
        String str2 = this.encodedPath;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        Parameters parameters = this.parameters;
        int hashCode4 = (hashCode3 + (parameters != null ? parameters.hashCode() : 0)) * 31;
        String str3 = this.fragment;
        int hashCode5 = (hashCode4 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.user;
        int hashCode6 = (hashCode5 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.password;
        if (str5 != null) {
            i = str5.hashCode();
        }
        int i2 = (hashCode6 + i) * 31;
        boolean z = this.trailingQuery;
        if (z) {
            z = true;
        }
        int i3 = z ? 1 : 0;
        int i4 = z ? 1 : 0;
        return i2 + i3;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.protocol.getName());
        sb.append("://");
        String str = this.user;
        if (str != null) {
            sb.append(str);
            if (this.password != null) {
                sb.append(JsonReaderKt.COLON);
                sb.append(this.password);
            }
            sb.append('@');
        }
        if (this.specifiedPort == 0) {
            sb.append(this.host);
        } else {
            sb.append(URLUtilsKt.getHostWithPort(this));
        }
        sb.append(URLUtilsKt.getFullPath(this));
        if (this.fragment.length() > 0) {
            sb.append('#');
            sb.append(this.fragment);
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }
}
