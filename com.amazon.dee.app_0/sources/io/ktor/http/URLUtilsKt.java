package io.ktor.http;

import androidx.core.app.FrameMetricsAggregator;
import com.amazon.alexa.assetManagementService.model.constants.AssetUrlMappingTableConstants;
import com.amazon.alexa.biloba.utils.WebConstants;
import io.ktor.util.StringValuesKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: URLUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0001\u001a\u000e\u0010\n\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0001\u001a(\u0010\u000b\u001a\u00020\f*\u00060\rj\u0002`\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0000\u001a\u0012\u0010\u0014\u001a\u00020\b*\u00020\b2\u0006\u0010\u0015\u001a\u00020\b\u001a\u0012\u0010\u0014\u001a\u00020\b*\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004¨\u0006\u0016"}, d2 = {"fullPath", "", "Lio/ktor/http/Url;", "getFullPath", "(Lio/ktor/http/Url;)Ljava/lang/String;", "hostWithPort", "getHostWithPort", "URLBuilder", "Lio/ktor/http/URLBuilder;", "urlString", AssetUrlMappingTableConstants.URL_COLUMN_NAME, "appendUrlFullPath", "", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "encodedPath", "queryParameters", "Lio/ktor/http/Parameters;", "trailingQuery", "", "takeFrom", "url", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class URLUtilsKt {
    @NotNull
    public static final URLBuilder URLBuilder(@NotNull String urlString) {
        Intrinsics.checkParameterIsNotNull(urlString, "urlString");
        return URLParserKt.takeFrom(new URLBuilder(null, null, 0, null, null, null, null, null, false, FrameMetricsAggregator.EVERY_DURATION, null), urlString);
    }

    @NotNull
    public static final Url Url(@NotNull String urlString) {
        Intrinsics.checkParameterIsNotNull(urlString, "urlString");
        return URLBuilder(urlString).build();
    }

    public static final void appendUrlFullPath(@NotNull Appendable receiver$0, @NotNull String encodedPath, @NotNull Parameters queryParameters, boolean z) {
        boolean startsWith$default;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(encodedPath, "encodedPath");
        Intrinsics.checkParameterIsNotNull(queryParameters, "queryParameters");
        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(encodedPath, "/", false, 2, null);
        if (!startsWith$default) {
            receiver$0.append('/');
        }
        receiver$0.append(encodedPath);
        if (!queryParameters.isEmpty() || z) {
            receiver$0.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
        }
        HttpUrlEncodedKt.formUrlEncodeTo(queryParameters, receiver$0);
    }

    @NotNull
    public static final String getFullPath(@NotNull Url receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        StringBuilder sb = new StringBuilder();
        appendUrlFullPath(sb, receiver$0.getEncodedPath(), receiver$0.getParameters(), receiver$0.getTrailingQuery());
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    @NotNull
    public static final String getHostWithPort(@NotNull Url receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return receiver$0.getHost() + JsonReaderKt.COLON + receiver$0.getPort();
    }

    @NotNull
    public static final URLBuilder takeFrom(@NotNull URLBuilder receiver$0, @NotNull URLBuilder url) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(url, "url");
        receiver$0.setProtocol(url.getProtocol());
        receiver$0.setHost(url.getHost());
        receiver$0.setPort(url.getPort());
        receiver$0.setEncodedPath(url.getEncodedPath());
        receiver$0.setUser(url.getUser());
        receiver$0.setPassword(url.getPassword());
        StringValuesKt.appendAll(receiver$0.getParameters(), url.getParameters());
        receiver$0.setFragment(url.getFragment());
        receiver$0.setTrailingQuery(url.getTrailingQuery());
        return receiver$0;
    }

    @NotNull
    public static final URLBuilder takeFrom(@NotNull URLBuilder receiver$0, @NotNull Url url) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(url, "url");
        receiver$0.setProtocol(url.getProtocol());
        receiver$0.setHost(url.getHost());
        receiver$0.setPort(url.getSpecifiedPort());
        receiver$0.setEncodedPath(url.getEncodedPath());
        receiver$0.setUser(url.getUser());
        receiver$0.setPassword(url.getPassword());
        receiver$0.getParameters().appendAll(url.getParameters());
        receiver$0.setFragment(url.getFragment());
        receiver$0.setTrailingQuery(url.getTrailingQuery());
        return receiver$0;
    }
}
