package io.ktor.http;

import java.net.URI;
import java.net.URL;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: URLUtilsJvm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006\u001a\n\u0010\u0007\u001a\u00020\u0004*\u00020\b¨\u0006\t"}, d2 = {"takeFrom", "", "Lio/ktor/http/URLBuilder;", "uri", "Ljava/net/URI;", "url", "Ljava/net/URL;", "toURI", "Lio/ktor/http/Url;", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class URLUtilsJvmKt {
    public static final void takeFrom(@NotNull URLBuilder receiver$0, @NotNull URI uri) {
        List split$default;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(uri, "uri");
        String scheme = uri.getScheme();
        if (scheme != null) {
            receiver$0.setProtocol(URLProtocol.Companion.createOrDefault(scheme));
            receiver$0.setPort(receiver$0.getProtocol().getDefaultPort());
        }
        if (uri.getPort() > 0) {
            receiver$0.setPort(uri.getPort());
        } else {
            String scheme2 = uri.getScheme();
            if (scheme2 != null) {
                int hashCode = scheme2.hashCode();
                if (hashCode != 3213448) {
                    if (hashCode == 99617003 && scheme2.equals("https")) {
                        receiver$0.setPort(443);
                    }
                } else if (scheme2.equals("http")) {
                    receiver$0.setPort(80);
                }
            }
        }
        boolean z = false;
        if (uri.getUserInfo() != null) {
            String userInfo = uri.getUserInfo();
            Intrinsics.checkExpressionValueIsNotNull(userInfo, "uri.userInfo");
            if (userInfo.length() > 0) {
                String userInfo2 = uri.getUserInfo();
                Intrinsics.checkExpressionValueIsNotNull(userInfo2, "uri.userInfo");
                split$default = StringsKt__StringsKt.split$default((CharSequence) userInfo2, new String[]{":"}, false, 0, 6, (Object) null);
                receiver$0.setUser((String) CollectionsKt.first((List<? extends Object>) split$default));
                receiver$0.setPassword((String) CollectionsKt.getOrNull(split$default, 1));
            }
        }
        String host = uri.getHost();
        if (host != null) {
            receiver$0.setHost(host);
        }
        String rawPath = uri.getRawPath();
        if (rawPath != null) {
            if (rawPath.hashCode() == 0 && rawPath.equals("")) {
                rawPath = "/";
            }
            receiver$0.setEncodedPath(rawPath);
        }
        String query = uri.getQuery();
        if (query != null) {
            receiver$0.getParameters().appendAll(QueryKt.parseQueryString$default(query, 0, 0, 6, null));
        }
        String query2 = uri.getQuery();
        if (query2 != null) {
            if (query2.length() == 0) {
                z = true;
            }
            if (z) {
                receiver$0.setTrailingQuery(true);
            }
        }
        String fragment = uri.getFragment();
        if (fragment != null) {
            receiver$0.setFragment(fragment);
        }
    }

    @NotNull
    public static final URI toURI(@NotNull Url receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return new URI(receiver$0.toString());
    }

    public static final void takeFrom(@NotNull URLBuilder receiver$0, @NotNull URL url) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(url, "url");
        URI uri = url.toURI();
        Intrinsics.checkExpressionValueIsNotNull(uri, "url.toURI()");
        takeFrom(receiver$0, uri);
    }
}
