package io.ktor.http;

import androidx.core.app.FrameMetricsAggregator;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: URLBuilder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\u0018\u0000 92\u00020\u0001:\u00019Bc\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J#\u0010+\u001a\u0002H,\"\f\b\u0000\u0010,*\u00060-j\u0002`.2\u0006\u0010/\u001a\u0002H,H\u0002¢\u0006\u0002\u00100J\u0006\u00101\u001a\u000202J\u0006\u00103\u001a\u00020\u0005J\u001f\u00104\u001a\u00020\u00002\u0012\u00105\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000506\"\u00020\u0005¢\u0006\u0002\u00107J\u0014\u00104\u001a\u00020\u00002\f\u00105\u001a\b\u0012\u0004\u0012\u00020\u000508R\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\r\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0012\"\u0004\b\u0018\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0012\"\u0004\b\u001c\u0010\u0014R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0012\"\u0004\b*\u0010\u0014¨\u0006:"}, d2 = {"Lio/ktor/http/URLBuilder;", "", "protocol", "Lio/ktor/http/URLProtocol;", "host", "", "port", "", "user", MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD, "encodedPath", "parameters", "Lio/ktor/http/ParametersBuilder;", "fragment", "trailingQuery", "", "(Lio/ktor/http/URLProtocol;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lio/ktor/http/ParametersBuilder;Ljava/lang/String;Z)V", "getEncodedPath", "()Ljava/lang/String;", "setEncodedPath", "(Ljava/lang/String;)V", "getFragment", "setFragment", "getHost", "setHost", "getParameters", "()Lio/ktor/http/ParametersBuilder;", "getPassword", "setPassword", "getPort", "()I", "setPort", "(I)V", "getProtocol", "()Lio/ktor/http/URLProtocol;", "setProtocol", "(Lio/ktor/http/URLProtocol;)V", "getTrailingQuery", "()Z", "setTrailingQuery", "(Z)V", "getUser", "setUser", "appendTo", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "out", "(Ljava/lang/Appendable;)Ljava/lang/Appendable;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lio/ktor/http/Url;", "buildString", RouteParameter.PATH, "components", "", "([Ljava/lang/String;)Lio/ktor/http/URLBuilder;", "", "Companion", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class URLBuilder {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private String encodedPath;
    @NotNull
    private String fragment;
    @NotNull
    private String host;
    @NotNull
    private final ParametersBuilder parameters;
    @Nullable
    private String password;
    private int port;
    @NotNull
    private URLProtocol protocol;
    private boolean trailingQuery;
    @Nullable
    private String user;

    /* compiled from: URLBuilder.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/http/URLBuilder$Companion;", "", "()V", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public URLBuilder() {
        this(null, null, 0, null, null, null, null, null, false, FrameMetricsAggregator.EVERY_DURATION, null);
    }

    public URLBuilder(@NotNull URLProtocol protocol, @NotNull String host, int i, @Nullable String str, @Nullable String str2, @NotNull String encodedPath, @NotNull ParametersBuilder parameters, @NotNull String fragment, boolean z) {
        Intrinsics.checkParameterIsNotNull(protocol, "protocol");
        Intrinsics.checkParameterIsNotNull(host, "host");
        Intrinsics.checkParameterIsNotNull(encodedPath, "encodedPath");
        Intrinsics.checkParameterIsNotNull(parameters, "parameters");
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        this.protocol = protocol;
        this.host = host;
        this.port = i;
        this.user = str;
        this.password = str2;
        this.encodedPath = encodedPath;
        this.parameters = parameters;
        this.fragment = fragment;
        this.trailingQuery = z;
    }

    private final <A extends Appendable> A appendTo(A a) {
        a.append(this.protocol.getName());
        a.append("://");
        String str = this.user;
        boolean z = true;
        if (str != null) {
            a.append(CodecsKt.encodeURLParameter$default(str, false, 1, null));
            String str2 = this.password;
            if (str2 != null) {
                a.append(":");
                a.append(CodecsKt.encodeURLParameter$default(str2, false, 1, null));
            }
            a.append("@");
        }
        a.append(this.host);
        int i = this.port;
        if (i != 0 && i != this.protocol.getDefaultPort()) {
            a.append(":");
            a.append(String.valueOf(this.port));
        }
        URLUtilsKt.appendUrlFullPath(a, this.encodedPath, this.parameters.mo10292build(), this.trailingQuery);
        if (this.fragment.length() <= 0) {
            z = false;
        }
        if (z) {
            a.append('#');
            a.append(CodecsKt.encodeURLQueryComponent$default(this.fragment, false, false, null, 7, null));
        }
        return a;
    }

    @NotNull
    public final Url build() {
        return new Url(this.protocol, this.host, this.port, this.encodedPath, this.parameters.mo10292build(), this.fragment, this.user, this.password, this.trailingQuery);
    }

    @NotNull
    public final String buildString() {
        String sb = ((StringBuilder) appendTo(new StringBuilder(256))).toString();
        Intrinsics.checkExpressionValueIsNotNull(sb, "appendTo(StringBuilder(256)).toString()");
        return sb;
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
    public final ParametersBuilder getParameters() {
        return this.parameters;
    }

    @Nullable
    public final String getPassword() {
        return this.password;
    }

    public final int getPort() {
        return this.port;
    }

    @NotNull
    public final URLProtocol getProtocol() {
        return this.protocol;
    }

    public final boolean getTrailingQuery() {
        return this.trailingQuery;
    }

    @Nullable
    public final String getUser() {
        return this.user;
    }

    @NotNull
    public final URLBuilder path(@NotNull String... components) {
        List<String> asList;
        Intrinsics.checkParameterIsNotNull(components, "components");
        asList = ArraysKt___ArraysJvmKt.asList(components);
        path(asList);
        return this;
    }

    public final void setEncodedPath(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.encodedPath = str;
    }

    public final void setFragment(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.fragment = str;
    }

    public final void setHost(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.host = str;
    }

    public final void setPassword(@Nullable String str) {
        this.password = str;
    }

    public final void setPort(int i) {
        this.port = i;
    }

    public final void setProtocol(@NotNull URLProtocol uRLProtocol) {
        Intrinsics.checkParameterIsNotNull(uRLProtocol, "<set-?>");
        this.protocol = uRLProtocol;
    }

    public final void setTrailingQuery(boolean z) {
        this.trailingQuery = z;
    }

    public final void setUser(@Nullable String str) {
        this.user = str;
    }

    public /* synthetic */ URLBuilder(URLProtocol uRLProtocol, String str, int i, String str2, String str3, String str4, ParametersBuilder parametersBuilder, String str5, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? URLProtocol.Companion.getHTTP() : uRLProtocol, (i2 & 2) != 0 ? AndroidInfoHelpers.DEVICE_LOCALHOST : str, (i2 & 4) != 0 ? 0 : i, (i2 & 8) != 0 ? null : str2, (i2 & 16) != 0 ? null : str3, (i2 & 32) != 0 ? "/" : str4, (i2 & 64) != 0 ? new ParametersBuilder(0, 1, null) : parametersBuilder, (i2 & 128) != 0 ? "" : str5, (i2 & 256) == 0 ? z : false);
    }

    @NotNull
    public final URLBuilder path(@NotNull List<String> components) {
        String joinToString$default;
        Intrinsics.checkParameterIsNotNull(components, "components");
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(components, "/", "/", null, 0, null, URLBuilder$path$1.INSTANCE, 28, null);
        this.encodedPath = joinToString$default;
        return this;
    }
}
